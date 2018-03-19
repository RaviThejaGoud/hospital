package com.urt.persistence.interfaces.library;



import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.urt.persistence.interfaces.base.UniversalDao;
import com.urt.persistence.model.library.Block;
import com.urt.persistence.model.library.BookAndBlockDetails;
import com.urt.persistence.model.library.BookLables;
import com.urt.persistence.model.library.BookTitle;
import com.urt.persistence.model.library.IssuedBook;
import com.urt.persistence.model.library.LibrarySettings;
import com.urt.persistence.model.library.RackDetails;
import com.urt.persistence.model.library.Reservations;
import com.urt.persistence.model.library.ViewIssuedBookAndSettings;

/**
 * User Data Access Object (Dao) interface.
 *
 * <p>
 * <a href="UserDao.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface LibraryDao extends UniversalDao {
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	BookTitle saveBookTitle(BookTitle bookTitle);
	
	List bookSearchResultsByKeywordAndCustId(String searchWord,long custId,String searchBy,long classId,long academicYearId,String reservationDate,String selectedType);
	List getIssuedBooksByStudentUserId(long studentUserId, long custId,String status);
	List getIssuedBookCountByStatusAndCustId(long bookTitleId ,String status,String type,long custId,String bookNumber);
	void deleteBookLablesByBookTitleId(long bookTitleId);
	List getStudentRequestBooksByStudentAccountId(long studenAccountId, long custId,String status);
	List getStudentBooksByStudentId(long studentId, long custId);
	IssuedBook getSbumitedBooksByStudentIdAndBookCode(long studentAccountId, long custId,long bookLableId);
	Reservations bookSearchByBookNumberAndCustId(String searchBookNumber, long custId,String status);
	List getAllIssuedBooksByStatus(long custId,String status);
	List getAllRequestedBooksByStatus(long custId,String status);
	BookLables getbookLableIdBybookNumberAndStatusAndCustId(String bookNumber,String status,long custId);
	List<ViewIssuedBookAndSettings> getIssuedBooksByAcountId(long staffAcountId, long custId,String status);
	List getStaffRequestBooksByStaffAccountId(long staffAccountId, long custId,String status);
	IssuedBook getSbumitedBooksByStaffIdAndBookCode(long staffAccountId, long custId,long bookLableId);
	List getAllRacksByBlockId(long blockId);
	List getRacksBySubjectByCustId(long subjectId,long custId);
	List getBlocksByBlockId(String blockId);
	List getRacksByBlockIdAndSubjectId(long blockId,long subjectId,long custId);
	List<Block> checkBlockNameByCustId(String blockName,long custId);
	List<RackDetails> checkRackName(String rackName,long blockId);
	List<BookLables> getBooklablesByBookIdAndCustId(long bookId,String type,long custId);
	Block getBlockByBlockName(String blockName);
	RackDetails getRackByRackName(String rackaName);
	List getAllBooksByCustId(long custId);
	List getAllBlocksByCustId(long custId);
	
	@Transactional(timeout=50, propagation = Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor = RuntimeException.class )
	IssuedBook saveIssuedBook(IssuedBook issuedBook);
	List getAllOtherSubjectsByCustId(long custId, String type);
	List getRacksByOtherSubjectByCustId(String subjectName,long custId);
	List getRacksByOtherSubjectBlocks(long blockId,String subjectName);
	List getBlocksByCustId(long custId);
	LibrarySettings getLibrarySettingsByCustId(long custId,long academicYearId);
	List getAllBookLablesByBookIdAndCustId(long bookId,long custId);
	BookAndBlockDetails getBookAndBlockDetailsBycustIdAndBookId(long bookId,long custId);
	List getStudySubjectByCustIdAndSearchword(String searchword,long custId);
	void updateBookTitleStatus(long bookTitleId,String status);
	List getAllRequestedBooksByStatus(String userStatus,long custId,String status,long subjectId);
	List getAllRenewalBooksByStatus(String userStatus,long custId,String status,long subjectId,String renewalStatus);
	List getAllBooksIssuedToStudentOrStaff(String userStatus,long custId,String status,long classId);

	void deleteBookLablesById(long id);
	void deleteIssuedBookById(long id);
	void deleteBookTitleById(long id);
	List<Long> getIssueBookLables(long custId,String bookId);

}
