/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.doctor;

import com.dentinium.auth.Util;
import com.dentinium.company.CompanyDataController;
import com.dentinium.customer.UserDataController;
import com.dentinium.hibernate.Company;
import com.dentinium.hibernate.Doctors;
import com.dentinium.hibernate.Reservations;
import com.dentinium.hibernate.Users;
import java.util.Iterator;
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
public class DoctorDataController {

    Random randomGenerator;
    private CompanyDataController compdatacon;

    public DoctorDataController() {
randomGenerator = new Random();
compdatacon = new CompanyDataController();
    }

    public boolean isDoctor() {
        return true;
    }

    public boolean isAdmin() {
        return true;
    }

    public void changeEmail() {

    }

    public void setDoctor() {

    }

    public void createDoctor(int userid) {
        Session session = createSession();
        session = createSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            UserDataController userdatacon = new UserDataController();
            CompanyDataController compdatacon = new CompanyDataController();

            Users user = userdatacon.getUserByID(userid);
            Company comp = compdatacon.getCompanyByName(Util.getCompanyName());

            //  System.out.println(resDate);
            Doctors doctor = new Doctors(user.getUserid(), comp, user);
            session.save(doctor);
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

    public int getDoctorIDbyUserID(int userid) {
        Session session = createSession();
        session = createSession();
        Query query = session.createQuery("FROM Doctors WHERE Users.userid = :userid");
        query.setParameter("userid", userid);

        List users = query.list();

        if (users.size() >= 1) {
            Doctors us = (Doctors) users.get(0);
            session.close();
            return us.getDoctorid();
        } else {
            System.out.println("DOCTOR ID COULDN'T BE FOUND!");
            session.close();
            return -1;
        }

    }

    public Doctors getDoctorByUserID(int userid) {
        Session session = createSession();
        Query query = session.createQuery("FROM Doctors WHERE users = :userid");
        query.setParameter("userid", userid);

        List users = query.list();

        if (users.size() >= 1) {
            Doctors us = (Doctors) users.get(0);
            session.close();
            return us;
        } else {
            System.out.println("USER COULDN'T BE FOUND!");
            session.close();
            return null;
        }

    }

    public List<Doctors> getDoctorsByCompanyName(String companyName) {
        Session session = createSession();
        Company comp = compdatacon.getCompanyByName(companyName);
        int companyid = comp.getCompanyid();
        System.out.println("COMPANY IDDDDDD : " + 1);
        Query query = session.createQuery("FROM Doctors WHERE company = " + companyid);
        //query.setParameter("company", 1); 
       
            List<Doctors> doctors = query.list();
 
            if (doctors.size() >= 1) {
              
                return doctors;
            } else {
                System.out.println("DOCTORS COULDN'T BE FOUND!");
          
                return null;
            }

      
         

    }

    public Doctors getDoctorByName(String name) {
        Session session = createSession();
        Query query = session.createQuery("FROM Doctors");
        //query.setParameter("company", 1);

        List<Doctors> doctors = query.list();

        for (Iterator iter = doctors.iterator(); iter.hasNext();) {
            Doctors doc = (Doctors) iter.next();
            if (doc.getUser().getName().equals(name)) {
                session.close();
                return doc;
            }
        }
        System.out.println("DOCTOR COULDN'T BE FOUND!");
        session.close();
        return null;
    }

    public void deleteDoctor(int doctorid) {
        Session sess = createSession();
        Transaction tran = null;
        try {

            tran = sess.beginTransaction();
            Doctors doc = (Doctors) sess.load(Reservations.class, doctorid);
            sess.delete(doc);
            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sess.close();
        }

    }

    public Session createSession() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        return factory.openSession();
    }

}
