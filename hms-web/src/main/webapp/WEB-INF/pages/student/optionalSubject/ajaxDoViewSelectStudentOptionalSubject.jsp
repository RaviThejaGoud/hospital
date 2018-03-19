<%@ include file="/common/taglibs.jsp"%>
<div id="contentDiv">
	<%@ include file="/common/messages.jsp"%>
	<s:form id="selectStudentForm" action="#" theme="simple"
		cssClass="form-horizontal">
		<div class="form-group">
			<label class="col-md-3 control-label"> Select Class : </label>
			<div class="col-md-9">
				<s:select list="studyClassList" id="classSectionId"
					cssClass="form-control input-medium" name="classSectionId"
					listKey="id" listValue="classAndSection" headerKey=""
					headerValue="- Select Class -" theme="simple"
					onchange="javascript:getAjaxGetOptionalStudentDetails(this.value);" />
			</div>
		</div>
	</s:form>
	
	<div id="viewStudentsList"></div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var classSectionId= $('#classSectionId').val();
		if(classSectionId !=0){
			getAjaxGetOptionalStudentDetails(classSectionId);
		}
	});
	function getAjaxGetOptionalStudentDetails(studyClassId) {
		if (studyClassId != 0) {
			var pars = "classSectionId=" + studyClassId;
			$("#viewStudentsList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$('div.alert-success').hide();
			var url = jQuery.url.getChatURL("/student/ajaxSearchStudentByStudyClass.do");
			$.ajax({
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#viewStudentsList").html(html);
					$("#viewStudentsList").show();
				}
			});
		} else {
			$("#viewStudentsList").hide();
		}
	}
</script>