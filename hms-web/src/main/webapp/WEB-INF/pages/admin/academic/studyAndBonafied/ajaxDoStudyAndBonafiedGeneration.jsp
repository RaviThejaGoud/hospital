<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
	<span id="certificateType" class="<s:property value='anyTitle'/>"></span>
	<div class="row">
		<s:form action="#" theme="simple" id="studentsList" method="post">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-5">
						Select Class :
					</label>
					<div class="col-md-7">
						<s:select list="studyClassList" listKey="id" listValue="classAndSection" listTitle="className"
							id="studyClassId" name="className"
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
		<s:form id="studentRollNumber" action="ajaxGetSearchStudentPendingSB"
			theme="simple" method="post" namespace="/admin">
			<s:hidden name="description" value="%{anyTitle}"></s:hidden>
			<div class="col-md-7">
				<div class="form-group">
					<label class="control-label col-md-3">
						Search Student :
					</label>
					<div class="col-md-8">
						<div class="input-group">
							<sj:textfield name="selectedId" id="rollNumber"
									cssClass="form-control"
									placeholder="Student First or Last Name"></sj:textfield>
							<span class="input-group-btn">
							<sj:submit  
									targets="studentDuesList" value="Find Student"
									cssClass="submitBt btn blue" indicator="indicator"
									onBeforeTopics="searchTCStudentForm" validate="true"/> </span>
						</div>
						<span class="help-block">(Type at least 3 chars and hit
							'Find Student'.)</span>
					</div>
					<!--<div class="grid_2">
							<sj:submit   targets="studentDuesList" value="Find"
								cssClass="submit" indicator="indicator"
								onClickTopics="searchTCStudentForm" />
						</div>
					-->
				</div>
			</div>
		</s:form>
	</div>
	<div id="studentDuesList"></div>
</s:if>
<s:else>
<div class="alert alert-info">
 Currently there are no  	
 	<s:if test='%{anyTitle == "studyCertificate"}'>
		Study Certificate
	</s:if>
	<s:elseif test='%{anyTitle == "bonafiedCertificate"}'>
		Bonafied Certificate
	</s:elseif>
	<s:elseif test='%{anyTitle == "noDuesCertificate"}'>
		NO Dues Certificate
	</s:elseif>
	<s:elseif test='%{anyTitle == "feeCertificate"}'>
		Fee Certificate
	</s:elseif>
 	settings .  
 	<a href="#" onclick="javascript:studyAndBonafiedSettings()"><font color="red">Click here </font></a>to add settings .
		</div>
 </s:else>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('searchTCStudentForm');
});
 $.subscribe('searchTCStudentForm', function(event, data) {
		var name = $('#rollNumber').val();
		if (name == null || name == '' || name == 'Student First or Last Name') {
			alert("Please enter student student first or last name.");
			event.originalEvent.options.submit=false;
			
		}
		else if(name.length < 3){
			alert("Please enter minimum 3 characters.");
			//$('#rollNumber').val('Student Admission Number');
			event.originalEvent.options.submit=false;
		}
});
		 
changePageTitle("Study And Bonafied Generation");


	function getAjaxDoStudentFeeDues(studyClassId) {
	var className = $('select#studyClassId').find("option:selected").attr("title");
	var certificateType = $('span#certificateType').attr('class');
		if(studyClassId == "" || studyClassId == 0){
			$("#studentDuesList").hide();
		}
		else{
			var pars = "studyClassId=" + studyClassId+"&tempString="+className+"&plTitle="+certificateType;
			$("#studentDuesList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/admin/ajaxGetPendingDuesSBGeneration.do");
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
function clearText(field){
    	if (field.defaultValue == field.value) field.value = '';
    	else if (field.value == '') field.value = field.defaultValue;
	}
function studyAndBonafiedSettings(){
	$('a#addtemplate').click();
}
</script>
 