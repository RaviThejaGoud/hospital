<%@ include file="/common/taglibs.jsp"%>
<div align="center">
	<s:url id="urlGetMost30DaysStaffLeaves"
		action="ajaxViewStaffLeaveDetails" namespace="/admin"/>
	<sj:a id="urlGetMost30DaysStaffLeaves"
		href="%{urlGetMost30DaysStaffLeaves}" cssClass="btn green btn-xs"
		targets="staffLeaveDiv" indicator="indicator">
		Staff Leaves Details</sj:a></div>
<!-- 
<div class="grid_5 border"></div>
<div class="block_content grid_5" id="sideMenu" style="padding-left: 10px;">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<div class="grid_4">
					<div>
						<strong> Staff Leaves </strong>
					</div>
					<ul>
						<li>
							<s:url id="urlGetMost30DaysStaffLeaves"
								action="ajaxViewStaffLeaveDetails" />
							<sj:a id="urlGetMost30DaysStaffLeaves"
								href="%{urlGetMost30DaysStaffLeaves}"
								targets="staffLeaveApproval" indicator="indicator">
								Staff Leaves Details</sj:a>
						</li>
					</ul>
				</div>
			</fieldset>
		</div>
	</div>
</div>
<div class="grid_5 border"></div>-->