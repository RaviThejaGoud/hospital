package com.urt.util.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.churchgroup.util.string.StringFunctions;
import com.mindscapehq.raygun4java.core.RaygunClient;

public class RayGunException {
	
	private static final Log log = LogFactory.getLog(RayGunException.class);
	public void sendRayGunException(Exception exception){
		//RaygunClient client = new RaygunClient("KAnCyDLnu7xm/TLJ/YQLWA==")Sivas;
		log.debug("into here raygun");
		String hostUrl=ServletActionContext.getServletContext().getInitParameter("hostUrl");
		if(!StringFunctions.isNullOrEmpty(hostUrl)){
			RaygunClient client=null;
			if(hostUrl.contains("dev.") || hostUrl.contains("localhost")){
				 client = new RaygunClient("0KNNNW1PySzJiDwxEB1/qA=="); 
				 //client = new RaygunClient("OPqYOx3WFKCxdeQe3oKesw=="); // This(Dev) key is venkatesh@uroomtech.co key. 
			}else{
				 client = new RaygunClient("BeDNQlPPXWy8x8OX1yQCmw=="); 
				// client = new RaygunClient("l2MX4CzOrJPgksK8zMesiw=="); // This(Prod) key is venkatesh@uroomtech.co key.
			}
			client.Send(exception);
		}
	}

}
