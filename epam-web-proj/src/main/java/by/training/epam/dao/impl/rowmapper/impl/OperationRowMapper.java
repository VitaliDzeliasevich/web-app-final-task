package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.entity.Operation;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationRowMapper implements RowMapper<Operation> {

    private static final Logger log = Logger.getLogger(OperationRowMapper.class);

    @Override
    public List<Operation> map(ResultSet resultSet) throws DAOException{
        List<Operation> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Operation operation = new Operation();
                operation.setId(resultSet.getInt(SQLColumnLabel.ID));
                operation.setPatientId(resultSet.getInt(SQLColumnLabel.PATIENT_ID));
                operation.setOperationTypeId(resultSet.getInt(SQLColumnLabel.OPERATION_TYPE_ID));
                operation.setPlannedDate(resultSet.getString(SQLColumnLabel.OPERATION_PLANNED_DATE));
                operation.setExecutionDate(resultSet.getString(SQLColumnLabel.EXECUTION_DATE));
                operation.setId(resultSet.getInt(SQLColumnLabel.OPERATION_SURGEON_ID));
                operation.setDescription(resultSet.getString(SQLColumnLabel.OPERATION_DESCRIPTION));
                list.add(operation);
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