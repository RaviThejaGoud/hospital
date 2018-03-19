/**
 * 
 */
package com.urt.util.excel.student;

/**
 * @author sreeram
 */

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class ExcelReport {

	/**
	 * Always make sure the positions and query matches. Correct the Class and
	 * Section positions. This is used to create a drop down list for classes
	 * and sections
	 */
	private static final Log log = LogFactory.getLog(ExcelReport.class);
	private String[] titles;

	private Map<String, CellStyle> styles;
	private Workbook wb;

	public ExcelReport() {
		this.wb = new HSSFWorkbook();
		this.styles = createStyles(wb);
	}

	/** @return the wb */
	public Workbook getWb() {
		return this.wb;
	}

	/**
	 * @param wb
	 *            the wb to set
	 */
	public void setWb(Workbook wb) {
		this.wb = wb;
	}
	private static final Integer[] colwidths = { 20,20,20,20,20,20,20,20,20,20,20,20,20,20,25,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,25,20,20,20,20,20,25,25};
	/*private void createSheetTitle(Row titleRow, String title) {
		try {
			titleRow.setHeightInPoints(45);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue(title);
			titleCell.setCellStyle(styles.get("title"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
*/
	private void createSheetHeader(Row headerRow) {
		try {
			headerRow.setHeightInPoints(40);
			Cell headerCell;
			for (int i = 0; i < titles.length; i++) {
				headerCell = headerRow.createCell(i);
				/**  * @Description 8rd Apr cvs: Modularization for case sensitive   */
				if("aadharNumber".equalsIgnoreCase(titles[i])){
					headerCell.setCellValue("Aadhaar Card No");
				}else if("bplStatus".equalsIgnoreCase(titles[i])){
					headerCell.setCellValue("Below Poverty Line (BPL)");
				}else if("rteStatus".equalsIgnoreCase(titles[i])){
					headerCell.setCellValue("RTE Student");
				}else if("phId".equalsIgnoreCase(titles[i])){
					headerCell.setCellValue("Student Disability");
				}else if("physicallyHandicappedDesc".equalsIgnoreCase(titles[i])){
					headerCell.setCellValue("Disability Details");
				}else
					headerCell.setCellValue(WordUtils.capitalize(titles[i]));
				headerCell.setCellStyle(styles.get("header"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}

	}

	public void createSheet(String sheetName, List<Object[]> objects,int feilds,String sheetTitleDesc) {

		try {
			log.debug(feilds);
			HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
			sheet.createFreezePane(7, 2);
			log.debug("sheet name : " + sheetName);
			PrintSetup printSetup = sheet.getPrintSetup();
			printSetup.setLandscape(true);
			sheet.setFitToPage(true);
			sheet.setHorizontallyCenter(true);
			int rownum = 0;
			if(!StringFunctions.isNullOrEmpty(sheetTitleDesc)){
				createSheetTitle(sheet.createRow(rownum++), sheetTitleDesc);
				sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$CR$1"));
			}
			createSheetHeader(sheet.createRow(rownum++));
			sheet.setAutoFilter(new CellRangeAddress(rownum - 1, rownum - 1, 0, titles.length - 1));

			Row row;
			int colNum = 0;
			if (!ObjectFunctions.isNullOrEmpty(objects)) {
				for (Object[] obj : objects) {
					row = sheet.createRow(rownum++);
					wb.getSheetAt(0).autoSizeColumn(colNum++);
					writeRow(obj, row);
				}
			}else{
				row=sheet.createRow(rownum++);
				org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
				cell.setCellValue("No RTE students found for this class");
			}
			 org.apache.poi.ss.usermodel.Cell cell = null;	
		     CellStyle hlink_style = wb.createCellStyle();
		     Font hlink_font = wb.createFont();
		     hlink_font.setUnderline(Font.U_SINGLE);
		     hlink_font.setColor(IndexedColors.BLUE.getIndex());
		     hlink_style.setAlignment(hlink_style.ALIGN_RIGHT);
		     hlink_style.setFont(hlink_font);
		 	 HSSFHyperlink url_link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
		 	 url_link.setAddress("http://www.EazySchool.com");
             cell = sheet.createRow(rownum).createCell(0);
             sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,0,feilds));
             cell.setCellValue("Generated by : www.EazySchool.com");         
             cell.setHyperlink(url_link);
             cell.setCellStyle(hlink_style);
             
             sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
 		    for (int i = 0; i < colwidths.length; i++) {
 		    	sheet.setColumnWidth(i, colwidths[i] * 256);
 		    }
			
			// finally set column widths, the width is measured in units of
			// 1/256th of a character width
			// sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
			// for (int i = 0; i < colwidths.length; i++) {
			// sheet.setColumnWidth(i, colwidths[i] * 256);
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	
	public void createAdmissionsSheet(String sheetName, List<Object[]> objects) {

		try {

			HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
			sheet.createFreezePane(7, 2);
			log.debug("sheet name : " + sheetName);
			PrintSetup printSetup = sheet.getPrintSetup();
			printSetup.setLandscape(true);
			sheet.setFitToPage(true);
			sheet.setHorizontallyCenter(true);
			int rownum = 0;
			//createSheetTitle(sheet.createRow(rownum++), "School Name : Academic Year objects");
			createSheetHeader(sheet.createRow(rownum++));
			//sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AQ$1"));
			sheet.setAutoFilter(new CellRangeAddress(rownum - 1, rownum - 1, 0, titles.length - 1));

			Row row;
			if (!ObjectFunctions.isNullOrEmpty(objects)) {
				for (Object[] obj : objects) {
					row = sheet.createRow(rownum++);
					writeRow(obj, row);
				}
			}
			// finally set column widths, the width is measured in units of
			// 1/256th of a character width
			// sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
			// for (int i = 0; i < colwidths.length; i++) {
			// sheet.setColumnWidth(i, colwidths[i] * 256);
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	
	
	
	

	private void writeRow(Object[] object, Row row) {

		try {
			int colnum = 0;
			Cell cell;
			if (!ObjectFunctions.isNullOrEmpty(object)) {
				for (Object obj : object) {
					cell = row.createCell(colnum);
					cell.setCellStyle(styles.get("string"));

					if (ObjectFunctions.isNullOrEmpty(obj)) {
						cell.setCellValue("");
					} else if (obj instanceof String || obj instanceof BigInteger || obj instanceof Integer) {
						cell.setCellValue((String) obj.toString());
					} else if (obj instanceof Date || obj instanceof Timestamp) {
						cell.setCellValue((Timestamp) obj);
						cell.setCellStyle(styles.get("date"));
					} else if (obj instanceof Double) {
						cell.setCellValue((Double) obj);
					} else {
						cell.setCellValue(obj.toString());
					}
					colnum++;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}

	/** Create a library of cell styles */
	private static Map<String, CellStyle> createStyles(Workbook wbl) {

		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		try {
			CellStyle style;
			Font titleFont = wbl.createFont();
			titleFont.setFontHeightInPoints((short) 18);
			titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style = wbl.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style.setFont(titleFont);
			styles.put("title", style);

			Font monthFont = wbl.createFont();
			monthFont.setFontHeightInPoints((short) 11);
			monthFont.setColor(IndexedColors.WHITE.getIndex());
			style = wbl.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setFont(monthFont);
			style.setWrapText(true);
			style.setRightBorderColor(IndexedColors.WHITE.getIndex());
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setTopBorderColor(IndexedColors.WHITE.getIndex());
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setBottomBorderColor(IndexedColors.WHITE.getIndex());
			style.setBorderBottom(CellStyle.BORDER_THIN);
			styles.put("header", style);

			style = wbl.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setWrapText(true);
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			styles.put("cell", style);

			style = wbl.createCellStyle();
			style.setWrapText(true);
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			styles.put("string", style);

			CreationHelper createHelper = wbl.getCreationHelper();
			style = wbl.createCellStyle();
			style.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setWrapText(true);
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			styles.put("date", style);

		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}

		return styles;
	}
	
	 private void createSheetTitle(Row titleRow, String title) {
			try {
			    titleRow.setHeightInPoints(45);
			    Cell titleCell = titleRow.createCell(0);
			    titleCell.setCellValue(title);
			    titleCell.setCellStyle(styles.get("title"));
			} catch (Exception ex) {
			    ex.printStackTrace();
			    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
			}
		    }
	 
	/** @return the titles */
	public String[] getTitles() {
		return this.titles;
	}

	/**
	 * @param titles
	 *            the titles to set
	 */
	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	public void assignTitles(String title)
	{
		this.titles = title.split(",");
	}
	public String getMimeType() {
		return "application/vnd.ms-excel"; //$NON-NLS-1$
	}
	/**
	 * This method is used to created student details report with class core subjects and language subjects.
	 * @param sheetName
	 * @param objects
	 * @param feilds
	 * @param sheetTitleDesc
	 * @param isLangSubject
	 * @param isCoreSubject
	 * @param subjects
	 */
	public void createSheetWithSubjects(String sheetName, List<Object[]> objects,int feilds,String sheetTitleDesc,boolean isLangSubject, boolean isCoreSubject, Object[] subjects) {

		try {
			HSSFSheet sheet = (HSSFSheet) wb.createSheet(sheetName);
			sheet.createFreezePane(7, 2);
			log.debug("sheet name : " + sheetName);
			PrintSetup printSetup = sheet.getPrintSetup();
			printSetup.setLandscape(true);
			sheet.setFitToPage(true);
			sheet.setHorizontallyCenter(true);
			int rownum = 0;
			if(!StringFunctions.isNullOrEmpty(sheetTitleDesc)){
				createSheetTitle(sheet.createRow(rownum++), sheetTitleDesc);
				sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$CR$1"));
			}
			createSheetHeader(sheet.createRow(rownum++));
			sheet.setAutoFilter(new CellRangeAddress(rownum - 1, rownum - 1, 0, titles.length - 1));

			Row row;
			int colNum = 0;
			if (!ObjectFunctions.isNullOrEmpty(objects)) {
				wb.getSheetAt(0).autoSizeColumn(colNum++);
				for (Object[] obj : objects) {
					row = sheet.createRow(rownum++);
						writeRow(obj, row,subjects);
				}
			}else{
				row=sheet.createRow(rownum++);
				org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
				cell.setCellValue("No RTE students found for this class");
			}
			 org.apache.poi.ss.usermodel.Cell cell = null;	
		     CellStyle hlink_style = wb.createCellStyle();
		     Font hlink_font = wb.createFont();
		     hlink_font.setUnderline(Font.U_SINGLE);
		     hlink_font.setColor(IndexedColors.BLUE.getIndex());
		     hlink_style.setAlignment(hlink_style.ALIGN_RIGHT);
		     hlink_style.setFont(hlink_font);
		 	 HSSFHyperlink url_link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
		 	 url_link.setAddress("http://www.EazySchool.com");
             cell = sheet.createRow(rownum).createCell(0);
             sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,0,feilds));
             cell.setCellValue("Generated by : www.EazySchool.com");         
             cell.setHyperlink(url_link);
             cell.setCellStyle(hlink_style);
             
             sheet.setColumnWidth(0, 30 * 256); // 30 characters wide
 		    for (int i = 0; i < colwidths.length; i++) {
 		    	sheet.setColumnWidth(i, colwidths[i] * 256);
 		    }
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
	
	private void writeRow(Object[] object, Row row,Object[] object1) {

		try {
			int colnum = 0;
			Cell cell;
			if (!ObjectFunctions.isNullOrEmpty(object)) {
				for (Object obj : object) {
					cell = row.createCell(colnum);
					cell.setCellStyle(styles.get("string"));

					if (ObjectFunctions.isNullOrEmpty(obj)) {
						cell.setCellValue("");
					} else if (obj instanceof String || obj instanceof BigInteger || obj instanceof Integer) {
						cell.setCellValue((String) obj.toString());
					} else if (obj instanceof Date || obj instanceof Timestamp) {
						cell.setCellValue((Timestamp) obj);
						cell.setCellStyle(styles.get("date"));
					} else if (obj instanceof Double) {
						cell.setCellValue((Double) obj);
					} else {
						cell.setCellValue(obj.toString());
					}
					colnum++;
				}
			}
			if (!ObjectFunctions.isNullOrEmpty(object1)) {
				for (Object obj : object1) {
					cell = row.createCell(colnum);
					cell.setCellStyle(styles.get("string"));

					if (ObjectFunctions.isNullOrEmpty(obj)) {
						cell.setCellValue("");
					} else if (obj instanceof String || obj instanceof BigInteger || obj instanceof Integer) {
						cell.setCellValue((String) obj.toString());
					} else if (obj instanceof Date || obj instanceof Timestamp) {
						cell.setCellValue((Timestamp) obj);
						cell.setCellStyle(styles.get("date"));
					} else if (obj instanceof Double) {
						cell.setCellValue((Double) obj);
					} else {
						cell.setCellValue(obj.toString());
					}
					colnum++;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
	}
}
