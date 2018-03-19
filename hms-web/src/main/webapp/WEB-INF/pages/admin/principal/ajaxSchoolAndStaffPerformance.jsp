<%@ include file="/common/taglibs.jsp"%>
<div id="commonTabContent" class="grid_11">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<div class="grid_11">
					<div>
						<b>School & Staff Performance</b>
					</div>
					<div>
						<s:select
							list="#{'STP':'Staff Perfomance','SP':'School Perfomance'}"
							name="perfomanceName" cssStyle="width:150px;height:25px"
							cssClass="required" headerKey="" headerValue="- Select -"
							theme="css_xhtml"
							onchange="javascript:getStaffPerfomance(this.value);"></s:select>
					</div>
					<div id="shcoolPerfomance"></div>
					<div class="classPendingAvg">
						<h6>
							<s:url id="viewSchoolAndStaffPerfomanceLink"
								action="ajaxViewSchoolAndStaffPerfomance" namespace="/admin">
							</s:url>
							<sj:a id="viewSchoolAndStaffPerfomanceLink"
								href="%{viewSchoolAndStaffPerfomanceLink}"
								targets="staffLeaveApproval" indicator="indicator">More Details</sj:a>
						</h6>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
