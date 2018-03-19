<%@ include file="/common/taglibs.jsp"%>
<s:form id="downloadTemplate"
	action="ajaxDownloadStudentsActivitiesMarkSheet" method="post"
	theme="simple" cssClass="form-horizontal"
	onsubmit="javascript:return getActivitiesSubmitErrors();" namespace="/exam">
	<div class="form-body">
		<h4 class="bold pageTitle">
			Download activities template
		</h4>
		<div class="form-group">
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							Please select class then system shows exam types applicable for
							this class then click on 'Download Sheet' button.
						</li>
						<li>
							This excel sheet has marked with set of column names that are supported
							by the system.
						</li>
						<li>
							<font color="red">Please do not add new columns or delete
								the marked columns</font>. If you want add more columns, please contact
							EazySchool support team(support@eazyschool.com).
						</li>
						<li>
							If any activity doesn't contain category name then that activity
							come under Others.
						</li>
					</ul>
				</div>
			</div>
		</div>
		<s:hidden name="anyTitle" value="" />
		<s:hidden name="tempString" value="" />
		<s:hidden name="classId" value="" />
		<s:hidden name="classSectionId" value="" />
		<s:hidden name="anyId" value="" />
		<s:hidden name="empId" value="" />
		<s:hidden name="x" value="" />
		<s:hidden id="admisnum" name="eventId"></s:hidden>
		<div class="row">
			<div class="col-md-8">
				<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
					<label class="control-label col-md-3">
						<span class="required">*</span>Select Class :
					</label>
					<div class="col-md-13">
						<s:select list="studyClassList" listKey="classSectionIdAndClassId"
							listValue="classAndSection" id="classSection"
							cssClass="form-control input-medium required"
							onchange="javascript:getClassExamTypes(this.value);"
							name="classSectionAndclassId" headerKey=""
							headerValue="- Select Class -">
						</s:select>
					</div>
				</s:if>
				&nbsp;
				<s:else>
					<div class="alert alert-info">
						There are no Classes.
					</div>
				</s:else>
			</div>
			<div class="col-md-6">
				<div id="templateActExamTypesContent">
				</div>
			</div>
		</div>
		<div>
			<div id="activitiesCategoriesDiv">
			</div>
		</div>
		&nbsp;
		<div class="spaceDiv"></div>
		<div class="form-actions fluid" id="submitDivId">
			<div class="col-md-offset-2 col-md-9">
				<s:submit value="Download Sheet" cssClass="submit long btn blue" />
				<s:url id="urlImportActSheet" action="ajaxViewStudentActivities"
					includeParams="all" escapeAmp="false" namespace="/exam">
				</s:url>
				<%-- <sj:a href="%{urlImportActSheet}" cssClass="btn default"
					onCompleteTopics="highlight" indicator="downloadIndicator"
					targets="studentsActivitiesContent">Cancel</sj:a> --%>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
	if($("div#noCategoriesDiv").is(":visible") || $("div#noExamTypesDiv").is(":visible")){
		  $("div#submitDivId").hide();
	}
	
	if(isNonEmpty($("#classSection").val())){
		$("div#submitDivId").show();
	}
	else{
		$("div#submitDivId").hide();
	}
});
changePageTitle('Download Activities Template');
	function getActivitiesSubmitErrors(){
		var admissionNumber = $('input[name=SelectType]:radio:checked').val();
		$("#admisnum").val(admissionNumber);
			var classSectionId=$("#classSection").val();
			var examTypeId=$("#examTypeListId").val();
			if(isNonEmpty(classSectionId) && isNonEmpty(examTypeId) && $("input[name='categories']:checked").length > 0){
				
				var examType=$("#examTypeListId  option[value="+examTypeId+"]").text();
				var categoryNames = [];
				var studentAssessments = [];
				if(isNonEmpty(examType))
				$("[name='tempString']").val(examType);
				$("[name='classId']").val(classSectionId.split(':')[1]);
				$("[name='classSectionId']").val(classSectionId.split(':')[0]);
				$("input[name='categories']:checked").each(function(){
						categoryNames.push("'"+$(this).val()+"'");					
				});
				$("input[name='anyId']").val(categoryNames);
				$("input[name='stuAssessment']:checked").each(function(){
						studentAssessments.push($(this).val());					
				});
				$("input[name='empId']").val(studentAssessments);
				$("input[name='x']").val(studentAssessments.length);
				$('#submitDivId').show();
				return true;
			}
			 else{
				if($("div#noCategoriesDiv").is(":visible") || $("div#noExamTypesDiv").is(":visible")){
				  $("div#submitDivId").hide();
				  return false;
				}
				else{
					
					if(isNonEmpty(classSectionId)){
						alert("Please select category.");
					}
					else{
						alert("Please select class.");	
					}
					return false;
				}
			} 
       }
       function getClassExamTypes(classSectionAndClassId){
       if(isNonEmpty(classSectionAndClassId)){
            var classId= classSectionAndClassId.split(':')[0];
			var className=$("#classSection  option:selected").text();
			if(isNonEmpty(className))
				$("[name='anyTitle']").val(className);
			$('#templateActExamTypesContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "classId=" + classId;					
			$.ajax( {
				url : jQuery.url.getChatURL("/exam/ajaxGetClassExamTypesForStudentActivities.do"),
				cache : true,
				data : pars,
				success : function(response) {
					$('#templateActExamTypesContent').html(response);
					//if($('select#examTypeListId').is(":visible")){
					 getCategoriesList(classSectionAndClassId.split(':')[1],classSectionAndClassId.split(':')[0]);
					//}
					 $('#submitDivId').show();
					 if($("div#noCategoriesDiv").is(":visible") || $("div#noExamTypesDiv").is(":visible")){
						  $("div#submitDivId").hide();
					}
					
				}
			});
           }else{
            alert("Please select class.");
            $('#templateActExamTypesContent').html('');
            $('#activitiesCategoriesDiv').html('');
            $('#submitDivId').hide();
            return false;
           }
       }
       
       function getCategoriesList(classId,classSectionAndClassId){
       if($('select#examTypeListId').is(":visible")){
         $('#activitiesCategoriesDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars1 = "classId=" + classId+"&tempId="+classSectionAndClassId;			
			$.ajax( {
				url : jQuery.url.getChatURL("/exam/ajaxGetClassCategoriesForStudentActivities.do"),
				cache : true,
				data : pars1,
				success : function(response) {
					$('#activitiesCategoriesDiv').html(response);
					var selectedType=$("span#selectedGPM").html();
					if(isNonEmpty(selectedType)){
					 var existGradeType='G';
					 $("span.selectedTypeValue").find("span").each(function(){
					 existGradeType=$(this).find('input').val();
					 if(selectedType==existGradeType){
					  $(this).addClass("checked");
					  $(this).find("input").attr("checked","checked");
					 }
					 else{
					  $(this).removeClass("checked");
					  $(this).find("input").removeAttr("checked");
					  $(this).find("input").attr("disabled","disabled");
					 }
					 });
					}
				}
			 });
			 }
			 else{
			   $('#activitiesCategoriesDiv').html('');
			   $('#submitDivId').hide();
			 }
       }
</script>