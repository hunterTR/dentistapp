/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.doctor;

import com.dentinium.auth.Util;
import com.dentinium.hibernate.Reservations;
import com.dentinium.hibernate.Rooms;
import com.dentinium.reservation.ReservationDataController;
import com.dentinium.room.RoomDataController;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ahmetcem
 */
@ViewScoped
@ManagedBean(name = "DoctorPanel")

public class DoctorPanel {
    
    private String name;
    
    private Date reservationDate;
    private String roomname;
    
    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private Map<String, String> rooms;
    ReservationDataController resdatacon = new ReservationDataController();
    RoomDataController roomdatacon = new RoomDataController();
    private List<Reservations> reservationsList;
    private List<Rooms> roomList;
    
    
    public DoctorPanel() {
        rooms = new HashMap<String, String>();
        getRoomsHashmap();
    }

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
        return resdatacon.getReservationsForDoctor(Util.getUserId());
    }

    /**
     * @param reservationsList the reservationsList to set
     */
    public void setReservationsList(List<Reservations> reservationsList) {
        this.reservationsList = reservationsList;
    }

    /**
     * @return the reservationDate
     */
    public Date getReservationDate() {
        return reservationDate;
    }

    /**
     * @param reservationDate the reservationDate to set
     */
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
    
    public void getRoomsHashmap() {
        
        List<Rooms> rList = roomdatacon.getRoomsByDoctorID(Util.getUserId());
        if (rList != null) {
            for (Rooms room : rList) {
                System.out.println(room.getRoomname());
                getRooms().put(room.getRoomname(), room.getRoomname());
                
            }
        }  //  data.put(company, map);
        // doctors = data.get(company);
    }
    
    public void createReservationDate() {
        
        resdatacon.createReservation(reservationDate, roomname);
        
    }
    
    public void deleteReservation(int reservationID) {
        resdatacon.deleteReservationForDoctor(reservationID);
    }

    
    /**
     * @return the roomname
     */
    public String getRoomname() {
        return roomname;
    }

    /**
     * @param roomname the roomname to set
     */
    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    /**
     * @return the data
     */
    public Map<String, Map<String, String>> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    /**
     * @return the rooms
     */
    public Map<String, String> getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(Map<String, String> rooms) {
        this.rooms = rooms;
    }

    /**
     * @return the roomList
     */
    public List<Rooms> getRoomList() {
        return roomdatacon.getRoomsByDoctorID(Util.getUserId());
    }

    /**
     * @param roomList the roomList to set
     */
    public void setRoomList(List<Rooms> roomList) {
        this.roomList = roomList;
    }
    
    public void deleteRoom(int roomid) {
        
    }
    
    public void addRoom()
    {
        roomdatacon.createRoom(roomname);
    }
    
}
