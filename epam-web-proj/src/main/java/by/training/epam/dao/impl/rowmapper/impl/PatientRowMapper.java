package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
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
    public List<Patient> map(ResultSet resultSet) throws DAOException{
        List<Patient> patientList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setRoomNumber(resultSet.getInt(SQLColumnLabel.ROOM_NUMBER));
                patient.setId(resultSet.getInt(SQLColumnLabel.ID));
                patient.setSurname(resultSet.getString(SQLColumnLabel.PATIENT_SURNAME));
                patient.setSex(resultSet.getString(SQLColumnLabel.PATIENT_SEX));
                patient.setName(resultSet.getString(SQLColumnLabel.PATIENT_NAME));
                patient.setBirthDate(resultSet.getString(SQLColumnLabel.PATIENT_BIRTHDATE));
                patient.setDepartment(resultSet.getString(SQLColumnLabel.TYPE) + " " +
                        resultSet.getString(SQLColumnLabel.DEPARTMENT_NUMBER));
//                patient.setAdmissionDiagnosis(resultSet.getString(SQLColumnLabel.DISEASE_HISTORY_ADMISSION_DIAGNOSIS));
//                patient.setAdmissionDate(resultSet.getString(SQLColumnLabel.DISEASE_HISTORY_ADMISSION_DATE));
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

