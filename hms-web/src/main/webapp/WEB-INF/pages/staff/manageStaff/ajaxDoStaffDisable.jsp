<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{tempString != null || tempString == "driverInfo" || tempString == "helperInfo"}'>
	<div data-width="760" class="modal fade modal-overflow in" 
		style="display: block; width: 650px; margin-left: -279px; margin-top: 100px;" aria-hidden="false">
		<div class="modal-body">
			<div class="form-body">
				<s:form id="editStaffDisable" action="ajaxDisableDriverOrHelper"
					namespace="/admin" method="post" theme="simple"
					cssClass="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title">
							Inactive Non-Teaching Staff
						</h4>
					</div>
					<s:hidden name="anyTitle" value="%{anyTitle}"></s:hidden>
					<s:hidden name="tempString" value="%{tempString}"></s:hidden>
					<s:hidden name="tempId1" value="%{tempId1}"></s:hidden>
					<s:hidden name="tempId" value="%{tempId}" />

					<s:if
						test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
						<div class="form-group">
							<label class="control-label col-md-5">
								<span class="required">*</span> Staff Inactive For Cause/Reason	:
							</label>
							<div class="col-md-10">
								<s:textarea cols="5" rows="5" id="messageDescription1"
									maxCharsData="255" cssClass="required form-control word_count"
									name="staff.description"></s:textarea>
								<span class="counter"></span>
							</div>
						</div>
					</s:if>
					<div class="form-actions fluid">
						<div class="col-md-offset-2 col-md-9">
							<s:if test='%{#session.previousYear == "N"}'>
								<sj:submit   cssClass="submitBt btn blue" value="Submit"
									formIds="editStaffDisable" targets="transportDriverOrHelper"
									onBeforeTopics="changeStaffDisableValidation" />
							</s:if>
							<button type="button" data-dismiss="modal" class="btn default">
								Cancel
							</button>
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</div>
</s:if>
<s:else>
	<div data-width="760" class="modal fade modal-overflow in" 
		style="display: block; width: 650px; margin-left: -279px; margin-top: 100px;" aria-hidden="false">
		<div class="modal-body">
			<div class="form-body">
			<s:form id="editStaffDisable1" action="ajaxDisableStaff" method="post"
				theme="simple" namespace="/staff" cssClass="form-horizontal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						Inactive Teaching Staff
					</h4>
				</div>
					<s:hidden name="tempId" value="%{tempId}" />
					<s:if
						test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
						<div class="form-group">
							<label class="control-label col-md-5">
								<span class="required">*</span> Staff Inactive For Cause/Reason :
							</label>
							<div class="col-md-10">
								<s:textarea cols="5" rows="5" id="messageDescription"
									maxCharsData="255" cssClass="required form-control word_count"
									 name="staffVo.description"></s:textarea>
								<span class="counter"></span>
							</div>
						</div>
					</s:if>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
						<s:if test='%{#session.previousYear == "N"}'>
							<sj:submit   cssClass="submitBt btn blue" value="Submit"
								  formIds="editStaffDisable1" targets="editStaffResultsDiv"
								onBeforeTopics="changeStaffDisableValidation1" />
						</s:if>
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</div>
				</div>
			</s:form>
		 </div>
		</div>
	</div>
</s:else>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<script type="text/javascript">
$.subscribe('changeStaffDisableValidation', function(event, data) {
	if ($('#editStaffDisable').valid()) {
	$('button.close').click();
		return true;
	} else {
		event.originalEvent.options.submit=false;
	}
});
$.subscribe('changeStaffDisableValidation1', function(event, data) {
	if ($('#editStaffDisable1').valid()) {
	$('button.close').click();
		return true;
	} else {
		event.originalEvent.options.submit=false;
	}
});
</script>