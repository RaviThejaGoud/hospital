<%@ include file="/common/taglibs.jsp"%>
<td colspan="6" style="background-color: #CCC;">
<s:if test="%{viewStudentPersonAccountDetails != null}">
	<s:form action="ajaxStaffSendMessageToParent" theme="simple" method="post" cssClass="form-horizontal"
		id="formApprovalLeaves">
		<s:hidden name="viewStudentPersonAccountDetails.parentId" />
		<s:hidden name="viewStudentPersonAccountDetails.username" />
		<div class="form-body">
				<h4 class="pageTitle bold form-section">Send Message To Parent</h4>
		<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"><span class="required">*</span>Student Id :</label>
							<div class="col-md-5">
							<s:property value="viewStudentPersonAccountDetails.rollNumber" />
							</div>
						</div>
					</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"><span class="required">*</span>Student Name :</label>
						<div class="col-md-5">
							<s:property value="viewStudentPersonAccountDetails.personFullName" />
						</div>
					</div>
				</div>
			</div>
		
		<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"><span class="required">*</span>Parent Name :</label>
							<div class="col-md-5">
							<s:property value="viewStudentPersonAccountDetails.fatherName" />
							</div>
						</div>
					</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"><span class="required">*</span>Parent Email :</label>
						<div class="col-md-5">
							<s:property value="viewStudentPersonAccountDetails.parentEmail" />
						</div>
					</div>
				</div>
			</div>
			 <div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"><span class="required">*</span>Title :</label>
							<div class="col-md-5">
							<s:textfield name="messages.title" id="title" label="Title"
							size="25" labelposition="top" cssClass="small required"
							 maxlength="80"  ></s:textfield>
							</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"><span class="required">*</span>Comments :</label>
						<div class="col-md-5">
							<sj:textarea name="messages.messageDescription" cssStyle="height: 95px;width : 120%"
					         id="messageDescription" label="Comments" cssClass="required"
					         rows="5" cols="40"></sj:textarea>
						</div>
					</div>
				</div>
			</div>
			  <div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"><span class="required">*</span>Comments :</label>
							<div class="col-md-5">
							<sj:textarea name="messages.messageDescription" cssStyle="height: 95px;width : 120%"
								id="messageDescription" label="Comments" cssClass="required"
								 rows="5" cols="40"></sj:textarea>
							</div>
						</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"><span class="required">*</span>Comments :</label>
						<div class="col-md-5">
							<sj:textarea name="messages.messageDescription" cssStyle="height: 95px;width : 120%"
					         id="messageDescription" label="Comments" cssClass="required"
					         rows="5" cols="40"></sj:textarea>
						</div>
					</div>
				</div>
			</div>
		<div class="form-actions fluid">
		<div class="col-md-offset-2 col-md-9">
			<div class="grid_4" style="text-align: left;">
				<sj:submit   cssClass="submit small" value="Submit"
					indicator="indicator" targets="myInboxHome"
					onClickTopics="formValidationForApprovalLeaves" />

				<s:url id="doCancelLeave" action="ajaxDoSendMyMessagesToParent"
					includeParams="all"></s:url>
				<sj:a href="%{doCancelLeave}" cssClass="cancelButton"
					indicator="indicator" targets="myInboxHome">Cancel</sj:a>
			</div>
		</div></div>
		</div>
	</s:form>
	</s:if>
	<s:else>
		currently there are no students.
	</s:else>
</td>
<script type="text/javascript">
	$(document).ready(function() {
		var validator;
		$.subscribe('formValidationForApprovalLeaves', function(event, data) {
			if ($('#formApprovalLeaves').valid())
				return true;
			else
				return false;
		});
	});
</script>
