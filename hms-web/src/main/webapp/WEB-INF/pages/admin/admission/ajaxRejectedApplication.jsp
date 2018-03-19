<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{classList != null && !classList.isEmpty()}">
	<div class="searchDiv" id="searchRejAppClassWise">
		<s:form id="searchStudentByAdmissionNumber"
			action="ajaxRejectedApplicationsDetails" theme="simple"
			cssClass="form-horizontal" namespace="/admin">
			<input type="hidden" class="academicYearId" name="academicYearId">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required"> * </span>Select Class :
						</label>
						<div class="col-md-6">
							<s:select list="classList" id="classId" listKey="id"
								listValue="className" name="classId" theme="simple"
								cssClass="required form-control input-medium" headerKey="0" 
								onchange="javascript:classWiseRejectedAdmissionDetails(this.value);">
							</s:select>
						</div>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</s:if>
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<div class="spaceDiv"></div>
		<div id="sendMessageRejectDiv" style="display: none;">
			<jsp:include page="/WEB-INF/pages/admin/admission/ajaxSendAdmissionMessages.jsp"></jsp:include>
		</div>
	<div class="spaceDiv"></div>
	<div class="col-md-2" style="float: right;">
		<a style="cursor: pointer;" class="btn btn-primary btn-sm green"
			id="processApplicationLink"
			onclick="javascript:validateEntranceExamMarksUploadedForAllClasses(<s:property value="admissionSettings.academicYearId"/>,'S');">Shortlist
			Applications<img style="display: none;" alt="Loading..."
			src="${pageContext.request.contextPath}/images/indicator.gif"
			id="processMarksIndicator">
		</a>
	</div>
	<s:set name="rejectedApplicationsCount" value="%{0}" />
	<s:iterator value="classList">
		<s:if test="%{classSection != null && !classSection.isEmpty()}">
			<s:set name="rejectedApplicationsCount" value="%{#rejectedApplicationsCount+classSection.size}" />
		</s:if>
	</s:iterator>
	<div class="col-md-7">
		<p class="text-primary"><label><strong>Total Rejected Applications : <s:property
					value="studentsList.size" /> (Boys: <span class="totalBoys">0</span>
				| Girls: <span class="totalGirls">0</span> | UnAssigned : <span class="totalUnAssigned">0</span>) </strong></label></p>
	</div>
	<div class="spaceDiv">&nbsp;</div><div class="spaceDiv"></div>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					# Application
				</th>
				<th>
					Student Name
				</th>
				<th>
					Father Name
				</th>
				<th>
					Mobile Number
				</th>
				<%--<th>
						Pay Fee
					</th>
					--%>
				<th>
					Send Message To All
					</label> <div class="checker">
								<span> <input type="checkbox" name="selectAll" value="" id="selectAllIds"
					onclick=checkAll(); class="checkbox allonlineStudents"> 
				</th>
				<th>Select All <div class="checker">
								<span> <input type="checkbox" name="selectAllApplications"
					onclick=selectAllOnlineApps();
					class="checkbox allonlineAppStudents">
				</th>
				<th>
					Edit
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
			<s:set var="classNameId" value=""></s:set>
			<s:iterator value="studentsList">
				<s:set var="studentDetailsId" value="studId"></s:set>
				<s:if test="%{classId != #classNameId}">
					<tr class="boysGirls">
						<td colspan="8">
							<center>
								<p class="alert-info"><label><strong>Class Name : <s:property value="className" />
									, Total Applications : <span class="studentSize">0</span>
									(Boys: <span class="boys">0</span> | Girls: <span class="girls">0</span> | UnAssigned : <span class="unAssigned">0</span>)
								</strong></label></p>
							</center>
						</td>
					</tr>
				</s:if>
				<tr class="<s:property value="gender" />statusSex">
					<td>
						<%-- <s:url id="doEditClassSubjects" action="ajaxGetApplicationDetails"
							includeParams="all" escapeAmp="false" namespace="/admin">
							<s:param name="empId" value="%{applicationId}" />
							<s:param name="status" value="%{applicationStatus}" />
						</s:url>
						<sj:a href="%{doEditClassSubjects}"
							targets="academicsRejectedContentDiv"
							onBeforeTopics="cleanOpenDivs"
							onCompleteTopics="doInitClassDetails" indicator="indicator"
							button="false" buttonIcon="ui-icon-plus"> --%>
							<s:property value="applicationNumber" />
						<%-- </sj:a> --%>
					</td>
					<td>
						<s:property value="childrenFullPersonName" />
					</td>
					<td>
						<s:if test='%{fatherName == "" || fatherName == null}'>
											 -
										</s:if>
						<s:else>
							<s:property value="fatherName" />
						</s:else>
					</td>
					<td>
						<s:if test='%{mobileNumber == "" || mobileNumber == null}'>
											-
											</s:if>
						<s:else>
							<s:property value="mobileNumber" />
						</s:else>
					</td>
					<td>
						<s:if test='%{mobileNumber == "" || mobileNumber == null}'>
						<div class="checker">
								<span> 
							<input type="checkbox" name="chkBoxSelectedIds"
							id="toSendMsg<s:property value='applicationNumber' />" value='<s:property value='mobileNumber' />' disabled="disabled" readonly="readonly" title="Do not have mobile number"></span></div>
						</s:if>
						<s:else>
						<div class="checker">
								<span> 
						   <input type="checkbox" name="chkBoxSelectedIds"
							id="toSendMsg<s:property value='applicationNumber' />" value='<s:property value='mobileNumber' />'></span></div> 
						</s:else>
					</td>
					<td>
					<div class="checker">
								<span> 
						<input type="checkbox" name="anyId" value='<s:property value="applicationId"/>'>
						</span></div>
					</td>
					<td>
						<s:url id="doEditClassSubjects"
							action="ajaxGetApplicationDetails" includeParams="all"
							escapeAmp="false" namespace="/admin">
							<s:param name="empId" value="%{applicationId}" />
							<s:param name="status" value="%{applicationStatus}" />
						</s:url> 
						<sj:a href="%{doEditClassSubjects}"
							targets="academicsRejectedContentDiv"
							cssClass="btn btn-xs purple">
							<i class="fa fa-edit"></i>Edit</sj:a>
					</td>
					<td>
						<s:url id="removeClass" action="ajaxRemoveApplication"
							includeParams="all" escapeAmp="false">
							<s:param name="id" value="%{applicationId}"></s:param>
							<s:param name="status" value="%{status}"></s:param>
							<s:param name="academicYearId" value="%{academicYearId}"></s:param>
						</s:url>
						<s:div cssClass="btn btn-xs red"
							onclick="javascript:confirmDialogWithTarget(this,'academicsRejectedContentDiv');"
							id='%{removeClass}' theme="simple"
							title="Delete this application">
							<i class="fa fa-trash-o"></i>Delete
									</s:div>
					</td>
				</tr>
				<s:set var="classNameId" value="classId"></s:set>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no rejected applications for selected academic
		year.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Rejected Applications');
	//$("input:checkbox, input:radio:not('.toggle')").uniform(); 
	$('#pendingApplications').show();
	$('#createApplicationForm').hide();
	$('#topMenu').hide();
	if($('input#lastClassWiseType').is(":not(:checked)")){
		$("div#searchRejAppClassWise").hide();
	}
	$('.boysGirls').each(function() {
	 $(this).find('.boys').html(($(this).nextUntil('.boysGirls','tr.MstatusSex').size()));
	 $(this).find('.girls').html(($(this).nextUntil('.boysGirls','tr.FstatusSex').size()));
	 $(this).find('.unAssigned').html(($(this).nextUntil('.boysGirls','tr.statusSex').size()));
	  $(this).find('.studentSize').html(($(this).nextUntil('.boysGirls').size()));
	 $('.totalBoys').html($('tr.MstatusSex').size());
	 $('.totalGirls').html($('tr.FstatusSex').size());
	 $('.totalUnAssigned').html($('tr.statusSex').size());
	});

}); 

$('button.btn-default').click(function(){
	$('#sendMessageRejectDiv').hide();
	$("input[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		   $(this).removeAttr("checked");
	});
	 $(".allonlineStudents").parent('span').removeClass('checked');
     $(".allonlineStudents").attr("checked", "false");
     $(".allonlineStudents").removeAttr("checked");
     $('textarea#offLineMsgsForStudents').val('');
});	

$("input[name=chkBoxSelectedIds]").click(function() {
	if ($(this).is(":checked")) {
		 $(this).parent('span').addClass('checked');
		 $(this).attr("checked", true);
		 $('#sendMessageRejectDiv').show();
		 
	}else{
		 $(this).parent('span').removeClass('checked');
		 $(this).attr("checked", false);
		 $('#sendMessageRejectDiv').hide();
	}   
	addMobileNumbers("SingleCheck");
});
function addMobileNumbers(status) {
var selectedonlineStuIds = [];
$("#mobileNos").val("");
	 $("input[name='chkBoxSelectedIds']:enabled").each(function(){
		 if(status=="CheckAll" && $("input#selectAllIds").is(":checked")){
		    	$(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
		 }else{
			 if(status=="CheckAll"){
				 $(this).parent('span').removeClass('checked');
				 $(this).attr("checked", "false");
				 $(this).removeAttr("checked");
				 $('#sendMessageRejectDiv').hide();
				 $('textarea#offLineMsgsForStudents').val('');
			 } 
		 }
		 if($(this).parent('span').hasClass("checked")){
		 if(isNonEmpty($(this).val())){
			 selectedonlineStuIds.push($(this).val()); 
		 }
	     $('#sendMessageRejectDiv').show();
		 }
	});
	 if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		 $('#sendMessageRejectDiv').hide();
		 $('#selectAllIds').parent('span').removeClass('checked');
		 $('#selectAllIds').attr("checked", "false");
		 $('#selectAllIds').removeAttr("checked");
	 } 
	 if ($("input[name=chkBoxSelectedIds]:enabled").length != $("input[name=chkBoxSelectedIds]:checked").length) {
		 $('#selectAllIds').parent('span').removeClass('checked');
		 $('#selectAllIds').attr("checked", "false");
		 $('#selectAllIds').removeAttr("checked");
	 }
	 if ($("input[name=chkBoxSelectedIds]:enabled").length == $("input[name=chkBoxSelectedIds]:checked").length) {
		 $('#selectAllIds').parent('span').addClass('checked');
		 $('#selectAllIds').attr("checked", "true");
	 }
	 $("#mobileNos").val(selectedonlineStuIds);
	 $('#dosendMsgForPendStud_status').val('PendingApp');
}

function checkAll() {
	addMobileNumbers("CheckAll");
}

function classWiseRejectedAdmissionDetails(classId){
	$("#academicsRejectedContentDiv").html(
			'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>').show();
	var academicYearId = $('#academicYearId').val();
	if(isNonEmpty(academicYearId) && academicYearId != 0){
		if(isNonEmpty(classId)){
			var pars = "academicYearId=" + academicYearId + "&classId="+classId;
			var url = jQuery.url.getChatURL("/admin/ajaxRejectedApplicationsDetails.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#academicsRejectedContentDiv").html(html).show();
				}
			});			
		}else{
			$("#academicsRejectedContentDiv").html('<div class="alert alert-info">Please select class.</div>').show();
		}
	}else{
		$("#academicsRejectedContentDiv").html('<div class="alert alert-info">Please select academic year.</div>').show();
	}
}

function validateEntranceExamMarksUploadedForAllClasses(academicYearId,status) {
	if ($("input[name='anyId']:checked").length > 0) {
		var applicationIds = $("input[name='anyId']:checked");
		var selectedApplicationIds = '';
		selectedApplicationIds = '(';
			for ( var i = 0; i < applicationIds.length; i++) {
				selectedApplicationIds += applicationIds[i].value + ', ';
			}
			selectedApplicationIds += '0)';
		 var answer = confirm("Are you sure you want to process applications ?");
		 if (answer) {
			 $('#processMarksIndicator').show();
			 var pars = "tempId="+academicYearId+"&anyId="+selectedApplicationIds+"&anyTitle="+status;
   	   		var url = jQuery.url.getChatURL("/admin/ajaxProcessApplications.do");
    	  		$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(responce) {
						$("#mainContentDiv").html(responce);
						}
				});
		 	return true;
		 } else {
			$('#processMarksIndicator').hide();
			return false;
		 }
}
else{
	alert("Please select at least one application.");
}
}
$("input[name='anyId']").click(function() {
	if ($(this).is(":checked")) {
		$(this).parent('span').addClass("checked");
	    $(this).attr("checked", true);
	} else {
		   $(this).parent('span').removeClass("checked");
			$(this).attr("checked", false);
	}
	var i= $("input[name='anyId']").length;//0;
	var checked =$("input[name='anyId']:checked").length;//0;
	if(i==checked && i>0){
		$(".allonlineAppStudents").parent('span').addClass("checked");
		$(".allonlineAppStudents").attr("checked", true);
	}else{
		$(".allonlineAppStudents").parent('span').removeClass("checked");
		$(".allonlineAppStudents").attr("checked", false);
	}
});
function selectAllOnlineApps() {
	if ($(".allonlineAppStudents").is(':checked')){
		$(".allonlineAppStudents").parent('span').addClass("checked");
		$(".allonlineAppStudents").attr("checked", true);
	    $("input[name='anyId']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
		$(".allonlineAppStudents").parent('span').removeClass("checked");
		$(".allonlineAppStudents").attr("checked", false);
	 $("input[name='anyId']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}
</script>
 