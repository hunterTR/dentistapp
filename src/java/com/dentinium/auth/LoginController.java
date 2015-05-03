/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.auth;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.dentinium.db.dbconn;
import com.mongodb.DBObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ahmetcem
 */
@ApplicationScoped
@ManagedBean(name = "LoginController")
public class LoginController implements Serializable {

    private String password;
    private String username;

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
        dbconn dbDAO = null;
        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("users");

            if (isCollectionAvailable) {
                BasicDBObject searchQuery = dbDAO.getBasicDBObject();
                searchQuery.put("username", username);
                searchQuery.put("password", password);

                DBCursor cursor = dbDAO.searchData(searchQuery);
                if (null != cursor && cursor.hasNext()) {
                    returnText = "success";

                    //Query sonucu
                    List<DBObject> result = cursor.toArray();
                    String token = result.get(0).get("token").toString();
                    String email = result.get(0).get("email").toString();
                    String name = result.get(0).get("name").toString();
                    String userID = result.get(0).get("userid").toString();
                    boolean admin = (boolean) result.get(0).get("admin");
                    boolean doctor = (boolean) result.get(0).get("doctor");
                     DBObject xxx = (DBObject) result.get(0).get("works");
                     String companyname = "not working";
                     if(xxx != null)
                     companyname = xxx.get("companyname").toString();
                             

                    System.out.println("TOKEN :" + token);
                    HttpSession session = Util.getSession();
                    session.setAttribute("userid",userID);
                    session.setAttribute("username", username);
                    session.setAttribute("email", email);
                    session.setAttribute("companyname", companyname);
                    session.setAttribute("admin", admin);
                    session.setAttribute("doctor", doctor);
                    session.setAttribute("token", token);
                    session.setAttribute("name", name);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }
        System.out.println(returnText);
        return returnText;
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
}
