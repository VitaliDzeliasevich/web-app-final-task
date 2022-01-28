package by.training.epam.dao.impl;

import by.training.epam.dao.interfaces.ConsultingDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.UniversalRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.ConsultingRequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.UniversalRequestOpImpl;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.entity.Consultation;

import java.util.List;

public class ConsultingDAOImpl implements ConsultingDAO {


    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(SQLTableTitle.CONSULTING_TABLE);
    private static final String GET_BY_PATIENT_ID_REQUEST =
            "SELECT * FROM %s WHERE %s = ?".formatted(SQLTableTitle.CONSULTING_TABLE, SQLColumnLabel.PATIENT_ID);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(SQLTableTitle.CONSULTING_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s) VALUES (?, ?)".formatted(SQLTableTitle.CONSULTING_TABLE, SQLColumnLabel.PATIENT_ID,
                    SQLColumnLabel.CONSULTING_TYPE_ID);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(SQLTableTitle.CONSULTING_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?, %s = ?  WHERE id = ?".formatted(SQLTableTitle.CONSULTING_TABLE,
            SQLColumnLabel.CONSULTING_DESCRIPTION, SQLColumnLabel.CONSULTING_DATE);

    private ConsultingDAOImpl() {}

    private static final ConsultingDAOImpl instance = new ConsultingDAOImpl();

    public static ConsultingDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<Consultation> getAll() throws DAOException {
        RequestOperator<Consultation> requestOp = ConsultingRequestOperator.getInstance();
        return requestOp.findAll(GET_ALL_REQUEST);
    }

    @Override
    public Consultation getEntityById(int id) throws DAOException {
        RequestOperator<Consultation> requestOp = ConsultingRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST, id).get(0);
    }

    @Override
    public boolean update(Consultation entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.update(UPDATE_REQUEST, entity.getDescription(), entity.getDate());
    }

    @Override
    public boolean delete(int id) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.delete(DELETE_BY_ID, id);
    }

    @Override
    public int create(Consultation entity) throws DAOException {
        UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();
        return universalRequestOp.create(CREATE_REQUEST, entity.getPatientId(),
                entity.getConsultationTypeId());
    }
}
