package com.urt.util.xml;

/*
 * Copyright Sun Microsystems, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN
 * OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 * PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF
 * LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that Software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of
 * any nuclear facility.
 */
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.webapp.action.jrexception.JRExceptionClient;

/**
 * This class construcs an XML document in memory using DOM.
 * It first creates the root Order element and subsequently creates components
 * of the order by inserting nodes to the root element.
 */
public class SocialChurchDOM {

  /**
   * DOM Document
   */
  private Document document=null;
  private List contentList;
 // private String customerId;  
  private String contextPath;
 // private Customer customer;
 

  
  /**
 * @return the contextPath
 */
public String getContextPath() {
    return contextPath;
}

/**
 * @param contextPath the contextPath to set
 */
public void setContextPath(String contextPath) {
    this.contextPath = contextPath;
}

/**
   * Convenience method to get the session. This will create a session if one doesn't exist.
   * @return the session from the request (request.getSession()).
   */


     /**
      * @return Document
      */
     public Document getDocument(){
       return document;
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
       
    public Document lessonPodcastDetails() {
        DocumentBuilder builder = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            this.document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            // Insert Root Order
            Element root = (Element) document.createElement("rss");
            root.setAttribute("xmlns:itunes", "http://www.itunes.com/dtds/podcast-1.0.dtd");
            root.setAttribute("version", "2.0");
            // Insert child flashGroupSearchChild
            document.appendChild(root);

           /* if (StringFunctions.isNotNullOrEmpty(this.getCustomerId())) {
                //insertChannelDetails(document,root);
               insertLessonDetails(document, root);
            }*/
            document.getDocumentElement().normalize();
            return document;
        } catch (Exception ex) {
            ex.printStackTrace();
            JRExceptionClient jre = new JRExceptionClient();jre.sendException(ex);jre = null;
            return null;
        }
    }
   /* public void insertLessonDetails(Document document,Node parent){
        Node eventType;
        Node typeValue; 
        
        try
        {
            Node groupLocationChild=document.createElement("channel");     
            Element iTunesImage;
            eventType = document.createElement("title");
            String title,link,podDesc,podauthor, podimage,subtitle,itunesCategory;
            if(ObjectFunctions.isNullOrEmpty(getPodCastInfo()))
            {
                title="";
                link="";
                podDesc="";
                podauthor="";
                podimage="";
                subtitle="";
                itunesCategory="";
                
            }
            else
            {
                title=getPodCastInfo().getTitle();
                link=getPodCastInfo().getLink();
                podDesc=getPodCastInfo().getDescription();
                podauthor=getPodCastInfo().getAuthor();
                subtitle=getPodCastInfo().getSubtitle();
                itunesCategory=getPodCastInfo().getItunesCategory();
                if(ObjectFunctions.isNullOrEmpty(getPodCastInfo().getAttachment()))
                {
                    podimage="";
                }
                else
                {
                    podimage=getContextPath()+getPodCastInfo().getAttachment().getAdjustedAttachmentFilePath();
                }
            }
            typeValue=document.createTextNode(title); 
            eventType.appendChild(typeValue); 
            groupLocationChild.appendChild(eventType); 
            eventType = document.createElement("link");
            if(StringFunctions.isNullOrEmpty(link))
            {
                typeValue=document.createTextNode("");
            }
            else if(link.contains("http"))
                typeValue=document.createTextNode(link);
            else
                typeValue=document.createTextNode("http://"+link);
            eventType.appendChild(typeValue); 
            groupLocationChild.appendChild(eventType); 
            eventType = document.createElement("language");
            typeValue=document.createTextNode("en-us");                           
            eventType.appendChild(typeValue); 
            groupLocationChild.appendChild(eventType); 
            eventType = document.createElement("copyright");
            if(StringFunctions.isNullOrEmpty(getCustomer().getCustomerName())){
                typeValue=document.createTextNode("");
            }
            else{
                typeValue=document.createTextNode(getCustomer().getCustomerName());
            }
            eventType.appendChild(typeValue); 
            groupLocationChild.appendChild(eventType);
            
            eventType = document.createElement("itunes:subtitle");
            typeValue=document.createTextNode(subtitle);                          
            eventType.appendChild(typeValue); 
            groupLocationChild.appendChild(eventType);
            
            iTunesImage = document.createElement("itunes:image");
            iTunesImage.setAttribute("href", podimage);
            groupLocationChild.appendChild(iTunesImage);
            
            iTunesImage = document.createElement("itunes:category");
            iTunesImage.setAttribute("text", itunesCategory);
            groupLocationChild.appendChild(iTunesImage);
            
            Node owner = document.createElement("itunes:owner");
            
            eventType = document.createElement("itunes:name");
            typeValue=document.createTextNode(getCustomer().getCustomerName());
            eventType.appendChild(typeValue); 
            owner.appendChild(eventType);
            
            eventType = document.createElement("itunes:email");
            typeValue=document.createTextNode(getCustomer().getCustEmail());
            eventType.appendChild(typeValue); 
            owner.appendChild(eventType);
            groupLocationChild.appendChild(owner);
            eventType = document.createElement("itunes:author");
            typeValue=document.createTextNode(podauthor);
            eventType.appendChild(typeValue); 
            groupLocationChild.appendChild(eventType);
            
            eventType = document.createElement("itunes:summary");
            typeValue=document.createTextNode(podDesc);       
            eventType.appendChild(typeValue); 
            groupLocationChild.appendChild(eventType);
            eventType = document.createElement("description");
            typeValue=document.createTextNode(podDesc);                               
            eventType.appendChild(typeValue); 
            groupLocationChild.appendChild(eventType); 
          //  System.err.println("*********Calling iterator to parse the media for podcast***************");
           if (!ObjectFunctions.isNullOrEmpty(getContentList())) {
                Iterator groupDetailsIterator = getContentList().iterator();
                String description;
                for (Iterator objectsIterator = groupDetailsIterator; objectsIterator.hasNext();) {
                    SermonLesson sermonMedia = (SermonLesson) objectsIterator.next();
                    if(!ObjectFunctions.isNullOrEmpty(sermonMedia)){
                      //  System.err.println("*********Creating node***************");
                        Node events= document.createElement("item");  
                        
                        eventType = document.createElement("title");
                        typeValue=document.createTextNode(sermonMedia.getTitle());                           
                        eventType.appendChild(typeValue); 
                        events.appendChild(eventType); 
                        
                        eventType = document.createElement("itunes:author");
                        if(StringFunctions.isNullOrEmpty(getCustomer().getCustomerName())){
                            typeValue=document.createTextNode("");
                        }
                        else{
                            typeValue=document.createTextNode(getCustomer().getCustomerName());
                        }                            
                        eventType.appendChild(typeValue); 
                        events.appendChild(eventType);
                        eventType = document.createElement("itunes:subtitle");
                        typeValue=document.createTextNode(sermonMedia.getTitle());                           
                        eventType.appendChild(typeValue); 
                        events.appendChild(eventType);
                        
                       
                        
                       eventType = document.createElement("category");
                        if(StringFunctions.isNullOrEmpty(sermonMedia.getCategory())){
                            typeValue=document.createTextNode(" ");
                        }else{
                            typeValue=document.createTextNode(sermonMedia.getCategory()); 
                        }
                        eventType.appendChild(typeValue); 
                        events.appendChild(eventType); 
                       
                        if(StringFunctions.isNullOrEmpty(sermonMedia.getDescription())){
                            description="";
                        }else{
                            description=sermonMedia.getDescription(); 
                        }
                        eventType = document.createElement("itunes:summary");
                        typeValue=document.createTextNode(description);                           
                        eventType.appendChild(typeValue); 
                        events.appendChild(eventType);
                        
                        eventType = document.createElement("description");
                        typeValue=document.createTextNode(description);
                        eventType.appendChild(typeValue); 
                        events.appendChild(eventType);     
                        
                        System.err.println("*********Adding audio node***************");
                        try
                        {
                            eventType = document.createElement("guid");
                            Element encloseURL = document.createElement("enclosure");
                            if(ObjectFunctions.isNullOrEmpty(sermonMedia.getAttachment())){
                                encloseURL.setAttribute("url", "");
                                encloseURL.setAttribute("length", "");
                                encloseURL.setAttribute("type", "");
                                typeValue=document.createTextNode(" ");
                            }else{
                                encloseURL.setAttribute("url", getContextPath()+sermonMedia.getAttachment().getOriginalAttachmentFilePath());
                                if(StringFunctions.isNullOrEmpty(sermonMedia.getAttachment().getFileSize())){
                                    encloseURL.setAttribute("length", "");
                                }else{
                                    encloseURL.setAttribute("length", sermonMedia.getAttachment().getFileSize());
                                }
                                if(StringFunctions.isNullOrEmpty(sermonMedia.getAttachment().getFileType())){
                                    encloseURL.setAttribute("type", "");
                                }else{
                                    encloseURL.setAttribute("type", sermonMedia.getAttachment().getFileType());
                                }
                                typeValue=document.createTextNode(getContextPath()+sermonMedia.getAttachment().getOriginalAttachmentFilePath());
                            }
                            eventType.appendChild(typeValue);
                            events.appendChild(encloseURL);
                            events.appendChild(eventType);
                        }
                        catch(Exception ex)
                        {
                            System.err.println("Inside podcast : " + ex.getMessage());
                            ex.printStackTrace();
                        }
                        eventType = document.createElement("pubDate");
                        typeValue=document.createTextNode(DateUtil.getDateTime(DateFormatter.EEEDDMMMYYYY_PATTERN,sermonMedia.getCreatedDate())+" 00:00:00 CST");                           
                        eventType.appendChild(typeValue); 
                        events.appendChild(eventType); 
                        
                        eventType = document.createElement("itunes:duration");
                        typeValue=document.createTextNode("00:00:00");                           
                        eventType.appendChild(typeValue); 
                        events.appendChild(eventType);
                        
                        eventType = document.createElement("itunes:keywords");
                        if(StringFunctions.isNullOrEmpty(sermonMedia.getSearchString())){
                            typeValue=document.createTextNode(" ");
                        }else{
                            typeValue=document.createTextNode(sermonMedia.getSearchString()); 
                        }
                        eventType.appendChild(typeValue); 
                        events.appendChild(eventType);
                        
                        eventType = document.createElement("itunes:explicit");
                        typeValue=document.createTextNode("no");                           
                        eventType.appendChild(typeValue); 
                        events.appendChild(eventType);
                        groupLocationChild.appendChild(events);
                        sermonMedia=null;
                        }
                    }
                }
            parent.appendChild(groupLocationChild);  
        }
        catch (Exception e) {
            System.err.println(" Pod Cast failed: " + e.getMessage());
            e.printStackTrace();
        }
  }
    *//**
     * @return the customerId
     *//*
    public String getCustomerId() {
        return customerId;
    }


    *//**
     * @param customerId the customerId to set
     *//*
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PodCastInfo getPodCastInfo() {
        return podCastInfo;
    }

    public void setPodCastInfo(PodCastInfo podCastInfo) {
        this.podCastInfo = podCastInfo;
    }*/
   }

