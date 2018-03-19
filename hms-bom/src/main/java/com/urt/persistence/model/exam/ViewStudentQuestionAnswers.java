package com.urt.persistence.model.exam;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/********************************************************************
 * Copyright (C) 2005-06 IFS All Rights Reserved
 * 
 * File: ViewClassExamDetails.java
 ******************************************************************** 
 * 
 * Ver Date Name Description ==== ======== ============ ================== 1.0
 * Oct 07, 2010 Ganesh Created /
 ********************************************************************/

@Entity
@Table(name = "vw_studentQuestionAnswers")
public class ViewStudentQuestionAnswers implements Serializable,Cloneable,Comparable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private long questionId;
	private long quizId;
	private long custId;
	private long studentId;
	private String questionName;
	private String studentAnswer;
	private String studentCorrectAnswer;
	private String correctAnswer;
	//private String lastUpdatedBy;
	//protected Date lastUpdatedDate;
	//protected Date createdDate;
	private String status;
	private Date startDate;
	private Date endDate;
	
	
	
	
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the quizId
	 */
	public long getQuizId() {
		return quizId;
	}

	/**
	 * @param quizId the quizId to set
	 */
	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}

	/**
	 * @return the questionId
	 */
	@Id
	public long getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the questionName
	 */
	public String getQuestionName() {
		return questionName;
	}

	/**
	 * @param questionName the questionName to set
	 */
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	

	/**
	 * @return the studentId
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the studentAnswer
	 */
	public String getStudentAnswer() {
		return studentAnswer;
	}

	/**
	 * @param studentAnswer the studentAnswer to set
	 */
	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	/**
	 * @return the studentCorrectAnswer
	 */
	public String getStudentCorrectAnswer() {
		return studentCorrectAnswer;
	}

	/**
	 * @param studentCorrectAnswer the studentCorrectAnswer to set
	 */
	public void setStudentCorrectAnswer(String studentCorrectAnswer) {
		this.studentCorrectAnswer = studentCorrectAnswer;
	}

	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public int compareTo(Object object) {
        return 0;       
    }
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
}
