package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.rowmapper.impl.UserRowMapper;
import by.training.epam.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRequestOperator implements RequestOperator<User> {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final static RowMapper<User> userMapper = new UserRowMapper();

    private final static UserRequestOperator instance = new UserRequestOperator();

    private UserRequestOperator() {}

    public static UserRequestOperator getInstance() {
        return instance;
    }


    public List<User> findAll(String SQLRequest, ConnectionPool connectionPool) {
        List<User> list = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
            resultSet = preparedStatement.executeQuery();
            list  = userMapper.fillFields(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    public List<User> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object... attributes) {
        List<User> list = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
                for (int i=0; i<attributes.length; i++) {
                    preparedStatement.setObject(i+1,attributes[i]);
                }
            resultSet = preparedStatement.executeQuery();
            list  = userMapper.fillFields(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try { ///////////// в одном трае не закрывается!
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }
}
