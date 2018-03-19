<%@ include file="/common/taglibs.jsp"%>
<div id="syllabusDetails">
	<br />
	<s:if
		test="%{studyClass.subjects != null  && !studyClass.subjects.isEmpty() }">
		<div class="grid_9">
			<div class="grid_3" >
				<b>Subjects</b>
			</div>
			<div class="grid_4">
				<b>Enter Syllabus</b>
			</div>
		</div>
		<s:iterator value="studyClass.subjects">
			<div class="grid_9">
				<div class="grid_3">
					<input type="hidden" name="subId" value="<s:property value="id"/>">
					<label>
						<s:property value="name" />
					</label>
				</div>
				<div class="grid_4">
					<sj:textfield name="address.postalCode" id="pincode"
						required="true" cssClass="numeric required textfield"
						maxlength="6"></sj:textfield>
				</div>
			</div>
		</s:iterator>
	</s:if>
</div>
<script type="text/javascript">
changePageTitle("Add Class Details");
$(document).ready(function() {
	$.subscribe('addClassValidation', function(event, data) {
		if ($('#addNewClass').valid())
			return true;
		else
			return false;
	});
});
</script>