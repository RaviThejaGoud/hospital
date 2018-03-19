<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_13 commomnTabs">
	&nbsp;<%@ include file="/common/messages.jsp"%>
	<s:form id="selectStaffLoanForm4"  theme="css_xhtml" >
	<div class="tableactions grid_12">
		<div class="grid_2 alpha">
			<label class="right">
				Select Role:
			</label>
		</div>
		<s:if test="%{ staffRoles != null && !staffRoles.isEmpty()}">
			<s:select list="staffRoles" id="className" name="staffRoleName"
				theme="simple" onchange="javascript:getStaffByRole(this.value);">
			</s:select>
		</s:if>
	</div>
	</s:form>
</div>
<script type="text/javascript">
function getStaffByRole(staffRole) {
	if (staffRole == "") {
		$("#staffLoanSettings").hide();
	} else {
		var pars = "tempString=" + staffRole+"&stLoan=stLoan";
		$("#staffLoanSettings")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/admin/ajaxGetStaffByRoleForLoan.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#staffLoanSettings").html(html);
				$("#staffLoanSettings").show();
			}
		});
	}
}
</script>
<!--<div class="grid_3">
		<s:if test="%{tempId != 0}">
			<s:url id="doViewPrincipalList"
				action="ajaxDoViewStaffDetailsList" includeParams="all"
				escapeAmp="false">
				<s:param name="tempString">principals</s:param>
			</s:url>
			<sj:a href="%{doViewPrincipalList}" targets="viewStaffDetails"
				onBeforeTopics="cleanOpenRows"
				onCompleteTopics="doInitClassSyllabusDetails" indicator="indicator1">
								Principal List(<s:property value="tempId" />)
					</sj:a>
			<img style="display: none;" alt="Loading..."
				src="${pageContext.request.contextPath}/images/indicator.gif"
				id="indicator1">
		</s:if>
		<s:else>
					Principal List(<s:property value="tempId" />)
				</s:else>
	</div>
	<div class="grid_3">
		<s:if test="%{tempId1 != 0 }">
			<s:url id="doViewTeacherList" action="ajaxDoViewStaffDetailsList"
				includeParams="all" escapeAmp="false">
				<s:param name="tempString" >teachers</s:param>
			</s:url>
			<sj:a href="%{doViewTeacherList}" targets="viewStaffDetails"
				onBeforeTopics="cleanOpenRows"
				onCompleteTopics="doInitClassSyllabusDetails" indicator="indicator2">
								Teaching List(<s:property value="tempId1" />)
							</sj:a>
			<img style="display: none;" alt="Loading..."
				src="${pageContext.request.contextPath}/images/indicator.gif"
				id="indicator2">
		</s:if>
		<s:else>
					Teaching List(<s:property value="tempId1" />)
				</s:else>
	</div>
	<div class="grid_4">
		<s:if test="%{tempId2 != 0}">
			<s:url id="doViewNonTechingList" action="ajaxDoViewStaffDetailsList"
				includeParams="all" escapeAmp="false">
				<s:param name="tempString" >nonTeachers</s:param>
			</s:url>
			<sj:a href="%{doViewNonTechingList}" targets="viewStaffDetails"
				onBeforeTopics="cleanOpenRows"
				onCompleteTopics="doInitClassSyllabusDetails">
							Non-Teaching List (<s:property value="tempId2" />)
						</sj:a>
			<img style="display: none;" alt="Loading..."
				src="${pageContext.request.contextPath}/images/indicator.gif"
				id="indicator2">
		</s:if>
		<s:else>
					Non-Teaching List (<s:property value="tempId2" />)
				</s:else>
	</div>
	<div class="clear"></div>
	<div id="viewStaffDetails"></div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('doInitEditStaff', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
});
</script>-->