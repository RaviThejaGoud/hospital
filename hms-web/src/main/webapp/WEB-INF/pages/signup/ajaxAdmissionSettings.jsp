<%@ include file="/common/taglibs.jsp"%>
<head><title>Eazy School :: Admission Information</title></head>
	<div class="grid_16"  style="color: #999;padding:10px;font-size: 14px;line-height: 24px;background: #fff">
	<s:if test="%{admissionSettingsList !=null || !admissionSettingsList.isEmpty}">
	<script type="text/javascript" charset="utf-8" > 
		var host = (("https:" == document.location.protocol) ? "https://secure.": "http://");
		document.write(unescape("%3Cscript src='http://demo.eazyschool.in/scripts/common/form.js' type='text/javascript'%3E%3C/script%3E"));
	</script> 
	<script type="text/javascript" charset="utf-8"> 
		var urtGILite = new URTSubForm();
		urtGILite.initialize({
		'product':'signup',
		'custId':'2',
		'scrolling':'yes',
		'height':'1450'});
		urtGILite.display();
	</script>
	</s:if>
	<!--<s:if test="%{admissionSettingsList !=null || !admissionSettingsList.isEmpty}">
	<s:iterator value="admissionSettingsList">
	 <div class="grid_11" >
				<h2 style="padding: 0px;margin: 0px;color: #999;font-size: 25px;">Admissions are opened for Academic Year <s:property value="academicYear.academicYear"/></h2>
			</div>
			 <div class="grid_11">
				<s:property value="admissionContent"/>
			</div> 
			<div class="grid_11">
				 <strong>Applications Issue Date :</strong>
				<s:property value="applicationStartDateStr"/>
				</div>
 
			<div class="grid_11">
			 <strong>Applications Closed Date  :</strong>
				 
				<s:property value="applicationClosedDateStr"/> 
			</div>
		
		<s:if test="%{admissionSettings.testConducted}">
			<div class="grid_11"  id="entranceDate">
				<strong> Entrance Date : </strong>
			<s:property value="entranceDateStr"/>
				 
			</div>
			</s:if>
				<div class="grid_11">
			<strong> Admissions End Date: </strong>
				<s:property value="admissionsEndDateStr"/> 
			</div>
			<div class="grid_11">
			<strong> Application Fee : </strong>
				<s:property value="applicationFee"/>
			</div>
			<div class="grid_11">
			<s:if test='%{admissionOpenOrClose == "Y"}'>
			    To apply online <a href="${pageContext.request.contextPath}/signup/onlineApplicationForm.do?customerId=<s:property value="custId"/>&academicYearId=<s:property value="academicYear.id"/>">Click Here</a>
			    Click Here To <a href="${pageContext.request.contextPath}/signup/downloadApplicationForm.do?customerId=<s:property value="custId"/>" target="_new">Download </a> Application
			  </s:if>
			  <s:else>
			    <p style="color: #E3302C;">Application issue date is over to apply through online.Please Contact Us.</p> 
			  </s:else>
			</div>
			</s:iterator>
	</s:if>
	--><s:else>
	<h2 style="padding: 0px;margin: 0px;color: #999;font-size: 25px;">Admission are not opened keep watching regularly for updates.</h2>
	</s:else>
	 	</div>