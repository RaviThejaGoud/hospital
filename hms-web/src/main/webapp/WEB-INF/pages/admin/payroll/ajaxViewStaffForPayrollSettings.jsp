<%@ include file="/common/taglibs.jsp"%>
<div class="grid_13">
	<div id="staffDetailsForAdmin" class="commomnTabs">
		<jsp:include page="/common/messages.jsp"></jsp:include>
	</div>
	<s:form id="selectStudentForm" theme="css_xhtml"
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
	<div id="tabWrapper13">
		<s:form action="ajaxAddPayrollSettingsForStaff" theme="css_xhtml"
			id="editStaffDetails" method="post" enctype="multipart/form-data" namespace="/admin">
			<div class="grid_12">
				<div id="commonStep13">
					<s:if
						test="%{payrollSettingsList != null && !payrollSettingsList.isEmpty()}">
						<s:hidden name="tempId" value="%{payrollSettings.staffId}" />
						<fieldset id="stepAttendance">
							<div class="grid_15">
								<div id="editClassSubjects" class="grid_13">
									<div class="grid_12 th">
										<div class="grid_2 payNameDiv sortHeader divArrow">
											Payroll Name ddd
										</div>
										<div class="grid_2 salaryDiv sortHeader divArrow">
											Basic Salary
										</div>
										<div class="grid_2 perNameDiv sortHeader divArrow">
											Percentage
										</div>
										<div class="grid_3">
											Allowance/Deduction
										</div>
										<div class="grid_2  monthNameDiv sortHeader divArrow">
											Month
										</div>
										<div class="grid_1  yearNameDiv sortHeader divArrow" style="width: 50px;">
											Year
										</div>
									</div>
									<div id="payRollSalaryDiv">
										<s:iterator value="payrollSettingsList">
										 <div payrollName="<s:property value='payrollName' />" payrollType="<s:property value='payrollType' />"  percentage="<s:property value='percentage' />" month="<s:property value='month' />" year="<s:property value='year' />" class="item">
											<div class="grid_12 row">
												<div class="grid_2">
													<s:property value="payrollName" />
												</div>
												<div class="grid_2">
													<s:if test="%{payrollType == 'BS'}">
														<s:property value="basicSalary" />
													</s:if>
													<s:else>
													&nbsp;
												</s:else>
												</div>

												<div class="grid_2">
													<s:property value="percentage" />
												</div>
												<div class="grid_3">
													<s:property value="payrollTypeDesc" />
												</div>
												<div class="grid_2">
													<s:property value="month" />
												</div>
												<div class="grid_1">
													<s:property value="year" />
												</div>
											</div>
											</div>
										</s:iterator>
									</div>
								</div>
							</div>
						</fieldset>
					</s:if>
					<s:else>
					    There is no payroll setting for this staff.
					</s:else>
				</div>
			</div>
		</s:form>
	</div>
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
	var params = "tempString=" + roleName;
	$('#payrollTypesSettings')
			.html(
					'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : jQuery.url
				.getChatURL("/staff/ajaxGetStaffByRoleForPayrollSettings.do"),
		cache : true,
		data : params,
		success : function(response) {
			$('#payrollTypesSettings').html(response);
		}
	});
}
function getStaffDetails(staffId) {
	var role = $('#staffRole').val()
	if (isNonEmpty(role)) {
		var params = "tempString=" + role + "&tempId=" + staffId;
		$('#payrollTypesSettings')
				.html(
						'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$
				.ajax( {
					url : jQuery.url
							.getChatURL("/staff/ajaxGetStaffByRoleForPayrollSettings.do"),
					cache : true,
					data : params,
					success : function(response) {
						$('#payrollTypesSettings').html(response);
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

var name=1;
$('div.payNameDiv').click(function () {
     $('div#payRollSalaryDiv>div.item').sortElements(function (a, b) {return ($(a).attr('payrollName').toLowerCase() > $(b).attr('payrollName').toLowerCase() ? 1 : -1) * name;});
    updateDirectionArrows($(this), name);
    //$("#payRollSalaryDiv").pagination();
    name= name* -1;
    return false;
});

var dis=1;
$('div.salaryDiv').click(function () {
     $('div#payRollSalaryDiv>div.item').sortElements(function (a, b) {return ($(a).attr('payrollType').toLowerCase() > $(b).attr('payrollType').toLowerCase() ? 1 : -1) * dis;});
    updateDirectionArrows($(this), dis);
   // $("#payRoleDiv").pagination();
    dis= dis* -1;
    return false;
});

var type=1;
$('div.perNameDiv').click(function () {
     $('div#payRollSalaryDiv>div.item').sortElements(function (a, b) {return (parseFloat($(a).attr('percentage')) > parseFloat($(b).attr('percentage')) ? 1 : -1) * type; });
    updateDirectionArrows($(this), type);
    //$("#payRoleDiv").pagination();
    type= type* -1;
    return false;
});


var month=1;
$('div.monthNameDiv').click(function () {
    $('div#payRollSalaryDiv>div.item').sortElements(function (a, b) {return (parseInt($(a).attr('month')) > parseInt($(b).attr('month')) ? 1 : -1) * month; });
    updateDirectionArrows($(this), month);
    //$("#subjectsPaginationCont").pagination();
    month = month * -1;
    return false;
});


var year=1;
$('div.yearNameDiv').click(function () {
    $('div#payRollSalaryDiv>div.item').sortElements(function (a, b) {return (parseInt($(a).attr('year')) > parseInt($(b).attr('year')) ? 1 : -1) * year; });
    updateDirectionArrows($(this), year);
    //$("#subjectsPaginationCont").pagination();
    year = year * -1;
    return false;
});


</script>