package com.urt.service.manager.impl.library;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.google.gson.Gson;
import com.hyniva.sms.ws.vo.StudentIssuedBookDetailsVO;
import com.hyniva.sms.ws.vo.StudentLibraryBooksDetailsVO;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.interfaces.library.LibraryDao;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.library.Block;
import com.urt.persistence.model.library.BookAndBlockDetails;
import com.urt.persistence.model.library.BookLables;
import com.urt.persistence.model.library.BookTitle;
import com.urt.persistence.model.library.IssuedBook;
import com.urt.persistence.model.library.LibrarySettings;
import com.urt.persistence.model.library.RackDetails;
import com.urt.persistence.model.library.Reservations;
import com.urt.persistence.model.library.ViewIssuedBookAndSettings;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.impl.base.UniversalManagerImpl;
import com.urt.service.manager.interfaces.library.LibraryManager;


/**
 * Implementation of UserManager interface.</p>
 * 
 * <p>
 * <a href="UserManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 */
@Component
public class LibraryManagerImpl extends UniversalManagerImpl implements LibraryManager {
	
	@Autowired
	private LibraryDao libraryDao;

	public BookTitle saveBookTitle(BookTitle bookTitle){
		return libraryDao.saveBookTitle(bookTitle);
	}
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List bookSearchResultsByKeywordAndCustId(String searchWord, long custId,String searchBy,long classId,long academicYearId,String reservationDate,String selectedType) {
		 return libraryDao.bookSearchResultsByKeywordAndCustId(searchWord, custId,searchBy,classId,academicYearId,reservationDate,selectedType);
	 }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getIssuedBooksByStudentUserId(long studentUserId, long custId,String status) {
		 return libraryDao.getIssuedBooksByStudentUserId(studentUserId, custId,status);
	 }
	//@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getIssuedBookCountByStatusAndCustId(long bookTitleId,String status,String type,long custId,String bookNumber) {
		 return libraryDao.getIssuedBookCountByStatusAndCustId(bookTitleId,status,type,custId,bookNumber);
	 }
	 public void deleteBookLablesByBookTitleId(long bookTitleId) {
		 libraryDao.deleteBookLablesByBookTitleId(bookTitleId);
	 }
	 public void deleteBookLablesById(long id){
		 libraryDao.deleteBookLablesById(id);
	 }
	 public void deleteIssuedBookById(long id){
		 libraryDao.deleteIssuedBookById(id);
	 }
	 public void deleteBookTitleById(long id) {
		 libraryDao.deleteBookTitleById(id);	
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getStudentRequestBooksByStudentAccountId(long studentAccountId, long custId,String status){
		 return libraryDao.getStudentRequestBooksByStudentAccountId(studentAccountId, custId,status);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getStudentBooksByStudentId(long studentId, long custId) {
		 return libraryDao.getStudentBooksByStudentId(studentId, custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public IssuedBook getSbumitedBooksByStudentIdAndBookCode(long studentAccountId, long custId,long bookLableId){
			return libraryDao.getSbumitedBooksByStudentIdAndBookCode(studentAccountId,custId,bookLableId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public Reservations bookSearchByBookNumberAndCustId(String searchBookNumber, long custId,String status){
			return libraryDao.bookSearchByBookNumberAndCustId(searchBookNumber,custId, status);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getAllIssuedBooksByStatus(long custId,String status){
		 return libraryDao.getAllIssuedBooksByStatus(custId, status);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getAllRequestedBooksByStatus(long custId,String status){
		 return libraryDao.getAllRequestedBooksByStatus(custId, status);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public BookLables getbookLableIdBybookNumberAndStatusAndCustId(String bookNumber,String status,long custId){
			return libraryDao.getbookLableIdBybookNumberAndStatusAndCustId(bookNumber,status,custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<ViewIssuedBookAndSettings> getIssuedBooksByAcountId(long staffAcountId, long custId,String status){
		 return libraryDao.getIssuedBooksByAcountId(staffAcountId, custId, status);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getStaffRequestBooksByStaffAccountId(long staffAccountId, long custId,String status){
		 return libraryDao.getStaffRequestBooksByStaffAccountId(staffAccountId, custId, status);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public IssuedBook getSbumitedBooksByStaffIdAndBookCode(long staffAccountId, long custId,long bookLableId){
		 return libraryDao.getSbumitedBooksByStaffIdAndBookCode(staffAccountId,custId,bookLableId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getAllRacksByBlockId(long blockId){
		 return libraryDao.getAllRacksByBlockId(blockId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getRacksBySubjectByCustId(long subjectId,long custId){
		 return libraryDao.getRacksBySubjectByCustId(subjectId,custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getBlocksByBlockId(String blockId){
		 return libraryDao.getBlocksByBlockId(blockId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getRacksByBlockIdAndSubjectId(long blockId,long subjectId,long custId){
		 return libraryDao.getRacksByBlockIdAndSubjectId(blockId,subjectId,custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<Block> checkBlockNameByCustId(String blockName,long custId){
		 return libraryDao.checkBlockNameByCustId(blockName,custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<RackDetails> checkRackName(String rackName,long blockId){
		 return libraryDao.checkRackName(rackName,blockId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List<BookLables> getBooklablesByBookIdAndCustId(long bookId,String type,long custId){
		 return libraryDao.getBooklablesByBookIdAndCustId(bookId,type,custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public Block getBlockByBlockName(String blockName){
		 return libraryDao.getBlockByBlockName(blockName);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public RackDetails getRackByRackName(String rackaName){
		 return libraryDao.getRackByRackName(rackaName);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getAllBooksByCustId(long custId){
		 return libraryDao.getAllBooksByCustId(custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getAllBlocksByCustId(long custId){
		 return libraryDao.getAllBlocksByCustId(custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public IssuedBook saveIssuedBook(IssuedBook issuedBook){
		 return libraryDao.saveIssuedBook(issuedBook);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getAllOtherSubjectsByCustId(long custId, String type){
		 return libraryDao.getAllOtherSubjectsByCustId(custId, type);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getRacksByOtherSubjectByCustId(String subjectName,long custId){
		 return libraryDao.getRacksByOtherSubjectByCustId(subjectName, custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getRacksByOtherSubjectBlocks(long blockId,String subjectName){
		 return libraryDao.getRacksByOtherSubjectBlocks(blockId,subjectName);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getBlocksByCustId(long custId){
		 return libraryDao.getBlocksByCustId(custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public LibrarySettings getLibrarySettingsByCustId(long custId,long academicYearId){
		 return libraryDao.getLibrarySettingsByCustId(custId,academicYearId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getAllBookLablesByBookIdAndCustId(long bookId,long custId){
		 return libraryDao.getAllBookLablesByBookIdAndCustId(bookId,custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public BookAndBlockDetails getBookAndBlockDetailsBycustIdAndBookId(long bookId,long custId){
		 return libraryDao.getBookAndBlockDetailsBycustIdAndBookId(bookId,custId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getStudySubjectByCustIdAndSearchword(String searchword,long custId){
		 return libraryDao.getStudySubjectByCustIdAndSearchword(searchword,custId);
	 }
	 public void updateBookTitleStatus(long bookTitleId,String status){
		  libraryDao.updateBookTitleStatus(bookTitleId,status);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getAllRequestedBooksByStatus(String userStatus,long custId,String status,long subjectId){
		 return libraryDao.getAllRequestedBooksByStatus(userStatus,custId,status,subjectId);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getAllRenewalBooksByStatus(String userStatus,long custId,String status,long subjectId,String renewalStatus){
		 return libraryDao.getAllRenewalBooksByStatus(userStatus,custId,status,subjectId,renewalStatus);
	 }
	 //@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	 public List getAllBooksIssuedToStudentOrStaff(String userStatus,long custId,String status,long classId){
		 return libraryDao.getAllBooksIssuedToStudentOrStaff(userStatus,custId,status,classId);
	 }
	 
	 public void sendLibraryNotification(User user, Customer customer, Messages messages, IssuedBook issueBooks){
		 try{
			 if(!ObjectFunctions.isNullOrEmpty(user.getStudentParent()))
			 {
			 StringBuffer accountIdbuffer = new StringBuffer("(");
			 accountIdbuffer.append(user.getStudentParent().getId());
			 accountIdbuffer.append(")");
			 String message = "Library Book has been Issued.";
	
				//StudentLibraryBooksVO studentLibraryBooksVO = null;
				List<StudentLibraryBooksDetailsVO> studentLibraryBooksDetailsVOs = new ArrayList<StudentLibraryBooksDetailsVO>();
				ViewIssuedBookAndSettings studentIssuedBook =  (ViewIssuedBookAndSettings)this.get(ViewIssuedBookAndSettings.class,issueBooks.getId());
				if(!ObjectFunctions.isNullOrEmpty(studentIssuedBook)){
					//studentLibraryBooksVO = new StudentLibraryBooksVO();
					StudentLibraryBooksDetailsVO studentLibraryBooksDetailsVO = new StudentLibraryBooksDetailsVO();
					List<StudentIssuedBookDetailsVO> listStudentIssuedBooks = new ArrayList<StudentIssuedBookDetailsVO>();
					StudentIssuedBookDetailsVO issueBook = new StudentIssuedBookDetailsVO();
						studentLibraryBooksDetailsVO.setStudentAccountId(studentIssuedBook.getAccountId());
						
						issueBook.setBookId(studentIssuedBook.getBookTitleId());
						issueBook.setBookName(studentIssuedBook.getBookName());
						issueBook.setSubjectId(studentIssuedBook.getSubjectId());
						issueBook.setSubjectName(studentIssuedBook.getName());
						issueBook.setIssuedDate(ObjectFunctions.isNullOrEmpty(studentIssuedBook.getIssuedDate())? "":DateFunctions.convertDateToString(studentIssuedBook.getIssuedDate()));
						issueBook.setDueDate(ObjectFunctions.isNullOrEmpty(studentIssuedBook.getDueDate())? "":DateFunctions.convertDateToString(studentIssuedBook.getDueDate()));
						
						listStudentIssuedBooks.add(issueBook);
						studentLibraryBooksDetailsVO.setIssuedBooks(listStudentIssuedBooks);
						
						studentLibraryBooksDetailsVOs.add(studentLibraryBooksDetailsVO);
					
				}
			
		 		JSONObject main = new JSONObject();
				JSONObject subVal = new JSONObject();
				main.put("notificationFor", "Library");
				subVal.put("description", messages.getMessageDescription());
				subVal.put("title", message);
	//			subVal.put("studentLibraryBooksVOs",new Gson().toJson(studentLibraryBooksDetailsVOs));
				subVal.put("studentLibraryBooksVOs",new JSONArray(new Gson().toJson(studentLibraryBooksDetailsVOs)));
				main.put("information", subVal);
				
				this.sendNotificationToAndroidMobileUsersByUserIds(0,main.toString(),accountIdbuffer.toString()); //To add notification for mobile app.
			}
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	@Override
	public List<Long> getIssueBookLables(long custId, String bookId) {
		
		return libraryDao.getIssueBookLables(custId,bookId);
	}
}
