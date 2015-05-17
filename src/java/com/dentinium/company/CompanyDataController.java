package com.dentinium.company;

import com.dentinium.hibernate.Company;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ahmetcem
 */
public class CompanyDataController {

    public CompanyDataController() {

    }

    public void createCompany(int userid) {

    }

    public Company getCompanyByName(String companyname) {
        Session session = createSession();
        Query query = session.createQuery("FROM Company WHERE companyname = :companyname");
        query.setParameter("companyname", companyname);

        List companies = query.list();

        if (companies.size() >= 1) {
            Company comp = (Company) companies.get(0);
            System.out.println("Company FOUND");
            session.close();
            return comp;
        } else {
            System.out.println("Company COULDN'T BE FOUND!");
            session.close();
            return null;
        }

    }

    public List<Company> getCompanies() {
        Session session = createSession();
        Query query = session.createQuery("FROM Company");

        List companies = query.list();

        if (companies.size() >= 1) {
          
            return companies;
        } else {

            System.out.println("Company COULDN'T BE FOUND!");
            session.close();
            return null;
        }
        
    }

    public Session createSession() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        return factory.openSession();
    }
    
    
    
    

}
