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
    private int departmentsId;
    private String phone;

    public User() {}

    public User(String login, String password, String name, String surname, int roleId, String phone) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
        this.phone = phone;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId && departmentsId == user.departmentsId && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, login, password, name, surname, departmentsId, phone);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", departmentsId=" + departmentsId +
                ", phone='" + phone + '\'' +
                '}';
    }
}
