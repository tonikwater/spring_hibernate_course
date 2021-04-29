package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create student obj
			System.out.println("Creating new student object ...");
			Student temp = new Student("Daffy", "duck", "duck@luv2code.com");
			
			// start transaction
			session.beginTransaction();
			// save obj
			System.out.println("Saving student object ...");
			System.out.println(temp.toString());
			session.save(temp);
			// commit transaction
			session.getTransaction().commit();
			
			// NEW CODE
			
			// find out the students id (primary key)
			System.out.println("Generated student id: " + temp.getId());
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("\nGetting student with id: " + temp.getId());
			
			Student myStudent = session.get(Student.class, temp.getId());
			
			System.out.println("Get complete: " + myStudent);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
