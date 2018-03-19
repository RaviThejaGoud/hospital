<%@ include file="/common/taglibs.jsp"%>
<div class="grid_13">
	<s:form id="selectStaffLoanForm1" theme="css_xhtml">
		<div class="grid_6">
			<div class="tableactions">
				<div class="grid_2 alpha">
					<label class="right">
						Select Role:
					</label>
				</div>
				<s:select list="staffRoles" id="staffRole"
					name="viewStaffPersonAccountDetails.roleName" theme="css_xhtml"
					onchange="javascript:getStaffByRoleId(this.value);" tabindex="1">
				</s:select>
			</div>
		</div>
		<div class="grid_6">
			<s:if test="%{staffsList != null && !staffsList.isEmpty()}">
				<div class="tableactions">
					<div class="grid_2 alpha">
						<label class="right">
							Select Staff:
						</label>
					</div>
					<s:select list="staffsList" id="staffName"
						name="viewStaffPersonAccountDetails.staffId" listKey="staffId"
						listValue="personFullName" headerKey="" headerValue="- Select -"
						theme="css_xhtml" tabindex="2"
						onchange="javascript:getStaffDetails(this.value);">
					</s:select>
				</div>
			</s:if>
		</div>
		<s:else>
			<div class="noteFont grid_12">
				<br />
				<div class="grid_1">
					<span class="noteMassage"> Note :</span>
				</div>
				<div class="grid_11">
					Currently there are no staff assign to this particular Role.
				</div>
			</div>
		</s:else>
	</s:form>
</div>
<div class="grid_13">
	&nbsp;
</div>
<div class="grid_12 commomnTabs">
	<s:if test="%{viewStaffPersonAccountDetails != null}">
		<div id="tabWrapper13">
			<s:form action="ajaxAddLoanForStaff" theme="css_xhtml"
				id="loanStaffDetails" method="post" enctype="multipart/form-data" namespace="/admin">
				<s:hidden name="tempId"
					value="%{viewStaffPersonAccountDetails.staffId}" />
				<s:hidden name="tempString"
					value="%{viewStaffPersonAccountDetails.roleName}" />
				<div class="grid_12">
					<div class="grid_9">
						<div class="grid_6">
							<div class="grid_6">
								<div class="grid_3">
									<label class="labelRight">
										Staff Name:
									</label>
								</div>
								<div class="grid_3">
									<s:property
										value="viewStaffPersonAccountDetails.personFullName" />
								</div>
							</div>
						</div>
						<div class="grid_6">
							<div class="grid_3">
								<label class="labelRight">
									Gender:
								</label>
							</div>
							<div class="grid_3">
								<s:property value="viewStaffPersonAccountDetails.genderStr" />
							</div>
						</div>
						<div class="grid_6">
							<div class="grid_3">
								<label class="labelRight">
									Date of Birth:
								</label>
							</div>
							<div class="grid_3">
								<s:property value="viewStaffPersonAccountDetails.dateOfBirthStr" />

							</div>
						</div>
						<div class="grid_6">
							<div class="grid_3">
								<label class="labelRight">
									Date of Joining:
								</label>
							</div>
							<div class="grid_3">
								<s:property
									value="viewStaffPersonAccountDetails.staffDateOfJoing" />
							</div>
						</div>
					</div>
					<div class="grid_3">
						<img
							src='<c:url value="${viewStaffPersonAccountDetails.adjustedAttachmentFilePath}"/>'
							alt='<s:property  value="viewStaffPersonAccountDetails.personFullName" />'
							border="0" height="102px;" />
					</div>
				</div>
				<div class="grid_12">
					<div class="grid_6">
						<div class="grid_3">
							<label class="labelRight">
								<span class="required">*</span>Loan Amount:
							</label>
						</div>
						<div class="grid_3">
							<sj:textfield name="staffLoanDetails.loanAmount" id="loanAmount"
								tabindex="1" cssClass="numeric textfield required" cssStyle="width:150px;"
								maxlength="11"></sj:textfield>
						</div>
					</div>
					<!--<div class="grid_6">
							<sj:datepicker id="loanTakenDate" name="staffLoanDetails.loanTakenDate" label="Start Date"
								cssClass="textfield required" changeMonth="true" readonly="true" 
								changeYear="true" />
						</div>
						-->
					<div class="grid_6">
						<div class="grid_3">
							<label class="labelRight">
							<span class="required">*</span>	Installments:
							</label>
						</div>
						<div class="grid_3">
							<sj:textfield name="staffLoanDetails.installments"
								id="installments" tabindex="2" cssStyle="width:150px;"
								cssClass="textfield required numeric" maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="grid_12">
						<div class="grid_3">
							<label class="labelRight">
								Description:
							</label>
						</div>
						<div class="grid_9">
							<sj:textarea name="staffLoanDetails.loanDescription"
								id="loanDescription" tabindex="3" cols="5" rows="5" maxlength="160"></sj:textarea>
						</div>
					</div>
				<div class="grid_6">
					&nbsp;
				</div>
				<div class="grid_6">
					<s:if
						test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
						<sj:submit   cssClass="submit small" value="Save" validate="true"
							targets="staffLoanSettings" formIds="loanStaffDetails" indicator="indicator"/>
					</s:if>
				</div>
			</s:form>
		</div>
	</s:if>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
});
function getStaffByRoleId(staffRole) {
	var pars = "tempString=" + staffRole;
	$("#staffLoanSettings")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/staff/ajaxDoGetStaffByRoleForLoan.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#staffLoanSettings").html(response);
		}
	});
}

function getStaffDetails(staffId) {
	var role = $('#staffRole').val()
	if (isNonEmpty(role)) {
		var params = "tempString=" + role + "&tempId=" + staffId;
		$('#staffLoanSettings')
				.html(
						'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url
					.getChatURL("/staff/ajaxDoGetStaffByRoleForLoan.do"),
			cache : true,
			data : params,
			success : function(response) {
				$('#staffLoanSettings').html(response);
			}
		});
	}
}
</script>