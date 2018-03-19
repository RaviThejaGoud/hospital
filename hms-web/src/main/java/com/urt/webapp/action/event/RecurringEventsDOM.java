package com.urt.webapp.action.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.event.Events;
import com.urt.persistence.model.study.SchoolHolidays;
import com.urt.webapp.action.jrexception.JRExceptionClient;

public class RecurringEventsDOM {
	
	private static final Log log = LogFactory.getLog(RecurringEventsDOM.class);

  /**
   * DOM Document
   */
  private Document document=null;
 
  private List contentList;
  private List eventsList;
  private List categoriesList;
  private List deletedRecEvents;
  private List holidayBoardList;
  protected List objectList;
  
     public List getHolidayBoardList() {
	return holidayBoardList;
}

public void setHolidayBoardList(List holidayBoardList) {
	this.holidayBoardList = holidayBoardList;
}

	/**
 * @return the deletedRecEvents
 */
public List getDeletedRecEvents() {
	if(ObjectFunctions.isNullOrEmpty(this.deletedRecEvents)){
        this.deletedRecEvents=new ArrayList();
    }
	return this.deletedRecEvents;
}

/**
 * @param deletedRecEvents the deletedRecEvents to set
 */
public void setDeletedRecEvents(List deletedRecEvents) {
	this.deletedRecEvents = deletedRecEvents;
}

	/**
 * @return the categoriesList
 */
public List getCategoriesList() {
	if(ObjectFunctions.isNullOrEmpty(this.categoriesList)){
        this.categoriesList=new ArrayList();
    }
    return this.categoriesList;
}

/**
 * @param categoriesList the categoriesList to set
 */
public void setCategoriesList(List categoriesList) {
	this.categoriesList = categoriesList;
}

	/**
      * @return Document
      */
     public Document getDocument(){
       return document;
     }
    
     public List getEventsList() {
         if(ObjectFunctions.isNullOrEmpty(this.eventsList)){
             this.eventsList=new ArrayList();
         }
         return this.eventsList;
     }
     public void setEventsList(List eventsList) {
         this.eventsList = eventsList;
     }
     public List getContentList() {
         if(ObjectFunctions.isNullOrEmpty(this.contentList)){
             this.contentList=new ArrayList();
         }
         return this.contentList;
     }

     public void setContentList(List contentList) {
         this.contentList = contentList;
     }
     
     public List getObjectList() {
		if (ObjectFunctions.isNullOrEmpty(this.objectList)) {
			this.objectList = new ArrayList();
		}
		return objectList;
	}

	public void setObjectList(List objectList) {
		this.objectList = objectList;
	}
      /**
       * Insert Social Church CalendarEvent List  to Document
       * @param document - socialChurch Document
       * @param parent - Node where to insert a "ChurchEvents"

       */
    public Element insertEmsEvents(Document document,Element root,List objectList){     	
        Node eventType;
        Node typeValue; 
           if (!ObjectFunctions.isNullOrEmpty(objectList)) {   
	               Iterator eventsIterator =objectList.iterator();
	               for (Iterator objectsIterator = eventsIterator; objectsIterator.hasNext();) {
	        	   Events cwEvent = (Events) objectsIterator.next();
	                    if(!ObjectFunctions.isNullOrEmpty(cwEvent))
	                    { 
	                    	Element event= document.createElement("event");
	                        event.setAttribute("id", cwEvent.getStrId());
	                       
	                        eventType = document.createElement("readonly");
	                        typeValue=document.createTextNode("true");                           
	                        eventType.appendChild(typeValue);                   
	                        event.appendChild(eventType);                        
	                        eventType = document.createElement("text");                    
	                        typeValue=document.createTextNode(cwEvent.getEventName());                           
	                        eventType.appendChild(typeValue); 	                        
	                        event.appendChild(eventType); 
	                        eventType = document.createElement("title");                    
	                        typeValue=document.createTextNode(cwEvent.getEventDescription());                           
	                        eventType.appendChild(typeValue); 
	                        event.appendChild(eventType);	                  
	                        //eventType = document.createElement("section_id"); 
	                        eventType = document.createElement("start_date");
	 	                    typeValue=document.createTextNode(cwEvent.getStartDateStr());                           
	 	                    eventType.appendChild(typeValue);                   
	 	                    event.appendChild(eventType);
	 	                    eventType = document.createElement("end_date");
	 	                    typeValue=document.createTextNode(cwEvent.getCalendarEventEndDateStr());                           
	 	                    eventType.appendChild(typeValue); 
	 	                    event.appendChild(eventType);
	                        root.appendChild(event); 
	                       
	                        
                        cwEvent=null;
                    }
                 }
	        }
         
	       return root;
    }
    
   
    public Document emsEvents() {
        DocumentBuilder builder = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
        	 Element root = (Element) document.createElement("data");
        	root=insertEmsEvents(document, root, getObjectList()); 
        	root=insertHolidayEvents(document, root, getHolidayBoardList());
        	// root= insertDeletedRecEvents(document, root,getDeletedRecEvents());
        	 document.appendChild(root);
        	 document.getDocumentElement().normalize();
         	// root= insertDeletedRecEvents(document, root,getDeletedRecEvents());
             return document;
        } catch (Exception ex) {
            ex.printStackTrace();
            JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
            return null;
        }
    }
    public Document hostelEvents() {
        DocumentBuilder builder = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
        	 Element root = (Element) document.createElement("data");
        	//root=insertHostelEvents(document, root, getObjectList()); 
        	//root=insertHolidayEvents(document, root, getHolidayBoardList());
        	// root= insertDeletedRecEvents(document, root,getDeletedRecEvents());
        	 document.appendChild(root);
        	 document.getDocumentElement().normalize();
         	// root= insertDeletedRecEvents(document, root,getDeletedRecEvents());
             return document;
        } catch (Exception ex) {
            ex.printStackTrace();
            JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
            return null;
        }
    }
    public Element insertHolidayEvents(Document document,Element root,List<SchoolHolidays> holidayBoardList){     	
        Node eventType;
        Node typeValue; 
           if (!ObjectFunctions.isNullOrEmpty(holidayBoardList)) {   
        	   for(SchoolHolidays schoolHolidays : holidayBoardList)
        	   {
                    if(!ObjectFunctions.isNullOrEmpty(schoolHolidays))
                    { 
                    	log.debug("" + schoolHolidays.getStartDateStr() + "--" + schoolHolidays.getEndDateStr()); 
                    	Element event= document.createElement("event");
                        event.setAttribute("id", schoolHolidays.getStrId()+" Holiday");
                        
                        eventType = document.createElement("readonly");
                        typeValue=document.createTextNode("true");                           
                        eventType.appendChild(typeValue);                   
                        event.appendChild(eventType);                        
                        eventType = document.createElement("text");                    
                        typeValue=document.createTextNode(schoolHolidays.getDescription());                           
                        eventType.appendChild(typeValue); 	                        
                        event.appendChild(eventType); 
                        eventType = document.createElement("title");                    
                        typeValue=document.createTextNode("");                           
                        eventType.appendChild(typeValue); 
                        event.appendChild(eventType);	                  
                        eventType.appendChild(typeValue); 
                        event.appendChild(eventType);	                                         		
 	                    eventType = document.createElement("start_date");
 	                    typeValue=document.createTextNode(schoolHolidays.getCalendarStartDateStr());                           
 	                    eventType.appendChild(typeValue);                   
 	                    event.appendChild(eventType);
 	                    eventType = document.createElement("end_date");
 	                    typeValue=document.createTextNode(schoolHolidays.getCalendarEndDateStr());                           
 	                    eventType.appendChild(typeValue); 
 	                    event.appendChild(eventType);  
 	                   root.appendChild(event); 
 	               
                    }
                    schoolHolidays=null;
                }
           }
	       return root;
    }
}