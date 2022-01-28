package by.training.epam.dao.impl;

import by.training.epam.dao.impl.requestoperator.PatientRequestOperator;
import by.training.epam.dao.interfaces.PatientDAO;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.PatientRequestOperatorImpl;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.entity.DiseaseHistory;
import by.training.epam.entity.Patient;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(SQLTableTitle.PATIENT_TABLE);
    private static final String GET_BY_ID_REQUEST = ("SELECT %s.%s, %s, %s, %s, %s, %s, %s, %s FROM %s INNER JOIN %s ON  " +
            "%s.%s = %s INNER JOIN %s ON  %s = %s.%s INNER JOIN %s ON %s = %s.%s WHERE %s.%s = ?").formatted(
                    SQLTableTitle.PATIENT_TABLE, SQLColumnLabel.ID, SQLColumnLabel.PATIENT_SURNAME,
            SQLColumnLabel.PATIENT_NAME, SQLColumnLabel.PATIENT_SEX, SQLColumnLabel.PATIENT_BIRTHDATE, SQLColumnLabel.ROOM_NUMBER,
            SQLColumnLabel.DEPARTMENT_NUMBER, SQLColumnLabel.TYPE, SQLTableTitle.PATIENT_TABLE, SQLTableTitle.ROOM_TABLE,
            SQLTableTitle.ROOM_TABLE, SQLColumnLabel.ID, SQLColumnLabel.PATIENT_ROOM_ID, SQLTableTitle.DEPARTMENT_TABLE,
            SQLColumnLabel.ROOM_DEPARTMENT_ID, SQLTableTitle.DEPARTMENT_TABLE, SQLColumnLabel.ID, SQLTableTitle.DEPARTMENT_TYPE_TABLE,
            SQLColumnLabel.DEPARTMENT_TYPE_ID, SQLTableTitle.DEPARTMENT_TYPE_TABLE, SQLColumnLabel.ID, SQLTableTitle.PATIENT_TABLE,
            SQLColumnLabel.ID);
    private static final String GET_BY_SURNAME_REQUEST = ("SELECT %s.%s, %s, %s, %s, %s, %s, %s, %s, %s, %s FROM %s " +
            "INNER JOIN %s ON %s.%s = %s INNER JOIN %s ON  %s = %s.%s INNER JOIN %s ON %s = %s.%s INNER JOIN" +
            " %s ON %s.%s = %s.%s WHERE %s = ?").formatted(
            SQLTableTitle.PATIENT_TABLE, SQLColumnLabel.ID, SQLColumnLabel.PATIENT_SURNAME,
            SQLColumnLabel.PATIENT_NAME, SQLColumnLabel.PATIENT_SEX, SQLColumnLabel.PATIENT_BIRTHDATE,
            SQLColumnLabel.ROOM_NUMBER, SQLColumnLabel.DEPARTMENT_NUMBER, SQLColumnLabel.TYPE,
            SQLColumnLabel.DISEASE_HISTORY_ADMISSION_DATE, SQLColumnLabel.DISEASE_HISTORY_ADMISSION_DIAGNOSIS ,
            SQLTableTitle.PATIENT_TABLE, SQLTableTitle.ROOM_TABLE, SQLTableTitle.ROOM_TABLE, SQLColumnLabel.ID,
            SQLColumnLabel.PATIENT_ROOM_ID, SQLTableTitle.DEPARTMENT_TABLE, SQLColumnLabel.ROOM_DEPARTMENT_ID,
            SQLTableTitle.DEPARTMENT_TABLE, SQLColumnLabel.ID, SQLTableTitle.DEPARTMENT_TYPE_TABLE,
            SQLColumnLabel.DEPARTMENT_TYPE_ID, SQLTableTitle.DEPARTMENT_TYPE_TABLE,
            SQLColumnLabel.ID,SQLTableTitle.DISEASE_HISTORY_TABLE, SQLTableTitle.PATIENT_TABLE, SQLColumnLabel.ID,
            SQLTableTitle.DISEASE_HISTORY_TABLE,SQLColumnLabel.DISEASE_HISTORY_PATIENT_ID ,SQLColumnLabel.PATIENT_SURNAME);
    private static final String CREATE_PATIENT_REQUEST = "INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?, ?, ?, ?, ?)"
            .formatted(SQLTableTitle.PATIENT_TABLE, SQLColumnLabel.PATIENT_ROOM_ID, SQLColumnLabel.PATIENT_SURNAME,
                    SQLColumnLabel.PATIENT_NAME, SQLColumnLabel.PATIENT_SEX, SQLColumnLabel.PATIENT_BIRTHDATE);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(SQLTableTitle.PATIENT_TABLE);

    private PatientDAOImpl() {}

    private static final PatientDAOImpl instance = new PatientDAOImpl();

    public static PatientDAOImpl getInstance() {
        return instance;
    }


    @Override
    public List<Patient> getBySurname(String surname) throws DAOException{
        PatientRequestOperator requestOp = PatientRequestOperatorImpl.getInstance();
        return  requestOp.findByParameters(GET_BY_SURNAME_REQUEST, surname);
    }


    @Override
    public List<Patient> getAll() throws DAOException {
        PatientRequestOperator requestOp = PatientRequestOperatorImpl.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST);
    }

    @Override
    public Patient getEntityById(int id) throws DAOException {
        PatientRequestOperator requestOp = PatientRequestOperatorImpl.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST,id).get(0);
    }

    @Override
    public boolean update(Patient entity) throws DAOException{
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID,id);
    }


    @Override
    public int create(Patient entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_PATIENT_REQUEST,entity.getRoomID(), entity.getSurname(),
                entity.getName(), entity.getSex(), entity.getBirthDate());
    }

    @Override
    public boolean createWithHistory(Patient entity) throws DAOException {
        PatientRequestOperator requestOp = PatientRequestOperatorImpl.getInstance();
        return requestOp.createWithHistory(entity);
    }

    @Override
    public boolean discharge(DiseaseHistory history) throws DAOException {
        PatientRequestOperator requestOp = PatientRequestOperatorImpl.getInstance();
        return requestOp.discharge(history);
    }


}
