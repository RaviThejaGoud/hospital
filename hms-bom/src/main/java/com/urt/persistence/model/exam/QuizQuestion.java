package com.urt.persistence.model.exam;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 * 
 */
@Entity
@Table(name = "quizQuestion")
public class QuizQuestion extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	private long custId;
	private String questionName;
	private long teacherId;
	private long quizId;
	private String status;
	private Date startDate;
	private Date endDate;
	protected Set<QuestionAnswer> answerList;
	
	public QuizQuestion() {

	}

	public QuizQuestion(long id) {
		setId(id);
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
	public int compareTo(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	
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
	 * @return the questionName
	 */
	@Column(name = "questionName", nullable = true, length = 1024)
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
	 * @return the teacherId
	 */
	public long getTeacherId() {
		return teacherId;
	}

	/**
	 * @param teacherId the teacherId to set
	 */
	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
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
	 * @return the status
	 */
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(1) default 'A'")
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
	 * @return the answerList
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="questionId")
	public Set<QuestionAnswer> getAnswerList() {
		return answerList;
	}

	/**
	 * @param answerList the answerList to set
	 */
	public void setAnswerList(Set<QuestionAnswer> answerList) {
		this.answerList = answerList;
	}
	
	public void addQuesationAnswers(QuestionAnswer questionAnswer) {
		if(ObjectFunctions.isNullOrEmpty(this.getAnswerList())){
			this.answerList=new HashSet<QuestionAnswer>();
		}
		this.answerList.add(questionAnswer);
	}
	@Transient
    public String getStartDateStr() {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,this.startDate);

    }
	@Transient
    public String getEndDateStr() {
        return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN,this.endDate);

    }
}
