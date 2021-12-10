package dao.impl.requestoperator.impl;

import dao.connection.ConnectionPool;
import dao.connection.ConnectionPoolException;
import dao.impl.rowmapper.RowMapper;
import dao.impl.rowmapper.impl.PatientRowMapper;
import dao.impl.requestoperator.RequestOperator;
import entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientRequestOperator implements RequestOperator<Patient> {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final static RowMapper<Patient> fieldsMapper= new PatientRowMapper();

    private final static PatientRequestOperator instance = new PatientRequestOperator();

    private PatientRequestOperator() {}

    public static PatientRequestOperator getInstance() {
        return instance;
    }

    @Override
    public List<Patient> findAll(String SQLRequest, ConnectionPool connectionPool) {
        List<Patient> list = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
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
    public List<Patient> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object... attributes) {
        List<Patient> list = new ArrayList<>();
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
