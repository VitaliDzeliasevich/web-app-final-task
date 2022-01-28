package by.training.epam.dao.impl.rowmapper.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.entity.Room;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRowMapper implements RowMapper<Room> {

    private static final Logger log = Logger.getLogger(RoomRowMapper.class);

    @Override
    public List<Room> map(ResultSet resultSet) throws DAOException {
        List<Room> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt(SQLColumnLabel.ID));
                room.setDepartmentId(resultSet.getInt(SQLColumnLabel.ROOM_DEPARTMENT_ID));
                room.setLeadingDrId(resultSet.getInt(SQLColumnLabel.ROOM_LEADING_DR_ID));
                room.setNumber(resultSet.getInt(SQLColumnLabel.ROOM_NUMBER));
                list.add(room);
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
