package by.training.epam.dao.impl;

import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.DrugTreatmentRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.dao.interfaces.DrugTreatmentDAO;
import by.training.epam.entity.DrugTreatment;

import java.util.List;

public class DrugTreatmentDAOImpl implements DrugTreatmentDAO {

    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(SQLTableTitle.DRUGS_TREATMENT_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            ("SELECT %s.%s, %s.%s, %s.%s, %s, %s, %s, %s, %s FROM %s INNER JOIN %s ON %s.%s = %s.%s INNER JOIN %s ON " +
                    "%s=%s.%s INNER JOIN %s ON %s = %s.%s WHERE %s = ? " +
                    "ORDER BY %s DESC").formatted(SQLTableTitle.DRUGS_TREATMENT_TABLE, SQLColumnLabel.ID,
                    SQLTableTitle.DRUGS_TABLE, SQLColumnLabel.DRUG_NAME, SQLTableTitle.USER_TABLE,
                    SQLColumnLabel.USER_SURNAME, SQLColumnLabel.WAY_OF_USING,SQLColumnLabel.DRUG_TREATMENT_IS_INTERRUPTED,
                    SQLColumnLabel.DRUG_TREATMENT_START_DATE, SQLColumnLabel.DRUG_TREATMENT_END_DATE,
                    SQLColumnLabel.DRUG_TREATMENT_DOSE,SQLTableTitle.DRUGS_TREATMENT_TABLE, SQLTableTitle.DRUGS_TABLE,
                    SQLTableTitle.DRUGS_TREATMENT_TABLE, SQLColumnLabel.DRUG_TREATMENT_DRUGS_ID, SQLTableTitle.DRUGS_TABLE,
                    SQLColumnLabel.ID, SQLTableTitle.USER_TABLE, SQLColumnLabel.DRUG_TREATMENT_DR_ID,
                    SQLTableTitle.USER_TABLE, SQLColumnLabel.ID, SQLTableTitle.WAY_OF_USING_TABLE,
                    SQLColumnLabel.DRUG_WAY_OF_USING_ID, SQLTableTitle.WAY_OF_USING_TABLE, SQLColumnLabel.ID,
                    SQLColumnLabel.DRUG_TREATMENT_PATIENT_ID, SQLColumnLabel.DRUG_TREATMENT_END_DATE);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(SQLTableTitle.ANALYZES_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s) VALUES (?, ?, ?, ?, ?, ?, ?)".formatted(SQLTableTitle.DRUGS_TREATMENT_TABLE,
                    SQLColumnLabel.PATIENT_ID, SQLColumnLabel.DRUG_TREATMENT_DRUGS_ID, SQLColumnLabel.DRUG_TREATMENT_DR_ID,
                    SQLColumnLabel.DRUG_TREATMENT_START_DATE, SQLColumnLabel.DRUG_TREATMENT_END_DATE,
                    SQLColumnLabel.DRUG_TREATMENT_DOSE, SQLColumnLabel.DRUG_WAY_OF_USING_ID);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(SQLTableTitle.ANALYZES_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?  WHERE %s = ?".formatted(
            SQLTableTitle.DRUGS_TREATMENT_TABLE, SQLColumnLabel.DRUG_TREATMENT_END_DATE, SQLColumnLabel.ID);
    private static final String INTERRUPT_REQUEST = "UPDATE %s SET %s = 1  WHERE %s = ?".formatted(
            SQLTableTitle.DRUGS_TREATMENT_TABLE, SQLColumnLabel.DRUG_TREATMENT_IS_INTERRUPTED, SQLColumnLabel.ID);


    private DrugTreatmentDAOImpl() {}

    private static final DrugTreatmentDAOImpl instance = new DrugTreatmentDAOImpl();

    public static DrugTreatmentDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<DrugTreatment> getAll() throws DAOException {
        return null;
    }

    @Override
    public List<DrugTreatment> getByHistoryId(int id) throws DAOException {
        RequestOperator<DrugTreatment> requestOp = DrugTreatmentRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_PATIENT_ID_REQUEST,id);
    }

    @Override
    public DrugTreatment getEntityById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean update(DrugTreatment entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.update(UPDATE_REQUEST, entity.getEndDate(), entity.getId());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public int create(DrugTreatment entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST, entity.getPatientId(), entity.getDrugId(), entity.getDrId(),
                entity.getStartDate(), entity.getEndDate(), entity.getDose(), entity.getWayOfUsingId());
    }

    @Override
    public void interrupt(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        universalRequestOp.update(INTERRUPT_REQUEST, id);

    }
}
