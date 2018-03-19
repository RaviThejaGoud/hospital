<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Staff Class Subjects
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:url id="urlAssignStaffClassSubs"
									action="ajaxDoAssignClassSubjectsToStaff" escapeAmp="false"
									includeParams="all" namespace="/admin">
									<s:param name="tempId" value="%{tempId}" />
									<s:param name="tempString" value="%{tempString}" />
								</s:url>
								<sj:a id="addClass" href="%{urlAssignStaffClassSubs}"
									targets="stepTeacherClasses" data-toggle="tab">Assign Class Subjects</sj:a>
							</s:if>
						</li>
						<li class="active">
							<s:url id="urlClassSubjects" action="ajaxViewTeacherSubjects"
								namespace="/admin" />
							<sj:a href="%{urlClassSubjects}" targets="mainContentDiv"
								data-toggle="tab">
									Staff Class Subjects</sj:a>
						</li>
					</ul>
					<div id="stepTeacherClasses" class="tab-content">
					<%@ include file="/common/messages.jsp"%>
						<s:if test="%{tempList != null && !tempList.isEmpty()}">
							<s:form id="addStaffClassSubjectsSettings"
								action="ajaxAddStaffHandledPeriodsCount" method="post" cssClass="form-horizontal"
								theme="simple" namespace="/admin">
								<s:hidden name="tempString" cssClass="tempString"></s:hidden>
										<div class="form-group">
											<label class="control-label col-md-2">
												Select Teacher :
											</label>
											<div class="col-md-3">
												<s:select list="tempList" listKey="staffId"
													listValue="staffFullNameWithRole"
													cssClass="required form-control" 
													id="staffId" name="tempId"
													onchange="javascript:getStaffSubjects(this.value);">
												</s:select>
											</div>
										</div>
								<div class="spaceDiv"></div>
								<div id="timeTableStaffSubjDetails"></div>
							</s:form>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Staffs are not available.
							</div>
						</s:else>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Subject Settings');
	var staffId = $('#staffId').val();
	getStaffSubjects(staffId);
	$.subscribe('staffSubjectSettingsFormValidation', function(event, data) {
		var classTeacherId = '';
		var periodsHandled = '';
		var jsonObj = [];
		$('tr.staffSubjectsData').each(function() {
			classTeacherId = $(this).find("td.classTeacherId").attr('id');
			periodsHandled = $(this).find(".periodsHandled").val();
			if (isNonEmpty(classTeacherId)) {
				jsonObj.push( {
					"classTeacherId" : classTeacherId,
					"periodsHandled" : periodsHandled
				});
			}
		});
		$('.tempString').val(JSON.stringify(jsonObj));
	});
});

function getStaffSubjects(staffId) {
	var staffName = $("#staffId option:selected").text();
	if (isNonEmpty(staffId) && isNonEmpty(staffName)) {
		$('#timeTableStaffSubjDetails')
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId=" + staffId + "&tempString=" + staffName;
		$.ajax( {
			url : jQuery.url
					.getChatURL("/admin/ajaxGetStaffSubjectsDetails.do"),
			cache : true,
			data : pars,
			success : function(response) {
				$('#timeTableStaffSubjDetails').html(response);
			}
		});
	} else {
		$('#timeTableStaffSubjDetails').html("<div>Please select staff.</div>");
	}
}
</script>