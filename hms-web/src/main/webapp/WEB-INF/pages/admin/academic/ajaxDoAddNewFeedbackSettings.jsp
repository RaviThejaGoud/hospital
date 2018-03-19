<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{feedBackSettings.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Feedback Settings
			</h4>
		</div>
		<div class="modal-body">
</s:if>
<s:form action="ajaxCreateFeedbackSettings" theme="simple"
	id="addFeedbackSettings" cssClass="form-horizontal form-body"
	namespace="/admin" method="post">
	<s:hidden name="feedBackSettingId" value="%{feedBackSettings.id}"></s:hidden>
	<h4 class="bold pageTitle">
		<s:if test="%{feedBackSettings.id == 0}">
			Create Feedback Settings
		</s:if>
	</h4>
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE : </span>
		</div>
		<div class="col-md-10">
			<s:if test="%{feedBackSettings.id != 0}">
				<ul>
					<li>
						You can update feedback Settings from current date to academic
						year end date
					</li>
					<li>
						If current date is below than academic year start date you can
						define feedback settings from academic year start date to end
						date.
					</li>
					<li>
						If current date is above than academic year end date you can't
						edit feedback settings.
					</li>
				</ul>
			</s:if>
			<s:else>
				<ul>
					<li>
						You can define feedback Settings from current date to academic
						year end date
					</li>
					<li>
						If current date is below than academic year start date you can
						define feedback settings from academic year start date to end
						date.
					</li>
					<li>
						If current date is above than academic year end date you can't
						create feedback settings.
					</li>
				</ul>
			</s:else>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required">*</span>Attendance% :
		</label>
		<div class="col-md-3">
			<sj:textfield name="academicYear.attendancePercentage"
				id="percentage" cssClass="form-control required" maxlength="40"></sj:textfield>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required">*</span>From Date :
		</label>
		<div class="col-md-3">
			<s:if test="%{feedBackSettings.id != 0}">
				<div data-date-start-date="" data-date-end-date="+0d" data-date-format="mm/dd/yyyy"
					class="input-group input-medium  date date-picker">
					<input type="text" id="startDate" readonly="readonly"
						name="feedBackSettings.startDate"
						value='<s:property value="feedBackSettings.startDateFormet"/>'
						onchange="javascript:verifyDate(this.value);"
						class="required form-control input-medium" />
					<span class="dateInput input-group-btn">
						<button type="button" class="btn default">
							<i class="fa fa-calendar"></i>
						</button> </span>
				</div>
				<span class="help-block">(MM/DD/YYYY)</span>
			</s:if>
			<s:else>
				<div data-date-start-date="" data-date-end-date="+0d"  data-date-format="mm/dd/yyyy"
					class="input-group input-medium  date date-picker">
					<input type="text" id="startDate" readonly="readonly"
						name="feedBackSettings.startDate"
						onchange="javascript:verifyDate(this.value);"
						class="required form-control input-medium" />
					<span class="dateInput input-group-btn">
						<button type="button" class="btn default">
							<i class="fa fa-calendar"></i>
						</button> </span>
				</div>
				<span class="help-block">(MM/DD/YYYY)</span>
			</s:else>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required">*</span>To Date :
		</label>
		<div class="col-md-3">
			<s:if test="%{feedBackSettings.id != 0}">
				<div data-date-start-date="" data-date-end-date="+0d" data-date-format="mm/dd/yyyy"
					class="input-group input-medium  date date-picker">
					<input type="text" id="endDate" readonly="readonly"
						name="feedBackSettings.endDate"
						value='<s:property value="feedBackSettings.endDateFormet"/>'
						onchange="javascript:verifyDate(this.value);"
						class="required form-control input-medium" />
					<span class="dateInput input-group-btn">
						<button type="button" class="btn default">
							<i class="fa fa-calendar"></i>
						</button> </span>
				</div>
				<span class="help-block">(MM/DD/YYYY)</span>
			</s:if>
			<s:else>
				<div data-date-start-date="" data-date-end-date="+0d" data-date-format="mm/dd/yyyy"
					class="input-group input-medium date date-picker">
					<input type="text" id="endDate" readonly="readonly"
						name="feedBackSettings.endDate"
						onchange="javascript:verifyDate(this.value);"
						class="required form-control input-medium" />
					<span class="dateInput input-group-btn">
						<button type="button" class="btn default">
							<i class="fa fa-calendar"></i>
						</button> </span>
				</div>
				<span class="help-block">(MM/DD/YYYY)</span>
			</s:else>
		</div>
	</div>
	<div class="form-actions fluid">
		<div class="col-md-6">
			<div class="col-md-offset-3 col-md-12">
				<img src="../img/bg/bigWaiting.gif" alt="Loading..."
					title="Loading..." id="indicator"
					style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					targets="mainContentDiv" validate="true"
					formIds="addFeedbackSettings"
					onBeforeTopics="feedBackFormValidation" />
				<s:if test="%{feedBackSettings.id != 0}">
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</s:if>
				<s:else>
					<s:url id="doViewRecords" action="ajaxGetFeedbackSettings"
						includeParams="all" escapeAmp="false" namespace="/admin">
						<s:param value="academicYear.id" name="academicYearId" />
					</s:url>
					<sj:a href="%{doViewRecords}" cssClass="btn default"
						targets="mainContentDiv">Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{feedBackSettings.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
changePageTitle('Add Feedback Settings');
$(document).ready(function() {
	FormComponents.init();
	$.subscribe('feedBackFormValidation', function(event, data) {
		if ($('#addFeedbackSettings').valid()) {
			$('button.close').click();
			return true;
		} else
			event.originalEvent.options.submit = false;
	});
});
function verifyDate(date) {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
	//alert(startDate+"-------------"+endDate);
		if (Date.parse(endDate) < Date.parse(startDate)) {
			alert("To Date should be greater than or equal to From Date.");
			$('#endDate').val("");
		}
	}
}
</script>