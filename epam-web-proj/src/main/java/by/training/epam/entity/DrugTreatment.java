package by.training.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class DrugTreatment implements Serializable {

    private int id;
    private int patientId;
    private int drId;
    private int drugId;
    private String drSurname;
    private String drugName;
    private String drugGroup;
    private int wayOfUsingId;
    private String wayOfUsing;
    private String startDate;
    private String endDate;
    private String dose;
    private byte isInterrupted;
    private boolean isTerminated;

    public DrugTreatment() {}

    public DrugTreatment(int patientId, int drugId, int drId, String startDate, String endDate, String dose,
                         int wayOfUsingId) {
        this.patientId = patientId;
        this.drugId = drugId;
        this.drId = drId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dose = dose;
        this.wayOfUsingId = wayOfUsingId;
    }

    public DrugTreatment(int id, String endDate) {
        this.id = id;
        this.endDate = endDate;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getPatientId() { return patientId; }

    public void setPatientId(int patientId) { this.patientId = patientId; }

    public int getDrId() { return drId; }

    public void setDrId(int drId) { this.drId = drId; }

    public int getDrugId() { return drugId; }

    public void setDrugId(int drugId) { this.drugId = drugId; }

    public String getDrugName() { return drugName; }

    public void setDrugName(String drugName) { this.drugName = drugName; }

    public String getDrugGroup() { return drugGroup; }

    public void setDrugGroup(String drugGroup) { this.drugGroup = drugGroup; }

    public int getWayOfUsingId() { return wayOfUsingId; }

    public void setWayOfUsingId(int wayOfUsingId) { this.wayOfUsingId = wayOfUsingId; }

    public String getWayOfUsing() { return wayOfUsing; }

    public void setWayOfUsing(String wayOfUsing) { this.wayOfUsing = wayOfUsing; }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getDose() { return dose; }

    public void setDose(String dose) { this.dose = dose; }

    public String getDrSurname() { return drSurname; }

    public void setDrSurname(String drSurname) { this.drSurname = drSurname; }

    public byte getIsInterrupted() { return isInterrupted; }

    public void setIsInterrupted(byte isInterrupted) { this.isInterrupted = isInterrupted; }

    public boolean getIsTerminated() { return isTerminated; }

    public void setTerminated(boolean terminated) { isTerminated = terminated; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrugTreatment treatment = (DrugTreatment) o;
        return id == treatment.id && patientId == treatment.patientId && drId == treatment.drId && drugId == treatment.drugId && wayOfUsingId == treatment.wayOfUsingId && Objects.equals(drSurname, treatment.drSurname) && Objects.equals(drugName, treatment.drugName) && Objects.equals(drugGroup, treatment.drugGroup) && Objects.equals(wayOfUsing, treatment.wayOfUsing) && Objects.equals(startDate, treatment.startDate) && Objects.equals(endDate, treatment.endDate) && Objects.equals(dose, treatment.dose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, drId, drugId, drSurname, drugName, drugGroup, wayOfUsingId, wayOfUsing, startDate, endDate, dose);
    }

    @Override
    public String toString() {
        return "DrugTreatment{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", drId=" + drId +
                ", drugId=" + drugId +
                ", drSurname='" + drSurname + '\'' +
                ", drugName='" + drugName + '\'' +
                ", drugGroup='" + drugGroup + '\'' +
                ", wayOfUsingId=" + wayOfUsingId +
                ", wayOfUsing='" + wayOfUsing + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", dose='" + dose + '\'' +
                '}';
    }
}
