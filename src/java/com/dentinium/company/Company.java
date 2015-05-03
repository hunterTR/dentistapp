/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.company;

import com.mongodb.BasicDBObject;
import java.util.Date;

/**
 *
 * @author ahmetcem
 */
public class Company {
     public String companyID;

    public String companyName;

    
    public Company(BasicDBObject obj)
    {
        BasicDBObject tmp = (BasicDBObject) obj.get("works");
        System.out.println(tmp.get("companyname"));
        companyID = tmp.getString("companyid");
        

      
        companyName = tmp.getString("companyname");

    }
    
    
    public void setCompanyID(String id)
    {
        this.companyID = id;
    }
    
    public String getCompanyID()
    {
        return companyID;
    }
    
    public void setCompanyName(String id)
    {
        this.companyName = id;
    }
    
    public String getCompanyName()
    {
        return companyName;
    }
    
    
    
    
}
