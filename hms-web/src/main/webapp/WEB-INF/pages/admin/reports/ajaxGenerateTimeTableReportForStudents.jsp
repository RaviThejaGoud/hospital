<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Student-->TimeTable
				</div>
			</div>
			<div class="portlet-body">
			<div class="form-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<div class="tab-content" id="studTimeTabCont">
							<s:if
								test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
								<s:form action="ajaxGenerateTimeTableByClasses" cssClass="form-horizontal"
									theme="simple" id="generateTTForm" method="post" namespace="/reports">
									<div class="form-body">
									<div class="form-group">
										<label class="control-label col-md-2">
											<span class="required">*</span>Student Name :
										</label>
										<div class="col-md-3">
											<s:select list="viewStudentPersonAccountDetailsList"
												name="selectedId" listKey="'('+classSectionId+')'"
												listValue="displayName" cssClass="required form-control"></s:select>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-2 col-md-4">
											<s:submit type="submit small" value="Download Timetable"
												cssClass="submitBt btn blue" title="generate report"
												cssStyle="cursor:pointer">
											</s:submit>
										</div>
									</div>
									</div>
								</s:form>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									students are not available.
								</div>
							</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">

/*function generateClassIds() {
 if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
 var classIds = $("input[name=chkBoxSelectedIds]:checked");
 var selectedClassIds = '';
 if (classIds.length > 0) {
 selectedClassIds = '(';
 for ( var i = 0; i < classIds.length; i++) {
 selectedClassIds += classIds[i].value + ', ';
 }
 selectedClassIds += '0)';
 }
 $("#classNameIds").val(selectedClassIds);
 return true;
 } else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
 alert("Please select at least one Class");
 return false;
 } else {
 return false;
 }
 }*/
</script>