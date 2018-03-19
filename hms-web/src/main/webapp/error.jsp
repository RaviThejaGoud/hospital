<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <title><fmt:message key="errorPage.title"/></title>
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/theme.css'/>" />
</head>

<body id="error">
    <div id="page">
        <div id="content" class="clearfix">
            <div id="main">
               
                <% if (exception != null) { 
                
                System.out.println("error.jsp1 If") ;
                
                %>
                    <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
                <% } else if (request.getAttribute("javax.servlet.error.exception") != null) { 
                System.out.println("error.jsp1 else If") ;
                %>
                    <pre><% ((Exception)request.getAttribute("javax.servlet.error.exception"))
                                           .printStackTrace(new java.io.PrintWriter(out)); %></pre>
                <% }  else { 
                System.out.println("error.jsp3 else") ;
                %>
                     <pre></pre>
                <% } %>
            </div>
        </div>
    </div>
</body>
</html>
