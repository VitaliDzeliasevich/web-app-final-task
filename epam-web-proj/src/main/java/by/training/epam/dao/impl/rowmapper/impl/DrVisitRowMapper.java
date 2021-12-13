package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.entity.DrVisit;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrVisitRowMapper implements RowMapper<DrVisit> {

    @Override
    public List<DrVisit> fillFields(ResultSet resultSet) {
        List<DrVisit> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                DrVisit visit = new DrVisit();
                visit.setId(resultSet.getInt(ColumnLabel.ID));
                visit.setPatientId(resultSet.getInt(ColumnLabel.PATIENT_ID));
                visit.setPatientConditionId(resultSet.getInt(ColumnLabel.VISIT_PATIENT_CONDITION_ID));
                visit.setDate(resultSet.getString(ColumnLabel.VISIT_DATE));
                visit.setDescription(resultSet.getString(ColumnLabel.VISIT_DESCRIPTION));
                list.add(visit);
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
