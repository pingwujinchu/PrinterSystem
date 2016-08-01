package com.xjtu.test;

import java.util.List;

import com.xjtu.util.DBUtil;

public class StateTest {
	public static void main(String[]args){
		List l =DBUtil.quaryAllWithCondition(DBUtil.getConnection(), "printjob",null);
		
	}
}
