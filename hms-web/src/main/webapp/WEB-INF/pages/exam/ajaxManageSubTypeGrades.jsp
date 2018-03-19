<%@ include file="/common/taglibs.jsp"%>
<%--<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery.struts2.js">	
</script>
--%>
<div data-width="760" class="modal fade modal-overflow in"
	id="responsive5"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Subtype Name :
			<s:property value="tempString" />
		</h4>
		<h4 class="modal-title">
			Subtype Grades
		</h4>
	</div>
	<jsp:include page="/common/messages.jsp"></jsp:include>
	<s:form id="subtypesGradesCreationFM"
		action="ajaxAddOrUpdateSubtypeGrades" theme="simple"
		cssClass="form-horizontal" name="myform" namespace="/exam">
		<div class="modal-body" style="padding: 42px;position: relative;">
			<s:hidden name="anyTitle" cssClass="subtypesGradesData"></s:hidden>
			<s:hidden name="tempId" value="%{tempId}"></s:hidden>
			<s:hidden name="tempString" value="subTypes"></s:hidden>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample">
				<thead>
					<tr>
						<th>
							Grade Name
						</th>
						<th>
							Min Marks
						</th>
						<th>
							Max Marks
						</th>
						<th>
							Grade Point
						</th>
						<th>
							Delete
						</th>
					</tr>
				</thead>
				<tbody id="subTypeContentDiv">
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<s:iterator value="objectList">
							<div class="manageSubGradesDiv">
								<span id="minMarksSpan" class="<s:property value='minMarks'/>"></span>
								<span id="maxMarksSpan" class="<s:property value='maxMarks'/>"></span>
							</div>
							<tr class="subTypesGradesContSpan">
								<td id='<s:property value='id'/>' class='subTypeGradeId'>
									<sj:textfield name="grade" id="gradeName%{id}" maxlength="45"
										onblur="javascript:validateActivityGradeNames(this);"
										cssClass="required form-control input-small gradeName"></sj:textfield>
								</td>
								<td>
									<sj:textfield name="minMarks" id="minMarks%{id}" maxlength="5"
										onchange="javascript:validateGradeMarks(this, %{id});"
										cssClass="required numeric form-control input-small minMarks"></sj:textfield>
								</td>
								<td>
									<sj:textfield name="maxMarks" id="maxMarks%{id}" maxlength="5"
										onchange="javascript:validateGradeMarks(this, %{id} );"
										cssClass="required numeric form-control input-small maxMarks"></sj:textfield>
								</td>
								<td>
									<sj:textfield name="gradePoint" id="gradePoint%{id}"
										maxlength="5"
										onblur="javascript:validateSubtypeGradePoints(this);"
										cssClass="required numeric form-control input-small gradePoint"></sj:textfield>
								</td>
								<td>
									<s:if test='%{#session.previousYear=="N"}'>
										<s:url id="removeSubTypeGrades"
											action="ajaxRemoveSubtypeGrade" includeParams="all"
											escapeAmp="false" namespace="/exam">
											<s:param name="tempId" value="%{tempId}" />
											<s:param name="tempId1" value="%{id}" />
										</s:url>
										<s:div cssClass="btn btn-xs red"
											onclick="javascript:confirmDialogWithTarget(this,'responsive5');"
											id='%{removeSubTypeGrades}'
											title="Delete this grade">
											<i class="fa fa-times"></i>Delete
													</s:div>
									</s:if>
									<s:else>
									</s:else>
								</td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>
			</table>
			<s:if test='%{#session.previousYear == "N"}'>
				<a href="javascript:void(0)"
					onclick="javascript:showSubTypeGradesCreationForm();"
					class="btn btn-xs green"><i class="fa fa-plus"></i><b>Add
						Subtype Grade</b> </a>
				<div class="modal-footer">
					<div class="form-actions fluid">
						<div class="col-md-6">
							<div class="col-md-offset-3 col-md-12">
								<img src="../img/bg/bigWaiting.gif" alt="Loading..."
									title="Loading..." id="indicator"
									style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
								<sj:submit targets="examContentInfo" value="Submit"
									cssClass="submitBt btn blue" formIds="subtypesGradesCreationFM"
									indicator="indicator" validate="true"
									onBeforeTopics="addSubtypesGradesValidation" />
							</div>
						</div>
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="subTypesGradesContSpan"></div>
				<s:if test='%{#session.previousYear == "N"}'>
					<a href="javascript:void(0)"
						onclick="javascript:showSubTypeGradesCreationForm();"
						class="btn btn-xs green" style="width: 165px"><i
						class="fa fa-plus"></i><b>Add Subtype Grade</b> </a>
					<div class="modal-footer">
						<div class="form-actions fluid">
							<div class="col-md-6">
								<div class="col-md-offset-3 col-md-12">
									<img src="../img/bg/bigWaiting.gif" alt="Loading..."
										title="Loading..." id="indicator"
										style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
									<sj:submit targets="responsive5" value="Submit"
										cssClass="submitBt btn blue" id="submitButton"
										formIds="subtypesGradesCreationFM" indicator="indicator"
										validate="true" onBeforeTopics="addSubtypesGradesValidation" />
								</div>
							</div>
						</div>
					</div>
					<div class="alert alert-info">
						You have not created any subtype grades. You can able to create
						subtype grades through + link.
					</div>
				</s:if>
			</s:else>
		</div>
	</s:form>
</div>
<script type="text/javascript">
	//TableAdvanced.init();
$(document).ready(function() {
	$.destroyTopic('addSubtypesGradesValidation');
	$('.numeric').numeric({allow:"."});
	$.subscribe('addSubtypesGradesValidation', function(event, data) {
		var subTypeGradeId = 0;
		var gradeName = '';
		var gradePoint = 0;
		var minMarks = 0;
		var maxMarks = 0;
		var jsonObj = [];
			$('tr.subTypesGradesContSpan').each(function() {
						subTypeGradeId = $(this).find("td.subTypeGradeId").attr("id");
						gradeName = $(this).find(".gradeName").val();
						gradePoint = $(this).find(".gradePoint").val();
						minMarks = $(this).find(".minMarks").val();
						maxMarks = $(this).find(".maxMarks").val();
						if (isNonEmpty(subTypeGradeId)
								&& isNonEmpty(gradeName)
								&& isNonEmpty(gradePoint) && isNonEmpty(minMarks) && isNonEmpty(maxMarks)) {
							jsonObj.push( {
								"gradeName" : gradeName,
								"gradePoint" : gradePoint,
								"subTypeGradeId" : subTypeGradeId,
								"maxMarks" : maxMarks,
								"minMarks" : minMarks
							});
						}
					});
			$('.subtypesGradesData').val(JSON.stringify(jsonObj));
			//alert(JSON.stringify(jsonObj));
			if(isNonEmpty(JSON.stringify(jsonObj)) && (JSON.stringify(jsonObj)) != '[]' ){
				if(event.originalEvent.options.submit)
				$('button.close').click();
				return true;
				
			}else{
				alert("Please add at least one subtype grade using 'Add subtype grade' link.");
				event.originalEvent.options.submit=false;
			}
	});
});

function toggleSubTypesGradesCont(subtypeId){
	$('#responsive5'+subtypeId).hide();
}
var rowCount = 1;
function showSubTypeGradesCreationForm() {
	$("tbody#subTypeContentDiv")
			.append(
					'<tr class="subTypesGradesContSpan">'
							+ '<td class="subTypeGradeId" id="0"><input type="text" maxlength="45" class="required form-control input-small gradeName" onblur="javascript:validateActivityGradeNames(this);" id="gradeName'
							+ rowCount
							+ '" name="grade'
							+ rowCount
							+ '"/>'
							+ '</td>'
							+ '<td><input type="text" maxlength="4" style="width:35px;" class="required numeric form-control input-small minMarks" onchange="javascript:validateGradeMarks(this,'+rowCount+');" id="minMarks'
							+ rowCount
							+ '" name="minMarks'
							+ rowCount
							+ '"/>'
							+ '</td>'
							+ '<td><input type="text" maxlength="4"  style="width:35px;" class="required numeric form-control input-small maxMarks" onchange="javascript:validateGradeMarks(this,'+rowCount+');" id="maxMarks'
							+ rowCount
							+ '" name="maxMarks'
							+ rowCount
							+ '"/>'
							+ '</td>'
							+ '<td><input type="text" maxlength="4" style="width:35px;" class="required numeric form-control input-small gradePoint" onblur="javascript:validateSubtypeGradePoints(this);" id="gradePoint'
							+ rowCount
							+ '" name="gradePoint'
							+ rowCount
							+ '"/>'
							+ '</td>'
							+ '<td><a title="Delete" class="btn btn-xs red" id="removeActivityGrade" onclick="removeSubTypeGradeRow(this)"><i class="fa fa-times"></i>Delete</a></td>'
							+ '</tr>');
	rowCount++;

	$('.numeric').numeric({allow:"."});
}
function validateActivityGradeNames(evt){
	var selectedGradeName = $(evt).val();
	var selectedGradeId = $(evt).attr('id');
	if(isNonEmpty(selectedGradeName) && isNonEmpty(selectedGradeId)){
		var gradeName = '';
		var gradeId = '';
		$('tr.subTypesGradesContSpan').each(function() {
			gradeName = $(this).find(".gradeName").val();
			gradeId = $(this).find(".gradeName").attr('id');
			if(isNonEmpty(gradeName) && isNonEmpty(gradeId)){
				if(selectedGradeId.toLowerCase().replace(/\s+/g, '') != gradeId.toLowerCase().replace(/\s+/g, '')){
					if(selectedGradeName.toLowerCase().replace(/\s+/g, '') == gradeName.toLowerCase().replace(/\s+/g, '')){
						alert("'"+selectedGradeName+"'"+" is already assigned. Please change grade name.");
			           	$(evt).val('');
			           event.originalEvent.options.submit=false;
					}					
				}
			}
	 	});	
	}
}
function validateSubtypeGradePoints(evt){
	var selectedGradePoint = $(evt).val();
	var selectedGradeId = $(evt).attr('id');
	if(isNonEmpty(selectedGradePoint) && isNonEmpty(selectedGradeId)){
		var gradePoint = '';
		var gradeId = '';
		$('tr.subTypesGradesContSpan').each(function() {
			gradePoint = $(this).find(".gradePoint").val();
			gradeId = $(this).find(".gradePoint").attr('id');
			if(isNonEmpty(gradePoint) && isNonEmpty(gradeId)){
				if(selectedGradeId.toLowerCase().replace(/\s+/g, '') != gradeId.toLowerCase().replace(/\s+/g, '')){
					if(selectedGradePoint.toLowerCase().replace(/\s+/g, '') == gradePoint.toLowerCase().replace(/\s+/g, '')){
						alert("'"+selectedGradePoint+"'"+" is already assigned. Please change grade point.");
			           	$(evt).val('');
			            event.originalEvent.options.submit=false;
					}					
				}
			}
	 	});	
	}
}
function removeSubTypeGradeRow(evnt) {
	$(evnt).parents('tr').remove();
}
function validateGradeMarks(evt,incrementId){
	 var maxMarks ="";
	 var minMarks ="";
	 maxMarks = Number($("#maxMarks"+incrementId+"").val());
	 minMarks= Number($("#minMarks"+incrementId+"").val());
	 if(isNonEmpty(minMarks) && isNonEmpty(maxMarks)){
		if(minMarks > maxMarks){
			alert('Sub grades min marks should be less than max marks.');
			$(evt).val('');
		}
	}
	 
/*  var max = $(evt).hasClass('maxMarks');
 var min = $(evt).hasClass('minMarks');
 var maxMarks ="";
 var minMarks ="";
	 if(max == true){
	 	maxMarks = Number($(evt).val());
	 	}
	 if(min == true){
		minMarks = Number($(evt).val());
	 }
	if(isNonEmpty(minMarks) && isNonEmpty(maxMarks)){
		if(minMarks > maxMarks){
			alert('Sub grades min marks should be less than max marks.');
			$(evt).val('');
		}
	}	 */
	$('div.manageSubGradesDiv').each(function() {
		var minMarksSpan = $(this).children('span#minMarksSpan').attr("class");
		var maxMarksSpan = $(this).children('span#maxMarksSpan').attr("class");
		//alert(minMarksSpan+"----"+maxMarksSpan);
		//alert(minMarks+"----"+maxMarks);
		//alert(maxMarks+"----"+minMarksSpan+"-- && ---"+maxMarks+"----"+maxMarksSpan);
		if(isNonEmpty(minMarksSpan) && isNonEmpty(maxMarksSpan)){
		var minMarksSpan = Number($(this).children('span#minMarksSpan').attr("class"));
		var maxMarksSpan = Number($(this).children('span#maxMarksSpan').attr("class"));
		   if(minMarks < maxMarksSpan && maxMarks >= minMarksSpan){
		          	alert(minMarksSpan+" to "+maxMarksSpan+" is already assigned.");
		           	$(evt).val('');
		           event.originalEvent.options.submit=false;
		    }
		   if(minMarks >= minMarksSpan && minMarks <= maxMarksSpan){
					alert(minMarksSpan+" to "+maxMarksSpan+" is already assigned.");
					$(evt).val('');
				   event.originalEvent.options.submit=false;
			}
			else if((maxMarks >= minMarksSpan) && (maxMarks <= maxMarksSpan)){
					alert(minMarksSpan+" to "+maxMarksSpan+" is already assigned.");
					$(evt).val('');
				    event.originalEvent.options.submit=false;
			}else{
			event.originalEvent.options.submit=true;
			}
		 }
	 });
 }
</script>