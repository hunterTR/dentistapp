/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.reservation;

import com.mongodb.BasicDBObject;
import java.util.Date;

/**
 *
 * @author ahmetcem
 */

public class Reservation {
    public int reservationID;
    public Date date;
    public String customerName;
    public String doctorName;
    public String doctorID;
    public String customerID = null;
    public String room;
    public String type;
    public String companyID;  // eklenecekler.
    public String companyName;
    
    public int cost;
    
    public Reservation(BasicDBObject obj)
    {
        reservationID = obj.getInt("reservationID");
        date = obj.getDate("date");

        if(obj.getString("doctor") != null)
        {
         BasicDBObject tmpObject = (BasicDBObject) obj.get("doctor");
         doctorName = tmpObject.getString("name");
         doctorID = tmpObject.getString("doctorID");
        }
        
        
        if(obj.getString("customer") != null)
        {
         BasicDBObject tmpObject = (BasicDBObject) obj.get("customer");
         customerName = tmpObject.getString("name");
         customerID = tmpObject.getString("customerID");
        }
        
        
        if(obj.getString("companyname") != null)
        {
   
         companyName = obj.getString("companyname");
  
        }
        
        if(obj.getString("room") != null)
        room = obj.getString("room");
        
        if(obj.getString("type") != null)
        type = obj.getString("type");
        else
            type = "Not Set";
     
        if(obj.getString("customerID") != null)
        customerID = obj.getString("customerID");
        
        if(obj.getString("cost") != null)
        cost = obj.getInt("cost");
        else
            cost = 0;
    }
    
    
      public void setReservationID(int id)
    {
        this.reservationID = id;
    }
    
    public int getReservationID()
    {
        return reservationID;
    }
    
    public void setRoom(String room)
    {
        this.room = room;
    }
    
    public String getRoom()
    {
        return room;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    
    public void setdoctorID(String id)
    {
        this.doctorID = id;
    }
    
    public String getdoctorID()
    {
        return doctorID;
    }
    
    
     public void setType(String type)
    {
        this.type = type;
    }
    
    public String getType()
    {
        return type;
    }
    
     public void setCost(int cost)
    {
        this.cost = cost;
    }
    public int getCost()
    {
        return cost;
    }
    
    
    public void setCustomerName(String name)
    {
        this.customerName = name;
    }
    
    public String getCustomerName()
    {
        return customerName;
    }
    
        
    public void setDoctorName(String name)
    {
        this.doctorName = name;
    }
    
    public String getDoctorName()
    {
        return doctorName;
    }
    
     public void setCompanyName(String name)
    {
        this.companyName = name;
    }
    
    public String getCompanyName()
    {
        return companyName;
    }
    
    
    
    public boolean isEmpty()
    {
        if(customerID == null)
            return true;
        else
        return false;
    }
    
}
