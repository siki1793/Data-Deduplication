package com.iiitb.ddf;

import com.iiitb.dao.*;

public class Student {
	
	private Integer sno;
	private String rollno;
	private String name;
	private String college;
	public Student(Integer sno, String rollno, String name){
		this.sno = sno;
		this.rollno = rollno;
		this.name = name;
		//this.college = college;
	}
	public Integer getSno() {
		return sno;
	}
	public String getRollno() {
		return rollno;
	}
	public String getName() {
		return name;
	}
	public String getCollege()
	{
		return college;
	}
} 