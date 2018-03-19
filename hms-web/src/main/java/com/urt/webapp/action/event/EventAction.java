package com.urt.webapp.action.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.State;
import com.urt.service.manager.interfaces.calendar.CalendarManager;
import com.urt.webapp.action.base.BaseAction;

/**
 * Action for facilitating calendar Management feature.
 */
@Namespace("/calendar")
@Actions( { @Action(value = "adminCalendar", results = { @Result(location = "eventCalendar.jsp", name = "success") }),
	        @Action(value = "ajaxDoAddCategory", results = { @Result(location = "createCategory.jsp", name = "success") }) })
public class EventAction extends BaseAction {

	private static final long serialVersionUID = -1646390427462403153L;
     
	protected String categoryName;
	private String anyId;
	private Address address;
	private List<State> statesLt;
	
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	

	
	
	
	

	
	public Map getJsonResult() {
		if(ObjectFunctions.isNullOrEmpty(super.jsonResult))
		{
			super.jsonResult=new HashMap();
			
		}
		return super.jsonResult;
	}
	/**
	 * @return the statesLt
	 */
	public List<State> getStatesLt() {
		if (ObjectFunctions.isNullOrEmpty(this.statesLt)) {
			this.statesLt = new ArrayList<State>();
		}
		return this.statesLt;
	}

	/**
	 * @param statesLt the statesLt to set
	 */
	public void setStatesLt(List<State> statesLt) {
		this.statesLt = statesLt;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the anyId
	 */
	public String getAnyId() {
		return anyId;
	}

	/**
	 * @param anyId the anyId to set
	 */
	public void setAnyId(String anyId) {
		this.anyId = anyId;
	}

	
	public void setCalendarManager(CalendarManager calendarManager) {
		this.calendarManager = calendarManager;
	}

}