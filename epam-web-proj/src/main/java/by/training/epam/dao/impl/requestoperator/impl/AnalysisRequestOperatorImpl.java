package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.connectionpool.ConnectionPoolFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.AnalysisRequestOperator;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.rowmapper.impl.AnalysisRowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.entity.Analysis;
import by.training.epam.entity.transfer.PatientAnalysis;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisRequestOperatorImpl implements AnalysisRequestOperator {

    private final static Logger log = Logger.getLogger(AnalysisRequestOperatorImpl.class);

    private final static AnalysisRequestOperatorImpl instance = new AnalysisRequestOperatorImpl();

    private AnalysisRequestOperatorImpl() {}

    public static AnalysisRequestOperatorImpl getInstance() {
        return instance;
    }

    @Override
    public List<Analysis> findAll(String SQLRequest) throws DAOException {
        RowMapper<Analysis> rowMapper = AnalysisRowMapper.getInstance();
        List<Analysis> list;
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
    public List<Analysis> findByParameters(String SQLRequest, Object... attributes)
            throws DAOException{
        RowMapper<Analysis> rowMapper = AnalysisRowMapper.getInstance();
        List<Analysis> list;
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

    @Override
    public List<PatientAnalysis> findByDepartmentAndDate(String SQLRequest, Object... attributes) throws DAOException {
        List<PatientAnalysis> list = new ArrayList<>();
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
            try {
                while (resultSet.next()) {
                    PatientAnalysis patientAnalysis = new PatientAnalysis();
                    patientAnalysis.setPatientId(resultSet.getInt(SQLColumnLabel.ID));
                    patientAnalysis.setType(resultSet.getString(SQLColumnLabel.TYPE));
                    patientAnalysis.setAppointmentDate(resultSet.getString(SQLColumnLabel.APPOINTMENT_DATE));
                    patientAnalysis.setRoomNumber(Integer.parseInt(resultSet.getString(SQLColumnLabel.ROOM_NUMBER)));
                    patientAnalysis.setPatientName(resultSet.getString(SQLColumnLabel.PATIENT_NAME));
                    patientAnalysis.setPatientName(resultSet.getString(SQLColumnLabel.PATIENT_SURNAME));
                    list.add(patientAnalysis);
                }
            } catch (SQLException e) {
                log.log(Level.ERROR,e);
                throw new DAOException(e);
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.log(Level.ERROR,e);
                }
            }
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
