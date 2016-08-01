package com.xjtu.bean;

import java.util.ArrayList;
import java.util.List;

public class Record {
	
	private String semester;
	private double takenCredit;
	private double deservedCredit;
	private double deservedPoint;
	private List<Course> classes;

	public Record() {
		this.takenCredit = 0;
		this.deservedCredit = 0;
		this.deservedPoint = 0;
		this.classes = new ArrayList<Course>();
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public List<Course> getClasses() {
		return classes;
	}

	public void setClasses(List<Course> classes) {
		this.classes = classes;
	}

	public double getTakenCredit() {
		return takenCredit;
	}

	public void setTakenCredit(double takenCredit) {
		this.takenCredit = takenCredit;
	}

	public double getDeservedCredit() {
		return deservedCredit;
	}

	public void setDeservedCredit(double deservedCredit) {
		this.deservedCredit = deservedCredit;
	}

	public double getDeservedPoint() {
		return deservedPoint;
	}

	public void setDeservedPoint(double deservedPoint) {
		this.deservedPoint = deservedPoint;
	}

	public void addCourse(Course course){
		this.classes.add(course);
	}
	
	public int numClasses(){
		return this.classes.size();
	}
	
	public void takenCreditIncrease(double increase){
		this.takenCredit += increase;
	}
	
	public void deservedCreaditIncrease(double increase){
		this.deservedCredit += increase;
	}
	
	public void deservedPointIncrease(double increase){
		this.deservedPoint += increase;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer courseInfo = new StringBuffer();
		for(int i = 0; i < this.classes.size(); i++)
			courseInfo.append(this.classes.get(i).toString() + "\n");
		return "学期: " + this.semester + "\n" + courseInfo.toString()
				+ "修习学分：" + this.deservedCredit + "\n实得学分：" + this.takenCredit
				+ "\n绩点：" + this.deservedPoint;
	}
	
	
}
