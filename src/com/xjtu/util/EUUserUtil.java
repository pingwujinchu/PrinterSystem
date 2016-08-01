package com.xjtu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.xjtu.bean.Course;
import com.xjtu.bean.Record;
import com.xjtu.bean.User;

public class EUUserUtil {

	public static final int YEAR = 1 - 1;
	public static final int SEME = 2 - 1;
	public static final int NAME = 6 - 1;
	public static final int GRADE = 8 - 1;
	public static final int DESERVEDCREDIT = 9 - 1;
	public static final int TAKENCREDIT = 12 - 1;
	public static final int POINT = 10 - 1;

	public static User getUserById(String stu_id) throws ParseException {
		User user = new User();
		Connection con = EUDBUtil.getConnection();
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("stu_id", "'" + stu_id + "'");
		// --------------------------基本信息:表头----------------------------
		List<Object> userInfo = EUDBUtil.QueryInfo(con, condition);
		int i = 0;
		user.setUserName((String) userInfo.get(i++));
		user.setStudentId((String) userInfo.get(i++));
		user.setSex((String) userInfo.get(i++));
		user.setIdCard((String) userInfo.get(i++));
		user.setNativePlace(((String) userInfo.get(i++)) == null ? "" : (String) userInfo.get(i - 1));
		user.setNation((String) userInfo.get(i++));
		user.setPolitical((String) userInfo.get(i++));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		user.setBirthDay(sdf.parse((String) userInfo.get(i++)));
		user.setClassName((String) userInfo.get(i++));
		user.setEntranceDay(sdf.parse(userInfo.get(i++).toString() + "-09-01"));
		user.setGraduDay(null);
		user.setMajor((String)userInfo.get(i++));
		user.setMajorFiled(userInfo.get(i++).toString());
		user.setCollege((String) userInfo.get(i++));
		// --------------------------基本信息:表尾----------------------------
		user.setCredit(((BigDecimal)userInfo.get(i++)).doubleValue());
//		try {
//			Image image = Image.getInstance(EUDBUtil.QueryPicture(EUDBUtil.getOracleConnection(), user.getStudentId()));
//			user.setImage(image);
//		} catch (BadElementException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return user;
	}

	private static boolean isSemeSame(String s1, String s2) {
		if (s1.equals("99") || s2.equals("99"))
			return true;
		else if (s1.equals("10") || s1.equals("21"))
			if (s2.equals("10") || s2.equals("21"))
				return true;
			else
				return false;
		else if (s2.equals("20") || s2.equals("22"))
			return true;
		else
			return false;
	}
	
	public static List<Record> getGrades(String stu_id){
		Connection con = EUDBUtil.getConnection();
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("stu_id", "'" + stu_id + "'");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		List<List<Object>> grades = EUDBUtil.QueryGrades(con, condition);
		List<Record> records = new ArrayList<Record>();
		Record curRecord = new Record();
		int year = 0;
		String seme = "";
		for (int i = 0; i < grades.size(); i++) {
			List<Object> curGrade = grades.get(i);
			int classYear = (int)curGrade.get(YEAR);
			String classSeme = (String)curGrade.get(SEME);
			if (classYear == year && isSemeSame(classSeme, seme)) {
				//若学期不变，则将课程信息添加到record里
				Course course = new Course();
				Object deservedCredit = curGrade.get(DESERVEDCREDIT);
				Object point = curGrade.get(POINT);
				Object takenCredit = curGrade.get(TAKENCREDIT);
				if(deservedCredit != null){
					course.setDeservedCredit(((BigDecimal)deservedCredit).doubleValue());
					curRecord.deservedCreaditIncrease(((BigDecimal)deservedCredit).doubleValue());
				}else{
					course.setDeservedCredit(0);
				}
				course.setGrade(Math.round(Double.valueOf((String)curGrade.get(GRADE))));
				course.setName((String)curGrade.get(NAME));
				if(point != null){
					course.setPoint(((BigDecimal)point).doubleValue());
					curRecord.deservedPointIncrease(((BigDecimal)point).doubleValue());
				}else
					course.setPoint(0);
				if(takenCredit != null){
					course.setCredit(((BigDecimal) takenCredit).doubleValue());
					curRecord.takenCreditIncrease(((BigDecimal) takenCredit).doubleValue());
				}else
					course.setCredit(0);
				course.setAttr("");
				try {
					course.setTime(sdf.parse((seme.equals("10")? year + "-10-01" : (year + 1) + "-06-01")));
					
					Date d = new Date();
					if(seme.equals("10")){
					    d.setYear(year-1900);
					    d.setMonth(10);
					    d.setDate(1);
					}else{
						d.setYear(year+1-1900);
						d.setMonth(6);
						d.setDate(1);
					}
					course.setTime(d);
	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("日期转换出错！");
					e.printStackTrace();
				}
				course.setAttr((String)curGrade.get(curGrade.size() - 1));
				curRecord.addCourse(course);
			} else {
//				System.out.println("year: " + year + " ------ classYear: " + classYear 
//						+ "----- seme: " + seme + "-------classSeme: " + classSeme + "---" + i);
				if(curRecord.numClasses() != 0){
					records.add(curRecord);
					curRecord = new Record();
				}
				String semester = classYear + "学年 " 
				+ (classSeme.equals("10") ? "第一学期(" + classYear + "年9月至" + (classYear + 1) + "年1月)"
						: "第二学期(" + (classYear + 1) + "年3月至" + (classYear + 1) + "年7月)");
				curRecord.setSemester(semester);
				year = classYear;
				seme = classSeme;
				i--;
			}
		}
		records.add(curRecord);
		return records;
	}

	public static void getImage(String stu_id) {
		Connection con = EUDBUtil.getOracleConnection();
		byte[] bs = EUDBUtil.QueryPicture(con, stu_id);
		File file = new File("\\" + stu_id + ".png");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			if (bs.length > 0) {
				fos.write(bs, 0, bs.length);
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
