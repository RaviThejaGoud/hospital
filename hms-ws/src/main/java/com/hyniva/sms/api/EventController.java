package com.hyniva.sms.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.api.base.SMSBaseController;
import com.hyniva.sms.api.constants.SMSURIConstants;
import com.hyniva.sms.api.exception.SMSAPIException;
import com.hyniva.sms.ws.enums.ERROR_CODE_ENUM;
import com.hyniva.sms.ws.vo.AlbumAttachmentVO;
import com.hyniva.sms.ws.vo.EventMainVO;
import com.hyniva.sms.ws.vo.EventsVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.base.APIStatusVO;
import com.hyniva.sms.ws.vo.base.SMSBaseVO;
import com.urt.persistence.model.event.Events;
import com.urt.service.manager.interfaces.admin.AdminManager;
import com.urt.service.thread.EventsNotificationThread;

@RestController
public class EventController<T extends UserTokenVO> extends SMSBaseController<T> {
	
	@Autowired
	protected AdminManager adminManager;
	
	/*http: http://dev.eazyschool.in/api/event/85655/S*/
	@RequestMapping(value = SMSURIConstants.GET_EVENTS, method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getEvents(@PathVariable("accountId") long accountId,@PathVariable("type") String type)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(accountId>0){
			
			//http://stackoverflow.com/questions/23751172/convert-string-to-date-and-time-as-am-pm-format
			EventMainVO eventMainVO = adminManager.getEventDetails(accountId,type);
			if(!ObjectFunctions.isNullOrEmpty(eventMainVO))
				return eventMainVO;
			else
				apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	
	@RequestMapping(value = SMSURIConstants.ADD_EVENTS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO addEvents(@RequestBody EventsVO eventsVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		//boolean runTimeTableReportsStatus  = adminManager.addEvent(eventsVO);
		
		try {
			if(!ObjectFunctions.isNullOrEmpty(eventsVO))
			{
				int availableSmsCount= adminManager.getAvailableSmsCount(eventsVO.getIdentifier().getCustId(),eventsVO.getIdentifier().getAcademicYearId());
				DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				DateFormat outputFormat = new SimpleDateFormat("KK:mm a");
				
				String startDateTime = eventsVO.getStartDateTime();
				eventsVO.setStartTime(outputFormat.format(inputFormat.parse(startDateTime)));
				
				String endDateTime = eventsVO.getEndDateTime();
				eventsVO.setEndTime(outputFormat.format(inputFormat.parse(endDateTime)));
				eventsVO.setEventCreatedUserId(eventsVO.getIdentifier().getAccountId());	
				eventsVO.setCustId(eventsVO.getIdentifier().getCustId());
				eventsVO.setAcademicYearId(eventsVO.getIdentifier().getAcademicYearId());
				eventsVO.setRequestType("M"); 
				
				Events event  = adminManager.addEvent(eventsVO);
				EventsNotificationThread R1 = new EventsNotificationThread(event,eventsVO.getId(),null);
			    R1.start();
				if(availableSmsCount == 0)
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_EVENTS.getErrorCode(),ERROR_CODE_ENUM.ALLOTTED_SMS_COUNT_EVENTS.getErrorDesc());
				else if(!ObjectFunctions.isNullOrEmpty(event))
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
				else
					apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_DATA.getErrorCode(),ERROR_CODE_ENUM.INVALID_DATA.getErrorDesc());
				smsBaseVO.setApiStatus(apistatusVO);
				
				event = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return smsBaseVO;
	}
	
	
	/*http: http://dev.eazyschool.in/api/event/attachment
	 */
	@RequestMapping(value = SMSURIConstants.GET_EVENTS_ALBUM_ATTACHMENTS, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public SMSBaseVO getEventAlbumAttachment(@RequestBody AlbumAttachmentVO albumAttachmentVO)throws SMSAPIException {
		SMSBaseVO smsBaseVO = new SMSBaseVO();
		APIStatusVO apistatusVO = null;
		if(albumAttachmentVO.getId()>0){ // here Id means eventId, we are using here albumAttachment Id as eventId
			
			return adminManager.uploadEventAlbumPhotos(albumAttachmentVO.getId(),albumAttachmentVO.getFilePath());
			/*Events events =  (Events)adminManager.get(Events.class, "id="+albumAttachmentVO.getId());
			 if(!ObjectFunctions.isNullOrEmpty(events))
			 {
				 EventsAlbum album = (EventsAlbum)adminManager.get(EventsAlbum.class, "eventId="+events.getId());
				 if(!ObjectFunctions.isNullOrEmpty(album))
				 {
					 if(!ObjectFunctions.isNullOrEmpty(album.getAlbumAttachment()))
					 {
						 List<AlbumAttachment> albumAttachmentAllList = ConvertUtil.convertSetToList(album.getAlbumAttachment()); 
						 AlbumAttachment albumAttachment =  albumAttachmentAllList.get(albumAttachmentAllList.size() - 1);
						 keyIdentifier.setChannel(String.valueOf(albumAttachment.getId()));
						 List<AlbumAttachment> albumAttachmentList = new ArrayList<AlbumAttachment>();
						 albumAttachmentList.add(albumAttachment);
						 EventsNotificationThread R1 = new EventsNotificationThread(events,events.getId(),Constants.YES_STRING,albumAttachmentList);
						 R1.start();
					 }
				 }
				 if(responseCode == 0)
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.SUCCESS.getErrorCode(),ERROR_CODE_ENUM.SUCCESS.getErrorDesc());
					else if(responseCode == 1)
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.CAN_NOT_SAVE_FILE.getErrorCode(),ERROR_CODE_ENUM.CAN_NOT_SAVE_FILE.getErrorDesc());
					else if(responseCode == 2)
						apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.DATA_ERROR.getErrorCode(),ERROR_CODE_ENUM.DATA_ERROR.getErrorDesc());
					smsBaseVO.setApiStatus(apistatusVO);
					smsBaseVO.setIdentifier(keyIdentifier);
					 return smsBaseVO;
			 }
			 events = null;*/
		}else{
			apistatusVO = new APIStatusVO(ERROR_CODE_ENUM.INVALID_ID.getErrorCode(),ERROR_CODE_ENUM.INVALID_ID.getErrorDesc());
		}
		smsBaseVO.setApiStatus(apistatusVO);
		return smsBaseVO;
	}
	
	
	
	@Override
	protected Validator setValidator() {
		// TODO Auto-generated method stub
		return null;
	}
}
