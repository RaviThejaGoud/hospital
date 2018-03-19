<%@ include file="/common/taglibs.jsp"%>
<div class="form-group">
	<label class="control-label col-md-5">
		Caste Name :
	</label>
	<div class="col-md-7">
		<s:select id="subCastName" list="objectList" headerValue="- Select -"
			listKey="id" listValue="subCastName" headerKey=""
			name="studentVo.account.personVo.subCastId" theme="simple"
			cssClass="form-control input-medium" />
	</div>
</div>
<div id="resultsDiv2"></div>
