package com.churchgroup.util.pdf;


import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.string.StringFunctions;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.SimpleCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Explains the concept concerning PdfContentByte layers.
 */
public class PDFGenerator {
	private static final Log log = LogFactory.getLog(PDFGenerator.class);
    private PdfWriter pdfWriter;
    private Document document;
    private Paragraph paragraph;
    private String pdfName;
    private List groups;
    private List users;
    
    public String getPdfName() {
        return pdfName;
    }
    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }
    
    public void createWriter()
    {
        try {
            pdfWriter=PdfWriter.getInstance(document, new FileOutputStream(getPdfName()));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void createWriter(Document document)
    {
        try {
            pdfWriter=PdfWriter.getInstance(document, new FileOutputStream(getPdfName()));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void createDocument()
    {
        setDocument(new Document(PageSize.A4.rotate(),50,50,110, 50));
        getDocument().addAuthor("Hyniva Consulting Services");
        getDocument().addSubject("Group Interactive");
    }
    /**
     * Draws different things into different layers.
     * @param args no arguments needed

    public void createGroupsPDF()
    {
        try {
            
            if(!ObjectFunctions.isNullOrEmpty(getGroups()))
            {
               // createWriter(document);
               document.open();
               GroupCategory groupCategory;
               EventDetail eventDetail;
               Address address;
               Iterator groupListIterator= getGroups().iterator();
               for(Iterator objectIterator = groupListIterator;objectIterator.hasNext();)
               {
                   Group group = (Group)objectIterator.next();
                   groupCategory = group.getGroupCategory();
                   eventDetail = group.getEventDetail();
                   address = group.getAddress();
                   Paragraph p = new Paragraph();
                   if(!ObjectFunctions.isNullOrEmpty(group)
                           && !ObjectFunctions.isNullOrEmpty(groupCategory))
                   {
                       p.add(new Chunk(group.getGroupName()));
                     //  p.add(new Chunk(groupCategory.getGroupCatName()));
                       p.add(new Chunk(group.getGroupSubCatName()));
                       p.add(new Chunk(group.getGroupDescription()));
                       p.add(new Chunk(group.getSelfManaged()));
                      // p.add(new Chunk(group.getGroupSearchMapStatus()));
                       p.add(new Chunk(group.getGroupStatus()));
                       if(!ObjectFunctions.isNullOrEmpty(eventDetail))
                       {
                           p.add(new Chunk(eventDetail.getEventFrequency()));
                           p.add(new Chunk(eventDetail.getEventDay()));
                           p.add(new Chunk(eventDetail.getEventTime()));
                           p.add(new Chunk(eventDetail.getEventEndTime()));
                       }
                       if(!ObjectFunctions.isNullOrEmpty(address))
                       {
                         //  p.add(new Chunk( address.getAddressType()));
                          // p.add(new Chunk(address.getAddressLine3()));
                         //  p.add(new Chunk(address.getStreetPostDirection()));
                           p.add(new Chunk(address.getAddressLine1()));
                           //p.add(new Chunk(address.getAddressLine2()));
                         //  p.add(new Chunk(address.getCity()));
                           p.add(new Chunk(address.getState()));
                           p.add(new Chunk(address.getPostalCode()));
                       }
                      
                   }
                   document.add(p);
                   document.add(Chunk.NEWLINE);
               }
            }
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }
     */
    public PdfWriter getPdfWriter() {
        return pdfWriter;
    }

    public void setPdfWriter(PdfWriter pdfWriter) {
        this.pdfWriter = pdfWriter;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }
    public List getGroups() {
        return groups;
    }
    public void setGroups(List groups) {
        this.groups = groups;
    }
    public List getUsers() {
        return users;
    }
    public void setUsers(List users) {
        this.users = users;
    }
    
    /**
     * @see org.displaytag.export.ExportView#getMimeType()
     * @return "application/vnd.ms-excel"
     */
    public String getMimeType()
    {
        return "application/pdf"; //$NON-NLS-1$
    }

    public static void main(String[] args) {

		log.debug("document.add(BigTable)");
		// step1
		Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
		try {
			// step2
			PdfWriter.getInstance(document,
					new FileOutputStream("AddBigTable.pdf"));
			// step3
			document.open();
			// step4
			String[] bogusData = { "M0065920", "SL", "FR86000P", "PCGOLD",
					"119000", "96 06", "2001-08-13", "4350", "6011648299",

					"FLFLMTGP", "153", "119000.00" };
			int NumColumns = 12;

			PdfPTable datatable = new PdfPTable(NumColumns);
			int headerwidths[] = { 9, 4, 8, 10, 8, 11, 9, 7, 9, 10, 4, 10 }; // percentage
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100); // percentage
			datatable.getDefaultCell().setPadding(3);
			datatable.getDefaultCell().setBorderWidth(2);
			datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_CENTER);
			datatable.addCell("Clock #");
			datatable.addCell("Trans Type");
			datatable.addCell("Cusip");
			datatable.addCell("Long Name");
			datatable.addCell("Quantity");
			datatable.addCell("Fraction Price");
			datatable.addCell("Settle Date");
			datatable.addCell("Portfolio");
			datatable.addCell("ADP Number");
			datatable.addCell("Account ID");
			datatable.addCell("Reg Rep ID");
			datatable.addCell("Amt To Go ");

			datatable.setHeaderRows(1); // this is the end of the table header

			datatable.getDefaultCell().setBorderWidth(1);
			for (int i = 1; i < 750; i++) {
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}
				for (int x = 0; x < NumColumns; x++) {
					datatable.addCell(bogusData[x]);
				}
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(1);
				}
			}
			document.add(datatable);
		} catch (Exception de) {
			de.printStackTrace();
		}
		// step5
		document.close();
	}

    /**
     * @Description This method returns a row with two cells only with the following format
     * 					<label > : <text>
     * @param label
     * @param text
     * @return
     */
    public SimpleCell getRowWithMiddleAlignTwoCells(String label, String text)
    {
    	SimpleCell row = new SimpleCell(SimpleCell.ROW);
    	SimpleCell cell = new SimpleCell(SimpleCell.CELL);
        cell.setWidth(150f);
        cell.setHorizontalAlignment(SimpleCell.ALIGN_RIGHT);
        cell.add(new Paragraph(label));
        row.add(cell);
        
        cell = new SimpleCell(SimpleCell.CELL);
        cell.setWidth(150f);
        cell.setHorizontalAlignment(SimpleCell.ALIGN_LEFT);
        cell.add(new Paragraph(text));
        row.add(cell);
        return row;
    }
    public SimpleCell getRowWithMiddleAlignTwoCells(String label)
    {
    	SimpleCell row = new SimpleCell(SimpleCell.ROW);
    	SimpleCell cell = new SimpleCell(SimpleCell.CELL);
        cell.setWidth(150f);
        cell.add(new Paragraph(label,FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
        cell.setColspan(2);
        row.add(cell);
        return row;
    }
    
    public static PdfPCell getPdfCellHeaderRow(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBackgroundColor(Color.decode("#e7eadf"));
   	 	cell.setBorder(0);
   	 	cell.setColspan(2);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderRowWithColspan(String label,int colspan)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBackgroundColor(Color.decode("#e7eadf"));
   	 	cell.setBorder(0);
   	 	cell.setColspan(colspan);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderLeftRow(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
   	 	cell.setBackgroundColor(Color.decode("#e7eadf"));
   	 	cell.setBorder(0);
   	 	cell.setColspan(2);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderCenterRow(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
   	 	cell.setBackgroundColor(Color.decode("#e7eadf"));
   	 	cell.setBorder(0);
   	 	cell.setColspan(1);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderRightRow(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBackgroundColor(Color.decode("#e7eadf"));
   	 	cell.setBorder(0);   	 	
        return cell;
    }
    public static PdfPCell getPdfCellFooterRow(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBackgroundColor(Color.decode("#e7eadf"));
   	    cell.setHorizontalAlignment (Element.ALIGN_CENTER);
   	 	cell.setBorder(0);
   	    return cell;
    }
    public static PdfPCell getPdfCellHeaderCell(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBackgroundColor(Color.decode("#e7eadf"));
   	 	cell.setBorder(0);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderCell(String label, String color)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD,Color.decode(color))));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBackgroundColor(Color.decode("#e7eadf"));
   	 	cell.setBorder(0);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderCellWithOutBackGround(String label, String color)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD,Color.decode(color))));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	//cell.setBackgroundColor(Color.decode("#e7eadf"));
   	 	cell.setBorder(0);
        return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlign(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("Droid Sans", 10, Font.NORMAL)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBorder(0);
        return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlign(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label));
   	 	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
   	 	cell.setBorder(0);      	  
        return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignBold(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBorder(0);
        return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignBlueUnderLine(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont(FontFactory.HELVETICA, 10, Font.UNDERLINE,Color.BLUE)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBorder(0);
        return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlignHeading(String label,int colSpanValue)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,Color.BLUE)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
   	 	cell.setBorder(0);
   	    cell.setColspan(colSpanValue);
        return cell;
    }
    public static PdfPCell getPdfCellWithRightAlign(String label)
    {
    	PdfPCell cell = new PdfPCell (new Paragraph (label));
   	 	cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
   	 	cell.setBorder(0);
        return cell;
    }
    /**
     * @Description: Gets Image instance from the file path
     * @param filePath
     * @return
     */
    public static Image getImageInstance(String filePath)
    {
		try {
			Image image = Image.getInstance(filePath);
			image.setAlignment(Image.TEXTWRAP);
	        image.setIndentationRight(10.0f);
	        return image;
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    public static Image getImageInstanceSmallIcon(String filePath)
    {
		try {
			Image image = Image.getInstance(filePath);
			image.setAlignment(Image.TEXTWRAP);
			image.setXYRatio(2);
	        return image;
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    public static Image getImageInstance(String filePath,String realPath)
    {
		try {
			Image image = Image.getInstance(realPath+"/images/common/photo_notAvailable.jpg");
			image.setAlignment(Image.TEXTWRAP);
	        image.setIndentationRight(10.0f);
	        return image;
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    // for jasper type 
    
    public void createDocumentJasper()
    {
        setDocument(new Document(PageSize.A4.rotate(),20,20,20, 60));
        getDocument().addAuthor("Hyniva Consulting Services");
        getDocument().addSubject("");
    }
    public static PdfPCell getPdfCellWithCenterAlignHeadingJasper(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 20, Font.BOLD,Color.BLUE)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
   	 	cell.setBorder(0);
   	    cell.setColspan(colSpanValue);
        return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlignHeadings(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 14, Font.BOLD,Color.BLUE)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorder(0);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlignHeadingsWithRightAlign(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 14, Font.BOLD,Color.BLACK)));
    	cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
    	cell.setBorder(0);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithCenterAlignHeadingsWithRightAligns(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 10, Font.BOLD,Color.BLACK)));
    	cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
    	cell.setBorder(0);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithCenterAlignHeadingJasperWthBorder(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 15, Font.BOLD,Color.BLUE)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
   	 	cell.setBorder(2);
   	    cell.setColspan(colSpanValue);
        return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlignHeadingJasperNoColor(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBackgroundColor(Color.lightGray);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignJasperNoColor(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 10, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBorderColor(Color.BLACK);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlignJasperNoColor(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans",9,Font.NORMAL)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignFontJasperNoColor(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 12, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignFontSizeJasperNoColor(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9,Font.NORMAL)));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignCenterJasperNoColor(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.NORMAL)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignCenterJasperNoColorWithBold(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 12, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlignHeadingJaspersForFemale(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 10, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBackgroundColor(Color.gray);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellHeaderCellJasperWithBorder(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
   	 	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
   	    cell.setBackgroundColor(Color.lightGray);
   	 	cell.setBorderColor(Color.BLACK);
   	 	//cell.setBorder(2);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderCellJasper(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBackgroundColor(Color.decode("#FFFFFF"));
   	 	cell.setBorder(2);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderCellJasperClass(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 9, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	//cell.setBorder(1);
    	return cell;
    }
    public static PdfPCell getPdfCellHeaderCellWithLeftAlignJasperClass(String label, int color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 9, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBackgroundColor(Color.lightGray);
    	return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignJasperWithBorder(String label,String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.NORMAL)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
        return cell;
    }
    
    public static PdfPCell getPdfCellAlignCenter(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 9, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	//cell.setBorder(1);
    	return cell;
    }
    public static PdfPCell getPdfCellHeaderCellJasperClassForFemale(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBackgroundColor(Color.decode("#FFCCCC"));
    	//cell.setBorder(1);
    	return cell;
    }
    public static PdfPCell getPdfCellHeaderCellJasperClassForMale(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBackgroundColor(Color.decode("#CCFFFF"));
    	//cell.setBorder(1);
    	return cell;
    }
    public static PdfPCell getPdfCellHeaderCellJasperClassWithColor(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBackgroundColor(Color.gray);
    	//cell.setBorder(1);
    	return cell;
    }
    public static PdfPCell getPdfCellHeaderCellJasperWithNoBorder(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBackgroundColor(Color.decode("#FFFFFF"));
   	 	cell.setColspan(2);
   	 	//cell.setBorder(0);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderRowWithColspanJasper(String label,int colspan,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 15, Font.BOLD,Color.decode("#FFFFFF"))));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setVerticalAlignment(Element.ALIGN_TOP);   	 	
   	 	cell.setBackgroundColor(Color.decode("#3399FF"));
   	 	cell.setBorder(0);
   	 	cell.setColspan(colspan);   	 	
        return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlignHeadingJasper1(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 15, Font.BOLD,Color.decode("#666666"))));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 cell.setBackgroundColor(Color.decode("#FFFFFF"));
   	 	cell.setBorder(0);
   	    cell.setColspan(colSpanValue);
        return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlignHeadingJasper2(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 15, Font.BOLD,Color.decode("#666666"))));
   	 	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
   	 cell.setBackgroundColor(Color.decode("#FFFFFF"));
   	 	cell.setBorder(1);
   	    cell.setColspan(colSpanValue);
        return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignJasper(String label,String fontPath)
    {
    	FontFactory.register(fontPath);
    	
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.NORMAL)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	cell.setBorder(0);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndBackGrundColorAndAlignmentAndPaddingJasper(String label,int colspan,String fontPath,String fontColor,int fontSize,String backGroundColor,int alignMent,float padding)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", fontSize, Font.BOLD,Color.decode(fontColor))));
   	 	cell.setHorizontalAlignment (alignMent);
   	 	cell.setPadding (padding);
   	 	if(!StringFunctions.isNullOrEmpty(backGroundColor))
   	 		cell.setBackgroundColor(Color.decode(backGroundColor));
   	 	cell.setBorder(0);
   	 	cell.setColspan(colspan);   	 	

        return cell;
    }
    public static PdfPCell getPdfCellHeaderRowWithColspanAndFontColorFontSizAndAlignmentAndPaddingJasper(String label,int colspan,String fontPath ,int fontSize,String backGroundColor,int alignMent,float padding)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", fontSize, Font.BOLD,Color.BLUE)));
    	cell.setHorizontalAlignment (alignMent);
    	cell.setPadding (padding);
    	cell.setBorder(0);
    	cell.setColspan(colspan);   	 	
    	return cell;
    }
    public static PdfPCell getPdfCellHeaderRowWithColspanAndFontColorFontSizeAndNOBackGrundColorAndAlignmentAndPaddingJasper(String label,int colspan,String fontPath,String fontColor,int fontSize,String backGroundColor,int alignMent,float padding)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", fontSize, Font.BOLD,Color.decode(fontColor))));
    	cell.setHorizontalAlignment (alignMent);
    	cell.setPadding (padding);
    	//cell.setBackgroundColor(Color.decode(backGroundColor));
    	cell.setBorder(0);
    	cell.setColspan(colspan);   	 	
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithRightAlignJasper(String label,String fontPath)
    {
    	FontFactory.register(fontPath);
    	
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.NORMAL)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
   	 	cell.setBorder(0);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderVerticalColumnWithColspanAndFontColorFontSizeAndBackGrundColorAndAlignmentAndPaddingJasper(String label,int colspan,String fontPath,String fontColor,int fontSize,String backGroundColor,int alignMent,float padding)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", fontSize, Font.BOLD,Color.decode(fontColor))));
   	 	cell.setVerticalAlignment(alignMent);
   	 	cell.setMinimumHeight (0.1f);
   	 	cell.setPadding (padding);
   	 	cell.setBackgroundColor(Color.decode(backGroundColor));
   	 	cell.setBorder(0);
   	 	cell.setColspan(colspan);   	 	
        return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignJasperNoBorder(String label,String fontPath)
    {
    	FontFactory.register(fontPath);
    	
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.NORMAL)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
   	 	//cell.setBorder(4);
   	 //cell.setBorder(3);
        return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlignJasperNoBorder(String label,String fontPath)
    {
    	FontFactory.register(fontPath);
    	
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.NORMAL)));
   	 	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
   	 	//cell.setBorder(4);
   	 //cell.setBorder(3);
        return cell;
    }
    public static PdfPCell getPdfCellHeaderCellJasperClassWithAlignment(String label, String color,String fontPath,int alignment)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (alignment);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	//cell.setBorder(1);
    	return cell;
    }
    public static PdfPCell getPdfCellHeaderRowWithColspanAndNoColor(String label,int colspan,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setVerticalAlignment(Element.ALIGN_TOP);   	 	
    	cell.setBorder(0);
    	cell.setColspan(colspan);   	 	
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithinTableSubHeaders(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 10, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBorder(8);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellHeadersNoBorder(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	cell.setBorder(8);
    	cell.setBottom(0);
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithLeftAlign(String label,int colSpanValue,String fontPath)
    {
        FontFactory.register(fontPath);
        PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 10, Font.BOLD,Color.BLACK)));
        cell.setHorizontalAlignment (Element.ALIGN_LEFT);
        cell.setBorder(0);
        cell.setColspan(colSpanValue);
        return cell;
    }
    
    public static PdfPCell getPdfCellHeaderCellJasperHorizontalClass(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	cell.setBorder(4);
    	return cell;
    }
    public static PdfPCell getPdfCellAlignCenterWithColspanAndBorder(String label, String color,String fontPath,int colspan)
    {
    FontFactory.register(fontPath);
    PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
    cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    cell.setBackgroundColor(Color.decode("#FFFFFF"));
    cell.setColspan(colspan);
    return cell;
    }
    public static PdfPCell getPdfCellHeaderRowWithColspanAndNormalFontColorFontSizeAndBackGrundColorAndAlignmentAndPaddingJasper(String label,int colspan,String fontPath,int alignMent,float padding)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.NORMAL,Color.decode("#FFFFFF"))));
   	 	cell.setHorizontalAlignment (alignMent);
   	 	cell.setPadding (padding);
   	 		cell.setBackgroundColor(Color.decode("#005CB9"));
   	 	cell.setBorder(0);
   	 	cell.setColspan(colspan);   	 	
        return cell;
    }

    public static PdfPCell getPdfCellWithAlignCenterHeaderNoColorWithBoldFont10(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellAlignCenter1(String label, String color,String fontPath) {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	cell.setBorder(Rectangle.RIGHT);
    	cell.setBorder(Rectangle.LEFT);
    	return cell;
    }
   
    public static PdfPCell getPdfCellWithAlignCenterHeaderNoColorWithBoldFont101(String label,int colSpanValue,String fontPath) {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.BOLD)));
    	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cell.setVerticalAlignment(Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBorder(Rectangle.RIGHT);
    	cell.setBorder(Rectangle.LEFT);
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithRightAlignFontJasperNoColor(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithAlignLeftHeaderNoColorWithBoldFont9(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellHeadersNoBorder(String label, String color,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	cell.setBorder(8);
    	cell.setBottom(0);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    
    
    public static PdfPCell getPdfCellWithLeftAlignFontSizeBoldJasperNoColor(String label,int colSpanValue,int fontSize,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", fontSize,Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellHeadersNoBorderAlignCenter(String label, String color,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 10, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	cell.setBorder(8);
    	cell.setBottom(0);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignCenterJasperNoColorWithBoldFont(String label,int colSpanValue,int fontSize,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", fontSize, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(4);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithLeftAlignFontSizeJasperNoColorWithBold(String label,int colSpanValue,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 9,Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithLeftAlignCenterJasperNoColorWithBoldFontBorder4(String label,int colSpanValue,int fontSize,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", fontSize, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBorder(4);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithAlignCenterHeaderNoColorWithBoldFont101(String label,int colSpanValue,int fontSize,String fontPath) {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", fontSize, Font.BOLD)));
    	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cell.setVerticalAlignment(Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBorder(Rectangle.RIGHT);
    	cell.setBorder(Rectangle.LEFT);
    	return cell;
    }
    
    public static PdfPCell getPdfCellHeadersNoBorderWithFont(String label, String color,int colSpanValue,int fontSize,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", fontSize, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	cell.setBorder(0);
    	//cell.setBottom(0);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellHeadersNoBorderAlignCenter(String label, String color,int colSpanValue,int fontSize,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", fontSize, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	cell.setBorder(0);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithAlignLeftHeaderNoColorWithBoldFont101(String label,int colSpanValue,int fontSize,String fontPath) {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", fontSize, Font.BOLD)));
    	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	cell.setVerticalAlignment(Element.ALIGN_LEFT);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBorder(Rectangle.RIGHT);
    	cell.setBorder(Rectangle.LEFT);
    	return cell;
    }
    public static PdfPCell getPdfCellHeaderRowWithColspanAndNormalFontColorFontSizeAndBackGrundColor(String label,int colspan,String fontPath,String fontColor,int fontSize,String backGroundColor,int alignMent,float padding)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", fontSize, Font.BOLD,Color.decode(fontColor))));
   	 	cell.setHorizontalAlignment (alignMent);
   	 	cell.setPadding (padding);
   	 	if(!StringFunctions.isNullOrEmpty(backGroundColor))
   	 		cell.setBackgroundColor(Color.decode(backGroundColor));
   	 	cell.setBorder(0);
   	 	cell.setColspan(colspan);   	 	
        return cell;
    }
    public static PdfPCell getPdfCellWithCenterAlignCenterJasperNoColorWithBold(String label,int colSpanValue,String fontPath,float padding)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", 12, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	cell.setPadding(padding);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithAlignCenterHeaderNoColorWithBoldFont10(String label,int colSpanValue,int fontSize,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", fontSize, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithAlignLeftNoColorWithNoBorderFont(String label,int colSpanValue,int fontSize,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", fontSize, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBorder(0);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithAlignRightNoColorWithNoBorderFont(String label,int colSpanValue,int fontSize,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", fontSize, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBorder(0);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    
    public static PdfPCell getPdfCellWithRightAlignJasperNoColorFont(String label,int colSpanValue,int fontSize,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", fontSize, Font.BOLD)));
    	cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
    	cell.setBorderColor(Color.BLACK);
    	//cell.setBorder(2);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellWithAlignLeftNoColorWithNoBorderNormalFont(String label,int colSpanValue,int fontSize,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label,FontFactory.getFont("droidsans", fontSize, Font.NORMAL)));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBorderColor(Color.BLACK);
    	cell.setBorder(0);
    	cell.setColspan(colSpanValue);
    	return cell;
    }
    public static PdfPCell getPdfCellAlignLeft(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 9, Font.BOLD,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	//cell.setBorder(1);
    	return cell;
    }
    
    public static PdfPCell getPdfCellAlignLeftNormalFont(String label, String color,String fontPath)
    {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 9, Font.NORMAL,Color.decode(color))));
    	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	//cell.setBorder(1);
    	return cell;
    }
    public static PdfPCell getPdfCellAlignCenter2(String label, String color,String fontPath) {
    	FontFactory.register(fontPath);
    	PdfPCell cell = new PdfPCell (new Paragraph (label, FontFactory.getFont("droidsans", 9, Font.NORMAL,Color.decode(color))));
    	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cell.setBackgroundColor(Color.decode("#FFFFFF"));
    	//cell.setBorder(Rectangle.RIGHT);
    //	cell.setBorder(Rectangle.LEFT);
    	return cell;
    }
}