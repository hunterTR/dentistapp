/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistrationForm
{
 private Integer userId;
 private String userName;
 private String password;
 private String name;
 private String email;
 private String phone;
 private Date dob;
 private String address;
 private boolean subscribeToNewsLetter;
 
 //setters/getters

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getName() {
        return name;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setName(String firstName) {
        this.name = firstName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the subscribeToNewsLetter
     */
    public boolean isSubscribeToNewsLetter() {
        return subscribeToNewsLetter;
    }

    /**
     * @param subscribeToNewsLetter the subscribeToNewsLetter to set
     */
    public void setSubscribeToNewsLetter(boolean subscribeToNewsLetter) {
        this.subscribeToNewsLetter = subscribeToNewsLetter;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
  
}