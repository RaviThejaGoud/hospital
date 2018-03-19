package com.urt.webapp.action.hostel;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.w3c.dom.Document;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ConvertUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.xls.ExcelView;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.Hostel;
import com.urt.persistence.model.exam.WeekDays;
import com.urt.persistence.model.hostel.Bed;
import com.urt.persistence.model.hostel.Building;
import com.urt.persistence.model.hostel.Floor;
import com.urt.persistence.model.hostel.FoodMenuItems;
import com.urt.persistence.model.hostel.FoodTypes;
import com.urt.persistence.model.hostel.HostelStudents;
import com.urt.persistence.model.hostel.IssueProvisionItemsToMess;
import com.urt.persistence.model.hostel.ManageItems;
import com.urt.persistence.model.hostel.Merchant;
import com.urt.persistence.model.hostel.Mess;
import com.urt.persistence.model.hostel.MessFoodType;
import com.urt.persistence.model.hostel.MessMenuTime;
import com.urt.persistence.model.hostel.ProvisionItems;
import com.urt.persistence.model.hostel.Room;
import com.urt.persistence.model.hostel.StudentOut;
import com.urt.persistence.model.hostel.ViewBuildingFloorDetails;
import com.urt.persistence.model.hostel.ViewBuildingMenuItemsDetails;
import com.urt.persistence.model.hostel.ViewHostelBuildingDetails;
import com.urt.persistence.model.hostel.ViewIssueProvisionItemsToMess;
import com.urt.persistence.model.hostel.ViewRoomDetails;
import com.urt.persistence.model.hostel.ViewStaffAllotedBeds;
import com.urt.persistence.model.hostel.ViewStudentOutHostelDetails;
import com.urt.persistence.model.hostel.ViewStudentsAllotedBeds;
import com.urt.persistence.model.hostel.Visitors;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.ViewHostelStudentDetails;
import com.urt.util.common.RayGunException;
import com.urt.webapp.action.base.BaseAction;
import com.urt.webapp.action.event.DOMUtil;
import com.urt.webapp.action.event.RecurringEventsDOM;
import com.urt.webapp.action.jrexception.JRExceptionClient;
/**
 * Action for facilitating calendar Management feature.
 */

@Namespace("/hostel")

@Actions( {
	@Action(value = "ajaxDoImportStudentExcelSheet", results = { @Result(location = "importStudentOutExcelSheet.jsp", name = "success") }),
    @Action(value = "ajaxDoImportStudentInExcelSheet", results = { @Result(location = "importStudentInExcelSheet.jsp", name = "success") }),
    @Action(value = "adminGetHostelFee", results = { @Result(location = "fee/ajaxGetHostelFeeHome.jsp", name = "success") }),
    @Action(value = "manageStudentAndStaff", results = { @Result(location = "ajaxGetHostelFeeHome.jsp", name = "success") }) ,
    @Action(value = "ajaxGetAdminCalendar", results = { @Result(location = "hostelEvents/ajaxHostelCalendar.jsp", name = "success") }),
    @Action(value = "adminGetBackAllSchoolFee", results = { @Result(location = "fee/ajaxGetSchoolHome.jsp", name = "success") }),
    @Action(value = "ajaxAdminGetFourteenSchoolFee", results = { @Result(location = "fee/ajaxGetFourteenSchoolFee.jsp", name = "success") }),
    @Action(value = "ajaxAdminGetFifteenSchoolFee", results = { @Result(location = "fee/ajaxGetAboveFifteenSchoolFee.jsp", name = "success") }),
    @Action(value = "ajaxAdminGetThirtySchoolFee", results = { @Result(location = "fee/ajaxGetThirtySchoolFee.jsp", name = "success") }),
    @Action(value = "ajaxAdminSixtySchoolFee", results = { @Result(location = "fee/ajaxGetSixtySchoolFee.jsp", name = "success") }),
    @Action(value = "ajaxAdminUpcomingSchoolFee", results = { @Result(location = "fee/ajaxGetUpcomingSchoolFee.jsp", name = "success") }),
    @Action(value = "ajaxViewStudentInAndOut", results = { @Result(location = "ajaxSearchStudentInOutDetails.jsp", name = "success") }),
    @Action(value = "ajaxViewStudentIn", results = { @Result(location = "ajaxViewSearchStudentInDetails.jsp", name = "success") }),
    @Action(value = "ajaxModifyStudentTransportPaymentFee", results = { @Result(location = "fee/searchStudentDetails.jsp", name = "success") }) 
    })

public class HostelAction extends BaseAction {

    private static final long serialVersionUID = -1646390427462403153L;
    protected List<Floor> floorList;
    protected List<Room> roomList;
    protected List messTimeingsList;
    private List foodTypeList;
    private List foodMenuTypeList;
    protected List<Hostel> hostelList;
    protected List<Bed> BedList;
    //protected HostelFeeType hostelFeeType;
    protected String studentNumber;
    protected Fee feeStructure;
    private List classFeeTypeList;
    protected List tempActivePrekgClassList;
    protected List hostelFeeList;
    protected String staffNumber;
    protected List studentOutList;
    
    
    /**
     * @return the studentOutList
     */
    public List getStudentOutList() {
	if (ObjectFunctions.isNullOrEmpty(this.studentOutList)) {
		this.studentOutList = new ArrayList();
	}
        return studentOutList;
    }

    /**
     * @param studentOutList the studentOutList to set
     */
    public void setStudentOutList(List studentOutList) {
        this.studentOutList = studentOutList;
    }

    public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	/**
     * @return the hostelFeeList
     */
    public List getHostelFeeList() {
	if (ObjectFunctions.isNullOrEmpty(this.hostelFeeList)) {
		this.hostelFeeList = new ArrayList();
	}
        return hostelFeeList;
    }

    /**
     * @param hostelFeeList the hostelFeeList to set
     */
    public void setHostelFeeList(List hostelFeeList) {
        this.hostelFeeList = hostelFeeList;
    }

    /**
     * @return the tempActivePrekgClassList
     */
    public List getTempActivePrekgClassList() {
	return tempActivePrekgClassList;
    }

    /**
     * @param tempActivePrekgClassList
     *            the tempActivePrekgClassList to set
     */
    public void setTempActivePrekgClassList(List tempActivePrekgClassList) {
	this.tempActivePrekgClassList = tempActivePrekgClassList;
    }

    /**
     * @return the classFeeTypeList
     */
    public List getClassFeeTypeList() {
	if (ObjectFunctions.isNullOrEmpty(this.classFeeTypeList)) {
	    this.classFeeTypeList = new ArrayList();
	}
	return classFeeTypeList;
    }

    /**
     * @param classFeeTypeList
     *            the classFeeTypeList to set
     */
    public void setClassFeeTypeList(List classFeeTypeList) {
	this.classFeeTypeList = classFeeTypeList;
    }

    /**
     * @return the feeStructure
     */
    public Fee getFeeStructure() {
	return feeStructure;
    }

    /**
     * @param feeStructure
     *            the feeStructure to set
     */
    public void setFeeStructure(Fee feeStructure) {
	this.feeStructure = feeStructure;
    }

    public String getStudentNumber() {
	return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
	this.studentNumber = studentNumber;
    }

    /* 
     * * @return the hostelList
     */
    public List<Hostel> getHostelList() {
	return hostelList;
    }

    /**
     * @param hostelList
     *            the hostelList to set
     */
    public void setHostelList(List<Hostel> hostelList) {
	this.hostelList = hostelList;
    }

    /**
     * @return the bedList
     */
    public List<Bed> getBedList() {
		if (ObjectFunctions.isNullOrEmpty(this.BedList)) {
			this.BedList = new ArrayList<Bed>();
		}
		return BedList;
    }

    /**
     * @param bedList
     *            the bedList to set
     */
    public void setBedList(List<Bed> bedList) {
	BedList = bedList;
    }

    /**
     * /**
     * 
     * @return the floorList
     */
    public List<Floor> getFloorList() {
	if (ObjectFunctions.isNullOrEmpty(this.floorList)) {
	    this.floorList = new ArrayList<Floor>();
	}
	return floorList;
    }

    /**
     * @param floorList
     *            the floorList to set
     */
    public void setFloorList(List<Floor> floorList) {
	this.floorList = floorList;
    }

    public List getFoodMenuTypeList() {
	if (ObjectFunctions.isNullOrEmpty(this.foodMenuTypeList)) {
	    this.foodMenuTypeList = new ArrayList();
	}
	return foodMenuTypeList;
    }

    public void setFoodMenuTypeList(List foodMenuTypeList) {
	this.foodMenuTypeList = foodMenuTypeList;
    }

    public List getFoodTypeList() {
	if (ObjectFunctions.isNullOrEmpty(this.foodTypeList)) {
	    this.foodTypeList = new ArrayList();
	}
	return foodTypeList;
    }

    public void setFoodTypeList(List foodTypeList) {
	this.foodTypeList = foodTypeList;
    }

    public List getMessTimeingsList() {
	if (ObjectFunctions.isNullOrEmpty(this.messTimeingsList)) {
	    this.messTimeingsList = new ArrayList();
	}
	return messTimeingsList;
    }

    public void setMessTimeingsList(List messTimeingsList) {
	this.messTimeingsList = messTimeingsList;
    }

    /**
     * @return the roomList
     */
    public List<Room> getRoomList() {
	if (ObjectFunctions.isNullOrEmpty(this.roomList)) {
	    this.roomList = new ArrayList<Room>();
	}
	return this.roomList;
    }

    /**
     * @param roomList
     *            the roomList to set
     */
    public void setRoomList(List<Room> roomList) {
	this.roomList = roomList;
    }

    public String getAutoCheck() {
	return super.autoCheck;
    }
    
    @Action(value = "manageHostel", results = { @Result(location = "ajaxHostelLeftNav.jsp", name = "success") })
    public String manageHostel() throws URTUniversalException {
    	if (log.isDebugEnabled()) {
    	    log.debug("Entering 'manageHostel' method");
    	}
    	try
    	{ 
    		ajaxViewSchoolBuildingSettings();
    		if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("showTaskReminder")))
				getTaskReminderToUserLogin();
    	} catch (Exception ex) {
    	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
    	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
    	}
    	return SUCCESS;
        }
    
    @Actions( {@Action(value = "ajaxViewSchoolBuildingSettings", results = { @Result(location = "ajaxViewSchoolBuildingTypesList.jsp", name = "success") }),
	       @Action(value = "ajaxLoadManageInfoByStatus", results = { @Result(location = "ajaxViewHostelFloors.jsp", name = "success") }),
	       @Action(value = "ajaxLoadRoomsBysStatus", results = { @Result(location = "ajaxViewHostelRooms.jsp", name = "success") })
	       })
    public String ajaxViewSchoolBuildingSettings() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxViewSchoolBuildingSettings' method");
	}
	try {
	    if (getUserAcademicYearId() > 0) {
		if (StringFunctions.isNullOrEmpty(getAnyTitle())|| "buildings".equalsIgnoreCase(getAnyTitle())) {
		    setAnyTitle("buildings");
		   // setBuildingList(hostelManager.getAllByCustId("ViewHostelBuildingDetails", getUserCustId(),getUserAcademicYearId()));
		    
		    setBuildingList(hostelManager.getAll(ViewHostelBuildingDetails.class, "custId="+getUserCustId()+" and buildingStatus ='"+Constants.YES_STRING+"' and academicYearId="+getUserAcademicYearId()));
		} 
		else if ("floors".equalsIgnoreCase(anyTitle)) {
			ajaxLoadManageInfoByFloors();
		}
		else if ("rooms".equalsIgnoreCase(anyTitle)) {
			ajaxLoadManageInfoByRooms();
		}
		else if ("beds".equalsIgnoreCase(anyTitle)) {
			ajaxLoadManageInfoByBeds();
		}
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
    
	@Actions( { @Action(value = "ajaxDoAddBuildingSettings", results = { @Result(location = "ajaxCreateSchoolBuilding.jsp", name = "success") }) })
	public String ajaxDoAddBuildingSettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddBuildingSettings' method");
		}
		try {
			if (getTempId2() > 0) {
				setBuilding((Building) adminManager.get(Building.class,getTempId2()));
				if (!ObjectFunctions.isNullOrEmpty(getBuilding())) {
					if (getBuilding().getAddressId() > 0) {
						setAddress((Address) hostelManager.get(Address.class,getBuilding().getAddressId()));
					}
					if (getBuilding().getHostelId() > 0)
						setHostel((Hostel) adminManager.get(Hostel.class,getBuilding().getHostelId()));
				}
			} else {
				setBuilding(null);
				setAddress(null);
			}
			setHostelList(adminManager.getAll(Hostel.class, "custId="+ getUserCustId()));
			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
    
	@Actions( { @Action(value = "ajaxViewBuildingInformation", results = { @Result(location = "ajaxViewSchoolBuildingList.jsp", name = "success") }) })
	public String ajaxViewBuildingInformation() throws URTUniversalException {
		try {
			String hostelId = getParamValue("anyId");
			setHostel((Hostel) adminManager.get(Hostel.class, Long.valueOf(hostelId)));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxCreateSchoolBuilding", results = { @Result(location = "ajaxViewHostelSettingsTypesList.jsp", name = "success") }) })
    public String ajaxCreateSchoolBuilding() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxCreateSchoolBuilding' method");
	}
	try {
	    Address address = null;
	    long buildingId = getTempId2();
	    long hostelId = 0;
	    Building building = null;
	    long floorNum;
	    Floor floor = null;
	    if (getUserAcademicYearId() > 0) {
		if (buildingId > 0) {
		    building = (Building) hostelManager.get(Building.class,buildingId);
		    hostelId = building.getHostelId();
		} else {
		    building = new Building();
		    hostelId = getHostel().getId();
		}
		if (getBuilding().getAddressId() > 0) {
		    address = (Address) hostelManager.get(Address.class,getBuilding().getAddressId());
		} else {
		    address = new Address();
		}
		if (!ObjectFunctions.isNullOrEmpty(address)) {
		    address.setCity(getAddress().getCity());
		    address.setStreetName(getAddress().getStreetName());
		    address.setPostalCode(getAddress().getPostalCode());
		    address.setState(getAddress().getState());
		    address = hostelManager.saveAddress(address);
		}
		if (hostelId > 0 && !ObjectFunctions.isNullOrEmpty(getCurrentAcademicYear())) {
		    if (getNumberOfDays() > 0) {
		    	floorNum = hostelManager.getMaxFloorLevel(getFloor().getFloorName(), getUserCustId(),getUserAcademicYearId());
					for (int i = 1; i <= getNumberOfDays(); i++)
					{
					    floor = new Floor();
					    floor.setCustId(getUserCustId());
					    floor.setFloorLevel(floorNum);
					    floor.setHostelId(hostelId);
					    floor.setFloorName(getFloor().getFloorName()+" "+floorNum);
					    floor.setAcademicYear(getCurrentAcademicYear());
					    floor.setStatus(Constants.YES_STRING);
					    building.addBuildingToFloor(floor);
					    floorNum++;
					}
			    }
			    building.setCustId(getUserCustId());
			    building.setHostelId(hostelId);
			    building.setAddressId(address.getId());
			    building.setBuildingName(getBuilding().getBuildingName());
			    building.setBuildingShortName(getBuilding().getBuildingShortName());
			    building.setContactNumber(getBuilding().getContactNumber());
			    building.setGender(getBuilding().getGender());
			    building.setAcademicYear(getCurrentAcademicYear());
			    building.setStatus(Constants.YES_STRING);
			    adminManager.save(building);
			    if (buildingId > 0)
				super.addActionMessage("Building updated successfully.");
			    else
				super.addActionMessage("Building added successfully.");
			}
		 }
	    building = null;
	    setBuilding(null);
	    ajaxViewSchoolBuildingSettings();
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
	
	@Actions( { @Action(value = "ajaxDeleteBuilding", results = { @Result(location = "ajaxViewHostelSettingsTypesList.jsp", name = "success") }) })
	public String ajaxDeleteBuilding() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteBuilding' method");
		}
		try {
			setAnyTitle("buildings");
			if (getTempId2() != 0) {
				List<Floor> floorList = adminManager.getAll(Floor.class,"buildingId=" + getTempId2());
				if (ObjectFunctions.isNullOrEmpty(floorList)) {
					adminManager.remove(Building.class, getTempId2());
					super.addActionMessage("Building deleted successfully.");
				} else
					super.addActionError("Building contains floors. You can't delete floor.");
				floorList = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ajaxViewSchoolBuildingSettings();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxLoadManageInfoByFloors", results = { @Result(location = "ajaxViewHostelFloorsByFloorId.jsp", name = "success") }) })
	public String ajaxLoadManageInfoByFloors() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxLoadManageInfoByFloors' method");
		}
		try {
			ViewHostelBuildingDetails viewHostelBuildingDetails = null;
			setObjectList(hostelManager.getAll(ViewHostelBuildingDetails.class," custId=" + getUserCustId() +" and buildingStatus ='"+Constants.YES_STRING+"' and academicYearId="+ getUserAcademicYearId()));
			if (getTempId2() <= 0) {
				if (ObjectFunctions.isNotNullOrEmpty(getObjectList())) {
					viewHostelBuildingDetails = (ViewHostelBuildingDetails) getObjectList().get(0);
					setTempId2(viewHostelBuildingDetails.getBuildingId());
				}
				viewHostelBuildingDetails = null;
			}
			if (getTempId2() > 0) {
				setBuilding((Building) hostelManager.get(Building.class,getTempId2()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoAddFloorSettings", results = { @Result(location = "ajaxCreateSchoolFloor.jsp", name = "success") }) })
	public String ajaxDoAddFloorSettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddFloorSettings' method");
		}
		try {
			//setObjectList(hostelManager.getAll(ViewHostelBuildingDetails.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()));
			//setObjectList(hostelManager.getAll(ViewHostelBuildingDetails.class," custId=" + getUserCustId() +" and buildingStatus ='"+Constants.YES_STRING+"'  and floorStatus ='"+Constants.YES_STRING+"'  and buildingStatus ='"+Constants.YES_STRING+"' and academicYearId="+ getUserAcademicYearId()));
			setObjectList(hostelManager.getAll(ViewHostelBuildingDetails.class," custId=" + getUserCustId() +" and buildingStatus ='"+Constants.YES_STRING+"'  and buildingStatus ='"+Constants.YES_STRING+"' and academicYearId="+ getUserAcademicYearId()));
			if (getFloor().getId() != 0) {
				setFloor((Floor) adminManager.get(Floor.class, getFloor().getId()));
				if(!ObjectFunctions.isNullOrEmpty(getFloor().getRoomList()))
				{
					setQuizId(getFloor().getRoomList().size());
					setRoom(getFloor().getRoomList().get(0));
				}
			} else {
				getFloor().setFloorName(null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* @Ganesh For create and edit floor we are using same mothod */
	@Actions( { @Action(value = "ajaxCreateSchoolFloors", results = { @Result(location = "ajaxViewHostelFloors.jsp", name = "success") }) })
	public String ajaxCreateSchoolFloors() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateSchoolFloors' method");
		}
		try {
			String[] ids = getTempString().split("_");
			long hostelId = Long.valueOf(ids[0]);
			long buildingId = Long.valueOf(ids[1]);
			Floor floor = null;
			Room room = null;
			long floorNum;
			long roomNum = 1;
			setAnyTitle("floors");
			if (!ObjectFunctions.isNullOrEmpty(getCurrentAcademicYear())) {
				if (getFloor().getId() > 0)
					floor = (Floor) hostelManager.get(Floor.class, getFloor().getId());
				else
					floor = new Floor();
				if (buildingId > 0 && !ObjectFunctions.isNullOrEmpty(getCurrentAcademicYear())) {
					if (getNumberOfDays() > 0) {
						roomNum = hostelManager.getMaxRoomLevel(getRoom().getRoomName(), getUserCustId(),getCurrentAcademicYear().getId());
						if(roomNum <= 1)
							 roomNum = 1;
						else
							roomNum = roomNum+1;
						for (int i = 1; i <= getNumberOfDays(); i++) 
						{
							room = new Room();
							room.setCustId(getUserCustId());
							room.setRoomLevel(roomNum);
							room.setRoomName(getRoom().getRoomName()+""+roomNum);
							room.setAcademicYear(getCurrentAcademicYear());
							room.setStatus(Constants.YES_STRING);
							floor.addFloorToRoom(room);
							roomNum++;
						}
					}
					if (!getFloor().getFloorName().equalsIgnoreCase(
							floor.getFloorName())) {
								floorNum = hostelManager.getMaxFloorLevel(getFloor().getFloorName(), getUserCustId(),getCurrentAcademicYear().getId());
								floor.setFloorLevel(++floorNum);
					}
					floor.setCustId(getUserCustId());
					floor.setHostelId(hostelId);
					floor.setBuildingId(buildingId);
					floor.setStatus(Constants.YES_STRING);
					floor.setFloorName(getFloor().getFloorName());
					if (StringFunctions.isNotNullOrEmpty(getBuilding().getGender()))
						floor.setGender(getBuilding().getGender());
					floor.setAcademicYear(getCurrentAcademicYear());
					adminManager.save(floor);
					if (getFloor().getId() > 0)
						super.addActionMessage("Floor updated successfully.");
					else
						super.addActionMessage("Floor added successfully.");
				}
				floor = null;
				setFloor(null);
				ajaxViewSchoolBuildingSettings();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDeleteFloor", results = { @Result(location = "ajaxViewHostelFloorsByFloorId.jsp", name = "success") }) })
	public String ajaxDeleteFloor() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteFloor' method");
		}
		try {
			setAnyTitle("floors");
			if (getFloor().getId() != 0) {
				List<Room> roomsList = adminManager.getAll(Room.class,"floorId=" + getFloor().getId());
				if (ObjectFunctions.isNullOrEmpty(roomsList)) {
					adminManager.remove(Floor.class, getFloor().getId());
					super.addActionMessage("Floor deleted successfully.");
				} else
					super.addActionError("Floor contains rooms.You can't delete this floor.");
				roomsList = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ajaxViewSchoolBuildingSettings();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoAddRoomSettings", results = { @Result(location = "ajaxCreateSchoolRoom.jsp", name = "success") }) })
	public String ajaxDoAddRoomSettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddRoomSettings' method");
		}
		try {
			if (getUserAcademicYearId() != 0)
				//setObjectList(hostelManager.getAll(ViewBuildingFloorDetails.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+ " order by buildingName,floorName,floorLevel"));
				setObjectList(hostelManager.getAll(ViewBuildingFloorDetails.class," custId=" + getUserCustId() +" and buildingStatus ='"+Constants.YES_STRING+"'  and floorStatus ='"+Constants.YES_STRING+"' and academicYearId="+ getUserAcademicYearId()+ " order by buildingName,floorName,floorLevel"));
			if (getRoom().getId() > 0) {
				setRoom((Room) adminManager.get(Room.class, getRoom().getId()));
				if (!ObjectFunctions.isNullOrEmpty(getRoom()))
					setTempId1(getRoom().getFloorId());
			} else
				setRoom(null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxLoadManageInfoByRooms", results = { @Result(location = "ajaxViewHostelRoomsByRoomId.jsp", name = "success") }) })
	public String ajaxLoadManageInfoByRooms() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxLoadManageInfoByRooms' method");
		}
		try {
			ViewBuildingFloorDetails viewBuildingFloorDetails = null;
			//setObjectList(hostelManager.getAll(ViewBuildingFloorDetails.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+ " order by buildingName,floorName,floorLevel"));
			setObjectList(hostelManager.getAll(ViewBuildingFloorDetails.class," custId=" + getUserCustId() +" and buildingStatus ='"+Constants.YES_STRING+"'  and floorStatus ='"+Constants.YES_STRING+"' and academicYearId="+ getUserAcademicYearId()+ " order by buildingName,floorName,floorLevel"));
			if (getTempId1() <= 0) {
				if (ObjectFunctions.isNotNullOrEmpty(getObjectList())) {
					viewBuildingFloorDetails = (ViewBuildingFloorDetails) getObjectList().get(0);
					setTempId1(viewBuildingFloorDetails.getFloorId());
				}
			}
			if (getTempId1() > 0) {
				setFloor((Floor) hostelManager.get(Floor.class, getTempId1()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxCreateSchoolRooms", results = { @Result(location = "ajaxViewHostelRooms.jsp", name = "success") }) })
	public String ajaxCreateSchoolRooms() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateSchoolRooms' method");
		}
		try {
			long floorId = getTempId1();
			long roomId = getRoom().getId();
			AcademicYear year = getCurrentAcademicYear();
			Room room = null;
			Bed bed = null;
			Object bedNumObj[];
			int bedNum =1;
			setAnyTitle("rooms");
			if (!ObjectFunctions.isNullOrEmpty(year))
			{
				long academicYearId = year.getId();
				if (roomId > 0) {
					room = (Room) hostelManager.get(Room.class, roomId);
					if (!ObjectFunctions.isNullOrEmpty(room))
						floorId = room.getFloorId();
					
					
				} else
					room = new Room();
				if (floorId > 0 && !ObjectFunctions.isNullOrEmpty(year)) 
				{
					int totalCount = 0;
					int roomCapicity = 0;
					if (room.getId() > 0) 
					{
						roomCapicity = getNumberOfDays();
						bedNumObj = (Object[]) hostelManager.get("select IFNULL(max(bedLevel),0),id from bed where custId="+getUserCustId()+" and academicYearId="+academicYearId+" and roomId="+room.getId());
						if (!ObjectFunctions.isNullOrEmpty(bedNumObj))
						{
							bedNum = Integer.parseInt(String.valueOf(bedNumObj[0]))+1;
						}
						totalCount =  roomCapicity + Integer.parseInt(getRoom().getCapacity());
						log.debug(totalCount);
						room.setCapacity(String.valueOf(totalCount));
					}
					else
					{
						roomCapicity = Integer.parseInt(getRoom().getCapacity());
						room.setCapacity(getRoom().getCapacity());
						room.setStatus(Constants.YES_STRING);
					}
					if(roomCapicity > 0 || totalCount>0)
					{
						for (int i = 1; i <= roomCapicity; i++) 
						{
							bed = new Bed();
							bed.setCustId(getUserCustId());
							bed.setBedLevel(bedNum);
							bed.setBedCost(getTotalAmount());
							bed.setBedName(""+bedNum);
							bed.setAcademicYear(year);
							bed.setStatus(true);
							room.addRoomToBed(bed);
							bedNum++;
						}
					}
					/*if (!getRoom().getRoomName().equalsIgnoreCase(room.getRoomName())) {
						bedNum = hostelManager.getMaxRoomLevel(getRoom().getRoomName(), getUserCustId(),academicYearId);
						room.setRoomLevel(++bedNum);
					}*/
					/*if (getNumberOfDays() > 0) {
						room.setCapacity(String.valueOf(roomCapicity));
					}*/
					room.setCustId(getUserCustId());
					room.setFloorId(floorId);
					room.setRoomName(getRoom().getRoomName());
					room.setRoomType(getRoom().getRoomType());
					room.setAcademicYear(year);
					
					adminManager.save(room);
					if (roomId > 0)
						super.addActionMessage("Room updated successfully.");
					else
						super.addActionMessage("Room added successfully.");
				}
			}
			setAnyTitle("rooms");
			ajaxLoadManageInfoByRooms();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxViewSchoolBuildingSettings();
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDeleteRoom", results = { @Result(location = "ajaxViewHostelRoomsByRoomId.jsp", name = "success") }) })
	public String ajaxDeleteRoom() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteRoom' method");
		}
		try {
			setAnyTitle("rooms");
			if (getRoom().getId() > 0) {
				List<Bed> bedsList = adminManager.getAll(Bed.class, "roomId="+ getRoom().getId());
				if (ObjectFunctions.isNullOrEmpty(bedsList)) {
					adminManager.remove(Room.class, getRoom().getId());
					super.addActionMessage("Room deleted successfully.");
				} else
					super.addActionError("Room contains beds. You can't delete this room.");
				bedsList = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ajaxViewSchoolBuildingSettings();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxLoadManageInfoByBeds", results = { @Result(location = "ajaxViewHostelBedsByRoom.jsp", name = "success") }) })
	public String ajaxLoadManageInfoByBeds() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxLoadManageInfoByBeds' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getRoom().getId()))
			{
				setObjectList(adminManager.getAll(Bed.class,"roomId="+getRoom().getId()));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoAddBedSettings", results = { @Result(location = "ajaxCreateSchoolBeds.jsp", name = "success") }) })
	public String ajaxDoAddBedSettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddBedSettings' method");
		}
		try {
			if (getUserAcademicYearId() != 0)
			if (!ObjectFunctions.isNullOrEmpty(getAnyId())) {
				setBed((Bed) adminManager.get(Bed.class, "id="+Long.valueOf(getAnyId())));
				if (!ObjectFunctions.isNullOrEmpty(getBed()))
					setTempId1(getBed().getRoomId());
			} else
			{
				setTempId1(getRoom().getId());
				setBed(null);
			}
			setAnyId(null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxCreateSchoolBeds", results = { @Result(location = "ajaxViewHostelBedsByRoom.jsp", name = "success") }) })
	public String ajaxCreateSchoolBeds() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateSchoolBeds' method");
		}
		try {
			long bedId = getBed().getId();
			Room room = null;
			Bed bed = null;
			long roomId = getTempId1();
			long bedNum = 0;
			setAnyTitle("beds");
			if (getUserAcademicYearId() > 0) {
				if (bedId > 0) {
					bed = (Bed) hostelManager.get(Bed.class, bedId);
					roomId = bed.getRoomId();
				} else {
					bed = new Bed();
				}
				if (!ObjectFunctions.isNullOrEmpty(getCurrentAcademicYear()) && roomId > 0) {
					if (!getBed().getBedName().equalsIgnoreCase(bed.getBedName())) {
						bedNum = hostelManager.getMaxBedLevel(getBed().getBedName(), getUserCustId(),getUserAcademicYearId());
						bed.setBedLevel(bedNum+1);
					}
					bed.setCustId(getUserCustId());
					room = (Room) hostelManager.get(Room.class, roomId);
					//bed.setBedName(getBed().getBedName());
					bed.setBedName(""+bedNum+1);
					bed.setAcademicYear(getCurrentAcademicYear());
					bed.setBedCost(getBed().getBedCost());
					room.addRoomToBed(bed);
					adminManager.save(room);
					if (bedId > 0)
						super.addActionMessage("Bed updated successfully.");
					else
						super.addActionMessage("Bed added successfully.");
				}
			}
			setRoom(room);
			setAnyId(null);
			ajaxLoadManageInfoByBeds();
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDeleteBed", results = { @Result(location = "ajaxViewHostelSettingsTypesList.jsp", name = "success") }) })
	public String ajaxDeleteBed() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteBed' method");
		}
		try {
			if (getBed().getId() > 0) {
				Student student = (Student) adminManager.get(Student.class,"bedId=" + getBed().getId());
				if (ObjectFunctions.isNullOrEmpty(student)) {
					adminManager.remove(Bed.class, Long.valueOf(getBed().getId()));
					super.addActionMessage("Bed deleted successfully.");
				} else
					super.addActionError("Bed is assined to student.You can't delete this bed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ajaxLoadManageInfoByBeds();
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxViewMessManagementDetails", results = { @Result(location = "messManagement/ajaxViewMessManagemetTypesList.jsp", name = "success") }) })
	public String ajaxViewMessManagementDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewMessManagementDetails' method");
		}
		try {
			loadMessInfoByStatus();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxLoadMessInfoByStatus", results = { @Result(location = "messManagement/ajaxViewMessSettingsTypesList.jsp", name = "success") }) })
	public String loadMessInfoByStatus() throws URTUniversalException {
		try {
			String displayMessInfoType = getParamValue("type");
			if (!StringFunctions.isNullOrEmpty(displayMessInfoType)) {
				setAnyTitle(displayMessInfoType);
			}
			setObjectList(hostelManager.getAll(ViewHostelBuildingDetails.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()));
			if (getTempId2() <= 0) {
				if (!ObjectFunctions.isNullOrEmpty(getObjectList())) {
					ViewHostelBuildingDetails hostelBuding = (ViewHostelBuildingDetails) getObjectList().get(0);
					setTempId2(hostelBuding.getBuildingId());
				}
			}
			if (getTempId2() > 0) {
				if (displayMessInfoType.equalsIgnoreCase("MessFoodItems")) {
					setSelectedId(String.valueOf(getTempId2()));
					buldingFoodItems();
				} else if (displayMessInfoType.equalsIgnoreCase("MessTimings")) {
					setMessTimeingsList(hostelManager.getAll(MessMenuTime.class, "custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and buildingId=" + getTempId2()));
				} else if (displayMessInfoType.equalsIgnoreCase("MessFoodTypes")) {
					setFoodTypeList(hostelManager.getAll(FoodTypes.class,"custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and buildingId=" + getTempId2()));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetBuldingFoodItems", results = { @Result(location = "messManagement/ajaxViewMessSettingsTypesList.jsp", name = "success") }) })
	public String buldingFoodItems() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'buldingFoodItems' method");
		}
		try {

			String displayMessInfoType = getParamValue("type");
			if (!StringFunctions.isNullOrEmpty(displayMessInfoType)) {
				setAnyTitle(displayMessInfoType);
			}
			setObjectList(hostelManager.getAll(ViewHostelBuildingDetails.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()));
			if (getTempId2() <= 0) {
				if (!ObjectFunctions.isNullOrEmpty(getObjectList())) {
					ViewHostelBuildingDetails hostelBuding = (ViewHostelBuildingDetails) getObjectList().get(0);
					setTempId2(hostelBuding.getBuildingId());
				}
			}
			if (getTempId2() > 0) {
				if (displayMessInfoType.equalsIgnoreCase("MessFoodItems")) {
					setSelectedId(String.valueOf(getTempId2()));
					setMessTimeingsList(hostelManager.getAll(MessMenuTime.class, "custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and buildingId="+ Long.valueOf(getSelectedId())));
					setFoodMenuTypeList(hostelManager.getAll(ViewBuildingMenuItemsDetails.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+ " and buildingId="+ Long.valueOf(getSelectedId())+ " order by dayId"));
					setFoodTypeList(hostelManager.getAll(FoodTypes.class,"custId=" + getUserCustId() + "  and buildingId="+ Long.valueOf(getSelectedId())));
					setWeekDayList(hostelManager.getAll(WeekDays.class));
				} else if (displayMessInfoType.equalsIgnoreCase("MessTimings")) {
					setMessTimeingsList(hostelManager.getAll(MessMenuTime.class, "custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and buildingId=" + getTempId2()));
				} else if (displayMessInfoType.equalsIgnoreCase("MessFoodTypes")) {
					setFoodTypeList(hostelManager.getAll(FoodTypes.class,"custId=" + getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and buildingId=" + getTempId2()));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetViewMessMenuItems", results = { @Result(type = "json", name = "success", params = {"includeProperties", "foodMenuTypeList.*" }) }) })
	public String ajaxGetViewMessMenuItems() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetViewMessMenuItems' method");
		}
		try {
			if (getTempId1() > 0) {
				setFoodMenuTypeList(hostelManager.getAll(ViewBuildingMenuItemsDetails.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and buildingId="+ getTempId1() + " order by dayId"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoAddFoodTypes", results = { @Result(location = "messManagement/ajaxAddFoodTypes.jsp", name = "success") }) })
	public String ajaxDoAddFoodTypes() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddFoodTypes' method");
		}
		try {
			setFoodTypes(null);
			setTempId2(Long.valueOf(getParamValue("buldingId")));
			setObjectList(hostelManager.getAll(ViewHostelBuildingDetails.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()));
			/*if (!ObjectFunctions.isNullOrEmpty(getObjectList())) {
			//	ViewHostelBuildingDetails hostelBuding = (ViewHostelBuildingDetails) getObjectList().get(0);
			}*/
			if (!ObjectFunctions.isNullOrEmpty(getTempId1())&& getTempId1() > 0) {
				setFoodTypes((FoodTypes) hostelManager.get(FoodTypes.class,getTempId1()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxSaveFoodTypes", results = { @Result(location = "messManagement/ajaxViewMessSettingsTypesList.jsp", name = "success") }) })
	public String addFoodTypes() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'addFoodTypes' method");
		}
		try {
			FoodTypes foodTypes = null;
			if (!ObjectFunctions.isNullOrEmpty(getTempId2())) {
				foodTypes = new FoodTypes();
				foodTypes.setBuildingId(getTempId2());
				foodTypes.setFoodTypeName(getFoodTypes().getFoodTypeName().toUpperCase());
				foodTypes.setCustId(getUserCustId());
				foodTypes.setAcademicYearId(getUserAcademicYearId());
				hostelManager.save(foodTypes);
				super.addActionMessage("Food type added successfully.");
			}
			loadMessInfoByStatus();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoEditFoodTypes", results = { @Result(location = "messManagement/ajaxEditFoodTypes.jsp", name = "success") }) })
	public String ajaxDoEditFoodTypes() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoEditFoodTypes' method");
		}
		try {
			if (getTempId1() > 0)
				foodTypes = (FoodTypes) hostelManager.get(FoodTypes.class,getTempId1());
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxUpdateFoodTypes", results = { @Result(location = "messManagement/ajaxViewMessSettingsTypesList.jsp", name = "success") }) })
	public String ajaxUpdateFoodTypes() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUpdateFoodTypes' method");
		}
		try {
			if (getTempId1() > 0) {
				FoodTypes foodTypes = (FoodTypes) hostelManager.get(FoodTypes.class, getTempId1());
				foodTypes.setFoodTypeName(getFoodTypes().getFoodTypeName().toUpperCase());
				setTempId2(foodTypes.getBuildingId());
				hostelManager.save(foodTypes);
				super.addActionMessage("Food type updated successfully.");
				loadMessInfoByStatus();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDeleteFoodTypes", results = { @Result(location = "messManagement/ajaxViewMessSettingsTypesList.jsp", name = "success") }) })
	public String ajaxDeleteFoodTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteFoodTypes' method");
		}
		try {
			if (getTempId() > 0) {
				hostelManager.removeFoodTypesByFoodTypeIdCustId(getTempId(), getUserCustId());
				hostelManager.remove(FoodTypes.class, getTempId());
			}
			loadMessInfoByStatus();
			super.addActionMessage("Food types deleted successfully.");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoAddMessTimings", results = { @Result(location = "messManagement/ajaxDoAddMessTimings.jsp", name = "success") }) })
	public String ajaxDoAddMessTimings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewMessDetails' method");
		}
		try {
			loadMessInfoByStatus();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetBuldingFoodTimeings", results = { @Result(location = "messManagement/ajaxDoHostelFoodTimes.jsp", name = "success") }) })
	public String ajaxGetBuldingFoodTimeings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetBuldingFoodTimeings' method");
		}
		try {
			if (getTempId2() > 0) {
				setMessTimeingsList(hostelManager.getAll(MessMenuTime.class,"custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and buildingId="+ getTempId2()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetEditFoodTimes", results = { @Result(type = "json", name = "success", params = {"includeProperties", "messTimeingsList.*" }) }) })
	public String ajaxGetEditFoodTimes() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetEditFoodTimes' method");
		}
		try {
			if (getTempId1() > 0) {
				setMessTimeingsList(hostelManager.getAllFoodTimeingsByBuildingId(getUserCustId(),getTempId1()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxRemovieMenuTimes", results = { @Result(location = "messManagement/ajaxDoHostelFoodTimes.jsp", name = "success") }) })
	public String ajaxRemovieMenuTimes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemovieMenuTimes' method");
		}
		try {
			long messFoodTypeId = Long.valueOf(getParamValue("messFoodTypeId"));
			if (messFoodTypeId > 0) {
				hostelManager.remove(MessMenuTime.class, messFoodTypeId);
			}
			super.addActionMessage("Food menu times deleted successfully.");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDeleteFoodTimeings", results = { @Result(location = "messManagement/ajaxViewMessSettingsTypesList.jsp", name = "success") }) })
	public String ajaxDeleteFoodTimeings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteFoodTimeings' method");
		}
		try {
			if (getTempId() > 0) {
				hostelManager.removeMessTimingsByMessTimeingIdCustId(getTempId(), getBuilding().getId(),getUserCustId());
			}
			loadMessInfoByStatus();
			super.addActionMessage("Food timeings deleted successfully.");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxCreateMessTimeings", results = { @Result(location = "messManagement/ajaxViewMessSettingsTypesList.jsp", name = "success") }) })
	public String ajaxCreateMessTimeings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCreateMessTimeings' method");
		}
		try {
			String messFoodTypeName = null;
			String startTime = null;
			String endTime = null;
			String messFoodTypeId = null;
			JSONObject messTimeingsJson = null;
			MessMenuTime messMenuTime = null;
			JSONArray messTimeingsJsonArray = null;
			if (getTempId2() > 0 && getUserAcademicYearId() != 0 && StringFunctions.isNotNullOrEmpty(getTempString())) {
				messTimeingsJsonArray = new JSONArray(getTempString());
				for (int i = 0; i < messTimeingsJsonArray.length(); i++) {
					messTimeingsJson = messTimeingsJsonArray.getJSONObject(i);

					if (!ObjectFunctions.isNullOrEmpty(messTimeingsJson)) {
						messFoodTypeName = (String) messTimeingsJson.get("messFoodTypeName");
						startTime = (String) messTimeingsJson.get("startTime");
						endTime = (String) messTimeingsJson.get("endTime");
						messFoodTypeId = (String) messTimeingsJson.get("messFoodTypeId");
						if (StringFunctions.isNotNullOrEmpty(messFoodTypeId))
							messMenuTime = (MessMenuTime) hostelManager.get(MessMenuTime.class, Long.valueOf(messFoodTypeId));
						else
							messMenuTime = new MessMenuTime();
						messMenuTime.setMessFoodTypeName(messFoodTypeName.toUpperCase());
						messMenuTime.setStartTime(startTime);
						messMenuTime.setEndTime(endTime);
						messMenuTime.setCustId(getUserCustId());
						messMenuTime.setAcademicYearId(getCurrentAcademicYear().getId());
						messMenuTime.setBuildingId(getTempId2());
						hostelManager.save(messMenuTime);
					}
				}
				if (StringFunctions.isNotNullOrEmpty(messFoodTypeId))
					super.addActionMessage("Food timings updated successfully.");
				else
					super.addActionMessage("Food timings added successfully.");
				buldingFoodItems();
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxdoAddNewMenuItems", results = { @Result(location = "messManagement/ajaxBuildingList.jsp", name = "success") }) })
	public String addNewMenuItems() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addNewMenuItems' method");
		}
		try {
			loadMessInfoByStatus();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetBuldingMenus", results = { @Result(location = "messManagement/ajaxAddMenuItems.jsp", name = "success") }) })
	public String ajaxGetBuldingMenus() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetBuldingMenus' method");
		}
		try {
			setTempList2(hostelManager.getAll(WeekDays.class));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxGetmenuItems", results = { @Result(location = "messManagement/ajaxViewAddMenuItems.jsp", name = "success") }) })
	public String ajaxGetmenuItems() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetmenuItems' method");
		}
		try {
			setMessTimeingsList(hostelManager.getAll(MessMenuTime.class,"custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and buildingId="+ Long.valueOf(getSelectedId())));
			setFoodTypeList(hostelManager.getAll(FoodTypes.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and buildingId="+ Long.valueOf(getSelectedId())));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxEditFoodMenuItems", results = { @Result(type = "json", name = "success", params = {"includeProperties", "objectList.*" }) }) })
	public String ajaxEditFoodMenuItems() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'EditFoodMenuItems' method");
		}
		try {
			if (!StringFunctions.isNullOrEmpty(getAnyId())) {
				setObjectList(hostelManager.getAll(ViewBuildingMenuItemsDetails.class, "custId="+ getUserCustId() + " and buildingId="+ Long.valueOf(getSelectedId()) + " and dayId="+ Long.valueOf(getAnyId())));
				if (ObjectFunctions.isNotNullOrEmpty(getObjectList())) {
					JSONArray res = new JSONArray();
					JSONObject j;
					for (Object obj : getObjectList()) {
						ViewBuildingMenuItemsDetails viewBuildingMenuItemsDetails = (ViewBuildingMenuItemsDetails) obj;
						j = new JSONObject();
						j.put("foodTypeId", viewBuildingMenuItemsDetails.getFoodTypeId());
						j.put("menuItemNames", viewBuildingMenuItemsDetails.getMenuItemNames());
						j.put("messMenuTypeId", viewBuildingMenuItemsDetails.getMessMenuTimeId());
						res.put(j);
					}
					j = new JSONObject();
					j.put("data", res);
					getResponse().getOutputStream().print(j.toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	@Actions( { @Action(value = "ajaxAddWeekMenuItems", results = { @Result(location = "messManagement/ajaxViewMessSettingsTypesList.jsp", name = "success") }) })
	public String ajaxCreateWeekItems() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddWeekItems' method");
		}
		try {
			if (StringFunctions.isNotNullOrEmpty(getTempString())) {
				JSONArray messMenuItemsJsonArray = new JSONArray(getTempString());
				long foodTypeId = 0;
				long messMenuTypeId = 0;
				String menuItemNames = null;
				FoodMenuItems foodMenuItems = null;
				FoodTypes foodTypes = null;
				JSONObject messMenuItemsJson = null;
				if (getTempId2() > 0 && !ObjectFunctions.isNullOrEmpty(getAnyId())) {
					for (int i = 0; i < messMenuItemsJsonArray.length(); i++) {
						messMenuItemsJson = messMenuItemsJsonArray.getJSONObject(i);
						if (!ObjectFunctions.isNullOrEmpty(messMenuItemsJson)) {
							if (!ObjectFunctions.isNullOrEmpty(messMenuItemsJson.get("foodTypeId"))) {
								foodTypeId = Long.valueOf((String) messMenuItemsJson.get("foodTypeId"));
							}
							if (!ObjectFunctions.isNullOrEmpty(messMenuItemsJson.get("messMenuTypeId"))) {
								messMenuTypeId = Long.valueOf((String) messMenuItemsJson.get("messMenuTypeId"));
							}
							if (!ObjectFunctions.isNullOrEmpty(messMenuItemsJson.get("menuItemNames"))) {
								menuItemNames = (String) messMenuItemsJson.get("menuItemNames");
							}
							foodTypes = (FoodTypes) hostelManager.get(FoodTypes.class, "custId="+ getUserCustId() + " and id="+ foodTypeId);
							if (!ObjectFunctions.isNullOrEmpty(foodTypes)) {
								foodMenuItems = (FoodMenuItems) hostelManager.get(FoodMenuItems.class, "custId="+ getUserCustId()+ " and foodTypeId="+ foodTypeId + " and dayId="+ Long.valueOf(getAnyId())+ " and messMenuTimeId="+ messMenuTypeId);
								if (!ObjectFunctions.isNullOrEmpty(foodMenuItems)) {
									if (StringFunctions.isNullOrEmpty(menuItemNames)) {
										adminManager.remove(FoodMenuItems.class,foodMenuItems.getId());
									} else {
										foodMenuItems.setMenuItemNames(menuItemNames);
										foodMenuItems.setCustId(getUserCustId());
										foodMenuItems.setDayId(foodMenuItems.getDayId());
										hostelManager.save(foodMenuItems);
									}
								} else {
									if (StringFunctions.isNotNullOrEmpty(menuItemNames)) {
										foodMenuItems = new FoodMenuItems();
										foodMenuItems.setCustId(getUserCustId());
										foodMenuItems.setMenuItemNames(menuItemNames);
										foodMenuItems.setDayId(Long.valueOf(getAnyId()));
										foodMenuItems.setMessMenuTimeId(messMenuTypeId);
										foodTypes.addFoodMenuItems(foodMenuItems);
										hostelManager.save(foodTypes);
									}
									foodTypes = null;
									foodMenuItems = null;
								}
							}
						}
					}
					super.addActionMessage("Menu items details added successfully.");
					ajaxViewMessManagementDetails();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	/*@Actions( { @Action(value = "manageStudentAndStaff", results = { @Result(location = "ajaxGetHostelFeeHome.jsp", name = "success") }) })
	public String manageStudentAndStaff() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'manageStudentAndStaff' method");
		}
		try {

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}*/
	
	@Actions( {
			@Action(value = "ajaxSearchStudentByCriteria", results = { @Result(location = "ajaxViewStudentList.jsp", name = "success") }),
			@Action(value = "ajaxSearchStudentListByRollNumber", results = { @Result(location = "common/ajaxViewSearchStudentList.jsp", name = "success") }) })
	public String searchStudentForInvoice() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSearchStudentByCriteria' method");
		}
		try {
			String type = getParamValue("addStaffToHostel");
			if (StringFunctions.isNotNullOrEmpty(type)) {
				if (type.equals("Student")) {
					setStudentsList(adminManager.getAll(ViewHostelStudentDetails.class,"custId="+getUserCustId()+" and (firstName like '%"+getAnyTitle()+"%' or lastName like '%"+getAnyTitle()+"%' || admissionNumber like '%"+getAnyTitle()+"%') and academicYearId="+getUserAcademicYearId()+" and accountExpired='N' order by firstName,lastName"));
				} else {
					setStaffsList(adminManager.getStaffDetailsBySearchName(getAnyTitle(), getUserCustId(),Constants.YES_STRING));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoEditStuOrStaffDetails", results = { @Result(location = "ajaxEditStuOrStaffDetails.jsp", name = "success") }) })
	public String ajaxDoEditStuOrStaffDetails() throws URTUniversalException {
		try {
			Staff staff = null;
			Student student = null;
			String gender = null;
			if (getUserAcademicYearId() > 0) {
				setSelectedId(getParamValue("accountId"));
				student = studentManager.getStudentByAccountId(Long.valueOf(getSelectedId()), getUserAcademicYearId(),getUserCustId());
				if (ObjectFunctions.isNullOrEmpty(student)) {
					staff = adminManager.getStaffByAcountIdAndCustId(Long.valueOf(getSelectedId()), Constants.YES_STRING,getUserCustId());
					gender = staff.getAccount().getPerson().getGender();
				} else {
					gender = student.getAccount().getPerson().getGender();
				}
				ViewStaffAllotedBeds viewStaffAllotedBeds = null;
				ViewStudentsAllotedBeds viewStudentsAllotedBeds = null;
				setBuildingList(hostelManager.getAll(ViewBuildingFloorDetails.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+ " and ( gender is null or gender='" + gender+ "') and (fgender is null or fgender='"+ gender + "') "));
				if (StringFunctions.isNotNullOrEmpty(getParamValue("EditStudentStaff"))) {
					if (getParamValue("EditStudentStaff").equalsIgnoreCase("Edit")) {
						if (StringFunctions.isNotNullOrEmpty(getSelectedId())) {
							if (!ObjectFunctions.isNullOrEmpty(student)) {
								viewStudentsAllotedBeds = (ViewStudentsAllotedBeds) adminManager.get(ViewStudentsAllotedBeds.class,"accountId="+ student.getAccount().getId());
								if (!ObjectFunctions.isNullOrEmpty(viewStudentsAllotedBeds))
									setAnyId(String.valueOf(viewStudentsAllotedBeds.getFloorId()));
							} else {
								if (!ObjectFunctions.isNullOrEmpty(staff)) {
									viewStaffAllotedBeds = (ViewStaffAllotedBeds) adminManager.get(ViewStaffAllotedBeds.class,"accountId="+ staff.getAccount().getId());
									if (!ObjectFunctions.isNullOrEmpty(viewStaffAllotedBeds))
										setAnyId(String.valueOf(viewStaffAllotedBeds.getFloorId()));
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoGetRoomsOfOneFloor", results = { @Result(location = "ajaxDoGetFloorRooms.jsp", name = "success") }) })
	public String ajaxDoGetRoomsOfOneFloor() throws URTUniversalException {
		try {
			ViewStaffAllotedBeds viewStaffAllotedBeds = null;
			ViewStudentsAllotedBeds viewStudentsAllotedBeds = null;
			Staff staff = null;
			Student student = null;
			long accountId = Long.valueOf(getParamValue("accountId"));
			setSelectedId(getParamValue("accountId"));
			if (accountId != 0) {
				student = studentManager.getStudentByAccountId(accountId,getUserAcademicYearId(), getUserCustId());
				if (!ObjectFunctions.isNullOrEmpty(student)) {
					viewStudentsAllotedBeds = (ViewStudentsAllotedBeds) adminManager.get(ViewStudentsAllotedBeds.class, "accountId="+ student.getAccount().getId());
					if (!ObjectFunctions.isNullOrEmpty(viewStudentsAllotedBeds))
						setRoomId(String.valueOf(viewStudentsAllotedBeds.getRoomId()));
					if (!ObjectFunctions.isNullOrEmpty(student.getCategoryId()) && student.getCategoryId() != 0) {
						setTempId1(student.getCategoryId());
					}
				} else {
					staff = adminManager.getStaffByAcountIdAndCustId(accountId,"Y", getUserCustId());
					if (!ObjectFunctions.isNullOrEmpty(staff)) {
						viewStaffAllotedBeds = (ViewStaffAllotedBeds) adminManager.get(ViewStaffAllotedBeds.class, "accountId="+ staff.getAccount().getId());
						if (!ObjectFunctions.isNullOrEmpty(viewStaffAllotedBeds))
							setRoomId(String.valueOf(viewStaffAllotedBeds.getRoomId()));
						if (!ObjectFunctions.isNullOrEmpty(staff.getHostelCategoryId()) && staff.getHostelCategoryId() != 0) {
							setTempId1(staff.getHostelCategoryId());
						}
					}
				}
			}
			if (getUserAcademicYearId() > 0) {
				String clause = "academicYearId=" + getUserAcademicYearId()+ " and custId = " + getUserCustId() + " and floorId="+ getParamValue("yourOptionValue");
				setSchoolCategoriesList(adminManager.getAllByCustId("SchoolCategory", getUserCustId(),0));
				setRoomList(hostelManager.getAll(ViewRoomDetails.class, clause));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxDoGetBedsOfOneRoom", results = { @Result(location = "ajaxDoGetRoomBeds.jsp", name = "success") }) })
	public String ajaxDoGetBedsOfOneRoom() throws URTUniversalException {
		try {
			String clause = null;
			if (getUserAcademicYearId() > 0) {
				clause = "academicYearId=" + getUserAcademicYearId()+ " and custId = " + getUserCustId() + " and roomId="+ getParamValue("yourOptionValue") + " and status="+ Constants.STATUS_TRUE + " ORDER BY id DESC";
				List bedList = hostelManager.getAll(Bed.class, clause);
				if (ObjectFunctions.isNotNullOrEmpty(bedList)) {
					Collections.sort(bedList);
					setBedList(bedList);
				}
				if (!StringFunctions.isNullOrEmpty(getParamValue("accountId"))) {
					setSelectedId(getParamValue("accountId"));
				}
			}
			Bed beds = null;
			if (!StringFunctions.isNullOrEmpty(getParamValue("accountId"))) {
				clause = "academicYearId=" + getUserAcademicYearId()+ " and custId = " + getUserCustId()+ " and accountId=" + getParamValue("accountId")+ " and roomId=" + getParamValue("yourOptionValue")+ " and status=" + Constants.STATUS_TRUE;
				beds = (Bed) hostelManager.get(Bed.class, clause);
				if (!ObjectFunctions.isNullOrEmpty(beds)) {
					getBedList().add(beds);
				}
			}
			if (ObjectFunctions.isNotNullOrEmpty(getBedList())) {
				Collections.sort(getBedList());
				setBedList(getBedList());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxAddStudentToHostel", results = { @Result(location = "ajaxViewStudentList.jsp", name = "success") }) })
	public String ajaxAddStudentToHostel() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddStudentToHostel' method");
		}
		try {
			String accountId = getParamValue("student.accountId");
			//String buildingId = getAnyId();
			//String roomId = getParamValue("roomId");
			Bed newBed = null;
			Bed oldBed = null;
			long bedId = Long.valueOf(getParamValue("tempList"));
			if (getUserAcademicYearId() != 0) {
				setStudent(studentManager.getStudentByAccountId(Long.valueOf(accountId), getUserAcademicYearId(),getUserCustId()));
				if (!ObjectFunctions.isNullOrEmpty(getStudent())) {
					if (!ObjectFunctions.isNullOrEmpty(getStudent().getBed())) {
						oldBed = (Bed) hostelManager.get(Bed.class,getStudent().getBed().getId());
						if (!ObjectFunctions.isNullOrEmpty(oldBed)) {
							oldBed.setStatus(false);
							oldBed.setAccountId(null);
							adminManager.save(oldBed);
							super.addActionMessage("Student bed updated successfully.");
						}
					}
					newBed = (Bed) hostelManager.get(Bed.class, bedId);
					if (!ObjectFunctions.isNullOrEmpty(newBed)) {
						getStudent().setBed(newBed);
						if (getTempId1() != 0)
							getStudent().setCategoryId(getTempId1());
						newBed.setStatus(true);
						newBed.setAccountId(Long.valueOf(accountId));
						if (ObjectFunctions.isNullOrEmpty(oldBed)) {
							super.addActionMessage("Student bed created successfully.");
						}
						adminManager.save(newBed);
					}
					setTempString("Student");
				} else {
					setStaff(adminManager.getStaffByAcountIdAndCustId(Long.valueOf(accountId), "Y", getUserCustId()));
					if (!ObjectFunctions.isNullOrEmpty(getStaff())) {
						if (!ObjectFunctions.isNullOrEmpty(getStaff().getBedId())) {
							oldBed = (Bed) hostelManager.get(Bed.class,getStaff().getBedId().getId());
							if (!ObjectFunctions.isNullOrEmpty(oldBed)) {
								oldBed.setStatus(false);
								oldBed.setAccountId(null);
								adminManager.save(oldBed);
								super.addActionMessage("Staff bed updated successfully.");
							}
						}
						newBed = (Bed) hostelManager.get(Bed.class, bedId);
						if (!ObjectFunctions.isNullOrEmpty(newBed)) {
							getStaff().setBedId(newBed);
							if (getTempId1() != 0)
								getStaff().setHostelCategoryId(getTempId1());
							newBed.setStatus(true);
							newBed.setAccountId(Long.valueOf(accountId));
							if (ObjectFunctions.isNullOrEmpty(oldBed)) {
								super.addActionMessage("Staff bed created successfully.");
							}
							adminManager.save(newBed);
						}
					}
				}
				searchHostelStudentOrStaff(getAnyTitle(), getTempString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxViewHostelEvents", results = { @Result(location = "hostelEvents/ajaxViewHostelEvents.jsp", name = "success") }) })
	public String ajaxViewHostelEvents() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewHostelEvents' method");
		}
		try {
			setToDate(new Date());
			if (getUserAcademicYearId() > 0)
				setObjectList(hostelManager.getAllByCustId("HostelEvents",getUserCustId(), getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	
	@Actions( { @Action(value = "ajaxViewWeekdayHostelCalendar", results = { @Result(location = "hostelEvents/viewWeekHostelCalendar.jsp", name = "success") }) })
	public String ajaxViewWeekdayCalendar() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewWeekdayCalendar' method");
		}
		try {
			//ajaxLoadEventStatus();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxViewMessDetails", results = { @Result(location = "messManagement/ajaxViewMessDetails.jsp", name = "success") }) })
	public String ajaxViewMessDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewMessDetails' method");
		}
		try {
			setBuildingList(hostelManager.getAllByCustId("Building",getUserCustId(), getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
    @Actions( { @Action(value = "hostelEventsXml", results = { @Result(location = "hostelEvents/ajaxHostelCalendar.jsp", name = "success") }) })
    public String recurringEventXml() throws IOException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'recurringEventXml' method");
	}
	try {
	    PrintWriter toClient = getResponse().getWriter();
	    getResponse().setContentType("text/xml");
	    Document document = null;
	    RecurringEventsDOM scDOM = new RecurringEventsDOM();
	    //ajaxAllHostelEvents();
	    scDOM.setObjectList(getObjectList());
	    document = scDOM.hostelEvents();
	    DOMUtil.writeXmlToFile(toClient, document);
	} catch (Exception e) {
	    log.error("Entering into 'catch block':" + e.getMessage());
	    e.printStackTrace();
	}
	return null;
    }
   
    
   
    @Actions( { @Action(value = "ajaxDoManageStudentOut", results = { @Result(location = "ajaxManageStudentOut.jsp", name = "success") }) })
    public String ajaxDoManageStudentOut() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDoManageStudentOut' method");
	}
	try {
	    setStudentOutList(hostelManager.getAll(ViewStudentOutHostelDetails.class, " custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+" ORDER BY outDate DESC"));
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
    @Actions( { @Action(value = "ajaxDoAddStudent", results = { @Result(location = "ajaxCreateNewStudent.jsp", name = "success") }),
    	@Action(value = "ajaxDoAddVisitorDetails", results = { @Result(location = "ajaxDoGetVisitorDetails.jsp", name = "success") }) })
    public String ajaxDoAddStudent() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDoAddStudent' method");
	}
	try {
	    setSelectedId(getParamValue("accountId"));
	    getTempString();
	    setStudentOutList(hostelManager.getAll(StudentOut.class, " custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId()));
	    StudentOut studentOut = (StudentOut)adminManager.get(StudentOut.class," custId="+ getUserCustId()+ " and accountId="+Long.valueOf(getSelectedId())+" and academicYearId="+ getUserAcademicYearId()+" and studentInOutStatus='"+Constants.NO_STRING+"' ");
	    log.debug(" custId="+ getUserCustId()+ " and accountId="+Long.valueOf(getSelectedId())+" and academicYearId="+ getUserAcademicYearId()+" and studentInOutStatus='"+Constants.NO_STRING+"' ");
	    if(!ObjectFunctions.isNullOrEmpty(studentOut)){
	    	 setAnyId(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, studentOut.getOutDate()));
	    	 setSelectedDate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, studentOut.getExpectedInDate()));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
    @Actions( { @Action(value = "ajaxCreateNewStudent", results = { @Result(location = "ajaxViewStudentOutList.jsp", name = "success") }),
    	@Action(value = "ajaxDogetVisitorDetails", results = { @Result(location = "ajaxVisitorsInOutList.jsp", name = "success") })})
    public String ajaxCreateNewStudent() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxCreateNewStudent' method");
	}
	try {
	    AcademicYear year = getCurrentAcademicYear();
	    setStudent(studentManager.getStudentByAccountId(getStudent().getAccount().getId(), getUserAcademicYearId(),getUserCustId()));
	    Visitors visitor = null;
	    if (getUserAcademicYearId() > 0) {
		if (!ObjectFunctions.isNullOrEmpty(getVisitors())) {
		    visitor = new Visitors();
		    visitor.setCustId(getUserCustId());
		    visitor.setAccountId(getStudent().getAccount().getId());
		    visitor.setLastUpdatedById(getUser().getId());
		    visitor.setCreatedById(getUser().getId());
		    visitor.setCreatedDate(new Date());
		    visitor.setVisitorName(getStudentOut().getVisitorName());
		    visitor.setRelation(getVisitors().getRelation());
		    visitor.setInDate(getVisitors().getInDate());
		    visitor.setOutDate(getVisitors().getOutDate());
		    visitor.setInTime(getVisitors().getInTime());
		    visitor.setOutTime(getVisitors().getOutTime());
		    visitor.setOutDate(getVisitors().getOutDate());
		    visitor.setReason(getVisitors().getReason());
		    visitor.setAcademicYear(year);
		    setAnyTitle("visitors");
		    adminManager.save(visitor);
		    ajaxSearchVisitorsIn();
		}
		log.debug("cvs  "+ getStudent().getStudentName().toUpperCase());
		if (!ObjectFunctions.isNullOrEmpty(getStudentOut().getOutDate())&& StringFunctions.isNotNullOrEmpty(getStudentOut().getOutTime())&& StringFunctions.isNotNullOrEmpty(getStudentOut().getExceptedInTime())) {
		    //StudentOut studentOut = (StudentOut) adminManager.get(StudentOut.class, getStudentOut().getId());
		    StudentOut student = new StudentOut();
		    student.setCustId(getUserCustId());
		    student.setCreatedById(getUser().getId());
		    student.setCreatedDate(new Date());
		    student.setVisitorName(getStudentOut().getVisitorName());
		    student.setVisitorRelation(getStudentOut().getVisitorRelation());
		    student.setReasonForOuting(getStudentOut().getReasonForOuting());
		    student.setOutDate(getStudentOut().getOutDate());
		    student.setOutTime(getStudentOut().getOutTime());
		    student.setExceptedInTime(getStudentOut().getExceptedInTime());
		    student.setExpectedInDate(getStudentOut().getExpectedInDate());
		    student.setAcademicYear(year);
		    setPlTitle("student");
		    student.setAccountId(getStudent().getAccount().getId());
		    adminManager.save(student);
		    log.debug("cvs if "+ getStudent().getStudentName().toUpperCase());
		 }
		 if(!StringFunctions.isNullOrEmpty(getPlTitle()) && !StringFunctions.isNullOrEmpty(getAnyTitle())){
		    	super.addActionMessage(getStudent().getAccount().getPerson().getFullPersonName().toUpperCase()+" (Student) has got outing permission with "+  getStudentOut().getVisitorName().toUpperCase()+"(Visitor).");
		    	setPlTitle(null);
		    	setAnyTitle(null);
	      }else{
	    	super.addActionMessage(getStudentOut().getVisitorName().toUpperCase()+" visited " + getStudent().getAccount().getPerson().getFullPersonName().toUpperCase()+ "(Student) successfully.");
	     }
	    }
	    ajaxDoManageStudentOut();
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
  /* @Actions( { @Action(value = "ajaxViewStudentInAndOut", results = { @Result(location = "ajaxSearchStudentInOutDetails.jsp", name = "success") }) })
    public String ajaxViewStudentInAndOut() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxViewStudentInAndOut' method");
	}
	try { 	    
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
    }*/
     @Actions( { @Action(value = "ajaxUpdateInStudent", results = { @Result(location = "ajaxViewStudentInRegisterList.jsp", name = "success") }) })
	    public String ajaxUpdateInStudent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxUpdateInStudent' method");
		}
		try {
		    if(getStudentOut().getId() > 0) {
			StudentOut studentout=(StudentOut)adminManager.get(StudentOut.class,getStudentOut().getId());
			if(!ObjectFunctions.isNullOrEmpty(studentout)) {
			    studentout.setActualInDate(new Date());
			    studentout.setStudentInOutStatus(true);
			    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			    studentout.setActualInTime(dateFormat.format(new Date()));
			    studentout.setLastUpdatedById(getUser().getId());
			    studentout.setLastAccessDate(new Date());
			    studentout.setLastUpdatedDate(new Date());
			    adminManager.save(studentout);
			    super.addActionMessage("Successfully student Into the hostel premises.");
			}
		    }
		    ajaxSearchStudentIn();
		} catch (Exception ex) {
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
		}
		return SUCCESS;
	    }

    @Actions( { @Action(value = "ajaxSearchStudentInOut", results = { @Result(location = "ajaxViewStudentRegisterList.jsp", name = "success") }) })
    public String ajaxSearchStudentInOut() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxSearchStudentInOut' method");
	}
	try {
	    StringBuffer  buffer=null;
	    setTodayDate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
	    List<StudentOut> stuOutList = adminManager.getAll(StudentOut.class," academicYearId=" + getUserAcademicYearId()+ " and custId = " + getUserCustId()+ " and outDate <='" + getTodayDate()+ " 00:00:00'" + " and expectedInDate >='"+ getTodayDate() + " 00:00:00'"+ " and studentInOutStatus='" + "N'"+ " OR expectedInDate <='" + getTodayDate()+ " 00:00:00' and actualInDate is null");
	    if (!ObjectFunctions.isNullOrEmpty(stuOutList)) {
		buffer = new StringBuffer();
		buffer.append("(");
		for (StudentOut out : stuOutList) {
		    if (out.getAccountId() != 0) {
			buffer.append(out.getAccountId());
			buffer.append(",");
		    }
		}
		buffer.append("0)");
	    }
	    stuOutList = null;
	    if (!ObjectFunctions.isNullOrEmpty(buffer)) {
		setStudentsList(adminManager.getAll(ViewStudentsAllotedBeds.class, "custId="+ getUserCustId() + " and(firstName like '%"+ getAnyTitle() + "%' or lastName like '%"+ getAnyTitle() + "%') and status='"+ Constants.YES_STRING+ "' and accountId not in" + buffer.toString()));
	    } else {
		setStudentsList(adminManager.getAll(ViewStudentsAllotedBeds.class, "custId="+ getUserCustId() + " and(firstName like '%"+ getAnyTitle() + "%' or lastName like '%"+ getAnyTitle() + "%') and status='"+ Constants.YES_STRING + "' "));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
    @Actions( { @Action(value = "ajaxSearchStudentIn", results = { @Result(location = "ajaxViewStudentInRegisterList.jsp", name = "success") }) })
    public String ajaxSearchStudentIn() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxSearchStudentIn' method");
	}
	try {
	     setStudentOutList(hostelManager.getAll(ViewStudentOutHostelDetails.class, " custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+" and studentInOutStatus='N' ORDER BY outDate DESC"));
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
    @Actions( { @Action(value = "ajaxDoAddInStudent", results = { @Result(location = "ajaxCreateInStudent.jsp", name = "success") }) })
    public String ajaxDoAddInStudent() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDoAddInStudent' method");
	}
	try {
	    setSelectedId(getParamValue("accountId"));
	    setStudentOutList(hostelManager.getAll(StudentOut.class, " custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId()));
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
    @Actions( { @Action(value = "ajaxUploadHostelStudentOutData", results = { @Result(location = "importStudentOutExcelSheet.jsp", name = "success") }) })
    public String ajaxUploadHostelStudentOutData() {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxUploadHostelStudentOutData' method");
	}
	try {
		 boolean excelFileType = false;
		excelFileType = validateExcelFileType(getUploadContentType());
		if(excelFileType){
			log.debug("No file to upload....");
			super.addActionError("File type not matched.");
			  return "dummyInit";
		}
	    /*if (!(Constants.FILE_TYPE_XLS.equalsIgnoreCase(getUploadContentType()) || Constants.FILE_TYPE_APPLICATION_XLS.equalsIgnoreCase(getUploadContentType()))) {
		log.debug("No file to upload....");
		super.addActionError("File type not matched.");
		return "dummyInit";
	    }*/
	    ViewStudentsAllotedBeds studentsAllotedBeds = null;
	    if (getAcademicYearId() > 0) {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("en", "EN"));
		Workbook workbook = Workbook.getWorkbook(getUpload(), ws);
		Sheet sheet = null;
		for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
		    sheet = workbook.getSheet(sheetNum);
		    int rowSize = sheet.getRows();
		    for (int i = 5; i < rowSize; i++) {
			String staus = sheet.getCell(11, i).getContents();
			if ("Yes".equalsIgnoreCase(staus)) {
			    long rollNumber = Long.valueOf(sheet.getCell(0, i).getContents());
			    String className = sheet.getCell(1, i).getContents();
			    String[] ids = className.split("-");
			    String clName = ids[0];
			    String section = null;
			    if (ids.length > 1) {
				section = ids[1];
			    }
			    if (!StringFunctions.isNullOrEmpty(section)) {
				studentsAllotedBeds = (ViewStudentsAllotedBeds) hostelManager.get(ViewStudentsAllotedBeds.class," custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and rollNumber="+ rollNumber+ " and className='"+ clName+ "' and section='"+ section + "'");
			    } else {
				studentsAllotedBeds = (ViewStudentsAllotedBeds) hostelManager.get(ViewStudentsAllotedBeds.class," custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and rollNumber="+ rollNumber+ " and className='"+ clName + "'");
			    }
			    AcademicYear year = getCurrentAcademicYear();
			    if (!ObjectFunctions.isNullOrEmpty(studentsAllotedBeds)) {
				StudentOut studentOut = new StudentOut();
				studentOut.setCustId(getUserCustId());
				studentOut.setCreatedById(getUser().getId());
				studentOut.setCreatedDate(new Date());
				studentOut.setAcademicYear(year);
				studentOut.setAccountId(studentsAllotedBeds.getAccountId());
				studentOut.setOutDate(DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN,sheet.getCell(4, i).getContents()));
				studentOut.setOutTime(sheet.getCell(5, i).getContents());
				studentOut.setExpectedInDate(DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN,sheet.getCell(6, i).getContents()));
				studentOut.setExceptedInTime(sheet.getCell(7, i).getContents());
				studentOut.setVisitorName(sheet.getCell(8, i).getContents());
				studentOut.setReasonForOuting(sheet.getCell(9,i).getContents());
				studentOut.setInformParentsOnOut(sheet.getCell(10, i).getContents());
				adminManager.save(studentOut);
			    }
			}
		    }
		    super.addActionMessage("Successfully uploaded the student-out details.");
		}
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    @Actions( { @Action(value = "ajaxDownloadStudentOutFiles", results = {}) })
    public void ajaxDownloadStudentOutFiles() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDownloadStudentOutFiles' method");
	}
	try {
	    StringBuffer buffer = null;
	    List<ViewStudentsAllotedBeds> studentsList=null;
	    if (getUserAcademicYearId() != 0) {
		String fileName = null;
		if (ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYearName")))
		    fileName = "student In Details";
		else
		    fileName = "student In Details_"+ getSession().getAttribute("academicYearName");
		ExcelView excelView = new ExcelView();
		getResponse().setContentType(excelView.getMimeType());
		getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
		excelView.setWb(Workbook.createWorkbook(getResponse().getOutputStream()));
		excelView.setWorkSheetName("Student-In");
		excelView.createWorkSheet(0);
		excelView.setDefaultFormat(excelView.getArial10format());
		excelView.getWs().setColumnView(0, 13);
		excelView.getWs().setColumnView(1, 15);
		excelView.getWs().setColumnView(2, 25);
		excelView.getWs().setColumnView(3, 25);
		excelView.getWs().setColumnView(4, 15);
		excelView.getWs().setColumnView(5, 10);
		excelView.getWs().setColumnView(6, 15);
		excelView.getWs().setColumnView(7, 10);
		excelView.getWs().setColumnView(8, 25);
		excelView.getWs().setColumnView(9, 25);
		excelView.getWs().setColumnView(10,25);
		excelView.getWs().setColumnView(11,10);
		setHostel((Hostel)adminManager.get(Hostel.class, "custId="+getUserCustId()));
		WritableFont boldfont10=null;
        	WritableCellFormat cellFormat10=null;
        	boldfont10 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true);
        	cellFormat10 = new WritableCellFormat(boldfont10);
		boldfont10.setColour(Colour.WHITE);
		cellFormat10=ExcelView.getUserFormattedCell(boldfont10, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
		if (!ObjectFunctions.isNullOrEmpty(getHostel().getHostelName())) {
		    excelView.getWs().mergeCells(0, 0, 11, 0);
		    excelView.getWs().addCell(new Label(0, 0, getHostel().getHostelName(),cellFormat10));
		}
		excelView.getWs().mergeCells(0, 1, 11, 0);
		excelView.getWs().addCell(new Label(0, 1, "Note:- If you want to send student out, you have to enter some details in excel sheet. " ));
		excelView.getWs().mergeCells(0, 2, 11, 1);
		excelView.getWs().addCell(new Label(0, 2, "         1. In 5th column you have to enter out date, this should be in 'DD/MM/YYYY' format. " ));
		excelView.getWs().mergeCells(0, 3, 11, 2);
		excelView.getWs().addCell(new Label(0, 3, " 	        2. In 6th column you have to enter out time, this should be in 'HH:MM AM/PM' format." ));
		excelView.getWs().mergeCells(0, 4, 11, 3);
		excelView.getWs().addCell(new Label(0, 4, "	         3. In 7th column you have to enter student expected in date, this should be in 'DD/MM/YYYY' format. " ));
		excelView.getWs().mergeCells(0, 5, 11, 4);
		excelView.getWs().addCell(new Label(0, 5, "	         4. In 8th column you have to enter student expected in time, this should be in 'HH:MM AM/PM' format. " ));
		excelView.getWs().mergeCells(0, 6, 11, 5);
		excelView.getWs().addCell(new Label(0, 6, "	         5. In 9th column you have to enter visitor name. " ));
		excelView.getWs().mergeCells(0, 7, 11, 6);
		excelView.getWs().addCell(new Label(0, 7, "         6. In 10th column you have to enter reason for which purpose student is going to out. " ));
		excelView.getWs().mergeCells(0, 8, 11, 7);
		excelView.getWs().addCell(new Label(0, 8, "         7. In 12th column you have to change the status who are going out those student(s) should be change the status is as 'Yes'."));
		/*if (!ObjectFunctions.isNullOrEmpty(getHostel().get)) {
		    excelView.getWs().mergeCells(0, 3, size - 1, 3);
		    excelView.getWs().addCell(new Label(0, 3, customer.getCustomerAddress(),cellFormat8));
		}*/
		cellFormat10=ExcelView.getUserFormattedCell(boldfont10, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
		excelView.getWs().addCell(new Label(0, 9, "Roll Number",cellFormat10));
		excelView.getWs().addCell(new Label(1, 9, "Class of Student",cellFormat10));
		excelView.getWs().addCell(new Label(2, 9, "Student Name",cellFormat10));
		excelView.getWs().addCell(new Label(3, 9, "Room Name/Bed Number",cellFormat10));
		excelView.getWs().addCell(new Label(4, 9, "Out Date",cellFormat10));
		excelView.getWs().addCell(new Label(5, 9, "Out Time",cellFormat10));
		excelView.getWs().addCell(new Label(6, 9, "Expected In Date",cellFormat10));
		excelView.getWs().addCell(new Label(7, 9, "In Time",cellFormat10));
		excelView.getWs().addCell(new Label(8, 9, "Visitor Name",cellFormat10));
		excelView.getWs().addCell(new Label(9, 9, "Reason",cellFormat10));
		excelView.getWs().addCell(new Label(10, 9, "Inform Parents On Out",cellFormat10));
		excelView.getWs().addCell(new Label(11, 9, "Status",cellFormat10));
		setTodayDate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
		List<StudentOut> stuOutList = adminManager.getAll(StudentOut.class," academicYearId=" + getUserAcademicYearId()+ " and custId = " + getUserCustId()+ " and outDate <='" + getTodayDate()+ " 00:00:00'" + " and expectedInDate >='"+ getTodayDate() + " 00:00:00'"+ " and studentInOutStatus='" + "N'"+ " OR expectedInDate <='" + getTodayDate()+ " 00:00:00' and actualInDate is null");
		    if (!ObjectFunctions.isNullOrEmpty(stuOutList)) {
			buffer = new StringBuffer();
			buffer.append("(");
			for (StudentOut out : stuOutList) {
			    if (out.getAccountId() != 0) {
				buffer.append(out.getAccountId());
				buffer.append(",");
			    }
			}
			buffer.append("0)");
		    }
		    stuOutList = null;
		    if (!ObjectFunctions.isNullOrEmpty(buffer)) {
			studentsList=adminManager.getAll(ViewStudentsAllotedBeds.class, "custId="+ getUserCustId() + "  and academicYearId="+getUserAcademicYearId() +" and status='"+ Constants.YES_STRING+ "' and accountId not in" + buffer.toString());
		    } else {
			studentsList=adminManager.getAll(ViewStudentsAllotedBeds.class, "custId="+ getUserCustId() + "  and academicYearId="+getUserAcademicYearId() +" and status='"+ Constants.YES_STRING + "' ");
		    }
		if (ObjectFunctions.isNotNullOrEmpty(studentsList)) {
		    int columnStart = 10;
		    for (ViewStudentsAllotedBeds studentperson : studentsList) {
			if (!ObjectFunctions.isNullOrEmpty(studentperson)) {
			    excelView.getWs().addCell(new Label(0, columnStart, String.valueOf(studentperson.getRollNumber())));
			    if(!StringFunctions.isNullOrEmpty(studentperson.getSection())) {
				excelView.getWs().addCell(new Label(1, columnStart, studentperson.getClassName()+"-"+studentperson.getSection()));
			    }else {
				excelView.getWs().addCell(new Label(1, columnStart, studentperson.getClassName()));
			    }
			    
			    excelView.getWs().addCell(new Label(2, columnStart, studentperson.getFirstName()+" "+studentperson.getLastName()));
			    excelView.getWs().addCell(new Label(3, columnStart, String.valueOf(studentperson.getRoomName()+"/"+studentperson.getBedName()+"-"+studentperson.getBedLevel())));
			    //excelView.getWs().addCell(new Label(11,columnStart,"Yes"));
			    columnStart++;
			}
		    }
		}
		excelView.getWb().write();
		excelView.getWb().close();
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }
  /*  @Actions( { @Action(value = "ajaxViewStudentIn", results = { @Result(location = "ajaxViewSearchStudentInDetails.jsp", name = "success") }) })
    public String ajaxViewStudentIn() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxViewStudentIn' method");
	}
	try {
	    
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
    }*/
    @Actions( { @Action(value = "ajaxDownloadStudentInFiles", results = {}) })
    public void ajaxDownloadStudentInFiles() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDownloadStudentInFiles' method");
	}
	try {
	//    StringBuffer buffer = null;
	//    List<ViewStudentOutHostelDetails> studentsList=null;
	    if (getUserAcademicYearId() != 0) {
		String fileName = null;
		if (ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYearName")))
		    fileName = "student Out Details";
		else
		    fileName = "student Out Details_"+ getSession().getAttribute("academicYearName");
		ExcelView excelView = new ExcelView();
		getResponse().setContentType(excelView.getMimeType());
		getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
		excelView.setWb(Workbook.createWorkbook(getResponse().getOutputStream()));
		excelView.setWorkSheetName("Student-Out");
		excelView.createWorkSheet(0);
		excelView.setDefaultFormat(excelView.getArial10format());
		excelView.getWs().setColumnView(0, 13);
		excelView.getWs().setColumnView(1, 15);
		excelView.getWs().setColumnView(2, 25);
		excelView.getWs().setColumnView(3, 25);
		excelView.getWs().setColumnView(4, 15);
		excelView.getWs().setColumnView(5, 10);
		excelView.getWs().setColumnView(6, 15);
		excelView.getWs().setColumnView(7, 10);
		excelView.getWs().setColumnView(8, 15);
		excelView.getWs().setColumnView(9, 13);
		excelView.getWs().setColumnView(10,11);
		excelView.getWs().setColumnView(11,35);
		setHostel((Hostel)adminManager.get(Hostel.class, "custId="+getUserCustId()));
		WritableFont boldfont10=null;
        	WritableCellFormat cellFormat10=null;
        	boldfont10 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true);
        	cellFormat10 = new WritableCellFormat(boldfont10);
		boldfont10.setColour(Colour.WHITE);
		cellFormat10=ExcelView.getUserFormattedCell(boldfont10, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
		if (!ObjectFunctions.isNullOrEmpty(getHostel().getHostelName())) {
		    excelView.getWs().mergeCells(0, 0, 11, 0);
		    excelView.getWs().addCell(new Label(0, 0, getHostel().getHostelName(),cellFormat10));
		}
		excelView.getWs().mergeCells(0, 1, 11, 0);
		excelView.getWs().addCell(new Label(0, 1, "Note:- If you want to send student out, you have to enter some details in excel sheet. " ));
		excelView.getWs().mergeCells(0, 2, 11, 1);
		excelView.getWs().addCell(new Label(0, 2, "         1. In 5th column you have to enter out date, this should be in 'DD/MM/YYYY' format. " ));
		excelView.getWs().mergeCells(0, 3, 11, 2);
		excelView.getWs().addCell(new Label(0, 3, "	         2. In 6th column you have to enter out time, this should be in 'HH:MM AM/PM' format." ));
		excelView.getWs().mergeCells(0, 4, 11, 3);
		excelView.getWs().addCell(new Label(0, 4, "	         3. In 7th column you have to enter student expected in date, this should be in 'DD/MM/YYYY' format. " ));
		excelView.getWs().mergeCells(0, 5, 11, 4);
		excelView.getWs().addCell(new Label(0, 5, "	         4. In 8th column you have to enter student expected in time, this should be in 'HH:MM AM/PM' format. " ));
		excelView.getWs().mergeCells(0, 6, 11, 5);
		excelView.getWs().addCell(new Label(0, 6, "	         5. In 9th column you have to enter visitor name. " ));
		excelView.getWs().mergeCells(0, 7, 11, 6);
		excelView.getWs().addCell(new Label(0, 7, "         6. In 10th column you have to enter reason for which purpose student is going to out. " ));
		excelView.getWs().mergeCells(0, 8, 11, 7);
		excelView.getWs().addCell(new Label(0, 8, "         7. In 11th column you have to change the student arrival info."));
		/*if (!ObjectFunctions.isNullOrEmpty(getHostel().get)) {
		    excelView.getWs().mergeCells(0, 3, size - 1, 3);
		    excelView.getWs().addCell(new Label(0, 3, customer.getCustomerAddress(),cellFormat8));
		}*/
		cellFormat10=ExcelView.getUserFormattedCell(boldfont10, Colour.GREEN, false, false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.NONE,BorderLineStyle.NONE);
		excelView.getWs().addCell(new Label(0,9, "Roll Number",cellFormat10));
		excelView.getWs().addCell(new Label(1,9, "Class of Student",cellFormat10));
		excelView.getWs().addCell(new Label(2,9, "Student Name",cellFormat10));
		excelView.getWs().addCell(new Label(3,9, "Room Name/Bed Number",cellFormat10));
		excelView.getWs().addCell(new Label(4,9, "Out Date",cellFormat10));
		excelView.getWs().addCell(new Label(5,9, "Out Time",cellFormat10));
		excelView.getWs().addCell(new Label(6,9, "Expected In Date",cellFormat10));
		excelView.getWs().addCell(new Label(7,9, "In Time",cellFormat10));
		excelView.getWs().addCell(new Label(8,9, "Visitor Name",cellFormat10));
		excelView.getWs().addCell(new Label(9,9, "Status",cellFormat10));
		excelView.getWs().addCell(new Label(10,9, "Comments",cellFormat10));
		excelView.getWs().addCell(new Label(11,9, "Inform Parents about student arrival",cellFormat10));
		setTodayDate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
		List<ViewStudentOutHostelDetails> stuOutList = adminManager.getAll(ViewStudentOutHostelDetails.class," academicYearId=" + getUserAcademicYearId()+ " and custId = " + getUserCustId()+ " and outDate <='" + getTodayDate()+ " 00:00:00'" + " and expectedInDate >='"+ getTodayDate() + " 00:00:00'"+ " and studentInOutStatus='" + "Y'"+ " OR expectedInDate <='" + getTodayDate()+ " 00:00:00' and actualInDate is null");
		if (ObjectFunctions.isNotNullOrEmpty(stuOutList)) {
		    int columnStart = 10; 
		    for (ViewStudentOutHostelDetails studentperson : stuOutList) {
			if (!ObjectFunctions.isNullOrEmpty(studentperson)) {
			    excelView.getWs().addCell(new Label(0, columnStart, String.valueOf(studentperson.getRollNumber())));
			    if(!StringFunctions.isNullOrEmpty(studentperson.getSection())) {
				excelView.getWs().addCell(new Label(1, columnStart, studentperson.getClassName()+"-"+studentperson.getSection()));
			    }else {
				excelView.getWs().addCell(new Label(1, columnStart, studentperson.getClassName()));
			    }
			    excelView.getWs().addCell(new Label(2, columnStart, studentperson.getFirstName()+" "+studentperson.getLastName()));
			    excelView.getWs().addCell(new Label(3, columnStart, String.valueOf(studentperson.getRoomName()+"/"+studentperson.getBedName()+"-"+studentperson.getBedLevel())));
			    excelView.getWs().addCell(new Label(4, columnStart, String.valueOf(studentperson.getOutDateStr())));
			    excelView.getWs().addCell(new Label(5, columnStart, studentperson.getOutTime()));
			    excelView.getWs().addCell(new Label(6, columnStart, String.valueOf(studentperson.getExpectedInDateStr())));
			    excelView.getWs().addCell(new Label(7, columnStart, studentperson.getExceptedInTime()));
			    excelView.getWs().addCell(new Label(8, columnStart, studentperson.getVisitorName()));
			    
			    columnStart++;
			}
		    }
		}
		excelView.getWb().write();
		excelView.getWb().close();
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }
    @Actions( { @Action(value = "ajaxUploadHostelStudentInData", results = { @Result(location = "importStudentInExcelSheet.jsp", name = "success") }) })
    public String ajaxUploadHostelStudentInData() {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxUploadHostelStudentInData' method");
	}
	try {
		 boolean excelFileType = false;
		 excelFileType = validateExcelFileType(getUploadContentType());
		 if(excelFileType){
			log.debug("No file to upload....");
			super.addActionError("File type not matched.");
			  return "dummyInit";
		 }
	   /* if (!(Constants.FILE_TYPE_XLS.equalsIgnoreCase(getUploadContentType()) || Constants.FILE_TYPE_APPLICATION_XLS.equalsIgnoreCase(getUploadContentType()))) {
		log.debug("No file to upload....");
		super.addActionError("File type not matched.");
		return "dummyInit";
	    }*/
	    ViewStudentOutHostelDetails inHostelDetails = null;
	    if (getAcademicYearId() > 0) {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("en", "EN"));
		Workbook workbook = Workbook.getWorkbook(getUpload(), ws);
		Sheet sheet = null;
		for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
		    sheet = workbook.getSheet(sheetNum);
		    int rowSize = sheet.getRows();
		    for (int i = 5; i < rowSize; i++) {
			String staus = sheet.getCell(9, i).getContents();
			if ("Yes".equalsIgnoreCase(staus)) {
			    long rollNumber = Long.valueOf(sheet.getCell(0, i).getContents());
			    String className = sheet.getCell(1, i).getContents();
			    String[] ids = className.split("-");
			    String clName = ids[0];
			    String section = null;
			    if (ids.length > 1) {
				section = ids[1];
			    }
			    setTodayDate(DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, DateFormatter.parseString(DateFormatter.DDMMCCYY_PATTERN,sheet.getCell(4, i).getContents())));
			    if (!StringFunctions.isNullOrEmpty(section)) {
				inHostelDetails = (ViewStudentOutHostelDetails) hostelManager.get(ViewStudentOutHostelDetails.class," custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and rollNumber="+ rollNumber+ " and className='"+ clName+ "' and section='"+ section+ "' and outDate='"+ getTodayDate()+ " 00:00:00' ");
			    } else {
				inHostelDetails = (ViewStudentOutHostelDetails) hostelManager.get(ViewStudentOutHostelDetails.class," custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and rollNumber="+ rollNumber+ " and className='"+ clName+ "' and outDate='"+ getTodayDate()+ " 00:00:00'");
			    }
			    if (!ObjectFunctions.isNullOrEmpty(inHostelDetails)) {
				StudentOut studentOut = (StudentOut) adminManager.get(StudentOut.class, inHostelDetails.getStudentOutId());
				if (!ObjectFunctions.isNullOrEmpty(studentOut)) {
				    studentOut.setLastUpdatedById(getUser().getId());
				    studentOut.setLastUpdatedDate(new Date());
				    studentOut.setLastAccessDate(new Date());
				    studentOut.setActualInDate(new Date());
				    studentOut.setStudentInOutStatus(true);
				    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				    studentOut.setActualInTime(dateFormat.format(new Date()));
				    studentOut.setStudentInOutStatus(true);
				    adminManager.save(studentOut);
				}
			    }
			}
		    }
		    super.addActionMessage("Successfully uploaded the student-out details.");
		}
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    @Actions( { @Action(value = "ajaxSearchVisitorsIn", results = { @Result(location = "ajaxVisitorsInOutList.jsp", name = "success") }) })
    public String ajaxSearchVisitorsIn() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxSearchVisitorsIn' method");
	}
	try {
	     if (StringFunctions.isNotNullOrEmpty(getTempString())) {
		setObjectList(hostelManager.getAll(Visitors.class, " custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " ORDER BY inDate DESC"));
	    }
	}
	catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
    
    @Actions( { @Action(value = "ajaxSearchStudent", results = { @Result(location = "ajaxViewStudentInRegisterList.jsp", name = "success") }) })
    public String ajaxSearchStudent() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxSearchStudent' method");
	}
	try {
	    setStudentOutList(adminManager.getAll(ViewStudentOutHostelDetails.class, "custId="+ getUserCustId() + " and(firstName like '%"+ getAnyTitle() + "%' or lastName like '%"+ getAnyTitle() + "%') and studentInOutStatus='N' ORDER BY outDate DESC" ));
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
    
    @Actions( {
		@Action(value = "ajaxViewHostelStudentInvoiceModifyers", results = { @Result(location = "fee/deleteStudentInvoice/ajaxViewModifyHostelStudentInvoiceMembers.jsp", name = "success") })})
		public String ajaxViewHostelStudentInvoiceModifyers() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewHostelStudentInvoiceModifyers' method");
			}
			try {
				setTodayDate(DateFormatter.formatDate(DateFormatter.ddMMMyyyy_PATTERN1, new Date()));
				String todayDate = DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date());
				setStudentPaymentList(adminManager.getAll("select invoiceNumber,firstName,lastName,rollNumber,paidAmount,paymentDate,studentId,lastUpdatedDate from vw_studentHostelFeePaymentDetails where paymentDate like '%"+ todayDate+ "%'  and deleteStatus='"+ Constants.NO_STRING+ "' and custId="+ getUserCustId() + " and academicYearId="+getUserAcademicYearId()+" group by invoiceNumber"));
				setStaffPaymentList(adminManager.getAll("select invoiceNumber,firstName,lastName,paymentType,paidAmount,paymentDate,staffId,lastUpdatedDate from vw_staffHostelFeePaymentDetails where paymentDate like '%"+ todayDate+ "%'  and deleteStatus='"+ Constants.NO_STRING+ "' and custId="+ getUserCustId() + " and academicYearId="+getUserAcademicYearId()+" group by invoiceNumber"));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
	
	@Actions( {
		@Action(value = "ajaxDoModifyHostelStudentInvoice", results = { @Result(location = "fee/deleteStudentInvoice/ajaxDoModifyHostelStudentInvoice.jsp", name = "success") })})
		public String ajaxDoModifyTransportInvoice() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoModifyTransportInvoice' method");
			}
			try {
				setAnyId(getParamValue("studentId"));
				setSelectedId(getParamValue("invoiceNumber"));
				Date date = DateFormatter.parseString(DateFormatter.YYYYMMDDHHMMSSN_3_PATTERN,getParamValue("lastUpdatedDateStr"));
				SimpleDateFormat lastUpdatedStr = new SimpleDateFormat(DateFormatter.YYYY_MM_DD_PATTERN);
				setSelectedDate(lastUpdatedStr.format(date));
				if(!StringFunctions.isNullOrEmpty(getParamValue("type"))) {
				    setTempString(getParamValue("type"));
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
	
	
	
	@Actions( {
		@Action(value = "ajaxDoDeleteHostelStudentInvoice", results = { @Result(location = "fee/deleteStudentInvoice/ajaxDoDeleteHostelStudentInvoice.jsp", name = "success") })})
		public String ajaxDoDeleteTransportInvoice() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoDeleteTransportInvoice' method");
			}
			try {
				setAnyId(getParamValue("studentId"));
				setSelectedId(getParamValue("invoiceNumber"));
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
	
	@Actions( {
		@Action(value = "ajaxDoModifyHostelStaffInvoice", results = { @Result(location = "fee/deleteStaffInvoice/ajaxDoModifyHostelStaffInvoice.jsp", name = "success") })})
		public String ajaxDoModifyHostelStaffInvoice() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoModifyTransportInvoice' method");
			}
			try {
				setAnyId(getParamValue("staffId"));
				setSelectedId(getParamValue("invoiceNumber"));
				Date date = DateFormatter.parseString(DateFormatter.YYYYMMDDHHMMSSN_3_PATTERN,getParamValue("lastUpdatedDateStr"));
				SimpleDateFormat lastUpdatedStr = new SimpleDateFormat(DateFormatter.DD_MM_YYYY_HHMMSS_PATTERN);
				setSelectedDate(lastUpdatedStr.format(date));
				if(!StringFunctions.isNullOrEmpty(getParamValue("type"))) {
				    setTempString(getParamValue("type"));
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
	}
	
	 @Actions( { 
		    @Action(value = "ajaxCheckHostelEmailId", results = { @Result(type = "json", name = "success", params = {"includeProperties", "autoCheck" }) }) })
		public String ajaxCheckEmailId() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckHostelEmailId' method");
		}
		try {
		    String custEmail = getParamValue("keyWord");
		    if (StringFunctions.isNotNullOrEmpty(custEmail)) {
			List usersList = (List) adminManager.getAll(Hostel.class, "custId="+ getUserCustId() +" and custEmail like '%"+custEmail.trim()+"%'");

			if (ObjectFunctions.isNullOrEmpty(usersList)) {
				setAutoCheck("0");
			} else if (usersList.size() > 0) {
				setAutoCheck("1");
			} else {
				setAutoCheck("0");
			}
		    }
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 @Actions( { 
		    @Action(value = "ajaxDoGetBuildingList", results = { @Result(location = "ajaxHostelGendersList.jsp", name = "success") }) })
		public String ajaxDoGetBuildingList() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetBuildingList' method");
		}
		try { 
			if(StringFunctions.isNotNullOrEmpty(getTempString())){
				 String[] ids = getTempString().split("_");
				 long buildingId = Long.valueOf(ids[1]);
				 setFloor((Floor)adminManager.get(Floor.class, Long.valueOf(getAnyId())));
				 setBuilding((Building) hostelManager.get(Building.class, buildingId));
			}
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	
	@Actions( {
		@Action(value = "ajaxDoAssignStudent", results = { @Result(location = "ajaxDoAssignStudents.jsp", name = "success") }) })
public String ajaxDoAssignStudent() throws URTUniversalException {

	if (log.isDebugEnabled()) { 
		log.debug("Entering 'ajaxDoAssignStudent' method");
	}
	try {
		
		ajaxLoadManageInfoByFloors();
		ajaxDoGetFloorsByBuildings();

	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}

	return SUCCESS;
}
	
	@Actions( {
		@Action(value = "ajaxDoGetFloorsByBuildings", results = { @Result(location = "ajaxGetFloorsByBuilding.jsp", name = "success") }) })
public String ajaxDoGetFloorsByBuildings() throws URTUniversalException {

	if (log.isDebugEnabled()) { 
		log.debug("Entering 'ajaxDoGetFloorsByBuildings' method");
	}
	try {
		
		ViewBuildingFloorDetails viewBuildingFloorDetails = null;
		setFloorList(hostelManager.getAll(ViewBuildingFloorDetails.class," custId=" + getUserCustId() + " and academicYearId="+ getUserAcademicYearId()+ " and buildingId="+ getTempId2() +" order by buildingName,floorName,floorLevel"));
		if (getTempId2() <= 0) {
			if (ObjectFunctions.isNotNullOrEmpty(getFloorList())) {
				viewBuildingFloorDetails = (ViewBuildingFloorDetails) getObjectList().get(0);
				setTempId2(viewBuildingFloorDetails.getFloorId());
			}
		}
		if (getTempId2() > 0) {
			setFloor((Floor) hostelManager.get(Floor.class, getTempId1()));
		}
	
	
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}

	return SUCCESS;
}
	@Actions( {
		@Action(value = "ajaxGetRoomsByFloorId", results = { @Result(location = "ajaxGetRoomsByFloor.jsp", name = "success") }) })
	public String ajaxGetRoomsByFloorId() throws URTUniversalException {
	
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxGetRoomsByFloorId' method");
		}
		try {
			setRoomList(adminManager.getAll(Room.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and floorId="+String.valueOf(getTempId1())));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
	}
	@Actions( {
		@Action(value = "ajaxGetClassesByFloorId", results = { @Result(location = "ajaxGetClassesByFloors.jsp", name = "success") }) })
	public String ajaxGetClassesByFloorId() throws URTUniversalException {
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxGetClassesByFloorId' method");
		}
		try {
			ajaxGetRoomsByFloorId();
			if(ObjectFunctions.isNotNullOrEmpty(getRoomList()))
			{ 
				boolean isRoomCapacity=false;
				for(Room room: getRoomList())
				{
					if(!StringFunctions.isNullOrEmpty(room.getCapacity())){
						isRoomCapacity=true;
					}
				}
				if(isRoomCapacity){
					if(getUserAcademicYearId() > 0){
						checkHostelStudyClassHavingStudentsOrNot();
					}
					setAnyTitle("Y");
				}else{
					super.addActionMessage("Beds are not created for the room. Please create Beds in Manage Rooms.");
				}
			} 
			else
			{
				setAnyTitle("N");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
	}
	public void displayDropDownlistFromNamedRangeForExcelSheet(int columnNum,int  rowNum,String dataValidationRangeName,ExcelView excelView,String cellContent){
		try{
			Blank sheetxls = new Blank(columnNum,rowNum);
			WritableCellFeatures wcf = new WritableCellFeatures();
			wcf.setDataValidationRange(dataValidationRangeName);
			excelView.getWs().addCell(new Label(columnNum,rowNum, cellContent,excelView.getWrapCellFormat()));
			if(StringFunctions.isNotNullOrEmpty(cellContent)){
				sheetxls.setCellFeatures(wcf);
			}else{
				sheetxls.setCellFeatures(wcf);
				excelView.getWs().addCell(sheetxls);
			}
			sheetxls=null;
			wcf=null;
			dataValidationRangeName = null;
			cellContent = null;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Actions( { @Action(value = "ajaxDownloadStudentsForHostel", results = {}) })
    public void ajaxDownloadStudentsForHostel() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDownloadStudentsForHostel' method");
	}
	try {
	    String floorId=null;
	    List<Object[]> studentDetails = null;
	    List<Object[]> roomDetailsList = null;
	    List<StudyClass> studyClassList = null;
	   // List<String> roomAndBedNameList = new ArrayList<String>();
	    if (getUserAcademicYearId() != 0 &&  StringFunctions.isNotNullOrEmpty(getSelectedId())) {
	    	floorId=String.valueOf(getTempId1());
		String fileName = null;
		log.debug(getTempString().trim());
		if(StringFunctions.isNullOrEmpty(getTempString()))
			fileName = "student Details_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
		else
			fileName = "Hostel student Details_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
		ExcelView excelView = new ExcelView();
		getResponse().setContentType(excelView.getMimeType());
		getResponse().setHeader("Content-Disposition","attachment; filename="+ fileName.replace(' ', '_') + ".xls");
		excelView.setWb(Workbook.createWorkbook(getResponse().getOutputStream()));
	//	Customer customer = getCustomerByCustId();
		WritableFont font1 = new WritableFont(WritableFont.ARIAL,10, WritableFont.BOLD, true);
		font1.setColour(Colour.WHITE);
		// For School Name
		roomDetailsList = adminManager.getAll("select roomAndBedName,bedId,CAST(hostelBuildingFloorName as char(255)) AS hostelBuildingFloorName,floorId from vw_hostelRoomBedDetails where custId="+ getUserCustId()+" and floorId ="+ floorId +" and bedId is not NULL order by roomName");
		if(ObjectFunctions.isNotNullOrEmpty(roomDetailsList)) {
		 	excelView.setWorkSheetName("RoomNameAndBedName");
		 	excelView.createWorkSheet(0);
			excelView.setDefaultFormat(excelView.getArial10format());
			excelView.getWs().setColumnView(0, 100); 
			CellView cv = new CellView();
			cv.setHidden(true);
			excelView.getWs().setColumnView(0, cv);
			cv = null;
		    int columnStart = 0;
			int cellStart = 0;
			for(Object[] hotelDetails : roomDetailsList)
			{  
				excelView.getWs().addCell(new Label(cellStart,columnStart,hotelDetails[0].toString(),excelView.getWrapCellFormat()));	
				columnStart++;
			}
			excelView.getWb().addNameArea("buildingName", excelView.getWs(), 0, columnStart, 0, 0);
		}
		roomDetailsList=null;
		roomDetailsList= adminManager.getAll("select roomAndBedName,bedId,CAST(hostelBuildingFloorName as char(255)) AS hostelBuildingFloorName,floorId from vw_hostelRoomBedDetails where custId="+ getUserCustId()+" and floorId ="+ floorId+" and bedId is not NULL group by floorId");
		if(ObjectFunctions.isNotNullOrEmpty(roomDetailsList))
		{
		 	excelView.setWorkSheetName("HotelBuildingAndFloorName");
	    	excelView.createWorkSheet(0);
			excelView.setDefaultFormat(excelView.getArial10format());
			excelView.getWs().setColumnView(0, 10); 
			CellView cv = new CellView();
			cv.setHidden(true);
			excelView.getWs().setColumnView(0, cv);
			cv = null;
		   int columnStart = 0;
		   int cellStart = 0;
		   for(Object[] hotelDetails : roomDetailsList) {  
			   if(!ObjectFunctions.isNullOrEmpty(hotelDetails[0])) {
				   excelView.getWs().addCell(new Label(cellStart,columnStart,hotelDetails[2].toString(),excelView.getWrapCellFormat()));	
					columnStart++;
				}
			}
			excelView.getWb().addNameArea("hostelName", excelView.getWs(), 0, columnStart, 0, 0);
		}
		studyClassList = adminManager.getAll(StudyClass.class,"id in"+getSelectedId());
		if(ObjectFunctions.isNotNullOrEmpty(studyClassList)){
			for(StudyClass studyClass: studyClassList)
			{
				excelView.setWorkSheetName(studyClass.getClassAndSection()+" class");
		    	excelView.createWorkSheet(0);
		    	excelView.setDefaultFormat(excelView.getArial10format());
		    	
		    	hostelStudentDetailsOnlyForExcel(excelView,10);
		    	excelView.getWs().removeRow(0);
		    	excelView.getWs().setColumnView(0, 20);
				excelView.getWs().setColumnView(1, 18);
				excelView.getWs().setColumnView(2, 20);
				excelView.getWs().setColumnView(3, 18);
				excelView.getWs().setColumnView(4, 15);
				excelView.getWs().setColumnView(5, 13);
				excelView.getWs().setColumnView(6, 15);
				excelView.getWs().setColumnView(7, 52);
				excelView.getWs().setColumnView(8, 40);
				excelView.getWs().setColumnView(9, 0);
				excelView.getWs().setColumnView(10, 0);
				CellView cv = new CellView();
				cv.setHidden(true);
				excelView.getWs().setColumnView(9, cv);
				cv = null;
				excelView.getWs().addCell(new Label(0, 5, "AdmissionNumber", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(1, 5, "First Name", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(2, 5, "Last Name", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(3, 5, "Class", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(4, 5, "Section", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(5, 5, "Father Name", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(6, 5, "Street Name", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(7, 5, "Hotel - Building - Floor Name", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(8, 5, "Room Name - Bed Name", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(9, 5, "Student Id", excelView.getUsermore10BoldformatGreenBgClr()));
				excelView.getWs().addCell(new Label(10, 5, "Floor Id", excelView.getUsermore10BoldformatGreenBgClr()));
				setTodayDate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
	    		studentDetails = adminManager.getAll("select admissionNumber,firstName,lastName,className,section,fatherName,streetName,hostelBuildingFloorName,roomAndBedName,studentId,floorId  from vw_hostelStudentDetails where classSectionId="+studyClass.getId()+ " and academicYearId="+ getUserAcademicYearId() + " and hostelMode='H' and description is null ");
	    		if (!ObjectFunctions.isNullOrEmpty(studentDetails)) {
				    int columnStart = 6;
				    int cellStart = 0;
				    for (Object[] object : studentDetails) {
					    for (int l = 0; l < (object.length); l++) {
							if (!ObjectFunctions.isNullOrEmpty(object[l])){
							    if (l == 0) {
							    	excelView.getWs().addCell(new Label(cellStart,columnStart,object[l].toString(),excelView.getWrapCellFormat()));
							    } 
						    	else {
						    		if(l == 7){
						    			if(!ObjectFunctions.isNullOrEmpty(object[10])){
						    				displayDropDownlistFromNamedRangeForExcelSheet(cellStart, columnStart,"hostelName",excelView,object[l].toString());
						    			}
						    		}
								    else if (l == 8) {
								    	if(!ObjectFunctions.isNullOrEmpty(object[10])){
								    		displayDropDownlistFromNamedRangeForExcelSheet(cellStart, columnStart,"buildingName",excelView,object[l].toString());
								    	}
									}
								    else if (l == 10) {
										excelView.getWs().addCell(new Label(cellStart,columnStart,""+floorId,excelView.getWrapCellFormat()));	
									}
								    else {
									    excelView.getWs().addCell(new Label(cellStart,columnStart,object[l].toString()));
									}
							    }
							  }else{
								if(l == 7){
									if(ObjectFunctions.isNullOrEmpty(roomDetailsList)){
										 excelView.getWs().addCell(new Label(cellStart,columnStart,object[l].toString()));
									}else{
										displayDropDownlistFromNamedRangeForExcelSheet(cellStart, columnStart,"hostelName",excelView,object[l].toString());
									}
								}else if(l == 8){
									if(ObjectFunctions.isNullOrEmpty(roomDetailsList)){
										 excelView.getWs().addCell(new Label(cellStart,columnStart,object[l].toString()));
									}else{
										displayDropDownlistFromNamedRangeForExcelSheet(cellStart, columnStart,"buildingName",excelView,object[l].toString());
									}
								}else{
									excelView.getWs().addCell(new Label(cellStart, columnStart,""));
								}
							  }
							cellStart++;
					    }object=null;
					    columnStart++;
					    cellStart = 0;
				    }
				    showSchoolUrlInExcelSheetFooter(columnStart-1, excelView, 8);
				}
			}
			roomDetailsList=null;
		}
		excelView.getWb().write();
		excelView.getWb().close();
	    }
	   
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
    }
	@Actions( { @Action(value = "ajaxUploadStudentHostelData", results = { @Result(location = "ajaxDoAssignStudents.jsp", name = "success"),@Result(location = "ajaxDoAssignStudents.jsp", name = "dummyInit")}) })
    public String ajaxUploadStudentHostelData() {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxUploadStudentHostelData' method");
	}
	try {
			boolean excelFileType = false;
			excelFileType = validateExcelFileType(getUploadContentType());
			if(excelFileType){
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				  return "dummyInit";
			}
		  /*if (!(Constants.FILE_TYPE_XLS.equalsIgnoreCase(getUploadContentType()) || Constants.FILE_TYPE_APPLICATION_XLS.equalsIgnoreCase(getUploadContentType()))) {
			log.debug("No file to upload....");
			super.addActionError("File type not matched.");
			 ajaxDoAssignStudent();
			return "dummyInit";
		    }*/
		    StringBuffer failureMsg=new StringBuffer();
		    failureMsg.append("The following admission number(s) is(are) already available.");
		    if (getUserAcademicYearId() > 0) {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			Workbook workbook = Workbook.getWorkbook(getUpload(), ws);
			Sheet sheet = null;
			setCurrentUserCustId(getUserCustId());
			for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets()-2; sheetNum++) 
			{
			    sheet = workbook.getSheet(sheetNum);
			    createStudentHostel(sheet,"E");
			}
			super.addActionMessage("Students Assign to hostel successfully.");
		}
		    
	 } catch (Exception e) {
	    e.printStackTrace();
	    RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
	}
	 ajaxDoAssignStudent();
	return SUCCESS;
    }
   public void createStudentHostel(Sheet sheet,String loadType)
	{
	   if (log.isDebugEnabled()) {
		    log.debug("Entering 'createStudentHostel' method");
		}
		try
		{
			int rowSize = sheet.getRows();
			Student student = null;
			long roomId=0;
			long floorId1=0;
			Cell cell;
			String[] acountRules = new String[3];
			cell = sheet.getCell(0, 0);
			acountRules[0] = StringFunctions.trim(cell.getContents());
			cell = sheet.getCell(1, 0); 	
			acountRules[1] = StringFunctions.trim(cell.getContents());
			cell = sheet.getCell(2, 0);
			acountRules[2] = StringFunctions.trim(cell.getContents());
			 int j=0;
			 if(loadType.equals("E"))
			 {
				  j=6;
			 }
			 StringBuffer failureMsg=new StringBuffer();
			 failureMsg.append("(");
			 Object[] roomsByBed=null; 
			for (int i = j; i < rowSize; i++) 
			{
				try {
					if(loadType.equals("E") || loadType.equals(""))
					{
						if (loadType.equals("E")) {
							if (!ObjectFunctions.isNullOrEmpty(sheet.getCell(9, i).getContents())) 
							{
								HostelStudents hostelStudents = null;
								int avaibleHostelStudentId = 0;
								long studentId = Long.valueOf(sheet.getCell(9, i).getContents());
								long floorId = Long.valueOf(sheet.getCell(10, i).getContents());
								String roomName = sheet.getCell(8, i).getContents();
								if(!StringFunctions.isNullOrEmpty(roomName))
								{
									log.debug("Room Name:"+roomName.trim());
									log.debug("select roomId,floorId,bedId from vw_roomDetails where roomAndBedName='"+roomName.trim()+"'"); 
									roomsByBed = adminManager.get("select roomId,floorId,bedId from vw_roomDetails where roomAndBedName='"+roomName.trim()+"' and floorId="+ floorId);
									if(!ObjectFunctions.isNullOrEmpty(roomsByBed) && !StringFunctions.isNullOrEmpty(roomsByBed[2].toString()))
									{
										log.debug("select * from hostelStudents where bedId = "+Long.valueOf(roomsByBed[2].toString()) + " and status='"+Constants.YES_STRING+ "' and custId = " + getUserCustId() + " and academicYearId = " + getUserAcademicYearId());
										hostelStudents = (HostelStudents)adminManager.get(HostelStudents.class, "bedId = "+Long.valueOf(roomsByBed[2].toString()) + " and status='"+Constants.YES_STRING+ "' and custId = " + getUserCustId() + " and academicYearId = " + getUserAcademicYearId());
										avaibleHostelStudentId=adminManager.getCount("hostelStudents", "studentId="+studentId+" and bedId="+Long.valueOf(roomsByBed[2].toString())+" and status='"+Constants.YES_STRING+ "' and custId = " + getUserCustId() + " and academicYearId ="+getUserAcademicYearId());
										if (ObjectFunctions.isNullOrEmpty(hostelStudents)){
											hostelStudents = new HostelStudents();
											
												student = (Student) hostelManager.get(Student.class,Long.valueOf(studentId));
												if(!ObjectFunctions.isNullOrEmpty(student))
												{
													roomId= Long.valueOf(roomsByBed[0].toString());
													floorId1= Long.valueOf(roomsByBed[1].toString());
													long bedId = Long.valueOf(roomsByBed[2].toString());
													hostelStudents.setAcademicYear(getCurrentAcademicYear());
													hostelStudents.setBedId(bedId);
													hostelStudents.setStudentId(student.getId());
													hostelStudents.setAccountId(student.getAccount().getId());													
													hostelStudents.setRoomId(roomId);
													hostelStudents.setStatus(Constants.YES_STRING);
													hostelStudents.setCustId(getUserCustId());
													hostelStudents.setCreatedById(getUser().getId());
													hostelStudents.setCreatedDate(new Date());
													student.setHostelMode("H");
													adminManager.save(student);
													adminManager.save(hostelStudents);
													roomsByBed=null;
													hostelStudents=null;
													student=null;
												}
											}else{
												if(avaibleHostelStudentId==0){
													cell = sheet.getCell(0, i);
													if(StringFunctions.isNotNullOrEmpty(cell.getContents())){
														failureMsg.append(cell.getContents());
														failureMsg.append(",");
													}
											}
										  }
										}
								}
							}
						} 
					}
				} catch (Exception e) {
					e.printStackTrace();
					RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
					continue;
				}
			}
			failureMsg.deleteCharAt(failureMsg.length() - 1);
			failureMsg.append(")");
			setAnyTitle(failureMsg.toString());
			if(getAnyTitle().length() > 1){
				super.addActionError("The following admission number(s) is(are) "+getAnyTitle()+". student is already assigned to the this bed number please try with the another bed");
			}
		}
		catch (Exception e) {
            log.debug("Record not processed: " + e.getMessage());
			e.printStackTrace();
			RayGunException raygex = new RayGunException();raygex.sendRayGunException(e);raygex=null;
		}
	}
   
   @Actions( { @Action(value = "ajaxViewMessManagementHome", results = { @Result(location = "messManagement/ajaxViewMessManagementHome.jsp", name = "success") }) })
	public String ajaxViewMessManagementHome() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewMessManagementHome' method");
		}
		try {
			setHostelList(hostelManager.getAll(Hostel.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
			setObjectList(hostelManager.getAll(Mess.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   
   
   @Actions( { @Action(value = "ajaxDoAddMess", results = { @Result(location = "messManagement/ajaxAddMess.jsp", name = "success") }) })
  	public String ajaxDoAddMess() throws URTUniversalException {
  		if (log.isDebugEnabled()) {
  			log.debug("Entering 'ajaxDoAddMess' method");
  		}
  		try {
  			setMess(null);
  			setChkBoxSelectedIds(null);
  			setHostelList(hostelManager.getAll(Hostel.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
  		} catch (Exception ex) {
  			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  		}
  		return SUCCESS;
  	}
   /** @Description 27th AUG 2015  RaviTejaGoud : Made changes to update Mess details  */
   @Actions( { @Action(value = "ajaxAddMess", results = { @Result(location = "messManagement/ajaxViewMessManagementHome.jsp", name = "success") }) })
 	public String ajaxAddMess() throws URTUniversalException {
 		if (log.isDebugEnabled()) {
 			log.debug("Entering 'ajaxAddMess' method");
 		}
 		try 
 		{
 			if(!ObjectFunctions.isNullOrEmpty(getMess()) && !ObjectFunctions.isNullOrEmpty(getAnyTitle()))
 			{
 				Mess mess = null;
 				List<Hostel> hostelList = hostelManager.getAll(Hostel.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"' and id in "+ getAnyTitle() );
 				if(!ObjectFunctions.isNullOrEmpty(hostelList))
 	 			{
 					if(getTempId() > 0){
 						mess=(Mess) hostelManager.get(Mess.class,"id="+getTempId());
 						mess.setLastUpdatedDate(new Date());
 	 					mess.setLastAccessDate(new Date());
 	 					mess.setLastUpdatedById(getUser().getId());
 					}else{
 						mess = new Mess();
 						mess.setCreatedById(getUser().getId());
 	 					mess.setCreatedDate(new Date());
 					}
 					mess.setMessName(getMess().getMessName());
	 				mess.setMessDescription(getMess().getMessDescription());
	 				mess.setHostels(ConvertUtil.convertListToSet(hostelList));
	 				mess.setCustId(getUserCustId());
 					mess.setStatus(Constants.YES_STRING);
 					hostelManager.save(mess);
 					if(getTempId() > 0){
 						super.addActionMessage("You have successfully updated mess.");
 					}else{
 						super.addActionMessage("You have successfully added mess.");
 					}
 	 			}
 				hostelList = null;
 			}
 		} catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 		ajaxViewMessManagementHome();
 		return SUCCESS;
 	}
   
   
   @Actions( { @Action(value = "ajaxViewAllMerchants", results = { @Result(location = "messManagement/ajaxViewMerchantsList.jsp", name = "success") }) })
	public String ajaxViewAllMerchants() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewAllMerchants' method");
		}
		try {
			setObjectList(hostelManager.getAll(Merchant.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
  
  
  @Actions( { @Action(value = "ajaxDoAddMerchant", results = { @Result(location = "messManagement/ajaxAddMerchant.jsp", name = "success") }) })
 	public String ajaxDoAddMerchant() throws URTUniversalException {
 		if (log.isDebugEnabled()) {
 			log.debug("Entering 'ajaxDoAddMerchant' method");
 		}
 		try {
			if(getMerchant().getId() > 0){
				//setTempId(getMerchant().getMerchantAddress().getId());
				setMerchant((Merchant)adminManager.get(Merchant.class, getMerchant().getId()));
				setAddress((Address)adminManager.get(Address.class, "id="+getMerchant().getMerchantAddress().getId()));
			}else{
				setMerchant(null);
			}
 			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
 		} catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 		return SUCCESS;
 	}
  @Actions( { @Action(value = "ajaxAddMerchant", results = { @Result(location = "messManagement/ajaxViewMerchantsList.jsp", name = "success") }) })
	public String ajaxAddMerchant() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddMess' method");
		}
		try 
		{
			if(!ObjectFunctions.isNullOrEmpty(getMerchant()))
			{
				Merchant merchant = null;
				Address address = null;
				if(getMerchant().getId() > 0)
				{
					merchant =(Merchant)adminManager.get(Merchant.class, getMerchant().getId());
					merchant.setLastUpdatedDate(new Date());
					merchant.setLastAccessDate(new Date());
					merchant.setLastUpdatedById(getUser().getId());
				}else{
					merchant= new Merchant();
					merchant.setCreatedById(getUser().getId());
					merchant.setCreatedDate(new Date());
				}
				if(!ObjectFunctions.isNullOrEmpty(merchant.getMerchantAddress())){
					address=merchant.getMerchantAddress();
				}else{
					address =new Address();
				}
				address.setAddressLine1(getAddress().getAddressLine1());
				address.setCity(getAddress().getCity());
				address.setState(getAddress().getState());
				address.setPostalCode(getAddress().getPostalCode());
				merchant.setMerchantName(getMerchant().getMerchantName());
				merchant.setMobileNumber(getMerchant().getMobileNumber());
				merchant.setCustId(getUserCustId());
				merchant.setStatus(Constants.YES_STRING);
				
				merchant.setMerchantAddress(address);
				hostelManager.save(merchant);
				if(getMerchant().getId() > 0)
					super.addActionMessage("You have successfully updated merchant.");
				else
					super.addActionMessage("You have successfully added merchant.");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxViewAllMerchants();
		return SUCCESS;
	}
  
  @Actions( { @Action(value = "ajaxViewAllMessFoodTypes", results = { @Result(location = "messManagement/ajaxViewAllMessFoodTypesList.jsp", name = "success") }) })
	public String ajaxViewAllMessFoodTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewAllMessFoodTypes' method");
		}
		try {
			setObjectList(hostelManager.getAll("select m.messName,mf.foodTypeName,mf.id from messFoodType mf left join mess m on(mf.messId=m.Id) where m.custId="+ getUserCustId() + " and mf.status='"+ Constants.YES_STRING+"'" ));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
 
 @Actions( { @Action(value = "ajaxDoAddMessFoodTypes", results = { @Result(location = "messManagement/ajaxAddMessFoodTypes.jsp", name = "success") }) })
	public String ajaxDoAddMessFoodTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddMessFoodTypes' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getMessFoodType()))
			{
				if(getMessFoodType().getId() > 0)
	 			{
	 				setMessFoodType((MessFoodType)adminManager.get(MessFoodType.class, getMessFoodType().getId()));
	 			}
			}
			else 			   
				setMessFoodType(null); 			
				setMessList(hostelManager.getAll(Mess.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
			   
			   } catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
 
 @Actions( { @Action(value = "ajaxAddMessFoodTypes", results = { @Result(location = "messManagement/ajaxViewAllMessFoodTypesList.jsp", name = "success") }) })
	public String ajaxAddMessFoodTypes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddMessFoodTypes' method");
		}
		try 
		{
			MessFoodType messFoodType = null;
			if(!ObjectFunctions.isNullOrEmpty(getMessFoodType()))
			{
				if(getMessFoodType().getId() > 0)
				{
					messFoodType =(MessFoodType)adminManager.get(MessFoodType.class, getMessFoodType().getId());
					super.addActionMessage("You have successfully edited Menu Item.");
				}
				else
				{
					messFoodType = new MessFoodType();
					messFoodType.setCustId(getUserCustId());
					messFoodType.setStatus(Constants.YES_STRING);
					messFoodType.setCreatedById(getUser().getId());
					messFoodType.setCreatedDate(new Date());
					super.addActionMessage("You have successfully added Menu Item.");
				}
				messFoodType.setMessId(getMessFoodType().getMessId());
				messFoodType.setFoodTypeName(getMessFoodType().getFoodTypeName());
				
				messFoodType.setLastUpdatedDate(new Date());
				messFoodType.setLastAccessDate(new Date());
				messFoodType.setLastUpdatedById(getUser().getId());
				
				hostelManager.save(messFoodType);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxViewAllMessFoodTypes();
		return SUCCESS;
	}
 
  
  @Actions( { @Action(value = "ajaxViewAllProvisionItems", results = { @Result(location = "messManagement/ajaxViewAllProvisionItemsList.jsp", name = "success") }) })
	public String ajaxViewAllProvisionItems() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewAllProvisionItems' method");
		}
		try {
			setObjectList(hostelManager.getAll(ProvisionItems.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}


@Actions( { @Action(value = "ajaxDoAddProvisionItems", results = { @Result(location = "messManagement/ajaxAddProvisionItems.jsp", name = "success") }) })
	public String ajaxDoAddProvisionItems() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddMerchant' method");
		}
		try {
			ajaxViewAllProvisionItems();
			setObjectList(null);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
@Actions( { @Action(value = "ajaxAddProvisionItems", results = { @Result(location = "messManagement/ajaxViewAllProvisionItemsList.jsp", name = "success") }) })
	public String ajaxAddProvisionItems() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddProvisionItems' method");
		}
		try 
		{
			if(StringFunctions.isNotNullOrEmpty(getTempString())){
				JSONArray provisionItemsJSONArray=new JSONArray(getTempString());
				JSONObject provisionItemsJson=null;
				long provistionItemsClassId=0;
				String itemName = null;
				String mreasurement = null;
				ProvisionItems provisionItems= null;
				for(int i=0;i<provisionItemsJSONArray.length();i++)
				{
					provisionItemsJson=provisionItemsJSONArray.getJSONObject(i);
					if(!ObjectFunctions.isNullOrEmpty(provisionItemsJson))
					{
						itemName = (String) provisionItemsJson.get("itemName");
						mreasurement = (String) provisionItemsJson.get("measurement");
						if(StringFunctions.isNotNullOrEmpty(itemName) && StringFunctions.isNotNullOrEmpty(mreasurement)){
							if(StringFunctions.isNotNullOrEmpty((String)provisionItemsJson.get("provistionItemsClassId")) && Long.valueOf((String)provisionItemsJson.get("provistionItemsClassId")) > 0){
								provistionItemsClassId = Long.valueOf((String)provisionItemsJson.get("provistionItemsClassId"));
								provisionItems =(ProvisionItems)adminManager.get(ProvisionItems.class,"id="+provistionItemsClassId);
							}else{
								provisionItems = new ProvisionItems();
								provisionItems.setCustId(getUserCustId());
								provisionItems.setStatus(Constants.YES_STRING);
								provisionItems.setCreatedById(getUser().getId());
								provisionItems.setCreatedDate(new Date());
							}
							if(!ObjectFunctions.isNullOrEmpty(provisionItems)){
								provisionItems.setItemName(itemName);
								provisionItems.setMeasurement(mreasurement);
								provisionItems.setLastAccessDate(new Date());
								provisionItems.setLastUpdatedById(getUser().getId());
								provisionItems.setLastUpdatedDate(new Date());
								hostelManager.save(provisionItems);
								provisionItems = null;
							}
						}
					}
				}
			}
			
			super.addActionMessage("You have successfully added provision items.");
		} catch (Exception ex) {
			ex.printStackTrace();//RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxViewAllProvisionItems();
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxRemoveProvisionItems", results = { @Result(location = "messManagement/ajaxAddProvisionItems.jsp", name = "success") }) })
		public String ajaxRemoveProvisionItems() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRemoveProvisionItems' method");
			}
			try {
				if(getTempId() > 0){
					hostelManager.remove(ProvisionItems.class, getTempId());
					super.addActionMessage("Provision Items removed successfully.");
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			ajaxViewAllProvisionItems();
			return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxManageProvisionStoreHome", results = { @Result(location = "messManagement/ajaxManageProvisionStoreHome.jsp", name = "success") }) })
	public String ajaxManageProvisionStoreHome() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxManageProvisionStoreHome' method");
		}
		try {
			setHostelList(hostelManager.getAll(Hostel.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
			setMessList(hostelManager.getAll(Mess.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
			setObjectList(hostelManager.getAll(ManageItems.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
  			setTempList(hostelManager.getAll(Merchant.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));

		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   
   @Actions( { @Action(value = "ajaxDoAddProvisionItemsToStrore", results = { @Result(location = "messManagement/ajaxAddProvisionItemsToStrore.jsp", name = "success") }) })
  	public String ajaxDoAddProvisionStore() throws URTUniversalException {
  		if (log.isDebugEnabled()) {
  			log.debug("Entering 'ajaxDoAddProvisionStore' method");
  		}
  		try {
  			setTempList(hostelManager.getAll(Merchant.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
  			setTempList1(hostelManager.getAll(ProvisionItems.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
  			setTempList2(hostelManager.getAll(Mess.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
  		} catch (Exception ex) {
  			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  		}
  		return SUCCESS;
  	}
   @Actions( { @Action(value = "ajaxAddProvisionItemsToStrore", results = { @Result(location = "messManagement/ajaxManageProvisionStoreHome.jsp", name = "success") }) })
 	public String ajaxAddProvisionStore() throws URTUniversalException {
 		if (log.isDebugEnabled()) {
 			log.debug("Entering 'ajaxAddProvisionStore' method");
 		}
 		try 
 		{
			if(!StringFunctions.isNullOrEmpty(getTempString()) && !StringFunctions.isNullOrEmpty(getParamValue("merchantId")))
			{
				Merchant merchant = (Merchant)hostelManager.get(Merchant.class," id='"+ getParamValue("merchantId")+"'" );
				
				//log.debug(getTempString());
				JSONArray provisionItemsJSONArray=new JSONArray(getTempString());
				JSONObject provisionItemsJson=null;
				long provistionItemsClassId=0;
				String itemName = null;
				String quantityStr = null;
				String priceStr = null;
				String purchaseDateStr = null;
				ManageItems manageItems= null;
				Map<Long,ProvisionItems> provisionItemsMap = new HashMap<Long, ProvisionItems>();
				
				List<ProvisionItems> provisionItemsList = hostelManager.getAll(ProvisionItems.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" );
				if(!ObjectFunctions.isNullOrEmpty(provisionItemsList))
				{
					for(ProvisionItems provisionItems : provisionItemsList)
					{
						provisionItemsMap.put(provisionItems.getId(), provisionItems);
					}
				}
				
				for(int i=0;i<provisionItemsJSONArray.length();i++)
				{
					provisionItemsJson=provisionItemsJSONArray.getJSONObject(i);
					if(!ObjectFunctions.isNullOrEmpty(provisionItemsJson))
					{
						itemName = (String) provisionItemsJson.get("itemName");
						quantityStr = (String) provisionItemsJson.get("quantity");
						priceStr = (String) provisionItemsJson.get("price");
						purchaseDateStr =   (String) provisionItemsJson.get("purchaseDate");
						if(StringFunctions.isNotNullOrEmpty(itemName) && StringFunctions.isNotNullOrEmpty(quantityStr)){
							if(StringFunctions.isNotNullOrEmpty((String)provisionItemsJson.get("provistionItemsClassId")) && Long.valueOf((String)provisionItemsJson.get("provistionItemsClassId")) > 0){
								provistionItemsClassId = Long.valueOf((String)provisionItemsJson.get("provistionItemsClassId"));
								manageItems =(ManageItems)adminManager.get(ManageItems.class,"id="+provistionItemsClassId);
							}else{
								manageItems = new ManageItems();
								manageItems.setCustId(getUserCustId());
								manageItems.setStatus(Constants.YES_STRING);
								manageItems.setCreatedById(getUser().getId());
								manageItems.setCreatedDate(new Date());
							}
							if(!ObjectFunctions.isNullOrEmpty(manageItems))
							{
								if(!ObjectFunctions.isNullOrEmpty(provisionItemsMap))
								{
									if(!ObjectFunctions.isNullOrEmpty(provisionItemsMap.get(Long.valueOf(itemName))))
									{
										manageItems.setProvisionItems(provisionItemsMap.get(Long.valueOf(itemName)));
										manageItems.setMerchant(merchant);
										manageItems.setPrice(Double.valueOf(priceStr));
										manageItems.setQuantity(Double.valueOf(quantityStr));
										manageItems.setMessId(Long.valueOf(getParamValue("messId")));
										manageItems.setPurchaseDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,purchaseDateStr)));
										
										manageItems.setLastAccessDate(new Date());
										manageItems.setLastUpdatedById(getUser().getId());
										manageItems.setLastUpdatedDate(new Date());
										hostelManager.save(manageItems);
									}
									manageItems = null;
								}
							}
						}
					}
				}
				merchant = null;
			}
			
			super.addActionMessage("You have successfully added provision items to Store.");
		} catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 		ajaxManageProvisionStoreHome();
 		return SUCCESS;
 	}
   
   @Actions( { @Action(value = "ajaxDoIssueProvisonItemsToMess", results = { @Result(location = "messManagement/ajaxIssueProvisonItemsToMess.jsp", name = "success") }) })
  	public String ajaxDoIssueProvisonItemsToMess() throws URTUniversalException {
  		if (log.isDebugEnabled()) {
  			log.debug("Entering 'ajaxDoIssueProvisonItemsToMess' method");
  		}
  		try {
  			setAttendanceDate(DateFormatter.getTodayDateStr(DateFormatter.MM_DD_YYYY_PATTERN1));
  			setTempList(hostelManager.getAll(Mess.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
  			//setTempList1(hostelManager.getAll(ProvisionItems.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
  		} catch (Exception ex) {
  			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  		}
  		return SUCCESS;
  	}
   @Actions( { @Action(value = "ajaxGetProvisonItemsForIssueItems", results = { @Result(location = "messManagement/ajaxGetProvisonItemsForIssueItems.jsp", name = "success") }) })
 	public String ajaxGetProvisonItemsForIssueItems() throws URTUniversalException {
 		if (log.isDebugEnabled()) {
 			log.debug("Entering 'ajaxGetProvisonItemsForIssueItems' method");
 		}
 		try {
 			setTempList(hostelManager.getAll(MessFoodType.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'  and messId='"+ getParamValue("messId")+"'" ));
 			setTempList1(hostelManager.getAll(ProvisionItems.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
 		} catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 		return SUCCESS;
 	}
   
   
   
   @Actions( { @Action(value = "ajaxCalculateAvailableProvisionItemsToMess", results = { @Result(type = "json", name = "success", params = {
			"includeProperties", "AvailableQty" }) }) })
	public String ajaxCalculateAvailableProvisionItemsToMess() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCalculateAvailableProvisionItemsToMess' method");
		}
		try {
			Object[]  issueProvisionItemObj= null;
			if(getTempId() > 0 && getTempId1() > 0) //tempId = messId and tempId1 = itemId
			{
				log.debug("select sum(quantity),sum(usedQuantity) from  manageItems mi  LEFT JOIN issueProvisionItemsToMess ipim on (ipim.provisionItemId = mi.provisionItemsId)  where mi.custId=" + getUserCustId() + " and mi.status='"+ Constants.YES_STRING+"' and mi.provisionItemsId="+getTempId1() + " and ipim.messId="+getTempId()); 
				issueProvisionItemObj = (Object[])hostelManager.get("select sum(quantity),sum(usedQuantity) from  manageItems mi  LEFT JOIN issueProvisionItemsToMess ipim on (ipim.provisionItemId = mi.provisionItemsId)  where mi.custId=" + getUserCustId() + " and mi.status='"+ Constants.YES_STRING+"' and mi.provisionItemsId="+getTempId1() + " and ipim.messId="+getTempId());
				if(!ObjectFunctions.isNullOrEmpty(issueProvisionItemObj))
				{
					Double toatalQuantity = 0.0;
					Double toatalAvailableQty = 0.0;
					if(ObjectFunctions.isNullOrEmpty(issueProvisionItemObj[0]))
					{
						issueProvisionItemObj = (Object[])hostelManager.get("select sum(quantity),sum(usedQuantity) from  manageItems mi  LEFT JOIN issueProvisionItemsToMess ipim on (ipim.provisionItemId = mi.provisionItemsId)  where mi.custId=" + getUserCustId() + " and mi.status='"+ Constants.YES_STRING+"' and mi.provisionItemsId="+getTempId1());
					}
					if(!ObjectFunctions.isNullOrEmpty(issueProvisionItemObj[0]))
					{
						toatalQuantity = Double.valueOf(issueProvisionItemObj[0].toString());
					}
					if(!ObjectFunctions.isNullOrEmpty(issueProvisionItemObj[1]))
					{
						toatalAvailableQty = Double.valueOf(issueProvisionItemObj[1].toString());
					}
					
				    JSONObject j = new JSONObject();
				    
				    j.put("AvailableQty", ( toatalQuantity - toatalAvailableQty));
				    
				    getResponse().getOutputStream().print(j.toString());
					
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();//RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
   
   @Actions( { @Action(value = "ajaxIssueProvisonItemsToMess", results = { @Result(location = "messManagement/ajaxViewAllIssueItemsToMessList.jsp", name = "success") }) })
	public String ajaxIssueProvisonItemsToMess() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxIssueProvisonItemsToMess' method");
		}
		try 
		{
			if(!StringFunctions.isNullOrEmpty(getTempString()) && !StringFunctions.isNullOrEmpty(getParamValue("messId")))
			{
				List<MessFoodType> messFoodTypeList = hostelManager.getAll(MessFoodType.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" );
				JSONObject formData = new JSONObject(getTempString());
				JSONArray provisionItemsJSONArray=(JSONArray) formData.get("data");;
				JSONObject provisionItemsJson=null;
				long provistionItemsClassId=0;
				String itemName = null;
				String quantityStr = null;
				String priceStr = null;
				IssueProvisionItemsToMess issueProvisionItemsToMess= null;
				if(!ObjectFunctions.isNullOrEmpty(messFoodTypeList))
				{
					int q=1;
					for(int i=0;i<provisionItemsJSONArray.length();i++)
					{
						provisionItemsJson=provisionItemsJSONArray.getJSONObject(i);
						if(!ObjectFunctions.isNullOrEmpty(provisionItemsJson))
						{
							itemName = (String) provisionItemsJson.get("itemName");
							if(StringFunctions.isNotNullOrEmpty(itemName))
							{
								issueProvisionItemsToMess = new IssueProvisionItemsToMess();
								issueProvisionItemsToMess.setCustId(getUserCustId());
								issueProvisionItemsToMess.setStatus(Constants.YES_STRING);
								issueProvisionItemsToMess.setCreatedById(getUser().getId());
								issueProvisionItemsToMess.setCreatedDate(new Date());
								issueProvisionItemsToMess.setMessId(Long.valueOf(getParamValue("messId")));
								issueProvisionItemsToMess.setProvisionItemId(Long.valueOf(itemName));
								issueProvisionItemsToMess.setLastAccessDate(new Date());
								issueProvisionItemsToMess.setLastUpdatedById(getUser().getId());
								issueProvisionItemsToMess.setLastUpdatedDate(new Date());
								
								if(!ObjectFunctions.isNullOrEmpty(issueProvisionItemsToMess))
								{
									for(MessFoodType messFoodType : messFoodTypeList)
									{
										issueProvisionItemsToMess.setMessFoodTypeId(messFoodType.getId());
										if(!StringFunctions.isNullOrEmpty(getParamValue("messFoodType_"+q+"_"+messFoodType.getId())))
										{
											log.debug(getParamValue("messFoodType_"+q+"_"+messFoodType.getId()));
											issueProvisionItemsToMess.setUsedQuantity(Double.valueOf(getParamValue("messFoodType_"+q+"_"+messFoodType.getId())));
										}
										else
											issueProvisionItemsToMess.setUsedQuantity(0.0);
										
										issueProvisionItemsToMess.setIssueDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN,DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN,getAttendanceDate())));
										
										hostelManager.save(issueProvisionItemsToMess);
									}
								}
							}
						}
						q++;
					}
				}
			}
			
			super.addActionMessage("You have successfully issued items to mess.");
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxViewAllIssueItemsToMess();
		return SUCCESS;
	}
   
   @Actions( { @Action(value = "ajaxViewAllIssueItemsToMess", results = { @Result(location = "messManagement/ajaxViewAllIssueItemsToMessList.jsp", name = "success") }) })
  	public String ajaxViewAllIssueItemsToMess() throws URTUniversalException {
  		if (log.isDebugEnabled()) {
  			log.debug("Entering 'ajaxViewAllIssueItemsToMess' method");
  		}
  		try {
  			setObjectList(hostelManager.getAll(ViewIssueProvisionItemsToMess.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
  		} catch (Exception ex) {
  			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  		}
  		return SUCCESS;
  	}
   
   @Actions( { @Action(value = "ajaxViewAvailableItemsInMess", results = { @Result(location = "messManagement/ajaxViewAvailableItemsInMess.jsp", name = "success") }) })
	public String ajaxViewAvailableItemsInMess() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewAvailableItemsInMess' method");
		}
		try {
			//setTempList(hostelManager.getAll(Mess.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxGetAvailableItemsListInMess();
		return SUCCESS;
	}
   @Actions( { @Action(value = "ajaxGetAvailableItemsListInMess", results = { @Result(location = "messManagement/ajaxGetAvailableItemsListInMess.jsp", name = "success") }) })
	public String ajaxGetAvailableItemsListInMess() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetAvailableItemsListInMess' method");
		}
		try {
			setObjectList(null);
			Map<String,String> issueProvisionItemsMap = new HashMap<String, String>();
			List<Object[]> issueManageItemObjList = hostelManager.getAll("select sum(quantity),itemName,provisionItemsId from  manageItems mi LEFT JOIN provisionItems pt on (mi.provisionItemsId = pt.id) where mi.custId=" + getUserCustId() + " and mi.status='"+ Constants.YES_STRING+"' group by provisionItemsId,messId");
			
			List<Object[]> issueProvisionItemObjList = hostelManager.getAll("select provisionItemId,sum(usedQuantity) from  issueProvisionItemsToMess   where custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"' group by provisionItemId,messId");
			
			if(!ObjectFunctions.isNullOrEmpty(issueProvisionItemObjList))
			{
				for(Object[] object : issueProvisionItemObjList)
				{
					if(!ObjectFunctions.isNullOrEmpty(object))
					{
						if(!ObjectFunctions.isNullOrEmpty(object[0]))
						{
							issueProvisionItemsMap.put(object[0].toString(), object[1].toString());
						}
					}
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(issueManageItemObjList))
			{
				for(Object[] object : issueManageItemObjList)
				{
					Double toatalQuantity = 0.0;
					Double toatalAvailableQty = 0.0;
					
					ViewIssueProvisionItemsToMess viewIssueProvisionItemsToMess = new ViewIssueProvisionItemsToMess();
					if(!ObjectFunctions.isNullOrEmpty(object[0]))
					{
						toatalQuantity = Double.valueOf(object[0].toString());
					}
					if(!ObjectFunctions.isNullOrEmpty(object[2]))
					{
						if(!ObjectFunctions.isNullOrEmpty(issueProvisionItemsMap))
						{
							if(!ObjectFunctions.isNullOrEmpty(issueProvisionItemsMap.get(object[2].toString())))
							{
								toatalAvailableQty = Double.valueOf(issueProvisionItemsMap.get(object[2].toString()));
							}
						}
					}
					
					viewIssueProvisionItemsToMess.setUsedQuantity(toatalQuantity - toatalAvailableQty);
					viewIssueProvisionItemsToMess.setItemName(object[1].toString());
					getObjectList().add(viewIssueProvisionItemsToMess);
					viewIssueProvisionItemsToMess = null;
				}
			}
			issueProvisionItemObjList = null;
			issueManageItemObjList = null;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   
   @Actions( { @Action(value = "ajaxDeleteMessFoodType", results = { @Result(location = "messManagement/ajaxViewAllMessFoodTypesList.jsp", name = "success") }) })
  	public String ajaxDeleteMessFoodType() throws URTUniversalException 
     {
  		if (log.isDebugEnabled()) 
  		{
  			log.debug("Entering 'ajaxDeleteMessFoodType' method");
  		}
  		try {
  			if(getMessFoodType().getId() > 0)
  			{
  				MessFoodType messFoodType =(MessFoodType)hostelManager.get(MessFoodType.class, getMessFoodType().getId());
  				messFoodType.setStatus(Constants.NO_STRING);
  				hostelManager.save(messFoodType);
  				super.addActionMessage("You have successfully made FoodType Inactive.");
  				messFoodType = null;
  			}	
  		} catch (Exception ex) {
  			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
  		}
  		ajaxViewAllMessFoodTypes();
  		return SUCCESS;
  	}
   /** @Description 27th AUG 2015  RaviTejaGoud : Check MessName Already Available or not */  
   @Actions( { 
	    @Action(value = "ajaxCheckAddMessAvailableOrNot", results = { @Result(type = "json", name = "success", params = {"includeProperties", "autoCheck" }) }) })
		public String ajaxCheckAddMessAvailableOrNot() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckAddMessAvailableOrNot' method");
		}
		try {
		    String messName = getParamValue("keyWord");
		    if (StringFunctions.isNotNullOrEmpty(messName)) {
				List messNameList = adminManager.getAll(Mess.class, " messName='"+messName+"'");
				if (ObjectFunctions.isNullOrEmpty(messNameList)) {
					setAutoCheck("0");
				} else if (messNameList.size() > 0) {
					setAutoCheck("1");
				} else {
					setAutoCheck("0");
				}
				messNameList=null;
		    }
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /** @Description 27th AUG 2015  RaviTejaGoud : edit the mess details */  
	@Actions( { @Action(value = "ajaxDoEditMessDetails", results = { @Result(location = "messManagement/ajaxEditMessDetails.jsp", name = "success") }) })
	public String ajaxDoEditMessDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoEditMessDetails' method");
		}
		try {
			if(getTempId()>0){
				setMess((Mess)hostelManager.get(Mess.class, "id="+getTempId()));
				if(ObjectFunctions.isNotNullOrEmpty(getMess().getHostels())){
					for(Hostel objHostel:getMess().getHostels()){
						if(!ObjectFunctions.isNullOrEmpty(objHostel)){
							getChkBoxSelectedIds().add(String.valueOf(objHostel.getId()));
						}
					}
				}
				setHostelList(hostelManager.getAll(Hostel.class," custId=" + getUserCustId() + " and status='"+ Constants.YES_STRING+"'" ));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/** @Description 27th AUG 2015  RaviTejaGoud : delete the mess*/  
	@Actions( { @Action(value = "ajaxDeleteMess", results = { @Result(location = "messManagement/ajaxViewMessManagementHome.jsp", name = "success") }) })
    public String ajaxDeleteMess() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDeleteMess' method");
	}
	try {
	    if (getTempId() > 0) {
		     List<Object> messesList = adminManager.getAll("select * from messHostel where messId="+getTempId());
		     List<MessFoodType> messFoodTypeList=adminManager.getAll(MessFoodType.class, " messId="+getTempId());
		    if(ObjectFunctions.isNullOrEmpty(messesList) && ObjectFunctions.isNullOrEmpty(messFoodTypeList)) {
			    adminManager.remove("messHostel","messId=" + getTempId());
			    adminManager.remove(Hostel.class,getTempId());
			    super.addActionMessage("Mess deleted successfully.");
		    }else{
		    	if(!ObjectFunctions.isNullOrEmpty(messFoodTypeList))
		    		super.addActionError("Mess contains foodtype.You can't delete mess.");
		    	else
		    		super.addActionError("Mess contains Hostel's.You can't delete mess.");
		    }
	    }    
	    ajaxViewMessManagementHome();
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
	
	@Actions( {
		@Action(value = "ajaxGetBedDetailsByRoomId", results = { @Result(location = "ajaxGetBedDetails.jsp", name = "success") }) })
	public String ajaxGetBedDetailsByRoomId() throws URTUniversalException {
	
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxGetBedDetailsByRoomId' method");
		}
		try {
			List<BigInteger> bedIds = adminManager.getAll("select bedId from hostelStudents where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and roomId="+getRoomId());
			
			String bedIdString=null;
			if (ObjectFunctions.isNotNullOrEmpty(bedIds)) 
				bedIdString = StringFunctions.convertListToCommaDelimitedString(bedIds);
			else
				bedIdString="0";
			setBedList(adminManager.getAll(Bed.class, "custId="+ getUserCustId() + " and academicYearId="+ getUserAcademicYearId() + " and roomId="+getRoomId()+" and id not in("+bedIdString+") order by bedName" ));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
	}
	
	public void checkHostelStudyClassHavingStudentsOrNot() 
	{
		log.debug("User Id:" + getUser().getId());
		List<StudyClass> studyClassList = adminManager.GetAllStudyClasses(getUserCustId(),getUserAcademicYearId(),null);
		if (ObjectFunctions.isNotNullOrEmpty(studyClassList)) 
		{
			 int studentsCount=0;
			for (StudyClass studyClass : studyClassList) 
			{
				studentsCount=adminManager.getCount("student", "classSectionId="+studyClass.getId()+" and hostelMode='H' and custId="+getUserCustId()+" and description is null");
				// studentsCount = studentManager.getClassStudentsCountByClassIdandStatus(studyClass.getId(),"H",getUserCustId());
				 if(studentsCount > 0 )
				  getStudyClassList().add(studyClass);
			     else
				  getTempList2().add(studyClass);
			}
		}
	}
	/** @Description 20th JULY 2016  RaviTejaGoud : delete the mess*/  
	@Actions( { @Action(value = "ajaxDeleteMerchant", results = { @Result(location = "messManagement/ajaxViewMerchantsList.jsp", name = "success") }) })
    public String ajaxDeleteMerchant() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDeleteMerchant' method");
	}
	try {
	    if (getTempId() > 0) {
	    	List<ManageItems> mabageItemsList=adminManager.getAll(ManageItems.class, " merchantId="+getTempId());
	    	if(ObjectFunctions.isNullOrEmpty(mabageItemsList)){
	    		adminManager.remove("merchant", "id="+getTempId()+" and custId="+getUserCustId());
				super.addActionMessage("Merchant deleted successfully.");
	    	}else{
	    		super.addActionError("You can't delete the merchant as it is associated with provision items");
	    	}
	    }    
	    ajaxViewAllMerchants();
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
	}
	return SUCCESS;
    }
	
}