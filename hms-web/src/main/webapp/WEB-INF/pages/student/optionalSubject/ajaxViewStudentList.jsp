<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{studySubjectList != null && !studySubjectList.isEmpty()}">
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
   
	<s:form action="ajaxAddStudentOptionalSubject" id="addStudentOptionalSubj" method="post" name="addStudentOptionalSubj1" cssClass="form-horizontal" theme="simple" namespace="/student">
			<s:hidden name="classSectionId" ></s:hidden>
			<s:hidden name="tempString" cssClass='tempString' />
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					#Roll
				</th>
				<th>
					#Admission
				</th>
				<th>
					Student Name
				</th>
				<th>
					Optional Subject
				</th>
			</tr>
		</thead>
		<tbody>
		<s:set var="classNameId" value=""></s:set>
			<s:iterator value="studentsList">
				<s:set var="studentDetailsId" value="studId"></s:set>
				<tr class="optionalSubj">
					<td>
						<s:property value="rollNumber" />
					</td>
					<td>
						<s:property value="admissionNumber" />
					</td>
					<td>
						<s:property value="fullName" />
					</td>
					
					<td id="subjId">
						<s:select list="studySubjectList" id="%{studId}"
							cssClass="form-control input-medium optionalSubj" name="optionalSubjectId"
							listKey="subjectId" listValue="subjectName" headerKey="0"
							headerValue="- Select Optional Subject -" theme="simple" />
					</td>
						
					</tr>
				<s:set var="classNameId" value="classId"></s:set>
			</s:iterator>
		</tbody>
	</table>
	<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-3 col-md-12">
							<sj:submit value="Submit" id="submitButtonMainContent"
								targets="viewStudentsList" indicator="indicator"
								cssClass="submitBt btn blue" formIds="addStudentOptionalSubj"
								onBeforeTopics="studentOptionlaSubjFormValidation" />
							
							
						</div>
					</div>
				</div>
		</s:form>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently no student to this study class.
	</div>
</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		Selected class don't have any optional subject. Please assign optional subject to class section.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	 	//TableAdvanced.init();
		UIExtendedModals.init();
		var classSectionId = $('#classSectionId').val();
		//alert(classSectionId);
		if(classSectionId >0){
			var feeURL = jQuery.url.getChatURL("/student/ajaxEditStudyClassWiseStudentOptionalSubj.do?classSectionId="+ classSectionId);
			$.ajax( {
				url : feeURL,
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (response.data) {
						var data = response.data;
						var studentId;
						var optionalSubjectId;
						var optionalSubjMarksCount;
						var inputObj;
						//alert(data.length);
						if (data.length >= 1) {
							for ( var i = 0; i < data.length; i++) {
								studentId = data[i].studentId;
								optionalSubjectId = data[i].optionalSubjectId;
								optionalSubjMarksCount = data[i].optionalSubjMarksCount;
								//alert("studentId:"+studentId+" optionalSubjectId :"+optionalSubjectId+" optional subject marks count :"+optionalSubjMarksCount)
								inputObj = $('select[id^=' + studentId + ']');
								if (inputObj) {
									inputObj.val(optionalSubjectId);
									inputObj.attr('id',studentId);
									if (optionalSubjMarksCount != 0) {
										inputObj.attr('disabled',true);
									}
								}
							}
						}
					}
				}
			});
		}
		$.subscribe('studentOptionlaSubjFormValidation',function(event, data) {
			var fieldErrorString = '';
			var studentId = '';
			var subjectId = '';
			var jsonObj = [];
			var objIds;
			var allids;
			$("tr.optionalSubj").each(function() { 
				subjectId = $(this).find("td#subjId").find("select.optionalSubj").val();  
				studentId = $(this).find("td#subjId").find("select.optionalSubj").attr("id"); 
				if (isNonEmpty(subjectId)) {
					jsonObj.push( {
							"studentId" : studentId,
							"optionalSubjId" : subjectId
					});
					$('#submitButtonMainContent').val('Saving...');
					$('#submitButtonClassFee').val('Saving...');
				}
			});
			var jsono = {
				"data" : jsonObj
			}
			if (jsonObj.length > 0 ) {
				$('input[name=tempString]').val(JSON.stringify(jsono));
			} else {
				event.originalEvent.options.submit = false;
				alert('Please select optinal subject atleast one student.');
			}
		});
});
</script>
