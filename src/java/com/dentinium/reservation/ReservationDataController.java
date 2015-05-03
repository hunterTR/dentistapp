/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.reservation;

import com.dentinium.auth.Util;
import com.dentinium.company.Company;
import com.dentinium.customer.AppointmentBean;
import com.dentinium.db.dbconn;
import com.dentinium.doctor.Doctor;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ahmetcem
 */
public class ReservationDataController {

    public String token;
    public String username;
    public String name;
    public Random randomGenerator;
    public String userID;
    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();

    public ReservationDataController(String token, String uname, String name, String id) {
        this.token = token;
        this.username = uname;
        this.name = name;
        this.userID = id;
        randomGenerator = new Random();
    }

    public void createReservation(Date resDate, String resRoom) // Doctor creates reservation.
    {
        String returnText = "LoginError";
        dbconn dbDAO = null;

        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("Reservations");

            if (isCollectionAvailable) {
                BasicDBObject reservationHolderDocument = new BasicDBObject();
                BasicDBObject reservationDocument = new BasicDBObject();
                BasicDBObject companyDocument = new BasicDBObject();
                BasicDBObject doctorDocument = new BasicDBObject();
                String drToken = this.token;
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = resDate;
                // We are sure that the user is Doctor so we can get from session.

                doctorDocument.put("username", Util.getUserName());
                doctorDocument.put("name", Util.getName());
                doctorDocument.put("doctorid", userID);

                reservationDocument.put("companyname", Util.getCompanyName());
                // companyDocument.put("companyID", Util.get)
                //doctorDocument.put("image", date)
                reservationDocument.put("room", resRoom);
                reservationDocument.put("date", date);
                int randomInt = randomGenerator.nextInt(1000);
                reservationDocument.put("reservationID", randomInt);
                reservationDocument.put("doctor", doctorDocument);
                // reservationHolderDocument.put("possiblereservationdates", reservationDocument);

                dbDAO.insertData(reservationDocument);

               
                
                try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("doctorpanel.xhtml");
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Complete!", "Reservation slot has been created.");

                RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (IOException ex) {
            Logger.getLogger(AppointmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }

                returnText = "reservationsuccess";
                // resultText = "You have created reservation date!";
                System.out.println("RESERVATION CREATED!!!!!!!!" + username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

    }

    public List<Reservation> getReservationsForDoctor() {
        dbconn dbDAO = null;
        List<Reservation> returnList = new ArrayList();
        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("Reservations");

            if (isCollectionAvailable) {

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("doctor.doctorid", Util.getUserId());
                System.out.println("USERID: " + Util.getUserId());
                DBCursor cursor = dbDAO.searchData(searchQuery);
                if (null != cursor && cursor.hasNext()) {
                    List<DBObject> result = cursor.toArray();

                    for (DBObject obj : result) {
                        returnList.add(new Reservation((BasicDBObject) obj));
                    }
                    //Query sonucu
                    System.out.println("LIST HAS BEEN CREATED SIR!");
                    return returnList;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

        return null;
    }

    public List<Reservation> getBookedReservationsForCustomer() {
        dbconn dbDAO = null;
        List<Reservation> returnList = new ArrayList();
        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("Reservations");

            if (isCollectionAvailable) {

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("customer.customerid", Util.getUserId());
                System.out.println("USERID: " + Util.getUserId());
                DBCursor cursor = dbDAO.searchData(searchQuery);
                if (null != cursor && cursor.hasNext()) {
                    List<DBObject> result = cursor.toArray();

                    for (DBObject obj : result) {
                        returnList.add(new Reservation((BasicDBObject) obj));
                    }
                    //Query sonucu
                    System.out.println("LIST HAS BEEN CREATED SIR!");
                    return returnList;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

        return null;
    }

    public List<Reservation> getPossibleReservationsByDoctor(String doctorname) {
        dbconn dbDAO = null;
        List<Reservation> returnList = new ArrayList();
        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("Reservations");

            if (isCollectionAvailable) {

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("doctor.name", doctorname);
                //   System.out.println("USERID: " + Util.getUserId());
                DBCursor cursor = dbDAO.searchData(searchQuery);
                if (null != cursor && cursor.hasNext()) {
                    List<DBObject> result = cursor.toArray();
                    System.out.println("ASDASDASDASDASDASDAS: " + result.size());
                    for (DBObject obj : result) {
                        if (obj.get("customer") == null) // if appointment is not taken before.
                        {
                            returnList.add(new Reservation((BasicDBObject) obj));
                        }
                    }
                    //Query sonucu
                    System.out.println("LIST HAS BEEN CREATED SIR!");
                    return returnList;
                } else {
                    System.out.println("NO DATA FOUND FROM THAT QUERY! ");
                }

            } else {
                System.out.println("COULD NOT CONNECTED TO DATABASE!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

        return null;
    }
    
    
    public List<Reservation> getReservationsByCompany(String companyname) {
        dbconn dbDAO = null;
        List<Reservation> returnList = new ArrayList();
        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("Reservations");

            if (isCollectionAvailable) {

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("companyname", companyname);
                //   System.out.println("USERID: " + Util.getUserId());
                DBCursor cursor = dbDAO.searchData(searchQuery);
                if (null != cursor && cursor.hasNext()) {
                    List<DBObject> result = cursor.toArray();
                    System.out.println("ASDASDASDASDASDASDAS: " + result.size());
                    for (DBObject obj : result) {
                        if (obj.get("customer") != null) // if appointment is not taken before.
                        {
                            returnList.add(new Reservation((BasicDBObject) obj));
                        }
                    }
                    //Query sonucu
                    System.out.println("LIST HAS BEEN CREATED SIR!");
                    return returnList;
                } else {
                    System.out.println("NO DATA FOUND FROM THAT QUERY! ");
                }

            } else {
                System.out.println("COULD NOT CONNECTED TO DATABASE!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

        return null;
    }

    public void updateReservation(int reservationID, String treatment) // call when user book a appointment
    {
        dbconn dbDAO = null;

        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("Reservations");

            if (isCollectionAvailable) {

                BasicDBObject searchQuery = new BasicDBObject();

                searchQuery.put("reservationID", reservationID);

                BasicDBObject customerDocument = new BasicDBObject();
                customerDocument.put("name", Util.getName());
                customerDocument.put("username", Util.getUserName());
                customerDocument.put("customerid", Util.getUserId());
                customerDocument.put("email", Util.getEmail());

                BasicDBObject updateDocument = new BasicDBObject();
                updateDocument.put("type", treatment);
                updateDocument.put("cost", 100);
                updateDocument.put("customer", customerDocument);
                dbDAO.updateData(searchQuery, new BasicDBObject("$set", updateDocument));

                System.out.println("WE HAVE RECIEVED YOUR RESERVATION!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

    }

    public void deleteReservationForUser(int reservationID) // Removes customer object from reservation.So It will be rebookable.
    {
        dbconn dbDAO = null;

        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("Reservations");

            if (isCollectionAvailable) {

                BasicDBObject searchQuery = new BasicDBObject();

                searchQuery.put("reservationID", reservationID);

                BasicDBObject customerDocument = new BasicDBObject();

                dbDAO.updateData(searchQuery, new BasicDBObject("$unset", new BasicDBObject("customer", "")));

                System.out.println("RESERVATION HAS BEEN CANCELED");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

    }

    public void deleteReservationForDoctor(int reservationID) // Removes whole reservation document from database.
    {
        dbconn dbDAO = null;

        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("Reservations");

            if (isCollectionAvailable) {

                BasicDBObject searchQuery = new BasicDBObject();

                searchQuery.put("reservationID", reservationID);

                dbDAO.removeData(searchQuery);

                System.out.println("RESERVATION HAS BEEN CANCELED");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

    }

    public List<Company> getCompanyList() {
        dbconn dbDAO = null;
        List<Company> returnList = new ArrayList();
        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("users");

            if (isCollectionAvailable) {

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("company", true);
                //  System.out.println("USERID: " + Util.getUserId());
                DBCursor cursor = dbDAO.searchData(searchQuery);
                if (null != cursor && cursor.hasNext()) {
                    List<DBObject> result = cursor.toArray();

                    for (DBObject obj : result) {
                        returnList.add(new Company((BasicDBObject) obj));
                    }

                    //Query sonucu
                    System.out.println("LIST HAS BEEN CREATED SIR!");
                    return returnList;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

        return null;
    }

    public List<Doctor> getDoctorListByCompany(String companyname) {

        dbconn dbDAO = null;
        List<Doctor> returnList = new ArrayList();
        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("users");

            if (isCollectionAvailable) {

                System.out.println("COMPANY NAME BRO : " + companyname);
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("works.companyname", companyname);

                //  System.out.println("USERID: " + Util.getUserId());
                DBCursor cursor = dbDAO.searchData(searchQuery);
                if (null != cursor && cursor.hasNext()) {
                    List<DBObject> result = cursor.toArray();

                    for (DBObject obj : result) {
                        returnList.add(new Doctor((BasicDBObject) obj));
                    }

                    //Query sonucu
                    System.out.println("LIST HAS BEEN CREATED SIR!");
                    return returnList;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbDAO.closeDB();
        }

        return null;

    }

}
