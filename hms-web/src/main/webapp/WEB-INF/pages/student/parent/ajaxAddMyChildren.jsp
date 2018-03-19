<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-bar-chart-o"></i> Add My Children
		</div>
	</div>
	<div class="portlet-body">
		<div id="addChildrenDiv">
			<jsp:include page="/common/messages.jsp" />
			<div id="studentDetails">
				<s:form id="addMychild" action="ajaxAddMyChildren" method="post"
					theme="simple" cssClass="form-horizontal" namespace="/student">
					<div class="form-body">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Student Login Id :
							</label>
							<div class="col-md-3">
								<sj:textfield name="anyTitle" id="userName"
									cssClass="required form-control" maxlength="60"></sj:textfield>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-2 col-md-9">
								<sj:submit cssClass="submitBt btn blue" formIds="addMychild"
									value="Submit" indicator="indicator"
									onBeforeTopics="formValidationForAddMychild"
									targets="studentDetails" validate="true" />
								<a href="${pageContext.request.contextPath}/student/studentHome.do"
									class="btn default">Cancel</a>
							</div>
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('#userName').focus();
	$('input#userName').val('');
});
</script>
