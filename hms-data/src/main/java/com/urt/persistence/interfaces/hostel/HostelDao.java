package com.urt.persistence.interfaces.hostel;



import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.hostel.Building;

/**
 * User Data Access Object (Dao) interface.
 *
 * <p>
 * <a href="UserDao.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface HostelDao extends UniversalDao {
	List getAllFoodTimeingsByBuildingId(long custId,long buildingId);
	void removeFoodTypesByFoodTypeIdCustId(long foodTypeId,long custId);
	void removeMessTimingsByMessTimeingIdCustId(long messTimeId,long buildingId,long custId);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	Building saveBuilding(Building building);
	long getMaxFloorLevel(String floorName,long custId,long academicYearId);
	long getMaxRoomLevel(String floorName,long floorLevel,long custId,long academicYearId);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	Address saveAddress(Address address) throws DataAccessException;
	
	long getMaxRoomLevel(String roomName,long custId,long academicYearId);
	long getMaxBedLevel(String bedName,long custId,long academicYearId);
	
	long getStaffPaidAmountByStaffId(String queryString,long staffId,long custId,long academicYearId,long hostelTermId);
	List getHostelFeeTypeList(String hostelFeeType,long custId,long academicYearId);
	
	
	long getFeeTotalAmountByHostelTerm(long custId,long classId,long academicYearId,long hostelTermId,long hostelCategoryId);
	long getStudentCountByCustIdAndClassNameClassIdAndHostelCategoryId(long custId,long academicYearId,long classId,long categoryId,String status);
	int getHostelPaidAmountByClassId(String queryString,long custId,long classId,long academicYearId,long hostelCategoryId);
	
	List getHostelFeeTypeListByAcademicYearId(String queryString,long custId,long academicYearId);
	List getClassFeeTermsByStudentId(String table,long studentId,long custId,long academicYearId,long classId,long hostelCategoryId);
	List getFeetypesByClassId(String table,long classId,long custId,long academicYearId);
	List getAllFeeByClasIdAndCustId(String table,long custId,long classId,long academicYearId,long hostelCategoryId);
	AcademicYear getCurrentAcademicYear(String status,long custId);
	List getHostelTermsByCompleteDuedate(String table,long custId,long academicYearId,String toDayDate);
	List getAllStudentInvoiceHostelFeeDetailsByCustId(String queryString,long customerId,long academicYearId,long hostelTermId,String today,String status);
	long getHostelFeeTotalAmountByTerm(String queryString,long custId,long classId,long academicYearId,long hostelTermId,long hostelCategoryId);
	long getStaffHostelFeeTotalAmountByTerm(String queryString,long custId,long academicYearId,long hostelTermId,long hostelCategoryId);
	long getStudentPaidAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,long hostelTermId);
	long getStudentDiscountAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds);//long termId,String status
	List getClassWiseFeeDefaultersByCustId(long customerId,long academicYearId,long classId,long termId,String status,String paymentStatus);
	List getStaffWiseFeeDefaultersByCustId(long customerId,long academicYearId,long termId,String status,String paymentStatus);
	
	 long getFeeTotalAmountByHostelTermAndCategoryId(long custId,long academicYearId,long hostelTermId,long hostelCategoryId);
	 long getStaffCountByCustIdAndHostelCategoryId(long custId,long academicYearId,long categoryId,String status);
	 List getStaffFeeTermsByStaffId(String table,long staffId,long custId,long academicYearId,long hostelCategoryId);
	 
}
