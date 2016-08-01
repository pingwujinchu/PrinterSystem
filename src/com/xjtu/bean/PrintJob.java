package com.xjtu.bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrintJob {
	 private User user;
	 private List gradeList;
	 private Date time;
	 private String jobID;
     
	 
	public PrintJob(User user, List gradeList, Date time) {
		super();
		this.user = user;
		this.gradeList = gradeList;
		this.time = time;
		UUID uuid  =  UUID.randomUUID(); 
		jobID = UUID.randomUUID().toString();
	}
	
	
	
	public String getJobID() {
		return jobID;
	}



	public void setJobID(String jobID) {
		this.jobID = jobID;
	}



	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List getGradeList() {
		return gradeList;
	}
	public void setGradeList(List gradeList) {
		this.gradeList = gradeList;
	}
	 
}
