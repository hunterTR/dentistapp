/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.customer;

import com.dentinium.auth.Util;
import com.dentinium.reservation.Reservation;
import com.dentinium.reservation.ReservationDataController;
import com.dentinium.doctor.DoctorDataController;
import com.mongodb.BasicDBObject;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ahmetcem
 */

@ApplicationScoped
@ManagedBean(name = "CustomerPanel")
public class CustomerPanel {
        private String name = Util.getName();
    private String username = Util.getUserName();
    private String token = Util.getToken();
    private boolean doctor;
    private String email;
    private String companyname = Util.getCompanyName();
    private String doctorID = "asdasd";
    private Date reservationDate = new Date();
    private String room ="A12";
    private List<Reservation> reservationList;

    private DoctorDataController dataController = new DoctorDataController(token,username);
    private ReservationDataController resDataController = new ReservationDataController(token,username,name,doctorID);
    private BasicDBObject doctorObject =dataController.getDoctorObject();
    
  
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

    public String getEmail() {
        return Util.getEmail();
    }
    
      public void setReservationList(List<Reservation> list)
    {
        reservationList = list;
    }
    public List<Reservation> getReservationList()
    {
       return resDataController.getBookedReservationsForCustomer();

    }


    
    public String createReservationDate()
    {
      // dataController.createReservationDate(reservationDate,room);
      //  resDataController.updateReservation(70);
        
        
        return null;
    }
    
    public String deleteReservationDate(int id)
    {
        resDataController.deleteReservationForUser(id);
        return null;
    }

}
