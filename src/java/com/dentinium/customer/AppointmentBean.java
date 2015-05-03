package com.dentinium.customer;

import com.dentinium.auth.Util;
import com.dentinium.company.Company;
import com.dentinium.doctor.Doctor;
import com.dentinium.reservation.Reservation;
import com.dentinium.reservation.ReservationDataController;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
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
@ApplicationScoped
@ManagedBean(name = "AppointmentBean")

public class AppointmentBean {

    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private Map<String, Map<Date, String>> data2 = new HashMap<String, Map<Date, String>>();
    private String company;
    private String doctor;
    private String treatment;
    private Date reservationDate;
    private Map<String, String> companies;
    private Map<String, String> doctors;
    private Map<String, String> treatments;
     private Map<Date, String> reservationDates;
     private int reservationID;

    private ReservationDataController resDataController = new ReservationDataController(Util.getToken(), Util.getUserName(), Util.getName(), Util.getUserId());

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    @PostConstruct
    public void init() {
        companies = new HashMap<String, String>();
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
        List<Company> cList = resDataController.getCompanyList();

        for (Company comp : cList) {
            System.out.println(comp.companyName);
            companies.put(comp.companyName, comp.companyName);
        }

    }

    public void onCompanyChange() {
        if (company != null && !company.equals("")) {
            List<Doctor> dList = resDataController.getDoctorListByCompany(company);
            Map<String, String> map = new HashMap<String, String>();

            for (Doctor doc : dList) {
                System.out.println(doc.doctorName);
                map.put(doc.doctorName, doc.doctorName);
            }

            data.put(company, map);

            doctors = data.get(company);
        } else {
            doctors = new HashMap<String, String>();
        }

    }

    public void onDoctorChange() {

            if (doctor != null && !doctor.equals("")) {
            List<Reservation> rList = resDataController.getPossibleReservationsByDoctor(doctor);
            System.out.println("SIZE OF RES DATE LIST : " + rList.size());
            Map<Date,String> map = new HashMap<Date, String>();

            for (Reservation res : rList) {
                System.out.println(res.date);
                map.put(res.date,Integer.toString(res.reservationID));
            }

            data2.put(doctor, map);

            reservationDates = data2.get(doctor);
        } else {
            reservationDates = new HashMap<Date, String>();
        }
    }

    public void makeAppointment() {

       System.out.println("Reservation ID :"+reservationID);

       resDataController.updateReservation(reservationID,treatment);  // make an appointment
       
       //redirect
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("customerpanel.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AppointmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }


}
