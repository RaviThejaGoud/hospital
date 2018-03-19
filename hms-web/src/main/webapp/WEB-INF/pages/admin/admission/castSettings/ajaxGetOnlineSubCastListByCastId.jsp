<%@ include file="/common/taglibs.jsp"%>
<div class="form-group">
	<label class="control-label col-md-4">
		Caste Name :
	</label>
	<div class="col-md-6" id="subCastDivid">
		<s:select id="subCastName" list="objectList" theme="simple"
			listKey="id" listValue="subCastName" headerKey="0"
			cssClass="form-control input-medium" headerValue="- Select -"
			name="onlineApplicationDetails.subCastId.id" />
	</div>
</div>


