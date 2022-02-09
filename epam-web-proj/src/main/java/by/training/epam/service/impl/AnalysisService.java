package by.training.epam.service.impl;

import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.interfaces.AnalysisDAO;
import by.training.epam.entity.Analysis;
import by.training.epam.entity.transfer.PatientAnalysis;
import by.training.epam.service.Service;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.validator.Validator;
import by.training.epam.service.validator.exception.ValidationException;
import by.training.epam.service.validator.impl.DateValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class AnalysisService implements Service<Analysis> {

    private static final Logger log = Logger.getLogger(AnalysisService.class);
    private static final AnalysisService instance = new AnalysisService();

    private AnalysisService() {}

    public static AnalysisService getInstance() {
        return instance;
    }

    @Override
    public List<Analysis> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Analysis getEntityById(int id) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Analysis entity) throws ServiceException {
        boolean updated;
        try {
            AnalysisDAO analysisDAO = DAOFactory.getInstance().getAnalysisDAO();
            updated = analysisDAO.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return updated;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        return false;
    }

    @Override
    public boolean create(Analysis entity) throws ServiceException {
        boolean created = false;
        if (entity.getAppointmentDate().isEmpty() || entity.getAppointmentDate() == null || entity.getPatientId() == 0) {
            created=false;
        } else {
            try {
                AnalysisDAO analysisDAO = DAOFactory.getInstance().getAnalysisDAO();
                int createdAnalysisId = analysisDAO.create(entity);
                if (createdAnalysisId != 0) {
                    created = true;
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return created;
    }

    public List<Analysis> getByPatientId(int patientId) throws ServiceException {
        List<Analysis> analysisList;
        try {
            AnalysisDAO analysisDAO = DAOFactory.getInstance().getAnalysisDAO();
            analysisList = analysisDAO.getByHistoryId(patientId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return analysisList;
    }

    public List<PatientAnalysis> getByDepartmentAndDate(int departmentId, String date) throws ServiceException {
        List<PatientAnalysis> analysisList;
        try {
            AnalysisDAO analysisDAO = DAOFactory.getInstance().getAnalysisDAO();
            analysisList = analysisDAO.getByDepartmentIdAndDate(departmentId, date);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return analysisList;
    }

    public boolean validateDate(String date) throws ServiceException {

        Validator validator = DateValidator.getInstance();
        boolean valid;
        try {
            valid = validator.validate(date);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }
        return valid;
    }
}
