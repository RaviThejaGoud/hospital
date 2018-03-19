<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_14">
	<div class="block_head" id="topMenu">
		<h2>
			Manage Staff
		</h2>
		<s:if test='%{#session.previousYear == "N"}'>  
		<ul>
	       	<li>
				<s:url id="doAddStaff" action="ajaxDoAddStaff" includeParams="all" escapeAmp="false" namespace="/admin"/>
				<sj:a href="%{doAddStaff}"  targets="staffsContent">
						Add Staff
				</sj:a>
			</li>
			<li>							
				<s:url id="DiscontinueStaffs" action="ajaxViewExpiredStaffs" namespace="/admin"/>
				<sj:a href="%{DiscontinueStaffs}" targets="staffsContent">Disabled Staff</sj:a>
			</li>
			<li>							
				<s:url id="importStaffExcelSheet" action="ajaxDoImportStaffExcelSheet" namespace="staff"/>
				<sj:a href="%{importStaffExcelSheet}" targets="staffsContent">Import Staff</sj:a>
			</li>
		</ul>
		</s:if>
	</div>
	<div class="block_content" id="staffsContent">
		<jsp:include page="/WEB-INF/pages/admin/payroll/ajaxViewStaffList.jsp"/>
	</div>
</div>
<script type="text/javascript">
	changePageTitle("Mange Staff");
</script>
