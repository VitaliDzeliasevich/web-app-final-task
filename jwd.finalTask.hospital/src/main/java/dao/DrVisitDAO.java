package dao;

import entity.DrVisit;

import java.util.List;

public interface DrVisitDAO extends AbstractDAO<DrVisit>{
    List<DrVisit> getByHistoryId(int id);
}
