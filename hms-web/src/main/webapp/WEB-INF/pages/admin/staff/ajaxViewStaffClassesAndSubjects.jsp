<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:if test='%{(studySubjectListVo != null && !studySubjectListVo.isEmpty()) || (studyClassList!=null && studyClassList.isEmpty())}'>
		<s:form action="ajaxEditStaffEligibleSubjects" theme="simple"
			id="editStaffEligibleSubjects" method="post" cssClass="form-horizontal" namespace="/admin">
			<div id="viewStaffPersondetails">
			</div>
			<s:hidden name="tempId" value="%{tempId}" />
			<s:hidden name="anyId" value="%{anyId}" />
			<s:if test="%{studyClassList !=null && !studyClassList.isEmpty()}">
				<div class="form-group">
					<label class="conLable control-label col-md-3"><span class="required">*</span> Assign Classes :</label>
					<div class="col-md-12">
						<s:checkboxlist name="chkBoxClassSelectedIds" listKey="id" theme="ems" 
							cssClass="checkbox" list="studyClassList" 
							listValue="classAndSection" />
					</div>
				</div>
				</s:if>
				<div class="form-group">
						<label class="conLable control-label col-md-3"><span class="required">*</span> Staff Eligible subjects :</label>
						<div class="col-md-12">
							<s:if
								test="%{studySubjectListVo !=null && !studySubjectListVo.isEmpty()}">
								<s:checkboxlist name="chkBoxSelectedIds"  
									cssClass="checkbox required" list="studySubjectListVo" listKey="id"
									theme="ems" listValue="name" />
							</s:if>
						</div>
					</div>
				<s:if
					test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
					<div class="form-actions fluid">
						<div class="col-md-offset-2 col-md-9">
							<sj:submit   cssClass="submitBt btn blue" value="Save" validate="true"
								targets="staffEditContentDiv" formIds="editStaffEligibleSubjects"
								onBeforeTopics="doInitStaffEligibleSubjects" />
						</div>
					</div>
				</s:if>
			<s:if test="%{alertsList != null && !alertsList.isEmpty()}">
				<div class="spaceDiv"></div>
				<h4 class="pageTitle bold">
					Staff Assignments
				</h4>
				<jsp:include page="/common/messages.jsp"></jsp:include>
				<div id="staffSubjectsDiv"></div>
				<p>
					<span class="label label-danger"> NOTE : </span> &nbsp;Simple! just
					select class in the dropdown box and hit Add button.
				</p>
					<s:iterator value="alertsList" status="status">
						<div class="col-md-6">
							<s:set name="subjectId" value="%{id}"></s:set>
							<b><s:property value="name" />
							</b>
						</div>
						<div class="col-md-6">
							<s:if
								test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
								<a data-toggle="modal"  href="#responsive3"  class="btn btn-xs purple" 
									onclick="javascript:PopupStaffClassAndSubjects(<s:property value="%{id}" />,<s:property value="%{tempId}" />,'<s:property value="%{anyId}" />');"><i class="fa fa-edit"></i>Assign Class </a>
							</s:if>
						</div>
						<s:iterator value="subjectClassList" status="status">
							<div id="removeTeacherSubject<s:property value='classTeacherId' />" >
								<div style="margin-left:10px;">
									<s:url id="removeTeacherSubject"
										action="ajaxDeleteTeacherSubject" escapeAmp="false" namespace="/admin">
										<s:param name="tempId1" value="%{classTeacherId}"></s:param>
									</s:url>
									<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
										<s:div cssClass="btn btn-xs red emsRemove" id='%{removeTeacherSubject}'  
											title="Delete this Class Subject for this staff."><i class="fa fa-trash-o"></i></s:div>
											
									</s:if>
									<s:property value="classAndSection" />
								</div>
							</div>
							<div class="spaceDiv"></div>
						</s:iterator>
						<div class="spaceDiv"></div>
						<div class="spaceDiv"></div>
						<div id="assignTeacherSubjectToClass"></div>
						<div class="spaceDiv"></div>
					</s:iterator>
			</s:if>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no subjects available.
		</div>
	</s:else>
</div>
<div id="responsive3"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	$.destroyTopic('doInitStaffEligibleSubjects');
	/*$.subscribe('doInitClassDetails', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});*/
	$.subscribe('doInitStaffEligibleSubjects',function(event, data) {
		if(($("input[name='chkBoxSelectedIds']:checked").length) > 0){
			event.originalEvent.options.submit=true;
		}else{
			alert("Please select at least one subject.");
			event.originalEvent.options.submit=false;
		}
		/*if ($('#editStaffEligibleSubjects').valid()) 
			return true;
		 else
			return false;*/
	});
});
 if ($('div.emsRemove')) {
	$('div.emsRemove').unbind('click');
	$("div.emsRemove").click(function() {
		confirmDialog(this);
	});
} 

function PopupStaffClassAndSubjects(id,tempId,anyId) {
		var url = jQuery.url.getChatURL("/admin/ajaxAssignTeacherSubjectToClass.do");
		$('#responsive3').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "subjectId=" + id +"&tempId=" +tempId +"&anyId=" +anyId,
				success : function(html) {
					$("#responsive3").html(html);
				}
			});
		} 
		
function confirmDialog(event) {
	thishref = $(event).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
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
					var staffSubjectsClass = response.info;
					if (isNonEmpty(staffSubjectsClass)) {
					alert("NOTE: "+staffSubjectsClass);
						prdDiv.remove();
					} else{
					prdDiv.parent().parent().remove();
					}
				} else {
					prdDiv.parent().parent().remove();
					$('div#staffSubjectsDiv').html('<div class="alert alert-success"><strong>StaffClass Subjects deleted successfully.</strong><button class="close" data-dismiss="alert"></button></div>');
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