package by.training.epam.dao.impl.requestoperator;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;

public interface UniversalRequestOperator {
    int create(String SQLRequest, ConnectionPool connectionPool, Object...attributes) throws DAOException;

    boolean delete(String SQLRequest, ConnectionPool connectionPool, int id) throws DAOException;

    boolean update(String SQLRequest, ConnectionPool connectionPool, Object...attributes) throws DAOException;
}
