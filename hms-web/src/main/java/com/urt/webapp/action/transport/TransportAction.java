package com.urt.webapp.action.transport;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;

import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableFont;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.xls.ExcelView;
import com.hyniva.common.cache.SMSLookUpDataCache;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.State;
import com.urt.persistence.model.common.TcSettings;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.fee.SchoolFeeSetting;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudyClass;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentClassDetails;
import com.urt.persistence.model.study.ViewStudentFeePaymentDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.study.ViewTransportRequestFormDetails;
import com.urt.persistence.model.transport.Route;
import com.urt.persistence.model.transport.RouteBoardingPoints;
import com.urt.persistence.model.transport.TransportMaintenance;
import com.urt.persistence.model.transport.Vehicles;
import com.urt.persistence.model.transport.VehiclesAcademicDetails;
import com.urt.persistence.model.transport.ViewAssignedVehiclestoRoutesWithBoardingPoints;
import com.urt.persistence.model.transport.ViewStudentsTransportDetails;
import com.urt.persistence.model.transport.ViewVehicleAndDriverInfo;
import com.urt.persistence.model.transport.ViewVehicleWithDriverDetails;
import com.urt.persistence.model.user.Person;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.transport.TransportManager;
import com.urt.util.common.PasswordUtils;
import com.urt.util.common.RayGunException;
import com.urt.webapp.action.base.BaseAction;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.NullImageBehaviour;




/**
 * Action for facilitating Resources Management feature.
 */
@Namespace("/admin")


@Actions( {
		@Action(value = "adminGetTransportSchoolFee", results = { @Result(location = "transportFee/ajaxGetSchoolFeeHome.jsp", name = "success") }),
		@Action(value = "adminGetBackAllSchoolFee", results = { @Result(location = "transportFee/ajaxGetSchoolHome.jsp", name = "success") }),
		@Action(value = "ajaxAdminGetFourteenTransportSchoolFee", results = { @Result(location = "transportFee/ajaxGetFourteenSchoolFee.jsp", name = "success") }),
		@Action(value = "ajaxAdminGetFifteenSchoolFee", results = { @Result(location = "transportFee/ajaxGetAboveFifteenSchoolFee.jsp", name = "success") }),
		@Action(value = "ajaxAdminGetThirtySchoolFee", results = { @Result(location = "transportFee/ajaxGetThirtySchoolFee.jsp", name = "success") }),
		@Action(value = "ajaxAdminSixtySchoolFee", results = { @Result(location = "transportFee/ajaxGetSixtySchoolFee.jsp", name = "success") }),
		@Action(value = "ajaxAdminUpcomingSchoolFee", results = { @Result(location = "transportFee/ajaxGetUpcomingSchoolFee.jsp", name = "success") }) ,
		@Action(value = "ajaxGetStudentBusDetails", results = {@Result(location = "transport/ajaxGetStudentBusDetails.jsp", name = "success") })  
	})
public class TransportAction extends BaseAction {
	
	private static final long serialVersionUID = -1646390427462403153L;

	protected TransportManager transportManager;
	protected Person person;
	protected Staff staff;
	protected Customer customer;
	protected User regUser;
	private List<Vehicles> vehicleList;
	private Vehicles vehicle;
	private List<Route> routeList;
	private Route route;
	public List<Vehicles> vehicleDetailsList;
	protected String vehicleId;
	private List<Person> personList;
	private List<ViewStaffPersonAccountDetails> viewStaffAccountDetails;
	private List<Staff> staffList;
	private String roleNumber;
	protected List studentsList;
	protected List classList; 
	private List<ViewStaffPersonAccountDetails> driverList;
	private List<ViewStaffPersonAccountDetails> helperList;
	private String stops[]=new String[6];
	private String ampm[]=new String[6];
	private String hours[]=new String[6];
	private String minutes[]=new String[6];
	private String driverId;
	private String helperId;
	
	private String boardingPoint;
	private String selectedId;
	private String selectedVehicleType;
	private String selectedRoleType;
	protected List<ClassName> transportFeeActiveClassList;
    protected List<ClassName> transportFeeInactiveClassList;
    private List<RouteBoardingPoints> routeBoardingPointsList;
	private RouteBoardingPoints routeBoardingPoints;
	protected ClassName classNames;
	protected String studentNumber;
	protected boolean tempBoolean;
	//protected Fee transportFee;
	private String anyVehicleType;
	protected Map<String ,String> routeNamesList;
	private TransportMaintenance  transportMaintenance;
	private List<TransportMaintenance>  transportMaintenanceList;
	private VehiclesAcademicDetails vehicleAcademicDetails;
	private String studentTransportData;
	
	
	public String getStudentTransportData() {
		return studentTransportData;
	}
	public void setStudentTransportData(String studentTransportData) {
		this.studentTransportData = studentTransportData;
	}
	/**
	 * @return the vehicleAcademicDetails
	 */
	public VehiclesAcademicDetails getVehicleAcademicDetails() {
		return vehicleAcademicDetails;
	}
	/**
	 * @param vehicleAcademicDetails the vehicleAcademicDetails to set
	 */
	public void setVehicleAcademicDetails(
			VehiclesAcademicDetails vehicleAcademicDetails) {
		this.vehicleAcademicDetails = vehicleAcademicDetails;
	}
	public List<TransportMaintenance> getTransportMaintenanceList() {
		return transportMaintenanceList;
	}
	public void setTransportMaintenanceList(
			List<TransportMaintenance> transportMaintenanceList) {
		this.transportMaintenanceList = transportMaintenanceList;
	}
	public TransportMaintenance getTransportMaintenance() {
		return transportMaintenance;
	}
	public void setTransportMaintenance(TransportMaintenance transportMaintenance) {
		this.transportMaintenance = transportMaintenance;
	}
	
	
	public Map<String, String> getRouteNamesList() {
		if(ObjectFunctions.isNullOrEmpty(this.routeNamesList)){
			this.routeNamesList=new LinkedHashMap<String,String>();
		}
		return routeNamesList;
	}
	public void setRouteNamesList(Map<String, String> routeNamesList) {
		this.routeNamesList = routeNamesList;
	}
	public String getAnyVehicleType() {
		return anyVehicleType;
	}
	public void setAnyVehicleType(String anyVehicleType) {
		this.anyVehicleType = anyVehicleType;
	}
	public String getAutoCheck() {
		return super.autoCheck;
		}
	public String getStudentNumber() {
		return studentNumber;
	}

	public boolean isTempBoolean() {
		return tempBoolean;
	}
	public void setTempBoolean(boolean tempBoolean) {
		this.tempBoolean = tempBoolean;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	/**
	 * @return the routeBoardingPointsList
	 */
	public List<RouteBoardingPoints> getRouteBoardingPointsList() {
		return routeBoardingPointsList;
	}

	/**
	 * @param routeBoardingPointsList the routeBoardingPointsList to set
	 */
	public void setRouteBoardingPointsList(
			List<RouteBoardingPoints> routeBoardingPointsList) {
		this.routeBoardingPointsList = routeBoardingPointsList;
	}

	/**
	 * @return the routeBoardingPoints
	 */
	public RouteBoardingPoints getRouteBoardingPoints() {
		return routeBoardingPoints;
	}

	/**
	 * @param routeBoardingPoints the routeBoardingPoints to set
	 */
	public void setRouteBoardingPoints(RouteBoardingPoints routeBoardingPoints) {
		this.routeBoardingPoints = routeBoardingPoints;
	}
		
	public List<ClassName> getTransportFeeActiveClassList() {
		if (ObjectFunctions.isNullOrEmpty(this.transportFeeActiveClassList)) {
			this.transportFeeActiveClassList = new ArrayList<ClassName>();
		}
		return transportFeeActiveClassList;
	}

	public void setTransportFeeActiveClassList(
			List<ClassName> transportFeeActiveClassList) {
		this.transportFeeActiveClassList = transportFeeActiveClassList;
	}

	public List<ClassName> getTransportFeeInactiveClassList() {
		if (ObjectFunctions.isNullOrEmpty(this.transportFeeInactiveClassList)) {
			this.transportFeeInactiveClassList = new ArrayList<ClassName>();
		}
		return transportFeeInactiveClassList;
	}

	public void setTransportFeeInactiveClassList(
			List<ClassName> transportFeeInactiveClassList) {
		this.transportFeeInactiveClassList = transportFeeInactiveClassList;
	}
	private List<Fee> transportFeeList;
	

	public List<Fee> getTransportFeeList() {
		return transportFeeList;
	}

	public void setTransportFeeList(List<Fee> transportFeeList) {
		this.transportFeeList = transportFeeList;
	}

	

	public ClassName getClassNames() {
		return classNames;
	}

	public void setClassNames(ClassName classNames) {
		this.classNames = classNames;
	}


	public String getSelectedRoleType() {
		return selectedRoleType;
	}

	public void setSelectedRoleType(String selectedRoleType) {
		this.selectedRoleType = selectedRoleType;
	}
	public String getSelectedVehicleType() {
		return selectedVehicleType;
	}

	public void setSelectedVehicleType(String selectedVehicleType) {
		this.selectedVehicleType = selectedVehicleType;
	}

	public String getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}
	public String getHelperId() {
		return helperId;
	}

	public void setHelperId(String helperId) {
		this.helperId = helperId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String[] getStops() {
		return stops;
	}

	public void setStops(String[] stops) {
		this.stops = stops;
	}

	public String[] getAmpm() {
		return ampm;
	}

	public void setAmpm(String[] ampm) {
		this.ampm = ampm;
	}

	public String[] getHours() {
		return hours;
	}

	public void setHours(String[] hours) {
		this.hours = hours;
	}

	public String[] getMinutes() {
		return minutes;
	}

	public void setMinutes(String[] minutes) {
		this.minutes = minutes;
	}

	public String getBoardingPoint() {
		return boardingPoint;
	}

	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}

	public List<Staff> getStaffList() {
		if(ObjectFunctions.isNullOrEmpty(this.staffList))
		{
			this.staffList = new ArrayList<Staff>(); 
		}
		return this.staffList;
	}

	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}
	
	public List<ViewStaffPersonAccountDetails> getViewStaffAccountDetails() {
		if(ObjectFunctions.isNullOrEmpty(this.viewStaffAccountDetails))
		{
			this.viewStaffAccountDetails = new ArrayList<ViewStaffPersonAccountDetails>(); 
		}
		return this.viewStaffAccountDetails;
	}

	public void setViewStaffAccountDetails(
			List<ViewStaffPersonAccountDetails> viewStaffAccountDetails) {
		this.viewStaffAccountDetails = viewStaffAccountDetails;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}


	public List<Vehicles> getVehicleList() {
		if(ObjectFunctions.isNullOrEmpty(this.vehicleList))
		{
			this.vehicleList = new ArrayList<Vehicles>(); 
		}
		return this.vehicleList;
	}

	public void setVehicleList(List<Vehicles> vehicleList) {
		this.vehicleList = vehicleList;
	}
 
	public List<ViewStaffPersonAccountDetails> getDriverList() {
		if(ObjectFunctions.isNullOrEmpty(this.driverList))
		{
			this.driverList = new ArrayList<ViewStaffPersonAccountDetails>(); 
		}
		return this.driverList;
	}

	public void setDriverList(List<ViewStaffPersonAccountDetails> driverList) {
		this.driverList = driverList;
	}

	public List<ViewStaffPersonAccountDetails> getHelperList() {
		if(ObjectFunctions.isNullOrEmpty(this.helperList))
		{
			this.helperList = new ArrayList<ViewStaffPersonAccountDetails>(); 
		}
		return this.helperList;
	}

	public void setHelperList(List<ViewStaffPersonAccountDetails> helperList) {
		this.helperList = helperList;
	} 

	public Vehicles getVehicle() {
		if(ObjectFunctions.isNullOrEmpty(this.vehicle))
		{
			this.vehicle = new Vehicles(); 
		}
		return this.vehicle;
	}

	public void setVehicle(Vehicles vehicle) {
		this.vehicle = vehicle;
	}

	public TransportManager getTransportManager() {
		return transportManager;
	}

	public void setTransportManager(TransportManager transportManager) {
		this.transportManager = transportManager;
	}

	
	
	public List<Vehicles> getVehicleDetailsList() {
		return vehicleDetailsList;
	}

	public void setVehicleDetailsList(List<Vehicles> vehicleDetailsList) {
		this.vehicleDetailsList = vehicleDetailsList;
	}

	public List<Route> getRouteList() {
		if(ObjectFunctions.isNullOrEmpty(this.routeList))
		{
			this.routeList = new ArrayList<Route>(); 
		}
		return this.routeList;
	}

	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}

	public Route getRoute() {
		if(ObjectFunctions.isNullOrEmpty(this.route))
		{
			this.route = new Route(); 
		}
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public User getRegUser() {
		return regUser;
	}


	public void setRegUser(User regUser) {
		this.regUser = regUser;
	}
	
	public Person getPerson() {
		if(ObjectFunctions.isNullOrEmpty(this.person))
		{
			this.person = new Person();
		}
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<Person> getPersonList() {
		if(ObjectFunctions.isNullOrEmpty(this.personList))
		{
			this.personList = new ArrayList<Person>(); 
		}
		return this.personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	public String getRoleNumber() {
		return roleNumber;
	}

	public void setRoleNumber(String roleNumber) {
		this.roleNumber = roleNumber;
	}

	public List getStudentsList() {
		return studentsList;
	}
	public void setStudentsList(List studentsList) {
		this.studentsList = studentsList;
	}
	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 17, 2013		 Seshu				Adding academicYear wise data.
/********************************************************************/
	@Action(value = "transportDashboard", results = { @Result(location = "transport/ajaxTransportHome.jsp", name = "success") }) 
	public String transportDashboard() throws URTUniversalException {
		try
		{
			manageRoutes();
			if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("showTaskReminder")))
				getTaskReminderToUserLogin();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 17, 2013		 Seshu				Adding academicYear wise data.
 * Jan 25, 2014		 Seshu				Display transport assigned students count.
/********************************************************************/
	@Action(value = "ajaxManageRoutes", results = { @Result(location = "transport/ajaxTransportRoute.jsp", name = "success") }) 
	public String manageRoutes() throws URTUniversalException {
	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxManageroutes' method");
		}
		try {
			ajaxLoadRouteDetails();
			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
	}
	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 17, 2013		 Seshu				Adding academicYear wise data.
/********************************************************************/
	@Actions({
		@Action(value = "ajaxDoAddRoutes", results = { @Result(location = "transport/ajaxAddRoute.jsp", name = "success") }) })
		public String ajaxDoAddRoutes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddRoutes' method");
		}
		try
		{
			setTempBoolean(getCurrentAcademicYear().isTransportFeeByBoardingPoint());
			if(getTempId()>0){
				setRoute((Route)adminManager.get(Route.class,getTempId()));
				
				Object[] routeVehicle = transportManager.get("select routeId,vehicleAcademicId from RouteWithVehicles WHERE routeId="+getRoute().getId());
				
				if(!ObjectFunctions.isNullOrEmpty(routeVehicle))
				{
					//ViewVehicleWithDriverDetails viewVehicleWithDriverDetails =(ViewVehicleWithDriverDetails)transportManager.get(ViewVehicleWithDriverDetails.class," vehicleAcademicId="+routeVehicle[1].toString());
					if(!ObjectFunctions.isNullOrEmpty(routeVehicle[1]))
					setEventId(routeVehicle[1].toString());
				}
				
				if(getCurrentAcademicYear().isTransportFeeByBoardingPoint())
				{
					if(!ObjectFunctions.isNullOrEmpty(getRoute().getRouteBoardingPointList()))
					{
						List<RouteBoardingPoints> routeBoardingPointList = new ArrayList<RouteBoardingPoints>();
						
						for(RouteBoardingPoints routeBoardingPoints : getRoute().getRouteBoardingPointList())
						{
							
							StringBuffer query = new StringBuffer("FROM StudentFeePaidDetails spd WHERE spd.fee.routeBoardingPointId=").append(routeBoardingPoints.getId());
									//vehicleAcademicDetailList = adminManager.getAllHqlQuery(query.toString());
							StringBuffer query1 = new StringBuffer("FROM StudentFeePaidDetails spd where spd.fee.routeBoardingPointId=").append(routeBoardingPoints.getId());
							List studentsList = adminManager.getAllHqlQuery(query.toString());
							if(!ObjectFunctions.isNullOrEmpty(studentsList))
							{
								routeBoardingPoints.setStudentsPaidFee("Y");
							}
							else
								routeBoardingPoints.setStudentsPaidFee("N");
							routeBoardingPointList.add(routeBoardingPoints);
						}
						
						getRoute().setRouteBoardingPointList(routeBoardingPointList);
					}
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(getCustomerByCustId()))
			{
				setCustomer(getCustomerByCustId());	
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		
		return SUCCESS;
	}
	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * April 1,2013		 Seshu				Commented unnecessary code.
 * Dec 17, 2013		 Seshu				Adding academicYear wise data.
/********************************************************************/
	@Actions({
		@Action(value = "ajaxAddRoute", results = { @Result(location = "transport/ajaxRouteDetails.jsp", name = "success"),@Result(location = "transport/ajaxAddRoute.jsp", name = "SUCCESSADDROUTE") }) })
	public String ajaxAddRoute() throws URTUniversalException {
		try
		{  
			long routeId=getTempId();
			String boardingPointName =null;
			String boardingIds=null;
			String startTime =null;
			String endTime =null;
			String routeEndTime = null;
			String boardingPointId =null;
			String boardingPointFeeAmount=null;
			JSONObject boardingPointsJson=null;
			RouteBoardingPoints routeBoardingPoints =null;
			JSONArray boardingPointsJsonArray=null;
			Route route=null;
			String addressLine1 =null;
			Customer customer = getCustomerByCustId();
			if(!ObjectFunctions.isNullOrEmpty(customer))
			{
				if(ObjectFunctions.isNullOrEmpty(customer.getAddress())){
					addressLine1 = "";
				}else{
					addressLine1 = customer.getAddress().getAddressLine1();
				}
			}
			if(routeId > 0){
				boardingIds=getBoardingPoint();
				if(StringFunctions.isNotNullOrEmpty(boardingIds)){
					if(!"(0)".equalsIgnoreCase(boardingIds)){
						transportManager.removeBoardingPointsByBoardingIds(boardingIds,routeId);
					}
				}
				route=(Route)transportManager.get(Route.class, routeId);
			}
			else{
				route=new Route();
			}
			if(!ObjectFunctions.isNullOrEmpty(route) && StringFunctions.isNotNullOrEmpty(getTempString())){
				boardingPointsJsonArray=new JSONArray(getTempString());
				route.setRouteName(getRoute().getRouteName());
				route.setRouteEndName(addressLine1);
				routeEndTime = getRoute().getRoutePointEndTime();
				long hours = 0;
				long minutes =0;
				String minuteParse = "";
				long totalStartTimeinMns = 0; 
				 if(routeEndTime.toUpperCase().contains("AM"))
				 {
					  hours = Long.valueOf(routeEndTime.split(":")[0]);
    		    	  minuteParse = routeEndTime.split(":")[1];
    		    	  minutes = Long.valueOf(minuteParse.toUpperCase().split("AM")[0].trim());	         		    	 
				 }
				 else
				 {
				  hours = Long.valueOf(routeEndTime.split(":")[0]);
				  hours = hours+12;
   		    	  minuteParse = routeEndTime.split(":")[1];
   		    	  minutes = Long.valueOf(minuteParse.toUpperCase().split("PM")[0].trim());	   
				 }
				long totalEndTimeinMns = (hours*60 )+ minutes;
				hours = 0;
				minutes = 0;
				minuteParse = "";
		    	route.setRouteEndTimeinMns(totalEndTimeinMns);
				route.setRoutePointEndTime(routeEndTime);
				route.setRoutePointReturnStartTime(getRoute().getRoutePointReturnStartTime());
				route.setCustId(getUserCustId());
				route.setAcademicYearId(getUserAcademicYearId());
				route.setStatus(true);//Here status is refers route is active(live)
				int boardingPointOrder=1;
				for(int i=0;i<boardingPointsJsonArray.length();i++)
				{
					boardingPointsJson=boardingPointsJsonArray.getJSONObject(i);
					if(!ObjectFunctions.isNullOrEmpty(boardingPointsJson))
					{
						if (boardingPointsJson.get("boardingPointName").equals(addressLine1)) {
							boardingPointName = (String) boardingPointsJson.get("boardingPointName");
							super.addActionError("Route can not created ,Because of you have entered arrival and departure points are same.");
							ajaxDoAddRoutes();
							return "SUCCESSADDROUTE";
						} else {
							boardingPointName = (String) boardingPointsJson.get("boardingPointName");
						}
						startTime =(String) boardingPointsJson.get("startTime");
						endTime =(String) boardingPointsJson.get("endTime");
					    boardingPointId =(String) boardingPointsJson.get("boardingPointId");
						boardingPointFeeAmount =(String) boardingPointsJson.get("boardingPointFeeAmount");
						if(StringFunctions.isNotNullOrEmpty(boardingPointId)){
							routeBoardingPoints=(RouteBoardingPoints)adminManager.get(RouteBoardingPoints.class, Long.valueOf(boardingPointId));
							routeBoardingPoints.setBoardingPointOrder(boardingPointOrder++);
						}else{
							routeBoardingPoints=new RouteBoardingPoints();
							routeBoardingPoints.setBoardingPointOrder(boardingPointOrder++);
						}
						if(!ObjectFunctions.isNullOrEmpty(routeBoardingPoints)){
							if(i == 0){
								route.setRoutePointStartTime(startTime);
			    				route.setRoutePointReturnEndTime(endTime);
			    				route.setRoutePointName(boardingPointName);
			    				if(StringFunctions.isNotNullOrEmpty(boardingPointFeeAmount))
			    					route.setRoutePointFeeAmount(Double.valueOf(boardingPointFeeAmount) );
							}
							routeBoardingPoints.setBoardingPointName(boardingPointName);
							routeBoardingPoints.setBoardingPointStatTime(startTime);
							routeBoardingPoints.setBoardingPointEndTime(endTime);
							routeBoardingPoints.setCustId(getUserCustId());
							routeBoardingPoints.setAcademicYearId(getUserAcademicYearId());
							routeBoardingPoints.setStatus(true);//Here status is refers routeBoadring point is active(live)
							if(StringFunctions.isNotNullOrEmpty(boardingPointFeeAmount))
								routeBoardingPoints.setBoardingPointFeeAmount(Double.valueOf(boardingPointFeeAmount));
							route.addRouteToBoardingPoints(routeBoardingPoints);
						}
						if(i == 0)
						{
							 if(startTime.toUpperCase().contains("AM"))
			   				 {
			   					  hours = Long.valueOf(startTime.split(":")[0]);
			       		    	  minuteParse = startTime.split(":")[1];
			       		    	  minutes = Long.valueOf(minuteParse.toUpperCase().split("AM")[0].trim());	         		    	 
			   				 }
			   				 else
			   				 {
				   				  hours = Long.valueOf(startTime.split(":")[0].trim());
				   				  hours = hours+12;
				      		      minuteParse = startTime.split(":")[1];
				      		      minutes = Long.valueOf(minuteParse.toUpperCase().split("PM")[2].trim());	   
			   				 }
							totalStartTimeinMns = hours*60 + minutes;
							route.setRouteStartTimeinMns(totalStartTimeinMns);
						}
					}
				}
				//log.debug("Before Saving the route");
				Route routeObj = (Route) transportManager.merge(route);
				//Route routeObj = (Route)transportManager.saveOrUpdateObject(route);
				//log.debug("After Saving the route:"+routeObj.getId());
				AcademicYear academicYear=(AcademicYear)adminManager.get(AcademicYear.class, getUserAcademicYearId());
				if (academicYear.isTransportFeeByBoardingPoint() && !ObjectFunctions.isNullOrEmpty(routeObj)) {
					SchoolFeeSetting schoolFeeSetting = (SchoolFeeSetting) transportManager.get(SchoolFeeSetting.class, " status='"+ Constants.TRANSPORT_STATUS + "'");
					if (!ObjectFunctions.isNullOrEmpty(schoolFeeSetting)) {
						List<SchoolTerms> schoolTermsList = transportManager.getAll(SchoolTerms.class, " custId="+ getUserCustId()+ " and academicYearId="+ getUserAcademicYearId()+ " and feeSettingId="+ schoolFeeSetting.getId());
						if (!ObjectFunctions.isNullOrEmpty(schoolTermsList)) {
							for (SchoolTerms schoolTerms : schoolTermsList) {
								for(RouteBoardingPoints boardingPoints : routeObj.getRouteBoardingPointList()){
									/* Here we will check boarding point wise fees. If any one paid the fee in term wise we will not allow to update the fees respective boarding point*/
									List<ViewStudentFeePaymentDetails> studentPaidList=adminManager.getAll(ViewStudentFeePaymentDetails.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and schoolTermId="+schoolTerms.getId()+" and routeBoardingPointId="+boardingPoints.getId()+" and paymentStatus='"+Constants.PAYMENT_STATUS+"'");
									if(ObjectFunctions.isNullOrEmpty(studentPaidList))
										ajaxCreateSchoolTermBoardingPointWiseFee(schoolTerms, boardingPoints,schoolFeeSetting,academicYear,customer);
								}
							}
						}
					}
				}
				
				if(!ObjectFunctions.isNullOrEmpty(getEventId()))
				{
					//log.debug("Before Saving the VehiclesAcademicDetails:"+getEventId());
					VehiclesAcademicDetails vehicle = (VehiclesAcademicDetails) adminManager.get(VehiclesAcademicDetails.class,Long.valueOf(getEventId()));
					if(!ObjectFunctions.isNullOrEmpty(vehicle))
					{
						transportManager.remove("RouteWithVehicles", "routeId="+routeObj.getId());
						//log.debug("If Saving the VehiclesAcademicDetails");
						if(!ObjectFunctions.isNullOrEmpty(getVehicleAcademicDetails()))
						{
							if(!ObjectFunctions.isNullOrEmpty(getVehicleAcademicDetails().getDriverId()))
								vehicle.setDriverId(getVehicleAcademicDetails().getDriverId());
							if(!ObjectFunctions.isNullOrEmpty(getVehicleAcademicDetails().getHelperId()))
								vehicle.setHelperId(getVehicleAcademicDetails().getHelperId());
						}
						vehicle.addRoute(routeObj);
						//log.debug("Befor Saving the vehicle");
						adminManager.merge(vehicle);
						//adminManager.saveOrUpdateObject(vehicle);
						//log.debug("After Saving the vehicle");
					}
				}
				routeObj=null;
				route=null;
			}
			//setRouteList(adminManager.getAll(Route.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and status='"+Constants.YES_STRING+"'"));//Here we have displayed only active routes with custId nad academicYearId
			//setRouteList(transportManager.getRouteDetailsByCustId(getUserCustId(),getUserAcademicYearId()));
			if(routeId > 0){
				super.addActionMessage("Route and boarding points(s) updated successfully.");
			}else{
				super.addActionMessage("Route and boarding point(s) created successfully.");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		ajaxLoadRouteDetails();
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 17, 2013		 Seshu				Adding academicYear wise data.
/********************************************************************/
	@Actions({
		@Action(value = "ajaxDoAddVehicles", results = { @Result(location = "transport/ajaxAddVehicle.jsp", name = "success") }) })
		public String ajaxDoAddVehicles() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddVehicles' method");
		}
		try
		{
			setRouteList(null);
			setVehicle(null);
			/*StringBuffer query = new StringBuffer("select group_concat(CONVERT(driverId,CHAR)),group_concat(CONVERT(helperId,CHAR)) from vehiclesAcademicDetails where academicYearId=").append(getUserAcademicYearId());
			Object[] vehicleIds = transportManager.get(query.toString());
			query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and status='Y'");
			if(!ObjectFunctions.isNullOrEmpty(vehicleIds) && !ObjectFunctions.isNullOrEmpty(vehicleIds[0])) {
				query.append(" and accountId not in (").append(vehicleIds[0].toString()).append(")");
			}
			
			List<ViewStaffPersonAccountDetails> driverList =adminManager.getAll(ViewStaffPersonAccountDetails.class, query.toString()+" and roleName='ROLE_DRIVER' order by roleName");
			if(ObjectFunctions.isNotNullOrEmpty(driverList)){
				for (ViewStaffPersonAccountDetails drivers  : driverList )
				{	
					getSelectboxMap().put(drivers.getAccountId(), drivers.getFullName());
					drivers=null;
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(vehicleIds) && !ObjectFunctions.isNullOrEmpty(vehicleIds[1])) {
				query.append(" and accountId not in (").append(vehicleIds[1].toString()).append(")");
			}
			List<ViewStaffPersonAccountDetails> helperList =adminManager.getAll(ViewStaffPersonAccountDetails.class, query.toString()+" and roleName='ROLE_HELPER' order by roleName");
			if(ObjectFunctions.isNotNullOrEmpty(helperList)){
				for (ViewStaffPersonAccountDetails helper  : helperList )
				{	
					getJsonResult().put(helper.getAccountId(), helper.getFullName());
					helper=null;
				}
			}*/
			//setHelperList(adminManager.getAll(ViewStaffPersonAccountDetails.class, query.toString()+" and roleName='ROLE_HELPER' order by roleName"));
			AcademicYear academicYear = getCurrentAcademicYear();
			if(!ObjectFunctions.isNullOrEmpty(academicYear))
			{
			   	setAnyTitle(academicYear.getPastYear()+":"+(Long.valueOf(academicYear.getPastYear())+45));
			}
			
			List<Object[]>  routeLt = adminManager.getAll("select id,routeName from route WHERE custId="+getUserCustId()+" and  academicYearId="+getUserAcademicYearId()+" and  status='"+Constants.YES_STRING+"' and id not in (select distinct(routeId) from RouteWithVehicles)" );
			if(ObjectFunctions.isNotNullOrEmpty(routeLt)){
				for(Object[] routeObj:routeLt){
					if(!ObjectFunctions.isNullOrEmpty(routeObj) && !ObjectFunctions.isNullOrEmpty(routeObj[0]) && !ObjectFunctions.isNullOrEmpty(routeObj[1])){
						Route route = new Route();
						route.setId(Long.valueOf(routeObj[0].toString())); 
						route.setRouteName(routeObj[1].toString()); 
						getRouteList().add(route);
						route = null;
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 17, 2013		 Seshu				Adding academicYear wise data.
/********************************************************************/
	@Actions({
		@Action(value = "ajaxAddVehicle", results = {@Result(location = "transport/ajaxViewTransportVehicleDetails.jsp", name = "success")}) })
	public String ajaxAddVehicle() throws URTUniversalException {
		try
		{  
			if(!ObjectFunctions.isNullOrEmpty(getVehicle())){
				Route route = null;
				List vehicleList = transportManager.getVehiclesByTypeAndVehicleNumber(getVehicle().getVehicleType(),getVehicle().getVehicleNumber());
				if(ObjectFunctions.isNullOrEmpty(vehicleList)){
					if(ObjectFunctions.isNullOrEmpty(getVehicle().getRoadTaxAmount()))
					{
						getVehicle().setRoadTaxAmount("0");
					}
					getVehicle().setCustId(getUserCustId());
					VehiclesAcademicDetails  vehicleAcademicDetails = new VehiclesAcademicDetails();
					vehicleAcademicDetails.setAcademicYearId(getUserAcademicYearId());
					vehicleAcademicDetails.setCreatedById(getUser().getId());
					vehicleAcademicDetails.setLastUpdatedById(getUser().getId());
					vehicleAcademicDetails.setName(getVehicleAcademicDetails().getName());
					
					if(!ObjectFunctions.isNullOrEmpty(getAnyId()))
					{
						route = (Route) adminManager.get(Route.class,"id="+getAnyId());
						vehicleAcademicDetails.setDriverId(getVehicleAcademicDetails().getDriverId());
						vehicleAcademicDetails.setHelperId(getVehicleAcademicDetails().getHelperId());
						vehicleAcademicDetails.addRoute(route);
						route = null;
					}
					else
					{
						vehicleAcademicDetails.setDriverId(0);
						vehicleAcademicDetails.setHelperId(0);
					}
					
					vehicleAcademicDetails.setStatus(true);
					getVehicle().addVehicleAcademicDetails(vehicleAcademicDetails);
					transportManager.save(getVehicle());
					super.addActionMessage("Vehicle created successfully."); 
				}else{
					super.addActionError("Vehicle Number already assigned to some other vehicle.");
				}
			}
			getExpiryDates();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally{
			manageTransports();
		}
			return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 17, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Actions({
		@Action(value = "ajaxDoEditVehicle", results = { @Result(location = "transport/editVehicle.jsp", name = "success") }) })
	public String ajaxDoEditVehicle() throws URTUniversalException {
		try
		{
			AcademicYear academicYear = getCurrentAcademicYear();
			if(!ObjectFunctions.isNullOrEmpty(academicYear))
			{
				setAnyTitle(academicYear.getPastYear()+":"+(Long.valueOf(academicYear.getPastYear())+45));
			}
			long vehicleAcademicDetailId = getVehicleAcademicDetails().getId();
			setVehicleAcademicDetails((VehiclesAcademicDetails)transportManager.get(VehiclesAcademicDetails.class, vehicleAcademicDetailId)); 
			if(!ObjectFunctions.isNullOrEmpty(getVehicleAcademicDetails())){
				/*StringBuffer query = new StringBuffer("select group_concat(CONVERT(driverId,CHAR)),group_concat(CONVERT(helperId,CHAR)) from vehiclesAcademicDetails where academicYearId=").append(getUserAcademicYearId())
				.append(" and id != ").append(vehicleAcademicDetailId);
				Object[] vehicleIds = transportManager.get(query.toString());
				query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and status='Y'");
				if(!ObjectFunctions.isNullOrEmpty(vehicleIds) && !ObjectFunctions.isNullOrEmpty(vehicleIds[0])) {
					query.append(" and accountId not in (").append(vehicleIds[0].toString()).append(")");
				}
				List<ViewStaffPersonAccountDetails> driverList =adminManager.getAll(ViewStaffPersonAccountDetails.class, query.toString()+" and roleName='ROLE_DRIVER' order by roleName");
				if(ObjectFunctions.isNotNullOrEmpty(driverList)){
					for (ViewStaffPersonAccountDetails drivers  : driverList )
					{	
						getSelectboxMap().put(drivers.getAccountId(), drivers.getFullName());
						drivers=null;
					}
					driverList=null;
				}
				if(!ObjectFunctions.isNullOrEmpty(vehicleIds) && !ObjectFunctions.isNullOrEmpty(vehicleIds[1])) {
					query.append(" and accountId not in (").append(vehicleIds[1].toString()).append(")");
				}
				List<ViewStaffPersonAccountDetails> helperList =adminManager.getAll(ViewStaffPersonAccountDetails.class, query.toString()+" and roleName='ROLE_HELPER' order by roleName");
				if(ObjectFunctions.isNotNullOrEmpty(helperList)){
					for (ViewStaffPersonAccountDetails helper  : helperList )
					{	
						getJsonResult().put(helper.getAccountId(), helper.getFullName());
						helper=null;
					}
					helperList=null;
				}*/
				
				
				List<Object[]>  routeLt = adminManager.getAll("select id,routeName from route WHERE custId="+getUserCustId()+" and  academicYearId="+getUserAcademicYearId()+" and  status='"+Constants.YES_STRING+"' and id not in (select distinct(routeId) from RouteWithVehicles)" );
				if(ObjectFunctions.isNotNullOrEmpty(routeLt)){
					for(Object[] routeObj:routeLt){
						if(!ObjectFunctions.isNullOrEmpty(routeObj) && !ObjectFunctions.isNullOrEmpty(routeObj[0]) && !ObjectFunctions.isNullOrEmpty(routeObj[1])){
							Route route = new Route();
							route.setId(Long.valueOf(routeObj[0].toString())); 
							route.setRouteName(routeObj[1].toString()); 
							getRouteList().add(route);
							route = null;
						}
					}
				}
				
				if(!ObjectFunctions.isNullOrEmpty(getVehicleAcademicDetails().getRoute()))
				{
					Object[] routeIdObj = adminManager.get("select distinct(routeId),vehicleAcademicId from RouteWithVehicles where vehicleAcademicId="+getVehicleAcademicDetails().getId());
					if(!ObjectFunctions.isNullOrEmpty(routeIdObj))
					{
						Route route = (Route) adminManager.get(Route.class,"id="+routeIdObj[0]);
						if(!ObjectFunctions.isNullOrEmpty(routeIdObj))
						{
							getRouteList().add(route);
							setAnyId(String.valueOf(route.getId()));
							route = null;
						}
					}
				}
				//setHelperList(adminManager.getAll(ViewStaffPersonAccountDetails.class, query.toString()+" and roleName='ROLE_HELPER' order by roleName"));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 18, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Actions( {
		@Action(value = "ajaxManageTransportVehicles", results = { @Result(location = "transport/ajaxManageTransportVehicles.jsp", name = "success") }) })
	public String manageTransports() throws URTUniversalException {
	
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxManageTransportVehicles' method");
		}
		try {
			setObjectList(transportManager.getAll(ViewVehicleAndDriverInfo.class,"academicYearId="+getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
	}
	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 18, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxEditVehicle", results = {@Result(location = "transport/ajaxViewTransportVehicleDetails.jsp", name = "success")})
	public String ajaxEditVehicle() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editGroupType' method");
		}
		try
		{
			VehiclesAcademicDetails vehicleAcademicDetails =(VehiclesAcademicDetails) adminManager.get(VehiclesAcademicDetails.class, getVehicleAcademicDetails().getId());
			if(!ObjectFunctions.isNullOrEmpty(vehicleAcademicDetails))
			{
				vehicleAcademicDetails.copyFormValues(getVehicleAcademicDetails());
				adminManager.save(vehicleAcademicDetails.getVehicle());
				Route route=null;
				if(!ObjectFunctions.isNullOrEmpty(getAnyId()))
				{
					route = (Route) adminManager.get(Route.class,"id="+getAnyId());
					vehicleAcademicDetails.setDriverId(getVehicleAcademicDetails().getDriverId());
					vehicleAcademicDetails.setHelperId(getVehicleAcademicDetails().getHelperId());
					vehicleAcademicDetails.addRoute(route);
					route = null;
				}
				else
				{
					vehicleAcademicDetails.setDriverId(0);
					vehicleAcademicDetails.setHelperId(0);
				}
				
				adminManager.merge(vehicleAcademicDetails);
				vehicleAcademicDetails = null;
				super.addActionMessage("Vehicle updated successfully.");
			}
			getExpiryDates();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		finally{
			manageTransports();
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 18, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Actions({
		@Action(value = "ajaxDoAddNewPetrolCost", results = { @Result(location = "transport/ajaxAddTransportMaintenance.jsp", name = "success")
		}) })
		public String ajaxDoAddNewPetrolCost() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddNewPetrolCost' method");
		}
		try
		{
			setTransportMaintenance(null);
			Date startDate = null;
			Date closeDate = null;
			AcademicYear academicYear = getCurrentAcademicYear();
			if (getVehicleAcademicDetails().getId() > 0) {
				if (!ObjectFunctions.isNullOrEmpty(academicYear) && !ObjectFunctions.isNullOrEmpty(academicYear.getStartDate())&& !ObjectFunctions.isNullOrEmpty(academicYear.getEndDate()))
				{	
					startDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,academicYear.getStartDate().toString());
					closeDate = DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN,academicYear.getEndDate().toString());
				}
				if (!ObjectFunctions.isNullOrEmpty(startDate)&& !ObjectFunctions.isNullOrEmpty(closeDate)) {
					int startMonth = DateFunctions.getMonthOfDate(startDate);
					int closeMonth = DateFunctions.getMonthOfDate(closeDate);
					int monthId;
					int yearId;
					Calendar cal = Calendar.getInstance();
					DateFormat df = new SimpleDateFormat("MMMM"); 
					int currentMonth = DateFunctions.getMonthOfDate(new Date());
					int currentYear = DateFunctions.getDayOfYear(new Date());
					cal.setTime(startDate);
					for (Calendar openDate = cal; DateFunctions.compare2Dates(closeDate, openDate.getTime());) {
						monthId = DateFunctions.getMonthOfDate(openDate.getTime());
						yearId = DateFunctions.getDayOfYear(openDate.getTime());
						if(monthId <= currentMonth && yearId == currentYear  || monthId > currentMonth && yearId != currentYear)
						{
							if (monthId == startMonth) {
								getMonthNamesList().put(df.format(openDate.getTime()),monthId);
							} else if (monthId == closeMonth) {
								getMonthNamesList().put(df.format(openDate.getTime()),monthId);
							} else if (monthId == currentMonth && yearId == currentYear) {
								getMonthNamesList().put(df.format(openDate.getTime()),monthId);
							} else
								getMonthNamesList().put(df.format(openDate.getTime()),monthId);
							
						}
						openDate.add(Calendar.MONTH, 1);
						openDate.set(Calendar.DATE, 1);
					}
					if(!ObjectFunctions.isNullOrEmpty(getMonthNamesList())){
						StringBuffer query = new StringBuffer("select monthName from transportMaintenance where custId=").append(getUserCustId())
						.append(" and vehicleAcademicDetailId=").append(getVehicleAcademicDetails().getId()).append(" and academicYearId=").append(getUserAcademicYearId());
						List <Object[]> existingmonthNameList = transportManager.getAll(query.toString());
						if(!ObjectFunctions.isNullOrEmpty(existingmonthNameList)){
							for(Object object :existingmonthNameList){
		    					if(!ObjectFunctions.isNullOrEmpty(object)){
		    						getMonthNamesList().remove(object.toString());
		    					}
							}
						}
					}
				}
			}
		}	
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 18, 2013		 Seshu				Code Refactor.
/********************************************************************/	
	@Actions({
		@Action(value = "ajaxAddPetrolCost", results = { @Result(location = "transport/ajaxViewAllTransportMaintenance.jsp", name = "success") }) })
		public String addPetrolCost() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAddPetrolCost' method");
		}
		try
		{	
			TransportMaintenance transportMaintenance=null;
			long vehicleAcademicId = getVehicleAcademicDetails().getId();
			long monthId = getTransportMaintenance().getMonthId();
			 if(vehicleAcademicId > 0 && !ObjectFunctions.isNullOrEmpty(monthId)){
				 StringBuffer query = new StringBuffer("vehicleAcademicDetailId=").append(vehicleAcademicId).append(" and monthId=").append(monthId);
				transportMaintenance =  (TransportMaintenance) adminManager.get(TransportMaintenance.class, query.toString());
				if(ObjectFunctions.isNullOrEmpty(transportMaintenance)){
					transportMaintenance = new TransportMaintenance();
					transportMaintenance.setCreatedById(getUser().getId());
					transportMaintenance.setCustId(getUserCustId());
					transportMaintenance.setVehicleAcademicDetailId(vehicleAcademicId);
					transportMaintenance.setAcademicYear(getCurrentAcademicYear());
					transportMaintenance.setMonthId(getTransportMaintenance().getMonthId());
					transportMaintenance.setMonthName(DateFunctions.getMonthForInt(getTransportMaintenance().getMonthId()));
					transportMaintenance.setOpeningReading(getTransportMaintenance().getOpeningReading());
					transportMaintenance.setClosingReading(getTransportMaintenance().getClosingReading());
					transportMaintenance.setTotalKms(getTransportMaintenance().getTotalKms());
					transportMaintenance.setOilPurchased(getTransportMaintenance().getOilPurchased());
					transportMaintenance.setOilConsumed(getTransportMaintenance().getOilConsumed());
					transportMaintenance.setOilBalance(getTransportMaintenance().getOilBalance());
					transportMaintenance.setOilCost(getTransportMaintenance().getOilCost());
					transportMaintenance.setRepairDescription(getTransportMaintenance().getRepairDescription());
					transportMaintenance.setRepairsCost(getTransportMaintenance().getRepairsCost());
					adminManager.save(transportMaintenance);
					super.addActionMessage("Maintenance cost created successfully.");
				}
			}
			transportMaintenance=null;
			ajaxViewAllPetrolCost();
		}catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 18, 2013		 Seshu				Code Refactor.
/********************************************************************/	
	@Actions({
		@Action(value = "ajaxViewAllPetrolCost", results = { @Result(location = "transport/ajaxViewAllTransportMaintenance.jsp", name = "success") }) })
		public String ajaxViewAllPetrolCost() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewAllPetrolCost' method");  
		}
		try
		{
			StringBuffer query = new StringBuffer("custId=").append(getUserCustId()).append(" and vehicleAcademicDetailId=").append(getVehicleAcademicDetails().getId())
			.append(" order by monthId");
			setTransportMaintenanceList(adminManager.getAll(TransportMaintenance.class,query.toString()));
			query = null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}	
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 18, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxEditPetrolCost", results = { @Result(location = "transport/ajaxEditTransportMaintenance.jsp", name = "success")}) 
		public String ajaxEditwPetrolCost() throws URTUniversalException 
		{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditwPetrolCost' method");
		}
		try
		{
			setTransportMaintenance((TransportMaintenance)transportManager.get(TransportMaintenance.class,getTransportMaintenance().getId()));
			if (!ObjectFunctions.isNullOrEmpty(getTransportMaintenance())){
					getMonthNamesList().put(getTransportMaintenance().getMonthName(),getTransportMaintenance().getMonthId());
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 18, 2013		 Seshu				Code Refactor.
/********************************************************************/
	
	@Action(value = "ajaxEditMaintenanceCost", results = { @Result(location = "transport/ajaxViewAllTransportMaintenance.jsp", name = "success")}) 
	public String ajaxEditMaintenanceCost() throws URTUniversalException 
	{
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxEditMaintenanceCost' method");
	}
	try{
		TransportMaintenance transportMaintenance=null;
		if(getTransportMaintenance().getId() != 0 ){
			transportMaintenance = (TransportMaintenance) adminManager.get(TransportMaintenance.class, getTransportMaintenance().getId());
			transportMaintenance.setLastUpdatedDate(new Date());
			transportMaintenance.setLastUpdatedById(getUser().getId());
			transportMaintenance.setOpeningReading(getTransportMaintenance().getOpeningReading());
			transportMaintenance.setClosingReading(getTransportMaintenance().getClosingReading());
			transportMaintenance.setTotalKms(getTransportMaintenance().getTotalKms());
			transportMaintenance.setOilPurchased(getTransportMaintenance().getOilPurchased());
			transportMaintenance.setOilConsumed(getTransportMaintenance().getOilConsumed());
			transportMaintenance.setOilBalance(getTransportMaintenance().getOilBalance());
			transportMaintenance.setOilCost(getTransportMaintenance().getOilCost());
			transportMaintenance.setRepairDescription(getTransportMaintenance().getRepairDescription());
			transportMaintenance.setRepairsCost(getTransportMaintenance().getRepairsCost());
			transportMaintenance.setLastAccessDate(new Date());
			adminManager.save(transportMaintenance);
			super.addActionMessage("Maintenance cost Updated successfully.");
		} 
		transportMaintenance=null;
		ajaxViewAllPetrolCost();
	}
	catch(Exception ex)
	{
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 18, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxRemoveTMSMaintanence", results = { @Result(location = "transport/ajaxViewAllTransportMaintenance.jsp", name = "success")})
	public String removeTMSMaintanence() throws URTUniversalException {
		try
		{		
			if (getTransportMaintenance().getId() > 0) {
				transportManager.remove(TransportMaintenance.class,getTransportMaintenance().getId());
				super.addActionMessage("Maintenance cost removed successfully.");
			}
			ajaxViewAllPetrolCost();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 18, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxGetVehicleInformation", results = { @Result(location = "transport/ajaxViewTransportVehicleDetails.jsp", name = "success")})
	public String ajaxGetVehicleInformation() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetVehicleInformation' method");
		}
		try {
			JSONObject vehicleInfo =new JSONObject();
			if(getVehicleAcademicDetails().getId() > 0){
				StringBuffer buffer = new StringBuffer("vehicleAcademicDetailsId=").append(getVehicleAcademicDetails().getId());
				int vehicleAssignedStudsCount = adminManager.getCount("student", buffer.toString());
				buffer = new StringBuffer("vehicleAcademicDetailId=").append(getVehicleAcademicDetails().getId());
				int vehicleMaintnanceInfoCount = adminManager.getCount("transportMaintenance", buffer.toString());
				if(vehicleAssignedStudsCount > 0 || vehicleMaintnanceInfoCount > 0){
					if(vehicleAssignedStudsCount > 0 && vehicleAssignedStudsCount > 0)
						vehicleInfo.put("info","Students and Vehicle maintenance details assigned to this vehicle. Please change students to another vehicle and remove vehicle maintenace information before removing this vehicle.");
					else if(vehicleAssignedStudsCount > 0)
						vehicleInfo.put("info","Students Assigned to this vehicle. Please change these students to another vehicle before removing this vehicle.");
					else
						vehicleInfo.put("info","Vehicle maintanance information is available for this vehicle. Please remove vehicle information before removing this vehicle.");
					 getResponse().getOutputStream().print(vehicleInfo.toString());
				}else{
					removeVehicle(); 
					getResponse().getOutputStream().print(vehicleInfo.toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 18, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxRemoveVehicle", results = { @Result(location = "transport/ajaxViewTransportVehicleDetails.jsp", name = "success")})
	public String removeVehicle() throws URTUniversalException {
		try
		{		
			if(getVehicleAcademicDetails().getId() > 0 && getVehicleAcademicDetails().getVehicle().getId() > 0){
				transportManager.updateStudentsVehicleandBoardingPoints(getVehicleAcademicDetails().getId(),getUserAcademicYearId(),null);
				StringBuffer query = new StringBuffer("vehicleAcademicId=").append(getVehicleAcademicDetails().getId());
				transportManager.remove("RouteWithVehicles", query.toString());
				adminManager.remove("vehiclesAcademicDetails", "id="+getVehicleAcademicDetails().getId());
				query = new StringBuffer("vehicleId=").append(getVehicleAcademicDetails().getVehicle().getId());
				int academicVehicleDetailsCount = adminManager.getCount("vehiclesAcademicDetails", query.toString());
				if(academicVehicleDetailsCount == 0){
					adminManager.remove(Vehicles.class, getVehicleAcademicDetails().getVehicle().getId());
				}
				super.addActionMessage("Vehicle deleted successfully.");
			}
			manageTransports();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 19, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Actions( {
		@Action(value = "ajaxDoAssignVehicle", results = { @Result(location = "transport/ajaxAssignVehiclesToRoute.jsp", name = "success") }) })
	public String ajaxDoAssignVehicle() throws URTUniversalException {
	
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxDoAssignVehicle' method");
		}
		try {
			getRouteListByCustIdandAcademicYearId(getUserAcademicYearId());			
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 19, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxManageVehicleRoutes", results = { @Result(location = "transport/ajaxManageVehicleRoutes.jsp", name = "success") }) 
	public String manageVehicleRoutes() throws URTUniversalException {
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxManageVehicleRoutes' method");
		}
		try {
			 setObjectList(transportManager.getAllViewVehicleWithDriverDetails("academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 19, 2013		 Seshu				Code Refactor.
/********************************************************************/
  @Action(value = "ajaxGetVehicleByRouteId", results = { @Result(location = "transport/ajaxVehiclesByRouteId.jsp", name = "success") })
	public String ajaxGetVehicleByRouteId() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetVehicleByRouteId' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getAnyId()))
			{
				Route route = (Route) adminManager.get(Route.class,Long.valueOf(getAnyId()));
				if(!ObjectFunctions.isNullOrEmpty(route))
				{
					Object[] vehicleIds = null;
					StringBuffer stringBuffer =new StringBuffer("select group_concat(CONVERT(id,CHAR)),routeName from route WHERE ((routeEndTimeInmns between ").append(route.getRouteStartTimeinMns())
					.append(" and ").append(route.getRouteEndTimeinMns()).append(" or routeStartTimeInmns between ").append(route.getRouteStartTimeinMns()).append(" and ").append( route.getRouteEndTimeinMns())
					.append(") or (routeStartTimeInMns is NULL AND routeEndTimeInMns is Null)) and custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId());
					Object[] routeIds = transportManager.get(stringBuffer.toString());
					if(!ObjectFunctions.isNullOrEmpty(routeIds) && !ObjectFunctions.isNullOrEmpty(routeIds[0]))
					{
						vehicleIds = transportManager.get("select group_concat(CONVERT(vehicleAcademicId,CHAR)),routeId from RouteWithVehicles WHERE routeId in ("+routeIds[0].toString()+")");
					}
					if(!ObjectFunctions.isNullOrEmpty(vehicleIds) && !ObjectFunctions.isNullOrEmpty(vehicleIds[0])){
						setTempList1(adminManager.getAll(VehiclesAcademicDetails.class,"id not in("+vehicleIds[0].toString()+" )  and academicYearId="+getUserAcademicYearId()));
					}else{
						setTempList1(adminManager.getAll(VehiclesAcademicDetails.class," academicYearId="+getUserAcademicYearId()));
					}
					vehicleIds = null;
				}
				route = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
 /********************************************************************
  * Date              Name               Description
  * ========          ============       ==================
  * Dec 19, 2013	  Seshu				 Code Refactor.
 /********************************************************************/
	 @Action(value = "ajaxSaveVehicleToRoute", results = { @Result(location = "transport/ajaxManageVehicleRoutes.jsp", name = "success") })
	public String ajaxSaveVehicleToRoute() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveVehicleToRoute' method");
		}
		try {
			
			Route route = (Route) adminManager.get(Route.class,"id="+getAnyId());
			if(!ObjectFunctions.isNullOrEmpty(getEventId())){
				VehiclesAcademicDetails vehicle = (VehiclesAcademicDetails) adminManager.get(VehiclesAcademicDetails.class,Long.valueOf(getEventId()));
				if(!ObjectFunctions.isNullOrEmpty(vehicle)){
					if(!ObjectFunctions.isNullOrEmpty(route)){
						if(!ObjectFunctions.isNullOrEmpty(getVehicleAcademicDetails())){
							vehicle.setDriverId(getVehicleAcademicDetails().getDriverId());
							vehicle.setHelperId(getVehicleAcademicDetails().getHelperId());
						}
						vehicle.addRoute(route);
						route=null;
					}
					adminManager.merge(vehicle);
					vehicle = null;
				}
			}
			manageVehicleRoutes();
			super.addActionMessage("Vehicle is successfully added to Route.");
		} catch (Exception ex) {
			log.error("Entering into 'catch block':" + ex.getMessage());
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 24, 2013		 Seshu				Validate if vehicle with route contains students or not. If students not found remove vehicle with route detail.
/********************************************************************/
 	@Actions( {
 		@Action(value = "ajaxGetVehicleWithRouteInformation", results = { @Result(location = "transport/ajaxViewManageVehicleRouteDetails.jsp", name = "success")}) })
 	public String ajaxGetVehicleWithRouteInformation() throws URTUniversalException {
 		if (log.isDebugEnabled()) {
 			log.debug("Entering 'ajaxGetVehicleWithRouteInformation' method");
 		}
 		try {
 			JSONObject vehicleInfo =new JSONObject();
 			if(getVehicleAcademicDetails().getId() > 0 && StringFunctions.isNotNullOrEmpty(getAnyId())){
 				StringBuffer buffer = new StringBuffer("pickupVehicleId=").append(getVehicleAcademicDetails().getId()).append(" or dropVehicleId=").append(getVehicleAcademicDetails().getId()).append(" and status='Y'");
 				int vehicleAssignedStudsCount = adminManager.getCount("vw_studentsTransportDetails", buffer.toString());
 				if(vehicleAssignedStudsCount > 0){
 					vehicleInfo.put("info","Students assigned to this vehicle. Please change these students to another vehicle before removing this vehicle route details.");
 					getResponse().getOutputStream().print(vehicleInfo.toString());
 				}else{
 					 buffer = new StringBuffer("vehicleAcademicId=").append(getVehicleAcademicDetails().getId()).append(" and routeId=")
 					 .append(getAnyId());
 					 transportManager.remove("RouteWithVehicles",buffer.toString());
 					getResponse().getOutputStream().print(vehicleInfo.toString());
 				}
 			}
 		} catch (Exception ex) {
 			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
 		}
 		return null;
 	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 24, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxDoAssignStudent", results = { @Result(location = "transport/ajaxDoAssignStudents.jsp", name = "success") }) 
	public String ajaxDoAssignStudent() throws URTUniversalException {
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxDoAssignStudent' method");
		}
		try {
			  List<Object[]> vehicleDetails = null;
			  vehicleDetails= adminManager.getAll("select VehicleBoardingPointname,vehicleId from vw_vehicleWithBoardingPoint where custId="+ getUserCustId()+ " and academicYearId="+getUserAcademicYearId()+"   order by VehicleBoardingPointname");
				if(ObjectFunctions.isNotNullOrEmpty(vehicleDetails))
				{
					checkStudyClassHavingStudentsOrNot();
					setAnyTitle("Y");
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
	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 24, 2013		 Seshu				Code Refactor.
 * Jan 25, 2013	     Seshu				Display student transport information in excel sheet, only if student transport mode is set 'School Transport'.
/********************************************************************/
	@Actions( { @Action(value = "ajaxDownloadStudentsForTransport", results = {}) })
    public void ajaxDownloadStudentsForTransport() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDownloadStudent' method");
	}
	try {
		//Date today = new Date();
		//log.debug("The time is " +today.getHours()+":" +today.getMinutes()+":" +today.getSeconds());

	    if (getUserAcademicYearId() != 0 &&  StringFunctions.isNotNullOrEmpty(getSelectedId())) {
	    	List<Object[]> studentDetails =null;
			String fileName = "student Details_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
			ExcelView excelView = new ExcelView();
			getResponse().setContentType(excelView.getMimeType());
			getResponse().setHeader("Content-Disposition","attachment; filename="+ fileName.replace(' ', '_') + ".xls");
			excelView.setWb(Workbook.createWorkbook(getResponse().getOutputStream()));
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,10, WritableFont.BOLD, true);
			font1.setColour(Colour.WHITE);
			
			//today = new Date();
			//log.debug("***************");
			//log.debug("The time is " +today.getHours()+":" +today.getMinutes()+":" +today.getSeconds());
			studentDetails = adminManager.getAll("select distinct(routeName),routeId from vw_vehicleWithBoardingPoint where custId="+ getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" order by routeName");
			//today = new Date();
			//log.debug("The time is " +today.getHours()+":" +today.getMinutes()+":" +today.getSeconds());
			if(ObjectFunctions.isNotNullOrEmpty(studentDetails))
			{
			 	excelView.setWorkSheetName("RouteList");
			 	excelView.createWorkSheet(0);
				excelView.setDefaultFormat(excelView.getArial10format());
				excelView.getWs().setColumnView(0, 100); 
				CellView cv = new CellView();
				cv.setHidden(true);
				excelView.getWs().setColumnView(0, cv);
				cv = null;
			    int columnStart = 0;
				int cellStart = 0;
				for(Object[] routeDetails : studentDetails)
				{  
					excelView.getWs().addCell(new Label(cellStart,columnStart,routeDetails[0].toString(),excelView.getWrapCellFormat()));	
					columnStart++;
				}
				excelView.getWb().addNameArea("namedrange", excelView.getWs(), 0, columnStart, 0, 0);
				if("RouteList".equalsIgnoreCase(excelView.getWorkSheetName())){
					excelView.getWs().setHidden(true);
				}
			}studentDetails=null;
			//today = new Date();
			//log.debug("***************");
			//log.debug("The time is " +today.getHours()+":" +today.getMinutes()+":" +today.getSeconds());
			studentDetails= adminManager.getAll("select VehicleBoardingPointname,vehicleAcademicDetailId from vw_vehicleWithBoardingPoint where custId="+ getUserCustId()+ " and academicYearId="+getUserAcademicYearId()+"  order by VehicleBoardingPointname");
			//today = new Date();
			//log.debug("The time is " +today.getHours()+":" +today.getMinutes()+":" +today.getSeconds());
			if(ObjectFunctions.isNotNullOrEmpty(studentDetails))
			{
			 	excelView.setWorkSheetName("VehicleBoardingPoints");
		    	excelView.createWorkSheet(0);
				excelView.setDefaultFormat(excelView.getArial10format());
				excelView.getWs().setColumnView(0, 10); 
				CellView cv = new CellView();
				cv.setHidden(true);
				excelView.getWs().setColumnView(0, cv);
				cv = null;
			   int columnStart = 0;
			   int cellStart = 0;
				for(Object[] routeDetails : studentDetails)
				{  
					excelView.getWs().addCell(new Label(cellStart,columnStart,routeDetails[0].toString(),excelView.getWrapCellFormat()));	
					columnStart++; 
				}
				excelView.getWb().addNameArea("vehicleRange", excelView.getWs(), 0, columnStart, 0, 0);
				if("VehicleBoardingPoints".equalsIgnoreCase(excelView.getWorkSheetName())){
					excelView.getWs().setHidden(true);
				}
			}studentDetails=null;
			List<StudyClass> studyClassList = adminManager.getAll(StudyClass.class,"id in"+getSelectedId());
			if(ObjectFunctions.isNotNullOrEmpty(studyClassList)){
				for(StudyClass studyClass: studyClassList){
					excelView.setWorkSheetName(studyClass.getClassAndSection()+" class");
			    	excelView.createWorkSheet(0);
			    	excelView.setDefaultFormat(excelView.getArial10format());
			    	schoolAddresDetailsOnlyForExcel(excelView,10);
			    	excelView.getWs().removeRow(0);
					excelView.getWs().setColumnView(0, 20);
					excelView.getWs().setColumnView(1, 15);
					excelView.getWs().setColumnView(2, 20);
					excelView.getWs().setColumnView(3, 18);
					excelView.getWs().setColumnView(4, 15);
					excelView.getWs().setColumnView(5, 13);
					excelView.getWs().setColumnView(6, 15);
					excelView.getWs().setColumnView(7, 23);
					excelView.getWs().setColumnView(8, 35);
					excelView.getWs().setColumnView(9, 40);
					excelView.getWs().setColumnView(10, 0);
					CellView cv = new CellView();
					cv.setHidden(true);
					excelView.getWs().setColumnView(9, cv);
					cv = null;
					excelView.getWs().addCell(new Label(0, 5, "Admission Number",  excelView.getUsermore10BoldformatGreenBgClr()));	
					excelView.getWs().addCell(new Label(1, 5, "First Name",  excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(2, 5, "Last Name",  excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(3, 5, "Class",  excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(4, 5, "Section",  excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(5, 5, "Father Name",  excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(6, 5, "Street Name",  excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(7, 5, "Route Name",  excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(8, 5, "Vehicle Name - Boarding Point Name",  excelView.getUsermore10BoldformatGreenBgClr()));
					excelView.getWs().addCell(new Label(9, 5, "Student Id",  excelView.getUsermore10BoldformatGreenBgClr()));
					setTodayDate(DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, new Date()));
					//today = new Date();
					//log.debug("***************");
					//log.debug("S time is " +today.getHours()+":" +today.getMinutes()+":" +today.getSeconds());
		    		//studentDetails = adminManager.getAll("select admissionNumber,firstName,lastName,className,section,fatherName,streetName,routeName,VehicleBoardingPointname,studentId,transportMode  from vw_studentDetails where classSectionId="+studyClass.getId()+" and description is null order by IF((registerNumber IS NULL or registerNumber = ''),fullName,registerNumber)");
					studentDetails = adminManager.getAll("select admissionNumber,firstName,lastName,className,section,fatherName,streetName,routeName,VehicleBoardingPointname,studentId,transportMode  from vw_studentDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and classSectionId="+studyClass.getId()+" and transportMode='T'  and description is null order by IF((registerNumber IS NULL or registerNumber = ''),fullName,registerNumber)");
		    		//today = new Date();
					//log.debug("E time is " +today.getHours()+":" +today.gcustId=79etMinutes()+":" +today.getSeconds());
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
							    			if(!ObjectFunctions.isNullOrEmpty(object[10]) && "T".equals(object[10].toString())){
							    				displayDropDownlistFromNamedRangeForExcelSheet(cellStart, columnStart,"namedrange",excelView,object[l].toString());
							    			}
							    		}
									    else if (l == 8) {
									    	if(!ObjectFunctions.isNullOrEmpty(object[10]) && "T".equals(object[10].toString())){
									    		displayDropDownlistFromNamedRangeForExcelSheet(cellStart, columnStart,"vehicleRange",excelView,object[l].toString());
									    	}
										}else {
										    excelView.getWs().addCell(new Label(cellStart,columnStart,object[l].toString()));
										}
								    }
								  }else{
									if(l == 7){
										displayDropDownlistFromNamedRangeForExcelSheet(cellStart, columnStart,"namedrange",excelView,"");
									}else if(l == 8){
										displayDropDownlistFromNamedRangeForExcelSheet(cellStart, columnStart,"vehicleRange",excelView,"");
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
			}
			excelView.getWb().write();
			excelView.getWb().close();
			studentDetails = null;
			excelView = null;
	    }
	    
	   // today = new Date();

		//log.debug("End time is " +today.getHours()+":" +today.getMinutes()+":" +today.getSeconds());
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
   }
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Jan 25, 2014		 Seshu				Code Refactor.
/********************************************************************/
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
	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 24, 2013		 Seshu				Code Refactor.
/********************************************************************/
	 @Actions( { @Action(value = "ajaxUploadStudentTransportData", results = { @Result(location = "transport/ajaxAssignStudentsVehicle.jsp", name = "success"),@Result(location = "transport/ajaxAssignStudentsVehicle.jsp", name = "dummyInit")}) })
	    public String ajaxUploadStudentTransportData() {
			if (log.isDebugEnabled()) {
			    log.debug("Entering 'ajaxUploadStudentTransportData' method");
			}
			try {
				 boolean excelFileType = false;
					excelFileType = validateExcelFileType(getUploadContentType());
					if(excelFileType){
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						  return "dummyInit";
					}
				/*if (!(Constants.FILE_TYPE_XLS.equalsIgnoreCase(getUploadContentType()) || Constants.FILE_TYPE_APPLICATION_XLS.equalsIgnoreCase(getUploadContentType())
						|| Constants.FILE_TYPE_APPLICATION_DOWNLOAD.equalsIgnoreCase(getUploadContentType()) || Constants.FILE_TYPE_APPLICATION_STREAM_XLS.equalsIgnoreCase(getUploadContentType()) 
						)) {
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						return "dummyInit";
				    }*/
					
					WorkbookSettings ws = new WorkbookSettings();
					ws.setLocale(new Locale("en", "EN"));
					Workbook workbook = Workbook.getWorkbook(getUpload(), ws);
					
					Sheet firstSheet1 = workbook.getSheet(0);
					if(!ObjectFunctions.isNullOrEmpty(firstSheet1))
					{
						Cell secondRow[] = firstSheet1.getRow(5);
						if(!ObjectFunctions.isNullOrEmpty(secondRow))
						{
							if(!ObjectFunctions.isNullOrEmpty(secondRow[0]) && !ObjectFunctions.isNullOrEmpty(secondRow[1]))
							{
								String fistColumn = secondRow[0].getContents();
								String secondColumn = secondRow[1].getContents();
								
								if(!"Admission Number".equalsIgnoreCase(fistColumn.toString())  || !"First Name".equalsIgnoreCase(secondColumn.toString()))
								{
									log.debug("No file to upload....");
									super.addActionError("File type not matched.");
									return "dummyInit";
								}
								fistColumn = null;
								secondColumn = null;
							}
							else
							{
								log.debug("No file to upload....");
								super.addActionError("File type not matched.");
								return "dummyInit";
							}
						}
						else
						{
							log.debug("No file to upload....");
							super.addActionError("File type not matched.");
							return "dummyInit";
						}
					}
					else
					{
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						return "dummyInit";
					}
					
					
					Sheet sheet = null;
				    StringBuffer failureMsg=new StringBuffer();
				    failureMsg.append("The following admission number(s) is(are) already available.");
				    if (getUserAcademicYearId() > 0) {
							setCurrentUserCustId(getUserCustId());
							final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							Date date = new Date();
							log.debug("****************Start:" + sdf.format(date));
							for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets()-2; sheetNum++) 
							{
							    sheet = workbook.getSheet(sheetNum);
							    createStudentTransport(sheet);
							    sheet = null;
							}
							
							date = new Date();
							log.debug("****************End:" + sdf.format(date));
						if(!StringFunctions.isNullOrEmpty(getAnyTitle())){
								super.addActionMessage("Students are successfully assigned to the vehicles."); 
			 			}else{
			 				super.addActionError("Please upload valid student(s) vehicles data.");
			 			}
					}
			 } catch (Exception e) {
			    // TODO: handle exception
			    e.printStackTrace();
			}finally{
				ajaxDoAssignStudent();
			}
			return SUCCESS;
	  }
 /********************************************************************
  * Date              Name               Description
  * ========          ============       ==================
  * Dec 26, 2013	  Seshu				 Code Refactor.
 /********************************************************************/
	@Action(value = "ajaxGetRouteInformation", results = { @Result(type = "json", name = "success",params = {"includeProperties","anyId"}) }) 
	public String ajaxGetRouteInformation() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetRouteInformation' method");
		}
		try {
			if(getRoute().getId() > 0)
			{
				StringBuffer query = new StringBuffer("routeId=").append(getRoute().getId());
				int count=adminManager.getCount("RouteWithVehicles", query.toString());
				JSONObject studentActivityTypesJson =new JSONObject().put("studActivities", count);
				getResponse().getOutputStream().print(studentActivityTypesJson.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
/********************************************************************
  * Date              Name               Description
  * ========          ============       ==================
  * Dec 26, 2013	  Seshu				 Code Refactor.
 /********************************************************************/
	@Action(value = "ajaxRemoveRoute", results = { @Result(location = "transport/ajaxRouteDetails.jsp", name = "success") }) 
	public String ajaxRemoveRoute() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveRoute' method");
		}
		try
		{		
			if(getRoute().getId() > 0){
				StringBuffer query = new StringBuffer("select group_concat(CONVERT(id,CHAR)),id from routeBoardingPoints WHERE routeId=").append(getRoute().getId());
				Object[] boardingPointIds = transportManager.get(query.toString());
				if(!ObjectFunctions.isNullOrEmpty(boardingPointIds) && !ObjectFunctions.isNullOrEmpty(boardingPointIds[0])){
					transportManager.updateStudentsVehicleandBoardingPoints(0, getUserAcademicYearId(),boardingPointIds[0].toString());
					//query = new StringBuffer("id in(").append(boardingPointIds[0].toString()).append(")");
					//transportManager.remove("routeBoardingPoints", query.toString());
					 transportManager.updateRouteBoardingPointStatus(getUserCustId(),getUserAcademicYearId(),boardingPointIds[0].toString());//Updating all boardingPoint  status with boarding pointIds if routeboardingpoint is deleted(soft delete)
				}
				// query = new StringBuffer("routeId =").append(getRoute().getId());
				//transportManager.remove("RouteWithVehicles",query.toString());//Here i have commented no need to remove record its just soft delete so i am not deleting the record changing the flag not displaying the vehicle with that route
				//query = new StringBuffer("id=").append(getRoute().getId());
				//transportManager.remove("route", query.toString());/// Here record is not deleted permanent  just soft removed so i have updated status1
				
				query = new StringBuffer("routeId=").append(getRoute().getId());
				transportManager.remove("RouteWithVehicles", query.toString());
				
				Route route = (Route)adminManager.get(Route.class, getRoute().getId());
				route.setStatus(false); 
				adminManager.save(route);
				super.addActionMessage("Route removed successfully.");
			}
			getRouteListByCustIdandAcademicYearId(getUserAcademicYearId());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
  * Date              Name               Description
  * ========          ============       ==================
  * Dec 27, 2013	  Seshu				 Code Refactor.
 /********************************************************************/
	@Action(value = "ajaxAddDriverOrHelper", results = { @Result(location = "transport/ajaxManageDriverDetails.jsp", name = "success"), 
			                                             @Result(location = "transport/ajaxManageHelperDetails.jsp", name = "organizationShortName"),
			                                             @Result(location = "transport/ajaxDriversByRouteIdVehicleId.jsp", name = "showDrivers")	                                             
	}) 
	public String ajaxAddDriverOrHelper() throws URTUniversalException {
	
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxAddDriverOrHelper' method");
	   }
	try
	{     
		  Customer customer=getCustomerByCustId();
		  User createduser=null;
		  String randamNumber=null;
		 // String randamUserName=null;
		  if(!ObjectFunctions.isNullOrEmpty(getNewUser()) && StringFunctions.isNotNullOrEmpty(getAnyTitle())){		
	    	getNewUser().setCustId(getUserCustId());
	    	getNewUser().setPerson(getPerson());
	    	getNewUser().setPrimaryAddress(getAddress());
			if(!ObjectFunctions.isNullOrEmpty(customer.getCustomerShortName()) && !StringFunctions.isNullOrEmpty(getPerson().getFirstName())){
				String userName = StringFunctions.prepareUserName(customer.getCustomerShortName().toLowerCase(),getPerson().getFirstName().toLowerCase(), getPerson().getLastName().toLowerCase());
				if(transportManager.IsUserAccountExists(customer.getCustomerShortName().toLowerCase(), getPerson().getFirstName().toLowerCase(),getPerson().getLastName().toLowerCase())){
					 randamNumber=Math.round(Math.random()*10)+DateFormatter.formatDate(DateFormatter.HHMM_GMT_PATTERN, new Date());
					 userName=userName+randamNumber;
					/*if("ROLE_DRIVER".equalsIgnoreCase(getAnyTitle())){
						 super.addActionError("Driver name already exist.");
						 return SUCCESS;
					}
					else{
						super.addActionError("Helper name already exist.");
						return "organizationShortName";
					}*/
				}
				getNewUser().setUsername(userName);
				getNewUser().setSecondaryUsername(userName);
		    	getNewUser().setPassword(PasswordUtils.passwordEncoder(userName, null));
		    	if("ROLE_DRIVER".equalsIgnoreCase(getAnyTitle())){	
		    		getNewUser().addNewRole(transportManager.getRoleByName(Constants.SCHOOL_DRIVER));
		    	}
		    	else{
		    		getNewUser().addNewRole(transportManager.getRoleByName(Constants.SCHOOL_HELPER));
		    	}
		    	 createduser=(User)transportManager.saveUser(getNewUser());
		    	if(!ObjectFunctions.isNullOrEmpty(createduser)){
		    		Staff staff=new Staff();
		    		staff.setAccount(createduser);
		    		staff.setCustId(getUserCustId());
		    		AcademicYear academicYear = getCurrentAcademicYear();
		    		if(!ObjectFunctions.isNullOrEmpty(academicYear))
		    		{
		    			staff.setAcademicYear(academicYear);	
		    		}
		    		staff.setStatus("Y");
		    		setStaff((Staff) transportManager.save(staff));
		    		if("ROLE_DRIVER".equalsIgnoreCase(getAnyTitle())){	
		    			super.addActionMessage("Driver created successfully.");
		    		}else{
		    			super.addActionMessage("Helper created successfully.");
		    		}
		    	}
			}else{
				super.addActionError("Please update organization shortname. This is used to prefix the username.");
				return "organizationShortName";
			}
		}
		 customer = null;
		 
		 if(!StringFunctions.isNullOrEmpty(getTempString3()))
		 {
			 if("addRoute".equalsIgnoreCase(getTempString3()))
			 {
				 long routeId = 0;
				 if(!StringFunctions.isNullOrEmpty(getAnyId()))
				 {
					 routeId = Long.valueOf(getAnyId());
				 }
				 ajaxDriverAndHelpersList(getRoute().getRoutePointStartTime(), getRoute().getRoutePointEndTime(),getTempId2(),routeId);
				 return "showDrivers";
			 }
		 }
	}
	catch(Exception ex)
	{
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	finally{
		driverOrHelperInformation();
	}
	if("ROLE_HELPER".equalsIgnoreCase(getAnyTitle()))
		return "organizationShortName";
	return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * July 8, 2013      Rama		        We need to show expirydates information for vehicles.
 * 
/********************************************************************/	
	@Action(value = "ajaxDoGetVehiclesInformation", results = { @Result(location = "transport/ajaxGetVehiclesInformation.jsp", name = "success") })
	public String ajaxDoGetVehiclesInformation() throws URTUniversalException{
		try{				
			getExpiryDates(); 				
	     }
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;			
	}	
	
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 31, 2013		 Seshu				For displaying routes and vehicles list for sending mobile SMS.
/********************************************************************/
	@Action(value = "ajaxGetTransportVehicleList", results = { @Result(location = "transport/ajaxManageTransportVehiclesList.jsp", name = "vehicle"),
			 												   @Result(location = "transport/ajaxManageTransportVehiclesList.jsp", name = "route"),
			 												   @Result(location = "transport/ajaxAssignedStundentsToRoute.jsp", name = "routeAll") })
	public String vehicleInformation() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxGetTransportVehicleList' method");
	}
	try
	{ 
		String type = getParamValue("type");
		String routeId = getParamValue("routeId");
		StringBuffer query = null;
		if(!StringFunctions.isNullOrEmpty(type)){
			// In SMS module create SMS or Email under this select transport type as 'Vehicle Wide'. 
			query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId())
			.append(" and status='Y'").append(" group by vehicleAcademicId");
			setTempList(transportManager.getAllViewAssignedVehiclestoRoutes(query.toString()));
			return "vehicle";
		}
		if(!StringFunctions.isNullOrEmpty(routeId)){
			if("All".equalsIgnoreCase(routeId)){
				//In SMS module create SMS or Email under this select Transport messages, under select route with ALL option. Display all students count.
				query = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId())
				.append(" and status='Y'").append(" and (pickupBoardingPointId!=0 or dropBoardingPointId!=0) and (pickupVehicleId !=0 or dropVehicleId !=0)");
		    	setObjectList(transportManager.getAll(ViewStudentsTransportDetails.class,query.toString()));
				return "routeAll";
			}else{
				//In SMS module create SMS or Email under this select Transport messages, under select any route.
				query = new StringBuffer("custId=").append(getUserCustId()).append(" and routeId=").append(routeId).append(" and academicYearId=").append(getUserAcademicYearId())
				.append(" and status='Y'").append(" group by vehicleAcademicId");
				setTempList(transportManager.getAllViewAssignedVehiclestoRoutes(query.toString()));
				setAnyId(routeId);
				return "route";
			}
		}
	}
	catch(Exception ex)
	{
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
} 
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 30, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxLoadRouteDetails", results = {@Result(location = "transport/ajaxRouteDetails.jsp", name = "success") })
		public String ajaxLoadRouteDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'routeInformation' method");
		}
		try
		{
			getRouteListByCustIdandAcademicYearId(getUserAcademicYearId());
		}
		catch(Exception ex)
		{ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Jan 2nd, 2014	 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxGetTransportRouteList", results = { @Result(location = "transport/ajaxManageTransportRouteList.jsp", name = "success") })
		public String ajaxGetTransportRouteList() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'routeInformation' method");
		}
		try
		{
			List<Object[]>  routeLt =adminManager.getAllHqlQuery("select r.id,r.routeName from Route r WHERE r.custId="+getUserCustId()+" and r.academicYearId="+getUserAcademicYearId()+" and status='"+Constants.YES_STRING+"'");
			if(ObjectFunctions.isNotNullOrEmpty(routeLt)){
				getRouteNamesList().put("All", "All");
				for(Object[] route:routeLt){
					if(!ObjectFunctions.isNullOrEmpty(route) && !ObjectFunctions.isNullOrEmpty(route[0]) && !ObjectFunctions.isNullOrEmpty(route[1])){
						getRouteNamesList().put(route[0].toString(), route[1].toString());
					}
				}
			}
			/*List<Route>  routeLt = transportManager.getRouteDetailsByCustId(getUserCustId(),getUserAcademicYearId());
			if(ObjectFunctions.isNotNullOrEmpty(routeLt)){
				getRouteNamesList().put("All", "All");
				for(Route route:routeLt){
					if(!ObjectFunctions.isNullOrEmpty(route)){
						getRouteNamesList().put(String.valueOf(route.getId()), route.getRouteName());
					}
				}
			}*/
			routeLt = null;
		}
		catch(Exception ex)
		{ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 30, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxCheckRouteNameInRoute", results = { @Result(type = "json", name = "success", params = {"includeProperties", "autoCheck" }) }) 
    public String ajaxCheckRouteNameInRoute() throws URTUniversalException {
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'ajaxCheckRouteNameInRoute' method");
		}
		try {
			String termNameAndId = getSelectedId().replace("?keyWord=", ",");
			String[] keyWordAndId = termNameAndId.split(",");
			if (!StringFunctions.isNullOrEmpty(keyWordAndId.toString())) {
				List categoryNameList = null;
				if(StringFunctions.isNotNullOrEmpty(keyWordAndId[0].toString().trim()) && Long.valueOf(keyWordAndId[0].toString().trim()) > 0){
					categoryNameList = transportManager.getAll(Route.class,"academicYearId="+ getUserAcademicYearId() + " and routeName ='"+keyWordAndId[1].toString().trim()+"' and id !="+keyWordAndId[0].toString().trim()+" and status ='"+Constants.YES_STRING+"'");
				}else{
					if(StringFunctions.isNotNullOrEmpty(keyWordAndId[1].toString().trim())) 
					categoryNameList = transportManager.getAll(Route.class,"academicYearId="+ getUserAcademicYearId() + " and routeName ='"+keyWordAndId[1].toString().trim()+"' and status ='"+Constants.YES_STRING+"'");
				}
				if (ObjectFunctions.isNullOrEmpty(categoryNameList)) {
				    setAutoCheck("0");
				} else if (categoryNameList.size() > 0) {
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
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 30, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxGetBoardingPoints", results = { @Result(type = "json", name = "success", params = {"includeProperties", "routeBoardingPointsList.*" }) }) 
	public String ajaxGetBoardingPoints() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetBoardingPoints' method");
		}
		try {
			if(getTempId1() > 0 ){
				
				StringBuffer query = new StringBuffer("select id,boardingPointName,boardingPointStatTime,boardingPointEndTime,boardingPointFeeAmount from routeBoardingPoints where routeId=")
						.append(getTempId1()).append(" order by boardingPointOrder");
				
				List<Object[]> boardingPointsList = adminManager.getAll(query.toString());
				if(!ObjectFunctions.isNullOrEmpty(boardingPointsList))
				{
					List routeBoardingPointObjList = new ArrayList();
					
					for(Object[] routeBoardingPointsObj : boardingPointsList)
					{
						Object[] routeBoardingPointObj = new Object[6];
						
						routeBoardingPointObj[0] = routeBoardingPointsObj[0];
						routeBoardingPointObj[1] = routeBoardingPointsObj[1];
						routeBoardingPointObj[2] = routeBoardingPointsObj[2];
						routeBoardingPointObj[3] = routeBoardingPointsObj[3];
						routeBoardingPointObj[4] = routeBoardingPointsObj[4];
						
						StringBuffer query1 = new StringBuffer("SELECT COUNT(*) count,id FROM studentFeePaidDetails WHERE custId=");
						query1.append(getUserCustId());
						query1.append(" AND studTransportDetailsId in (SELECT id FROM studentTransportDetails WHERE custId=");
						query1.append(getUserCustId());
						query1.append(" AND academicYearId=");
						query1.append(getUserAcademicYearId());
						query1.append(" AND (pickupBoardingPointId=");
						query1.append(routeBoardingPointsObj[0].toString());
						query1.append(" OR ").append("dropBoardingPointId=");
						query1.append(routeBoardingPointsObj[0].toString());
						query1.append("))");
						Object[] studentFeePaid = adminManager.get(query1.toString());
						if(!ObjectFunctions.isNullOrEmpty(studentFeePaid)){
							if(Long.valueOf(studentFeePaid[0].toString())>0)
								routeBoardingPointObj[5] = "Y";
							else
								routeBoardingPointObj[5] = "N";
						}
							
						routeBoardingPointObjList.add(routeBoardingPointObj);
						studentFeePaid=null;
					}
					setRouteBoardingPointsList(routeBoardingPointObjList);
					routeBoardingPointObjList = null;
				}
				boardingPointsList = null;
				/*StringBuffer query = new StringBuffer("select id,boardingPointName,boardingPointStatTime,boardingPointEndTime,boardingPointFeeAmount from routeBoardingPoints where routeId=").append(getTempId1())
				.append(" order by boardingPointOrder");
				setRouteBoardingPointsList(adminManager.getAll(query.toString()));
				query = null;*/
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 30, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxCheckBoardingPointAssignedStatus", results = { @Result(type = "json", name = "success",params = {"includeProperties","tempBoolean.*"}) }) 
	public String ajaxCheckBoardingPointAssignedStatus() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckBoardingPointAssignedStatus' method");
		}
		try {
			setTempBoolean(true);
		   if(getTempId2() > 0){
			   StringBuffer query = new StringBuffer("custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and description is not null and boardingPointId=").append(getTempId2());//Here inactive student we have consider while deleting the routeboardingPoint
			   long count = adminManager.getCount("student",query.toString());
			   if(count <=0 ){
				   //Here we have deleted the routrBoadingpoint with soft delete
				  RouteBoardingPoints routeBoardingPoints = (RouteBoardingPoints)adminManager.get(RouteBoardingPoints.class, getTempId2());
				  routeBoardingPoints.setStatus(false); //inactive
				  adminManager.save(routeBoardingPoints);
				  // adminManager.remove(RouteBoardingPoints.class, getTempId2());
				   setTempBoolean(true);
			   }else
				   setTempBoolean(false);
		   }
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 30, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Actions( { @Action(value = "ajaxLoadDriverOrHelperByRoleName", results = { @Result(location = "transport/ajaxManageDriverDetails.jsp", name = "success"),
																	            @Result(location = "transport/ajaxManageHelperDetails.jsp", name = "helperInfo")}), 
                @Action(value = "ajaxAddDriverOrHelperStaff", results = {@Result(location = "transport/ajaxManageDriverOrHelper.jsp", name = "success")})})
	public String driverOrHelperInformation() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'driverOrHelperInformation' method");
		}
		try
		{
			String roleName=null;
			String anyTitleName = getParamValue("anyTitleName");
			if(!StringFunctions.isNullOrEmpty(anyTitleName)){
				 roleName=anyTitleName;
			}
			else if(StringFunctions.isNotNullOrEmpty(getParamValue("type"))){
				 roleName=getParamValue("type");
			}else
				roleName=getAnyTitle();
			if(!StringFunctions.isNullOrEmpty(roleName)){
				//setViewStaffAccountDetails((transportManager.getAllDriverOrHelperByCustIdIdAndType(roleName,getUserCustId())));
				if("ROLE_DRIVER".equalsIgnoreCase(roleName))
				{
					List<ViewStaffPersonAccountDetails> driversList = transportManager.getAll(ViewStaffPersonAccountDetails.class,"custId="+getUserCustId()+" and academicYearId="+ getUserAcademicYearId() + " and description is null and roleName='"+ roleName+"'");
					if(!ObjectFunctions.isNullOrEmpty(driversList))
					{
						for(ViewStaffPersonAccountDetails viewStaffPersonAccountDetails : driversList)
						{
							viewStaffPersonAccountDetails.setAssignedVehicleList(transportManager.getAll("select concat(name, ' - ', ifnull(routeName, '')) AS staffName  from vw_assignedVehiclestoRoutes where driverId="+viewStaffPersonAccountDetails.getAccountId()+" and status='"+Constants.YES_STRING+"'"));
							getDriverList().add(viewStaffPersonAccountDetails);
						}
					}
				}
				else if("ROLE_HELPER".equalsIgnoreCase(roleName))
				{
					
					List<ViewStaffPersonAccountDetails> helperList = transportManager.getAll(ViewStaffPersonAccountDetails.class,"custId="+getUserCustId()+" and academicYearId="+ getUserAcademicYearId() + " and description is null and roleName='"+ roleName+"'");
					
					if(!ObjectFunctions.isNullOrEmpty(helperList))
					{
						for(ViewStaffPersonAccountDetails viewStaffPersonAccountDetails : helperList)
						{
							viewStaffPersonAccountDetails.setAssignedVehicleList(transportManager.getAll("select concat(name, ' - ', ifnull(routeName, '')) AS staffName  from vw_assignedVehiclestoRoutes where helperId="+viewStaffPersonAccountDetails.getAccountId()+" and status='"+Constants.YES_STRING+"'"));
							getHelperList().add(viewStaffPersonAccountDetails);
						}
					}
					
					return "helperInfo";
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 30, 2013		 Seshu				Code Refactor.
/********************************************************************/
	
	@Actions( { @Action(value = "ajaxDoAddVehicleDriverOrHelper", results = {@Result(location = "transport/ajaxAddVehicleDriverOrHeper.jsp", name = "success")}),
		 @Action(value = "ajaxViewVehicleDriverOrHelper", results = {@Result(location = "transport/ajaxViewAddDriverAndHelper.jsp", name = "success")})
		
	})
	
		public String ajaxDoAddVehicleDriverOrHelper() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAddVehicleDriverOrHelper' method");
		}
		try
		{
			setPerson(null);
			setAddress(null);
			setStatesList(null);
			if(!ObjectFunctions.isNullOrEmpty(getCurrentAcademicYear())){
				setAnyTitle(getCurrentAcademicYear().getPastYear()+":"+(Long.valueOf(getCurrentAcademicYear().getPastYear())+45));
			}
			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ========          ============       ==================
 * Dec 30, 2013		 Seshu				Code Refactor.
/********************************************************************/
	@Action(value = "ajaxDoEditDriverOrHelper", results = { @Result(location = "transport/editDriverOrHelper.jsp", name = "success") }) 
	public String doEditDriverOrHelper() throws URTUniversalException {
		try
		{
			if(!ObjectFunctions.isNullOrEmpty(getCurrentAcademicYear())){
				setAnyTitle(getCurrentAcademicYear().getPastYear()+":"+(Long.valueOf(getCurrentAcademicYear().getPastYear())+45));
			}
			setStatesList((List<State>)SMSLookUpDataCache.lookUpDataMap.get(Constants.STATE_LIST));
			if (getStaff().getId() > 0) {
				long staffId=getStaff().getId();
				setStaff((Staff)transportManager.get(Staff.class, staffId));
				setNewUser(getStaff().getAccount());
				setPerson(getStaff().getAccount().getPerson());
				setAddress(getStaff().getAccount().getPrimaryAddress());
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
/********************************************************************
 * version		Date              	Name               Description
 * ========     ===========     	============       ==================
 * 1.0			Dec 31, 2013		Seshu			   Disable Driver or helper from transport admin
/********************************************************************/	
	@Action(value = "ajaxDisableDriverOrHelper", results = { @Result(location = "transport/ajaxManageDriverDetails.jsp", name = "success"),
															@Result(location = "transport/ajaxManageHelperDetails.jsp", name = "helperInfo")	}) 
    public String ajaxDisableDriverOrHelper() throws URTUniversalException {
	if (log.isDebugEnabled()) {
	    log.debug("Entering 'ajaxDisableDriverOrHelper' method");
	}
	try {
		StringBuffer query = new StringBuffer("academicYearId=").append(getUserAcademicYearId());
		int count = 0;
		if("ROLE_DRIVER".equalsIgnoreCase(getAnyTitle())){
			query.append(" and driverId=").append(getTempId1());
			count = transportManager.getCount("vehiclesAcademicDetails", query.toString());
			if(count > 0){
				super.addActionError("This driver is assigned to vehicle. Please unassign staff from vehicle.");
				return SUCCESS;
			}
		}else if("ROLE_HELPER".equalsIgnoreCase(getAnyTitle())){
			query.append(" and helperId=").append(getTempId1());
			count = transportManager.getCount("vehiclesAcademicDetails", query.toString());
			if(count > 0){
				super.addActionError("This helper is assigned to vehicle. Please unassign staff from vehicle.");
				return "helperInfo";
			}
		}
		Map<String,String> msg = staffManager.disableStaff(getTempId(),getStaff().getDescription(), getUser().getId());
		addActionMessages(msg);
		
	} catch (Exception ex) {
	    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
    }
/********************************************************************
 * version		Date              	Name               Description
 * ========     ===========     	============       ==================
 * 1.0			Dec 31, 2013		Seshu			   When we click on form cancel button display all vehicles details.
/********************************************************************/	
	@Action(value = "ajaxDoManageTransportVehicles", results = { @Result(location = "transport/ajaxViewTransportVehicleDetails.jsp", name = "success") }) 
	public String ajaxDoManageTransportVehicles() throws URTUniversalException {
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxManageTransportVehicles' method");
		}
		try {
			manageTransports();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	
		return SUCCESS;
	}
/********************************************************************
 * version		Date              	Name               Description
 * ========     ===========     	============       ==================
 * 1.0			Dec 31, 2013		Seshu			   When we click on form cancel button display all vehicles details.
/********************************************************************/	
	@Action(value = "ajaxGetAssignedStundentsToVehicle", results = { @Result(location = "transport/ajaxAssignedStundentsToVehicle.jsp", name = "success"),
																		 @Result(location = "transport/ajaxAssignedStundentsToVehicle.jsp", name = "routeWideStudents"),
																		 @Result(location = "transport/ajaxAssignedStundentsToVehicle.jsp", name = "vehicleWideStudents"),
				                                                         @Result(location = "transport/ajaxAssignedStundentsToRoute.jsp", name = "routeWide"),
				                                                         @Result(location = "transport/ajaxAssignedStundentsToRoute.jsp", name = "vehicleWide") })
	 public String ajaxGetAssignedStundentsToVehicle() throws URTUniversalException {
		try
		{	
			setCustomer(getCustomerByCustId());
			getSmsCount();
		    String vehicleId = getParamValue("vehicleId");
		    String routeId = getParamValue("routeId");
		    String transportType=getParamValue("transportId");
		    Route route=(Route) transportManager.get(Route.class, "Id= "+routeId);
		    StringBuffer query = new StringBuffer("academicYearId=").append(getUserAcademicYearId());
		  //In SMS module create SMS or Email select Transport Message -> select Vehicle Wide -> Select Vehicle -> Select Point-> B->Both Pickup and Drop P->Only Pickup D-Only Drop
		   if(StringFunctions.isNotNullOrEmpty(vehicleId) && StringFunctions.isNullOrEmpty(routeId) && "B".equalsIgnoreCase(transportType)){
		    	query.append(" and pickupVehicleId=").append(vehicleId).append(" and dropVehicleId=").append(vehicleId).append(" and status='Y'").append(" and activeStatus in ('Y','S','B')");
		    }else if(StringFunctions.isNotNullOrEmpty(vehicleId) && StringFunctions.isNullOrEmpty(routeId) && "P".equalsIgnoreCase(transportType)){
		    	query.append(" and pickupVehicleId=").append(vehicleId).append(" and dropVehicleId!=").append(vehicleId).append(" and status='Y'").append(" and activeStatus in ('Y','S','B')");
		    }else if(StringFunctions.isNotNullOrEmpty(vehicleId) && StringFunctions.isNullOrEmpty(routeId) && "D".equalsIgnoreCase(transportType)){
		    	query.append(" and pickupVehicleId!=").append(vehicleId).append(" and dropVehicleId=").append(vehicleId).append(" and status='Y'").append(" and activeStatus in ('Y','S','B')");
		    }//In SMS module create SMS or Email select Transport Message -> select Route Wide -> Select Route -> Select Vehicle -> Select Point-> B->Both Pickup and Drop P->Only Pickup D-Only Drop
		   else if(StringFunctions.isNotNullOrEmpty(vehicleId) && StringFunctions.isNotNullOrEmpty(routeId) && "B".equalsIgnoreCase(transportType)){
		    	query.append(" and (pickupVehicleId=").append(vehicleId).append(" and dropVehicleId=").append(vehicleId).append(") and (pickupBoardingPointId in ").append(route.getRouteBoardingPointIds()).append(" or dropBoardingPointId in ").append(route.getRouteBoardingPointIds()).append(") and status='Y'").append(" and activeStatus in ('Y','S','B')");
		    }else if(StringFunctions.isNotNullOrEmpty(vehicleId) && StringFunctions.isNotNullOrEmpty(routeId) && "P".equalsIgnoreCase(transportType)){
		    	query.append(" and (pickupVehicleId=").append(vehicleId).append(" and dropVehicleId!=").append(vehicleId).append(") and (pickupBoardingPointId in ").append(route.getRouteBoardingPointIds()).append(" or dropBoardingPointId in ").append(route.getRouteBoardingPointIds()).append(") and status='Y'").append(" and activeStatus in ('Y','S','B')");
		    }else if(StringFunctions.isNotNullOrEmpty(vehicleId) && StringFunctions.isNotNullOrEmpty(routeId) && "D".equalsIgnoreCase(transportType)){
		    	query.append(" and (pickupVehicleId!=").append(vehicleId).append(" and dropVehicleId=").append(vehicleId).append(") and (pickupBoardingPointId in ").append(route.getRouteBoardingPointIds()).append(" or dropBoardingPointId in ").append(route.getRouteBoardingPointIds()).append(") and status='Y'").append(" and activeStatus in ('Y','S','B')");
		    }
		   	setObjectList(transportManager.getAll(ViewStudentsTransportDetails.class,query.toString()));
	    	return "vehicleWideStudents";
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * version		Date              	Name               Description
 * ========     ===========     	============       ==================
 * 1.0			Jan 23, 2014		Seshu			   For validating vehicle number is already exist or not.
/********************************************************************/			
	@Action(value = "ajaxCheckVehicleNumber", results = { @Result(type = "json", name = "success", params = {"includeProperties","autoCheck"}) })
	public String ajaxCheckVehicleNumber() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxCheckVehicleNumber' method");
		}
		try {
			String vehicleNumber =getParamValue("keyWord");
			if (!ObjectFunctions.isNullOrEmpty(vehicleNumber)) {
				int vehiclesCount = transportManager.getCount("vehicles", "custId="+getUserCustId()+" and vehicleNumber='"+vehicleNumber.trim()+"'");
				if(vehiclesCount <= 0)
					setAutoCheck("0");
				else
					setAutoCheck("1");
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}

		return SUCCESS;
	}
		
		@Actions( { @Action(value = "ajaxCheckRouteNumber", results = { @Result(type = "json", name = "success", params = {"includeProperties","autoCheck"}) }) })
		public String ajaxCheckRouteNumber() throws URTUniversalException {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxCheckRouteNumber' method");
			}
			try {
				long routeNumber =Long.valueOf(getParamValue("keyWord"));
				if (!ObjectFunctions.isNullOrEmpty(routeNumber)) {
					List routeNameList = transportManager.checkRouteNumber(routeNumber);
					if (ObjectFunctions.isNullOrEmpty(routeNameList)) {
						setAutoCheck("0");
					} else if (routeNameList.size() > 0) {
						setAutoCheck("1");
					} else {
						setAutoCheck("0");
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}

			return SUCCESS;
		}
		
		@Actions({
			@Action(value = "ajaxEditDriverOrHelper", results = {@Result(location = "transport/ajaxManageDriverDetails.jsp", name = "success"),
			           @Result(location = "transport/ajaxManageHelperDetails.jsp", name = "helperInfo")
			}) })
			public String editDriverOrHelper() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'EditDriverHelper' method");
			}
			try 
			{
				if(!ObjectFunctions.isNullOrEmpty(getStaff().getId())){
				long staffId=Long.valueOf(getStaff().getId());
				Staff staff=(Staff)transportManager.get(Staff.class, staffId);
				User account=staff.getAccount();
				account.getPerson().copyFrom(getPerson());
				account.getPrimaryAddress().copyFrom(getAddress());				
				account=transportManager.saveUser(account);
				staff.setAccount(account);
				transportManager.save(staff);	
				ViewStaffPersonAccountDetails personAccountDetails=transportManager.getPersonAccountDetailsByStudentId(staffId);
				if(!ObjectFunctions.isNullOrEmpty(personAccountDetails)){
 					if("ROLE_DRIVER".equalsIgnoreCase(personAccountDetails.getRoleName()))
					{
						super.addActionMessage("Driver updated successfully.");
						setAnyTitle("ROLE_DRIVER");
						driverOrHelperInformation();
					} else {
						super.addActionMessage("Helper updated successfully.");
						setAnyTitle("ROLE_HELPER");
						driverOrHelperInformation();
						return "helperInfo";
					}
				}
			}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
		/*@Actions({
			@Action(value = "ajaxRemoveDriverOrHelper", results = { @Result(location = "transport/ajaxViewDriversOrHeperList.jsp", name = "success") }) })
		public String removeDriverOrHelper() throws URTUniversalException {
			try
			{		
			    String accountId = getParamValue("id");
			    if(!ObjectFunctions.isNullOrEmpty(accountId)){
			    	User user = (User) transportManager.get(User.class, Long.valueOf(accountId));
			    	long personId = user.getPerson().getId();
			    	long addressId = user.getPrimaryAddress().getId();
			    	user.setPerson(null);
			    	user.setPrimaryAddress(null);
			    	transportManager.save(user);
			    	user = null;
			    	transportManager.removeUserRoleByUserId(Long.valueOf(accountId));
			    	if(!ObjectFunctions.isNullOrEmpty(personId)){
			    		transportManager.remove(Person.class, personId);
			    	}
			    	if(!ObjectFunctions.isNullOrEmpty(addressId)){
			    		transportManager.remove(Address.class, addressId);
			    	}
			    	Staff staff=transportManager.getStaffByAaccountId(Long.valueOf(accountId));
			    	if(!ObjectFunctions.isNullOrEmpty(staff)){
			    		transportManager.remove(Staff.class,Long.valueOf(staff.getId()));
			    	}
			    	transportManager.remove(User.class, Long.valueOf(accountId));
			    	Vehicles vehicle = (Vehicles) transportManager.get(Vehicles.class,"driverId="+Long.valueOf(accountId));
			    	if(!ObjectFunctions.isNullOrEmpty(vehicle))
			    	{
			    		vehicle.setDriverId(0);
			    		transportManager.save(vehicle);
			    	}
			    	super.addActionMessage("Helper/Driver deleted successfully.");
			    }
			    setStaffList((transportManager.getAll(Staff.class)));
			    //manageTripsCommonMethod();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}*/
		
	/*@Actions({
			@Action(value = "ajaxGetStudentBusDetails", results = {@Result(location = "transport/ajaxGetStudentBusDetails.jsp", name = "success") }) })
			public String doStudentBusDetails() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'addVehicleStaff' method");
			}
			try
			{
				 //setStudentsList(transportManager.getAllStudentsByRollNumber(getRoleNumber(),getUserCustId()));	
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}*/
	public void searchTranportStudentIsNull(String criteria){ 
		try {
			setStudentsList(adminManager.getAll(ViewStudentPersonAccountDetails.class,"custId="+getUserCustId()+" and (firstName like '%"+criteria+"%' or lastName like '%"+criteria+"%') and status='"+Constants.YES_STRING+"' and boardingPointId=0"));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	@Actions({
		@Action(value = "ajaxGetStudentRoutes", results = {@Result(location = "transport/ajaxStudentBusDetails.jsp", name = "success") }) })
		public String doStudentRoutes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetStudentRoutes' method");
		}
		try {
			getRouteListByCustIdandAcademicYearId(getUserAcademicYearId());
			//setRouteList(transportManager.getAll(Route.class,"custId="+getUserCustId()+" and academicYearId="+getUsrChgedAcademicId()));
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	public Map getJsonResult() {
		if(ObjectFunctions.isNullOrEmpty(super.jsonResult))
		{
			super.jsonResult=new HashMap();
		}
		return super.jsonResult;
	}	
	
	@Actions( {
		@Action(value = "ajaxLoadAllRotes", results = {  @Result(type = "json", name = "success", params = {"includeProperties", "routeList.*" }) }) })
	public String loadAllRotes() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'loadAllRotes' method");
		}
		try {
			setRouteList(adminManager.getAll(Route.class, "custId="+getUserCustId()+" and academicYearId<="+getUserAcademicYearId()));
		  } catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	
	@Actions( { @Action(value = "ajaxCheckRegistrationNumberInVehicle", results = { @Result(type = "json", name = "success", params = {
		    "includeProperties", "autoCheck" }) }) })
	    public String ajaxCheckRegistrationNumberInVehicle() throws URTUniversalException {
			if (log.isDebugEnabled()) {
			    log.debug("Entering 'ajaxCheckTransportFeeCategoryType' method");
			}
		try {
			String registrationNumber = getParamValue("keyWord");
		    if (StringFunctions.isNotNullOrEmpty(registrationNumber)) {
				List categoryNameList = transportManager.getAll(Vehicles.class," custId="+getUserCustId()+" and academicYearId<="+ getUserAcademicYearId() + " and registrationNumber like '%"+registrationNumber.trim()+"%'");
				if (ObjectFunctions.isNullOrEmpty(categoryNameList)) {
				    setAutoCheck("0");
				} else if (categoryNameList.size() > 0) {
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
	
	@Actions( { @Action(value = "ajaxCheckInsuranceNumberInVehicle", results = { @Result(type = "json", name = "success", params = {
		    "includeProperties", "autoCheck" }) }) })
	    public String ajaxCheckInsuranceNumberInVehicle() throws URTUniversalException {
			if (log.isDebugEnabled()) {
			    log.debug("Entering 'ajaxCheckInsuranceNumberInVehicle' method");
			}
		try {
			String insuranceNumber = getParamValue("keyWord");
		    if (StringFunctions.isNotNullOrEmpty(insuranceNumber)) {
		    	int categoryCount = transportManager.getCount("vehicles", " custId="+getUserCustId()+"  and insuranceNumber like '%"+insuranceNumber.trim()+"%'");
				//List categoryNameList = transportManager.getAll(Vehicles.class," custId="+getUserCustId()+" and academicYearId<="+ getUserAcademicYearId() + " and insuranceNumber like '%"+insuranceNumber.trim()+"%'");
				if (categoryCount>0) {
				    setAutoCheck("1");
				} else  {
				    setAutoCheck("0");
				}
		    }
		} catch (Exception ex) {
		    log.error("Entering into 'catch block':" + ex.getMessage());
		    ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	    }
	@Actions( { @Action(value = "ajaxCheckChasisNumberInVehicle", results = { @Result(type = "json", name = "success", params = {
		    "includeProperties", "autoCheck" }) }) })
	    public String ajaxCheckChasisNumberInVehicle() throws URTUniversalException {
			if (log.isDebugEnabled()) {
			    log.debug("Entering 'ajaxCheckChasisNumberInVehicle' method");
			}
		try {
			String chasisNumber = getParamValue("keyWord");
		    if (StringFunctions.isNotNullOrEmpty(chasisNumber)) {
				List categoryNameList = transportManager.getAll(Vehicles.class," custId="+getUserCustId()+"  and chasisNumber like '%"+chasisNumber.trim()+"%'");//and academicYearId<="+ getUserAcademicYearId() + "
				if (ObjectFunctions.isNullOrEmpty(categoryNameList)) {
				    setAutoCheck("0");
				} else if (categoryNameList.size() > 0) {
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
/********************************************************************
 * Date              Name               Description
 * ============      =======		    ==================
 * Jan 17, 2013      Seshu		    	Get students assiged vehicles routes list.
/********************************************************************/
	@Action(value = "ajaxManageTransportRouteWiseReport", results = { @Result(location = "reports/ajaxManageTransportRouteWiseReports.jsp", name = "success") })
	public String ajaxManageTransportRouteWiseReport() throws URTUniversalException {
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxManageTransportRouteWiseReport' method");
		}		
		try {
			setTempList(transportManager.getAllRoutesAssiginedToVehicles(getUserAcademicYearId()));
			}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
/********************************************************************
 * Date              Name               Description
 * ============      =======		    ==================
 * Jan 16, 2013      Seshu		    	Display students assigned vehicles
/********************************************************************/
	@Action(value = "ajaxManageTransportVehicleWiseReport", results = { @Result(location = "reports/ajaxManageTransportVehicleWiseReports.jsp", name = "success") }) 
	public String ajaxManageTransportVehicleWiseReport() throws URTUniversalException {
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxManageTransportVehicleWiseReport' method");
		}		
		try {
			setTempList(transportManager.getAllVehiclesAssiginedToStudents(getUserAcademicYearId()));
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	/*@Actions( {
		@Action(value = "ajaxManageTransportFeeCollectionWiseReport", results = { @Result(location = "reports/ajaxVehicleWiseFeeCollectionReport.jsp", name = "success") }) })
	public String ajaxManageTransportFeeCollectionWiseReport() throws URTUniversalException {

		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxManageTransportFeeCollectionWiseReport' method");
		}		
		try {
			setVehicleList(transportManager.getAll(Vehicles.class,"custId="+getUserCustId()+" and academicYearId="+ getUserAcademicYearId()));
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}*/
	@Actions( {
		@Action(value = "ajaxManageTmsVehiclesDefaultFeeCollectionWiseReport", results = { @Result(location = "reports/ajaxVehicleWiseDefaulterFeeCollectionReport.jsp", name = "success") }) })
	public String ajaxManageTmsVehiclesDefaultFeeCollectionWiseReport() throws URTUniversalException {

		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxManageTmsVehiclesDefaultFeeCollectionWiseReport' method");
		}		
		try {
			setVehicleList(transportManager.getAll(Vehicles.class,"custId="+getUserCustId()+" and academicYearId<="+ getUserAcademicYearId()));
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	   public void createStudentTransport(Sheet sheet)
		{
			try
			{
				//final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				log.debug("Started createStudentTransport........................");
				int rowSize = sheet.getRows();
				Student student = null;
				Long vehicleAcademicDetailId= null;
				Long boardingPointId=null;
				Cell cell;
				String[] acountRules = new String[3];
				cell = sheet.getCell(0, 0);
				acountRules[0] = StringFunctions.trim(cell.getContents());
				cell = sheet.getCell(1, 0); 	
				acountRules[1] = StringFunctions.trim(cell.getContents());
				cell = sheet.getCell(2, 0);
				acountRules[2] = StringFunctions.trim(cell.getContents());
				 List<Object[]> vehicleListByBoardingPoint=null; 
				 StringBuffer errorMessageBuffer = new StringBuffer();
				 HashMap<String, Fee> feeMap = new HashMap<String, Fee>();
				for (int i = 6; i < rowSize; i++) {
					if (!ObjectFunctions.isNullOrEmpty(sheet.getCell(9, i).getContents())) {
						long studentId = Long.valueOf(sheet.getCell(9, i).getContents());
						String vehicleName = sheet.getCell(8, i).getContents();
						//Date date = new Date();
					    // log.debug("Start:" + sdf.format(date));
						student = (Student) transportManager.get(Student.class,Long.valueOf(studentId));
						//date = new Date();
					     //log.debug("End:" + sdf.format(date));
					     
					     log.debug("Name:" + student.getFullFormattedName());
						long categoryId =student.getCategoryId();
							if(StringFunctions.isNotNullOrEmpty(vehicleName))
							{
								if(!ObjectFunctions.isNullOrEmpty(student))
								{
									vehicleListByBoardingPoint = adminManager.getAll("select vehicleAcademicDetailId,boardingPointId from vw_vehicleWithBoardingPoint where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and vehicleBoardingPointname='"+vehicleName+"'");
									if(ObjectFunctions.isNotNullOrEmpty(vehicleListByBoardingPoint))
									{
										for(Object[] str:vehicleListByBoardingPoint)
										{
											if(!ObjectFunctions.isNullOrEmpty(str[0]))
												vehicleAcademicDetailId= Long.valueOf(str[0].toString());
											if(!ObjectFunctions.isNullOrEmpty(str[1]))
												boardingPointId= Long.valueOf(str[1].toString()); 
											str=null;
										}
									}
									if(!ObjectFunctions.isNullOrEmpty(student.getVehicleAcademicDetailsId()) && !ObjectFunctions.isNullOrEmpty(vehicleAcademicDetailId))
									{
										if(student.getVehicleAcademicDetailsId().compareTo(vehicleAcademicDetailId) != 0)
										{
											ViewVehicleWithDriverDetails viewVehicleWithDriverDetails = (ViewVehicleWithDriverDetails) adminManager.get(ViewVehicleWithDriverDetails.class, "custId="+getUserCustId()+" and vehicleAcademicDetailId="+vehicleAcademicDetailId);
											if(!ObjectFunctions.isNullOrEmpty(viewVehicleWithDriverDetails))
											{
												if(viewVehicleWithDriverDetails.getAvailablePickUp() > 0 || viewVehicleWithDriverDetails.getAvailableDrop() > 0 )
												{
													adminManager.updateStudentTransport(studentId,vehicleAcademicDetailId,boardingPointId);
												}
												else
												{
													errorMessageBuffer.append(student.getAccount().getFullPersonName() +", " );
												}
											}
											viewVehicleWithDriverDetails = null;
										}
										else
										{
											continue;
										}
										/*else
										{
											adminManager.updateStudentTransport(studentId,vehicleAcademicDetailId,boardingPointId);
										}*/
									}
									else
									{
										if(!ObjectFunctions.isNullOrEmpty(vehicleAcademicDetailId))
										{
											ViewVehicleWithDriverDetails viewVehicleWithDriverDetails = (ViewVehicleWithDriverDetails) studentManager.get(ViewVehicleWithDriverDetails.class, "custId="+getUserCustId()+" and vehicleAcademicDetailId="+vehicleAcademicDetailId);
											if(!ObjectFunctions.isNullOrEmpty(viewVehicleWithDriverDetails))
											{
												if(viewVehicleWithDriverDetails.getAvailablePickUp() > 0 || viewVehicleWithDriverDetails.getAvailableDrop() > 0 )
												{
													adminManager.updateStudentTransport(studentId,vehicleAcademicDetailId,boardingPointId);
												}
												else
												{
													errorMessageBuffer.append(student.getAccount().getFullPersonName() +", " );
												}
											}
											viewVehicleWithDriverDetails = null;
										}
									}
									if(!ObjectFunctions.isNullOrEmpty(boardingPointId))
									{
										Fee feeObj = null;
										String boardingPointCategoryId = boardingPointId+"_"+categoryId;
										if(!ObjectFunctions.isNullOrEmpty(feeMap.get(boardingPointCategoryId)))
										{
											feeObj = feeMap.get(boardingPointCategoryId);
										}
										else
										{
											feeObj = (Fee) adminManager.get(Fee.class,"custId="+getUserCustId()+" and academicYearId ="+getUserAcademicYearId()+" and routeBoardingPointId="+boardingPointId+" and categoryId="+categoryId);
											feeMap.put(boardingPointCategoryId, feeObj);
										}
										if(!ObjectFunctions.isNullOrEmpty(feeObj)){
											//This method is used to change the feeCpnfgured status is "Y" if fee table contains records with boadrding pointId and categoryId
											adminManager.updateFeeConfiguredStatusInStudentForTransport(0,categoryId,"Y",studentId);
											Object[] studentFeeObj = adminManager.get("select paymentStatus,studentId from vw_studentFeePaymentDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId="+studentId+" and paymentStatus='"+Constants.NO_STRING+"'");
											if(!ObjectFunctions.isNullOrEmpty(studentFeeObj)){
												Object[] studentOb = adminManager.get("select feePaidStatus,id from student where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and id="+studentId+" and feePaidStatus='F'");
												if(!ObjectFunctions.isNullOrEmpty(studentOb)){
													adminManager.studentFeePaidStatusForTransport(studentId,getUserCustId(),getUserAcademicYearId(),categoryId);
												}
												studentOb = null;
											}
										}
										/*boolean studentFeePaidStatusUpdate = updateStudentFeePaidStatus(getUserCustId(),getUserAcademicYearId(),studentId,boardingPointId,categoryId);
										if(studentFeePaidStatusUpdate)
											adminManager.studentFeePaidStatusForTransport(studentId,getUserCustId(),getUserAcademicYearId(),categoryId);*/
										feeObj = null;
									}
									vehicleListByBoardingPoint = null;
								}
							}else{
								adminManager.updateStudentTransport(studentId,vehicleAcademicDetailId,boardingPointId);
								// Here we have changed student feePaidStatus if student paid all terms fee
								adminManager.studentFeePaidStatusWithTransport(studentId,getUserCustId(),getUserAcademicYearId(),categoryId,student.getClassNameId());
							}
						setAnyTitle("Vehicle is not assigned for the students");								
					}
					
					vehicleAcademicDetailId = boardingPointId = null;
					
					log.debug("*******************");
				} 
				
				if(errorMessageBuffer.length() > 0)
	  		    {
					errorMessageBuffer.insert(0,"Few student(s) data not valid. Vehicle Capacity is full, Please assign some other vehicle:");
	  		    	 //super.addActionError(errorMsg.toString().substring(0,errorMsg.toString().length()-1) + " Few student(s) data not valid. Please verify the data.");
	  		    	super.addActionError(errorMessageBuffer.toString().substring(0,errorMessageBuffer.toString().length()-1));
	  		    }
				
				feeMap = null;
			}
			catch (Exception e) {
	            log.debug("Record not processed: " + e.getMessage());
				e.printStackTrace();
			}
	 }
	 @Action(value = "ajaxStudentTransportApplication", results = { @Result(location = "transport/ajaxStudentTransportReceiptsGeneration.jsp", name = "success") }) 
		public String ajaxDownLoadStudentforReceopt() throws URTUniversalException {
			if (log.isDebugEnabled()) { 
				log.debug("Entering 'ajaxDownLoadStudentforReceopt' method");
			}
		try {
			setStudyClassList(adminManager.getAll(StudyClass.class, "custId=" + getUserCustId() + " and academicYearId=" + getUserAcademicYearId()));
			ajaxTransportApplicationSettings();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 @Action(value = "ajaxTransportApplicationSettings", results = { @Result(location = "transport/ajaxAddTransportApplicationSettings.jsp", name = "success") }) 
		public String ajaxTransportApplicationSettings() throws URTUniversalException {
			if (log.isDebugEnabled()) { 
				log.debug("Entering 'ajaxTransportApplicationSettings' method");
			}
		try {
			TcSettings transportfromcSettings = (TcSettings) adminManager.get(TcSettings.class, "custId="+getUserCustId()+" and type='transportRequestForm'");
			if(!ObjectFunctions.isNullOrEmpty(transportfromcSettings)){
				getObjectList().add(transportfromcSettings);
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Action(value = "ajaxAddTransportApplicationSettings", results = { @Result(location = "transport/ajaxAddTransportApplicationSettings.jsp", name = "success") }) 
		public String ajaxAddTransportApplicationSettings() throws URTUniversalException {
			if (log.isDebugEnabled()) { 
				log.debug("Entering 'ajaxAddTransportApplicationSettings' method");
			}
		try {
			//StringBuffer pathName = new StringBuffer("userFiles/").append("transportRequestForm/").append(getUserCustId());
			   // pathName.append("/");
			    if(getFileUpload().size()!=0)
			    {
			    	AcademicYear academicYear = getCurrentAcademicYear();
			    	TcSettings transportFormSettings = null;
			    	 if(getFileUpload().size()!=0)
			    	 {
				    	  for(int i=0;i<getFileUpload().size();i++)
				    	  {
				    		 if(!ObjectFunctions.isNullOrEmpty(getFileUpload().get(i)))
				    		 {
					    		 File file = getFileUpload().get(i);
					    		 String fileName = getFileUploadFileName().get(i);
					    		 //File destDir = new File(getSession().getServletContext().getRealPath(pathName+fileName));
					    		 try {
					    			 String filePath = adminManager.getUploadTemplates(file, academicYear.getAcademicYear(), fileName);
					    			 
					    			 	transportFormSettings = new TcSettings();
					    			 	transportFormSettings.setFileName(fileName);
					    			 	transportFormSettings.setFilePath(filePath);
					    			 	transportFormSettings.setCustId(getUserCustId());
					    			 	transportFormSettings.setType("transportRequestForm");
					    				transportFormSettings.setCreatedById(getUser().getId());
					    				adminManager.save(transportFormSettings);
					    				transportFormSettings = null;
					    				super.addActionMessage("Transport Request Form uploaded  successfully");
									} catch (Throwable e) {
										e.printStackTrace();
								}
								 //FileUtils.copyFile(file, destDir);
				    		 }
				        }
			    	 }
			    	 academicYear = null;
				}
			    ajaxTransportApplicationSettings();
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Action(value = "ajaxDeleteTransportRequestTemplate", results = { @Result(location = "transport/ajaxAddTransportApplicationSettings.jsp", name = "success") }) 
	public String ajaxDeleteTransportRequestTemplate() throws URTUniversalException {
		if (log.isDebugEnabled()) { 
			log.debug("Entering 'ajaxDeleteTransportRequestTemplate' method");
		}
	try {
		if(getTempId2() > 0){
			
			TcSettings transportfromcSettings = (TcSettings) adminManager.get(TcSettings.class, "id="+getTempId2());
			if(!ObjectFunctions.isNullOrEmpty(transportfromcSettings)){
				adminManager.remove(TcSettings.class, getTempId2());
				
				/*try {
					S3Wrapper s3Wrapper = new S3Wrapper();
					URL url = new URL(transportfromcSettings.getFilePath());
					s3Wrapper.delete(url);
				} catch (Exception e) {
					log.debug("File Not deleted:"+e.getMessage());
				}*/
			}
			transportfromcSettings = null;
			super.addActionMessage("Transport request form setting is deleted successfully.");
		}
	} catch (Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoGetSearchTransportStudent", results = { @Result(location = "transport/ajaxViewTransportRequestStudentLsit.jsp", name = "success") })	})
	public String ajaxDoGetSearchTransportStudent() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoGetSearchTransportStudent' method");
		}
		try {
        StringBuffer sqlQuery = null;
        if(StringFunctions.isNotNullOrEmpty(getSelectedId())){
        	setSelectedId(getSelectedId());
         	sqlQuery = new StringBuffer("select firstName,boardingPointName,admissionNumber,transportMode,classSectionId,studentId,lastName,classNameAndSection  from vw_studentDetails").  
         	append(" where custId=").append(getUserCustId()).append(" and (firstName like '%").append(getSelectedId()).append("%' or lastName like '%").append(getSelectedId()).append("%') and academicYearId=").append(getUserAcademicYearId()).append(" and transportMode='T' and description is null group by accountId");
         	setTempList(adminManager.getAll(sqlQuery.toString()));
         }
        sqlQuery = null;
       } catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
       }
		return SUCCESS;
	}
	 @Actions( {
			@Action(value = "ajaxGenerateRequestForm", results = { @Result(location = "transport/ajaxViewTransportRequestStudentLsit.jsp", name = "success") }) })
	public String getAjaxGenerateRequestForm() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'getAjaxGenerateRequestForm' method");
		}
		try {
			if(StringFunctions.isNotNullOrEmpty(getStudyClassId())){
				setStudyClassId(getStudyClassId());
				setTempList(adminManager.getAll("select p.firstName,rb.boardingPointName,a.admissionNumber,s.transportMode,s.classSectionId,IFNULL(s.id, 0) as studentId,p.lastName  FROM student s JOIN academicYear ac on (s.custId = ac.custId and ac.id = s.academicYearId) LEFT JOIN Account a on(a.id=s.accountId)  LEFT JOIN  Person p on (p.id=a.personId) LEFT JOIN routeBoardingPoints rb on (s.transportMode='T' and s.boardingPointId = rb.id and s.academicYearId= rb.academicYearId) LEFT JOIN route r on (r.id = rb.routeId) LEFT JOIN vehiclesAcademicDetails va on (s.transportMode='T' and s.vehicleAcademicDetailsId = va.id and va.academicYearId = s.academicYearId)  where  s.custId="+getUserCustId()+" and s.academicYearId="+getUserAcademicYearId()+" and s.classSectionId="+getStudyClassId()+" and s.transportMode='T' group by s.accountId")); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 
	@Actions( { @Action(value = "ajaxGenerateTransportRequestForm", results = { @Result(location = "transport/ajaxViewTransportRequestStudentLsit.jsp", name = "success") }) })
		public String ajaxGenerateTransportRequestForm() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGenerateTransportRequestForm' method");
			}
			StringBuffer query = null;
			InputStream templateFile = null;
			IXDocReport report = null;
			IContext context = null;
			StringBuffer studentDOCXFilePath = null;
			List<ViewTransportRequestFormDetails> vwStudentList = null;
			OutputStream out = null;
			File directory = null;
			TcSettings tcSettingsObj = null;
			HashMap<String, IXDocReport> tcSettingsMap = new HashMap<String, IXDocReport>();
			StringBuffer studentsListQuery = null;
			try {
				Customer customer = getCustomerByCustId();
				if (!ObjectFunctions.isNullOrEmpty(customer) && StringFunctions.isNotNullOrEmpty(customer.getCustomerShortName())) 
				{
					ZipOutputStream zipOutStream = new ZipOutputStream(getResponse().getOutputStream());
					getResponse().setContentType("application/zip");
					if (StringFunctions.isNullOrEmpty(getTempString()))
						getResponse().addHeader("Content-Disposition","attachment; filename=TransportRequestForm.zip");
					else
						getResponse().addHeader("Content-Disposition","attachment; filename="+ getTempString().replace(' ', '_')+ "_TransportRequestForm.zip");
					
					StringBuffer trTemplateFilePath = new StringBuffer("userFiles/transportRequestForm/");
					StringBuffer generatedTsFilePath = new StringBuffer(trTemplateFilePath).append(getUserCustId()).append("/").append("temp/");
					
					File outFile = Files.createTempDirectory("HallTicket").toFile();
					
					File outFile1 = new File(getSession().getServletContext().getRealPath(generatedTsFilePath.toString()));
					if(outFile1.exists())
					FileUtils.deleteDirectory(outFile1);// Removes existing files
					outFile1.mkdirs(); // If directories are not available it creates directories
					// For generating HallTicket for classwise
					if (StringFunctions.isNotNullOrEmpty(getClassSectionId())  && StringFunctions.isNotNullOrEmpty(getStudentNumber())) {
						query = new StringBuffer("custId=").append(getUserCustId()).append(" and type='transportRequestForm'");
						studentsListQuery = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and transportMode='T'").append(" and classSectionId=").append(getClassSectionId()).append(" and studId in").append(getStudentNumber());
					}else if (StringFunctions.isNotNullOrEmpty(getStudentNumber())) {
						query = new StringBuffer("custId=").append(getUserCustId()).append(" and type='transportRequestForm'");
						studentsListQuery = new StringBuffer("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and transportMode='T'").append(" and studId in").append(getStudentNumber());
					}
					if (!ObjectFunctions.isNullOrEmpty(query)) {
						tcSettingsObj = (TcSettings) adminManager.get(TcSettings.class, query.toString());
						if (!ObjectFunctions.isNullOrEmpty(tcSettingsObj) && StringFunctions.isNotNullOrEmpty(tcSettingsObj.getFileName())) 
						{
							URL url = new URL(tcSettingsObj.getFilePath());
							URLConnection conn = url.openConnection();
							InputStream is = conn.getInputStream();
							
							String ext = FilenameUtils.getExtension(tcSettingsObj.getFileName());
							//File file = File.createTempFile(htSetting.getFileName(), "",directory);
							directory = new File(outFile,tcSettingsObj.getFileName());
							
							/*directory = File.createTempFile(htSetting.getFileName(), null);
							directory.deleteOnExit();*/
							FileUtils.copyInputStreamToFile(is, directory);
							
							
							//directory=new File(getSession().getServletContext().getRealPath(trTemplateFilePath.append(getUserCustId()).append("/").append(tcSettingsObj.getFileName()).toString()));
							if(directory.exists()){
							templateFile = new FileInputStream(directory);
							if (!ObjectFunctions.isNullOrEmpty(templateFile)) {
								report = XDocReportRegistry.getRegistry().loadReport(templateFile,TemplateEngineKind.Velocity);
								FieldsMetadata metadata = new FieldsMetadata();
								metadata.addFieldAsImage("logo");
								//metadata.addFieldAsImage("logo1");
								metadata.setUseImageSize(true);
								metadata.addFieldAsImage("fileImageNotExistsAndRemoveImageTemplate","logo",NullImageBehaviour.RemoveImageTemplate);
								//metadata.addFieldAsImage("fileImageNotExistsAndRemoveImageTemplate","logo1",NullImageBehaviour.RemoveImageTemplate);
								report.setFieldsMetadata(metadata);
								tcSettingsMap.put(tcSettingsObj.getType().trim(), report);								
							}
						  }
						}
					}
					if (!ObjectFunctions.isNullOrEmpty(studentsListQuery))
						studentsListQuery.append("group by studId");
					vwStudentList = adminManager.getAll(ViewTransportRequestFormDetails.class, studentsListQuery.toString());
					if (ObjectFunctions.isNullOrEmpty(vwStudentList) || ObjectFunctions.isNullOrEmpty(tcSettingsMap)) {
						if (ObjectFunctions.isNullOrEmpty(tcSettingsMap))
							adminManager.writeToFile("Transport form settings are not available. Please add transport form settings.",getSession().getServletContext().getRealPath(new StringBuffer(generatedTsFilePath).append("readMe.doc").toString()));
						if (ObjectFunctions.isNullOrEmpty(vwStudentList))
							adminManager.writeToFile("Students are not avilable for generating transport form.",
									getSession().getServletContext().getRealPath(new StringBuffer(generatedTsFilePath).append("readMe.doc").toString()));
					} else {
						Collections.sort(vwStudentList);
						try {
							ViewStudentPersonAccountDetails studentDetails = null;
							for (ViewTransportRequestFormDetails viewStudentPersonAccountDetails : vwStudentList) {
								//log.debug("classSectionId="+ viewStudentPersonAccountDetails.getClassSectionId());
									report = tcSettingsMap.get("transportRequestForm");
									if (ObjectFunctions.isNullOrEmpty(report)) {
										studentDOCXFilePath = new StringBuffer(generatedTsFilePath).append("readMe").append(viewStudentPersonAccountDetails.getClassNameAndSection()).append("_").append(viewStudentPersonAccountDetails.getPersonFullName()).append("_").append(viewStudentPersonAccountDetails.getAdmissionNumber().replaceAll("/", "")).append(".doc");
										if (ObjectFunctions.isNullOrEmpty(report))
											adminManager.writeToFile("Please create transport form settings.",getSession().getServletContext().getRealPath(studentDOCXFilePath.toString()));
										continue;
									} else {
										studentDOCXFilePath = new StringBuffer(generatedTsFilePath).append("TRANSPORT_FORM_").append(viewStudentPersonAccountDetails.getClassNameAndSection()).append("_").append(viewStudentPersonAccountDetails.getPersonFullName()).append("_").append(viewStudentPersonAccountDetails.getAdmissionNumber().replaceAll("/", "")).append(".doc");
										try {
											studentDetails = new ViewStudentPersonAccountDetails();
											log.debug(viewStudentPersonAccountDetails.getPersonFirstLastNameOnly());
											studentDetails.setFirstName(viewStudentPersonAccountDetails.getPersonFirstLastNameOnly());
											studentDetails.setClassNameAndSection(viewStudentPersonAccountDetails.getClassNameAndSection());
											studentDetails.setOrganization(viewStudentPersonAccountDetails.getOrganization());
											studentDetails.setStreetName(viewStudentPersonAccountDetails.getStreetName());
											studentDetails.setCity(viewStudentPersonAccountDetails.getCity());	
											if (StringFunctions.isNullOrEmpty(viewStudentPersonAccountDetails.getPostalCode()))
												studentDetails.setPostalCode("");
											else
												studentDetails.setPostalCode(viewStudentPersonAccountDetails.getPostalCode());
											if (StringFunctions.isNullOrEmpty(viewStudentPersonAccountDetails.getMobileNumber()))
												studentDetails.setMobileNumber("");
											else
												studentDetails.setMobileNumber(viewStudentPersonAccountDetails.getMobileNumber());
											studentDetails.setBoardingPointFeeAmount(Double.valueOf(viewStudentPersonAccountDetails.getBoardingPointFeeAmount()));
											// Address
											if ("NULL".equalsIgnoreCase(viewStudentPersonAccountDetails.getAddressLine1()) || StringFunctions.isNullOrEmpty(viewStudentPersonAccountDetails.getAddressLine1()))
												studentDetails.setAddressLine1("");
											else
												studentDetails.setAddressLine1(viewStudentPersonAccountDetails.getAddressLine1());
											if ("NULL".equalsIgnoreCase(viewStudentPersonAccountDetails.getBoardingPointName()) || StringFunctions.isNullOrEmpty(viewStudentPersonAccountDetails.getBoardingPointName()))
												studentDetails.setBoardingPointName("");
											else
												studentDetails.setBoardingPointName(viewStudentPersonAccountDetails.getBoardingPointName());
											
											if ("NULL".equalsIgnoreCase(viewStudentPersonAccountDetails.getVehicleName()) || StringFunctions.isNullOrEmpty(viewStudentPersonAccountDetails.getVehicleName()))
												studentDetails.setVehicleName("");
											else
												studentDetails.setVehicleName(viewStudentPersonAccountDetails.getVehicleName());
											if (StringFunctions.isNullOrEmpty(viewStudentPersonAccountDetails.getVehicleNumber()))
												studentDetails.setVehicleNumber("");
											else
												studentDetails.setVehicleNumber(viewStudentPersonAccountDetails.getVehicleNumber());
											if (StringFunctions.isNullOrEmpty(viewStudentPersonAccountDetails.getRouteName()))
												studentDetails.setRouteName("");
											else
												studentDetails.setRouteName(viewStudentPersonAccountDetails.getRouteName());
											
											studentDetails.setImageName(viewStudentPersonAccountDetails.getImageName());
											studentDetails.setImagePath(viewStudentPersonAccountDetails.getImagePath());
											
											context = report.createContext();
											if(StringFunctions.isNotNullOrEmpty(viewStudentPersonAccountDetails.getSchoolIdCardAttachmentFilePath())){
												File imageFile = new File(viewStudentPersonAccountDetails.getSchoolIdCardAttachmentFilePath());
												if(!ObjectFunctions.isNullOrEmpty(imageFile) && imageFile.exists()){
													IImageProvider studentLogo = new FileImageProvider(imageFile);
													if (!ObjectFunctions.isNullOrEmpty(studentLogo))
														context.put("logo", studentLogo);
												}
											}
											context.put("tRF", studentDetails);
											out = new FileOutputStream(new File(getSession().getServletContext().getRealPath(studentDOCXFilePath.toString())));
											report.process(context, out);
										} catch (Exception ex) {
											ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
										} finally {
											out.close();
										}
										studentDOCXFilePath = null;
										viewStudentPersonAccountDetails = null;
									}
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					// For generating zip file
					directory = new File(getSession().getServletContext().getRealPath(generatedTsFilePath.toString()));
					StringFunctions.zipFiles(directory, zipOutStream);
					FileUtils.deleteDirectory(directory);
					FileUtils.deleteDirectory(outFile);
					zipOutStream = null;
					customer = null;
					trTemplateFilePath = null;
					generatedTsFilePath = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				studentsListQuery = null;
				tcSettingsObj = null;
				tcSettingsMap = null;
				directory = null;
				query = null;
				templateFile = null;
				report = null;
				context = null;
				studentDOCXFilePath = null;
				vwStudentList = null;
			}
			return null;
		}
	  @Actions({ @Action(value = "ajaxDownloadTransportReuestTemplate", results = {}) })
		public String ajaxDownloadTransportReuestTemplate() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDownloadTransportReuestTemplate' method");
			}
			try {
				//File templateFile = null;
				//StringBuffer htCustomerPath = null;
				TcSettings tcSettings = null;
				if (getTempId() != 0) {
					tcSettings = (TcSettings)adminManager.get(TcSettings.class, getTempId());
					//Customer customer = getCustomerByCustId();
					if(!ObjectFunctions.isNullOrEmpty(tcSettings) && !ObjectFunctions.isNullOrEmpty(tcSettings.getFileName()))
					{
						URL url = new URL(tcSettings.getFilePath());
						URLConnection conn = url.openConnection();
						InputStream is = conn.getInputStream();
						
						//String ext = FilenameUtils.getExtension(tcSettings.getFileName());
						//File file = File.createTempFile(htSetting.getFileName(), "",directory);
						File templateFile = new File(tcSettings.getFileName());
						
						/*directory = File.createTempFile(htSetting.getFileName(), null);
						directory.deleteOnExit();*/
						FileUtils.copyInputStreamToFile(is, templateFile);
						
						
						//htCustomerPath = new StringBuffer("userFiles/transportRequestForm/").append(getUserCustId());
						//templateFile = new File(tcSettings.getFilePath());
						if (!StringFunctions.isNullOrEmpty(templateFile.toString())) {
							getResponse().setContentType("application/octet-stream");
							getResponse().addHeader("Content-Disposition","attachment; filename="+ tcSettings.getFileName().replaceAll(" ", "_"));
							ServletOutputStream out = getResponse().getOutputStream();
							DataInputStream in = new DataInputStream(new FileInputStream(templateFile));
							byte[] bbuf = new byte[1024];
							int length = 0;
							while ((in != null) && ((length = in.read(bbuf)) != -1)) {
								out.write(bbuf, 0, length);
							}
							in.close();
							out.flush();
							out.close();
						}
						tcSettings = null;
						//customer = null;
						templateFile = null;
						//htCustomerPath = null;
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				RayGunException raygex = new RayGunException();
				raygex.sendRayGunException(ex);
				raygex = null;
			}
			return null;
		}
	  
	  @Action(value = "ajaxGetDriversByVehicleRoute", results = { @Result(location = "transport/ajaxDriversByRouteIdVehicleId.jsp", name = "success") })
		public String ajaxGetDriversByVehicleRoute() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetDriversByVehicleRoute' method");
			}
			try {
				
				/*SELECT id,`academicYearId`,`custId`,`routePointStartTime`,`routePointEndTime` FROM `route` 
				WHERE (TIME(STR_TO_DATE(routePointStartTime, '%h:%i%p')) between TIME(STR_TO_DATE('08:00AM', '%h:%i%p')) AND TIME(STR_TO_DATE('09:30AM', '%h:%i%p'))) OR
				(TIME(STR_TO_DATE(routePointEndTime, '%h:%i%p')) between TIME(STR_TO_DATE('08:00AM', '%h:%i%p')) AND TIME(STR_TO_DATE('09:30AM', '%h:%i%p'))) and `custId` = 3*/
				
				/*SELECT id,`academicYearId`,`custId`,`routePointStartTime`,`routePointEndTime` FROM `route` 
				WHERE (STR_TO_DATE(routePointStartTime, '%h:%i%p') between STR_TO_DATE('08:00AM', '%h:%i%p') AND STR_TO_DATE('09:30AM', '%h:%i%p')) OR
				(STR_TO_DATE(routePointEndTime, '%h:%i%p') between STR_TO_DATE('08:00AM', '%h:%i%p') AND STR_TO_DATE('09:30AM', '%h:%i%p')) and `custId` = 3*/
				
				
				if(StringFunctions.isNotNullOrEmpty(getAnyId()))
				{
					Route route = (Route) adminManager.get(Route.class,Long.valueOf(getAnyId()));
					if(!ObjectFunctions.isNullOrEmpty(route))
					{
						ajaxDriverAndHelpersList(route.getRoutePointStartTime(), route.getRoutePointEndTime(),getTempId2(),route.getId());
					}
					route = null;
				}
				
				
			} catch (Exception ex) {
				log.error("Entering into 'catch block':" + ex.getMessage());
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
		
			return SUCCESS;
		}
	  
	  public void ajaxDriverAndHelpersList(String routePointStartTime, String routePointEndTime,long vehicleId,long routeId)
	  {
		  try {
			  List<Long> vehicleIds = null;
				
				List<VehiclesAcademicDetails> vehiclesAdminDetailsList = null;
				String query = "SELECT id FROM `route`  WHERE ((STR_TO_DATE(routePointStartTime, '%h:%i%p') between STR_TO_DATE('"+routePointStartTime+"', '%h:%i%p') AND STR_TO_DATE('"+routePointEndTime+"', '%h:%i%p')) OR (STR_TO_DATE(routePointEndTime, '%h:%i%p') between STR_TO_DATE('"+routePointStartTime+"', '%h:%i%p') AND STR_TO_DATE('"+routePointEndTime+"', '%h:%i%p'))) and  academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId() + " and id!=" + routeId;
				
				/*StringBuffer stringBuffer =new StringBuffer("select group_concat(CONVERT(id,CHAR)),routeName from route WHERE ((routeEndTimeInmns between ").append(route.getRouteStartTimeinMns())
				.append(" and ").append(route.getRouteEndTimeinMns()).append(" or routeStartTimeInmns between ").append(route.getRouteStartTimeinMns()).append(" and ").append( route.getRouteEndTimeinMns())
				.append(") or (routeStartTimeInMns is NULL AND routeEndTimeInMns is Null)) and custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId());*/
				
				log.debug(query); 
				List<Long> routeIds = transportManager.getAll(query);
				if(!ObjectFunctions.isNullOrEmpty(routeIds))
				{
					vehicleIds = transportManager.getAll("select vehicleAcademicId from RouteWithVehicles WHERE routeId not in ("+StringFunctions.convertListToCommaDelimitedString(routeIds)+") group by vehicleAcademicId");
				}
				if(!ObjectFunctions.isNullOrEmpty(vehicleIds)){
					
					vehiclesAdminDetailsList = adminManager.getAll(VehiclesAcademicDetails.class,"id in ("+StringFunctions.convertListToCommaDelimitedString(vehicleIds)+")  and academicYearId= "+getUserAcademicYearId() + " and vehicleId!=" + vehicleId);
				}
				else
				{
					vehiclesAdminDetailsList = adminManager.getAll(VehiclesAcademicDetails.class," academicYearId="+getUserAcademicYearId() + " and vehicleId!=" + vehicleId);
				}
				
				StringBuffer driverAccountIds = new StringBuffer();
				StringBuffer helperrAccountIds = new StringBuffer();
				
				if(!ObjectFunctions.isNullOrEmpty(vehiclesAdminDetailsList))
				{
					for(VehiclesAcademicDetails VehiclesAcademicDetails : vehiclesAdminDetailsList)
					{
						driverAccountIds.append(VehiclesAcademicDetails.getDriverId()+",");
						helperrAccountIds.append(VehiclesAcademicDetails.getHelperId()+",");
					}
				}
				
				StringBuffer sqlquery = new StringBuffer();
				sqlquery.append("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and status='Y'");
				
				List<ViewStaffPersonAccountDetails> driverList =adminManager.getAll(ViewStaffPersonAccountDetails.class, sqlquery.toString()+" and roleName='ROLE_DRIVER' and accountId not in ("+driverAccountIds.toString()+"0) order by roleName");
				if(ObjectFunctions.isNotNullOrEmpty(driverList)){
					for (ViewStaffPersonAccountDetails drivers  : driverList )
					{	  
						getSelectboxMap().put(drivers.getAccountId(), drivers.getFullName());
						drivers=null;
					}
				}
				List<ViewStaffPersonAccountDetails> helperList =adminManager.getAll(ViewStaffPersonAccountDetails.class, sqlquery.toString()+" and roleName='ROLE_HELPER' and accountId not in ("+helperrAccountIds.toString()+"0) order by roleName");
				if(ObjectFunctions.isNotNullOrEmpty(helperList)){
					for (ViewStaffPersonAccountDetails helper  : helperList )
					{	
						getJsonResult().put(helper.getAccountId(), helper.getFullName());
						helper=null;
					}
				}
				
				
				vehicleIds = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	  
	  @Actions({
			@Action(value = "ajaxGetDriversAndHelpersList", results = { @Result(location = "transport/ajaxAddRoute.jsp", name = "success") }) })
			public String ajaxGetDriversAndHelpersList() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetDriversAndHelpersList' method");
			}
			try
			{
				ajaxDriverAndHelpersList(getRoute().getRoutePointStartTime(), getRoute().getRoutePointEndTime(),getTempId2(),0);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			
			return SUCCESS;
		}
	  
	  @Actions({
			@Action(value = "ajaxGetVehiclesByRouteStartTimeAndEndTime", results = { @Result(location = "transport/ajaxVehiclesByRouteStartAndEndTime.jsp", name = "success") }) })
			public String ajaxGetVehiclesByRouteStartTimeAndEndTime() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetVehiclesByRouteStartTimeAndEndTime' method");
			}
			try
			{
				ajaxGetVehiclesByRouteStartTimeAndEndTime(getRoute().getRoutePointStartTime(),getRoute().getRoutePointEndTime());
				
				if(getTempId()>0)
				{
					List<ViewVehicleWithDriverDetails> viewVehicleWithDriverDetailsList = transportManager.getAllViewVehicleWithDriverDetails("academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId()+" and routeId="+getTempId());
					if(!ObjectFunctions.isNullOrEmpty(viewVehicleWithDriverDetailsList))
					{
						ViewVehicleWithDriverDetails viewVehicleWithDriverDetails =(ViewVehicleWithDriverDetails)viewVehicleWithDriverDetailsList.get(0);
						setEventId(String.valueOf(viewVehicleWithDriverDetails.getVehicleAcademicId()));
						
						VehiclesAcademicDetails vehiclesAcademicDetails = new VehiclesAcademicDetails();
						vehiclesAcademicDetails.setId(viewVehicleWithDriverDetails.getVehicleAcademicId());
						vehiclesAcademicDetails.setName(viewVehicleWithDriverDetails.getName());
						
						getTempList1().add(vehiclesAcademicDetails);
						vehiclesAcademicDetails = null;
					}
				}
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			
			return SUCCESS;
		}
	  
	  public void ajaxGetVehiclesByRouteStartTimeAndEndTime(String routeStartTime,String routeEndTime)
	  {
		  	try {
				Object[] vehicleIds = null;
				String query = "SELECT id FROM `route`  WHERE ((STR_TO_DATE(routePointStartTime, '%h:%i%p') between STR_TO_DATE('"+routeStartTime+"', '%h:%i%p') AND STR_TO_DATE('"+routeEndTime+"', '%h:%i%p')) OR (STR_TO_DATE(routePointEndTime, '%h:%i%p') between STR_TO_DATE('"+routeStartTime+"', '%h:%i%p') AND STR_TO_DATE('"+routeEndTime+"', '%h:%i%p'))) and  academicYearId="+getUserAcademicYearId()+" and custId="+getUserCustId();
				List<Long> routeIds = transportManager.getAll(query);
				if(!ObjectFunctions.isNullOrEmpty(routeIds))
				{
					vehicleIds = transportManager.get("select group_concat(CONVERT(vehicleAcademicId,CHAR)),routeId from RouteWithVehicles WHERE routeId in ("+StringFunctions.convertListToCommaDelimitedString(routeIds)+")");
				}
				if(!ObjectFunctions.isNullOrEmpty(vehicleIds) && !ObjectFunctions.isNullOrEmpty(vehicleIds[0])){
					setTempList1(adminManager.getAll(VehiclesAcademicDetails.class,"id not in("+vehicleIds[0].toString()+" )  and academicYearId="+getUserAcademicYearId()));
				}else{
					setTempList1(adminManager.getAll(VehiclesAcademicDetails.class," academicYearId="+getUserAcademicYearId()));
				}
				vehicleIds = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }
	  
	  
	 /* @Actions({
			@Action(value = "ajaxGetDriverDetatilsByVehicleId", results = { @Result(type = "json", name = "success",params = {"includeProperties","thresholdMonths,classTeacherStatus"}) }) })*/
	  @Actions({
			@Action(value = "ajaxGetDriverDetatilsByVehicleId", results = { @Result(location = "transport/ajaxViewDriversInfo.jsp", name = "success") }) })
			public String ajaxGetDriverDetatilsByVehicleId() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetVehiclesByRouteStartTimeAndEndTime' method");
			}
			try
			{
				StringBuffer trmpString=new StringBuffer();
				if(getTempId2() > 0)
				{
					VehiclesAcademicDetails vehiclesAcademicDetails = (VehiclesAcademicDetails) adminManager.get(VehiclesAcademicDetails.class, "id="+getTempId2());
					if(!ObjectFunctions.isNullOrEmpty(vehiclesAcademicDetails))
					{
						if(vehiclesAcademicDetails.getDriverId() > 0)
						{
							ViewStaffPersonAccountDetails driverAccount = (ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class, " accountId="+vehiclesAcademicDetails.getDriverId());
							if(!ObjectFunctions.isNullOrEmpty(driverAccount))
							{
								trmpString.append("<div id='routeDriverDivId'><strong>Driver Name:</strong>" + driverAccount.getFullName());
								if(vehiclesAcademicDetails.getDriverId() > 0)
								{
									ViewStaffPersonAccountDetails helperAccount = (ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class, " accountId="+vehiclesAcademicDetails.getHelperId());
									if(!ObjectFunctions.isNullOrEmpty(helperAccount))
									{
										trmpString.append("<br/><strong>Helper Name:</strong>" + helperAccount.getFullName());
									}
								}
								trmpString.append("</div>");
							}
						}
						else
						{
							ajaxDriverAndHelpersList(getRoute().getRoutePointStartTime(), getRoute().getRoutePointEndTime(),getTempId2(),0);
							if(ObjectFunctions.isNullOrEmpty(getSelectboxMap()))
								trmpString.append("No Drivers are found, Please click <a data-toggle='modal' href='#popupStudPaymentDiv' onclick='javascript:popupViewFeePayments();'>Add Driver</a> link to add the driver");
							else
								return SUCCESS;
						}
						
					}
					
				}
				/*JSONObject driverInfoObj =new JSONObject();
				driverInfoObj.put("afterNoonPeriodsCount", "<label class='control-label col-md-8'>"+trmpString+"</label>");
				getResponse().getOutputStream().print(driverInfoObj.toString());*/
				setTempString("<label class='control-label col-md-8'>"+trmpString+"</label>");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			
			return SUCCESS;
			//return null;
		}
	  
	  
	  @Actions({
			@Action(value = "ajaxGetDriverDetatilsByVRoteId", results = { @Result(location = "transport/ajaxViewDriversInfo.jsp", name = "success") }) })
			public String ajaxGetDriverDetatilsByVRoteId() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetDriverDetatilsByVRoteId' method");
			}
			try
			{
				StringBuffer trmpString=new StringBuffer();
				Route route = (Route) adminManager.get(Route.class,Long.valueOf(getAnyId()));
				if(!ObjectFunctions.isNullOrEmpty(route))
				{
					ajaxDriverAndHelpersList(route.getRoutePointStartTime(), route.getRoutePointEndTime(),getTempId2(),route.getId());
					
					if(!ObjectFunctions.isNullOrEmpty(getVehicleAcademicDetails())) 
					{
						if(!ObjectFunctions.isNullOrEmpty(getVehicleAcademicDetails().getId() > 0)) 
						{
							VehiclesAcademicDetails vehicle = (VehiclesAcademicDetails) adminManager.get(VehiclesAcademicDetails.class,getVehicleAcademicDetails().getId());
							if(!ObjectFunctions.isNullOrEmpty(vehicle)) 
							{
								setVehicleAcademicDetails(vehicle);
								StringBuffer sqlquery = new StringBuffer();
								sqlquery.append("custId="+getUserCustId()).append(" and academicYearId="+getUserAcademicYearId()).append(" and status='Y'");
								
								ViewStaffPersonAccountDetails drivers =(ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class, sqlquery.toString()+" and accountId="+getVehicleAcademicDetails().getDriverId());
								if(!ObjectFunctions.isNullOrEmpty(drivers)){
									getSelectboxMap().put(drivers.getAccountId(), drivers.getFullName());
									drivers=null;
								}
								
								ViewStaffPersonAccountDetails helper =(ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class, sqlquery.toString()+" and accountId="+getVehicleAcademicDetails().getHelperId());
								if(!ObjectFunctions.isNullOrEmpty(helper)){
									getJsonResult().put(helper.getAccountId(), helper.getFullName());
									helper=null;
								}
							}
						}
					}
					if(ObjectFunctions.isNullOrEmpty(getSelectboxMap()))
						trmpString.append("No Drivers are found, Please click <a data-toggle='modal' href='#popupStudPaymentDiv' onclick='javascript:popupViewFeePayments();'>Add Driver</a> link to add the driver");
					else
						return SUCCESS;
				}
				setTempString("<label class='control-label col-md-8'>"+trmpString+"</label>");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			
			return SUCCESS;
		}
	  
	  @Actions( {
			 @Action(value = "ajaxViewAddVehicleDriverOrHelper", results = {@Result(location = "transport/ajaxViewAddDriverAndHelper.jsp", name = "success")})
		})
			public String ajaxViewAddVehicleDriverOrHelper() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewAddVehicleDriverOrHelper' method");
			}
			try
			{
				setRoute((Route)adminManager.get(Route.class,Long.valueOf(getAnyId())));
				ajaxDoAddVehicleDriverOrHelper();
				setTempString2(getTempString2());
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	  
	  
	  @Action(value = "ajaxGetStudentsCountByRoute", results = { @Result(type = "json", name = "success",params = {"includeProperties","anyId"}) }) 
		public String ajaxGetStudentsCountByRoute() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxGetStudentsCountByRoute' method");
			}
			try {
				if(getRoute().getId() > 0)
				{
					int count=0;
					Route route = (Route)adminManager.get(Route.class,"id="+getRoute().getId());
					if(!ObjectFunctions.isNullOrEmpty(route))
					{
						long studentsCount=adminManager.getCount("studentTransportDetails","pickupBoardingPointId in "+route.getRouteBoardingPointIds()+" or dropBoardingPointId in "+route.getRouteBoardingPointIds());
						JSONObject studentActivityTypesJson =new JSONObject().put("studActivities", studentsCount);
						getResponse().getOutputStream().print(studentActivityTypesJson.toString());
					}
					route = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
	  
	@Action(value = "ajaxDoAssignStudentForm", results = { @Result(location = "transport/ajaxDoGetTransportWiseClassSectionDetails.jsp", name = "success") })
	public String ajaxDoAssignStudentForm() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoAssignStudentForm' method");
		}
		try {
			setClassSectionId(null);
			setStudyClassList(transportManager.getAll(StudyClass.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
			setSchoolTermsList(transportManager.getAll(SchoolTerms.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and feeSettingId=3"));
			setObjectList(transportManager.getAll(ViewAssignedVehiclestoRoutesWithBoardingPoints.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	@Action(value = "ajaxSearchTransportModeWiseStudentDetails", results = { @Result(location = "transport/ajaxClassSectionTransportStudentDetails.jsp", name = "success") })
	public String ajaxSearchTransportModeWiseStudentDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSearchTransportModeWiseStudentDetails' method");
		}
		try {
			String transportType = getParamValue("transportType");
			if(!StringFunctions.isNullOrEmpty(getClassSectionId()) &&Long.valueOf(getClassSectionId())>0 && !StringFunctions.isNullOrEmpty("transportType")){
				List<Long> transportStudIds=null;
				setSchoolTermsList(transportManager.getAll(SchoolTerms.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and feeSettingId=3 order by fromDate ASC"));
				setObjectList(transportManager.getAll(ViewAssignedVehiclestoRoutesWithBoardingPoints.class, "custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()));
				StringBuilder query =new StringBuilder("custId=").append(getUserCustId()).append(" and academicYearId=").append(getUserAcademicYearId()).append(" and classSectionId=").append(getClassSectionId());
				List<Long> studnetIds = transportManager.getAll("select id from student where "+query.toString()+" and description is null");
				query.append(" and studDiscontinueDesc is null ");
				if(!ObjectFunctions.isNullOrEmpty(studnetIds)){
					transportStudIds = transportManager.getAll("select studentId from studentTransportDetails where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and studentId in ("+StringFunctions.convertListToCommaDelimitedString(studnetIds)+") group by studentId");
					if(!ObjectFunctions.isNullOrEmpty(transportStudIds)){
						query.append(" and studId ");
						if("T".equalsIgnoreCase(transportType))
							query.append(" in (").append(StringFunctions.convertListToCommaDelimitedString(transportStudIds));
						else
							query.append(" not in (").append(StringFunctions.convertListToCommaDelimitedString(transportStudIds));
						query.append(")");
					}else{
						query.append(" and studId ");
						if("T".equalsIgnoreCase(transportType))
							query.append(" in (").append(0);
						else
							query.append(" not in (").append(0);
						query.append(")");
					}
				}
				setStudentsList(transportManager.getAll(ViewStudentClassDetails.class, query.toString()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxEditStudentTransportDetails", results = { @Result(type = "json", name = "success", params = {"includeProperties", "classFeeList.*" }) }) })
	public String ajaxEditClassFeeDetails() throws URTUniversalException {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditStudentTransportDetails' method");
		}
		try {
			String transportType=getParamValue("transportType");
			if (!StringFunctions.isNullOrEmpty(getClassSectionId()) && Long.valueOf(getClassSectionId())>0 && !StringFunctions.isNullOrEmpty(transportType)) {
				JSONArray res = transportManager.getStudentTransportBoardingPointDetails(Long.valueOf(getClassSectionId()),transportType,getUserCustId(),getCurrentAcademicYear());
				if (!ObjectFunctions.isNullOrEmpty(res)) {
					JSONObject j = new JSONObject();
					j.put("data", res);
					getResponse().getOutputStream().print(j.toString());
				}
				transportType=null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}

	@Actions({ @Action(value = "ajaxAssignStudentBoardingDetails", results = { @Result(location = "transport/ajaxDoGetTransportWiseClassSectionDetails.jsp", name = "success") }) })
	public String ajaxAssignStudentBoardingDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAssignStudentBoardingDetails' method");
		}
		try {
			if (!StringFunctions.isNullOrEmpty(getStudentTransportData())) {
				long returnCode = transportManager.saveStudentTransportPickupAndDropBoardingPointDetailsTermWise(getUserCustId(), getCurrentAcademicYear(),Long.valueOf(getClassSectionId()),getParamValue("transportType"),getStudentTransportData(), getUser().getId());
				if(returnCode==1)
					super.addActionMessage("Student transport data created successfully.");
				else if(returnCode==2)
					super.addActionMessage("Pickup vehicle capacity is full, please assign student to different vehicle.");
				else if(returnCode==3)
					super.addActionMessage("Drop vehicle capacity is full, please assign student to different vehicle.");
				else if(returnCode ==0)
					super.addActionError("Error occured please contact administrator.");

			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}finally{
			ajaxDoAssignStudentForm();
		}
		return SUCCESS;
	}
}