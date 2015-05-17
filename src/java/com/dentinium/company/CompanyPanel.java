/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.company;

import com.dentinium.auth.Util;
import com.dentinium.customer.UserDataController;
import com.dentinium.doctor.DoctorDataController;
import com.dentinium.hibernate.Doctors;
import com.dentinium.hibernate.Income;
import com.dentinium.hibernate.Reservations;
import com.dentinium.hibernate.Users;
import com.dentinium.income.IncomeDataController;
import com.dentinium.reservation.ReservationDataController;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ahmetcem
 */

@ViewScoped
@ManagedBean(name = "CompanyPanel")
public class CompanyPanel {

    private String name;
    private List<Reservations> reservationsList;
    private List<Income> incomeList;
    private List<Income> filteredIncomeList;
    private List<Doctors> doctors;
    private Reservations selectedReservation;
  
    private List<Users> filteredUsers;
    private int reservationIncome;
    private String registerDescription;
    
    private String searchName = "";
    

    DoctorDataController docdatacon = new DoctorDataController();
    ReservationDataController resdatacon = new ReservationDataController();
    IncomeDataController incomedatacon = new IncomeDataController();
    UserDataController userdatacon = new UserDataController();
     private List<Users> users = userdatacon.searchUser("");

    /**
     * @return the name
     */
    public String getName() {
        return Util.getCompanyName();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the reservationsList
     */
    public List<Reservations> getReservationsList() {
        return resdatacon.getReservationsForCompany(Util.getCompanyName());
    }

    /**
     * @param reservationsList the reservationsList to set
     */
    public void setReservationsList(List<Reservations> reservationsList) {
        this.reservationsList = reservationsList;
    }

    /**
     * @return the incomeList
     */
    public List<Income> getIncomeList() {
        return incomedatacon.getIncomesByCompany(Util.getCompanyName());
    }

    /**
     * @param incomeList the incomeList to set
     */
    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    /**
     * @return the doctors
     */
    public List<Doctors> getDoctors() {
        return docdatacon.getDoctorsByCompanyName(Util.getCompanyName());
    }

    /**
     * @param doctors the doctors to set
     */
    public void setDoctors(List<Doctors> doctors) {
        this.doctors = doctors;
    }

    public void deleteDoctor(int doctorID) {
       docdatacon.deleteDoctor(doctorID);
    }

    public void deleteReservation(int reservationid) {
        resdatacon.deleteReservationForDoctor(reservationid);
    }

    public void reservationDone(int reservationid) {
       Reservations res = resdatacon.getReservationByID(reservationid);
       incomedatacon.createIncome(res, res.getDate(),"Patient has been treated", reservationIncome);
       resdatacon.deleteReservationForDoctor(reservationid);
    }
    
    public void registerIn()
    {
           incomedatacon.createIncome(new Date(), getRegisterDescription(), reservationIncome);
    }

    /**
     * @return the reservationIncome
     */
    public int getReservationIncome() {
        return reservationIncome;
    }

    /**
     * @param reservationIncome the reservationIncome to set
     */
    public void setReservationIncome(int reservationIncome) {
        this.reservationIncome = reservationIncome;
    }

    /**
     * @return the users
     */
    public List<Users> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public void searchUser()
    {
     this.users = userdatacon.searchUser("");
       
    }
    
    public void assignDoctor(int userid)
    {
        docdatacon.createDoctor(userid);
    }

    /**
     * @return the searchName
     */
    public String getSearchName() {
        return searchName;
    }

    /**
     * @param searchName the searchName to set
     */
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * @return the filteredUsers
     */
    public List<Users> getFilteredUsers() {
        return filteredUsers;
    }

    /**
     * @param filteredUsers the filteredUsers to set
     */
    public void setFilteredUsers(List<Users> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    /**
     * @return the filteredIncomeList
     */
    public List<Income> getFilteredIncomeList() {
        return filteredIncomeList;
    }

    /**
     * @param filteredIncomeList the filteredIncomeList to set
     */
    public void setFilteredIncomeList(List<Income> filteredIncomeList) {
        this.filteredIncomeList = filteredIncomeList;
    }

    /**
     * @return the selectedReservation
     */
    public Reservations getSelectedReservation() {
        return selectedReservation;
    }

    /**
     * @param selectedReservation the selectedReservation to set
     */
    public void setSelectedReservation(Reservations selectedReservation) {
        this.selectedReservation = selectedReservation;
    }

    /**
     * @return the registerDescription
     */
    public String getRegisterDescription() {
        return registerDescription;
    }

    /**
     * @param registerDescription the registerDescription to set
     */
    public void setRegisterDescription(String registerDescription) {
        this.registerDescription = registerDescription;
    }
    
}
