package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
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
    public List<User> map(ResultSet resultSet) throws DAOException {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(SQLColumnLabel.ID));
                user.setRoleId(resultSet.getInt(SQLColumnLabel.USER_ROLE));
                user.setLogin(resultSet.getString(SQLColumnLabel.USER_LOGIN));
                user.setPassword(null);
                user.setName(resultSet.getString(SQLColumnLabel.USER_NAME));
                user.setSurname(resultSet.getString(SQLColumnLabel.USER_SURNAME));
                user.setDepartmentsId(resultSet.getInt(SQLColumnLabel.USER_DEPARTMENT));
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
