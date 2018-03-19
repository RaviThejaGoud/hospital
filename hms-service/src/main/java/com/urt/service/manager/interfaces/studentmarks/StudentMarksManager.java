package com.urt.service.manager.interfaces.studentmarks;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.urt.persistence.model.exam.ExamTypes;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.service.manager.interfaces.base.UniversalManager;
/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="studentMarksManager.java.html"><i>View Source</i></a></p>
 */	

public interface StudentMarksManager  extends UniversalManager {
	
	public void createStudentMarksSheet(Workbook wb,StudyClass studyClass,List<ExamTypes> examTypes,String sheetTitleDesc,List<ViewStudentClassDetails> students,String roundOffMarks);
	public Map<String, CellStyle> createStyles (Workbook wbl);
	public void createSheetTitle(Row titleRow, String title);
	public int createSheetHeader(HSSFSheet sheet,int rownum,List<ExamTypes> examTypes);
	public void writeStudentRow(Object[] object, Row row,String roundOffMarks);
}