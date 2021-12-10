package dao.impl.rowmapper.impl;

import dao.impl.rowmapper.RowMapper;
import dao.impl.tableinfo.ColumnLabel;
import entity.Diagnostic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticRowMapper implements RowMapper<Diagnostic> {
    @Override
    public List<Diagnostic> fillFields(ResultSet resultSet) {
        List<Diagnostic> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Diagnostic diagnostic = new Diagnostic();
                diagnostic.setId(resultSet.getInt(ColumnLabel.ID));
                diagnostic.setDiseaseHistoryId(resultSet.getInt(ColumnLabel.DIAGNOSTIC_PATIENT_ID));
                diagnostic.setDiagnosticTypeId(resultSet.getInt(ColumnLabel.DIAGNOSTIC_DIAGNOSTIC_TYPE_ID));
                diagnostic.setDiagnosticDrId(resultSet.getInt(ColumnLabel.DIAGNOSTIC_DR_ID));
                diagnostic.setAppointmentDate(resultSet.getString(ColumnLabel.APPOINTMENT_DATE));
                diagnostic.setExecutionDate(resultSet.getString(ColumnLabel.EXECUTION_DATE));
                diagnostic.setResult(resultSet.getString(ColumnLabel.RESULT));
                list.add(diagnostic);
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
