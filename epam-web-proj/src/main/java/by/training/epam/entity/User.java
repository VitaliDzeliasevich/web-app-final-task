package by.training.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private int id;
    private int roleId;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String role;
    private int departmentsId;
    private String phone;
    private int isBlocked;

    public User() {}

    public User(String login, String password, String name, String surname, int roleId, String phone, int departmentsId) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
        this.phone = phone;
        this.departmentsId = departmentsId;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDepartmentsId(int departmentsId) {
        this.departmentsId = departmentsId;
    }

    public int getId() {
        return id;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getDepartmentsId() {
        return departmentsId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIsBlocked() { return isBlocked; }

    public void setBlocked(int blocked) { this.isBlocked = blocked;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId && departmentsId == user.departmentsId && isBlocked == user.isBlocked && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(role, user.role) && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, login, password, name, surname, role, departmentsId, phone, isBlocked);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", departmentsId=" + departmentsId +
                ", phone='" + phone + '\'' +
                '}';
    }

}
