<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />

<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					Student Assignment Report
				</div>
			</div>
			<div class="portlet-body">
	<div class="form-body">		
<s:form action="ajaxStudentAssignmentReport" id="studentAssignmentFormId" enctype="multipart/form-data" method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">

	<%-- <s:hidden id="classSubject" name="anyTitle"></s:hidden> --%>
		
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required"> * </span>Select Class :
					</label>
					<div class="col-md-5">
						<s:select list="tempList" name="viewClassAssignmentDetails.classSectionId"
							listKey="classSectionId" id="classSectionId" listValue="classAndSection" headerKey=""
							headerValue="- Select Class -" theme="simple"
							onchange="javascript:getClassSubjects(this.value);"
							cssClass="required form-control input-medium">
						</s:select>
					</div>
				</div>
				
				<div id="studyClassSubjectDiv"></div>
				
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-6 col-md-9">
							<sj:submit   targets="displayResultsDivId" value="Submit" validate="true"
								cssClass="submit small btn blue" formIds="studentAssignmentFormId"
								onBeforeTopics="addRouteFormValidation1" indicator="indicator" />
							<%-- <s:url id="urlManageRoute" action="ajaxManageRoutes"
								namespace="/admin" />
							<sj:a href="%{urlManageRoute}" targets="mainContentDiv"
								cssClass="cancelButton btn default">
										 Cancel</sj:a> --%>
						</div>
					</div>
				</div>
		
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Currently no classes assigned for you.
				</div>
			</s:else>
	
</s:form>


<br/><br/><br/>
<div id="displayResultsDivId"></div>


</div>

</div>
			</div>
		</div>
</div>


<script type="text/javascript">
changePageTitle('Student Assignment Report');
var assignmentId=0;
$(document).ready(function() {
	
	
});
 
$.subscribe('addClassAssignment', function(event, data) {
	var subjectId = $('#subjectIds').val();
	var classAssignmentId='<s:property value="classAssignment.id" />';
	var classIds = 0;
	if (isNonEmpty(subjectId) && subjectId > 0) {
		$("#classSubject").val($("select[id='subjectId'] option:selected").text());
		  if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			  classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			if ((classIds.length > 0 ) && isNonEmpty(classIds)) {
				selectedClassIds = '(';
				for ( var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				selectedClassIds += '0)';
			}
			$("#classNameIds").val(selectedClassIds);
			var balance = document.getElementById("anyId").value;
			/* if($('#startDate').val()!=''){
				 if (balance == 'SMS') {
					 $("#imagDiv").html('<img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading');
					 $('.submitBt').attr("disabled", true);
				} else {
					$("#imagDiv").html('<img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading');
					$('.submitBt').attr("disabled", true);
				} 
			} */
		 }else if(classAssignmentId==0){
			 alert("Please select Class");
			 event.originalEvent.options.submit = false;
		 }
	 }
	 $('button.close').click();
	return true;
});
function getClassSubjects(studyClassid) {
	//var subjectId = $("select#subjectId").val();
	var url = jQuery.url.getChatURL("/admin/ajaxGetAssignmentClassSubjects.do");
	if (studyClassid.length == 0) {
		alert("!Oops select class");
		return false;
	} else {
		$("#studyClassSubjectDiv").html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId2=" + studyClassid;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#studyClassSubjectDiv").html(html);
				//document.getElementById('studyClassSubjectDiv').style.display = "block";
			}
		});
	}
}
function confirmDialog(event) {
	thishref = $(event).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event).after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax( {
			url : thishref,
			cache : false,
				success : function(response) {
				if (isNonEmpty(response.info)) {
					var classAttach = response.info;
					if (isNonEmpty(classAttach)) {
						prdDiv.remove();
					} else{
					prdDiv.parent().parent().remove();
					}
				} else {
					prdDiv.parent().parent().remove();
					$('div#attachmentDiv').html('<div class="alert alert-success"><strong>Attachment removed successfully.</strong><button class="close" data-dismiss="alert"></button></div>');
				}
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
}

</script>