package com.revature.model;

import java.util.Objects;

public class User {

    private  int id;
    private String username;
    private String password;
    private String firstName;
    private String userRole;


    public User(){

    }

    public User(int id, String username, String password, String firstName, String userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(userRole, user.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
