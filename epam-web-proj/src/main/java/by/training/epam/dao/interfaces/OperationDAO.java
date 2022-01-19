package by.training.epam.dao.interfaces;



import by.training.epam.dao.AbstractDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.Operation;

import java.util.List;

public interface OperationDAO extends AbstractDAO<Operation> {
    List<Operation> getByHistoryId(int id) throws DAOException;
}
