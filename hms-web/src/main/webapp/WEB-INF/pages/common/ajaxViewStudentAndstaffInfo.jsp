<%@ include file="/common/taglibs.jsp"%>
<s:form id="searchStudentByNumber" action="#" theme="simple"
	cssClass="form-horizontal">
	<div class="row">
		<div class="col-md-5">
			<div class="form-group">
				<label class="control-label col-md-4">
					Select Class :
				</label>
				<div class="col-md-8">
					<s:select list="studyClassList" id="classId" name="classId"
						listKey="id" listValue="classAndSection" headerKey=""
						headerValue="- Select Class -"
						cssClass="form-control input-medium" theme="simple"
						onchange="javascript:getStudentsByClasId(this.value);">
					</s:select>
				</div>
			</div>
		</div>
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Select Student :
					</label>
					<div class="col-md-8">
						<s:select list="studentsList" id="studentName" name="anyId"
							listKey="id" listValue="studentName" headerKey=""
							headerValue="- Select Student -"
							cssClass="form-control input-medium" theme="simple"
							onchange="javascript:getStudentDetails(this.value);">
						</s:select>
					</div>
				</div>
			</div>
		</s:if>
	</div>
	<s:if
		test="%{(studentVo == null || studentVo == empty) || (studentsList == null || studentsList.isEmpty())}">
		<div>
			<br />
			<span class="label label-danger">NOTE :</span> Currently there are no
			students assign to this particular class section.
		</div>
	</s:if>
</s:form>

<div id="viewStudentDetailsDivId">
<%@ include file="/common/messages.jsp"%>
<jsp:include page="/WEB-INF/pages/common/ajaxViewStudentDetails.jsp" />
</div>
			
<script type="text/javascript">
/* $(document).ready(function() {
	$('li#attendanceDiv  a').click();
}); */
function getStudentsByClasId(classId) {
	var params = "classId=" + classId;
	$('#staffList').html(
					'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : jQuery.url.getChatURL("/student/ajaxViewStudentAcademics.do"),
		cache : true,
		data : params,
		success : function(response) {
			$('#staffList').html(response);
		}
	});
}
function getStudentDetails(studId) {
	var classId = $('#classId').val();
	if (isNonEmpty(classId)) {
		var params = "studentId=" + studId + "&classId=" + classId;
		$('#viewStudentDetailsDivId').html(
						'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/student/ajaxViewStudentDetails.do"),
			cache : true,
			data : params,
			success : function(response) {
				$('#viewStudentDetailsDivId').html(response);
			}
		});
	}
	else
	{
	  alert("Sorry we didn't recognize the class, please select class again. Thank you");
	}
}


function checkStudentFeepaymentAndMarks(classId) {
	
	
	$('#studentSubmitDiv').attr('disabled', 'disabled');
	
	var studentId = $('#studentName').val();
	
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
					$("#changeClassId").val(classId);
			}
			if(isNonEmpty(response.marksDesc)){
				alertMsg = alertMsg + response.marksDesc;
				$("#changeClassId").val(classId);
			}
			if (isNonEmpty(alertMsg)) {
				alert(alertMsg);
			}
			$('#studentSubmitDiv').removeAttr('disabled');
			
			}
		}
	});
}
/*function checkTc(academicYearId,accountId) {
	if (isNonEmpty(accountId)) {
		var tcURL = jQuery.url.getChatURL("/reports/ajaxCheckTc.do?academicYearId="+ academicYearId+"&accountId="+accountId);
		$.ajax( {
				url : tcURL,
				cache : false,
				dataType : 'json',
				success : function(response) {
				if(isNonEmpty(response)){
				   if(isNonEmpty(response.data)){
				       alert("tc already generated");
				   }
				}else{
				    var answer = confirm("tc generate only one time");
					if (!answer) {
						$('#tcGeneration').attr("href", "javascript:void(0);");
					} else {
						$("myform").show();
						document.myform.submit();
					}
				}
			}
		});
		
	
	}
}*/
/*function checkTc(academicYearId,accountId) {
			$("myform").show();
			document.myform.submit();
}*/
</script>