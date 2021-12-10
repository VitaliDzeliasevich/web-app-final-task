package dao.impl.requestoperator;

import dao.connection.ConnectionPool;

public interface UniversalRequestOperator {
    boolean create(String SQLRequest, ConnectionPool connectionPool, Object...attributes);

    boolean delete(String SQLRequest, ConnectionPool connectionPool, int id);

    boolean update(String SQLRequest, ConnectionPool connectionPool, Object...attributes);
}
