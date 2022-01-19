package by.training.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class Consultation implements Serializable {

    private int id;
    private int patientId;
    private int consultantDrId;
    private String description;
    private String date;
    private int consultationTypeId;

    public Consultation() {}

    public Consultation(int patientId, int consultationTypeId) {
        this.patientId = patientId;
        this.consultationTypeId = consultationTypeId;
    }

    public int getConsultationTypeId() {
        return consultationTypeId;
    }

    public void setConsultationTypeId(int consultationTypeId) {
        this.consultationTypeId = consultationTypeId;
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

    public int getConsultantDrId() {
        return consultantDrId;
    }

    public void setConsultantDrId(int consultantDrId) {
        this.consultantDrId = consultantDrId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultation that = (Consultation) o;
        return id == that.id && patientId == that.patientId && consultantDrId == that.consultantDrId && Objects.equals(description, that.description) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, consultantDrId, description, date);
    }

    @Override
    public String toString() {
        return "Consulting{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", consultantDrId=" + consultantDrId +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
