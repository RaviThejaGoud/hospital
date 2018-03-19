<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Exam Results
				</div>
			</div>
			<div class="portlet-body">
				<div id="marksContent" class="tab-content">
					<div class="row">
						<div class="col-md-6">
							<s:if test='%{viewStudentsLatestExamMarksDetailsList != null && !viewStudentsLatestExamMarksDetailsList.isEmpty()}'>
								<span id="studyClassIdSpan" class="<s:property value='tempId2'/>"></span>
								<div class="form-group">
									<label class="control-label col-md-4">
										Select Class & Section :
									</label>
									<div class="col-md-5">
										<s:select list="viewStudentsLatestExamMarksDetailsList"
											listKey="classSectionId" onchange="javascript:getExamTypesMoreDetails(this.value);"
											listValue="classAndSection" id="selectedClasId" name="className" headerKey="tempId"
											cssClass="form-control input-medium required">
										</s:select>
									</div>
								</div>
							</s:if> 
							<s:else>
								<div class="alert alert-info">Currently Student marks are not available. Once the marks are uploaded results will be displayed accordingly.
								</div>
							</s:else>
						</div>
					   <div id="examTypeResultsMoreDiv"> </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
var classSectionId=$('span#studyClassIdSpan').attr("class");
	if(isNonEmpty(classSectionId)){
		getExamTypesMoreDetails(classSectionId);
	}
});
function getExamTypesMoreDetails(tempId2) {
	if (isNonEmpty(tempId2)) {
		var params = "tempId2=" + tempId2+"&classId=";
		$('#examTypeResultsMoreDiv').html('<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/exam/ajaxGetExamTypesByClassNameClassId.do"),
			cache : true,
			data : params,
			success : function(response) {
				$('#examTypeResultsMoreDiv').html(response);
			}
		});
	}
	else
	{
		$('#examTypeResultsMoreDiv').html("");
	  alert("Please select exam type.");
	}
}
</script>