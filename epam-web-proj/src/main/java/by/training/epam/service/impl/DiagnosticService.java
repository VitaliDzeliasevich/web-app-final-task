package by.training.epam.service.impl;

import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.interfaces.DiagnosticDAO;
import by.training.epam.entity.Diagnostic;
import by.training.epam.service.Service;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.validator.Validator;
import by.training.epam.service.validator.exception.ValidationException;
import by.training.epam.service.validator.impl.DateValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class DiagnosticService implements Service<Diagnostic> {

    private static final DiagnosticService instance = new DiagnosticService();

    private DiagnosticService() {}

    public static DiagnosticService getInstance() {
        return instance;
    }

    @Override
    public List<Diagnostic> getAll() throws ServiceException {

        try {
            DiagnosticDAO diagnosticDAO = DAOFactory.getInstance().getDiagnosticDAO();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public Diagnostic getEntityById(int id) throws ServiceException {
        try {
            DiagnosticDAO diagnosticDAO = DAOFactory.getInstance().getDiagnosticDAO();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public boolean update(Diagnostic entity) throws ServiceException {
        boolean updated;
        try {
            DiagnosticDAO diagnosticDAO = DAOFactory.getInstance().getDiagnosticDAO();
            updated = diagnosticDAO.update(entity);
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
    public boolean create(Diagnostic entity) throws ServiceException {
        boolean created = false;
        try {
            DiagnosticDAO diagnosticDAO = DAOFactory.getInstance().getDiagnosticDAO();
            int createdAnalysisId = diagnosticDAO.create(entity);
            if (createdAnalysisId!=0){created = true;}
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return created;
    }

    public List<Diagnostic> getByPatientId(int id) throws ServiceException {
            List<Diagnostic> list;
        try {
            DiagnosticDAO diagnosticDAO = DAOFactory.getInstance().getDiagnosticDAO();
            list = diagnosticDAO.getByHistoryId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return list;
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
