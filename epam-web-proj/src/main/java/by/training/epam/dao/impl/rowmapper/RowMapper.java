package by.training.epam.dao.impl.rowmapper;

import by.training.epam.dao.exeption.DAOException;

import java.sql.ResultSet;
import java.util.List;

public interface RowMapper<E> {
    List<E> map(ResultSet resultSet) throws DAOException;

}
