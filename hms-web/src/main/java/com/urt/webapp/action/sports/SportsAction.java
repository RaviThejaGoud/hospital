/********************************************************************
 * Copyright (C) 2017
 * HYNIVA
 * All Rights Reserved 
 *
 * File: SportsAction.java
 ********************************************************************
 *
 *  Ver   Date              Author             Description
 *  ====  ========          ============       ==================
 *  1.0   July 07, 2017      shanth		       Initial Version
/ ********************************************************************/
package com.urt.webapp.action.sports;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.AttachmentVO;
import com.hyniva.sms.ws.vo.StaffVO;
import com.hyniva.sms.ws.vo.sports.AchievementVO;
import com.hyniva.sms.ws.vo.sports.SportsVO;
import com.hyniva.sms.ws.vo.sports.TeamVO;
import com.hyniva.sms.ws.vo.sports.TournamentVO;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Attachment;
import com.urt.persistence.model.sports.Sport;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.webapp.action.base.BaseAction;

@Namespace("/sports")
public class SportsAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	protected SportsVO sportsVO;
	protected List<SportsVO> sportsList;
	protected Sport sport;
	public String autoCheck;
	protected List<TeamVO> teamList;
	protected List<TournamentVO> tourList;
	protected Date currentDate;
	protected TeamVO teamVO;
	protected TournamentVO tournamentVO;
	protected AchievementVO achievementVO;
	protected List<AchievementVO> achievementList;
	protected List<StaffVO> coachList;
	protected Long achievementId;
	protected List<AttachmentVO> attachmentVOList;
	protected Long attachmentId;
	
	

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Long getAchievementId() {
		return achievementId;
	}

	public List<AttachmentVO> getAttachmentVOList() {
		if (ObjectFunctions.isNullOrEmpty(this.attachmentVOList)) {
			this.attachmentVOList = new ArrayList<AttachmentVO>();
		}
		return attachmentVOList;
	}

	public void setAttachmentVOList(List<AttachmentVO> attachmentVOList) {
		this.attachmentVOList = attachmentVOList;
	}

	public void setAchievementId(Long achievementId) {
		this.achievementId = achievementId;
	}

	public List<StaffVO> getCoachList() {
		if (ObjectFunctions.isNullOrEmpty(this.coachList)) {
			this.coachList = new ArrayList<StaffVO>();
		}
		return coachList;
	}

	public void setCoachList(List<StaffVO> coachList) {
		this.coachList = coachList;
	}

	public List<AchievementVO> getAchievementList() {
		if (ObjectFunctions.isNullOrEmpty(this.achievementList)) {
			this.achievementList = new ArrayList<AchievementVO>();
		}
		return achievementList;
	}

	public void setAchievementList(List<AchievementVO> achievementList) {
		this.achievementList = achievementList;
	}

	public AchievementVO getAchievementVO() {
		return achievementVO;
	}

	public void setAchievementVO(AchievementVO achievementVO) {
		this.achievementVO = achievementVO;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public List<TournamentVO> getTourList() {
		if (ObjectFunctions.isNullOrEmpty(this.tourList)) {
			this.tourList = new ArrayList<TournamentVO>();
		}
		return tourList;
	}

	public void setTourList(List<TournamentVO> tourList) {
		this.tourList = tourList;
	}
	
	public List<TeamVO> getTeamList() {
		if (ObjectFunctions.isNullOrEmpty(this.teamList)) {
			this.teamList = new ArrayList<TeamVO>();
		}
		return teamList;
	}

	public void setTeamList(List<TeamVO> teamList) {
		this.teamList = teamList;
	}

	/*public List<TeamVO> getTeamlist() {
		if (ObjectFunctions.isNullOrEmpty(this.teamList)) {
			this.teamList = new ArrayList<TeamVO>();
		}
		return teamList;
	}

	public void setTeamlist(List<TeamVO> teamList) {
		this.teamList = teamList;
	}*/
	
	public String getAutoCheck() {
		return autoCheck;
	}

	public void setAutoCheck(String autoCheck) {
		this.autoCheck = autoCheck;
	}
	
	public SportsVO getSportsVO() {
		if(ObjectFunctions.isNullOrEmpty(this.sportsVO)) {
			this.sportsVO=new SportsVO() ;
		}
		return sportsVO;
	}
	/**
	 * @param sportsVO the sportsVO to set
	 */
	public void setSportsVO(SportsVO sportsVO) {
		this.sportsVO = sportsVO;
	}

	public List<SportsVO> getSportsList() {
		if (ObjectFunctions.isNullOrEmpty(this.sportsList)) {
			this.sportsList = new ArrayList<SportsVO>();
		}
		return sportsList;
	}
	public void setSportsList(List<SportsVO> sportsList) {
		this.sportsList = sportsList;
	}
	
	public Sport getSport() {
		if (ObjectFunctions.isNullOrEmpty(this.sport)) {
			this.sport = new Sport();
		}
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	
	/**Team getters
	 * @return the teamVO
	 */
	public TeamVO getTeamVO() {
		if (ObjectFunctions.isNullOrEmpty(this.sportsList)) {
			this.sportsList = new ArrayList<SportsVO>();
		}
		return teamVO;
	}

	/**
	 * @param teamVO the teamVO to set
	 */
	public void setTeamVO(TeamVO teamVO) {
		this.teamVO = teamVO;
	}
	public TournamentVO getTournamentVO() {
		if(ObjectFunctions.isNullOrEmpty(this.tournamentVO)) {
			this.tournamentVO=new TournamentVO();
		}
		return this.tournamentVO;
	}
	public void setTournamentVO(TournamentVO tournamentVO) {
		this.tournamentVO = tournamentVO;
	}

	/**
	 * It contains List of sports in Home page ..on click ManageSport
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions( { @Action(value = "ajaxSportsInformationHome", results = { @Result(location = "ajaxSportsdetails.jsp", name = "success") })})
	public String ajaxSportsInformationHome() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSportsInformationHome' method");
		}
		try {
			setSportsList(sportsManager.getSportsList(getUserCustId()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * Here we adding the new sport on submit it take us to sportsList page of sport
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions( { @Action(value = "ajaxAddSports", results = { @Result(location = "ajaxSportsdetails.jsp", name = "success") }) })
	public String ajaxAddSports() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddSports' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getSportsVO()) ){
				int returnCode = sportsManager.addOrUpdateSports(getSportsVO(),getUser());
				if(returnCode==0)
					super.addActionError("Sports not added. Please contact administrator");
				else if (returnCode==1)
					super.addActionMessage("Sports updated successfully.");
				else if (returnCode==2)
					super.addActionMessage("Sports created successfully.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			ajaxSportsInformationHome();
		}
		return SUCCESS;
	}
	
	/**
	 * adding and editing the sport in 
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions({
			@Action(value = "ajaxGetCoachToSports", results = { @Result(location = "ajaxAddSports.jsp", name = "success") }),
			@Action(value = "ajaxEditSports", results = { @Result(location = "ajaxAddSports.jsp") }) })
	public String ajaxGetCoachToSports() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetCoachToSports' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getSportsVO())) {
				if (getSportsVO().getId() > 0 && !ObjectFunctions.isNullOrEmpty(getSportsVO())) {
					setSportsVO(sportsManager.getSportsDetailsById(getSportsVO().getId()));
				}
			}
			setObjectList(adminManager.getAllStaffList(getUserCustId(),getUserAcademicYearId()));
			//setObjectList(adminManager.getAll(Staff.class, "custId="+ getUserCustId()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * removing the sports 
	 * 
	 */
	@Actions( { @Action(value="ajaxRemoveSports", results = { @Result( location= "ajaxSportsdetails.jsp" )  } ) })
	public String ajaxRemoveSports() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveSports' method");
		}
		try {
			if(getSportsVO().getId()>0){
				int returnCode=sportsManager.removeSports(getSportsVO().getId());
				if(returnCode == 1){
					super.addActionMessage("Sports removed successfully.");
				}else if(returnCode == 2){
					super.addActionError("This sport is already in use. So you cannot delete.");
				}else if(returnCode==0)
					super.addActionError("Sports not added. Please contact administrator");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			ajaxSportsInformationHome();
		}
		return SUCCESS;
	}
	/**
	 *  Team Home page  
	 * @return
	 * @throws URTUniversalException
	 */
	
	@Actions({ @Action (value="ajaxTeamInformationHome", results = { @Result( location="ajaxTeamDetails.jsp" , name="success") })})
	public String ajaxTeamInformationHome() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxTeamInformationHome' method");
		}
		try {
			setTeamList(sportsManager.getTeamList(getUserCustId(),getUserAcademicYearId()));
			Object	coachId = sportsManager.getStaffById(getUser().getId());
			if(!ObjectFunctions.isNullOrEmpty(coachId)){
			  setStaff(adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * //Adding the new team Details
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions({ @Action(value="ajaxAddTeam", results = { @ Result ( location ="ajaxTeamDetails.jsp", name="success" )} ) })
	public String ajaxAddTeam() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddTeam' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getTeamVO())){
				int returnCode = sportsManager.addOrUpdateTeam(getTeamVO(),getUser(),getUserCustId(), getUserAcademicYearId());
				if(returnCode==0)
					super.addActionError("Team not added. Please contact administrator");
				else if (returnCode==1)
					super.addActionMessage("Team updated successfully.");
				else if (returnCode==2)
					super.addActionMessage("Team created successfully.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			ajaxTeamInformationHome();
		}
		return SUCCESS;
	}
	/**
	 * adding the students to the text area and mapping
	 * @return
	 * @throws URTUniversalException
	 */
	 @Actions( { @Action(value = "ajaxSearchStudentName", results = { @Result(type = "json", name = "success", params = {"includeProperties", "objectList.*" }) }) })
		public String ajaxSearchStudentName() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxSearchStudentName' method");
			}
			try {
				String searchword = getParamValue("searchword");
				List playersList=new ArrayList();
				if (!StringFunctions.isNullOrEmpty(searchword)) {
					List<ViewStudentPersonAccountDetails> studentsList = null;
					studentsList= sportsManager.getAllStudentList(getUserCustId(),searchword,getAnyTitle());
					//studentsList = adminManager.getAll(ViewStudentPersonAccountDetails.class,"custId="+getUserCustId()+" and (fullName like '%"+searchword.trim()+ "%') and accountId not in ("+ getAnyTitle().substring(0, getAnyTitle().length() - 1)+ ")  and description is null ");
					JSONArray res = new JSONArray();
					JSONObject j;
					if (!ObjectFunctions.isNullOrEmpty(studentsList)) {
						Collections.sort(studentsList);
							for (ViewStudentPersonAccountDetails studentDetails : studentsList) {
								j = new JSONObject();
								j.put("studentId", studentDetails.getStudentId());
								j.put("fullName", studentDetails.getFullName());
								j.put("className", studentDetails.getClassName());
								j.put("section", studentDetails.getSection());
								res.put(j);
								playersList.add(studentDetails);
							}
					} else {
						j = new JSONObject();
						j.put("studentId", 0);
						j.put("fullName", "No Results Found !!");
						j.put("className", "");
						j.put("section", "");
						res.put(j);
					}
					j = new JSONObject();
					j.put("data", res);
					getResponse().getOutputStream().print(j.toString());
					setObjectList(playersList);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}
	/**
	 * editing the team and setting the sports name to team adding page
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions( { @Action(value = "ajaxGetSportsToTeam", results = { @Result(location = "ajaxAddTeam.jsp", name = "success") }),
				@Action(value = "ajaxEditTeam", results = { @Result(location = "ajaxAddTeam.jsp") })})
	public String ajaxGetSportsToTeam() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetSportsToTeam' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getTeamVO())) {
				if (getTeamVO().getId() > 0 && !ObjectFunctions.isNullOrEmpty(getTeamVO())) {
					setTeamVO(sportsManager.getTeamDetailsById(getTeamVO().getId()));
				}
			}
			setSportsList(sportsManager.getSportsList(getUserCustId()));
			//setSportsList(sportsManager.getAll(Sport.class, "custId="+getUserCustId()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 *  removing the team
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions( { @Action(value="ajaxRemoveTeam", results = { @Result( location= "ajaxTeamDetails.jsp" )  } ) })
	public String ajaxRemoveTeam() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveTeam' method");
		}
		try {
			if(getTeamVO().getId()>0){
				sportsManager.removeTeam(getTeamVO().getId());
				super.addActionMessage("Team removed successfully.");
				/*sportsManager.remove("teamPlayers", " teamId="+ getTeamVO().getId());
				sportsManager.remove(Team.class,getTeamVO().getId());*/
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			ajaxTeamInformationHome();
		}
		return SUCCESS;
	}
	/**
	 * viewing the Tournament home 
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions({ @Action (value="ajaxTournamentInformationHome", results = { @Result( location="ajaxTournamentDetails.jsp" , name="success") })})
	public String ajaxTournamentInformationHome() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxTournamentInformationHome' method");
		}
		try {
			setTourList(sportsManager.getTournamentList(getUserCustId(),getUserAcademicYearId()));
			Object	coachId = sportsManager.getStaffById(getUser().getId());
			if(!ObjectFunctions.isNullOrEmpty(coachId)){
			  setStaff(adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 *  adding the sports list and team List to tournament
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions ({ @Action(value="ajaxDoAddTournament",results = { @Result( location = "ajaxAddTournament.jsp", name="success") }),
				@Action(value = "ajaxEditTournament", results = { @Result(location = "ajaxAddTournament.jsp") })})
	public String ajaxDoAddTournament() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddTournament' method");
		}
		try {
				if (!ObjectFunctions.isNullOrEmpty(getTournamentVO()) ) {
					if (getTournamentVO().getTournamentId() > 0 && !ObjectFunctions.isNullOrEmpty(getTournamentVO())) {
						setTournamentVO(sportsManager.getTournamentDetailsById(getTournamentVO().getTournamentId()));
					}
					setStatesList(sportsManager.getStateList());
					setTeamList(sportsManager.getTeamList(getUserCustId(),getUserAcademicYearId()));
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;	
	}
	/**
	 *  Adding the Tournament and updating the tournament
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions ({ @Action(value="ajaxAddorUpdateTournament",results = { @Result( location = "ajaxTournamentDetails.jsp", name="success") }) })
	public String ajaxAddorUpdateTournament() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddorUpdateTournament' method");
		}
		try {
			getTournamentVO().setCustId(getUserCustId());
			getTournamentVO().setUserId(getUser().getId());
			int returnCode = sportsManager.ajaxAddorUpdateTournament(getTournamentVO(), getCurrentAcademicYear());
			if(returnCode==0)
				super.addActionError("Tournament not added. Please contact administrator");
			else if (returnCode==1)
				super.addActionMessage("Tournament updated successfully.");
			else if (returnCode==2)
				super.addActionMessage("Tournament created successfully.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			ajaxTournamentInformationHome();
		}
		return SUCCESS;	
	}
	/**
	 *  removing the team
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions( { @Action(value="ajaxRemoveTournament", results = { @Result( location= "ajaxTournamentDetails.jsp" )  } ) })
	public String ajaxRemoveTournament() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveTournament' method");
		}
		try {
			if(getTournamentVO().getTournamentId()>0){
				sportsManager.removeTournament(getTournamentVO());
				super.addActionMessage("Tournament removed successfully.");
			}
			/*setCurrentDate(DateFunctions.getTodayPlusNdays(0));
			setTournamentVO(sportsManager.getTournamentDetailsById(getTournamentVO().getTournamentId()));
			if(getCurrentDate().after(getTournamentVO().getStartDate()) && getCurrentDate().before(getTournamentVO().getEndDate())) {
				super.addActionMessage("Cannot be removed Tournament already started.");
			}else if(getCurrentDate().after(getTournamentVO().getEndDate()) )
				super.addActionMessage("Cannot be removed Tournament completed.");
			else{
				if(getTournamentVO().getTournamentId()>0){
					 if(getTournamentVO().getAddressVO().getId()>0){
					 	sportsManager.remove(Address.class,getTournamentVO().getAddressVO().getId());
					 	tournamentVO.getAddressVO().setId((Long)null);
					 }
					sportsManager.remove(Tournament.class ,getTournamentVO().getTournamentId());
					super.addActionMessage("Tournament removed successfully.");
				}
			}*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			ajaxTournamentInformationHome();
		}
		return SUCCESS;
	}
	@Actions( { @Action(value="ajaxRemoveTournamentAttachments", results = { @Result(location = "ajaxAddTournament.jsp") } ) })
	public String ajaxRemoveTournamentAttachments() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveTournamentAttachments' method");
		}try{
			if(getAttachmentId() > 0){
				sportsManager.removeTournamentAttachments(getAttachmentId());
				super.addActionMessage("Tournament Schedule removed successfully.");
			}
			ajaxDoAddTournament();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 *  To download the schedule documents
	 * @return
	 * @throws URTUniversalException
	 */
	 @Actions( { @Action(value = "downloadTournamentAttachments", results = {}) })
	public void downloadTournamentAttachments() throws URTUniversalException{
		if (log.isDebugEnabled()) {
				log.debug("Entering 'downloadTournamentAttachments' method");
			}
		try {
			ZipOutputStream zipOutStream = null;
    		File directory = Files.createTempDirectory("TournamentSchedules").toFile();
    		List<Attachment> tournamentAttachmentList =sportsManager.getAll(Attachment.class,"tournamentId="+ getTempId());
    		if(!ObjectFunctions.isNullOrEmpty(tournamentAttachmentList))
    		{
    			zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
				getResponse().setContentType("application/zip");
				getResponse().addHeader("Content-Disposition", "attachment; filename=TOURNAMENT_SCHEDULE.zip");
				for(Attachment attachmentObj : tournamentAttachmentList)
				{
					URL url = new URL(attachmentObj.getFilePath());
					URLConnection conn = url.openConnection();
					InputStream is = conn.getInputStream();
					String ext = FilenameUtils.getExtension(attachmentObj.getFileName());
					File file = new File(directory,attachmentObj.getFileName());
					FileUtils.copyInputStreamToFile(is, file);
				}
				StringFunctions.zipFiles(directory,zipOutStream);
                FileUtils.deleteDirectory(directory);
    		}
    			zipOutStream.close();
				zipOutStream = null;
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * Sports AchievementInfoHome home page
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions( { @Action(value = "ajaxSportAchievementInfoHome", results = { @Result(location = "ajaxAchievementDetails.jsp", name = "success") }) })
	public String ajaxSportAchievementInfoHome() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSportAchievementInfoHome' method");
		}
		try {
			setAchievementList(sportsManager.getAchievementList(getUserCustId(),getUserAcademicYearId()));
			Object	coachId = sportsManager.getStaffById(getUser().getId());
			if(!ObjectFunctions.isNullOrEmpty(coachId)){
			  setStaff(adminManager.getStaffByAcountId(getUser().getId(),Constants.YES_STRING));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * To set the achievement photos that are added.
	 * @return
	 * @throws URTUniversalException
	 */
	
	@Actions( { @Action(value = "ajaxViewAchievementPhotos", results = { @Result(location = "ajaxViewAchievementPhotos.jsp", name = "success") }) })
	public String ajaxViewAchievementPhotos() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewAchievementPhotos' method");
		}
		try {
			if(getAchievementId()>0){
				setAttachmentVOList(sportsManager.getAttachmentsList(getAchievementId()));
			}	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	 @Actions( { @Action(value = "ajaxDoUploadAchievementPhotos", results = { @Result(location = "ajaxUploadAchievementPhotos.jsp", name = "success") }) })
	public String ajaxDoUploadEventPhotos() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoUploadAchievementPhotos' method");
		}
		try {
			/* if(getAchievementVO().getId() > 0){
				 achievementVO.setId(getAchievementVO().getId());
			 }*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	 
@Actions( {  @Action(value = "ajaxUploadAchievementPhotos", results = { @Result(location = "ajaxAchievementDetails.jsp", name = "success") }) })
	 public String ajaxUploadAchievementPhotos() throws URTUniversalException{
		 if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxUploadAchievementPhotos' method");
			}
		 try {
		  // AcademicYear academicYear = (AcademicYear) sportsManager.get(AcademicYear.class, getUserAcademicYearId());
			   int returnCode= sportsManager.addOrUploadAchievementPhotos(getCurrentAcademicYear(),getAchievementVO());
			   if (returnCode==1)
					super.addActionMessage("Achievement Photos updated successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ajaxSportAchievementInfoHome();
		}
		 return SUCCESS; 
	 }
	 
	/**
	 * 
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions( { @Action(value = "ajaxDoAddAchievements", results = { @Result(location = "ajaxAddAchievements.jsp", name = "success") }) })
	 public String ajaxDoAddAchievements()throws URTUniversalException{
		 if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoAddAchievements' method");
			}
			try {
					setTeamList(sportsManager.getTeamList(getUserCustId(),getUserAcademicYearId()));
					setTourList(sportsManager.getTournamentList(getUserCustId(),getUserAcademicYearId()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return SUCCESS; 
	 }
	/**
	 * checking adding and saving the achievements
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions( { @Action(value = "ajaxAddorUpdateAchievements", results = { @Result(location = "ajaxAchievementDetails.jsp", name = "success") }) })
	public String ajaxAddorUpdateAchievements() throws URTUniversalException{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddorUpdateAchievements' method");
		}
		try {
			getAchievementVO().setCustId(getUserCustId());
			AcademicYear academicYear = (AcademicYear) sportsManager.get(AcademicYear.class, getUserAcademicYearId());
			int returnCode	=sportsManager.addOrUpdateAchievement(academicYear,getUser(),getAchievementVO());
			if(returnCode==0)
				super.addActionError("Achievement not added. Please contact administrator");
			else if (returnCode==1)
				super.addActionMessage("Achievement updated successfully.");
			else if (returnCode==2)
				super.addActionMessage("Achievement created successfully.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			ajaxSportAchievementInfoHome();
		}
		return SUCCESS; 	
	}
	/**
	 * checking the sports Name availablity
	 * @return
	 * @throws URTUniversalException
	 */
	@Actions( { @Action(value = "ajaxCheckSportNameAvailableOrNot", results = { @Result(type = "json", name = "success", params = {
		    "includeProperties", "autoCheck" }) }) })
	    public String ajaxCheckSportNameAvailableOrNot() throws URTUniversalException {
			if (log.isDebugEnabled()) {
			    log.debug("Entering 'ajaxCheckSportNameAvailableOrNot' method");
			}
		try {
		    String sportName = getParamValue("keyWord");
		    if (StringFunctions.isNotNullOrEmpty(sportName)) {
		    	List sportsList = sportsManager.getSportsListByName(sportName,getUserCustId());
				//List sportsList = sportsManager.getAll(Sport.class, " sportName='"+sportName+"' and custId=" + getUserCustId() );
				if (ObjectFunctions.isNullOrEmpty(sportsList)) {
					setAutoCheck("0");
				} else if (sportsList.size() > 0) {
					setAutoCheck("1");
				} else {
					setAutoCheck("0");
				}
				sportsList=null;
		    }
		} catch (Exception ex) {
		    log.error("Entering into 'catch block':" + ex.getMessage());
		    ex.printStackTrace();
		}
		return SUCCESS;
	    }
	
}
