package dao.impl;

import dao.DrVisitDAO;
import dao.connection.ConnectionPool;
import dao.exeption.DAOException;
import dao.impl.requestoperator.RequestOperator;
import dao.impl.requestoperator.UniversalRequestOperator;
import dao.impl.requestoperator.impl.DrVisitRequestOperator;
import dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import dao.impl.tableinfo.ColumnLabel;
import dao.impl.tableinfo.TableTitle;
import entity.DrVisit;

import java.util.List;

public class DrVisitDAOImpl implements DrVisitDAO {

    private  final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.DR_VISITS_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT * FROM %s WHERE %s = ?".formatted(TableTitle.DR_VISITS_TABLE, ColumnLabel.PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.DR_VISITS_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s,%s, %s) VALUES (?, ?, ?, ?, ?)".formatted(TableTitle.DR_VISITS_TABLE,
                    ColumnLabel.ID,ColumnLabel.PATIENT_ID,
                    ColumnLabel.VISIT_PATIENT_CONDITION_ID, ColumnLabel.VISIT_DATE, ColumnLabel.VISIT_DESCRIPTION);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.DR_VISITS_TABLE);
//    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?, %s = ?  WHERE id = ?".formatted
//            (TableTitle.DR_VISITS_TABLE,
//                    ColumnLabel.OPERATION_SURGEON_ID, ColumnLabel.EXECUTION_DATE, ColumnLabel.OPERATION_DESCRIPTION);

    private final static RequestOperator<DrVisit> requestOp = DrVisitRequestOperator.getInstance();
    private final static UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();

    public DrVisitDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<DrVisit> getAll() throws DAOException {
        return requestOp.findAll(GET_ALL_REQUEST, connectionPool);
    }

    @Override
    public DrVisit getEntityById(int id) throws DAOException {
        return requestOp.findByParameters(GET_BY_ID_REQUEST, connectionPool, id).get(0);
    }

    @Override
    public boolean update(DrVisit entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return universalRequestOp.delete(DELETE_BY_ID, connectionPool, id);
    }

    @Override
    public boolean create(DrVisit entity) throws DAOException {
        return universalRequestOp.create(CREATE_REQUEST, connectionPool, entity.getId(), entity.getPatientId(),
                entity.getPatientConditionId(), entity.getDate(), entity.getDescription());
    }

    @Override
    public List<DrVisit> getByHistoryId(int id) {
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST, connectionPool, id);
    }
}
