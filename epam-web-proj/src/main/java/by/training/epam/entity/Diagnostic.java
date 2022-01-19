package by.training.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class Diagnostic implements Serializable {

    private int id;
    private int patientId;
    private int diagnosticTypeId;
    private int diagnosticDrId;
    private String appointmentDate;
    private String executionDate;
    private String result;

    public Diagnostic() {}

    public Diagnostic(int patientId, int diagnosticTypeId, String appointmentDate) {
        this.patientId = patientId;
        this.diagnosticTypeId = diagnosticTypeId;
        this.appointmentDate = appointmentDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDiagnosticTypeId(int diagnosticTypeId) {
        this.diagnosticTypeId = diagnosticTypeId;
    }

    public void setDiagnosticDrId(int diagnosticDrId) {
        this.diagnosticDrId = diagnosticDrId;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDiagnosticTypeId() {
        return diagnosticTypeId;
    }

    public int getDiagnosticDrId() {
        return diagnosticDrId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public String getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnostic that = (Diagnostic) o;
        return id == that.id && patientId == that.patientId && diagnosticTypeId == that.diagnosticTypeId && diagnosticDrId == that.diagnosticDrId && Objects.equals(appointmentDate, that.appointmentDate) && Objects.equals(executionDate, that.executionDate) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, diagnosticTypeId, diagnosticDrId, appointmentDate, executionDate, result);
    }

    @Override
    public String toString() {
        return "Diagnostic{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", diagnosticTypeId=" + diagnosticTypeId +
                ", diagnosticDrId=" + diagnosticDrId +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", executionDate='" + executionDate + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
