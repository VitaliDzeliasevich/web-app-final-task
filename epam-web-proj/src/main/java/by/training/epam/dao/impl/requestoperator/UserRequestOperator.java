package by.training.epam.dao.impl.requestoperator;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.User;

public interface UserRequestOperator extends RequestOperator<User>{

    String getRole(String SQLRequest, String login) throws DAOException;
}
