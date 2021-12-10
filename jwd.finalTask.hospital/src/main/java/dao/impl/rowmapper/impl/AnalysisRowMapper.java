package dao.impl.rowmapper.impl;

import dao.impl.rowmapper.RowMapper;
import dao.impl.tableinfo.ColumnLabel;
import entity.Analysis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnalysisRowMapper implements RowMapper<Analysis> {
    @Override
    public List<Analysis> fillFields(ResultSet resultSet) {
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
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
