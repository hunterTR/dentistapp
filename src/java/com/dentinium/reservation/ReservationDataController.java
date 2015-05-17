/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.reservation;

import com.dentinium.auth.Util;
import com.dentinium.company.CompanyDataController;

import com.dentinium.customer.UserDataController;
import com.dentinium.doctor.DoctorDataController;
import com.dentinium.hibernate.Company;
import com.dentinium.hibernate.Doctors;
import com.dentinium.hibernate.Reservations;
import com.dentinium.hibernate.Rooms;
import com.dentinium.hibernate.Users;
import com.dentinium.room.RoomDataController;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ReservationDataController {

    public String token;
    public String username;
    public String name;
    public Random randomGenerator;
    public int userID;
    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();

    private Session session;

    public ReservationDataController() {
        // session = createSession();
        randomGenerator = new Random();
    }

    public boolean createReservation(Date resDate, String roomname) // Doctor creates reservation.
    {

        session = createSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            UserDataController userdatacon = new UserDataController();
            DoctorDataController doctordatacon = new DoctorDataController();
            RoomDataController roomdatacon = new RoomDataController();
            CompanyDataController compdatacon = new CompanyDataController();

            // Users rd = userdatacon.getUserByID(Util.getUserId());
            Doctors doc = doctordatacon.getDoctorByUserID(Util.getUserId());
            Rooms room = roomdatacon.getRoomByName(roomname);
            Company comp = compdatacon.getCompanyByName(Util.getCompanyName());

            System.out.println(resDate);
            Reservations res = new Reservations(randomGenerator.nextInt(1000), resDate, doc, room, comp);

            session.save(res);
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DONE!", ""));
            return true;

        } catch (HibernateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DATABASE ERROR", ""));

            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            session.close();
        }
        return false;

    }

    public List<Reservations> getReservationsForDoctor(int doctorid) {
        session = createSession();
        Query query = session.createQuery("FROM Reservations WHERE doctors = :doctorid");
        query.setParameter("doctorid", doctorid);

        List reservations = query.list();
        System.out.println("RESERVATION LIST SIZE : " + reservations.size());
        return reservations;

    }

    public List<Reservations> getReservationsForCompany(String companyname) {
        session = createSession();
        CompanyDataController compdatacon = new CompanyDataController();
        Company comp = compdatacon.getCompanyByName(companyname);
        Query query = session.createQuery("FROM Reservations WHERE company = " + comp.getCompanyid());
        //query.setParameter("companyid", comp.getCompanyid());

        List reservations = query.list();
        System.out.println("RESERVATION LIST SIZE : " + reservations.size());
        return reservations;

    }

    public List<Reservations> getBookedReservationsForUser(int userid) {
        session = createSession();
        Query query = session.createQuery("FROM Reservations WHERE users = :userid");
        query.setParameter("userid", userid);

        List reservations = query.list();
        System.out.println("RESERVATION LIST SIZE : " + reservations.size());
        return reservations;

    }

    public List<Reservations> getPossibleReservationsForUsers(String doctorname) {
        session = createSession();
        DoctorDataController controller = new DoctorDataController();
        Doctors doc = controller.getDoctorByName(doctorname);
        Query query = session.createQuery("FROM Reservations WHERE doctors = :doctorid ");
        query.setParameter("doctorid", doc.getDoctorid());

        List<Reservations> reservations = query.list();
        List<Reservations> returnList = new ArrayList();
        for (Reservations res : reservations) {
            if (res.getUser() == null) {
                returnList.add(res);
            }
        }
        System.out.println("RESERVATION LIST SIZE : " + reservations.size());
        return returnList;

    }

    public void updateReservation(int reservationid) {
        Transaction tran = null;
        session = createSession();

        tran = session.beginTransaction();
        Query query = session.createQuery("UPDATE Reservations SET users = 263" + "WHERE reservationid = 66");
        // query.setParameter("reservationid",reservationid);
        // query.setParameter("userid", Util.getUserId());
        UserDataController controller = new UserDataController();

        Users user = controller.getUserByID(Util.getUserId());
        Reservations res = (Reservations) session.load(Reservations.class, reservationid);
        res.setUser(user);

        session.update(res);
        tran.commit();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Your have made an reservation!", ""));
    }

    public void deleteReservationForUser(int reservationid) {

        Transaction tran = null;
        session = createSession();

        tran = session.beginTransaction();
        Query query = session.createQuery("UPDATE Reservations SET users = 263" + "WHERE reservationid = 66");

        Users user = null;
        Reservations res = (Reservations) session.load(Reservations.class, reservationid);
        res.setUser(user);

        session.update(res);
        tran.commit();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Your reservation has been canceled!", ""));

    }

    public void deleteReservationForDoctor(int reservationid) {
        Session sess = createSession();
        Transaction tran = null;
        try {

            tran = sess.beginTransaction();
            Reservations res = (Reservations) sess.load(Reservations.class, reservationid);
            sess.delete(res);
            tran.commit();
   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Deleted!", ""));
            
        } catch (Exception ex) {
            ex.printStackTrace();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Deletion Error!", ""));
        } finally {
            sess.close();
        }

    }
    
    
    public Reservations getReservationByID(int reservationid)
    {
        session = createSession();
        Query query = session.createQuery("FROM Reservations WHERE reservationid = :reservationid");
        query.setParameter("reservationid", reservationid);

        List<Reservations> reservations = query.list();
        if(reservations.size()>=1)
        {
            return reservations.get(0);
        }
        else
            return null;
    }

    public Session createSession() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        return factory.openSession();
    }

}
