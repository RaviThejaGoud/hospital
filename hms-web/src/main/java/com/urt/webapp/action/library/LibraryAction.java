/*******************************************************************************
 * Copyright (c) 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.urt.webapp.action.library;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFormatter;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.date.DateUtil;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.urt.exception.base.URTUniversalException;
import com.urt.json.JSONArray;
import com.urt.json.JSONObject;
import com.urt.persistence.model.common.AcademicYear;
import com.urt.persistence.model.common.Messages;
import com.urt.persistence.model.common.ViewAllUsers;
import com.urt.persistence.model.library.Block;
import com.urt.persistence.model.library.BookAndBlockDetails;
import com.urt.persistence.model.library.BookLables;
import com.urt.persistence.model.library.BookTitle;
import com.urt.persistence.model.library.BooksAssignToRack;
import com.urt.persistence.model.library.IssuedBook;
import com.urt.persistence.model.library.LibrarySettings;
import com.urt.persistence.model.library.RackDetails;
import com.urt.persistence.model.library.Reservations;
import com.urt.persistence.model.library.ViewBookAndLabelDetails;
import com.urt.persistence.model.library.ViewIssuedBookAndSettings;
import com.urt.persistence.model.library.ViewStudentIssuebookAndReservations;
import com.urt.persistence.model.study.ClassName;
import com.urt.persistence.model.study.Student;
import com.urt.persistence.model.study.StudySubject;
import com.urt.persistence.model.study.ViewStaffPersonAccountDetails;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.User;
import com.urt.util.common.RayGunException;
import com.urt.util.excel.library.PrepareLibraryBooksExcel;
import com.urt.util.excel.parser.ExcelParsingException;
import com.urt.util.excel.parser.SheetParser;
import com.urt.webapp.action.base.BaseAction;

@Namespace("/library")
@Actions({
		@Action(value = "ajaxDoGetSchoolWideBooksList", results = { @Result(location = "ajaxBookDetails.jsp", name = "success" )}),		
		@Action(value = "ajaxDoReturntBooks", results = { @Result(location = "ajaxReturnBooks.jsp", name = "success" )}) ,
		@Action(value = "ajaxAddIssuedBookDetails", results = { @Result(type = "redirect", location = "libraryHome.jsp", name = "success") }) ,
		@Action(value = "ajaxDoAddFineDetails",  results = { @Result(location = "block/ajaxShowRacks.jsp", name = "success" )}),
		@Action(value = "ajaxDoAddLibrarySettings",  results = { @Result(location = "librarySettings/ajaxAddLibrarySettings.jsp", name = "success" )}),
        @Action(value = "ajaxBookFinesList", results = { @Result(location = "ajaxBookFineTransctions.jsp", name = "success" )}) ,
		 @Action(value = "ajaxDoImportSchoolBooks", results = { @Result(location = "ajaxImportSchoolBooks.jsp", name = "success") }),
		 @Action(value = "ajaxDoEditSchoolBooks", results = { @Result(location = "ajaxEditSchoolBooks.jsp", name = "success") }) 	
	})
        
        
public class LibraryAction extends BaseAction {

	private static final long serialVersionUID = -1646390427462403153L;
	private static String  alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private BookTitle bookTitle;
	private Block block;
	private RackDetails rackDetails;
	private Reservations reservations;
	private BookAndBlockDetails bookAndBlockDetails;
	private IssuedBook issuedBook;
	private BookLables bookLables;
	private RackDetails blockBookDetails;
	private String searchWord;
	private List bookTitleList;
	private String searchBookNumber;
	private long selectedId2;
	private List rackDetailList;
	private List blockList;
	private LibrarySettings librarySettings;
	private String selectedId3;
	private String bookNumber; 	
    private long blockId;
    private long bookId;    
    
    
	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getBlockId() {
		return blockId;
	}

	public void setBlockId(long blockId) {
		this.blockId = blockId;
	}
	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getSelectedId3() {
		return selectedId3;
	}

	public void setSelectedId3(String selectedId3) {
		this.selectedId3 = selectedId3;
	}

	public LibrarySettings getLibrarySettings() {
		return librarySettings;
	}

	public void setLibrarySettings(LibrarySettings librarySettings) {
		this.librarySettings = librarySettings;
	}

	public List getBlockList() {
		if(ObjectFunctions.isNullOrEmpty(this.blockList)){
			this.blockList=new ArrayList();
		}
		return this.blockList;
	}

	public void setBlockList(List blockList) {
		this.blockList = blockList;
	}

	public String getAutoCheck() {
		return super.autoCheck;
	}
	
	 
	/**
	 * @return the bookAndBlockDetails
	 */
	public BookAndBlockDetails getBookAndBlockDetails() {
		return bookAndBlockDetails;
	}

	/**
	 * @param bookAndBlockDetails the bookAndBlockDetails to set
	 */
	public void setBookAndBlockDetails(BookAndBlockDetails bookAndBlockDetails) {
		this.bookAndBlockDetails = bookAndBlockDetails;
	}

	/**
	 * @return the rackDetailList
	 */
	public List getRackDetailList() {
		if(ObjectFunctions.isNullOrEmpty(this.rackDetailList)){
			this.rackDetailList=new ArrayList();
		}
		return this.rackDetailList;
	}

	/**
	 * @param rackDetailList the rackDetailList to set
	 */
	public void setRackDetailList(List rackDetailList) {
		this.rackDetailList = rackDetailList;
	}

	/**
	 * @return the block
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(Block block) {
		this.block = block;
	}

	/**
	 * @return the rackDetails
	 */
	public RackDetails getRackDetails() {
		return rackDetails;
	}

	/**
	 * @param rackDetails the rackDetails to set
	 */
	public void setRackDetails(RackDetails rackDetails) {
		this.rackDetails = rackDetails;
	}

	/**
	 * @return the selectedId2
	 */
	public long getSelectedId2() {
		return selectedId2;
	}

	/**
	 * @param selectedId2 the selectedId2 to set
	 */
	public void setSelectedId2(long selectedId2) {
		this.selectedId2 = selectedId2;
	}
	
	/**
	 * @return the reservations
	 */
	public Reservations getReservations() {
		return reservations;
	}
	/**
	 * @param reservations the reservations to set
	 */
	public void setReservations(Reservations reservations) {
		this.reservations = reservations;
	}
	/**
	 * @return the searchBookNumber
	 */
	public String getSearchBookNumber() {
		return searchBookNumber;
	}
	/**
	 * @param searchBookNumber the searchBookNumber to set
	 */
	public void setSearchBookNumber(String searchBookNumber) {
		this.searchBookNumber = searchBookNumber;
	}
	/**
	 * @return the issuedBook
	 */
	public IssuedBook getIssuedBook() {
		return issuedBook;
	}
	/**
	 * @param issuedBook the issuedBook to set
	 */
	public void setIssuedBook(IssuedBook issuedBook) {
		this.issuedBook = issuedBook;
	}
	/**
	 * @return the bookTitleList
	 */
	public List getBookTitleList() {
		if (ObjectFunctions.isNullOrEmpty(this.bookTitleList)) {
			this.bookTitleList = new ArrayList();
		}
		return this.bookTitleList;
	}
	/**
	 * @param bookTitleList the bookTitleList to set
	 */
	public void setBookTitleList(List bookTitleList) {
		this.bookTitleList = bookTitleList;
	}
	/**
	 * @return the searchWord
	 */
	public String getSearchWord() {
		return searchWord;
	}
	/**
	 * @param searchWord the searchWord to set
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	/**
	 * @return the blockBookDetails
	 */
	public RackDetails getBlockBookDetails() {
		return blockBookDetails;
	}
	/**
	 * @param blockBookDetails the blockBookDetails to set
	 */
	public void setBlockBookDetails(RackDetails blockBookDetails) {
		this.blockBookDetails = blockBookDetails;
	}
	/**
	 * @return the bookLables
	 */
	public BookLables getBookLables() {
		if (ObjectFunctions.isNullOrEmpty(this.bookLables)) {
			this.bookLables = new BookLables();
		}
		return bookLables;
	}
	/**
	 * @param bookLables the bookLables to set
	 */
	public void setBookLables(BookLables bookLables) {
		this.bookLables = bookLables;
	}
	/**
	 * @return the bookTitle
	 */
	public BookTitle getBookTitle() {
		return bookTitle;
	}
	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(BookTitle bookTitle) {
		this.bookTitle = bookTitle;
	}
	/* @Description 15rd Apr cvs: Modularization remove the unnecessary code*/ 
	@Actions({
		@Action(value = "ajaxLibraryHome", results = { @Result(location = "libraryHome.jsp", name = "success") }),
		@Action(value = "libraryHome", results = { @Result(location = "libraryHome.jsp", name = "success") }) })
		public String home() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'library Home' method");
			}
			try {
			 StringBuffer sqlQuery = null;
				if(getUserCustId() > 0){
	            	/*sqlQuery = new StringBuffer("select  bt.id ,bt.bookName,ss.name,bt.otherSubjects,bt.author,bt.publisher, bt.noOfCopies ," +
	            			"(SELECT count(*)  FROM bookLables bls  WHERE bls.bookTitleId = bt.Id and bls.custId="+getUserCustId()+" and bls.bookLabelStatus='"+Constants.BOOK_OPEND+"' and bls.type='"+Constants.ISSUE_BOOK+"' and bls.deleteStatus='"+Constants.NO_STRING+"') As booksCount," +
						"  bt.issueBookCount,bt.acquisitionNumber,bt.bookEdition,bl.lableCode  from bookTitle bt LEFT JOIN bookLables  bl on (bt.id = bl.bookTitleId) LEFT JOIN studySubject ss on (bt.subjectId=ss.id)")
	            	 .append(" where bt.custId=").append(getUserCustId()).append(" and bt.academicYearId <="+getUserAcademicYearId()).append(" and bl.deleteStatus='"+Constants.NO_STRING+"'").append(" group by bl.bookTitleId");
	            	log.debug(sqlQuery.toString());*/
					if(!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("showTaskReminder")))
						getTaskReminderToUserLogin();
					List<BookTitle> bookTitleList =  libraryManager.getAll(BookTitle.class," custId="+getUserCustId()+" and academicYearId<="+getUserAcademicYearId() +" and status='"+Constants.YES_STRING+"'");
					if(!ObjectFunctions.isNullOrEmpty(bookTitleList)){
						for(BookTitle bookTitle : bookTitleList)
						{
							/*List<IssuedBook> issuedBookList = libraryManager.getAll(IssuedBook.class,"custId = "+getUserCustId()+ " and bookId="+bookTitle.getId()+"");
							if(!ObjectFunctions.isNullOrEmpty(issuedBookList))
								bookTitle.setIssuedBooksCount((bookTitle.getIssueBookCount() - issuedBookList.size()));
							else
								bookTitle.setIssuedBooksCount(bookTitle.getNoOfCopies());*/
							//Siva: For Total no of available book labels
							String sqlQuery1 = "SELECT id,count(*)  FROM bookLables  WHERE custId="+getUserCustId()+" and bookTitleId="+bookTitle.getId()+" AND deleteStatus!='Y'";
							Object[] booksLabAry = adminManager.get(sqlQuery1);
							if(!ObjectFunctions.isNullOrEmpty(booksLabAry)){
								   if(Integer.parseInt(booksLabAry[1].toString()) > 0){
									   bookTitle.setNoOfCopies(Integer.valueOf(booksLabAry[1].toString()));
								   }
								   else{
									   bookTitle.setNoOfCopies(0);
								   }
							}
							//Siva: For issued books with not returned status books i.e. status='S'
							String sqlQuery2 = "SELECT id,count(*)  FROM issuedBook  WHERE custId="+getUserCustId()+" and bookId="+bookTitle.getId()+" AND status !='S'";
							Object[] issBooksAry = adminManager.get(sqlQuery2);
							if(!ObjectFunctions.isNullOrEmpty(issBooksAry)){
								   if(Integer.parseInt(issBooksAry[1].toString()) > 0){
									   bookTitle.setIssuedBooksCount(bookTitle.getNoOfCopies() - Integer.valueOf(issBooksAry[1].toString()));
								   }
								   else{
									   bookTitle.setIssuedBooksCount(bookTitle.getNoOfCopies());
								   }
							}
							//END
							getObjectList().add(bookTitle);
							bookTitle=null;
						}
					}
					
				} 
			} catch (Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	/*
	 * prepareAcademicYearId() commented by ramadevi,Because here academicyearid
	 * getting from session
	 */
	@Actions( { @Action(value = "ajaxDoAddSchoolWideBooks", results = { @Result(location = "ajaxAddSchoolWideBook.jsp", name = "success") }) })
	public String doAddSchoolWideBooks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doAddSchoolWideBooks' method");
		}
		try {			
			setClassList(adminManager.getAllClassNames( getUserCustId(),getUserAcademicYearId()));
			setSubjectsList(adminManager.GetAllStudySubjects(getUserCustId(),getUserAcademicYearId()));
			setObjectList(libraryManager.getAllOtherSubjectsByCustId(getUserCustId(),"OS"));
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* @Description 30th Apr cvs: Modularization  below method  remove the unnecessary code  change the  LibraryAction*/
	@Actions({
        @Action(value = "ajaxDoEditSchoolWideBook", results = { @Result(location = "editSchoolWideBook.jsp", name = "success" )})})
        public String ajaxDoEditSchoolWideBook() throws URTUniversalException {       
        if (log.isDebugEnabled()) {
            log.debug("Entering 'ajaxDoEditSchoolWideBook' method");
        }
        try  {   
        	setClassList(adminManager.getAllClassNames( getUserCustId(),getUserAcademicYearId())); 			
			setSubjectsList(adminManager.GetAllStudySubjects(getUserCustId(),getUserAcademicYearId()));        	
			setTempList(libraryManager.getAllOtherSubjectsByCustId(getUserCustId(),"OS"));          
			setRackDetailList(libraryManager.getAllOtherSubjectsByCustId(getUserCustId(),"S"));			
			setBlockList(libraryManager.getBlocksByCustId(getUserCustId())); 			
			setBookTitle((BookTitle)libraryManager.get(BookTitle.class,getBookId()));			
            setBookAndBlockDetails((BookAndBlockDetails)libraryManager.getBookAndBlockDetailsBycustIdAndBookId(getBookId(),getUserCustId()));  
         }
        catch(Exception ex)
        {
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return SUCCESS;
    }
	
	/* @Description 30th Apr cvs: Modularization  below method  remove the unnecessary code  change the  LibraryAction*/
	@Actions( { @Action(value = "ajaxAddSchoolWideBooks", results = { @Result(location = "ajaxBookDetails.jsp", name = "success" ) }) })
    public String addSchoolWideBooks() throws URTUniversalException {
    if (log.isDebugEnabled()) {
        log.debug("Entering 'ajaxAddSchoolWideBooks' method");
    }
    try {
        if(!ObjectFunctions.isNullOrEmpty(getUserCustId())){
        	AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, getUserAcademicYearId());
            getBookTitle().setCustId(getUserCustId());
            int noOfCopyes=getBookTitle().getReadingBookCount()+getBookTitle().getIssueBookCount();
            getBookTitle().setNoOfCopies(noOfCopyes);
            getBookTitle().setTotalCost(noOfCopyes*(getBookTitle().getCost()));
            if("N".equalsIgnoreCase(getParamValue("type"))){
                StudySubject studySubject=(StudySubject)libraryManager.get(StudySubject.class, Long.valueOf(getSubjectId()));
                getBookTitle().setStudySubject(studySubject);
                  ClassName className=(ClassName) libraryManager.get(ClassName.class,Long.valueOf(getClassId()));
                getBookTitle().setClassName(className);
            }
            if("R".equalsIgnoreCase(getParamValue("type"))){
                getBookTitle().setClassName(null);
                getBookTitle().setOtherSubjects(getParamValue("otherSubject"));
            }
            String bookLable= getBookTitle().getBookKeyWord();
            String bookNumber = null;
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int n=noOfCopyes;
            String randamNumber=Math.round(Math.random()*100)+DateFormatter.formatDate(DateFormatter.DD_PATTERN, new Date());
            //String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char ch=0;
            char chr=0;
            int k=0;
            if(!ObjectFunctions.isNullOrEmpty(getBookTitle().getReadingBookCount())){
            	k=1;
                for(int i=1;i<=getBookTitle().getReadingBookCount();i++) {
                    getBookLables().setBookLabelStatus(Constants.BOOK_OPEND);
                    getBookLables().setType(Constants.READING_BOOK);
                    getBookLables().setDeleteStatus(Constants.NO_STRING);
                    getBookLables().setCustId(getUserCustId());
                    if(alphabet.charAt(k)=='Z'){
                    	k=1;
                    }
                    ch= alphabet.charAt(k);
                    bookNumber = bookLable + year + randamNumber+ ch + i;
                    getBookLables().setLableCode(bookNumber);
                    getBookTitle().addBookLablesSettings(getBookLables());
                    setBookLables(null);
                    k++;
                }
            }
            if(!ObjectFunctions.isNullOrEmpty(getBookTitle().getIssueBookCount())){
            	k=1;
                for(int j=getBookTitle().getReadingBookCount()+1;j<=n;j++){
                    getBookLables().setBookLabelStatus(Constants.BOOK_OPEND);
                    getBookLables().setCustId(getUserCustId());
                    getBookLables().setType(Constants.ISSUE_BOOK);
                    if(alphabet.charAt(k)=='Z'){
                        k=1;
                    }
                    chr= alphabet.charAt(k);
                    bookNumber = bookLable + year + randamNumber+ chr + j;
                    getBookLables().setLableCode(bookNumber);
                    getBookLables().setDeleteStatus(Constants.NO_STRING);
                    getBookTitle().addBookLablesSettings(getBookLables());
                    setBookLables(null);
                    k++;
                }
            }
            getBookTitle().setAcademicYear(academicYear);
            setBookTitle((BookTitle)libraryManager.saveBookTitle(getBookTitle()));
            if(!ObjectFunctions.isNullOrEmpty(getBlockId())){      
                Block block=(Block)libraryManager.get(Block.class,getBlockId());
                RackDetails rackDetails=(RackDetails)libraryManager.get(RackDetails.class, getTempId());//tempId=rackId
                if(!ObjectFunctions.isNullOrEmpty(getBookTitle())){
                    BookAndBlockDetails blockDetails=new BookAndBlockDetails();
                    blockDetails.setBookTitle(getBookTitle());
                    blockDetails.setBlock(block);
                    blockDetails.setRackDetails(rackDetails);
                    blockDetails.setCustId(getUserCustId());
                    libraryManager.save(blockDetails);
                }
            }
            }
            home();
           super.addActionMessage("Book details added sucessfully.");
        }
        catch(Exception ex) {
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return SUCCESS;
    }
	@Actions({
        @Action(value = "ajaxEditSchoolWideBook", results = { @Result(location = "ajaxBookDetails.jsp", name = "success" )})
        })
        public String ajaxEditSchoolWideBook() throws URTUniversalException {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'ajaxEditSchoolWideBook' method");
        }
        try {
        	if (!ObjectFunctions.isNullOrEmpty(getBookId())) {        		
		    	BookTitle bookTitle = (BookTitle) libraryManager.get(BookTitle.class,getBookId());
	            if (!ObjectFunctions.isNullOrEmpty(bookTitle)) {
	            	int noOfCopyes=getBookTitle().getReadingBookCount()+getBookTitle().getIssueBookCount();
	            	bookTitle.setBookName(getBookTitle().getBookName());
	                bookTitle.setAuthor(getBookTitle().getAuthor());
	                bookTitle.setPublisher(getBookTitle().getPublisher());
	                //bookTitle.setSubject(getBookTitle().getSubject());
	                bookTitle.setBookRequestExpareDays(getBookTitle().getBookRequestExpareDays());
	                bookTitle.setNoOfCopies(noOfCopyes);
	                bookTitle.setBookKeyWord(getBookTitle().getBookKeyWord());
	                bookTitle.setCustId(getUserCustId());
	                bookTitle.setTotalCost(noOfCopyes*(getBookTitle().getCost()));
	                bookTitle.setCost(getBookTitle().getCost());
	                bookTitle.setReadingBookCount(getBookTitle().getReadingBookCount());
	                bookTitle.setIssueBookCount(getBookTitle().getIssueBookCount());
	                bookTitle.setIssueDays(getBookTitle().getIssueDays());
	                if("N".equalsIgnoreCase(getParamValue("type"))){  
			    		//ClassName className=(ClassName) libraryManager.get(ClassName.class,bookTitle.getClassName().getId());
	                	ClassName className=bookTitle.getClassName();
			    		bookTitle.setClassName(className);
			    		StudySubject studySubject=(StudySubject)libraryManager.get(StudySubject.class,getBookTitle().getStudySubject().getId());
			    		bookTitle.setStudySubject(studySubject);
		    		}
		    		if("R".equalsIgnoreCase(getParamValue("type"))){
		    			bookTitle.setClassName(null); 
		    			//bookTitle.setOtherSubjects(getBookAndBlockDetails().getRackDetails().getOtherSubjects());
		    		}
	                //String bookLable= getBookTitle().getBookKeyWord();
	                String bookNumber = null;
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					//totalBookLableSize=totalBookLableSize+1;
					String randamNumber=Math.round(Math.random()*100)+DateFormatter.formatDate(DateFormatter.DD_PATTERN, new Date());
					//String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		    		char ch=0;
		    		char chr=0;
		    		int k=0;
		    		List<BookLables> ibBookLablesList=libraryManager.getBooklablesByBookIdAndCustId(getBookId(),Constants.ISSUE_BOOK,getUserCustId());
			    	List<BookLables> rbBookLablesList=libraryManager.getBooklablesByBookIdAndCustId(getBookId(),Constants.READING_BOOK,getUserCustId());
			    	/*if(ObjectFunctions.isNullOrEmpty(rbBookLablesList)){
			    		k=1;
			    		if(!ObjectFunctions.isNullOrEmpty(getBookTitle().getReadingBookCount())){
			    			for(int i=1;i<=getBookTitle().getReadingBookCount();i++){
			    				//getBookLables().setBookTitleId(getBookTitle().getId());
			    				getBookLables().setBookLabelStatus(Constants.BOOK_OPEND);
			    				getBookLables().setType("RB");
			    				getBookLables().setDeleteStatus(Constants.NO_STRING);
			    				getBookLables().setCustId(getUserCustId());
			    				if(alphabet.charAt(k)=='Z'){
			    					k=1;
			    				}
			    				ch= alphabet.charAt(i);
			    				bookNumber = bookLable + year + randamNumber+ ch + i;
			    				getBookLables().setLableCode(bookNumber);
			    				//libraryManager.save(getBookLables());
			    				bookTitle.addBookLablesSettings(getBookLables());
			                    setBookLables(null);
			                    k++;
			    			}
			    		}
			    	}else{*/
			    		int totalBookLableSize=ibBookLablesList.size()+rbBookLablesList.size();	
			    		k=1;
			    		if(rbBookLablesList.size()<getBookTitle().getReadingBookCount()){
			    			for(int i=totalBookLableSize+1;i<=getBookTitle().getReadingBookCount()+rbBookLablesList.size();i++){
			    				//getBookLables().setBookTitleId(getBookTitle().getId());
			    				getBookLables().setBookLabelStatus(Constants.BOOK_OPEND);
			    				getBookLables().setType(Constants.READING_BOOK);
			    				getBookLables().setCustId(getUserCustId());
			    				if(alphabet.charAt(k)=='Z'){
			    					k=1;
			    				}
			    				chr= alphabet.charAt(i);
			    				bookNumber =  getBookTitle().getBookKeyWord() + year + randamNumber+ ch + i;
			    				getBookLables().setLableCode(bookNumber);
			    				getBookLables().setDeleteStatus(Constants.NO_STRING);
			    				//libraryManager.save(getBookLables());
			    				bookTitle.addBookLablesSettings(getBookLables());
			                    setBookLables(null);
			                    k++;
			    			}
			    			bookNumber = null;
			    		}
			    	//}
		    		//List<BookLables> ibBookLablesList1=libraryManager.getBooklablesByBookIdAndCustId(getBookId(),"IB",getUserCustId());
			    	//List<BookLables> rbBookLablesList1=libraryManager.getBooklablesByBookIdAndCustId(getBookId(),"RB",getUserCustId());
			    	/*if(ObjectFunctions.isNullOrEmpty(ibBookLablesList1)){
			    		int n=getBookTitle().getIssueBookCount();
			    		for(int i=1;i<=getBookTitle().getIssueBookCount();i++){
		    				//getBookLables().setBookTitleId(getBookTitle().getId());
		    				getBookLables().setBookLabelStatus(Constants.BOOK_OPEND);
		    				getBookLables().setType("IB");
		    				getBookLables().setDeleteStatus(Constants.NO_STRING);
		    				getBookLables().setCustId(getUserCustId());
		    				if(alphabet.charAt(k)=='Z'){
		    					k=1;
		    				}
		    				ch= alphabet.charAt(i);
		    				bookNumber = bookLable + year + randamNumber+ ch + i;
		    				getBookLables().setLableCode(bookNumber);
		    				//libraryManager.save(getBookLables());
		    				bookTitle.addBookLablesSettings(getBookLables());
		                    setBookLables(null);
		                    k++;
		    			}
			    	}else{*/
			    		int totalBookLableSize1=ibBookLablesList.size()+rbBookLablesList.size();
			    		k=1;
			    		if(ibBookLablesList.size()<getBookTitle().getIssueBookCount()){
			    			for(int j=totalBookLableSize1+1;j<=getBookTitle().getIssueBookCount()+rbBookLablesList.size();j++){
			    				//getBookLables().setBookTitleId(getBookTitle().getId());
			    				getBookLables().setBookLabelStatus(Constants.BOOK_OPEND);
			    				getBookLables().setType(Constants.ISSUE_BOOK);
			    				getBookLables().setCustId(getUserCustId());
			    				//char ch = alphabet.charAt(j);
			    				if(alphabet.charAt(k)=='Z'){
			    					k=1;
			    				}
			    				chr= alphabet.charAt(j);
			    				bookNumber = getBookTitle().getBookKeyWord() + year + randamNumber+ chr + j;
			    				getBookLables().setLableCode(bookNumber);
			    				getBookLables().setDeleteStatus(Constants.NO_STRING);
			    				//libraryManager.save(getBookLables());
			    				bookTitle.addBookLablesSettings(getBookLables());
			                    setBookLables(null);
			                    k++;
			    			}
			    			bookNumber = null;
			    		}
			    	//}
			    	 setBookTitle((BookTitle)libraryManager.saveBookTitle(bookTitle));
		                if(!ObjectFunctions.isNullOrEmpty(getBookAndBlockDetails().getBlock().getId())){
			    			Block block=(Block)libraryManager.get(Block.class,getBookAndBlockDetails().getBlock().getId());
					    	RackDetails rackDetails=(RackDetails)libraryManager.get(RackDetails.class, getBookAndBlockDetails().getRackDetails().getId());
					    	BookAndBlockDetails blockDetails =(BookAndBlockDetails)libraryManager.getBookAndBlockDetailsBycustIdAndBookId(getBookId(),getUserCustId());
				    		if(!ObjectFunctions.isNullOrEmpty(getBookTitle())){
				    			blockDetails.setBookTitle(getBookTitle());
				    			blockDetails.setBlock(block);
				    			blockDetails.setRackDetails(rackDetails);
				    			blockDetails.setCustId(getUserCustId());
				    			libraryManager.save(blockDetails);
				    		}
			    		}
	            }
	            super.addActionMessage("Book details updated successfully.");
	            home();
            }           
        }
        catch(Exception ex)
        {
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return SUCCESS;
    }
	@Actions( { @Action(value = "ajaxDeleteBook", results = { @Result(location = "sendSchoolWideBooksList.jsp", name = "success") }) })
	public String ajaxDeleteBook() throws URTUniversalException {
	    if (log.isDebugEnabled()) {
	        log.debug("Entering 'ajaxDeleteBook' method");
	    }
	    try {
	        if (!ObjectFunctions.isNullOrEmpty(getBookId())) {
	        	libraryManager.deleteBookLablesByBookTitleId(getBookId());
	            libraryManager.remove(BookTitle.class, Long.valueOf(getBookId()));
	        }
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	    }
	    return SUCCESS;
	}
	/* revised by ramadevi */
	@Actions({
		@Action(value = "ajaxDoBlockDetails", results = { @Result(location = "ajaxBlockDetails.jsp", name = "success" )})})
		public String doAddBlockDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doAddBlockDetails' method");
		}
		try {  
			setObjectList(libraryManager.getAllBlocksByCustId(getUserCustId()));//here we r getting blocklist
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* revised by ramadevi */
	@Actions({
		@Action(value = "ajaxDoAddNewRack", results = { @Result(location = "block/ajaxAddRacksDetails.jsp", name = "success") }),
		 @Action(value = "ajaxUpdateRack", results = { @Result(location = "block/ajaxEditRacksDetails.jsp", name = "success") }) })
		public String doAddRacksDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doAddRacksDetails' method");
		}
		try {
			//setSubjectsList(getAllStudySubjects());
			if(!ObjectFunctions.isNullOrEmpty(getBlockId())) {
				String sqlQuery = "SELECT subjectId,subjectName,noOfCount-existedCount As noOfCopies,existedCount,rackId FROM vw_bookrackassignmentdetails  WHERE custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and noOfCount >0 and noOfCount <> existedCount";
				List<Object[]> booksList = adminManager.getAll(sqlQuery);
				List<Object[]> booksList1 =null;
				String sqlQuery1=null;
				if(!ObjectFunctions.isNullOrEmpty(booksList)){
					for(Object[] obj:booksList){
						if(!ObjectFunctions.isNullOrEmpty(obj[0])){
							if(Integer.parseInt(obj[0].toString())==0)
								sqlQuery1 = "SELECT  0 As subjectId,bookName,author,publisher,SUM(noOfCopies)-"+obj[3]+" As noOfCopies,id FROM bookTitle  WHERE custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and subjectId is null and status!='N' Group By author,publisher";
							else
							sqlQuery1 = "SELECT subjectId,bookName,author,publisher,SUM(noOfCopies)-"+obj[3]+" As noOfCopies,id FROM bookTitle  WHERE custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and subjectId="+obj[0]+" and status!='N' Group By author,publisher";
							booksList1 = adminManager.getAll(sqlQuery1);
						}
						if(!ObjectFunctions.isNullOrEmpty(booksList1)){
							getTempList2().addAll(booksList1);
						}
					}
					setObjectList(booksList);
				}		
				else{
					//sqlQuery1 = "SELECT subjectId,bookName,author,publisher,SUM(noOfCopies) As noOfCopies,id FROM bookTitle  WHERE custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and status!='N' Group By author,publisher";
					//Siva: Getting books from previous academic year also if they are not assinged to any rack
					sqlQuery1 = "SELECT subjectId,bookName,author,publisher,noOfCopies,id FROM bookTitle  WHERE custId="+getUserCustId()+" and academicYearId<="+getUserAcademicYearId()+" and status!='N' Group By author,publisher";
					booksList1 = adminManager.getAll(sqlQuery1);
					if(!ObjectFunctions.isNullOrEmpty(booksList1)){
						getTempList2().addAll(booksList1);
					}
					//Siva: if there are no books assigned, displaying all books
					setObjectList(booksList1);
				}
			                    
				/*String sqlQuery = "SELECT b.otherSubjects,b.bookName,b.author,b.publisher,count(*) As totalCount,case when (SELECT SUM(r.booksCount)  FROM rackDetails r  WHERE r.rackName = b.otherSubjects and r.custId="+getUserCustId()+" and r.academicYearId="+getUserAcademicYearId()+" group by r.rackName) then" +
						" (SELECT SUM(r.booksCount)  FROM rackDetails r  WHERE r.rackName = b.otherSubjects and r.custId="+getUserCustId()+" and r.academicYearId="+getUserAcademicYearId()+"  group by r.rackName) Else 0 end As booksCount  FROM bookTitle As b WHERE b.custId="+getUserCustId()+" and b.academicYearId="+getUserAcademicYearId()+" group by b.otherSubjects";
				List<Object[]> booksList = libraryManager.getAll(sqlQuery);
				if(!ObjectFunctions.isNullOrEmpty(booksList)){
					setTempList2(booksList);
				}*/
				if(getTempId()> 0)
				setRackDetails((RackDetails)libraryManager.get(RackDetails.class,getTempId()));
				setBlockId(getBlockId());
				// setTempList(libraryManager.getAllRacksByBlockId(getBlockId()));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* revised by ramadevi */
	@Actions({
		@Action(value = "ajaxViewAllRackDetails", results = { @Result(location = "block/ajaxViewAllRacksDetails.jsp", name = "success") }) })
		public String viewAllRacks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxViewAllRackDetails' method");  
		}
		try {  
			if(!ObjectFunctions.isNullOrEmpty(getBlockId())){			
			 setSelectedId2(getBlockId());		
			 setBlock((Block)libraryManager.get(Block.class, getBlockId()));
			 setTempList(libraryManager.getAllRacksByBlockId(getBlockId()));
			 
		 }
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* revised by Cherivi 03/01/2014 */
	@Actions({
		@Action(value = "ajaxAddRacks", results = { @Result(location = "block/ajaxViewAllRacks.jsp", name = "success") }) })
		public String addRacksDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addRacksDetails' method");
		}
		try {				
			if(getBlockId()>0){
				/*setSelectedId2(getBlockId());
				JSONObject formData = new JSONObject(getAnyTitle());
				JSONArray jsonArray =(JSONArray) formData.get("JSONOBJ");
				JSONObject jsonObj = null;
				RackDetails rackDetails=null;
				String rackName=null;
				int booksCount=0;
				int existedCount=0;
				if(!ObjectFunctions.isNullOrEmpty(jsonArray)){
				Block block=(Block)libraryManager.get(Block.class, getBlockId());
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONArray jsnArr =(JSONArray) jsonArray.get(i);
					if (!ObjectFunctions.isNullOrEmpty(jsnArr)) { 
						if(jsnArr.length()>0){
						for (int j = 0; j < jsnArr.length(); j++) {
							jsonObj = jsnArr.getJSONObject(j);
							if (!ObjectFunctions.isNullOrEmpty(jsonObj)) {
								Iterator<String> iter = jsonObj.keys();
								 while (iter.hasNext()) {
								   String key = iter.next();
								 if(key.equalsIgnoreCase("RACKNAME")){
									 rackName=(String)jsonObj.get(key);
								 }
								 else if(key.equalsIgnoreCase("BOOKSCOUNT")){
									 booksCount=Integer.parseInt((String)jsonObj.get(key));
								 }
								 }
							   }
						    }
						if(!StringFunctions.isNullOrEmpty(rackName)){
							rackDetails=(RackDetails)libraryManager.get(RackDetails.class,"rackName='"+rackName+"' and blockId="+getBlockId()+" and custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
							if(!ObjectFunctions.isNullOrEmpty(rackDetails)){
								existedCount=rackDetails.getBooksCount();								
								rackDetails.setBooksCount(existedCount+booksCount);
							}
							else{
								rackDetails=new RackDetails();
								rackDetails.setBooksCount(booksCount);
								rackDetails.setRackName(rackName);
								rackDetails.setBlock(block);
								rackDetails.setCustId(getUserCustId());
								rackDetails.setAcademicYear(getCurrentAcademicYear());
							}
						  }
							rackDetails.setCreatedById(getUser().getId());
							rackDetails.setCreatedDate(new Date());
							rackDetails.setLastAccessDate(new Date());
							rackDetails.setLastUpdatedById(getUser().getId());
							rackDetails.setLastUpdatedDate(new Date());
							adminManager.save(rackDetails);
						  }
						
					   }
					}
					super.addActionMessage("Racks are added successfully.");
					viewAllRacks();
				}*/				
			
				setTempList(libraryManager.getAllRacksByBlockId(getBlockId()));	
				List rackNameList = libraryManager.checkRackName(getRackDetails().getRackName().trim(),getBlockId());
				if (!ObjectFunctions.isNullOrEmpty(rackNameList)) {
	    			if(rackNameList.size()>0){
	    				super.addActionError("Already have this rack name.");
	    				return SUCCESS;
	    			}
	    		}
				Block block=(Block)libraryManager.get(Block.class, getBlockId());
				getRackDetails().setBlock(block);
				getRackDetails().setCustId(getUserCustId());
				getRackDetails().setType("S");
				//libraryManager.save(getRackDetails());
				if(!ObjectFunctions.isNullOrEmpty(getRackDetails())){
					int booksCount=0;
					BooksAssignToRack booksAssign=null;
					if(!StringFunctions.isNullOrEmpty(getAnyTitle())){
						String[] strArry=getAnyTitle().split(",");
						String[] strArry1=null;
						BookTitle bookTitle=null;
						int partialCount=0;
						boolean statusVal=false;
						int existedCount=0;
						AcademicYear academicYear = getCurrentAcademicYear();
							for(int i=0; i< strArry.length;i++){
								strArry1=strArry[i].split("_");
								booksAssign=new BooksAssignToRack();
								booksAssign.setSubjectId(Long.valueOf(strArry1[0]));
								booksAssign.setBookTitleId(Integer.parseInt(strArry1[1]));
								booksAssign.setAcademicYear(academicYear);
								booksAssign.setCustId(getUserCustId());
								partialCount +=Integer.parseInt(strArry1[2]);
								if(partialCount > getRackDetails().getRackCapacity()){
									booksAssign.setnoOfCopies(getRackDetails().getRackCapacity()-booksCount);
									booksCount +=getRackDetails().getRackCapacity()-booksCount;									
									statusVal=true;
								}
								else{
									if(partialCount == getRackDetails().getRackCapacity())
									   statusVal=true;
									booksAssign.setnoOfCopies(Integer.parseInt(strArry1[2]));
									booksCount +=Integer.parseInt(strArry1[2]);
								}
								getRackDetails().addBookAssignments(booksAssign);
								
								bookTitle=(BookTitle)libraryManager.get(BookTitle.class,booksAssign.getBookTitleId());
								if(!ObjectFunctions.isNullOrEmpty(bookTitle)){
									String sqlQuery1 = "SELECT id,SUM(noOfCopies),count(*)  FROM booksAssignToRack  WHERE custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and bookTitleId="+booksAssign.getBookTitleId();
									Object[] booksTitle = adminManager.get(sqlQuery1);
									if(!ObjectFunctions.isNullOrEmpty(booksTitle)){
										   if(Integer.parseInt(booksTitle[2].toString()) > 0)
											existedCount=Integer.parseInt(booksTitle[1].toString());
									}
									if(bookTitle.getNoOfCopies() == booksAssign.getnoOfCopies() || bookTitle.getNoOfCopies()== (existedCount+booksAssign.getnoOfCopies())){
										libraryManager.updateBookTitleStatus(booksAssign.getBookTitleId(),Constants.NO_STRING);
									}
									else{
										libraryManager.updateBookTitleStatus(booksAssign.getBookTitleId(),Constants.PARTIAL_STATUS);
									}
								}
								if(statusVal) break;
							}
					}
					getRackDetails().setBooksCount(booksCount);
					getRackDetails().setAcademicYear(getCurrentAcademicYear());
					libraryManager.save(getRackDetails());
					super.addActionMessage("Rack added successfully.");
					viewAllRacks();
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	/* revised by ramadevi */
	 @Actions({
			@Action(value = "ajaxSearchKeyWords", results = { @Result(location = "ajaxSearchResults.jsp", name = "success" )}),
			@Action(value = "ajaxStaffSearchKeyWords", results = { @Result(location = "staff/teacher/ajaxStaffSearchResults.jsp", name = "success" )}),
			@Action(value = "ajaxStudentSearchKeyWords", results = { @Result(location = "student/ajaxStudentSearchResults.jsp", name = "success" )}),
			@Action(value = "ajaxSearchReadingBooks", results = { @Result(location = "readBooks/ajaxReadingBooksList.jsp", name = "success" )})
	 })
		public String searchKeyWord() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchKeyWord' method");
		}
		try
		{		
			if(!ObjectFunctions.isNullOrEmpty(getSearchWord())){
			//	StringBuffer query =  new StringBuffer("select id from studySubject ").append(" where ").append(" name like '%").append(getSearchWord()).append("%' and academicYearId=").append(getUserAcademicYearId()).append(" and custId=").append(getUserCustId());
				//List<BigInteger> studySubjectIds = adminManager.getAll(query.toString());
			//	query = null;
			//	String classSectionIdsString=null;
				/*if (ObjectFunctions.isNotNullOrEmpty(studySubjectIds)) 
					 classSectionIdsString = StringFunctions.convertListToCommaDelimitedString(studySubjectIds);
				else
					classSectionIdsString="0";*/

				long classId=0;
				   if(!StringFunctions.isNullOrEmpty(getParamValue("selectedType"))){
					   if(getParamValue("selectedType").equalsIgnoreCase("All")){
						   classId=0;
					   }
					   else{
						   Student student=(Student)libraryManager.get(Student.class,"custId="+getUserCustId()+" and status='Y' and accountId="+getUser().getId()+" and academicYearId="+getUserAcademicYearId());
						    if(!ObjectFunctions.isNullOrEmpty(student))
							   classId=student.getClassNameId();
					   }
				   }
						
				List<ViewStudentIssuebookAndReservations> searchList=libraryManager.bookSearchResultsByKeywordAndCustId(getSearchWord(),getUserCustId(),getParamValue("searchBy").trim(),classId,getUserAcademicYearId(),getSelectedDate(),getParamValue("selectedType"));
				if(!ObjectFunctions.isNullOrEmpty(searchList)){
						/*ViewStudentIssuebookAndReservations studentIssueBooks=null;
						List availableBooksList=null;
							for(Object obj : searchList){
								studentIssueBooks=(ViewStudentIssuebookAndReservations)obj;
								if(!StringFunctions.isNullOrEmpty(getParamValue("selectedType")) && "RB".equalsIgnoreCase(getParamValue("selectedType")))
									 availableBooksList=libraryManager.getIssuedBookCountByStatusAndCustId(studentIssueBooks.getBookTitleId(),Constants.BOOK_OPEND,Constants.READING_BOOK,getUserCustId(),"");
								else{
									 availableBooksList=libraryManager.getIssuedBookCountByStatusAndCustId(studentIssueBooks.getBookTitleId(),Constants.BOOK_OPEND,Constants.ISSUE_BOOK,getUserCustId(),"");
								}
								if (!ObjectFunctions.isNullOrEmpty(availableBooksList)) {
									 studentIssueBooks.setBooksAvaliableList(availableBooksList);
									}
									getObjectList().add(studentIssueBooks);
									studentIssueBooks=null;
							}
							searchList=null;*/
						for(ViewStudentIssuebookAndReservations bookTitle : searchList)
						{
							/*List<IssuedBook> issuedBookList = libraryManager.getAll(IssuedBook.class,"custId = "+getUserCustId()+ " and bookId="+bookTitle.getId()+"");
							if(!ObjectFunctions.isNullOrEmpty(issuedBookList))
								bookTitle.setIssuedBooksCount((bookTitle.getIssueBookCount() - issuedBookList.size()));
							else
								bookTitle.setIssuedBooksCount(bookTitle.getNoOfCopies());*/
							//Siva: For Total no of available book labels
							String sqlQuery1 = "SELECT id,count(*)  FROM bookLables  WHERE custId="+getUserCustId()+" and bookTitleId="+bookTitle.getBookTitleId()+" AND deleteStatus!='Y'";
							Object[] booksLabAry = adminManager.get(sqlQuery1);
							if(!ObjectFunctions.isNullOrEmpty(booksLabAry)){
								   if(Integer.parseInt(booksLabAry[1].toString()) > 0){
									   bookTitle.setNoOfCopies(Integer.valueOf(booksLabAry[1].toString()));
								   }
								   else{
									   bookTitle.setNoOfCopies(0);
								   }
							}
							//Siva: For issued books with not returned status books i.e. status='S'
							String sqlQuery2 = "SELECT id,count(*)  FROM issuedBook  WHERE custId="+getUserCustId()+" and bookId="+bookTitle.getBookTitleId()+" AND status !='S'";
							Object[] issBooksAry = adminManager.get(sqlQuery2);
							if(!ObjectFunctions.isNullOrEmpty(issBooksAry)){
								   if(Integer.parseInt(issBooksAry[1].toString()) > 0){
									   bookTitle.setIssuedBooksCount(bookTitle.getNoOfCopies() - Integer.valueOf(issBooksAry[1].toString()));
								   }
								   else{
									   bookTitle.setIssuedBooksCount(bookTitle.getNoOfCopies());
								   }
							}
							//END
							getObjectList().add(bookTitle);
							bookTitle=null;
						}
					//setObjectList(searchList);
				}
				if (getUser().isSchoolStudent() || getUser().isSchoolPrincipal() || getUser().isSchoolTeacher() || getUser().isOnlySchoolHod() || getUser().isOnlySchoolAdmin() || getUser().isSchoolDirector()) {
	        		List studentBooksList=libraryManager.getStudentRequestBooksByStudentAccountId(getUser().getId(),getUserCustId(),"R");
	        		if (!ObjectFunctions.isNullOrEmpty(studentBooksList)) {
	        			getTempList().addAll(studentBooksList);
	        			Collections.sort(getTempList());
	        		}
	        	}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	 }	
	@Actions( { @Action(value = "ajaxLibrarianIssueBookDetails", results = { @Result(location = "ajaxLibrarianIssueBookDetails.jsp", name = "success") }) })
		public String popupIssueBooks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'popupIssueBooks' method");
		}
		try {
			 setTempId(getTempId());
			 setAnyId(getAnyId());
			 if(!ObjectFunctions.isNullOrEmpty(getTempId())){	
				 StringBuffer  bookLableIds = new StringBuffer();
				 bookLableIds.append("(");
				 List<IssuedBook> issuedBookList = libraryManager.getAll(IssuedBook.class, "custId = " +getUserCustId()+" and bookId="+getTempId() + " and status='"+Constants.ACTIVE_STATUS+"'");
				if(!ObjectFunctions.isNullOrEmpty(issuedBookList))
				{
					
					for( IssuedBook issuedBook : issuedBookList)
					{
						bookLableIds.append(issuedBook.getBookLable().getId()+",");
					}
				}
				bookLableIds.append("0)");
				issuedBookList = null;
	   		 	setObjectList(libraryManager.getAll(ViewBookAndLabelDetails.class," custId="+getUserCustId()+" and bookTitleId="+getTempId()+" and deleteStatus='"+Constants.NO_STRING+"' and bookLabelStatus='"+Constants.BOOK_OPEND+"' and type='"+Constants.ISSUE_BOOK+"' and bookLableId not in "+bookLableIds.toString() ));
	   		  }
			 setBookTitle((BookTitle)libraryManager.get(BookTitle.class,getTempId()));
			 setCustomer(getCustomerByCustId());
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

	/* @Description 1 st may cvs: Modularization  below method add getAllIssuedBooks it is a common method */  
	@Actions( { @Action(value = "ajaxPopupLibrianSearchBooks", results = { @Result(location = "ajaxPopupLibrarianSearchBooks.jsp", name = "success"),@Result(location = "ajaxUserNotExist.jsp", name = "userNotExist")}) })
	public String ajaxStudentBooks() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxStudentBooks' method");
	}
	try {	
		LibrarySettings settings = (LibrarySettings)adminManager.get(LibrarySettings.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" ");
		if(!ObjectFunctions.isNullOrEmpty(settings)){
			setLibrarySettings(settings);
			if(StringFunctions.isNotNullOrEmpty(getPlTitle())){ // here BC is a barcode scaner read from id card
				User userObj = (User) adminManager.get(User.class, "barcode='"+getPlTitle()+"' ");
				if (!ObjectFunctions.isNullOrEmpty(userObj)) {
					setUsername(userObj.getUsername());
					userObj=null;
				}
			}
			if(!ObjectFunctions.isNullOrEmpty(getUserCustId()) && !ObjectFunctions.isNullOrEmpty(getBookId()) && !ObjectFunctions.isNullOrEmpty(getUsername())){
				User user =(User)userManager.getAccountByCustIdAndStatus(getUsername().trim(),getUserCustId(),Constants.YES_STRING);			
				if(!ObjectFunctions.isNullOrEmpty(user)){
					getIssuedBooks(user.getId(),getBookId());
					getAllIssuedBooks(user);
					setTempId(user.getId());
					setBookNumber(getAnyId());
					setAnyTitle(user.getUserRoleName());
					if(ObjectFunctions.isNullOrEmpty(getBookTitleList())){
						setBookTitle((BookTitle) libraryManager.get(BookTitle.class, getBookId()));
					}
				
					/*if(!ObjectFunctions.isNullOrEmpty(getIssuedBook())){
						setAnyTitle("exist");
					}*/
				}else{
					super.addActionError("user name not found. please give Student / Staff ID correctly.");
					return "userNotExist";
					}
				}
		}
	}
	catch(Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
	/********************************************************************
	 *
	 * Date              Name               Description
	 * ========          ============       ==================
	 * May 6, 2013		 cvs				Validating whether user or not and bookId,AccountId
	 * 										saveIssuedbookByBookIdAndCustId method reusability of Save Librarian issued book.
	/********************************************************************/
	@Actions( { @Action(value = "ajaxSaveLibrarinIssuedBook", results = { @Result(location = "libraryHome.jsp", name = "success") }) })
	public String ajaxAddIssuedBook() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveLibrarinIssuedBook' method");
		}
		try {
			
			if (!ObjectFunctions.isNullOrEmpty(getTempId()) && !ObjectFunctions.isNullOrEmpty(getTempId1())) { //here tempId=accountId and tempId1=bookId
				getIssuedBooks(getTempId(),getTempId1());
				if (!ObjectFunctions.isNullOrEmpty(getIssuedBook())) {
					home();
					return SUCCESS;
				}
				User user=(User)libraryManager.get(User.class, getTempId());
				if (!ObjectFunctions.isNullOrEmpty(user)) {
					
					List availableBooksList=libraryManager.getIssuedBookCountByStatusAndCustId(getTempId1(),Constants.BOOK_OPEND,Constants.ISSUE_BOOK,getUserCustId(),getBookNumber());
					if (!ObjectFunctions.isNullOrEmpty(availableBooksList)) {
						BookLables bookLables=(BookLables)availableBooksList.get(0);
						//here reservations is empty obj because this method use saveIssuedbookByBookIdAndCustId here pass the reservations changed by cvs
						saveIssuedbookByBookIdAndCustId(getTempId1(),user,bookLables,reservations);
					}
					availableBooksList=null;
				}
			}
			home();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Actions({
        @Action(value = "ajaxBookFinesList", results = { @Result(location = "ajaxBookFineTransctions.jsp", name = "success" )})  })
        public String ajaxBookFinesList() throws URTUniversalException {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'ajaxBookFinesList' method");
        }
        try {
            List teansactionsList = (FineDetails) libraryManager.getFineListByStudentIdAndCustId(FineDetails.class,StudentId,getUserCustId());
            if (!ObjectFunctions.isNullOrEmpty(teansactionsList)) {
            	getObjectList().addAll(teansactionsList);
               }
            }
        catch(Exception ex)   {
            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
        }
        return SUCCESS;
    }*/
   
	   @Actions({
		   @Action(value = "ajaxDoStaffBooksHistory", results = { @Result(location = "staff/teacher/ajaxStaffBooksHistory.jsp", name = "success" )}),
	        @Action(value = "ajaxDoStudentBooksHistory", results = { @Result(location = "student/ajaxStudentBooksHistory.jsp", name = "success" )})
	        
	        })
	        public String studentBooksHistory() throws URTUniversalException {
	        if (log.isDebugEnabled()) {
	            log.debug("Entering 'studentBooksHistory' method");
	        }
	        try  {
	        	long accountId = 0;
	        	ViewStudentPersonAccountDetails  viewStudentDetails =null;
	        	ViewStaffPersonAccountDetails viewStaffPersonAccountDetails = null;
	        	accountId = getUser().getId();
				if(getUser().isParent()){
					//viewStudentDetails =(ViewStudentPersonAccountDetails)adminManager.get(ViewStudentPersonAccountDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and parentId="+accountId);
					viewStudentDetails =(ViewStudentPersonAccountDetails)adminManager.get(ViewStudentPersonAccountDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getSelectedStudentId());
					if(!ObjectFunctions.isNullOrEmpty(viewStudentDetails))
					    setObjectList(adminManager.getAll(ViewIssuedBookAndSettings.class," accountId="+viewStudentDetails.getAccountId()+" and custId="+getUserCustId()+" and status='"+Constants.BOOK_SUBMITED+"' "));
				}else if(getUser().isSchoolStudent()){
					viewStudentDetails =(ViewStudentPersonAccountDetails)adminManager.get(ViewStudentPersonAccountDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+accountId);
					if(!ObjectFunctions.isNullOrEmpty(viewStudentDetails)){
						setObjectList(adminManager.getAll(ViewIssuedBookAndSettings.class," accountId="+viewStudentDetails.getAccountId()+" and custId="+getUserCustId()+" and status='"+Constants.BOOK_SUBMITED+"' "));
					}
				}
				else{
					/*//viewStaffPersonAccountDetails =(ViewStaffPersonAccountDetails)adminManager.get(ViewStaffPersonAccountDetails.class,"custId="+getUserCustId()+" and accountId="+accountId);
					if(!ObjectFunctions.isNullOrEmpty(viewStaffPersonAccountDetails)){*/
						setObjectList(adminManager.getAll(ViewIssuedBookAndSettings.class," accountId="+accountId+" and custId="+getUserCustId()+" and status='"+Constants.BOOK_SUBMITED+"' "));
					//}
				}
	        }
	        catch(Exception ex) {
	            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
	        return SUCCESS;
	    }
	   
	   /********************************************************************
	   *
	   * Date              Name               Description
	   * ========          ============       ==================
	   * May 6, 2013		 cvs				Validating whether user is correct or not.
	   * am changing the ajaxSendRequestStaffBook,ajaxSendResquestStuddentBook put in one method because we are save the accountId in reservations 
	  /********************************************************************/
	   @Actions({
		   @Action(value = "ajaxSendRequestStaffBook", results = { @Result(location = "staff/teacher/ajaxStaffLibraryDetails.jsp", name = "success") }),
	       @Action(value = "ajaxSendResquestStuddentBook", results = { @Result(location = "student/ajaxStudentLibraryDetails.jsp", name = "success") }) })
	        public String resquestStuddentBook() throws URTUniversalException {
	        if (log.isDebugEnabled()) {
	            log.debug("Entering 'resquestStuddentBook' method");
	        }
	        try {
	        	long accountId = 0;
	        	if(getUser().isParent())
	        		accountId = getUser().getId();
	        	else
	        		accountId = getUser().getId();
	        	Date expiryDate=null;
	        	ViewStudentPersonAccountDetails viewStudentDetails = null;
	        	User user = null;
	        	if(!ObjectFunctions.isNullOrEmpty(getBookId()) && !ObjectFunctions.isNullOrEmpty(accountId)){
	        		if(getUser().isParent()){	
	        			//viewStudentDetails =(ViewStudentPersonAccountDetails)adminManager.get(ViewStudentPersonAccountDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and parentId="+accountId);
	        			viewStudentDetails =(ViewStudentPersonAccountDetails)adminManager.get(ViewStudentPersonAccountDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getSelectedStudentId());
	        			user =(User)userManager.get(User.class,viewStudentDetails.getAccountId());
	        		}else
	        			user =(User)userManager.get(User.class,accountId);
	        		Reservations reservations = (Reservations) libraryManager.get(Reservations.class," bookId="+getBookId()+" and status='"+Constants.BOOK_RECEIVED+ "' and custId="+ getUserCustId()+" and accountId="+user.getId());
	    			if (!ObjectFunctions.isNullOrEmpty(reservations)) {
	    				super.addActionError("You have already reserved this "+reservations.getBookTitle().getBookName());
	    				if (user.isSchoolStudent()) {
	    					studentLibrayHome();
	    				}else{
	    					staffLibrayHome();
	    				}
	    				return SUCCESS;
	    			}
	    			getIssuedBooks(user.getId(),getBookId());
					if (!ObjectFunctions.isNullOrEmpty(getIssuedBook())) {
						if (user.isSchoolStudent()) {
	    					studentLibrayHome();
	    				}else{
	    					staffLibrayHome();
	    				}
						return SUCCESS;
					}
	        		String bookNumber=Math.round(Math.random()*100*01)+DateFormatter.formatDate(DateFormatter.YYYY_PATTERN, new Date());
	        		BookTitle bookTitle=(BookTitle)libraryManager.get(BookTitle.class, getBookId());
	        		expiryDate=DateFunctions.add(new Date(),bookTitle.getBookRequestExpareDays());
	        		Reservations requestBook=new Reservations();
		        	requestBook.setBookTitle(bookTitle);
		        	requestBook.setCustId(getUserCustId());
		        	requestBook.setUser(user);
		        	requestBook.setStatus(Constants.BOOK_RECEIVED);
		        	requestBook.setExpiryDate(expiryDate);
		        	if(!StringFunctions.isNullOrEmpty(getAnyId())){ // lableCode
		        		BookLables bookLable=(BookLables)libraryManager.get(BookLables.class, "bookTitleId='"+getBookId()+"' and lableCode='"+getAnyId()+"'");
		        		requestBook.setBookLable(bookLable);
		        	}
		        	requestBook.setBookReservationNo(bookNumber);
		        	if(user.isSchoolStudent())
		        		requestBook.setUserStatus("student");
					else
						requestBook.setUserStatus("staff");
		        	
		        	libraryManager.save(requestBook);
		        	super.addActionMessage("Book request sent successfully.");
		        	bookNumber=null;
		        	bookTitle=null;
		        	if (user.isSchoolStudent()) {
						studentLibrayHome();
					}else{
						staffLibrayHome();
					}
	        	}
	        }
	        catch(Exception ex) {
	            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
	        return SUCCESS;
	    }
	     
	   /* @Description 1st may cvs: Modularization  below method  remove the unnecessary code and change the service through view  in LibraryDaoH*/
	   @Actions({
	        @Action(value = "ajaxReturntBooks", results = { @Result(location = "ajaxSubmitReturnsBooks.jsp", name = "success" )}) })
	        public String submitReurnBooks() throws URTUniversalException {
	        if (log.isDebugEnabled()) {
	            log.debug("Entering 'submitReurnBooks' method");
	        }
	        try {
				if (!StringFunctions.isNullOrEmpty(getUsername().trim())) {
					User user =(User)userManager.getAccountByCustId(getUsername().trim(),getUserCustId());		
					if (!ObjectFunctions.isNullOrEmpty(user)) {
						if (user.isSchoolStudent()) {
						    setTempList(libraryManager.getIssuedBooksByAcountId(user.getId(),getUserCustId(),Constants.PENDING_STATUS));						
						}
						if (user.isSchoolPrincipal() || user.isSchoolTeacher() || user.isOnlySchoolHod()) {						
							setTempList(libraryManager.getIssuedBooksByAcountId(user.getId(),getUserCustId(),Constants.PENDING_STATUS));							
						}
					}
				}
	        }
	        catch(Exception ex) {
	            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
	        return SUCCESS;
	    }
	   /* revised by ramadevi */
	   @Actions({
	        @Action(value = "ajaxSubmitedIssuedBook", results = { @Result(location = "ajaxSubmitReturnsBooks.jsp", name = "success" )}) })
	        public String submitedIssuedBook() throws URTUniversalException {
	        if (log.isDebugEnabled()) {
	            log.debug("Entering 'submitedIssuedBook' method");
	        }
	        try
	        {
	        	String todayDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date());
				Date submitedDate = DateFormatter.parseString(DateFormatter.CCYY_MM_DD_PATTERN,todayDate);
	        	if(!ObjectFunctions.isNullOrEmpty(getTempId1()) && !ObjectFunctions.isNullOrEmpty(getTempId())){
	        		BookLables bookLables=(BookLables)libraryManager.get(BookLables.class,getTempId());//here TempId1=issuedbookId,tempId=bookLableId,anyTitle=fine
	        		bookLables.setBookLabelStatus(Constants.BOOK_OPEND);
	        		libraryManager.save(bookLables);
	        		IssuedBook issuedBook=(IssuedBook)libraryManager.get(IssuedBook.class,getTempId1());
	        		issuedBook.setStatus(Constants.BOOK_SUBMITED);
	        		issuedBook.setSubmitedDate(submitedDate);
	        		issuedBook.setFineAmount(Integer.parseInt(getAnyTitle()));
	        		if(!ObjectFunctions.isNullOrEmpty(getParamValue("paidAmount"))){
	        			int paidAmount = Integer.valueOf(getParamValue("paidAmount"));
		        		issuedBook.setPaidFineAmount(paidAmount);
	        		}else
	        			issuedBook.setPaidFineAmount(0);
	        		issuedBook.setFineExemption(getPlTitle());
	        		libraryManager.saveIssuedBook(issuedBook);
	        		super.addActionMessage("Book returned successfully.");
	        		setTempList(libraryManager.getIssuedBooksByAcountId(issuedBook.getUser().getId(),getUserCustId(),Constants.ACTIVE_STATUS));  		
					issuedBook=null;
					bookLables=null;
	        	}
	        	librainIssuedBookDetails();
	        }
	        catch(Exception ex)
	        {
	            ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	        }
	        return SUCCESS;
	    } 
	/* revised by cherivi */
	   @Actions( {  @Action(value = "ajaxDoIssuedAndRequestBooks",  results = { @Result(location = "ajaxIssuedAndRequestBooks.jsp", name = "success" )}),
		   @Action(value = "ajaxDoApproveOrRejectBooks",  results = { @Result(location = "ajaxDoApproveOrRejectBooks.jsp", name = "success" )})})
		public String issuedAndRequestBooks() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoApproveOrRejectBooks' method");
			}
			try {
				 if(!StringFunctions.isNullOrEmpty(getAnyId())){
				    	if("RR".equalsIgnoreCase(getAnyId())){
				    		List<Reservations> requestedBooks= libraryManager.getAllRequestedBooksByStatus("student",getUserCustId(),Constants.BOOK_RECEIVED,0);	
							if(!ObjectFunctions.isNullOrEmpty(requestedBooks)){
								 setObjectList(requestedBooks);
							}
							requestedBooks=null;
							requestedBooks=libraryManager.getAllRequestedBooksByStatus("staff",getUserCustId(),Constants.BOOK_RECEIVED,0);
							if(!ObjectFunctions.isNullOrEmpty(requestedBooks)){
								 setTempList(requestedBooks);
							}
				    	}
				    	else{
				    		List<IssuedBook> issuedBooks= libraryManager.getAllRenewalBooksByStatus("student",getUserCustId(),Constants.PENDING_STATUS,0,Constants.YES_STRING);	
							if(!ObjectFunctions.isNullOrEmpty(issuedBooks)){
								 setObjectList(issuedBooks);
							}
							issuedBooks=null;
							issuedBooks=libraryManager.getAllRenewalBooksByStatus("staff",getUserCustId(),Constants.PENDING_STATUS,0,Constants.YES_STRING);
							if(!ObjectFunctions.isNullOrEmpty(issuedBooks)){
								 setTempList(issuedBooks);
							}
				    	}
				    }
			}
			catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	   /* revised by cherivi */
	   @Actions({
			@Action(value = "ajaxSearchRequestedBook", results = { @Result(location = "ajaxSearchReservationBook.jsp", name = "success" )})})
		public String searchRequestedBook() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchRequestedBook' method");
		}
		try {		
			if(!ObjectFunctions.isNullOrEmpty(getSearchBookNumber())){	
				Reservations bookReservation=libraryManager.bookSearchByBookNumberAndCustId(getSearchBookNumber(),getUserCustId(),"R");
				if(!ObjectFunctions.isNullOrEmpty(bookReservation)){
						setUser(bookReservation.getUser());
						setReservations(bookReservation);
				}
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	 }   
	   /* here there is no use of prepareAcademicYearId() method.Y because we r getting academicyearid from session.Done by ramadevi */
	   @Actions( {
           @Action(value = "ajaxDoViewAllIssuedBooks",  results = { @Result(location = "viewDoAllIssuedBooks.jsp", name = "success" )}) })
       public String doViewAllIssuedBooks() throws URTUniversalException {
           if (log.isDebugEnabled()) {
               log.debug("Entering 'doViewAllIssuedBooks' method");
           }
           try {
            	   setClassList(adminManager.getAllClassNames( getUserCustId(),getUserAcademicYearId()));               
           }
           catch(Exception ex) {
               ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
           }
           return SUCCESS;
       }
	   
	   /* Here classid property value is there so there is no use of getparamvalue(classid),prepareacademicyearid() method also.Done by ramadevi */
	   @Actions( { 
			@Action(value = "ajaxViewAllPendingStudentBooks",  results = { @Result(location = "viewAllIssuedStudentBooksList.jsp", name = "success" )}) })
		public String ajaxViewAllPendingStudentBooks() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewAllPendingStudentBooks' method");
			}
			try
			{
				 List issuedBooksList=null;
					if(!StringFunctions.isNullOrEmpty(getClassId())){ // Here classId is classNameClassId
						 if(Long.valueOf(getClassId()) > 0)
						  issuedBooksList=libraryManager.getAllBooksIssuedToStudentOrStaff("student",getUserCustId(),Constants.ACTIVE_STATUS,Long.valueOf(getClassId()));	
					}				
					else
						 issuedBooksList=libraryManager.getAllBooksIssuedToStudentOrStaff("student",getUserCustId(),Constants.ACTIVE_STATUS,0);
		            	 if(!ObjectFunctions.isNullOrEmpty(issuedBooksList)){
		            	   getTempList().addAll(issuedBooksList);
		            	   issuedBooksList=null;
		            }
			}
			catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	   
	   /* revised by ramadevi */
	   @Actions( { 
			@Action(value = "ajaxViewAllPendingStaffBooks",  results = { @Result(location = "viewAllIssuedStaffBooksList.jsp", name = "success" )}) })
		public String ajaxViewAllPendingStaffBooks() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewAllPendingStaffBooks' method");
			}
			try {
				 List issuedBooksList=libraryManager.getAllBooksIssuedToStudentOrStaff("staff",getUserCustId(),Constants.ACTIVE_STATUS,0);
            	 if(!ObjectFunctions.isNullOrEmpty(issuedBooksList)){
            	   getObjectList().addAll(issuedBooksList);
            	   issuedBooksList=null;
            	 }
			}
			catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	   /********************************************************************
	   *
	   * Date              Name               Description
	   * ========          ============       ==================
	   * May 6, 2013		 cvs				Validating user or not,book,IssuedBook checking
	   * 										saveIssuedbookByBookIdAndCustId method reusability of Save Librarian issued book.
	  /********************************************************************/
	  @Actions( { @Action(value = "ajaxAddIssuedBookThroughRequest", results = { @Result(location = "ajaxIssuedAndRequestBooks.jsp", name = "success"),@Result(location = "ajaxUserNotExist.jsp", name = "userNotExist")}) })
	  public String addIssuedBookThrowRequest() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxAddIssuedBookThroughRequest' method");
			}
			try {
				if (getBookId() > 0 && !ObjectFunctions.isNullOrEmpty(getTempId1()) && !ObjectFunctions.isNullOrEmpty(getTempId()) ) {//Here tempId=accountid,tempId1=reservationid					
					User user=(User)libraryManager.get(User.class,getTempId());
					 LibrarySettings librarySettings=(LibrarySettings)libraryManager.getLibrarySettingsByCustId(getUserCustId(),getUserAcademicYearId());
					if (ObjectFunctions.isNullOrEmpty(librarySettings)) {
						super.addActionError("Please create library settings.");
	    				return SUCCESS;
					}
					getAllIssuedBooks(user);
					if (!ObjectFunctions.isNullOrEmpty(getBookTitleList()) || ObjectFunctions.isNullOrEmpty(getLibrarySettings())) {
						return SUCCESS;
					}
					if (user.isSchoolStudent() || user.isSchoolPrincipal() || user.isSchoolTeacher() || user.isOnlySchoolHod()) {
						preapareIssuedBookThrowRequest(getTempId1(),user,getBookId());
					  }
				}
				setAnyId("RR");
				issuedAndRequestBooks();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	  public String preapareIssuedBookThrowRequest(long tempId,User user,long bookId){
		  Reservations reservations=(Reservations)libraryManager.get(Reservations.class,tempId);
			List avableBooksList=libraryManager.getIssuedBookCountByStatusAndCustId(bookId,Constants.BOOK_OPEND,Constants.ISSUE_BOOK,getUserCustId(),"");
			if (!ObjectFunctions.isNullOrEmpty(avableBooksList)) {
				BookLables bookLables=(BookLables)avableBooksList.get(0);
				if (!ObjectFunctions.isNullOrEmpty(user)) {
					saveIssuedbookByBookIdAndCustId(bookId,user,bookLables,reservations);
				}
			  }
			  else{
					super.addActionError("Currently there are no stock.");
					issuedAndRequestBooks();
  				return SUCCESS;
  			}
			avableBooksList=null;
			return SUCCESS;
	   }
	 public void  saveIssuedbookByBookIdAndCustId(long bookId,User user,BookLables bookLables,Reservations reservations){
		 String todayDate = DateFormatter.formatDate(DateFormatter.CCYY_MM_DD_PATTERN, new Date());
			Date issuedDate = DateFormatter.parseString(DateFormatter.CCYY_MM_DD_PATTERN,todayDate);				
			BookTitle bookTitle=(BookTitle)libraryManager.get(BookTitle.class, bookId);
			if (!ObjectFunctions.isNullOrEmpty(bookTitle)){
				//Siva: Checking whether the student/staff exceeding the maximum books count or not as per library setting
				boolean issueBooktoUser = true;
				Object[] librarySettings = libraryManager.get("select fineAmountPerDay,id,noOfStudentIssuBooks,noOfStaffIssuBooks from librarySettings where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
				List receivedBookList=libraryManager.getStaffRequestBooksByStaffAccountId(user.getId(),getUserCustId(),"A");
				if (!ObjectFunctions.isNullOrEmpty(receivedBookList)) {
					if(user.isSchoolStudent()){
						if(receivedBookList.size() >= Integer.valueOf(librarySettings[2].toString())){
							super.addActionError("Max books allowed for student '"+user.getPerson().getFirstName()+"' are exceeded.");
							issueBooktoUser = false;
						}
					}
					else {
						if(receivedBookList.size() >= Integer.valueOf(librarySettings[3].toString())){
							super.addActionError("Max books allowed for staff '"+user.getPerson().getFirstName()+"' are exceeded.");
							issueBooktoUser = false;
						}
					}
				}
				//END
				if(issueBooktoUser)
				{
					Date dueDate1=DateFunctions.add(issuedDate,+bookTitle.getIssueDays());
					IssuedBook issueBook = new IssuedBook();
					issueBook.setUser(user);						
					issueBook.setBookTitle(bookTitle);
					issueBook.setCustId(getUserCustId());
					issueBook.setIssuedDate(issuedDate);
					issueBook.setDueDate(dueDate1);
					bookLables.setCreatedById(user.getId());
					bookLables.setLastAccessDate(new Date());
					bookLables.setLastUpdatedDate(new Date());
					bookLables.setBookLabelStatus(Constants.BOOK_CLOSED);
					issueBook.setBookLable(bookLables);
					issueBook.setBookTitle(bookTitle);
					//issueBook.setStatus(Constants.PENDING_STATUS);
					issueBook.setStatus(Constants.ACTIVE_STATUS);
					issueBook.setCreatedById(user.getId());
					issueBook.setLastAccessDate(new Date());
					issueBook.setLastUpdatedDate(new Date());
					if(user.isSchoolStudent()){
						Student student = (Student)adminManager.get(Student.class,"accountId="+user.getId());
						if(!ObjectFunctions.isNullOrEmpty(student))
							issueBook.setClassId(student.getClassNameClassId().getId());
						issueBook.setUserStatus("student");
					}
					else
						issueBook.setUserStatus("staff");
					
					issueBook.setApprovedStatus(Constants.NO_STRING);
					issueBook.setRenewalStatus(Constants.NO_STRING);
					
					if (!ObjectFunctions.isNullOrEmpty(reservations)) {
						reservations.setStatus(Constants.PENDING_STATUS);
						libraryManager.save(reservations);
					}
					issueBook = (IssuedBook)libraryManager.merge(issueBook);
					/* Writing code to implement the SMS and Email integration */	
					Messages messages = new Messages();
					messages.setAcademicYear(getCurrentAcademicYear());
					messages.setPurposeType("Issue Book");
					messages.setTitle("Issue Book");
					messages.setSmsSenderId(getCustomer().getSender());
					messages.setMessageDescription(bookTitle.getBookName().toUpperCase()+" Book Has Been Issued To You on date "+todayDate+" till date "+DateFormatter.formatDate( DateFormatter.CCYY_MM_DD_PATTERN, dueDate1));
					sendIssuedBookMessage(user,getCustomer(),messages);
					//Sending mobile notification for library issue book
					libraryManager.sendLibraryNotification(user,getCustomer(),messages,issueBook);
					super.addActionMessage("Book is given to '"+user.getPerson().getFirstName()+"' successfully.");
				}
				libraryManager.merge(bookLables);
			}
			else{
				super.addActionError("This book is not available.");
			}
	  }
	  
	  /********************************************************************
	   *
	   * Date              Name               Description
	   * ========          ============       ==================
	   * May 6, 2013		 cvs				Validating user or not,book,IssuedBook checking
	   * 										in below  method calls saveIssuedbookByBookIdAndCustId...this method used to reusable   
	   * saveIssuedbookByBookIdAndCustId method reusability of Save Librarian issued book.
	  /********************************************************************/
	@Actions( { @Action(value = "ajaxLibrainIssuedBookDetails", results = { @Result(location = "ajaxIssuedBooks.jsp", name = "success"),
			@Result(location = "ajaxUserNotExist.jsp", name = "userNotExist"),@Result(location = "ajaxSubmitReturnsBooks.jsp", name = "returnBooks")})})
	public String librainIssuedBookDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'librainIssuedBookDetails' method");
		}
		try {
			setCustomer(getCustomerByCustId());
			if("BC".equalsIgnoreCase(getPlTitle())){
				User userObj = (User) adminManager.get(User.class, "barcode='"+getUsername()+"' ");
				if (!ObjectFunctions.isNullOrEmpty(userObj)) {
					setUsername(userObj.getUsername());
					userObj=null;
				}
			}
			BookLables lbookLables=null;
			Object[] librarySettings = libraryManager.get("select fineAmountPerDay,id,noOfStudentIssuBooks,noOfStaffIssuBooks from librarySettings where custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(librarySettings) && !ObjectFunctions.isNullOrEmpty(librarySettings[0].toString())){
				setNumberOfDays(Integer.valueOf(librarySettings[0].toString()));
			}
			if(!StringFunctions.isNullOrEmpty(getAnyTitle())){
				if(getAnyTitle().equalsIgnoreCase(Constants.READING_BOOK)){
					if (!StringFunctions.isNullOrEmpty(getUsername().trim())) {
						//User user =(User)userManager.getAccountByCustId(getUsername().trim(),getUserCustId());	
						User user =(User)userManager.get(User.class," (username='"+getUsername().trim()+"' OR admissionNumber='"+getUsername().trim()+"') and accountEnabled='"+Constants.YES_STRING+"' and custId="+getUserCustId());
						if (!ObjectFunctions.isNullOrEmpty(user)) {
							if (user.isSchoolStudent() || user.isSchoolPrincipal() || user.isSchoolTeacher() || user.isOnlySchoolHod() || user.isSchoolDirector() || user.isSchoolAsstStaff()) {
							    setTempList(libraryManager.getIssuedBooksByAcountId(user.getId(),getUserCustId(),Constants.ACTIVE_STATUS));					
							}
						}
					}
					return "returnBooks";
				}
				else{
					if (!ObjectFunctions.isNullOrEmpty(getUsername()) && !StringFunctions.isNullOrEmpty(getBookNumber())) {
						if (!ObjectFunctions.isNullOrEmpty(getUsername().trim()) && !StringFunctions.isNullOrEmpty(getBookNumber().trim())) {
							//User user =(User)userManager.getAccountByCustIdAndStatus(getUsername().trim(),getUserCustId(),Constants.YES_STRING);
							User user =(User)userManager.get(User.class," (username='"+getUsername().trim()+"' OR admissionNumber='"+getUsername().trim()+"') and accountEnabled='"+Constants.YES_STRING+"' and custId="+getUserCustId());
							if (!ObjectFunctions.isNullOrEmpty(user)){
								lbookLables =(BookLables)libraryManager.get(BookLables.class," custId="+ getUserCustId()+ " and lableCode='"+getBookNumber().trim()+"' ");
								if (!ObjectFunctions.isNullOrEmpty(lbookLables)) {
									if(lbookLables.getType().equalsIgnoreCase(Constants.READING_BOOK) ){
										super.addActionError("This book is only for reading purpose .");
										return SUCCESS;
									} else if(lbookLables.getBookLabelStatus().equalsIgnoreCase(Constants.BOOK_CLOSED) ){
										super.addActionError("This book already issued.");
										return SUCCESS;
									}else if(lbookLables.getBookLabelStatus().equalsIgnoreCase(Constants.BOOK_OPEND) && lbookLables.getDeleteStatus().equalsIgnoreCase(Constants.NO_STRING)){
											ViewBookAndLabelDetails viewBookAndLabelDetails=(ViewBookAndLabelDetails)libraryManager.get(ViewBookAndLabelDetails.class," custId="+getUserCustId()+" and lableCode='"+lbookLables.getLableCode()+"' ");
											IssuedBook issuedBook = (IssuedBook) libraryManager.get(IssuedBook.class," bookId="+viewBookAndLabelDetails.getBookTitleId()+" and status='"+Constants.PENDING_STATUS+ "' and custId="+ getUserCustId()+" and accountId="+user.getId());
										if (!ObjectFunctions.isNullOrEmpty(issuedBook)) {
											super.addActionError("You have already taken this "+issuedBook.getBookTitle().getBookName());
											return SUCCESS;
										} 
										getAllIssuedBooks(user);
										if (ObjectFunctions.isNullOrEmpty(getLibrarySettings())) {
											super.addActionError("Please create library settings.");
											return SUCCESS;
										}
										//Siva: Checking whether the student/staff exceeding the maximum books count or not as per library setting
										List receivedBookList=libraryManager.getStaffRequestBooksByStaffAccountId(user.getId(),getUserCustId(),"A");
										if (!ObjectFunctions.isNullOrEmpty(receivedBookList)) {
											if(user.isSchoolStudent()){
												if(receivedBookList.size() >= Integer.valueOf(librarySettings[2].toString())){
													super.addActionError("Max books allowed for student '"+user.getPerson().getFirstName()+"' are exceeded.");
													return SUCCESS;
												}
											}
											else {
												if(receivedBookList.size() >= Integer.valueOf(librarySettings[3].toString())){
													super.addActionError("Max books allowed for staff '"+user.getPerson().getFirstName()+"' are exceeded.");
													return SUCCESS;
												}
											}
										}
										//END
										//here reservations is empty obj because this method use preapareIssuedBookThrowRequest heare pass the reservations changed by cvs
										if(ObjectFunctions.isNullOrEmpty(getBookTitleList()))
										saveIssuedbookByBookIdAndCustId(viewBookAndLabelDetails.getBookTitleId(),user,lbookLables,reservations);
									}
								}
								else{
									super.addActionError("Please check book number.");
								}
							}else {
								super.addActionError("This user ID/Admission Number not available (or) incorrect.");
							}
						}
					}
				 }
			}
		}
	catch(Exception ex)  {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
 }
   /* @Description 15rd Apr cvs: Modularization  below method  get the issued books and reservation books*/  
   @Actions({
	   @Action(value = "ajaxGetStudentLibrayHome", results = { @Result(location = "student/studentLibrayHome.jsp", name = "success") }),
		@Action(value = "getStudentLibrayHome", results = { @Result(location = "student/studentLibrayHome.jsp", name = "success") }) })
		public String studentLibrayHome() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'studentLibrayHome' method");
		}
		try { 
			staffLibrayHome();
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	   }
   
   /* @Description 15rd Apr cvs: Modularization  below method  get the issuedBooks*/  
   @Actions({
	   @Action(value = "ajaxGetStaffLibrayHome", results = { @Result(location = "staff/teacher/staffLibrayHome.jsp", name = "success") }),
		@Action(value = "getStaffLibrayHome", results = { @Result(location = "staff/teacher/staffLibrayHome.jsp", name = "success") }) })
		public String staffLibrayHome() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'staffLibrayHome' method");
		}
		try {
			long accountId = 0;
			if(getUser().isParent())
				accountId = getUser().getId();
			else
				accountId = getUser().getId();
			List requestedBooksList= null;
			List receivedBookList= null;
			ViewStudentPersonAccountDetails viewStudentDetails =null;
			if (accountId>0) {
				viewStudentDetails =(ViewStudentPersonAccountDetails) adminManager.get(ViewStudentPersonAccountDetails.class,"custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and accountId="+getUser().getSelectedStudentId());
					if(getUser().isParent() && !ObjectFunctions.isNullOrEmpty(viewStudentDetails)){
						requestedBooksList=libraryManager.getAll (Reservations.class," accountId="+viewStudentDetails.getAccountId()+" and custId="+getUserCustId ()+" and status='R' ");
					}else{
						requestedBooksList=libraryManager.getAll (Reservations.class," accountId="+accountId+" and custId="+getUserCustId()+" and status='R' ");
					}
					if (ObjectFunctions.isNotNullOrEmpty (requestedBooksList)) {
						getObjectList().addAll(requestedBooksList);
						Collections.sort(getObjectList());
					}
					if(getUser().isParent() && !ObjectFunctions.isNullOrEmpty(viewStudentDetails)) 
						receivedBookList=libraryManager.getStaffRequestBooksByStaffAccountId (viewStudentDetails.getAccountId(),getUserCustId(),"A");
					else
						receivedBookList=libraryManager.getStaffRequestBooksByStaffAccountId(accountId,getUserCustId (),"A");
					if (!ObjectFunctions.isNullOrEmpty(receivedBookList))  {
			       		getTempList().addAll(receivedBookList);
			   			Collections.sort(getTempList());
					}
				viewStudentDetails=null;requestedBooksList=null;receivedBookList= null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
   	}
   /* revised by ramadevi */
   @Actions( { @Action(value = "ajaxAddBlock",  results = { @Result(location = "block/ajaxAllBloks.jsp", name = "success" )}) })
	public String addBlock() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'addBlock' method");
		}
		try {
			String blockName = getParamValue("blockName").trim();
			if (!ObjectFunctions.isNullOrEmpty(blockName)) {
			List blockNameList = libraryManager.checkBlockNameByCustId(blockName,getUserCustId());
				if (!ObjectFunctions.isNullOrEmpty(blockNameList)) {
	    			if(blockNameList.size()>0){
	    				super.addActionError("This block name already exist.");
	    				setObjectList(libraryManager.getAllBlocksByCustId(getUserCustId()));
	    				return SUCCESS;
	    			}
	    		}
				Block block =new Block();
				block.setCustId(getUserCustId());
				AcademicYear academicYear = getCurrentAcademicYear();
				block.setAcademicYear(academicYear);
				block.setBlockName(blockName);
				libraryManager.save(block);
				super.addActionMessage("Block added successfully.");
			}
			setObjectList(libraryManager.getAllBlocksByCustId(getUserCustId()));	       	
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Description 29th Apr cvs: Modularization remove the 2 method to one method */ 
   @Actions( { @Action(value = "ajaxGetBlockBySuibject",  results = { @Result(location = "block/ajaxShowBlocks.jsp", name = "success" )}),
	   			@Action(value = "ajaxGetBlockByOtherSuibject",  results = { @Result(location = "block/ajaxShowBlocks.jsp", name = "success" )})   
   })
	public String ajaxGetBlockByOtherSuibjetAndSubject() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetBlockByOtherSuibject' method");
		}
		try {		 
			RackDetails rackDetails =null;	
			List rackList=null;
			if(!StringFunctions.isNullOrEmpty(getPlSubjectName())){
				setSelectedId(getPlSubjectName());
				rackList=libraryManager.getRacksByOtherSubjectByCustId(getPlSubjectName(),getUserCustId());
			}else{
				setTempId(Long.valueOf(getSubjectId()));
				rackList=libraryManager.getRacksBySubjectByCustId(Long.valueOf(getSubjectId()),getUserCustId());
			}
			if(!ObjectFunctions.isNullOrEmpty(rackList)){
				StringBuffer tBuffer = new StringBuffer();
				if(!ObjectFunctions.isNullOrEmpty(rackList)){
					tBuffer.append("(");   
					for(Object obj : rackList){
						rackDetails=(RackDetails)obj;
						if(!ObjectFunctions.isNullOrEmpty(rackDetails)){
							tBuffer.append(rackDetails.getBlock().getId());
							tBuffer.append(",");
						}
					}
					tBuffer.append("0)");
					List blockList1 = libraryManager.getBlocksByBlockId(tBuffer.toString());
					if(!ObjectFunctions.isNullOrEmpty(blockList1)){
						if(!StringFunctions.isNullOrEmpty(getPlSubjectName())){
							getTempList().addAll(blockList1);
						}else{
							getObjectList().addAll(blockList1);
						}
						blockList1=null;
					}
					rackList=null;
					tBuffer=null;
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Description 29th Apr cvs: Modularization remove the 2 method to one method */ 
   @Actions( { @Action(value = "ajaxGetEditBlockBySuibject",  results = { @Result(location = "block/ajaxEditShowBlocks.jsp", name = "success" )}),
			@Action(value = "ajaxGetEditBlockByOtherSuibject",  results = { @Result(location = "block/ajaxEditShowBlocks.jsp", name = "success" )})   
	})
	public String ajaxGetEditBlockByOtherSuibjetAndSubject() throws URTUniversalException {
	if (log.isDebugEnabled()) {
		log.debug("Entering 'ajaxGetBlockByOtherSuibject' method");
	}
	try {		 
		RackDetails rackDetails =null;	
		List rackList=null;
		if(!StringFunctions.isNullOrEmpty(getPlSubjectName())){
			setSelectedId3(getPlSubjectName());
			rackList=libraryManager.getRacksByOtherSubjectByCustId(getPlSubjectName(),getUserCustId());
		}else{
			setTempId(Long.valueOf(getSubjectId()));
			rackList=libraryManager.getRacksBySubjectByCustId(Long.valueOf(getSubjectId()),getUserCustId());
		}
		if(!ObjectFunctions.isNullOrEmpty(rackList)){
			StringBuffer tBuffer = new StringBuffer();
			if(!ObjectFunctions.isNullOrEmpty(rackList)){
				tBuffer.append("(");   
				for(Object obj : rackList){
					rackDetails=(RackDetails)obj;
					if(!ObjectFunctions.isNullOrEmpty(rackDetails)){
						tBuffer.append(rackDetails.getBlock().getId());
						tBuffer.append(",");
					}
				}
				tBuffer.append("0)");
				setBlockList(libraryManager.getBlocksByBlockId(tBuffer.toString()));
				rackList=null;
				tBuffer=null;
			}
		}
	}
	catch(Exception ex) {
		ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	return SUCCESS;
	}
   /* @Description 29th Apr cvs: Modularization remove Unnecessary code */ 
   @Actions( { @Action(value = "ajaxGetRacksByBlock",  results = { @Result(location = "block/ajaxShowRacks.jsp", name = "success" )}) })
	public String ajaxGetRacksByBlock() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxBlockBySuibject' method");
		}
		try {			
			if(!ObjectFunctions.isNullOrEmpty(getBlockId()) && !StringFunctions.isNullOrEmpty(getSubjectId())){
				List rackList=libraryManager.getRacksByBlockIdAndSubjectId(getBlockId(),Long.valueOf(getSubjectId()),getUserCustId());
				if(!ObjectFunctions.isNullOrEmpty(rackList)){
						getTempList().addAll(rackList);
					}
				}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Description 29th Apr cvs: Modularization remove Unnecessary code */ 
   @Actions( { @Action(value = "ajaxGetOtherRacksByBlock",  results = { @Result(location = "block/ajaxShowRacks.jsp", name = "success" )}) })
	public String ajaxGetOtherRacksByBlock() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetOtherRacksByBlock' method");
		}
		try {			
			if(!ObjectFunctions.isNullOrEmpty(getBlockId()) && !ObjectFunctions.isNullOrEmpty(getPlSubjectName())){
				setTempList(libraryManager.getAll(RackDetails.class," custId="+getUserCustId()+" and otherSubjects='"+getPlSubjectName()+"' and blockId="+getBlockId()));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Description 29th Apr cvs: Modularization remove Unnecessary code */ 
   @Actions( { @Action(value = "ajaxGetEditRacksByBlock",  results = { @Result(location = "block/ajaxEditShowRacks.jsp", name = "success" )}) })
	public String ajaxGetEditRacksByBlock() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetEditRacksByBlock' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getBlockId())){
				setRackDetailList(libraryManager.getAll(RackDetails.class, "custId=" + getUserCustId()+ " and blockId="+ getBlockId()+""));
				}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
 /* @Description 15rd Apr cvs: Modularization   below method*/ 
  @Actions( { @Action(value = "ajaxGetEditOtherRacksByBlockName",  results = { @Result(location = "block/ajaxEditShowRacks.jsp", name = "success" )}) })
	public String ajaxGetEditOtherRacksByBlock() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxGetEditOtherRacksByBlock' method");
		}
		try {			
			if(!ObjectFunctions.isNullOrEmpty(getBlockId()) && !ObjectFunctions.isNullOrEmpty(getPlSubjectName())){
				setRackDetailList(libraryManager.getAll(RackDetails.class," custId="+getUserCustId()+" and otherSubjects="+getPlSubjectName()+" and blockId="+getBlockId()));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   
  /* @Description 1st may cvs: Modularization   below method*/ 
   @Actions( { @Action(value = "ajaxDoReadingDetails",  results = { @Result(location = "ajaxReadingBooksDetails.jsp", name = "success" )}) })
	public String doReadingDetails() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'doReadingDetails' method");
		}
		try {
			StringBuffer sqlQuery = null;
		//	ViewBookAndLabelDetails bookTitle = null;
			if(getUserCustId() > 0){
            	sqlQuery = new StringBuffer("select bt.bookName,ss.name,bt.otherSubjects,bt.author,bt.publisher, bt.noOfCopies ," +
            			"(SELECT count(*)  FROM bookLables bls  WHERE bls.bookTitleId = bt.Id and bls.custId="+getUserCustId()+" and bls.bookLabelStatus='"+Constants.BOOK_OPEND+"' and bls.type='"+Constants.READING_BOOK+"' and bls.deleteStatus='"+Constants.NO_STRING+"') As booksCount," +
					"  bt.issueBookCount,bt.acquisitionNumber,bt.bookEdition,IFNULL(b.blockName,'') AS blockName,IFNULL(rd.rackName,'') AS rackName  from bookTitle bt LEFT JOIN bookLables  bl on (bt.id = bl.bookTitleId) LEFT JOIN studySubject ss on (bt.subjectId=ss.id) left join booksAssignToRack ba on (bt.id=ba.bookTitleId) left join rackDetails rd on(rd.id=ba.rackDetailsId) left join block b on(rd.blockId=b.id) ")
            	 .append(" where bt.custId=").append(getUserCustId()).append(" and bt.academicYearId ="+getUserAcademicYearId()).append(" and bl.deleteStatus='"+Constants.NO_STRING+"' and bl.bookLabelStatus='"+Constants.BOOK_OPEND+"' and bl.type='"+Constants.READING_BOOK+"'").append(" group by bl.bookTitleId");
				List  availableRBbooks = libraryManager.getAll(sqlQuery.toString());
				log.debug(sqlQuery.toString());
				if(!ObjectFunctions.isNullOrEmpty(availableRBbooks)){
					setObjectList(availableRBbooks);
				}
			/*List bookAndBlockList=libraryManager.getAll(ViewBookTitleAndBlockDetails.class," custId="+getUserCustId());
			ViewBookTitleAndBlockDetails bookTitle=null;
			if(!ObjectFunctions.isNullOrEmpty(bookAndBlockList)){
				for(Object obj : bookAndBlockList){
					bookTitle=(ViewBookTitleAndBlockDetails)obj;
					List avableBooksList=libraryManager.getIssuedBookCountByStatusAndCustId(bookTitle.getBookTitleId(),Constants.BOOK_OPEND,Constants.READING_BOOK,getUserCustId(),"");
					if (!ObjectFunctions.isNullOrEmpty(avableBooksList)) {
						bookTitle.setBooksAvaliableList(avableBooksList);
						getObjectList().add(bookTitle);
						bookTitle=null;
					}
				}
			} */
		}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
  
   /* @Description 30th Apr cvs: Modularization  below method  remove the unnecessary code  change the  LibraryAction*/
   @Actions( { @Action(value = "ajaxViewLibrarySettings",  results = { @Result(location = "librarySettings/viewLibrarySettings.jsp", name = "success" )}) })
	public String ajaxDoLibrarySettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoLibrarySettings' method");
		}
		try {				
			LibrarySettings librarySettings = libraryManager.getLibrarySettingsByCustId(getUserCustId(),getUserAcademicYearId());
			if(!ObjectFunctions.isNullOrEmpty(librarySettings)){
				getObjectList().add(librarySettings);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* revised by ramadevi */
   @Actions( { @Action(value = "ajaxSaveLibrarySettings",  results = { @Result(location = "librarySettings/viewLibrarySettings.jsp", name = "success" )}) })
	public String ajaxSaveLibrarySettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSaveLibrarySettings' method");
		}
		try
		{
			if(!ObjectFunctions.isNullOrEmpty(getUserCustId())){
				getLibrarySettings().setAcademicYear(getCurrentAcademicYear());
				getLibrarySettings().setCustId(getUserCustId());
				libraryManager.save(getLibrarySettings());
				super.addActionMessage("Library settings added successfully.");
			 }
			ajaxDoLibrarySettings();
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
   /* @Description 30th Apr cvs: Modularization  below method change the  LibraryAction*/
   @Actions( { @Action(value = "ajaxDoEditLibSettings",  results = { @Result(location = "librarySettings/ajaxDoEditLibSettings.jsp", name = "success" )}) })
	public String ajaxDoEditLibSettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoLibrarySettings' method");
		}
		try {			
			if(!ObjectFunctions.isNullOrEmpty(getTempId())){
				setLibrarySettings((LibrarySettings)libraryManager.get(LibrarySettings.class, getTempId()));//Here tempId is librarysettings Id
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

   /* @Description 30th Apr cvs: Modularization  below method  remove the unnecessary code  change the  LibraryAction*/
   @Actions( { @Action(value = "ajaxUpdateLibSettings",  results = { @Result(location = "librarySettings/viewLibrarySettings.jsp", name = "success" )}) })
	public String ajaxUpdateLibSettings() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUpdateLibSettings' method");
		}
		try {
			if (!ObjectFunctions.isNullOrEmpty(getTempId())) {        		
				LibrarySettings librarySettings = (LibrarySettings)libraryManager.get(LibrarySettings.class,getTempId());//Here tempId is librarysettings Id
				librarySettings.setNoOfStaffIssuBooks(getLibrarySettings().getNoOfStaffIssuBooks());
				librarySettings.setNoOfStudentIssuBooks(getLibrarySettings().getNoOfStudentIssuBooks());
				librarySettings.setFineAmountPerDay(getLibrarySettings().getFineAmountPerDay());
				librarySettings.setLimitDays(getLibrarySettings().getLimitDays());
				librarySettings.setNoOfDaysForReuturnStudents(getLibrarySettings().getNoOfDaysForReuturnStudents());
				librarySettings.setNoOfDaysForReuturnStaff(getLibrarySettings().getNoOfDaysForReuturnStaff());
			//	LibrarySettings ls=getLibrarySettings();
				libraryManager.save(librarySettings);
				super.addActionMessage("Library settings updated successfully.");
				ajaxDoLibrarySettings();		
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}

   /* Here we r getting booknumbers trough ViewBookAndLabelDetails Done by cvs */
   	@Actions( { @Action(value = "ajaxViewBookNumbers", results = { @Result(location ="ajaxEditBookNumbers.jsp", name = "success") }) })
   	public String ajaxViewBookNumbers() throws URTUniversalException {
   		if (log.isDebugEnabled()) {
   			log.debug("Entering 'ajaxViewBookNumbers' method");
   		}
   		try {  	
   			if(!StringFunctions.isNullOrEmpty(getSelectedId())){	
   				
   				List<ViewBookAndLabelDetails> viewBookAndLabelDetailsList = libraryManager.getAll(ViewBookAndLabelDetails.class," custId="+getUserCustId()+" and bookTitleId="+getSelectedId()+" and deleteStatus='"+Constants.NO_STRING+"' group by bookLableId order by type");
   				if (!ObjectFunctions.isNullOrEmpty(viewBookAndLabelDetailsList)) 
   				{
   					List<ViewBookAndLabelDetails> openBooksList = new ArrayList<ViewBookAndLabelDetails>();
   					List<ViewBookAndLabelDetails> issuedBooksList = new ArrayList<ViewBookAndLabelDetails>();
   					List<Long> issuedBookLableList = libraryManager.getIssueBookLables(getUserCustId(),getSelectedId());
   					for(ViewBookAndLabelDetails ViewBookAndLabelDetails : viewBookAndLabelDetailsList)
   					{
   						if(issuedBookLableList.contains(ViewBookAndLabelDetails.getBookLableId()))
   		   				{
   							ViewBookAndLabelDetails.setBookLabelStatus("I");
   							issuedBooksList.add(ViewBookAndLabelDetails);
   		   				}else{
   		   				     openBooksList.add(ViewBookAndLabelDetails);
   		   				}
   					}
   					openBooksList.addAll(issuedBooksList);
   					getTempList2().addAll(openBooksList);
   					openBooksList= null;
   					issuedBooksList= null;
   					issuedBookLableList= null;
   				}
   				
   			}
   		} catch (Exception ex) {
   			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
   		}
   		return SUCCESS;
   	}
/* done by ramadevi*/
   @Actions( { @Action(value = "ajaxDeleteBooks",  results = { @Result(location = "ajaxEditBookNumbers.jsp", name = "success" )}) })
	public String ajaxDeleteBooks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDeleteBooks' method");
		}
		try {
			if(!StringFunctions.isNullOrEmpty(getSearchBookNumber()) && !StringFunctions.isNullOrEmpty(getSelectedId())){
					BookLables bookLables=(BookLables)libraryManager.get(BookLables.class,Long.valueOf(getSearchBookNumber()));
					bookLables.setDeletedById(getUser().getId());
					bookLables.setDeleteStatus(Constants.YES_STRING);					
					//libraryManager.save(bookLables);
					BookTitle bookTitle = (BookTitle) libraryManager.get(BookTitle.class, Long.valueOf(getSelectedId()));
			            if (!ObjectFunctions.isNullOrEmpty(bookTitle)) {
			            	if(Constants.ISSUE_BOOK.equalsIgnoreCase(getAnyTitle())){
			            		bookTitle.setIssueBookCount(bookTitle.getIssueBookCount()-1);
			            	}else{
			            		bookTitle.setReadingBookCount(bookTitle.getReadingBookCount()-1);
			            	}
			            	bookTitle.setNoOfCopies(bookTitle.getNoOfCopies()-1);
			            	bookTitle.setTotalCost((bookTitle.getNoOfCopies()-1)*(bookTitle.getCost()));
			            	bookTitle.addBookLablesSettings(bookLables);
			            	libraryManager.merge(bookTitle);
			            }
			            //Siva - Soft deleting the book when there are no book lables 
			            String sqlQuery = "SELECT id,count(*)  FROM bookLables  WHERE custId="+getUserCustId()+" and bookTitleId="+bookTitle.getId()+" AND deleteStatus!='Y'";
						Object[] booksLabAry = adminManager.get(sqlQuery);
						if(!ObjectFunctions.isNullOrEmpty(booksLabAry)){
							   if(Integer.parseInt(booksLabAry[1].toString()) <= 0){
								   bookTitle.setStatus(Constants.NO_STRING);
							   	   libraryManager.merge(bookTitle);
							   }
						}
						bookTitle=null;
						//End
					super.addActionMessage("Book is deleted succefully.");
				}
				setSelectedId(getSelectedId());
				ajaxViewBookNumbers();		
		}
		catch(Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	} 
   public String getAllIssuedBooks(User user){
	   setLibrarySettings((LibrarySettings)libraryManager.getLibrarySettingsByCustId(getUserCustId(),getUserAcademicYearId()));
	   List issuedBookList = libraryManager.getAll(IssuedBook.class," accountId="+ user.getId() +" and status='"+Constants.PENDING_STATUS+"' and custId="+getUserCustId());
		if(!ObjectFunctions.isNullOrEmpty(issuedBookList)){
		if (user.isSchoolStudent() || user.isSchoolPrincipal() || user.isSchoolTeacher() || user.isOnlySchoolHod() || user.isSchoolDirector()) {
			if(issuedBookList.size()>=getLibrarySettings().getNoOfStudentIssuBooks()){
				super.addActionError("Already taken "+getLibrarySettings().getNoOfStudentIssuBooks()+" books. Please find the books details below.");
				setBookTitleList(issuedBookList);
				return "userNotExist";
			}
		}
		else{
			super.addActionError("This book is not eligible for you. Because of your "+ user.getUserRoleDescription());
			return SUCCESS;
		}
		getTempList().addAll(issuedBookList);
	}
		return SUCCESS;
   }
   public String getIssuedBooks(long accountId,long bookTitleId){
	  setIssuedBook((IssuedBook) libraryManager.get(IssuedBook.class," bookId="+bookTitleId+" and status='"+Constants.PENDING_STATUS+ "' and custId="+ getUserCustId()+" and accountId="+accountId));
		if (!ObjectFunctions.isNullOrEmpty(getIssuedBook())) {
			setTempString("Closed");
			super.addActionError("You have already taken '"+ getIssuedBook().getBookTitle().getBookName()+"' book.");
	   }
		return SUCCESS;
   }
   
   /*
	 * Import Books to system. 
	 */
	/*@Actions( { @Action(value = "ajaxDoImportSchoolBooks", results = { @Result(location = "ajaxImportSchoolBooks.jsp", name = "success") }),
		 @Action(value = "ajaxDoEditSchoolBooks", results = { @Result(location = "ajaxEditSchoolBooks.jsp", name = "success") }) 	
	})
	public String ajaxDoImportSchoolBooks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoImportSchoolBooks' method");
		}
		try {			
			// Nothing TO DO here.
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}*/
	@Actions( { @Action(value = "ajaxDownloadTemplateBooks", results = {}) })
	public void ajaxDownloadTemplateBooks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDownloadTemplateBooks' method");
		}
		try{
		if (getUserAcademicYearId() != 0) {
			
			String fileName = "LibraryBooks_Details_"+ DateUtil.getDateTime(DateFormatter.MMDDYYY_PATTERN,new Date());
			getResponse().setContentType("application/vnd.ms-excel");
			getResponse().setHeader("Content-Disposition","attachment; filename=" + fileName.replace(' ', '_')+ ".xls");
			PrepareLibraryBooksExcel prepareLibraryBooksExcel = new PrepareLibraryBooksExcel();
			
			/** The below commented code reference is useful .Please do not remove it until it is really need to remove. */
			List<StudySubject> studySubjectsList = adminManager.GetAllStudySubjects(getUserCustId(),getUserAcademicYearId());
			List<ClassName> classesList = adminManager.getAllClassNames( getUserCustId(),getUserAcademicYearId());
			prepareLibraryBooksExcel.createConfigurationsSheet("Configurations",getUserAcademicYearId(),getUserCustId(),studySubjectsList,classesList);
			// prepareLibraryBooksExcel.createAcademicAndCustIdConfigSheet("Configurations",getUserAcademicYearId(),getUserCustId());
			
			StringBuffer sheetTitleDesc = new StringBuffer();
			sheetTitleDesc.append("School Name : ");
			if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("organization"))) {
				sheetTitleDesc.append((String) getSession().getAttribute("organization"));
			}
			sheetTitleDesc.append(", Academic Year : ");
			if (!ObjectFunctions.isNullOrEmpty(getSession().getAttribute("academicYearName"))) {
				sheetTitleDesc.append((String) getSession().getAttribute("academicYearName"));
			}
			
			if((!"AG".equalsIgnoreCase(getAnyId()) || "MG".equalsIgnoreCase(getAnyId())) && !"data".equalsIgnoreCase(getAnyTitle()))
			{
				prepareLibraryBooksExcel.createLibraryBookSheet("Library Books Details",sheetTitleDesc.toString(),getAnyId());
			}
			else{
				List<Object[]> libraryDetails  = libraryManager.getAll(PrepareLibraryBooksExcel.query+ " where bt.custId="+getUserCustId()  + " and bt.academicYearId<="+getUserAcademicYearId()+" and bl.deleteStatus='"+Constants.NO_STRING+"' group by bl.bookTitleId");
				prepareLibraryBooksExcel.createLibraryBookSheet("Library Books Details",libraryDetails,sheetTitleDesc.toString(),getAnyId());
				libraryDetails = null;
			}
			
			prepareLibraryBooksExcel.finalPrep("Configurations");
			prepareLibraryBooksExcel.getWb().write(getResponse().getOutputStream());
			prepareLibraryBooksExcel = null;
		}
		}
		catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
	}
	@Actions( { @Action(value = "ajaxUploadLibraryBooksTemplate", results = {@Result(location = "ajaxImportSchoolBooks.jsp", name = "success"),
			@Result(location = "ajaxEditSchoolBooks.jsp", name = "editSchoolBooks"),
			@Result(location = "ajaxImportSchoolBooks.jsp", name = "dummyInit")}) })
	public String ajaxUploadStudentData() throws ExcelParsingException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxUploadLibraryBooksTemplate' method");
		}
		try {
			boolean excelFileType = false;
			excelFileType = validateExcelFileType(getUploadContentType());
			if(excelFileType){
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				return "dummyInit";
			}
			/*if (!(Constants.FILE_TYPE_XLS.equalsIgnoreCase(getUploadContentType()) || Constants.FILE_TYPE_APPLICATION_MS_EXCEL.equalsIgnoreCase(getUploadContentType()) || Constants.FILE_TYPE_APPLICATION_STREAM_XLS.equalsIgnoreCase(getUploadContentType()) || Constants.FILE_TYPE_APPLICATION_XLS.equalsIgnoreCase(getUploadContentType()))) {
				log.debug("No file to upload....");
				super.addActionError("File type not valid.");
				return "dummyInit";
			}*/
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getUpload()));
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("Configurations");
						
			long academicYearId = 0;
			long custId = 0;
			if (ObjectFunctions.isNullOrEmpty(sheet)) {
				/*academicYearId = getUserAcademicYearId();
				custId = getUserCustId();*/
				
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				if("edit".equalsIgnoreCase(getAlertSendType()))
					return "editSchoolBooks";
				else
					return "dummyInit";
				
			} else {
				Row row = sheet.getRow(0);
				academicYearId = (long) row.getCell(1).getNumericCellValue();
				row = sheet.getRow(1);
				custId = (long) row.getCell(1).getNumericCellValue();
			}
			
			org.apache.poi.ss.usermodel.Sheet firstSheet1 = workbook.getSheetAt(0);
			Row secondRow = firstSheet1.getRow(2);
			if(!ObjectFunctions.isNullOrEmpty(secondRow))
			{
				if(!ObjectFunctions.isNullOrEmpty(secondRow.getCell(0)) && !ObjectFunctions.isNullOrEmpty(secondRow.getCell(1)))
				{
					String fistColumn = secondRow.getCell(0).getStringCellValue();
					String secondColumn = secondRow.getCell(1).getStringCellValue();
					
					if(!"Book Title Id".equalsIgnoreCase(fistColumn.toString()) || !"Subjects".equalsIgnoreCase(secondColumn.toString()))
					{
						log.debug("No file to upload....");
						super.addActionError("File type not matched.");
						if("edit".equalsIgnoreCase(getAlertSendType()))
							return "editSchoolBooks";
						else
							return "dummyInit";
					}
					fistColumn = null;
					secondColumn = null;
				}
				else
				{
					log.debug("No file to upload....");
					super.addActionError("File type not matched.");
					if("edit".equalsIgnoreCase(getAlertSendType()))
						return "editSchoolBooks";
					else
						return "dummyInit";
				}
			}
			else
			{
				log.debug("No file to upload....");
				super.addActionError("File type not matched.");
				if("edit".equalsIgnoreCase(getAlertSendType()))
					return "editSchoolBooks";
				else
					return "dummyInit";
			}
			
			
			log.debug("Uploading library books AcademicYearId****** "+academicYearId+" **** UserCustId***** "+custId);	
			if(custId !=0){
				StringBuffer sqlQuery = null;
				/*List<BookTitle> bookTitlesList = null;
				SheetParser parser = null;
				AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, academicYearId);
				sheet = workbook.getSheetAt(0);
				if(!ObjectFunctions.isNullOrEmpty(academicYear) && !ObjectFunctions.isNullOrEmpty(sheet)){
						parser = new SheetParser();
						bookTitlesList = parser.createEntity(sheet, sheet.getSheetName(), BookTitle.class);
						int count=2;
						if (ObjectFunctions.isNotNullOrEmpty(bookTitlesList)) {
							for (BookTitle bookDetails : bookTitlesList) {
								bookDetails.setCost(Integer.parseInt(bookDetails.getCostStr()));
								bookDetails.setCustId(custId);
								bookDetails.setTotalCost(Integer.parseInt(bookDetails.getCostStr()));
								bookDetails.setStatus("Y");
								bookDetails.setAcademicYear(academicYear);
								if(StringFunctions.isNullOrEmpty(bookDetails.getBookName())){
									bookDetails.setBookName("No Title");
								}
								if(StringFunctions.isNullOrEmpty(bookDetails.getAuthor())){
									bookDetails.setBookName("No Author");
									bookDetails.setAuthor("No Author");
								}
								if(StringFunctions.isNullOrEmpty(bookDetails.getOtherSubjects())){
									bookDetails.setBookName("No Subject");
									bookDetails.setOtherSubjects("No Subject");
								}
								if(StringFunctions.isNullOrEmpty(bookDetails.getPublisher())){
									bookDetails.setBookName("No Publisher");
									bookDetails.setPublisher("No Publisher");
								}
								if(!StringFunctions.isNullOrEmpty(bookDetails.getBookPublishedDateStr())){
									bookDetails.setBookPublishedDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, bookDetails.getBookPublishedDateStr())));
								}
								if(ObjectFunctions.isNullOrEmpty(sheet.getRow(count++))){
									super.addActionMessage("Library books added successfully.");
									break;
								}
								libraryManager.save(bookDetails);
								log.debug("Row count is ....."+count);
								bookDetails = null;
							}
							home();
							bookTitlesList = null;
						}
						parser = null;
				}*/	
			/** This is for old code reference  , Please do not remove it until it is really need to remove.*/	
			 List<BookTitle> bookTitlesList = null;
			 List<BookLables> bookLablesList=null;
			 List<IssuedBook> issuedBooks;
				BookLables bookLables  =null;
				StringBuffer cellNumber = new StringBuffer();
				StringBuffer cellNumbers = new StringBuffer();
				StringBuffer acnoNo = new StringBuffer();
				StringBuffer bookNo=new StringBuffer();
				AcademicYear academicYear = (AcademicYear) adminManager.get(AcademicYear.class, academicYearId);
				
				StudySubject studySubj=null;
				ClassName classNameOb=null;
			
				if(!ObjectFunctions.isNullOrEmpty(academicYear)){
					
				HashMap<String, StudySubject> studySubjectMap = new HashMap<String, StudySubject>();
				List<StudySubject> studySubjectsList = adminManager.GetAllStudySubjects(getUserCustId(),getUserAcademicYearId());
				if (ObjectFunctions.isNotNullOrEmpty(studySubjectsList)) {
					for (StudySubject studySub : studySubjectsList) {
						studySubjectMap.put(studySub.getName().trim(), studySub);
					}
				}
				HashMap<String, ClassName> claaNameMap = new HashMap<String, ClassName>();
				List<ClassName> classesList = adminManager.getAllClassNames( getUserCustId(),getUserAcademicYearId());
				if (ObjectFunctions.isNotNullOrEmpty(classesList)) {
					for (ClassName classNameObj : classesList) {
						claaNameMap.put(classNameObj.getClassName(), classNameObj);
					}
				}
					//BookTitle bookTitleObj=null;
					char ch=0;
			        int k=1;
					int readCount=1;
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
				//	int exixtedCount=1;
					int count=2;
 					int loopCount=1;
					String nodata = null;
					String dataScuccess = null;
				for (int sheetNo = 0; sheetNo < (workbook.getNumberOfSheets() - 1); sheetNo++) {
					sheet = workbook.getSheetAt(sheetNo);
					SheetParser parser = new SheetParser();
					bookTitlesList = parser.createEntity(sheet, sheet.getSheetName(), BookTitle.class);
					long countNo = 0;
					if (ObjectFunctions.isNotNullOrEmpty(bookTitlesList)) {
						for (BookTitle bookDetails : bookTitlesList) 
						{
							if(loopCount > 4) break;
							if(ObjectFunctions.isNullOrEmpty(sheet.getRow(count++))){
								loopCount++;
								continue;
							}
							loopCount=1;
							if(!StringFunctions.isNullOrEmpty(bookDetails.getAcquisitionNumber()) && !StringFunctions.isNullOrEmpty(bookDetails.getBookName())){
								if(!StringFunctions.isNullOrEmpty(bookDetails.getAcquisitionNumber())){
									//if(!StringFunctions.isNullOrEmpty(bookDetails.getBookName())){
									bookDetails.setBookName(bookDetails.getBookName().replaceAll("'","~"));
								//}
								if(!StringFunctions.isNullOrEmpty(bookDetails.getAuthor())){
									bookDetails.setAuthor(bookDetails.getAuthor().replaceAll("'","~"));
								}
								if(!StringFunctions.isNullOrEmpty(bookDetails.getPublisher())){
									bookDetails.setPublisher(bookDetails.getPublisher().replaceAll("'","~"));
								}
								if(!StringFunctions.isNullOrEmpty(bookDetails.getBookEdition())){
									bookDetails.setBookEdition(bookDetails.getBookEdition().replaceAll("'","~"));
								}
								if(!StringFunctions.isNullOrEmpty(bookDetails.getPlace())){
									bookDetails.setPlace(bookDetails.getPlace().replaceAll("'","~"));
								}
								if(!StringFunctions.isNullOrEmpty(bookDetails.getBookRemarks())){
									bookDetails.setBookRemarks(bookDetails.getBookRemarks().replaceAll("'","~"));
								}
								
								if(!StringFunctions.isNullOrEmpty(bookDetails.getStudySubject().getName())){
									studySubj = (StudySubject) studySubjectMap.get(bookDetails.getStudySubject().getName().trim());
								}
								/*if(!ObjectFunctions.isNullOrEmpty(studySubj))
									bookTitleObj=(BookTitle) libraryManager.get(BookTitle.class, "(bookName='"+bookDetails.getBookName()+"' or otherSubjects='"+bookDetails.getBookName()+"') and author='"+bookDetails.getAuthor()+"' and publisher='"+bookDetails.getPublisher()+"' and subjectId="+studySubj.getId()+" and custId="+custId);	
								else
									bookTitleObj=(BookTitle) libraryManager.get(BookTitle.class, "(bookName='"+bookDetails.getBookName()+"' or otherSubjects='"+bookDetails.getBookName()+"') and author='"+bookDetails.getAuthor()+"' and publisher='"+bookDetails.getPublisher()+"' and custId="+custId+"");*/
								
								
								String randamNumber=Math.round(Math.random()*100)+DateFormatter.formatDate(DateFormatter.DD_PATTERN, new Date());
							    ch=0;
						        k=1;
								readCount=1;
								cal = Calendar.getInstance();
							    year = cal.get(Calendar.YEAR); 
							//    exixtedCount=1;
							    if("edit".equalsIgnoreCase(getAlertSendType()))
							    {
							    	 BookTitle bookTitleObj = (BookTitle) libraryManager.get(BookTitle.class, "id='"+bookDetails.getBookTitleId()+"' and custId="+getUserCustId() + " ");
										if(!ObjectFunctions.isNullOrEmpty(bookTitleObj))
										{ // If book is existed then update
											sqlQuery = new StringBuffer("select bt.id from bookTitle bt left join bookLables bls on(bt.id=bls.bookTitleId) where bls.bookTitleId not in("+bookTitleObj.getId()+")  and bls.deleteStatus='"+Constants.NO_STRING+"' and bt.acquisitionNumber='"+bookDetails.getAcquisitionNumber()+"' and bls.custId="+getUserCustId()+" ");
							            	//log.debug(sqlQuery.toString());
											setTempList2(libraryManager.getAll(sqlQuery.toString()));
											if(getTempList2().size() < 1)
											{
												bookTitleObj.setAcquisitionNumber(bookDetails.getAcquisitionNumber());
													if(!ObjectFunctions.isNullOrEmpty(studySubj))
														bookTitleObj.setStudySubject(studySubj);
													else
													if(!StringFunctions.isNullOrEmpty(bookDetails.getOtherSubjects()))
														bookTitleObj.setOtherSubjects(bookDetails.getOtherSubjects());
													else
														bookTitleObj.setOtherSubjects("No Subject");
													if(!StringFunctions.isNullOrEmpty(bookDetails.getBookName()))
														bookTitleObj.setBookName(bookDetails.getBookName());
													else
														bookTitleObj.setBookName("No Title");
													if(!StringFunctions.isNullOrEmpty(bookDetails.getAuthor()))
														bookTitleObj.setAuthor(bookDetails.getAuthor());
													else
														bookTitleObj.setAuthor("No Author");
													if(!StringFunctions.isNullOrEmpty(bookDetails.getPublisher()))
														bookTitleObj.setPublisher(bookDetails.getPublisher());
													else
														bookTitleObj.setPublisher("No Publisher");
													if(!StringFunctions.isNullOrEmpty(bookDetails.getBookEdition()))
														bookTitleObj.setBookEdition(bookDetails.getBookEdition());
													else
														bookTitleObj.setBookEdition("No Edition");
													if(!StringFunctions.isNullOrEmpty(bookDetails.getLanguage()))
														bookTitleObj.setLanguage(bookDetails.getLanguage());
													else
														bookDetails.setLanguage("English");
												bookTitleObj.setStudySubject(studySubj);
												classNameOb =(ClassName) claaNameMap.get(bookDetails.getClassName().getClassName());
												bookTitleObj.setClassName(classNameOb);
												
												/*if(bookDetails.getNoOfCopies()!=bookTitleObj.getNoOfCopies()){
												      bookLable = (List<BookLables>) adminManager.getAll(BookLables.class, "bookTitleId="+bookDetails.getBookTitleId());
												      for(BookLables bookLable1:bookLable){
												    	  for(int i=0;i<bookDetails.getNoOfCopies();i++){
												    	  if("O".equalsIgnoreCase(bookLable1.getBookLabelStatus()))
												    			  bookLable1.setDeleteStatus(Constants.YES_STRING);
												    	          bookTitleObj.addBookLablesSettings(bookLable1);
												    	  }
												      }
												}
													*/
												//int avgCost=(bookTitleObj.getTotalCost()+Integer.parseInt(bookDetails.getCostStr())*Integer.parseInt(bookDetails.getNoOfCopiesStr())) / (bookTitleObj.getNoOfCopies()+Integer.parseInt(bookDetails.getNoOfCopiesStr()));
												bookTitleObj.setCost(Integer.parseInt(bookDetails.getCostStr()));	
												/*if(Integer.parseInt(bookDetails.getNoOfCopiesStr())<=0){
													bookDetails.setNoOfCopiesStr("1");
													bookDetails.setIssueBooksCountStr("1");
												
													bookTitleObj.setIssueBookCount(bookTitleObj.getIssueBookCount()+1);
													bookTitleObj.setIssueBookCount(bookDetails.getNoOfCopies());
												}
												else{
													if(Integer.parseInt(bookDetails.getIssueBooksCountStr())<=0 || Integer.parseInt(bookDetails.getNoOfCopiesStr()) < Integer.parseInt(bookDetails.getIssueBooksCountStr())){
														bookDetails.setIssueBooksCountStr(bookDetails.getNoOfCopiesStr());
													}
													bookTitleObj.setReadingBookCount(Integer.parseInt(bookDetails.getNoOfCopiesStr()) - Integer.parseInt(bookDetails.getIssueBooksCountStr()));
																						
													bookTitleObj.setIssueBookCount(Integer.parseInt(bookDetails.getIssueBooksCountStr()));
												}*/
												issuedBooks=(List<IssuedBook>) adminManager.getAll(IssuedBook.class, "bookId="+bookDetails.getBookTitleId() +" and  status='"+Constants.ACTIVE_STATUS+"'");
												if(Integer.parseInt(bookDetails.getNoOfCopiesStr()) < bookTitleObj.getNoOfCopies() && Integer.parseInt(bookDetails.getNoOfCopiesStr()) >= issuedBooks.size()){
													bookLablesList = (List<BookLables>) adminManager.getAll(BookLables.class, "bookTitleId="+bookDetails.getBookTitleId() +" and  bookLabelStatus='"+Constants.BOOK_OPEND+"'");
													int bookCount=0;
													for(BookLables bookLable:bookLablesList){
														List<IssuedBook> issuedBook=libraryManager.getAll(IssuedBook.class,"bookLableId="+bookLable.getId() +" and status='"+Constants.BOOK_SUBMITED+"'");
														if(!ObjectFunctions.isNullOrEmpty(issuedBook)){
															libraryManager.deleteIssuedBookById(bookLable.getId());
															issuedBook=null;
														}
														if(bookCount<bookTitleObj.getNoOfCopies()-Integer.parseInt(bookDetails.getNoOfCopiesStr())){
															libraryManager.deleteBookLablesById(bookLable.getId());
															bookCount++;
														}
													}
													bookLablesList=null;
												}
												else if(Integer.parseInt(bookDetails.getNoOfCopiesStr())>bookTitleObj.getNoOfCopies()){
											    	  for(int i=0;i<Integer.parseInt(bookDetails.getNoOfCopiesStr())-bookTitleObj.getNoOfCopies();i++){

															bookLables=new BookLables();
															bookLables.setBookLabelStatus(Constants.BOOK_OPEND);
															bookLables.setDeleteStatus(Constants.NO_STRING);
															if(readCount > bookDetails.getReadingBookCount()){
																bookLables.setType(Constants.ISSUE_BOOK);	
															}else{
																bookLables.setType(Constants.READING_BOOK);
															}
															if(alphabet.charAt(k)=='Z'){
											                    	k=1;
											                 }
															ch=alphabet.charAt(k);
															if(!StringFunctions.isNullOrEmpty(bookDetails.getBookLableCode()) && !StringFunctions.isNullOrEmpty(bookDetails.getBookKeyWord())){
																bookLables.setLableCode(bookDetails.getBookLableCode()+bookDetails.getBookKeyWord()+year+randamNumber+ch+i);	
															}
															else if(!StringFunctions.isNullOrEmpty(bookDetails.getBookKeyWord())){
																bookLables.setLableCode(bookDetails.getBookKeyWord()+year+randamNumber+ch+i);
															}
															else{
																bookLables.setLableCode(year+randamNumber+ch+i);
															}
															bookLables.setCustId(custId);
															bookTitleObj.addBookLablesSettings(bookLables);
															readCount++;
															k++;
															bookLables=null;
											    	  }
												}else
												{
													if(Integer.parseInt(bookDetails.getNoOfCopiesStr()) == bookTitleObj.getNoOfCopies())
														continue;
													bookNo.append(bookDetails.getBookName() + ",");
													continue;
												}
												if(issuedBooks.size() == 0 && (Integer.parseInt(bookDetails.getNoOfCopiesStr())) == 0){
													 libraryManager.deleteBookTitleById(bookTitleObj.getId());	
												}else{
												      if(Integer.parseInt(bookDetails.getNoOfCopiesStr()) <= 0 && bookNo.length() == 0){
												      bookDetails.setNoOfCopiesStr("0");
												      bookDetails.setIssueBooksCountStr("0");
												      //bookTitleObj.setIssueBookCount(bookTitleObj.getIssueBookCount()+1);
												     bookTitleObj.setIssueBookCount(bookDetails.getNoOfCopies());
											         }
											        else{
												    if(Integer.parseInt(bookDetails.getIssueBooksCountStr()) <=0 || Integer.parseInt(bookDetails.getNoOfCopiesStr()) < Integer.parseInt(bookDetails.getIssueBooksCountStr())){
													bookDetails.setIssueBooksCountStr(bookDetails.getNoOfCopiesStr());
												}
												bookTitleObj.setReadingBookCount(Integer.parseInt(bookDetails.getNoOfCopiesStr()) - Integer.parseInt(bookDetails.getIssueBooksCountStr()));
																					
												bookTitleObj.setIssueBookCount(Integer.parseInt(bookDetails.getIssueBooksCountStr()));
											}
												bookTitleObj.setNoOfCopies(Integer.parseInt(bookDetails.getNoOfCopiesStr()));
												bookTitleObj.setTotalCost(bookTitleObj.getTotalCost()+Integer.parseInt(bookDetails.getCostStr())* Integer.parseInt(bookDetails.getNoOfCopiesStr()));
												if(bookTitleObj.getIssueDays() < Integer.parseInt(bookDetails.getIssueDaysStr())){
													bookTitleObj.setIssueDays(Integer.parseInt(bookDetails.getIssueDaysStr()));	
												}
												if(!StringFunctions.isNullOrEmpty(bookDetails.getBookPublishedDateStr())){
													bookTitleObj.setBookPublishedDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, bookDetails.getBookPublishedDateStr())));
												}
												//exixtedCount=bookTitleObj.getReadingBookCount();
												
												//for(int i=0;i<Integer.parseInt(bookDetails.getNoOfCopiesStr());i++){
														//bookLables=new BookLables();
													/*	bookLables.setBookLabelStatus(Constants.BOOK_OPEND);
														bookLables.setDeleteStatus(Constants.NO_STRING);
														if(Integer.parseInt(bookDetails.getIssueBooksCountStr()) < Integer.parseInt(bookDetails.getNoOfCopiesStr())){
															if(exixtedCount > Integer.parseInt(bookDetails.getIssueBooksCountStr())){
																bookLables.setType(Constants.READING_BOOK);	
															}else{
																bookLables.setType(Constants.ISSUE_BOOK);
															}
														}else{
															bookLables.setType(Constants.ISSUE_BOOK);
														}
														*/
													/*	if(alphabet.charAt(k)=='Z'){
										                    	k=1;
										                 }
														ch=alphabet.charAt(k);
														if(!StringFunctions.isNullOrEmpty(bookDetails.getBookLableCode()) && !StringFunctions.isNullOrEmpty(bookDetails.getBookKeyWord())){
															bookLables.setLableCode(bookDetails.getBookLableCode()+bookDetails.getBookKeyWord()+year+randamNumber+ch+i+"N");	
														}
														else if(!StringFunctions.isNullOrEmpty(bookDetails.getBookKeyWord())){
															bookLables.setLableCode(bookDetails.getBookKeyWord()+year+randamNumber+ch+i+"N");
														}
														else{
															bookLables.setLableCode(year+randamNumber+ch+i+"N");
														}*/
												/*
														bookLables = (BookLables)adminManager.get(BookLables.class, "bookTitleId="+bookDetails.getBookTitleId());
														bookLables.setCustId(custId);
														bookTitleObj.addBookLablesSettings(bookLables);*/
														/*exixtedCount++;
														k++;*/
												//}
									        	 //libraryManager.save(bookTitleObj);
												   libraryManager.merge(bookTitleObj);
											}
											}
											else
												cellNumber.append(bookDetails.getAcquisitionNumber() + ",");
										}
										else{
											setAlertSendType("new");								
										}
							    }
							   if("new".equalsIgnoreCase(getAlertSendType()))
							    {
							    	/*BookTitle bookTitleObj = (BookTitle) libraryManager.get(BookTitle.class, "acquisitionNumber='"+bookDetails.getAcquisitionNumber()+"' and custId="+custId+" and status='"+Constants.YES_STRING+"'");
							    	if(!ObjectFunctions.isNullOrEmpty(bookTitleObj)){*/
										sqlQuery = new StringBuffer("select bt.id from bookTitle bt  left join bookLables bls on(bt.id=bls.bookTitleId) where  bls.deleteStatus='"+Constants.NO_STRING+"' and bt.acquisitionNumber='"+bookDetails.getAcquisitionNumber()+"' and bls.custId="+getUserCustId()+" ");
						            	//log.debug(sqlQuery.toString());
										setTempList1(libraryManager.getAll(sqlQuery.toString()));
							    	//}
									if(getTempList1().size() < 1){
							    		bookDetails.setCost(Integer.parseInt(bookDetails.getCostStr()));	
										if(Integer.parseInt(bookDetails.getNoOfCopiesStr())<=0 && Integer.parseInt(bookDetails.getIssueBooksCountStr())<=0){
										    bookDetails.setReadingBookCount(0);
											bookDetails.setNoOfCopies(1);
								        	bookDetails.setIssueBookCount(1); 
							        	    bookDetails.setTotalCost(Integer.parseInt(bookDetails.getCostStr()));
								        }
										else if(Integer.parseInt(bookDetails.getNoOfCopiesStr())<=0){
											bookDetails.setNoOfCopies(Integer.parseInt(bookDetails.getIssueBooksCountStr()));
								        	bookDetails.setIssueBookCount(Integer.parseInt(bookDetails.getIssueBooksCountStr()));
								        	bookDetails.setReadingBookCount(0);
								        	bookDetails.setTotalCost(Integer.parseInt(bookDetails.getCostStr())* Integer.parseInt(bookDetails.getNoOfCopiesStr()));
										}
										else if(Integer.parseInt(bookDetails.getIssueBooksCountStr())<=0){
											bookDetails.setNoOfCopies(Integer.parseInt(bookDetails.getNoOfCopiesStr()));
								        	bookDetails.setIssueBookCount(Integer.parseInt(bookDetails.getNoOfCopiesStr()));
								        	bookDetails.setReadingBookCount(0);
								        	bookDetails.setTotalCost(Integer.parseInt(bookDetails.getCostStr())* Integer.parseInt(bookDetails.getNoOfCopiesStr()));
										}
										else{
											if(Integer.parseInt(bookDetails.getIssueBooksCountStr()) > Integer.parseInt(bookDetails.getNoOfCopiesStr())){
												bookDetails.setIssueBookCount(Integer.parseInt(bookDetails.getNoOfCopiesStr()));
											}else{
												bookDetails.setIssueBookCount(Integer.parseInt(bookDetails.getIssueBooksCountStr()));
											}
											bookDetails.setNoOfCopies(Integer.parseInt(bookDetails.getNoOfCopiesStr()));
								        	if(Integer.parseInt(bookDetails.getNoOfCopiesStr()) - Integer.parseInt(bookDetails.getIssueBooksCountStr()) > 0)
								        		bookDetails.setReadingBookCount(Integer.parseInt(bookDetails.getNoOfCopiesStr()) - Integer.parseInt(bookDetails.getIssueBooksCountStr()));	
								        	else
								        		bookDetails.setReadingBookCount(0);
											
								        	bookDetails.setTotalCost(Integer.parseInt(bookDetails.getCostStr())* Integer.parseInt(bookDetails.getNoOfCopiesStr()));
								           }
										  if(ObjectFunctions.isNullOrEmpty(studySubj)){
											if(!StringFunctions.isNullOrEmpty(bookDetails.getOtherSubjects()))
												bookDetails.setOtherSubjects(bookDetails.getOtherSubjects());
											else
												bookDetails.setOtherSubjects("No Subject");
											}
											if(!StringFunctions.isNullOrEmpty(bookDetails.getBookName()))
												bookDetails.setBookName(bookDetails.getBookName());
											else
												bookDetails.setBookName("No Title");
											if(!StringFunctions.isNullOrEmpty(bookDetails.getAuthor()))
												bookDetails.setAuthor(bookDetails.getAuthor());
											else
												bookDetails.setAuthor("No Author");
											if(!StringFunctions.isNullOrEmpty(bookDetails.getPublisher()))
												bookDetails.setPublisher(bookDetails.getPublisher());
											else
												bookDetails.setPublisher("No Publisher");
											if(!StringFunctions.isNullOrEmpty(bookDetails.getBookEdition()))
												bookDetails.setBookEdition(bookDetails.getBookEdition());
											else
												bookDetails.setBookEdition("No Edition");
											if(!StringFunctions.isNullOrEmpty(bookDetails.getLanguage()))
												bookDetails.setLanguage(bookDetails.getLanguage());
											else
												bookDetails.setLanguage("English");
											
									bookDetails.setCustId(custId);
									bookDetails.setIssueDays(Integer.parseInt(bookDetails.getIssueDaysStr()));
									bookDetails.setAcademicYear(academicYear);
									
									if(!StringFunctions.isNullOrEmpty(bookDetails.getBookPublishedDateStr())){
										bookDetails.setBookPublishedDate(DateFormatter.parseString(DateFormatter.YYYY_MM_DD_PATTERN, DateFormatter.formatDate(DateFormatter.YYYY_MM_DD_PATTERN, bookDetails.getBookPublishedDateStr())));
									}
									bookDetails.setStudySubject(studySubj);
									classNameOb =(ClassName) claaNameMap.get(bookDetails.getClassName().getClassName());
									bookDetails.setClassName(classNameOb);
									bookDetails.setStatus(Constants.YES_STRING);
								          
										  for(int i=0;i<bookDetails.getNoOfCopies();i++){
											bookLables=new BookLables();
											bookLables.setBookLabelStatus(Constants.BOOK_OPEND);
											bookLables.setDeleteStatus(Constants.NO_STRING);
											if(readCount > bookDetails.getReadingBookCount()){
												bookLables.setType(Constants.ISSUE_BOOK);	
											}else{
												bookLables.setType(Constants.READING_BOOK);
											}
											if(alphabet.charAt(k)=='Z'){
							                    	k=1;
							                 }
											ch=alphabet.charAt(k);
											if(!StringFunctions.isNullOrEmpty(bookDetails.getBookLableCode()) && !StringFunctions.isNullOrEmpty(bookDetails.getBookKeyWord())){
												bookLables.setLableCode(bookDetails.getBookLableCode()+bookDetails.getBookKeyWord()+year+randamNumber+ch+i);	
											}
											else if(!StringFunctions.isNullOrEmpty(bookDetails.getBookKeyWord())){
												bookLables.setLableCode(bookDetails.getBookKeyWord()+year+randamNumber+ch+i);
											}
											else{
												bookLables.setLableCode(year+randamNumber+ch+i);
											}
											bookLables.setCustId(custId);
											bookDetails.addBookLablesSettings(bookLables);
											readCount++;
											k++;
											bookLables=null;
										}
									  libraryManager.save(bookDetails);
									  dataScuccess = "successMessage";
							    	}
							    	else{
										cellNumber.append(bookDetails.getAcquisitionNumber() + ",");
							    	}
								}
								studySubj=null;
								classNameOb=null;
								//bookTitleObj=null;
							}
							else
								acnoNo.append(bookDetails.getBookName() + ",");
							}else{
								nodata="Y";
								cellNumbers.append(countNo+",");
							}
							countNo++;
						}
					}else{
						super.addActionError("No data entered into the sheet. Please verify the data.");
					}
			  }
			 //home();
			 if("edit".equalsIgnoreCase(getAlertSendType()))
					 super.addActionMessage("Library books updated successfully.");
			 else{
				 if(!StringFunctions.isNullOrEmpty(dataScuccess))
					 super.addActionMessage("Library books added successfully.");
			 }
			 if(!StringFunctions.isNullOrEmpty(nodata))
					super.addActionError("Please check the mandatory fields.");
			 if(cellNumber.length() > 0){
					super.addActionError("The following acquisition number(s) are already available. " + cellNumber.toString());
				}
			 if(acnoNo.length() > 0){
				 super.addActionError("The following book name(s) acquisition number(s) are not available. " + acnoNo.toString());
				}
			 if(bookNo.length()>0){
				 bookNo.delete(bookNo.length()-1, bookNo.length());
				 super.addActionError("The book '("+bookNo.toString()+")' is already been issued, cannot reduce the count." );
			 }
			}
		}
	} catch (Exception ex) {
	ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
	}
	 if("edit".equalsIgnoreCase(getAlertSendType()))
	{
		return "editSchoolBooks";
	}
	return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxAssignBooksToRacks", results = { @Result(location = "block/ajaxAssignBooksToRack.jsp", name = "success") }) })
	public String ajaxAssignBooksToRacks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxAssignBooksToRacks' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getTempId())){
				String sqlQuery = "SELECT subjectId,bookName,author,publisher,SUM(noOfCopies),id FROM bookTitle  WHERE custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and subjectId="+getTempId()+" Group By author,publisher";
				List<Object[]> booksList = adminManager.getAll(sqlQuery);
			if(!ObjectFunctions.isNullOrEmpty(booksList)){
				setObjectList(booksList);
			 }
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxDoViewRack", results = { @Result(location = "block/ajaxViewBooksForRack.jsp", name = "success") }) })
	public String ajaxDoViewRack() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxDoViewRack' method");
		}
		try {
			if(!ObjectFunctions.isNullOrEmpty(getTempId())){
				String sqlQuery = "SELECT id,rackName,subjectName,rackId,subjectId,SUM(noOfCopies),bookTitleId FROM vw_racksubjectdetails  WHERE custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and rackId="+getTempId()+" group by subjectId";
				List<Object[]> booksList = adminManager.getAll(sqlQuery);
			if(!ObjectFunctions.isNullOrEmpty(booksList)){
				setObjectList(booksList);
			 }
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	 
	@Actions({
		@Action(value = "ajaxEditUpdateRacks", results = { @Result(location = "block/ajaxViewAllRacks.jsp", name = "success") }) })
		public String ajaxEditUpdateRacks() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxEditUpdateRacks' method");
		}
		try {	
			if(getBlockId()>0){
				setSelectedId2(getBlockId());
				RackDetails rackDetails=(RackDetails)libraryManager.get(RackDetails.class, getRackDetails().getId());
				//Block block = null;
				if(!ObjectFunctions.isNullOrEmpty(rackDetails)){
					int booksCount=0;
					BooksAssignToRack booksAssign=null;
					if(!StringFunctions.isNullOrEmpty(getAnyTitle())){
						String[] strArry=getAnyTitle().split(",");
						String[] strArry1=null;
						BookTitle bookTitle=null;
						int partialCount=0;
						boolean statusVal=false;
						int existedCount=0;
						AcademicYear academicYear = getCurrentAcademicYear();
							for(int i=0; i< strArry.length;i++){
								strArry1=strArry[i].split("_");
								booksAssign=new BooksAssignToRack();
								booksAssign.setSubjectId(Long.valueOf(strArry1[0]));
								booksAssign.setBookTitleId(Integer.parseInt(strArry1[1]));
								booksAssign.setAcademicYear(academicYear);
								booksAssign.setCustId(getUserCustId());
								partialCount +=Integer.parseInt(strArry1[2]);
								if(partialCount > getRackDetails().getRackCapacity()){
									booksAssign.setnoOfCopies(getRackDetails().getRackCapacity()-booksCount);
									booksCount +=getRackDetails().getRackCapacity()-booksCount;									
									statusVal=true;
								}
								else{
									if(partialCount == getRackDetails().getRackCapacity())
									   statusVal=true;
									booksAssign.setnoOfCopies(Integer.parseInt(strArry1[2]));
									booksCount +=Integer.parseInt(strArry1[2]);
								}
								rackDetails.addBookAssignments(booksAssign);
								bookTitle=(BookTitle)libraryManager.get(BookTitle.class,booksAssign.getBookTitleId());
								if(!ObjectFunctions.isNullOrEmpty(bookTitle)){
									String sqlQuery1 = "SELECT id,SUM(noOfCopies),count(*)  FROM booksAssignToRack  WHERE custId="+getUserCustId()+" and academicYearId="+getUserAcademicYearId()+" and bookTitleId="+booksAssign.getBookTitleId();
									Object[] booksTitle = adminManager.get(sqlQuery1);
									if(!ObjectFunctions.isNullOrEmpty(booksTitle)){
										   if(Integer.parseInt(booksTitle[2].toString()) > 0)
											existedCount=Integer.parseInt(booksTitle[1].toString());
									}
									if(bookTitle.getNoOfCopies() == booksAssign.getnoOfCopies() || bookTitle.getNoOfCopies()== (existedCount+booksAssign.getnoOfCopies())){
										libraryManager.updateBookTitleStatus(booksAssign.getBookTitleId(),Constants.NO_STRING);
									}
									else{
										libraryManager.updateBookTitleStatus(booksAssign.getBookTitleId(),Constants.PARTIAL_STATUS);
									}
								}
								if(statusVal) break;
							}
							rackDetails.setBooksCount(rackDetails.getBooksCount()+booksCount);
							/*block = (Block)libraryManager.get(Block.class,"id="+rackDetails.getBlock().getId()+" ");
							if(!ObjectFunctions.isNullOrEmpty(block)){
								block.setBookId(bookTitle.getId());
								adminManager.saveOrUpdateObject(block);
							}*/
					}
					rackDetails.setRackCapacity(getNumberOfDays());
					rackDetails.setRackName(getRackDetails().getRackName());
					adminManager.saveOrUpdateObject(rackDetails);
					super.addActionMessage("Rack updated successfully.");
					viewAllRacks();
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	
	@Actions( { @Action(value = "ajaxRemoveLibrarySubject",results = { @Result(location = "block/ajaxViewBooksForRack.jsp", name = "success") }) })
	public String ajaxRemoveLibrarySubject() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxRemoveLibrarySubject' method");
		}
		try {
			if (getTempId() > 0 && !ObjectFunctions.isNullOrEmpty(getTempId1())) {  // getTempId = rackId and getTempId1=subjectId				
				
					RackDetails rackDetails=(RackDetails)libraryManager.get(RackDetails.class,getTempId());
					if(!ObjectFunctions.isNullOrEmpty(rackDetails)){
					rackDetails.setBooksCount(rackDetails.getBooksCount()-Integer.parseInt(getAnyId()));
					libraryManager.save(rackDetails);
					adminManager.remove("booksAssignToRack", "custId="+getUserCustId()+" and subjectId="+getTempId1()+" and rackDetailsId="+getTempId());
					if(!StringFunctions.isNullOrEmpty(getSelectedId())){
						BookTitle bookTitle=(BookTitle)libraryManager.get(BookTitle.class, Long.valueOf(getSelectedId()));
						if(!ObjectFunctions.isNullOrEmpty(bookTitle)){
							bookTitle.setStatus(Constants.YES_STRING);
							libraryManager.save(bookTitle);
						}
						bookTitle=null;
					}					
					super.addActionMessage("Book deleted successfully.");
					ajaxDoViewRack();
			       }
			  rackDetails=null;	
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return SUCCESS;
	}
	@Actions( { @Action(value = "ajaxSearchStudentOrStaff", results = { @Result(type = "json", name = "success") }) })
	public String ajaxGetAttendanceForClasses() throws URTUniversalException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ajaxSearchStudentOrStaff' method");
		}
		try { 
			List<ViewAllUsers> allUsersList=null;
			List<ViewStudentPersonAccountDetails> allStudentList=null;
			String searchword = getParamValue("searchword");
			
			if (!StringFunctions.isNullOrEmpty(searchword)) {
				StringBuffer queryString = new StringBuffer();
				String selectedType=getParamValue("selectType");
				
				if(!StringFunctions.isNullOrEmpty(selectedType)) {
					if("S".equalsIgnoreCase(selectedType)){
					    queryString.append("custId="+ getUserCustId()+ " and accountExpired='"+ Constants.NO_STRING+ "' and (firstName like '%"+ searchword+ "%' or lastName like '%"+ searchword+ "%') and roleName in ('ROLE_TEACHER','ROLE_ADMIN_COORDINATOR','ROLE_HOD','ROLE_PRINCIPAL','ROLE_VICEPRINCIPAL','ROLE_ASST_TEACHER') group by accountId");
					    allUsersList = adminManager.getAll(ViewAllUsers.class,queryString.toString());
					}else{
						queryString.append("custId="+ getUserCustId()+ " and accountExpired='"+ Constants.NO_STRING+ "' and (firstName like '%"+ searchword+ "%' or lastName like '%"+ searchword+ "%' or admissionNumber like '%"+ searchword+ "%') and roleName='ROLE_STUDENT' group by accountId");
						allStudentList = adminManager.getAll(ViewStudentPersonAccountDetails.class,queryString.toString());
					}
				}
				JSONArray res = new JSONArray();
				JSONObject j;
				j = new JSONObject();
				if (!ObjectFunctions.isNullOrEmpty(allUsersList) || !ObjectFunctions.isNullOrEmpty(allStudentList)) {
					//Collections.sort(allUsersList);
					if("S".equalsIgnoreCase(selectedType)){
						for (ViewAllUsers users : allUsersList) {
							j = new JSONObject();
							j.put("accountId", users.getAccountId());
							j.put("title", users.getFirstName()+" ( "+users.getRoleDescription()+" )");
							j.put("role", users.getRoleDescription());
							j.put("userName", users.getUsername());
							res.put(j);
						}
					}else{
						for (ViewStudentPersonAccountDetails users : allStudentList) {
							j = new JSONObject();
							j.put("accountId", users.getAccountId());
							j.put("title", users.getFirstName()+"-"+users.getAdmissionNumber()+" ( "+users.getClassAndSection()+" )");
							j.put("role", users.getRoleDescription());
							j.put("userName", users.getUsername());
							res.put(j);
						}
					}
				} else {
					j = new JSONObject();
					j.put("accountId", 0);
					j.put("title", "No Results Found !!");
					j.put("lastName", "");
					j.put("role","");
					j.put("userName","");
					res.put(j);
				}
				j = new JSONObject();
				j.put("movies", res);
				getResponse().getOutputStream().print(j.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
		}
		return null;
	}
	
	 /* @Description 28-04-2014 added by Cherivi */ 
	   @Actions( {  @Action(value = "ajaxRenewalStudentStaffBook",  results = { @Result(location = "student/ajaxStudentGetBooks.jsp", name = "success" )}) })
		public String ajaxRenewalStudentStaffBook() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxRenewalStudentStaffBook' method");
			}
			try {
				 if(!ObjectFunctions.isNullOrEmpty(getBookId())){ // Here bookId is issueBookId
					 IssuedBook issueBook=(IssuedBook)libraryManager.get(IssuedBook.class, getBookId());
					 //For showing label
					 if(issueBook.getUser().isSchoolStudent())
						 setTempString("Student");
					 else
						 setTempString("Staff");
					 if(!ObjectFunctions.isNullOrEmpty(issueBook)){
						 if(issueBook.getRenewalStatus().equalsIgnoreCase(Constants.YES_STRING)){
							 super.addActionError("You have already renewed this book.");
						 }else{
							 issueBook.setRenewalStatus(Constants.YES_STRING);
							 libraryManager.save(issueBook);
							 super.addActionMessage("You have renewed this book successfully.");
						 }
						 issueBook=null;
					 }
					 staffLibrayHome();
				 }
			}
			catch(Exception ex) {
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	   
	@Actions( { @Action(value = "ajaxApproveOrRejectConfirmRequest", results = { @Result(location = "ajaxAllRenewalRequestedbooks.jsp", name = "success")}) })
	  public String ajaxApproveOrRejectConfirmRequest() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxApproveOrRejectConfirmRequest' method");
			}
			try {
				if (getBookId() > 0 && !ObjectFunctions.isNullOrEmpty(getTempId1()) && !ObjectFunctions.isNullOrEmpty(getTempId()) ) { //Here tempId=accountId,tempId1=IssueBookId					
					 IssuedBook issueBook=(IssuedBook)libraryManager.get(IssuedBook.class, getTempId1());
					 if(!ObjectFunctions.isNullOrEmpty(issueBook)){
						 issueBook.setRenewalStatus(Constants.NO_STRING);
						 if(Constants.YES_STRING.equalsIgnoreCase(getAnyId())){
							 issueBook.setApprovedStatus(Constants.YES_STRING);
							 Date dueDate=DateFunctions.add(issueBook.getDueDate(),issueBook.getBookTitle().getIssueDays());
							 issueBook.setDueDate(dueDate);
							 super.addActionMessage("You have approved the renewal request.");
						 }else{
							 issueBook.setApprovedStatus(Constants.NO_STRING);
							 super.addActionError("You have rejected the renewal request.");
						 }
						 libraryManager.save(issueBook);
						 issueBook=null;
						 setAnyId("RN");
						 issuedAndRequestBooks();
					 }
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	
	@Actions( { @Action(value = "ajaxViewStudentLibraryIdCasrds", results = { @Result(location = "ajaxViewStudentsLibraryIdCards.jsp", name = "success")}) })
	  public String ajaxViewStudentLibraryIdCasrds() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxViewStudentLibraryIdCasrds' method");
			}
			try {
				setCustomer(getCustomerByCustId());
				checkStudyClassHavingStudentsOrNot();
				prepareTeachingRolesMap();
				getTeachingRoleMap().remove(getTeachingRoleMap().firstKey());
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
	@Actions( { @Action(value = "ajaxDoIssuedBookDetail", results = { @Result(location = "ajaxIssuedBooks.jsp", name = "success")}) })
	  public String ajaxDoIssuedBookDetail() throws URTUniversalException {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'ajaxDoIssuedBookDetail' method");
			}
			try {
				setCustomer(getCustomerByCustId());
				setPlTitle("I");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();RayGunException raygex = new RayGunException();raygex.sendRayGunException(ex);raygex=null;
			}
			return SUCCESS;
		}
}