package com.revature.dto;

import java.io.InputStream;
import java.util.Objects;

public class AddReimbursement {

    public AddReimbursement(){

    }

    private int amount;
    private String description;
    private InputStream image;
    private int typeId;

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddReimbursement that = (AddReimbursement) o;
        return amount == that.amount && typeId == that.typeId && Objects.equals(description, that.description) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, image, typeId);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public AddReimbursement(int amount, String description, InputStream image, int typeId) {
        this.amount = amount;
        this.description = description;
        this.image = image;
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "AddReimbursement{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", typeId=" + typeId +
                '}';
    }
}
