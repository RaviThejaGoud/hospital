<%@ include file="/common/taglibs.jsp"%>
<div>
	<s:if
		test="%{(viewStudentMarksDetailsList != null && !viewStudentMarksDetailsList.isEmpty())}">
		<span id="overalGradesForcustomer" style="display: none;"><s:property
				value="anyTitle" /> </span>
		<div class="form-group">
			<label class="col-md-2 control-label" for="inputEmail1">
				<span class="required">*</span>Select ExamType :
			</label>
			<div class="col-md-3">
				<s:select list="viewStudentMarksDetailsList" listKey="examTypeId"
					listValue="examType" label="Select ExamType" name="tempId1"
					id="examTypeId" theme="simple" headerKey="null"
					cssClass="form-control input-medium required"
					headerValue="- Select ExamType-" onchange="javascript:getStudensForThisClassAndExamType(this.value);">
				</s:select>
			</div>
		<div class="row"> &nbsp; </div>
		<div id="resultsDiv3"></div>
		<%-- <div class="form-group">
			<label class="control-label col-md-2">
			<br/>
				<span class="required">*</span>Select Type :
			</label>
			<div class="col-md-3">
			<br/>
				<s:select list="#{'G':'Marks With Grade','R':'Marks With Rank','N':'Only Marks'}"
					id="gradeType" cssClass="form-control input-medium "
					name="selectedId" headerKey="" headerValue="- Select -">
				</s:select>
			</div>
		</div> --%>
		<s:hidden id="gradeType" value="N" />
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no marks assigned for this class.
		</div>
	</s:else>
</div>

<div id="errorMsgDivId"></div>

</div>