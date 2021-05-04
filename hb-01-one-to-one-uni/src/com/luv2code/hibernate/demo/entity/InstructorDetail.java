package com.luv2code.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="youtube_channel")
	private String youtubeChanneL;
	
	@Column(name="hobby")
	private String hobby;

	public InstructorDetail() {
		
	}

	public InstructorDetail(String youtubeChanneL, String hobby) {
		this.youtubeChanneL = youtubeChanneL;
		this.hobby = hobby;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYoutubeChanneL() {
		return youtubeChanneL;
	}

	public void setYoutubeChanneL(String youtubeChanneL) {
		this.youtubeChanneL = youtubeChanneL;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youtubeChanneL=" + youtubeChanneL + ", hobby=" + hobby + "]";
	}
}
