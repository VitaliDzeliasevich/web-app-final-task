package by.training.epam.dao.interfaces;

import by.training.epam.dao.AbstractDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.DrugTreatment;

import java.util.List;

public interface DrugTreatmentDAO extends AbstractDAO<DrugTreatment> {
    List<DrugTreatment> getByHistoryId(int id) throws DAOException;

    void interrupt(int id) throws DAOException;
}
