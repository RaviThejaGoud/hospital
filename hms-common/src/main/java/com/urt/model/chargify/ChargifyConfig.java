package com.urt.model.chargify;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.churchgroup.util.object.ObjectFunctions;

public class ChargifyConfig {
	private static final Log log = LogFactory.getLog(ChargifyConfig.class);
	private HttpClient charigifyClient;
	private HostConfiguration hostConfiguration;
	private String apiKey;
	private String domain;
	private final String xmlContentType="application/xml";
	//private final String jsonContentType="application/json";
	private final String encoding="UTF-8";
	
	
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public HttpClient getCharigifyClient() {
		if(ObjectFunctions.isNullOrEmpty(this.charigifyClient)){
			this.charigifyClient=new HttpClient();
		}
		return this.charigifyClient;
	}

	public void setCharigifyClient(HttpClient charigifyClient) {
		this.charigifyClient = charigifyClient;
	}

	public HostConfiguration getHostConfiguration() {
		if(ObjectFunctions.isNullOrEmpty(this.hostConfiguration)){
			this.hostConfiguration=new HostConfiguration();
		}
		return hostConfiguration;
	}

	public void setHostConfiguration(HostConfiguration hostConfiguration) {
		this.hostConfiguration = hostConfiguration;
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException  {
		// TODO Auto-generated method stub
		ChargifyConfig config=new ChargifyConfig();
		List products=config.getProductDetails();
		log.debug(products);
		//config.getSubscriptionDetailsById("374433");
		//config.upoRdownGradeProduct("374433", "19717");
		//config.deleteSubscriptionById("374433");
	}

	public ChargifyConfig() {		
		getHostConfiguration().setHost("groupsinteractive.chargify.com", 443, Protocol.getProtocol( "https"));
		getCharigifyClient().setHostConfiguration(getHostConfiguration());
		getCharigifyClient().getParams().setAuthenticationPreemptive(true);
		getCharigifyClient().getState().setCredentials( new AuthScope("groupsinteractive.chargify.com", 443,"Chargify API"), new UsernamePasswordCredentials("c_lEtvh4g3hyCCbBHct2", "x"));
	}

	public ChargifyConfig(String apiKey, String domain) {		
		this.apiKey = apiKey;
		this.domain = domain;		
		getHostConfiguration().setHost(domain, 443, Protocol.getProtocol( "https"));
		getCharigifyClient().setHostConfiguration(getHostConfiguration());
		getCharigifyClient().getParams().setAuthenticationPreemptive(true);
		getCharigifyClient().getState().setCredentials( new AuthScope(domain, 443,"Chargify API"), new UsernamePasswordCredentials(apiKey, "x"));
	}
	
	public ChargifySubscription getSubscriptionDetailsById(String subscriptionId){
		GetMethod method = new GetMethod( "/subscriptions/" + subscriptionId + ".xml");		
		// Execute request
//		String response="failure";
        try {
        	int responseCode = getCharigifyClient().executeMethod(method);
            // Display status code
        	log.debug("Response status code: " + responseCode);        	
            // Display response
    		if(responseCode==200){
    		//	response="success";
    			log.debug("Response body: ");         
    			log.debug(method.getResponseBodyAsString());
    			ChargifySubscription subscription= parseSubscriptionResponse(method.getResponseBodyAsStream());
    		    return subscription;
    		}
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        	log.debug("Something happened badly...." + ex.getMessage());	
        }
        finally {
            // Release current connection to the connection pool once you are done
        	method.releaseConnection();
        } 	
        return null;
	}
	
	public String deleteSubscriptionById(String subscriptionId){
		DeleteMethod deletemethod = new DeleteMethod( "/subscriptions/" + subscriptionId + ".xml");	
		// Execute request
		String response="failure";
        try {
        	int responseCode = getCharigifyClient().executeMethod(deletemethod);
            // Display status code
        	log.debug("Response status code: " + responseCode);        	
            // Display response
    		if(responseCode==200){
    			response="success";
    			log.debug("Response body: ");         
    			log.debug(deletemethod.getResponseBodyAsString());    			
    			response="success";
    		}
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        	log.debug("Something happened badly...." + ex.getMessage());        
        }
        finally {
            // Release current connection to the connection pool once you are done
        	deletemethod.releaseConnection();
        } 	
        return response;
	}
	
	
	public String upoRdownGradeProduct(String subscriptionId , String productId) throws UnsupportedEncodingException{
		PostMethod postMethod=new PostMethod("/subscriptions/"+subscriptionId+"/migrations.xml");
		String response="failure";
		try {
			StringBuffer xmlInputBuff=new StringBuffer();
			xmlInputBuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><migration><product_id>");
			xmlInputBuff.append(productId);
			xmlInputBuff.append("</product_id><include_trial>");
			xmlInputBuff.append("0");
			xmlInputBuff.append("</include_trial><include_initial_charge>");
			xmlInputBuff.append("1");
			xmlInputBuff.append("</include_initial_charge></migration>");			
			RequestEntity entity = new StringRequestEntity(xmlInputBuff.toString(),xmlContentType,encoding);	       
	        postMethod.setRequestEntity(entity);
	        // Execute request        
        	int responseCode = getCharigifyClient().executeMethod(postMethod);
            // Display status code        	
        	log.debug("Response status code: " + responseCode);
            // Display response            
            if(responseCode==200){
            	response="success";
            	log.debug("Response body: ");         
                log.debug(postMethod.getResponseBodyAsString());
        	}
            else{
            	response="failure";
            }
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
			log.debug("Something happened badly...." + ex.getMessage());			
        }
        finally {
            // Release current connection to the connection pool once you are done
        	postMethod.releaseConnection();
        } 
        return response;
	}
	
	
	public List getProductDetailsById(String productId){
		GetMethod method = new GetMethod( "/products/" + productId + ".xml");
		// Execute request
		@SuppressWarnings("unused")
		String response="failure";
		List productlist=null;
        try {
        	int responseCode = getCharigifyClient().executeMethod(method);
            // Display status code
        	log.debug("Response status code: " + responseCode);
            // Display response
        	// Display response            
            if(responseCode==200 ){
            	response="success";
            	log.debug("Response body: ");         
                log.debug(method.getResponseBodyAsString());
                productlist=parseProductsResponse(method.getResponseBodyAsStream());                
        	}
            else{
            	response="failure";
            }           
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        	log.debug("Something happened badly...." + ex.getMessage());	
        }
        finally {
            // Release current connection to the connection pool once you are done
        	method.releaseConnection();
        } 
        return productlist;
	}
	
	public List<ChargifyProduct> getProductDetails(){
		GetMethod method = new GetMethod( "/products.xml");	
		List<ChargifyProduct> productlist=null;
		// Execute request
        try {
        	int responseCode = getCharigifyClient().executeMethod(method);
            // Display status code
        	log.debug("Response status code: " + responseCode);
            // Display response
        	if(responseCode==200){             
             	log.debug("Response body: ");         
                log.debug(method.getResponseBodyAsString());
                productlist=parseProductsResponse(method.getResponseBodyAsStream());
                log.debug(productlist.size());              
         	}                          
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        	log.debug("Something happened badly...." + ex.getMessage());	
        }
        finally {
            // Release current connection to the connection pool once you are done
        	method.releaseConnection();
        } 
        return productlist;
	}
	
	public ChargifySubscription parseSubscriptionResponse(InputStream input){
		ChargifySubscription subscription=null;
    	try{    		
	    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();	
	        Document doc = db.parse(input);
            doc.getDocumentElement().normalize();
            NodeList nodeLst = doc.getElementsByTagName("subscription"); 
            NodeList resourceElementList=null;                         
            Element resourceElement=null;
            NodeList resourceName=null;            
            Element customerElement;
            NodeList customerElementItems;
            Element customerElementItem=null;
            subscription=new ChargifySubscription();
            Node fstNode = nodeLst.item(0);   
            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {               
	             Element fstElmnt = (Element) fstNode;                     
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("id");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubId(((Node) resourceName.item(0)).getNodeValue());       
	             }catch(Exception ex){
	            	 subscription.setSubId("");
	             }                       
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("activated_at");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubActivatedDate(((Node) resourceName.item(0)).getNodeValue());
	             }catch(Exception ex){
	            	 subscription.setSubActivatedDate("");
	             }  
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("created_at");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubCreatedDate(((Node) resourceName.item(0)).getNodeValue());
	             }catch(Exception ex){
	            	 subscription.setSubCreatedDate("");
	             }  	             
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("current_period_started_at");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubCurrentPeriodStartDate(((Node) resourceName.item(0)).getNodeValue());
	             }catch(Exception ex){
	            	 subscription.setSubCurrentPeriodStartDate("");
	             }  
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("current_period_ends_at");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubCurrentPeriodEndDate(((Node) resourceName.item(0)).getNodeValue());
	             }catch(Exception ex){
	            	 subscription.setSubCurrentPeriodEndDate("");
	             }       
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("trial_ended_at");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubTrialEndDate(((Node) resourceName.item(0)).getNodeValue());
	             }catch(Exception ex){
	            	 subscription.setSubTrialEndDate("");
	             }       
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("trial_started_at");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubTrialStartDate(((Node) resourceName.item(0)).getNodeValue());
	             }catch(Exception ex){
	            	 subscription.setSubTrialStartDate("");
	             }       
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("next_assessment_at");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubNextAssessmentDate(((Node) resourceName.item(0)).getNodeValue());
	             }catch(Exception ex){
	            	 subscription.setSubNextAssessmentDate("");
	             }   
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("state");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubCurrentState(((Node) resourceName.item(0)).getNodeValue());
	             }catch(Exception ex){
	            	 subscription.setSubCurrentState("");
	             }  
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("previous_state");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubPreviousState(((Node) resourceName.item(0)).getNodeValue());
	             }catch(Exception ex){
	            	 subscription.setSubPreviousState("");
	             }  	           
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("expires_at");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 resourceName = resourceElement.getChildNodes();
	                 subscription.setSubExpiryDate(((Node) resourceName.item(0)).getNodeValue());
	             }catch(Exception ex){
	            	 subscription.setSubExpiryDate("");
	             }    
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("customer");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 if (resourceElement.getNodeType() == Node.ELEMENT_NODE) {               
	                     customerElement = (Element) resourceElement;         
	                     try{
	                    	 customerElementItems = customerElement.getElementsByTagName("id");
		                     customerElementItem = (Element) customerElementItems.item(0);
		                     resourceName = customerElementItem.getChildNodes();
		                     subscription.setCustomerId(((Node) resourceName.item(0)).getNodeValue());       
	                     }catch(Exception ex){
	                    	 subscription.setCustomerId("");
	                     }                       
	                     try{
	                    	 customerElementItems = customerElement.getElementsByTagName("address");
		                     customerElementItem = (Element) customerElementItems.item(0);
		                     resourceName = customerElementItem.getChildNodes();
		                     subscription.setAddress(((Node) resourceName.item(0)).getNodeValue());       
	                     }catch(Exception ex){
	                    	 subscription.setAddress("");
	                     }         	                         
	                     try{
	                    	 customerElementItems = customerElement.getElementsByTagName("address_2");
		                     customerElementItem = (Element) customerElementItems.item(0);
		                     resourceName = customerElementItem.getChildNodes();
		                     subscription.setAddress_2(((Node) resourceName.item(0)).getNodeValue());       
	                     }catch(Exception ex){
	                    	 subscription.setAddress_2("");
	                     }         
	                     try{
	                    	 customerElementItems = customerElement.getElementsByTagName("city");
		                     customerElementItem = (Element) customerElementItems.item(0);
		                     resourceName = customerElementItem.getChildNodes();
		                     subscription.setCity(((Node) resourceName.item(0)).getNodeValue());       
	                     }catch(Exception ex){
	                    	 subscription.setCity("");
	                     }         
	                     try{
	                    	 customerElementItems = customerElement.getElementsByTagName("email");
		                     customerElementItem = (Element) customerElementItems.item(0);
		                     resourceName = customerElementItem.getChildNodes();
		                     subscription.setEmail(((Node) resourceName.item(0)).getNodeValue());       
	                     }catch(Exception ex){
	                    	 subscription.setEmail("");
	                     } 
	                     try{
	                    	 customerElementItems = customerElement.getElementsByTagName("last_name");
		                     customerElementItem = (Element) customerElementItems.item(0);
		                     resourceName = customerElementItem.getChildNodes();
		                     subscription.setLast_name(((Node) resourceName.item(0)).getNodeValue());       
	                     }catch(Exception ex){
	                    	 subscription.setLast_name("");
	                     }   
	                     try{
	                    	 customerElementItems = customerElement.getElementsByTagName("first_name");
		                     customerElementItem = (Element) customerElementItems.item(0);
		                     resourceName = customerElementItem.getChildNodes();
		                     subscription.setFirst_name(((Node) resourceName.item(0)).getNodeValue());       
	                     }catch(Exception ex){
	                    	 subscription.setFirst_name("");
	                     }   
	                     try{
	                    	 customerElementItems = customerElement.getElementsByTagName("organization");
		                     customerElementItem = (Element) customerElementItems.item(0);
		                     resourceName = customerElementItem.getChildNodes();
		                     subscription.setOrganization(((Node) resourceName.item(0)).getNodeValue());       
	                     }catch(Exception ex){
	                    	 subscription.setOrganization("");
	                     }   
	                 }        		                     
	             }
	             catch(Exception ex){
	            	 ex.printStackTrace();
	             }	
	             try{
	                 resourceElementList = fstElmnt.getElementsByTagName("product");
	                 resourceElement = (Element) resourceElementList.item(0);
	                 subscription.setProduct(prepareProduct(resourceElement));
	             }
	             catch(Exception ex){
	            	 ex.printStackTrace();
	             }	
            }				
            input.close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return subscription;
	}
	
    public List<ChargifyProduct> parseProductsResponse(InputStream input){
    	List<ChargifyProduct> productsList=null;
    	try{    		
	    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();	
	        Document doc = db.parse(input);
            doc.getDocumentElement().normalize();
            NodeList nodeLst = doc.getElementsByTagName("product");           
            productsList=new ArrayList<ChargifyProduct>();
            for (int p = 0; p < nodeLst.getLength(); p++) { 
                 ChargifyProduct product=prepareProduct(nodeLst.item(p));
                 if(!ObjectFunctions.isNullOrEmpty(product)){
	                 productsList.add(product);
	                 product=null;	                 
	            }	
			}
            input.close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return productsList;
	}
    
    public ChargifyProduct prepareProduct(Node productNode){
    	ChargifyProduct product=new ChargifyProduct();  
    	try{    		                 
             NodeList resourceElementList=null;                         
             Element resourceElement=null;
             NodeList resourceName=null;             
             Element productFamilyElement;
             NodeList familyElementList;
             Element familyElement=null;
             if (productNode.getNodeType() == Node.ELEMENT_NODE) {               
                 Element fstElmnt = (Element) productNode;                     
                 try{
                     resourceElementList = fstElmnt.getElementsByTagName("id");
                     resourceElement = (Element) resourceElementList.item(0);
                     resourceName = resourceElement.getChildNodes();
                     product.setId(((Node) resourceName.item(0)).getNodeValue());       
                 }catch(Exception ex){
                	 product.setId("");
                 }                       
                 try{
                     resourceElementList = fstElmnt.getElementsByTagName("name");
                     resourceElement = (Element) resourceElementList.item(0);
                     resourceName = resourceElement.getChildNodes();
                     product.setName(((Node) resourceName.item(0)).getNodeValue());
                     if(product.getName().contains("Less Than 1000")){						
                    	 product.setUserLimit(999);						
					 }
					 else if(product.getName().contains("1000-2500")){						
						 product.setUserLimit(2499);						
					 }
					 else if(product.getName().contains("2500-5000")){						
						 product.setUserLimit(4999);					
					 } 
                 }catch(Exception ex){
                	 product.setName("");
                 }      
                 
                 try{
                     resourceElementList = fstElmnt.getElementsByTagName("description");
                     resourceElement = (Element) resourceElementList.item(0);
                     resourceName = resourceElement.getChildNodes();
                     product.setDescription(((Node) resourceName.item(0)).getNodeValue());
                 }catch(Exception ex){
                	 product.setDescription("");
                 }       
                 
                 try{
                     resourceElementList = fstElmnt.getElementsByTagName("interval_unit");
                     resourceElement = (Element) resourceElementList.item(0);
                     resourceName = resourceElement.getChildNodes();
                     product.setIntervalUnit(((Node) resourceName.item(0)).getNodeValue());
                 }catch(Exception ex){
                	 product.setIntervalUnit("");
                 }    
                 
                 try{
                     resourceElementList = fstElmnt.getElementsByTagName("interval");
                     resourceElement = (Element) resourceElementList.item(0);
                     resourceName = resourceElement.getChildNodes();
                     product.setInterval(((Node) resourceName.item(0)).getNodeValue());
                 }catch(Exception ex){
                	 product.setInterval("");
                 }    
                 
                 try{
                     resourceElementList = fstElmnt.getElementsByTagName("price_in_cents");
                     resourceElement = (Element) resourceElementList.item(0);
                     resourceName = resourceElement.getChildNodes();
                     product.setPriceInCents(((Node) resourceName.item(0)).getNodeValue());
                 }catch(Exception ex){
                	 product.setPriceInCents("");
                 }    
                 
                 
                 try{
                     resourceElementList = fstElmnt.getElementsByTagName("product_family");
                     resourceElement = (Element) resourceElementList.item(0);
                     if (resourceElement.getNodeType() == Node.ELEMENT_NODE) {               
                         productFamilyElement = (Element) resourceElement;         
                         try{
                        	 familyElementList = productFamilyElement.getElementsByTagName("id");
    	                     familyElement = (Element) familyElementList.item(0);
    	                     resourceName = familyElement.getChildNodes();
    	                     product.setProductFamilyId(((Node) resourceName.item(0)).getNodeValue());       
                         }catch(Exception ex){
                        	 product.setProductFamilyId("");
                         }                       
                         try{
                        	 familyElementList = productFamilyElement.getElementsByTagName("name");
                        	 familyElement = (Element) familyElementList.item(0);
    	                     resourceName = familyElement.getChildNodes();
    	                     product.setProductFamilyName(((Node) resourceName.item(0)).getNodeValue());
                         }catch(Exception ex){
                        	 product.setProductFamilyName("");
                         }      	                         
                         try{
                        	 familyElementList = productFamilyElement.getElementsByTagName("description");
                        	 familyElement = (Element) familyElementList.item(0);
    	                     resourceName = familyElement.getChildNodes();
    	                     product.setProductFamilyDescription(((Node) resourceName.item(0)).getNodeValue());
                         }catch(Exception ex){
                        	 product.setProductFamilyDescription("");
                         }       
                     }        		                     
                 }
                 catch(Exception ex){
                	 ex.printStackTrace();
                 }                                 
            }	
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return product;
	}
	
}
