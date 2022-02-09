package by.training.epam.entity.transfer;

import by.training.epam.entity.Analysis;

import java.io.Serializable;
import java.util.Objects;

public class PatientAnalysis extends Analysis implements Serializable {
    private int patientId;
    private int analysisId;
    private String analysisType;
    private String patientName;
    private String patientSurname;
    private int roomNumber;

    public PatientAnalysis() {}

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(int analysisId) {
        this.analysisId = analysisId;
    }

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientAnalysis that = (PatientAnalysis) o;
        return patientId == that.patientId && analysisId == that.analysisId && roomNumber == that.roomNumber && Objects.equals(analysisType, that.analysisType) && Objects.equals(patientName, that.patientName) && Objects.equals(patientSurname, that.patientSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, analysisId, analysisType, patientName, patientSurname, roomNumber);
    }

    @Override
    public String toString() {
        return "PatientAnalysis{" +
                "patientId=" + patientId +
                ", analysisId=" + analysisId +
                ", analysisType='" + analysisType + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientSurname='" + patientSurname + '\'' +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
