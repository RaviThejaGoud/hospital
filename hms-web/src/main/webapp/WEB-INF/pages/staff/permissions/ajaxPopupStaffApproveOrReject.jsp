<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 730px; margin-left: -435px; margin-top: 80px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">Staff Permission Request</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
		<jsp:include page="/common/messages.jsp"></jsp:include>
			<s:form action="ajaxStaffPermisssionApproveOrReject" theme="simple" id="approve_%{tempId2}" namespace="/staff">
				<s:hidden name="tempId2" value="%{tempId2}"></s:hidden>
				<s:hidden name="bankName" value="isSchoolPrincipal"></s:hidden>
				<s:hidden name="anyTitle" cssClass="anyTitleId" value=""></s:hidden>
					<div class="col-md-12">
					<b>Description:</b>
					<br />
					<s:property value="comments" />
				</div>
				<div class="col-md-12">
					<sj:textarea name="PlTitle" cols="30"
						rows="3" value="" label="Comments"
						cssClass=" form-control"></sj:textarea>
				</div>
				<div class="col-md-12">
					<div class="spaceDiv"></div>
					<sj:submit cssClass="submitBt btn blue" value="Approve"
						onBeforeTopics="staffApproveFormDetails"
						indicator="indicator" targets="permissionsDiv"
						resetForm="true" formIds="approve_%{tempId2}" />
					<sj:submit cssClass="submitBt btn red" value="Reject"
						onBeforeTopics="staffRejectFormDetails"
						indicator="indicator" targets="permissionsDiv"
						resetForm="true" formIds="approve_%{tempId2}" />
					<button type="button" data-dismiss="modal" class="btn default">Cancel</button>
				</div>
				<div class="spaceDiv"></div>
			</s:form>
		</div>
	</div>
</div>
<script type="text/javascript">
	//$.destroyTopic('staffApproveFormDetails');
	$.subscribe('staffApproveFormDetails', function(event, data) {
		$('.anyTitleId').val('approve');
		$('button.close').click();
		return true;
	});
	$.subscribe('staffRejectFormDetails', function(event, data) {
		$('.anyTitleId').val('reject');
		$('button.close').click();
		return true;
	});
</script>
