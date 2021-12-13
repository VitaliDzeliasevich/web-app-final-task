package by.training.epam.dao;


import by.training.epam.entity.User;

public interface UserDAO extends AbstractDAO<User> {
    boolean authorized(String login, String password);
}
