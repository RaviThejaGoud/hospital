<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{statesList != null && !statesList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-4"><span class="required"> * </span> State : </label>
		<div class="col-md-5">
			<s:if test='%{tempString=="memberPage"}'>
				<s:select id="stateLists" list="statesList" listKey="id" 
					listValue="stateName" headerKey="" headerValue="- Select -"
					name="tempId" theme="simple" cssClass="form-control required" />
			</s:if>
			<s:else>
				<s:select id="stateLists" list="statesList" listKey="id" 
					listValue="stateName" headerKey="" headerValue="- Select -"
					name="tempId" theme="simple" cssClass="form-control required" />
			</s:else>	 
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no
		states.</div>
</s:else>