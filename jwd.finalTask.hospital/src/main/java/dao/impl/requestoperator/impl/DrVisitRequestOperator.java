package dao.impl.requestoperator.impl;

import dao.connection.ConnectionPool;
import dao.connection.ConnectionPoolException;
import dao.impl.requestoperator.RequestOperator;
import dao.impl.rowmapper.RowMapper;
import dao.impl.rowmapper.impl.DrVisitRowMapper;
import entity.DrVisit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrVisitRequestOperator implements RequestOperator<DrVisit> {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final static RowMapper<DrVisit> fieldsMapper = new DrVisitRowMapper();

    private final static DrVisitRequestOperator instance = new DrVisitRequestOperator();

    private DrVisitRequestOperator() {}

    public static DrVisitRequestOperator getInstance() {
        return instance;
    }

    @Override
    public List<DrVisit> findAll(String SQLRequest, ConnectionPool connectionPool) {
        List<DrVisit> list = new ArrayList<>();
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
    public List<DrVisit> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object... attributes) {
        List<DrVisit> list = new ArrayList<>();
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
