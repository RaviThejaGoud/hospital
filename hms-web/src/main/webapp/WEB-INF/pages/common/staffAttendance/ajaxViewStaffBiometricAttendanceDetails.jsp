<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test='%{academicYear.useBiometricForStaff == "Y"}'>
	<s:if test='%{#session.previousYear=="N"}'>
		<s:if test='%{startDate != null && endDate != empty}'>
			<s:form id="addStaffAttendance" action="ajaxCreateStaffAttendance"
				theme="simple" cssClass="form-horizontal" name="staffAttendanceform"
				namespace="/admin">
				<s:hidden name="anyTitle" id="attendanceData" />
				<s:hidden name="anyId" value="staffBiometric"></s:hidden>
				<s:hidden name="attendanceDate" value="%{attendanceDate}"></s:hidden>
				<div class="form-body" id="emailSmsEnable">
					<s:if test="%{customer.checkMobileService == false}">
						<p id="enableServiceId">
							<span class="label label-danger">ALERT :</span>&nbsp;&nbsp;
							<b>SMS service disabled, enable service. <s:url
									id="urlAttendanceLink" action="ajaxDoSchoolInformation"
									includeParams="all" namespace="/admin" /> <sj:a
									href="%{urlAttendanceLink}" targets="mainContentDiv"
									indicator="indicator" cssClass="enableEmailService">Enable
						Service </sj:a> </b>
						</p>
					</s:if>
					<s:if
						test="%{viewStaffPersonAccountDetailsList != null && !viewStaffPersonAccountDetailsList.isEmpty()}">
						<div class="panel-body">
							<div class="col-md-1">
								<span class="label label-danger"> NOTE : </span>
							</div>
							<div class="col-md-10">
								<ul>
									<li>
										You can not submit attendance for more than two previous days
										and future days!
									</li>
									<li>
										You can Un check to make the staff as absent.
									</li>
								</ul>
							</div>
						</div>
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2">
							<thead>
								<tr>
									<th>
										Role
									</th>
									<th>
										User Name
									</th>
									<th>
										Staff Name
									</th>
									<th>
										Late Time ( HH : MM : SS )
									</th>
									<th>
										Attendance
									</th>
							</thead>
							<tbody>
								<s:iterator value="viewStaffPersonAccountDetailsList">
									<tr>
										<td>
											<s:property value="roleDescription" />
										</td>
										<td>
											<s:property value="username" />
										</td>
										<td>
											<s:property value="firstName" />
											&nbsp;
											<s:property value="lastName" />
										</td>
										<td>
											<s:property value="staffLateTime" />
										</td>
										<td>
											<s:if test='%{present}'>
												<div class="checkbox-list">
													<label class="checkbox-inline">
														<s:checkbox cssClass="checkbox" title="%{accountId}"
															id="%{accountId}" fieldValue="true" value="true"
															name="%{accountId}_checkbox"></s:checkbox>
													</label>
												</div>
											</s:if>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="space10"></div>
						<div class="form-actions fluid">
							<div class="col-md-offset-2 col-md-9">
								<sj:submit targets="staffBiometricAttendanceDiv" value="Save"
									id="submitButton1" cssClass="submit btn blue" validate="true"
									formIds="addStaffAttendance" indicator="indicator"
									onBeforeTopics="staffBiometricValidation" />
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there is no data for selected date.
						</div>
					</s:else>
				</div>
			</s:form>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Please add academic planner details. Then only you can submit
				attendance for staff and student.
				<s:url id="urlSendSmsLink" action="ajaxAcademicSchoolSettings"
					includeParams="all" namespace="/admin" />
				<sj:a href="%{urlSendSmsLink}" targets="mainContentDiv"
					indicator="indicator" cssClass="academicPlannerId">Click here</sj:a>
				to add academic planner details.
			</div>
		</s:else>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You can't submit attendance for past academic year.
		</div>
	</s:else>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
$.destroyTopic('staffBiometricValidation');
 $("input:checkbox, input:radio").uniform();
 // TableAdvanced.init();
	$.subscribe('staffBiometricValidation', function(event, data) {
	     var jsona = [];
	     var status='';
	     var accountId;
	     if ($("input.checkbox:unchecked").length > 0) {
			$('input.checkbox:unchecked').each(function () {
				   accountId=$(this).attr('title');
				    if ($(this).is(':unchecked')) {
			           status='A';
			        }
					jsona.push( {
						"status" : status,
						"accountId" : accountId
					});
			});
		if(isNonEmpty(jsona) && jsona.length > 0){
			var jsono={"attendanceDate" : $('#attendanceDate').val(), "data" : jsona}
			$('#attendanceData').val(JSON.stringify(jsono));
			return true;
		}else{
			alert('Something gone wrong! Unable to read the response. Please reload the screen and try or contact system administrator');
			event.originalEvent.options.submit=false;
		}
		} else{
			alert("Please uncheck at least one staff.");
			event.originalEvent.options.submit=false;
		} 
	});
});
$('a.enableEmailService').click(function(){
	window.location.hash="target=ES.ajaxify AMCS";
	window.location.reload();
});
</script>
 
