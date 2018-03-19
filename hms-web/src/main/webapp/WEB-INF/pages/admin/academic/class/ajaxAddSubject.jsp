<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studySubject.id != 0}">
<div data-width="760"  class="modal fade modal-overflow in" id="responsive2" style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Update Subject
		</h4>
	</div>
	<div class="modal-body">
	</s:if>
	<s:form action="ajaxAddSubjects" theme="simple" id="addStudySubject"
		method="post" cssClass="form-horizontal" namespace="/admin">
		<s:hidden name="studySubject.id" value="%{studySubject.id}" id="studySubjectId" />
		<s:hidden name="studySubject.subjectType" value="%{studySubject.subjectType}" id="booleanValue" />
		<div class="form-body">
		<s:if test="%{studySubject.id == 0}">
			<h4 class="bold pageTitle">
				Add subject
			</h4>
		</s:if>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Subject Name :
						</label>
						<div class="col-md-7">
							<sj:textfield name="studySubject.name" id="subjectName"
								cssClass="required form-control input-medium" maxlength="40" />
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label class="control-label col-md-5">
							Subject Code :
						</label>
						<div class="col-md-7">
							<sj:textfield name="studySubject.subjectNumber" id="subNum"
								onchange="javascript:formateSubjectNumber(this);"
								cssClass="form-control input-medium" maxlength="10" />
						</div>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Short Name :
						</label>
						<div class="col-md-7">
							<sj:textfield name="studySubject.description" id="subDesc"
								cssClass="form-control input-medium" maxlength="20" />
						<span class="help-block">Please use less characters for short name.</span>
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label class="control-label col-md-5">
							Is language :
						</label>
						<div class="col-md-7">
							<p class="form-control-static"><s:checkbox name="studySubject.language" /></p>
							<span class="help-block">(Select the check box if the
								subject is Language.<br /> Example: Hindi, English,etc.)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Is Optional :
						</label>
						<div class="col-md-7">
							<p class="form-control-static"><s:checkbox name="subjectType" id="subjVal"/></p>
							<span class="help-block">(Select the check box if the
								subject is Optional.<br /> Example: Telugu, Sanskrit,Kanada,Tamil etc.)</span>
						</div>
					</div>
				</div>
				<div class="col-md-5">
					&nbsp;
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit cssClass="submitBt btn blue" value="Submit"  id="clickSubmit"
							onBeforeTopics="subjFormValidation" validate="true" indicator="indicator"
							targets="mainContentDiv" formIds="addStudySubject" />
						<s:if test="%{studySubject.id != 0}">
							<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
						</s:if>
						<s:else>
							<s:url id="doViewSubjects" action="ajaxGetStudyClassSubjects"
								includeParams="all" escapeAmp="false" namespace="/admin">
							</s:url>
							<sj:a href="%{doViewSubjects}" cssClass="btn default"
								indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	<s:if test="%{studySubject.id != 0}">
</s:if>
<script type="text/javascript">
/* $('#clickSubmit').bind('click', function() {
    $(this).unbind('click');
}); */
	$(document).ready(function() {
		
	$("input:checkbox, input:radio").uniform();
	changePageTitle("Add Subject");
	$('.numeric').numeric();
	var subjectType = $('#booleanValue').val();
	if(subjectType == "Y"){
		$('input#subjVal').attr('checked',true);
		$('input#subjVal').parent('span').addClass('checked');
	}else
		$("#subjVal").removeAttr("checked");
	
	var studySubjectId=$('#studySubjectId').val();
	$.destroyTopic('subjFormValidation');
	$("#subjectName").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckStudySubjectValuesAvailableOrNot.do?selectedId="+studySubjectId,{
		minChars : 3,
		min : "no"
	}).focus();
	 $("#subNum").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckStudyNumnerValuesAvailableOrNot.do?selectedId="+studySubjectId,{
		minChars : 3,
		min : "no"
	});
	 $("#subDesc").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckStudyDescriptionValuesAvailableOrNot.do?selectedId="+studySubjectId,{
		minChars : 3,
		min : "no"
	});
	});
	$.subscribe('subjFormValidation',function(event, data) {
		
	var notValid="No";
	 $('p.word-taken').each(function() {
	  if($(this).html()=='Already taken!!!'){
		  notValid = "Yes";
	     event.originalEvent.options.submit=false;
	   }
	 });
	 
	/*  var subjectName = $('#subjectName').val();
	 
	 if(!isNonEmpty(subjectName)){
		 
		 alert("Please enter subject name.");
		 event.originalEvent.options.submit=false;
	 } */
	 
	 $('p.word-available').each(function() 
	 {
		  if($(this).html()=='Available'){
			  $('button.close').click();
			  return true;
		   }
	 });
	 
	 if ($('#addStudySubject').valid()){
		 if("Yes" == notValid)
		 {
			 event.originalEvent.options.submit=false;
		 }
		 else
		 {
			 $('button.close').click();
				return true;
		 }
			
	}
	 
	// $('button.close').click();
	// return true;
	 
	});
	function formateSubjectNumber(event){
		var subjectNum = $(event).val();
		if(isNonEmpty(subjectNum)){
			if(subjectNum.length < 3){
				for(var i = 0 ; i < (4 - subjectNum.length) ; i++){
					subjectNum = '0'+subjectNum;
				}
			}
			$(event).val(subjectNum);
		}
	}
	$("input[name='subjectType']").click(function(){
        if($(this).prop("checked") == true){
            $('#booleanValue').val('Y');
        }
        else if($(this).prop("checked") == false){
            $('#booleanValue').val('N');
        }
    });
	
</script>