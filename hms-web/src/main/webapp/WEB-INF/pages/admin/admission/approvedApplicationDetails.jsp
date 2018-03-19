<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Shortlisted Applications
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content" id="searchAppAdmissionWise">
					<%@ include file="/common/messages.jsp"%>
					<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
						<div style="color: red;" class="alert alert-info col-md-12">
							You have been used all your allotted
							<s:property value="smsAlloted" />
							SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
						</div>
					</s:if>
					<s:if
						test="%{academicYearList != null && !academicYearList.isEmpty}">
						<s:form id="selectStudentForm" action="#" theme="simple"
							cssClass="form-horizontal">
							<div class="form-group">
								<label class="control-label col-md-2">
									<span class="required">*</span>Select Academic Year :
								</label>
								<div class="col-md-3">
									<s:select id="academicYearId" list="academicYearList"
										cssClass="required form-control input-medium" listKey="id"
										listValue="academicYear" headerKey="0"  name="tempId2"
										onchange="javascript:academicApprovedApplicationDetails();" />
								</div>
							</div>
						</s:form>
						<div class="form-group">
								<label class="col-md-2 control-label">
									Search Student By :
								</label>
							<div class="col-md-9 radio-list">
							<label class="col-md-2 radio-inline">
								<input type="radio" name="SelectType" id="allAppl"
									value="allApplications"  checked="checked" class="admissionSearch"
									onclick="viewApprovedSearchForm(this.value);"/>
								All Applications 
								</label>
								<label class="col-md-2 radio-inline">
								<input type="radio" name="SelectType" value="studentName" class="admissionSearch" 
									onclick="viewApprovedSearchForm(this.value);"/>
								Student Name
								</label>
								<label class="col-md-3 radio-inline">
								<input type="radio" name="SelectType" class="admissionSearch"
									value="applicationNumber"
									onclick="viewApprovedSearchForm(this.value);"/>
								Application Number
								</label>
								<label class="col-md-2 radio-inline">
								<input type="radio" name="SelectType" id="lastClassWiseType" class="admissionSearch"
									value="classWise"
									onclick="viewApprovedSearchForm(this.value);"/>
								Class Wise
								</label>
							</div>
						</div>
						<br/><br/>
						<div class="searchDiv" id="searchStudent" style="display: none;">
							<s:form id="searchStudentByNumber" cssClass="form-horizontal"
								action="ajaxApprovedApplications" theme="simple"
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
												
												<sj:submit   targets="academicsApprovedContent" value="Find Student"
												cssClass="btn blue long" indicator="indicator"
												cssStyle="float:none;vertical-align:middle;" onBeforeTopics="searchStudentForm"
												formIds="searchStudentByNumber" validate="true" />
												</span>
										</div>
										<span class="help-block">(Key at least 3 chars and hit 'Find Student' to get closer match.) </span>
									</div>
								 </div>
							</s:form>
						</div>
						<div class="searchDiv" id="searchApplNumber" style="display: none;">
							<s:form id="searchStudentByAdmissionNumber" cssClass="form-horizontal"
								action="ajaxApprovedApplications" theme="simple"
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
											
											<sj:submit   targets="academicsApprovedContent" value="Find Student"
											cssClass="btn blue long" indicator="indicator"
											cssStyle="float:none;vertical-align:middle;" 
											onBeforeTopics="searchStudentAdmissionForm"
											formIds="searchStudentByAdmissionNumber" validate="true" />
											</span>
									</div>
									<span class="help-block">(Type application number and hit 'Find Student'.)</span>
								</div></div>
							</s:form>
						</div>
						<div id="academicsApprovedContent" style="display: none;">
							<jsp:include
								page="/WEB-INF/pages/admin/admission/ajaxApprovedApplicationDetails.jsp"></jsp:include>
						</div>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no admission settings created to any academic
							year.
							<s:url id="admissionSettingsShortList" namespace="/admin"
								action="ajaxAdmissionSettingsHome" >
								<s:param name="description">createSettings</s:param>
							</s:url>
							<sj:a id="createShortAdmissionSettings" href="%{admissionSettingsShortList}"
								targets="mainContentDiv" data-toggle="tab"><b>Click here </b></sj:a> to add Admission Settings
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
	$.destroyTopic('searchStudentForm');
	$.destroyTopic('searchStudentAdmissionForm');
	changePageTitle('Short-Listed Applications');
	$("input:checkbox, input:radio").uniform();
	var selected = $('input[name=SelectType]:radio:checked').val();
		viewApprovedSearchForm(selected);
		$.subscribe('searchStudentForm', function(event, data) {
			$('.academicYearId').val($('#academicYearId option:selected').val());
				var name = $('#firstOrLastName').val();
			if (name == null || name == ''
					|| name == 'Student Admission Number'
					|| name == 'Student First or Last Name') {
				alert("Please enter first name or last name.");
				event.originalEvent.options.submit=false;
			} else if (name.length < 3) {
				alert("Please enter minimum 3 characters.");
				$('#firstOrLastName').val('');
				event.originalEvent.options.submit=false;
			}  else{
				$('#academicsApprovedContent').html('');
					$('#academicsApprovedContent').show();	
				return true;
			}
			//var applNumber = $('#applNumber').val('');
		});
		$.subscribe('searchStudentAdmissionForm', function(event, data) {
			//$('#makePayment').hide();
			$('.academicYearId').val($('#academicYearId option:selected').val());
			var applNumber = $('#applNumber').val();
			if (applNumber == null || applNumber == ''
					|| applNumber == 'Student Application Number') {
				$('#applNumber').val('');
				alert("Please enter student application number.");
				event.originalEvent.options.submit=false;

			} else if (applNumber.length < 3) {
				alert("Please enter minimum 3 characters.");
				$('#applNumber').val('');
				event.originalEvent.options.submit=false;
			} else{
					$('#academicsApprovedContent').html('');
					$('#academicsApprovedContent').show();
					return true;
			}
		});
});
function academicApprovedApplicationDetails() {
	var academicYearId = $('#academicYearId option:selected').val(); 
	$('.admissionSearch').removeAttr("checked");
	$('#allAppl').attr("checked","checked");
	if(isNonEmpty(academicYearId) && academicYearId != 0){
		var pars = "academicYearId=" + academicYearId;
		$("#academicsApprovedContent").html(
				'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>').show();
		var url = jQuery.url.getChatURL("/admin/ajaxApprovedApplications.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#academicsApprovedContent").html(html);
				$("#academicsApprovedContent").show();
			}
		});	
	}else{
		$("#academicsApprovedContent").html('<div class="alert alert-info">Please select academic year.</div>').show();
	}
}
function viewApprovedSearchForm(value) {
$('.searchDiv').hide();
	$('#academicsApprovedContent').hide();
	if (isNonEmpty(value)) {
		if ("allApplications" == value) {
			academicApprovedApplicationDetails();
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
	$("#academicsApprovedContent").html(
			'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$("#academicsApprovedContent").show();
	var academicYearId = $('#academicYearId').val();
	if(isNonEmpty(academicYearId) && academicYearId != 0){
		var pars = "academicYearId=" + academicYearId + "&classId="+classId;
		var url = jQuery.url.getChatURL("/admin/ajaxApprovedApplications.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#academicsApprovedContent").html(html);
				$("#academicsApprovedContent").show();
			}
		});		
	}else{
		$("#academicsApprovedContent").html('<div class="alert alert-info">Please select academic year.</div>');
		$("#academicsApprovedContent").show();
	}
}
$('a.academicPlannerId').click(function(){
	window.location.hash="target=ADMS.ajaxify ADST";
	window.location.reload();
});
</script>
