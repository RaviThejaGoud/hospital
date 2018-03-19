<%@ include file="/common/taglibs.jsp"%>
<fieldset id="stepTcsss" class="step13"> 
<jsp:include page="/common/messages.jsp" />
<div class="grid_12 commomnTabs">
<span class="tcIssue" id="<s:property value='bankName'/>"></span>
	<!--<div class="grid_3">
		<a href='${pageContext.request.contextPath}/reports/ajaxTCGeneration.do?accountId=<s:property value="anyId"/>' target="_NEW">Generate TC</a>
	</div>
	-->
	<s:if test="%{tempId != 0}">
		<div class="grid_3">
	      	<a href='${pageContext.request.contextPath}/userfiles/<s:property value="customerName" />/<s:property value="viewStudentPersonAccountDetails.academicYear" />/<s:property value="viewStudentPersonAccountDetails.classAndSection" />/<s:property value="anyTitle" />' title="TC Download" target="_NEW">Download TC (Pdf)</a>
	    </div>
	     <div class="grid_12 examTabBorder">
				 Tc already generated if you want updated student details click on again this link <a style="cursor: pointer;"  id="generateTC" onclick="javascript:doGetStudentGenerateTC(<s:property value="anyId"/>);"  target="_NEW">Generate TC</a>
		 </div>
	</s:if>
	<s:else>
		<div class="grid_12 examTabBorder">
				 Tc not yet generated for this student if you want generate click on <a style="cursor: pointer;"  id="generateTC" onclick="javascript:doGetStudentGenerateTC(<s:property value="anyId"/>);"  target="_NEW">Generate TC</a>
		</div>
	</s:else>
	</div>
</fieldset>
<script type="text/javascript"> 
function doGetStudentGenerateTC(id) {
	var desc = $('span.tcIssue').attr('id');
	var answer = '';
	if(isNonEmpty(desc)) {
        answer = confirm(""+desc);
       }else
        $('#generateTC').attr("href","${pageContext.request.contextPath}/reports/ajaxTCGeneration.do?accountId="+id);
	if(isNonEmpty(answer)) {
		if (!answer) {
               $('#generateTC').attr("href", "javascript:void(0);");
       } else {
               $('#generateTC').attr("href","${pageContext.request.contextPath}/reports/ajaxTCGeneration.do?accountId="+id);
       }
	}
 }
</script>