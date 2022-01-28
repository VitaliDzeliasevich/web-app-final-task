package by.training.epam.dao.impl;

import by.training.epam.dao.AbstractDAO;
import by.training.epam.dao.connectionpool.ConnectionPool;
import by.training.epam.dao.exeption.DAOException;
import by.training.epam.dao.impl.requestoperator.RequestOperator;
import by.training.epam.dao.impl.requestoperator.impl.DepartmentRequestOperator;
import by.training.epam.dao.impl.tableinfo.SQLColumnLabel;
import by.training.epam.dao.impl.tableinfo.SQLTableTitle;
import by.training.epam.entity.Department;

import java.util.List;

public class DepartmentDAOImpl implements AbstractDAO<Department> {

    private static final String GET_ALL_REQUEST = "SELECT * FROM %s".formatted(SQLTableTitle.DEPARTMENT_TABLE);
    private static final String GET_DEPARTMENT_TYPE = "SELECT type FROM %s WHERE id = ?".formatted(SQLTableTitle.DEPARTMENT_TYPE_TABLE);
    private static final String GET_BY_ID_REQUEST =
            "SELECT * FROM %s WHERE id = ?".formatted(SQLTableTitle.DEPARTMENT_TABLE);
    private static final String CREATE_REQUEST =
            "INSERT INTO %s (%s,%s,%s,%s) VALUES (?, ?, ?, ?)".formatted(SQLTableTitle.DEPARTMENT_TABLE,
                    SQLColumnLabel.DEPARTMENT_PHONE, SQLColumnLabel.DEPARTMENT_NUMBER,
                    SQLColumnLabel.DEPARTMENT_HOSPITAL_ID, SQLColumnLabel.DEPARTMENT_TYPE_ID);
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?".formatted(SQLTableTitle.DEPARTMENT_TABLE);
    private static final String UPDATE_REQUEST = "UPDATE %s SET %s = ?  WHERE id = ?".formatted
            (SQLTableTitle.DEPARTMENT_TABLE,
                    SQLColumnLabel.DEPARTMENT_PHONE);

//    private final static UniversalRequestOperator universalRequestOp = UniversalRequestOpImpl.getInstance();

    private DepartmentDAOImpl() {}

    private static final DepartmentDAOImpl instance = new DepartmentDAOImpl();

    public static DepartmentDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<Department> getAll() throws DAOException {
        return null;
    }

    @Override
    public Department getEntityById(int id) throws DAOException {
        RequestOperator<Department> requestOp = DepartmentRequestOperator.getInstance();
        return requestOp.findByParameters(GET_BY_ID_REQUEST,id).get(0);
    }

    @Override
    public boolean update(Department entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public int create(Department entity) throws DAOException {
        return 0;
    }
}
