<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_13 commomnTabs">
	&nbsp;<%@ include file="/common/messages.jsp"%>
	<s:form action="ajaxViewOldStaffPayroll" theme="css_xhtml" id="viewOldStaffPayroll" method="post" namespace="/admin">
	
	
	<div class="tableactions grid_12">
	
	<div class="grid_2 alpha">
			<label class="right">
				Month:
			</label>
		</div>
		<select tabindex="1" class="combo" id="dobMonth" name="month">
            	<option value="1">JAN</option>
				<option value="2">FEB</option>
				<option value="3">MAR</option>
				<option value="4">APR</option>
				<option value="5">MAY</option>
				<option value="6">JUN</option>
				<option value="7">JUL</option>
				<option value="8">AUG</option>
				<option value="9">SEP</option>
				<option value="10">OCT</option>
				<option value="11">NOV</option>
				<option value="12">DEC</option>

        </select>



<div class="grid_2 alpha">
			<label class="right">
				Year:
			</label>
		</div>
		<select tabindex="2" class="combo" id="dobYear" name="year">
			
			<option value="2012">2012</option>
			<option value="2000">2013</option>
			<option value="2001">2014</option>
			<option value="2002">2015</option>
			<option value="2003">2016</option>
			<option value="2004">2017</option>
			<option value="2005">2018</option>
			<option value="2006">2019</option>
			<option value="2007">2020</option>
			<option value="2008">2021</option>
			<option value="2009">2022</option>
			<option value="2010">2023</option>
			<option value="2011">2024</option>
			<option value="2011">2025</option>

        </select>
		
	</div>
	<div class="tableactions grid_12">
		<div class="grid_2 alpha">
			<label class="right">
				Select Role:
			</label>
		</div>
		<s:if test="%{ staffRoles != null && !staffRoles.isEmpty()}">
		
		<s:select list="staffRoles" id="staffRole"
				name="staffRoleName" theme="css_xhtml"
				onchange="javascript:getStaffByRole(this.value);" tabindex="1">
			</s:select>
		
		</s:if>
	
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
	<div id="getStaff"></div>
	<div class="grid_12">
			<s:url id="doGetPayrollTypes" action="ajaxDoGeneratePayroll"
				includeParams="all" escapeAmp="false" namespace="/admin">
			</s:url>
			<sj:a href="%{doGetPayrollTypes}" cssClass="cancelButton"
				targets="staffPayroll" indicator="indicator">Cancel</sj:a>
			<sj:submit   targets="doViewOldStaffPayroll" value="Find"
				cssClass="submit small" formIds="viewOldStaffPayroll"
				indicator="indicator"
				/>
		</div>
	</s:form>
</div>

<div class="block_content" id="doViewOldStaffPayroll"></div>

<script type="text/javascript">
changePageTitle("View Old Generate  Payroll");
</script>


<script type="text/javascript">
function getStaffByRole(staffRole) {
	if (staffRole == "") {
		$("#payrollGeneration").hide();
	} else {
		var pars = "tempString=" + staffRole;
		$("#payrollGeneration")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/admin/ajaxDoViewOldGeneratedPayroll.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#payrollGeneration").html(html);
				$("#payrollGeneration").show();
			}
		});
	}
}
</script>