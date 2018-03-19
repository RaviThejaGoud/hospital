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
<%@ include file="/common/taglibs.jsp"%>

<div id="commonStep13">
	<fieldset id="stepAttendance">
		<div class="grid_14">
			<div id="editClassSubjects" class="grid_13">
				<%@ include file="/common/messages.jsp"%>
				<s:if
					test="%{payrollTypesList!= null && !payrollTypesList.isEmpty()}">
					<div class="grid_13 th">
						<div class="grid_4">
							<div class="grid_2 nameDiv sortHeader divArrow">
								Name
							</div>
						</div>
						<div class="grid_5">
							<div class="grid_2 desDiv sortHeader divArrow">
								Description
							</div>
						</div>
						<div class="grid_4">
							<div class="grid_2 typeNameDiv sortHeader divArrow">
								Type
							</div>
						</div>
					</div>
					<div id="payRoleDiv">
					<s:iterator value="payrollTypesList">
					  <div payrollName="<s:property value='payrollName' />" payrollDescription="<s:property value='payrollDescription' />"  payrollTypeDesc="<s:property value='payrollTypeDesc' />" class="item">
						<div class="grid_13 row">
							<div class="grid_4">
								<s:property value="payrollName" />
							</div>
							<div class="grid_5">
								<s:property value="payrollDescription" />
							</div>
							<div class="grid_4">
								<s:property value="payrollTypeDesc" />
							</div>
						</div>
						</div>
					</s:iterator>
					</div>
				</s:if>
				<s:else>
					<div class="examTabBorder">
						Payroll Types data not found for this Staff.
					</div>
				</s:else>
			</div>
		</div>
	</fieldset>
</div>
<script type="text/javascript">
changePageTitle('Get Payroll Types');

var name=1;
$('div.nameDiv').click(function () {
     $('div#payRoleDiv>div.item').sortElements(function (a, b) {return ($(a).attr('payrollName').toLowerCase() > $(b).attr('payrollName').toLowerCase() ? 1 : -1) * name;});
    updateDirectionArrows($(this), name);
    //$("#payRoleDiv").pagination();
    name= name* -1;
    return false;
});

var dis=1;
$('div.desDiv').click(function () {
     $('div#payRoleDiv>div.item').sortElements(function (a, b) {return ($(a).attr('payrollDescription').toLowerCase() > $(b).attr('payrollDescription').toLowerCase() ? 1 : -1) * dis;});
    updateDirectionArrows($(this), dis);
   // $("#payRoleDiv").pagination();
    dis= dis* -1;
    return false;
});

var type=1;
$('div.typeNameDiv').click(function () {
     $('div#payRoleDiv>div.item').sortElements(function (a, b) {return ($(a).attr('payrollTypeDesc').toLowerCase() > $(b).attr('payrollTypeDesc').toLowerCase() ? 1 : -1) * type;});
    updateDirectionArrows($(this), type);
    //$("#payRoleDiv").pagination();
    type= type* -1;
    return false;
});

</script>