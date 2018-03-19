<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required">*</span>Select Vehicle :
		</label>
		<div class="col-md-3">
			<s:select id="vehicleId" list="tempList" label="Select Vehicle"
				listKey="vehicleAcademicId" listValue="name" headerKey=""
				headerValue="- Select -"
				cssClass="required form-control input-medium" name="eventId"
				theme="simple" />
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info col-md-12">
		No Vehicles for this BoardingPoint.
	</div>
</s:else>
