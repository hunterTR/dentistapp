/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.auth;

import com.dentinium.db.dbconn;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
            resultText = "You are already logged in!";
            return "signupsuccess";
        } else {
            return signUp();

        }
    }

    public String signUp() {
        String returnText = "signuperror";
        dbconn dbDAO = null;
        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("users");
            BasicDBObject searchQuery = dbDAO.getBasicDBObject();
            searchQuery.put("username", username);
            DBCursor cursor = dbDAO.searchData(searchQuery);
            if (!cursor.hasNext()) {
                if (isCollectionAvailable) {
                    BasicDBObject userDocument = new BasicDBObject();
                    String token = "notsetyet";
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    
                    userDocument.put("username", username);
                    userDocument.put("email", email);
                    userDocument.put("password", password);
                    userDocument.put("token", token);
                    userDocument.put("lastlogin", date);
                    userDocument.put("date", date);
                    userDocument.put("admin", false);
                    userDocument.put("doctor", false);
                    userDocument.put("company", false);

                    dbDAO.insertData(userDocument);
                    returnText = "signupsuccess";
                    resultText = "You have been signed Up!";
                }
            } else {
                resultText = "Username is in use by another user.";
                returnText = "signupsuccess";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }
        System.out.println(resultText);
        return returnText;
    }

}
