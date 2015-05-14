/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.admin;


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

}
