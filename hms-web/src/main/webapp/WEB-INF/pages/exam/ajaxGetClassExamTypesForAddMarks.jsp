<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{examTypeList != null && !examTypeList.isEmpty()}">
<s:if test='%{plTitle == "Class Wise Marks"}'>
		<div class="form-group">
			<label class="conLable col-md-11 control-label"> Select Exam Type : </label>
			<div class="col-md-11">
				<div class="checkbox-list">
					<label class="checkbox-inline"> <input type="checkbox"
						name="" value="" onClick="checkAllExamTypes()"
						class="checkbox allExamTypes"> All ExamTypes
					</label>
				</div>
			</div>
			<div class="col-md-11">
				<div class="checkbox-list">
					<s:checkboxlist name="chkBoxExamTypeIds" list="examTypeList"  
					listKey="id" listValue="examType" theme="ems" cssClass="small" />
				</div>
			</div>
		</div>
		<span class="label label-danger">NOTE :</span> If you want to calculate the student marks for percentage wise please give the percentage value for the respected subtypes. It will be applicable for the respected subtypes.
		<div>&nbsp;</div>
		
	</s:if>
	<s:else>
		<div class="form-group">
			<label class="control-label col-md-4">
				<span class="required">*</span>Select Exam Type :
			</label>
			<div class="col-md-6">
				<s:select list="examTypeList" listKey="id" listValue="examType" onchange="javascript:getsubtypes(this.value)" headerKey="" headerValue="- Select -"
					cssClass="form-control input-medium required" name="examType"
					id="examTypeListId" theme="simple">
				</s:select>
			</div>
		</div>
		<div id="displaySubtypesList"></div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info"> You do not have any active
		Exam Types. Please create <b>Exam Type</b> </div>
</s:else>
<script type="text/javascript">
function getsubtypes(examTypes) {
	//anyId
	var studyClassId = $('#classSection').val();
	if(isNonEmpty(examTypes)){
	$('#displaySubtypesList').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/exam/ajaxSubTypesByEamytpeIdForAddMarks.do");
	var pars = "anyId="+examTypes+"&studyClassId="+studyClassId;
	//alert("cvs"+pars);
	$.ajax( {
		type : "POST",
		url : url,
		data : pars,
		cache : false,
		success : function(html) {
			$("#displaySubtypesList").html(html);
			$("#displaySubtypesList").show();
		}
	});
	}
}
</script>
