<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studyClassSubjectDetails != null && !studyClassSubjectDetails.isEmpty()}">
	<div class="form-group" id="subjectSelectDiv">
		<label class="control-label col-md-2">
			Select Subject :
		</label>
		<s:if test="%{classSectionNameId > 0}">
			<div class="col-md-3">
				<s:select list="studyClassSubjectDetails" listKey="subjectId" value=""
					listValue="subjectName" theme="simple" id="editSubjectIds" headerKey="" headerValue="-Select Subject-"
					cssClass="required form-control input-medium" name="questionPaperBankVO.studySubjectVO.id"
					onchange="javascript:getLessonsBySubject(this.value);">
				</s:select>
			</div>
		</s:if>
		<s:else>
			<div class="col-md-3">
				<s:select list="tempList1" listKey="subjectId" 
					listValue="subjectName" theme="simple" id="subjectIds" headerKey="" headerValue="-Select Subject-"
					cssClass="required form-control input-medium" name="questionPaperBankVO.studySubjectVO.id"
					onchange="javascript:getLessonsBySubject(this.value);">
				</s:select>
			</div>
		</s:else>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		There are no subjects.
	</div>
</s:else>
<div id="lessondiv" style="display: none;"></div>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
	var studySubjectIds=$("#subjectId").val();
	$("#editSubjectIds").val(studySubjectIds);
	if(isNonEmpty(studySubjectIds)){
		document.getElementById('editSubjectIds').disabled = true;
		getLessonsBySubject(studySubjectIds);
	}
});
function getLessonsBySubject(subjectId){
	  var pars = "";
	  var lessonIds='<s:property value="lessonId"/>';
	  var classIds='<s:property value="classSectionNameId"/>';
	  var questionBankIds = '<s:property value="questionBankId"/>';
	  if(isNonEmpty(subjectId)){
		  $("div#lessondiv") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		  var url = jQuery.url.getChatURL("/exam/ajaxGetAllLessonsBySubjects.do?studyClassSubjectId="+subjectId+ "&lessonId="+lessonIds+ "&classSectionNameId="+classIds+ "&questionBankId="+questionBankIds);
		  		$.ajax( {
		  			url : url,
		  			cache : false,
		  			data : pars,
		  			success : function(html) {
						$("div#lessondiv").html(html);
						$("div#lessondiv").show();
		  			}
		  		});
	  } 
	  else{
		  $("div#questionDiv").hide();
		  $("div#submitDiv").hide();
		  $("div#uploadDiv").hide();
		  $("div#lessondiv").hide();
	  }
 }
</script>
