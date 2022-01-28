package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
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
    public List<Analysis> map(ResultSet resultSet) throws DAOException{
        List<Analysis> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Analysis analysis = new Analysis();
                analysis.setId(resultSet.getInt(SQLColumnLabel.ID));
                analysis.setType(resultSet.getString(SQLColumnLabel.TYPE));
                analysis.setAppointmentDate(resultSet.getString(SQLColumnLabel.APPOINTMENT_DATE));
                analysis.setExecutionDate(resultSet.getString(SQLColumnLabel.EXECUTION_DATE));
                analysis.setResult(resultSet.getString(SQLColumnLabel.RESULT));
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
