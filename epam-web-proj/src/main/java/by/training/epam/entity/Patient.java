package by.training.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class Patient implements Serializable {

    private int id;
    private int roomID;
    private String surname;
    private String name;
    private String sex;
    private String birthDate;
    private String address;

    public Patient() {}

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
        return id == patient.id && roomID == patient.roomID && sex.equals(patient.sex) && Objects.equals(surname, patient.surname) && Objects.equals(name, patient.name) && Objects.equals(birthDate, patient.birthDate) && Objects.equals(address, patient.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomID, surname, name, sex, birthDate, address);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", roomID=" + roomID +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthDate='" + birthDate + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

