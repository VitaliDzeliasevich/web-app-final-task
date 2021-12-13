package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UniversalRequestOpImpl implements UniversalRequestOperator {

    private Connection connection;
    private PreparedStatement preparedStatement;

    private final static UniversalRequestOpImpl instance = new UniversalRequestOpImpl();

    private UniversalRequestOpImpl() {}

    public static UniversalRequestOpImpl getInstance() {
        return instance;
    }




    @Override
    public boolean create(String SQLRequest, ConnectionPool connectionPool, Object... attributes) {
        boolean created = false;
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
            int updated = preparedStatement.executeUpdate();
            if (updated==1) { created = true;}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return created;
    }
    @Override
    public boolean delete(String SQLRequest, ConnectionPool connectionPool, int id) {
        boolean deleted = false;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
            preparedStatement.setObject(1,id);
            int queryRes = preparedStatement.executeUpdate();
            if (queryRes==1) { deleted = true;}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return deleted;
    }

    public boolean update(String SQLRequest, ConnectionPool connectionPool, Object... attributes) {
        boolean updated = false;
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
            int queryRes = preparedStatement.executeUpdate();
            if (queryRes==1) { updated = true;}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return updated;
    }
}
