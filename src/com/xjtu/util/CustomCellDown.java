package com.xjtu.util;

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPCellEvent;
import com.lowagie.text.pdf.PdfPTable;

public class CustomCellDown implements PdfPCellEvent {

	@Override
	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
		// TODO Auto-generated method stub
		PdfContentByte cb = canvases[PdfPTable.LINECANVAS];
		cb.saveState();
		// cb.setLineCap(PdfContentByte.LINE_CAP_ROUND);
		// cb.setLineDash(0, 1, 1);
		cb.setLineWidth(0.5f);
		cb.setLineDash(new float[] { 1.0f, 3.0f }, 0);
		cb.moveTo(position.getLeft(), position.getBottom());
		cb.lineTo(position.getRight(), position.getBottom());
		cb.stroke();
		cb.restoreState();
	}

}
