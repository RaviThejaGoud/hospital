<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{onlineApplicationDetails != null}">
	<div class="alert alert-success">
		 You have successfully submitted online	application for your child <b><s:property value="onlineApplicationDetails.childrenFullPersonName" /></b>.
		<s:if test="%{admissionSettings.testConducted}">
			<br /> Your child needs to take entrance test on <b> <s:property value="admissionSettings.entranceDateStr" />
       </s:if>
	</div>
	</b>
		<br />Please note down the application number <b><s:property value="onlineApplicationDetails.applicationNumber" /> </b> for further reference.
 <div align="center">
   <a href="<c:url value='/signup/exportOnlineApplicationPDFGenerator.do'/>?apNo=<s:property value="onlineApplicationDetails.applicationNumber"/>&custId=<s:property value="custId"/>"
		class="btn green" target="_new"><i class="fa fa-print" ></i>&nbsp; Print</a>
</div>
</s:if>
<s:else>
	<div class="alert alert-danger">
		<strong>Error!</strong> Your application not created. Please try
		again.
	</div>
</s:else>
