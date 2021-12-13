package by.training.epam.dao;

import by.training.epam.entity.DrVisit;

import java.util.List;

public interface DrVisitDAO extends AbstractDAO<DrVisit> {
    List<DrVisit> getByHistoryId(int id);
}
