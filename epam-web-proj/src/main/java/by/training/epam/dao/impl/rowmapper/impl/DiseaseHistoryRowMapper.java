package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.entity.DiseaseHistory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiseaseHistoryRowMapper implements RowMapper<DiseaseHistory> {

    private static final Logger log = Logger.getLogger(DiseaseHistoryRowMapper.class);

    @Override
    public List<DiseaseHistory> map(ResultSet resultSet) throws DAOException{
        List<DiseaseHistory> historyList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                DiseaseHistory history = new DiseaseHistory();
                history.setId(resultSet.getInt(SQLColumnLabel.ID));
                history.setPatientID(resultSet.getInt(SQLColumnLabel.DISEASE_HISTORY_PATIENT_ID));
                history.setAdmissionDate(resultSet.getString(SQLColumnLabel.DISEASE_HISTORY_ADMISSION_DATE));
                history.setAdmissionDiagnosis(resultSet.getString(SQLColumnLabel.DISEASE_HISTORY_ADMISSION_DIAGNOSIS));
                history.setLifeAnamnesis(resultSet.getString(SQLColumnLabel.DISEASE_HISTORY_LIFE_ANAMNESIS));
                history.setDiseaseAnamnesis(resultSet.getString(SQLColumnLabel.DISEASE_HISTORY_DISEASE_ANAMNESIS));
                history.setDischargingDate(resultSet.getString(SQLColumnLabel.DISEASE_HISTORY_DISCHARGING_DATE));
                history.setEpicrysis(resultSet.getString(SQLColumnLabel.DISEASE_HISTORY_EPICRYSIS));
                historyList.add(history);
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
        return historyList;
    }
}
