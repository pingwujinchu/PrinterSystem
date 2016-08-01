package com.xjtu.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Course {
	
	private String attr;
	private String name;
	private double credit;
	private double deservedCredit;

	private double grade;
	private double point;
	private Date time;

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date startTime) {
		this.time = startTime;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public Course() {
		super();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		return "课程属性：" + this.attr + "; 课程名：" + this.name + "; 学分：" + this.credit
				+ "; 成绩：" + this.grade + "; 绩点：" + this.point + "；时间：" + sdf.format(this.time);
	}

	public Course(String attr, String name, double credit, double grade, double point,Date time) {
		super();
		this.attr = attr;
		this.name = name;
		this.credit = credit;
		this.grade = grade;
		this.time = time;
		this.point = point;
	}

	public Course(String attr, String name, double credit, double grade, double point,double deservedCredit) {
		super();
		this.attr = attr;
		this.name = name;
		this.credit = credit;
		this.grade = grade;
		this.point = point;
		this.deservedCredit =deservedCredit;
	}

	public double getDeservedCredit() {
		return deservedCredit;
	}

	public void setDeservedCredit(double deservedCredit) {
		this.deservedCredit = deservedCredit;
	}
	
	


}
