package by.training.epam.entity;

import java.util.Objects;

public class Room {

    private int id;
    private int number;
    private int departmentId;
    private String department;
    private int leadingDrId;
    private String leadingDr;
    private int bedsAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getLeadingDrId() {
        return leadingDrId;
    }

    public void setLeadingDrId(int leadingDrId) {
        this.leadingDrId = leadingDrId;
    }

    public String getLeadingDr() {
        return leadingDr;
    }

    public void setLeadingDr(String leadingDr) {
        this.leadingDr = leadingDr;
    }

    public int getBedsAmount() {
        return bedsAmount;
    }

    public void setBedsAmount(int bedsAmount) {
        this.bedsAmount = bedsAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && number == room.number && departmentId == room.departmentId && leadingDrId == room.leadingDrId && bedsAmount == room.bedsAmount && Objects.equals(department, room.department) && Objects.equals(leadingDr, room.leadingDr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, departmentId, department, leadingDrId, leadingDr, bedsAmount);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", departmentId=" + departmentId +
                ", department='" + department + '\'' +
                ", leadingDrId=" + leadingDrId +
                ", leadingDr='" + leadingDr + '\'' +
                ", bedsAmount=" + bedsAmount +
                '}';
    }
}
