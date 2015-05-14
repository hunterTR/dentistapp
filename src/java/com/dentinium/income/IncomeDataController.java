/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.income;

import com.dentinium.auth.Util;
import com.dentinium.company.CompanyDataController;
import com.dentinium.customer.UserDataController;
import com.dentinium.doctor.DoctorDataController;
import com.dentinium.hibernate.Company;
import com.dentinium.hibernate.Doctors;
import com.dentinium.hibernate.Income;
import com.dentinium.hibernate.Reservations;
import com.dentinium.room.RoomDataController;
import java.util.Date;
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
public class IncomeDataController {

    public Random randomGenerator;
    private Session session;

    public IncomeDataController() {
        randomGenerator = new Random();
    }

    public boolean createIncome(Reservations reservation, Date incomeDate, String description, int income) // Doctor creates reservation.
    {
        session = createSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CompanyDataController compdatacon = new CompanyDataController();

            // Users rd = userdatacon.getUserByID(Util.getUserId());
            //  Doctors doc = doctordatacon.getDoctorByUserID(Util.getUserId());
            Company comp = compdatacon.getCompanyByName(Util.getCompanyName());

            // System.out.println(resDate);
            Income res = new Income(randomGenerator.nextInt(1000), reservation.getUser(), reservation.getDoctor(), comp, description, incomeDate, income);

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

    public boolean createIncome(Date incomeDate, String description, int income) // Doctor creates reservation.
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

            Company comp = compdatacon.getCompanyByName(Util.getCompanyName());

            Income res = new Income(randomGenerator.nextInt(1000), doc, comp, description, incomeDate, income);

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

    public void deleteIncome() {

    }
    
    
    public List<Income> getIncomesByCompany(String companyname)
    { 
        session = createSession();
        CompanyDataController compdatacon = new CompanyDataController();
        Company comp = compdatacon.getCompanyByName(companyname);
        Query query = session.createQuery("FROM Income WHERE company = " + comp.getCompanyid());
        //query.setParameter("companyid", comp.getCompanyid());

        List reservations = query.list();
        System.out.println("RESERVATION LIST SIZE : " + reservations.size());
        return reservations;

    }

    public Session createSession() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        return factory.openSession();
    }
}
