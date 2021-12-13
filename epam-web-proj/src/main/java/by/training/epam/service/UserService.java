package by.training.epam.service;


import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.UserDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.User;
import by.training.epam.service.exception.ServiceException;

import java.util.List;

public class UserService {

    UserService() {}

    public List<User> getAll() throws ServiceException {
        List<User> users;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            users = userDAO.getAll();
        } catch (DAOException e) {
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
            throw new ServiceException(e);
        }
        return user;
    }

    public boolean authorized(String login, String password) throws ServiceException {
        boolean authorized = false;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            authorized = userDAO.authorized(login, password);
        } catch (DAOException e) {
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
            created = userDAO.create(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return created;
    }
}
