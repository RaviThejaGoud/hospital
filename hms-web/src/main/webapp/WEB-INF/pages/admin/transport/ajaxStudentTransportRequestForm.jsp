<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{(objectList != null && !objectList.isEmpty())}">
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
							onchange="javascript:getAjaxGenerateRequestForm(this.value);"
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
			action="ajaxDoGetSearchTransportStudent" theme="simple" method="post"
			namespace="/admin">
			<div class="col-md-7">
				<div class="form-group">
					<label class="control-label col-md-3">
						Search Student :
					</label>
					<div class="col-md-8">
						<div class="input-group">
							<sj:textfield name="selectedId" id="studentRollId"
								cssClass="form-control  medium"
								placeholder="Student First or Last Name"></sj:textfield>
							<span class="input-group-btn"> <sj:submit
									targets="tStudentDuesList" value="Find Student"
									indicator="indicator" cssClass="submitBt btn blue"
									validate="true" onBeforeTopics="searchTransportForm" /> </span>
						</div>
						<span class="help-block"> (Type at least 3 chars and hit
							'Find Student'.) </span>
					</div>
				</div>
			</div>
		</s:form>
	</div>
	<div id="tStudentDuesList"></div>
</s:if>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no request form setting. Please 
		<a href="#" onclick="javascript:transportRequestSettings()"><font color="red"> Add transport request form settings </font></a>
	</div>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('searchTransportForm');
});
 $.subscribe('searchTransportForm', function(event, data) {
	var name = $('#studentRollId').val();
	if (name == null || name == '' || name == 'Student First or Last Name') {
		alert("Please type first name or last name.");
		 event.originalEvent.options.submit=false;
	}
	else if(name.length < 3){
		alert("Please enter minimum 3 characters.");
		 event.originalEvent.options.submit=false;
	}
});
		 
	function getAjaxGenerateRequestForm(studyClassId) {
	var className = $('select#studyClassId').find("option:selected").attr("title");
	if(isNonEmpty(className)){
		if(studyClassId == "" || studyClassId == 0){
			$("#tStudentDuesList").hide();
		}
		else{
			var pars = "studyClassId=" + studyClassId;
			$("#tStudentDuesList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/admin/ajaxGenerateRequestForm.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#tStudentDuesList").html(html);
					$("#tStudentDuesList").show();
				}
			});
		}
	 }
	 else{
	  alert("Please select class.");
	  $("#tStudentDuesList").html("");
	  return false;
	 }	
	}
function clearText(field){
    	if (field.defaultValue == field.value) field.value = '';
    	else if (field.value == '') field.value = field.defaultValue;
	}
function transportRequestSettings(){
	$('a#transportRequestSettings').click();
}
</script>
 