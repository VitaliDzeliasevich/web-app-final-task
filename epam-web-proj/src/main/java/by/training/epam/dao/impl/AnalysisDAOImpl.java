package by.training.epam.dao.impl;

import by.training.epam.dao.interfaces.AnalysisDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.AnalysisRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.entity.Analysis;

import org.apache.log4j.Logger;

import java.util.List;

public class AnalysisDAOImpl implements AnalysisDAO {

    private final static Logger log = Logger.getLogger(AnalysisDAOImpl.class);

    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(SQLTableTitle.ANALYZES_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT %s.%s, %s.%s, %s.%s, %s.%s, %s.%s FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s.%s = ?".formatted(
                    SQLTableTitle.ANALYZES_TABLE, SQLColumnLabel.ID, SQLTableTitle.ANALYSIS_TYPES_TABLE,
                    SQLColumnLabel.TYPE, SQLTableTitle.ANALYZES_TABLE, SQLColumnLabel.APPOINTMENT_DATE,
                    SQLTableTitle.ANALYZES_TABLE, SQLColumnLabel.EXECUTION_DATE, SQLTableTitle.ANALYZES_TABLE,
                    SQLColumnLabel.RESULT,SQLTableTitle.ANALYZES_TABLE, SQLTableTitle.ANALYSIS_TYPES_TABLE,
                    SQLTableTitle.ANALYZES_TABLE, SQLColumnLabel.ANALYSIS_TYPE_ID, SQLTableTitle.ANALYSIS_TYPES_TABLE,
                    SQLColumnLabel.ID, SQLTableTitle.ANALYZES_TABLE, SQLColumnLabel.PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(SQLTableTitle.ANALYZES_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s) VALUES (?, ?, ?)".formatted(SQLTableTitle.ANALYZES_TABLE,
                    SQLColumnLabel.PATIENT_ID, SQLColumnLabel.ANALYSIS_TYPE_ID, SQLColumnLabel.APPOINTMENT_DATE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(SQLTableTitle.ANALYZES_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?  WHERE id = ?".formatted(
            SQLTableTitle.ANALYZES_TABLE, SQLColumnLabel.EXECUTION_DATE, SQLColumnLabel.RESULT);

    private AnalysisDAOImpl() {}

    private static final AnalysisDAOImpl instance = new AnalysisDAOImpl();

    public static AnalysisDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<Analysis> getAll() throws DAOException {
        RequestOperator<Analysis> requestOp = AnalysisRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST);
    }

    @Override
    public List<Analysis> getByHistoryId(int id) throws DAOException{
        RequestOperator<Analysis> requestOp = AnalysisRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST,id);
    }

    @Override
    public Analysis getEntityById(int id) throws DAOException {
        RequestOperator<Analysis> requestOp = AnalysisRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST,id).get(0);
    }

    @Override
    public boolean update(Analysis entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.update(UPDATE_REQUEST, entity.getExecutionDate(),entity.getResult(), entity.getId());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID,id);
    }

    @Override
    public int create(Analysis entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST,entity.getPatientId(),
                entity.getAnalysisTypeId(), entity.getAppointmentDate());
    }
}
