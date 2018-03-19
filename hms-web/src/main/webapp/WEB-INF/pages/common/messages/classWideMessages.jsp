<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<s:if test='%{customer.checkMobileService == true}'> <!-- Here remove the user.isSchoolAdmin because is login classTeacher its mobile Service enable this customer show the error message  -->
	<s:form action="ajaxSendSchoolWideMessages" theme="simple"
		cssClass="form-horizontal" id="addClassWideMessage" method="post">
		<input type="hidden" name="anyTitle" id="chkBoxClassWiseStatus" />
		<input type="hidden" name="chkBoxSelectedIds" id="classWideemailOrSms" />
		<s:if test='%{tempString == "stepTRMessage"}'>
			<input type="hidden" value="TR" name="status" />
		</s:if>
		<s:hidden name="student.id" />
		<input type="hidden" value="inbox" name="inbox" />
		<div id="clsDiv" style="display: none;">
			<div class="form-group">
				<label class="col-md-2 control-label"> Select Class Type : </label>
				<div class="col-md-8">
					<div class="radio-list">
						<label class="radio-inline"> <input type="radio"
							value="SingleClassStudents" id="selectedStudents"
							onclick="changeSelectedType(this.value);"
							name="sendSmsSingleClasses" class="radio" checked="checked">
							Send Message For Selected Class Students
						</label>
						<s:if
							test='%{user.isSchoolAdmin == "Y" || user.isOnlySchoolHod=="Y"}'>
							<label class="radio-inline"> <input type="radio"
								value="MultipleclassStudents" id="classStudents"
								onclick="changeSelectedType(this.value);"
								name="sendSmsSingleClasses" class="radio"> Send Message
								For Selected Class
							</label>
						</s:if>
					</div>
				</div>
			</div>
			<div class="form-group" id="DaysScholarOrHostelCw"
				style="display: none;">
				<s:if test="%{customer.hostelModuleStatus}">
					<label class="col-md-2 control-label"> Select : </label>
					<div class="col-md-9">
						<div class="checkbox-list">
							<div id="DaysScholar">
								<label class="checkbox-inline col-md-2"> <input
									type="checkbox" name="chkBoxClassWiseStatus"
									id="chkBoxClassWiseStatus" value="D" checked="checked">
									Days Scholar
								</label>
							</div>
							<div id="Hostler">
								<label class="checkbox-inline col-md-2"> <input
									type="checkbox" name="chkBoxClassWiseStatus"
									id="chkBoxClassWiseStatus" value="H" /> Hostler
								</label>
							</div>
						</div>
					</div>
				</s:if>
			</div>
			<div id="classesWithChkBoxes">
				<s:if
					test="%{(studyClassList != null && !studyClassList.isEmpty())}">
					<s:if test='%{studyClassList.size >1}'>
						<div class="checkbox">
							<label> <input type="checkbox" name="" value=""
								onClick="checkAllClasses()" class="checkbox allClasses">
								All Classes & Sections
							</label>
						</div>
					</s:if>
					<div class="form-group">
						<label class="col-md-2 control-label"> Classes With
							Students : </label>
						<div class="col-md-9">
							<s:checkboxlist name="chkBoxClassSelectedIds"
								list="studyClassList" listKey="id" listValue="classAndSection"
								theme="ems" />
						</div>
					</div>
					<s:if test='%{tempList2.size >0}'>
						<div class="form-group">
							<label class="col-md-2 control-label"> Classes With Out
								Students : </label>
							<div class="col-md-9">
								<s:checkboxlist name="chkBoxNotStudents" list="tempList2"
									listKey="id" listValue="classAndSection" theme="ems"
									cssClass="small" disabled="true" />
							</div>
						</div>
					</s:if>
				</s:if>
			</div>
			<div id="classesInSelectBox">
				<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
					<div class="form-group">
						<label class="control-label col-md-2"> Select Class : </label>
						<div class="col-md-3">
							<s:select list="studyClassList" id="className" name=""
								cssClass="form-control" listKey="id" listValue="classAndSection"
								headerKey="" headerValue="- Select Class -" theme="simple"
								onchange="javascript:getAjaxDoGetStudent(this.value);">
							</s:select>

						</div>
					</div>
				</s:if>
			</div>
			<div id="studentList">
			
			</div>
			<div id="reloadMessageDiv">
				<div class="form-group">
					<label class="control-label col-md-2"> <span
						class="required">*</span>SMS Text :
					</label>
					<div class="col-md-6">
						<sj:textarea name="messages.messageDescription"
							maxCharsData="1000" id="messageDesc" rows="3"
							cssClass="smsWord_count form-control required" cols="20"></sj:textarea>
						<span class="smsCounter"></span>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-5">
						<s:if test='%{selectedId == "classTeacher"}'>
						<a data-toggle="modal" href="#popupSelectClassSMSPreviewDiv"
							class="submitBt btn blue" id="popupMesageText"
							onclick="javascript:popupSendSelectClassSMSPreview(this.value);">
							Preview </a>
						<sj:submit cssClass="submitBt btn blue" value="Submit"
								targets="schoolWideMessagesHomeDiv" onBeforeTopics="classWideStudentsMsgs"
								formIds="addClassWideMessage" validate="true" />
							<s:url id="classTeacherDetail"
								action="ajaxDoGetClassWideMessagesList" namespace="/common"
								includeParams="all" escapeAmp="false">
								<s:param value="#session.academicYear" name="academicYearId" />
							</s:url>
							<sj:a href="%{classTeacherDetail}" targets="schoolWideMessagesHomeDiv"
								cssClass="btn default"> Cancel </sj:a>
						</s:if>
						<s:else>
						<a data-toggle="modal" href="#popupSelectClassSMSPreviewDiv"
							class="submitBt btn blue" id="popupMesageText"
							onclick="javascript:popupSendSelectClassSMSPreview(this.value);">
							Preview </a>
							<sj:submit cssClass="submitBt btn blue" value="Submit"
								targets="schoolWideMessagesHomeDiv"
								onBeforeTopics="classWideStudentsMsgs"
								formIds="addClassWideMessage" validate="true" />
							<s:url id="urlInboxesDetails"
								action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
								includeParams="all" escapeAmp="false">
								<s:param value="#session.academicYear" name="academicYearId" />
							</s:url>
							<sj:a href="%{urlInboxesDetails}" targets="schoolWideMessagesHomeDiv"
								cssClass="btn default"> Cancel</sj:a>
						</s:else>
					</div>
				</div>
		</div>
	</s:form>
</s:if>
<s:else>
	<s:if test='%{user.isSchoolAdmin == "Y"}'>
		<span class="label label-danger"> ALERT ! </span>&nbsp;
		Your SMS service is disabled <s:url
			id="urlManageSchool" action="ajaxDoSchoolInformation"
			namespace="/admin" />
		<sj:a href="%{urlManageSchool}" cssClass="enableEmailService"
			targets="mainContentDiv">
					Click Here</sj:a>to enable services.
	</s:if>
	<s:else>
		<span class="label label-danger"> ALERT : </span>&nbsp;
		<b>SMS services disabled, Contact Admin to enable services.</b>
	</s:else>
</s:else>
<div id="popupSelectClassSMSPreviewDiv"></div>
<script>
	$(document).ready(function() {
		$("#popupMesageText").addClass('disabled');
		$('.messagesArea2').wysihtml5({
			
			"font-styles" : false, //Font styling, e.g. h1, h2, etc. Default true
			"emphasis" : true, //Italics, bold, etc. Default true
			"lists" : true, //(Un)ordered lists, e.g. Bullets, Numbers. Default true
			"html" : true, //Button which allows you to edit the generated HTML. Default false
			"link" : true, //Button to insert a link. Default true
			"image" : true, //Button to insert an image. Default tr
			"color" : false
		//Button to change color of font  
		});
		//UIExtendedModals.init();
	});
	$('textarea#messageDesc').keyup(function() {
		var messDesc = $('#messageDesc').val();
		if(messDesc.length > 0 ){
			$("#popupMesageText").removeClass('disabled');
		}else {
			$("#popupMesageText").addClass('disabled');
		}
	});
	$('a.enableEmailService').click(function() {
		window.location.hash = "target=ASMS.ajaxify ASMEL";
		window.location.reload();
	});
	function popupSendSelectClassSMSPreview(id){
		var desc= $('#messageDesc').val();
		if(isNonEmpty(desc)){
		  var pars = "tempString=" + desc;
	        $.ajax( {
		        url : jQuery.url.getChatURL("/common/ajaxDoPopUpSmsPreview.do"),
				cache : true,
				data : pars,
				success : function(html) {
				$("#popupSelectClassSMSPreviewDiv").html(html);
				}
			});
		}
	 }
</script>