<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>

<div id="commonStep13">
	<fieldset id="stepAttendance" >
		<div class="grid_14">
			<div id="editClassSubjects" class="grid_13">
			<%@ include file="/common/messages.jsp"%>
	<s:if test="%{viewStaffPersonAccountDetailsList!= null && !viewStaffPersonAccountDetailsList.isEmpty()}">
		<div class="grid_13 th">
		<div class="grid_3">
				<div class="grid_2 staffNameDiv sortHeader divArrow">
					Staff Name
				</div>
				</div>
				<div class="grid_2 roleNameDiv sortHeader divArrow">
					Role
				</div>
				<div class="grid_2 salaryNameDiv sortHeader divArrow">
					Salary
				</div>
				<div class="grid_2  dateDiv sortHeader divArrow">
					Date of Joining
				</div>
				<div class="grid_2 expDiv sortHeader divArrow">
					No of Exp
				</div>
				<div class="grid_2">
					Details
				</div>
         </div>
         <div id="payRoleDiv">
         <s:iterator value="viewStaffPersonAccountDetailsList">
          <div personFullName="<s:property value='personFullName' />" roleDescription="<s:property value='roleDescription' />" salary="<s:property value='salary.salary' />" staffDateOfJoing="<s:property value='staffJoingDate' />" staffExperiencestr="<s:property value='staffExperiencestr' />" class="item">
         	<div class="grid_13 row">
					<div class="grid_3" >
						<s:property value="personFullName" />
					</div>
					<div class="grid_2" >
						<s:property value="roleDescription" />
					</div>
					<div class="grid_2">
						<s:if test="%{salary.salary!= null}">
							<s:property value="salary.salary" />
						</s:if>
						<s:else>
							--
						</s:else>
						
					</div>
					<div class="grid_2">
						<s:if test="%{staffDateOfJoing!= null}">
							<s:property value="staffDateOfJoing" />
						</s:if>
						<s:else>
							--
						</s:else>
					</div>
					<div class="grid_2">
						<s:if test="%{staffExperiencestr!= null}">
							<s:property value="staffExperiencestr" />
						</s:if>
						<s:else>
							--
						</s:else>
					</div>
					<div class="grid_2">
						<s:url id="doViewStaffPayroll" action="ajaxDoViewRecentStaffPayroll"
							includeParams="all" escapeAmp="false" namespace="/admin">
							<s:param name="staffId" value="%{staffId}" />
						</s:url>
						<sj:a href="%{doViewStaffPayroll}" targets="payrollGeneration">Payroll
						</sj:a>
					</div>
			</div>
			</div>
		</s:iterator>
		</div>
	</s:if>
	<s:else>
		<div class="examTabBorder">
				 Staff Payrolls data not found for this Staff.
		</div>
	</s:else>
</div>
</div>
</fieldset>
</div>
<script type="text/javascript">
	changePageTitle('Get Payroll Settings');
var name=1;
$('div.staffNameDiv').click(function () {
     $('div#payRoleDiv>div.item').sortElements(function (a, b) {return ($(a).attr('personFullName').toLowerCase() > $(b).attr('personFullName').toLowerCase() ? 1 : -1) * name;});
    updateDirectionArrows($(this), name);
    //$("#payRoleDiv").pagination();
    name= name* -1;
    return false;
});

var dis=1;
$('div.roleNameDiv').click(function () {
     $('div#payRoleDiv>div.item').sortElements(function (a, b) {return ($(a).attr('roleDescription').toLowerCase() > $(b).attr('roleDescription').toLowerCase() ? 1 : -1) * dis;});
    updateDirectionArrows($(this), dis);
   // $("#payRoleDiv").pagination();
    dis= dis* -1;
    return false;
});

var type=1;
$('div.salaryNameDiv').click(function () {
     $('div#payRoleDiv>div.item').sortElements(function (a, b) {return ($(a).attr('salary').toLowerCase() > $(b).attr('salary').toLowerCase() ? 1 : -1) * type;});
    updateDirectionArrows($(this), type);
    //$("#payRoleDiv").pagination();
    type= type* -1;
    return false;
});

var date=1;
$('div.dateDiv').click(function () {
	$('div#payRoleDiv>div.item').sortElements(function (a, b) {return ($(a).attr('staffDateOfJoing') > $(b).attr('staffDateOfJoing') ? 1 : -1) * date; });
	updateDirectionArrows($(this), date);
   // $("#completedSchedulesCont").pagination();
    date = date * -1;
    return false;
});

var exp=1;
$('div.expDiv').click(function () {
     $('div#payRoleDiv>div.item').sortElements(function (a, b) {return (parseFloat($(a).attr('staffExperiencestr')) > parseFloat($(b).attr('staffExperiencestr')) ? 1 : -1)  * exp;});
    updateDirectionArrows($(this), exp);
   // $("#studentTakenBooks").pagination();
    exp= exp* -1;
    return false;
}); 
</script>