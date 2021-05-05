package com.luv2code.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(cascade= {
			CascadeType.REFRESH, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.PERSIST
	})
	@JoinColumn(name="instructor_id") // foreign key column of instructor
	private Instructor instructor;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id") // foreign key to course, is on the review side
	private List<Review> reviews;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= {
			CascadeType.REFRESH, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.PERSIST
	})
	@JoinTable(
			name="course_student", // name of join table
			// column name where primary key of this entity is stored
			joinColumns=@JoinColumn(name="course_id"),
			// column name where primary key of the entity
			// on "the other side" of the relationship is stored
			inverseJoinColumns=@JoinColumn(name="student_id")
	)
	private List<Student> students;
	
	public Course() {
		
	}

	public Course(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	// add a convenience method
	
	public void addReview(Review theReview) {
		if(reviews == null) {
			reviews = new ArrayList<>();
		}
		reviews.add(theReview);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	// add a convenience method
	
	public void addStudent(Student theStudent) {
		if(students == null) {
			students = new ArrayList<>();
		}
		students.add(theStudent);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
}
