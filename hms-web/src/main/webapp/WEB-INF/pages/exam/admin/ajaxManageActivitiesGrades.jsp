<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body">
<s:form id="activitiesGradesCreationFM"
	action="ajaxAddOrUpdateActivitiesGrades" theme="simple" name="myform"
	cssClass="form-horizontal" namespace="/exam" method="post">
	<s:hidden name="tempString" cssClass="activitiesData"></s:hidden>
		<span class="label label-danger"> NOTE : </span>&nbsp;
				You can add/update grade,grade points for activities.
		<h4 class="bold pageTitle">
			Activities grades
		</h4>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Grade Name
					</th>
					<th>
						Grade Point
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<tbody>
				<s:if test="%{objectList != null && !objectList.isEmpty()}">
					<s:iterator value="objectList">
						<tr class="activitiesGradesData">
							<td id='<s:property value='id'/>' class='activitiesGradeId'>
									<sj:textfield name="gradeName" id="gradeName%{id}"
										maxlength="45"
										cssClass="form-control input-medium required gradeName"></sj:textfield>
							</td>
							<td>
									<sj:textfield name="gradePoint" id="gradePoint%{id}"
									    maxlength="4"
										cssClass="form-control input-medium required numeric gradePoint"></sj:textfield>
							</td>
							<td>
									<s:url id="removeActivitiesGrades"
										action="ajaxRemoveActivitiesGrade" includeParams="all"
										escapeAmp="false" namespace="/exam">
										<s:param name="tempId" value="%{id}" />
									</s:url>
									<s:div cssClass="btn btn-xs red"
										onclick="javascript:confirmDialogWithTarget(this,'studentsActivitiesContent');"
										id='%{removeActivitiesGrades}' theme="simple"
										title="Delete this grade">
										<i class="fa fa-times"></i>Delete
									</s:div>
							</td>

						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						You have not created any activities grades. You can able to create
						activities grades through + link.
						</div>
				</s:else>
			</tbody>
		</table>
		<div class="spaceDiv">
		</div>
		<div class="grid_12">
			<a href="javascript:void(0)"
				onclick="javascript:showActivitiesGradesCreationForm();"
				class="btn btn-xs green" style="width: 165px"><b><i
					class="fa fa-plus"></i> Add Activities Grade</b> </a>
		</div>
		<div class="spaceDiv">
		</div>
		<div class="spaceDiv"></div>
	<div class="form-actions fluid">
		<div class="col-md-offset-3 col-md-9">
			<sj:submit   targets="studentsActivitiesContent" value="Submit"
				cssClass="submit small btn blue"
				formIds="activitiesGradesCreationFM" indicator="indicator"
				 onBeforeTopics="addActivitiesGradesValidation" validate="true"/>
			<%-- <s:url id="urlImportActSheet" action="ajaxManageStudentActivities"
				includeParams="all" escapeAmp="false" namespace="/exam">
			</s:url>
			<sj:a href="%{urlImportActSheet}" cssClass="btn default"
				onCompleteTopics="highlight" indicator="downloadIndicator"
				targets="mainContentDiv">Cancel</sj:a> --%>
		</div>
	</div>
</s:form>
</div>
<script type="text/javascript">
$(document).ready(function(){
$.destroyTopic("addActivitiesGradesValidation");
	$('.numeric').numeric( {
		allow : "."
	});
});
$.subscribe('addActivitiesGradesValidation', function(event, data) {
	var activitiesGradeId = 0;
	var gradeName = '';
	var gradePoint = 0;
	var jsonObj = [];
	var boolVal=false;
	$('tr.activitiesGradesData') .each(
					function() {										
						activitiesGradeId = $(this).find("td.activitiesGradeId").attr("id");
						gradeName = $(this).find(".gradeName").val();
						gradePoint = $(this).find(".gradePoint").val();
						if (isNonEmpty(activitiesGradeId) && isNonEmpty(gradeName) && isNonEmpty(gradePoint)) {
							jsonObj.push( {
							            "gradeName" : gradeName,
										"gradePoint" : gradePoint,
										"activitiesGradeId" : activitiesGradeId
									});
						}
						else{
						 boolVal=true;
						}
						$('.submit').attr("disabled", true);
					});
		if(boolVal){
		  alert(" Grade name and Grade points can not be empty."); 
		  event.originalEvent.options.submit = false;
		}										
	$('.activitiesData').val(JSON.stringify(jsonObj));
	if (isNonEmpty(JSON.stringify(jsonObj))
			&& (JSON.stringify(jsonObj)) == '[]') {
		alert("Please add at least one activities grade using 'Add activities grade' link.");
		event.originalEvent.options.submit = false;
	}
});
var rowCount = 1;
function showActivitiesGradesCreationForm() {
	$("tbody")
			.append(
					'<tr class="activitiesGradesData"><td class="activitiesGradeId" id="0">'
							+ '<div><input type="text" maxlength="45" class="form-control input-medium required gradeName" onblur="javascript:validateActivityGradeNames(this);" id="gradeName'
							+ rowCount
							+ '" name="gradeName'
							+ rowCount
							+ '"/>'
							+ '</div></td>'
							+ '<td><div><input type="text" maxlength="4" class="form-control input-medium required numeric gradePoint" onblur="javascript:validateActivityGradePoints(this);" id="gradePoint'
							+ rowCount
							+ '" name="gradePoint'
							+ rowCount
							+ '"/>'
							+ '</div></td>'
							+ '<td><div><a title="Delete"  id="removeActivityGrade"></a><div class="btn btn-xs red newDeleteRecord"><i class="fa fa-times">&nbsp;Delete</i></div></div>'
							+ '</td></tr>');
	rowCount++;
	$('.numeric').numeric( {
		allow : "."
	});
$('div.newDeleteRecord').on('click',function(){
	 $(this).parents('tr').remove();
});
}

function validateActivityGradeNames(evt) {
	var selectedGradeName = $(evt).val();
	var selectedGradeId = $(evt).attr('id');
	if (isNonEmpty(selectedGradeName) && isNonEmpty(selectedGradeId)) {
		var gradeName = '';
		var gradeId = '';
		$('tr.activitiesGradesData')
				.each(
						function() {
							gradeName = $(this).find(".gradeName").val();
							gradeId = $(this).find(".gradeName").attr('id');
							if (isNonEmpty(gradeName) && isNonEmpty(gradeId)) {
								if (selectedGradeId.toLowerCase().replace(
										/\s+/g, '') != gradeId.toLowerCase()
										.replace(/\s+/g, '')) {
									if (selectedGradeName.toLowerCase()
											.replace(/\s+/g, '') == gradeName
											.toLowerCase().replace(/\s+/g, '')) {
										alert("'"
												+ selectedGradeName
												+ "'"
												+ " is already assigned. Please change grade name.");
										$(evt).val('');
										return false;
									}
								}
							}
						});
	}
}
function validateActivityGradePoints(evt) {
	var selectedGradePoint = $(evt).val();
	var selectedGradeId = $(evt).attr('id');
	if (isNonEmpty(selectedGradePoint) && isNonEmpty(selectedGradeId)) {
		var gradePoint = '';
		var gradeId = '';
		$('tr.activitiesGradesData')
				.each(
						function() {
							gradePoint = $(this).find(".gradePoint").val();
							gradeId = $(this).find(".gradePoint").attr('id');
							if (isNonEmpty(gradePoint) && isNonEmpty(gradeId)) {
								if (selectedGradeId.toLowerCase().replace(
										/\s+/g, '') != gradeId.toLowerCase()
										.replace(/\s+/g, '')) {
									if (selectedGradePoint.toLowerCase()
											.replace(/\s+/g, '') == gradePoint
											.toLowerCase().replace(/\s+/g, '')) {
										alert("'"
												+ selectedGradePoint
												+ "'"
												+ " is already assigned. Please change grade point.");
										$(evt).val('');
										return false;
									} else {
										var pointValue = '.0';
										if (selectedGradePoint
												+ pointValue.toLowerCase()
														.replace(/\s+/g, '') == gradePoint
												.toLowerCase().replace(/\s+/g,
														'')) {
											alert("'"
													+ selectedGradePoint
													+ "'"
													+ " is already assigned. Please change grade point.");
											$(evt).val('');
											return false;
										}
									}
								}
							}
						});
	}
}
</script>