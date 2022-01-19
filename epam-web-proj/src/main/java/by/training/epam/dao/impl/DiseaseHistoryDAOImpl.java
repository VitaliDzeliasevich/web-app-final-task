package by.training.epam.dao.impl;

import by.training.epam.dao.impl.tableinfo.ColumnLabel;
import by.training.epam.dao.interfaces.DiseaseHistoryDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.DiseaseHistoryRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.TableTitle;
import by.training.epam.entity.DiseaseHistory;


import java.sql.SQLException;
import java.util.List;

public class DiseaseHistoryDAOImpl implements DiseaseHistoryDAO {

    private  final ConnectionPool connectionPool;
    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(TableTitle.DISEASE_HISTORY_TABLE);
    private static final String GET_BY_ID_REQUEST = "SELECT * FROM %s WHERE id = ?".formatted(TableTitle.DISEASE_HISTORY_TABLE);
    private static final String CREATE_REQUEST = "INSERT INTO %s (%s,%s,%s) VALUES (?, ?, ?)"
            .formatted(TableTitle.DISEASE_HISTORY_TABLE, ColumnLabel.DISEASE_HISTORY_PATIENT_ID,
                    ColumnLabel.DISEASE_HISTORY_ADMISSION_DATE, ColumnLabel.DISEASE_HISTORY_ADMISSION_DIAGNOSIS);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(TableTitle.DISEASE_HISTORY_TABLE);

    public DiseaseHistoryDAOImpl(ConnectionPool connectionPool) throws DAOException {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<DiseaseHistory> getAll() throws DAOException {
        RequestOperator<DiseaseHistory> requestOp = DiseaseHistoryRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST,connectionPool);
    }

    @Override
    public DiseaseHistory getEntityById(int id) throws DAOException {
        RequestOperator<DiseaseHistory> requestOp = DiseaseHistoryRequestOperator.getInstance();
        return  requestOp.findByParameters(GET_BY_ID_REQUEST, connectionPool, id).get(0);
    }

    @Override
    public boolean update(DiseaseHistory entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID, connectionPool, id);
    }

    @Override
    public int create(DiseaseHistory entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST,connectionPool,
                entity.getPatientID(), entity.getAdmissionDate(),
                entity.getAdmissionDiagnosis());
    }
}
