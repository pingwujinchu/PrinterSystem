package com.xjtu.thread;

import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xjtu.bean.User;
import com.xjtu.util.DBUtil;
import com.xjtu.util.EUUserUtil;

public class LoginThread implements Runnable{
    public boolean isTerm = false;
    private String username;
    private String password;
    private User user;
    
    
	public LoginThread(String username, String password) {
		super();
		this.isTerm = isTerm;
		this.username = username;
		this.password = password;
		this.user = user;
	}
	
	

	public LoginThread() {
		super();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
//		String table = "stuinfo";
//		Map condition = new HashMap();
//		condition.put("id", username);
//		Connection conn = DBUtil.getConnection();
//		String name = null,id = null,sex = null,idCard = null,nativeplace = null,nation = null,political = null,className = null,major = null,majorFiled = null,college = null,degreeType = null;
//		double credit = 0,totalCredit = 0,curCredit = 0;
//		Date birthDay = null,entranceDay = null,graduDay = null;
//		List resultList = DBUtil.quaryAllWithCondition(conn, table, condition);
//		
//		if(resultList.size()>0){
//		List l = (List) resultList.get(0);
//		for(int i = 0 ; i < l.size() ; i++){
//			List temList = (List) l.get(i);
//			if(temList.get(0).toString().equals("name")){
//				name = (String) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("sex")){
//				sex = temList.get(1).toString();
//			}
//			if(temList.get(0).toString().equals("nativeplace")){
//				nativeplace = (String) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("nation")){
//				nation = (String) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("political")){
//				political = (String) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("className")){
//				className = (String) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("major")){
//				major = temList.get(1).toString();
//			}
//			if(temList.get(0).toString().equals("majorFiled")){
//				majorFiled = temList.get(1).toString();
//			}
//			if(temList.get(0).toString().equals("college")){
//				college = temList.get(1).toString();
//			}
//			if(temList.get(0).toString().equals("degreeType")){
//				degreeType = temList.get(1).toString();
//			}
//			if(temList.get(0).toString().equals("credit")){
//				credit = (double) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("totalCredit")){
//				totalCredit = (double) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("curCredit")){
//				curCredit = (double) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("birthDay")){
//				birthDay = (Date) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("entranceDay")){
//				entranceDay = (Date) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("graduDay")){
//				graduDay = (Date) temList.get(1);
//			}
//			if(temList.get(0).toString().equals("idCard")){
//				idCard = temList.get(1).toString();
//			}
//			if(temList.get(0).toString().equals("id")){
//				id = temList.get(1).toString();
//			}
//		}
//		user = new User(name, id, sex, idCard, nativeplace, nation, political, className, major, majorFiled, college, degreeType, null, credit, birthDay, entranceDay, graduDay);
//		}
		user = this.getUserByID(username);
		isTerm = true;
	}
	
	private User getUserByID(String studentID){
		User user = new User();
		try {
			user = EUUserUtil.getUserById(studentID);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUser(){
		return this.user;
	}

}
