package by.training.epam.service.impl;

import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.interfaces.PatientDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.entity.DiseaseHistory;
import by.training.epam.entity.Patient;
import by.training.epam.service.Service;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class PatientService implements Service<Patient> {

    Logger log = Logger.getLogger(PatientService.class);

    private static final PatientService instance = new PatientService();

    public static PatientService getInstance() {
        return instance;
    }

    private PatientService() {}

    public List<Patient> getBySurname(String surname) throws ServiceException {
        PatientDAO patientDAO;
        List<Patient> list;
        try {
            patientDAO = DAOFactory.getInstance().getPatientDAO();
            list = patientDAO.getBySurname(surname);
        } catch (DAOException e) {
            log.log(Level.ERROR,e);
            throw new ServiceException(e);
        }
            return list;
    }

    public boolean discharge(DiseaseHistory history) throws ServiceException {
        PatientDAO patientDAO;
        boolean discharged;

        try {
            patientDAO = DAOFactory.getInstance().getPatientDAO();
            discharged = patientDAO.discharge(history);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
/////////////////////////////////!!!!!!!!!!!!!!!!!!!
        return true;
    }

    @Override
    public List<Patient> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Patient getEntityById(int id) throws ServiceException {
        Patient patient;
        PatientDAO patientDAO;
        try {
            patientDAO = DAOFactory.getInstance().getPatientDAO();
            patient = patientDAO.getEntityById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patient;
    }

    @Override
    public boolean update(Patient entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(Patient entity) throws ServiceException {
        boolean created;
        try {
            PatientDAO patientDAO = DAOFactory.getInstance().getPatientDAO();
            created = patientDAO.createWithHistory(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return created;
    }
}


