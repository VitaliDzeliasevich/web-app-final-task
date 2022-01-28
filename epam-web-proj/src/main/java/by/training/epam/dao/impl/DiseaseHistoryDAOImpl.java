package by.training.epam.dao.impl;

import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.interfaces.DiseaseHistoryDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.DiseaseHistoryRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.entity.DiseaseHistory;


import java.util.List;

public class DiseaseHistoryDAOImpl implements DiseaseHistoryDAO {


    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(SQLTableTitle.DISEASE_HISTORY_TABLE);
    private static final String GET_BY_ID_REQUEST = "SELECT * FROM %s WHERE id = ?".formatted(
            SQLTableTitle.DISEASE_HISTORY_TABLE);
    private static final String CREATE_REQUEST = "INSERT INTO %s (%s,%s,%s) VALUES (?, ?, ?)"
            .formatted(SQLTableTitle.DISEASE_HISTORY_TABLE, SQLColumnLabel.DISEASE_HISTORY_PATIENT_ID,
                    SQLColumnLabel.DISEASE_HISTORY_ADMISSION_DATE, SQLColumnLabel.DISEASE_HISTORY_ADMISSION_DIAGNOSIS);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(SQLTableTitle.DISEASE_HISTORY_TABLE);

    private DiseaseHistoryDAOImpl() {}

    private static final DiseaseHistoryDAOImpl instance = new DiseaseHistoryDAOImpl();

    public static DiseaseHistoryDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<DiseaseHistory> getAll() throws DAOException {
        RequestOperator<DiseaseHistory> requestOp = DiseaseHistoryRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST);
    }

    @Override
    public DiseaseHistory getEntityById(int id) throws DAOException {
        RequestOperator<DiseaseHistory> requestOp = DiseaseHistoryRequestOperator.getInstance();
        return  requestOp.findByParameters(GET_BY_ID_REQUEST, id).get(0);
    }

    @Override
    public boolean update(DiseaseHistory entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID, id);
    }

    @Override
    public int create(DiseaseHistory entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST,
                entity.getPatientID(), entity.getAdmissionDate(),
                entity.getAdmissionDiagnosis());
    }
}
