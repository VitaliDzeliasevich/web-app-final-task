package by.training.epam.dao.impl;

import by.training.epam.dao.UserDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.requestoperator.impl.UserRequestOperator;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.dao.impl.tableinfo.TableTitle;
import by.training.epam.entity.User;


import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.USER_TABLE);
    private static final String GET_BY_ID_REQUEST = "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.USER_TABLE);
    private static final String CREATE_REQUEST = "INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?, ?, ?, ?, ?)".
            formatted(TableTitle.USER_TABLE, ColumnLabel.USER_ROLE, ColumnLabel.USER_LOGIN, ColumnLabel.USER_PASSWORD,
                    ColumnLabel.USER_NAME, ColumnLabel.USER_SURNAME);
    private static final String DELETE_BY_ID_REQUEST = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.USER_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ? WHERE id = ?".formatted(TableTitle.USER_TABLE,
            ColumnLabel.USER_DEPARTMENT);
    private static final String CHECK_LOGIN_REQUEST = "SELECT * FROM %s WHERE %s = ? AND %s = ?"
            .formatted(TableTitle.USER_TABLE,
            ColumnLabel.USER_LOGIN, ColumnLabel.USER_PASSWORD);

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
        return universalRequestOp.create(CREATE_REQUEST,connectionPool, entity.getRoleId(),
                entity.getLogin(),
                entity.getPassword(), entity.getName(), entity.getSurname());
    }

    public boolean authorized(String login, String password) {
        boolean authorized = false;
        List<User> users;
        users = requestOp.findByParameters(CHECK_LOGIN_REQUEST, connectionPool, login, password);
        if (users.size()>0) {
            authorized=true;
        }
        return authorized;
    }
}
