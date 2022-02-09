package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.entity.DrugTreatment;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugTreatmentRowMapper implements RowMapper<DrugTreatment> {

    private static final Logger log = Logger.getLogger(DrugTreatmentRowMapper.class);

    private static final DrugTreatmentRowMapper instance = new DrugTreatmentRowMapper();

    public static DrugTreatmentRowMapper getInstance() {
        return instance;
    }

    private DrugTreatmentRowMapper() {}

    @Override
    public List<DrugTreatment> map(ResultSet resultSet) throws DAOException {
        List<DrugTreatment> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                DrugTreatment treatment = new DrugTreatment();
                treatment.setId(resultSet.getInt(SQLColumnLabel.ID));
                treatment.setDrugName(resultSet.getString(SQLColumnLabel.DRUG_NAME));
                treatment.setDrSurname(resultSet.getString(SQLColumnLabel.USER_SURNAME));
                treatment.setStartDate(resultSet.getString(SQLColumnLabel.DRUG_TREATMENT_START_DATE));
                treatment.setEndDate(resultSet.getString(SQLColumnLabel.DRUG_TREATMENT_END_DATE));
                treatment.setDose(resultSet.getString(SQLColumnLabel.DRUG_TREATMENT_DOSE));
                treatment.setWayOfUsing(resultSet.getString(SQLColumnLabel.WAY_OF_USING));
                treatment.setIsInterrupted(resultSet.getByte(SQLColumnLabel.DRUG_TREATMENT_IS_INTERRUPTED));
                list.add(treatment);
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
