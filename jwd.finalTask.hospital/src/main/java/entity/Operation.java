package entity;

import java.io.Serializable;
import java.util.Objects;

public class Operation implements Serializable {

    private int id;
    private int patientId;
    private int operationTypeId;
    private String plannedDate;
    private int surgeonId;
    private String executionDate;
    private String description;

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

    public int getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(int operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public String getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(String plannedDate) {
        this.plannedDate = plannedDate;
    }

    public int getSurgeonId() {
        return surgeonId;
    }

    public void setSurgeonId(int surgeonId) {
        this.surgeonId = surgeonId;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return id == operation.id && patientId == operation.patientId && operationTypeId == operation.operationTypeId && surgeonId == operation.surgeonId && Objects.equals(plannedDate, operation.plannedDate) && Objects.equals(executionDate, operation.executionDate) && Objects.equals(description, operation.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, operationTypeId, plannedDate, surgeonId, executionDate, description);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", operationTypeId=" + operationTypeId +
                ", plannedDate='" + plannedDate + '\'' +
                ", surgeonId=" + surgeonId +
                ", executionDate='" + executionDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
