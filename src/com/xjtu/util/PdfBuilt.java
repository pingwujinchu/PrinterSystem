package com.xjtu.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.xjtu.bean.Course;
import com.xjtu.bean.Record;
import com.xjtu.bean.User;

public class PdfBuilt {
	// 设置表格中每一列的宽
	private static float[] WIDTHS = { 34.0152f, 34.0152f, 39.6844f, 4.2519f, 26.9287f, 42.519f, 5.6692f, 8.5038f,
			29.7633f, 4.2519f, 14.173f, 21.2595f, 2.8346f, 5.6692f, 8.5038f, 8.5038f, 9.9211f, 19.8422f, 12.7557f,
			5.6692f, 7.0865f, 17.0076f, 53.8574f, 22.6768f, 31.1806f, 31.1805f, 34.346f };
	//
	private static float[] BASEWIDTH = { 34.0152f, 34.0152f, 39.6844f, 4.2519f, 26.9287f, 42.519f, 5.6692f, 8.5038f,
			29.7633f, 4.2519f, 14.173f, 21.2595f, 2.8346f, 5.6692f, 8.5038f, 8.5038f, 9.9211f, 19.8422f, 12.7557f,
			5.6692f, 7.0865f, 17.0076f, 53.8574f, 22.6768f };
	// 表格的宽度
	private static final float TOTALWIDTH = 536.0701f;
	//
	private static final float PICWIDTH = 96.7071f;
	// 表格上面空白宽度
	private static final float SPACE_TOP = 10f;
	// 标题的行间距
	private static final float LINESPACING_TITLE = 1f;
	// 标题字体的大小
	private static final int SIZETITAL = 15;
	// 其余字体的大小
	private static final int SIZELEFT = 9;
	// 成绩的最大行数
	private static final int MAXROWS = 34;
	// 成绩部分的高度
	private static final float GRADE_HEIGHT = 14f;
	// 已获学分的高度
	private static final float DEGREE_HEIGHT = 16f;
	//
	private static final int NUMSPACE = 34;
	//
	private static final float NORMALHEIGHT = 20.0f;
	//
	private static final float AVARAGE_HEIGHT = 17f;
	// 时间模式化
	private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
	// 字体
	private static Font titleChinese = null;
	private static Font exFont = null;
	private static Map left;
	private static Map middle;
	private static Map right;

	static {
		left = new HashMap();
		left.put("height", GRADE_HEIGHT);
		left.put("size", 12);
		left.put("top", false);
		left.put("down", false);

		middle = new HashMap();
		middle.put("height", GRADE_HEIGHT);
		middle.put("top", false);
		middle.put("down", false);

		right = new HashMap();
		right.put("height", GRADE_HEIGHT);
		right.put("size", 14);
		right.put("top", false);
		right.put("down", false);
	}

	public static void BuiltPdf(User stu, List<Record> record) throws DocumentException, FileNotFoundException {
		// A4纸大小 左、右、上、下,单位是像素
		Document document = new Document(PageSize.A4, 28, 28, 65, 43);
		/* 使用中文字体 */
		// 中文处理
		BaseFont bfChinese;
		try {
			if (titleChinese == null && exFont == null) {
				// bfChinese = BaseFont.createFont("font/simsun.ttf",
				// BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				// 模板抬头的字体

				bfChinese = BaseFont.createFont("webapps/PrinterSystem/font/simsun.ttf", BaseFont.IDENTITY_H,
						BaseFont.NOT_EMBEDDED);
				titleChinese = new Font(bfChinese, PdfBuilt.SIZETITAL, Font.COURIER);
				// 信息和数字的字体
				exFont = new Font(bfChinese, PdfBuilt.SIZELEFT, Font.COURIER);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("DocumentException occured in BuiltPdf!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException occured in BuiltPdf!");
			e.printStackTrace();
		}
		File file = new File("webapps/PrinterSystem/temp");
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		// 这里先试着生成文件
		PdfWriter.getInstance(document,
				new FileOutputStream("webapps/PrinterSystem/temp/" + stu.getStudentId() + ".pdf"));
		// WebContent//Record//new.pdf
		// 打开文档
		document.open();
		int curPage = 1;
		int totalPage = 1;
		Paragraph TITLE = new Paragraph("西安欧亚学院学生中文成绩单", titleChinese);
		TITLE.setAlignment(Element.ALIGN_CENTER);
		TITLE.setLeading(PdfBuilt.LINESPACING_TITLE);

		double[] numbers = { 0.0, 0.0, 0.0 };
		List<ArrayList<Map>> gradeInfo = builtGradeInfoMap(record, stu, numbers);
		System.out.println("修习学分: " + numbers[0] + "; 实得学分: " + numbers[1] + "; 学分绩点: " + numbers[2]);
		if (gradeInfo.size() > (MAXROWS * 2))
			totalPage = (int) Math.ceil((double) gradeInfo.size() / (double) (MAXROWS * 2));
		do {
			if (curPage > 1)
				document.newPage();
			// ------------开始写数据-------------------
			// 抬头
			// 居中设置
			// 设置行间距
			// 将设计好的抬头添加到文件中
			document.add(TITLE);
			// 开始设置表格
			// 根据列宽建立一个pdf表格
			// 设置表格上面空白宽度
			// 设置表格的宽度
			// 设置表格宽度为%100
			// 开始添加表信息-----------基本信息
			PdfPTable table = getTableWithHead(stu);
			PdfPTable column = new PdfPTable(PdfBuilt.WIDTHS);
			List<Map> columnInfo = builtColumnsMap();
			for (int i = 0; i < columnInfo.size(); i++)
				column.addCell(createCell(columnInfo.get(i)));
			PdfPCell columnCell = new PdfPCell(column);
			columnCell.setColspan(27);
			columnCell.setFixedHeight(NORMALHEIGHT);
			table.addCell(columnCell);
			// 开始添加表信息------------成绩信息
			// 分别记录相关数据，顺序为修习学分、实得学分、sum(绩点*学分)
			int index = 0 + (MAXROWS * 2) * (curPage - 1);
			for (; index < gradeInfo.size() && index < MAXROWS + ((MAXROWS * 2) * (curPage - 1)); index++) {
				addCells(table, gradeInfo.get(index));
				table.addCell(createCell(middle));
				if (index + MAXROWS < gradeInfo.size())
					addCells(table, gradeInfo.get(index + MAXROWS));
				else {
					table.addCell(createCell(right));
				}
			}
			for (; MAXROWS + ((MAXROWS * 2) * (curPage - 1)) > index; index++) {
				table.addCell(createCell(left));
				table.addCell(createCell(middle));
				table.addCell(createCell(right));
			}
			// 开始添加表信息------------尾信息
			List<Map> endInfo = builtEndInfoMap(stu, numbers);
			for (int i = 0; i < endInfo.size(); i++)
				table.addCell(createCell(endInfo.get(i)));
			// 将表格添加进文件
			document.add(table);
			// // 添加表格后信息
			Paragraph tail = getTail(curPage++, totalPage);
			document.add(tail);
		} while (curPage <= totalPage);
		document.close();
	}

	static Paragraph getTail(int curPage, int totalPage) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String dateNowStr = sdf.format(d);
		// // 100
		StringBuffer str1 = new StringBuffer();
		StringBuffer str2 = new StringBuffer();
		for (int i = 0; i < NUMSPACE; i++) {
			str1.append(" ");
		}
		Paragraph tail = new Paragraph("创表人：赵华" + str1.toString() + "第 " + curPage + " 页 / 共 " + totalPage + " 页 "
				+ str1.toString() + "创表日期：" + dateNowStr, exFont);
		tail.setSpacingBefore(0f);
		return tail;
	}

	public static PdfPTable getTableWithHead(User stu) {
		PdfPTable table = new PdfPTable(PdfBuilt.WIDTHS);
		table.setSpacingBefore(PdfBuilt.SPACE_TOP);
		table.setTotalWidth(PdfBuilt.TOTALWIDTH);
		table.setWidthPercentage(100);
		PdfPTable baseInfoTable = new PdfPTable(PdfBuilt.BASEWIDTH);
		List<Map> baseInfo = builtBaseInfoMap(stu);
		for (int i = 0; i < baseInfo.size(); i++)
			baseInfoTable.addCell(createCell(baseInfo.get(i)));
		PdfPCell baseInfoCell = new PdfPCell(baseInfoTable);
		baseInfoCell.setColspan(24);
		baseInfoCell.setFixedHeight(NORMALHEIGHT * 5);
		table.addCell(baseInfoCell);
		PdfPCell imageCell = null;
		Image img = stu.getImage();
		if (img != null)
			imageCell = new PdfPCell(img);
		else
			imageCell = new PdfPCell();
		imageCell.setColspan(3);
		imageCell.setBorderWidth(0.8f);
		imageCell.setFixedHeight(NORMALHEIGHT * 5);
		table.addCell(imageCell);
		return table;
	}

	private static void addCells(PdfPTable table, ArrayList<Map> curRow) {
		// TODO Auto-generated method stub
		for (int i = 0; i < curRow.size(); i++) {
			table.addCell(createCell(curRow.get(i)));
		}
	}

	private static List<ArrayList<Map>> builtGradeInfoMap(List<Record> records, User stu, double[] numbers) {
		// TODO Auto-generated method stub
		List<ArrayList<Map>> gradeInfo = new ArrayList<ArrayList<Map>>();
		int totalNum = 0;
		int curSize = 12;
		for (int i = 0; i < records.size(); i++) {
			Record record = records.get(i);
			double deservedPoint = 0;
			ArrayList<Map> curHang = new ArrayList<Map>();
			Map curMap = new HashMap();
			// 在record的第一行插入学期行
			if (((totalNum++ / MAXROWS) % 2) == 0)
				curSize = 12;
			else
				curSize = 14;
			curMap.put("content", record.getSemester());
			curMap.put("size", curSize);
			curMap.put("horizontalAlig", Element.ALIGN_CENTER);
			curMap.put("event", "down");
			curMap.put("top", true);
			curMap.put("height", GRADE_HEIGHT);
			curHang.add(curMap);
			gradeInfo.add(curHang);

			// 修习学分
			numbers[0] += record.getDeservedCredit();
			// 实得学分
			numbers[1] += record.getTakenCredit();

			List<Course> courses = record.getClasses();
			for (int j = 0; j < courses.size(); j++) {
				Course curCourse = courses.get(j);
				if (((totalNum++ / MAXROWS) % 2) == 0)
					gradeLeft(gradeInfo, curCourse);
				else
					gradeRight(gradeInfo, curCourse);
				numbers[2] += curCourse.getCredit() * curCourse.getPoint();
				deservedPoint += curCourse.getCredit() * curCourse.getPoint();
			}
			if (((totalNum++ / MAXROWS) % 2) == 0)
				curSize = 12;
			else
				curSize = 14;
			curHang = new ArrayList<Map>();
			curMap = new HashMap();
			curMap.put("height", GRADE_HEIGHT);
			curMap.put("size", curSize);
			curMap.put("top", false);
			curMap.put("down", false);
			curHang.add(curMap);
			gradeInfo.add(curHang);

			// 去掉最后加的一行空行
			gradeInfo.remove(gradeInfo.size() - 1);
			totalNum--;
			// 获得成绩的最后一行
			// curHang = gradeInfo.get(gradeInfo.size() - 1);
			// 将最后一行成绩的下部变成虚线
			// for (int k = 0; k < curHang.size(); k++) {
			// curMap = curHang.get(k);
			// curMap.put("event", true);
			// }
			curHang = new ArrayList<Map>();
			curMap = new HashMap();
			curMap.put("content", "修习学分/实得学分");
			curMap.put("height", AVARAGE_HEIGHT);
			curMap.put("horizontalAlig", Element.ALIGN_LEFT);
			if (((totalNum / MAXROWS) % 2) == 0)
				curMap.put("size", 8);
			else
				curMap.put("size", 12);
			curMap.put("top", false);
			curMap.put("down", false);
			curMap.put("right", false);
			curMap.put("event", "up");
			curHang.add(curMap);

			curMap = new HashMap();
			curMap.put("content", record.getDeservedCredit() + "/");
			if (((totalNum / MAXROWS) % 2) == 0)
				curMap.put("size", 2);
			else
				curMap.put("size", 1);
			curMap.put("top", false);
			curMap.put("height", AVARAGE_HEIGHT);
			curMap.put("down", false);
			curMap.put("left", false);
			curMap.put("right", false);
			curMap.put("event", "up");
			curHang.add(curMap);

			curMap = new HashMap();
			curMap.put("content", record.getTakenCredit());
			if (((totalNum++ / MAXROWS) % 2) == 0)
				curMap.put("size", 2);
			else
				curMap.put("size", 1);
			curMap.put("top", false);
			curMap.put("height", AVARAGE_HEIGHT);
			curMap.put("down", false);
			curMap.put("left", false);
			curMap.put("event", "up");
			curHang.add(curMap);

			gradeInfo.add(curHang);

			curHang = new ArrayList<Map>();
			curMap = new HashMap();
			curMap.put("content", "平均绩点");
			curMap.put("height", AVARAGE_HEIGHT);
			curMap.put("horizontalAlig", Element.ALIGN_LEFT);
			if (((totalNum / MAXROWS) % 2) == 0)
				curMap.put("size", 10);
			else
				curMap.put("size", 13);
			curMap.put("top", false);
			curMap.put("right", false);
			curHang.add(curMap);

			curMap = new HashMap();
			curMap.put("content", String.format("%.2f", deservedPoint / record.getDeservedCredit()));
			if (((totalNum++ / MAXROWS) % 2) == 0)
				curMap.put("size", 2);
			else
				curMap.put("size", 1);
			curMap.put("height", AVARAGE_HEIGHT);
			curMap.put("top", false);
			curMap.put("left", false);
			curHang.add(curMap);

			gradeInfo.add(curHang);
		}

		ArrayList<Map> curHang;
		Map curMap;
		if (gradeInfo.size() % 68 == 0) {
			ArrayList<Map> tempList = new ArrayList();
			tempList.add(right);
			// tempList.add(right);
			gradeInfo.add(gradeInfo.size() - 2, tempList);
			gradeInfo.add(gradeInfo.size() - 2, tempList);
			ArrayList<Map> lastRow = (ArrayList) gradeInfo.get(gradeInfo.size() - 2);
			for (int i = 0; i < lastRow.size(); i++) {
				Map leftMap = lastRow.get(i);
				switch (i) {
				case 0:
					leftMap.replace("size", leftMap.get("size"), 8);
					break;
				case 1:
					leftMap.replace("size", leftMap.get("size"), 2);
					break;
				case 2:
					leftMap.replace("size", leftMap.get("size"), 2);
					break;
				}
			}

			ArrayList<Map> last2Row = (ArrayList) gradeInfo.get(gradeInfo.size() - 1);
			for (int i = 0; i < last2Row.size(); i++) {
				Map leftMap = last2Row.get(i);
				switch (i) {
				case 0:
					leftMap.replace("size", leftMap.get("size"), 10);
					break;
				case 1:
					leftMap.replace("size", leftMap.get("size"), 2);
					break;

				}
			}
		} else if ((gradeInfo.size() - 1) % 68 == 0) {
			ArrayList<Map> tempList = new ArrayList();
			tempList.add(right);
			gradeInfo.add(gradeInfo.size() - 1, tempList);
		}
		curHang = new ArrayList<Map>();
		curMap = new HashMap();
		curMap.put("content", "以下空白");
		if (((totalNum++ / MAXROWS) % 2) == 0)
			curMap.put("size", 12);
		else
			curMap.put("size", 14);
		curMap.put("down", false);
		curMap.put("horizontalAlig", Element.ALIGN_LEFT);
		curHang.add(curMap);

		gradeInfo.add(curHang);

		return gradeInfo;
	}

	private static void gradeRight(List<ArrayList<Map>> gradeInfo, Course course) {
		// TODO Auto-generated method stub
		Map curMap;
		ArrayList<Map> curHang = new ArrayList<Map>();
		curMap = new HashMap();
		curMap.put("content", course.getAttr());
		curMap.put("horizontalAlig", Element.ALIGN_LEFT);
		curMap.put("height", GRADE_HEIGHT);
		curMap.put("top", false);
		curMap.put("down", false);
		curMap.put("right", false);
		curMap.put("size", 4);
		curHang.add(curMap);

		curMap = new HashMap();
		curMap.put("content", course.getName());
		curMap.put("height", GRADE_HEIGHT);
		curMap.put("horizontalAlig", Element.ALIGN_LEFT);
		curMap.put("top", false);
		curMap.put("down", false);
		curMap.put("left", false);
		curMap.put("right", false);
		curMap.put("size", 8);
		curHang.add(curMap);

		// 处理学分
		curMap = new HashMap();
		curMap.put("content", course.getCredit());
		curMap.put("height", GRADE_HEIGHT);
		curMap.put("top", false);
		curMap.put("down", false);
		curMap.put("left", false);
		curMap.put("right", false);
		curMap.put("size", 1);
		curHang.add(curMap);

		// 处理绩点
		curMap = new HashMap();
		curMap.put("content", Math.ceil(course.getGrade()));
		curMap.put("height", GRADE_HEIGHT);
		curMap.put("top", false);
		curMap.put("down", false);
		curMap.put("left", false);
		curMap.put("size", 1);
		curHang.add(curMap);

		gradeInfo.add(curHang);
	}

	private static void gradeLeft(List<ArrayList<Map>> gradeInfo, Course course) {
		Map curMap;
		ArrayList<Map> curHang = new ArrayList<Map>();
		curMap = new HashMap();
		curMap.put("content", course.getAttr());
		curMap.put("horizontalAlig", Element.ALIGN_LEFT);
		curMap.put("height", GRADE_HEIGHT);
		curMap.put("top", false);
		curMap.put("down", false);
		curMap.put("right", false);
		curHang.add(curMap);

		curMap = new HashMap();
		curMap.put("content", course.getName());
		curMap.put("height", GRADE_HEIGHT);
		curMap.put("horizontalAlig", Element.ALIGN_LEFT);
		curMap.put("top", false);
		curMap.put("down", false);
		curMap.put("left", false);
		curMap.put("right", false);
		curMap.put("size", 7);
		curHang.add(curMap);

		// 处理学分
		curMap = new HashMap();
		curMap.put("content", course.getCredit());
		curMap.put("height", GRADE_HEIGHT);
		curMap.put("top", false);
		curMap.put("down", false);
		curMap.put("left", false);
		curMap.put("right", false);
		curMap.put("size", 2);
		curHang.add(curMap);

		// 处理绩点
		curMap = new HashMap();
		curMap.put("content", Math.round(course.getGrade()) + "");
		curMap.put("height", GRADE_HEIGHT);
		curMap.put("top", false);
		curMap.put("down", false);
		curMap.put("left", false);
		curMap.put("size", 2);
		curHang.add(curMap);

		gradeInfo.add(curHang);
	}

	private static List<Map> builtEndInfoMap(User stu, double[] numbers) {
		// TODO Auto-generated method stub
		List<Map> endInfo = new ArrayList<Map>();
		Map cur = new HashMap();
		cur.put("content", "已获总学分数：");
		cur.put("height", DEGREE_HEIGHT);
		cur.put("size", 2);
		// cur.put("height", 17.0076f);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", numbers[1]);// 获取用户的总学分
		cur.put("height", DEGREE_HEIGHT);
		cur.put("horizontalAlig", Element.ALIGN_RIGHT);
		cur.put("size", 2);
		// cur.put("height", 17.0076f);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "平均学分绩点：");
		cur.put("height", DEGREE_HEIGHT);
		cur.put("size", 2);
		// cur.put("height", 17.0076f);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", String.format("%.2f", numbers[2] / numbers[0]));
		cur.put("horizontalAlig", Element.ALIGN_RIGHT);
		cur.put("height", DEGREE_HEIGHT);
		cur.put("size", 3);
		// cur.put("height", 17.0076f);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "方案要求学分:");
		cur.put("size", 7);
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("height", DEGREE_HEIGHT);
		// cur.put("height", 17.0076f);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getCredit());
		cur.put("horizontalAlig", Element.ALIGN_RIGHT);
		cur.put("height", DEGREE_HEIGHT);
		cur.put("size", 4);
		// cur.put("height", 17.0076f);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "教务主任签字：");
		cur.put("height", DEGREE_HEIGHT);
		cur.put("size", 3);
		// cur.put("height", 17.0076f);
		cur.put("down", false);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "公章");
		cur.put("height", DEGREE_HEIGHT);
		cur.put("horizontalAlig", Element.ALIGN_CENTER);
		cur.put("size", 4);
		// cur.put("height", 17.0076f);
		cur.put("down", false);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "获得学位");
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("height", DEGREE_HEIGHT);
		cur.put("size", 2);
		// cur.put("height", 17.0076f);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "");
		cur.put("height", DEGREE_HEIGHT);
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 18);
		// cur.put("height", 17.0076f);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("size", 3);
		// cur.put("height", 17.0076f);
		cur.put("down", false);
		cur.put("height", DEGREE_HEIGHT);
		cur.put("top", false);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("size", 4);
		cur.put("height", DEGREE_HEIGHT);
		// cur.put("height", 17.0076f);
		cur.put("down", false);
		cur.put("top", false);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("height", DEGREE_HEIGHT);
		cur.put("content", "备注");
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 2);
		cur.put("down", false);
		// cur.put("height", 17.0076f);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "");
		cur.put("height", DEGREE_HEIGHT);
		cur.put("size", 18);
		cur.put("down", false);
		// cur.put("height", 17.0076f);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("size", 3);
		// cur.put("height", 17.0076f);
		cur.put("down", false);

		cur.put("height", DEGREE_HEIGHT);
		cur.put("top", false);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("size", 4);
		// cur.put("height", 17.0076f);
		cur.put("down", false);
		cur.put("height", DEGREE_HEIGHT);
		cur.put("top", false);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "");
		cur.put("height", DEGREE_HEIGHT);
		cur.put("size", 2);
		// cur.put("height", 17.0076f);
		cur.put("top", false);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "");
		cur.put("height", DEGREE_HEIGHT);
		cur.put("size", 18);
		// cur.put("height", 17.0076f);
		cur.put("top", false);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("size", 3);
		cur.put("height", DEGREE_HEIGHT);
		// cur.put("height", 17.0076f);
		cur.put("top", false);
		endInfo.add(cur);

		cur = new HashMap();
		cur.put("size", 4);
		cur.put("height", DEGREE_HEIGHT);
		// cur.put("height", 17.0076f);
		cur.put("top", false);
		endInfo.add(cur);
		return endInfo;
	}

	private static List<Map> builtColumnsMap() {
		List<Map> baseInfo = new ArrayList<Map>();
		Map cur = new HashMap();

		cur = new HashMap();
		cur.put("content", "属性");
		cur.put("right", false);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "课程名称");
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("right", false);
		cur.put("left", false);
		cur.put("size", 7);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "学分");
		cur.put("right", false);
		cur.put("left", false);
		cur.put("size", 2);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "成绩");
		cur.put("left", false);
		cur.put("size", 2);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "");
		cur.put("down", false);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "属性");
		cur.put("size", 4);
		cur.put("right", false);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "课程名称");
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 8);
		cur.put("right", false);
		cur.put("left", false);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "学分");
		cur.put("right", false);
		cur.put("left", false);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "成绩");
		cur.put("left", false);
		baseInfo.add(cur);

		return baseInfo;
	}

	private static List<Map> builtBaseInfoMap(User stu) {
		// TODO Auto-generated method stub
		List<Map> baseInfo = new ArrayList<Map>();
		Map cur = new HashMap();

		cur.put("content", "姓名");
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getUserName());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 2);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "学号");
		cur.put("size", 2);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getStudentId());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 6);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "性别");
		cur.put("size", 3);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getSex());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 3);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "身份证号");
		cur.put("size", 4);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getIdCard());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 3);
		baseInfo.add(cur);

		// cur = new HashMap();
		// cur.put("content", "");
		// cur.put("size", 3);
		// cur.put("down", false);
		// baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "籍贯");
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getNativePlace());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 2);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "民族");
		cur.put("size", 2);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getNation());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 3);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "政治面貌");
		cur.put("size", 3);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getPolitical());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 7);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "出生日期");
		cur.put("size", 4);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", SDF.format(stu.getBirthDay()));
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 2);
		baseInfo.add(cur);
		//
		// cur = new HashMap();
		// cur.put("content", "");
		// cur.put("size", 3);
		// cur.put("down", false);
		// cur.put("top", false);
		// baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "班级");
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getClassName());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 4);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "入学日期");
		cur.put("size", 2);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", SDF.format(stu.getEntranceDay()));
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 8);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "毕业日期");
		cur.put("size", 4);
		baseInfo.add(cur);

		cur = new HashMap();
		String cont = "";
		if (stu.getGraduDay() != null) {
			cont = SDF.format(stu.getGraduDay());
		}
		cur.put("content", cont);
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 5);
		baseInfo.add(cur);

		// cur = new HashMap();
		// cur.put("content", stu.getImage());
		// cur.put("horizontalAlig", Element.ALIGN_CENTER);
		// cur.put("size", 3);
		// cur.put("down", false);
		// cur.put("top", false);
		// baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "专业");
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getMajor());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 7);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "专业方向");
		cur.put("size", 3);
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getMajorFiled());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 13);
		baseInfo.add(cur);

		// cur = new HashMap();
		// cur.put("content", "");
		// cur.put("size", 3);
		// cur.put("down", false);
		// cur.put("top", false);
		// baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", "学院");
		baseInfo.add(cur);

		cur = new HashMap();
		cur.put("content", stu.getCollege());
		cur.put("horizontalAlig", Element.ALIGN_LEFT);
		cur.put("size", 23);
		baseInfo.add(cur);

		// cur = new HashMap();
		// cur.put("content", "");
		// cur.put("size", 3);
		// cur.put("top", false);
		// baseInfo.add(cur);

		return baseInfo;
	}

	public static PdfPCell createCell(Map map) {
		PdfPCell cell = null;
		String content = "";
		Font font = PdfBuilt.exFont;
		float height = 19.9f;
		int size = 1, verticalAlig = Element.ALIGN_MIDDLE, horizontalAlig = Element.ALIGN_CENTER;
		boolean top = true, down = true, left = true, right = true;
		String event = null;
		if (map.get("font") != null)
			font = (Font) map.get("font");
		if (map.get("content") != null) {
			Object obj = map.get("content");
			if (obj instanceof String) {
				content = (String) map.get("content");
				cell = new PdfPCell(new Paragraph(content, font));
			} else if (obj instanceof Double) {
				content = "" + obj;
				cell = new PdfPCell(new Paragraph(content, font));
			}
		} else {
			cell = new PdfPCell();
		}
		// height
		if (map.get("height") != null)
			height = (float) map.get("height");
		cell.setFixedHeight(height);
		// size
		if (map.get("size") != null)
			size = (int) map.get("size");
		cell.setColspan(size);
		// verticalAlig
		if (map.get("verticalAlig") != null)
			verticalAlig = (int) map.get("verticalAlig");
		cell.setVerticalAlignment(verticalAlig);
		// horizontalAlig
		if (map.get("horizontalAlig") != null)
			horizontalAlig = (int) map.get("horizontalAlig");
		cell.setHorizontalAlignment(horizontalAlig);
		// border
		cell.setBorderWidth(0.8f);
		// top
		if (map.get("top") != null)
			top = (boolean) map.get("top");
		if (!top)
			cell.disableBorderSide(1);
		// down
		if (map.get("down") != null)
			down = (boolean) map.get("down");
		if (!down)
			cell.disableBorderSide(2);
		// left
		if (map.get("left") != null)
			left = (boolean) map.get("left");
		if (!left)
			cell.disableBorderSide(4);
		// right
		if (map.get("right") != null)
			right = (boolean) map.get("right");
		if (!right)
			cell.disableBorderSide(8);
		if (map.get("event") != null) {
			event = (String) map.get("event");
			if (event.equals("up")) {
				cell.disableBorderSide(1);
				cell.setCellEvent(new CustomCellUp());
			} else if (event.equals("down")) {
				cell.disableBorderSide(2);
				cell.setCellEvent(new CustomCellDown());
			}
		}
		return cell;
	}
}
