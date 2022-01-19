package by.training.epam.dao.impl.requestoperator;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;

import java.util.List;

public interface RequestOperator<T> {
    List<T> findAll(String SQLRequest, ConnectionPool connectionPool) throws DAOException;

    List<T> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object...attributes) throws DAOException;


}
