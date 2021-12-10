package dao.impl;

import dao.UserDAO;
import dao.connection.ConnectionPool;
import dao.exeption.DAOException;
import dao.impl.requestoperator.UniversalRequestOperator;
import dao.impl.requestoperator.RequestOperator;
import dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import dao.impl.requestoperator.impl.UserRequestOperator;
import dao.impl.tableinfo.ColumnLabel;
import dao.impl.tableinfo.TableTitle;
import entity.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.USER_TABLE);
    private static final String GET_BY_ID_REQUEST = "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.USER_TABLE);
    private static final String CREATE_REQUEST = "INSERT INTO %s VALUES (?, ?, ?, ?, ?, ?, ?)".formatted(TableTitle.USER_TABLE);
    private static final String DELETE_BY_ID_REQUEST = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.USER_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ? WHERE id = ?".formatted(TableTitle.USER_TABLE,
            ColumnLabel.USER_DEPARTMENT);

    private final static RequestOperator<User> requestOp = UserRequestOperator.getInstance();
    private final static UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();

    public UserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<User> getAll() throws DAOException {
        return requestOp.findAll(GET_ALL_REQUEST,connectionPool);
    }

    @Override
    public User getEntityById(int id) throws DAOException{
        return  requestOp.findByParameters(GET_BY_ID_REQUEST, connectionPool, id).get(0);
    }

    @Override
    public boolean update(User entity) throws DAOException{
        return universalRequestOp.update(UPDATE_REQUEST, connectionPool, entity.getDepartmentsId(), entity.getId());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return universalRequestOp.delete(DELETE_BY_ID_REQUEST, connectionPool, id);
    }

    @Override
    public boolean create(User entity) throws DAOException{
        return universalRequestOp.create(CREATE_REQUEST,connectionPool,
                entity.getId(), entity.getRoleId(), entity.getLogin(),
                entity.getPassword(), entity.getName(), entity.getSurname(), entity.getDepartmentsId());
    }
}
