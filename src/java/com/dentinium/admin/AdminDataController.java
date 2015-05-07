/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.admin;

import com.dentinium.company.Company;
import com.dentinium.db.dbconn;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ahmetcem
 */
public class AdminDataController {
    
   
    private String resultText;
    String token;
    String username;
    Random randomGenerator = new Random();
    
     public AdminDataController(String tok, String uname) {
        token = tok;
        username = uname;
    }

        public List<Company> getCompaniesList() {
        dbconn dbDAO = null;
        List<Company> returnList = new ArrayList();
        try {
            dbDAO = new dbconn();
            dbDAO.openDB();

            boolean isCollectionAvailable = dbDAO.connectDBCollection("users");

            if (isCollectionAvailable) {

                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("company",true);
                
                //System.out.println("USERID: " + Util.getUserId());
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
}
