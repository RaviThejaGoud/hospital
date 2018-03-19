<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{(bedList != null && !bedList.isEmpty())}">
	<div class="form-group">
		<label class="control-label col-md-6">
			<span class="required">*</span> Available Beds :
		</label>
		<div class="col-md-6">
			<s:set var="selectedId" value="%{selectedId}" />
			<s:iterator value="bedList">
				<div>
					<s:if test="%{accountId == (#selectedId)}">
						<input type="radio" name="tempList" id="studentBedId"
							value='<s:property value="id"/>' checked="checked" />
					</s:if>
					<s:else>
						<input type="radio" name="tempList" id="studentBedId"
							value='<s:property value="id"/>' />
					</s:else>
					<s:property value="bedName" />
					<s:property value="bedLevel" />
				</div>
			</s:iterator>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no beds.
	</div>
</s:else>
<s:if test="%{(tempList1 != null && !tempList1.isEmpty())}">
	<s:if test="%{(tempList1 != null && !tempList1.isEmpty())}">
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span> Filled Beds: :
			</label>
			<div class="col-md-4">
				<s:iterator value="tempList1">
					<div class="grid_1 ">
						<s:property value="bedName" />
						&nbsp;
					</div>
				</s:iterator>
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no filled beds.
		</div>
	</s:else>
</s:if>
