package com.xjtu.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import com.lowagie.text.Image;

public class User {

	private String userName;
	private String studentId;
	private String sex;
	private String idCard;
	private String nativePlace;
	private String nation;
	private String political;
	private String className;
	private String major;
	private String majorFiled;
	private String college;
	private String degreeType;
	private Image image;
	private double credit;
	private Date birthDay;
	private Date entranceDay;
	private Date graduDay;

	public User() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajorFiled() {
		return majorFiled;
	}

	public void setMajorFiled(String majorFiled) {
		this.majorFiled = majorFiled;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Date getEntranceDay() {
		return entranceDay;
	}

	public void setEntranceDay(Date entranceDay) {
		this.entranceDay = entranceDay;
	}

	public Date getGraduDay() {
		return graduDay;
	}

	public void setGraduDay(Date graduDay) {
		this.graduDay = graduDay;
	}
	
	

	/*
	 *  userName;studentId;sex;idCard;nativePlace;nation;political;className;major;
	 *  majorFiled;college;degreeType;image;credit;birthDay;entranceDay;graduDay;
	 * 
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "姓名：" + this.userName + "; 学号: " + this.studentId
				+ "; 性别：" + this.sex + "; 身份证号：" + this.idCard + "; 籍贯：" + this.nativePlace
				+ "; 民族：" + this.nation + "; 政治面貌：" + this.political + "; 班级：" + this.className
				+ "; 专业：" + this.major + "; 专业方向：" + this.majorFiled + "; 学院：" + this.college
				+ "; 方案要求学分：" + this.credit + "; birthDay:" + this.birthDay.toString();
	}

	public User(String userName, String studentId, String sex, String idCard, String nativePlace, String nation,
			String political, String className, String major, String majorFiled, String college, String degreeType,
			Image image, double credit, Date birthDay, Date entranceDay, Date graduDay) {
		super();
		this.userName = userName;
		this.studentId = studentId;
		this.sex = sex;
		this.idCard = idCard;
		this.nativePlace = nativePlace;
		this.nation = nation;
		this.political = political;
		this.className = className;
		this.major = major;
		this.majorFiled = majorFiled;
		this.college = college;
		this.degreeType = degreeType;
		this.image = image;
		this.credit = credit;
		this.birthDay = birthDay;
		this.entranceDay = entranceDay;
		this.graduDay = graduDay;
	}
	
	
}
