package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.entity.Operation;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationRowMapper implements RowMapper<Operation> {
    @Override
    public List<Operation> fillFields(ResultSet resultSet) {
        List<Operation> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Operation operation = new Operation();
                operation.setId(resultSet.getInt(ColumnLabel.ID));
                operation.setPatientId(resultSet.getInt(ColumnLabel.PATIENT_ID));
                operation.setOperationTypeId(resultSet.getInt(ColumnLabel.OPERATION_TYPE_ID));
                operation.setPlannedDate(resultSet.getString(ColumnLabel.OPERATION_PLANNED_DATE));
                operation.setExecutionDate(resultSet.getString(ColumnLabel.EXECUTION_DATE));
                operation.setId(resultSet.getInt(ColumnLabel.OPERATION_SURGEON_ID));
                operation.setDescription(resultSet.getString(ColumnLabel.OPERATION_DESCRIPTION));
                list.add(operation);
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