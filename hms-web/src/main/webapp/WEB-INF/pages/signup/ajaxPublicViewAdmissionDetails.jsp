<%@ include file="/common/taglibs.jsp"%>
	<div class="grid_16"  style="color: #999;padding:10px;font-size: 14px;line-height: 24px;background: #fff">
	<s:if test="%{admissionSettingsList !=null || !admissionSettingsList.isEmpty}">
	<s:iterator value="admissionSettingsList">
	 <div class="grid_11" >
				<h2 class="appHeader">Admissions are opened for Academic Year <span class="appHeader"><s:property value="academicYear.academicYear"/></span></h2>
			</div>
			 <div class="grid_11">
				<span class="appLableValue"><s:property value="admissionContent"/></span>
			</div> 
			<div class="grid_11">
				 <strong class="appLableFont">Applications Issue Date :</strong>
				<span class="appLableValue"><span class="appLableValue"><s:property value="applicationStartDateStr" /></span></span>
				</div>
 
			<div class="grid_11">
			 <strong class="appLableFont">Applications Closed Date  :</strong>
				 
				<span class="appLableValue"><s:property value="applicationClosedDateStr"/> </span>
			</div>
		
		<s:if test="%{admissionSettings.testConducted}">
			<div class="grid_11"  id="entranceDate">
				<strong class="appLableFont"> Entrance Date : </strong>
			<span class="appLableValue"><s:property value="entranceDateStr"/></span>
				 
			</div>
			</s:if>
				<div class="grid_11">
			<strong class="appLableFont"> Admissions End Date: </strong>
				<span class="appLableValue"><s:property value="admissionsEndDateStr"/> </span>
			</div>
			<div class="grid_11">
			<strong class="appLableFont"> Application Fee : </strong>
				<span class="appLableValue"><s:property value="applicationFee"/></span>
			</div>
			<div class="grid_11">
			 <s:if test='%{admissionOpenOrClose == "Y"}'>
			    <strong class="appLableFont"> To apply online <a href="${pageContext.request.contextPath}/signup/onlineApplicationFormByCustomer.do?admissionSettings.id=<s:property value="id"/>" target="_new">Click Here</a></strong>
			   <!--  <br/>
			 <strong class="appLableFont">Click here to <a href="${pageContext.request.contextPath}/signup/downloadApplicationForm.do?custId=<s:property value="custId"/>" target="_new">Download </a> application</strong> -->
			  </s:if>
			  <s:else>
			    <p style="color: #E3302C;">Application issue date is over to apply through online.Please Contact Us.</p> 
			  </s:else>
			</div>
			</s:iterator>
	</s:if>
	<s:else>
	<h2 style="padding: 0px;margin: 0px;color: #999;font-size: 25px;">Admission are not opened keep watching regularly for updates.</h2>
	</s:else>
	 	</div>
	 <style type="text/css">
	 .appLableFont
	 {
	 	font:14px/24px Arial,Helvetica,Sans-serif;
	 	font-weight: bold; 
	 	color: #545650;
	 }
	  .appLableValue
	 {
	 	font:14px/24px Arial,Helvetica,Sans-serif;
	 	 font-weight: normal;
	 	 color: #545650;
	 }
	   .appHeader
	 {
	 	font: 24px Arial,Helvetica,Sans-serif;
	 	 font-weight: bold;
	 	 color: #545650;
	 	 padding: 0px;
	 	 margin: 0px;
	 }
	 
	 
	 </style>