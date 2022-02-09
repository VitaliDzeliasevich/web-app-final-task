package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.entity.Diagnostic;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticRowMapper implements RowMapper<Diagnostic> {

    private static final Logger log = Logger.getLogger(DiagnosticRowMapper.class);

    @Override
    public List<Diagnostic> map(ResultSet resultSet) throws DAOException{
        List<Diagnostic> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Diagnostic diagnostic = new Diagnostic();
                diagnostic.setId(resultSet.getInt(SQLColumnLabel.ID));
                diagnostic.setPatientId(resultSet.getInt(SQLColumnLabel.DIAGNOSTIC_PATIENT_ID));
                diagnostic.setType(resultSet.getString(SQLColumnLabel.DIAGNOSTIC_NAME));
                diagnostic.setAppointmentDate(resultSet.getString(SQLColumnLabel.APPOINTMENT_DATE));
                diagnostic.setExecutionDate(resultSet.getString(SQLColumnLabel.EXECUTION_DATE));
                diagnostic.setResult(resultSet.getString(SQLColumnLabel.RESULT));
                list.add(diagnostic);
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
