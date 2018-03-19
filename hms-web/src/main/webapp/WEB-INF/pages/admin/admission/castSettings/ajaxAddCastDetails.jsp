<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{castSettings.id != 0}">
<div data-width="760"  class="modal fade modal-overflow in" id="responsive2" style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Update community  
		</h4>
	</div>
	<div class="modal-body">
	<span class="label label-danger"> NOTE : </span>&nbsp;You can change/update the community name.
	</s:if>
<s:form id="addCasteFormDetails" action="ajaxAddCastDetails"
	method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">
	<s:hidden name="castSettings.id" value="%{castSettings.id}"></s:hidden>
<s:if test="%{castSettings == null}">
	<h4 class="bold pageTitle">
			Add community
		</h4>
	<hr/>
		<span class="label label-danger"> NOTE : </span>&nbsp;You can add community names with respective to your state
</s:if>
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-3 control-label">
				Community :
			</label>
			<div class="col-md-4">
				<div class="input-group">
					<sj:textfield name="castSettings.castName" id="castName"
						maxlength="60" size="5"
						cssClass="required  form-control input-medium" />
				</div>
				<span class="hintMessage">Like:BC-A , BC-B ...</span>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-7">
				<div class="col-md-offset-5 col-md-9">
					<sj:submit   targets="mainContentDiv" value="Submit"
						formIds="addCasteFormDetails" cssClass="submitBt btn blue"
						validate="true" onBeforeTopics="formValidationForAddCaste" indicator="indicator"/>
						<s:if test="%{castSettings.id != 0}">
							<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
						</s:if>
						<s:else>
					<s:url id="cancelTicketUrls" action="ajaxCastSettingsHome"
						includeParams="all" escapeAmp="false" namespace="/admin">
					</s:url>
					<sj:a href="%{cancelTicketUrls}" cssClass="btn default"
						indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
						</s:else>
				</div>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{studySubject.id != 0}">
</s:if>
<script type="text/javascript">
changePageTitle("Add Caste Settings");
$.subscribe('formValidationForAddCaste',function(event, data) {
	if ($('#addCasteFormDetails').valid()) {
		$('button.close').click();
	}  
});
$('#castName').focus();
</script>
