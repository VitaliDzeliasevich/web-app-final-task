package by.training.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class Patient implements Serializable {

    private int id;
    private int roomID;
    private int roomNumber;
    private String department;
    private String surname;
    private String name;
    private String sex;
    private String birthDate;
    private String address;
    private String admissionDate;
    private String admissionDiagnosis;

    public Patient() {}

    public Patient(String name, String surname, String birthDate, String sex, String department, int roomID,
                   String admissionDate, String admissionDiagnosis) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.department = department;
        this.roomID = roomID;
        this.admissionDate = admissionDate;
        this.admissionDiagnosis = admissionDiagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getAdmissionDiagnosis() {
        return admissionDiagnosis;
    }

    public void setAdmissionDiagnosis(String admissionDiagnosis) {
        this.admissionDiagnosis = admissionDiagnosis;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public int getId() {
        return id;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && roomID == patient.roomID && roomNumber == patient.roomNumber && Objects.equals(department, patient.department) && Objects.equals(surname, patient.surname) && Objects.equals(name, patient.name) && Objects.equals(sex, patient.sex) && Objects.equals(birthDate, patient.birthDate) && Objects.equals(address, patient.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomID, roomNumber, department, surname, name, sex, birthDate, address);
    }

    @Override
    public String toString() {
        return "Patient{" +
                " roomNumber=" + roomNumber +
                ", department='" + department + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

