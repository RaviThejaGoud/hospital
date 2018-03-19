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
	<s:hidden name="reminderVO.id" value="%{reminderVO.id}" id="reminderVOId" />
	<s:hidden name="tempString" id="tempString"></s:hidden>
	<div class="form-body">
		<p>
			<span class="label label-danger"> NOTE : </span>&nbsp; You can add " Reminder " Details through this process.
		</p>
		<h4 class="pageTitle bold form-section">Reminder INFORMATION</h4>
		<s:if test="%{reminderVO.id == 0}"> 
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label">Select Type : </label>
						<div class="col-md-6">
							<div class="radio-list">
								<label class="radio-inline"> <input type="radio"
									value="R" id="reminder" checked="checked" name="selectType" class="radio"
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
								<label class="radio-inline"> <input type="radio" disabled="disabled"
									value="R" id="reminder" checked="checked" name="selectType" class="radio" 
									onclick="handleClick(this.value);"> Remainder
								</label>
							</div>
						</div>
					</div>

				</div>
			</div>
		</s:else> 
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
									type="text" class="form-control required"  value='<s:property value="reminderVO.expirationDateStr"/>'>
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
									value="E" id="reminderSelectId" checked="checked" onclick="checkReminderOption(this.value);"
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
									type="text" class="form-control required"  value='<s:property value="reminderVO.specificDateStr"/>'>
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
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						id="clickSubmit" onBeforeTopics="validateImageFormat"
						validate="true" indicator="indicator" targets="taskInfoContentDiv"
						formIds="addTaskInfromation" />
					<s:url id="doViewTaskInfo" action="ajaxReminderInformation"
						includeParams="all" escapeAmp="false" namespace="/staff">
					</s:url>
					<sj:a href="%{doViewTaskInfo}" cssClass="btn default"
						indicator="indicator" targets="taskInfoContentDiv">
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
		handleClick(selected);
		checkReminderOption(reminderOption);
		$.destroyTopic('validateImageFormat');
		if('<s:property value='reminderVO.specificDateStr'/>'!=''){
			$('#reminderSelect').parent('span').addClass('checked');
			$('#reminderSelect').attr("checked", true);
			$('#reminderSelectId').parent('span').removeClass('checked');
			$('#reminderSelectId').attr("checked", false);
			$('#specificDateDiv').show();
		}else{
			$('#reminderSelectId').parent('span').addClass('checked');
			$('#reminderSelectId').attr("checked", true);
			$('#reminderSelect').parent('span').removeClass('checked');
			$('#reminderSelect').attr("checked", false);
			$('#specificDateDiv').hide();
		}
		//For setting checkbox values 
		if (<s:property value='reminderVO.checkEmailService'/>) {
			$('#checkEmailService').parent('span').addClass('checked');
			$('#checkEmailService').attr("checked", true);
		} else {
			$('#checkEmailService').parent('span').removeClass('checked');
			$('#checkEmailService').attr("checked", false);
		}
		
		if (<s:property value='reminderVO.checkMobileService'/>) {
			$('#checkMobileService').parent('span').addClass('checked');
			$('#checkMobileService').attr("checked", true);
		} else {
			$('#checkMobileService').parent('span').removeClass('checked');
			$('#checkMobileService').attr("checked", false);
		}
	});
	function handleClick(value) {
		if (isNonEmpty(value)) {
			$("#tempString").val(value);
			if ("T" == value) {
				$('#reminderDiv').hide();
				$('#taskInformationDiv').show();
			}else if ("R" == value) {
				$('#taskInformationDiv').hide();
				$('#reminderDiv').show();
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
	$.subscribe('validateImageFormat',function(event, data) {
		var selected = $('input[name=selectType]:radio:checked').val();
		if(selected == "R"){
			if($("#checkMobileService").prop('checked') == true || $("#checkEmailService").prop('checked') == true){
			    return true;
			}else{
				alert("Please select atleast one comminication type!.");
				event.originalEvent.options.submit = false;
			}
		}
	});
	
</script>
