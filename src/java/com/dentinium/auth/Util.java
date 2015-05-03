package com.dentinium.auth;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {

    public static HttpSession getSession() {

        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
    }

    public static HttpServletRequest getRequest() {

        return (HttpServletRequest) FacesContext.
                getCurrentInstance().
                getExternalContext().getRequest();
    }

    public static String getUserName() {

        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);

        
          if ( session.getAttribute("username") !=null)
        return session.getAttribute("username").toString();
        else
        return "yok";
    }

    public static String getToken() {
        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);

          if ( session.getAttribute("token") !=null)
        return session.getAttribute("token").toString();
        else
        return "yok";
    }

    public static String getEmail() {
        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);

         if ( session.getAttribute("email") !=null)
        return session.getAttribute("email").toString();
        else
        return "yok";
    }

    public static String getName() {
        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);

       if ( session.getAttribute("name") != null)
        return session.getAttribute("name").toString();
        else
        return "yok";
    }

    public static boolean isDoctor() {
        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);

        if ( session.getAttribute("doctor") !=null)
        return (boolean) session.getAttribute("doctor");
        else
        return false;
    }
    public static boolean isAdmin() {
        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);

        if ( session.getAttribute("admin") !=null)
        return (boolean) session.getAttribute("admin");
        else
        return false;
    }

    public static String getUserId() {

        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userid");
        } else {
            return null;
        }
    }

    public static String getCompanyName() {
        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);

        if ( session.getAttribute("companyname") !=null)
        return session.getAttribute("companyname").toString();
        else
        return "yok";
    }

    public static boolean isLoggedIn() {
        HttpSession session = Util.getRequest().getSession(false);
        if (session != null) {

            if (null == session.getAttribute("username")) {
  // User is not logged in.  

                return false;
            } else {
                // User IS logged in.  
                String uname = session.getAttribute("username").toString();
                return true;
            }

        } else {
            return false;
        }
    }
}
