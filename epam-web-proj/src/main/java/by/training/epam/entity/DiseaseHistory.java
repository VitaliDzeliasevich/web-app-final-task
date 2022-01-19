package by.training.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class DiseaseHistory implements Serializable {

    private int id;
    private int patientID;
    private String admissionDate;
    private String admissionDiagnosis;
    private String lifeAnamnesis;
    private String diseaseAnamnesis;
    private String dischargingDate;
    private String epicrysis;

    public DiseaseHistory() {}

    public DiseaseHistory(int patientID, String admissionDate, String admissionDiagnosis) {
        this.patientID = patientID;
        this.admissionDate = admissionDate;
        this.admissionDiagnosis = admissionDiagnosis;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setAdmissionDiagnosis(String admissionDiagnosis) {
        this.admissionDiagnosis = admissionDiagnosis;
    }

    public void setLifeAnamnesis(String lifeAnamnesis) {
        this.lifeAnamnesis = lifeAnamnesis;
    }

    public void setDiseaseAnamnesis(String diseaseAnamnesis) {
        this.diseaseAnamnesis = diseaseAnamnesis;
    }

    public void setDischargingDate(String dischargingDate) {
        this.dischargingDate = dischargingDate;
    }

    public void setEpicrysis(String epicrysis) {
        this.epicrysis = epicrysis;
    }

    public int getId() {
        return id;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getAdmissionDiagnosis() {
        return admissionDiagnosis;
    }

    public String getLifeAnamnesis() {
        return lifeAnamnesis;
    }

    public String getDiseaseAnamnesis() {
        return diseaseAnamnesis;
    }

    public String getDischargingDate() {
        return dischargingDate;
    }

    public String getEpicrysis() {
        return epicrysis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiseaseHistory that = (DiseaseHistory) o;
        return id == that.id && patientID == that.patientID && Objects.equals(admissionDate, that.admissionDate) && Objects.equals(admissionDiagnosis, that.admissionDiagnosis) && Objects.equals(lifeAnamnesis, that.lifeAnamnesis) && Objects.equals(diseaseAnamnesis, that.diseaseAnamnesis) && Objects.equals(dischargingDate, that.dischargingDate) && Objects.equals(epicrysis, that.epicrysis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientID, admissionDate, admissionDiagnosis, lifeAnamnesis, diseaseAnamnesis, dischargingDate, epicrysis);
    }

    @Override
    public String toString() {
        return "DiseaseHistory{" +
                "id=" + id +
                ", patientID=" + patientID +
                ", admissionDate='" + admissionDate + '\'' +
                ", admissionDiagnosis='" + admissionDiagnosis + '\'' +
                ", lifeAnamnesis='" + lifeAnamnesis + '\'' +
                ", diseaseAnamnesis='" + diseaseAnamnesis + '\'' +
                ", dischargingDate='" + dischargingDate + '\'' +
                ", epicrysis='" + epicrysis + '\'' +
                '}';
    }
}
