<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@ page import="com.mindscapehq.raygun4java.webprovider.RaygunServletClient" %>

<%
System.out.println("Into raygun:");
RaygunServletClient client = new RaygunServletClient("0KNNNW1PySzJiDwxEB1/qA==", request);

client.Send(exception);
%>
