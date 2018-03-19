package com.urt.persistence.impl.hostel;


import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.exception.base.URTDataAccessException;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.hostel.HostelDao;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.hostel.Building;
import com.urt.util.common.RayGunException;

@Transactional
public class HostelDaoHibernate extends UniversalHibernateDao implements HostelDao
{
	private static final Log log = LogFactory.getLog(HostelDaoHibernate.class);
	
	public List getAllFoodTimeingsByBuildingId(long custId,long buildingId){
		try {
			return this.getAllHqlQuery("from MessMenuTime where custId="+custId+" and buildingId="+buildingId);
		} catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return null;
	}
	public void removeFoodTypesByFoodTypeIdCustId(long foodTypeId,long custId){
		 try{
			 StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from foodMenuItems where foodTypeId=");
				sqlString.append(foodTypeId);
				sqlString.append(" and custId=");
				sqlString.append(custId);
				log.debug(sqlString.toString());
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
		 }catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	 }
	 public void removeMessTimingsByMessTimeingIdCustId(long messTimeId,long buildingId,long custId){
		 try{
			 StringBuffer sqlString = new StringBuffer();
				sqlString.append("delete from messMenuTime where id=");
				sqlString.append(messTimeId);
				sqlString.append(" and buildingId=");
				sqlString.append(buildingId);
				sqlString.append(" and custId=");
				sqlString.append(custId);
				log.debug(sqlString.toString());
				Query qry = getSession().createSQLQuery(sqlString.toString());
				int row = qry.executeUpdate();
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	 }
	 public Building saveBuilding(Building building) {
		try{	
			
	        this.saveObject(building);
	        return building;
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public long getMaxFloorLevel(String floorName,long custId,long academicYearId) {
	    	StringBuffer queryBuff=new StringBuffer();
		queryBuff.append("select IFNULL(max(floorLevel),0) from floor where custId="+custId+" and academicYearId="+academicYearId+" and floorName='"+floorName+"'");
		List resultList=this.getAll(queryBuff.toString());
		if(!ObjectFunctions.isNullOrEmpty(resultList))
		{
		   if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
		       BigInteger var= new BigInteger(String.valueOf(resultList.get(0)));
		       return var.longValue();
		     }
		}
		return 0;
	}
	public long getMaxRoomLevel(String floorName,long floorLevel,long custId,long academicYearId) {
	    	StringBuffer queryBuff=new StringBuffer();
		queryBuff.append("select IFNULL(max(roomNumber),0) from vw_roomDetails where custId="+custId+" and academicYearId="+academicYearId+" and floorName='"+floorName+"' and floorLevel="+floorLevel);
		List resultList=this.getAll(queryBuff.toString());
		log.debug(queryBuff.toString());
		if(!ObjectFunctions.isNullOrEmpty(resultList))
		{
		   if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
		       BigInteger var= new BigInteger(String.valueOf(resultList.get(0)));
		       return var.longValue();
		     }
		}
		return 0;
	}
	public Address saveAddress(Address address) throws DataAccessException{
		try {
			
			this.saveObject(address);
			return address;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new URTDataAccessException(e.getMessage());
		}
	}
	public long getMaxRoomLevel(String roomName,long custId,long academicYearId) {
	    	StringBuffer queryBuff=new StringBuffer();
		queryBuff.append("select IFNULL(max(roomLevel),0) from room where custId="+custId+" and academicYearId="+academicYearId+" and roomName='"+roomName+"'");
		List resultList=this.getAll(queryBuff.toString());
		if(!ObjectFunctions.isNullOrEmpty(resultList))
		{
		   if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
		       BigInteger var= new BigInteger(String.valueOf(resultList.get(0)));
		       return var.longValue();
		     }
		}
		return 0;
	}
	public long getMaxBedLevel(String bedName,long custId,long academicYearId) {
	    	StringBuffer queryBuff=new StringBuffer();
		queryBuff.append("select IFNULL(max(bedLevel),0) from bed where custId="+custId+" and academicYearId="+academicYearId+" and bedName='"+bedName+"'");
		List resultList=this.getAll(queryBuff.toString());
		if(!ObjectFunctions.isNullOrEmpty(resultList))
		{
		   if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
		       BigInteger var= new BigInteger(String.valueOf(resultList.get(0)));
		       return var.longValue();
		     }
		}
		return 0;
	}
	
	public List getHostelFeeTypeList(String hostelFeeType, long custId,long academicYearId) {
		try {
			return this.getAllHqlQuery("from HostelFeeType where custId=" + custId + " and description='"+ hostelFeeType + "' and academicYearId="+academicYearId);
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	 public long getFeeTotalAmountByHostelTerm(long custId,long classId,long academicYearId,long hostelTermId,long hostelCategoryId){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select sum(feeAmount) from hostelFee where classId=");
				queryBuff.append(classId);	
				queryBuff.append(" and custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and hostelTermId=");
				queryBuff.append(hostelTermId);
				queryBuff.append(" and hostelCategoryId=");
				queryBuff.append(hostelCategoryId);
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Double var= ((Double)resultList.get(0));
						return var.longValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return 0;
	 }
	 public long getStudentCountByCustIdAndClassNameClassIdAndHostelCategoryId(long custId,long academicYearId,long classId,long categoryId,String status){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from student where custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and classNameClassId=");
				queryBuff.append(classId);
				queryBuff.append(" and hostelCategoryId=");
				queryBuff.append(categoryId);
				queryBuff.append(" and status='"+status+"' and bedId is not null and description is null");	
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						BigInteger var= ((BigInteger)resultList.get(0));
						return var.longValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return 0;
	 }
	 public int getHostelPaidAmountByClassId(String queryString,long custId,long classId,long academicYearId,long hostelCategoryId){
		 try{
			 StringBuffer queryBuff=new StringBuffer();
			 if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
				 queryBuff.append("select sum(paymentAmount) from vw_studentHostelFeePaymentDetails where custId=");
				}else{
					queryBuff.append("select sum(paymentAmount) from vw_hostelStaffFeePaymentDetails where custId=");
				}
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and hostelCategoryId=");
				queryBuff.append(hostelCategoryId);
				if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
					queryBuff.append(" and classId=");
					queryBuff.append(classId);
				}  
				queryBuff.append(" and status='"+Constants.YES_STRING+"' and bedId is not null and description is null");	
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Double var= ((Double)resultList.get(0));
						return var.intValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return 0; 
		}
	 
    public List getHostelFeeTypeListByAcademicYearId(String queryString,long custId, long academicYearId) {
	try {
	    StringBuffer queryStr = new StringBuffer();
	    queryStr.append("from " + queryString + "  where academicYearId=");
	    queryStr.append(academicYearId);
	    queryStr.append(" and custId=");
	    queryStr.append(custId);
	    List feeTypeList = this.getAllHqlQuery(queryStr.toString());
	    if (!ObjectFunctions.isNullOrEmpty(feeTypeList)) {
		return feeTypeList;
	    }
	    return null;
	} catch (RuntimeException re) {
	    log.error("Get failed", re);
	    throw re;
	}
    }
    public List getClassFeeTermsByStudentId(String table,long studentId,long custId,long academicYearId,long classId,long hostelCategoryId){
	try{
	    return this.getAllHqlQuery("from "+table+" where custId ="+custId+" and academicYearId="+academicYearId+" and classId="+classId+" and studentId="+studentId+" and hostelCategoryId="+hostelCategoryId+" group by feeId order by fromDate, termName, feeId");
	}catch(Exception ex){
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return null;
    }

    public List getFeetypesByClassId(String table, long classId, long custId,long academicYearId) {
    	if ("ViewClassHostelFeeTypes".equalsIgnoreCase(table)) {
    		return this.getAllHqlQuery("from " + table + " where classId=" + classId + " and custId="+ custId + " and academicYearId=" + academicYearId);
    	}else{
    		return this.getAllHqlQuery("from " + table + " where custId="+ custId + " and academicYearId=" + academicYearId+" group by hostelFeeTypeId");
    	}
    }
   /* public List getAllClasseNamesNotinClass(long custId, String status,long academicYearId, long classId) {
    	return this.getAllHqlQuery("from ClassName where (custId=" + custId+ " and academicYearId=" + academicYearId+ " and id not in (" + classId + ") )");
    }*/
    public List getAllFeeByClasIdAndCustId(String table,long custId,long classId,long academicYearId,long hostelCategoryId){
    	if ("HostelFee".equalsIgnoreCase(table)) {
    		return this.getAllHqlQuery("from "+table+" where custId="+custId+" and classId="+classId+" and academicYearId="+academicYearId+" and hostelCategoryId="+hostelCategoryId);
    	}else{
    		log.debug("from "+table+" where custId="+custId+" and academicYearId="+academicYearId+" and hostelCategoryId="+hostelCategoryId);
    		return this.getAllHqlQuery("from "+table+" where custId="+custId+" and academicYearId="+academicYearId+" and hostelCategoryId="+hostelCategoryId);
    	}
    }
    public AcademicYear getCurrentAcademicYear(String status,long custId){
	try{
	List academicYear=this.getAllHqlQuery("from AcademicYear where status = '"+ status +"' and custId="+custId);
	if(!ObjectFunctions.isNullOrEmpty(academicYear)){
		return (AcademicYear)academicYear.get(0);
	}
	}catch(Exception ex){
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
	}
	return null;
    }
    
    public List getHostelTermsByCompleteDuedate(String table,long custId,long academicYearId, String todayDate) {
	try {
		StringBuffer queryString = new StringBuffer();
		queryString.append("from "+table+" where ");
		queryString.append(" academicYearId =");
		queryString.append(academicYearId);
		queryString.append(" and custId=" + custId);
		queryString.append(" and dueDate <'");
		queryString.append(todayDate);
		queryString.append(" 00:00:00' order by dueDate");
		List termNameNames = this.getAllHqlQuery(queryString.toString());
		if (!ObjectFunctions.isNullOrEmpty(termNameNames)) {
			Collections.sort(termNameNames);
			return termNameNames;
		}
		return null;
	} catch (RuntimeException re) {
		log.error("Get failed", re);
		throw re;
	}
    }

    public List getAllStudentInvoiceHostelFeeDetailsByCustId(String queryString,long customerId, long academicYearId, long hostelTermId, String today,String status) {
	try {
	    StringBuffer queryStr = new StringBuffer();
	    queryStr.append("select studentId,invoiceNumber from "+ queryString + " where custId=" + customerId);
	    if (!StringFunctions.isNullOrEmpty(today)) {
		queryStr.append(" and paymentDate=");
		queryStr.append("'" + today + "'");
	    }
	    queryStr.append(" and academicYearId=");
	    queryStr.append(academicYearId);
	    queryStr.append(" and hostelTermId=");
	    queryStr.append(hostelTermId);
	    queryStr.append(" and status='");
	    queryStr.append(status);
	    queryStr.append("'");
	    queryStr.append(" and bedId != 0 group by studentId,invoiceNumber");
	    List resultList = this.getAll(queryStr.toString());
	    if (!ObjectFunctions.isNullOrEmpty(resultList)) {
		return resultList;
	    }
	    return null;
	} catch (Exception re) {
	    re.printStackTrace();
	}
	return null;
    }
    public long getHostelFeeTotalAmountByTerm(String queryString,long custId,long classId,long academicYearId,long hostelTermId,long hostelCategoryId){
	 try{
			StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select sum(feeAmount) from "+queryString+" where classId=");
			queryBuff.append(classId);	
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);
			queryBuff.append(" and hostelTermId=");
			queryBuff.append(hostelTermId);
			queryBuff.append(" and hostelCategoryId=");
			queryBuff.append(hostelCategoryId);
			List resultList=this.getAll(queryBuff.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList)){
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					Double var= ((Double)resultList.get(0));
					return var.longValue();
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
		}
		return 0;
    }
    public long getStaffHostelFeeTotalAmountByTerm(String queryString,long custId,long academicYearId,long hostelTermId,long hostelCategoryId){
   	 try{
   			StringBuffer queryBuff=new StringBuffer();
   			queryBuff.append("select sum(feeAmount) from "+queryString+" where custId=");
   			queryBuff.append(custId);	
   			queryBuff.append(" and academicYearId=");
   			queryBuff.append(academicYearId);
   			queryBuff.append(" and hostelTermId=");
   			queryBuff.append(hostelTermId);
   			queryBuff.append(" and hostelCategoryId=");
   			queryBuff.append(hostelCategoryId);
   			List resultList=this.getAll(queryBuff.toString());
   			if(!ObjectFunctions.isNullOrEmpty(resultList)){
   				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
   					Double var= ((Double)resultList.get(0));
   					return var.longValue();
   				}
   			}
   		}
   		catch(Exception ex){
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
   		}
   		return 0;
       }
    public long getStudentPaidAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,long hostelTermId){
	 try{
		 StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select sum(paymentAmount) from "+queryString+" where studentId=");
			queryBuff.append(studentId);	
			queryBuff.append(" and classId=");
			queryBuff.append(classId);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);
			if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
				queryBuff.append(" and deleteStatus='N'");
		    }
			queryBuff.append(" and hostelTermId=");
			queryBuff.append(hostelTermId);
			List resultList=this.getAll(queryBuff.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList)){
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					Double var= ((Double)resultList.get(0));
					return var.longValue();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0; 
    }
    
    public long getStaffPaidAmountByStaffId(String queryString,long staffId,long custId,long academicYearId,long hostelTermId){
   	 try{
   		 StringBuffer queryBuff=new StringBuffer();
   			queryBuff.append("select sum(paymentAmount) from "+queryString+" where staffId=");
   			queryBuff.append(staffId);	
   			queryBuff.append(" and custId=");
   			queryBuff.append(custId);
   			queryBuff.append(" and academicYearId=");
   			queryBuff.append(academicYearId);
   			if ("vw_studentHostelFeePaymentDetails".equalsIgnoreCase(queryString)) {
				queryBuff.append(" and deleteStatus='N'");
			}
   			queryBuff.append(" and hostelTermId=");
   			queryBuff.append(hostelTermId);
   			List resultList=this.getAll(queryBuff.toString());
   			if(!ObjectFunctions.isNullOrEmpty(resultList)){
   				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
   					Double var= ((Double)resultList.get(0));
   					return var.longValue();
   				}
   			}
   		}catch (Exception e) {
   			e.printStackTrace();
   		}
   		return 0; 
       }
    public long getStudentDiscountAmountByClassId(String queryString,long studentId,long classId,long custId,long academicYearId,String feeIds){//long termId,String status
	 try{
		 StringBuffer queryBuff=new StringBuffer();
			queryBuff.append("select sum(discountAmount) from "+queryString+" where studentId=");
			queryBuff.append(studentId);	
			queryBuff.append(" and feeId in ");
			queryBuff.append(feeIds);
			queryBuff.append(" and custId=");
			queryBuff.append(custId);
			/*queryBuff.append(" and academicYearId=");
			queryBuff.append(academicYearId);*/
			List resultList=this.getAll(queryBuff.toString());
			if(!ObjectFunctions.isNullOrEmpty(resultList)){
				if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
					Double var= ((Double)resultList.get(0));
					return var.longValue();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0; 
    }
    public List getClassWiseFeeDefaultersByCustId(long customerId,long academicYearId,long classId,long termId,String status,String paymentStatus){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select studentId,invoiceNumber from vw_studentHostelFeePaymentDetails where custId="+customerId);
       	   queryString.append(" and classId=");
           queryString.append("'"+classId+"'");
           queryString.append(" and academicYearId=");
           queryString.append(academicYearId);
           queryString.append(" and hostelTermId=");
           queryString.append(termId);
           queryString.append(" and status='");
		   queryString.append(status);
		   queryString.append("'");
		   if(!StringFunctions.isNullOrEmpty(paymentStatus)){
			   queryString.append(" and paymentStatus='");
			   queryString.append(paymentStatus);
			   queryString.append(" ' ");
		   }
		   queryString.append(" and bedId != 0 group by studentId");
		   List resultList=this.getAll(queryString.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				//Collections.sort(resultList);
				return resultList;
			} return null; 
        } catch (Exception re) {
        	re.printStackTrace();
        }
		return null;
	}
    public List getStaffWiseFeeDefaultersByCustId(long customerId,long academicYearId,long termId,String status,String paymentStatus){
		try 
		{
           StringBuffer queryString = new StringBuffer();
           queryString.append("select staffId,invoiceNumber from vw_hostelStaffFeePaymentDetails where custId="+customerId);
           queryString.append(" and academicYearId=");
           queryString.append(academicYearId);
           queryString.append(" and hostelTermId=");
           queryString.append(termId);
           queryString.append(" and status='");
		   queryString.append(status);
		   queryString.append("'");
		   if(!StringFunctions.isNullOrEmpty(paymentStatus)){
			   queryString.append(" and paymentStatus='");
			   queryString.append(paymentStatus);
			   queryString.append(" ' ");
		   }
		   queryString.append(" group by staffId");
		   List resultList=this.getAll(queryString.toString());
			if (!ObjectFunctions.isNullOrEmpty(resultList)) {
				//Collections.sort(resultList);
				return resultList;
			} return null; 
        } catch (Exception re) {
        	re.printStackTrace();
        }
		return null;
	}
    public long getFeeTotalAmountByHostelTermAndCategoryId(long custId,long academicYearId,long hostelTermId,long hostelCategoryId){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select sum(feeAmount) from staffHostelFee where custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and hostelTermId=");
				queryBuff.append(hostelTermId);
				queryBuff.append(" and hostelCategoryId=");
				queryBuff.append(hostelCategoryId);
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						Double var= ((Double)resultList.get(0));
						return var.longValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return 0;
	 }
	 public long getStaffCountByCustIdAndHostelCategoryId(long custId,long academicYearId,long categoryId,String status){
		 try{
				StringBuffer queryBuff=new StringBuffer();
				queryBuff.append("select count(*) from staff where custId=");
				queryBuff.append(custId);
				queryBuff.append(" and academicYearId=");
				queryBuff.append(academicYearId);
				queryBuff.append(" and hostelCategoryId=");
				queryBuff.append(categoryId);
				queryBuff.append(" and status='"+status+"' and bedId is not null and description is null");	
				List resultList=this.getAll(queryBuff.toString());
				if(!ObjectFunctions.isNullOrEmpty(resultList)){
					if(!ObjectFunctions.isNullOrEmpty(resultList.get(0))){
						BigInteger var= ((BigInteger)resultList.get(0));
						return var.longValue();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;			
			}
			return 0;
	 }
	 public List getStaffFeeTermsByStaffId(String table,long staffId,long custId,long academicYearId,long hostelCategoryId){
		 try{
			 log.debug("from "+table+" where custId ="+custId+" and academicYearId="+academicYearId+" and staffId="+staffId+" and hostelCategoryId="+hostelCategoryId+" order by fromDate, hostelTermName, staffHostelFeeId");
			    return this.getAllHqlQuery("from "+table+" where custId ="+custId+" and academicYearId="+academicYearId+" and staffId="+staffId+" and hostelCategoryId="+hostelCategoryId+" group by staffHostelFeeId order by fromDate, hostelTermName, staffHostelFeeId");
			}catch(Exception ex){
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	    }
	 
}
