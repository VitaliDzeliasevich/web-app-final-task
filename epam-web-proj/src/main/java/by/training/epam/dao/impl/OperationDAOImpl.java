package by.training.epam.dao.impl;



import by.training.epam.dao.interfaces.OperationDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.OperationRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.entity.Operation;

import java.util.List;

public class OperationDAOImpl implements OperationDAO {


    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(SQLTableTitle.OPERATIONS_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT * FROM %s WHERE %s = ?".formatted(SQLTableTitle.OPERATIONS_TABLE, SQLColumnLabel.PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(SQLTableTitle.OPERATIONS_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s) VALUES (?, ?, ?)".formatted(SQLTableTitle.OPERATIONS_TABLE, SQLColumnLabel.PATIENT_ID,
                    SQLColumnLabel.OPERATION_TYPE_ID, SQLColumnLabel.OPERATION_PLANNED_DATE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(SQLTableTitle.OPERATIONS_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?, %s = ?  WHERE id = ?".formatted
            (SQLTableTitle.OPERATIONS_TABLE,
            SQLColumnLabel.OPERATION_SURGEON_ID, SQLColumnLabel.EXECUTION_DATE, SQLColumnLabel.OPERATION_DESCRIPTION);

    private OperationDAOImpl() {}

    private static final OperationDAOImpl instance = new OperationDAOImpl();

    public static OperationDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<Operation> getAll() throws DAOException {
        RequestOperator<Operation> requestOp = OperationRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST);
    }

    @Override
    public Operation getEntityById(int id) throws DAOException {
        RequestOperator<Operation> requestOp = OperationRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST, id).get(0);
    }

    @Override
    public boolean update(Operation entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.update(UPDATE_REQUEST, entity.getSurgeonId(), entity.getExecutionDate(),
                entity.getDescription());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID, id);
    }

    @Override
    public int create(Operation entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST, entity.getPatientId(),
                entity.getOperationTypeId(), entity.getPlannedDate());
    }

    @Override
    public List<Operation> getByHistoryId(int id) throws DAOException {
        RequestOperator<Operation> requestOp = OperationRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST, id);
    }
}
