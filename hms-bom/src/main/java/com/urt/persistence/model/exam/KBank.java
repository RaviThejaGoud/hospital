package com.urt.persistence.model.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Attachment;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "kBank")
public class KBank  extends PersistentObject {
	
    private static final long serialVersionUID = 3832626162173359411L;
   
    private String title;
	private String description;
	protected long custId;
	private long classId;
	private long subjectId;
	protected long userId;
	private String schoolLevel;
	private String status;
	private CommonType skillCommonType;
	protected Attachment attachment;
	private KBankType kBankType;
	private String searchKewords;
	private List<KBankComments> kBankCommentsList;
	private List<KBankRating> kBankRatingList;
	private String kBankFavourite;
	protected AcademicYear academicYear;
    
    
	/**
	 * @return the academicYear
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="skillCommonTypeId", insertable=true, updatable=true)
	public CommonType getSkillCommonType() {
		return skillCommonType;
	}
	public void setSkillCommonType(CommonType skillCommonType) {
		this.skillCommonType = skillCommonType;
	}
	/**
	 * @return the academicYear
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="academicYearId", insertable=true, updatable=true)
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	public String getSearchKewords() {
		return searchKewords;
	}
	public void setSearchKewords(String searchKewords) {
		this.searchKewords = searchKewords;
	}
	public KBank() {
        
    }
    public KBank(long id) {
        setId(id);
    }
    
    @Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", this.getId())
        .append("title", this.title)
        .append("description", this.description).toString();
  
	}
    @Override
	public int compareTo(Object object) {
    	KBank target = (KBank) object;		 
        if (target.getTitle() != null && this.getTitle() != null)
        {
            if(this.getTitle().equalsIgnoreCase(target.getTitle()))
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
	
	@Column(name="title", nullable=true, length = 256)
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="description", nullable=true, length = 1024)
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="classId", nullable=true, length = 10)
	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}
	@Column(name = "schoolLevel", nullable = true, length = 1,columnDefinition="char(5) default 'N'")
	public String getSchoolLevel() {
		return schoolLevel;
	}

	public void setSchoolLevel(String schoolLevel) {
		this.schoolLevel = schoolLevel;
	}
	@Column(name = "status", nullable = true, length = 1,columnDefinition="char(5) default 'A'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="attachmentId", insertable=true, updatable=true) 
	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	@ManyToOne
    @JoinColumn(name = "kBankTypeId", referencedColumnName = "id")
	public KBankType getkBankType() {
		return kBankType;
	}
	public void setkBankType(KBankType kBankType) {
		this.kBankType = kBankType;
	}
	@Column(name="custId", nullable=true, length = 10)
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Column(name="userId", nullable=true, length = 10)
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Column(name="subjectId", nullable=true, length = 10)
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	
	@Transient
	public String getCreatedDateStr() {
		try{
			return DateFormatter.formatDate(DateFormatter.MMDDCCYY_PATTERN, this.getCreatedDate());
		}
		catch(Exception ex){
			return "";
		}
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="kBankId") 
	public List<KBankComments> getKBankCommentsList() {
		if (ObjectFunctions.isNullOrEmpty(this.kBankCommentsList)) {
			this.kBankCommentsList = new ArrayList();
		}
		Collections.sort(this.kBankCommentsList);
		return this.kBankCommentsList;
	}
	public void setKBankCommentsList(List<KBankComments> kBankCommentsList) {
		this.kBankCommentsList = kBankCommentsList;
	}
	
	public void addKBankComments(KBankComments kBankComments) {
		if (!ObjectFunctions.isNullOrEmpty(kBankComments)) {
			log.debug("Adding new kBankComments ...");
			kBankComments.setkBankId(this.getId());
			this.getKBankCommentsList().add(kBankComments);
		}
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="kBankId") 
	public List<KBankRating> getKBankRatingList() {
		if (ObjectFunctions.isNullOrEmpty(this.kBankRatingList)) {
			this.kBankRatingList = new ArrayList();
		}
		Collections.sort(this.kBankRatingList);
		return kBankRatingList;
	}
	public void setKBankRatingList(List<KBankRating> kBankRatingList) {
		this.kBankRatingList = kBankRatingList;
	}
	
	public void addKBankRating(KBankRating kBankRating) {
		if (!ObjectFunctions.isNullOrEmpty(kBankRating)) {
			log.debug("Adding new kBankComments ...");
			kBankRating.setkBankId(this.getId());
			this.getKBankRatingList().add(kBankRating);
		}
	}
	@Transient
	public String getKBankFavourite() {
		return kBankFavourite;
	}
	public void setKBankFavourite(String kBankFavourite) {
		this.kBankFavourite = kBankFavourite;
	}
	@Transient
	public int getKBankRateAverage() {
		if (ObjectFunctions.isNullOrEmpty(this.getKBankRatingList())) {
			return 0;
		} else {
			Collections.sort(this.getKBankRatingList());
			KBankRating kBankRating = (KBankRating) this.getKBankRatingList().get(0);
			if (ObjectFunctions.isNullOrEmpty(kBankRating)) {
				return 0;
			} else {
				return kBankRating.getIntAverage();
			}
		}
	}
		
}

