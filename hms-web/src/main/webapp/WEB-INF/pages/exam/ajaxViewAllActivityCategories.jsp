<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required">*</span>Select Category :
		</label>
		<div class="col-md-6">
			<s:checkboxlist list="objectList" name="categories" theme="ems"
				listKey="categoryName" listValue="categoryName"></s:checkboxlist>
		</div>
	</div>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required"></span>Select Assessment :
		</label>
		<div class="col-md-6">
			<s:checkboxlist list="tempList" name="stuAssessment" theme="ems"
				listKey="id" listValue="assessmentName"></s:checkboxlist>
		</div>
	</div>
	</s:if>
	<label class="control-label col-md-2">
		Select Grading Type :
	</label>
	<div class="col-md-6">
	<span id="selectedGPM" style="display: none;"><s:property value="title"/></span>
		<div class="form-group">
		<div class="radio-list">
			<label class="radio-inline">
					<span class="checked selectedTypeValue"> <input id="gradetype" type="radio"
							name="title" value="G" checked="checked"> </span>
				Grade
			</label>
			<label class="radio-inline">
					<span class="selectedTypeValue"> <input id="gradetype1" type="radio"
							name="title" value="P"> </span>
				Points
			</label>
			<label class="radio-inline">
					<span class="selectedTypeValue"> <input id="gradetype2" type="radio"
							name="title" value="M"> </span>
				Marks
			</label>
		</div>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info" id="noCategoriesDiv">
		<p>
			<span style="color: red;"> You do not have any activity
				for this class. Please <a href="#" id="createSubActivityTyId">click here</a> to create activity. </span>
		</p>
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	if($("div#noCategoriesDiv").is(":visible") || $("div#noExamTypesDiv").is(":visible")){
		  $("div#submitDivId").hide();
	}
});
$("a#createSubActivityTyId").click(function(){
$("a#addStudentActivity").click();
});
</script>