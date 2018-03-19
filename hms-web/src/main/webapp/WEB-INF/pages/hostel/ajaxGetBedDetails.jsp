<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{bedList!= null && !bedList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-3">
			<span class="required">*</span>Select Bed No / Name :
		</label>
		<div class="col-md-6">
			<s:select list="bedList" listKey="id" theme="simple"
				id="bedId" headerValue="- Select -" headerKey="S"
				cssClass="select2_category form-control required" listValue="bedName" name="hostelStudents.bedId">
			</s:select>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Room doesn't contains beds.
	</div>
</s:else>
 
