package by.training.epam.dao.impl;



import by.training.epam.dao.interfaces.DrVisitDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.DrVisitRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.entity.DrVisit;

import java.util.List;

public class DrVisitDAOImpl implements DrVisitDAO {

    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(SQLTableTitle.DR_VISITS_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT * FROM %s WHERE %s = ?".formatted(SQLTableTitle.DR_VISITS_TABLE, SQLColumnLabel.PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(SQLTableTitle.DR_VISITS_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s,%s, %s) VALUES (?, ?, ?, ?, ?)".formatted(SQLTableTitle.DR_VISITS_TABLE,
                    SQLColumnLabel.ID, SQLColumnLabel.PATIENT_ID,
                    SQLColumnLabel.VISIT_PATIENT_CONDITION_ID, SQLColumnLabel.VISIT_DATE, SQLColumnLabel.VISIT_DESCRIPTION);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(SQLTableTitle.DR_VISITS_TABLE);
//    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?, %s = ?  WHERE id = ?".formatted
//            (TableTitle.DR_VISITS_TABLE,
//                    ColumnLabel.OPERATION_SURGEON_ID, ColumnLabel.EXECUTION_DATE, ColumnLabel.OPERATION_DESCRIPTION);

    private DrVisitDAOImpl() {}

    private static final DrVisitDAOImpl instance = new DrVisitDAOImpl();

    public static DrVisitDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<DrVisit> getAll() throws DAOException {
        RequestOperator<DrVisit> requestOp = DrVisitRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST);
    }

    @Override
    public DrVisit getEntityById(int id) throws DAOException {
        RequestOperator<DrVisit> requestOp = DrVisitRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST, id).get(0);
    }

    @Override
    public boolean update(DrVisit entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID, id);
    }

    @Override
    public int create(DrVisit entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST, entity.getId(), entity.getPatientId(),
                entity.getPatientConditionId(), entity.getDate(), entity.getDescription());
    }

    @Override
    public List<DrVisit> getByHistoryId(int id) throws DAOException {
        RequestOperator<DrVisit> requestOp = DrVisitRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST, id);
    }
}
