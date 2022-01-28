package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.connectionpool.ConnectionPoolFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.rowmapper.impl.DepartmentRowMapper;
import by.training.epam.entity.Department;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRequestOperator implements RequestOperator<Department> {

    private final static Logger log = Logger.getLogger(DepartmentRequestOperator.class);

    private final static RowMapper<Department> fieldsMapper = new DepartmentRowMapper();

    private final static DepartmentRequestOperator instance = new DepartmentRequestOperator();

    private DepartmentRequestOperator() {}

    public static DepartmentRequestOperator getInstance() {
        return instance;
    }

    @Override
    public List<Department> findAll(String SQLRequest) throws DAOException {
        List<Department> list = new ArrayList<>();
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolFactory.getInstance().getConnectionPool().takeConnection();
        } catch (ConnectionPoolException e) {
            log.log(Level.ERROR, e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
            resultSet = preparedStatement.executeQuery();
            list  = fieldsMapper.map(resultSet);
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
    public List<Department> findByParameters(String SQLRequest, Object... attributes)
            throws DAOException{
        List<Department> list = new ArrayList<>();
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolFactory.getInstance().getConnectionPool().takeConnection();
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
            list  = fieldsMapper.map(resultSet);
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
