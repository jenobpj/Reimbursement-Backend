package com.revature.dto;

import java.util.Objects;

public class ResponseReimbursementDTO {
    private  int reimbursementId;
    private String reimbursementAuthor;
    private String description;
    private String emailId;
    private String status;
    private String manager;
    private String type;
    private int amount;

    public ResponseReimbursementDTO(int reimbursementId,String reimbursementAuthor, String description, String emailId, String status, String manager, String type, int amount) {
        this.reimbursementId=reimbursementId;
        this.reimbursementAuthor = reimbursementAuthor;
        this.description = description;
        this.emailId = emailId;
        this.status = status;
        this.manager = manager;
        this.type = type;
        this.amount = amount;
    }

    public  int getReimbursementId(){
        return reimbursementId;
    }
    public  void setReimbursementId(int reimbursementId){
        this.reimbursementId=reimbursementId;
    }

    public String getReimbursementAuthor() {
        return reimbursementAuthor;
    }

    public void setReimbursementAuthor(String reimbursementAuthor) {
        this.reimbursementAuthor = reimbursementAuthor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseReimbursementDTO that = (ResponseReimbursementDTO) o;
        return amount == that.amount && Objects.equals(reimbursementAuthor, that.reimbursementAuthor) && Objects.equals(description, that.description) && Objects.equals(emailId, that.emailId) && Objects.equals(status, that.status) && Objects.equals(manager, that.manager) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursementAuthor, description, emailId, status, manager, type, amount);
    }

    @Override
    public String toString() {
        return "ResponseReimbursementDTO{" +
                "reimbursementAuthor='" + reimbursementAuthor + '\'' +
                ", description='" + description + '\'' +
                ", emailId='" + emailId + '\'' +
                ", status='" + status + '\'' +
                ", manager='" + manager + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
