package com.revature.model;

import java.util.Objects;

public class Reimbursement {
    private int id;
    private String status;
    private String type;
    private int amount;
    private String emailId;
    private String description;

    private User employee;
    private User manager;

    public Reimbursement(int id, String status, String type, int amount, String emailId, String description, User employee, User manager) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.amount = amount;
        this.emailId = emailId;
        this.description = description;
        this.employee = employee;
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", emailId='" + emailId + '\'' +
                ", description='" + description + '\'' +
                ", employee=" + employee +
                ", manager=" + manager +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && amount == that.amount && Objects.equals(status, that.status) && Objects.equals(type, that.type) && Objects.equals(emailId, that.emailId) && Objects.equals(description, that.description) && Objects.equals(employee, that.employee) && Objects.equals(manager, that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, type, amount, emailId, description, employee, manager);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
