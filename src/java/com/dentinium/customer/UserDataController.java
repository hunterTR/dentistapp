/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.customer;

import com.dentinium.hibernate.Doctors;
import com.dentinium.hibernate.Users;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ahmetcem
 */
public class UserDataController {



    public UserDataController() {
    }

    public boolean createUser(int userid, String username, String password, String token, Date date, String phone, String email, String address, String name) {

        Session session = createSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Users rd = new Users(userid, username, password, token, date, phone, email, address, name);
            session.save(rd);
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

    public Users getUserByID(int userid) {
         Session session = createSession();
        Query query = session.createQuery("FROM Users WHERE userid = :userid");
        query.setParameter("userid", userid);

        List users = query.list();

        if (users.size() >= 1) {
            Users us = (Users) users.get(0);
session.close();
            return us;
        } else {
            session.close();
            System.out.println("USER COULDN'T BE FOUND!");
            return null;
        }

    }

        public List<Users> searchUser(String name) {
 Session session = createSession();
        Criteria query = session.createCriteria(Users.class);
        query.add(Restrictions.like("name", name, MatchMode.START));

        List users = query.list();

        if (users.size() >= 1) {
            session.close();
            return users;
        } else {
            System.out.println("USERs COULDN'T BE FOUND!");
            session.close();
            return null;
        }

    }
        
    public Session createSession() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        return factory.openSession();
    }



}
