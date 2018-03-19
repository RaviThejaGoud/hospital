<%@ include file="/common/taglibs.jsp"%>
<!--<jsp:include page="/common/messages.jsp"></jsp:include>-->
<div class="form-group">
	<s:if test="%{classList != null && !classList.isEmpty()}">
		<label class="control-label col-md-4">
			<span class="required">*</span>Admission To Class :
		</label>
		<div class="col-md-6">
			<s:select list="classList" listKey="id" listValue="className"
				cssClass="required form-control input-medium"
				name="onlineApplicationDetails.classId.id" headerKey=""
				headerValue="- Select -" theme="simple" onchange="javascript:checkCommittedFee(this.value);">
			</s:select>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			No classes defined for this admissions.
		</div>
	</s:else>
</div>