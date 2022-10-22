package com.example.mid2application;

public class AddressModel {
    private int id;
    private String name,phone,present_address,permanent_address,job;

    public AddressModel(int id, String name, String phone,String job, String present_address, String permanent_address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.present_address = present_address;
        this.permanent_address = permanent_address;
        this.job=job;
    }
    public AddressModel(){

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPresent_address() {
        return present_address;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

}
