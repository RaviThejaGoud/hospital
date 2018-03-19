<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<%-- <s:if test="%{(classList != null && !classList.isEmpty())}"> --%>
<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
	<s:if
		test='%{tempString != null && (tempString == "studyCertificate" || tempString ==  "bonafiedCertificate" || tempString ==  "noDuesCertificate" || tempString ==  "feeCertificate")}'>
		<s:form action="ajaxAddStudyAndBonafiedSettings" theme="simple"
			cssClass="form-horizontal" id="uploadStudyAndBonafiedInfo"
			method="post" enctype="multipart/form-data" namespace="/admin">
			<s:hidden id="classNames" name="anyId" />
			<s:hidden name="tempString" value="%{tempString}" />
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							If you are not finding any certificate or need to prepare the certificate please contact eazyschool supporting team at (support@eazyschool.com).
						</li>
						<li>
							Eazyschool supporting will prepare the certificate with respect to your certificate design and upload into the respective place after getting the approval from the school management
						</li>
					</ul>
				</div>
			</div>
			<jsp:include
					page="/WEB-INF/pages/admin/common/ajaxGetAllClassAndSectionsList.jsp"></jsp:include>
			<div class="form-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6">
								<s:if test='%{tempString == "studyCertificate"}'>
									<span class="required">*</span>Upload Template Word Doc :
							</s:if>
								<s:elseif test='%{tempString == "bonafiedCertificate"}'>
									<span class="required">*</span>Upload Template Word Doc :
							</s:elseif>
								<s:elseif test='%{tempString ==  "noDuesCertificate"}'>
									<span class="required">*</span>Upload Template Word Doc :
							</s:elseif>
								<s:elseif test='tempString == "feeCertificate"'>
									<span class="required">*</span>Upload Template Word Doc :
							</s:elseif>
							
							</label>
							<div class="col-md-4">
								<s:if test='%{tempString == "studyCertificate"}'>
									<input type='file' name="uploadList[1]"
										class="btn default required fileNames " />
								</s:if>
								<s:elseif test='%{tempString == "bonafiedCertificate"}'>
									<input type='file' name="uploadList[1]"
										class="btn default required fileNames " />
								</s:elseif>
								<s:elseif test='%{tempString ==  "noDuesCertificate"}'>
									<input type='file' name="uploadList[1]"
										class="btn default required fileNames" />
								</s:elseif>
								<s:elseif test='tempString == "feeCertificate"'>
									<input type='file' name="uploadList[1]"
										class="btn default required fileNames" />
								</s:elseif>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-5">
						<sj:submit   targets="settingsDiv" value="Submit"
							formIds="uploadStudyAndBonafiedInfo" indicator="indicator"
							cssClass="submit btn blue" validate="true"
							onBeforeTopics="validateBonafiedForm" />
					</div>
				</div>
			</div>
			<br />
			<br />
		</s:form>
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<h4 class="pageTitle bold">
				Uploaded 
				<s:if test='%{tempString == "studyCertificate"}'>
				'Study' 
				</s:if>
				<s:elseif test='%{tempString == "bonafiedCertificate"}'>
					'Bonafied'
				</s:elseif>
				<s:elseif test='%{tempString ==  "noDuesCertificate"}'>
					'No Due'
				</s:elseif>
				<s:elseif test='tempString == "feeCertificate"'>
					'Fee'
				</s:elseif>
				  certificate templates 
			</h4>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Class Names
						</th>
						<th>
							Template
						</th>
						<th>
							Download
						</th>
						<th>
							Delete
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="objectList" status="stat">
						<tr>
							<td>
								<s:property value="objectList[#stat.count-1][3]" />
							</td>
							<td>
								<s:property value="objectList[#stat.count-1][2]" />
								<%-- <s:if test='%{tempString == "studyCertificate"}'>
									<s:property value="studentsList[#stat.count-1][2]" />
								</s:if>
								<s:elseif test='%{tempString == "bonafiedCertificate"}'>
									<s:property value="bonafiedFileName" />
								</s:elseif>
								<s:elseif test='%{tempString ==  "noDuesCertificate"}'>
									<s:property value="dueCertificateFileName" />
								</s:elseif> --%>
							</td>
							<td width="10%">
								<s:form action="ajaxDownloadStudyAndHTTemplate" method="post" theme="css_xhtml" cssClass="form-horizontal" namespace="/admin">
									<s:hidden name="tempId" value="%{objectList[#stat.count-1][0]}" />
									<s:hidden name="tempString" value="%{tempString}"></s:hidden>		
									<s:submit type="submit" value="Download" cssClass="btn btn-xs blue " cssStyle="cursor:pointer;float:left;" />											
								</s:form>
							</td>
							<td>
								<s:url id="removeStudyTemp"
									action="ajaxDeleteStudyBonafiedAndNoDueTemplate"
									includeParams="all" escapeAmp="false" namespace="/admin">
									<s:param name="tempId" value="objectList[#stat.count-1][0]" />
									<s:param name="tempString" value="%{tempString}"></s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red" id='%{removeStudyTemp}'
									theme="simple" title="Delete this Template"
									onclick="javascript:confirmDialogWithTarget(this,'certificatesCont');">
									<i class="fa fa-times"></i>Delete</s:div>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no
				<s:if test='%{tempString == "studyCertificate"}'>
								Study				
								</s:if>
				<s:elseif test='%{tempString == "bonafiedCertificate"}'>
								Bonafied
								</s:elseif>
				<s:elseif test='%{tempString ==  "noDuesCertificate"}'>
									No Dues
								</s:elseif>
				<s:elseif test='%{tempString ==  "feeCertificate"}'>
									Fee
								</s:elseif>
				certificate settings.
			</div>
		</s:else>
	</s:if>
	<s:if test='%{tempString != null && tempString == "studyBookSettings"}'>
		<span id='settingsCreatedClassIds'
			class='<s:property value="anyTitle"/>'></span>
		<s:form action="ajaxAddStudyBookSettings" theme="simple"
			cssClass="form-horizontal" id="studyCertificateForm" method="post">
			<s:hidden name="tempString" value="%{tempString}" />
			<s:hidden id="classNames" name="anyId" />
			<h4 class="pageTitle bold">
				Add Study Certificate Book Settings
			</h4>
			<div class="form-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-6">
								<span class="required">*</span>Book Number :
							</label>
							<div class="col-md-6">
								<sj:textfield name="tempId" id="tcBookNumber"
									cssClass="required  numeric form-control input-medium "
									value="" maxlength="11"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">
								<span class="required">*</span>Certificate Starting Number :
							</label>
							<div class="col-md-5">
								<sj:textfield name="tempId1" id="slStartNo"
									cssClass="required  numeric form-control input-medium" value=""
									maxlength="11"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<jsp:include
					page="/WEB-INF/pages/admin/common/ajaxClassAndSectionsList.jsp"></jsp:include>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-5">
						<sj:submit   targets="certificatesCont" value="Submit"
							formIds="studyCertificateForm" indicator="indicator"
							cssClass="submit btn blue" validate="true"
							onBeforeTopics="validateStudyCertificateForm" />
					</div>
				</div>
			</div>
			<br />
			<br />
		</s:form>
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<h4 class="pageTitle bold">
				View Study Certificate Book Settings
			</h4>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_3">
				<thead>
					<tr>
						<th>
							Book Number
						</th>
						<th>
							Start Number
						</th>
						<th>
							Classes
						</th>
						<th>
							Delete
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="tempList">
						<tr>
							<td>
								<s:property value="studyBookNumber" />
							</td>
							<td>
								<s:property value="studyStartingNumber" />
							</td>
							<td>
								<s:property value="bookSettingsClasses" />
							</td>
							<td>
								<s:if test='%{#session.previousYear=="N"}'>
									<a id="removeBookSetting<s:property value="id"/>"
										class="btn btn-xs red"
										onclick="javascript:removeStudyCertificateBookSettings(<s:property value="id"/>);">
										<i class="fa fa-times"></i>Delete</a>
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no Study book settings. Please add Study book
				settings.
			</div>
		</s:else>
	</s:if>
</s:if>
<s:else>
	<div class="alert alert-info">
		Class and Sections are not available.
	</div>
</s:else> 
<script type="text/javascript">
$(document)
		.ready(function() {
			$.destroyTopic('validateBonafiedForm');
			$.destroyTopic('validateStudyCertificateForm');
			
			TableAdvanced.init();
			changePageTitle("Study Certificate Settings");
			$('.numeric').numeric();
			//$('#tcFontName').val('');
				var settingClassIds = $("span#settingsCreatedClassIds").attr(
						'class');
				if (isNonEmpty(settingClassIds)) {
					var classIds = settingClassIds.split(',');
					for ( var i = 0; i < classIds.length; i++) {
						$(
								'input:checkbox[name="chkBoxSelectedIds"][value=' + classIds[i] + ']')
								.attr('disabled', true);
						$(
								'input:checkbox[name="chkBoxSelectedIds"][value=' + classIds[i] + ']')
								.parent('span').parent('div').addClass(
										'disabled');
					}
				}
				$
						.subscribe(
								'validateBonafiedForm',
								function(event, data) {
									if (!isNonEmpty($("input.fileNames").val())) {
										alert("Please browse your files to upload.");
										event.originalEvent.options.submit = false;
									} else {
										if (isNonEmpty($("input.fileNames")
												.val())
												&& ($("input.fileNames").val()
														.lastIndexOf(".docx") == -1)) {
											alert("File not acceped. Please upload your file in .docx");
											event.originalEvent.options.submit = false;
										}
									}
									var boolVal = generateTCSetingsWithClassIds();
									if (!boolVal)
										event.originalEvent.options.submit = false;

									//return true;

								});
				$.subscribe('validateStudyCertificateForm', function(event,
						data) {
					var boolVal = generateTCBookSetingsClassIds();
					if (!boolVal)
						event.originalEvent.options.submit = false;
				});
			});
/* 
 $.subscribe('doResetClassDetails', function(event, data) {
 $('input[name=chkBoxSelectedIds]:checked').each(function() {
 $(this).attr("disabled", "disabled");
 });

 $("form#uploadTcInfo").reset();

 });*/

/*
 function validateStartAndEndNumbers(evt){
 var startNumber = $('#slStartNo').val();
 var endNumber = $('#slEndNo').val();
 if(isNonEmpty(startNumber) && isNonEmpty(endNumber)
 && Number(startNumber) > Number(endNumber)){
 alert("TC Start number should be less than TC End number.");
 $(evt).val('');
 }
 }*/

function removeStudyCertificateBookSettings(id) {
	var url = jQuery.url
			.getChatURL("/admin/ajaxCheckBookNumberAssignedToStudents.do?anyId="
					+ id);
	$('#indicator' + id).show();
	$
			.ajax( {
				url : url,
				cache : false,
				dataType : 'json',
				success : function(response) {
					var data = response.bookUsed;
					var answer = '';
					if (isNonEmpty(data)) {
						answer = confirm("" + data);
					} else {
						var answer = confirm("Are you sure you want to delete ?");
						if (answer) {
							var pars = "tempId=" + id
									+ "&tempString=studyBookSettings";
							var url = jQuery.url
									.getChatURL("/admin/ajaxDeleteStudyBonafiedAndNoDueTemplate.do");
							$.ajax( {
								url : url,
								cache : false,
								data : pars,
								success : function(responce) {
									$("#certificatesCont").html(responce);
								}
							});
							return true;
						} else {
							$('#indicator' + id).hide();
							return false;
						}
					}
					if (isNonEmpty(answer)) {
						if (!answer) {
							$('#removeBookSetting' + id).attr("href",
									"javascript:void(0);");
							$('#indicator' + id).hide();
						} else {
							var pars = "tempId=" + id
									+ "&tempString=studyBookSettings";
							var url = jQuery.url
									.getChatURL("/admin/ajaxDeleteStudyBonafiedAndNoDueTemplate.do");
							$.ajax( {
								url : url,
								cache : false,
								data : pars,
								success : function(responce) {
									$("#certificatesCont").html(responce);
								}
							});
						}
					} else {
						$('#indicator' + id).hide();
					}
				}
			});
}

function generateTCBookSetingsClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassNames = [];
		if (classIds.length > 0) {
			for ( var i = 0; i < classIds.length; i++) {
				if (isNonEmpty(classIds[i].value)) {
					selectedClassNames.push(classIds[i].value);
				}
			}
		}
		$("#classNames").val(selectedClassNames);
		return true;
	} else {
		alert("Please select at least one class.");
		return false;
	}
}
function generateTCSetingsWithClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassNames = [];
		if (classIds.length > 0) {
			$(classIds)
					.each(
							function() {
								if (isNonEmpty($(this).val())
										&& isNonEmpty($(this).parents('label')
												.text())) {
									selectedClassNames.push($(this).parents(
											'label').text().trim())
								}
							})
		}
		$("#classNames").val(selectedClassNames);
		return true;
	} else {
		alert("Please select at least one class.");
		return false;
	}
}
</script>
