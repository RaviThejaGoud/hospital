package com.urt.persistence.impl.transport;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.exception.base.URTDataAccessException;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.transport.TransportDao;
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

@Transactional
public class TransportDaoHibernate extends UniversalHibernateDao implements	TransportDao {
	
	private static final Log log = LogFactory.getLog(TransportDaoHibernate.class);

	public List getAllVehiclesByVehicleCustIdIdAndType(String vehicleType,long custId) {
		try{			
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from Vehicles where custId=");
			queryBuff.append(custId);
			queryBuff.append("  and vehicleType='");
			queryBuff.append(vehicleType);
			queryBuff.append("'");
			List campusByStatusList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(campusByStatusList)){
				return campusByStatusList;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public List getAllVehiclesByVehicleType(String vehicleType) {
		try{			
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from Vehicles where  vehicleType='");
			queryBuff.append(vehicleType);
			queryBuff.append("'");
			List vehiclesList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(vehiclesList)){
				return vehiclesList;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	public Route saveRoute(Route route) throws DataAccessException {
		try{	
			
	       // this.saveObject(route);
			 this.mergeObject(route);
	        return route;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
    }
	public List<Route> checkRouteNumber(long routeNumber)  {
		if (!ObjectFunctions.isNullOrEmpty(routeNumber))
		{
			return (List<Route>) this.getAllHqlQuery("from Route where routeNumber="+routeNumber);
		}
		return null;
	}
	
	
	/*public List<ViewStaffStudentVehicleTripDetails> getAllRouteWithCustomerId(long customerId){

	       try {
	           StringBuffer queryBuf=new StringBuffer();
	           queryBuf.append("from ViewVehicleRouteDetails where custId=");
	           queryBuf.append(customerId);
	           queryBuf.append(" and routeId is not null");
	          return  this.getAllHqlQuery(queryBuf.toString());
	       }catch(Exception ex){
	           ex.printStackTrace();  
	           return null;
	       }  
	}*/
	
	public List<Vehicles> getAllVehicleByCustomerId(long customerId){

	       try {
	           StringBuffer queryBuf=new StringBuffer();
	           queryBuf.append("from Vehicles where custId=");
	           queryBuf.append(customerId);
	          return  (List<Vehicles>) this.getAllHqlQuery(queryBuf.toString());
	       }catch(Exception ex){
	           ex.printStackTrace();  
	           return null;
	       }  
	}
	
	public List<Staff> getAllStaffByCustomerId(long customerId){

	       try {
	           StringBuffer queryBuf=new StringBuffer();
	           queryBuf.append("from Staff where custId=");
	           queryBuf.append(customerId);
	           queryBuf.append("  and accountId is not null");
	          return  (List<Staff>) this.getAllHqlQuery(queryBuf.toString());
	       }catch(Exception ex){
	           ex.printStackTrace();  
	           return null;
	       }  
	}
	public User saveUser(User user){
		try{
			
			 this.saveObject(user);
			 return user;
		}
		catch (HibernateException ex) {
		 ex.printStackTrace();
		 
		 throw new URTDataAccessException(ex.getMessage());
	}
	}
	public List getAllStaffByAccounIdAndRoleName(long accountId,String roleName) {
	       try {
		    	  List leavesList = this.getAllHqlQuery("from ViewStaffPersonAccountDetails where accountId="+accountId+" and  academicYearStatus='Y' and  roleName='"+roleName+"'"); 
		           if(!ObjectFunctions.isNullOrEmpty(leavesList))
		           {
		               return leavesList;
		           }
	           }
	       catch (Exception re) {
	    	   re.printStackTrace();
	       }
	       return null;
	}
	public void removeStaffByStaffIdAndAccountId(long staffId,long accountId){
		try{
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("delete from Staff where id =");
			sqlString.append(staffId);
			sqlString.append("  and accountId='");
			sqlString.append(accountId);
			sqlString.append("'");
            Query qry = getSession().createSQLQuery(sqlString.toString());
            int row = qry.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
			
		}  
	}
	public List getAllDriverOrHelperByCustIdIdAndType(String roleName,long custId) {
		try{			
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from ViewStaffPersonAccountDetails where custId=");
			queryBuff.append(custId);
			queryBuff.append("  and roleName='");
			queryBuff.append(roleName);
			queryBuff.append("' and academicYearStatus='Y' and status='Y'");
			List campusByStatusList=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(campusByStatusList)){
				return campusByStatusList;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/*public VehicleBoardingPoints getBoardingPointsByVehicleTripId(long vehicleTripId){

	       try {
	           StringBuffer queryBuf=new StringBuffer();
	           queryBuf.append("from VehicleBoardingPoints where vehicleTripId=");
	           queryBuf.append(vehicleTripId);
	           List boardingPoints=this.getAllHqlQuery(queryBuf.toString());
				if(ObjectFunctions.isNotNullOrEmpty(boardingPoints)){
					return (VehicleBoardingPoints)boardingPoints.get(0);
				}
				 return null;
	       }catch(Exception ex){
	           ex.printStackTrace();  
	           return null;
	       }  
	}
	public VehicleTrip saveVehicleTrip(VehicleTrip vehicleTrip) throws DataAccessException {
		try{	
	        this.saveObject(vehicleTrip);
	        return vehicleTrip;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
    }
	 public List<ViewStudentPersonAccountDetails> getAllStudentsByRollNumber(String rollNumber,long custId){
			try{
				return this.getAllHqlQuery("from ViewStudentPersonAccountDetails where custId="+custId+" and rollNumber ='"+rollNumber+"'");
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return null;
		}
	 
	 public List<VehicleTrip> getAllVehicleTripByRouteNumber(String routeNumber)  {
			if (!ObjectFunctions.isNullOrEmpty(routeNumber))
			{
				return this.getAllHqlQuery("from VehicleTrip where id="+routeNumber);
			}
			return null;
		}
	 public List<VehicleTrip> getAllBoardingPointsByTripNumber(String tripId)  {
			if (!ObjectFunctions.isNullOrEmpty(tripId))
			{
				return this.getAllHqlQuery("from VehicleTrip where id="+tripId);
			}
			return null;
		}
		 public List<VehicleBoardingPoints> getBoardingPointsByVehicleTripId(String vehicleTripId){
			if (!ObjectFunctions.isNullOrEmpty(vehicleTripId))
			{
				return this.getAllHqlQuery("from VehicleBoardingPoints where vehicleTripId="+vehicleTripId);
			}
			return null;
		}
	 public List<VehicleTrip> getVehicleTripsByvehicleId(long vehicleId) {
			return this.getAllHqlQuery(
					"from VehicleTrip where vehicleId=" + vehicleId);
		}
		
	 public void removeVehicleTripByVehiclId(long vehicleId){
			try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from vehicleTrip where vehicleId =");
				sqlString.append(vehicleId);
	            Query qry = this.getAll(sqlString.toString());
	            int row = qry.executeUpdate();
			}catch(Exception ex){
				ex.printStackTrace();
				
			}  
		}*/
	 
	
	 public List<ViewStaffVehicleTripdetails> getStudentDetailsByVehicleTripId(String tripId)  {
			if (!ObjectFunctions.isNullOrEmpty(tripId))
			{
				return (List<ViewStaffVehicleTripdetails>) this.getAllHqlQuery("from ViewStaffVehicleTripdetails where vehicleTripId="+tripId);
			}
			return null;
		}
	 
	 /*public List getViewStaffVehicleTripdetailsByCustId(long custId){

	       try {
	           StringBuffer queryBuf=new StringBuffer();
	           queryBuf.append("from ViewStaffVehicleTripdetails where custId=");
	           queryBuf.append(custId);
	           queryBuf.append(" and vehicleId is not null group by vehicleId");
	          return  this.getAllHqlQuery(queryBuf.toString());
	       }catch(Exception ex){
	           ex.printStackTrace();  
	           return null;
	       }  
	}*/
	public List<ViewStaffVehicleTripdetails> updateStaffByStaffIdAndAccountId(long staffId,long accountId)  {
		if (!ObjectFunctions.isNullOrEmpty(staffId) && !ObjectFunctions.isNullOrEmpty(accountId))
		{
			return (List<ViewStaffVehicleTripdetails>) this.getAllHqlQuery("from ViewStaffVehicleTripdetails where driverStaffId="+staffId+" and driverAccountId='"+accountId+"'");
		}
		return null;
	}
	/*public List getAllVehicleTripsByAccountId(long accountId){
	       try {
	           StringBuffer queryBuf=new StringBuffer();
	           queryBuf.append("from VehicleTrip where driverAccountId=");
	           queryBuf.append(accountId);
	           List vehicleTripList=this.getAllHqlQuery("from VehicleTrip where driverAccountId="+accountId);
				if(ObjectFunctions.isNotNullOrEmpty(vehicleTripList)){
					return vehicleTripList;
				}
				else{
					return null;
				}
	       }catch(Exception ex){
	           ex.printStackTrace();  
	           return null;
	       }  
	}
	public StudentPayment getPaymentStatusByStudentId(long studentId) {
		List studentPaymentList = this.getAllHqlQuery("from StudentPayment where studentId=" + studentId);
			if (!ObjectFunctions.isNullOrEmpty(studentPaymentList)) {
				StudentPayment studentPayment = (StudentPayment) studentPaymentList.get(0);
				return studentPayment;
			} 
			else {
				return null;
			}
	}
	public List<VehicleTrip> getVehicleTripdetailsByCustId(long custId){

	       try {
	           StringBuffer queryBuf=new StringBuffer();
	           queryBuf.append("from VehicleTrip where vtCustId=");
	           queryBuf.append(custId);
	          return  this.getAllHqlQuery(queryBuf.toString());
	       }catch(Exception ex){
	           ex.printStackTrace();  
	           return null;
	       }  
	     }
	    public List getVehicleRouteByRouteId(long routeId)  {
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append("from vehicleroute where routeId ='");
            queryString.append(routeId);
            List routeDetails =  this.getAllHqlQuery(queryString.toString());
            
            if(!ObjectFunctions.isNullOrEmpty(routeDetails))
            {
                Collections.sort(routeDetails);
                return routeDetails;
            }
            return null;
        } catch (RuntimeException re) {
        	re.printStackTrace();
            throw re;
        }
	} 
	public List getStudentDetailsByVehicleTripId(String tripId)  {
			if (!ObjectFunctions.isNullOrEmpty(tripId))
			{
				 StringBuffer sqlString = new StringBuffer();
				 	sqlString.append("select * from StudentVehicleTrip where vehicletripId =");
				 	sqlString.append(tripId);
			 	 log.debug(sqlString.toString()); 
		        List vehicleList = (List) this.getAll(sqlString.toString()).list();
		           if(!ObjectFunctions.isNullOrEmpty(vehicleList))
		           {
		               return  vehicleList;
		           }
				//return this.getAllHqlQuery("from StudentVehicleTrip where vehicleTripId="+tripId);
			}
			return null;
		}
	 public VehicleTrip getVehicleTripById(long vehicleTripId){
		 try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from VehicleTrip where id=");
			queryBuff.append(vehicleTripId);
			List vehicleTrip=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(vehicleTrip)){
				return (VehicleTrip)vehicleTrip.get(0);
			}
		 	}
		 	catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }
	*/
	public List<Vehicles> getVehiclesByTypeAndVehicleNumber(String vehicleType,String vehicleNumber)  {
		if (!ObjectFunctions.isNullOrEmpty(vehicleType) && !ObjectFunctions.isNullOrEmpty(vehicleNumber))
		{
			return (List<Vehicles>) this.getAllHqlQuery("from Vehicles where  vehicleType='"+vehicleType+"' and vehicleNumber='"+vehicleNumber+"'");
		}
		return null;
	}
	
	public List<ViewStaffVehicleTripdetails> getStaffVehicleTripByDriverAccountId(long driverId,long custId){
		return (List<ViewStaffVehicleTripdetails>) this.getAllHqlQuery("from ViewStaffVehicleTripdetails where  driverId='"+driverId+"' and  custId='"+custId+"'");
	}
	public List<ViewStaffVehicleTripdetails> getStaffVehicleTripByHelperAccountId(long helperId,long custId){
		return (List<ViewStaffVehicleTripdetails>) this.getAllHqlQuery("from ViewStaffVehicleTripdetails where  helperId='"+helperId+"' and  custId='"+custId+"'");
	}
	public List<ViewStaffVehicleTripdetails> getStaffVehicleTripByRouteId(long routeId,long custId){
		return (List<ViewStaffVehicleTripdetails>) this.getAllHqlQuery("from ViewStaffVehicleTripdetails where  routeId='"+routeId+"' and  custId='"+custId+"'");
	}
	public Staff getStaffByAaccountId(long accountId){
       try {
           StringBuffer queryBuf=new StringBuffer();
           queryBuf.append("from Staff where accountId=");
           queryBuf.append(accountId);
           List accountIds=this.getAllHqlQuery(queryBuf.toString());
			if(ObjectFunctions.isNotNullOrEmpty(accountIds)){
				return (Staff)accountIds.get(0);
			}
			 return null;
       }catch(Exception ex){
           ex.printStackTrace();  
           return null;
       }  
	}
	 public void removeUserRoleByUserId(long userId)
	  {
		  try {
	          StringBuffer sqlString = new StringBuffer();
	          sqlString.append("delete from UserRoles where userId=");
	          sqlString.append(userId);
	          int row = getSession().createSQLQuery(sqlString.toString()).executeUpdate();
	          if(row == 0)
	          {
	              log.debug("The no of rows deleted:"+ row);
	          }
	      } catch (Exception re) {
	      	re.printStackTrace();
	      }
	  }
	 public List<Student> getStudentByVehicleTripId(String tripId){
		 if (!ObjectFunctions.isNullOrEmpty(tripId))
			{
				return (List<Student>) this.getAllHqlQuery("from Student where vehicleTripId="+tripId);
			}
			return null;
		}
	 public List<Student> getStudentByIdAndRollNumber(String studentId,String tripId){
		 if (!ObjectFunctions.isNullOrEmpty(studentId) && !ObjectFunctions.isNullOrEmpty(tripId))
			{
				return (List<Student>) this.getAllHqlQuery("from Student where  id='"+studentId+"' and vehicleTripId='"+tripId+"'");
			}
			return null;
	 }
	 public void removeTransportFeeByIdAndClassId(long classId,long feeId) {
			try{
				
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from Fee where classId =");
				sqlString.append(classId);
				sqlString.append("  and id='");
				sqlString.append(feeId);
				sqlString.append("'");
	            Query qry = getSession().createSQLQuery(sqlString.toString());
	            int row = qry.executeUpdate();
			}catch(Exception ex){
				ex.printStackTrace();
				
			}  
		}
	
	 /*public List<ClassName> getAllClassesById(long classId,String status){
		 return this.getAllHqlQuery("from ClassName where id="+classId+" and (feeStatus like'"+status+"')");			
		} */
	 public List<Fee> getAllTransportFeeStausList(String status){
		 return (List<Fee>) this.getAllHqlQuery("from Fee where transFeeStatus = '"+status+"'");
	 }
	 
	 public ViewStaffPersonAccountDetails getPersonAccountDetailsByStudentId(long staffId){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("from ViewStaffPersonAccountDetails where staffId=");
				queryBuff.append(staffId);
				queryBuff.append(" and academicYearStatus='Y'");
				List personAccountDetails=this.getAllHqlQuery(queryBuff.toString());
				if(ObjectFunctions.isNotNullOrEmpty(personAccountDetails)){
					return (ViewStaffPersonAccountDetails)personAccountDetails.get(0);
				}
			 	}
			 	catch(Exception ex){
					ex.printStackTrace();			
				}
				return null;
	 }
	 
	 
	 public List<Route> getRouteDetailsByCustId(long custId,long academicYearId){

	       try {
	           StringBuffer queryBuf=new StringBuffer();
	           queryBuf.append("from Route where custId=").append(custId)
	           .append(" and academicYearId=").append(academicYearId);
	          return (List<Route>) this.getAllHqlQuery(queryBuf.toString());
	       }catch(Exception ex){
	           ex.printStackTrace();  
	           return null;
	       }  
	}
	 public void removeVehicleRouteByRouteIdAndCustId(long routeId,long custId) {
			try{
				StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from route where id =");
				sqlString.append(routeId);
				sqlString.append("  and custId='");
				sqlString.append(custId);
				sqlString.append("'");
	            Query qry = getSession().createSQLQuery(sqlString.toString());
	            int row = qry.executeUpdate();
			}catch(Exception ex){
				ex.printStackTrace();
				
			}  
		}
	public List<RouteBoardingPoints> getBordingPointsByRouteIdAndCustId(long routeId,long custId) {
		return (List<RouteBoardingPoints>) this.getAllHqlQuery("from RouteBoardingPoints where  routeId='"+routeId+"' and custId='"+custId+"'"); 
	}
	public List<Vehicles> getRouteVehiclesByRouteIdAndCustId(long routeId,long custId) {
		return (List<Vehicles>) this.getAllHqlQuery("from Vehicles where  routeId='"+routeId+"' and custId='"+custId+"'"); 
	}
	public Vehicles saveVehicle(Vehicles vehicle) throws DataAccessException {
		try{
			
	        this.saveObject(vehicle);
	        return vehicle;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
    }
	/*public Vehicles getRemoveVehicleStudentById(long vehicleId){
		 try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("from Vehicles where id=");
			queryBuff.append(vehicleId);
			List vehicle=this.getAllHqlQuery(queryBuff.toString());
			if(ObjectFunctions.isNotNullOrEmpty(vehicle)){
				return (Vehicles)vehicle.get(0);
			}
		 	}
		 	catch(Exception ex){
				ex.printStackTrace();			
			}
			return null;
	 }*/
	 /*public List<Student> getVehicleDetailesByVehicleIdAndStudentId(long studentId,long vehicleId){
		if (!ObjectFunctions.isNullOrEmpty(studentId) && !ObjectFunctions.isNullOrEmpty(vehicleId))
		{
			return this.getAllHqlQuery("from Student where  id='"+studentId+"' and vehicleId='"+vehicleId+"'");
		}
		return null;
	}*/
	 public void removeBoardingPointsByBoardingIds(String scheduleIds,long routeId){
			try{
					StringBuffer sqlString = new StringBuffer();
					sqlString.append("delete from routeBoardingPoints where routeId="+routeId+" and id not in "+scheduleIds);
					Query qry = getSession().createSQLQuery(sqlString.toString());
					int row = qry.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();			
			}
	}
	 public void updateStudentsVehicleandBoardingPoints(long vehicleAcademicDetailId,long academicYearId,String boardingPointIds){
		 try{
			 StringBuffer query = null;
			 if(StringFunctions.isNullOrEmpty(boardingPointIds) && vehicleAcademicDetailId > 0){
				 query = new StringBuffer("update student set boardingPointId = null,vehicleAcademicDetailsId=null where vehicleAcademicDetailsId=")
				 .append(vehicleAcademicDetailId).append(" and academicYearId=").append(academicYearId);
			 }else if(StringFunctions.isNotNullOrEmpty(boardingPointIds)){
				 query = new StringBuffer("update student set boardingPointId = null,vehicleAcademicDetailsId=null where boardingPointId in( ")
				 .append(boardingPointIds).append(") ").append(" and academicYearId=").append(academicYearId);
			 }
			 if(!ObjectFunctions.isNullOrEmpty(query)){
				 Query qry = getSession().createSQLQuery(query.toString());
				 log.debug("No of. Students records updated Count : "+qry.executeUpdate());
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		}
	 }
	 public List<ViewVehicleWithDriverDetails> getAllViewVehicleWithDriverDetails(String clause){
		/* StringBuffer query = new StringBuffer("select IFNULL(academicYearId,0) as academicYearId,chasisNumber,classificationType,IFNULL(custId,0) as custId,IFNULL(driverId,0) as driverId,engineNumber,IFNULL(helperId,0) as helperId,insuranceExpiredDate,insuranceNumber,")
		 .append(" IFNULL(noOfSeats,0) as noOfSeats,ownerName,insurancePaidDate,vehicleMaker,registrationAuthority,insuranceDetails,IFNULL(routeId,0) as routeId,IFNULL(status,'N') as status,vehicleNumber,vehicleType,roadTaxAmount,fitnessCheckDate,")
		 .append(" fitnessExpiryDate,permitCheckedDate, permitExpiryDate, pollutionCheckedDate,pollutionExpiryDate,roadTaxNextPaymentDate,roadTaxPaidDate,name, driverName,")
		 .append(" mobileNumber,routeName,routePointStartTime,routePointEndTime,IFNULL(routeEndTimeInMns,0) as routeEndTimeInMns,IFNULL(routeStartTimeInMns,0) as routeStartTimeInMns, academicYearStatus, IFNULL(vehicleId,0) as vehicleId,IFNULL(vehicleAcademicDetailId,0) as vehicleAcademicDetailId,IFNULL(accountId,0) as accountId,vehicleAcademicId,IFNULL(availablePickUp,0) as availablePickUp,IFNULL(availableDrop,0) as availableDrop,IFNULL(filledPickUp,0) as filledPickUp,IFNULL(filledDrop,0) as filledDrop")
		 .append(" from vw_vehicleWithDriverDetails WHERE ").append(clause);
		 List<Object[]> vehicleDriverDetails =  this.getAll(query.toString());*/
		 List<ViewVehicleWithDriverDetails> retList = new ArrayList();
		 ViewVehicleWithDriverDetails vehicleDriverDetail = null;
		 List<Object[]> vehicleDriverDetails = null;
		 try{
			    String st[]=clause.split("and");
				Query qry = getSession().createSQLQuery("CALL SP_VehicleWithDriverDetails(:custId, :pickCurrentTerm)")
							.setParameter("custId",st[1].substring(8, st[1].length()) )
							.setParameter("pickCurrentTerm", true);
				vehicleDriverDetails = qry.list();
				if(ObjectFunctions.isNotNullOrEmpty(vehicleDriverDetails)){
					for(Object[] object : vehicleDriverDetails)
		    		{
						if(!ObjectFunctions.isNullOrEmpty(object)){
							vehicleDriverDetail =new ViewVehicleWithDriverDetails();
							if(ObjectFunctions.isNullOrEmpty(object[2]))
								vehicleDriverDetail.setAcademicYearId(2);
							else
								vehicleDriverDetail.setAcademicYearId(Long.valueOf(object[2].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[9]))
								vehicleDriverDetail.setChasisNumber(null);
							else
								vehicleDriverDetail.setChasisNumber(object[9].toString());
							if(ObjectFunctions.isNullOrEmpty(object[10]))
								vehicleDriverDetail.setClassificationType(null);
							else
								vehicleDriverDetail.setClassificationType(object[10].toString());
							if(ObjectFunctions.isNullOrEmpty(object[11]))
								vehicleDriverDetail.setCustId(0);
							else
								vehicleDriverDetail.setCustId(Long.valueOf(object[11].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[12]))
								vehicleDriverDetail.setDriverId(0);
							else
								vehicleDriverDetail.setDriverId(Long.valueOf(object[12].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[13]))
								vehicleDriverDetail.setEngineNumber(null);
							else
								vehicleDriverDetail.setEngineNumber(object[13].toString());
							if(ObjectFunctions.isNullOrEmpty(object[14]))
								vehicleDriverDetail.setHelperId(0);
							else
								vehicleDriverDetail.setHelperId(Long.valueOf(object[14].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[15]))
								vehicleDriverDetail.setInsuranceExpiredDate(null);
							else
								vehicleDriverDetail.setInsuranceExpiredDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[15].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[16]))
								vehicleDriverDetail.setInsuranceNumber(null);
							else
								vehicleDriverDetail.setInsuranceNumber(object[16].toString());
							if(ObjectFunctions.isNullOrEmpty(object[3]))
								vehicleDriverDetail.setNoOfSeats(0);
							else
								vehicleDriverDetail.setNoOfSeats(Long.valueOf(object[3].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[17]))
								vehicleDriverDetail.setOwnerName(null);
							else
								vehicleDriverDetail.setOwnerName(object[17].toString());
							if(ObjectFunctions.isNullOrEmpty(object[18]))
								vehicleDriverDetail.setInsurancePaidDate(null);
							else
								vehicleDriverDetail.setInsurancePaidDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[18].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[19]))
								vehicleDriverDetail.setVehicleMaker(null);
							else
								vehicleDriverDetail.setVehicleMaker(object[19].toString());
							if(ObjectFunctions.isNullOrEmpty(object[20]))
								vehicleDriverDetail.setRegistrationAuthority(null);
							else
								vehicleDriverDetail.setRegistrationAuthority(object[20].toString());
							if(ObjectFunctions.isNullOrEmpty(object[21]))
								vehicleDriverDetail.setInsuranceDetails(null);
							else
								vehicleDriverDetail.setInsuranceDetails(object[21].toString());
							if(ObjectFunctions.isNullOrEmpty(object[22]))
								vehicleDriverDetail.setRouteId(0);
							else
								vehicleDriverDetail.setRouteId(Long.valueOf(object[22].toString()));
							if("Y".equalsIgnoreCase(object[23].toString()))
								vehicleDriverDetail.setStatus(true);
							else
								vehicleDriverDetail.setStatus(false);
							if(ObjectFunctions.isNullOrEmpty(object[24]))
								vehicleDriverDetail.setVehicleNumber(null);
							else
								vehicleDriverDetail.setVehicleNumber(object[24].toString());
							if(ObjectFunctions.isNullOrEmpty(object[25]))
								vehicleDriverDetail.setVehicleType(null);
							else
								vehicleDriverDetail.setVehicleType(object[25].toString());
							if(ObjectFunctions.isNullOrEmpty(object[26]))
								vehicleDriverDetail.setRoadTaxAmount(null);
							else
								vehicleDriverDetail.setRoadTaxAmount(object[26].toString());
							if(ObjectFunctions.isNullOrEmpty(object[27]))
								vehicleDriverDetail.setFitnessCheckDate(null);
							else
								vehicleDriverDetail.setFitnessCheckDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[27].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[28]))
								vehicleDriverDetail.setFitnessExpiryDate(null);
							else
								vehicleDriverDetail.setFitnessExpiryDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[28].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[29]))
								vehicleDriverDetail.setPermitCheckedDate(null);
							else
								vehicleDriverDetail.setPermitCheckedDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[29].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[30]))
								vehicleDriverDetail.setPermitExpiryDate(null);
							else
								vehicleDriverDetail.setPermitExpiryDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[30].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[31]))
								vehicleDriverDetail.setPollutionCheckedDate(null);
							else
								vehicleDriverDetail.setPollutionCheckedDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[31].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[32]))
								vehicleDriverDetail.setPollutionExpiryDate(null);
							else
								vehicleDriverDetail.setPollutionExpiryDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[32].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[33]))
								vehicleDriverDetail.setRoadTaxNextPaymentDate(null);
							else
								vehicleDriverDetail.setRoadTaxNextPaymentDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[33].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[34]))
								vehicleDriverDetail.setRoadTaxPaidDate(null);
							else
								vehicleDriverDetail.setRoadTaxPaidDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, object[34].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[35]))
								vehicleDriverDetail.setName(null);
							else
								vehicleDriverDetail.setName(object[35].toString());
							if(ObjectFunctions.isNullOrEmpty(object[36]))
								vehicleDriverDetail.setDriverName(null);
							else
								vehicleDriverDetail.setDriverName(object[36].toString());
							if(ObjectFunctions.isNullOrEmpty(object[37]))
								vehicleDriverDetail.setMobileNumber(null);
							else	
								vehicleDriverDetail.setMobileNumber(object[37].toString());
							if(ObjectFunctions.isNullOrEmpty(object[38]))
								vehicleDriverDetail.setRouteName(null);
							else
								vehicleDriverDetail.setRouteName(object[38].toString());
							if(ObjectFunctions.isNullOrEmpty(object[39]))
								vehicleDriverDetail.setRoutePointStartTime(null);
							else
								vehicleDriverDetail.setRoutePointStartTime(object[39].toString());
							if(ObjectFunctions.isNullOrEmpty(object[40]))
								vehicleDriverDetail.setRoutePointEndTime(null);
							else
								vehicleDriverDetail.setRoutePointEndTime(object[40].toString());
							if(ObjectFunctions.isNullOrEmpty(object[41]))
								vehicleDriverDetail.setRouteEndTimeinMns(0);
							else
								vehicleDriverDetail.setRouteEndTimeinMns(new Double(object[41].toString()).longValue());
							if(ObjectFunctions.isNullOrEmpty(object[42]))
								vehicleDriverDetail.setRouteStartTimeinMns(0);
							else
								vehicleDriverDetail.setRouteStartTimeinMns(new Double(object[42].toString()).longValue());
							if(ObjectFunctions.isNullOrEmpty(object[43]))
								vehicleDriverDetail.setAcademicYearStatus("N");
							else
								vehicleDriverDetail.setAcademicYearStatus(object[43].toString());
							if(ObjectFunctions.isNullOrEmpty(object[44]))
								vehicleDriverDetail.setVehicleId(0);
							else	
								vehicleDriverDetail.setVehicleId(Long.valueOf(object[44].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[0]))
								vehicleDriverDetail.setVehicleAcademicDetailId(0);
							else
								vehicleDriverDetail.setVehicleAcademicDetailId(Long.valueOf(object[0].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[45]))
								vehicleDriverDetail.setAccountId(0);
							else
								vehicleDriverDetail.setAccountId(Long.valueOf(object[45].toString()));
							
							if(ObjectFunctions.isNullOrEmpty(object[2]))
								vehicleDriverDetail.setVehicleAcademicId(0);
							else
								vehicleDriverDetail.setVehicleAcademicId(Long.valueOf(object[2].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[6]))
								vehicleDriverDetail.setAvailablePickUp(0);
							else
								vehicleDriverDetail.setAvailablePickUp(Long.valueOf(object[6].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[7]))
								vehicleDriverDetail.setAvailableDrop(0);
							else
								vehicleDriverDetail.setAvailableDrop(Long.valueOf(object[7].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[4]))
								vehicleDriverDetail.setFilledPickUp(0);
							else
								vehicleDriverDetail.setFilledPickUp(Long.valueOf(object[4].toString()));
							if(ObjectFunctions.isNullOrEmpty(object[5]))
								vehicleDriverDetail.setFilledDrop(0);
							else
								vehicleDriverDetail.setFilledDrop(Long.valueOf(object[5].toString()));
	
							retList.add(vehicleDriverDetail);
						}
		    		}
				}
			 }catch(Exception exp){
				 exp.printStackTrace();
			 }
		 	vehicleDriverDetails = null;
			return (List<ViewVehicleWithDriverDetails>) retList;
	 }
	 public List<ViewAssignedVehiclestoRoutes> getAllViewAssignedVehiclestoRoutes(String clause){
		 StringBuffer query = new StringBuffer("select IFNULL(vehicleAcademicId,0) as vehicleAcademicId,IFNULL(routeId,0) as routeId,name,academicYearId,custId,IFNULL(driverId,0) as driverId,IFNULL(helperId,0) as helperId,")
		 .append("status,IFNULL(vehicleId,0) as vehicleId,routeName,IFNULL(routeStatus,'N') as routeStatus")
		 .append(" from vw_assignedVehiclestoRoutes WHERE ").append(clause);
		 List<Object[]> assignedVehicleToRoutes =  this.getAll(query.toString());
		 List<ViewAssignedVehiclestoRoutes> retList = new ArrayList();
		 ViewAssignedVehiclestoRoutes vehicleAssignedRoute = null;
			if(ObjectFunctions.isNotNullOrEmpty(assignedVehicleToRoutes)){
				for(Object[] object : assignedVehicleToRoutes)
	    		{
					if(!ObjectFunctions.isNullOrEmpty(object)){
						vehicleAssignedRoute =new ViewAssignedVehiclestoRoutes();
						if(ObjectFunctions.isNullOrEmpty(object[0]))
							vehicleAssignedRoute.setVehicleAcademicId(0);
						else
							vehicleAssignedRoute.setVehicleAcademicId(Long.valueOf(object[0].toString()));
						if(ObjectFunctions.isNullOrEmpty(object[1]))
							vehicleAssignedRoute.setRouteId(0);
						else
							vehicleAssignedRoute.setRouteId(Long.valueOf(object[1].toString()));
						if(ObjectFunctions.isNullOrEmpty(object[2]))
							vehicleAssignedRoute.setName(null);
						else
							vehicleAssignedRoute.setName(object[2].toString());
						if(ObjectFunctions.isNullOrEmpty(object[3]))
							vehicleAssignedRoute.setAcademicYearId(0);
						else
							vehicleAssignedRoute.setAcademicYearId(Long.valueOf(object[3].toString()));
						if(ObjectFunctions.isNullOrEmpty(object[4]))
							vehicleAssignedRoute.setCustId(0);
						else
							vehicleAssignedRoute.setCustId(Long.valueOf(object[4].toString()));
						if(ObjectFunctions.isNullOrEmpty(object[5]))
							vehicleAssignedRoute.setDriverId(0);
						else
							vehicleAssignedRoute.setDriverId(Long.valueOf(object[5].toString()));
						if(ObjectFunctions.isNullOrEmpty(object[6]))
							vehicleAssignedRoute.setHelperId(0);
						else
							vehicleAssignedRoute.setHelperId(Long.valueOf(object[6].toString()));
						if(ObjectFunctions.isNullOrEmpty(object[7]))
							vehicleAssignedRoute.setStatus(null);
						else
							vehicleAssignedRoute.setStatus(object[7].toString());
						if(ObjectFunctions.isNullOrEmpty(object[8]))
							vehicleAssignedRoute.setVehicleId(0);
						else
							vehicleAssignedRoute.setVehicleId(Long.valueOf(object[8].toString()));
						if(ObjectFunctions.isNullOrEmpty(object[9]))
							vehicleAssignedRoute.setRouteName(null);
						else
							vehicleAssignedRoute.setRouteName(object[9].toString());
						if("Y".equalsIgnoreCase(object[10].toString()))
							vehicleAssignedRoute.setRouteStatus(true);
						else
							vehicleAssignedRoute.setRouteStatus(false);
						retList.add(vehicleAssignedRoute);
					}
	    		}
			}
			assignedVehicleToRoutes = null;
			return (List<ViewAssignedVehiclestoRoutes>) retList;
	 }
	 public List<VehiclesAcademicDetails> getAllVehiclesAssiginedToStudents(long academicYearId){
		try{
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("SELECT * FROM vehiclesAcademicDetails WHERE id in(SELECT distinct(va.id) from vehiclesAcademicDetails va JOIN studentTransportDetails st on(va.id=st.pickupvehicleId or va.id=st.dropVehicleId) WHERE st.academicYearId=")
			.append(academicYearId).append(") and academicYearId=").append(academicYearId);
			return (List<VehiclesAcademicDetails>) getSession().createSQLQuery(sqlString.toString()).addEntity(VehiclesAcademicDetails.class).list();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 /*http://www.sergiy.ca/how-to-write-many-to-many-search-queries-in-mysql-and-hibernate/*/
	 public List<Route> getAllRoutesAssiginedToVehicles(long academicYearId){
		try{
			/*SELECT DISTINCT r.* FROM   `route` r INNER JOIN RouteWithVehicles vehicle ON vehicle.routeId = r.id
			       INNER JOIN vehiclesAcademicDetails t ON t.id = vehicle.vehicleAcademicId WHERE  t.academicYearId=academicYearId and r.academicYearId=academicYearId*/
			StringBuffer sqlString = new StringBuffer();
			sqlString.append("SELECT distinct r FROM Route as r JOIN r.vehicleAcademicDetailsList as vehicle WHERE vehicle.academicYearId=").append(academicYearId)
			.append(" AND r.academicYearId=").append(academicYearId);
			return (List<Route>) getSession().createQuery(sqlString.toString()).list();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	 public void updateRouteBoardingPointStatus(long custId,long academicYearId,String boardingPointIds){
		 try{
			 StringBuffer query = null;
			 if(custId>0  && academicYearId > 0 && StringFunctions.isNotNullOrEmpty(boardingPointIds)){
				 query = new StringBuffer("update routeBoardingPoints set status='N' where id in (").append(boardingPointIds).append(")");
			 }
			 if(!ObjectFunctions.isNullOrEmpty(query)){
				 Query qry = getSession().createSQLQuery(query.toString());
				 log.debug("No of. Students records updated Count : "+qry.executeUpdate());
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		}
	 }
}
