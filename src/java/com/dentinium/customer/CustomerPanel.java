/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.customer;

import com.dentinium.auth.Util;
import com.dentinium.hibernate.Reservations;
import com.dentinium.reservation.ReservationDataController;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ahmetcem
 */

@ViewScoped
@ManagedBean(name = "CustomerPanel")
public class CustomerPanel {

    private String name;
    private List<Reservations> reservationsList;
    ReservationDataController resdatacon = new ReservationDataController();

    /**
     * @return the name
     */
    public String getName() {
        return Util.getName();
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
        return resdatacon.getBookedReservationsForUser(Util.getUserId());
    }

    /**
     * @param reservationsList the reservationsList to set
     */
    public void setReservationsList(List<Reservations> reservationsList) {
        this.reservationsList = reservationsList;
    }
    
    public void deleteReservation(int reservationid)
    {
        resdatacon.deleteReservationForUser(reservationid);
    }
   
}
