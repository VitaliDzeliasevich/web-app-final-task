package by.training.epam.dao.impl.requestoperator;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;

import java.util.List;

public interface RequestOperator<T> {
    List<T> findAll(String SQLRequest) throws DAOException;

    List<T> findByParameters(String SQLRequest, Object...attributes) throws DAOException;


}
