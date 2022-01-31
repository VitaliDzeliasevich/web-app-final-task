package by.training.epam.dao.impl;

import by.training.epam.dao.impl.requestoperator.UserRequestOperator;
import by.training.epam.dao.interfaces.UserDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.requestoperator.impl.UserRequestOperatorImpl;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.entity.User;


import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String GET_ALL_REQUEST = "SELECT %s.%s,%s,%s,%s,%s,%s,%s FROM %s INNER JOIN %s ON %s = %s.%s".formatted(
            SQLTableTitle.USER_TABLE,  SQLColumnLabel.ID, SQLColumnLabel.USER_LOGIN,SQLColumnLabel.USER_NAME,
            SQLColumnLabel.USER_SURNAME, SQLColumnLabel.PHONE, SQLColumnLabel.ROLE, SQLColumnLabel.IS_BLOCKED,
            SQLTableTitle.USER_TABLE, SQLTableTitle.ROLE_TABLE, SQLColumnLabel.USER_ROLE,
            SQLTableTitle.ROLE_TABLE, SQLColumnLabel.ID);
    private static final String GET_BY_ID_REQUEST = "SELECT * FROM %s WHERE id = ?".formatted(SQLTableTitle.USER_TABLE);
    private static final String CREATE_REQUEST = "INSERT INTO %s (%s,%s,%s,%s,%s,%s) VALUES (?, ?, ?, ?, ?, ?)".
            formatted(SQLTableTitle.USER_TABLE, SQLColumnLabel.USER_ROLE, SQLColumnLabel.USER_LOGIN, SQLColumnLabel.USER_PASSWORD,
                    SQLColumnLabel.USER_NAME, SQLColumnLabel.USER_SURNAME, SQLColumnLabel.PHONE);
    private static final String DELETE_BY_ID_REQUEST = "DELETE FROM %s WHERE id = ?".formatted(SQLTableTitle.USER_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ? WHERE id = ?".formatted(SQLTableTitle.USER_TABLE,
            SQLColumnLabel.USER_DEPARTMENT);
    private static final String BLOCK_USER_REQUEST = "UPDATE %s SET %s = 1 WHERE id = ?".formatted
            (SQLTableTitle.USER_TABLE, SQLColumnLabel.IS_BLOCKED);
    private static final String CHECK_LOGIN_REQUEST = ("SELECT %s.%s,%s,%s,%s,%s,%s, %s FROM %s INNER JOIN %s ON %s = %s.%s" +
            " WHERE %s = ? AND %s = ?")
            .formatted(SQLTableTitle.USER_TABLE,  SQLColumnLabel.ID, SQLColumnLabel.USER_LOGIN,SQLColumnLabel.USER_NAME,
                    SQLColumnLabel.USER_SURNAME, SQLColumnLabel.PHONE, SQLColumnLabel.ROLE, SQLColumnLabel.IS_BLOCKED,
                    SQLTableTitle.USER_TABLE, SQLTableTitle.ROLE_TABLE, SQLColumnLabel.USER_ROLE,
                    SQLTableTitle.ROLE_TABLE, SQLColumnLabel.ID, SQLColumnLabel.USER_LOGIN, SQLColumnLabel.USER_PASSWORD);
    private static final String FIND_BY_LOGIN_REQUEST = ("SELECT %s.%s,%s,%s,%s,%s,%s,%s FROM %s INNER JOIN %s ON %s = %s.%s" +
            " WHERE %s = ?").formatted(SQLTableTitle.USER_TABLE,  SQLColumnLabel.ID, SQLColumnLabel.USER_LOGIN,
            SQLColumnLabel.USER_NAME, SQLColumnLabel.USER_SURNAME, SQLColumnLabel.PHONE, SQLColumnLabel.ROLE,
            SQLColumnLabel.IS_BLOCKED, SQLTableTitle.USER_TABLE, SQLTableTitle.ROLE_TABLE, SQLColumnLabel.USER_ROLE,
            SQLTableTitle.ROLE_TABLE, SQLColumnLabel.ID,
                    SQLColumnLabel.USER_LOGIN);
    private static final String GET_ROLE_REQUEST = ("SELECT %s FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s.%s = ?").
            formatted(SQLColumnLabel.ROLE, SQLTableTitle.USER_TABLE, SQLTableTitle.ROLE_TABLE, SQLTableTitle.USER_TABLE,
                    SQLColumnLabel.USER_ROLE, SQLTableTitle.ROLE_TABLE, SQLColumnLabel.ID, SQLTableTitle.USER_TABLE,
                    SQLColumnLabel.USER_LOGIN);

    private UserDAOImpl() {}

    private static final UserDAOImpl instance = new UserDAOImpl();

    public static UserDAOImpl getInstance() {
        return instance;
    }



    @Override
    public List<User> getAll() throws DAOException {
        UserRequestOperator requestOp = UserRequestOperatorImpl.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST);
    }

    @Override
    public User getEntityById(int id) throws DAOException{
        UserRequestOperator requestOp = UserRequestOperatorImpl.getInstance();
        return  requestOp.findByParameters(GET_BY_ID_REQUEST, id).get(0);
    }

    @Override
    public boolean update(User entity) throws DAOException{
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.update(UPDATE_REQUEST, entity.getDepartmentsId(), entity.getId());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID_REQUEST, id);
    }

    @Override
    public int create(User entity) throws DAOException{
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST, entity.getRoleId(),
                entity.getLogin(),
                entity.getPassword(), entity.getName(), entity.getSurname(), entity.getPhone());
    }

    public boolean authorized(String login, String password) throws DAOException {
        UserRequestOperator requestOp = UserRequestOperatorImpl.getInstance();
        boolean authorized = false;
        List<User> users;
        users = requestOp.findByParameters(CHECK_LOGIN_REQUEST, login, password);
        if (users.size()>0) {
            authorized=true;
        }
        return authorized;
    }

    public boolean isLoginExist(String login) throws DAOException {
        UserRequestOperator requestOp = UserRequestOperatorImpl.getInstance();
        return requestOp.findByParameters(FIND_BY_LOGIN_REQUEST, login).size() == 1;
    }

    public String getRole(String login) throws DAOException {
        UserRequestOperator requestOp = UserRequestOperatorImpl.getInstance();
        return requestOp.getRole(GET_ROLE_REQUEST, login);
    }

    @Override
    public User getByLogin(String login) throws DAOException {
        UserRequestOperator requestOp = UserRequestOperatorImpl.getInstance();
        return requestOp.findByParameters(FIND_BY_LOGIN_REQUEST,login).get(0);
    }

    @Override
    public boolean blockUser(int id) throws DAOException {
        UniversalRequestOperator requestOperator = UniversalRequestOpImpl.getInstance();
        return requestOperator.update(BLOCK_USER_REQUEST,id);
    }

    @Override
    public boolean checkIfBlocked(String login) throws DAOException {
        User user;
        boolean isNotBlocked = true;
        UserRequestOperator requestOp = UserRequestOperatorImpl.getInstance();
        user = requestOp.findByParameters(FIND_BY_LOGIN_REQUEST,login).get(0);
        if (user.getIsBlocked() == 1) {
            isNotBlocked = false;
        }
        return isNotBlocked;
    }
}
