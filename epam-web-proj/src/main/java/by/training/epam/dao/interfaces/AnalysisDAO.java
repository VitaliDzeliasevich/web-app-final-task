package by.training.epam.dao.interfaces;


import by.training.epam.dao.AbstractDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.Analysis;

import java.util.List;

public interface AnalysisDAO extends AbstractDAO<Analysis> {
    List<Analysis> getByHistoryId(int id) throws DAOException;
}
