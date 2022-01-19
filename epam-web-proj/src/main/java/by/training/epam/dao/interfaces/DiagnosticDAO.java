package by.training.epam.dao.interfaces;

import by.training.epam.dao.AbstractDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.Diagnostic;
;

import java.util.List;

public interface DiagnosticDAO extends AbstractDAO<Diagnostic> {
    List<Diagnostic> getByHistoryId(int id) throws DAOException;
}
