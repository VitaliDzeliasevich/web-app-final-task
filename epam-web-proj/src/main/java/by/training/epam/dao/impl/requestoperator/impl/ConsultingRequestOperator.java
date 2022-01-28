package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.connectionpool.ConnectionPoolFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.rowmapper.impl.ConsultingRowMapper;
import by.training.epam.entity.Consultation;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultingRequestOperator implements RequestOperator<Consultation> {

    private final static Logger log = Logger.getLogger(ConsultingRequestOperator.class);

    private final static RowMapper<Consultation> rowMapper = new ConsultingRowMapper();

    private final static ConsultingRequestOperator instance = new ConsultingRequestOperator();

    private ConsultingRequestOperator() {}

    public static ConsultingRequestOperator getInstance() {
        return instance;
    }

    @Override
    public List<Consultation> findAll(String SQLRequest) throws DAOException {
        List<Consultation> list = new ArrayList<>();
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
            list  = rowMapper.map(resultSet);
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
    public List<Consultation> findByParameters(String SQLRequest, Object... attributes)
            throws DAOException{
        List<Consultation> list = new ArrayList<>();
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
            list  = rowMapper.map(resultSet);
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
