<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="hideSearchStudentBody" id="hideRetunPageDiv">
	<s:form id="searchStudentByNumber" action="ajaxSearchStudentByCriteria"
		theme="simple" cssClass="form-horizontal" namespace="/hostel">
		<s:hidden name="student.accountId" value="%{selectedId}"></s:hidden>
		<s:hidden name="addStaffToHostel" value="Student"></s:hidden>
		<!-- <div class="form-group">
			<label class="control-label col-md-3">
				Select Staff/Student Type :
			</label>
			<div class="radio-list">
				<label class="radio-inline">
					<input type="radio" value="Student" id="Student"
						onclick="changeStaffOrStudent(this.value);"
						name="addStaffToHostel" class="radio" checked="checked">
					Student
				</label>
				<label class="radio-inline">
					<input type="radio" value="Staff" id="Staff"
						onclick="changeStaffOrStudent(this.value);"
						name="addStaffToHostel" class="radio">
					Staff
				</label>
			</div>
		</div> -->
		<div class="form-group">
			<h4 id="Students">
				<label class="col-md-3 control-label">
					Search Student :
				</label>
			</h4>
			<!-- <h4 id="Staffs">
				<label class="col-md-3 control-label">
					Search Staff :
				</label>
			</h4> -->
			<div class="col-md-4">
				<div class="input-group">
					<sj:textfield name="anyTitle" id="anyword"
						value="Enter First or Last Name."
						cssClass="form-control required defaultValue"></sj:textfield>
					<span class="input-group-btn"> <sj:submit  
							targets="searchStudentsList" value="Find" validate="true"
							cssClass="btn blue" onBeforeTopics="searchStudentHostelForm"
							formIds="searchStudentByNumber" /> </span>
				</div>
				<span class="hintMessage">Key at least 3 chars and hit submit
					to get closer match.</span>
			</div>
		</div>
	</s:form>
</div> 
<div id="searchStudentsList"></div>
<script type="text/javascript">
	$(document).ready(function() {
		$.subscribe('searchStudentHostelForm', function(event, data) {
			var name = $('#anyword').val();
			if (isNonEmpty(name)) {
				if (name == "Enter First or Last Name.") {
					alert("Please type first name or last name.");
					event.originalEvent.options.submit=false;
				} 
				else if (name.length < 3) {
					alert("Please enter minimum 3 characters.");
					$('#name').val('Enter First or Last Name.');
					event.originalEvent.options.submit=false;
				}
			} else {
				alert("Please type first name or last name.");
				event.originalEvent.options.submit=false;
			}
		});
		//$('#Staffs').hide();
		$('#searchStudentsList').show();
		$(".hideSearchStudentBody").show();//hide the all of the element with class msg_body
	});
	function changeStaffOrStudent(staffType) {
		$('input#anyword').val("Enter First or Last Name.");
		if("Staff" == staffType) {
			$('#searchStudentsList').hide();
			$('#Students').hide();
			$('#Staffs').show();
		} else if("Student" == staffType){
			$('#searchStudentsList').hide();
			$('#Students').show();
			//$('#Staffs').hide();
		}
	}
</script>
