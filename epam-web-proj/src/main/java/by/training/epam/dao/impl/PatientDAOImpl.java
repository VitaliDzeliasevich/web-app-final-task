package by.training.epam.dao.impl;

import by.training.epam.dao.impl.requestoperator.PatientRequestOperator;
import by.training.epam.dao.interfaces.PatientDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.PatientRequestOperatorImpl;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.dao.impl.tableinfo.TableTitle;
import by.training.epam.entity.DiseaseHistory;
import by.training.epam.entity.Patient;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    private final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.PATIENT_TABLE);
    private static final String GET_BY_ID_REQUEST = ("SELECT %s.%s, %s, %s, %s, %s, %s, %s, %s FROM %s INNER JOIN %s ON  " +
            "%s.%s = %s INNER JOIN %s ON  %s = %s.%s INNER JOIN %s ON %s = %s.%s WHERE %s.%s = ?").formatted(
                    TableTitle.PATIENT_TABLE, ColumnLabel.ID, ColumnLabel.PATIENT_SURNAME,
            ColumnLabel.PATIENT_NAME, ColumnLabel.PATIENT_SEX, ColumnLabel.PATIENT_BIRTHDATE, ColumnLabel.ROOM_NUMBER,
            ColumnLabel.DEPARTMENT_NUMBER, ColumnLabel.DEPARTMENT_TYPE, TableTitle.PATIENT_TABLE, TableTitle.ROOM_TABLE,
            TableTitle.ROOM_TABLE, ColumnLabel.ID, ColumnLabel.PATIENT_ROOM_ID, TableTitle.DEPARTMENT_TABLE,
            ColumnLabel.ROOM_DEPARTMENT_ID, TableTitle.DEPARTMENT_TABLE, ColumnLabel.ID, TableTitle.DEPARTMENT_TYPE_TABLE,
            ColumnLabel.DEPARTMENT_TYPE_ID, TableTitle.DEPARTMENT_TYPE_TABLE, ColumnLabel.ID,TableTitle.PATIENT_TABLE,
            ColumnLabel.ID);
    private static final String GET_BY_SURNAME_REQUEST = ("SELECT %s.%s, %s, %s, %s, %s, %s, %s, %s FROM %s INNER JOIN %s ON  " +
            "%s.%s = %s INNER JOIN %s ON  %s = %s.%s INNER JOIN %s ON %s = %s.%s WHERE %s = ?").formatted(
            TableTitle.PATIENT_TABLE, ColumnLabel.ID,ColumnLabel.PATIENT_SURNAME,
            ColumnLabel.PATIENT_NAME, ColumnLabel.PATIENT_SEX, ColumnLabel.PATIENT_BIRTHDATE, ColumnLabel.ROOM_NUMBER,
            ColumnLabel.DEPARTMENT_NUMBER, ColumnLabel.DEPARTMENT_TYPE, TableTitle.PATIENT_TABLE, TableTitle.ROOM_TABLE,
            TableTitle.ROOM_TABLE, ColumnLabel.ID, ColumnLabel.PATIENT_ROOM_ID, TableTitle.DEPARTMENT_TABLE,
            ColumnLabel.ROOM_DEPARTMENT_ID, TableTitle.DEPARTMENT_TABLE, ColumnLabel.ID, TableTitle.DEPARTMENT_TYPE_TABLE,
            ColumnLabel.DEPARTMENT_TYPE_ID, TableTitle.DEPARTMENT_TYPE_TABLE, ColumnLabel.ID, ColumnLabel.PATIENT_SURNAME);
    private static final String CREATE_PATIENT_REQUEST = "INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?, ?, ?, ?, ?)"
            .formatted(TableTitle.PATIENT_TABLE, ColumnLabel.PATIENT_ROOM_ID, ColumnLabel.PATIENT_SURNAME,
                    ColumnLabel.PATIENT_NAME, ColumnLabel.PATIENT_SEX, ColumnLabel.PATIENT_BIRTHDATE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.PATIENT_TABLE);

    public PatientDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public List<Patient> getBySurname(String surname) throws DAOException{
        PatientRequestOperator requestOp = PatientRequestOperatorImpl.getInstance();
        return  requestOp.findByParameters(GET_BY_SURNAME_REQUEST, connectionPool, surname);
    }


    @Override
    public List<Patient> getAll() throws DAOException {
        PatientRequestOperator requestOp = PatientRequestOperatorImpl.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST,connectionPool);
    }

    @Override
    public Patient getEntityById(int id) throws DAOException {
        PatientRequestOperator requestOp = PatientRequestOperatorImpl.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST, connectionPool, id).get(0);
    }

    @Override
    public boolean update(Patient entity) throws DAOException{
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID, connectionPool, id);
    }


    @Override
    public int create(Patient entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_PATIENT_REQUEST,connectionPool, entity.getRoomID(), entity.getSurname(),
                entity.getName(), entity.getSex(), entity.getBirthDate());
    }

    @Override
    public boolean createWithHistory(Patient entity) throws DAOException {
        PatientRequestOperator requestOp = PatientRequestOperatorImpl.getInstance();
        return requestOp.createWithHistory(connectionPool,entity);
    }

    @Override
    public boolean discharge(DiseaseHistory history) throws DAOException {
        PatientRequestOperator requestOp = PatientRequestOperatorImpl.getInstance();
        return requestOp.discharge(connectionPool,history);
    }


}
