package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.rowmapper.impl.DiagnosticRowMapper;
import by.training.epam.entity.Diagnostic;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticRequestOperator implements RequestOperator<Diagnostic> {

    private final static Logger log = Logger.getLogger(DiagnosticRequestOperator.class);

    private final static RowMapper<Diagnostic> fieldsMapper = new DiagnosticRowMapper();

    private final static DiagnosticRequestOperator instance = new DiagnosticRequestOperator();

    private DiagnosticRequestOperator() {}

    public static DiagnosticRequestOperator getInstance() {
        return instance;
    }

    @Override
    public List<Diagnostic> findAll(String SQLRequest, ConnectionPool connectionPool) throws DAOException {
        List<Diagnostic> list = new ArrayList<>();
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
            list  = fieldsMapper.fillFields(resultSet);
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

    @Override
    public List<Diagnostic> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object... attributes)
            throws DAOException{
        List<Diagnostic> list = new ArrayList<>();
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
            list  = fieldsMapper.fillFields(resultSet);
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

}
