package by.training.epam.dao.impl.requestoperator.impl;

import by.training.epam.dao.connectionpool.ConnectionPoolException;
import by.training.epam.dao.connectionpool.ConnectionPoolFactory;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.rowmapper.RowMapper;
import by.training.epam.dao.impl.rowmapper.impl.AnalysisRowMapper;
import by.training.epam.dao.impl.rowmapper.impl.DrugTreatmentRowMapper;
import by.training.epam.entity.Analysis;
import by.training.epam.entity.DrugTreatment;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DrugTreatmentRequestOperator implements RequestOperator<DrugTreatment> {

    private final static Logger log = Logger.getLogger(DrugTreatmentRequestOperator.class);

    private final static DrugTreatmentRequestOperator instance = new DrugTreatmentRequestOperator();

    private DrugTreatmentRequestOperator() {}

    public static DrugTreatmentRequestOperator getInstance() {
        return instance;
    }

    @Override
    public List<DrugTreatment> findAll(String SQLRequest) throws DAOException {
        return null;
    }

    @Override
    public List<DrugTreatment> findByParameters(String SQLRequest, Object... attributes) throws DAOException {
        RowMapper<DrugTreatment> rowMapper = DrugTreatmentRowMapper.getInstance();
        List<DrugTreatment> list;
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolFactory.getInstance().getConnectionPool().takeConnection();
        } catch (ConnectionPoolException e) {
            log.log(Level.ERROR, e);
            throw new DAOException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(SQLRequest);
            for (int i=0; i<attributes.length; i++) {
                preparedStatement.setObject(i+1,attributes[i]);
            }
            resultSet = preparedStatement.executeQuery();
            list  = rowMapper.map(resultSet);
        } catch (SQLException throwables) {
            log.log(Level.ERROR, throwables);
            throw new DAOException(throwables);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException throwables) {
                log.log(Level.ERROR,throwables);
            }
        }
        return list;
    }
}
