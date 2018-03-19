<%@ include file="/common/taglibs.jsp"%>
<s:form id="editStudentDisable" action="ajaxDisableStudent"	method="post" theme="simple" namespace="/student" cssClass="form-horizontal">
	<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
		<s:hidden name="tempId" value="%{tempId}" />
		<s:hidden name="bedId" value="%{selectedId}" />
		<s:hidden name="student.status" value="%{plTitle}" />
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-6">
				<s:if test='%{#session.previousYear == "N"}'>
					<img src="../img/bg/bigWaiting.gif" alt="Loading..."  title="Loading..." id="indicator"
						style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
					<sj:submit cssClass="submitBt btn blue" value="Save" id="submitButton1" indicator="indicator" 
						targets="searchStudentsList" formIds="editStudentDisable" 
						onBeforeTopics="changeStudentDisableValidation" />
					 	<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
				</s:if>
			</div>
		</div>
	</s:if>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('changeStudentDisableValidation');
	FormComponents.init();
	$.subscribe('changeStudentDisableValidation', function(event, data) {
			$('button.close').click();
	}); 
});

</script>