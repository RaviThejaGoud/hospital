<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div id="classSubjectsDeleteDiv"></div>
<div class="form-body">
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
	<s:if test='%{#session.previousYear=="N"}'>
		<s:url id="urlAssignStaffClassSubs"
			action="ajaxDoAssignClassSubjectsToStaff" escapeAmp="false"
			includeParams="all" namespace="/admin">
			<s:param name="tempId" value="%{tempId}" />
			<s:param name="tempString" value="%{tempString}" />
		</s:url>
		<sj:a id="addClass" href="%{urlAssignStaffClassSubs}"
			targets="stepTeacherClasses"></sj:a>
	</s:if>
	<div class="spaceDiv"></div>
	<span class='totalPeriodsCount' id='<s:property value="numberOfDays"/>'></span>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Class
					</th>
					<th>
						Subject
					</th>
					<th>
						Max periods per week
					</th>
					<th>
						Periods handle per week
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="objectList">
						<tr class="staffSubjectsData">
							<td id='<s:property value='classTeacherId'/>' class='classTeacherId'>
								<s:property value="classSection" />
							</td>
							<td id='<s:property value='periodsPerWeek'/>' class='periodsPerWeek_<s:property value="classTeacherId"/>'>
								<s:property value="subjectName" />
							</td>
							<td>
								<s:property value="periodsPerWeek" />
							</td>
							<td>
								<sj:textfield name="periodsHandleCount"
									id="periodsHandleCount_%{classTeacherId}"
									onchange="caluclateTotalPeriods(this);"
									onkeypress="return onlyNumbers(event);"
									cssClass="numeric form-control input-small periodsHandled"
									maxlength="2"></sj:textfield>
							</td>
							<td>
								<s:if test='%{#session.previousYear=="N"}'>
									<s:url id="removeClassTeacherClassDetails"
										action="ajaxRemoveStaffClassSubjectDetails"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="tempId1" value="%{classTeacherId}"></s:param>
									</s:url>
									<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'timeTableStaffSubjDetails');"
										id='%{removeClassTeacherClassDetails}'
										title="Delete this Class subject for this staff.">
										<i class="fa fa-times"></i>Delete
							</s:div>
								</s:if>
							</td>
						</tr>  
				</s:iterator>
			</tbody>
		</table>
		<div class="row">
			<div class="col-md-7">
				<div class="form-group">
					<label class="control-label col-md-4">
						Total Periods Handle :
					</label>
					<div class="col-md-4">
						<input type="text" disabled="disabled" id="handlePeriodsCount"
							class=" form-control" />
					</div>
				</div>
			</div>
		</div>&nbsp;
	<div class="spaceDiv"></div>
		<s:if test='%{#session.previousYear=="N"}'>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit   targets="mainContentDiv" value="Submit"
						cssClass="btn blue" formIds="addStaffClassSubjectsSettings"
						onBeforeTopics="staffSubjectSettingsFormValidation" indicator="indicator"/>
				</div>
			</div>
		</s:if>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			No class subjects defined for this staff.
		</div>
	</s:else>
</div>
<script type="text/javascript">
changePageTitle('Staff Subjects');
$(document).ready(function() {
	TableAdvanced.init();
	caluclateTotalPeriods(0);
});
function caluclateTotalPeriods(event) {
	if (isNonEmpty(event)) {
		var handlePeriods = $(event).val();
		if (isNonEmpty(handlePeriods)) {
			var idCont = $(event).attr('id');
			var classTeacherId = idCont.split('_')[1];
			var periodsPerWeek = $('.periodsPerWeek_' + classTeacherId).attr('id');
			if (Number(periodsPerWeek) < Number(handlePeriods)) {
				alert('Periods handle per week of this subject should be less than or equal to ' + periodsPerWeek + '.');
				$(event).val('0');
			}
		}
	}
	var periodsCount = 0;
	$('tr.staffSubjectsData').each(function() {
		if (isNonEmpty($(this).find(".periodsHandled").val()))
			periodsCount += Number($(this).find(".periodsHandled").val());
	});
	$('#handlePeriodsCount').val(periodsCount);
	var totalPeriodsCount = $('span.totalPeriodsCount').attr('id');
	/*Siva  - Commented this code due to showing alert, There is a unnecessary No of days list by checking and using many columns group by. Need to look at the backend queries*/
	/* if (Number(periodsCount) > totalPeriodsCount) {
		alert("This staff can handle "
				+ totalPeriodsCount
				+ " periods per week. But this staff is handling "
				+ periodsCount
				+ " periods per week. Please reduce periods handle per week count.");
	} */
}
function confirmDialogWithTarget(event,classSubjectsDeleteDiv) {
	thishref = $(event).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event).after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax( {
			url : thishref,
			cache : false,
			success : function(html) {
				prdDiv.parent().parent().remove();
				$('div#timeTableStaffSubjDetails').html('<div class="alert alert-success"> <button class="close" data-dismiss="alert"></button> <strong>Staff class subjects deleted successfully. </strong></div>');
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}
</script>
