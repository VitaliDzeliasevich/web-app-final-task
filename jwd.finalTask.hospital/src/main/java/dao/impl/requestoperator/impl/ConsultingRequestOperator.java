package dao.impl.requestoperator.impl;

import dao.connection.ConnectionPool;
import dao.connection.ConnectionPoolException;
import dao.impl.requestoperator.RequestOperator;
import dao.impl.rowmapper.RowMapper;
import dao.impl.rowmapper.impl.ConsultingRowMapper;
import entity.Consulting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultingRequestOperator implements RequestOperator<Consulting> {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final static RowMapper<Consulting> fieldsMapper = new ConsultingRowMapper();

    private final static ConsultingRequestOperator instance = new ConsultingRequestOperator();

    private ConsultingRequestOperator() {}

    public static ConsultingRequestOperator getInstance() {
        return instance;
    }

    @Override
    public List<Consulting> findAll(String SQLRequest, ConnectionPool connectionPool) {
        List<Consulting> list = new ArrayList<>();
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
    public List<Consulting> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object... attributes) {
        List<Consulting> list = new ArrayList<>();
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
