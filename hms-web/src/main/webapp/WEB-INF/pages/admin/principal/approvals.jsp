<%@ include file="/common/taglibs.jsp"%>
<div id="commonTabContent" class="grid_11">
	<div id="commonTabWrapper">
		<div id="commonStep">
			<fieldset>
				<div class="grid_11">
				<div><b>Approvals</b></div>
					<s:if test='%{user.isSchoolPrincipal == "Y" }'>
						<div style="padding-top: 1px">
							<s:if test='%{leaveApprovalCount != "0"}'>
								<b>HOD Leaves:</b>
								You have <s:property value="leaveApprovalCount" />
								<div>
									<p>
										<a href="doLeaveApprovalsCountHome.do"><b>Pending
												Approvals</b>
										</a>
									</p>
								</div>
							</s:if>
							<s:else>
								Currently there are no hod pending approvals.
							</s:else>
						</div>
					</s:if>
					<div style="padding-top: 1px">
						<div>
							<b>Non Teaching Leaves</b>
						</div>
						<s:url id="urlClassDetails"
							action="ajaxDoAdminLeaveApprovalsAsApprover" />
						<sj:a id="test" href="%{urlClassDetails}"
							targets="staffLeaveApproval" indicator="indicator">
							Pending Approvals</sj:a>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
