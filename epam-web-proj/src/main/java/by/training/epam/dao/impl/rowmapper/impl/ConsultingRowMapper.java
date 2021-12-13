package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.entity.Consulting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultingRowMapper implements RowMapper<Consulting> {

    @Override
    public List<Consulting> fillFields(ResultSet resultSet) {
        List<Consulting> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Consulting consulting = new Consulting();
                consulting.setId(resultSet.getInt(ColumnLabel.ID));
                consulting.setPatientId(resultSet.getInt(ColumnLabel.PATIENT_ID));
                consulting.setConsultantDrId(resultSet.getInt(ColumnLabel.CONSULTING_CONSULTANT_DR_ID));
                consulting.setDescription(resultSet.getString(ColumnLabel.CONSULTING_DESCRIPTION));
                consulting.setDate(resultSet.getString(ColumnLabel.CONSULTING_DATE));
                list.add(consulting);
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
