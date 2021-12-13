package by.training.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class DrVisit implements Serializable {

    private int id;
    private int patientId;
    private int patientConditionId;
    private String date;
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

    public int getPatientConditionId() {
        return patientConditionId;
    }

    public void setPatientConditionId(int patientConditionId) {
        this.patientConditionId = patientConditionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        DrVisit drVisit = (DrVisit) o;
        return id == drVisit.id && patientId == drVisit.patientId && patientConditionId == drVisit.patientConditionId && Objects.equals(date, drVisit.date) && Objects.equals(description, drVisit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, patientConditionId, date, description);
    }

    @Override
    public String toString() {
        return "DrVisit{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", patientConditionId=" + patientConditionId +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
