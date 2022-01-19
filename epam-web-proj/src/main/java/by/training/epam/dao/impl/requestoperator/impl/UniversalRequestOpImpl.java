package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import java.sql.*;

public class UniversalRequestOpImpl implements UniversalRequestOperator {

    private static final Logger log = Logger.getLogger(UniversalRequestOpImpl.class);

    private final static UniversalRequestOpImpl instance = new UniversalRequestOpImpl();

    private UniversalRequestOpImpl() {}

    public static UniversalRequestOpImpl getInstance() {
        return instance;
    }




    @Override
    public int create(String SQLRequest, ConnectionPool connectionPool, Object... attributes) throws  DAOException {
        int generatedId =0;
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            log.log(Level.ERROR,e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest, Statement.RETURN_GENERATED_KEYS);
            for (int i=0; i<attributes.length; i++) {
                preparedStatement.setObject(i+1,attributes[i]);
            }
            int updated = preparedStatement.executeUpdate();
            if (updated ==0) {
                throw new DAOException("Creating entity failed, no rows affected.");
            }
            if (updated==1) {
                try (ResultSet generatedKeys =preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                    else {
                        throw new DAOException("Creating entity failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException throwables) {
            log.log(Level.ERROR,throwables);
            throw new DAOException(throwables);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                log.log(Level.ERROR,throwables);
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                log.log(Level.ERROR,throwables);
            }
        }
        return generatedId;
    }


    @Override
    public boolean delete(String SQLRequest, ConnectionPool connectionPool, int id) throws DAOException{
        boolean deleted = false;
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            log.log(Level.ERROR,e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
            preparedStatement.setObject(1,id);
            int queryRes = preparedStatement.executeUpdate();
            if (queryRes==1) { deleted = true;}
        } catch (SQLException throwables) {
            log.log(Level.ERROR,throwables);
            throw new DAOException(throwables);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException throwables) {
                log.log(Level.ERROR,throwables);
            }
        }
        return deleted;
    }

    public boolean update(String SQLRequest, ConnectionPool connectionPool, Object... attributes) throws DAOException{
        boolean updated = false;
        Connection connection;
        PreparedStatement preparedStatement = null;
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
            int queryRes = preparedStatement.executeUpdate();
            if (queryRes==1) { updated = true;}
        } catch (SQLException throwables) {
            log.log(Level.ERROR,throwables);
            throw new DAOException(throwables);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException throwables) {
                log.log(Level.ERROR,throwables);
            }
        }
        return updated;
    }
}
