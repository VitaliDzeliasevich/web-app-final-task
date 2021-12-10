package dao;

import entity.Operation;

import java.util.List;

public interface OperationDAO extends AbstractDAO<Operation>{
    List<Operation> getByHistoryId(int id);
}
