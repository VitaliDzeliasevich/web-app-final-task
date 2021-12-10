package dao;

import dao.connection.ConnectionPool;
import dao.connection.ConnectionPoolFactory;
import dao.exeption.DAOException;
import dao.impl.*;
public class DAOFactory  {

    private DAOFactory ()  {}

    private static final DAOFactory daoFactory = new DAOFactory();

    public static DAOFactory getInstance() throws DAOException {
        return daoFactory; }

    private final ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
    private final ConnectionPool connectionPool = factory.getConnectionPool();


    public UserDAO getUserDAO() throws DAOException {
        return  new UserDAOImpl(connectionPool);
    }
    public PatientDAO getPatientDAO() throws DAOException  {
        return  new PatientDAOImpl(connectionPool);
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

    public void disposePool() {
        connectionPool.dispose();
    }
}
