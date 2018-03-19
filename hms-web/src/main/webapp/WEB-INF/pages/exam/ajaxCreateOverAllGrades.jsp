<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
 <s:if test="%{overAllGrades.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		id="responsive3"
		style="display: block; width: 779px; margin-left: -379px; margin-top: 200px;"
		aria-hidden="false">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update exam grades
			</h4>
			<h5 class="bold pageTitle">
			</h5>
		</div>
		<div class="modal-body">
</s:if> 
<s:form action="ajaxCreateOverAllGrades" theme="simple"
	id="updateOverAllGradetypes" method="post" cssClass="form-horizontal"
	namespace="/exam">
	<s:hidden name="overAllGrades.id" value="%{overAllGrades.id}"></s:hidden>
	<s:hidden name="tempString" value="overAllGrades"></s:hidden>
	<h4 class="bold pageTitle">
		<s:if test="%{overAllGrades.id != 0}">
		    <s:set name="schoolGradeId" value="%{overAllGrades.id}"></s:set>
		</s:if>
		<s:else>
				Add exam grades
			<h5 class="bold pageTitle">
			</h5>
			<hr /> 
		</s:else>

	</h4>
	<div class="form-body">
		<s:if
			test="%{overAllGradesList != null && !overAllGradesList.isEmpty()}">
			<s:iterator value="overAllGradesList">
				<s:if test="%{id != #schoolGradeId}">
					<div class="overAllGradesDiv">
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
						<sj:textfield name="overAllGrades.gradeName" id="gradeName"
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
						<sj:textfield name="overAllGrades.minMarks" id="minMarks"
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
						<sj:textfield name="overAllGrades.maxMarks" id="maxMarks"
							onchange="validateMarks(this);"
							cssClass="required numericDot form-control input-medium mathRound"
							maxlength="4"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Description :
					</label>
					<div class="col-md-5">
						<sj:textfield name="overAllGrades.description" id="description"
							cssClass="required form-control input-medium" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="spacediv"></div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit cssClass="submitBt btn blue" value="Submit" indicator="indicator"
						validate="true" formIds="updateOverAllGradetypes"
						onBeforeTopics="gradesValidation" targets="examContentInfo" />
					<s:if test="%{overAllGrades.id != 0}">
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</s:if>
					<s:else>
						<s:url id="doAddNewExamTypeList"
							action="ajaxViewExamTypesAndGrades" includeParams="all"
							escapeAmp="false" namespace="/exam">
						</s:url>
						<sj:a href="%{doAddNewExamTypeList}" cssClass="btn default"
							targets="examContentInfo">Cancel</sj:a>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{overAllGrades.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Add Grades");
	$('input.mathRound').each(function(){
	  $(this).val($(this).val().replace(".0",""));	
	});
	
	$("#gradeName").focus();
	$('.blockHeader h2').html('Manage Academics');
	$('.numeric').numeric( { allow : "0-9"});
	$('.numericDot').numeric( { allow : "."});
});

$.subscribe('gradesValidation',function(event, data) {
	if($('form#updateOverAllGradetypes').valid()){
	 $('button.close').click();
	}
	});
function validateMarks(evt){
	var minMarks = $('#minMarks').val();
	var maxMarks = $('#maxMarks').val();
	if(isNonEmpty(minMarks) && isNonEmpty(maxMarks)){
		if(Number(minMarks) > Number(maxMarks)){
			alert('Over all grades min marks should be less than max marks.');
			$(evt).val('');
		}
		$('div.overAllGradesDiv').each(function() {
			var minMarksSpan = $(this).children('span#minMarksSpan').attr("class");
			var maxMarksSpan = $(this).children('span#maxMarksSpan').attr("class");
			var gradeNameSpan = $(this).children('span#gradeNameSpan').attr("class");
			//alert("minMarksSpan : "+minMarksSpan+", maxMarksSpan : "+maxMarksSpan+", gradeNameSpan : "+gradeNameSpan);
			//alert("minMarks : "+minMarks+", maxMarks : "+maxMarks);
			if(isNonEmpty(minMarksSpan) && isNonEmpty(maxMarksSpan) && isNonEmpty(gradeNameSpan)){
				minMarksSpan = Number($(this).children('span#minMarksSpan').attr("class"));
				maxMarksSpan = Number($(this).children('span#maxMarksSpan').attr("class"));
			    if(minMarks < maxMarksSpan && maxMarks >= minMarksSpan){
			          	alert(minMarksSpan+" to "+maxMarksSpan+" is already assigned for "+gradeNameSpan);
			           	$(evt).val('');
			           return false;
			    }
			    if(minMarks >= minMarksSpan && minMarks <= maxMarksSpan){
						alert(minMarksSpan+" to "+maxMarksSpan+" is already assigned for "+gradeNameSpan);
						$(evt).val('');
					    return false;
				}
				else if((maxMarks >= minMarksSpan && maxMarks <= maxMarksSpan)){
						alert(minMarksSpan+" to "+maxMarksSpan+" is already assigned for "+gradeNameSpan);
						$(evt).val('');
					    return false;
				}else{
				 return true;
				}
			}
	 });		
	}	
	
 }
</script>