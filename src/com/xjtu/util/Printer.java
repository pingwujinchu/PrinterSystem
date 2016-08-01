package com.xjtu.util;

import java.awt.Graphics;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintJobAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;

import org.apache.commons.logging.impl.LogKitLogger;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;

import com.mysql.jdbc.log.Log4JLogger;




/**
 * 
 *  @fileName :   com.print.Printer.java
 *
 *	@version : 1.0
 *
 * 	@see { }
 *
 *	@author   :   fan
 *
 *	@since : JDK1.4
 *  
 *  Create date  : 2016年7月2日 上午12:12:51
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

public class Printer implements Printable{
	  private String filePath;
	  
     public Printer(String filePath) {
		super();
		this.filePath = filePath;
	}

	public static void doPrint(String filePath) throws IOException{
    	 
    	 
//    	 DocFlavor flavor = DocFlavor.INPUT_STREAM.JPEG;
//    	 
//    	 PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
//    	 PrintService []printers = PrintServiceLookup.lookupPrintServices(flavor, pras);
//    	 PrintService ps = null;
//    	 for(int i = 0 ; i < printers.length ; i++){
//    		 if(printers[i].getName().equals("\\\\XIXI-PC\\FX DocuPrint P158 b")){
//    			 ps = printers[i];
//    			 break;
//    		 }
//    		 System.out.println(printers[i].getName());
//    	 }
//    	 
//    	 DocPrintJob job = ps.createPrintJob();
//    	 try {
//			InputStream is = new FileInputStream(filePath);
//			DocAttributeSet das = new HashDocAttributeSet();
//			Doc doc = new SimpleDoc(is, flavor,das);
//			
////			HashPrintRequestAttributeSet as = new HashPrintRequestAttributeSet();
////			Attribute att = new JobName("zuoye", null);
////			Attribute att2 = new JobName("password","123456");
////			as.add(att);
////			as.add(att2);
//			job.print(doc, pras);
////		    PrinterJob pjob = PrinterJob.getPrinterJob();   
////		    try {
////				pjob.setPrintService(ps);
//////				pjob.setPrintable(new Printer());
////				pjob.print();
////			} catch (PrinterException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			
//		} catch (FileNotFoundException | PrintException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
    	 PDDocument document = PDDocument.load(new File(filePath));
    	 try {
    		 printWithPaper(document);
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	 document.close();
     }

	@Override
	public int print(Graphics gra, PageFormat pf, int pageIndex) throws PrinterException {
		// TODO method stub
		switch(pageIndex){
		case 0:       
			return PAGE_EXISTS;  
        default:  
        return NO_SUCH_PAGE;  
		}
	}
	
	
    public static void main(String args[]) throws PrinterException, IOException
    {
        if (args.length != 1)
        {
            System.err.println("usage: java " + Printer.class.getName() + " <input>");
            System.exit(1);
        }

        String filename = args[0];
        PDDocument document = PDDocument.load(new File(filename));
        
        // choose your printing method:
        print(document); 
        //printWithAttributes(document);
        //printWithDialog(document);
        //printWithDialogAndAttributes(document);
        //printWithPaper(document);
    }

    /**
     * Prints the document at its actual size. This is the recommended way to print.
     */
    private static void print(PDDocument document) throws IOException, PrinterException
    {
//   	 PrintService []printers = PrinterJob.lookupPrintServices() ;
//   	 PrintService ps = null;
//   	 for(int i = 0 ; i < printers.length ; i++){
//   		 if(printers[i].getName().equals("\\\\XIXI-PC\\FX DocuPrint P158 b")){
//   			 ps = printers[i];
//   			 break;
//   		 }
//   		 System.out.println(printers[i].getName());
//   	 }
   	   
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.print();
    }

    /**
     * Prints using custom PrintRequestAttribute values.
     */
    private static void printWithAttributes(PDDocument document)
            throws IOException, PrinterException
    {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));

        PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
        attr.add(new PageRanges(1, 1)); // pages 1 to 1

        job.print(attr);
    }

    /**
     * Prints with a print preview dialog.
     */
    private static void printWithDialog(PDDocument document) throws IOException, PrinterException
    {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));

        if (job.printDialog())
        {
            job.print();
        }
    }

    /**
     * Prints with a print preview dialog and custom PrintRequestAttribute values.
     */
    private static void printWithDialogAndAttributes(PDDocument document)
            throws IOException, PrinterException
    {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));

        PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
        attr.add(new PageRanges(1, 1)); // pages 1 to 1

        if (job.printDialog(attr))
        {
            job.print(attr);
        }
    }
    
    /**
     * Prints using a custom page size and custom margins.
     */
    private static void printWithPaper(PDDocument document)
            throws IOException, PrinterException
    {
//    	PrintService []printers = PrinterJob.lookupPrintServices();
//      	 PrintService ps = null;
//       	 for(int i = 0 ; i < printers.length ; i++){
//       		 if(printers[i].getName().equals("\\\\XIXI-PC\\FX DocuPrint P158 b")){
//       			 ps = printers[i];
//       			 break;
//       		 }
//       		 System.out.println(printers[i].getName());
//       	 }
//       	
//       	
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.setJobName("PrinterSystem");
        
        // define custom paper
        Paper paper = new Paper();
        paper.setSize(306, 396); // 1/72 inch
        paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight()); // no margins

        // custom page format
        PageFormat pageFormat = new PageFormat();
        pageFormat.setPaper(paper);
        
        // override the page format
        Book book = new Book();
        // append all pages
        book.append(new PDFPrintable(document), pageFormat, document.getNumberOfPages());
        job.setPageable(book);
        job.print();
        
    }

}
