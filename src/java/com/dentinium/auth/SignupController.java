/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.auth;

import com.dentinium.hibernate.Users;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ahmetcem
 */
@ApplicationScoped
@ManagedBean(name = "SignupController")

public class SignupController implements Serializable {

    private String username;
    private String email;
    private String password;
    private String resultText;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;

    }

    public String getResultText() {
        return resultText;
    }

    public void setUsername(String uname) {
        username = uname;
    }

    public void setEmail(String mail) {
        email = mail;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public void setResultText(String result) {
        resultText = result;
    }

    public String signUpIfNotLoggedIn() {
        if (Util.isLoggedIn()) {
            return signUp();
  
        } else {
            return signUp();

        }
    }

    public String signUp() {
        

        
             Session session = createSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
         
           
            Users rd = new Users(1,"hunterTR","alanya123", "ahmetcem3@gmail.com","Ahmet Cem Kaya");
            session.save(rd);
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DONE!", ""));
            return "success";
            
        } catch (HibernateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DATABASE ERROR", ""));

            if (transaction != null) {
                transaction.rollback();
            }
          
        } finally {
            session.close();
        }
        
    return "LoginError";
        
    }
    
    
    public Session createSession() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        return factory.openSession();
    }

    

}


/*

Company adder


            Session session = createSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
         
           UserDataController userdatacon = new UserDataController();
           Users us = userdatacon.getUserByID(Util.getUserId());
           
           Company comp = new Company(1,us,"dentinium");
            
            session.save(comp);
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DONE!", ""));
            return "success";
            
        } catch (HibernateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DATABASE ERROR", ""));

            if (transaction != null) {
                transaction.rollback();
            }
          
        } finally {
            session.close();
        }
        
    return "LoginError";


*/




/*
DOCTOR ADDER

              Session session = createSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
         
           UserDataController userdatacon = new UserDataController();
           CompanyDataController companydatacon = new CompanyDataController();
           
           System.out.println("signup function company name check :" + Util.getCompanyName());
           Company comp = companydatacon.getCompanyByName(Util.getCompanyName());
           Users us = userdatacon.getUserByID(Util.getUserId());
           
           Doctors doc = new Doctors(2,comp,us);
            
            session.save(doc);
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DONE!", ""));
            return "success";
            
        } catch (HibernateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "DATABASE ERROR", ""));

            if (transaction != null) {
                transaction.rollback();
            }
          
        } finally {
            session.close();
        }
        
    return "LoginError";
*/