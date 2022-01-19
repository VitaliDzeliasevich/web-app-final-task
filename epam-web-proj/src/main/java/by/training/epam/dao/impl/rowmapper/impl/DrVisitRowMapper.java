package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.entity.DrVisit;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrVisitRowMapper implements RowMapper<DrVisit> {

    private static final Logger log = Logger.getLogger(DrVisitRowMapper.class);

    @Override
    public List<DrVisit> fillFields(ResultSet resultSet) throws DAOException{
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
