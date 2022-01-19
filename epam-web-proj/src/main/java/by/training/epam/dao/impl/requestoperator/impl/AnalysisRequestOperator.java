package by.training.epam.dao.impl.requestoperator.impl;


import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.AnalysisDAOImpl;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.rowmapper.impl.AnalysisRowMapper;
import by.training.epam.entity.Analysis;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisRequestOperator implements RequestOperator<Analysis> {

    private final static Logger log = Logger.getLogger(AnalysisRequestOperator.class);

    private final static AnalysisRequestOperator instance = new AnalysisRequestOperator();

    private AnalysisRequestOperator() {}

    public static AnalysisRequestOperator getInstance() {
        return instance;
    }

    @Override
    public List<Analysis> findAll(String SQLRequest, ConnectionPool connectionPool) throws DAOException {
        RowMapper<Analysis> rowMapper = AnalysisRowMapper.getInstance();
        List<Analysis> list;
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            log.log(Level.ERROR, e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
            resultSet = preparedStatement.executeQuery();
            list  = rowMapper.fillFields(resultSet);
        } catch (SQLException throwables) {
            log.log(Level.ERROR, throwables);
            throw new DAOException(throwables);
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
                log.log(Level.ERROR, throwables);
            }
        }
        return list;
    }

    @Override
    public List<Analysis> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object... attributes)
            throws DAOException{
        RowMapper<Analysis> rowMapper = AnalysisRowMapper.getInstance();
        List<Analysis> list;
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            log.log(Level.ERROR, e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
            for (int i=0; i<attributes.length; i++) {
                preparedStatement.setObject(i+1,attributes[i]);
            }
            resultSet = preparedStatement.executeQuery();
            list  = rowMapper.fillFields(resultSet);
        } catch (SQLException throwables) {
            log.log(Level.ERROR, throwables);
            throw new DAOException(throwables);
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
                log.log(Level.ERROR,throwables);
            }
        }
        return list;
    }
}
