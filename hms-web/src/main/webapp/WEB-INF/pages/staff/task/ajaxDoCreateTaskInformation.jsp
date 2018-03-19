<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/textcounter.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:form action="ajaxAddTaskInformation" theme="simple"
	id="addTaskInfromation" method="post" cssClass="form-horizontal"
	namespace="/staff">
	<s:hidden name="taskDetailsVO.id" value="%{taskDetailsVO.id}" id="taskDetailsVOId" />
	<s:hidden name="reminderVO.id" value="0" id="reminderVOId" />
	<s:hidden name="tempString" id="tempString"></s:hidden>
	<div class="form-body">
		<p>
			<span class="label label-danger"> NOTE : </span>&nbsp; You can add "
			Task " Details through this process.
		</p>
		<h4 class="pageTitle bold form-section">Task INFORMATION</h4>
		<s:if test="%{taskDetailsVO.id == 0}"> 
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label">Select Type : </label>
						<div class="col-md-6">
							<div class="radio-list">
								<label class="radio-inline"> <input type="radio"
									value="T" id="taskInfo" checked="checked" name="selectType"
									onclick="handleClick(this.value);" class="radio"> Task
								</label> <label class="radio-inline"> <input type="radio"
									value="R" id="reminder" name="selectType" class="radio"
									onclick="handleClick(this.value);"> Reminder
								</label>
							</div>
						</div>
					</div>

				</div>
			</div>
		 </s:if>
		 <s:else>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label">Select Type : </label>
						<div class="col-md-6">
							<div class="radio-list">
								<label class="radio-inline"> <input type="radio"
									value="T" id="taskInfo" checked="checked" name="selectType" disabled="disabled"
									onclick="handleClick(this.value);" class="radio"> Task
								</label> <label class="radio-inline"> <input type="radio" disabled="disabled"
									value="R" id="reminder" name="selectType" class="radio" 
									onclick="handleClick(this.value);"> Remainder
								</label>
							</div>
						</div>
					</div>

				</div>
			</div>
		</s:else> 
		<div class="row" id="taskInformationDiv">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span> Task Name :
						</label>
						<div class="col-md-7">
							<sj:textfield name="taskDetailsVO.taskName" id="spName"
								cssClass="required form-control input-medium" maxlength="225" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span> Description :
						</label>
						<div class="col-md-6">
							<sj:textarea rows="3" cols="50" name="taskDetailsVO.description" id="maxlength_textarea2" 
								cssClass="required form-control word_count" maxlength="1024"></sj:textarea>
							<span class="help-block"> </span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Completion Date :
						</label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input id="startDate" name="taskDetailsVO.latestTaskHistoryVO.taskDate" readonly=""
									type="text" class="form-control required"
									value='<s:property value="taskDetailsVO.latestTaskHistoryVO.taskDateStr"/>'>
								<span class="dateInput input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button>
								</span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label">Reminder Type : </label>
						<div class="col-md-6">
							<div class="radio-list" style="margin-top:5px;">
								<label class="radio-inline"> <input type="radio"
									value="E" id="reminderSelectForTask" checked="checked" onclick="checkReminderOptionForTask(this.value);"
									name="taskDetailsVO.reminderOption" class="radio" > Every
									Day
								</label> 
								<label class="radio-inline"> <input type="radio"
									value="S" id="reminderSelectForTask" name="taskDetailsVO.reminderOption" onclick="checkReminderOptionForTask(this.value);"
									class="radio"> Specific Date
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row" id="specificDateDivForTask" style="display: none;">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Specific Date :
						</label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker" data-date-start-date="+0d" >
								<input id="specificDateForTask" name="taskDetailsVO.specificDate" readonly=""
									type="text" class="form-control required" >
								<span class="dateInput input-group-btn">
									<button type="button" class="btn default required">
										<i class="fa fa-calendar"></i>
									</button>
								</span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"><span
							class="required">*</span> Communication : </label>
				        <div class="col-md-4" style="margin-top:5px;">
					    <input type="checkbox"  name="taskDetailsVO.checkMobileService" checked="checked" id="checkMobileServiceForTask"/>
									SMS
					   </div>
					   <div class="col-md-4" style="margin-top:5px;">
					    <input type="checkbox" name="taskDetailsVO.checkEmailService" id="checkEmailServiceForTask"/>
									Email
					   </div>
			      </div>
			   </div>
		 </div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label"> <span
							class="required">*</span>Assign to :
						</label>
						<div class="col-md-8">
							<s:select list="objectList" listKey="accountId" id="accountId"
								listValue="fullName" theme="simple"
								cssClass="required form-control input-medium"
								name="taskDetailsVO.latestTaskHistoryVO.accountVO.id" headerKey="">
							</s:select>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" id="reminderDiv" style="display: none;">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Name :
						</label>
						<div class="col-md-7">
							<sj:textfield name="reminderVO.name" id="reminderName"
								cssClass="required form-control input-medium" maxlength="225" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span> Description :
						</label>
						<div class="col-md-6">
							<sj:textarea rows="3" cols="50" name="reminderVO.description" id="maxlength_textarea3" 
								cssClass="required form-control word_count" maxlength="1024"></sj:textarea>
							<span class="help-block"> </span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Expiration Date :
						</label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker" data-date-start-date="+0d">
								<input id="expirationDate" name="reminderVO.expirationDate" readonly=""
									type="text" class="form-control required">
								<span class="dateInput input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button>
								</span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label">Reminder Type : </label>
						<div class="col-md-6">
							<div class="radio-list" style="margin-top:5px;">
								<label class="radio-inline"> <input type="radio"
									value="E" id="reminderSelect" checked="checked" onclick="checkReminderOption(this.value);"
									name="reminderVO.reminderOption" class="radio" > Every
									Day
								</label> <label class="radio-inline"> <input type="radio"
									value="S" id="reminderSelect" name="reminderVO.reminderOption" onclick="checkReminderOption(this.value);"
									class="radio"> Specific Date
								</label>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="row" id="specificDateDiv" style="display: none;">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Specific Date :
						</label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker" data-date-start-date="+0d" >
								<input id="specificDate" name="reminderVO.specificDate" readonly=""
									type="text" class="form-control required" >
								<span class="dateInput input-group-btn">
									<button type="button" class="btn default required">
										<i class="fa fa-calendar"></i>
									</button>
								</span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"><span
							class="required">*</span> Communication : </label>
				        <div class="col-md-4" style="margin-top:5px;">
					    <input type="checkbox"  name="reminderVO.checkMobileService" checked="checked" id="checkMobileService"/>
									SMS
					   </div>
					   <div class="col-md-4" style="margin-top:5px;">
					    <input type="checkbox" name="reminderVO.checkEmailService" id="checkEmailService"/>
									Email
					   </div>
			      </div>
			   </div>
		 </div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6" id="taskSubmitDiv">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						id="clickSubmit" onBeforeTopics="validateImageFormat"
						validate="true" indicator="indicator" targets="mainContentDiv"
						formIds="addTaskInfromation" />
					<s:url id="doViewTaskInfo" action="ajaxTaskInformationHome"
						includeParams="all" escapeAmp="false" namespace="/staff">
					</s:url>
					<sj:a href="%{doViewTaskInfo}" cssClass="btn default"
						indicator="indicator" targets="mainContentDiv">
								Cancel</sj:a>
				</div>
			</div>

			<div class="col-md-6" id="remionderSubmitDiv" style="display: none;">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						id="clickSubmit1" onBeforeTopics="validateImageFormat"
						validate="true" indicator="indicator" targets="taskInfoContentDiv"
						formIds="addTaskInfromation" />
					<s:url id="doViewReminderInfo" action="ajaxTaskInformationHome"
						includeParams="all" escapeAmp="false" namespace="/staff">
					</s:url>
					<sj:a href="%{doViewReminderInfo}" cssClass="btn default"
						indicator="indicator" targets="mainContentDiv">
								Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
	$(document).ready(function() {
		FormComponents.init();
		FormAdvanced.init();
		UIExtendedModals.init();
		$("input:checkbox, input:radio:not('.toggle')").uniform();
		var selected = $('input[name=selectType]:radio:checked').val();
		var reminderOption = $('#reminderSelect:radio:checked').val();
		var reminderOptionForTask = $('#reminderSelectForTask:radio:checked').val();
		handleClick(selected);
		checkReminderOption(reminderOption);
		checkReminderOptionForTask(reminderOptionForTask);
		$.destroyTopic('validateImageFormat');
	});
	function handleClick(value) {
		if (isNonEmpty(value)) {
			$("#tempString").val(value);
			if ("T" == value) {
				$('#reminderDiv').hide();
				$('#taskInformationDiv').show();
				$('#remionderSubmitDiv').hide();
				$('#taskSubmitDiv').show();
			}else if ("R" == value) {
				$('#taskInformationDiv').hide();
				$('#reminderDiv').show();
				$('#remionderSubmitDiv').show();
				$('#taskSubmitDiv').hide();
			}
		}
	}
	function checkReminderOption(value) {
		if (isNonEmpty(value)) {
			if ("E" == value) {
				$('#specificDateDiv').hide();
				$('#specificDate').removeClass("required");
			}else if ("S" == value) {
				$('#specificDateDiv').show();
				$('#specificDate').addClass("required");
			}
		}
	}
	function checkReminderOptionForTask(value) {
		if(isNonEmpty(value)) {
			if("E" == value) {
				$('#specificDateDivForTask').hide();
				$('#specificDateForTask').removeClass("required");
			}else if("S" == value) {
				$('#specificDateDivForTask').show();
				$('#specificDateForTask').addClass("required");
			}
		}
	}
	
	$.subscribe('validateImageFormat',function(event, data) {
		var selected = $('input[name=selectType]:radio:checked').val();
		if(selected == "R"){
			if($("#checkMobileService").prop('checked') == true || $("#checkEmailService").prop('checked') == true){
			    return true;
			}else{
				alert("Please select atleast one comminication type!.");
				event.originalEvent.options.submit = false;
			}
		}else if(selected == "T"){
			if($("#checkMobileServiceForTask").prop('checked') == true || $("#checkEmailServiceForTask").prop('checked') == true){
				return true;
			}else{
				alert("Please select atleast one comminication type!.");
				event.originalEvent.options.submit = false;
			}
		}
	});
</script>
