package dao.connection;

public class ConnectionPoolFactory {

    private static final ConnectionPool connectionPool = new ConnectionPool();
    private static final ConnectionPoolFactory instance = new ConnectionPoolFactory();

    static {
        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    private ConnectionPoolFactory() {}

    public static ConnectionPoolFactory getInstance() {
        return instance;
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }


}
