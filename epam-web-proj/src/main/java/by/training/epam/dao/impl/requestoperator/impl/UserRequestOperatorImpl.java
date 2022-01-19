package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UserRequestOperator;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.rowmapper.impl.UserRowMapper;
import by.training.epam.entity.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRequestOperatorImpl implements UserRequestOperator {

    private static final Logger log = Logger.getLogger(UserRequestOperatorImpl.class);

    private final static RowMapper<User> userMapper = new UserRowMapper();

    private final static UserRequestOperatorImpl instance = new UserRequestOperatorImpl();

    private UserRequestOperatorImpl() {}

    public static UserRequestOperatorImpl getInstance() {
        return instance;
    }


    public List<User> findAll(String SQLRequest, ConnectionPool connectionPool) throws DAOException {
        List<User> list;
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            log.log(Level.ERROR,e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
            resultSet = preparedStatement.executeQuery();
            list  = userMapper.fillFields(resultSet);
        } catch (SQLException throwables) {
            log.log(Level.ERROR,throwables);
            throw new DAOException(throwables);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }} catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            } try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }} catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            } try {
                connection.close();
            } catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            }
        }
        return list;
    }

    public List<User> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object... attributes)
    throws DAOException{
        List<User> list;
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            log.log(Level.ERROR,e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
                for (int i=0; i<attributes.length; i++) {
                    preparedStatement.setObject(i+1,attributes[i]);
                }
            resultSet = preparedStatement.executeQuery();
            list  = userMapper.fillFields(resultSet);
        } catch (SQLException throwables) {
            log.log(Level.ERROR,throwables);
            throw new DAOException(throwables);
        } finally {
            try { ///////////// в одном трае не закрывается!
                if (resultSet != null) {
                    resultSet.close();
                }} catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            } try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }} catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            } try {
                connection.close();
            } catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            }
        }
        return list;
    }

    public String getRole(String SQLRequest, ConnectionPool connectionPool, String login)
            throws DAOException{
        String role = null;
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            log.log(Level.ERROR,e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
                preparedStatement.setObject(1,login);
            resultSet = preparedStatement.executeQuery();
            try {
                while (resultSet.next()) {
                    role = resultSet.getString(1);
                }
            } catch (SQLException throwables) {
                log.log(Level.ERROR,throwables);
                throw new DAOException(throwables);
            }
        } catch (SQLException throwables) {
            log.log(Level.ERROR,throwables);
            throw new DAOException(throwables);
        } finally {
            try { ///////////// в одном трае не закрывается!
                if (resultSet != null) {
                    resultSet.close();
                }} catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            } try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }} catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            } try {
                connection.close();
            } catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            }
        }
        return role;
    }


}
