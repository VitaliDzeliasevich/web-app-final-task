package by.training.epam.dao;

import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.connectionpool.ConnectionPoolFactory;
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

    private final ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
    private final ConnectionPool connectionPool = factory.getConnectionPool();

    private  final UserDAO userDAO = new UserDAOImpl(connectionPool);
    private  final PatientDAO patientDAO = new PatientDAOImpl(connectionPool);


    public UserDAO getUserDAO() throws DAOException {
        return  userDAO;
    }
    public PatientDAO getPatientDAO() throws DAOException  {
        return  patientDAO;
    }
    public OperationDAO getOperationDAO() throws DAOException {
        return  new OperationDAOImpl(connectionPool);
    }
    public DrVisitDAO getDrVisitDAO() throws DAOException {
        return  new DrVisitDAOImpl(connectionPool);
    }
    public DiseaseHistoryDAO getDiseaseHistoryDAO() throws DAOException {
        return  new DiseaseHistoryDAOImpl(connectionPool);
    }
    public DiagnosticDAO getDiagnosticDAO() throws DAOException {
        return  new DiagnosticDAOImpl(connectionPool);
    }
    public ConsultingDAO getConsultingDAO() throws DAOException {
        return  new ConsultingDAOImpl(connectionPool);
    }

    public AnalysisDAO getAnalysisDAO() throws DAOException {
        return  new AnalysisDAOImpl(connectionPool);
    }

    public AbstractDAO<Room> getRoomDAO() throws DAOException {
        return  new RoomDAOImpl(connectionPool);
    }

    public AbstractDAO<Department> getDepartmentDAO() throws DAOException {
        return  new DepartmentDAOImpl(connectionPool);
    }

    public void disposePool() {
        connectionPool.dispose();
    }
}
