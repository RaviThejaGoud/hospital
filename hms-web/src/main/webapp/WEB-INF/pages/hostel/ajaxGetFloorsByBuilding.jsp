<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{floorList!= null && !floorList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-3">
			<span class="required">*</span>Select Building & Floor :
		</label>
		<div class="col-md-6">
		<s:select list="floorList" listKey="floorId" theme="simple"
			id="floorId" headerValue="- Select -" headerKey=""
			cssClass="select2_category form-control required"
			onchange="javascript:getRoomsDetailsByFloor(this.value,'rooms');"
			listValue="buildingNameAndFloorName" name="tempId1">
		</s:select>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		<p>
			Buildings are not created. Please create buildings and try to assign
			students.
		</p>
	</div>
</s:else>
