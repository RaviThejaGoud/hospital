<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
<s:if test="%{leaveManagement != null}">
	<div class="form-group ">
		<label class="control-label col-md-2">
			<span class="required">*</span>Sick Leaves :
		</label>
		<div class="col-md-3">
			<sj:textfield name="leaveManagement.sickLeaves" id="sickLeaves"
				cssClass="required numeric form-control" maxlength="2"></sj:textfield>
		</div>
	</div>
	<div class="form-group ">
		<label class="control-label col-md-2">
			<span class="required">*</span>Casual Leaves :
		</label>
		<div class="col-md-3">
			<sj:textfield name="leaveManagement.casualLeaves"
				id="casualLeaves" cssClass="required numeric form-control"
				maxlength="2"></sj:textfield>
		</div>
	</div>
	<div class="form-group ">
		<label class="control-label col-md-2">
			<span class="required">*</span>Earned Leaves :
		</label>
		<div class="col-md-3">
			<sj:textfield name="leaveManagement.earnedLeaves"
				id="casualLeaves" cssClass="numeric required form-control "
				maxlength="2">
			</sj:textfield>
		</div>
	</div>
	<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
				<sj:submit cssClass="submitBt btn blue" value="Submit" 
						targets="stepStaffLeavesDiv" validate="true"
						formIds="teachingAndNonTeachingEdit" />
				</div>
			</div>
	<br/><br/>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Role
				</th>
				<th>
					Sick Leaves
				</th>
				<th>
					Casual Leaves
				</th>
				
				<th>
					Earned Leaves
				</th>
				<th>
					Total Leaves
				</th>
				<!--<th>
					Edit
				</th>
				--><th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody><tr>
					<td>
						<s:property value="leaveManagement.role.description" />
					</td>
					<td>
						<s:property value="leaveManagement.sickLeaves" />
					</td>
					<td>
						<s:property value="leaveManagement.casualLeaves" />
					</td>
					<td>
						<s:property value="leaveManagement.earnedLeaves" />
					</td>
					<td>
						<s:property value="(leaveManagement.earnedLeaves + leaveManagement.casualLeaves + leaveManagement.sickLeaves)" />
					</td>
					<!--<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<a data-toggle="modal"  href="#popupLeaveSettingDiv"  class="btn btn-xs purple" 
									onclick="javascript:popupLeaveSettings(<s:property value="id" />);"><i class="fa fa-edit"></i>Edit
							</a>
						</s:if>
					</td>
					--><td>
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="removeLeaveSetting" action="ajaxRemoveLeaveSetting"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="leaveManagement.id" value="%{leaveManagement.id}" />
							</s:url>
							<s:div cssClass="btn btn-xs red"
								onclick="javascript:confirmDialogWithTarget(this,'stepStaffLeavesDiv');"
								id='%{removeLeaveSetting}' title="Delete this leave">
								<i class="fa fa-times"></i>Delete
												</s:div>
						</s:if>
					</td>
				</tr>
			</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no leaves defined.
	</div>
</s:else>
</div></div>

