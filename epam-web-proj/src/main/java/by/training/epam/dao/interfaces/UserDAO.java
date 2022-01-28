package by.training.epam.dao.interfaces;


import by.training.epam.dao.AbstractDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.User;

public interface UserDAO extends AbstractDAO<User> {
    boolean authorized(String login, String password) throws DAOException;

    boolean isLoginExist(String login) throws DAOException;

    String getRole(String login) throws DAOException;

}
