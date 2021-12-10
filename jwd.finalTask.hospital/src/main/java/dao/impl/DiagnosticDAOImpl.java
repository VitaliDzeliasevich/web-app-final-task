package dao.impl;

import dao.DiagnosticDAO;
import dao.connection.ConnectionPool;
import dao.exeption.DAOException;
import dao.impl.requestoperator.RequestOperator;
import dao.impl.requestoperator.UniversalRequestOperator;
import dao.impl.requestoperator.impl.DiagnosticRequestOperator;
import dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import dao.impl.tableinfo.ColumnLabel;
import dao.impl.tableinfo.TableTitle;
import entity.Diagnostic;

import java.util.List;

public class DiagnosticDAOImpl implements DiagnosticDAO {

    private  final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.DIAGNOSTIC_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT * FROM %s WHERE %s = ?".formatted(TableTitle.DIAGNOSTIC_TABLE, ColumnLabel.DIAGNOSTIC_PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.DIAGNOSTIC_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?, ?, ?, ?, ?)".formatted(TableTitle.DIAGNOSTIC_TABLE,
                    ColumnLabel.ID,ColumnLabel.DIAGNOSTIC_PATIENT_ID,
                    ColumnLabel.DIAGNOSTIC_DIAGNOSTIC_TYPE_ID, ColumnLabel.DIAGNOSTIC_DR_ID,
                    ColumnLabel.APPOINTMENT_DATE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.DIAGNOSTIC_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?  WHERE id = ?".formatted(TableTitle.DIAGNOSTIC_TABLE,
            ColumnLabel.EXECUTION_DATE, ColumnLabel.RESULT);

    private final static RequestOperator<Diagnostic> requestOp = DiagnosticRequestOperator.getInstance();
    private final static UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();

    public DiagnosticDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Diagnostic> getAll() throws DAOException {
        return requestOp.findAll(GET_ALL_REQUEST,connectionPool);
    }

    @Override
    public List<Diagnostic> getByHistoryId(int id) {
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST,connectionPool,id);
    }

    @Override
    public Diagnostic getEntityById(int id) throws DAOException {
        return requestOp.findByParameters(GET_BY_ID_REQUEST,connectionPool,id).get(0);
    }

    @Override
    public boolean update(Diagnostic entity) throws DAOException {
        return universalRequestOp.update(UPDATE_REQUEST, connectionPool, entity.getExecutionDate(), entity.getResult(),
                entity.getId());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return universalRequestOp.delete(DELETE_BY_ID,connectionPool,id);
    }

    @Override
    public boolean create(Diagnostic entity) throws DAOException {
        return universalRequestOp.create(CREATE_REQUEST,connectionPool,entity.getId(),entity.getDiseaseHistoryId(),
                entity.getDiagnosticTypeId(),entity.getDiagnosticDrId(), entity.getAppointmentDate());
    }
}
