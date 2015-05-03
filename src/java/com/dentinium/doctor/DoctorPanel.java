/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.doctor;

import com.dentinium.auth.Util;
import com.dentinium.reservation.Reservation;
import com.dentinium.reservation.ReservationDataController;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ahmetcem
 */
@ApplicationScoped
@ManagedBean(name = "DoctorPanel")

public class DoctorPanel {
 
    private String name = Util.getName();
    private String username = Util.getUserName();
    private String token = Util.getToken();
    private boolean doctor;
    private String email;
    private String companyname = Util.getCompanyName();
    private String doctorID = "asdasd";
    private Date reservationDate = new Date();
    private String room;
    private DoctorDataController dataController = new DoctorDataController(token,username);
    private ReservationDataController resDataController = new ReservationDataController(token,username,name,doctorID);
    private BasicDBObject doctorObject =dataController.getDoctorObject();
    
    private List<Reservation> reservationList = resDataController.getReservationsForDoctor();
    
     public String getRoom() {
        return room;
    }
     public void setRoom(String room)
     {
         this.room = room;
     }

       public String getName() {
        return Util.getName();
    }
       
    public void setName(String name) {
        this.name = name;
    }

    
     
    public String getUsername() {
        return Util.getUserName();
    }

    public void setUsername(String uname) {
        this.username = uname;
    }

    public void setEmail(String mail) {
        email = mail;
    }
    public void setReservationDate(Date date)
    {
        reservationDate = date;
    }
    public Date getReservationDate()
    {
        return reservationDate;
    }
    
     public void setReservationList(List<Reservation> list)
    {
        reservationList = list;
    }
    public List<Reservation> getReservationList()
    {
        return resDataController.getReservationsForDoctor();
    }

    public String getEmail() {
        return Util.getEmail();
    }
    public void setCompany(String company) {
        companyname = company;
    }

    public String getCompany() {
        return Util.getCompanyName();
    }
    
    
     public void setDoctor(boolean doc) {
        doctor = doc;
    }

    public boolean getDoctor() {
        return Util.isDoctor();
    }
    
    public String createReservationDate()
    {
      // dataController.createReservationDate(reservationDate,room);
        System.out.println("Room Number: " + room);
        resDataController.createReservation(reservationDate, room);
        
        return "customerpanel";
    }
    
    public String deleteReservationDate(int reservationID)
    {
        resDataController.deleteReservationForDoctor(reservationID);
        return null;
    }

    
}
