<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-481"> </span>
				</div>
			</div>
			<div class="portlet-body" id="studRegister">
					<div class="tab-content" id="studRegister">
						<s:form id="downloadTemplate" cssClass="form-horizontal"
							action="ajaxDownloadSelectedStaffDetails" method="post"
							theme="simple" namespace="/reports"
							onsubmit="javascript:return generateStaffIds();">
							<h4 class="bold pageTitle">
								Download Staff Details
							</h4>
							<div class="form-body">
								<p>
									<span class="label label-danger">NOTE :</span>&nbsp;&nbsp;
									Please select the fields which you want to show in the report
									and click " Download" button to download the report.
								</p>
								<div class="spaceDiv"></div>
								<s:hidden id="selectedColumnNames" name="anyId" />
								<s:hidden id="selectedRoleNames" name="selectedId" />
								<div class="form-group">
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" name="" value=""
													onClick="allRoleNames()" class="checkbox userRoleNames">
												All Role Names
											</label>
										</div>
										<s:checkboxlist name="roleNameChkBoxSelectedIds"
											list="objectList" listKey="roleId"
											listValue="roleDescription" theme="ems" cssClass="small" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" name="" value=""
													onClick="allStaffFileds()" class="checkbox staffFileds">
												All Staff Fields
											</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<div class="col-md-3">
											<input type="checkbox" value="roleDescription"
												name="columnNameChkBoxSelectedIds" />
											RoleName
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="firstName"
												name="columnNameChkBoxSelectedIds" />
											First Name
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="lastName"
												name="columnNameChkBoxSelectedIds" />
											Last Name
										</div>
										<!-- <div class="col-md-3">
											<input type="checkbox" value="middleName"
												name="columnNameChkBoxSelectedIds" />
											Initial
										</div>-->
										<div class="col-md-3">
											<input type="checkbox" value="dateOfBirth"
												name="columnNameChkBoxSelectedIds" />
											Date Of Birth
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="dateOfJoining"
												name="columnNameChkBoxSelectedIds" />
											Date Of Joining
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="gender"
												name="columnNameChkBoxSelectedIds" />
											Gender
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="bloodGroup"
												name="columnNameChkBoxSelectedIds" />
											Blood Group
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="maritalStatus"
												name="columnNameChkBoxSelectedIds" />
											MaritalStatus
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="qualification1"
												name="columnNameChkBoxSelectedIds" />
											Qualification
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="email"
												name="columnNameChkBoxSelectedIds" />
											Email
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="city"
												name="columnNameChkBoxSelectedIds" />
											City
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="state"
												name="columnNameChkBoxSelectedIds" />
											State
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="postalCode"
												name="columnNameChkBoxSelectedIds" />
											Postal Code
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="panNumber"
												name="columnNameChkBoxSelectedIds" />
											Pan Number
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="gpfNumber"
												name="columnNameChkBoxSelectedIds" />
											Gpf Number
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="officeNumber"
												name="columnNameChkBoxSelectedIds" />
											Office Number
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="mobileNumber"
												name="columnNameChkBoxSelectedIds" />
											Mobile Number
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="salary"
												name="columnNameChkBoxSelectedIds" />
											Salary
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="castName"
												name="columnNameChkBoxSelectedIds" />
											Caste
										</div>
										<!-- <div class="col-md-3">
											<input type="checkbox" value="subCastName"
												name="columnNameChkBoxSelectedIds" />
											Sub Caste
										</div> -->
										<div class="col-md-3">
											<input type="checkbox" value="bankName"
												name="columnNameChkBoxSelectedIds" />
											Bank Name
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="bankAccountNumber"
												name="columnNameChkBoxSelectedIds" />
											Bank Account Number
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="familyDoctor"
												name="columnNameChkBoxSelectedIds" />
											FamilyDoctor
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="prefferedHospital"
												name="columnNameChkBoxSelectedIds" />
											Preffered Hospital
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="phoneNumber"
												name="columnNameChkBoxSelectedIds" />
											Phone Number
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="designation"
												name="columnNameChkBoxSelectedIds" />
											Designation
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="religion"
												name="columnNameChkBoxSelectedIds" />
											Religion
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="nationality"
												name="columnNameChkBoxSelectedIds" />
											Nationality
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="motherTounge"
												name="columnNameChkBoxSelectedIds" />
											Mother Tounge
										</div>
										<div class="col-md-3">
											<input type="checkbox" value="ifscCode"
												name="columnNameChkBoxSelectedIds" />
											IFSC Code
										</div>
									</div>
								</div>
								<div class="spaceDiv"></div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<s:submit type="submit small" value="Download Excel"
											cssClass="submitBt btn blue long"
											title="Downlaod excel report" />
									</div>
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
</div>
<script type="text/javascript">
$(document).ready(
		function() {
			$("input:checkbox, input:radio").uniform();
			$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim()+ "-->"
							+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			changePageTitle(title);
		});
function generateStaffIds() {
	if ($("input[name=roleNameChkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one role.");
		return false;
	}
	if ($("input[name=columnNameChkBoxSelectedIds]:checked").length < 2) {
		alert("Please select at least two fields names.");
		return false;
	} else {
		var columnNameIds = $("input[name=columnNameChkBoxSelectedIds]:checked");
		var selectedColumnNameIds = [];
		if (columnNameIds.length > 0) {
			for ( var i = 0; i < columnNameIds.length; i++) {
				if (isNonEmpty(columnNameIds[i].value))
					selectedColumnNameIds.push(columnNameIds[i].value.trim());
			}
		}
		$("#selectedColumnNames").val(selectedColumnNameIds);
		var roleNameIds = $("input[name=roleNameChkBoxSelectedIds]:checked");
		var selectedRoleIds = [];
		if (roleNameIds.length > 0) {
			selectedRoleIds.push('(0');
			for ( var i = 0; i < roleNameIds.length; i++) {
				if (isNonEmpty(roleNameIds[i].value))
					selectedRoleIds.push(roleNameIds[i].value.trim());
			}
			selectedRoleIds.push('0)');
		}
		$("#selectedRoleNames").val(selectedRoleIds);
		return true;
	}
}

function allStaffFileds() {
	if ($(".staffFileds").is(':checked')) {
		$("[name='columnNameChkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='columnNameChkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
$("input[name=columnNameChkBoxSelectedIds]").click(function() {
	if ($("input[name=columnNameChkBoxSelectedIds]:unchecked").length > 0) {
		$(".staffFileds").parent('span').removeClass("checked");
		$(".staffFileds").attr("checked", false);
	} else {
		$(".staffFileds").parent('span').addClass("checked");
		$(".staffFileds").attr("checked", true);
	}
});
function allRoleNames() {
	if ($(".userRoleNames").is(':checked')) {
		$("[name='roleNameChkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='roleNameChkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
$("input[name=roleNameChkBoxSelectedIds]").click(function() {
	if ($("input[name=roleNameChkBoxSelectedIds]:unchecked").length > 0) {
		$(".userRoleNames").parent('span').removeClass("checked");
		$(".userRoleNames").attr("checked", false);
	} else {
		$(".userRoleNames").parent('span').addClass("checked");
		$(".userRoleNames").attr("checked", true);
	}
});
</script>