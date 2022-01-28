package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.connectionpool.ConnectionPoolFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.PatientRequestOperator;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.rowmapper.impl.PatientRowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.entity.DiseaseHistory;
import by.training.epam.entity.Patient;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.List;

public class PatientRequestOperatorImpl implements PatientRequestOperator {

    private final static Logger log = Logger.getLogger(PatientRequestOperatorImpl.class);

    private final static RowMapper<Patient> rowMapper = new PatientRowMapper();

    private final static PatientRequestOperatorImpl instance = new PatientRequestOperatorImpl();

    private PatientRequestOperatorImpl() {}

    public static PatientRequestOperatorImpl getInstance() {
        return instance;
    }

    @Override
    public List<Patient> findAll(String SQLRequest) throws DAOException{
        List<Patient> list;
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
    public List<Patient> findByParameters(String SQLRequest, Object... attributes)
            throws DAOException{
        List<Patient> list;
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
                log.log(Level.ERROR, throwables);
            }
        }
        return list;
    }

    public boolean createWithHistory(Patient patient) throws DAOException {
        boolean created =false;
        String patientQuery = "INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?, ?, ?, ?, ?)"
                            .formatted(SQLTableTitle.PATIENT_TABLE, SQLColumnLabel.PATIENT_ROOM_ID, SQLColumnLabel.PATIENT_SURNAME,
                                        SQLColumnLabel.PATIENT_NAME, SQLColumnLabel.PATIENT_SEX, SQLColumnLabel.PATIENT_BIRTHDATE);

        String historyQuery = "INSERT INTO %s (%s,%s,%s) VALUES (?, ?, ?)"
                                     .formatted(SQLTableTitle.DISEASE_HISTORY_TABLE, SQLColumnLabel.DISEASE_HISTORY_PATIENT_ID,
                            SQLColumnLabel.DISEASE_HISTORY_ADMISSION_DATE, SQLColumnLabel.DISEASE_HISTORY_ADMISSION_DIAGNOSIS);
        int generatedPatientId =0;
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolFactory.getInstance().getConnectionPool().takeConnection();
            connection.setAutoCommit(false);
        } catch (ConnectionPoolException | SQLException e) {
            log.log(Level.ERROR, e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(patientQuery, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1, patient.getRoomID());
                preparedStatement.setObject(2, patient.getSurname());
                preparedStatement.setObject(3, patient.getName());
                preparedStatement.setObject(4, patient.getSex());
                preparedStatement.setObject(5, patient.getBirthDate());
            int updated = preparedStatement.executeUpdate();
            if (updated ==0) {
                throw new DAOException("Creating entity failed, no rows affected.");
            }
            if (updated==1) {
                try (ResultSet generatedKeys =preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                       generatedPatientId = generatedKeys.getInt(1);
                    }
                    else {
                        throw new DAOException("Creating entity failed, no ID obtained.");
                    }
                }
            }
            preparedStatement = connection.prepareStatement(historyQuery);
                preparedStatement.setObject(1, generatedPatientId);
                preparedStatement.setObject(2, patient.getAdmissionDate());
                preparedStatement.setObject(3, patient.getAdmissionDiagnosis());
            updated = preparedStatement.executeUpdate();
            if (updated==1) {created = true;}
                connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                log.log(Level.ERROR,e);
                throw new DAOException(e);
            }
            throw new DAOException(throwables);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                log.log(Level.ERROR, throwables);
            }
        }
        return created;
    }

    public boolean discharge(DiseaseHistory history) throws DAOException {
        boolean discharged =false;
        String updateQuery = "UPDATE %s INNER JOIN %s ON %s = %s.%s SET %s = ?, %s = ?, %s = 1 WHERE %s.%s = ?"
                .formatted(SQLTableTitle.DISEASE_HISTORY_TABLE, SQLTableTitle.PATIENT_TABLE,
                        SQLColumnLabel.DISEASE_HISTORY_PATIENT_ID, SQLTableTitle.PATIENT_TABLE, SQLColumnLabel.ID,
                        SQLColumnLabel.DISEASE_HISTORY_DISCHARGING_DATE, SQLColumnLabel.DISEASE_HISTORY_EPICRYSIS,
                        SQLColumnLabel.PATIENT_IS_DISCHARGED, SQLTableTitle.DISEASE_HISTORY_TABLE, SQLColumnLabel.ID);
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolFactory.getInstance().getConnectionPool().takeConnection();
        } catch (ConnectionPoolException e) {
            log.log(Level.ERROR, e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setObject(1, history.getDischargingDate());
            preparedStatement.setObject(2, history.getEpicrysis());
            preparedStatement.setObject(3, history.getId());
            int updated = preparedStatement.executeUpdate();
            if (updated ==0) {
                throw new DAOException("Updating entity failed, no rows affected.");
            }
            if (updated==1) {
                discharged=true;
            }
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
                throw new DAOException(e);
            }
            throw new DAOException(throwables);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return discharged;
    }


}
