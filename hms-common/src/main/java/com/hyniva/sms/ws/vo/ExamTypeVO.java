package com.hyniva.sms.ws.vo;

public class ExamTypeVO {
	protected long id;
    protected String examType;
    protected int sortingOrder;
    protected String minMarks;
    protected String maxMarks;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	 
	public String getMinMarks() {
		return minMarks;
	}
	public void setMinMarks(String minMarks) {
		this.minMarks = minMarks;
	}
	public String getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}
	public int getSortingOrder() {
		return sortingOrder;
	}
	public void setSortingOrder(int sortingOrder) {
		this.sortingOrder = sortingOrder;
	}
}
