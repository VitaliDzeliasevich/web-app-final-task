package by.training.epam.dao.interfaces;

import by.training.epam.dao.AbstractDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.DrVisit;

import java.util.List;

public interface DrVisitDAO extends AbstractDAO<DrVisit> {
    List<DrVisit> getByHistoryId(int id) throws DAOException;
}
