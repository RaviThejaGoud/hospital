package com.urt.persistence.model.study;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "studentQuestionAnswer")
public class StudentQuestionAnswer  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected long custId; 
    protected long studentId;
    protected long questionId;
    protected String studentAnswer;
    protected String correctAnswer;
    protected String studentCorrectAnswer;
    
    
    
    
    
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
	 * @return the questionId
	 */
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

	public StudentQuestionAnswer() {
        this.createdDate=DateFunctions.getTodayPlusNdays(0);
        this.lastUpdatedDate=DateFunctions.getTodayPlusNdays(0);
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
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
	@Transient
	public String getSectionName()
	{
		 StringBuffer ret = new StringBuffer(10);
		 ret.append("Section ");
		 
		return ret.toString();
	}

    @Override
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
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
    

  

