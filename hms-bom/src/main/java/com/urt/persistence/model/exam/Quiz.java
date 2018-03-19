package com.urt.persistence.model.exam;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 * 
 */
@Entity
@Table(name = "Quiz")
public class Quiz extends PersistentObject {

	private static final long serialVersionUID = 3832626162173359411L;

	private String title;
	private String description;
	private long custId;
	protected Set<QuizQuestion> quizQuesstionList;
	
	public Quiz() {

	}

	public Quiz(long id) {
		setId(id);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", this.getId()).append("title", this.title).append(
						"description", this.description).toString();

	}

	@Override
	public int compareTo(Object object) {
		Quiz target = (Quiz) object;
		if (target.getTitle() != null && this.getTitle() != null) {
			if (this.getTitle().equalsIgnoreCase(target.getTitle()))
				return 0;
			else
				return target.getTitle().compareToIgnoreCase(this.getTitle());
		}
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

	@Column(name = "title", nullable = true, length = 256)
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", nullable = true, length = 1024)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	/**
	 * @return the quizList
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
 	@JoinColumn(name="quizId")
	public Set<QuizQuestion> getQuizQuestionList() {
		return quizQuesstionList;
	}

	/**
	 * @param quizList the quizList to set
	 */
	public void setQuizQuestionList(Set<QuizQuestion> quizQuesstionList) {
		this.quizQuesstionList = quizQuesstionList;
	}	
}
