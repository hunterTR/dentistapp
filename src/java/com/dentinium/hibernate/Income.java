package com.dentinium.hibernate;
// Generated May 9, 2015 1:08:47 AM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Income generated by hbm2java
 */
public class Income implements java.io.Serializable {

    private int incomeid;

    private Users user;
    private Doctors doctor;
    private Company company;
    private String description;
    private Date date;
    private int income;

    public Income() {

    }

    public Income(int incomeid, Doctors doctor, Company company, String description, Date date, int income) {
        this.incomeid = incomeid;
        this.doctor = doctor;
        this.description = description;
        this.company = company;
        this.date = date;
        this.income = income;
    }

    public Income(int incomeid,Users user, Doctors doctor, Company company, String description, Date date, int income) {
        this.incomeid = incomeid;
        this.doctor = doctor;
        this.user = user;
        this.description = description;
        this.company = company;
        this.date = date;
        this.income = income;
    }

    public int getIncomeid() {
        return this.incomeid;
    }

    public void setIncomeid(int incomeid) {
        this.incomeid = incomeid;
    }

    /**
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * @return the doctor
     */
    public Doctors getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctors doctor) {
        this.doctor = doctor;
    }



    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the income
     */
    public int getIncome() {
        return income;
    }

    /**
     * @param income the income to set
     */
    public void setIncome(int income) {
        this.income = income;
    }

}
