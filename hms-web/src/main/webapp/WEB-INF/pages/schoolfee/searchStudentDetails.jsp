<%@ include file="/common/taglibs.jsp"%>
	<jsp:include page="/common/messages.jsp" />
	<div class="hideSearchStudentBody">
		<div id="staffList">
			<div class="form-group form-horizontal">
				<label class="col-md-2 control-label">
					<strong>Search Student By :</strong>
				</label>
				<div class="col-md-9 radio-list">
					<label class="col-md-2 radio-inline">
					<input type="radio" name="SelectType"  value="Select Class" onclick="handleClick(this.value);" checked="checked">
					 	Class & Section </label>
					 <label class="col-md-2 radio-inline">
					<input type="radio" name="SelectType"  value="Search Student" onclick="handleClick(this.value);"> 
						Student Name </label>
						<label class="col-md-3 radio-inline">
					<input type="radio" name="SelectType"  value="Search Admission Number" onclick="handleClick(this.value);">
						 Admission Number </label>
				</div>
			</div>
			<br/><br/>
			<div class="searchDiv" id="selectserchClass">
				<s:form id="selectStudentForm" action="#" theme="simple" cssClass="form-horizontal">
					<div class="form-group">
						<label class="col-md-3 control-label">
								 Select Class : 
						</label>
						<div class="col-md-9">
							<s:select list="studyClassList" id="className" cssClass="form-control input-medium"
								name="classSectionId" listKey="id" listValue="classAndSection"
								headerKey="" headerValue="- Select Class -" theme="simple"
								onchange="javascript:getAjaxDoGetStudent(this.value);" />
						</div>
					</div>
				</s:form>
			</div>
			<div class="searchDiv" id="searchStudent" style="display: none;" >
				<s:form id="searchStudentByNumber" action="ajaxSearchStudentByCriteria" theme="simple" cssClass="form-horizontal" namespace="/schoolfee">
					<div class="form-group">
					<div class="col-md-3 ">
						&nbsp;
					</div>
						<div class="col-md-5">
							<div class="input-group">
								<sj:textfield name="anyTitle" id="rollNumber"
									placeholder="Student First or Last Name" 
									cssClass="form-control" />
								<span class="input-group-btn"> <sj:submit   targets="searchStudentsList" value="Find Student" validate="true"
									cssClass="submitBt btn blue long"  
									cssStyle="float:none;" onBeforeTopics="searchStudentForm"
									formIds="searchStudentByNumber"  /></span> 
							</div>
							<span class="help-block">(Key at least 3 chars and hit
								Find Student to get closer match.)</span>
						</div>
					</div>
				</s:form>
			</div>
			<div class="searchDiv" id="searchAdmissionNumber" style="display: none;" >
				<s:form id="searchStudentByAdmissionNumber" action="ajaxSearchStudentByCriteria" theme="simple" cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-md-3 ">
							&nbsp;
						</div>
						<div class="col-md-5">
							<div class="input-group">
								<sj:textfield name="selectedId" id="admissionNumber"
								cssClass="form-control" 
								 placeholder="Student Admission Number"></sj:textfield>
								<span class="input-group-btn"><sj:submit   targets="searchStudentsList" value="Find Student" validate="true"
								cssClass="submitBt btn blue long" indicator="indicator"
								cssStyle="float:none;"
								onBeforeTopics="searchStudentAdmissionForm"
								formIds="searchStudentByAdmissionNumber" /></span>
							</div>
							<span class="help-block">(Type admission number and hit
								Find Student.)</span>
						</div>
					</div>
				</s:form>
			</div>
			<div id="myStudentList"></div>
		</div>
		<!-- here empId is a paid amount -->
		<s:if test='%{studentPayment.paymentType!="CL" }'>
		<form method="post" id="printReport"
			action="javaScript:printPreview('<s:property value="studentNumber" />','<s:property value="todayDate"/>','<s:property value="quizId" />','<s:property value="empId" />','payandprint','<s:property value="tempId1" />','<s:property value="alertSendType"/>')"
			style="display: none;">
		</form>
		</s:if>
		<form method="post" id="printFutureAcademicReport"
			action="javaScript:printFutureAcademicPaymentPreview('<s:property value="anyId" />','<s:property value="todayDate"/>','<s:property value="tempId2" />','<s:property value="tempId1" />','<s:property value="feePaymentType" />','<s:property value="empId" />','payandprint','<s:property value="alertSendType"/>')"
			style="display: none;">
		</form>
		<form method="post" id="printFineFeeReport" action="javaScript:printStudentFineFeeInvoice('<s:property value="anyId" />','<s:property value="tempId2" />','<s:property value="empId" />','<s:property value="paymentDateStr" />')" style="display: none;"></form>
		
		<s:if test='%{studentPayment.paymentType=="CL" }'>
			<form method="post" id="printChallana"
				action="javaScript:printChallanaPreview('<s:property value="studentNumber" />','<s:property value="todayDate"/>','<s:property value="quizId" />','<s:property value="empId" />','payandprint','<s:property value="tempId1" />','<s:property value="alertSendType"/>')"
				style="display: none;">
			</form>
		</s:if>
		
	</div>
<div id="searchStudentsList"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	 $("input:checkbox, input:radio:not('.toggle')").uniform();
	$(".hideSearchStudentBody").show();//hide the all of the element with class msg_body
		var selected = $('input[name=SelectType]:radio:checked').val();
		handleClick(selected);
	});
function handleClick(value) {
	if (isNonEmpty(value)) {
		if ("Select Class" == value) {
			var studyClassId = $('select#className').val();
				getAjaxDoGetStudent(studyClassId);
			$('#searchAdmissionNumber').hide();
			$('#searchStudentsList').show();
			$('#searchStudent').hide();
			$('#selectserchClass').show();
		} else if ("Search Student" == value) {
		//$('input#rollNumber').val("Student First or Last Name")
			$('#rollNumber').val('');
			$('#searchAdmissionNumber').hide();
			$('#searchStudentsList').hide();
			$('#selectserchClass').hide();
			$('#searchStudent').show();
		} else if ("Search Admission Number" == value) {
			//$('input#admissionNumber').val("Student Admission Number");
			$('#admissionNumber').val('');
			$('#selectserchClass').hide();
			$('#searchStudentsList').hide();
			$('#searchStudent').hide();
			$('#searchAdmissionNumber').show();
		}
	}
}
function getAjaxDoGetStudent(studyClassId) {
if (studyClassId != 0) {
	var pars = "classSectionId=" + studyClassId;
	$("#searchStudentsList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$('div.alert-success').hide();
	var url = jQuery.url.getChatURL("/schoolfee/ajaxSearchStudentByCriteria.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#searchStudentsList").html(html);
			$("#searchStudentsList").show();
		}
	});
  }
  else {
		$("#searchStudentsList").hide();
	}
}
</script>