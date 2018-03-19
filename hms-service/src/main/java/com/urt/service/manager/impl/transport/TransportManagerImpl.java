package com.urt.service.manager.impl.transport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.churchgroup.util.string.StringUtil;
import com.hyniva.sms.ws.vo.account.FinancialParticaularAssociationMainVO;
import com.hyniva.sms.ws.vo.transport.StudentTransportDetailsMainVO;
import com.hyniva.sms.ws.vo.transport.StudentTransportDetailsVo;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.interfaces.transport.TransportDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.customer.FeeType;
import com.urt.persistence.model.customer.SchoolTerms;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudentFeePaidDetails;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.transport.Route;
import com.urt.persistence.model.transport.RouteBoardingPoints;
import com.urt.persistence.model.transport.StudentTransportDetails;
import com.urt.persistence.model.transport.Vehicles;
import com.urt.persistence.model.transport.VehiclesAcademicDetails;
import com.urt.persistence.model.transport.ViewAssignedVehiclestoRoutes;
import com.urt.persistence.model.transport.ViewStaffVehicleTripdetails;
import com.urt.persistence.model.transport.ViewVehicleWithDriverDetails;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.transport.TransportManager;
import com.urt.util.common.RayGunException;

/**
 * Implementation of UserResourcesManager interface.
 * </p>
 * 
 * <p>
 * <a href="SubscriptionManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */
@Component
public class TransportManagerImpl extends UniversalManagerImpl implements TransportManager {

	@Autowired
	private TransportDao transportDao;

	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Vehicles> getAllVehiclesByVehicleCustIdIdAndType(String vehicleType,long custId)	 {
		return transportDao.getAllVehiclesByVehicleCustIdIdAndType(vehicleType,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Vehicles> getAllVehiclesByVehicleType(String vehicleType)	 {
		return transportDao.getAllVehiclesByVehicleType(vehicleType);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Route saveRoute(Route route) {
	        return transportDao.saveRoute(route);
	    }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Route> checkRouteNumber(long routeNumber)  {
		return transportDao.checkRouteNumber(routeNumber);
	}
	
	/*public List<ViewStaffStudentVehicleTripDetails> getAllRouteWithCustomerId(long customerId) {
		return transportDao.getAllRouteWithCustomerId(customerId);
	}*/
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Vehicles> getAllVehicleByCustomerId(long customerId) {
		return transportDao.getAllVehicleByCustomerId(customerId);
	}
	public User saveUser(User user){
		return transportDao.saveUser(user);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Staff> getAllStaffByCustomerId(long customerId) {
		return transportDao.getAllStaffByCustomerId(customerId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllStaffByAccounIdAndRoleName(long accountId,String roleName) {
		return transportDao.getAllStaffByAccounIdAndRoleName(accountId,roleName);
	}
	public void removeStaffByStaffIdAndAccountId(long staffId,long accountId){
		transportDao.removeStaffByStaffIdAndAccountId(staffId,accountId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffPersonAccountDetails> getAllDriverOrHelperByCustIdIdAndType(String roleName,long custId)	 {
		return transportDao.getAllDriverOrHelperByCustIdIdAndType(roleName,custId);
	}
	/*public VehicleBoardingPoints getBoardingPointsByVehicleTripId(long vehicleTripId){
		return transportDao.getBoardingPointsByVehicleTripId(vehicleTripId);
	}
	public VehicleTrip saveVehicleTrip(VehicleTrip vehicleTrip) {
        return transportDao.saveVehicleTrip(vehicleTrip);
    }
    public List<VehicleTrip> getAllVehicleTripByRouteNumber(String routeNumber){
		return transportDao.getAllVehicleTripByRouteNumber(routeNumber);
	}
	public List<VehicleTrip> getAllBoardingPointsByTripNumber(String tripId){
		return transportDao.getAllBoardingPointsByTripNumber(tripId);
	}
	
	public void removeVehicleTripByVehiclId(long vehicleId){
		transportDao.removeVehicleTripByVehiclId(vehicleId);
	}
	public List<VehicleBoardingPoints> getBoardingPointsByVehicleTripId(String vehicleTripId){
		return transportDao.getBoardingPointsByVehicleTripId(vehicleTripId);
	}
	public List<ViewStudentPersonAccountDetails> getAllStudentsByRollNumber(String rollNumber,long custId){
		return transportDao.getAllStudentsByRollNumber(rollNumber,custId);
	}
	public List getVehicleTripsByvehicleId(long vehicleId) {
		return transportDao.getVehicleTripsByvehicleId(vehicleId);
	}
	
	public List getAllVehicleTripsByAccountId(long accountId){
		return transportDao.getAllVehicleTripsByAccountId(accountId);
	}
	public List<VehicleTrip> getVehicleTripdetailsByCustId(long custId){
		return transportDao.getVehicleTripdetailsByCustId(custId);
	}
	public List getVehicleRouteByRouteId(long routeId) {
		return transportDao.getVehicleRouteByRouteId(routeId);
	}
	
	public List getStudentDetailsByVehicleTripId(String tripId){
			return transportDao.getStudentDetailsByVehicleTripId(tripId);
	}
	public VehicleTrip getVehicleTripById(long vehicleTripId){
		return transportDao.getVehicleTripById(vehicleTripId);
	}
	 public StudentPayment getPaymentStatusByStudentId(long studentId){
    	return transportDao.getPaymentStatusByStudentId(studentId);
    }
	public List getBordingPointsByvehicleTripId(long vehicleTripId) {
		return transportDao.getBordingPointsByvehicleTripId(vehicleTripId);
	}*/
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffVehicleTripdetails> getStudentDetailsByVehicleTripId(String tripId){
			return transportDao.getStudentDetailsByVehicleTripId(tripId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffVehicleTripdetails> updateStaffByStaffIdAndAccountId(long staffId,long accountId) {
        return transportDao.updateStaffByStaffIdAndAccountId(staffId,accountId);
    }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Vehicles> getVehiclesByTypeAndVehicleNumber(String vehicleType,String vehicleNumber){
		 return transportDao.getVehiclesByTypeAndVehicleNumber(vehicleType,vehicleNumber);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffVehicleTripdetails> getStaffVehicleTripByDriverAccountId(long driverId,long custId){
		return transportDao.getStaffVehicleTripByDriverAccountId(driverId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffVehicleTripdetails> getStaffVehicleTripByHelperAccountId(long helperId,long custId){
		return transportDao.getStaffVehicleTripByHelperAccountId(helperId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<ViewStaffVehicleTripdetails> getStaffVehicleTripByRouteId(long routeId,long custId){
		return transportDao.getStaffVehicleTripByRouteId(routeId,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public Staff getStaffByAaccountId(long accountId){
		return transportDao.getStaffByAaccountId(accountId);
	}
	public void removeUserRoleByUserId(long userId)	{
		transportDao.removeUserRoleByUserId(userId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Student> getStudentByVehicleTripId(String tripId){
		return transportDao.getStudentByVehicleTripId(tripId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<Student> getStudentByIdAndRollNumber(String studentId,String tripId){
		 return transportDao.getStudentByIdAndRollNumber(studentId,tripId);
	}
	public void removeTransportFeeByIdAndClassId(long classId,long feeId){
		transportDao.removeTransportFeeByIdAndClassId(classId,feeId);
	}
	/*public List getAllClassesById(long classId,String status) {
		return transportDao.getAllClassesById(classId,status);
	}*/
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllTransportFeeStausList(String status){
		 return transportDao.getAllTransportFeeStausList(status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )	
	 public List<Route> getRouteDetailsByCustId(long custId,long academicYearId){
		 return transportDao.getRouteDetailsByCustId(custId,academicYearId);
	 }
	 public void removeVehicleRouteByRouteIdAndCustId(long routeId,long custId) {
		transportDao.removeVehicleRouteByRouteIdAndCustId(routeId,custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<RouteBoardingPoints> getBordingPointsByRouteIdAndCustId(long routeId,long custId){
		 return transportDao.getBordingPointsByRouteIdAndCustId(routeId,custId);
		 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<Vehicles> getRouteVehiclesByRouteIdAndCustId(long routeId,long custId){
		 return transportDao.getRouteVehiclesByRouteIdAndCustId(routeId,custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public Vehicles saveVehicle(Vehicles vehicle) {
	        return transportDao.saveVehicle(vehicle);
	    }
	/* public Vehicles getRemoveVehicleStudentById(long vehicleId){
			return transportDao.getRemoveVehicleStudentById(vehicleId);
		}*/
	/* public List<Student> getVehicleDetailesByVehicleIdAndStudentId(long studentId,long vehicleId){
		 return transportDao.getVehicleDetailesByVehicleIdAndStudentId(studentId,vehicleId);
	}*/
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public ViewStaffPersonAccountDetails getPersonAccountDetailsByStudentId(long staffId){
		 return transportDao.getPersonAccountDetailsByStudentId(staffId);
	 }

	 /*public List getViewStaffVehicleTripdetailsByCustId(long custId){
			return transportDao.getViewStaffVehicleTripdetailsByCustId(custId);
		}*/
	 public void removeBoardingPointsByBoardingIds(String scheduleIds,long routeId){
		 transportDao.removeBoardingPointsByBoardingIds(scheduleIds,routeId);
	}
	 public void updateStudentsVehicleandBoardingPoints(long vehicleAcademicDetailId,long academicYearId,String boardingPointIds){
		 transportDao.updateStudentsVehicleandBoardingPoints(vehicleAcademicDetailId,academicYearId,boardingPointIds);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<ViewVehicleWithDriverDetails> getAllViewVehicleWithDriverDetails(String clause){
		 return transportDao.getAllViewVehicleWithDriverDetails(clause);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<ViewAssignedVehiclestoRoutes> getAllViewAssignedVehiclestoRoutes(String clause){
		 return transportDao.getAllViewAssignedVehiclestoRoutes(clause);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 @Transactional
	 public List<VehiclesAcademicDetails> getAllVehiclesAssiginedToStudents(long academicYearId){
		return transportDao.getAllVehiclesAssiginedToStudents(academicYearId);
	}
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 @Transactional
	 public List<Route> getAllRoutesAssiginedToVehicles(long academicYearId){
		 return transportDao.getAllRoutesAssiginedToVehicles(academicYearId);
	 }
	 
	 public void updateRouteBoardingPointStatus(long custId,long academicYearId,String boardingPointIds){
		 transportDao.updateRouteBoardingPointStatus(custId,academicYearId,boardingPointIds);
	 }

	public JSONArray getStudentTransportBoardingPointDetails(long classSectionId, String transportType,long custId,AcademicYear academicYear) {
		try {
			JSONArray res = new JSONArray();
			JSONObject j;
			StringBuilder query = new StringBuilder("custId=").append(custId).append(" and academicYearId=").append(academicYear.getId());
			StringBuilder studQuery = new StringBuilder("select id from student where "+query.toString()+" and classSectionId="+classSectionId+" and description is null");
			if("T".equalsIgnoreCase(transportType))
				studQuery.append(" and id in (select studentId from studentTransportDetails where ").append(query.toString());
			else
				studQuery.append(" and id not in (select studentId from studentTransportDetails where ").append(query.toString());
			studQuery.append(" group by studentId").append(")");
			List<Long> studentIds = this.getAll(studQuery.toString()); 
			if(!ObjectFunctions.isNullOrEmpty(studentIds)){
				query.append(" and studentId in (").append(StringFunctions.convertListToCommaDelimitedString(studentIds)).append(")").append(" order by studentId,lastUpdatedDate DESC");
				List<StudentTransportDetails> studentTransportDetails=this.getAll(StudentTransportDetails.class,query.toString());
				if (ObjectFunctions.isNotNullOrEmpty(studentTransportDetails)) {
					for (StudentTransportDetails studentTransport : studentTransportDetails) {
						List<StudentFeePaidDetails> studentPaymentList=this.getAll(StudentFeePaidDetails.class, " studTransportDetailsId ="+studentTransport.getId()+" and custId="+custId+" and deleteStatus='"+Constants.NO_STRING+"'");
						j = new JSONObject();
						j.put("studTransportId", studentTransport.getId());
						j.put("studId", studentTransport.getStudent().getId());
						j.put("pickUpBoardingAndVechileIds", studentTransport.getPickupDropingAndVechileId());
						j.put("dropBoardingAndVechileIds", studentTransport.getDropDropingAndVechileId());
						j.put("termId", studentTransport.getSchoolTerm().getId());
						j.put("status", studentTransport.getBoardingType());
						j.put("studentPaymentId", studentPaymentList.size());
						res.put(j);
						studentPaymentList=null;
					}
					
					/*j = new JSONObject();
					j.put("data", res);*/
					return res;
					// getResponse().setHeader("X-JSON", res.toString());
					//getResponse().getOutputStream().print(j.toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}

	public long saveStudentTransportPickupAndDropBoardingPointDetailsTermWise(long custId, AcademicYear academicYear, long classSectionId,String transportType, String studentTransportData, long userId) {
		try {

			JSONObject studentTransportFormtData = new JSONObject(studentTransportData);
			JSONArray studTranSectionWiseJsonArray = (JSONArray) studentTransportFormtData.get("data");
			long schoolTermId = 0;
			long studentId = 0;
			String pickUpBoardingPontAndVechileIds = null;
			String dropBoardingPontAndVechileIds = null;
			String studTransportType = null;
			JSONObject studentTransportJson = null;
			Student student = null;
			StudentTransportDetails studentTransportDetails = null;
			Map<Long, RouteBoardingPoints> routeBoardingPointsMap = new HashMap<Long, RouteBoardingPoints>();
			Map<Long, SchoolTerms> schoolTermsMap = new HashMap<Long, SchoolTerms>();
			Map<Long, VehiclesAcademicDetails> vehiclesAcademicDetailsMap = new HashMap<Long, VehiclesAcademicDetails>();
			List<RouteBoardingPoints> routeBoardingPointsList = this.getAll(RouteBoardingPoints.class, "custId=" + custId+ " and academicYearId=" + academicYear.getId());
			if (!ObjectFunctions.isNullOrEmpty(routeBoardingPointsList)) {
				for (RouteBoardingPoints boardingPoints : routeBoardingPointsList) {
					routeBoardingPointsMap.put(boardingPoints.getId(),boardingPoints);
					boardingPoints = null;
				}
				routeBoardingPointsList = null;
			}
			List<SchoolTerms> schooTermsList = this.getAll(SchoolTerms.class,"custId=" + custId + " and academicYearId="+ academicYear.getId() + " and feeSettingId=3");
			if (!ObjectFunctions.isNullOrEmpty(schooTermsList)) {
				for (SchoolTerms terms : schooTermsList) {
					schoolTermsMap.put(terms.getId(), terms);
					terms = null;
				}
				schooTermsList = null;
			}
			List<VehiclesAcademicDetails> vehiclesAcademicDetails = this.getAll(VehiclesAcademicDetails.class, "academicYearId="+ academicYear.getId());
			if (!ObjectFunctions.isNullOrEmpty(vehiclesAcademicDetails)) {
				for (VehiclesAcademicDetails vehiclesAcademic : vehiclesAcademicDetails) {
					vehiclesAcademicDetailsMap.put(vehiclesAcademic.getVehicle().getId(), vehiclesAcademic);
					vehiclesAcademic = null;
				}
				vehiclesAcademicDetails = null;
			}
			long studId = 0;
			FeeType feeType = (FeeType)this.get(FeeType.class, "custId="+custId+" and academicYearId="+academicYear.getId()+" and feeType='"+Constants.TRANSPORT_FEES+"' and feeSettingId=3");
			if(ObjectFunctions.isNullOrEmpty(feeType)){
				feeType = new FeeType();
				feeType.setCustId(custId);
				feeType.setAcademicYear(academicYear);
				feeType.setFeeType(Constants.TRANSPORT_FEES);
				feeType.setFeeSettingId(3);
				feeType.setStatus(Constants.TRANSPORT_STATUS);
				feeType = (FeeType)this.saveOrUpdateObject(feeType);
			}
			for (int i = 0; i < studTranSectionWiseJsonArray.length(); i++) {
				studentTransportJson = studTranSectionWiseJsonArray.getJSONObject(i);
				if (!ObjectFunctions.isNullOrEmpty(studentTransportJson)) {
					if (!ObjectFunctions.isNullOrEmpty(studentTransportJson.get("studentId"))) {
						studentId = Long.valueOf((String) studentTransportJson.get("studentId"));
					}
					if (!ObjectFunctions.isNullOrEmpty(studentTransportJson.get("pickUpPointId"))) {
						pickUpBoardingPontAndVechileIds = (String) studentTransportJson.get("pickUpPointId");
					}
					if (!ObjectFunctions.isNullOrEmpty(studentTransportJson.get("termId"))) {
						schoolTermId = Long.valueOf((String) studentTransportJson.get("termId"));
					}
					if (!ObjectFunctions.isNullOrEmpty(studentTransportJson.get("dropPointId"))) {
						dropBoardingPontAndVechileIds = (String) studentTransportJson.get("dropPointId");
					}
					if (!ObjectFunctions.isNullOrEmpty(studentTransportJson.get("status"))) {
						studTransportType = (String) studentTransportJson.get("status");
					}
					if (studentId != studId) {
						student=null;
						student = (Student) this.get(Student.class, studentId);
						if(Constants.NO_STRING.equalsIgnoreCase(student.getFeeConfigured())){
							student.setFeeConfigured(Constants.YES_STRING);
							student =(Student)this.saveOrUpdateObject(student);
						}
					}

					studentTransportDetails = (StudentTransportDetails) this.get(StudentTransportDetails.class, "custId="+ custId + " and academicYearId="+ academicYear.getId() + " and studentId="+student.getId()+" and termId="+ schoolTermId);
					if (ObjectFunctions.isNullOrEmpty(studentTransportDetails)) {
						studentTransportDetails = new StudentTransportDetails();
						studentTransportDetails.setCreatedDate(new Date());
						studentTransportDetails.setCreatedById(userId);
					}
					if(StringFunctions.isNullOrEmpty(pickUpBoardingPontAndVechileIds) && StringFunctions.isNullOrEmpty(dropBoardingPontAndVechileIds)){
						if(studentTransportDetails.getId()>0){
							this.remove(StudentTransportDetails.class, studentTransportDetails.getId());
						}
						int count = this.getCount("studentTransportDetails", "custId="+student.getCustId()+" and academicYearId="+student.getAcademicYearId()+" and studentId="+student.getId());
						if(count==0){
							Fee fee = (Fee) this.get(Fee.class,"classId="+ student.getClassNameId()+ " and categoryId="+ student.getCategoryId()+ " and feeAmount != 0 ");
							if(ObjectFunctions.isNullOrEmpty(fee)){
								student.setFeeConfigured(Constants.NO_STRING);
								student =(Student)this.saveOrUpdateObject(student);
							}
						}
						continue;
					}
					studentTransportDetails.setLastUpdatedDate(new Date());
					studentTransportDetails.setLastUpdatedById(userId);
					studentTransportDetails.setCustId(custId);
					studentTransportDetails.setAcademicYear(academicYear);
					studentTransportDetails.setStudent(student);
					studentTransportDetails.setSchoolTerm(schoolTermsMap.get(schoolTermId));
					if(!StringFunctions.isNullOrEmpty(pickUpBoardingPontAndVechileIds)){
						studentTransportDetails.setPickupBoardingPoint(routeBoardingPointsMap.get(Long.valueOf(pickUpBoardingPontAndVechileIds.split("-")[0].toString())));
						studentTransportDetails.setPickupVehicle(vehiclesAcademicDetailsMap.get(Long.valueOf(pickUpBoardingPontAndVechileIds.split("-")[2].toString())));
					}else{
						studentTransportDetails.setPickupBoardingPoint(null);
						studentTransportDetails.setPickupVehicle(null);
					}
					if(!StringFunctions.isNullOrEmpty(dropBoardingPontAndVechileIds)){
						studentTransportDetails.setDropBoardingPoint(routeBoardingPointsMap.get(Long.valueOf(dropBoardingPontAndVechileIds.split("-")[0].toString())));
						studentTransportDetails.setDropVehicle(vehiclesAcademicDetailsMap.get(Long.valueOf(dropBoardingPontAndVechileIds.split("-")[2].toString())));
					}else{
						studentTransportDetails.setDropBoardingPoint(null);
						studentTransportDetails.setDropVehicle(null);
					}
					
					studentTransportDetails.setBoardingType(studTransportType);
					studentTransportDetails.setFeeType(feeType);
					this.save(studentTransportDetails);
					
				}
				studId = studentId;
				studentTransportDetails = null;
				studentId=0;
				pickUpBoardingPontAndVechileIds=null;
				schoolTermId=0;
				dropBoardingPontAndVechileIds=null;
				studTransportType=null;
			}
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
			return 0;
		}
	}
	
	public StudentTransportDetailsMainVO getStudentTransportAssigntDetails(long custId,long academicYearId,String createdDate){
		try {
			StringBuilder query = new StringBuilder("custId=").append(custId).append(" and academicYearId=").append(academicYearId);
			if(!StringFunctions.isNullOrEmpty(createdDate))
				query.append(" and createdDate >='"+createdDate+"' ");
			List<StudentTransportDetails> studentTransportDetailsList = this.getAll(StudentTransportDetails.class, query.toString());
			if(!ObjectFunctions.isNullOrEmpty(studentTransportDetailsList)){
				StudentTransportDetailsMainVO studentTransportDetails = new StudentTransportDetailsMainVO();
				for(StudentTransportDetails studTranDetails: studentTransportDetailsList){
					StudentTransportDetailsVo studTransportDetails = new StudentTransportDetailsVo();
					studTransportDetails.setId(studTranDetails.getId());
					studTransportDetails.setStudentId(studTranDetails.getStudent().getId());
					studTransportDetails.setSchoolTermId(studTranDetails.getSchoolTerm().getId());
					studTransportDetails.setFeeTypeId(ObjectFunctions.isNullOrEmpty(studTranDetails.getFeeType())?0:studTranDetails.getFeeType().getId());
					if(!ObjectFunctions.isNullOrEmpty(studTranDetails.getPickupBoardingPoint()))
						studTransportDetails.setPickupBoardingPointId(studTranDetails.getPickupBoardingPoint().getId());
					if(!ObjectFunctions.isNullOrEmpty(studTranDetails.getPickupVehicle()))
						studTransportDetails.setPickupVehicleId(studTranDetails.getPickupVehicle().getId());
					if(!ObjectFunctions.isNullOrEmpty(studTranDetails.getDropBoardingPoint()))
						studTransportDetails.setDropBoardingPointId(studTranDetails.getDropBoardingPoint().getId());
					if(!ObjectFunctions.isNullOrEmpty(studTranDetails.getDropVehicle()))
						studTransportDetails.setDropVehicleId(studTranDetails.getDropVehicle().getId());
					studTransportDetails.setBoardingType(studTranDetails.getBoardingType());
					studentTransportDetails.getStudentTransportDetails().add(studTransportDetails);
				}
				return studentTransportDetails;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex = null;
		}
		return null;
	}
}
