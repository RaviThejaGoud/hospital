package com.urt.service.manager.interfaces.hostel;

import java.util.List;

import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.hostel.Building;
import com.urt.service.manager.interfaces.base.UniversalManager;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="UserManager.java.html"><i>View Source</i></a></p>
 */
public interface HostelManager extends UniversalManager {
       
    //List getAllByCustId(String clazz,long custId);
    //List getAllByCustId(String clazz,long custId,long academicYear);
	
    List getAllFoodTimeingsByBuildingId(long custId,long buildingId);
    void removeFoodTypesByFoodTypeIdCustId(long foodTypeId,long custId);
    void removeMessTimingsByMessTimeingIdCustId(long messTimeId,long buildingId,long custId);
    Building saveBuilding(Building building) ;
    long getMaxFloorLevel(String floorName,long custId,long academicYearId) ;
    long getMaxRoomLevel(String floorName,long floorLevel,long custId,long academicYearId);
    
    Address saveAddress(Address address);
    long getMaxRoomLevel(String roomName,long custId,long academicYearId) ;
    long getMaxBedLevel(String bedName,long custId,long academicYearId) ;
    
    List getHostelFeeTypeList(String hostelFeeType,long custId,long academicYearId);
    
    
    long getFeeTotalAmountByHostelTerm(long custId,long classId,long academicYearId,long hostelTermId,long hostelCategoryId);
    long getStudentCountByCustIdAndClassNameClassIdAndHostelCategoryId(long custId,long academicYearId,long classId,long categoryId,String status);
    int getHostelPaidAmountByClassId(String queryString,long custId,long classId,long academicYearId,long hostelCategoryId);
    long getStaffPaidAmountByStaffId(String queryString,long staffId,long custId,long academicYearId,long hostelTermId);
    
    
    
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
