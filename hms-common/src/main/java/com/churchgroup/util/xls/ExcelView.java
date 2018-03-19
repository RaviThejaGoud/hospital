package com.churchgroup.util.xls;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Export view for excel exporting.
 * @author Sreeramulu J
 * @version $Revision: 1.4 $ ($Author: eazyschool $)
 */
public class ExcelView 
{

	private static final Log log = LogFactory.getLog(ExcelView.class);
    private WritableSheet ws;
    private WritableWorkbook wb;
    private String workSheetName;
    private int columnSize=0;
    private int rowSize=0;
    private String[] columnHeaders;
    private String columnHeader;
    private CellFormat columnHeaderformat;
    private WritableCellFormat arial10format;
    private WritableCellFormat arial10Boldformat;
    private WritableCellFormat defaultFormat;
    private WritableCellFormat wrapCellFormat; 
    private WritableCellFormat wrapCellFont; 
    private WritableCellFormat wrapCellFormatCenter; 
    private WritableCellFormat user14format;
    private WritableCellFormat user30format;
    private WritableCellFormat usermore30format;
    private WritableCellFormat usermore10BoldformatGreenBgClr;
    private WritableCellFormat usermore10BoldformatRedClr;
    private WritableCellFormat usermore10BoldformatWhiteBgClr;
    private WritableCellFormat wrapCellFormatWithBold; 
    private WritableCellFormat arial10FontBoldWhiteRight;
    private WritableCellFormat defaultFormatRight;
    
    /**
     * To read the xls
     */
    
    WorkbookSettings wbs;
    Workbook workBook;
    public static final String [] EXPORTED_THESTORY_COLUMNS = { "User Name","Email","Story","Guide","Request Date" };
    public static final String [] EXPORTED_USERS_COLUMNS = {"Email","First Name","Middle Intial","Last Name", "Date Of Birth","Privacy","Address Line1","Address Line2","Subdivision","City","State","Zip","Phone Type","Area Code","Prefix","Suffix"};
    public static final String [] EXPORTED_USERSWITHOUGROUPS_COLUMNS = {"First Name","Last Name", "E-Mail","Address Line1","Address Line2","Subdivision","City","State","Zip","Phone"};
    public static final String [] EXPORTED_GROUPS_COLUMNS = {"Group Name","Group Type","Description", "Self Managed","Viewable on Church","Group Status","Meeting Frequency","Meeting Day","Start Time","End Time","Meeting Place","Room No","Subdivision","Address1","Address2","City","State","Zip Code","Leader Name","Leader Email","Leader PhoneNumber"};
    public static final String [] EXPORTED_GROUPMEMBERS_COLUMNS = {"GroupMember Name","Email","Role","Date Of Birth","Phone Number","Subdivision","Address Line1","Address Line2","City","State","Zip"};
    public static final String [] SUBSCRIPTIONS_COLUMNS = {"Email","First Name", "Last Name", "Date Of Birth"};
    public static final String [] GROUP_MEMBERS_COLUMNS = {"Member Name","Email", "Phone#", "Image"};
    public static final String [] EXPORTED_ALL_USERS_COLUMNS = {"First Name","Last Name", "E-Mail","Group(as a Leader)","Group(as a Member)","Address Line1","Address Line2","Subdivision","City","State","Zip","Phone"};
    public static final String [] EXPORTED_STUDIES_COLUMNS = {"Study Title","Study Author","Created Date", "Study Staus","Description","Total Lessons","Group Names"};
    public static final String [] EXPORTED_ADMINACTIVESTUDIES_COLUMNS = {"Study Title","Study Author","Created Date","Description","Total Lessons","Group Names"};
    public static final String [] EXPORTED_ADMININACTIVESTUDIES_COLUMNS = {"Study Title","Study Author","Created Date","Description","Total Lessons","Group Names"};    
    public static final String [] EXPORTED_LEADERSTUDIES_COLUMNS = {"Study Title","Study Author","Created Date","Description","Total Lessons","Group Names"};
    public static final String [] EXPORTED_USERGUIDE_COLUMNS = {"Email-Id","Guide","Type","createdDate"};
    public static final String [] EXPORTED_GROUPAPPROVALS_COLUMNS = {"Group","Name","Email","Submitted","Overdue","Phone Number"};
    public static final String [] EXPORTED_OVERDUEINQUIRIESREPORTS_COLUMNS = {"Name","Group Name","Submitted","Overdue","Is Registered","Member Groups"};
    public static final String [] EXPORTED_ACCEPTEDINQUIRIESREPORTS_COLUMNS = {"Name","Group Name","Accepted By","Accepted Date","Pending Days","Contacted Status"};
    public static final String [] EXPORTED_USERLOGIN_COLUMNS = {"Name","LastAccessDate","LastAccessOn"};
    public static final String [] EXPORTED_PARTOFLEADER_COLUMNS = {"Name","EmailAddress","Address Line1","Address Line2","Subdivision","City","State","Zip","Phone Number","MemberGroups"};
    public static final String [] EXPORTED_PARTOFDELEGATES_COLUMNS = {"Name","EmailAddress","Address Line1","Address Line2","Subdivision","City","State","Zip","Phone Number","MemberGroups"};    
    public static final String [] EXPORTED_PARTOFALLUSERS_COLUMNS = {"Name","EmailAddress","Address Line1","Address Line2","Subdivision","City","State","Zip","Phone Number"};
    public static final String [] EXPORTED_PARTOFMEMBER_COLUMNS = {"Name","EmailAddress","Address Line1","Address Line2","Subdivision","City","State","Zip","Phone Number","MemberGroups"};
    public static final String [] EXPORTED_WITHOUTGROUPS_COLUMNS = {"Name","Email","Phone Number"};
    public static final String [] EXPORTED_STAFFMEMBERSREPORTS_COLUMNS ={"Staff Name","Email","Department Name","Designation","Contact Number"};
    public static final String [] EXPORTED_STAFFDEPARTMENTS_COLUMNS ={"Department Name","Total Staff"};
    public static final String [] EXPORTED_FIVEDAYSREPORTS_COLUMNS ={"Name","Last Access Date","Last Access On"};
    public static final String [] EXPORTED_TENDAYSREPORTS_COLUMNS ={"Name","Last Access Date","Last Access On"};
    public static final String [] EXPORTED_USERSNEVERLOGGEDREPORTS_COLUMNS ={"Name","Last Access Date","Last Access On"};
    public static final String [] EXPORTED_INTERESTEDPARTIESREPORTS_COLUMNS ={"Name","EmailAddress","Interested Groups","Gender","Zipcode"};
    public static final String [] EXPORTED_FOUNDSEARCHRESULTSREPORTS_COLUMNS ={"Search Type","Search Keyword","Groups Found","Search Date"};
    public static final String [] EXPORTED_NOTFOUNDSEARCHRESULTSREPORTS_COLUMNS ={"Search Type","Search Keyword","Search Date"};
    public static final String [] EXPORTED_REMOVEMEMBERSREPORTS_COLUMNS ={"Name","EmailAddress","Phone Number","Removed From","Removed By","Remove Date","Remarks"};
    public static final String [] EXPORTED_REMOVEUSERSREPORTS_COLUMNS ={"Name","EmailAddress","Phone Number","Removed From","Removed By","Remove Date"};
    public static final String [] EXPORTED_NEWGROUPSREPORTS_COLUMNS ={"Group Name","Group Type","Meeting Time/Day","Meeting Frequency","Group Status","Location","Meeting Address"};
    public static final String [] EXPORTED_RECRUITINGGROUPSREPORTS_COLUMNS ={"Group Name","Group Type","Meeting Time/Day","Meeting Frequency","Group Status","Location","Recruiting Stops On(Date)","Meeting Address"};
    public static final String [] EXPORTED_PENDINGINVITATIONSREPORTS_COLUMNS ={"From","Email","Group Name","Submitted Date","Days","Invited As"};
    public static final String [] EXPORTED_ACCEPTEDINVITATIONSREPORTS_COLUMNS ={"From","Email","Group Name","Accepted Date","Invited As"};
    public static final String [] EXPORTED_USERSTATISTICALREPORTS_COLUMNS ={"Total Registered Users","Users With Groups","Users WithOut Groups"};
    public static final String [] EXPORTED_GROUPSTATISTICALREPORTS_COLUMNS ={"Closed status Groups","New Groups","Open status Groups","Recuriting Groups","Total Groups","Users Remove from the groups"};
    public static final String [] EXPORTED_ADMINSTUDIESSTATISTICALREPORTS_COLUMNS ={"Total Studies","Total Admin Studies","Total Admin Active Studies","Total Admin Active Study lessons","Total Admin InActive Studies","Total Admin Inactive Study Lessons","Total Leader Studies","Total Leader Study Lessons"};
    public static final String [] EXPORTED_USERINQUIRIESSTATISTICALREPORTS_COLUMNS ={"Accepted Inquiries","Pending inquiries"};
    public static final String [] EXPORTED_INVITATIONSTATISTICALREPORTS_COLUMNS ={"Accepted Invitations","Leader Invitations","Member Invitations","Pending Invitations","Total Invitations"};
    public static final String [] EXPORTED_USERLOGINSTATISTICALREPORTS_COLUMNS ={"User Logged In Last 5 Days","Users Logged in Lase 10 Days","Users Never Logged in"};
    public static final String [] EXPORTED_INTERESTEDPARTYSTATISTICALREPORTS_COLUMNS ={"Total number of interested party Users"};
    public static final String [] EXPORTED_SEARCHSTATISTICALREPORTS_COLUMNS ={"Total Number of searches","Total Search Results"};
    public static final String [] EXPORTED_STAFFDEPARTMENTANDSTAFFMEMBERSSTATISTICALREPORTS_COLUMNS ={"Total number of Departments","Total Staff Members"};
    public static final String [] EXPORTED_GROUPINQUIRIESSTATISTICALREPORTS_COLUMNS ={"Accepted Approval Groups","Pending Approval Groups","Rejected Approval Groups"};
    public static final String [] EXPORTED_INTERESTEDPARTIES_COLUMNS ={"First Name","Last Name","Email Address","Gender","AddressLine1","AddressLine2","Subdivision or description of area","City","State","Zipcode","Phone Number"};
    public static final String [] EXPORTED_NEWADMINUPDATE_COLUMNS ={"Name","Email Address","UpDated Date","Phone Number","Address_Line1","Address_Line2","City","State","Zip"};
    public static final String [] EXPORTED_THIRTYDAYS_COLUMNS ={"Name","Email Address","UpDated Date","Phone Number","Address_Line1","Address_Line2","City","State","Zip"};
    public static final String [] EXPORTED_SIXTYDAYS_COLUMNS ={"Name","Email Address","UpDated Date","Phone Number","Address_Line1","Address_Line2","City","State","Zip"};
    public static final String [] EXPORTED_NINTYDAYS_COLUMNS ={"Name","Email Address","UpDated Date","Phone Number","Address_Line1","Address_Line2","City","State","Zip"};
    public static final String [] EXPORTED_ONEYEAR_COLUMNS ={"Name","Email Address","UpDated Date","Phone Number","Address_Line1","Address_Line2","City","State","Zip"};
    public static final String [] EXPORTED_CONSTANTCONTACT_COLUMNS ={"Email","First Name","Last Name"};
    public static final String [] EXPORTED_RECENTLIST_CONSTANTCONTACT_COLUMNS ={"List Name","Exported Contacts","Created Date"};
    public static final String [] EXPORTED_GROUPSTATISTICALREPORTS_COLUMNS_FOR_COACH ={"New Groups","Recuriting Groups","Total Groups","Users Remove from the groups"};
    public static final String [] EXPORTED_USERSTATISTICALREPORTS_COLUMNS_FOR_COACH ={"Total Registered Users"};
    
    public static final String [] EXPORTED_GROUPS_COLUMNS_IN_LITE = {"Group Name","Group Type","Description","Viewable on Church","Group Status","Address1","Address2","street Name","City","State","Zip Code"};
    
    public static final String [] EXPORTED_PROJECT_COLUMNS = {"Project Name","Client Name","Notes","Create Date"};
    public static final String [] EXPORTED_CLIENT_COLUMNS = {"Client Name","Notes","Create Date"};
    public static final String [] EXPORTED_TASKS_COLUMNS = {"Task Name","Project Name","Notes","Start Date","Create Date"};
    
    public static final String [] EXPORTED_NEIGHBORHOODMEMBERS_COLUMNS = {"First Name","Last Name","Email Address","Phone Number","Address Line1","City","State","Zip Code"};
    public static final String [] EXPORTED_CUSTOMERDETAILS_COLUMNS = {"Name","EmailId","Date Opened","Account Type","Address"};
    
    public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public String[] getColumnHeaders() {
		return columnHeaders;
	}

	public void setColumnHeaders(String[] columnHeaders) {
		this.columnHeaders = columnHeaders;
		this.columnSize= this.columnHeaders.length;
	}

	
	/**
	 * @return the columnHeader
	 */
	public String getColumnHeader() {
		return columnHeader;
	}

	/**
	 * @param columnHeader the columnHeader to set
	 */
	public void setColumnHeader(String columnHeader) {
		this.columnHeader = columnHeader;
	}

	/**
     * @see org.displaytag.export.ExportView#getMimeType()
     * @return "application/vnd.ms-excel"
     */
    public String getMimeType()
    {
        return "application/vnd.ms-excel"; //$NON-NLS-1$
    }

    /**
     * @see org.displaytag.export.BaseExportView#getRowEnd()
     */
    protected String getRowEnd()
    {
        return "\n"; //$NON-NLS-1$
    }

    /**
     * @see org.displaytag.export.BaseExportView#getCellEnd()
     */
    protected String getCellEnd()
    {
        return "\t"; //$NON-NLS-1$
    }

    /**
     * @see org.displaytag.export.BaseExportView#getAlwaysAppendCellEnd()
     * @return false
     */
    protected boolean getAlwaysAppendCellEnd()
    {
        return false;
    }

    /**
     * @see org.displaytag.export.BaseExportView#getAlwaysAppendRowEnd()
     * @return false
     */
    protected boolean getAlwaysAppendRowEnd()
    {
        return false;
    }

    /**
     * Escaping for excel format.
     * <ul>
     * <li>Quotes inside quoted strings are escaped with a double quote</li>
     * <li>Fields are surrounded by " (should be optional, but sometimes you get a "Sylk error" without those)</li>
     * </ul>
     * @see org.displaytag.export.BaseExportView#escapeColumnValue(java.lang.Object)
     */
    protected String escapeColumnValue(Object value)
    {
        if (value != null)
        {
            // quotes around fields are needed to avoid occasional "Sylk format invalid" messages from excel
            return "\"" //$NON-NLS-1$
                + StringUtils.replace(StringUtils.trim(value.toString()), "\"", "\"\"") //$NON-NLS-1$ //$NON-NLS-2$ 
                + "\""; //$NON-NLS-1$ 
        }

        return null;
    }
    
    public void addHeader()
    {
    	// Create the label, specifying content and format
    	try {
    		WritableFont arial10font = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
    		arial10font.setColour(Colour.WHITE);
    		WritableCellFormat wcf = new WritableCellFormat(arial10font);
    		wcf.setBackground(Colour.GREEN);
    		wcf.setWrap(false);
    		wcf.setShrinkToFit(false);
    		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
    		wcf.setAlignment(Alignment.JUSTIFY);
    		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
    		for(int i=0;i<columnSize; i++)
    		{
    			getWs().addCell(new Label(i,0, columnHeaders[i], wcf));
    		}
    		rowSize=1;
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void addHeaderForMergedCells()
    {
    	// Create the label, specifying content and format
    	try {
    		WritableFont arial10font = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
    		arial10font.setColour(Colour.WHITE);
    		WritableCellFormat wcf = new WritableCellFormat(arial10font);
    		wcf.setBackground(Colour.GREEN);
    		wcf.setWrap(false);
    		wcf.setShrinkToFit(false);
    		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
    		wcf.setAlignment(Alignment.CENTRE);
    		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
    		getWs().addCell(new Label(0,0, columnHeader, wcf));
    		/*for(int i=0;i<columnSize; i++)
    		{
    			getWs().addCell(new Label(i,0, columnHeaders[i], wcf));
    		}*/
    		rowSize=1;
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public WritableSheet getWs() {
		if(ws == null)
		{
			this.ws = this.wb.createSheet(workSheetName, 0);
		}
		return ws;
	}

	public void setWs(WritableSheet ws) {
		this.ws = ws;
	}

	public WritableWorkbook getWb() {
		return wb;
	}

	public void setWb(WritableWorkbook wb) {
		this.wb = wb;
	}

    public void createWorkBook(List emailList)
    {
    	try
    	{
	    	
	    	addHeader();
	    	Iterator emailListIterator = emailList.iterator();
	    	for(Iterator objectIterator = emailListIterator;objectIterator.hasNext();)
	    	{
	    		String value = (String)objectIterator.next();
	    		getWs().addCell(new Label(0,rowSize, value, getArial10format()));
	    		rowSize++;
	    	}
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
    public void createWorkSheet(int sheetNumber)
    {
        try
        {
            this.ws = this.wb.createSheet(workSheetName, sheetNumber);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

	public String getWorkSheetName() {
		return workSheetName;
	}

	public void setWorkSheetName(String workSheetName) {
		this.workSheetName = workSheetName;
	}

	public WritableCellFormat getArial10format() {
		return arial10format;
	}

	public void setArial10format(WritableCellFormat arial10format) {
		this.arial10format = arial10format;
	}
    
	/**
	 * @return the wrapCellFont
	 */
	public WritableCellFormat getWrapCellFont() {
		return wrapCellFont;
	}

	/**
	 * @param wrapCellFont the wrapCellFont to set
	 */
	public void setWrapCellFont(WritableCellFormat wrapCellFont) {
		this.wrapCellFont = wrapCellFont;
	}

	public ExcelView() {
    	try {
    		WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10);
    		WritableFont arial10fontWithBold = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
        	this.arial10format = new WritableCellFormat (arial10font);
			this.arial10format.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont arial8Boldfont = new WritableFont(WritableFont.ARIAL,8,WritableFont.BOLD);
			WritableFont arial10Boldfont = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
			this.arial10Boldformat = new WritableCellFormat (arial10Boldfont);
            this.arial10Boldformat.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.arial10Boldformat.setBackground(Colour.LIGHT_BLUE);
            this.user14format = new WritableCellFormat (arial10font);
            this.user14format.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.user14format.setBackground(Colour.LIGHT_TURQUOISE);
            this.user14format.setWrap(true);
            this.user14format.setShrinkToFit(false);
            this.user14format.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.user14format.setAlignment(Alignment.LEFT);
            this.user14format.setVerticalAlignment(VerticalAlignment.TOP);
            this.user30format = new WritableCellFormat (arial10font);
            this.user30format.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.user30format.setBackground(Colour.YELLOW);
            this.user30format.setWrap(true);
            this.user30format.setShrinkToFit(false);
            this.user30format.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.user30format.setAlignment(Alignment.LEFT);
            this.user30format.setVerticalAlignment(VerticalAlignment.TOP);
            this.usermore30format = new WritableCellFormat (arial10font);
            this.usermore30format.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.usermore30format.setBackground(Colour.ROSE);  
            this.usermore30format.setWrap(true);
            this.usermore30format.setShrinkToFit(false);
            this.usermore30format.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.usermore30format.setAlignment(Alignment.LEFT);
            this.usermore30format.setVerticalAlignment(VerticalAlignment.TOP);
            this.wrapCellFormat = new WritableCellFormat(arial10font);
            this.wrapCellFormat.setWrap(true);
            this.wrapCellFormat.setShrinkToFit(false);
            this.wrapCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.wrapCellFormat.setAlignment(Alignment.LEFT);
            this.wrapCellFormat.setVerticalAlignment(VerticalAlignment.TOP);
            this.wrapCellFormatWithBold = new WritableCellFormat(arial10fontWithBold);
            this.wrapCellFormatWithBold.setWrap(true);
            this.wrapCellFormatWithBold.setShrinkToFit(false);
            this.wrapCellFormatWithBold.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.wrapCellFormatWithBold.setAlignment(Alignment.LEFT);
            this.wrapCellFormatWithBold.setVerticalAlignment(VerticalAlignment.TOP);
            this.wrapCellFormatCenter = new WritableCellFormat(arial10font);
            this.wrapCellFormatCenter.setWrap(true);
            //this.wrapCellFormatCenter.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLUE);
            this.wrapCellFormatCenter.setFont(arial10Boldfont);
            this.wrapCellFormatCenter.setShrinkToFit(false);
            //this.wrapCellFormatCenter.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.wrapCellFormatCenter.setAlignment(Alignment.RIGHT);
            this.wrapCellFormatCenter.setVerticalAlignment(VerticalAlignment.BOTTOM);
            this.wrapCellFont = new WritableCellFormat(arial8Boldfont);
            this.wrapCellFont.setWrap(true);
            this.wrapCellFont.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLUE);
            this.wrapCellFont.setFont(arial8Boldfont);
            this.wrapCellFont.setShrinkToFit(false);
            this.wrapCellFont.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.wrapCellFont.setAlignment(Alignment.CENTRE);
            this.wrapCellFont.setVerticalAlignment(VerticalAlignment.TOP);
            WritableFont arial10FontBold = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
            arial10FontBold.setColour(Colour.WHITE);
			this.usermore10BoldformatGreenBgClr = new WritableCellFormat (arial10FontBold);
			this.usermore10BoldformatGreenBgClr.setBackground(Colour.GREEN);
			this.usermore10BoldformatGreenBgClr.setWrap(false);
			this.usermore10BoldformatGreenBgClr.setShrinkToFit(false);
			this.usermore10BoldformatGreenBgClr.setBorder(Border.ALL, BorderLineStyle.THIN);
			this.usermore10BoldformatGreenBgClr.setAlignment(Alignment.JUSTIFY);
			this.usermore10BoldformatGreenBgClr.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont arial10FontBoldRed = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
			arial10FontBoldRed.setColour(Colour.RED);
			this.usermore10BoldformatRedClr = new WritableCellFormat (arial10FontBoldRed);
			this.usermore10BoldformatRedClr.setWrap(false);
			this.usermore10BoldformatRedClr.setShrinkToFit(false);
			this.usermore10BoldformatRedClr.setBorder(Border.ALL, BorderLineStyle.THIN);
			this.usermore10BoldformatRedClr.setAlignment(Alignment.JUSTIFY);
			this.usermore10BoldformatRedClr.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont arial10FontBoldWhite = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
            arial10FontBold.setColour(Colour.WHITE);
			this.usermore10BoldformatWhiteBgClr = new WritableCellFormat (arial10FontBoldWhite);
			this.usermore10BoldformatWhiteBgClr.setBackground(Colour.WHITE);
			this.usermore10BoldformatWhiteBgClr.setWrap(false);
			this.usermore10BoldformatWhiteBgClr.setShrinkToFit(false);
			this.usermore10BoldformatWhiteBgClr.setBorder(Border.ALL, BorderLineStyle.THIN);
			this.usermore10BoldformatWhiteBgClr.setAlignment(Alignment.JUSTIFY);
			this.usermore10BoldformatWhiteBgClr.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont arial10FontBoldWhiteRight = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
            arial10FontBold.setColour(Colour.WHITE);
			this.arial10FontBoldWhiteRight = new WritableCellFormat (arial10FontBoldWhiteRight);
			this.arial10FontBoldWhiteRight.setBackground(Colour.GRAY_25);
			this.arial10FontBoldWhiteRight.setWrap(false);
			this.arial10FontBoldWhiteRight.setShrinkToFit(false);
			this.arial10FontBoldWhiteRight.setBorder(Border.ALL, BorderLineStyle.THIN);
			this.arial10FontBoldWhiteRight.setAlignment(Alignment.RIGHT);
			this.arial10FontBoldWhiteRight.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont defaultFormatRight = new WritableFont(WritableFont.ARIAL,10);
            arial10FontBold.setColour(Colour.WHITE);
			this.defaultFormatRight = new WritableCellFormat (defaultFormatRight);
			this.defaultFormatRight.setBackground(Colour.WHITE);
			this.defaultFormatRight.setWrap(false);
			this.defaultFormatRight.setShrinkToFit(false);
			this.defaultFormatRight.setBorder(Border.ALL, BorderLineStyle.THIN);
			this.defaultFormatRight.setAlignment(Alignment.RIGHT);
			this.defaultFormatRight.setVerticalAlignment(VerticalAlignment.CENTRE);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	
	public WritableCellFormat getDefaultFormatRight() {
		return defaultFormatRight;
	}

	public void setDefaultFormatRight(WritableCellFormat defaultFormatRight) {
		this.defaultFormatRight = defaultFormatRight;
	}

	public WritableCellFormat getArial10FontBoldWhiteRight() {
		return arial10FontBoldWhiteRight;
	}

	public void setArial10FontBoldWhiteRight(WritableCellFormat arial10FontBoldWhiteRight) {
		this.arial10FontBoldWhiteRight = arial10FontBoldWhiteRight;
	}

	public WritableCellFormat getUsermore10BoldformatWhiteBgClr() {
		return usermore10BoldformatWhiteBgClr;
	}

	public void setUsermore10BoldformatWhiteBgClr(
			WritableCellFormat usermore10BoldformatWhiteBgClr) {
		this.usermore10BoldformatWhiteBgClr = usermore10BoldformatWhiteBgClr;
	}

	public WritableCellFormat getUsermore10BoldformatRedClr() {
		return usermore10BoldformatRedClr;
	}

	public void setUsermore10BoldformatRedClr(
			WritableCellFormat usermore10BoldformatRedClr) {
		this.usermore10BoldformatRedClr = usermore10BoldformatRedClr;
	}

	public CellFormat getColumnHeaderformat() {
		return columnHeaderformat;
	}

	public void setColumnHeaderformat(CellFormat columnHeaderformat) {
		this.columnHeaderformat = columnHeaderformat;
	}
	
	/**
	 * @return the usermore10BoldformatGreenBgClr
	 */
	public WritableCellFormat getUsermore10BoldformatGreenBgClr() {
		return usermore10BoldformatGreenBgClr;
	}

	/**
	 * @param usermore10BoldformatGreenBgClr the usermore10BoldformatGreenBgClr to set
	 */
	public void setUsermore10BoldformatGreenBgClr(
			WritableCellFormat usermore10BoldformatGreenBgClr) {
		this.usermore10BoldformatGreenBgClr = usermore10BoldformatGreenBgClr;
	}

	public static void main(String [] args) 
    {
		try
		{
		  WorkbookSettings ws = new WorkbookSettings();
	      ws.setLocale(new Locale("en", "EN"));
	      String filename = "d:/www/testGI.xls";
	      Workbook workbook = Workbook.getWorkbook(new File(filename),ws);
	      Sheet s  = workbook.getSheet(0);
	      
	      log.debug("Rows : " + s.getRows()+ " Columns : " + s.getColumns());
	      Cell b2 = s.getCell(0, 1); 
	      log.debug(" Name1 : " + s.getName()+", Value(0,1) : " + b2.getContents());
	      b2 = s.getCell(1, 1); 
	      log.debug(" Name2 : " + s.getName()+", Value(1,1) : " + b2.getContents());
	      b2 = s.getCell(2, 1); 
	      log.debug(" Name2 : " + s.getName()+", Value(2,1) : " + b2.getContents());
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
    }

    public WritableCellFormat getDefaultFormat() {
        return defaultFormat;
    }

    public void setDefaultFormat(WritableCellFormat defaultFormat) {
        this.defaultFormat = defaultFormat;
    }

    public WritableCellFormat getArial10Boldformat() {
        return arial10Boldformat;
    }

    public void setArial10Boldformat(WritableCellFormat arial10Boldformat) {
        this.arial10Boldformat = arial10Boldformat;
    }
    
    public void writeImageSheet(int col, int row, WritableSheet s, String imageHref)
    {
        try
        {
        File  image = new File(imageHref);
        BufferedImage imageFile = ImageIO.read(image);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imageFile, "PNG", baos);
        s.addImage(new WritableImage(col,row,imageFile.getWidth() / CELL_DEFAULT_WIDTH,
                imageFile.getHeight() / CELL_DEFAULT_HEIGHT,baos.toByteArray()));
        }
        catch(Exception ex)
        {
            ex.getMessage();
        }
    }
    private static final double CELL_DEFAULT_HEIGHT = 17;
    private static final double CELL_DEFAULT_WIDTH = 64;
     
	/**
	 * @return the wrapCellFormatWithBold
	 */
	public WritableCellFormat getWrapCellFormatWithBold() {
		return wrapCellFormatWithBold;
	}

	/**
	 * @param wrapCellFormatWithBold the wrapCellFormatWithBold to set
	 */
	public void setWrapCellFormatWithBold(WritableCellFormat wrapCellFormatWithBold) {
		this.wrapCellFormatWithBold = wrapCellFormatWithBold;
	}

	public WritableCellFormat getWrapCellFormat() {
		return wrapCellFormat;
	}

	public void setWrapCellFormat(WritableCellFormat wrapCellFormat) {
		this.wrapCellFormat = wrapCellFormat;
	}
     
	/**
	 * @return the wrapCellFormatCenter
	 */
	public WritableCellFormat getWrapCellFormatCenter() {
		return wrapCellFormatCenter;
	}

	/**
	 * @param wrapCellFormatCenter the wrapCellFormatCenter to set
	 */
	public void setWrapCellFormatCenter(WritableCellFormat wrapCellFormatCenter) {
		this.wrapCellFormatCenter = wrapCellFormatCenter;
	}

	/**
	 * @return the user14format
	 */
	public WritableCellFormat getUser14format() {
		return this.user14format;
	}

	/**
	 * @param user14format the user14format to set
	 */
	public void setUser14format(WritableCellFormat user14format) {
		this.user14format = user14format;
	}

	/**
	 * @return the user30format
	 */
	public WritableCellFormat getUser30format() {
		return this.user30format;
	}

	/**
	 * @param user30format the user30format to set
	 */
	public void setUser30format(WritableCellFormat user30format) {
		this.user30format = user30format;
	}

	/**
	 * @return the usermore30format
	 */
	public WritableCellFormat getUsermore30format() {
		return this.usermore30format;
	}

	/**
	 * @param usermore30format the usermore30format to set
	 */
	public void setUsermore30format(WritableCellFormat usermore30format) {
		this.usermore30format = usermore30format;
	}
	
	public static final String [] EXPORTED_NEWGROUPSREPORTS_COLUMNS1 ={"Group Name","Group Type","Description","Meeting Time/Day","Meeting Frequency","Group Status","Location","Meeting Address"};

	public static final String [] EXPORTED_RECRUITINGGROUPSREPORTS_COLUMNS1 ={"Group Name","Group Type","Description","Meeting Time/Day","Meeting Frequency","Group Status","Location","Recruiting Stops On(Date)","Meeting Address"};
    public static final String [] EXPORTED_GROUPNOTES_COLUMNS = {"Group Name","Note Title","Note Description","Created Date"};

    public static WritableCellFormat getUserFormattedCell(WritableFont font ,Colour color,boolean wrap,boolean shrinkToFit,Alignment alignment,VerticalAlignment verticalAlignment,Border border,BorderLineStyle borderLineStyle)
    {
    	WritableCellFormat userFormatted= new WritableCellFormat (font);
    	try
        {
        	userFormatted.setBackground(color);
    		userFormatted.setWrap(wrap);
    		userFormatted.setShrinkToFit(shrinkToFit);
    		userFormatted.setBorder(border, borderLineStyle);
    		userFormatted.setAlignment(alignment);
    		userFormatted.setVerticalAlignment(verticalAlignment);
        }catch (Exception e) {
        	e.printStackTrace();
			// TODO: handle exception
		}
        return userFormatted;
    }
}
