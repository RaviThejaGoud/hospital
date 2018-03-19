package com.urt.service.manager.interfaces.transport;

import java.util.List;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.hyniva.sms.ws.vo.transport.StudentTransportDetailsMainVO;
import com.urt.json.JSONArray;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.customer.Fee;
import com.urt.persistence.model.study.Staff;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.transport.Route;
import com.urt.persistence.model.transport.RouteBoardingPoints;
import com.urt.persistence.model.transport.Vehicles;
import com.urt.persistence.model.transport.VehiclesAcademicDetails;
import com.urt.persistence.model.transport.ViewAssignedVehiclestoRoutes;
import com.urt.persistence.model.transport.ViewStaffVehicleTripdetails;
import com.urt.persistence.model.transport.ViewVehicleWithDriverDetails;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.base.UniversalManager;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="SubscriptionManager.java.html"><i>View Source</i></a></p>
 */
public interface TransportManager extends UniversalManager {    
	
	List<Vehicles> getAllVehiclesByVehicleCustIdIdAndType(String vehicleType,long custId);
	
	List<Vehicles> getAllVehiclesByVehicleType(String vehicleType);
	
	Route saveRoute(Route route);
	List<Route> checkRouteNumber(long routeNumber);
	/*List<ViewStaffStudentVehicleTripDetails> getAllRouteWithCustomerId(long customerId);*/
	
	List<Vehicles> getAllVehicleByCustomerId(long customerId);
	User saveUser(User user);
	
	List<Staff> getAllStaffByCustomerId(long customerId);
	
	List getAllStaffByAccounIdAndRoleName(long accountId,String roleId);
	void removeStaffByStaffIdAndAccountId(long staffId,long accountId);
	
	List<ViewStaffPersonAccountDetails> getAllDriverOrHelperByCustIdIdAndType(String roleName,long custId);
	
	
    List<ViewStaffVehicleTripdetails> getStudentDetailsByVehicleTripId(String tripId);
    /*List getViewStaffVehicleTripdetailsByCustId(long custId);*/
	
    List<ViewStaffVehicleTripdetails> updateStaffByStaffIdAndAccountId(long staffId,long accountId);
	
    List<Vehicles> getVehiclesByTypeAndVehicleNumber(String vehicleType,String vehicleNumber);
   /* List getAllVehicleTripsByAccountId(long accountId);
    List<VehicleTrip> getVehicleTripdetailsByCustId(long custId);
    VehicleBoardingPoints getBoardingPointsByVehicleTripId(long vehicleTripId);
	VehicleTrip saveVehicleTrip(VehicleTrip vehicleTrip);
	List<ViewStudentPersonAccountDetails> getAllStudentsByRollNumber(String rollNumber,long custId);	
	List<VehicleTrip> getAllVehicleTripByRouteNumber(String routeNumber);
	List<VehicleTrip> getAllBoardingPointsByTripNumber(String tripId);
	void removeVehicleTripByVehiclId(long vehicleId); 
	List<VehicleBoardingPoints> getBoardingPointsByVehicleTripId(String vehicleTripId);
	List getVehicleTripsByvehicleId(long vehicleId);
	List getStudentDetailsByVehicleTripId(String tripId);
	VehicleTrip getVehicleTripById(long vehicleTripId);
    List getVehicleRouteByRouteId(long routeId);
    StudentPayment getPaymentStatusByStudentId(long studentId);
    */
	
    List<ViewStaffVehicleTripdetails> getStaffVehicleTripByDriverAccountId(long driverId,long custId);
	
    List<ViewStaffVehicleTripdetails> getStaffVehicleTripByHelperAccountId(long helperId,long custId);
	
    List<ViewStaffVehicleTripdetails> getStaffVehicleTripByRouteId(long routeId,long custId);
	
    Staff getStaffByAaccountId(long accountId);
    void removeUserRoleByUserId(long userId);
    
    List<Student> getStudentByVehicleTripId(String tripId);
    
    List<Student> getStudentByIdAndRollNumber(String studentId,String tripId);
    void removeTransportFeeByIdAndClassId(long classId,long feeId);
   /* List<ClassName> getAllClassesById(long classId,String status);*/
    
    List<Fee> getAllTransportFeeStausList(String status);
    
    ViewStaffPersonAccountDetails getPersonAccountDetailsByStudentId(long staffId);
    
    List<Route> getRouteDetailsByCustId(long custId,long academicYearId);
    void removeVehicleRouteByRouteIdAndCustId(long routeId,long custId); 
    
    List<RouteBoardingPoints> getBordingPointsByRouteIdAndCustId(long routeId,long custId);
    
    List<Vehicles> getRouteVehiclesByRouteIdAndCustId(long routeId,long custId);
    
    Vehicles saveVehicle(Vehicles vehicle);
    /*Vehicles getRemoveVehicleStudentById(long vehicleId);*/
    /*List<Student> getVehicleDetailesByVehicleIdAndStudentId(long studentId,long vehicleId);*/
    void removeBoardingPointsByBoardingIds(String scheduleIds,long routeId);
    void updateStudentsVehicleandBoardingPoints(long vehicleAcademicDetailId,long academicYearId,String boardingPointIds);
    
    List<ViewVehicleWithDriverDetails> getAllViewVehicleWithDriverDetails(String clause);
    
    List<ViewAssignedVehiclestoRoutes> getAllViewAssignedVehiclestoRoutes(String clause);
    
    List<VehiclesAcademicDetails> getAllVehiclesAssiginedToStudents(long academicYearId);
    
    List<Route> getAllRoutesAssiginedToVehicles(long academicYearId);
    void updateRouteBoardingPointStatus(long custId,long academicYearId,String boardingPointIds);
    
    JSONArray getStudentTransportBoardingPointDetails(long classSectionId,String transportType,long custId,AcademicYear academicYear);
    
    long saveStudentTransportPickupAndDropBoardingPointDetailsTermWise(long custId,AcademicYear academicYear,long classSectionId,String transportType,String studentTransportData,long userId);
    
    StudentTransportDetailsMainVO getStudentTransportAssigntDetails(long custId,long academicYearId,String webCreatedDate);
    
}
