package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.entity.Patient;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientRowMapper implements RowMapper<Patient> {

    private static final Logger log = Logger.getLogger(PatientRowMapper.class);

    @Override
    public List<Patient> fillFields(ResultSet resultSet) throws DAOException{
        List<Patient> patientList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setRoomNumber(resultSet.getInt(ColumnLabel.ROOM_NUMBER));
                patient.setId(resultSet.getInt(ColumnLabel.ID));
                patient.setSurname(resultSet.getString(ColumnLabel.PATIENT_SURNAME));
                patient.setSex(resultSet.getString(ColumnLabel.PATIENT_SEX));
                patient.setName(resultSet.getString(ColumnLabel.PATIENT_NAME));
                patient.setBirthDate(resultSet.getString(ColumnLabel.PATIENT_BIRTHDATE));
                patient.setDepartment(resultSet.getString(ColumnLabel.DEPARTMENT_TYPE) + " " +
                        resultSet.getString(ColumnLabel.DEPARTMENT_NUMBER));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR,e);
            throw new DAOException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            }
        }
        return patientList;
    }
}

