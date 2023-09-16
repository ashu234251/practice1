package com.telusko.DemoHib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class App {
	public static void main( String[] args )
    {
    	Alien telusko = new Alien();
    	telusko.setAid(101);
    	telusko.setAname("Ashish");
    	telusko.setColor("Green");
    	
    	
    	
    	Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
    	
    	ServiceRegistry reg= new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
    	
    	SessionFactory sf = con.buildSessionFactory(reg);
    	
    	Session session= sf.openSession();
    	
    	Transaction tx=session.beginTransaction();
    	
    	session.save(telusko);
    	
    	tx.commit();
    }
}
