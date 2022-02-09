package by.training.epam.service.impl;

import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.interfaces.DrugTreatmentDAO;
import by.training.epam.entity.DrugTreatment;
import by.training.epam.service.Service;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.validator.Validator;
import by.training.epam.service.validator.exception.ValidationException;
import by.training.epam.service.validator.impl.DateValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DrugTreatmentService implements Service<DrugTreatment> {

    private static final DrugTreatmentService instance = new DrugTreatmentService();

    private DrugTreatmentService() {}

    public static DrugTreatmentService getInstance() {
        return instance;
    }

    @Override
    public List<DrugTreatment> getAll() throws ServiceException {
        return null;
    }

    @Override
    public DrugTreatment getEntityById(int id) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(DrugTreatment entity) throws ServiceException {
        boolean updated;
        try {
            DrugTreatmentDAO drugTreatmentDAO = DAOFactory.getInstance().getDrugTreatmentDAO();
            updated = drugTreatmentDAO.update(entity);
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
    public boolean create(DrugTreatment entity) throws ServiceException {
        boolean created = false;
            try {
                DrugTreatmentDAO drugTreatmentDAO = DAOFactory.getInstance().getDrugTreatmentDAO();
                int createdTreatmentId = drugTreatmentDAO.create(entity);
                if (createdTreatmentId != 0) {
                    created = true;
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        return created;
    }

    public List<DrugTreatment> getByPatientId(int patientId) throws ServiceException {
        List<DrugTreatment> list;
        try {
            DrugTreatmentDAO drugTreatmentDAO = DAOFactory.getInstance().getDrugTreatmentDAO();
            list = drugTreatmentDAO.getByHistoryId(patientId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        for (DrugTreatment entity : list) {
            chekIfTreatmentTerminated(entity);
        }
        return list;
    }

    public void interrupt(int id) throws ServiceException {
        try {
            DrugTreatmentDAO drugTreatmentDAO = DAOFactory.getInstance().getDrugTreatmentDAO();
            drugTreatmentDAO.interrupt(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean validateDate(String...date) throws ServiceException {

        Validator validator = DateValidator.getInstance();
        boolean valid;
        try {
                valid = validator.validate(date);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }
        return valid;
    }

    private void chekIfTreatmentTerminated(DrugTreatment entity) throws ServiceException{
        Date actualDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date therapyEndDate;
        Date formattedActualDate;
        try {
            formattedActualDate = formatter.parse(formatter.format(actualDate));
            therapyEndDate = formatter.parse(entity.getEndDate());
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
        if (formattedActualDate.compareTo(therapyEndDate) > 0) {
            entity.setTerminated(true);
        }
    }



}
