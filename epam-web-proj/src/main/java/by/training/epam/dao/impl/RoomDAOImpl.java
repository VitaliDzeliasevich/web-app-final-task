package by.training.epam.dao.impl;

import by.training.epam.dao.AbstractDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.RoomRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.dao.impl.tableinfo.TableTitle;
import by.training.epam.entity.Room;

import java.util.List;

public class RoomDAOImpl implements AbstractDAO<Room> {

    private  final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.ROOM_TABLE);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.ROOM_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s,%s) VALUES (?, ?, ?, ?)".formatted(TableTitle.ROOM_TABLE,
                    ColumnLabel.ROOM_DEPARTMENT_ID,ColumnLabel.ROOM_LEADING_DR_ID,
                    ColumnLabel.ROOM_NUMBER, ColumnLabel.ROOM_BEDS_AMOUNT);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.ROOM_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?  WHERE id = ?".formatted
            (TableTitle.ROOM_TABLE,
                    ColumnLabel.ROOM_LEADING_DR_ID, ColumnLabel.ROOM_BEDS_AMOUNT);

//    private final static UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();

    public RoomDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Room> getAll() throws DAOException {
        RequestOperator<Room> requestOp = RoomRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST, connectionPool);
    }

    @Override
    public Room getEntityById(int id) throws DAOException {
        RequestOperator<Room> requestOp = RoomRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST, connectionPool,id).get(0);
    }

    @Override
    public boolean update(Room entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public int create(Room entity) throws DAOException {
        return 0;
    }
}
