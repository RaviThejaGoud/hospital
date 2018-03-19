<%@ include file="/common/taglibs.jsp"%>
<div class="row">
<div class="col-md-12">
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<s:if test="%{studentsList.size > 1}">
		<div class="form-group form-horizontal">
			<label class="control-label col-md-3">
				Student Name :
			</label>
			<div class="col-md-5">
				<s:select id="sectionId" list="studentsList"
					listKey="classSectionId" cssClass="form-control"
					listValue="studentNameAndUserName" name="anyId" theme="simple"
					onchange="javascript:getStudentExamSchedules(this.value);" />
			</div>
		</div>
		<div class="spaceDiv"></div>
		<div class="spaceDiv"></div>
		<div class="spaceDiv"></div>
	</s:if>
</s:if>
<s:if test='%{user.isSchoolTeacher=="Y"}'>
	<s:if test="%{studyClassList.size > 1}">
		<div class="form-group form-horizontal">
			<label class="control-label col-md-2">
				Select Class :
			</label>
			<div class="col-md-3">
				<s:select list="studyClassList" listKey="id"
					listValue="classAndSection" id="dropDownIds"			
					cssClass="form-control input-medium required" 
					name="anyId" onchange="javascript:getStudentExamSchedules(this.value);" >
				</s:select>
			</div>
		</div>
		<div class="spaceDiv"></div>
		<div class="spaceDiv"></div>
		<div class="spaceDiv"></div>
	</s:if>
</s:if>
	<div id="examSchedulesContent">
		<jsp:include
			page="/WEB-INF/pages/exam/ajaxUpcomingExamSchedulesInfo.jsp"></jsp:include>
	</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var studyClassIds = $("select#dropDownIds").val();
	getStudentExamSchedules(studyClassIds);
});
	function getStudentExamSchedules(classSectionId){
		if(isNonEmpty(classSectionId)){
			$('#examSchedulesContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
					url : jQuery.url.getChatURL("/student/ajaxStudentsUpcomingExamSchedulesDetails.do?ClassSectionId="+classSectionId),
					cache : true,
					success : function(response) {
					 	if(isNonEmpty(response)){
					 		$('#examSchedulesContent').html(response);
						}
					}
				});
		}
	}
</script>
