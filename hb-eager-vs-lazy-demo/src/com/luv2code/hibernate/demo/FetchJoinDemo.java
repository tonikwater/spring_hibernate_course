package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start transaction
			session.beginTransaction();
			
			
			// get instructor from db
			int theId = 1;
			// option 2: create hql query
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
							+ "JOIN FETCH i.courses "
							+ "where i.id=:theInstructorId",
							Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			// execute query
			Instructor tempInstructor = query.getSingleResult();

			System.out.println("LUV: Instructor: " + tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			// close session
			session.close();
			
			System.out.println("LUV: Closing session.");
			System.out.println("LUV: Courses: " + tempInstructor.getCourses());
			
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
