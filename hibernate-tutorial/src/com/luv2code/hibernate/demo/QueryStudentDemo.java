package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session
					.createQuery("from Student")
					.getResultList();
			
			
			// display student
			displayStudents(theStudents);
			
			// query students: lastName == 'Doe'
			System.out.println("\n");
			theStudents = session.
					createQuery("from Student s where s.lastName='Doe'")
					.getResultList();
			
			// display student
			System.out.println("Students where last name is Doe");
			displayStudents(theStudents);
			
			// query students: lastName == 'Doe'
			System.out.println("\n");
			theStudents = session.
					createQuery("from Student s where s.lastName='Doe'"
							+" OR s.firstName='Daffy'")
					.getResultList();
			
			// display student
			System.out.println("Students where last name is Doe or first name is Daffy");
			displayStudents(theStudents);
			
			// query students: with certain email
			System.out.println("\n");
			theStudents = session.
					createQuery("from Student s where s.email LIKE '%@luv2code.com'")
					.getResultList();
			
			// display student
			System.out.println("Students where email ends with @luv2code.com");
			displayStudents(theStudents);
			
			// query students: via sql injection !! createQuery is actually not safe
			// because we have control over the query, because the query
			// is not preprocessed, because here it of course wouldnt be possible
			// so in this case input validation should be used if we want to execute
			// a query with user input parameter
			System.out.println("\n");
			theStudents = session.
					createQuery(buildMyQuery("%a@luv2code.com' OR 'a'='a"))
					.getResultList();
			
			// display student
			System.out.println("Result from sql injected query");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student s : theStudents) {
			System.out.println(s);
		}
	}
	
	// my test
	private static String buildMyQuery(String param) {
		return "from Student s where s.email LIKE '" + param + "'";
	}

}
