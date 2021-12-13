package by.training.epam.dao.connectionpool;

import java.util.ResourceBundle;

public class DBProperties {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("db");

    static final String DRIVER = bundle.getString("db.driver");
    static final String URL = bundle.getString("db.url");
    static final String USER = bundle.getString("db.user");
    static final String PASSWORD = bundle.getString("db.password");
    static final String POOL_SIZE = bundle.getString("db.poolsize");
}
