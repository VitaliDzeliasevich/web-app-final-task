package by.training.epam.service.impl;

import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.interfaces.OperationDAO;
import by.training.epam.entity.Operation;
import by.training.epam.service.Service;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class OperationService implements Service<Operation> {

    private static final Logger log = Logger.getLogger(OperationService.class);
    private static final OperationService instance = new OperationService();

    private OperationService() {}

    public static OperationService getInstance() {
        return instance;
    }

    @Override
    public List<Operation> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Operation getEntityById(int id) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Operation entity) throws ServiceException {
        boolean updated;
        try {
            OperationDAO operationDAO = DAOFactory.getInstance().getOperationDAO();
           updated = operationDAO.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return updated;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(Operation entity) throws ServiceException {
        boolean created = false;
        try {
            OperationDAO operationDAO = DAOFactory.getInstance().getOperationDAO();
            int createdOperationId = operationDAO.create(entity);
            if (createdOperationId!=0){created = true;}
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return created;
    }
}
