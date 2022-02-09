package by.training.epam.dao.interfaces;


import by.training.epam.dao.AbstractDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.Analysis;
import by.training.epam.entity.transfer.PatientAnalysis;

import java.util.List;

public interface AnalysisDAO extends AbstractDAO<Analysis> {
    List<Analysis> getByHistoryId(int id) throws DAOException;

    List<PatientAnalysis> getByDepartmentIdAndDate(int departmentId, String date) throws DAOException;
}
