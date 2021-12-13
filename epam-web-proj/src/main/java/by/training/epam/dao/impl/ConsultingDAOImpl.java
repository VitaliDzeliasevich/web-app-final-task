package by.training.epam.dao.impl;

import by.training.epam.dao.ConsultingDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.ConsultingRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.dao.impl.tableinfo.TableTitle;
import by.training.epam.entity.Consulting;

import java.util.List;

public class ConsultingDAOImpl implements ConsultingDAO {

    private  final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.CONSULTING_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT * FROM %s WHERE %s = ?".formatted(TableTitle.CONSULTING_TABLE, ColumnLabel.PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.CONSULTING_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s) VALUES (?, ?, ?)".formatted(TableTitle.ANALYZES_TABLE,
                    ColumnLabel.ID,ColumnLabel.PATIENT_ID,
                    ColumnLabel.CONSULTING_CONSULTANT_DR_ID);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.CONSULTING_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?  WHERE id = ?".formatted(TableTitle.CONSULTING_TABLE,
            ColumnLabel.CONSULTING_DESCRIPTION, ColumnLabel.CONSULTING_DATE);

    private final static RequestOperator<Consulting> requestOp = ConsultingRequestOperator.getInstance();
    private final static UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();

    public ConsultingDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Consulting> getAll() throws DAOException {
        return requestOp.findAll(GET_ALL_REQUEST,connectionPool);
    }

    @Override
    public Consulting getEntityById(int id) throws DAOException {
        return requestOp.findByParameters(GET_BY_ID_REQUEST, connectionPool, id).get(0);
    }

    @Override
    public boolean update(Consulting entity) throws DAOException {
        return universalRequestOp.update(UPDATE_REQUEST,connectionPool, entity.getDescription(), entity.getDate());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return universalRequestOp.delete(DELETE_BY_ID, connectionPool, id);
    }

    @Override
    public boolean create(Consulting entity) throws DAOException {
        return universalRequestOp.create(CREATE_REQUEST, connectionPool, entity.getId(), entity.getPatientId(),
                entity.getConsultantDrId());
    }
}
