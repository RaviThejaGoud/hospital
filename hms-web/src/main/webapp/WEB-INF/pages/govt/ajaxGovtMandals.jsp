<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-4">
			<span class="required">*</span> Select Mandal :
		</label>
		<div class="col-md-7">
			<s:select id="mandalId" list="tempList1"
				cssClass="required form-control input-medium as-input" listKey="id" listValue="mandalName" headerKey=""
				headerValue="- Select -" name="anyId" theme="simple" />
		</div>
	</div>
</s:if>







