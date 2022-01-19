package by.training.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class Analysis implements Serializable {

    private int id;
    private int patientId;
    private int analysisTypeId;
    private int labDrId;
    private String appointmentDate;
    private String executionDate;
    private String result;

    public Analysis() {}

    public Analysis(int patientId,int analysisTypeId, String appointmentDate) {
        this.patientId = patientId;
        this.analysisTypeId = analysisTypeId;
        this.appointmentDate = appointmentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAnalysisTypeId() {
        return analysisTypeId;
    }

    public void setAnalysisTypeId(int analysisTypeId) {
        this.analysisTypeId = analysisTypeId;
    }

    public int getLabDrId() {
        return labDrId;
    }

    public void setLabDrId(int labDrId) {
        this.labDrId = labDrId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analysis analysis = (Analysis) o;
        return id == analysis.id && patientId == analysis.patientId && analysisTypeId == analysis.analysisTypeId && labDrId == analysis.labDrId && Objects.equals(appointmentDate, analysis.appointmentDate) && Objects.equals(executionDate, analysis.executionDate) && Objects.equals(result, analysis.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, analysisTypeId, labDrId, appointmentDate, executionDate, result);
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", analysisTypeId=" + analysisTypeId +
                ", labDrId=" + labDrId +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", executionDate='" + executionDate + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
