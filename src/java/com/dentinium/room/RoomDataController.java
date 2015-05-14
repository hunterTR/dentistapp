/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.room;

import com.dentinium.auth.Util;
import com.dentinium.doctor.DoctorDataController;
import com.dentinium.hibernate.Doctors;
import com.dentinium.hibernate.Rooms;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ahmetcem
 */
public class RoomDataController {
    

    private Random randomGenerator;
    
    public RoomDataController()
    {
        randomGenerator = new Random();
    }
    
    
    public Rooms getRoomByID(int roomid)
    {
        Session session = createSession();
            Query query = session.createQuery("FROM Rooms WHERE roomid = :roomid");
        query.setParameter("roomid", roomid);

        List users = query.list();

        if (users.size() >= 1) {
            Rooms us = (Rooms) users.get(0);
 session.close();
            return us;
        } else {
            System.out.println("Room COULDN'T BE FOUND!");
             session.close();
            return null;
        }

    }
    
    public Rooms getRoomByName(String roomname)
    {
        Session session = createSession();
            Query query = session.createQuery("FROM Rooms WHERE roomname = :roomname");
        query.setParameter("roomname", roomname);

        List users = query.list();

        if (users.size() >= 1) {
            Rooms us = (Rooms) users.get(0);
 session.close();
            return us;
        } else {
            System.out.println("Room COULDN'T BE FOUND!");
             session.close();
            return null;
        }

    }
    
    public List<Rooms> getRoomsByDoctorID(int doctorid)
    {
        Session session = createSession();
            Query query = session.createQuery("FROM Rooms WHERE doctors = :doctorid");
        query.setParameter("doctorid", doctorid);

        List rooms = query.list();

        if (rooms.size() >= 1) {
         session.close();
            return rooms;
        } else {
             session.close();
            System.out.println("Room COULDN'T BE FOUND!");
            return null;
        }
    }
    
    public void createRoom(String roomname)
    { Session session = createSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
          DoctorDataController docdatacon = new DoctorDataController();

           Doctors doc = docdatacon.getDoctorByUserID(Util.getUserId());

          //  System.out.println(resDate);
           // Doctors doctor = new Doctors(user.getUserid(),comp,user);
           
            Rooms room = new Rooms(randomGenerator.nextInt(1000),roomname,doc);
            session.save(room);
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DONE!", ""));
            

        } catch (HibernateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DATABASE ERROR", ""));

            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            session.close();
        }
 
    }
    
    
    
       public Session createSession() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        return factory.openSession();
    }
}
