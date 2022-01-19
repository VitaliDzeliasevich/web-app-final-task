package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.entity.Analysis;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisRowMapper implements RowMapper<Analysis> {

    private static final Logger log = Logger.getLogger(AnalysisRowMapper.class);

    private static final AnalysisRowMapper instance = new AnalysisRowMapper();

    public static AnalysisRowMapper getInstance() {
        return instance;
    }

    private AnalysisRowMapper() {}

    @Override
    public List<Analysis> fillFields(ResultSet resultSet) throws DAOException{
        List<Analysis> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Analysis analysis = new Analysis();
                analysis.setId(resultSet.getInt(ColumnLabel.ID));
                analysis.setPatientId(resultSet.getInt(ColumnLabel.PATIENT_ID));
                analysis.setAnalysisTypeId(resultSet.getInt(ColumnLabel.ANALYSIS_TYPE_ID));
                analysis.setLabDrId(resultSet.getInt(ColumnLabel.ANALYSIS_LAB_DR_ID));
                analysis.setAppointmentDate(resultSet.getString(ColumnLabel.APPOINTMENT_DATE));
                analysis.setExecutionDate(resultSet.getString(ColumnLabel.EXECUTION_DATE));
                analysis.setResult(resultSet.getString(ColumnLabel.RESULT));
                list.add(analysis);
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
        return list;
    }
}
