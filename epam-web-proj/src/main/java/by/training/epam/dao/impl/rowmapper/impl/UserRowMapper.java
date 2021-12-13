package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.entity.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public List<User> fillFields(ResultSet resultSet) {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ColumnLabel.ID));
                user.setRoleId(resultSet.getInt(ColumnLabel.USER_ROLE));
                user.setLogin(resultSet.getString(ColumnLabel.USER_LOGIN));
                user.setPassword(null);
                user.setName(resultSet.getString(ColumnLabel.USER_NAME));
                user.setSurname(resultSet.getString(ColumnLabel.USER_SURNAME));
                user.setDepartmentsId(resultSet.getInt(ColumnLabel.USER_DEPARTMENT));
                userList.add(user);
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
        return userList;
    }
}
