package com.urt.service.manager.impl.sports;

import java.io.File;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.AttachmentVO;
import com.hyniva.sms.ws.vo.StaffVO;
import com.hyniva.sms.ws.vo.sports.AchievementVO;
import com.hyniva.sms.ws.vo.sports.PlayersVO;
import com.hyniva.sms.ws.vo.sports.SportsVO;
import com.hyniva.sms.ws.vo.sports.TeamVO;
import com.hyniva.sms.ws.vo.sports.TournamentVO;
import com.urt.persistence.interfaces.sports.SportsDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.Attachment;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.sports.Achievement;
import com.urt.persistence.model.sports.Sport;
import com.urt.persistence.model.sports.Team;
import com.urt.persistence.model.sports.TeamPlayers;
import com.urt.persistence.model.sports.Tournament;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.manager.interfaces.sports.SportsManager;
@Component
public class SportsManagerImpl extends UniversalManagerImpl implements SportsManager{

	
	@Autowired
	private SportsDao sportsDao;
	@Autowired
	private AdminManager adminManager;
	
	/*
	 *adding or updating the sports
	 * @see com.urt.service.manager.interfaces.sports.SportsManager#addOrUpdateSports(com.hyniva.sms.ws.vo.sports.SportsVO, com.urt.persistence.model.user.User)
	 */
	public int addOrUpdateSports(SportsVO sportsVO, User user) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'AddOrUpdatesports' method");
		}
		try {
			Sport sport = null;
				if ( !ObjectFunctions.isNullOrEmpty(sportsVO) && sportsVO.getId() > 0) {
					sport = (Sport) this.get(Sport.class, sportsVO.getId());
				} else {
					sport = new Sport();
					sport.setCreatedById(user.getId());
					sport.setCreatedDate(new Date());
				}
				sportsVO.setCustId(user.getCustId());
				sport.copyFromVoToEntity(sportsVO);
				sport.setLastUpdatedDate(new Date());
				sport.setLastUpdatedById(user.getId());
				if (!ObjectFunctions.isNullOrEmpty(sportsVO.getCoachIds())) {
					List<Staff> coachList = super.getAll(Staff.class,"id in ("+ StringFunctions.convertListToCommaDelimitedString(sportsVO.getCoachIds()) + ")");
					sport.setCoachList(coachList);
				}
				this.merge(sport);
				sport=null;
			
			if(sportsVO.getId() >0){
				return 1;
			}
			else
				return 2;
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			sportsVO=null;
		}
			return 0;
	}
	/*
	 * (non-Javadoc) getting the sportslist
	 * @see com.urt.service.manager.interfaces.sports.SportsManager#getSportsList(java.lang.Long)
	 */
	public  List<SportsVO> getSportsList(Long custId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getSportsList' method");
		}
		try {
			List<SportsVO> sportsVOList=new ArrayList<SportsVO>();
			List<Sport> sportList=super.getAll(Sport.class," custId= " + custId );
			SportsVO sportsVO=null;
			if(ObjectFunctions.isNotNullOrEmpty(sportList)){
				for(Sport sport:sportList){
					StringBuffer buffer=new StringBuffer();
					sportsVO = sport.copyFromEntityToVo();
					if(!ObjectFunctions.isNullOrEmpty(sport.getCoachList())){
						for(Staff coach:sport.getCoachList()){
							StaffVO staffVO=new StaffVO();
							staffVO.setId(coach.getId());
							staffVO.getStaffAccountVo().setFullName(coach.getAccount().getFullPersonName());
							sportsVO.getCoachList().add(staffVO);
							staffVO=null;
						}
					}
					sportsVOList.add(sportsVO);
					sportsVO=null;
				}
			}
			return sportsVOList;	
		} catch (Exception ex) {
			ex.printStackTrace();		
			}
	return null;
	} 
	/*
	 * getting sports List by Id for editing.
	 * @see com.urt.service.manager.interfaces.sports.SportsManager#getSportsDetailsById(long)
	 */
	public SportsVO getSportsDetailsById(long sportsId){
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getSportsDetailsById' method");
		}
		try {
			SportsVO sportsVO=null;
			Sport sport = (Sport) this.get(Sport.class,sportsId);
			if(!ObjectFunctions.isNullOrEmpty(sport)){
				sportsVO=sport.copyFromEntityToVo();
				if (!ObjectFunctions.isNullOrEmpty(sport) && ObjectFunctions.isNotNullOrEmpty(sport.getCoachList())) {
					for (Staff staff : sport.getCoachList()) {
						sportsVO.getCoachIds().add(String.valueOf(staff.getId()));
						staff=null;
					}
				}
				sport = null;
			}
			return sportsVO;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<SportsVO> getSportsListByName(String sportName, Long userCustId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getSportsListByName' method");
		}try {
			
			List<SportsVO> sportsList=null;
			if (StringFunctions.isNotNullOrEmpty(sportName)) {
				sportsList=this.getAll(Sport.class, " sportName='"+sportName+"' and custId=" + userCustId );
				}
			return sportsList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int removeSports(Long sportsId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'removeSports' method");
		}try{
			if(sportsId > 0){
				List<Team> teamList=super.getAll(Team.class,"sportId="+sportsId);
				if(ObjectFunctions.isNullOrEmpty(teamList)){
					this.updateQuery("delete from sportsCoach where sportId="+sportsId);
					this.updateQuery("delete from sports where id="+sportsId);
					teamList=null;
					return 1;
				}
				teamList=null;
				return 2;
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		return 0;
	}
	/*
	 * Getting Student List for the search text area
	 */
	
	@Override
	public List<ViewStudentPersonAccountDetails> getAllStudentList( long userCustId, String searchword, String anyTitle) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'AddOrUpdatesports' method");
		}
		try {
			List<ViewStudentPersonAccountDetails> studentsList=null;
			if (!StringFunctions.isNullOrEmpty(searchword)) {
			  studentsList = adminManager.getAll(ViewStudentPersonAccountDetails.class,"custId="+userCustId+" and (fullName like '%"+searchword.trim()+ "%') and accountId not in ("+ anyTitle.substring(0, anyTitle.length() - 1)+ ")  and description is null ");
			}
			 return studentsList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * Adding the Team or updating the team
	 */
	
	@Override
	public int addOrUpdateTeam(TeamVO teamVO, User user,Long custId, Long academicYearId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'AddOrUpdatesports' method");
		}
		try {
			Team team=null;
			TeamPlayers players=null;
			if((!ObjectFunctions.isNullOrEmpty(teamVO.getId())) && teamVO.getId()>0){
				this.removeTeamPlayers(teamVO.getId());				
				team=(Team) this.get(Team.class,teamVO.getId());
				team.setLastUpdatedById(user.getId());
			}
			else{
				team = new Team();
				team.setCreatedById(user.getId());
			}
			teamVO.setCustId(custId);
			teamVO.setAcademicYearId(academicYearId);
			team.copyFromVoToEntity(teamVO);
			if(teamVO.getSportsId() > 0){
				Sport sport= (Sport)this.get(Sport.class,teamVO.getSportsId());
				team.setSports(sport);
			}
			if(!ObjectFunctions.isNullOrEmpty(team)){
					List<Student> playersIds = super.getAll(Student.class,"id in ("+ StringFunctions.convertListToCommaDelimitedString(teamVO.getStudentIds()) + ")");
					if(!ObjectFunctions.isNullOrEmpty(playersIds)){
						for(Student student : playersIds){
							players= new TeamPlayers();
							if(!ObjectFunctions.isNullOrEmpty(student)){
								players.setPlayer(student);
								if(student.getId() > 0){
									if(teamVO.getCaptainId()==student.getId()){
										players.setPlayerStatus("C");	
									}else if(teamVO.getViceCaptainId()==student.getId()){ 
										players.setPlayerStatus("V");
										}
									else {
										players.setPlayerStatus("N");	
									}
								}
							}
							team.addTeamPlayersDetails(players);
						}
					}
					this.save(team);
					team=null;
			}
			if((!ObjectFunctions.isNullOrEmpty(teamVO.getId())) && teamVO.getId()>0){
				return 1;
			}
			else
				return 2;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			teamVO=null;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)Getting the teamList
	 * @see com.urt.service.manager.interfaces.sports.SportsManager#getTeamList(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<TeamVO> getTeamList(Long custId, Long academicYearId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getTeamList' method");
		}
	try {
			List objectList = new ArrayList<TeamVO>();
			List<Team> teamList=this.getAll(Team.class, " custId = "+ custId + " and academicYearId ="+ academicYearId );
			TeamVO teamVO=null;
			if(!ObjectFunctions.isNullOrEmpty(teamList)){
				for(Team team:teamList){
					teamVO = team.copyFromEntityToVo();
					if(!ObjectFunctions.isNullOrEmpty(team.getSports())){
							Sport sport =(Sport) this.get(Sport.class,team.getSports().getId());
							if(!ObjectFunctions.isNullOrEmpty(sport)){
								teamVO.setSportName(sport.getSportName());
								teamVO.setSportsId(team.getSports().getId());
								teamVO.getTeamWithSport();
								if(!ObjectFunctions.isNullOrEmpty(team.getTeamPlayers())){
									for(TeamPlayers teamPlayer:team.getTeamPlayers()){
										PlayersVO playersVo=new PlayersVO();
										if(!ObjectFunctions.isNullOrEmpty(teamPlayer)){
											playersVo.setPlayerId(teamPlayer.getPlayer().getId());
											playersVo.setPlayerName(teamPlayer.getPlayer().getAccount().getFullPersonName());
											playersVo.setPlayerStatus(teamPlayer.getPlayerStatus());
										}
										if("C".equalsIgnoreCase(teamPlayer.getPlayerStatus())){
											teamVO.getPlayersList().add(0, playersVo);
										}else if("V".equalsIgnoreCase(teamPlayer.getPlayerStatus())){
											teamVO.getPlayersList().add(0, playersVo);
										}else
											teamVO.getPlayersList().add(playersVo);
									}
								objectList.add(teamVO);
								teamVO=null;
								}
								sport=null;
								team=null;
					  }
					}
				}
			}
			return objectList;	
		} catch (Exception ex) {
			ex.printStackTrace();		
			}
		return null;
	}
	/*
	 * Getting the TeamDetailsById
	 */
	@Override
	public TeamVO getTeamDetailsById(Long teamId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getTeamDetailsById' method");
		}
		try {
			TeamVO teamVO=null;
			PlayersVO playersVO=null;
			Team team= (Team) this.get(Team.class,teamId);
			if(!ObjectFunctions.isNullOrEmpty(team)){
				teamVO=team.copyFromEntityToVo();
					if(!ObjectFunctions.isNullOrEmpty(team.getSports())){
					Sport sport =(Sport) this.get(Sport.class,team.getSports().getId());
						if(!ObjectFunctions.isNullOrEmpty(sport)){
							teamVO.setSportName(sport.getSportName());
							teamVO.setSportsId(team.getSports().getId());
						}
					}	
					if(!ObjectFunctions.isNullOrEmpty(team.getTeamPlayers())){
							for(TeamPlayers teamPlayer:team.getTeamPlayers()){
								PlayersVO playersVo=new PlayersVO();
								if(!ObjectFunctions.isNullOrEmpty(teamPlayer)){
									playersVo.setPlayerId(teamPlayer.getPlayer().getId());
									playersVo.setPlayerName(teamPlayer.getPlayer().getAccount().getFullPersonName());
									playersVo.setPlayerStatus(teamPlayer.getPlayerStatus());
								}
								if("C".equalsIgnoreCase(teamPlayer.getPlayerStatus())){
									teamVO.getPlayersList().add(0, playersVo);
								}else if("V".equalsIgnoreCase(teamPlayer.getPlayerStatus())){
									teamVO.getPlayersList().add(0, playersVo);
									}else
									teamVO.getPlayersList().add(playersVo);
							}
						}
			}
			return teamVO;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	/*
	 * removing the teamOlayers
	 */
	private void removeTeamPlayers(Long teamId) {
		this.updateQuery("delete from teamPlayers where teamId="+teamId);		
	}
	
	@Override
	public void removeTeam(Long teamId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'ajaxAddorUpdateTournament' method");
		}
		try{
			if(teamId > 0){
			this.updateQuery("delete from teamPlayers where teamId="+teamId);
			this.updateQuery("delete from team where id="+teamId);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)Adding or updating the tournament
	 * @see com.urt.service.manager.interfaces.sports.SportsManager#ajaxAddorUpdateTournament(com.hyniva.sms.ws.vo.sports.TournamentVO, com.urt.persistence.model.common.AcademicYear)
	 */
	public int ajaxAddorUpdateTournament(TournamentVO tournamentVO,AcademicYear academicYear ) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'ajaxAddorUpdateTournament' method");
		}
		try{
			Tournament tournament = null;
			Address address=null;
			Attachment attachment=null;
			List<Attachment> attachmentList=null;
			String academicYearStr=academicYear.getAcademicYear();
			if(!ObjectFunctions.isNullOrEmpty(tournamentVO.getTournamentId()) && tournamentVO.getTournamentId()> 0){
				tournament = (Tournament) this.get(Tournament.class,tournamentVO.getTournamentId());
				address=(Address) this.get(Address.class,tournament.getAddress().getId());
				attachmentList= this.getAll(Attachment.class, "tournamentId="+tournament.getId());
			}else{
				tournament = new Tournament();
				address=new Address();
			}
				tournament.copyFromVoToEntity(tournamentVO);
				tournament.setAcademicYear(academicYear);
				address = address.copyFromVoToEntity(address, tournamentVO.getAddressVO());
				this.save(address);
				tournament.setAddress(address);
				if(!ObjectFunctions.isNullOrEmpty(tournamentVO)){
				Team team=(Team) this.get(Team.class,"id ="+ tournamentVO.getTeamVO().getId());
				tournament.setTeam(team);
					/*if(!ObjectFunctions.isNullOrEmpty(tournamentVO.getTournamentId()) && tournamentVO.getTournamentId()> 0){
							if(tournamentVO.getFileUpload().size()!=0){
								for(Attachment attach:attachmentList){
									if(StringFunctions.isNotNullOrEmpty(tournamentVO.getFileUploadFileName().toString().replace("[]", "")))
									this.updateQuery("delete from attachment where tournamentId="+tournamentVO.getTournamentId());
								}
							}
					}*/	
					 if(!ObjectFunctions.isNullOrEmpty(tournamentVO.getFileUpload().get(0))){
						for(int i=0;i<tournamentVO.getFileUpload().size();i++){
							attachment = addOrUploadAttachments(tournamentVO.getFileUpload().get(i),academicYearStr,tournamentVO.getFileUploadFileName().get(i));
							tournament.addAttachments(attachment);
						}
					}
				}
			this.saveOrUpdateObject(tournament);
			tournament=null;
			if(tournamentVO.getTournamentId()>0){
				return 1;
			}
			else
				return 2;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			tournamentVO=null;
		}
		return 0;
	}

	@Override
	public List<TournamentVO> getTournamentList(Long custId, Long academicYearId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getTournamentList' method");
		}try {
			List objectList = new ArrayList<TournamentVO>();
			List<Tournament> tournamentList=this.getAll(Tournament.class, " custId = "+ custId + " and academicYearId ="+ academicYearId);
			TournamentVO tournamentVO=null;
			if(!ObjectFunctions.isNullOrEmpty(tournamentList)){
  				for(Tournament tournament:tournamentList){
					tournamentVO = tournament.copyFromEntityToVo();
					tournamentVO.getTeamVO().getSportsVO().setSportName(tournament.getTeam().getSports().getSportName());
					tournamentVO.getTeamVO().getTeamWithSport();
					tournamentVO.getTeamVO().setId(tournament.getTeam().getId());
					tournamentVO.getTeamVO().setTeamName(tournament.getTeam().getTeamName());
					tournamentVO.getAddressVO().setAddressLine1(tournament.getAddress().getAddressLine1());
					tournamentVO.getAddressVO().setAddressLine2(tournament.getAddress().getAddressLine2());
					tournamentVO.getAddressVO().setCity(tournament.getAddress().getCity());
					tournamentVO.getAddressVO().setPostalCode(tournament.getAddress().getPostalCode());
					tournamentVO.getTourLocationAddressDesc();
					tournamentVO.setTournamentId(tournament.getId());
					if(!ObjectFunctions.isNullOrEmpty(tournament.getTeam().getTeamPlayers())){
						for(TeamPlayers teamPlayers:tournament.getTeam().getTeamPlayers()){
							PlayersVO playersVo=new PlayersVO();
							if(!ObjectFunctions.isNullOrEmpty(teamPlayers)){
								playersVo.setPlayerId(teamPlayers.getPlayer().getId());
								playersVo.setPlayerName(teamPlayers.getPlayer().getAccount().getFullPersonName());
								playersVo.setPlayerStatus(teamPlayers.getPlayerStatus());
							}
							if("C".equalsIgnoreCase(teamPlayers.getPlayerStatus())){
								tournamentVO.getTeamVO().getPlayersList().add(0,playersVo);
							}else if("V".equalsIgnoreCase(teamPlayers.getPlayerStatus())){
								tournamentVO.getTeamVO().getPlayersList().add(0,playersVo);
							}else
							tournamentVO.getTeamVO().getPlayersList().add(playersVo);
							playersVo=null;
						}
						List<Attachment> attachmentList=this.getAll(Attachment.class,"tournamentId="+ tournament.getId());
						if(!ObjectFunctions.isNullOrEmpty(attachmentList)){
							for(Attachment attachment:attachmentList){
								AttachmentVO attachmentVO=new AttachmentVO();
								attachmentVO.setFileName(attachment.getFileName());
								attachmentVO.setFilePath(attachment.getFilePath());
								attachmentVO.setId(attachment.getId());
								tournamentVO.getAttachmentsVO().add(attachmentVO);
								attachmentVO=null;
							}
					}
					}
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date cuurentDate=DateFunctions.getTodayPlusNdays(0);
					Date todayWithZeroTime = formatter.parse(formatter.format(cuurentDate));
					if(todayWithZeroTime.after(tournamentVO.getEndDate())){
						tournamentVO.setTourDeleteStatus("D");
					}
					objectList.add(tournamentVO);
					tournamentVO=null;
				}
			}
			return objectList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TournamentVO getTournamentDetailsById(Long tournamentId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getTeamDetailsById' method");
		}
		try {
			TournamentVO tournamentVO=null;
			PlayersVO playersVO=null;
			Tournament tournament= (Tournament) this.get(Tournament.class,tournamentId);
			if(!ObjectFunctions.isNullOrEmpty(tournament)){
				tournamentVO=tournament.copyFromEntityToVo();
				tournamentVO.setTournamentName(tournament.getTournamentName());
				tournamentVO.getTeamVO().setId(tournament.getTeam().getId());
				tournamentVO.getAddressVO().setAddressLine1(tournament.getAddress().getAddressLine1());
				tournamentVO.getAddressVO().setAddressLine2(tournament.getAddress().getAddressLine2());
				tournamentVO.getAddressVO().setCity(tournament.getAddress().getCity());
				tournamentVO.getAddressVO().setPostalCode(tournament.getAddress().getPostalCode());
				tournamentVO.getAddressVO().setState(tournament.getAddress().getState());
				List<Attachment> attachmentList=this.getAll(Attachment.class,"tournamentId="+ tournament.getId());
				if(!ObjectFunctions.isNullOrEmpty(attachmentList)){
					for(Attachment attachment:attachmentList){
						AttachmentVO attachmentVO=new AttachmentVO();
						attachmentVO.setFileName(attachment.getFileName());
						attachmentVO.setFilePath(attachment.getFilePath());
						attachmentVO.setId(attachment.getId());
						tournamentVO.getAttachmentsVO().add(attachmentVO);
						attachmentVO=null;
					}
				}
			}
			return tournamentVO;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void removeTournament(TournamentVO tournamentVO) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'addOrUpdateAchievement' method");
		}
		try {
			if(tournamentVO.getAddressVO().getId() >0){
			 	this.remove(Address.class,"id="+tournamentVO.getAddressVO().getId());
			 	tournamentVO.getAddressVO().setId((Long)null);
			 }
			 this.remove("attachment", "tournamentId=" +tournamentVO.getTournamentId());
			 this.remove(Tournament.class ,tournamentVO.getTournamentId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public int addOrUpdateAchievement(AcademicYear academicYear, User user, AchievementVO achievementVO) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'addOrUpdateAchievement' method");
		}
		try {
				Achievement achievement=null;
				Attachment attachment=null;
				Team team=null;
				List<Attachment> attachmentList=null;
				String academicYearStr=academicYear.getAcademicYear();
				if(!ObjectFunctions.isNullOrEmpty(achievementVO.getId()) && achievementVO.getId()> 0){
					achievement= (Achievement) this.get(Achievement.class, achievementVO.getId());
				}else{
					achievement= new Achievement();
					achievement.setCreatedById(user.getId());
					achievement.setCreatedDate(new Date());
				}
				achievement.copyFromVoToEntity(achievementVO);
				achievement.setAcademicYear(academicYear);
				if(!ObjectFunctions.isNullOrEmpty(achievementVO.getTeamVO())){
					team=(Team) this.get(Team.class,"id ="+ achievementVO.getTeamVO().getId());
					achievement.setTeam(team);
				//	int indexCOunt=0;
					Tournament tournament=(Tournament) this.get(Tournament.class,"id ="+ achievementVO.getTournamentVO().getTournamentId());
					achievement.setTournament(tournament);
						if(!ObjectFunctions.isNullOrEmpty(achievementVO.getFileUpload().get(0))){
						for(int i=0;i<achievementVO.getFileUpload().size();i++){
							attachment = addOrUploadAttachments(achievementVO.getFileUpload().get(i),academicYearStr,achievementVO.getFileUploadFileName().get(i));
							achievement.addAttachments(attachment);
						//	indexCOunt++;
						}
						
					}
				}
				this.saveOrUpdateObject(achievement);
				if(!ObjectFunctions.isNullOrEmpty(achievement)){
					achievement=null;
					return 1;
				}
				else
					return 2;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			achievementVO=null;
		}
		return 0;
	}

	@Override
	public List<AchievementVO> getAchievementList(Long custId,Long  academicYearId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getAchievementList' method");
		}
		try {
			List objectList = new ArrayList<AchievementVO>();
			List<Achievement> achievementList=this.getAll(Achievement.class, " custId = "+ custId + " and academicYearId ="+ academicYearId );
			AchievementVO achievementVO=null;
			if(!ObjectFunctions.isNullOrEmpty(achievementList)){
				for(Achievement achievement:achievementList){
					achievementVO= achievement.copyFromEntityToVo();
					achievementVO.getTournamentVO().setTournamentId(achievement.getTournament().getId());
					achievementVO.getTeamVO().getSportsVO().setSportName(achievement.getTeam().getSports().getSportName());
					achievementVO.getTeamVO().setTeamName(achievement.getTeam().getTeamName());
					achievementVO.getTournamentVO().setTournamentName(achievement.getTournament().getTournamentName());
					if(!ObjectFunctions.isNullOrEmpty(achievement.getTeam().getTeamPlayers())){
						for(TeamPlayers teamPlayers:achievement.getTeam().getTeamPlayers()){
							PlayersVO playersVo=new PlayersVO();
							playersVo.setPlayerId(teamPlayers.getPlayer().getId());
							playersVo.setPlayerStatus(teamPlayers.getPlayerStatus());
							playersVo.setPlayerName(teamPlayers.getPlayer().getAccount().getFullPersonName());
							/*Object[] playerObj  = this.get("SELECT studentId,fullName from vw_studentDetails where studentId="+teamPlayers.getPlayer().getId());
							playersVo.setPlayerId(Long.valueOf(playerObj[0].toString()));
							playersVo.setPlayerName(playerObj[1].toString());
							playersVo.setPlayerStatus(teamPlayers.getPlayerStatus());*/
							if("C".equalsIgnoreCase(teamPlayers.getPlayerStatus())){
								achievementVO.getTeamVO().getPlayersList().add(0,playersVo);
							}else if("V".equalsIgnoreCase(teamPlayers.getPlayerStatus())){
								achievementVO.getTeamVO().getPlayersList().add(0,playersVo);
							}else
								achievementVO.getTeamVO().getPlayersList().add(playersVo);
							playersVo=null;
						}
					}
					List<Attachment> attachmentList=this.getAll(Attachment.class,"achievementId="+ achievement.getId());
						if(!ObjectFunctions.isNullOrEmpty(attachmentList)){
							for(Attachment attachment:attachmentList){
								AttachmentVO attachmentVO=new AttachmentVO();
								if(!ObjectFunctions.isNullOrEmpty(attachment)){
									attachmentVO.setFileName(attachment.getFileName());
									attachmentVO.setFilePath(attachment.getFilePath());
									attachmentVO.setId(attachment.getId());
								}
								achievementVO.getAttachmentVOs().add(attachmentVO);
								attachmentVO=null;
							}
						}
					objectList.add(achievementVO);
					achievementVO=null;
				}
			}
			return objectList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<AttachmentVO> getAttachmentsList(Long achievemetnId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getAchievementList' method");
		}try {
			List AttachmentVOList = new ArrayList<AttachmentVO>();
			if(achievemetnId>0){
				 List<Attachment> attachmentList=this.getAll(Attachment.class,"achievementId="+ achievemetnId);
					if(!ObjectFunctions.isNullOrEmpty(attachmentList)){
						for(Attachment attachment:attachmentList){
							AttachmentVO attachmentVO=new AttachmentVO();
							if(!ObjectFunctions.isNullOrEmpty(attachment)){
								attachmentVO.setFileName(attachment.getFileName());
								attachmentVO.setFilePath(attachment.getFilePath());
								attachmentVO.setId(attachment.getId());
							}
							AttachmentVOList.add(attachmentVO);
						}
					}
			}
			return AttachmentVOList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List getStateList() {
		List stateList=adminManager.getAll(State.class);
		return stateList;
	}

	@Override
	public AchievementVO getAchievementById(Long achievementId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getAchievementById' method");
		}try {
			AchievementVO achievementVO=new AchievementVO();
			if(achievementId > 0)
				achievementVO.setId(achievementId);
		return achievementVO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * Uploading the achievement photos once the achievement added when the initially if they didn't add.
	 */
	@Override
	public int addOrUploadAchievementPhotos(AcademicYear academicYear, AchievementVO achievementVO) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'addOrUploadAchievementPhotos' method");
		}
		try{
		Achievement achievement=null;
		Attachment attachment=null;
		String academicYearStr=academicYear.getAcademicYear();
		if(!ObjectFunctions.isNullOrEmpty(achievementVO.getId()) && achievementVO.getId()> 0){
			achievement= (Achievement) this.get(Achievement.class, achievementVO.getId());
		}
		if(!ObjectFunctions.isNullOrEmpty(achievementVO)){
			if(achievementVO.getFileUpload().size()!=0){
				for(int i=0;i<achievementVO.getFileUpload().size();i++){
				attachment = addOrUploadAttachments(achievementVO.getFileUpload().get(i),academicYearStr,achievementVO.getFileUploadFileName().get(i));
				achievement.addAttachments(attachment);
				}
			}
		}
		this.saveOrUpdateObject(achievement);
		if(!ObjectFunctions.isNullOrEmpty(achievementVO.getId()) && achievementVO.getId()> 0){
			achievement=null;
			return 1;
		}
	}catch (Exception e) {
		e.printStackTrace();
		}finally{
			achievementVO=null;
		}
		return 0;
	}
	
	/*
	 *  Uploading the files into attachment
	 */
	
	public Attachment addOrUploadAttachments(File fileUpload,String academicYearStr, String fileUploadFileName){
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'addOrUploadAchievementPhotos' method");
		}try {
			Attachment attachment=null;
			if(!ObjectFunctions.isNullOrEmpty(fileUpload)){
						if(!StringFunctions.isNullOrEmpty(fileUploadFileName)){
						//String fileName=fileUploadFileName;
							String filePath =  adminManager.getUploadDocuments(fileUpload, academicYearStr, fileUploadFileName);
							if(!StringFunctions.isNullOrEmpty(filePath))
			    			{	
								attachment=new Attachment();
								attachment.setFileName(fileUploadFileName);
								attachment.setFilePath(filePath);
			    			}
					}
			}
			return attachment;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
		public void removeTournamentAttachments(Long attachmentId) {
			if (log.isDebugEnabled()) {
				log.debug("Entering SportsManager 'addOrUpdateAchievement' method");
			}try{
				if(attachmentId> 0){
					this.updateQuery("delete from attachment where id ="+ attachmentId);
				}
				attachmentId=null;
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	@Override
	public Object getStaffById(Long accountId) {
		if (log.isDebugEnabled()) {
			log.debug("Entering SportsManager 'getStaffById' method");
		}try{
			if(accountId> 0){
				Object[]  staffId=this.get("select id,accountId from staff where accountId="+accountId);
				if(!ObjectFunctions.isNullOrEmpty(staffId)){
					Object[]  coachId=this.get("select * from sportsCoach where coachId="+staffId[0]);	
					if(!ObjectFunctions.isNullOrEmpty(coachId))
						return coachId[1];
					}
				}
		}catch(Exception ex){
		ex.printStackTrace();
		}
	return null;
	}

	
}





