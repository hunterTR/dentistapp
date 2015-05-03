/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.doctor;

import com.dentinium.auth.Util;
import com.dentinium.db.dbconn;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ahmetcem
 */
public class DoctorDataController {

    dbconn DB = new dbconn();
    private String resultText;
    String token;
    String username;
    Random randomGenerator = new Random();

    public DoctorDataController(String tok, String uname) {
        token = tok;
        username = uname;
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

    public void createReservationDate(Date resDate, String resRoom) {
        String returnText = "LoginError";
        dbconn dbDAO = null;

        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("users");

            if (isCollectionAvailable) {
                BasicDBObject reservationHolderDocument = new BasicDBObject();
                BasicDBObject reservationDocument = new BasicDBObject();

                BasicDBObject searchQuery = new BasicDBObject();
                String drToken = this.token;
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = resDate;

                searchQuery.put("username", username);
                searchQuery.put("token", token);
                reservationDocument.put("room", resRoom);
                reservationDocument.put("date", date);
                int randomInt = randomGenerator.nextInt(1000);
                reservationDocument.put("reservationID", randomInt);
                reservationHolderDocument.put("possiblereservationdates", reservationDocument);

                dbDAO.updateData(searchQuery, reservationHolderDocument);
                returnText = "signupsuccess";
                resultText = "You have created reservation date!";
                System.out.println("RESERVATION CREATED!!!!!!!!" + username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

    }

    public BasicDBObject getDoctorObject() {
        dbconn dbDAO = null;
        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("users");

            if (isCollectionAvailable) {
                BasicDBObject searchQuery = dbDAO.getBasicDBObject();
                searchQuery.put("username", username);
                searchQuery.put("token", token);
                DBCursor cursor = dbDAO.searchData(searchQuery);
                if (null != cursor && cursor.hasNext()) {

                    //Query sonucu
                    List<DBObject> result = cursor.toArray();

                    return (BasicDBObject) result.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }
        return null;
    }

    public void deleteReservationDate() {

    }
    
  

    public String getReservationDates() {
        
        return null;

    }

    public void updateCompany() {

    }

}
