<%@ include file="/common/taglibs.jsp"%>
   <td colspan="8">
	<s:form id="updateStudentMarks" action="ajaxEditStudentMarks"  theme="css_xhtml"  method="post">
		<s:hidden name="studentId" value="%{studentMarksChanges.student.id}"></s:hidden>
		<s:hidden name="scheduleId" value="%{studentMarksChanges.examSchedule.id}"></s:hidden>
		<s:hidden name="marksChangesId" value="%{studentMarksChanges.id}"></s:hidden>
		<div class="grid_10">
				<div class="grid_3" >
					<b>Student Name:</b>
				</div>
				<div class="grid_4">
					<sj:textfield name="studentMarksChanges.student.account.person.personFullName" cssClass="textfield" id="studentName" disabled="true"></sj:textfield>
				</div>
		</div>
		<div class="grid_2">&nbsp;</div>
			<div class="grid_10">
				<div class="grid_3" >
					<b>Exam Type:</b>
				</div>
				<div>
					<sj:textfield name="studentMarksChanges.examSchedule.examType.examType" id="examType" disabled="true"></sj:textfield>
				</div>
		</div>
		<div class="grid_2">&nbsp;</div>
		<div class="grid_10">
				<div class="grid_3" >
					<b>Student Marks:</b>
				</div>
				<div class="grid_5">
				<sj:textfield name="studentMarks" id="studentMarks" required="true" cssClass="required textfield"/>
				</div>
			</div>
		<div class="grid_4" style="float: right;">
			<div class="grid_2">
				<sj:submit   cssClass="submit small" value="Submit"
					indicator="indicator" targets="classTeacherContent" onClickTopics="sudentMarksValidation"/>
			</div>
			<div class="grid_2">
			  	 <s:url id="doCancelStudentMarksChanges" action="ajaxDoEditStudentMarks"
	                            includeParams="all" escapeAmp="false">
                 </s:url>
                 <sj:a href="%{doCancelStudentMarksChanges}" targets="doViewStudentMarksChanges%{studentMarksChanges.id}" cssClass="cancelButton"
                         onCompleteTopics="doViewStudentMarksChanges" >
                         Cancel
                 </sj:a>
			</div>
		   </div>			
		</s:form>
		</td>
<script type="text/javascript">
$(document).ready(function() {
	document.title = 'Update Student Marks';
	$.subscribe('sudentMarksValidation', function(event, data) {
		if ($('#updateStudentMarks').valid()){
			var maxMarks=<s:property value='studentMarksChanges.examSchedule.examType.maxMarks'/>
			if($("#studentMarks").val() > maxMarks){
				alert("Obtained Marks are more than maximum marks.");
				return false;
			}
			return true;
		}
		else
			return false;
	});
});
</script>
