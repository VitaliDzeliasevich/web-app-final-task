package by.training.epam.service;

import by.training.epam.service.exception.ServiceException;

import java.util.List;

public interface Service<E> {

    List<E> getAll() throws ServiceException;
    E getEntityById(int id) throws ServiceException;
    boolean update(E entity) throws ServiceException;
    boolean delete(int id) throws ServiceException;
    boolean create(E entity) throws ServiceException;
}
