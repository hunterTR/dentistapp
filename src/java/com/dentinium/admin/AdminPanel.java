/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dentinium.admin;

import com.dentinium.auth.Util;
import com.dentinium.company.CompanyDataController;
import com.dentinium.customer.UserDataController;
import com.dentinium.hibernate.Company;
import com.dentinium.hibernate.Users;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "AdminPanel")
@ViewScoped
public class AdminPanel {

    private String name;
    private List<Company> companies;

    private String userName;
    private String companyName;
    private Users user;
    private CompanyDataController compdatacon = new CompanyDataController();
    private UserDataController userdatacon = new UserDataController();
    private Map<String, String> hashUsers;
    private List<Users> users = userdatacon.searchUser("");

    public AdminPanel() {
        hashUsers = new HashMap();
      loadUserToHasmap();
    }

    /**
     * @return the name
     */
    public String getName() {
        return Util.getName();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the companies
     */
    public List<Company> getCompanies() {
        return compdatacon.getCompanies();
    }

    /**
     * @param companies the companies to set
     */
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public void addCompany() {

        System.out.println("COMPANY USEER : " + user.getName());

    }

    /**
     * @return the users
     */
    public List<Users> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public void loadUserToHasmap() {

        for (Users user : users) {
            System.out.println(user.getName());
            hashUsers.put(user.getName(), user.getName());
        }

    }
    public void deleteCompany(int companyid)
    {
       //compdatacon.deleteCompany(companyid);
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return the hashUsers
     */
    public Map<String, String> getHashUsers() {

        return hashUsers;
    }

    /**
     * @param hashUsers the hashUsers to set
     */
    public void setHashUsers(Map<String, String> hashUsers) {
        this.hashUsers = hashUsers;
    }

}
