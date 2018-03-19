package com.urt.service.manager.impl.hostel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urt.persistence.interfaces.hostel.HostelDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.hostel.Building;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.hostel.HostelManager;


/**
 * Implementation of UserManager interface.</p>
 * 
 * <p>
 * <a href="UserManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */
@Component
public class HostelManagerImpl extends UniversalManagerImpl implements HostelManager {
	
	@Autowired
	private HostelDao hostelDao;

	/*public List getAllByCustId(String clazz,long custId){
		return hostelDao.getAllByCustId(clazz,custId);
	}
	public List getAllByCustId(String clazz,long custId,long academicYear){
		return hostelDao.getAllByCustId(clazz,custId,academicYear);
	}*/
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllFoodTimeingsByBuildingId(long custId,long buildingId){
		return hostelDao.getAllFoodTimeingsByBuildingId(custId,buildingId);
	}
	 public void removeFoodTypesByFoodTypeIdCustId(long foodTypeId,long custId){
		 hostelDao.removeFoodTypesByFoodTypeIdCustId(foodTypeId, custId);
	 }
	 public void removeMessTimingsByMessTimeingIdCustId(long messTimeId,long buildingId,long custId){
		 hostelDao.removeMessTimingsByMessTimeingIdCustId(messTimeId,buildingId,custId);
	 }
	 public Building saveBuilding(Building building) {
	         return hostelDao.saveBuilding(building);
	 }
	public long getMaxFloorLevel(String floorName,long custId,long academicYearId) {
	         return hostelDao.getMaxFloorLevel(floorName,custId,academicYearId);
	}
	public long getMaxRoomLevel(String floorName,long floorLevel,long custId,long academicYearId) {
	        return hostelDao.getMaxRoomLevel(floorName,floorLevel,custId,academicYearId);
	}
	public Address saveAddress(Address address){
	    return hostelDao.saveAddress(address);
	}
	public long getMaxRoomLevel(String roomName,long custId,long academicYearId) {
	         return hostelDao.getMaxRoomLevel(roomName,custId,academicYearId);
	}
	public long getMaxBedLevel(String bedName,long custId,long academicYearId) {
	         return hostelDao.getMaxBedLevel(bedName,custId,academicYearId);
	}
	/*//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<HostelTerms> getHostelTermsByDuedate(String table,long custId,long academicYearId,String todayDate){
		return hostelDao.getHostelTermsByDuedate(table,custId, academicYearId, todayDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<HostelTerms> getHostelTermsOrderByDuedate(String table,long custId,long academicYearId){
		return hostelDao.getHostelTermsOrderByDuedate(table, custId, academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public HostelTerms saveHostelTerms(HostelTerms hostelTerms){
		return hostelDao.saveHostelTerms(hostelTerms);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<HostelFee> getAllFeeByClassIdAndCustIdAndHostelTermId(long classId,long custId,long academicYearId,long termId,long hostelCategoryId){
		return hostelDao.getAllFeeByClassIdAndCustIdAndHostelTermId(classId, custId, academicYearId, termId, hostelCategoryId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<HostelTerms> checkHostelTermsByNameAndCustId(String hostelTermName,long custId,long academicYearId) {
		return hostelDao.checkHostelTermsByNameAndCustId(hostelTermName,custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public HostelFee getFeeAndFeeTypeByClassId(String table,long classId,long hostelFeeTypeId,long custId,long academicYearId,long hostelTermId,long hostelCategoryId){
		return hostelDao.getFeeAndFeeTypeByClassId(table,classId,hostelFeeTypeId,custId,academicYearId,hostelTermId,hostelCategoryId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public StaffHostelFee getFeeAndFeeTypeByHostelCategoryId(String table,long hostelFeeTypeId,long custId,long academicYearId,long hostelTermId,long hostelCategoryId){
		return hostelDao.getFeeAndFeeTypeByHostelCategoryId(table,hostelFeeTypeId,custId,academicYearId,hostelTermId,hostelCategoryId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<HostelCategory> checkHostelCategoryByNameAndCustId(String categoryName,long custId){
		return hostelDao.checkHostelCategoryByNameAndCustId(categoryName,custId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List<HostelFeeType> checkHostelParticularNameAndCustId(String hostelFeeType,long custId,long academiYearId) {
		return hostelDao.checkHostelParticularNameAndCustId(hostelFeeType,custId,academiYearId);
	}
	*/
	
	public long getFeeTotalAmountByHostelTerm(long custId,long classId,long academicYearId,long hostelTermId,long hostelCategoryId){
		return hostelDao.getFeeTotalAmountByHostelTerm( custId, classId, academicYearId, hostelTermId, hostelCategoryId);
	}
	public long getStudentCountByCustIdAndClassNameClassIdAndHostelCategoryId(long custId,long academicYearId,long classId,long categoryId,String status){
		return hostelDao.getStudentCountByCustIdAndClassNameClassIdAndHostelCategoryId(custId, academicYearId, classId, categoryId, status);
	}
	public int getHostelPaidAmountByClassId(String queryString,long custId,long classId,long academicYearId,long hostelCategoryId) {
	    return hostelDao.getHostelPaidAmountByClassId(queryString,custId, classId, academicYearId, hostelCategoryId);
	}
	public long getStaffPaidAmountByStaffId(String queryString,long staffId,long custId,long academicYearId,long hostelTermId){
		return hostelDao.getStaffPaidAmountByStaffId(queryString,staffId,custId,academicYearId, hostelTermId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getHostelFeeTypeListByAcademicYearId(String queryString,long custId,long academicYearId){
	    return hostelDao.getHostelFeeTypeListByAcademicYearId(queryString,custId,academicYearId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassFeeTermsByStudentId(String table,long studentId,long custId,long academicYearId,long classId,long hostelCategoryId){
	    return hostelDao.getClassFeeTermsByStudentId(table,studentId,custId,academicYearId,classId,hostelCategoryId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getFeetypesByClassId(String table,long classId,long custId,long academicYearId){
		return hostelDao.getFeetypesByClassId(table,classId, custId, academicYearId);
	}
	/*public List getAllClasseNamesNotinClass(long custId,String status,long academicYearId,long classId){
		return hostelDao.getAllClasseNamesNotinClass(custId, status, academicYearId, classId);
	}*/
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllFeeByClasIdAndCustId(String table,long custId,long classId,long academicYearId,long hostelCategoryId){
		return hostelDao.getAllFeeByClasIdAndCustId(table,custId, classId, academicYearId, hostelCategoryId);
	}
	/*public List getAllByCustId(String clazz, long custId,String academicYear) {
		return hostelDao.getAllByCustId(clazz, custId,academicYear);
	}*/
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public AcademicYear getCurrentAcademicYear(String status, long custId) {
		return hostelDao.getCurrentAcademicYear(status, custId);
	}
	
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getHostelTermsByCompleteDuedate(String table,long custId,long academicYearId,String toDayDate){
		return hostelDao.getHostelTermsByCompleteDuedate(table,custId, academicYearId, toDayDate);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getAllStudentInvoiceHostelFeeDetailsByCustId(String queryString,long customerId,long academicYearId,long hostelTermId,String today,String status){
		return hostelDao.getAllStudentInvoiceHostelFeeDetailsByCustId(queryString, customerId, academicYearId,hostelTermId,today,status);
	}
	public long getHostelFeeTotalAmountByTerm(String queryString,long custId,long classId,long academicYearId,long hostelTermId,long hostelCategoryId){
		return hostelDao.getHostelFeeTotalAmountByTerm(queryString, custId, classId, academicYearId, hostelTermId, hostelCategoryId);
	}
	public long getStaffHostelFeeTotalAmountByTerm(String queryString,long custId,long academicYearId,long hostelTermId,long hostelCategoryId){
		return hostelDao.getStaffHostelFeeTotalAmountByTerm(queryString, custId, academicYearId, hostelTermId, hostelCategoryId);
	}
	public long getStudentPaidAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,long hostelTermId){
		return hostelDao.getStudentPaidAmountByClassId(queryString, studentId, classId, custId, academicYearId, hostelTermId);
	}
	public long getStudentDiscountAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds){//long termId,String status
		return hostelDao.getStudentDiscountAmountByClassId( queryString, studentId, classId, custId, academicYearId, feeIds);//termId, status
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getClassWiseFeeDefaultersByCustId(long customerId,long academicYearId,long classId,long termId,String status,String paymentStatus){
		return hostelDao.getClassWiseFeeDefaultersByCustId(customerId, academicYearId, classId, termId, status,paymentStatus);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStaffWiseFeeDefaultersByCustId(long customerId,long academicYearId,long termId,String status,String paymentStatus){
		return hostelDao.getStaffWiseFeeDefaultersByCustId(customerId, academicYearId,termId, status,paymentStatus);
	}
	public long getFeeTotalAmountByHostelTermAndCategoryId(long custId,long academicYearId,long hostelTermId,long hostelCategoryId){
		return hostelDao.getFeeTotalAmountByHostelTermAndCategoryId( custId, academicYearId, hostelTermId, hostelCategoryId);
	}
	public long getStaffCountByCustIdAndHostelCategoryId(long custId,long academicYearId,long categoryId,String status){
		return hostelDao.getStaffCountByCustIdAndHostelCategoryId(custId, academicYearId, categoryId, status);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getStaffFeeTermsByStaffId(String table,long staffId,long custId,long academicYearId,long hostelCategoryId){
	  return hostelDao.getStaffFeeTermsByStaffId(table,staffId, custId, academicYearId, hostelCategoryId);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	public List getHostelFeeTypeList(String hostelFeeType,long custId,long academicYearId){
		return hostelDao.getHostelFeeTypeList(hostelFeeType,custId,academicYearId);
	}
	
}
