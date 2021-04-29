package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create 3 student objs
			System.out.println("Creating 3 student objects ...");
			Student temp = new Student("Mary", "Public", "mary@luv2code.com");
			Student temp1 = new Student("John", "Doe", "john@luv2code.com");
			Student temp2 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

			// start transaction
			session.beginTransaction();
			
			// save obj
			System.out.println("Saving student object ...");
			session.save(temp);
			session.save(temp1);
			session.save(temp2);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}
}
