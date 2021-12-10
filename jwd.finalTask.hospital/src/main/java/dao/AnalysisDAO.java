package dao;

import entity.Analysis;

import java.util.List;

public interface AnalysisDAO extends AbstractDAO<Analysis>{
    List<Analysis> getByHistoryId(int id);
}
