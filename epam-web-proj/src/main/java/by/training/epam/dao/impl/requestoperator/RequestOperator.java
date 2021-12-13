package by.training.epam.dao.impl.requestoperator;

import by.training.epam.dao.connectionpool.ConnectionPool;

import java.util.List;

public interface RequestOperator<T> {
    List<T> findAll(String SQLRequest, ConnectionPool connectionPool);

    List<T> findByParameters(String SQLRequest, ConnectionPool connectionPool, Object...attributes);


}
