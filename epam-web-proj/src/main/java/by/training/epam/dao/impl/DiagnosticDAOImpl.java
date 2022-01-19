package by.training.epam.dao.impl;



import by.training.epam.dao.interfaces.DiagnosticDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.DiagnosticRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.dao.impl.tableinfo.TableTitle;
import by.training.epam.entity.Diagnostic;

import java.util.List;

public class DiagnosticDAOImpl implements DiagnosticDAO {

    private  final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.DIAGNOSTIC_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT * FROM %s WHERE %s = ?".formatted(TableTitle.DIAGNOSTIC_TABLE, ColumnLabel.DIAGNOSTIC_PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.DIAGNOSTIC_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s) VALUES (?, ?, ?)".formatted(TableTitle.DIAGNOSTIC_TABLE,
                    ColumnLabel.DIAGNOSTIC_PATIENT_ID, ColumnLabel.DIAGNOSTIC_DIAGNOSTIC_TYPE_ID,
                    ColumnLabel.APPOINTMENT_DATE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.DIAGNOSTIC_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?  WHERE id = ?".formatted(TableTitle.DIAGNOSTIC_TABLE,
            ColumnLabel.EXECUTION_DATE, ColumnLabel.RESULT);

    public DiagnosticDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Diagnostic> getAll() throws DAOException {
        RequestOperator<Diagnostic> requestOp = DiagnosticRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST,connectionPool);
    }

    @Override
    public List<Diagnostic> getByHistoryId(int id) throws DAOException {
        RequestOperator<Diagnostic> requestOp = DiagnosticRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST,connectionPool,id);
    }

    @Override
    public Diagnostic getEntityById(int id) throws DAOException {
        RequestOperator<Diagnostic> requestOp = DiagnosticRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST,connectionPool,id).get(0);
    }

    @Override
    public boolean update(Diagnostic entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.update(UPDATE_REQUEST, connectionPool, entity.getExecutionDate(), entity.getResult(),
                entity.getId());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID,connectionPool,id);
    }

    @Override
    public int create(Diagnostic entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST,connectionPool,entity.getPatientId(),
                entity.getDiagnosticTypeId(),entity.getAppointmentDate());
    }
}
