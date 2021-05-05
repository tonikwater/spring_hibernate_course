package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteMaryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start transaction
			session.beginTransaction();
			
			// get mary from the db
			int theId = 2;
			Student tempStudent = session.get(Student.class, theId);
			
			System.out.println("Loaded student: " + tempStudent);
			System.out.println("Courses: " + tempStudent.getCourses());
			
			// delete student
			System.out.println("Deleting student: " + tempStudent);
			session.delete(tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
