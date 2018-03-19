<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
		<div style="color: red;" class="alert alert-info col-md-12">
			You have been used all your allotted
			<s:property value="smsAlloted" />
			SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
		</div>
	</s:if>
<s:if test="%{classList != null && !classList.isEmpty()}">
	<div id="selectPenClass">
		<s:form id="searchStudentByAdmissionNumber" method="post"
			action="ajaxPendingApplications" theme="simple" namespace="/admin"
			cssClass="form-horizontal">
			<input type="hidden" class="academicYearId" name="academicYearId">
			<div class="form-group">
				<label class="col-md-2 control-label"> Select Class : </label>
				<div class="col-md-3">
					<s:select list="classList" id="classId" listKey="id"
						listValue="className" name="classId" theme="simple" headerKey="0"
						cssClass="form-control required input-medium"
						onchange="javascript:classWiseAdmissionDetails(this.value);">
					</s:select>
				</div>
			</div>
		</s:form>
	</div>
</s:if>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<div id="sendMessageDiv" style="display: none;">
		<jsp:include
			page="/WEB-INF/pages/admin/admission/ajaxSendAdmissionMessages.jsp"></jsp:include>
	</div>
	<div class="spaceDiv">&nbsp;</div>
	<div class="col-md-7">
		<s:set name="pendingApplicationsCount" value="%{0}" />
		<p class="text-primary">
			<label><strong>Total Pending Applications : <s:property
						value="studentsList.size" /> (Boys: <span class="totalBoys">0</span>
					| Girls: <span class="totalGirls">0</span> | UnAssigned : <span class="totalUnAssigned">0</span>)
			</strong></label>
		</p>
	</div>
	<div class="col-md-9" style="float: right; margin-right: -212px;">
		<s:if test='%{#session.previousYear == "N"}'>
			<s:if
				test="%{(admissionSettings != null && admissionSettings != empty && admissionSettings.testConducted)}">
				<div class="col-md-3">
					<a style="cursor: pointer;" class="btn btn-primary btn-sm green"
						id="processApplicationLink"
						onclick="javascript:validateEntranceExamMarksUploadedForAllClasses(<s:property value="admissionSettings.academicYearId"/>,'exam','S');">Shortlist
						Applications<img style="display: none;" alt="Loading..."
						src="${pageContext.request.contextPath}/images/indicator.gif"
						id="processMarksIndicator">
					</a>
				</div>
				<div class="col-md-3">
					<a style="cursor: pointer;" class="btn btn-primary btn-sm red"
						id="processApplicationLink"
						onclick="javascript:validateEntranceExamMarksUploadedForAllClasses(<s:property value="admissionSettings.academicYearId"/>,'exam','R');">Reject
						Applications<img style="display: none;" alt="Loading..."
						src="${pageContext.request.contextPath}/images/indicator.gif"
						id="processMarksIndicator">
					</a>
				</div>
				
				<div class="col-md-3">
					<a style="cursor: pointer;" class="btn btn-primary btn-sm blue"
						id="processApplicationLink"
						onclick="javascript:generateAdmissionReport();">Download Application<img style="display: none;" alt="Loading..."
						src="${pageContext.request.contextPath}/images/indicator.gif"
						id="processMarksIndicator">
					</a>
				</div>
				
			</s:if>
			<s:else>
				<div class="col-md-3">
					<a style="cursor: pointer;" class="btn btn-primary btn-sm green"
						id="processApplicationLink"
						onclick="javascript:validateEntranceExamMarksUploadedForAllClasses(<s:property value="admissionSettings.academicYearId"/>,'noExam','S');">Shortlist
						Applications<img style="display: none;" alt="Loading..."
						src="${pageContext.request.contextPath}/images/indicator.gif"
						id="processMarksIndicator">
					</a>
				</div>
				<div class="col-md-3">
					<a style="cursor: pointer;" class="btn btn-primary btn-sm red"
						id="processApplicationLink"
						onclick="javascript:validateEntranceExamMarksUploadedForAllClasses(<s:property value="admissionSettings.academicYearId"/>,'noExam','R');">Reject
						Applications<img style="display: none;" alt="Loading..."
						src="${pageContext.request.contextPath}/images/indicator.gif"
						id="processMarksIndicator">
					</a>
				</div>
				<div class="col-md-3">
					<a style="cursor: pointer;" class="btn btn-primary btn-sm blue"
						id="processApplicationLink"
						onclick="javascript:generateAdmissionReport();">Download Application<img style="display: none;" alt="Loading..."
						src="${pageContext.request.contextPath}/images/indicator.gif"
						id="processMarksIndicator">
					</a>
				</div>
			</s:else>
		</s:if>
	</div>
	<div class="spaceDiv">&nbsp;</div>
	<div class="spaceDiv"></div>
	
	<s:form id="studentGenerateHTInfoForm" method="post" theme="simple" action="ajaxGenerateAdmissionFormReport" cssClass="form-horizontal" namespace="/admin">
	
	<s:hidden name="admissionSettings.id" />
	<s:hidden name="studentNumber" id="studNumbers"></s:hidden>
	
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th># Application</th>
				<th>Student Name</th>
				<th>Father Name</th>
				<th>Mobile Number</th>
				<!-- <th>Collect Fee</th> -->
				<th>Send Message To All
					<div class="checker">
						<span> <input type="checkbox" name="selectAll" value=""
							id="selectAllIds" onclick=checkAll();
							class="checkbox allonlineStudents"> </a>
						</span>
					</div>
				</th>
				<th>Select All
					<div class="checker">
						<span><input type="checkbox" name="selectAllApplications"
							onclick=selectAllOnlineApps();
							class="checkbox allonlineAppStudents"></span>
					</div>
				</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<s:set var="classNameId" value=""></s:set>
			<s:iterator value="studentsList">
				<s:set var="studentDetailsId" value="studId"></s:set>
				<s:if test="%{classId != #classNameId}">
					<tr class="boysGirls">
						<td colspan="9"><center>
								<div class="alert-info">
									<label><strong> Class Name : <s:property
												value="className" /> , Total Applications : <span
											class="studentSize">0</span> (Boys : <span class="boys">0</span>
											| Girls : <span class="girls">0</span> | UnAssigned : <span class="unAssigned">0</span>)
									</strong></label>
								</div>
							</center> <s:if
								test='%{(#session.previousYear=="N") && (admissionSettings != null && admissionSettings != empty && admissionSettings.testConducted)}'>
								<div style="float: right; margin: 5px -35px 5px 5px;">
									<div class="col-md-5">
										<a style="cursor: pointer;" class="btn btn-primary btn-sm"
											href="<c:url value='/admin/admissionMarksSheetTemplate.do'/>?classId=<s:property value="classId" />&anyTitle='<s:property value="className" />'"
											>Download Template</a>
									</div>
									<div class="col-md-5">
										<a style="cursor: pointer;" class="btn btn-primary btn-sm"
											href="#uploadEntranceMarksDiv" src="../img/menu-toggler.png"
											alt=""
											onclick="javascript:PopupUploadEntranceMarks(<s:property value="classId" />,<s:property value="academicYearId" />);">Upload
											Entrance Marks </a>
									</div>
								</div>
							</s:if></td>
					</tr>
				</s:if>
				<tr class="<s:property value="gender" />statusSex">
					<td>
						<%-- <s:url id="doEditClassSubjects"
										action="ajaxGetApplicationDetails" includeParams="all"
										escapeAmp="false" namespace="/admin">
										<s:param name="empId" value="%{applicationId}" />
										<s:param name="status" value="%{applicationStatus}" />
									</s:url>
									<sj:a href="%{doEditClassSubjects}"
										targets="admissionsPendingContentAppli"
										onBeforeTopics="cleanOpenDivs"
										onCompleteTopics="doInitClassDetails" indicator="indicator"
										button="false" buttonIcon="ui-icon-plus"> --%> <s:property
							value="applicationNumber" /> <%-- 	</sj:a> --%>
					</td>
					<td><s:property value="childrenFullPersonName" /></td>
					<td><s:if test='%{fatherName == "" || fatherName == null}'>
							 -
						</s:if> <s:else>
							<s:property value="fatherName" />
						</s:else></td>
					<td><s:if test='%{mobileNumber == "" || mobileNumber == null}'>
								-
						</s:if> <s:else>
							<s:property value="mobileNumber" />
						</s:else></td>
					<%-- <td>
						<!--<a data-toggle="modal"  href="#admissionsContentDiv" onclick="javascript:PopupManageAdmissionStudentFee(<s:property  value="%{applicationId}" />,<s:property value="%{academicYearId}" />);">Make Payment
								</a>
							--> <!--<a href="${pageContext.request.contextPath}/schoolfee/manageAdmissionStudentFee.do?tempId1=<s:property value='applicationId'/>&tempId2=<s:property value='academicYearId'/>&eventId=pendingList" class="right">Make Payment</a>
							--> <s:url id="doManageAdmissionStudentFee"
							action="ajaxManageAdmissionStudentFee" includeParams="all"
							escapeAmp="false" namespace="/schoolfee">
							<s:param name="tempId1" value="%{applicationId}"></s:param>
							<s:param name="tempId2" value="%{academicYearId}"></s:param>
							<s:param name="eventId">pendingList</s:param>
						</s:url> <sj:a href="%{doManageAdmissionStudentFee}" indicator="indicator"
							targets="admissionsContent" cssClass="">Pay</sj:a>
					</td> --%>
					<td><s:if test='%{mobileNumber == "" || mobileNumber == null}'>
					<span>
							<div class="checker">
								<span> <input type="checkbox" name="chkBoxSelectedIds"
									id="toSendMsg<s:property value='applicationNumber' />"
									value='<s:property value='mobileNumber' />' disabled="disabled"
									readonly="readonly" title="Do not have mobile number">
								</span>
							</div>
</span>
						</s:if> <s:else>
						<span>
							<div class="checker">
								<span> <input type="checkbox" name="chkBoxSelectedIds"
									id="toSendMsg<s:property value='applicationNumber' />"
									value='<s:property value='mobileNumber' />'>
								</span>
							</div>
							</span>
						</s:else></td>
					<td>
						<div class="checker">
							<span><input type="checkbox" name="anyId"
								value='<s:property value="applicationId"/>'></span>
						</div>
					</td>
					<td><s:url id="doEditClassSubjects"
							action="ajaxApplyOfflineAdmission" includeParams="all"
							escapeAmp="false" namespace="/admin">
							<s:param name="empId" value="%{applicationId}" />
							<s:param name="status" value="%{applicationStatus}" />
						</s:url> <sj:a href="%{doEditClassSubjects}"
							targets="admissionsPendingContentAppli"
							cssClass="btn btn-xs purple">
							<i class="fa fa-edit"></i>Edit</sj:a></td>
					<td><s:url id="removeClass" action="ajaxRemoveApplication"
							includeParams="all" escapeAmp="false" namespace="/admin">
							<s:param name="id" value="%{applicationId}"></s:param>
							<s:param name="status" value="%{status}"></s:param>
							<s:param name="academicYearId" value="%{academicYearId}"></s:param>
						</s:url> <s:div cssClass="btn btn-xs red"
							onclick="javascript:confirmDialogWithTarget(this,'admissionsPendingContentAppli');"
							id='%{removeClass}' theme="simple"
							title="Delete this application">
							<i class="fa fa-trash-o"></i> Delete
						</s:div></td>
				</tr>
				<s:set var="classNameId" value="classId"></s:set>
			</s:iterator>
			<div id="importsheetDiv"></div>
		</tbody>
	</table>
	</s:form>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no pending Applications found.
	</div>
</s:else>
<div id="uploadEntranceMarksDiv"></div>
<div id="admissionsContentDiv"></div>
<div id="sendMessageDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	$('li#admissionsNav > ul > li').removeClass('active');
	$('li#applicationsNav').addClass('active');
	
	if($('input#lastClassWiseType').is(":not(:checked)")){
			$("div#selectPenClass").hide();
	}
	changePageTitle("Pending Application");
	//$("input:checkbox, input:radio:not('.toggle')").uniform(); 
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
	$('#sendMessageDiv').hide();
	$("input[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		   $(this).removeAttr("checked");
	});
	 $(".allonlineStudents").parent('span').removeClass('checked');
     $(".allonlineStudents").attr("checked", "true");
     $(".allonlineStudents").removeAttr("checked");
     $('textarea#offLineMsgsForStudents').val('');
});	
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($(this).is(":checked")) {
		 $(this).parent('span').addClass('checked');
		 $(this).attr("checked", true);
		 $('#sendMessageDiv').show();
		 
	}else{
		 $(this).parent('span').removeClass('checked');
		 $(this).attr("checked", false);
		 $('#sendMessageDiv').hide();
	}   
	addMobileNumbers("SingleCheck");
}); 

 /* $("input[name=chkBoxSelectedIds]").click(function() {
	if ($(this).is(":checked")) {
		 $('#sendMessageDiv').show();
	}   
	addMobileNumbers("SingleCheck");
});  */

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
				 $('#sendMessageDiv').hide();
				 $('textarea#offLineMsgsForStudents').val('');
			 } 
		 }
		 if($(this).parent('span').hasClass("checked")){
		 if(isNonEmpty($(this).val())){
			 selectedonlineStuIds.push($(this).val()); 
		 }
	     $('#sendMessageDiv').show();
		 }
	});
	 if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		 $('#sendMessageDiv').hide();
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


function prepareNonSelectedStudIds(){
	
	if ($("input[name='anyId']:checked").length > 0) {
		var applicationIds = $("input[name='anyId']:checked");
		var selectedApplicationIds = '';
		selectedApplicationIds = '(';
			for ( var i = 0; i < applicationIds.length; i++) {
				selectedApplicationIds += applicationIds[i].value + ', ';
			}
			selectedApplicationIds += '0)';
			$('#studNumbers').val(selectedApplicationIds);
			
			$('#studentGenerateHTInfoForm').submit();
	}
	else{
		alert("Please select at least one application.");
	}
	
		
}


function validateEntranceExamMarksUploadedForAllClasses(academicYearId,cond,status) {
	if ($("input[name='anyId']:checked").length > 0) {
		var applicationIds = $("input[name='anyId']:checked");
		var selectedApplicationIds = '';
		selectedApplicationIds = '(';
			for ( var i = 0; i < applicationIds.length; i++) {
				selectedApplicationIds += applicationIds[i].value + ', ';
			}
			selectedApplicationIds += '0)';
	if(cond == 'exam'){
		var url = jQuery.url.getChatURL("/admin/ajaxCheckEntranceMarksUploadedForAllClasses.do?academicYearId="+academicYearId);
		$('#processMarksIndicator').show();
		$.ajax( {
			url : url,
			cache : false,
			dataType : 'json',
				success : function(response) {
				var data= response.admissionFee;
				var deleteType="normal Delete";
				var answer = '';
				if(isNonEmpty(data)) {
			        answer = confirm(""+data);
			     }else{
			    	   var answer = confirm("Are you sure you want to process applications ?");
					 if (answer) {
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
				if(isNonEmpty(answer)) {
					if (!answer) {
			               $('#processApplicationLink').attr("href", "javascript:void(0);");
			               $('#processMarksIndicator').hide();
			       } else {
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
			       }
				}else{
					$('#processMarksIndicator').hide();
				}
			}
		});
	}else{
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
}
else{
	alert("Please select at least one application.");
}
}
function PopupUploadEntranceMarks(id,academicYearId) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoUploadEntranceMarks.do");
	//$('#uploadEntranceMarksDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "classId=" + id+"&academicYearId="+academicYearId,
			success : function(html) {
				$("#uploadEntranceMarksDiv").html(html);
			}
		});
	} 
	function PopupManageAdmissionStudentFee(applicationId,academicYearId) {
	var url = jQuery.url.getChatURL("/admin/ajaxManageAdmissionStudentFee.do");
	$('#admissionsContentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "tempId1=" + applicationId+"&tempId2="+academicYearId,
			success : function(html) {
				$("#admissionsContentDiv").html(html);
			}
		});
	} 
	
	//Changed by seshu on 21st May 2014
function classWiseAdmissionDetails(classId) {
	$("#admissionsPendingContentAppli").html(
			'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$("#admissionsPendingContentAppli").show();
	var academicYearId = $('#academicYearId').val();
	if(isNonEmpty(academicYearId) && academicYearId != 0){
		if(isNonEmpty(classId)){
			var pars = "academicYearId=" + academicYearId + "&classId="+classId;
			var url = jQuery.url.getChatURL("/admin/ajaxPendingApplications.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#admissionsPendingContentAppli").html(html);
					$("#admissionsPendingContentAppli").show();
				}
			});			
		}else{
			$("#admissionsPendingContentAppli").html('<div class="alert alert-info">Please select class.</div>');
			$("#admissionsPendingContentAppli").show();
		}
	}else{
		$("#admissionsPendingContentAppli").html('<div class="alert alert-info">Please select academic year.</div>');
		$("#admissionsPendingContentAppli").show();
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

function generateAdmissionReport()
{
	
	var templatepath = '<s:property value="admissionSettings.admissionFormTemplatepath"/>';
	var templateFileName = '<s:property value="admissionSettings.admissionFormTemplateFileName"/>';
	
	if(isNonEmpty(templatepath) && isNonEmpty(templateFileName))
	{
		prepareNonSelectedStudIds();
	}
	else
	{
		alert("Template not found,please upload template in admission settings.");
		return false;
	}
	
	
}
</script>
