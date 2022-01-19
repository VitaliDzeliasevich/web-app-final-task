package by.training.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {

    private int id;
    private int departmentTypeId;
    private String departmentType;
    private int number;
    private int hospitalId;
    private String hospitalName;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentTypeId() {
        return departmentTypeId;
    }

    public void setDepartmentTypeId(int departmentTypeId) {
        this.departmentTypeId = departmentTypeId;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && departmentTypeId == that.departmentTypeId && number == that.number && hospitalId == that.hospitalId && Objects.equals(departmentType, that.departmentType) && Objects.equals(hospitalName, that.hospitalName) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentTypeId, departmentType, number, hospitalId, hospitalName, phone);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentTypeId=" + departmentTypeId +
                ", departmentType='" + departmentType + '\'' +
                ", number=" + number +
                ", hospitalId=" + hospitalId +
                ", hospitalName='" + hospitalName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
