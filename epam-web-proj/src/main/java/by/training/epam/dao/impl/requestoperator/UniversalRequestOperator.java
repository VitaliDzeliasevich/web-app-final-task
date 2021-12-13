package by.training.epam.dao.impl.requestoperator;

import by.training.epam.dao.connectionpool.ConnectionPool;

public interface UniversalRequestOperator {
    boolean create(String SQLRequest, ConnectionPool connectionPool, Object...attributes);

    boolean delete(String SQLRequest, ConnectionPool connectionPool, int id);

    boolean update(String SQLRequest, ConnectionPool connectionPool, Object...attributes);
}
