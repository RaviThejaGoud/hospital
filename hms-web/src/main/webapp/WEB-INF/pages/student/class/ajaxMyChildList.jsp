<%@ include file="/common/taglibs.jsp"%>
<s:if
	test='%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}'>
	<s:if test='%{viewStudentPersonAccountDetailsList.size > 1}'>
		<div class="row">
			<div class="form-group form-horizontal" align="center">
				<label class="control-label col-md-3">
					Student Name :
				</label>
				<div class="col-md-3">
					<s:select id="sectionId" list="viewStudentPersonAccountDetailsList"
						listKey="studentId" label="Student Name" cssClass="form-control"
						listValue="idAndName" name="tempId1" theme="simple"
						onchange="javascript:getStudentAttendanceDetails(this.value);" />
				</div>
			</div>
		</div>
	</s:if>
</s:if>
<script type="text/javascript">
	function getStudentAttendanceDetails(studentId){
		if(isNonEmpty(studentId)){
			$('#attendanceConntent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
					url : jQuery.url.getChatURL("/student/ajaxStudentAttendanceDetails.do?tempId1="+studentId),
					cache : true,
					success : function(response) {
					 	if(isNonEmpty(response)){
					 		$('#attendanceConntent').html(response);
						}
					}
				});
		}
	}
</script>