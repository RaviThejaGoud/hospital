package com.urt.service.manager.interfaces.sports;

import java.util.List;

import com.hyniva.sms.ws.vo.AttachmentVO;
import com.hyniva.sms.ws.vo.sports.AchievementVO;
import com.hyniva.sms.ws.vo.sports.SportsVO;
import com.hyniva.sms.ws.vo.sports.TeamVO;
import com.hyniva.sms.ws.vo.sports.TournamentVO;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.base.UniversalManager;

public interface SportsManager extends UniversalManager {

	int addOrUpdateSports(SportsVO sports,User user);

	List<SportsVO> getSportsList(Long custId);
	
	SportsVO getSportsDetailsById(long sportsId);

	int addOrUpdateTeam(TeamVO teamVO, User user,Long custId, Long academicYearId);
	
	List<TeamVO> getTeamList(Long custId, Long academicYearId);
	
	int ajaxAddorUpdateTournament(TournamentVO tournamentVO, AcademicYear academicYear);

	TeamVO getTeamDetailsById(Long id);

	List<TournamentVO> getTournamentList(Long CustId, Long academicYearId);

	TournamentVO getTournamentDetailsById(Long tournamentId);

	int addOrUpdateAchievement(AcademicYear academicYear, User user, AchievementVO achievementVO);

	List<AchievementVO> getAchievementList(Long userCustId, Long  academicYearId);

	int removeSports(Long sportsId);

	List<SportsVO> getSportsListByName(String sportName, Long userCustId);

	List<ViewStudentPersonAccountDetails> getAllStudentList(long userCustId, String searchword, String anyTitle);

	void removeTeam(Long teamId);

	List getStateList();

	void removeTournament(TournamentVO tournamentVO);

	List<AttachmentVO> getAttachmentsList(Long tempId);

	AchievementVO getAchievementById(Long achievementId);

	int addOrUploadAchievementPhotos(AcademicYear academicYear, AchievementVO achievementVO);

	void removeTournamentAttachments(Long attachmentId);

	Object getStaffById(Long id);
}
