package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.entity.DiseaseHistory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiseaseHistoryRowMapper implements RowMapper<DiseaseHistory> {
    @Override
    public List<DiseaseHistory> fillFields(ResultSet resultSet) {
        List<DiseaseHistory> historyList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                DiseaseHistory history = new DiseaseHistory();
                history.setId(resultSet.getInt(ColumnLabel.ID));
                history.setPatientID(resultSet.getInt(ColumnLabel.DISEASE_HISTORY_PATIENT_ID));
                history.setAdmissionDate(resultSet.getString(ColumnLabel.DISEASE_HISTORY_ADMISSION_DATE));
                history.setAdmissionDiagnosis(resultSet.getString(ColumnLabel.DISEASE_HISTORY_ADMISSION_DIAGNOSIS));
                history.setLifeAnamnesis(resultSet.getString(ColumnLabel.DISEASE_HISTORY_LIFE_ANAMNESIS));
                history.setDiseaseAnamnesis(resultSet.getString(ColumnLabel.DISEASE_HISTORY_DISEASE_ANAMNESIS));
                history.setDischargingDate(resultSet.getString(ColumnLabel.DISEASE_HISTORY_DISCHARGING_DATE));
                history.setDischargingDiagnosis(resultSet.getString(ColumnLabel.DISEASE_HISTORY_DISCHARGING_DIAGNOSIS));
                historyList.add(history);
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
        return historyList;
    }
}
