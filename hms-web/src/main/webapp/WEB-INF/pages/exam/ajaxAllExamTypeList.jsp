<%@ include file="/common/taglibs.jsp"%>
<%-- Start date <s:property value="todayDate"/>  End date <s:property value="tempString"/> --%>
<s:if test="%{tempString != null || !tempString.isEmpty()}">
	<s:if test="%{todayDate >= tempString}">
		<div class="form-group">
			<label class="control-label col-md-4"> <span class="required">*</span>Select
				Type :
			</label>
			<div class="col-md-6">
				<s:select id="selectedType"
					list="#{'M':'Marks Wise','MG':'Subject Marks & grade Wise'}"
					cssClass="form-control input-medium" headerKey="" name="selectedId" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label"> Sort Students By : </label>
			<div class="radio-list" style="width: 696px;">
				<label class="radio-inline"> 
				<input type="radio" name="SelectType" value="firstName" checked> Student Name
				</label> <label class="radio-inline"> <input type="radio"
					name="SelectType" value="rollNumber"> Roll Number
				</label> <label class="radio-inline"> <input type="radio"
					name="SelectType" value="admissionNumber"> Admission
					Number
				</label>
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Exams are not yet started, you cannot download/upload marks sheet for students.
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
			Exam Schedules are not created for this exam type.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform(); 
});
</script>