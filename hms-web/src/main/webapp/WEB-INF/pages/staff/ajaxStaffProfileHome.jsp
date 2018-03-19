<%@ include file="/common/taglibs.jsp"%>
<div id="steps">
	<s:if
		test="%{viewStaffPersonAccountDetailsList != null && !viewStaffPersonAccountDetailsList.isEmpty()}">
		<jsp:include page="/WEB-INF/pages/staff/selectOneStaff.jsp"></jsp:include>
	</s:if>
	<s:elseif test="%{viewStaffPersonAccountDetails != null}">
		<jsp:include page="/WEB-INF/pages/staff/ajaxStaffProfile.jsp"></jsp:include>
	</s:elseif>
	<s:else>
			<div class="alert alert-info">
		Currently there are no staff.
	</div>
	</s:else>
</div>
<!--<div class="grid_12 omega block">
	<div class="block grid_12">
		<div class="block_head">
			<h2>
				Staff Profile
			</h2>
		</div>
		<div class="block_content" id="staffsProfileHome" style="padding: 0px">
			<div id="steps">
				<s:if
					test="%{viewStaffPersonAccountDetailsList != null && !viewStaffPersonAccountDetailsList.isEmpty()}">
					<jsp:include page="/WEB-INF/pages/staff/selectOneStaff.jsp"></jsp:include>
				</s:if>
				<s:elseif test="%{viewStaffPersonAccountDetails != null}">
					<jsp:include page="/WEB-INF/pages/staff/ajaxStaffProfile.jsp"></jsp:include>
				</s:elseif>
				<s:else>
			Currently there are no Staff.
		</s:else>
			</div>
		</div>
	</div>
	</div>-->