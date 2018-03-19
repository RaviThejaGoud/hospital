<%@ include file="/common/taglibs.jsp"%>
<div class="row">
<div class="col-md-12">
	<div class="form-group form-horizontal">
		<label class="col-md-2 control-label" style="width: 112px;"> 
			profile Search :
		</label>
		<div class="col-md-9">
			<div class="radio-list">
				<label class="radio-inline">
					<input type="radio" class="radio studentStaff" value="ST"						
						name="eventBelongs" checked="checked">
					Student 
				</label>
			<s:if test='%{user.isOnlySchoolHod=="Y"}'>
				<label class="radio-inline">
					<input type="radio" class="radio studentStaff" value="S"					
						name="eventBelongs">
					Staff
				</label>
			</s:if>
			</div>
		</div>
		<div class="spaceDiv"></div>
		<div id="selectToOtherProfile">
			<jsp:include page="/WEB-INF/pages/staff/myClassmatesList.jsp"></jsp:include>
		</div>
	</div>
</div>
</div>
<div id="profileId">
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script  type="text/javascript">
changePageTitle('Staff Leave Details');
$(document).ready(function() {
	var studyClassId = $("select#dropDownId").val();
	getStudentDetails(studyClassId,'ST');
});

$('input.studentStaff').click(function(){
	getStudentDetails($("select#dropDownId").val(),$(this).val());
});

$("select#dropDownId").change(function(){
	getStudentDetails($(this).val(),$('input.studentStaff:checked').val());
});


function getStudentDetails(studyClassId,studentOrStaff) {
	if(studentOrStaff=="undefied"){
		studentOrStaff=$('input.studentStaff:checked').val();
	}
	var url='';
	if(studentOrStaff=="ST"){
		url=jQuery.url.getChatURL("/staff/ajaxGetStudentsList.do");
	}
	else{
		url=jQuery.url.getChatURL("/staff/ajaxDoGetStaffProfile.do");
	}
	if (isNonEmpty(studyClassId)) {
		var params = "tempId1=" + studyClassId + "&frequency="+studentOrStaff;
		$('#classResultsDiv').html(
						'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$("#selectToOtherProfile").hide();
		$.ajax( {
			url : url,
			cache : false,
			data : params,
			success : function(html) {
				$("#selectToOtherProfile").html(html);
				$("#selectToOtherProfile").show();
			}
		});
	}
}
</script>