<%@ page language="java" isErrorPage="true" %>
<%@ page import="com.mindscapehq.raygun4java.webprovider.RaygunServletClient" %>
<%
  System.out.println("RayGun Error Before Sending Message") ;
  RaygunServletClient client = new RaygunServletClient("BeDNQlPPXWy8x8OX1yQCmw==", request);
  System.out.println("Exception from RayGun.........."+exception) ;
  client.Send(exception);  
  System.out.println("RayGun Error After Sending Message") ;
%>
 