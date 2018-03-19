<%@ include file="/common/taglibs.jsp"%>
<td colspan="6"> 
	<s:form action="ajaxUpdatePackageDetails" id="pakageUpdateForm" method="post" theme="simple" namespace="/admin">
		<s:if test="%{packageDetailsList != null && !packageDetailsList.isEmpty()}">
		<span class="required">*</span>Students Range(Upto):
		
		<div >
		<br/>
			<s:radio name="packageDetails_" cssClass="required" required="true" list="packageDetailsList" listKey="id" listValue="studentsRange" cssStyle="float: left; width:30px;"/>
		</div>
		<div >
		<br/>
			
			<sj:submit targets="schoolInformationContent" value="Submit" cssClass="button2 submit small"  formIds="pakageUpdateForm" onClickTopics="packageDetailsUpdateFormValidation"/>
			<s:url id="doCancelPackageUpdate" action="ajaxDoUpdatePackageDetails" includeParams="all" namespace="/admin"></s:url>
	    	<sj:a href="%{doCancelPackageUpdate}" cssClass="cancelButton" targets="editPackageDetails%{customer.packageDetails.id}" onCompleteTopics="doEditPackageDetails">Cancel</sj:a>
	    </div>
		</s:if>	
		<s:else>
			Currently there are no packages.
		</s:else>
	</s:form>
</td>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('packageDetailsUpdateFormValidation', function(event, data) {
							if ($('#pakageUpdateForm').valid()){
								 return true;
							}
							else
								return false;
						});
				});
</script>