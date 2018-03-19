package com.urt.util.common;

import com.churchgroup.util.string.StringFunctions;
import com.mindscapehq.raygun4java.core.RaygunClient;

public class RayGunException {
	
	
	public void sendRayGunException(Exception exception){
		//RaygunClient client = new RaygunClient("KAnCyDLnu7xm/TLJ/YQLWA==")Sivas;
		String hostUrl="dev";
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
