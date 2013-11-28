/**
 * 
 */
package com.bliferniz.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bliferniz.dto.UserDetails;

public class HibernateTest {

	private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	/**
	 * 1) First Level Cache := Session
	 * 2) Second Level Cache	:= Across sessions in application
	 * 							:= Across applications
	 * 							:= Across clusters
	 * 
	 */
	
	public static void main(String[] args) {

		Session session = factory.openSession();
		session.beginTransaction();
		
		UserDetails user = (UserDetails)session.get(UserDetails.class, 2);
		
//		user.setUserName("User2");
		
		
		session.getTransaction().commit();
		session.close();

		Session session2 = factory.openSession();
		session2.beginTransaction();
		
		UserDetails user2 = (UserDetails)session2.get(UserDetails.class, 2); //get from cash
		
		session2.getTransaction().commit();
		session2.close();
		

	}

	
}
