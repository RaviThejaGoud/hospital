<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
	<div class="row">
		<s:form action="#" theme="simple" id="studentsList" method="post">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-5">
						Select Class :
					</label>
					<div class="col-md-7">
						<s:select list="studyClassList" listKey="id" listTitle="className"
							listValue="classAndSection" id="studyClassId" name="className"
							onchange="javascript:getAjaxDoStudentFeeDues(this.value);"
							headerKey="0" headerValue="- Select -" cssClass="form-control">
						</s:select>
					</div>
				</div>
			</div>
		</s:form>
		<div class="col-md-1">
			(OR)
		</div>
		<s:form id="studentRollNumber"
			action="ajaxGetSearchStudentPendingDues" theme="simple" method="post"
			namespace="/admin">
			<div class="col-md-7">
				<div class="form-group">
					<label class="control-label col-md-3">
						Search Student :
					</label>
					<div class="col-md-8">
						<div class="input-group">
							<sj:textfield name="selectedId" id="rollNumber"
								cssClass="form-control  medium"
								placeholder="Student First or Last Name"></sj:textfield>
							<span class="input-group-btn"> <sj:submit
									targets="studentDuesList" value="Find Student"
									indicator="indicator" cssClass="submitBt btn blue"
									validate="true" onBeforeTopics="searchTCStudentForm" /> </span>
						</div>
						<span class="help-block"> (Type at least 3 chars and hit
							'Find Student'.) </span>
					</div>
				</div>
			</div>
		</s:form>
	</div>
	<div id="studentDuesList"></div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no TC settings . please
		<a href="#" onclick="javascript:tcSettings()"><font color="red"> Add TC settings </font></a>
	</div>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('searchTCStudentForm');
});
 $.subscribe('searchTCStudentForm', function(event, data) {
	var name = $('#rollNumber').val();
	if (name == null || name == '' || name == 'Student First or Last Name') {
		alert("Please type first name or last name.");
		 event.originalEvent.options.submit=false;
	}
	else if(name.length < 3){
		alert("Please enter minimum 3 characters.");
		//$('#rollNumber').val('Student Admission Number');
		 event.originalEvent.options.submit=false;
	}
});
		 
changePageTitle("TC Generation");
	function getAjaxDoStudentFeeDues(studyClassId) {
	var className = $('select#studyClassId').find("option:selected").attr("title");
	if(isNonEmpty(className)){
		if(studyClassId == "" || studyClassId == 0){
			$("#studentDuesList").hide();
		}
		else{
			className = className.replace("&", "$"); 
			var pars = "studyClassId=" + studyClassId+"&tempString='"+className+"'&plTitle=TC";
			$("#studentDuesList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/admin/ajaxGetStudentPendingDues.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#studentDuesList").html(html);
					$("#studentDuesList").show();
				}
			});
		}
	 }
	 else{
	  alert("Please select class.");
	  $("#studentDuesList").html("");
	  return false;
	 }	
	}
function clearText(field){
    	if (field.defaultValue == field.value) field.value = '';
    	else if (field.value == '') field.value = field.defaultValue;
	}
function tcSettings(){
	$('a#doTcSettings').click();
}
</script>
 