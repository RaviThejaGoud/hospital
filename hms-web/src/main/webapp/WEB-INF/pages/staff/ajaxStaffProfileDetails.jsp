<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
<div class="form-group">
	<label class="control-label col-md-2" style="width: 106px;">
		<span class="required">*</span>Select Class :
	</label>
	<div class="col-md-3">
		<s:select list="studyClassList" listKey="id"
			listValue="classAndSection" id="dropDownId"			
			cssClass="form-control input-medium required" headerValue="All"
			name="tempId" headerKey="0">
		</s:select>
	</div>
	<div class="spaceDiv"></div>
		<div id="selectToOtherProfiles">
			<jsp:include page="/WEB-INF/pages/staff/profileSearch.jsp"></jsp:include>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no classes assigned for you. 
	</div>
</s:else>

