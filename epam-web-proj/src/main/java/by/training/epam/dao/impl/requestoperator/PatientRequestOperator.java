package by.training.epam.dao.impl.requestoperator;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.DiseaseHistory;
import by.training.epam.entity.Patient;

public interface PatientRequestOperator extends RequestOperator<Patient>{

    boolean createWithHistory(Patient patient) throws DAOException;

    boolean discharge(DiseaseHistory history) throws DAOException;
}
