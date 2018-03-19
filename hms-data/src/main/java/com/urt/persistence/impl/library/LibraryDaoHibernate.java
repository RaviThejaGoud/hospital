package com.urt.persistence.impl.library;


import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.transaction.annotation.Transactional;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.persistence.impl.base.UniversalHibernateDao;
import com.urt.persistence.interfaces.library.LibraryDao;
import com.urt.persistence.model.library.Block;
import com.urt.persistence.model.library.BookAndBlockDetails;
import com.urt.persistence.model.library.BookLables;
import com.urt.persistence.model.library.BookTitle;
import com.urt.persistence.model.library.IssuedBook;
import com.urt.persistence.model.library.LibrarySettings;
import com.urt.persistence.model.library.RackDetails;
import com.urt.persistence.model.library.Reservations;
import com.urt.persistence.model.library.ViewIssuedBookAndSettings;
import com.urt.persistence.model.study.Student;
import com.urt.util.common.RayGunException;

@Transactional
public class LibraryDaoHibernate extends UniversalHibernateDao implements LibraryDao
{
	private static final Log log = LogFactory.getLog(LibraryDaoHibernate.class);
	
	public BookTitle saveBookTitle(BookTitle bookTitle) {
		try{	
			
	        this.saveObject(bookTitle);
	        return bookTitle;
		}catch(Exception ex){
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
    }
	
	public List getStudySubjectByCustIdAndSearchword(String searchword, long custId) {
		try {
			List studySubjectList = this.getAllHqlQuery("from StudySubject where name like '%"+searchword+"%'  and custId="+custId);
			if (ObjectFunctions.isNotNullOrEmpty(studySubjectList)) {
				return studySubjectList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;  
	}
	public List bookSearchResultsByKeywordAndCustId(String searchWord, long custId,String searchBy,long classId,long academicYearId,String reservationDate,String selectedType) {
		try {
			List bookList =null;
			String classIdText="";
			 //Date reserveDate =DateFormatter.parseString(DateFormatter.CCYY_MM_DD_PATTERN,reservationDate);
			// String reserveDate=null;
			
			if(classId > 0){
				classIdText= " and classId="+classId;
			}
			 if(!StringFunctions.isNullOrEmpty(reservationDate)){
			//	 reserveDate= DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_HHMMSS_PATTERN, reservationDate.toString());
				 if("TitleWords".equalsIgnoreCase(searchBy)){
						bookList = this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where (bookName like '%"+searchWord+"%'  or  otherSubjects like '%"+searchWord+"%' or publisher like '%"+searchWord+"%' or author like '%"+searchWord+"%') and custId=" + custId +" and bookLabelStatus ='O' and deleteStatus='N' and type='IB' and reservationStatus='P' and issueBookStatus='S'"+"  and academicYearId<="+academicYearId+classIdText+" group by bookTitleId");
					}
					else if("Title".equalsIgnoreCase(searchBy)){
						bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where custId="+custId+" and (bookName like '%"+searchWord+"%' or  otherSubjects like '%"+searchWord+"%'"+") and academicYearId<="+academicYearId +" and bookLabelStatus ='O' and deleteStatus='N' and type='IB' and  reservationStatus='P' and issueBookStatus='S'"+ classIdText+" group by bookTitleId");
					}
					else if("Author".equalsIgnoreCase(searchBy)){
						bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where custId="+custId+" and (author like '%"+searchWord+"%' or  otherSubjects like '%"+searchWord+"%'"+") and academicYearId<="+academicYearId +" and bookLabelStatus ='O' and deleteStatus='N' and type='IB' and reservationStatus='P' and issueBookStatus='S'"+ classIdText+" group by bookTitleId");
					}
					else if("Subject".equalsIgnoreCase(searchBy)){
						bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where (otherSubjects like '%"+searchWord+"%' and custId="+custId+" and bookName like '%"+searchWord+"%' and subjectName like '%"+searchWord+"%') and academicYearId<="+academicYearId+" and bookLabelStatus ='O' and deleteStatus='N' and type='IB' and reservationStatus='P' and issueBookStatus='S'"+classIdText+" group by bookTitleId");
					}
					else if("Publisher".equalsIgnoreCase(searchBy)){
						bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where  custId="+custId+" and (publisher like '%"+searchWord+"%' or  otherSubjects like '%"+searchWord+"%'"+") and academicYearId<="+academicYearId+" and bookLabelStatus ='O' and deleteStatus='N' and type='IB'  and reservationStatus='P' and issueBookStatus='S'"+classIdText+" group by bookTitleId");
			   } 
			 }
			 else{
				 if("TitleWords".equalsIgnoreCase(searchBy)){
					if(!StringFunctions.isNullOrEmpty(selectedType) && "RB".equalsIgnoreCase(selectedType))
						bookList = this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where  (bookName like '%"+searchWord+"%'  or  otherSubjects like '%"+searchWord+"%' or publisher like '%"+searchWord+"%' or author like '%"+searchWord+"%') and custId=" + custId +" and deleteStatus='N' and type='RB' and bookLabelStatus ='O' "+"  and academicYearId<="+academicYearId+classIdText+" group by bookTitleId");
					else
						bookList = this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where  (bookName like '%"+searchWord+"%'  or  otherSubjects like '%"+searchWord+"%' or publisher like '%"+searchWord+"%' or author like '%"+searchWord+"%') and custId=" + custId +" and deleteStatus='N' and type='IB' and bookLabelStatus ='O' "+"  and academicYearId<="+academicYearId+classIdText+" group by bookTitleId");
					}
					else if("Title".equalsIgnoreCase(searchBy)){
						if(!StringFunctions.isNullOrEmpty(selectedType) && "RB".equalsIgnoreCase(selectedType))
							bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where  custId="+custId+" and bookName like '%"+searchWord+"%'"+" and academicYearId<="+academicYearId +" and deleteStatus='N' and type='RB' and bookLabelStatus ='O'"+ classIdText+" group by bookTitleId");
						else
							bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where  custId="+custId+" and bookName like '%"+searchWord+"%'"+" and academicYearId<="+academicYearId +" and deleteStatus='N' and type='IB' and bookLabelStatus ='O'"+ classIdText+" group by bookTitleId");
					}
					else if("Author".equalsIgnoreCase(searchBy)){
						if(!StringFunctions.isNullOrEmpty(selectedType) && "RB".equalsIgnoreCase(selectedType))
							bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where  custId="+custId+" and author like '%"+searchWord+"%'"+" and academicYearId<="+academicYearId +" and deleteStatus='N'  and type='RB' and bookLabelStatus ='O'"+ classIdText+" group by bookTitleId");
						else
							bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where  custId="+custId+" and author like '%"+searchWord+"%'"+" and academicYearId<="+academicYearId +" and deleteStatus='N'  and type='IB' and bookLabelStatus ='O'"+ classIdText+" group by bookTitleId");
					}
					else if("Subject".equalsIgnoreCase(searchBy)){
						if(!StringFunctions.isNullOrEmpty(selectedType) && "RB".equalsIgnoreCase(selectedType))
							bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where  otherSubjects like '%"+searchWord+"%' and custId="+custId+" and (bookName like '%"+searchWord+"%' and subjectName like '%"+searchWord+"%') and academicYearId<="+academicYearId+" and deleteStatus='N' and type='RB' and bookLabelStatus ='O'"+classIdText+" group by bookTitleId");
						else
							bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where custId="+custId+" and (subjectName like '%"+searchWord+"%' or otherSubjects like '%"+searchWord+"%') and academicYearId<="+academicYearId+" and deleteStatus='N' and type='IB' and bookLabelStatus ='O' group by bookTitleId"+classIdText+" group by bookTitleId");
					}
					else if("Publisher".equalsIgnoreCase(searchBy)){
						if(!StringFunctions.isNullOrEmpty(selectedType) && "RB".equalsIgnoreCase(selectedType))
							bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where  custId="+custId+" and publisher like '%"+searchWord+"%'"+" and academicYearId<="+academicYearId+" and deleteStatus='N' and type='RB' and bookLabelStatus ='O'"+classIdText+" group by bookTitleId");
						else
							bookList= this.getAllHqlQuery("from ViewStudentIssuebookAndReservations where  custId="+custId+" and publisher like '%"+searchWord+"%'"+" and academicYearId<="+academicYearId+" and deleteStatus='N' and type='IB' and bookLabelStatus ='O'"+classIdText+" group by bookTitleId");
					} 
			 }
			
			if (ObjectFunctions.isNotNullOrEmpty(bookList)) {
				return bookList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	public List getIssuedBooksByStudentUserId(long studentUserId, long custId,String status) {
		try {
			List issuedBookList = this.getAllHqlQuery("from IssuedBook where accountId="+ studentUserId +" and status='"+status+"' and custId="+custId);
			if (ObjectFunctions.isNotNullOrEmpty(issuedBookList)) {
				return issuedBookList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;  
	}
	 public List getIssuedBookCountByStatusAndCustId(long bookTitleId ,String status,String type,long custId,String bookNumber) {
			try {
				String deleteStatus=Constants.NO_STRING;
				List avableBooksList =null;
				if(!StringFunctions.isNullOrEmpty(bookNumber)){
					avableBooksList=this.getAllHqlQuery("from BookLables where bookTitleId="+ bookTitleId +" and bookLabelStatus='"+status+"' and type='"+type+"' and custId="+custId+" and lableCode='"+bookNumber+"' and deleteStatus='"+deleteStatus+"' ");					
				}else{
					avableBooksList=this.getAllHqlQuery("from BookLables where bookTitleId="+ bookTitleId +" and bookLabelStatus='"+status+"' and type='"+type+"' and custId="+custId+" and deleteStatus='"+deleteStatus+"' ");
				}
				if (ObjectFunctions.isNotNullOrEmpty(avableBooksList)) {
					return avableBooksList;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	}
	 public void deleteBookLablesByBookTitleId(long bookTitleId){
		 try{
			 if(!ObjectFunctions.isNullOrEmpty(bookTitleId)){
				 StringBuffer sqlString =new StringBuffer();
				 sqlString.append("delete from bookLables where bookTitleId in ("+bookTitleId+")");
				 Query qry = getSession().createSQLQuery(sqlString.toString());
				 int row = qry.executeUpdate();
			 }
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
	 }
	 
	 public List getStudentRequestBooksByStudentAccountId(long studentAccountId, long custId,String status) {
			try {
				String todayDate=DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date());
				List studentRequestBooksList = this.getAllHqlQuery("from Reservations where accountId="+ studentAccountId +" and status='"+status+"' and custId="+custId+" and expiryDate>='"+todayDate+"'");
				if (ObjectFunctions.isNotNullOrEmpty(studentRequestBooksList)) {
					return studentRequestBooksList;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	}
	 public List getStudentBooksByStudentId(long studentId, long custId) {
			try {
				String todayDate=DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date());
				List issuedBookList = this.getAllHqlQuery("from IssuedBook where studentId="+ studentId +" and custId="+custId+" and dueDate>='"+todayDate+"'");
				if (ObjectFunctions.isNotNullOrEmpty(issuedBookList)) {
					return issuedBookList;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
	 public IssuedBook getSbumitedBooksByStudentIdAndBookCode(long studentAccountId, long custId,long bookLableId) {
		 try{
			 List issuedBookList=this.getAllHqlQuery("from IssuedBook where status='P' and accountId="+studentAccountId+" and bookLableId="+bookLableId+" and custId="+custId);
			 if(!ObjectFunctions.isNullOrEmpty(issuedBookList)){
				 return (IssuedBook)issuedBookList.get(0);
			 }
			 else{
				 return null;
			 }
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	 }
	 public Reservations bookSearchByBookNumberAndCustId(String searchBookNumber, long custId,String status){
			try {
				List requestedBooksList = this.getAllHqlQuery("from Reservations where bookReservationNo='"+ searchBookNumber +"' and status='"+status+"' and custId="+custId);
				 if(!ObjectFunctions.isNullOrEmpty(requestedBooksList)){
					 return (Reservations)requestedBooksList.get(0);
				 }
				 else{
					 return null;
				 }
			 }catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
	 public List getAllIssuedBooksByStatus(long custId,String status){
			try {
				List issuedBookList = this.getAllHqlQuery("from IssuedBook where status='"+ status +"' and custId="+custId);
				if (ObjectFunctions.isNotNullOrEmpty(issuedBookList)) {
					return issuedBookList;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
		}
 	public List getAllRequestedBooksByStatus(long custId,String status){
		try {
			String todayDate=DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date());
			List reservationBookList = this.getAllHqlQuery("from Reservations where status='"+ status +"' and custId="+custId+" and expiryDate >='"+todayDate+"'");
			if (ObjectFunctions.isNotNullOrEmpty(reservationBookList)) {
				return reservationBookList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	 public BookLables getbookLableIdBybookNumberAndStatusAndCustId(String bookNumber,String status,long custId) {
		 try{
			 List bookList=this.getAllHqlQuery("from BookLables where  lableCode='"+bookNumber+"'and bookLabelStatus='"+status+"' and custId="+custId);
			 if(!ObjectFunctions.isNullOrEmpty(bookList)){
				 return (BookLables)bookList.get(0);
			 }
			 else{
				 return null;
			 }
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	 }
	 public List<ViewIssuedBookAndSettings> getIssuedBooksByAcountId(long staffAcountId, long custId,String status){
		 try {
			 List issuedBookList = this.getAllHqlQuery("from ViewIssuedBookAndSettings where accountId="+ staffAcountId +" and status='"+status+"' and custId="+custId);
			 if (ObjectFunctions.isNotNullOrEmpty(issuedBookList)) {
				 return (List<ViewIssuedBookAndSettings>) issuedBookList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null;
	 }
	 public List getStaffRequestBooksByStaffAccountId(long staffAccountId, long custId,String status){
		 try {
			 	String todayDate=DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date());
				List receivedBookList = this.getAllHqlQuery("from IssuedBook where accountId="+ staffAccountId +" and status='"+status+"' and custId="+custId);
				if (ObjectFunctions.isNotNullOrEmpty(receivedBookList)) {
					return receivedBookList;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null;
	 }
	 public IssuedBook getSbumitedBooksByStaffIdAndBookCode(long staffAccountId, long custId,long bookLableId) {
		 try{
			 List issuedBookList=this.getAllHqlQuery("from IssuedBook where status='P' and accountId="+staffAccountId+" and bookLableId="+bookLableId+" and custId="+custId);
			 if(!ObjectFunctions.isNullOrEmpty(issuedBookList)){
				 return (IssuedBook)issuedBookList.get(0);
			 }
			 else{
				 return null;
			 }
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	 }
	 public List getAllRacksByBlockId(long blockId){
		 try {
			 List rackList = this.getAllHqlQuery("from RackDetails where blockId="+blockId );
			 if (ObjectFunctions.isNotNullOrEmpty(rackList)) {
				 return rackList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null;
	 }
	 public List getRacksBySubjectByCustId(long subjectId,long custId){
		 try {
			 List racksList = this.getAllHqlQuery("from RackDetails where subjectId='"+subjectId+"' and custId="+custId+" and blockId is not null" );
			 if (ObjectFunctions.isNotNullOrEmpty(racksList)) {
				 return racksList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null;
	 }
	 public List getRacksByOtherSubjectByCustId(String subjectName,long custId){
		 try {
			 List racksList = this.getAllHqlQuery("from RackDetails where otherSubjects='"+subjectName+"' and custId="+custId);
			 if (ObjectFunctions.isNotNullOrEmpty(racksList)) {
				 return racksList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null;
	 }
	 public List getBlocksByBlockId(String blockId){
		 try {
			 List racksList = this.getAllHqlQuery("from Block where id in "+blockId);
			 if (ObjectFunctions.isNotNullOrEmpty(racksList)) {
				 return racksList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null;
	 }
	 public List getRacksByBlockIdAndSubjectId(long blockId,long subjectId,long custId){
		 try {
			 List racksList = this.getAllHqlQuery("from RackDetails where subjectId="+subjectId+" and blockId="+blockId+" and custId="+custId);
			 if (ObjectFunctions.isNotNullOrEmpty(racksList)) {
				 return racksList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null;
	 }
	 public List getRacksByOtherSubjectBlocks(long blockId,String subjectName){
		 try {
			 List racksList = this.getAllHqlQuery("from RackDetails where otherSubjects='"+subjectName+"' and blockId="+blockId);
			 if (ObjectFunctions.isNotNullOrEmpty(racksList)) {
				 return racksList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null;
	 }
	 public List checkBlockNameByCustId(String blockName,long custId){
		 try {
			 StringBuffer queryString = new StringBuffer();
			 queryString.append("from Block where blockName='");
			 queryString.append(blockName);
			 queryString.append("' and custId=");
			 queryString.append(custId);
			 queryString.append("");
				List blockName1 =  this.getAllHqlQuery(queryString.toString());
	            if(!ObjectFunctions.isNullOrEmpty(blockName1))
	            {
	                Collections.sort(blockName1);
	                return blockName1;
	            }
	            return null;
	        } catch (RuntimeException re) {
	            log.error("Get failed", re);
	            throw re;
	        }
		 
	 }
	 public List<BookLables> getBooklablesByBookIdAndCustId(long bookId,String type,long custId){
		 try {
			 String status=Constants.NO_STRING;
				List bookLableList = this.getAllHqlQuery("from BookLables where type='"+type+"' and bookTitleId="+ bookId+" and custId="+custId+" and deleteStatus='"+status+"' ");
				if (ObjectFunctions.isNotNullOrEmpty(bookLableList)) {
					return (List<BookLables>) bookLableList;
				}
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return null; 
	 }
	 public Block getBlockByBlockName(String blockName){
		 try{
			 List blockList=this.getAllHqlQuery("from Block where  blockName='"+blockName+"' ");
			 if(!ObjectFunctions.isNullOrEmpty(blockList)){
				 return (Block)blockList.get(0);
			 }
			 else{
				 return null;
			 }
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	 }
	 public List checkRackName(String rackName,long blockId){
		 try {
	            StringBuffer queryString = new StringBuffer();
	            queryString.append("from RackDetails where ");
	            queryString.append(" rackName ='");
	            queryString.append(rackName);
	            queryString.append("' and blockId = ");
	            queryString.append(blockId);
	            queryString.append("");
	            List rackName1 =  this.getAllHqlQuery(queryString.toString());
	            
	            if(!ObjectFunctions.isNullOrEmpty(rackName1))
	            {
	                Collections.sort(rackName1);
	                return rackName1;
	            }
	            return null;
	        } catch (RuntimeException re) {
	            log.error("Get failed", re);
	            throw re;
	        }
		 
	 }
	 public RackDetails getRackByRackName(String rackaName){
		 try{
			 List RackDetailList=this.getAllHqlQuery("from RackDetails where rackName='"+rackaName+"' ");
			 if(!ObjectFunctions.isNullOrEmpty(RackDetailList)){
				 return (RackDetails)RackDetailList.get(0);
			 }
			 else{
				 return null;
			 }
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 return null;	 
	 }
	 public List getAllBooksByCustId(long custId){
		 try{
			 List booksList = this.getAllHqlQuery("from bookTitle where custId="+custId);
			 if (ObjectFunctions.isNotNullOrEmpty(booksList)) {
				 return booksList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null; 
	 }
	 public List getAllBlocksByCustId(long custId){
		 try{
			 List blocksList = this.getAllHqlQuery("from Block where custId="+custId);
			 if (ObjectFunctions.isNotNullOrEmpty(blocksList)) {
				 return blocksList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null; 
	 }
	 public IssuedBook saveIssuedBook(IssuedBook issuedBook){
		 try{
			 
			 this.saveObject(issuedBook);
		        return issuedBook;
		 }
		 catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		 return null;
	 }
	 public List getAllOtherSubjectsByCustId(long custId, String type){
		 try{
			 List otherBooksList = this.getAllHqlQuery("from RackDetails where type='"+type+"' and  custId="+custId);
			 if (ObjectFunctions.isNotNullOrEmpty(otherBooksList)) {
				 return otherBooksList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null; 
	 }
	 public List getBlocksByCustId(long custId){
		 try{
			 List blockList = this.getAllHqlQuery("from Block where custId="+custId);
			 if (ObjectFunctions.isNotNullOrEmpty(blockList)) {
				 return blockList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null; 
	 }
	 
	 public LibrarySettings getLibrarySettingsByCustId(long custId,long academicYearid){
		try{
			List librarySettingsList=this.getAllHqlQuery("from LibrarySettings where custId="+custId+" and academicYearid="+academicYearid);
			if(ObjectFunctions.isNotNullOrEmpty(librarySettingsList)){
				return (LibrarySettings)librarySettingsList.get(0);
			}
		}
		 catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		 return null;
	 }
	 public List getAllBookLablesByBookIdAndCustId(long bookId,long custId){
		 try{
			 String status=Constants.NO_STRING;
			 List booksList = this.getAllHqlQuery("from BookLables where bookTitleId="+bookId+" and custId="+custId+" and deleteStatus='"+status+"' ");
			 if (ObjectFunctions.isNotNullOrEmpty(booksList)) {
				 return booksList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null; 
	 }
	 public BookAndBlockDetails getBookAndBlockDetailsBycustIdAndBookId(long bookId,long custId){
		 try{
				List bookAndBlockDetailsList=this.getAllHqlQuery("from BookAndBlockDetails where custId="+custId+" and bookId="+bookId);
				if(ObjectFunctions.isNotNullOrEmpty(bookAndBlockDetailsList)){
					return (BookAndBlockDetails)bookAndBlockDetailsList.get(0);
				}
			}
			 catch (Exception ex) {
				 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			 return null;
	 }
	 public void updateBookTitleStatus(long bookTitleId,String status){
		 try{
			 if(!ObjectFunctions.isNullOrEmpty(bookTitleId)){
				 StringBuffer sqlString =new StringBuffer();
				 sqlString.append("update bookTitle set status='"+status+"' where Id ="+bookTitleId);
				 Query qry = getSession().createSQLQuery(sqlString.toString());
				 int row = qry.executeUpdate();
			 }
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
	 }
	 public List getAllRequestedBooksByStatus(String userStatus,long custId,String status,long subjectId){
		 try{
			 List reservationBookList = null;
			 if(subjectId > 0)
				 reservationBookList = this.getAllHqlQuery("from Reservations where subjectId="+ subjectId +" and status='"+status+"' and custId="+custId+" and userStatus='"+userStatus+"'");
			 else
				 reservationBookList = this.getAllHqlQuery("from Reservations where  status='"+status+"' and custId="+custId+" and userStatus='"+userStatus+"'");
			 if (ObjectFunctions.isNotNullOrEmpty(reservationBookList)) {
				 return reservationBookList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null; 
	 }
	 
	 public List getAllRenewalBooksByStatus(String userStatus,long custId,String status,long subjectId,String renewalStatus){
		 try{
			 List reservationBookList = null;
			 if(subjectId > 0)
				 reservationBookList = this.getAllHqlQuery("from IssuedBook where subjectId="+ subjectId +" and status='"+status+"' and custId="+custId+" and userStatus='"+userStatus+"' and renewalStatus='"+renewalStatus+"'");
			 else
				 reservationBookList = this.getAllHqlQuery("from IssuedBook where  status='"+status+"' and custId="+custId+" and userStatus='"+userStatus+"' and renewalStatus='"+renewalStatus+"'");
			 if (ObjectFunctions.isNotNullOrEmpty(reservationBookList)) {
				 return reservationBookList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null; 
	 }
	 public List getAllBooksIssuedToStudentOrStaff(String userStatus,long custId,String status,long classId){
		 try{
			 List issuedBookList = null;
			 if(classId > 0)
				 issuedBookList = this.getAllHqlQuery("from ViewIssuedBookAndSettings where classId="+ classId +" and status='"+status+"' and custId="+custId+" and userStatus='"+userStatus+"'");
			 else
				 issuedBookList = this.getAllHqlQuery("from ViewIssuedBookAndSettings where  status='"+status+"' and custId="+custId+" and userStatus='"+userStatus+"'");
			 if (ObjectFunctions.isNotNullOrEmpty(issuedBookList)) {
				 return issuedBookList;
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
		 return null; 
	 }
	 public void deleteBookLablesById(long id){
		 try{
			 if(!ObjectFunctions.isNullOrEmpty(id)){
				 StringBuffer sqlString =new StringBuffer();
				 sqlString.append("delete from bookLables where id="+id);
				 Query qry = getSession().createSQLQuery(sqlString.toString());
				 int row = qry.executeUpdate();
			 }
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
	 }
	 public void deleteIssuedBookById(long id){
		 try{
			 if(!ObjectFunctions.isNullOrEmpty(id)){
				 StringBuffer sqlString =new StringBuffer();
				 sqlString.append("delete from issuedBook where bookLableId="+id);
				 Query qry = getSession().createSQLQuery(sqlString.toString());
				 int row = qry.executeUpdate();
			 }
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
	 }
	 public void deleteBookTitleById(long id){
		 try{
			 if(!ObjectFunctions.isNullOrEmpty(id)){
				 StringBuffer sqlString =new StringBuffer();
				 sqlString.append("delete from bookTitle where id="+id);
				 Query qry = getSession().createSQLQuery(sqlString.toString());
				 int row = qry.executeUpdate();
			 }
		 }catch (Exception ex) {
			 ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		 }
	 }
	 
	 public List<Long> getIssueBookLables(long custId,String bookId){
		 String query = "SELECT bookLableId FROM issuedBook WHERE  custId="+custId+" and bookId="+bookId+" and status='"+Constants.ACTIVE_STATUS+"'";
		 Session session = this.sessionFactory.openSession();Transaction tx = session.beginTransaction();
		 try{
			 return  getSession().createSQLQuery(query.toString()).addScalar("bookLableId",StandardBasicTypes.LONG).list();
		 }
		 catch(Exception ex){
				ex.printStackTrace();			
		 }
		 finally {
			if(session != null) {
				session.close(); // extra work 
			} 
		}
	 return null;
	 
	 }
} 
