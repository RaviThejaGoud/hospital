package com.urt.persistence.model.study;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFunctions;
import com.urt.persistence.model.base.PersistentObject;

@Entity
@Table(name = "studentQuizResults")
public class StudentQuizResults  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
    
    protected long custId; 
    protected long quizId;
    protected long studentId;
    protected double correctAnswersCount;
    protected double nonCorrectAnswersCount;
    protected long totalQuestions;
    protected double persentege;
    protected String result;
    protected String status;
    
    
    
    
    
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
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
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
	 * @return the correctAnswersCount
	 */
	public double getCorrectAnswersCount() {
		return correctAnswersCount;
	}

	/**
	 * @param correctAnswersCount the correctAnswersCount to set
	 */
	public void setCorrectAnswersCount(double correctAnswersCount) {
		this.correctAnswersCount = correctAnswersCount;
	}

	/**
	 * @return the nonCorrectAnswersCount
	 */
	public double getNonCorrectAnswersCount() {
		return nonCorrectAnswersCount;
	}

	/**
	 * @param nonCorrectAnswersCount the nonCorrectAnswersCount to set
	 */
	public void setNonCorrectAnswersCount(double nonCorrectAnswersCount) {
		this.nonCorrectAnswersCount = nonCorrectAnswersCount;
	}

	/**
	 * @return the totalQuestions
	 */
	public long getTotalQuestions() {
		return totalQuestions;
	}

	/**
	 * @param totalQuestions the totalQuestions to set
	 */
	public void setTotalQuestions(long totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	/**
	 * @return the persentege
	 */
	public double getPersentege() {
		return persentege;
	}

	/**
	 * @param persentege the persentege to set
	 */
	public void setPersentege(double persentege) {
		this.persentege = persentege;
	}

	public StudentQuizResults() {
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
    

  

