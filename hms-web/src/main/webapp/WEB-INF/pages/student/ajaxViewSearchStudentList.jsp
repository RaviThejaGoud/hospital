<%@ include file="/common/taglibs.jsp"%>

<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<s:hidden name="tempString" cssClass='tempString' />
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		<s:iterator value="studyClassList">
			<s:hidden name="sectionAvailableSeatsCount%{id}" value="%{classSectionAvailableSeats}"></s:hidden>
		</s:iterator>
	</s:if>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_7">
		<thead>
			<tr>
				<th>Photo</th>
				<th>Roll No</th>
				<th>Admission No</th>
				<th>Student Name</th>
				<th>Change Class Section</th>
				
			</tr>
		</thead>
		<tbody>
			<s:iterator value="studentsList">
			<s:set name="studentId" value="studId"></s:set>
				<tr class="<s:property value="gender" />statusSex">
				<s:hidden name="studentId%{studId}"></s:hidden>
					<td>
						<s:if test="%{imagePath != null &&  imagePath != ''}">
							<img src='<s:property value='imagePath'/>'
								border="0"  style="height: 50px;width: 50px;" id="studentProfileDiv" />
						</s:if> 
						<s:else>
							<img height="50px;" width="50px"alt="" src="../images/common/photo_notAvailable.jpg">
						</s:else>
					</td>
					<td><s:property value="rollNumber" /></td>
					<td><s:property value="admissionNumber" /></td>
					<td>
					<a data-toggle="modal" href="#showStudenstProfileDiv"
									onclick="javascript:showStudentProfileDetails(<s:property value="studId" />,<s:property value="classSectionId" />);"><s:property value="firstName" /> &nbsp; <s:property
							value="lastName" /></a></td>
					<td>
						<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
							<s:select list="studyClassList" id="%{studId}"
								cssClass="form-control input-medium changeClassSelect"
								name="sectionStudentId%{studId}" listKey="id"
								listValue="classAndSection" theme="simple" headerKey="" headerValue="- Select -"
								onchange='javascript:calculateAvailableSeats(this.value,%{studId});checkStudentFeepaymentAndMarks(this.value,%{studId});'>
							</s:select>
						</s:if>
						<s:else>-</s:else>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
					
					<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
								<sj:submit value="Submit" id="submitButtonMainContent"
						targets="staffList" 
						cssClass="submitBt btn blue" formIds="selectStudentForm"
						onBeforeTopics="classFeeFormValidation" /> 
				</s:if>
		<s:url id="urlMyStudentsLink" action="ajaxGetStudyClassList"
				namespace="/student">
				<s:param name="staff.id" value="0">
				</s:param>
			</s:url>
			<sj:a href="%{urlMyStudentsLink}" targets="mainContentDiv"
				cssClass="btn default"> Cancel</sj:a>
					</div>
				</div>
				
				
			
				
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no students for
		this class.</div>
</s:else>



<script type="text/javascript">

$(document).ready(function() {

TableAdvanced.init();
UIExtendedModals.init();
	
});
	
 
 function calculateAvailableSeats(studyClassId,studentId){
	//$('.submitBt').removeAttr('disabled');
	if(isNonEmpty(studyClassId))
	{
		if("undefined" == $("#"+studyClassId).val() || !isNonEmpty($("#"+studyClassId).val()))
		{
			var sectionAvailableSeatsCount = parseInt($("#sectionAvailableSeatsCount"+studyClassId).val())-1;
			$("#sectionAvailableSeatsCount"+studyClassId).val(sectionAvailableSeatsCount);
			$('#studentId'+studentId).val(studyClassId);
			if(sectionAvailableSeatsCount < 0)
			{
				$("#"+studentId).val("");
				alert("This section does not have capacity to add the students. Please increase the section capacity and try again");
				
				var sectionAvailableSeatsCount = parseInt($("#sectionAvailableSeatsCount"+studyClassId).val())+1;
				$("#sectionAvailableSeatsCount"+studyClassId).val(sectionAvailableSeatsCount);
				
				//$('.submitBt').attr('disabled', 'disabled');
			}
		}
		else
		{
			var previousSelectedStudyClassId = parseInt($("#studentId"+studentId).val());
			var sectionAvailableSeatsCount = parseInt($("#sectionAvailableSeatsCount"+previousSelectedStudyClassId).val())+1;
			$("#sectionAvailableSeatsCount"+previousSelectedStudyClassId).val(sectionAvailableSeatsCount);
			//$('.submitBt').removeAttr('disabled');
		}
	}
	else
	{
		var previousSelectedStudyClassId = parseInt($("#studentId"+studentId).val());
		var sectionAvailableSeatsCount = parseInt($("#sectionAvailableSeatsCount"+previousSelectedStudyClassId).val())+1;
		$("#sectionAvailableSeatsCount"+previousSelectedStudyClassId).val(sectionAvailableSeatsCount);
		//$('.submitBt').removeAttr('disabled');
	}
}
 

 
 $.subscribe('classFeeFormValidation',function(event, data) {
		var studyClassId = '';
		var isAllCorrectMarks=true;
		var studentNames='';
		var jsonObj = [];
		var studentId;
		$('.changeClassSelect').each(function() {
			studyClassId = $(this).val();
			studentId = $(this).attr('id');
			if (isNonEmpty(studentId) && studyClassId) 
			{
				//alert("Student Id:" + studentId + "  Class Id:" + studyClassId);
				 jsonObj.push( {
					"STUDYCLASSID" : studyClassId,
					"STUDENTID" : studentId
				}); 
			}
		});
		//$('.tempString').val(JSON.stringify(jsonObj));
			
			var jsono = {
				"data" : jsonObj
			}
			if (jsonObj.length > 0) {
				$('input[name=tempString]').val(JSON.stringify(jsono));
			} 
			else {
				event.originalEvent.options.submit = false;
				alert('Please change class atleast one student');
			}
		});
 
 
 function checkStudentFeepaymentAndMarks(classId,studentId) {
		
		$.ajax( {
			url : jQuery.url.getChatURL("/student/ajaxCheckStudentFeepaymentAndMarks.do?classId=" + classId +"&tempId="+studentId),
			cache : false,
			dataType : 'json',
			success : function(response) {
				//alert(response.leaveDesc);
				if (isNonEmpty(response)) {
					
					var classId = $('#classId').val();
					//alert(response.feeDesc);
					//alert(response.marksDesc);
					var alertMsg='';
					if(isNonEmpty(response.feeDesc)){
						alertMsg = alertMsg + response.feeDesc;
						//$("#changeClassId").val(classId);
						$("#"+studentId).val("");
						var previousSelectedStudyClassId = parseInt($("#studentId"+studentId).val());
						var sectionAvailableSeatsCount = parseInt($("#sectionAvailableSeatsCount"+previousSelectedStudyClassId).val())+1;
						$("#sectionAvailableSeatsCount"+previousSelectedStudyClassId).val(sectionAvailableSeatsCount);
				}
				if(isNonEmpty(response.marksDesc)){
					alertMsg = alertMsg + response.marksDesc;
					//$("#changeClassId").val(classId);
					$("#"+studentId).val("");
					var previousSelectedStudyClassId = parseInt($("#studentId"+studentId).val());
					var sectionAvailableSeatsCount = parseInt($("#sectionAvailableSeatsCount"+previousSelectedStudyClassId).val())+1;
					$("#sectionAvailableSeatsCount"+previousSelectedStudyClassId).val(sectionAvailableSeatsCount);
				}
				if (isNonEmpty(alertMsg)) {
					alert(alertMsg);
				}
				
				}
			}
		});
	}
 
</script>

