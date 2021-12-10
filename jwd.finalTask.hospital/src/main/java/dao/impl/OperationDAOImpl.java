package dao.impl;

import dao.OperationDAO;
import dao.connection.ConnectionPool;
import dao.exeption.DAOException;
import dao.impl.requestoperator.RequestOperator;
import dao.impl.requestoperator.UniversalRequestOperator;
import dao.impl.requestoperator.impl.OperationRequestOperator;
import dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import dao.impl.tableinfo.ColumnLabel;
import dao.impl.tableinfo.TableTitle;
import entity.Operation;

import java.util.List;

public class OperationDAOImpl implements OperationDAO {

    private  final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.OPERATIONS_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT * FROM %s WHERE %s = ?".formatted(TableTitle.OPERATIONS_TABLE, ColumnLabel.PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.OPERATIONS_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s,%s) VALUES (?, ?, ?, ?)".formatted(TableTitle.OPERATIONS_TABLE,
                    ColumnLabel.ID,ColumnLabel.PATIENT_ID,
                    ColumnLabel.OPERATION_TYPE_ID, ColumnLabel.OPERATION_PLANNED_DATE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.OPERATIONS_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?, %s = ?  WHERE id = ?".formatted
            (TableTitle.OPERATIONS_TABLE,
            ColumnLabel.OPERATION_SURGEON_ID, ColumnLabel.EXECUTION_DATE, ColumnLabel.OPERATION_DESCRIPTION);

    private final static RequestOperator<Operation> requestOp = OperationRequestOperator.getInstance();
    private final static UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();

    public OperationDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Operation> getAll() throws DAOException {
        return requestOp.findAll(GET_ALL_REQUEST, connectionPool);
    }

    @Override
    public Operation getEntityById(int id) throws DAOException {
        return requestOp.findByParameters(GET_BY_ID_REQUEST, connectionPool, id).get(0);
    }

    @Override
    public boolean update(Operation entity) throws DAOException {
        return universalRequestOp.update(UPDATE_REQUEST, connectionPool, entity.getSurgeonId(), entity.getExecutionDate(),
                entity.getDescription());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return universalRequestOp.delete(DELETE_BY_ID, connectionPool, id);
    }

    @Override
    public boolean create(Operation entity) throws DAOException {
        return universalRequestOp.create(CREATE_REQUEST, connectionPool, entity.getId(), entity.getPatientId(),
                entity.getOperationTypeId(), entity.getPlannedDate());
    }

    @Override
    public List<Operation> getByHistoryId(int id) {
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST, connectionPool, id);
    }
}
