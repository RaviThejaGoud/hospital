<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{academicYearList != null && !academicYearList.isEmpty}">
<s:form id="selectStudentForm" action="#" theme="simple" cssClass="form-horizontal">
	<div class="form-group">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						You can select the academic year to view all pending student applications.
					</li>
					<li>
						You can shortlist the pending application by clicking on Shortlist application.
					</li>
					<li>
						You can send the sms to all applicants by selecting the check box under the send message column.
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">
			<span class="required">*</span>Select Academic Year :
		</label>
		<div class="col-md-7">
			<s:select id="academicYearId" list="academicYearList"
				cssClass="required form-control input-medium" listKey="id"
				listValue="academicYear" headerKey="0" 
				name="tempId2"
				onchange="javascript:academicApplicationDetails();" />
		</div>
		
		<%-- <s:if test='%{admissionSettings.admissionFormTemplatepath != null}'>
			<div class="col-md-3">
			
			<a rel="nofollow"  target="_blank;" 
								href='${pageContext.request.contextPath}<s:property value="admissionSettings.admissionFormTemplatepath" />/<s:property value="admissionSettings.admissionFormTemplateFileName" />'>
								<s:property value="admissionSettings.admissionFormTemplateFileName" /> 
							</a>
			
			<iframe src="${pageContext.request.contextPath}<s:property value="admissionSettings.admissionFormTemplatepath" />" name="ifrm" style="display: none;"></iframe>
     		<!-- <a href="#" target="_blank" onClick='window.frames["ifrm"].print();'>Print</a> -->
     							<!-- <button onclick="myFunction()">Print this page</button> -->
			<a style="cursor: pointer;" class="btn btn-primary btn-sm green"
					id="processApplicationLink"
					onClick='window.frames["ifrm"].print();'>Print New Application<img style="display: none;" alt="Loading..."
					src="${pageContext.request.contextPath}/images/indicator.gif"
					id="processMarksIndicator">
				</a>
			</div>
		</s:if> --%>
		
	</div>
</s:form>
	<div class="form-group">
		<label class="col-md-2 control-label">
			Search Student By :
		</label>
		<div class="col-mod-9 radio-list">
			<label class="col-md-2 radio-inline">
				<input type="radio" name="SelectType" id="allAppl" class="admissionSearch"
					value="allApplications" checked="checked"
					onclick="viewSearchForm(this.value);" />
				All Applications
			</label>
			<label class="col-md-2 radio-inline">
				<input type="radio" name="SelectType" value="studentName" class="admissionSearch"
					onclick="viewSearchForm(this.value);" />
				Student Name
			</label>
			<label class="col-md-2 radio-inline">
				<input type="radio" name="SelectType" class="admissionSearch"
					value="applicationNumber" onclick="viewSearchForm(this.value);" />
				Application Number
			</label>
			<label class="radio-inline">
				<input type="radio" name="SelectType" id="lastClassWiseType" class="admissionSearch"
					value="classWise" onclick="viewSearchForm(this.value);" />
				Class Wise
			</label>
		</div>
	</div>
	<div class="searchDiv" id="searchStudent" style="display: none;">
		<s:form id="searchStudentByNumber" cssClass="form-horizontal"
			action="ajaxPendingApplications" theme="simple" namespace="/admin">
			<input type="hidden" class="academicYearId" name="academicYearId">
			<div class="form-group">
				<div class="col-md-2">
					&nbsp;
				</div>
				<div class="col-md-5">
					<div class="input-group">
						<sj:textfield name="anyTitle" id="firstOrLastName"
							placeholder="Student First or Last Name"
							cssClass="form-control medium"></sj:textfield>

						<span class="input-group-btn"> <sj:submit
								targets="admissionsPendingContentAppli" value="Find Student"
								cssClass="btn blue long" indicator="indicator"
								cssStyle="float:none;vertical-align:middle;"
								onBeforeTopics="searchStudentForm"
								formIds="searchStudentByNumber" validate="true" /> </span>
					</div>
					<span class="help-block">(Key at least 3 chars and hit
						'Find Student' to get closer match.) </span>
				</div>
			</div>
		</s:form>
	</div>
	<div class="searchDiv" id="searchApplNumber" style="display: none;">
		<s:form id="searchStudentByAdmissionNumber" cssClass="form-horizontal"
			action="ajaxPendingApplications" theme="simple" namespace="/admin">
			<input type="hidden" class="academicYearId" name="academicYearId">
			<div class="form-group">
				<div class="col-md-2">
					&nbsp;
				</div>
				<div class="col-md-5">
					<div class="input-group">
						<sj:textfield name="selectedId" id="applNumber"
							cssClass="form-control  medium"
							placeholder="Student Application Number"></sj:textfield>

						<span class="input-group-btn"> <sj:submit
								targets="admissionsPendingContentAppli" value="Find Student"
								cssClass="btn blue long" indicator="indicator"
								cssStyle="float:none;vertical-align:middle;"
								onBeforeTopics="searchStudentAdmissionForm"
								formIds="searchStudentByAdmissionNumber" validate="true" /> </span>
					</div>
					<span class="help-block">(Type application number and hit
						'Find Student'.)</span>
				</div>
			</div>
		</s:form>
	</div>
	<%@ include file="/common/messages.jsp"%>
	<div id="admissionsPendingContentAppli" style="display: none;">
	<%@ include file="/common/messages.jsp"%>
		<jsp:include
			page="/WEB-INF/pages/admin/admission/ajaxPendingAdmissionDetails.jsp"></jsp:include>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no admission settings created to any academic
		year.
		<s:url id="admissionSettingsCreat" namespace="/admin"
			action="ajaxAdmissionSettingsHome" >
			<s:param name="description">createSettings</s:param>
		</s:url>
		<sj:a id="createAdmissionSettings" href="%{admissionSettingsCreat}"
			targets="mainContentDiv" data-toggle="tab"><b>Click here </b></sj:a> to add Admission Settings
	</div>
</s:else>
<form method="post" id="stuAppFeePrintReport"
	action="javaScript:printPreviewAdmissions('<s:property value="empId" />','<s:property value="bedId"/>','<s:property value="anyTitle"/>',<s:property value="tempId1" />)"
	style="display: none;">
</form>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/jquery.blockUI.js">
</script>
<script type="text/javascript">
$(document).ready(
		function() {
			$.destroyTopic('searchStudentForm');
			$.destroyTopic('searchStudentAdmissionForm');
			$("input:checkbox, input:radio").uniform();
			changePageTitle('Application Details');
			academicApplicationDetails();
			$.subscribe('searchStudentForm', function(event, data) {
				$('.academicYearId').val($('#academicYearId option:selected').val());
				var name = $('#firstOrLastName').val();
				if (name == null || name == ''
						|| name == 'Student Admission Number'
						|| name == 'Student First or Last Name') {
					alert("Please enter first name or last name.");
					event.originalEvent.options.submit = false;
				} else if (name.length < 3) {
					alert("Please enter minimum 3 characters.");
					$('#name').val('Student First or Last Name');
					event.originalEvent.options.submit = false;
				}else{
					$('#admissionsPendingContentAppli').html('');
					$('#admissionsPendingContentAppli').show();
					return true;
				}
			});
			
			$.subscribe('searchStudentAdmissionForm', function(event, data) {
				//$('#makePayment').hide();
				$('.academicYearId').val($('#academicYearId option:selected').val()); 
				var appliNum = $('#applNumber').val();
				if (appliNum == null || appliNum == ''
						|| appliNum == 'Student Application Number') {
					alert("Please enter student application number.");
					event.originalEvent.options.submit = false;
				} else if (appliNum.length < 3) {
					alert("Please enter minimum 3 characters.");
					$('#appliNum').val('Student Application Number');
					event.originalEvent.options.submit = false;
				} else{
					$('#admissionsPendingContentAppli').html('');
					$('#admissionsPendingContentAppli').show();
					return true;
				}
			});
		});

// Changed by seshu on 21st May 2014				
function academicApplicationDetails() {
	var academicYearId = $('#academicYearId option:selected').val(); 
	$('.admissionSearch').removeAttr("checked");
	$('#allAppl').attr("checked","checked");
	$('#searchStudent').hide();
	$('#searchApplNumber').hide();
	if(isNonEmpty(academicYearId) && academicYearId != 0 && academicYearId !="- Select -"){
		var pars = "academicYearId=" + academicYearId;
		$("#admissionsPendingContentAppli").html(
				'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>').show();
		var url = jQuery.url.getChatURL("/admin/ajaxPendingApplications.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#admissionsPendingContentAppli").html(html);
				$("#admissionsPendingContentAppli").show();
			}
		});	
	}else{
		$("#admissionsPendingContentAppli").html('<div class="alert alert-info">Please select academic year.</div>');
		$("#admissionsPendingContentAppli").show();
	}
}

//Changed by seshu on 21st May 2014
function viewSearchForm(value) {
	$('.searchDiv').hide();
	$('#admissionsPendingContentAppli').hide();
	if (isNonEmpty(value)) {
		if ("allApplications" == value) {
			academicApplicationDetails();
			$('.close').click();
		} else if ("studentName" == value) {
			$('#searchStudent').show();
			$('#firstOrLastName').val('');
			$('.close').click();
		} else if ("applicationNumber" == value) {
			$('#searchApplNumber').show();
			$('#applNumber').val('');
			$('.close').click();
		} else if ("classWise" == value) {
			var classId=$('select#classId').val();
			academicClassWiseAdmissionDetails(classId);
		} 
	}
}

//Changed by seshu on 21st May 2014
function academicClassWiseAdmissionDetails(classId) {
	$("#admissionsPendingContentAppli").html(
			'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$("#admissionsPendingContentAppli").show();
	var academicYearId = $('#academicYearId').val();
	if(isNonEmpty(academicYearId) && academicYearId != 0 && academicYearId !="- Select -"){
		var pars = "academicYearId=" + academicYearId + "&classId="+classId;
		var url = jQuery.url.getChatURL("/admin/ajaxPendingApplications.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#admissionsPendingContentAppli").html(html);
				$("#admissionsPendingContentAppli").show();
			}
		});		
	}else{
		$("#admissionsPendingContentAppli").html('<div class="alert alert-info">Please select academic year.</div>');
		$("#admissionsPendingContentAppli").show();
	}
}

</script>