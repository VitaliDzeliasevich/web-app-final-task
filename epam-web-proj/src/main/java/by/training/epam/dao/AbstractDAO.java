package by.training.epam.dao;

import by.training.epam.dao.exeption.DAOException;


import java.util.List;

public interface AbstractDAO<E> {

    List<E> getAll() throws DAOException;
    E getEntityById(int id) throws DAOException;
    boolean update(E entity) throws DAOException;
    boolean delete(int id) throws DAOException;
    boolean create(E entity) throws DAOException;

}
