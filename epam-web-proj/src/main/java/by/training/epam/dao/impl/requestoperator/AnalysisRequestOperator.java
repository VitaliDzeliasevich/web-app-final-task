package by.training.epam.dao.impl.requestoperator;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.Analysis;
import by.training.epam.entity.transfer.PatientAnalysis;

import java.util.List;

public interface AnalysisRequestOperator extends RequestOperator<Analysis>{
    List<PatientAnalysis> findByDepartmentAndDate(String SQLRequest, Object...attributes) throws DAOException;
}
