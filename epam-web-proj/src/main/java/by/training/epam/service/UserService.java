package by.training.epam.service;


import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.interfaces.UserDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.User;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class);
    private static final UserService instance = new UserService();

    private UserService() {}

    public static UserService getInstance() {
        return instance;
    }

    public List<User> getAll() throws ServiceException {
        List<User> users;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            users = userDAO.getAll();
        } catch (DAOException e) {
            log.log(Level.ERROR,e);
            throw new ServiceException(e);
        }
        return users;
    }




    public User getEntityById(int id) throws ServiceException {
        User user;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            user = userDAO.getEntityById(id);
        } catch (DAOException e) {
            log.log(Level.ERROR,e);
            throw new ServiceException(e);
        }
        return user;
    }

    public boolean authorized(String login, String password) throws ServiceException {
        boolean authorized;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            authorized = userDAO.authorized(login, password);
        } catch (DAOException e) {
            log.log(Level.ERROR,e);
            throw new ServiceException(e);
        }
        return authorized;
    }


    public boolean update(User entity) {

        return false;
    }


    public boolean delete(int id) {
        return false;
    }


    public boolean create(User entity) throws ServiceException{
        boolean created = false;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            int generatedUserId = userDAO.create(entity);
            if (generatedUserId!=0) {
                created = true;
            }
        } catch (DAOException e) {
            log.log(Level.ERROR,e);
            throw new ServiceException(e);
        }
        return created;
    }

    public boolean isLoginExist(String login) throws ServiceException {
        boolean isLoginExist;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            isLoginExist = userDAO.isLoginExist(login);
        } catch (DAOException e) {
            log.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isLoginExist;
    }

    public String getRole(String login) throws ServiceException {
        String role;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
           role = userDAO.getRole(login);
        } catch (DAOException e) {
            log.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return role;
    }
}
