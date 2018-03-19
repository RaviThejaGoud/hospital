package com.urt.webapp.action.alumnee;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.ShareUserActivitiesVO;
import com.urt.exception.base.URTUniversalException;
import com.urt.persistence.model.alumnee.ShareUserActivities;
import com.urt.persistence.model.alumnee.ShareUserActivitiesComments;
import com.urt.persistence.model.alumnee.SocialDiscussions;
import com.urt.persistence.model.alumnee.SocialDiscussionsComments;
import com.urt.persistence.model.user.User;
import com.urt.util.common.RayGunException;
import com.urt.webapp.action.base.BaseAction;

/**
 * Action for Student social activities
 */
@Namespace("/alumnee")
@Actions( {	
	@Action(value = "ajaxDoAddEvents", results = { @Result(location = "ajaxAddNewEvent.jsp", name = "success") }),
	@Action(value = "ajaxDoAddDiscussions", results = { @Result(location = "ajaxAddDiscussions.jsp", name = "success") })
 })

public class AlumneeAction extends BaseAction {
	@Actions( { @Action(value = "dashboard", results = {@Result(location = "dashboard.jsp", name = "success") }) })
	public String dashboard() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'dashboard' method");
	}
	try {
		
		Date aDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
		if(!ObjectFunctions.isNullOrEmpty(getUser()))
			setObjectList(adminManager.getShareUserActivitiesList(getUserCustId(),Constants.POST_TO_PULBLIC,getUser().getId(),aDate));
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxPhotosHome", results = {@Result(location = "photosHome.jsp", name = "success") }) })
	public String ajaxPhotosHome() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxPhotosHome' method");
	}
	try {
		List<ShareUserActivitiesVO> myPostList=adminManager.getShareUserActivitiesList(getUserCustId(),Constants.POST_TO_PULBLIC,getUser().getId(),null);
		List<ShareUserActivitiesVO> otherPostList=adminManager.getShareUserActivitiesList(getUserCustId(),"otherPosts",getUser().getId(),null);
		List<ShareUserActivitiesVO> allPhotosList=new ArrayList<ShareUserActivitiesVO>();
		allPhotosList.addAll(otherPostList);
		allPhotosList.addAll(myPostList);
		if(ObjectFunctions.isNotNullOrEmpty(allPhotosList)){
			ShareUserActivities attachment =null;
			List FileNamesList = new ArrayList();
			for(ShareUserActivitiesVO shareUserActivitiesVO: allPhotosList){
				if(!ObjectFunctions.isNullOrEmpty(shareUserActivitiesVO)){
					if(!ObjectFunctions.isNullOrEmpty(shareUserActivitiesVO.getImagePath())){
					    	File destFile = new File(getSession().getServletContext().getRealPath(shareUserActivitiesVO.getImagePath()));
					    	String[] children = destFile.list();
					        if (children == null) {
					        	FileUtils.deleteDirectory(destFile);
					        } else {
					            for (int i=0; i<children.length; i++) {
					            	attachment = new ShareUserActivities();
					                // Get filename of file or directory
					                String filename = children[i];
					                attachment.setImagePath(shareUserActivitiesVO.getImagePath());
					                attachment.setImageName(filename);
					                FileNamesList.add(attachment);
					            }
					        }
					    	setTempList(FileNamesList);	
					}
				}
			}
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	
	
	@Actions( { @Action(value = "ajaxMyEvents", results = {@Result(location = "viewEvents.jsp", name = "success") }) })
	public String ajaxMyEvents() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxMyEvents' method");
	}
	try {
		setObjectList(adminManager.getShareUserActivitiesList(getUserCustId(),Constants.CHURCH_WIDE_EVENT_TYPE,getUser().getId(),null));
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxSocialHome", results = {@Result(location = "socialHome.jsp", name = "success") }) })
	public String socialHome() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'socialHome' method");
	}
	try {
		ajaxViewAllSocialFriendsStatus();
		ajaxPhotosHome() ;
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxSaveUserSocailStatus", results = {@Result(location = "socialHome.jsp", name = "success") }),
		@Action(value = "ajaxAddNewEvent", results = {@Result(location = "eventsHome.jsp", name = "success") })
	})
	public String ajaxSaveUserSocailStatus() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxSaveUserSocailStatus' method");
	}
	try {
		if(!ObjectFunctions.isNullOrEmpty(getShareUserActivitiesVO()))
		{
			ShareUserActivities shareUserActivities =null;
			if(getTempId2() > 0){
				shareUserActivities=(ShareUserActivities)adminManager.get(ShareUserActivities.class,getTempId2());
			}else{
				 shareUserActivities = new ShareUserActivities();
			}
			if(!StringFunctions.isNullOrEmpty(getShareUserActivitiesVO().getEventName()))
			{
				shareUserActivities.setMessageType(Constants.CHURCH_WIDE_EVENT_TYPE);
				shareUserActivities.setEventName(getShareUserActivitiesVO().getEventName()); 
				shareUserActivities.setEventLocation(getShareUserActivitiesVO().getEventLocation()); 
				shareUserActivities.setEndDateTime(getShareUserActivitiesVO().getEndDateTime());
				shareUserActivities.setStartDateTime(getShareUserActivitiesVO().getStartDateTime());
			}
			else if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(0)))
			    shareUserActivities.setMessageType("P");
			else
				shareUserActivities.setMessageType("S");
			shareUserActivities.setDescription(getShareUserActivitiesVO().getDescription());
			shareUserActivities.setCustId(getUserCustId());
			shareUserActivities.setStatus(Constants.ACTIVE_STATUS);
			shareUserActivities.setMessageDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			shareUserActivities.setVisibleType(getShareUserActivitiesVO().getVisibleType());
			User account =	(User) adminManager.get(User.class,getUser().getId());
			shareUserActivities.setAccount(account);
			shareUserActivities =(ShareUserActivities) adminManager.save(shareUserActivities);
			if("P".equalsIgnoreCase(shareUserActivities.getMessageType())) 
			{
				 StringBuffer srcFileWithDir = new StringBuffer();
			        StringBuffer userDir = new StringBuffer();
			        StringBuffer url = new StringBuffer();
		        	url.append("userFiles").append("/alumnee").append("/socialImages/").append(getUserCustId()).append("/").append(shareUserActivities.getId()).append("/");
		        	userDir.append(getSession().getServletContext().getRealPath(url.toString()));
			        srcFileWithDir.append(userDir.toString());
			        srcFileWithDir.append("/");
			    	for(int i=0;i<getFileUpload().size();i++)
			    	{
			    		 File file = getFileUpload().get(i);
			    		 if(!ObjectFunctions.isNullOrEmpty(file)){
				    		 setUploadFileName(getFileUploadFileName().get(i).replaceAll(" ","_"));
						     setUploadFileName(StringFunctions.stripSymbols(getUploadFileName()));
						     File destDir = new File(srcFileWithDir.toString() + getUploadFileName());
							 FileUtils.copyFile(file, destDir);
			    		 }
			    	}
			        //shareUserActivities.setImageName(getUploadFileName());   
			        shareUserActivities.setImagePath(url.toString());
			        shareUserActivities.setImageType(getUploadContentType());
			       adminManager.save(shareUserActivities);
			}
			account = null;
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		if(!StringFunctions.isNullOrEmpty(getTempString())){
			eventsHome();
			if(getTempId2() > 0)
				super.addActionMessage("Event added successfully.");
			else
			    super.addActionMessage("Event updated successfully.");
		}else{
				super.addActionMessage("Post added successfully.");
			socialHome();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxViewAllSocialFriendsStatus", results = {@Result(location = "ajaxViewAllSocialFriendsStatus.jsp", name = "success") }) })
	public String ajaxViewAllSocialFriendsStatus() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxViewAllSocialFriendsStatus' method");
	}
	try {
		setObjectList(adminManager.getShareUserActivitiesList(getUserCustId(),"AllPosts",getUser().getId(),null));
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxEventsHome", results = {@Result(location = "eventsHome.jsp", name = "success") }),
		@Action(value = "ajaxViewEvents", results = {@Result(location = "eventsHome.jsp", name = "success") })	
	})
	public String eventsHome() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'eventsHome' method");
	}
	try {
		setObjectList(adminManager.getShareUserActivitiesList(getUserCustId(),Constants.CHURCH_WIDE_EVENT_TYPE,0,null));
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxViewEventsDetails", results = {@Result(location = "ajaxViewEventDetails.jsp", name = "success") }),
		@Action(value = "ajaxEditEventDetails", results = {@Result(location = "ajaxAddNewEvent.jsp", name = "success") })
	})
	public String ajaxViewEventsDetails() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxViewEventsDetails' method");
	}
	try {
		if(getShareUserActivitiesVO().getId()>0){
			setShareUserActivitiesVO(adminManager.getShareUserActivitiesById(getShareUserActivitiesVO().getId()));
			ShareUserActivities shareUserActivities = (ShareUserActivities)adminManager.get(ShareUserActivities.class, getShareUserActivitiesVO().getId());
			if(!ObjectFunctions.isNullOrEmpty(shareUserActivities)) {
				setShareUserActivitiesCommentsList(shareUserActivities.getShareUserActivitiesComments());
			}
			shareUserActivities = null;
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxSaveSocialDiscussions", results = {@Result(location = "discussionsHome.jsp", name = "success") })
	})
	public String ajaxSaveSocialDiscussions() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxSaveSocialDiscussions' method");
	}
	try {
		if(!ObjectFunctions.isNullOrEmpty(getSocialDiscussionsVO())) {
			SocialDiscussions socialDiscussions = new SocialDiscussions();
			socialDiscussions.setSubject(getSocialDiscussionsVO().getSubject());
			socialDiscussions.setDescription(getSocialDiscussionsVO().getDescription());
			socialDiscussions.setCategory(getSocialDiscussionsVO().getCategory());
			socialDiscussions.setCustId(getUserCustId());
			socialDiscussions.setStatus(Constants.ACTIVE_STATUS);
			socialDiscussions.setMessageDate(new Date());
			User account =	(User) adminManager.get(User.class,getUser().getId());
			socialDiscussions.setAccount(account);
			socialDiscussions = (SocialDiscussions)adminManager.save(socialDiscussions);
			if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(0)))  {
				 StringBuffer srcFileWithDir = new StringBuffer();
			        StringBuffer userDir = new StringBuffer();
			        StringBuffer url = new StringBuffer();
		        	url.append("userFiles").append("/alumnee").append("/socialDiscussions/").append(getUserCustId()).append("/").append(socialDiscussions.getId()).append("/");
		        	userDir.append(getSession().getServletContext().getRealPath(url.toString()));
			        srcFileWithDir.append(userDir.toString());
			        srcFileWithDir.append("/");
			    	for(int i=0;i<getFileUpload().size();i++) {
			    		 File file = getFileUpload().get(i);
			    		 if(!ObjectFunctions.isNullOrEmpty(file)){
				    		 setUploadFileName(getFileUploadFileName().get(i).replaceAll(" ","_"));
						     setUploadFileName(StringFunctions.stripSymbols(getUploadFileName()));
						     File destDir = new File(srcFileWithDir.toString() + getUploadFileName());
							 FileUtils.copyFile(file, destDir);
			    		 }
			    	}
			    	socialDiscussions.setAttachmentName(getUploadFileName());   
			    	socialDiscussions.setAttachmentPath(url.toString());
			    	socialDiscussions.setAttachmentType(getUploadContentType());
			    	adminManager.save(socialDiscussions);
			        super.addActionMessage("Discussions added successfully.");
			}
			account = null;
			socialDiscussions = null;
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		discussionsHome();
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "discussionsHome", results = {@Result(location = "discussionsHome.jsp", name = "success") }),
		@Action(value = "ajaxDiscussionsHome", results = {@Result(location = "discussionsHome.jsp", name = "success") })
	})
	public String discussionsHome() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'discussionsHome' method");
	}
	try {
		setObjectList(adminManager.getSocialDiscussionsList(getUserCustId(),0));
		
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxMySocialDiscussions", results = {@Result(location = "viewDiscussions.jsp", name = "success") }) })
	public String ajaxMySocialDiscussions() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxMySocialDiscussions' method");
	}
	try {
		setObjectList(adminManager.getSocialDiscussionsList(getUserCustId(),getUser().getId()));
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	
	
	@Actions( { @Action(value = "ajaxViewSocialDiscussions", results = {@Result(location = "ajaxViewSocialDiscussions.jsp", name = "success") }) })
	public String ajaxViewSocialDiscussions() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxViewSocialDiscussions' method");
	}
	try {
		if(getSocialDiscussionsVO().getId()>0){
			setSocialDiscussionsVO(adminManager.getsocialDiscussionsById(getSocialDiscussionsVO().getId()));
			ajaxGetDisComments();
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxAddCommentsForSocailEvent", results = {@Result(location = "ajaxViewEvent.jsp", name = "success") }) })
	public String ajaxAddCommentsForSocailEvent() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxAddCommentsForSocailEvent' method");
	}
	try {
		if(getShareUserActivitiesVO().getId()>0){
			ajaxAddCommentsForSocail();
			setShareUserActivitiesVO(adminManager.getShareUserActivitiesById(getShareUserActivitiesVO().getId()));
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}

	@Actions( { @Action(value = "ajaxAddCommentsForSocail", results = {@Result(location = "ajaxGetComments.jsp", name = "success") }) })
	public String ajaxAddCommentsForSocail() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxAddCommentsForSocail' method");
	}
	try {
		if(!ObjectFunctions.isNullOrEmpty(getShareUserActivitiesCommentsVO())) {
			ShareUserActivities shareUserActivities = (ShareUserActivities)adminManager.get(ShareUserActivities.class, getShareUserActivitiesVO().getId());
			if(!ObjectFunctions.isNullOrEmpty(getShareUserActivitiesCommentsVO())) {
				ShareUserActivitiesComments shareUserActivitiesComments = new ShareUserActivitiesComments();
				shareUserActivitiesComments.setCommentDescription(getShareUserActivitiesCommentsVO().getCommentDescription());
				shareUserActivitiesComments.setStatus(Constants.ACTIVE_STATUS);
				User account =	(User) adminManager.get(User.class,getUser().getId());
				shareUserActivitiesComments.setCommentUserAccount(account);
				shareUserActivitiesComments.setCustId(getUserCustId());
				shareUserActivitiesComments.setCommentDate(new Date());
				shareUserActivities.addShareUserActivitiesComments(shareUserActivitiesComments);
				adminManager.save(shareUserActivities);
				setShareUserActivitiesCommentsList(shareUserActivities.getShareUserActivitiesComments());
				super.addActionMessage("comment added successfully.");
			}
		} 
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxAddCommentsForDiscussions", results = {@Result(location = "ajaxViewSocialDiscussions.jsp", name = "success") }) })
	public String ajaxAddCommentsForDiscussions() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxAddCommentsForDiscussions' method");
	}
	try {
		if(!ObjectFunctions.isNullOrEmpty(getSocialDiscussionsVO())) {
			SocialDiscussions socialDiscussions = (SocialDiscussions)adminManager.get(SocialDiscussions.class, getSocialDiscussionsVO().getId());
			if(!ObjectFunctions.isNullOrEmpty(socialDiscussions)) {
				SocialDiscussionsComments socialDiscussionsComments = new SocialDiscussionsComments();
				socialDiscussionsComments.setCommentDescription(getSocialDiscussionsCommentsVO().getCommentDescription());
				socialDiscussionsComments.setStatus(Constants.ACTIVE_STATUS);
				User account =	(User) adminManager.get(User.class,getUser().getId());
				socialDiscussionsComments.setCommentUserAccount(account);
				socialDiscussionsComments.setCustId(getUserCustId());
				socialDiscussionsComments.setCommentDate(new Date());
				socialDiscussions.addDisCommnents(socialDiscussionsComments);
				adminManager.save(socialDiscussions);
				setSocialDiscussionsCommentsList(socialDiscussions.getSocialDiscussionsComments());
				 super.addActionMessage("comment added successfully.");
				 ajaxViewSocialDiscussions();
			}
		} 
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetDisComments", results = {@Result(location = "ajaxGetDisComments.jsp", name = "success") }) })
	public String ajaxGetDisComments() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxGetDisComments' method");
	}
	try {
		if(getSocialDiscussionsVO().getId()>0) {
			SocialDiscussions socialDiscussions = (SocialDiscussions)adminManager.get(SocialDiscussions.class, getSocialDiscussionsVO().getId());
			if(!ObjectFunctions.isNullOrEmpty(socialDiscussions)) {
				setSocialDiscussionsCommentsList(socialDiscussions.getSocialDiscussionsComments());
			}
			socialDiscussions = null;
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetComments", results = {@Result(location = "ajaxGetComments.jsp", name = "success") }),
				@Action(value = "ajaxAddEventComments", results = {@Result(location = "ajaxViewEvent.jsp", name = "success") })
	})
	public String ajaxGetComments() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxGetComments' method");
	}
	try {
		ajaxViewEventsDetails();
		/*if(getShareUserActivitiesVO().getId()>0) {
			ShareUserActivitiesVO vo = new ShareUserActivitiesVO();
			vo.setId(getShareUserActivitiesVO().getId());
			vo.setAdjustedStudentImagePath(getShareUserActivitiesVO().getAdjustedStudentImagePath());
			setShareUserActivitiesVO(vo);
			ShareUserActivities shareUserActivities = (ShareUserActivities)adminManager.get(ShareUserActivities.class, getShareUserActivitiesVO().getId());
			if(!ObjectFunctions.isNullOrEmpty(shareUserActivities)) {
				setShareUserActivitiesCommentsList(shareUserActivities.getShareUserActivitiesComments());
			}
			vo=null;
			shareUserActivities = null; 
		}*/
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
		return SUCCESS;
	}
}