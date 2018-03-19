<%@ include file="/common/taglibs.jsp"%>
<%
HttpSession sessieon = request.getSession( false ) ;
   if ( sessieon != null ) sessieon.invalidate() ;
   RequestDispatcher requestDispatcher ;
   requestDispatcher = request.getRequestDispatcher("/login.jsp" ) ;
   requestDispatcher.forward( request, response ) ;
   
%>

