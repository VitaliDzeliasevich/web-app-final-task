package dao.impl.requestoperator.impl;

import dao.connection.ConnectionPool;
import dao.connection.ConnectionPoolException;
import dao.impl.requestoperator.RequestOperator;
import dao.impl.rowmapper.RowMapper;
import dao.impl.rowmapper.impl.AnalysisRowMapper;
import entity.Analysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisRequestOperator implements RequestOperator<Analysis> {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final static RowMapper<Analysis> fieldsMapper = new AnalysisRowMapper();

    private final static AnalysisRequestOperator instance = new AnalysisRequestOperator();

    private AnalysisRequestOperator() {}

    public static AnalysisRequestOperator getInstance() {
        return instance;
    }

    @Override
    public List<Analysis> findAll(String SQLRequest, ConnectionPool connectionPool) {
        List<Analysis> list = new ArrayList<>();
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
    public List<Analysis> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object... attributes) {
        List<Analysis> list = new ArrayList<>();
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
