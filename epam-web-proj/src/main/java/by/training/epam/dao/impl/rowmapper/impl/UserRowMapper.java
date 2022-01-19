package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.entity.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<User> {

    private static final Logger log = Logger.getLogger(UserRowMapper.class);

    @Override
    public List<User> fillFields(ResultSet resultSet) throws DAOException {
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
            log.log(Level.ERROR,e);
            throw new DAOException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.log(Level.ERROR,e);
            }
        }
        return userList;
    }
}
