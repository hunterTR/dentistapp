
package com.dentinium.auth;

import com.dentinium.customer.UserDataController;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name = "registrationBean")
@RequestScoped
public class RegistrationBean
{
 private RegistrationForm registrationForm = null;
 private List<String> interests = null;
 private List<String> genders = null;
 private UserDataController userdatacon = new UserDataController();
 Random randomgenerator;
 public RegistrationBean()
 {
 randomgenerator = new Random();
 }
 
 public String register()
 {
  System.out.println("register.....");
  
  String token = "asdasd";
  
  //store data in DB

  if(userdatacon.createUser(randomgenerator.nextInt(1000),registrationForm.getUserName(), 
          registrationForm.getPassword(), token, registrationForm.getDob(), registrationForm.getPhone(),
          registrationForm.getEmail(), registrationForm.getAddress(), registrationForm.getName()))
  return "index";//go to welcome.xhtml
  else 
      return "";
 }
 
 public RegistrationForm getRegistrationForm()
 {
  if(this.registrationForm == null){
   this.registrationForm = new RegistrationForm();
  }
  return registrationForm;
 }

 public void setRegistrationForm(RegistrationForm registrationForm)
 {
  this.registrationForm = registrationForm;
 }

 public List<String> getInterests()
 {
  return interests;
 }

 public void setInterests(List<String> interests)
 {
  this.interests = interests;
 }

 public List<String> getGenders()
 {
  return genders;
 }

 public void setGenders(List<String> genders)
 {
  this.genders = genders;
 }
  
}