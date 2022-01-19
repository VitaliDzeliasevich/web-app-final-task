package by.training.epam.dao.impl;

import by.training.epam.dao.interfaces.AnalysisDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.AnalysisRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.dao.impl.tableinfo.TableTitle;
import by.training.epam.entity.Analysis;

import org.apache.log4j.Logger;

import java.util.List;

public class AnalysisDAOImpl implements AnalysisDAO {

    private final static Logger log = Logger.getLogger(AnalysisDAOImpl.class);
    private  final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.ANALYZES_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT * FROM %s WHERE %s = ?".formatted(TableTitle.ANALYZES_TABLE, ColumnLabel.PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.ANALYZES_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s) VALUES (?, ?, ?)".formatted(TableTitle.ANALYZES_TABLE,
                    ColumnLabel.PATIENT_ID, ColumnLabel.ANALYSIS_TYPE_ID, ColumnLabel.APPOINTMENT_DATE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.ANALYZES_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?, %s = ?  WHERE id = ?".formatted(
            TableTitle.ANALYZES_TABLE,ColumnLabel.ANALYSIS_LAB_DR_ID, ColumnLabel.EXECUTION_DATE, ColumnLabel.RESULT);

    public AnalysisDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Analysis> getAll() throws DAOException {
        RequestOperator<Analysis> requestOp = AnalysisRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST,connectionPool);
    }

    @Override
    public List<Analysis> getByHistoryId(int id) throws DAOException{
        RequestOperator<Analysis> requestOp = AnalysisRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST,connectionPool,id);
    }

    @Override
    public Analysis getEntityById(int id) throws DAOException {
        RequestOperator<Analysis> requestOp = AnalysisRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST,connectionPool,id).get(0);
    }

    @Override
    public boolean update(Analysis entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.update(UPDATE_REQUEST, connectionPool, entity.getExecutionDate(),entity.getLabDrId()
                ,entity.getResult(), entity.getId());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID,connectionPool,id);
    }

    @Override
    public int create(Analysis entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST,connectionPool,entity.getPatientId(),
                entity.getAnalysisTypeId(), entity.getAppointmentDate());
    }
}
