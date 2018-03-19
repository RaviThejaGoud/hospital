package com.churchgroup.util.pdf;


import java.awt.Color;
import java.util.Date;

import com.churchgroup.util.date.DateFormatter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;


 public class PdfHeaderFooterMark extends PdfPageEventHelper {
	 /** An Image that goes in the header. */
	 public Image headerImage;
	 /** The headertable. */
	 public PdfPTable table;
	 /** The Graphic state */
	 public PdfGState gstate;
	 /** A template that will hold the total number of pages. */
	 public PdfTemplate tpl;
	 /** The font that will be used. */
	 public BaseFont helv;
	 
	 public Image headerLeftImage;
	 public Image headerRightImage;
	 /**
	 * @return the headerLeftImage
	 */
	public Image getHeaderLeftImage() {
		return headerLeftImage;
	}

	/**
	 * @param headerLeftImage the headerLeftImage to set
	 */
	public void setHeaderLeftImage(Image headerLeftImage) {
		this.headerLeftImage = headerLeftImage;
	}

	/**
	 * @return the headerRightImage
	 */
	public Image getHeaderRightImage() {
		return headerRightImage;
	}

	/**
	 * @param headerRightImage the headerRightImage to set
	 */
	public void setHeaderRightImage(Image headerRightImage) {
		this.headerRightImage = headerRightImage;
	}

	/**
	     * @see com.lowagie.text.pdf.PdfPageEventHelper#onOpenDocument(com.lowagie.text.pdf.PdfWriter, com.lowagie.text.Document)
	     */

	 public void onOpenDocument(PdfWriter writer, Document document) {
	        try {
	        	Rectangle page = document.getPageSize();	        	
	        	 // initialization of the header table	           
	            table = new PdfPTable(2);	
	            table.getDefaultCell().setBackgroundColor(Color.decode("#F5F6F7"));
	           /* Phrase p = new Phrase();
	            Chunk ck = new Chunk("lowagie.com\n", new Font(Font.TIMES_ROMAN, 16, Font.BOLDITALIC, Color.blue));
	            p.add(ck);
	            ck = new Chunk("Ghent\nBelgium", new Font(Font.HELVETICA, 12, Font.NORMAL, Color.darkGray));
	            p.add(ck);*/	           
	            table.getDefaultCell().setBorderWidth(0);	            
	            table.addCell(new Phrase(new Chunk(this.getHeaderLeftImage(),0, 0)));
	            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            table.addCell(new Phrase(new Chunk(this.getHeaderRightImage(), 0, 0)));
	            //table.setTotalWidth(document.right() - document.left());
	           // table.writeSelectedRows(0,0,0,0,writer.getDirectContentUnder());
	            table.setTotalWidth(page.getWidth()-document.leftMargin() - document.rightMargin());
	            table.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight()-100+ table.getTotalHeight(),
	                                                            writer.getDirectContent());
	            // initialization of the Graphic State
		            gstate = new PdfGState();
		            gstate.setFillOpacity(0.3f);
		            gstate.setStrokeOpacity(0.3f);		            
	            // initialization of the template
	            tpl = writer.getDirectContent().createTemplate(100, 100);
	            tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));	                      
	            // initialization of the font
	            helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);	       
	        }
	        catch(Exception e) {
	            throw new ExceptionConverter(e);
	        }
	    }    
	    
	 /**
	     * @see com.lowagie.text.pdf.PdfPageEventHelper#onEndPage(com.lowagie.text.pdf.PdfWriter, com.lowagie.text.Document)
	     */
	    public void onEndPage(PdfWriter writer, Document document) {
	        PdfContentByte cb = writer.getDirectContent();
	        cb.saveState();
	        // write the headertable
	        table.setTotalWidth(document.right() - document.left());
	        //table.writeSelectedRows(0, -1, document.left(), document.getPageSize().getHeight() - 50, cb);
	        // compose the footer
	        String text = "Page " + writer.getPageNumber() + " of ";
	        String dateStr=DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN,new  Date());
	        float textSize = helv.getWidthPoint(text, 12);
	        float textBase = document.bottom() - 20;
	        cb.beginText();
	        cb.setFontAndSize(helv, 12);
	     // Create a Date to the left side of the footer      
	            cb.setTextMatrix(document.left(), textBase);
	            cb.showText(dateStr);
	            cb.endText();
	            cb.saveState();
	       // Create a page numbers to the right side of the footer
	            textSize = helv.getWidthPoint(text, 12);
		        textBase = document.bottom() - 20;
		        cb.beginText();
		        cb.setFontAndSize(helv, 12);
	            float adjust = helv.getWidthPoint(text, 12);
	            cb.setTextMatrix(document.right() - textSize-adjust+50, textBase);
	            cb.showText(text);
	            cb.endText();	  
	            cb.addTemplate(tpl, document.right() - textSize+50, textBase);	  
	            cb.saveState();      
	    }
        
	    /**
	     * @see com.lowagie.text.pdf.PdfPageEventHelper#onStartPage(com.lowagie.text.pdf.PdfWriter, com.lowagie.text.Document)
	     */
	    public void onStartPage(PdfWriter writer, Document document) {	    	     
	    	/*PdfContentByte cb = writer.getDirectContentUnder();
            cb.saveState();
            cb.setColorFill(Color.BLACK);
            float textBase = document.top();  
            cb.beginText();
            cb.setFontAndSize(helv,12);
            cb.setTextMatrix(document.top(), textBase);
            String dateStr=DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN,new  Date());            
            cb.showTextAligned(Element.ALIGN_TOP,dateStr,0,0,0);
            cb.endText();
            cb.restoreState(); 
	    	cb.saveState();*/
	    	/*String dateStr=DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN,new  Date());    	
            float textSize = helv.getWidthPoint(dateStr, 12);
            float textBase = document.top();           
            cb.beginText();
		    cb.setFontAndSize(helv, 12);
            cb.setTextMatrix(document.top(), textBase);
            cb.addTemplate(tpl, document.top() + textSize, textBase);
            cb.showText(dateStr);
            cb.endText(); 
            cb.addTemplate(tpl, document.left() + textSize, textBase);
            cb.saveState();
            cb.restoreState();*/
	          /*  PdfContentByte cb = writer.getDirectContentUnder();
	            cb.saveState();
	            cb.setColorFill(Color.pink);
	            cb.beginText();
	            cb.setFontAndSize(helv, 48);
	            cb.showTextAligned(Element.ALIGN_CENTER, "My Watermark Under " + writer.getPageNumber(), document.getPageSize().getWidth() / 2, document.getPageSize().getHeight() / 2, 45);
	            cb.endText();
	            cb.restoreState();	        */
	    }
	    
	    /**
	     * @see com.lowagie.text.pdf.PdfPageEventHelper#onCloseDocument(com.lowagie.text.pdf.PdfWriter, com.lowagie.text.Document)
	     */
	    public void onCloseDocument(PdfWriter writer, Document document) {
	       tpl.beginText();
	       tpl.setFontAndSize(helv, 12);
	       tpl.setTextMatrix(0, 0);
	       tpl.showText("" + (writer.getPageNumber() - 1));
	       tpl.endText();
	    }	    

 }
