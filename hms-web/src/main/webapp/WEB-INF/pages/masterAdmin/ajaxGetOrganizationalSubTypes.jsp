<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-5">
			<span class="required">*</span>Organization Sub Type  :
		</label>
		<div class="col-md-0">
			<s:select id="organizationSubType" list="objectList"
				cssClass="required form-control input-medium as-input"
				listKey="id" listValue="schoolCategory" headerKey="" value="%{customerEnrollmentRequest.organizationSubTypesId}"
				headerValue="- Select -" name="organizationSubTypes.id"
				theme="simple" />
		</div>
	</div>
</s:if>





