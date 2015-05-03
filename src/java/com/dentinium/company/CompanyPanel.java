/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.company;

import com.dentinium.auth.Util;
import com.dentinium.doctor.Doctor;
import com.dentinium.reservation.Reservation;
import com.dentinium.reservation.ReservationDataController;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ahmetcem
 */
@ApplicationScoped
@ManagedBean(name = "CompanyPanel")
public class CompanyPanel {

    private String companyName = Util.getCompanyName();
    private String companyID;
    private ReservationDataController resDataController = new ReservationDataController(Util.getToken(), Util.getUserName(), Util.getName(), Util.getUserId());

    private List<Reservation> reservationList = resDataController.getReservationsByCompany(companyName);
    List<Doctor> doctorList;

    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String doctor) {
        this.companyName = doctor;
    }

    public List<Reservation> getReservationList()
    {
       return resDataController.getReservationsByCompany(companyName);

    }
    
    public List<Doctor> getDoctorList()
    {
       return resDataController.getDoctorListByCompany(companyName);

    }
    
    
    public void deleteDoctor(int doctorID)
    {
        
    }
    
     
    public void deleteReservation(int doctorID)
    {
        
    }
}
