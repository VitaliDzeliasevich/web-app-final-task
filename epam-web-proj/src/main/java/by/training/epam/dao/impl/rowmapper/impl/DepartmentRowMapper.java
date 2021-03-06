package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.entity.Department;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRowMapper implements RowMapper<Department> {
    private static final Logger log = Logger.getLogger(DepartmentRowMapper.class);

    @Override
    public List<Department> map(ResultSet resultSet) throws DAOException{
        List<Department> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt(SQLColumnLabel.ID));
                department.setHospitalId(resultSet.getInt(SQLColumnLabel.DEPARTMENT_HOSPITAL_ID));
                department.setDepartmentTypeId(resultSet.getInt(SQLColumnLabel.DEPARTMENT_TYPE_ID));
                department.setNumber(resultSet.getInt(SQLColumnLabel.DEPARTMENT_NUMBER));
                department.setPhone(resultSet.getString(SQLColumnLabel.DEPARTMENT_PHONE));
                list.add(department);
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