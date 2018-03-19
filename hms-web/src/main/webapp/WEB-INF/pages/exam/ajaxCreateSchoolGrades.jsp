<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{schoolGrades.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in" id="responsive2" style="display: block; width: 804px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">Update subject grades</h4>
	</div>
	<div class="modal-body">
	</s:if>
	<s:form action="ajaxCreateSchoolGrades" theme="simple" id="updateGradetypes" method="post" cssClass="form-horizontal" namespace="/exam">
		<s:hidden name="schoolGrades.id" value="%{schoolGrades.id}"></s:hidden>
		<s:hidden name="tempString" value="grades"></s:hidden>
		<h4 class="bold pageTitle">
			<s:if test="%{schoolGrades.id != 0}">
				<s:set name="schoolGradesId" value="%{schoolGrades.id}"></s:set>
			</s:if>
			<s:else>
				Add subject grades
				<hr/>
			</s:else>
		</h4>
	<div class="form-body">
		<s:if
			test="%{schoolGradesList != null && !schoolGradesList.isEmpty()}">
			<s:iterator value="schoolGradesList">
				<s:if test="%{id != #schoolGradesId}">
					<div class="schoolGradesDiv">
						<span id="minMarksSpan" class="<s:property value='minMarks'/>"></span>
						<span id="maxMarksSpan" class="<s:property value='maxMarks'/>"></span>
						<span id="gradeNameSpan" class="<s:property value='gradeName'/>"></span>
					</div>
				</s:if>
			</s:iterator>
		</s:if>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Grade Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="schoolGrades.gradeName" id="gradeName"
							cssClass="required form-control input-medium" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Min Marks (%) :
					</label>
					<div class="col-md-5">
						<sj:textfield name="schoolGrades.minMarks" id="minMarks"
							onchange="validateMarks(this);"
							cssClass="required numericDot form-control input-medium mathRound"
							maxlength="4"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Max Marks (%) :
					</label>
					<div class="col-md-5">
						<sj:textfield name="schoolGrades.maxMarks" id="maxMarks"
							onchange="validateMarks(this);"
							cssClass="required numericDot form-control input-medium mathRound"
							maxlength="4"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Grade points :
					</label>
					<div class="col-md-5">
						<sj:textfield name="schoolGrades.gradePoints" id="gradePoints"
							cssClass="required numericDot form-control input-medium"
							maxlength="4"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-12">
					<sj:submit   cssClass="submitBt btn blue" value="Submit"
						onBeforeTopics="gradesValidation" validate="true"
						indicator="indicator" targets="examContentInfo" />
					<s:if test="%{schoolGrades.id != 0}">
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</s:if>
					<s:else>
						<s:url id="doAddNewExamTypeList"
							action="ajaxViewExamTypesAndGrades" includeParams="all"
							escapeAmp="false" namespace="/exam">
							<s:param name="tempString">grades</s:param>
						</s:url>
						<sj:a href="%{doAddNewExamTypeList}" cssClass="btn default"
							targets="examContentInfo">Cancel</sj:a>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</s:form>
	<s:if test="%{schoolGrades.id != 0}">
	</div>
	</div>
	</s:if>
<script type="text/javascript">
$(document).ready(function() {
	$('input.mathRound').each(function(){
	  $(this).val($(this).val().replace(".0",""));	
	});
	
	changePageTitle("Add Grades");
	$("#gradeName").focus();
	$('.blockHeader h2').html('Manage Academics');
	
	$('.numeric').numeric( { allow : "0-9"});
	$('.numericDot').numeric( { allow : "."});
});
function validateMarks(evt){
	var minMarks = $('#minMarks').val();
	var maxMarks = $('#maxMarks').val();
	if(isNonEmpty(minMarks) && isNonEmpty(maxMarks)){
		if(Number(minMarks) > Number(maxMarks)){
			alert('Subject grades min marks should be less than max marks.');
			$(evt).val('');
		}
		$('div.schoolGradesDiv').each(function() {
			var minMarksSpan = $(this).children('span#minMarksSpan').attr("class");
			var maxMarksSpan = $(this).children('span#maxMarksSpan').attr("class");
			var gradeNameSpan = $(this).children('span#gradeNameSpan').attr("class");
			if(isNonEmpty(minMarksSpan) && isNonEmpty(maxMarksSpan) && isNonEmpty(gradeNameSpan)){
				minMarksSpan = Number(minMarksSpan);
				maxMarksSpan = Number(maxMarksSpan);
			    if((minMarks < maxMarksSpan) && (maxMarks >= minMarksSpan)){
			          	alert(minMarksSpan+" to "+maxMarksSpan+" is already assigned for "+gradeNameSpan);
			           	$('input#maxMarks').val('');
			           return false;
			    }
			    if(minMarks >= minMarksSpan && minMarks <= maxMarksSpan){
						alert(minMarksSpan+" to "+maxMarksSpan+" is already assigned for "+gradeNameSpan);
						$('input#maxMarks').val('');
					    return false;
				}
				else if((maxMarks >= minMarksSpan && maxMarks <= maxMarksSpan)){
						alert(minMarksSpan+" to "+maxMarksSpan+" is already assigned for "+gradeNameSpan);
						$('input#maxMarks').val('');
					    return false;
				}else{
				 return true;
				}
			}
		 });
	}
}
$.subscribe('gradesValidation',function(event, data) {
	 $('button.close').click();
	});

</script>