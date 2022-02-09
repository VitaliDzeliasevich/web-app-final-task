package by.training.epam.dao.impl;



import by.training.epam.dao.interfaces.DiagnosticDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.DiagnosticRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.entity.Diagnostic;

import java.util.List;

public class DiagnosticDAOImpl implements DiagnosticDAO {


    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(SQLTableTitle.DIAGNOSTIC_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT %s.%s,%s, %s, %s, %s, %s FROM %s INNER JOIN %s ON %s = %s.%s WHERE %s = ? ORDER BY %s ASC".formatted(
                    SQLTableTitle.DIAGNOSTIC_TABLE, SQLColumnLabel.ID, SQLColumnLabel.DIAGNOSTIC_PATIENT_ID,
                    SQLColumnLabel.DIAGNOSTIC_NAME, SQLColumnLabel.APPOINTMENT_DATE,SQLColumnLabel.EXECUTION_DATE,
                    SQLColumnLabel.RESULT,SQLTableTitle.DIAGNOSTIC_TABLE,SQLTableTitle.DIAGNOSTIC_TYPES_TABLE,
                    SQLColumnLabel.DIAGNOSTIC_DIAGNOSTIC_TYPE_ID,SQLTableTitle.DIAGNOSTIC_TYPES_TABLE,SQLColumnLabel.ID,
                    SQLColumnLabel.DIAGNOSTIC_PATIENT_ID, SQLColumnLabel.APPOINTMENT_DATE);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(SQLTableTitle.DIAGNOSTIC_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s) VALUES (?, ?, ?)".formatted(SQLTableTitle.DIAGNOSTIC_TABLE,
                    SQLColumnLabel.DIAGNOSTIC_PATIENT_ID, SQLColumnLabel.DIAGNOSTIC_DIAGNOSTIC_TYPE_ID,
                    SQLColumnLabel.APPOINTMENT_DATE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(SQLTableTitle.DIAGNOSTIC_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?  WHERE id = ?".formatted(SQLTableTitle.DIAGNOSTIC_TABLE,
            SQLColumnLabel.EXECUTION_DATE, SQLColumnLabel.RESULT);

    private DiagnosticDAOImpl() {}

    private static final DiagnosticDAOImpl instance = new DiagnosticDAOImpl();

    public static DiagnosticDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<Diagnostic> getAll() throws DAOException {
        RequestOperator<Diagnostic> requestOp = DiagnosticRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST);
    }

    @Override
    public List<Diagnostic> getByHistoryId(int id) throws DAOException {
        RequestOperator<Diagnostic> requestOp = DiagnosticRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST,id);
    }

    @Override
    public Diagnostic getEntityById(int id) throws DAOException {
        RequestOperator<Diagnostic> requestOp = DiagnosticRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST,id).get(0);
    }

    @Override
    public boolean update(Diagnostic entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.update(UPDATE_REQUEST, entity.getExecutionDate(), entity.getResult(),
                entity.getId());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID,id);
    }

    @Override
    public int create(Diagnostic entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST,entity.getPatientId(),
                entity.getDiagnosticTypeId(),entity.getAppointmentDate());
    }
}
