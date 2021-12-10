package dao.impl.rowmapper.impl;

import dao.impl.rowmapper.RowMapper;
import dao.impl.tableinfo.ColumnLabel;
import entity.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientRowMapper implements RowMapper<Patient> {
    @Override
    public List<Patient> fillFields(ResultSet resultSet) {
        List<Patient> patientList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(resultSet.getInt(ColumnLabel.ID));
                patient.setRoomID(resultSet.getInt(ColumnLabel.PATIENT_ROOM_ID));
                patient.setSurname(resultSet.getString(ColumnLabel.PATIENT_SURNAME));
                patient.setSex(resultSet.getString(ColumnLabel.PATIENT_SEX));
                patient.setName(resultSet.getString(ColumnLabel.PATIENT_NAME));
                patient.setAddress(resultSet.getString(ColumnLabel.PATIENT_ADDRESS));
                patient.setBirthDate(resultSet.getString(ColumnLabel.PATIENT_BIRTHDATE));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return patientList;
    }
}

