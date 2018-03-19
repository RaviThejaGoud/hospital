<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">Enable Student</h4>
	</div>
	<div class="modal-body">
	<div class="form-body">
	<s:form action="ajaxEnableTransferStudentDetails" id="enableTransferStudent" method="post" theme="simple"
		cssClass="form-horizontal" namespace="/student">
		<s:hidden name="tempId" value="%{transferStudent.id}" />
		<div class="form-body">
		<!-- <div class="row">
			<div class="form-group">
				<label class="control-label col-md-9"> Are Sure do you
					want to transfer this student to another school (If yes click the
					check box): </label>
				<div class="col-md-1">
					<input type="checkbox" id="checkedStudent" name="status">
				</div>
			</div>
		</div> -->

		<div class="form-group">
			<label class="control-label col-md-4"> <span
				class="required">*</span>Admission Number :
			</label>
			<div class="col-md-8">
				<sj:textfield name="admissionNumber" id="studAdmissionNumber"
					cssClass="required form-control input-medium as-input"></sj:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-4"> <span
				class="required">*</span>Select Class :
			</label>
			<div class="col-md-8">
				<s:select list="studyClassList" id="className"
					cssClass="required form-control input-medium"
					name="classSectionId" listKey="id" listValue="classAndSection"
					headerKey="" headerValue="- Select Class -" theme="simple"
					onchange="" requiredposition="first">
				</s:select>
			</div>
		</div>
		<s:if test='%{customer.transportModuleStatus==true}'>
			<div class="form-group">
				<label class="control-label col-md-4"> Transport Mode :
				</label>
				<div class="col-md-8">
					<div class="clearfix">
						<div data-toggle="buttons" class="btn-group" id="transportDiv">
							<label class="btn blue radioMultiOption active"> <input
								type="radio" value="O" class="toggle" checked="checked"
								id="transportMode" name="anyId">
								Own
							</label>
							<label class="btn blue radioMultiOption"> <input
								type="radio" class="toggle" value="P" id="transportMode"
								name="anyId"> Private
							</label>
							<label class="btn blue radioMultiOption"> <input
								type="radio" class="toggle" value="T" id="transportMode"
								name="anyId"> School Transport
							</label>
						</div>
					</div>
				</div>
				</div>
		</s:if>
		<div class="form-group">
			<label class="control-label col-md-4"> <span
				class="required">*</span>Select Category :
			</label>
			<div class="col-md-8">
				<s:select list="schoolCategoriesList" id="categoryName"
					name="categoryName" listKey="id"
					cssClass="required form-control input-medium"
					listValue="categoryName" headerKey=""
					headerValue="- Select Category -" theme="simple"
					requiredposition="first" required="true">
				</s:select>
			</div>
		</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-8">
				<sj:submit cssClass="submitBt btn blue" value="Save"
					indicator="indicator" targets="mainContentDiv"
					onBeforeTopics="enableTransferStudentFomrValidation"
					validate="true" formIds="enableTransferStudent" />
				<button type="button" data-dismiss="modal" class="btn default">
					Cancel</button>
			</div>
		</div>
	</s:form>
	</div>
 </div>
</div>
<script type="text/javascript">
$.destroyTopic('enableTransferStudentFomrValidation');
$(document).ready(function() {
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	$.subscribe('enableTransferStudentFomrValidation', function(event, data) {
		if ($('#enableTransferStudent').valid()) {
			$('button.close').click();
			return true;
		} else
			event.originalEvent.options.submit = false;
	});
	/* $("input#studAdmissionNumber").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckStudentAdmissionNumber.do",
	{
		minChars : 1,
		min : "no"
	}); */
});
</script>
