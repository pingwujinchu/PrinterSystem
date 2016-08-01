package com.xjtu.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xjtu.user.User;

public class UserUtil {
     public static User getUserByID(String ID){
    	 User user = new User();
    	 Connection conn = DBUtil.getConnection();
    	 Map condition = new HashMap();
    	 condition.put("id", "'"+ID+"'");
    	 List userList =  DBUtil.quaryAllWithCondition(conn, "stuinfo", condition);
    	 List userInfo = (List) userList.get(0);
    	 String name = null;
    	 String sex;
    	 String sid = null;
    	 String password;
    	 for(int i = 0 ; i < userInfo.size() ; i++){
    		 String colInfo = ((List)userInfo.get(i)).get(0).toString();
    		 if(colInfo.equals("id")){
    			 String id = ((List)userInfo.get(i)).get(1).toString();
    		 }else if(colInfo.equals("name")){
    			 name = ((List)userInfo.get(i)).get(1).toString();
    		 }else if(colInfo.equals("sex")){
    			 sex = ((List)userInfo.get(i)).get(1).toString();
    		 }else if(colInfo.equals("sid")){
    			 sid = ((List)userInfo.get(i)).get(1).toString();
    		 }
    	 }
    	 user.setID(ID);
    	 user.setName(name);
    	 user.setPassword(sid.substring(sid.length()-7,sid.length()));
    	 return user;
     }
     
     public static List getUserGrade(String ID){
    	 List gradeList = null;
    	 Map condition = new HashMap();
    	 condition.put("id", "'"+ID+"'");
    	 gradeList = DBUtil.quaryAllWithCondition(DBUtil.getConnection(), "clainfo", condition);
    	 return gradeList;
     }
     
     public static void main(String []args){
    	 User user = UserUtil.getUserByID("15610401150916");
    	 System.out.println("ID:"+user.getID()+"\n"+"name:"+user.getName()+"\n"+"password:"+user.getPassword());
    	 List l = UserUtil.getUserGrade("15610401150916");
    	 for(int i = 0 ; i < l.size() ; i++){
    		 for(int j = 0 ; j < ((List)l.get(i)).size() ; j++){
    			 for(int k = 0 ; k < ((List)((List)l.get(i)).get(j)).size() ; k++){
    				 System.out.println(((List)((List)l.get(i)).get(j)).get(k)+":");
    			 }
    		 }
    	 }
     }
}
