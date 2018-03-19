<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<s:form action="ajaxDoAddOnlineAppoinments" id="addOnlineAppointment"
	method="post" theme="simple" namespace="/student"
	cssClass="form-horizontal">
	<%@ include file="/common/messages.jsp"%>
	<s:hidden name="tempId" value="%{appointment.id}"></s:hidden>
	
	<s:hidden name="appointment.studentAccountId" value="%{#session.selectedStudentId}"></s:hidden>
	<div class='error' style='display: none;'> <span></span> </div>
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Select Staff :
					</label>
					<div class="col-md-7">
						<s:if
							test="%{customerNamesSet != null && !customerNamesSet.isEmpty()}">
							<s:select id="appointmentId" list="customerNamesSet"
								cssClass="required form-control input-medium required"
								listKey="roleIdAndAccountId" listValue="personNameWithRoleDesc"
								headerKey="" headerValue="- Select -" name="anyTitle"
								theme="simple" />
						</s:if>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required"></span>
					</label>
					<div class="col-md-8"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Subject :
					</label>
					<div class="col-md-8">
						<sj:textfield id="lastName" name="appointment.subject" size="50"
							maxlength="50" cssClass="form-control input-medium required" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">Schedule Date : </label>
					<div class="col-md-7">
						<div class="input-group input-medium date date-picker"
							data-date-end-date="">
							<input type="text" readonly="readonly" class="form-control" required="required"
								value='<s:property value="appointment.scheduleDtaeStr"/>' required="required"
								name="appointment.scheduleDate"> <span
								class="dateInput input-group-btn">
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
			<s:if test="%{tempId > 0 }">
				<div class="col-md-6">
						<div class="form-group">
						<label class="col-md-4 control-label"><span
							class="required">*</span> Select Time : </label>
						<div class="col-md-8">
							<div class="input-group bootstrap-timepicker col-md-9">
								<input type="text"
									class="form-control timepicker-default startTimeChange"
									value="<s:property value="appointment.scheduleTime" />"
									required="required" name="appointment.scheduleTime"> <span
									class="dateInput input-group-btn">
									<button class="btn default" type="button">
										<i class="fa fa-clock-o"></i>
									</button>
								</span>
							</div>
						</div>
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label"><span
							class="required">*</span> Select Time : </label>
						<div class="col-md-8">
							<div class="input-group bootstrap-timepicker col-md-9">
								<input type="text"
									class="form-control timepicker-default startTimeChange"
									value="<s:property value="appointment.scheduleTime" />"
									required="required" name="appointment.scheduleTime"> <span
									class="dateInput input-group-btn">
									<button class="btn default" type="button">
										<i class="fa fa-clock-o"></i>
									</button>
								</span>
							</div>
						</div>
					</div>
				</div>
			</s:else>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> <span
						class="required">*</span>Email Address :
					</label>
					<div class="col-md-7">
						<sj:textfield id="email" size="30" maxlength="120"
							name="appointment.email"
							cssClass="form-control input-medium required email" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Mobile Number :
					</label>
					<div class="col-md-8">
						<sj:textfield id="mobileNumber" size="30" maxlength="10"
							name="appointment.mobileNumber"
							onchange="javascript:validateMobNumbers(this.value)" 
							cssClass="form-control input-medium required numeric" />
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2"> <span class="required">*</span>
				Description :
			</label>
			<div class="col-md-8">
				<sj:textarea rows="3" cols="20" id="description"
					name="appointment.description" maxCharsData="500" tabindex="3"
					cssClass="required form-control word_count"></sj:textarea>
				<span class="help-block">
					<div class="counter"></div>
				</span>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-5 col-md-9">
					<sj:submit targets="mainContentDiv" value="Submit" cssClass="btn blue"
						validate="true" formIds="addOnlineAppointment" />
					<s:url id="doCancel" action="ajaxViewOnlineAppointment"
						includeParams="all" namespace="/student"></s:url>
					<sj:a href="%{doCancel}" cssClass="btn default"
						indicator="indicator" targets="mainContentDiv" button="false">Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script  type="text/javascript">
	$(document).ready(function() {
		FormComponents.init();
	});
	function validateMobNumbers(txtMobId) {
		/* var mob = /^(\+91-|\+91|0)?\d{10}$/;
		if (mob.test($.trim(txtMobId)) == false) {
			alert("Please enter valid mobile number.");
			$("#mobileNumber").val('');
			$("#mobileNumber").focus();
			return false;
		} else  */if (!(txtMobId.length == 10)) {
			alert("The phone number is the wrong length. \nPlease enter 10 digit mobile no.");
			$("#mobileNumber").val("");
			$("#mobileNumber").focus();
			return false;
		} else if ((txtMobId.length == 10)) {
			return true;
		}
	}
</script>
