<%@ include file="/common/taglibs.jsp"%>
	<div class="header navbar navbar-inverse navbar-fixed-top">
	   <div class="header-inner">
		<s:if test='%{#session.customerStatus == "Y"}'>
		  <a class="brand navbar-brand" href="#"> <img
					src='<s:property  value="#session.custImage"/>' border="0"
					alt="logo" class="logoHeader" id="customerLogoDiv"/> </a>
			<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <img src="../img/menu-toggler.png" alt="" /> </a>
			<div class="hor-menu hidden-sm hidden-xs">
				<ul class="nav navbar-nav">
				  <s:if test='%{user.isSchoolStudent=="Y"}'>
					<li class="start active">
				  	 <a href="${pageContext.request.contextPath}/alumnee/dashboard.do?id=dashboard"
						id="dashboard" class="start"> <i class="fa fa-home"></i>
						<span class="title">Dashboard</span> <span class="selected"></span>
					  </a>
					</li>
					<li class="disabled-link" id="SOD">
						<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="USTM">
							<span class="text"></span>
							<i class="fa fa-twitter-square"></i>Social Home<span class="selected"></span>
							<i class="fa fa-angle-down"></i>     
						</a>
						<ul class="dropdown-menu">
							<li>
								<s:url id="eventsHome" action="ajaxEventsHome" namespace="/alumnee"></s:url>
								<sj:a id="eventsHome" href="%{eventsHome}" targets="mainContentDiv" cssClass='ajaxify EVENT'><i class="fa fa-calendar"></i>&nbsp; Events</sj:a>
							</li>
							<li>
								<s:url id="socialHome" action="ajaxSocialHome" namespace="/alumnee"></s:url>
								<sj:a id="socialHome" href="%{socialHome}" targets="mainContentDiv" cssClass='ajaxify SOCI'><i class="fa fa-twitter-square"></i>&nbsp; Go Social</sj:a>
							</li>
							<li>
								<s:url id="galleryHome" action="ajaxPhotosHome" namespace="/alumnee"></s:url>
								<sj:a id="galleryHome" href="%{galleryHome}" targets="mainContentDiv" cssClass='ajaxify PHOTO'><i class="fa fa-camera"></i>&nbsp; Gallery</sj:a>
							</li>
							<li>
								<s:url id="discussionsHome" action="ajaxDiscussionsHome" namespace="/alumnee"></s:url>
								<sj:a id="discussionsHome" href="%{discussionsHome}" targets="mainContentDiv" cssClass='ajaxify DIS'> <i class="fa fa-dribbble"></i> &nbsp;Discussions</sj:a>
							</li>
						</ul>
						<b class="caret-out"></b>  
					</li>
				  </s:if>
			  </ul>
			</div>
			<ul class="nav navbar-nav pull-right" style="margin-top: 5px;">
				<li class="dropdown user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" style="padding-top: 7px;"> 
							<s:if test="%{user.profileImage.adjustedAttachmentFilePath != null &&  user.profileImage.adjustedAttachmentFilePath != ''}">
								<img src='<c:url value="${user.profileImage.adjustedAttachmentFilePath}"/>' border="0" style="float: left; margin-right: 5px; height: 60px; width: 70px;" id="userProfileImageDiv"/>
							</s:if> 
							<s:elseif
								test="%{#session.studentImg != null &&  #session.studentImg != ''}">
								  <img src='<s:property  value="#session.studentImg"/>' border="0" style="float: left; margin-right: 5px; height: 60px; width: 70px;" id="studentProfileImageDiv"/> 
							</s:elseif> 
							<s:else>
								<img src="../img/avatar.png" id="profileImagesDiv" border="0" style="float: left; margin-right: 5px; height: 60px; width: 70px;">
							</s:else> 
							<p class="username hidden-480" style="white-space: normal;width: 175px;"> &nbsp;&nbsp;<s:property value="#session.firstName" />
								 <s:if test='%{user.isSchoolStudent=="Y"}'> 
								 	<small>(<s:property value="#session.studentClassSection" />)</small>
								  </s:if> 
								  <i class="fa fa-angle-down"></i>
						    </p> 
					     </a>
						<ul class="dropdown-menu">
						<li id="profileLink">
							<s:url id="signupStep1" action="../user/ajaxDoEditProfile" includeParams="all"></s:url>
							<sj:a href="%{signupStep1}" targets="mainContentDiv">
								<i class="fa fa-user"></i>My Profile
							</sj:a>
						</li>
						<li>
							<a href="javascript:;" id="trigger_fullscreen"><i
								class="icon-move"></i> Full Screen</a>
						</li>
						<li>
							<a href="<c:url value="/j_spring_security_logout"/>"><i
								class="icon-key"></i>Logout</a>
						</li>
					</ul>
				</li>
			</ul>
			<s:if test='%{#session.dateExceeded =="Y"}'>	
	 			<a id="showEndDate" class="warningResponse" href="#responsiveEndDate" data-toggle="modal">
	 			<jsp:include page="/WEB-INF/pages/admin/ajaxShowSchoolEndDate.jsp" />
		 </s:if> 	
		<s:if
			test="%{(#session.customerVision !=null && !#session.customerVision.isEmpty()) || (#session.customerMission !=null && !#session.customerMission.isEmpty())}">
			<a id="showVisionMission" class="response" href="#responsiveVision" data-toggle="modal"> <jsp:include
					page="/WEB-INF/pages/admin/ajaxShowCustomerVissionAndMission.jsp" />
			</a>
		</s:if>
			<!-- END TOP NAVIGATION MENU -->
	  </s:if>
	  </div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<div class="container">
	<div class="page-container">
		<div class="page-sidebar navbar-collapse collapse">
		<!--  below page used for student and parent hearders --- HORIZONTAL AND SIDEBAR MENU FOR MOBILE & TABLETS-->
		 	<jsp:include page="/common/tabletAndMobileHeaderPage.jsp" />
		</div>
	</div>
