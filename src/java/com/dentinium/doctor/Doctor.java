/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.doctor;

import com.mongodb.BasicDBObject;

/**
 *
 * @author ahmetcem
 */
public class Doctor {

    public String doctorName;
    public String doctorID;
    public String companyName;
    public String email;
    public String phone;
    public int wage;

    public Doctor(BasicDBObject obj) {

        doctorID = obj.getString("userid");
        doctorName = obj.getString("name");
        email = obj.getString("email");

        if (obj.getString("phone") != null) {
            phone = obj.getString("phone");
        } else {
            phone = "not set";
        }

        if (obj.getString("wage") != null) {
            wage = obj.getInt("wage");
        } else {
            wage = 0;
        }
        BasicDBObject tmp = (BasicDBObject) obj.get("works");
        companyName = tmp.getString("companyname");

    }

    public void setDoctorID(String id) {
        this.doctorID = id;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorName(String name) {
        this.doctorName = name;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public String getEmail() {
        return email;
    }

    public void setWage(int name) {
        this.wage = name;
    }

    public int getWage() {
        return wage;
    }

    public void setPhone(String name) {
        this.phone = name;
    }

    public String getPhone() {
        return phone;
    }

}
