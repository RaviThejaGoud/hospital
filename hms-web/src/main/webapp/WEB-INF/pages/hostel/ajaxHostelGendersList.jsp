<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{building != null && building != empty}">
	<s:if test='%{building.gender != null && building.gender != empty }'>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Gender :
			</label>
			<div class="col-md-5">
				<div class="make-switch has-switch" data-id="M" data-value="F"
					style="width: 120px" data-off="warning" data-on="success"
					data-off-label="Female" data-on-label="Male">
					<input type="radio" class="toggle" checked="checked" id="gender">
					<input type="hidden" name="building.gender" value="M">
				</div>
			</div>
		</div>
		<%-- <s:hidden name="building.gender" theme="simple"
			value="%{building.gender}"></s:hidden> --%>
	</s:if>
	<s:elseif test='%{floor.gender != null && floor.gender != empty }'>
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Select Gender :
			</label>
			<div class="col-md-5">
				<div class="make-switch has-switch" data-id="M" data-value="F"
					style="width: 120px" data-off="warning" data-on="success"
					data-off-label="Female" data-on-label="Male">
					<input type="radio" class="toggle" checked="checked" id="gender">
					<input type="hidden" name="building.gender" value="M">
				</div>
			</div>
		</div>
		<%-- <s:hidden name="building.gender" theme="simple"
			value="%{building.gender}"></s:hidden> --%>
	</s:elseif>
	<s:else>
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Select Gender :
			</label>
			<div class="col-md-5">
				<div class="make-switch has-switch" data-id="M" data-value="F"
					style="width: 120px" data-off="warning" data-on="success"
					data-off-label="Female" data-on-label="Male">
					<input type="radio" class="toggle" checked="checked" id="gender">
					<input type="hidden" name="building.gender" value="M">
				</div>
			</div>
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		Please select building.
	</div>
</s:else>
<script language="JavaScript" type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js">
</script>
<script>
FormAdvanced.init();
</script>
