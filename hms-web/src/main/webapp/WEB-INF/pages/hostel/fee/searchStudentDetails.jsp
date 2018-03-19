<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="searchDiv grid_16 commonFormTabs">
	
	<div class="grid_15">
		<div class="links">
			<span class="searchSpan right">Search Student/Staff</span>
		</div>
	</div>
	<div class="hideSearchStudentBody">
		<div class="grid_9 alpha">
		<%@ include file="/common/messages.jsp"%>
			<div class="grid_4 alpha">
				<label class="labelRight">
					Select Staff/Student Type:
				</label>
			</div>
			<div class="grid_5">
				<div class="grid_2">
					<input type="radio" value="Student" id="Student"
						onclick="changeStudnetOrStaff(this.value);" name="addStaffToHostel" class="radio" checked="checked"> Student
				</div>
				<div class="grid_2">
					<input type="radio" value="Staff" id="Staff"
						onclick="changeStudnetOrStaff(this.value);" name="addStaffToHostel" class="radio"> Staff
				</div>

			</div>
		</div>
		<div class="grid_13">
			<div id="Staffs">
				<s:form id="searchStaffByNumber"
					action="ajaxSearchStaffByCriteria" theme="css_xhtml" namespace="/hostel">
					<s:hidden name="studentOrStaff" value="Staff"></s:hidden>
					<fieldset>
						<h1>
							Search Staff
						</h1>
						<p>
							Key at least 3 chars and hit submit to get closer match.
						</p>
						<div class="grid_8">
							<div class="grid_5">
								<sj:textfield name="anyTitle" id="staffKeyword"
									value="Staff First or Last Name."
									onfocus="clearThis(this)" targets="text" 
									cssClass="textfield large required defaultValue"
									required="true"></sj:textfield>
							</div>
							<div class="grid_3">
							<sj:submit   targets="searchStaffList" value="Find Staff"
								cssClass="submit long" indicator="indicator" cssStyle="float:none;"
								onBeforeTopics="searchStaffForm" formIds="searchStaffByNumber"
								resetForm="true" />
							</div>
						</div>
					</fieldset>
				</s:form>
					<form method="post" id="printReport"
						action="javaScript:printHostelPreview('<s:property value="anyId" />','<s:property value="todayDate"/>','<s:property value="tempId" />')"
						style="display: none;">
					</form>
			</div>
			<div id="Students">
				<s:form id="searchStudentByNumber"
					action="ajaxSearchStudentFeeByCriteria" theme="css_xhtml" namespace="/hostel">
					<s:hidden name="studentOrStaff" value="Student"></s:hidden>
					<fieldset>
						<h1>
							Search Student
						</h1>
						<p>
							Key at least 3 chars and hit submit to get closer match.
						</p>
						<div class="grid_8">
							<div class="grid_5">
								<sj:textfield name="anyTitle" id="rollNumber"
									value="Student First or Last Name."
									onfocus="clearThis(this)" targets="text"
									cssClass="textfield large required defaultValue"
									required="true"></sj:textfield>
							</div>
							<div class="grid_3">
								<sj:submit   targets="searchStudentsList" value="Find Student"
								cssClass="submit long" indicator="indicator" cssStyle="float:none;"
								onBeforeTopics="searchStudentForm" formIds="searchStudentByNumber"
								resetForm="true" />
							</div>
						</div>
					</fieldset>
				</s:form>
					<form method="post" id="staffPrintReport"
						action="javaScript:printSraffHostelPreview('<s:property value="anyId" />','<s:property value="todayDate"/>','<s:property value="tempId" />')"
						style="display: none;">
					</form>
			</div>
		</div>
	</div>
</div>
<div id="searchStaffList" class="grid_15"></div>
<div id="searchStudentsList" class="grid_15"></div>
<script type="text/javascript">
$('#financeHome').addClass('current');
$(document).ready(function() {
	$('#Staffs').hide();
	$('.links').hide();
	$(".hideSearchStudentBody").show();//hide the all of the element with class msg_body
	});
$(".links").click(function()//toggle the componenet with class msg_body
		{
			if ($(".hideSearchStudentBody").is(":hidden"))
				$("span.searchSpan").attr("style", "color:#000000");
			else
				$("span.searchSpan").attr("style", "color:#008EE8");

			$(".hideSearchStudentBody").slideToggle(600);
		});
function clearThis(target) {
	$(target).val = "";
}
function changeStudnetOrStaff(staffType) {
	if (staffType == 'Staff') {
		$('#searchStudentsList').hide();
		$('#searchStaffList').show();
		$('#searchStaffList').html('');
		$('#Students').hide();
		$('#Staffs').show();
	} else {
		$('#searchStaffList').hide();
		$('#searchStudentsList').show();
		$('#searchStudentsList').html('');
		$('#Students').show();
		$('#Staffs').hide();
	}
}
</script>