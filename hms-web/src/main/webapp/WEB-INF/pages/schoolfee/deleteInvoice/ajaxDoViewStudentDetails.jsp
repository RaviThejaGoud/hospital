.<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if test="%{customerList != null && !customerList.isEmpty()}">
	<s:form id="searchStudentByNumber" action="ajaxSearchStudentByAdmissionNumber" theme="simple" cssClass="form-horizontal" namespace="/schoolfee">
		<div class="form-group">
			<label class="col-md-3 control-label"> <span
							class="required">*</span> Select Customer : </label>
			<div class="col-md-9">
				<s:select list="customerList" id="customerId"
					cssClass="form-control input-large required" name="tempId" listKey="id"
					listValue="organization" headerKey=""
					headerValue="- Select Customer -" theme="simple" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span
							class="required">*</span> Search By Student : </label>
			<div class="col-md-5">
				<div class="input-group">
					<sj:textfield name="anyTitle" id="rollNumber"
						placeholder="Student Admission Number" cssClass="form-control input-large required" />
					<span class="input-group-btn"> 
					<sj:submit
							targets="searchStudentsList" value="Find Student" validate="true"
							cssClass="submitBt btn blue long" cssStyle="float:none;"
							onBeforeTopics="searchStudentForm"
							formIds="searchStudentByNumber" /></span>
				</div>
				<!-- <span class="help-block">(Key at least 3 chars and hit Find
					Student to get closer match.)</span> -->
			</div>
		</div>
	</s:form>
	<div id="searchStudentsList"></div>
</s:if>
<script type="text/javascript">
$.subscribe('searchStudentForm', function(event, data) {
	if ($('#searchStudentByNumber').valid()) {
		$('div.alert-success').hide();
		event.originalEvent.options.submit = true;
	} else {
		event.originalEvent.options.submit = false;
	}
});
</script>