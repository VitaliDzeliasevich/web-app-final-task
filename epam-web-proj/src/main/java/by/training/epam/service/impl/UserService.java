package by.training.epam.service.impl;


import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.interfaces.UserDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.User;
import by.training.epam.service.Service;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.validator.Validator;
import by.training.epam.service.validator.exception.ValidationException;
import by.training.epam.service.validator.impl.LoginPasswordValidator;
import by.training.epam.service.validator.impl.PhoneNumberValidator;

import java.util.List;

public class UserService implements Service<User> {

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
        boolean authorized;
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
            int generatedUserId = userDAO.create(entity);
            if (generatedUserId!=0) {
                created = true;
            }
        } catch (DAOException e) {
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
            throw new ServiceException(e);
        }
        return role;
    }

    public boolean validatePhone(String phone) throws ServiceException{
        Validator validator = PhoneNumberValidator.getInstance();
        boolean valid;
        try {
            valid = validator.validate(phone);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }
        return valid;
    }

    public boolean validateLoginAndPassword(String login, String password) throws ServiceException{
        Validator validator = LoginPasswordValidator.getInstance();
        boolean valid;
        try {
            valid = validator.validate(login, password);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }
        return valid;
    }

    public User getByLogin(String login) throws ServiceException {
        User user;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            user = userDAO.getByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    public boolean isNotBlocked(String login) throws ServiceException {
        boolean isNotBlocked;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            isNotBlocked = userDAO.checkIfBlocked(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isNotBlocked;
    }

    public void blockUser(int id) throws ServiceException {

        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            userDAO.blockUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void unblockUser(int id) throws ServiceException {

        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            userDAO.unblockUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
