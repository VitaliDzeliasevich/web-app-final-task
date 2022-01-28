package by.training.epam.dao.connectionpool;

public class ConnectionPoolFactory {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ConnectionPoolFactory instance = new ConnectionPoolFactory();


    private ConnectionPoolFactory() {}

    public static ConnectionPoolFactory getInstance() {
        return instance;
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }


}
