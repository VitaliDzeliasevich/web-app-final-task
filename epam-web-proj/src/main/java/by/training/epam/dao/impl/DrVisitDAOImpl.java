package by.training.epam.dao.impl;



import by.training.epam.dao.interfaces.DrVisitDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.DrVisitRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.dao.impl.tableinfo.TableTitle;
import by.training.epam.entity.DrVisit;

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

    public DrVisitDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<DrVisit> getAll() throws DAOException {
        RequestOperator<DrVisit> requestOp = DrVisitRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST, connectionPool);
    }

    @Override
    public DrVisit getEntityById(int id) throws DAOException {
        RequestOperator<DrVisit> requestOp = DrVisitRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST, connectionPool, id).get(0);
    }

    @Override
    public boolean update(DrVisit entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID, connectionPool, id);
    }

    @Override
    public int create(DrVisit entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST, connectionPool, entity.getId(), entity.getPatientId(),
                entity.getPatientConditionId(), entity.getDate(), entity.getDescription());
    }

    @Override
    public List<DrVisit> getByHistoryId(int id) throws DAOException {
        RequestOperator<DrVisit> requestOp = DrVisitRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST, connectionPool, id);
    }
}
