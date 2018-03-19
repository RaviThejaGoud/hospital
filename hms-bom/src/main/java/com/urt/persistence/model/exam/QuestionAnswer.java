package com.urt.persistence.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 * 
 */
@Entity
@Table(name = "questionAnswer")
public class QuestionAnswer extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	private long custId;
	private String questionAnswer;
	private String correctAnswer;
	private String anserOptions;
	public QuestionAnswer() {
		
	}
	
	public QuestionAnswer(long id) {
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
		QuestionAnswer target = (QuestionAnswer) object;		 
        if (target.getAnserOptions() != null && this.getAnserOptions() != null)
        {
            if(this.getAnserOptions().equalsIgnoreCase(target.getAnserOptions()))
                return 0;
            else 
               return target.getAnserOptions().compareToIgnoreCase(this.getAnserOptions());
        }
        return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

	/**
	 * @return the questionAnswer
	 */
	@Column(name = "questionAnswer", nullable = true, length = 1024)
	public String getQuestionAnswer() {
		return questionAnswer;
	}

	/**
	 * @param questionAnswer the questionAnswer to set
	 */
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	/**
	 * @return the correctAnswer
	 */
	@Column(name = "correctAnswer", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
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
	 * @return the anserOptions
	 */
	@Column(name = "anserOptions", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
	public String getAnserOptions() {
		return anserOptions;
	}

	/**
	 * @param anserOptions the anserOptions to set
	 */
	public void setAnserOptions(String anserOptions) {
		this.anserOptions = anserOptions;
	}
}
