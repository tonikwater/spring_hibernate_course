package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			
			session.beginTransaction();
			
			System.out.println("Getting student with id: " + studentId);
			Student theStudent = session.get(Student.class, studentId);
			
			// delete student
			if(theStudent != null) {
				System.out.println("Deleting student: " + theStudent);
				session.delete(theStudent);	
			}
			
			// delete another student, with different way
			session.createQuery("delete from Student where id=2")
			.executeUpdate();
			
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		} 
	}

}
