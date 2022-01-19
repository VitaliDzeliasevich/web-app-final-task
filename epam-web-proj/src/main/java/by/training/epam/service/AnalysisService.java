package by.training.epam.service;

import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.interfaces.AnalysisDAO;
import by.training.epam.entity.Analysis;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class AnalysisService implements Service<Analysis>{

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
            log.log(Level.ERROR,e);
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
        try {
            AnalysisDAO analysisDAO = DAOFactory.getInstance().getAnalysisDAO();
            int createdAnalysisId = analysisDAO.create(entity);
            if (createdAnalysisId!=0){created = true;}
        } catch (DAOException e) {
            log.log(Level.ERROR,e);
            throw new ServiceException(e);
        }
        return created;
    }
}
