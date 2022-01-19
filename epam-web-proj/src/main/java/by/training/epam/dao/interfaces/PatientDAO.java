package by.training.epam.dao.interfaces;


import by.training.epam.dao.AbstractDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.DiseaseHistory;
import by.training.epam.entity.Patient;

import java.util.List;

public interface PatientDAO extends AbstractDAO<Patient> {

    List<Patient> getBySurname(String surname) throws DAOException;

    boolean createWithHistory(Patient patient) throws DAOException;

    boolean discharge(DiseaseHistory history) throws DAOException;
}
