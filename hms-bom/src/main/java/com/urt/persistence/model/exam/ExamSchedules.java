/**
 * 
 */
package com.urt.persistence.model.exam;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.StudySubject;

/**
 * @author urtuser
 *
 */
@Entity
@Table(name = "examSchedules")
public class ExamSchedules extends PersistentObject{

	private static final long serialVersionUID = 1L;
	
	private long custId;
	private StudyClass classSection;
    private StudySubject classSectionSubject;
    private ExamTypes examType;
    private Date examDate;
    private String startTime;
    private String endTime;
    private AcademicYear academicYear;
    private SubType subType;
    //protected List<Syllabus> syllabus;
    protected double attendancePercentage;
    protected int totalWorkingDays;
    protected int totalPresentDays;
    protected int totalAbsentDays;
    protected long totalAttendancePercentage;
    protected String currentYear;
    //private long classId;
    protected String monthName;
    private boolean scheduled;
    private double maxMarks;
    private Date startDate;
    private Date endDate; 
    private String syllabus;
    protected double attTotalPresentDays;
    protected double attTotalAbsentDays;
    /*protected String showInHT;
    
    Ganesh : This columns we will use to show in halll ticket because few clients asking they will create exam shedules but they don't want to show this sheduled subject into hall ticket. If any subject with status N it will not show in hall ticket.
    @Column(name = "showInHT", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    public String getShowInHT() {
		return showInHT;
	}

	public void setShowInHT(String showInHT) {
		this.showInHT = showInHT;
	}*/

	@Transient
    public double getAttTotalPresentDays() {
		return attTotalPresentDays;
	}

	public void setAttTotalPresentDays(double attTotalPresentDays) {
		this.attTotalPresentDays = attTotalPresentDays;
	}
	@Transient
	public double getAttTotalAbsentDays() {
		return attTotalAbsentDays;
	}

	public void setAttTotalAbsentDays(double attTotalAbsentDays) {
		this.attTotalAbsentDays = attTotalAbsentDays;
	}
    
    /**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "maxMarks",nullable = false,columnDefinition="double")
	public double getMaxMarks() {
		return maxMarks;
	}
	
	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}
	@Column(name = "scheduled", nullable = true, length = 1,columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
	public boolean isScheduled() {
		return scheduled;
	}
	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}
    
   /* @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "scheduleSyllabus",
       joinColumns = { @JoinColumn(name = "scheduleId") },
       inverseJoinColumns = { @JoinColumn(name = "syllabusId") })
    public List<Syllabus> getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(List<Syllabus> syllabus) {
		this.syllabus = syllabus;
	}
	*/
	/*public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}*/
	
	@OneToOne
    @JoinColumn(name = "classSubjectId",insertable=true, updatable=true)
    public StudySubject getClassSectionSubject() {
		return classSectionSubject;
	}
	public void setClassSectionSubject(StudySubject classSectionSubject) {
		this.classSectionSubject = classSectionSubject;
	}
	
	@OneToOne
    @JoinColumn(name = "classSectionId",insertable=true, updatable=true)
	public StudyClass getClassSection() {
		return this.classSection;
	}
	public void setClassSection(StudyClass classSection) {
		this.classSection = classSection;
	}
	
	@OneToOne
    @JoinColumn(name = "examTypeId",insertable=true, updatable=true)
	public ExamTypes getExamType() {
		return examType;
	}
	public void setExamType(ExamTypes examType) {
		this.examType = examType;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "academicYearId",insertable=true, updatable=true)
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
    
	@OneToOne
    @JoinColumn(name = "subTypeId",insertable=true, updatable=true)
	public SubType getSubType() {
		return subType;
	}

	public void setSubType(SubType subType) {
		this.subType = subType;
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
	 * @return the examDate
	 */
	public Date getExamDate() {
		return examDate;
	}
	/**
	 * @param examDate the examDate to set
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 
	 */
	public ExamSchedules() {
		// TODO Auto-generated constructor stub
		this.totalAbsentDays=0;
		this.totalAttendancePercentage=0;
		this.totalPresentDays=0;
		this.totalWorkingDays=0;
		this.attendancePercentage=0;
	}


	/**Changed by seshu on 30th April. Sort based on class section sorting order and exam type sorting order.*/
	@Override
	public int compareTo(Object object) {
		ExamSchedules target = (ExamSchedules) object;
		if(!ObjectFunctions.isNullOrEmpty(target) && !ObjectFunctions.isNullOrEmpty(target.getClassSection()) && !ObjectFunctions.isNullOrEmpty(target.getClassSection().getClassNameClassId()) 
				&& !ObjectFunctions.isNullOrEmpty(this)  && !ObjectFunctions.isNullOrEmpty(this.getClassSection()) && !ObjectFunctions.isNullOrEmpty(this.getClassSection().getClassNameClassId())){
			if(this.getClassSection().getClassNameClassId().getSortingOrder() > target.getClassSection().getClassNameClassId().getSortingOrder()){
				return 1;
			}else if(this.getClassSection().getClassNameClassId().getSortingOrder() == target.getClassSection().getClassNameClassId().getSortingOrder()){
				if(this.getClassSection().getSection().compareTo(target.getClassSection().getSection()) > 0){
					return 1;
				}else if(this.getClassSection().getSection().compareTo(target.getClassSection().getSection()) == 0){
					if(!ObjectFunctions.isNullOrEmpty(this.getExamType()) && !ObjectFunctions.isNullOrEmpty(target.getExamType())){
						if(this.getExamType().getSortingOrder() >= target.getExamType().getSortingOrder())
							return 1;
						else
							return 0;
					}
					return 1;
				}
			}
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	@Transient
	public double getAttendancePercentage() {
		return attendancePercentage;
	}
	public void setAttendancePercentage(double attendancePercentage) {
		this.attendancePercentage = attendancePercentage;
	}
	@Transient
	public int getTotalWorkingDays() {
		return totalWorkingDays;
	}
	public void setTotalWorkingDays(int totalWorkingDays) {
		this.totalWorkingDays = totalWorkingDays;
	}
	@Transient
	public int getTotalPresentDays() {
		return totalPresentDays;
	}
	public void setTotalPresentDays(int totalPresentDays) {
		this.totalPresentDays = totalPresentDays;
	}
	@Transient
	public int getTotalAbsentDays() {
		return totalAbsentDays;
	}
	public void setTotalAbsentDays(int totalAbsentDays) {
		this.totalAbsentDays = totalAbsentDays;
	}
	@Transient
	public long getTotalAttendancePercentage() {
		return totalAttendancePercentage;
	}
	public void setTotalAttendancePercentage(long totalAttendancePercentage) {
		this.totalAttendancePercentage = totalAttendancePercentage;
	}
	@Transient
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	@Transient
	public String getMonthName() {
		return this.monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	@Transient
	public long getStudySubjectId(){
		if(ObjectFunctions.isNullOrEmpty(getClassSectionSubject()))
			return 0;
		else
			return getClassSectionSubject().getId();
	}
	@Transient
	public long getExamTypeId(){
		if(ObjectFunctions.isNullOrEmpty(getExamType()))
			return 0;
		else
			return getExamType().getId();
	}
	@Transient
    public String getStartDateStr() {
		if(ObjectFunctions.isNullOrEmpty(this.getStartDate()))
			return "";
		else
         return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getStartDate());
    }
	@Transient
    public String getEndDateStr() {
	    if(ObjectFunctions.isNullOrEmpty(this.getEndDate()))
	    	return "";
	    else
	    	return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getEndDate());
    }
	@Transient
	public long getAcademicYearId(){
		if(ObjectFunctions.isNullOrEmpty(this.getAcademicYear()))
			return 0;
		else
			return this.getAcademicYear().getId();
	}
	@Transient
	public long getClassSectionId(){
		if(ObjectFunctions.isNullOrEmpty(this.classSection))
			return 0;
		else
			return this.classSection.getId();
	}
	@Transient
	public String getClassAndSection(){
		if(ObjectFunctions.isNullOrEmpty(this.classSection))
			return "";
		else
			return this.classSection.getClassAndSection();
	}
	@Transient
	public String getExamTypeName(){
		if(ObjectFunctions.isNullOrEmpty(this.examType))
			return "";
		else
			return this.examType.getExamType();
	}
	@Transient
    public String getStartExamDateStr() {
		if(ObjectFunctions.isNullOrEmpty(this.getStartDate()))
			return "";
		else
			return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getStartDate());
    }
	@Transient
    public String getExamEndDateStr() {
		if(ObjectFunctions.isNullOrEmpty(this.getEndDate()))
			return "";
		else
            return DateFormatter.formatDate(DateFormatter.MMDDYYYY_PATTERN, this.getEndDate());
    }
	@Transient
	public String getSubjectName(){
		if(ObjectFunctions.isNullOrEmpty(this.classSectionSubject))
			return "";
		else
			return classSectionSubject.getName(); 
	}
	@Transient
	public String getSubTypeName(){
		if(ObjectFunctions.isNullOrEmpty(this.getSubType()))
			return "";
		else
			return this.getSubType().getSubTypeName();
	}
	@Transient
    public String getExamDateStr() {
            return DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, this.getExamDate());
    }
	@Transient
	public boolean isClassHigherStandard(){
		if(!ObjectFunctions.isNullOrEmpty(this.classSection) && !ObjectFunctions.isNullOrEmpty(this.classSection.getClassNameClassId()))
			return this.classSection.getClassNameClassId().isHigherStandard();
		return false;
	}
	@Transient
	public boolean isHigherStandard(){
		if(!ObjectFunctions.isNullOrEmpty(this.classSection) && !ObjectFunctions.isNullOrEmpty(this.classSection.getClassNameClassId()))
			return this.classSection.getClassNameClassId().isHigherStandard();
		else
			return false; 
	}
	
	@Column(name = "syllabus",nullable = true,columnDefinition="varchar(10244)")
	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}
	@Transient
	public String getExamTypeMaxMarks(){
		if(!ObjectFunctions.isNullOrEmpty(this.examType))
			return this.examType.getMaxMarks();
		else
			return "0";
	}
	@Transient
	public String getSubjectType(){
		if(ObjectFunctions.isNullOrEmpty(this.classSectionSubject))
			return "";
		else
			return classSectionSubject.getSubjectType(); 
	}
	@Transient
    public String getExamTypeIdAndScheduleId(){
    	StringBuffer ids = new StringBuffer();
    	return ids.append(this.getExamTypeId()).append("_").append(this.getId()).toString();
    }
}
