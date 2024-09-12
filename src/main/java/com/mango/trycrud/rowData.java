package com.mango.trycrud;

import java.sql.Date;

public class rowData {
    String name;
    int id;
    int itemsBought;
    int phoneNumber;
    Date joineddate;

    public rowData(String name, int id, int itemsBought, int phoneNumber, Date joineddate){
        this.name = name;
        this.id = id;
        this.itemsBought = itemsBought;
        this.phoneNumber = phoneNumber;
        this.joineddate = joineddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(int itemsBought) {
        this.itemsBought = itemsBought;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getJoineddate() {
        return joineddate;
    }

    public void setJoineddate(Date joineddate) {
        this.joineddate = joineddate;
    }
}
