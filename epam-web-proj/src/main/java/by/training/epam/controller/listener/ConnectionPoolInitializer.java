package by.training.epam.controller.listener;

import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.connectionpool.ConnectionPoolFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConnectionPoolInitializer implements ServletContextListener {

    private final Logger log = LogManager.getLogger(ConnectionPoolInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPoolFactory.getInstance().getConnectionPool().initPoolData();
        } catch (ConnectionPoolException e) {
            log.error(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
            ConnectionPoolFactory.getInstance().getConnectionPool().dispose();
    }
}

