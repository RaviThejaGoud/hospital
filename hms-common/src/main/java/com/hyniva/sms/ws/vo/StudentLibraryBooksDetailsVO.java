package com.hyniva.sms.ws.vo;

import java.util.List;

public class StudentLibraryBooksDetailsVO {

	protected long studentAccountId;
	protected List<StudentIssuedBookDetailsVO> issuedBooks;
	public long getStudentAccountId() {
		return studentAccountId;
	}
	public void setStudentAccountId(long studentAccountId) {
		this.studentAccountId = studentAccountId;
	}
	public List<StudentIssuedBookDetailsVO> getIssuedBooks() {
		return issuedBooks;
	}
	public void setIssuedBooks(List<StudentIssuedBookDetailsVO> issuedBooks) {
		this.issuedBooks = issuedBooks;
	}
}