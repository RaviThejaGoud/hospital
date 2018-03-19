package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;

public class StudentLibraryBooksVO extends SMSBaseVO{

	protected List<StudentLibraryBooksDetailsVO> studentLibraryBooksDetailsVO;

	public List<StudentLibraryBooksDetailsVO> getStudentLibraryBooksVOs() {
		if(ObjectFunctions.isNullOrEmpty(this.studentLibraryBooksDetailsVO))
			this.studentLibraryBooksDetailsVO = new ArrayList<StudentLibraryBooksDetailsVO>(); 
		
		return studentLibraryBooksDetailsVO;
	}

	public void setStudentLibraryBooksVOs(List<StudentLibraryBooksDetailsVO> studentLibraryBooksDetailsVO) {
		this.studentLibraryBooksDetailsVO = studentLibraryBooksDetailsVO;
	}

	
}
