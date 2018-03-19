<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Rejected Applications
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<%@ include file="/common/messages.jsp"%>
					<s:if
						test="%{academicYearList != null && !academicYearList.isEmpty}">
						<s:form id="selectStudentForm" action="#" theme="simple"
							cssClass="form-horizontal">
							<div class="form-group">
								<div class="col-md-11">
									<label class="control-label col-md-4">
										<span class="required">*</span>Select Academic Year :
									</label>
									<div class="col-md-4">
										<s:select id="academicYearId" list="academicYearList"
											cssClass="required form-control input-medium" listKey="id"
											listValue="academicYear" headerKey="0" name="tempId2"
											onchange="javascript:academicRejectedApplicationDetails();" />
									</div>
								</div>
							</div>
						</s:form>
						<div class="form-group">
								<label class="col-md-2 control-label">
									Search Student By :
								</label>
							<div class="col-md-9 radio-list">
							<label class="col-md-2 radio-inline">
								<input type="radio" name="SelectType"  
									value="allApplications"  checked="checked" id="allAppl" class="admissionSearch"
									onclick="viewRejectedAppl(this.value);"/>
								All Applications 
								</label>
								<label class="col-md-2 radio-inline">
								<input type="radio" name="SelectType" value="studentName" class="admissionSearch"
									onclick="viewRejectedAppl(this.value);"/>
								Student Name
								</label>
								<label class="col-md-3 radio-inline">
								<input type="radio" name="SelectType" class="admissionSearch"
									value="applicationNumber"
									onclick="viewRejectedAppl(this.value);"/>
								Application Number
								</label>
								<label class="col-md-2 radio-inline">
								<input type="radio" name="SelectType" id="lastClassWiseType"
									value="classWise" class="admissionSearch"
									onclick="viewRejectedAppl(this.value);"/>
								Class Wise
								</label>
							</div>
						</div>
						<br/><br/>
						<div class="searchDiv" id="searchStudent" style="display: none;">
							<s:form id="searchStudentByNumber" cssClass="form-horizontal"
								action="ajaxRejectedApplicationsDetails" theme="simple"
								namespace="/admin">
								<input type="hidden" class="academicYearId" name="academicYearId">
									<div class="form-group">
										<div class="col-md-2">
											&nbsp;
										</div>
										<div class="col-md-5">
											<div class="input-group">
											<sj:textfield name="anyTitle" id="firstOrLastName"
												placeholder="Student First or Last Name"
												cssClass="form-control defaultValue medium"></sj:textfield>
												
												<span class="input-group-btn">
												<sj:submit   targets="academicsRejectedContentDiv" value="Find Student"
												cssClass="btn blue long" indicator="indicator"
												cssStyle="float:none;vertical-align:middle;" onBeforeTopics="searchRejAplStudentForm"
												formIds="searchStudentByNumber" validate="true" />
												</span>
										</div>
										<span class="help-block">(Key at least 3 chars and hit submit to get closer match.) </span>
									</div>
								 </div>
							</s:form>
						</div>
						<div class="searchDiv" id="searchApplNumber" style="display: none;">
							<s:form id="searchStudentByAdmissionNumber" cssClass="form-horizontal"
								action="ajaxRejectedApplicationsDetails" theme="simple"
								namespace="/admin">
								<input type="hidden" class="academicYearId" name="academicYearId">
								<div class="form-group">
										<div class="col-md-2">
											&nbsp;
										</div>
										<div class="col-md-5">
											<div class="input-group">
										<sj:textfield name="selectedId" id="applNumber"
											cssClass="form-control defaultValue medium"
											  placeholder="Student Application Number"></sj:textfield>
											 
											<span class="input-group-btn">
											<sj:submit   targets="academicsRejectedContentDiv" value="Find Student"
											cssClass="btn blue long" indicator="indicator"
											cssStyle="float:none;vertical-align:middle;" 
											onBeforeTopics="searchRejAplStudentAdmissionForm"
											formIds="searchStudentByAdmissionNumber" validate="true" />
											</span>
									</div>
									<span class="help-block">(Type application number and hit submit.)</span>
								</div></div>
							</s:form>
						</div>
						<div id="academicsRejectedContentDiv" style="display: none;">
							<jsp:include
								page="/WEB-INF/pages/admin/admission/ajaxRejectedApplication.jsp"></jsp:include>
						</div>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no admission settings created to any academic
							year.
							<s:url id="urlRejectedApplicationLink" action="ajaxAdmissionSettingsHome"
								includeParams="all"  namespace="/admin">
								<s:param name="description">createSettings</s:param>
							</s:url>
							<sj:a href="%{urlRejectedApplicationLink}" targets="mainContentDiv"><b>Click here</b> </sj:a> to add Admission Settings
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/jquery.blockUI.js"> </script>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('searchRejAplStudentForm');
	$.destroyTopic('searchRejAplStudentAdmissionForm');
changePageTitle('Rejected Applications');
$("input:checkbox, input:radio:not('.toggle')").uniform(); 
	var selected = $('input[name=SelectType]:radio:checked').val();
		viewRejectedAppl(selected);
		$.subscribe('searchRejAplStudentForm', function(event, data) {
			$('.academicYearId').val($('#academicYearId option:selected').val());
			var name = $('#firstOrLastName').val();
			if (name == null || name == ''
					|| name == 'Student Admission Number'
					|| name == 'Student First or Last Name') {
				alert("Please enter first name or last name.");
				event.originalEvent.options.submit=false;

			} else if (name.length < 3) {
				alert("Please enter minimum 3 characters.");
				$('#firstOrLastName').val('Student First or Last Name');
				event.originalEvent.options.submit=false;
			} else{
				$('#academicsRejectedContentDiv').html('').show();
				return true;
			}
		});
		$.subscribe('searchRejAplStudentAdmissionForm', function(event, data) {
			//$('#makePayment').hide();
			$('.academicYearId').val($('#academicYearId option:selected').val());
			var applNumber = $('#applNumber').val();
			if (applNumber == null || applNumber == ''
					|| applNumber == 'Student Application Number') {
				alert("Please enter student application number.");
				event.originalEvent.options.submit=false;

			} else if (applNumber.length < 3) {
				alert("Please enter minimum 3 characters.");
				$('#applNumber').val('Student Application Number');
				event.originalEvent.options.submit=false;
			} else{
				$('#academicsRejectedContentDiv').html('');
				$('#academicsRejectedContentDiv').show();
				return true;
			}
		});
});
function academicRejectedApplicationDetails() {
	var academicYearId = $('#academicYearId option:selected').val(); 
	$('.admissionSearch').removeAttr("checked");
	$('#allAppl').attr("checked","checked");
	if(isNonEmpty(academicYearId) && academicYearId != 0){
		var pars = "academicYearId=" + academicYearId;
		$("#academicsRejectedContentDiv").html(
				'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>').show();
		var url = jQuery.url.getChatURL("/admin/ajaxRejectedApplicationsDetails.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#academicsRejectedContentDiv").html(html);
				$("#academicsRejectedContentDiv").show();
			}
		});	
	}else{
		$("#academicsRejectedContentDiv").html('<div class="alert alert-info">Please select academic year.</div>').show();
	}
}
function viewRejectedAppl(value) {
	$('.searchDiv').hide();
	$('#academicsRejectedContentDiv').hide();
	if (isNonEmpty(value)) {
		if ("allApplications" == value) {
			academicRejectedApplicationDetails();
		} else if ("studentName" == value) {
			$('#searchStudent').show();
			$('#firstOrLastName').val('');
		} else if ("applicationNumber" == value) {
			$('#searchApplNumber').show();
			$('#applNumber').val('');
		} else if ("classWise" == value) {
		var classId = $("select#classId").val();
			academicClassWiseAdmissionDetails(classId);
		} 
	}
}
function academicClassWiseAdmissionDetails(classId) {
	$("#academicsRejectedContentDiv").html(
			'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>').show();
	var academicYearId = $('#academicYearId').val();
	if(isNonEmpty(academicYearId) && academicYearId != 0){
		var pars = "academicYearId=" + academicYearId + "&classId="+classId;
		var url = jQuery.url.getChatURL("/admin/ajaxRejectedApplicationsDetails.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#academicsRejectedContentDiv").html(html);
				$("#academicsRejectedContentDiv").show();
			}
		});		
	}else{
		$("#academicsRejectedContentDiv").html('<div class="alert alert-info">Please select academic year.</div>');
		$("#academicsRejectedContentDiv").show();
	}
}
</script>