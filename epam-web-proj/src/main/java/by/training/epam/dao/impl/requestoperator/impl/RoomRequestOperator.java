package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.rowmapper.impl.RoomRowMapper;
import by.training.epam.entity.Room;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRequestOperator implements RequestOperator<Room>{

    Logger log = Logger.getLogger(RoomRequestOperator.class);

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final static RowMapper<Room> fieldsMapper = new RoomRowMapper();

    private final static RoomRequestOperator instance = new RoomRequestOperator();

    private RoomRequestOperator() {}

    public static RoomRequestOperator getInstance() {
        return instance;
    }

    @Override
    public List<Room> findAll(String SQLRequest, ConnectionPool connectionPool)  throws DAOException {
        List<Room> list;
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
            log.log(Level.ERROR, throwables);
            throw new DAOException(throwables);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }} catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            } try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }} catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            } try {
                connection.close();
            } catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            }
        }
        return list;
    }

    @Override
    public List<Room> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object... attributes) throws
            DAOException{
        List<Room> list;
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
            log.log(Level.ERROR, throwables);
            throw new DAOException(throwables);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }} catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            } try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }} catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            } try {
                connection.close();
            } catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            }
        }
        return list;
    }
}

