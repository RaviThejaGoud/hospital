<?xml version="1.0" encoding="utf-8"?>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<s:if test='%{#session.customerStatus == "Y"}'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta name="MobileOptimized" content="320"> 
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title><decorator:title /></title>
		<style type="text/css" media="all">
	 
      		<%-- Start Common Styles --%>
				@import url("${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css");	
				@import url("${pageContext.request.contextPath}/plugins/font-awesome/css/glyphicons.css");	
				@import url("${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css");
				@import url("${pageContext.request.contextPath}/plugins/uniform/css/uniform.default.css");
				@import url("${pageContext.request.contextPath}/plugins/select2/select2_metro.css");
				@import url("${pageContext.request.contextPath}/plugins/data-tables/DT_bootstrap.css");
				
				 <%-- Start THEME Styles --%>
				@import url("${pageContext.request.contextPath}/styles/newCss/style-metronic.css");
				@import url("${pageContext.request.contextPath}/styles/newCss/style.css");
				
				@import url("${pageContext.request.contextPath}/styles/newCss/style-responsive.css");	
	                <%-- Start Calendar Styles --%>
				@import url("${pageContext.request.contextPath}/plugins/fullcalendar/fullcalendar/fullcalendar.css");
				@import url("${pageContext.request.contextPath}/plugins/bootstrap-colorpicker/css/colorpicker.css");	 
						
		 <%-- Start Datepicker Styles --%>
				@import url("${pageContext.request.contextPath}/plugins/bootstrap-datepicker/css/datepicker.css");
		 <%-- Start Profile Styles --%>
				@import url("${pageContext.request.contextPath}/styles/newCss/pages/profile.css");
						 
			<%-- Start Inbox Styles --%>	
				@import url("${pageContext.request.contextPath}/styles/newCss/pages/inbox.css");
				@import url("${pageContext.request.contextPath}/plugins/jquery-multi-select/css/multi-select.css");
				@import url("${pageContext.request.contextPath}/styles/newCss/plugins.css");
			<%-- Start Popup Styles --%>
				@import url("${pageContext.request.contextPath}/plugins/bootstrap-modal/css/bootstrap-modal.css");
				@import url("${pageContext.request.contextPath}/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css");
			<%-- Start Time Picker Styles --%>
				@import url("${pageContext.request.contextPath}/plugins/bootstrap-timepicker/compiled/timepicker.css");
			<%-- Start AutoCompleter backGround Styles --%>
				@import url("${pageContext.request.contextPath}/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.css");
				@import url("${pageContext.request.contextPath}/plugins/bootstrap-switch/static/stylesheets/bootstrap-switch-metro.css");
			<%-- start File upload scripts --%>	
				@import url("${pageContext.request.contextPath}/plugins/bootstrap-fileupload/bootstrap-fileupload.css");
			<%-- start event photos view scripts --%>	
				@import url("${pageContext.request.contextPath}/styles/newCss/pages/portfolio.css");
				@import url("${pageContext.request.contextPath}/plugins/jquery-mixitup/jquery.fancybox.css");
			 <%-- start easy-pie-chart scripts --%>
				@import url("${pageContext.request.contextPath}/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.css");
			 <%-- start editor scripts --%>
				@import url("${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css");
		     <%-- start time pickers  scripts --%>		
				@import url("${pageContext.request.contextPath}/plugins/bootstrap-timepicker/compiled/timepicker.css");	   
				@import url("${pageContext.request.contextPath}/styles/newCss/pages/todo.css");
				@import url("${pageContext.request.contextPath}/styles/newCss/pages/datetimepicker.css");
		 </style>	
		 <sj:head debug="true" compressed="false" defaultIndicator="myDefaultIndicator"  jqueryui="false" />
		<link rel="stylesheet" type="text/css" id="style_color" href="${pageContext.request.contextPath}/styles/newCss/themes/default.css" />
		<!--[if IE 7]>
			<html lang="en" class="ie7">
		<![endif]-->
		<!--[if IE 8]>
			<html lang="en" class="ie8">
		<![endif]-->
		<!--[if IE 9]>	
		 <html lang="en" class="ie9"> 
		<![endif]-->
		<!--[if !IE]>	
		 <html lang="en"> 
		<![endif]-->
		
		
		<script type="text/javascript">
		/* window.onbeforeunload = function (my_event) {
			var message = "Your logout page has been opened in a new window, Check that out. Make sure that you have enabled pop up in your browser to see that?";
			if (typeof my_event == 'undefined') {
			my_event = window.event;
			}
			window.confirm("Do you really want to close the browser?");
			if (my_event) {
				
			newWindow=window.open('test.html','','width=450,height=350')
			newWindow.document.write("<p>This is 'newWindow:'</p>" + my_event)
			newWindow.focus();
			my_event.returnValue = message;
			}
			return message;
			} */
			
			/* $(window).unload( function () {
			      $.ajax({
			        url: "http://localhost:8080/sms-web/j_spring_security_logout",
			        type: "GET",
			        data: "",
			        cache: true,
			        success: function(response){
			 			return "You logged out successfully,"+response;
			        }
			        });
			    } ); */

			
		</script>
		<!-- <script>
		
		 var xmlhttp = new XMLHttpRequest();
		        xmlhttp.onreadystatechange = function() {
		            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		
		            }
		         }
		
		   xmlhttp.open("GET", "testFile.jsp", true);
		        xmlhttp.send();   
		
		</script> -->

		 <!-- <script type="text/javascript">
		    function warning()
		    {
		    	if(confirm("Do you really want to close the browser?"))
	    		{
	    			alert("You conformed");
	    		}
		        return "You have trying to close the browser. Please logout your application before closing the browser."; //U can write any custom message here.
		    }
		    window.onbeforeunload = warning;
		    
		</script> --> 
		
		<!-- <script type="text/javascript">
			
		  function UnLoadWindow() {
				 return "Do you really want to close the browser?";
	        }
			window.onbeforeunload = UnLoadWindow;
			
			 
			</script> -->
		<!-- <script type="text/javascript">
		window.onbeforeunload = confirmExit;
		window.onunload = logout;

		 function confirmExit() {
		    //return "You have closed the browser. Do you want to logout from your application?";
		    
		    return "You have trying to  close the browser. Please logout your application before closing the browser";
		} 

		 function logout() {
		    window.location = 'http://localhost:8080/sms-web/j_spring_security_logout';
		} 
	</script> -->
	
	
	</head>
	<body class="page-header-fixed" onload="noBackPage();" onpageshow="if (event.persisted) noBackPage();" onunload=""
		<decorator:getProperty property="body.id" writeEntireProperty="true"/>
		<decorator:getProperty property="body.class" writeEntireProperty="true" />>
			<s:if test='%{#session.customerStatus == "Y"}'>
				 <jsp:include page="/common/userHomeHeader.jsp" />
				 	<s:if test='%{#session.newFeatures != "null"}'>
					 	<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
							 <div  class="alert alert-info" style="padding: 6px;margin-bottom: 0px;margin-left: 260px;position: relative;">  
								<marquee direction="left" scrollamount="3"><s:property value="#session.newFeatures" /></marquee>
							</div>
						</s:if>
					</s:if>
					<s:if test='%{user.isOnlySchoolAdmin == "Y" || (user.isSchoolTeacher == "Y") || user.isOnlySchoolHod=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolAsstStaff=="Y" || user.isChairMan=="Y" || user.isSchoolHostel == "Y" || user.isSchoolClerk=="Y" || user.isSchoolManager=="Y" || user.isSchoolTransport=="Y" || user.isSchoolDirector == "Y"}'>
	               	 <s:if test='%{!customerByCustId.checkEmailService && !customerByCustId.checkMobileService}'>
	               		<div class="alert alert-danger" style="padding: 7px;margin-bottom: 0px;margin-left: 260px;position: relative;">  
							<button aria-hidden="true" data-dismiss="alert" class="close" type="button"></button>
	                 			  ALERT ! Your SMS and E-Mail Services are disabled .
	                 			  <s:url id="urlSendSmsLink" action="ajaxDoSchoolInformation" namespace="/admin" />
								  <sj:a href="%{urlSendSmsLink}" targets="mainContentDiv" cssClass="ajaxify title AMCA emservicesDiv"> <s:if test='%{user.isOnlySchoolAdmin =="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>Click Here </s:if> </sj:a> to enable services
	                 	</div>  
	               	</s:if>
	               	<s:elseif test='%{!customerByCustId.checkEmailService}'>
	               		<div class="alert alert-danger" style="padding: 6px;margin-bottom: 0px;margin-left: 260px;position: relative;">  
							<button aria-hidden="true" data-dismiss="alert" class="close" type="button"></button>
	                 			  ALERT ! Your E-Mail service is disabled .
	                 			  <s:url id="urlSendSmsLink" action="ajaxDoSchoolInformation" namespace="/admin" />
								  <sj:a href="%{urlSendSmsLink}" targets="mainContentDiv" cssClass="ajaxify title AMCA eservicesDiv"> <s:if test='%{user.isOnlySchoolAdmin =="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>Click Here </s:if> </sj:a> to enable services
	                 		</div>  
	               	</s:elseif>
	               	<s:elseif test='%{!customerByCustId.checkMobileService}'>
	               		<div class="alert alert-danger" style="padding: 6px;margin-bottom: 0px;margin-left: 260px;position: relative;"> 
	               		    <s:if test='%{user.isSchoolTeacher == "Y" || user.isSchoolAsstStaff=="Y"}'>
	               		    <button aria-hidden="true" data-dismiss="alert" class="close" type="button"></button>
                 							ALERT ! Your SMS service is disabled, contact administrator  
                			</s:if>
                				<s:else>
                					<button aria-hidden="true" data-dismiss="alert" class="close" type="button"></button>
                 							ALERT ! Your SMS service is disabled  
                				</s:else>
	               		<s:url id="urlSendSmsLink" action="ajaxDoSchoolInformation" namespace="/admin" />
								  <sj:a href="%{urlSendSmsLink}" targets="mainContentDiv" cssClass="ajaxify title AMCA mservicesDiv"> <s:if test='%{user.isOnlySchoolAdmin =="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>Click Here </s:if> </sj:a> to enable services
	                 		</div>  
	               	</s:elseif>
               	</s:if>
               	<s:if test='%{user.isSchoolStudent == "Y" || user.isParent == "Y"}'>
               		<s:if test="%{dashBoardAlertsList != null && !dashBoardAlertsList.isEmpty()}">
         	      		<div  class="alert alert-info" style="padding: 10px;margin-bottom: -20px;margin-top:0px;margin-left: -61px;margin-right: -61px;position: relative;">  
							<marquee direction="left" scrollamount="3"><s:property value="dashBoardAlertsList[0].title" />-&nbsp;<s:property value="dashBoardAlertsList[0].messageDescription" /></marquee>
					   </div>
					</s:if>
					<s:else>
						<div  class="alert alert-info" style="padding: 10px;margin-bottom: -20px;margin-top:0px;margin-left: -61px;margin-right: -61px;position: relative;">   
							<marquee direction="left" scrollamount="3">currently there are no alerts.</marquee>
					   </div>
					</s:else>
               	</s:if>
               	<s:if test='%{user.isSchoolTeacher == "Y" || user.isOnlySchoolHod=="Y" || user.isSchoolAsstStaff=="Y" || user.isOnlySchoolTeacher == "Y" || user.isAdminCoordinator == "Y"}'>
               		<s:if test="%{dashBoardAlertsList != null && !dashBoardAlertsList.isEmpty()}">
     	          	    <div  class="alert alert-info" style="padding: 6px;margin-bottom: 0px;margin-left: 260px;margin-right: 0px;position: relative;">  
							<marquee direction="left" scrollamount="3"><s:property value="dashBoardAlertsList[0].title" />-&nbsp;<s:property value="dashBoardAlertsList[0].messageDescription" /></marquee>
					   </div>
					</s:if>
					<s:else>
						<div  class="alert alert-info" style="padding: 6px;margin-bottom: 0px;margin-left: 260px;margin-right: 0px;position: relative;">  
							<marquee direction="left" scrollamount="3">currently there are no alerts.</marquee>
					   </div>
					</s:else>
               	</s:if>
              </s:if>
               	<div><img   src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="myDefaultIndicator" style="display:none;background-repeat: no-repeat;position: fixed;z-index: 1000;top: 50%;left: 50%;text-align:center;" /></div>
               	<s:if test='%{user.isParent=="Y" || user.isSchoolStudent=="Y" || user.isSecretary=="Y" || user.isSecretaryPA=="Y" || user.isSchoolManager=="Y"}'>
               		<div class=container">
					<div class="page-container">
               	</s:if>
               	<s:else>
              		<div class=page-content-wrapper">
					<div class="page-content">
               	</s:else>
			 <div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					      </div>		 
			      	         </div>				 
				</div>
				<s:if test='%{user.isParent=="Y" || user.isSchoolStudent=="Y" || user.isSecretary=="Y" || user.isSecretaryPA=="Y" || user.isSchoolManager=="Y"}'>
					<span class="schoolLogoLink"><s:property value="#session.schoolName" /> </span>
					<div class="theme-panel hidden-xs hidden-sm" style="margin-top:-68px;">
				</s:if>
				<s:else>
					  <div class="theme-panel hidden-xs hidden-sm">
				</s:else>
							<div class="toggler">
							</div>
							<div class="toggler-close">
							</div>
							<div class="theme-options">
								<div class="theme-option theme-colors clearfix">
									<span>
										THEME COLOR
									</span>
									<ul>
										<li class="color-black current color-default" data-style="default">
										</li>
										<li class="color-blue" data-style="blue">
										</li>
										<li class="color-brown" data-style="brown">
										</li>
										<li class="color-purple" data-style="purple">
										</li>
										<li class="color-grey" data-style="grey">
										</li>
										<li class="color-white color-light" data-style="light">
										</li>
									</ul>
								</div>
								<div class="theme-option">
									<span>
										Layout
									</span>
									<select class="layout-option form-control input-small">
										<option value="fluid" selected="selected">Fluid</option>
										<option value="boxed">Boxed</option>
									</select>
								</div>
								<div class="theme-option">
									<span>
										Header
									</span>
									<select class="header-option form-control input-small">
										<option value="fixed" selected="selected">Fixed</option>
										<option value="default">Default</option>
									</select>
								</div>
								<div class="theme-option">
									<span>
										Sidebar
									</span>
									<select class="sidebar-option form-control input-small">
										<option value="fixed">Fixed</option>
										<option value="default" selected="selected">Default</option>
									</select>
								</div>
								<div class="theme-option">
									<span>
										Sidebar Position
									</span>
									<select class="sidebar-pos-option form-control input-small">
										<option value="left" selected="selected">Left</option>
										<option value="right">Right</option>
									</select>
								</div>
								<div class="theme-option">
									<span>
										Footer
									</span>
									<select class="footer-option form-control input-small">
										<option value="fixed">Fixed</option>
										<option value="default" selected="selected">Default</option>
									</select>
								</div>
						</div>	 
					  </div>
					<div class="space10"></div>
			   <div  id="mainContentDiv">
			 	<decorator:body />
					<!--[if lt IE 9]>
						<script src="${pageContext.request.contextPath}/scripts/newScripts/excanvas.min.js" type="text/javascript"></script>
						<script src="${pageContext.request.contextPath}/scripts/newScripts/respond.min.js" type="text/javascript"></script>
					<![endif]-->  
			<%-- Start common scripts --%>
			 		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/jQuery.url.js"></script>
					 <script src="${pageContext.request.contextPath}/scripts/newScripts/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
					<script src="${pageContext.request.contextPath}/scripts/newScripts/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
					<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
					<script src="${pageContext.request.contextPath}/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
					<script src="${pageContext.request.contextPath}/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
					<script src="${pageContext.request.contextPath}/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js" type="text/javascript" ></script>
					<script src="${pageContext.request.contextPath}/scripts/newScripts/jquery.blockui.min.js" type="text/javascript" ></script>
					<script src="${pageContext.request.contextPath}/scripts/newScripts/jquery.cookie.min.js" type="text/javascript" ></script>
					<script src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"  type="text/javascript"></script>
 

				<%-- Start date scripts --%>
                   <script src="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
                     <%-- Start table model scripts --%> 
					<script src="${pageContext.request.contextPath}/scripts/newScripts/table-advanced.js" type="text/javascript"></script> 				
					<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/data-tables/jquery.dataTables.js"></script>
					<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/data-tables/DT_bootstrap.js"></script>
					<script  src="${pageContext.request.contextPath}/plugins/select2/select2.min.js" type="text/javascript" ></script>
					<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/ui-extended-modals.js"> </script> 					
					<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/newScripts/table-editable.js"></script>
				<%-- Start Calendar scripts --%>
					<script src="${pageContext.request.contextPath}/plugins/fullcalendar/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
					<script src="${pageContext.request.contextPath}/scripts/newScripts/calendar.js" type="text/javascript"></script>
					<script src="${pageContext.request.contextPath}/scripts/newScripts/form-wizard.js" type="text/javascript"></script>	
				<%-- start popup scripts --%>
					<script src="${pageContext.request.contextPath}/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
					<script src="${pageContext.request.contextPath}/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
					<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-multi-select/js/jquery.multi-select.js"></script>
			 	<%-- start file upload scripts --%>	
					<script type="text/javascript"  src="${pageContext.request.contextPath}/plugins/bootstrap-fileupload/bootstrap-fileupload.js"></script> 
					<script  type="text/javascript" src="${pageContext.request.contextPath}/scripts/jQuery/jquery.MultiFile.js"></script>
			   <%-- start time pickers  scripts --%>	
			  		<script type="text/javascript"  src="${pageContext.request.contextPath}/plugins/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
			  		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/bootstrap-datetimepicker.min.js"> </script>
				<%-- start photo view  scripts --%>	
					<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/newScripts/portfolio.js"></script>
					<script type="text/javascript"  src="${pageContext.request.contextPath}/plugins/jquery-mixitup/jquery.mixitup.min.js"></script>
					<script type="text/javascript"  src="${pageContext.request.contextPath}/plugins/jquery-mixitup/jquery.fancybox.pack.js"></script>
					 <script type="text/javascript"  src="${pageContext.request.contextPath}/plugins/jquery-mixitup/image-picker.js"></script>
					
			 	<%-- start Old scripts --%>
					<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/form-image-crop.js"></script>			
					<script type="text/javascript"  src="${pageContext.request.contextPath}/plugins/uniform/jquery.uniform.min.js"></script>
					<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/newScripts/app.js"></script>    
			 	   	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
			 	   	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>	
					<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
					<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js"></script>
					<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
				
				<%-- start date and radio model scripts --%>
					<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/newScripts/form-components.js"></script>
					<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap/bootstrap-maxlength/boostrap-maxlength.min.js"></script> 
			 	   <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/formElementScript.js"></script>
			 	<%-- start editor scripts --%>
			 	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
			 	   <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
			 	    
			 	<%-- start charts scripts --%>
			 	   <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jQuery/highcharts.js"> </script>
				<%-- start New 3D charts scripts --%>
				   <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/amcharts/amcharts.js"> </script>
				   <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/amcharts/serial.js"> </script>
				   <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/amcharts/charts-amcharts.js"> </script>
				<%-- start easy-pie-chart scripts --%>
				   <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.js"> </script>
				   <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScripts/index.js"> </script>
			 	</div>
		   	</div>
		   	</div>
		  </div>
		   	<div class="footer">
		 		<jsp:include page="/common/footer.jsp" />
			</div>
			<script type="text/javascript">
				$(document).ready(function() {
					App.init(); 
                    $('.page-sidebar .ajaxify.start').click()
				});
				
				$('a.emservicesDiv').click(function() {
					enableServices();
				});
				$('a.eservicesDiv').click(function() {
					enableServices();
				});
				$('a.mservicesDiv').click(function() {
					enableServices();
				});
				function enableServices(){
					window.location.hash = "target=ES.ajaxify AMCS";
					if($("ul.page-sidebar-menu").find("li").hasClass('open')){
						//alert("rrrr="+$("ul.page-sidebar-menu").find("li").hasClass('open'));
						$("ul.page-sidebar-menu").find("li.open").find("span.arrow").removeClass("open");
						$("ul.page-sidebar-menu").find("li.open").find("ul.sub-menu").hide();
						$("ul.page-sidebar-menu").find("li.open").removeClass('active open');
					}
					$('li#schoolSettingsDiv').addClass('open active');
					$('li#manageSchool').addClass('active');
					$('li#manageSchool a').click();
					$("ul.page-sidebar-menu").find("li.open").find("ul.sub-menu").show();
					$("ul.page-sidebar-menu").find("li.open").find("span.arrow").addClass("open");
				}
				
				window.history.forward();
				  function noBackPage() { window.history.forward(); }
			</script>
			
	</body>
	</s:if>
	 <s:else>
			<script type="text/javascript">
				 window.location.href="<c:url value="/j_spring_security_logout"/>";
			</script>
	</s:else>  
</html>