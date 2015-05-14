package com.dentinium.customer;

import com.dentinium.company.CompanyDataController;
import com.dentinium.doctor.DoctorDataController;
import com.dentinium.hibernate.Company;
import com.dentinium.hibernate.Doctors;
import com.dentinium.hibernate.Reservations;
import com.dentinium.reservation.ReservationDataController;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahmetcem
 */

@ManagedBean(name = "AppointmentBean")
@ViewScoped
public class AppointmentBean {

    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private Map<String, Map<Date, String>> data2 = new HashMap<String, Map<Date, String>>();
    private String company;
    private int companyid;
    private String doctor;
    private String treatment;
    private Date reservationDate;
    private Map<String, String> companies;
    private Map<String, String> doctors;
    private Map<String, String> treatments;
     private Map<Date, String> reservationDates;
     private int reservationID;
     
     private ReservationDataController resdatacon = new ReservationDataController();
     private DoctorDataController docdatacon = new DoctorDataController();
private CompanyDataController compdatacon = new CompanyDataController();
   

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    @PostConstruct
    public void init() {
        
        

        companies = new HashMap<String, String>();
        doctors = new HashMap<String,String>();
        reservationDates = new HashMap<Date,String>();
        setCompanies();

        treatments = new HashMap<String, String>();
        treatments.put("Treatment A", "Treatment A");
        treatments.put("Treatment B", "Treatment B");
        treatments.put("Treatment C", "Treatment C");

    }
    
    

    public String getCompany() {
        return company;
    }

    public void setCompany(String country) {
        this.company = country;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    
    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    
     public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date country) {
        this.reservationDate = country;
    }

     public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int country) {
        this.reservationID = country;
    }
    public Map<String, String> getCompanies() {
        return companies;
    }

    public Map<String, String> getDoctors() {
        return doctors;
    }
    
    public Map<String, String> getTreatments() {
        return treatments;
    }
    
     public Map<Date, String> getReservationDates() {
        return reservationDates;
    }

    public void setCompanies() {

        List<Company> companyList  = compdatacon.getCompanies();
        
         for (Company comp : companyList) {
               // System.out.println(doc.getName());
                companies.put(comp.getCompanyname(), comp.getCompanyname());
            }
        

    }

    public void onCompanyChange() {
        System.out.println("ON COmpany CHANGEE!!" + company);
        if (company != null && !company.equals("")) {
            List<Doctors> dList = docdatacon.getDoctorsByCompanyName(company);
            Map<String, String> map = new HashMap<String, String>();

            for (Doctors doc : dList) {
                System.out.println(doc.getUser().getName());
                map.put(doc.getName(), doc.getName());
            }

            data.put(company, map);

            doctors = data.get(company);
        } else {
            doctors = new HashMap<String, String>();
        }

    }

    public void onDoctorChange() {

        System.out.println("ON DOCTOR CHANGEE!!" + doctor);
          if (doctor != null && !doctor.equals("")) {
            List<Reservations> rList = resdatacon.getPossibleReservationsForUsers(doctor);
            System.out.println("SIZE OF RES DATE LIST : " + rList.size());
            Map<Date,String> map = new HashMap<Date, String>();

                 System.out.println("ON DOCTOR CHANGEE!!");
            for (Reservations res : rList) {
                
                System.out.println(res.getDate());
                map.put(res.getDate(),Integer.toString(res.getReservationid()));
            }

            data2.put(doctor, map);

            reservationDates = data2.get(doctor);
        } else {
            reservationDates = new HashMap<Date, String>();
        }
    }

    public void makeAppointment() {

       System.out.println("Reservation ID :"+reservationID);

       resdatacon.updateReservation(reservationID);
       
       //redirect
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("customerpanel.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AppointmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * @return the companyid
     */
    public int getCompanyid() {
        return companyid;
    }

    /**
     * @param companyid the companyid to set
     */
    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }


}
