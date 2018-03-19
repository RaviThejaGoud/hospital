<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<s:if test="%{(classList != null && !classList.isEmpty())}">
		<s:form action="#" theme="simple" id="studentsList" method="post"
			cssClass="form-horizontal">
			<div class="form-group">
				<label class="control-label col-md-2">
					Select Class :
				</label>
				<div class="col-md-3">
					<s:select list="classList" listKey="id" listValue="description"
						id="classNameId" name="className" cssClass="form-control input-medium"
						onchange="javascript:getAjaxDoStudentFeeDues(this.value);"
						headerKey="0" headerValue="- Select -">
					</s:select>
				</div>
			</div>
		</s:form>
	</s:if>
	<div id="studentDuesList"></div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no 'Hall Ticket' settings . 
		<a href="#" onclick="javascript:htSettings()"><font color="red"> Click here </font></a>to add Hall Ticket settings.
	</div>
</s:else>
<script type="text/javascript">
$.subscribe('searchHTStudentForm', function(event, data) {
	var name = $('#rollNumber').val();
	if (name == null || name == '' || name == 'Please type first name or last name') {
		alert("Please type minmum three chars in first name or last name.");
		return false;
	} else if (name.length < 3) {
		alert("Please enter minimum 3 characters.");
		$('#rollNumber').val('Student Admission Number');
		return false;
	} else
		return true;
});
function getAjaxDoStudentFeeDues(studyClassId) {
	var className = $('#classNameId :selected').text();
	if (studyClassId == "" || studyClassId == 0) {
		$("#studentDuesList").hide();
	} else {
		var pars = "classId=" + studyClassId + "&tempString=" + className+ "&plTitle=HT";
		$("#studentDuesList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/exam/ajaxGetStudentPendingDues.do");
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
function clearText(field) {
	if (field.defaultValue == field.value)
		field.value = '';
	else if (field.value == '')
		field.value = field.defaultValue;
}
function htSettings() {
	$('a#doHallTicketSettings').click();
}
</script>
