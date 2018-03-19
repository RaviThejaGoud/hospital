<%@ include file="/common/taglibs.jsp"%>
<div class="grid_13">
	<s:form id="selectStaffLoanForm5" theme="css_xhtml"
		cssStyle="margin-left:18px;">
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
	<s:if test="%{staffLoanDetails != null && !staffLoanDetails()}">
		<s:if test="%{viewStaffPersonAccountDetails != null}">
			<div id="tabWrapper13">
				<s:form action="ajaxAddPayrollSettingsForStaff" theme="css_xhtml"
					id="editStaffDetails" method="post" enctype="multipart/form-data" namespace="/admin">
					<s:hidden name="tempId"
						value="%{viewStaffPersonAccountDetails.staffId}" />
					<s:hidden name="tempString"
						value="%{viewStaffPersonAccountDetails.roleName}" />
					<div class="grid_12">

						<div id="commonStep13">
							<fieldset id="stepAttendance">
								<div class="grid_14">
									<div id="viewStaffLoanDetailsId" class="grid_13">
										<div class="grid_12 th">
											<div class="grid_2 amountDiv sortHeader divArrow">
												Loan Amount
											</div>
											<div class="grid_2 installDiv sortHeader divArrow">
												Installments
											</div>
											<div class="grid_3 eachInstallDiv sortHeader divArrow">
												Each Installment
											</div>
											<div class="grid_3">
												Loan Created Date
											</div>
											<div class="grid_2">
												Status
											</div>
										</div>
										<div id="loanAmountDiv">
										<s:iterator value="staffLoanDetails">
										  <div loanAmount="<s:property value='staffLoanDetails.loanAmount' />" Installment="<s:property value='staffLoanDetails.installments' />"  installmentAmt="<s:property value='staffLoanDetails.installmentAmount' />"  createdDate="<s:property value='staffLoanDetails.loanTakenDateStr' />"  class="item">
											<div class="grid_12 row">
												<div class="grid_2">
													<s:url id="urlStaffEmailDetails"
														action="ajaxGetEmiDetailsForStaff" includeParams="all"
														escapeAmp="false" namespace="/admin">
														<s:param name="staffId"
															value="%{viewStaffPersonAccountDetails.staffId}"></s:param>
														<s:param name="loanId" value="staffLoanDetails.id"></s:param>
													</s:url>
													<sj:a id="viewClassSec" href="%{urlStaffEmailDetails}"
														targets="stepAttendance">
														<s:property value="staffLoanDetails.loanAmount" />
													</sj:a>
												</div>
												<div class="grid_2">
													<s:property value="staffLoanDetails.installments" />
												</div>
												<div class="grid_3">
													<s:property value="staffLoanDetails.installmentAmount" />
												</div>
												<div class="grid_3">
													<s:property value="staffLoanDetails.loanTakenDateStr" />
												</div>
												<div class="grid_2">
													<s:property value="staffLoanDetails.loanStatus" />
												</div>
											</div>
											</div>
										</s:iterator>
										</div>
									</div>
								</div>
							</fieldset>
						</div>
					</div>
				</s:form>
			</div>
		</s:if>
	</s:if>
	<s:else>
	   There is no loan for this staff.
	</s:else>
</div>

<script type="text/javascript">
$(document).ready(
		function() {
			commonLoadTab();
			$("#tabNavigation ul li").live('click', function(e) {
				$('#staffDetailsForAdmin').hide();
			});

			if ($('#tabNavigation ul li').hasClass("selected").toString()) {
				genericAjaxCall($('#tabNavigation ul li a').attr('id'), $(
						'#tabNavigation ul li a').attr('type'), $(
						'#tabNavigation ul li a').attr('class'));
			}
			$.subscribe('changeStaffInfoFormValidation', function(event, data) {

				if (($("input[name=chkBoxSelectedIds]:checked").length) > 0) {
					var classIds = $("input[name=chkBoxSelectedIds]:checked");
					var selectedClassIds = '';
					if (classIds.length > 0) {
						selectedClassIds = '(';
						for ( var i = 0; i < classIds.length; i++) {
							selectedClassIds += classIds[i].value + ', ';
						}
						selectedClassIds += '0)';
					}
					$("#classIds").val(selectedClassIds);
					/*{
					    	for ( var i = 0; i < selectedClassIds.length; i++) 
					    	{
					    		var percentage = document.getElementsByName("percentage"+selectedClassIds[i].value);
					    		if(percentage == null)
					    		{
					    		
					    		}
					                selectedClassIds += selectedClassIds[i].value + ', ';
					      }
					}*/
					return true;
				} else {
					alert("Please Select at least One Payroll Type");
					return false;
				}
				/*// alert($('div.staffDetailsForAdmin').children('div.block').children('div.message success').find('span.close1').size());*/
				$('#viewStaffPersondetails').hide();
				if ($('#editStaffDetails').valid()) {
					return true;
				} else
					return false;
			});
			var eremove = $('#staffDetailsForAdmin').find('span:first');
			eremove.removeClass('close1');

		});
function getStaffByRoleId(roleName) {
	var params = "tempString=" + roleName + "&stLoan=stLoan";
	$('#staffLoanSettings')
			.html(
					'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : jQuery.url.getChatURL("/staff/ajaxGetStaffByRoleForLoan.do"),
		cache : true,
		data : params,
		success : function(response) {
			$('#staffLoanSettings').html(response);
		}
	});
}
function getStaffDetails(staffId) {
	var role = $('#staffRole').val()
	if (isNonEmpty(role)) {
		var params = "tempString=" + role + "&tempId=" + staffId
				+ "&stLoan=stLoan";
		$('#staffLoanSettings')
				.html(
						'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/staff/ajaxGetStaffByRoleForLoan.do"),
			cache : true,
			data : params,
			success : function(response) {
				$('#staffLoanSettings').html(response);
			}
		});
	}
}
function changeStaffStatus(type) {
	if ($('input#checkedStaff').is(':checked')) {
		$('#staffStatus').show();
	} else {
		$('#staffStatus').hide();
	}
}


var amount=1;
$('div.amountDiv').click(function () {
    $('div#loanAmountDiv>div.item').sortElements(function (a, b) {return (parseInt($(a).attr('loanAmount')) > parseInt($(b).attr('loanAmount')) ? 1 : -1) * amount; });
    updateDirectionArrows($(this), amount);
    $("#loanAmountDiv").pagination();
    amount = amount * -1;
    return false;
});


var Installment=1;
$('div.amountDiv').click(function () {
    $('div#loanAmountDiv>div.item').sortElements(function (a, b) {return (parseInt($(a).attr('Installment')) > parseInt($(b).attr('Installment')) ? 1 : -1) * Installment; });
    updateDirectionArrows($(this), Installment);
    $("#loanAmountDiv").pagination();
    Installment = Installment * -1;
    return false;
});

var installmentAmt =1;
$('div.amountDiv').click(function () {
    $('div#loanAmountDiv>div.item').sortElements(function (a, b) {return (parseInt($(a).attr('installmentAmt')) > parseInt($(b).attr('installmentAmt')) ? 1 : -1) * installmentAmt; });
    updateDirectionArrows($(this), installmentAmt);
    $("#loanAmountDiv").pagination();
    installmentAmt = installmentAmt * -1;
    return false;
});

var date=1;
$('div.amountDiv').click(function () {
    $('div#loanAmountDiv>div.item').sortElements(function (a, b) {return ($(a).attr('createdDate') >  $(b).attr('createdDate') ? 1 : -1) * date; });
    updateDirectionArrows($(this), date);
    $("#loanAmountDiv").pagination();
    date = date * -1;
    return false;
});
</script>