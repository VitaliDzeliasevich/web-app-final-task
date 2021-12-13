package by.training.epam.dao;


import by.training.epam.entity.Analysis;

import java.util.List;

public interface AnalysisDAO extends AbstractDAO<Analysis>{
    List<Analysis> getByHistoryId(int id);
}
