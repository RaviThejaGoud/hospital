<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in" style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">Transfer Student</h4>
	</div>
	<div class="modal-body">
<div class="form-body">
<s:if test="%{customerList!= null && !customerList.isEmpty()}">
	<s:form action="ajaxAddTransferStudentDetails" id="transferStudent"
		method="post" theme="simple" cssClass="form-horizontal"
		namespace="/student">
		<s:hidden name="student.id" />
		<span> Are you sure do you want to transfer this student. If yes select the check box :<input
				type="checkbox" id="checkedStudent" tabindex="11" name="status"
				onclick="changeStudentStatus(this.value);"> </span>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6">
						<span class="required"> * </span>select organization :
					</label>
					<div class="col-md-4">
						<s:select list="customerList" id="organizationId" name="tempId"
							cssClass="required form-control input-medium" listKey="id"
							listValue="organization" headerKey=""
							headerValue="- Select Organization -" theme="simple"
							onchange="javascript:selectClass(this.value);">
						</s:select>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-6 col-md-12">
					<sj:submit    value="Save"
						indicator="indicator" targets="searchStudentsList"
						cssClass="submitBt btn blue"
						onBeforeTopics="studentTransferFomrValidation" validate="true"
						formIds="transferStudent" />
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					<!--<s:url id="doCancelStudent"
						action="ajaxSearchTransferStudentDetails" namespace="/student"
						includeParams="all" escapeAmp="false">
					</s:url>
					<sj:a href="%{doCancelStudent}" cssClass="btn default"
						indicator="indicator" targets="searchStudentsList">Cancel</sj:a>
				--></div>
			</div>
		</div>
	</s:form>
	</s:if>
<s:else>
	<div class="alert alert-info">
		Any Organization are not link with your school.
	</div>
</s:else>
</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
$("input:checkbox, input:radio").uniform();
	$.subscribe('studentTransferFomrValidation', function(event, data) {
		if (!$("#checkedStudent").is(':checked')) {
			alert("Please check the check box to transfer student");
			event.originalEvent.options.submit = false;
		}
		else
		{
			$('button.close').click();
		}
		
	});

});
</script>
