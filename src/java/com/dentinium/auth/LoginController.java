/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.auth;


import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import com.dentinium.hibernate.Company;
import com.dentinium.hibernate.Doctors;
import com.dentinium.hibernate.Users;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ahmetcem
 */
@ViewScoped
@ManagedBean(name = "LoginController")
public class LoginController implements Serializable {

    private String password;
    private String username;
    private Users user;
    private String companyName="";
    private Company company;
    private boolean isadmin = false;
    private boolean isdoctor = false;
    private boolean isLoggedIn = false;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String uname) {
        this.username = uname;
    }

    public String login() {
        
        String returnText = "LoginError";
        try {
            if (checkUsernameAndPassword()) {

                System.out.println("TOKEN :" + getUser().getToken());
                
                HttpSession session = Util.getSession();
                
                isadmin = isAdmin();
                isdoctor = isDoctor();
                session.setAttribute("userid", getUser().getUserid());
                session.setAttribute("username", getUser().getUsername());
                session.setAttribute("email", getUser().getEmail());
                session.setAttribute("companyname", companyName);
                session.setAttribute("doctor", isdoctor);
                session.setAttribute("admin", isadmin);
                session.setAttribute("token", getUser().getToken());
                session.setAttribute("name", getUser().getName());
                //session.setAttribute("company",company);
                
                
                System.out.println("TOKEN :" + getUser().getUserid());
                System.out.println("TOKEN :" + getUser().getUsername());
                System.out.println("TOKEN :" + getUser().getEmail());
                System.out.println("TOKEN :" + getUser().getName());
                
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                return "";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Invalid Login!",
                                "Please Try Again!"));
                return "LoginError";
            }
        } catch (HibernateException ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Could not connect to Database.",
                            ""));
            return "";
        }

    }

    public boolean checkUsernameAndPassword() {

        Query query = createSession().createQuery("FROM Users WHERE username = :username AND password = :password");
        query.setParameter("username", this.username);
        query.setParameter("password", this.password);
        List users = query.list();

        for (Iterator iter = users.iterator(); iter.hasNext();) {
            setUser((Users) iter.next());
            if (username.equals(getUser().getUsername())) {
                return true;
            }
        }
        System.out.println("Username incorrect");
        return false;
    }

    public boolean isDoctor() {

        
        Query query = createSession().createQuery("FROM Doctors WHERE users = :userid");
        query.setParameter("userid", this.getUser().getUserid());
        List users = query.list();

        if (users.size() >= 1) {
            Doctors us = (Doctors) users.get(0);
            setCompanyName(us.getCompany().getCompanyname());
            setCompany(us.getCompany());
            System.out.println("IS DOCTORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR!");
            
            return true;
        } else {
            System.out.println("IS NOT DOCTOR!");
            return false;
        }
    }

        public boolean isCompany() {

        
        Query query = createSession().createQuery("FROM Company WHERE users = :userid");
        query.setParameter("userid", this.getUser().getUserid());
        List users = query.list();

        if (users.size() >= 1) {
            Doctors us = (Doctors) users.get(0);
            setCompanyName(us.getCompany().getCompanyname());
            setCompany(us.getCompany());
            System.out.println("IS Company!");
            
            return true;
        } else {
            System.out.println("IS NOT DOCTOR!");
            return false;
        }
    }
    public boolean isAdmin() {

        Query query = createSession().createQuery("FROM Admins WHERE users = :userid");
        query.setParameter("userid",this.getUser().getUserid());
        List users = query.list();

        if (users.size() >= 1) {
              System.out.println("IS Admin!");
            return true;
           
        } else {
            System.out.println("IS NOT ADMIN!");
            return false;
        }
    }

    public boolean checkPassword(String password) {
        Query query = createSession().createQuery("FROM Users");

        List users = query.list();
        for (Iterator iter = users.iterator(); iter.hasNext();) {
            Users user = (Users) iter.next();
            if (password.equals(user.getPassword())) {
                return true;
            }
        }
        System.out.println("Password incorrect");
        return false;
    }

    public String logout() {
        System.out.println("LOGOUT CLICKED");
        HttpSession session = Util.getSession();
        session.invalidate();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            return "success";
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            return "LoginError";
        }
    }

    public String getWelcome() {

        if (Util.isLoggedIn()) {
            HttpSession session = Util.getRequest().getSession(false);
            String uname = session.getAttribute("username").toString();
            return uname;
        } else {
            return "guest";
        }
    }

    public String buttonName() {
        if (Util.isLoggedIn()) {
            return "Logout";
        } else {
            return "Login";
        }
    }

    public String Authentication() {
        if (Util.isLoggedIn()) {
            return logout();
        } else {
            return login();
        }
    }

    public Session createSession() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        return factory.openSession();
    }

    /**
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return the isadmin
     */
    public boolean isIsadmin() {
        return Util.isAdmin();
    }

    /**
     * @param isadmin the isadmin to set
     */
    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    /**
     * @return the isdoctor
     */
    public boolean isIsdoctor() {
        return Util.isDoctor();
    }

    /**
     * @param isdoctor the isdoctor to set
     */
    public void setIsdoctor(boolean isdoctor) {
        this.isdoctor = isdoctor;
    }

    /**
     * @return the isLoggedIn
     */
    public boolean isIsLoggedIn() {
        return Util.isLoggedIn();
    }

    /**
     * @param isLoggedIn the isLoggedIn to set
     */
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

}
