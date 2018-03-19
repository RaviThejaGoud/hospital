<%@ include file="/common/taglibs.jsp"%>
<div class="form-group">
	<label class="control-label col-md-4">
		Caste Name :
	</label>
	<div class="col-md-5">
		<s:select id="subCastName" list="objectList" listKey="id"
			listValue="subCastName" headerKey="0" headerValue="- Select -"
			name="newUser.person.subCastId" theme="simple"
			cssClass="form-control input-medium"
			 />
	</div>
	<div id="resultsDiv2"></div>
</div>