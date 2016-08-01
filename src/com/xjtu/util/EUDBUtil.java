package com.xjtu.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.ImageIcon;

public class EUDBUtil {

	public static Connection getConnection(String url, String user, String password) {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("找不到指定驱动类：com.microsoft.sqlserver.jdbc.SQLServerDriver。");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("建立数据库连接失败。");
			e.printStackTrace();
		}
		return con;
	}

	public static Connection getConnection() {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://10.51.0.6:1433;DatabaseName=urpdb_20160510";
		String user = "printer2016";
		String password = "pnt20160726";
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("鎵句笉鍒版寚瀹氶┍鍔ㄧ被锛歝om.microsoft.sqlserver.jdbc.SQLServerDriver銆�");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("寤虹珛鏁版嵁搴撹繛鎺ュけ璐ャ��");
			e.printStackTrace();
		}
		return con;
	}

	public static Connection getOracleConnection() {
		Connection conn = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@10.80.0.203:1521:ORCL";// 鏁版嵁搴撹繛鎺ワ紝oracle浠ｈ〃閾炬帴鐨勬槸oracle鏁版嵁搴擄紱thin:@MyDbComputerNameOrIP浠ｈ〃鐨勬槸鏁版嵁搴撴墍鍦ㄧ殑IP鍦板潃锛堝彲浠ヤ繚鐣檛hin:锛夛紱1521浠ｈ〃閾炬帴鏁版嵁搴撶殑绔彛鍙凤紱ORCL浠ｈ〃鐨勬槸鏁版嵁搴撳悕绉�
		String UserName = "ccense";// 鏁版嵁搴撶敤鎴风櫥闄嗗悕 ( 涔熸湁璇存槸 schema 鍚嶅瓧鐨� )
		String Password = "ccense";// 瀵嗙爜
		try {
			// 鍔犲叆oracle鐨勯┍鍔紝鈥溾�濋噷闈㈡槸椹卞姩鐨勮矾寰�
			Class.forName(driver);
			conn = DriverManager.getConnection(url, UserName, Password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("找不到类对象：oracle.jdbc.driver.OracleDriver");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("连接数据库错误！");
			e.printStackTrace();
		}
		return conn;
	}

	public static List<List<Object>> QueryGrades(Connection con, Map<String, String> condition) {
		List<List<Object>> result = new ArrayList<List<Object>>();
		String sql = "select distinct ca.clop_openyear as '开课学年', ca.clop_opensemester as '开课学期', ca.open_coll_id as '开课单位代码', ca.open_coll_name as '开课单位名称', ca.curs_id as '科目代码', ca.clop_name as '科目名称',ca.class_score as '成绩等级', ca.class_orgiscore as '成绩', ca.clop_credit as '学分', score_correspond.Score_Points as '绩点', case when ca.score_passstatus='P' then 1 else 0 end as '是否通过', case when ca.score_passstatus='P' then ca.clop_credit else 0 end as '获得学分', case when ca.clop_mainclass='50' then 1 else 0 end as '是否选修课', case when ca.clop_semeclass='21' then N'学年课(第一学期)' when ca.clop_semeclass='22' then N'学年课(第二学期)' when ca.clop_semeclass='10' or ca.clop_semeclass='20' or ca.clop_semeclass='23' then N'学期课' else 'UNKNOWN' end as '课程类型', attr.curs_DispName as '课程属性' from ( select ca.stud_id, ca.class_id, c.curs_id, c.clop_name, ca.class_orgiscore, row_number() over( partition by ca.stud_id,c.curs_id,c.clop_semeclass order by case when ca.score_int is not null then ca.score_int when ca.score_int is null and ca.class_orgiscore=sc.score_grading then sc.score_uscore else 0 end desc ) as rn, sc.score_passstatus, ca.class_score, c.clop_mainclass, c.clop_semeclass, c.clop_credit, s.coll_semegrade, sc.syst_id, c.clop_openyear, c.clop_opensemester, c.coll_id as open_coll_id, c1.coll_name as open_coll_name, s.coll_id as student_coll_id, c2.coll_name as student_coll_name, s.stud_name from ( select *, case when isnumeric(class_orgiscore) = 1 then floor(cast(class_orgiscore as float)) else null end as score_int from classscore_all where class_orgiscore is not null ) ca join clop c on ca.class_id=c.clas_id left join score_correspond sc on sc.coll_semegrade=c.clop_openyear and ( (ca.score_int is not null and ca.score_int between sc.score_dscore and sc.score_uscore) or (ca.score_int is null and ca.class_orgiscore=sc.score_grading) ) and sc.score_type=isnull(c.clop_scoretype,'10') join sdrg s on s.stud_id=ca.stud_id and s.seme_semeyear=c.clop_openyear and s.seme_semester=c.clop_opensemester left join coll c1 on c1.coll_id=c.coll_id and c1.coll_semegrade=c.clop_openyear left join coll c2 on c2.coll_id=s.coll_id and c2.coll_semegrade=s.coll_semegrade ) ca left join score_correspond on score_correspond.Score_DScore = cast(cast(ca.class_orgiscore as float) as int) and score_correspond.Coll_SemeGrade = ca.coll_semegrade and score_correspond.syst_id = ca.syst_id left join ( SELECT distinct  dbo.ClassScore_All.Class_Id as class_id, dbo.ClassScore_All.Stud_Id as stud_id, dbo.Curs.Curs_Name as curs_name, dbo.Para.Para_DispName as curs_DispName, dbo.ClassScore_All.Class_Credit, dbo.ClassScore_All.Class_Score FROM dbo.ClassScore_All INNER JOIN dbo.Curs ON dbo.ClassScore_All.Csno_Id = dbo.Curs.Curs_Id INNER JOIN dbo.Para ON dbo.ClassScore_All.Class_OpCode = dbo.Para.Para_PhsiName WHERE (dbo.Para.Para_Code = 'p_opcodescore')) attr on attr.stud_id = ca.stud_id and attr.class_id = ca.class_id where ca.rn<=1 and ca.Stud_Id="
				+ condition.get("stu_id") + " order by ca.clop_openyear, ca.clop_opensemester ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			con.setAutoCommit(false);
			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSetMetaData resultSetData = resultSet.getMetaData();
			con.commit();
			int columns = resultSetData.getColumnCount();
			while (resultSet.next()) {
				List<Object> grade = new ArrayList<Object>();
				for (int i = 1; i <= columns; i++) {
					grade.add(resultSet.getObject(i));
				}
				result.add(grade);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库操作出错！");
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("数据库roolback操作错误！");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static List<Object> QueryInfo(Connection con, Map<String, String> condition) {
		List<Object> first = new ArrayList<Object>();
		String sql = "select distinct sdrg.Stud_Name as '姓名', sdrg.Stud_ID as '学号', case when prsn.Prsn_Sex = 'F' then '女' else '男' end as '性别', sdrg.ID_Code as '身份证号', prsn.Prsn_HomeTown as '籍贯', data_ethnicity.name as '民族', Stud.STUD_PARTYNAME as '政治面貌', prsn.Prsn_BirthDate as '出生日期', sdrg.Cocs_Name as '班级', sdrg.Stud_SchlInYear as '入学年', sdrg.Coll_ID, sdrg.Coll_SemeGrade from ((sdrg Inner Join prsn on sdrg.ID_Code = prsn.ID_Code) inner join Stud on sdrg.Stud_ID = Stud.stud_id) left join data_ethnicity on prsn.Prsn_Ethnicity = data_ethnicity.sequence where sdrg.Stud_ID = "
				+ condition.get("stu_id");
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			con.setAutoCommit(false);
			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSetMetaData resultSetData = resultSet.getMetaData();
			con.commit();
			int columns = resultSetData.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= columns; i++) {
					first.add(resultSet.getObject(i));
				}
			}
			String coll_ID = "'" + first.get(first.size() - 2) + "'";
			String coll_SemeGrade = "'" + first.get(first.size() - 1) + "'";
			first.remove(first.size() - 1);
			first.remove(first.size() - 1);
			String collogeName, orientName, collogeId;
			sql = "select SDept_CName,SDept_Level,SDept_UpSDeptId from Schl_SDepartment where SDept_Id= " + coll_ID
					+ " and Coll_SemeGrade= " + coll_SemeGrade;
			preparedStatement = con.prepareStatement(sql);
			con.setAutoCommit(false);
			resultSet = preparedStatement.executeQuery();
			resultSetData = resultSet.getMetaData();
			con.commit();
			resultSet.next();
			String temp = resultSet.getObject(1).toString();
			String level = resultSet.getObject(2).toString();
			if (level.equals("50")) {
				first.add(temp);
				first.add("");
			} else if (level.equals("60")) {
				orientName = temp;
				// resultSet.next();
				collogeId = "'" + resultSet.getObject(3) + "'";
				sql = "select SDept_CName from Schl_SDepartment where SDept_Id= " + collogeId + " and Coll_SemeGrade= "
						+ coll_SemeGrade;
				preparedStatement = con.prepareStatement(sql);
				con.setAutoCommit(false);
				resultSet = preparedStatement.executeQuery();
				resultSetData = resultSet.getMetaData();
				con.commit();
				resultSet.next();
				first.add(resultSet.getObject(1));
				first.add(orientName);
			} else {
				first.add("");
				first.add("");
			}
			sql = "select SDept_CName,Coll_SemeGrade from Schl_SDepartment where SDept_Id=" + coll_ID.substring(0, 5)
					+ "'";
			preparedStatement = con.prepareStatement(sql);
			con.setAutoCommit(false);
			resultSet = preparedStatement.executeQuery();
			resultSetData = resultSet.getMetaData();
			con.commit();
			resultSet.next();
			first.add(resultSet.getObject(1));
			sql = "select sum(coll_cred) from coll_anno_credtype where Coll_ID="
					+ coll_ID + " and Coll_SemeGrade= " + coll_SemeGrade;
			preparedStatement = con.prepareStatement(sql);
			con.setAutoCommit(false);
			resultSet = preparedStatement.executeQuery();
			con.commit();
			resultSet.next();
			first.add(resultSet.getObject(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库操作出错！");
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("数据库roolback操作错误！");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return first;
	}
	
	public static byte[] QueryPicture(Connection con, String stu_id) {
		// TODO Auto-generated method stub
		String sql = "select * from BASE_CUSTOMERS_PHOTO where OUTID='" + stu_id + "'";//78090503    where OUTID='" + 78090503 + "'
		ResultSet rs = null;
		PreparedStatement stmt = null;
		OutputStream os = null;
		byte[] bs = null;
		try{
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
			Blob ob = null;
			bs = ((byte[])rs.getObject(3));
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("数据库错误！");
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resizeImage(bs,90,99);
	}
	
	private static byte[] resizeImage(byte[] in,int width,int height)  
	{  
	    try  
	    {  
//	        Image inImage=Toolkit.getDefaultToolkit().createImage(in);  
//	        ImageIcon inImageIcon = new ImageIcon(in);   
//	  
//	        int imh = inImageIcon.getIconHeight();  
//	        int imw = inImageIcon.getIconWidth();  
//	        double scale;  
//	        if( imh <= maxDim && imw <= maxDim )  
//	            scale = 1;  
//	        else if( imh > imw )  
//	           scale = (double) maxDim / (double) imh;  
//	        else  
//	           scale = (double) maxDim / (double) imw;   
//	  
//	        int scaledW = (int) (scale * imw);  
//	        int scaledH = (int) (scale * imh);   
//	  
//	        Image img = inImage.getScaledInstance(scaledW, scaledH, Image.SCALE_FAST);  
//	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
//	        JimiRasterImage raster = Jimi.createRasterImage(img.getSource());  
//	        // --java.io.ByteArrayOutputStream  
//	        Jimi.putImage("image/jpeg", raster, outStream);  
//	        outStream.flush();  
//	        outStream.close();  
	        ByteArrayInputStream ins = new ByteArrayInputStream(in); 
	        BufferedImage image = ImageIO.read(ins); 
	        BufferedImage resultImage = zoom(image, width, height);
//	            BufferedImage bImage= new BufferedImage(scaledW,scaledH,BufferedImage.TYPE_INT_ARGB); 
//	            Graphics bg= bImage.getGraphics(); 
//	            bg.drawImage(img,0,0,null); 
//	            
//	            bg.dispose(); 
	            ByteArrayOutputStream out= new ByteArrayOutputStream(); 
	            try 
	            { 
	                ImageIO.write(resultImage,"png",out); 
	            } 
	            catch(IOException e) 
	            { 
	                e.printStackTrace(); 
	            } 
	            return out.toByteArray(); 
	    }  
	    catch(Exception ex)  
	    {  
	        ex.printStackTrace();  
	        return null;  
	    }  
	    
	    
	}  
	
	private static BufferedImage zoom(BufferedImage bitmap, int width, int height){
        if(bitmap==null){
            return null;
        }
        if(width<1||height<1){
            return null;
        }
        float oldWidth=bitmap.getWidth(null);
        float oldHeight=bitmap.getHeight(null);
        float xRatio=oldWidth/width;
        float yRatio=oldHeight/height;
        
        BufferedImage result=new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        int x=0,y=0;
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                x=(int)(i*xRatio);
                if(x>oldWidth){
                    x=(int)oldWidth;
                }
                y=(int)(j*yRatio);
                if(y>oldHeight){
                    y=(int)oldHeight;
                }
                result.setRGB(i, j, bitmap.getRGB(x, y));
            }
        }
        return result;
    }


	public static void main(String []args) throws IOException{
		byte[] bs = EUDBUtil.QueryPicture(EUDBUtil.getOracleConnection(), "15610401150916");
	    FileImageOutputStream imageOutput = new FileImageOutputStream(new File("image.jpg"));
	    imageOutput.write(bs, 0, bs.length);
	    imageOutput.close();
	}
}
