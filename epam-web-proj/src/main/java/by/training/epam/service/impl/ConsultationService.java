package by.training.epam.service.impl;

import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.interfaces.ConsultingDAO;
import by.training.epam.entity.Consultation;
import by.training.epam.service.Service;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class ConsultationService implements Service<Consultation> {

    private static final Logger log = Logger.getLogger(ConsultationService.class);
    private static final ConsultationService instance = new ConsultationService();

    private ConsultationService() {}

    public static ConsultationService getInstance() {
        return instance;
    }

    @Override
    public List<Consultation> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Consultation getEntityById(int id) throws ServiceException {
        return null;
    }

    @Override
    public boolean update(Consultation entity) throws ServiceException {
        boolean updated;
        try {
            ConsultingDAO consultingDAO = DAOFactory.getInstance().getConsultingDAO();
            updated = consultingDAO.update(entity);
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
    public boolean create(Consultation entity) throws ServiceException {
        boolean created = false;
        try {
            ConsultingDAO consultingDAO = DAOFactory.getInstance().getConsultingDAO();
            int generated = consultingDAO.create(entity);
            if (generated!=0){created = true;}
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return created;
    }
}
