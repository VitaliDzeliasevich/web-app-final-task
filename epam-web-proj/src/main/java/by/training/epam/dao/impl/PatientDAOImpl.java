package by.training.epam.dao.impl;



import by.training.epam.dao.PatientDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.PatientRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.TableTitle;
import by.training.epam.entity.Patient;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    private final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.PATIENT_TABLE);
    private static final String GET_BY_ID_REQUEST = "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.PATIENT_TABLE);
    private static final String CREATE_REQUEST = "INSERT INTO %s VALUES (?, ?, ?, ?, ?, ?, ?)".formatted(TableTitle.PATIENT_TABLE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.PATIENT_TABLE);

    private final static RequestOperator<Patient> requestOp = PatientRequestOperator.getInstance();
    private final static UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();

    public PatientDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Patient> getAll() throws DAOException {
        return requestOp.findAll(GET_ALL_REQUEST,connectionPool);
    }

    @Override
    public Patient getEntityById(int id) throws DAOException {
        return  requestOp.findByParameters(GET_BY_ID_REQUEST, connectionPool, id).get(0);
    }

    @Override
    public boolean update(Patient entity) throws DAOException{
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return universalRequestOp.delete(DELETE_BY_ID, connectionPool, id);
    }


    @Override
    public boolean create(Patient entity) throws DAOException {
        return universalRequestOp.create(CREATE_REQUEST,connectionPool,
                entity.getId(), entity.getRoomID(), entity.getSurname(),
                entity.getName(), entity.getSex(), entity.getBirthDate(), entity.getAddress());
    }
}
