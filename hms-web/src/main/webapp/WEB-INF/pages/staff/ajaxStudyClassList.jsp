<%@ include file="/common/taglibs.jsp"%>
<div class="form-body form-horizontal">
	<div class="form-group">
		<label class="col-md-2 control-label">
			Select Class :
		</label>
		<div class="col-md-5">
			<s:select list="studyClassList" id="className"
				cssClass="form-control input-medium" name="classSectionId" headerKey="" headerValue="-Select Class"
				listKey="id" listValue="classAndSection" theme="simple"
				onchange="javascript:getAjaxDoGetStudent(this.value);">
			</s:select>
		</div>
	</div>
</div>
<script type="text/javascript">
function getAjaxDoGetStudent(studyClassId) {
if(studyClassId>0){
var senderValue='<s:property value="anyTitle"/>';
	var pars = "classId=" + studyClassId+"&anyTite="+senderValue;
	$("#classListDiv") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/staff/ajaxDoSendLoginCredentials.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#classListDiv").html(html);
			$("#classListDiv").show();
		}
	});
}else{
 alert("Please select class");
 return false;
}
}
</script>