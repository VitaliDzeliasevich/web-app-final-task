package dao.impl;

import dao.AnalysisDAO;
import dao.connection.ConnectionPool;
import dao.exeption.DAOException;
import dao.impl.requestoperator.RequestOperator;
import dao.impl.requestoperator.UniversalRequestOperator;
import dao.impl.requestoperator.impl.AnalysisRequestOperator;
import dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import dao.impl.tableinfo.ColumnLabel;
import dao.impl.tableinfo.TableTitle;
import entity.Analysis;

import java.util.List;

public class AnalysisDAOImpl implements AnalysisDAO {

    private  final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.ANALYZES_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT * FROM %s WHERE %s = ?".formatted(TableTitle.ANALYZES_TABLE, ColumnLabel.PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.ANALYZES_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?, ?, ?, ?, ?)".formatted(TableTitle.ANALYZES_TABLE,
                    ColumnLabel.ID,ColumnLabel.PATIENT_ID,
                    ColumnLabel.ANALYSIS_TYPE_ID, ColumnLabel.ANALYSIS_LAB_DR_ID,
                    ColumnLabel.APPOINTMENT_DATE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.ANALYZES_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?  WHERE id = ?".formatted(TableTitle.ANALYZES_TABLE,
            ColumnLabel.EXECUTION_DATE, ColumnLabel.RESULT);

    private final static RequestOperator<Analysis> requestOp = AnalysisRequestOperator.getInstance();
    private final static UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();

    public AnalysisDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Analysis> getAll() throws DAOException {
        return requestOp.findAll(GET_ALL_REQUEST,connectionPool);
    }

    @Override
    public List<Analysis> getByHistoryId(int id) {
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST,connectionPool,id);
    }

    @Override
    public Analysis getEntityById(int id) throws DAOException {
        return requestOp.findByParameters(GET_BY_ID_REQUEST,connectionPool,id).get(0);
    }

    @Override
    public boolean update(Analysis entity) throws DAOException {
        return universalRequestOp.update(UPDATE_REQUEST, connectionPool, entity.getExecutionDate(), entity.getResult(),
                entity.getId());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return universalRequestOp.delete(DELETE_BY_ID,connectionPool,id);
    }

    @Override
    public boolean create(Analysis entity) throws DAOException {
        return universalRequestOp.create(CREATE_REQUEST,connectionPool,entity.getId(),entity.getPatientId(),
                entity.getAnalysisTypeId(),entity.getLabDrId(), entity.getAppointmentDate());
    }
}
