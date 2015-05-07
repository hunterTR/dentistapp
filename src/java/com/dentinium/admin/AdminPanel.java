/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.admin;

import com.dentinium.auth.Util;
import com.dentinium.company.Company;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ApplicationScoped
@ManagedBean(name = "AdminPanel")
public class AdminPanel {

    private String name = Util.getName();
    private String username = Util.getUserName();
    private String token = Util.getToken();
    private String email;
    private List<Company> companiesList;

    //  private String companyname = Util.getCompanyName();
    private String adminID = "asdasd";
    private AdminDataController companyDataController = new AdminDataController(token, username);

    public List<Company> getCompaniesList() {
        return companyDataController.getCompaniesList();
    }

    public String getName() {
        return Util.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return Util.getUserName();
    }

    public void setUsername(String uname) {
        this.username = uname;
    }

    public void setEmail(String mail) {
        email = mail;
    }

    public String getEmail() {
        return Util.getEmail();
    }
    
    
    public void deleteCompany(int companyID)
    {
        
    }
}
