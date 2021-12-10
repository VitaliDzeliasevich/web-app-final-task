package dao;

import entity.Diagnostic;

import java.util.List;

public interface DiagnosticDAO extends AbstractDAO<Diagnostic>{
    List<Diagnostic> getByHistoryId(int id);
}
