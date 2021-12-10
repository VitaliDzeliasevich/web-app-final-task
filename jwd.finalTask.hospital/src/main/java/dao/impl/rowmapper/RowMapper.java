package dao.impl.rowmapper;

import java.sql.ResultSet;
import java.util.List;

public interface RowMapper<E> {
    List<E> fillFields(ResultSet resultSet);
}
