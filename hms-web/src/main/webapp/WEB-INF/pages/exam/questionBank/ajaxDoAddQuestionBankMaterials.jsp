<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxAddQuestionBankMaterials" theme="simple" id="addQuestionBankForm" enctype="multipart/form-data"
	cssClass="form-horizontal form-body" namespace="/exam" method="post">
	<s:hidden name="questionPaperBankVO.id" value="%{questionPaperBankVO.id}" id="questionBankMaterial"></s:hidden>
		<s:hidden name="classSectionId" value="%{classSectionId}" id="classSectionId"></s:hidden>
		<s:hidden name="subjectId" value="%{subjectId}" id="subjectId"></s:hidden>
		<s:hidden name="lessonId" value="%{lessonId}" id="lessonId"></s:hidden>
		<h4 class="bold pageTitle">
		<s:if test="%{questionPaperBankVO.id != 0}">
			Update Question Bank
		</s:if>
		<s:else>
				Create Question Bank
		</s:else>
		</h4><br>
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
				Select Class :
			</label>
			<s:if test="%{classSectionId > 0}">
				<div class="col-md-3">
					<s:select list="studyClassList" listKey="id" value="classSectionId"
						listValue="classAndSection" theme="simple" id="studyClassAndSectionId" headerKey="" headerValue="-Select Class-" 
						cssClass="required form-control input-medium" name="questionPaperBankVO.studyClassVO.id"
						onchange="javascript:getSubjectsByClass(this.value);">
					</s:select>
				</div>
			</s:if>
			<s:else>
				<div class="col-md-3">
					<s:select list="studyClassList" listKey="id"
						listValue="classAndSection" theme="simple" id="studyClassId" headerKey="" headerValue="-Select Class-" 
						cssClass="required form-control input-medium" name="questionPaperBankVO.studyClassVO.id"
						onchange="javascript:getSubjectsByClass(this.value);">
					</s:select>
				</div>
			</s:else>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			There are no Classes.
		</div>
	</s:else>
	<div id="studySubjectdiv" style="display: none;"></div>
</s:form>
<script language="JavaScript" type="text/javascript">
	 $(document).ready(function() {
		$("input[type=file].multi").MultiFile();
		$.destroyTopic('questionBankFormValidation');
		var classSectionIds=$("#classSectionId").val();
		if(isNonEmpty(classSectionIds)){
			document.getElementById('studyClassAndSectionId').disabled = true;
			getSubjectsByClass(classSectionIds);
		}
		$.subscribe('questionBankFormValidation', function(event, data) {
		});
	});
	 
   function getSubjectsByClass(classSectionId){
	  var pars = "";
	  var lessonIds='<s:property value="lessonId"/>';
	  var questionBankIds = '<s:property value="questionPaperBankVO.id"/>';
	  if(isNonEmpty(classSectionId)){
		  $("div#studySubjectdiv") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		  var url = jQuery.url.getChatURL("/exam/ajaxGetAllStudySubjects.do?classSectionNameId="+classSectionId + "&lessonId="+lessonIds +"&questionBankId="+questionBankIds);
		  		$.ajax( {
		  			url : url,
		  			cache : false,
		  			data : pars,
		  			success : function(html) {
						$("div#studySubjectdiv").html(html);
						$("div#studySubjectdiv").show();
		  			}
		  		});
	  } else{
		  $("div#questionDiv").hide();
		  $("div#submitDiv").hide();
	      $("div#uploadDiv").hide();
	      $("div#subjectSelectDiv").hide();
	      $("div#lessonSelectDiv").hide();
	  }
   }
</script>