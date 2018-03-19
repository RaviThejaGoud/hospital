<%@ include file="/common/taglibs.jsp"%>
<div class="block_head">
	<h2>
		Teachers Absent & School And Staff Performance
	</h2>
</div>
<div class="block_content" id="sideMenu">
	<div id="commonTabContent" class="grid_11">
		<div id="commonTabWrapper">
			<div id="commonStep">
				<fieldset>
					<div class="grid_11">


						<ul>
							<s:if
								test="%{approvedLeavesList != null && !approvedLeavesList.isEmpty()}">
								<li>
									<s:url id="viewStaffLeaveDetailsLink"
										action="ajaxViewStaffLeaveDetails" namespace="/admin">
									</s:url>
									<sj:a id="viewStaffLeaveDetails"
										href="%{viewStaffLeaveDetailsLink}"
										targets="staffLeaveApproval" indicator="indicator">Total Staff Absences(<s:property
											value="approvedLeavesList.size" />)</sj:a>
								</li>
							</s:if>
							<s:else>
								<li>
									<s:url id="viewStaffLeaveDetailsLink"
										action="ajaxViewStaffLeaveDetails" namespace="/admin">
									</s:url>
									<sj:a id="viewStaffLeaveDetails"
										href="%{viewStaffLeaveDetailsLink}"
										targets="staffLeaveApproval" indicator="indicator">Total Staff Absences(<s:property
											value="approvedLeavesList.size" />)</sj:a>
								</li>
							</s:else>
							<li>
								<a href="#">Subs Required</a>
							</li>
							<li>
								<a href="#">- Filled Jobs</a>
							</li>
							<li>
								<a href="#">- Unfilled Jobs</a>
							</li>
						</ul>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	<div class="grid_12">
		<jsp:include
			page="/WEB-INF/pages/admin/principal/ajaxSchoolAndStaffPerformance.jsp" />
	</div>
</div>