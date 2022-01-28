package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.entity.Consultation;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultingRowMapper implements RowMapper<Consultation> {

    private static final Logger log = Logger.getLogger(ConsultingRowMapper.class);

    @Override
    public List<Consultation> map(ResultSet resultSet) throws DAOException{
        List<Consultation> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Consultation consulting = new Consultation();
                consulting.setId(resultSet.getInt(SQLColumnLabel.ID));
                consulting.setPatientId(resultSet.getInt(SQLColumnLabel.PATIENT_ID));
                consulting.setConsultantDrId(resultSet.getInt(SQLColumnLabel.CONSULTING_CONSULTANT_DR_ID));
                consulting.setDescription(resultSet.getString(SQLColumnLabel.CONSULTING_DESCRIPTION));
                consulting.setDate(resultSet.getString(SQLColumnLabel.CONSULTING_DATE));
                list.add(consulting);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, e);
            throw new DAOException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            }
        }
            return list;
    }
}
