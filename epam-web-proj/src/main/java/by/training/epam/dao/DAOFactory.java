package by.training.epam.dao;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.*;
import by.training.epam.dao.interfaces.*;
import by.training.epam.entity.Department;
import by.training.epam.entity.Room;

public class DAOFactory  {

    private DAOFactory ()  {}

    private static final DAOFactory daoFactory = new DAOFactory();

    public static DAOFactory getInstance() {
        return daoFactory; }

    public UserDAO getUserDAO() throws DAOException {
        return  UserDAOImpl.getInstance();
    }
    public PatientDAO getPatientDAO() throws DAOException  {
        return  PatientDAOImpl.getInstance();
    }
    public OperationDAO getOperationDAO() throws DAOException {
        return OperationDAOImpl.getInstance();
    }
    public DrVisitDAO getDrVisitDAO() throws DAOException {
        return  DrVisitDAOImpl.getInstance();
    }
    public DiseaseHistoryDAO getDiseaseHistoryDAO() throws DAOException {
        return  DiseaseHistoryDAOImpl.getInstance();
    }
    public DiagnosticDAO getDiagnosticDAO() throws DAOException {
        return DiagnosticDAOImpl.getInstance();
    }
    public ConsultingDAO getConsultingDAO() throws DAOException {
        return ConsultingDAOImpl.getInstance();
    }

    public AnalysisDAO getAnalysisDAO() throws DAOException {
        return AnalysisDAOImpl.getInstance();
    }

    public AbstractDAO<Room> getRoomDAO() throws DAOException {
        return  RoomDAOImpl.getInstance();
    }

    public AbstractDAO<Department> getDepartmentDAO() throws DAOException {
        return DepartmentDAOImpl.getInstance();
    }

}
