<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{displayAttendanceSet != null && !displayAttendanceSet.isEmpty()}"> 

<div class="form-group">
			<label class="control-label col-md-4">
				<span class="required">*</span>Select Sub Type :
			</label>
			<div class="col-md-6">
				<s:select list="displayAttendanceSet" listKey="id" listValue="subTypeName" onchange="javascript:getStudentsAddMarks(this.value)"
					cssClass="form-control input-medium required" name="examType" headerKey="" headerValue="- Select -"
					id="subtypeId" theme="simple">
				</s:select>
			</div>
		</div>
		
		<div id="displaytudentsAddMarks"></div>
</s:if>
<s:else>
	<div class="alert alert-info">You have not created Subtypes,
		Creating subtype is simple process and system would guide you.</div>
</s:else>
<script type="text/javascript">
function getStudentsAddMarks(subtypeId) {
	
	
	var studyClassId = $('#classSection').val();
	var examTypeListId = $('#examTypeListId').val();
	if(isNonEmpty(subtypeId)){
	$('#displaytudentsAddMarks').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/exam/ajaxDoGetStudentsAddMarks.do");
	var pars = "subtypeId="+subtypeId+"&classId="+studyClassId+"&anyId="+examTypeListId;
	//alert("cvs"+pars);
	$.ajax( {
		type : "POST",
		url : url,
		data : pars,
		cache : false,
		success : function(html) {
			$("#displaytudentsAddMarks").html(html);
		}
	});
	}
};
</script>