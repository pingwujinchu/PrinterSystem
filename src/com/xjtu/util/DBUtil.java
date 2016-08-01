package com.xjtu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 *  @fileName :   application.DBUtil.java
 *
 *	@version : 1.0
 *
 * 	@see { }
 *
 *	@author   :   fan
 *
 *	@since : JDK1.4
 *  
 *  Create date  : 2016��6��7�� ����9:51:55
 *  Last modified time :
 *	
 * 	Test or not : No
 *	Check or not: No
 *
 * 	The modifier :
 *	The checker	: 
 *	 
 *  @describe :
 *  ALL RIGHTS RESERVED,COPYRIGHT(C) FCH LIMITED 2016
*/

public class DBUtil {
	public static Connection getConnection(String url,String user,String password){
		String driver = "com.mysql.jdbc.Driver";
		// URLָ��Ҫ���ʵ����ݿ���scutcs
		// MySQL����ʱ���û���
		// Java����MySQL����ʱ������
		Connection conn = null;
		try {
		// ������������
		Class.forName(driver);
		// �������ݿ�
		conn = DriverManager.getConnection(url, user, password);
//		if(!conn.isClosed())
//		System.out.println("Succeeded connecting to the Database!");
		// statement����ִ��SQL���
//		Statement statement = conn.createStatement();
//		// Ҫִ�е�SQL���
//		String sql = "select * from student";
	}catch(Exception e){
		e.printStackTrace();
	}
		return conn;
}
	
	
	public static Connection getConnection(){
		
		Properties pro = new Properties();
		File file= new File("webapps/PrinterSystem/config/config.properties");
        Properties props=new Properties(); 
        try{
        InputStream is = new FileInputStream(file);
        props.load(is);
        is.close();
        }catch(Exception e){
           e.printStackTrace();
        }
        String url =null;
        String user = null;
        String password = null;
        String driver = "com.mysql.jdbc.Driver";
        if(!props.isEmpty()){
        	url = props.getProperty("url");
        	user = props.getProperty("user");
        	password = props.getProperty("password");
        	driver = props.getProperty("driver");
//        	location = props.getProperty("location");
        }

		// URLָ��Ҫ���ʵ����ݿ���scutcs
		// MySQL����ʱ���û���
		// Java����MySQL����ʱ������
		Connection conn = null;
		try {
		// ������������
		Class.forName(driver);
		// �������ݿ�
		conn = DriverManager.getConnection(url, user, password);
//		if(!conn.isClosed())
//		System.out.println("Succeeded connecting to the Database!");
		// statement����ִ��SQL���
//		Statement statement = conn.createStatement();
//		// Ҫִ�е�SQL���
//		String sql = "select * from student";
	}catch(Exception e){
		e.printStackTrace();
	}
		return conn;
	}
	
	
	static Statement getStatement(Connection conn){
		Statement statement = null;
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}
	
	static void createTable(Connection conn,String tableName,Map<String,String> colmn){
		String sql = "create table user(";
		Set keyset = colmn.keySet();
		Iterator it = keyset.iterator();
		while(it.hasNext()){
			String col = (String) it.next();
			String type = colmn.get(col);
			sql+=col+" "+type+",";
		}
		sql+="primary key(id));";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(0, sql);
			conn.setAutoCommit(false);
			preparedStatement.execute();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insert(Connection conn,String tableName,Map value){
		String sql = "insert into "+tableName+"(";
		Set keyset = value.keySet();
		Iterator ir = keyset.iterator();
		String itemValue = "values(";
		while(ir.hasNext()){
			String item = (String) ir.next();
			sql+=item+",";
			itemValue+=(String)value.get(item)+",";
		}
		sql=sql.substring(0, sql.length()-1)+")";
		itemValue = itemValue.substring(0, itemValue.length()-1)+")";
		sql+=itemValue;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(0, sql);
			conn.setAutoCommit(false);
			preparedStatement.execute();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List quaryAll(Connection conn,String table){
		String sql = "";
		List result = new ArrayList();
	    sql = "select * from "+table;

	    try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(0, sql);
			conn.setAutoCommit(false);
			ResultSet rs = preparedStatement.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			conn.commit();
			int columns = rsd.getColumnCount();
			   while(rs.next())
			   {
				  
			    for(int i=1;i<=columns;i++)
			    {
			    	List l = new ArrayList();
			    	l.add(rsd.getColumnName(i));
			    	l.add(rs.getObject(i));
			        result.add(l);
			    }
			   }
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    System.out.println(result.size());
		return result;
	}
	
	
	public static List quaryAllWithCondition(Connection conn,String table,Map<String,String> condition){
		String sql = "";
		List result = new ArrayList();
	    sql = "select * from "+table;
	    Set key = condition.keySet();
        Iterator it = key.iterator();
        if(condition!=null&&condition.size()>0){
        	sql+=" where ";
        }
        while(it.hasNext()){
        	String keyValue = (String) it.next();
        	String value = condition.get(keyValue);
        	sql+=keyValue+" = "+value; 
        }
	    try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(0, sql);
			conn.setAutoCommit(false);
			ResultSet rs = preparedStatement.executeQuery();
			ResultSetMetaData rsd = rs.getMetaData();
			conn.commit();
			int columns = rsd.getColumnCount();
			   while(rs.next())
			   {
				  
				List l = new ArrayList();
			    for(int i=1;i<=columns;i++)
			    {
			    	List uinfo = new ArrayList();
			    	uinfo.add(rsd.getColumnName(i));
			    	uinfo.add(rs.getObject(i));
			    	l.add(uinfo);
			    }
			    result.add(l);
			   }
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    System.out.println(result.size());
		return result;
	}
	/**
	 * @param conn
	 * @param table
	 * 计算
	 */
	static void deleteAll(Connection conn,String table){
		String sql = "delete from "+table;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(0, sql);
			conn.setAutoCommit(false);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteWithCondition(Connection conn,String table,Map<String,String> condition){
		String sql = "delete from "+table;
		if(condition!=null&&condition.size()>0){
			sql+=" where ";
			Set keySet = condition.keySet();
			Iterator it = keySet.iterator();
			while(it.hasNext()){
				String key = (String) it.next();
				String value = condition.get(key);
				sql+=" "+key+" = "+value+" and";
			}
			sql = sql.substring(0, sql.length()-4);
		}
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(0, sql);
			conn.setAutoCommit(false);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void changValue(Connection conn,String table,Map<String,String> newValue,Map<String ,String>condition){
		String sql = "update "+table;
		if(newValue!=null&&newValue.size()>0){
			sql+=" set";
			Set keySet = newValue.keySet();
			Iterator it = keySet.iterator();
			while(it.hasNext()){
				String key = (String) it.next();
				String value = newValue.get(key);
				sql+=" "+key+" = "+value+" ,";
			}
			sql = sql.substring(0, sql.length()-1);
		}
		if(condition!=null&&condition.size()>0){
			sql+=" where ";
			Set keySet = condition.keySet();
			Iterator it = keySet.iterator();
			while(it.hasNext()){
				String key = (String) it.next();
				String value = condition.get(key);
				sql+=" "+key+" = "+value+" and";
			}
			sql = sql.substring(0, sql.length()-4);
		}
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
//			preparedStatement.setString(0, sql);
			conn.setAutoCommit(false);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
