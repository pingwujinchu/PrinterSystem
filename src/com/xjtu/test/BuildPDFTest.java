package com.xjtu.test;

import java.io.FileNotFoundException;

import com.lowagie.text.DocumentException;
import com.xjtu.bean.User;
import com.xjtu.util.PdfBuilt;

public class BuildPDFTest {
	public static void main(String []args){
		try {
			PdfBuilt.BuiltPdf(null,null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
