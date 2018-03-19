<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{classList != null && !classList.isEmpty()}">
		<div class="searchDiv" id="selectAdmitStudentClass">
			<s:form id="searchStudentByAdmissionNumber" action="ajaxAdmittedStudents" cssClass="form-horizontal" theme="simple" namespace="/admin">
				<input type="hidden" class="academicYearId" name="academicYearId">
				<div class="form-group">
					<label class="col-md-2 control-label"> <span
						class="required">*</span>Select Class :
					</label>
					<div class="col-md-3">
						<s:select list="classList" id="classId" listKey="id" listValue="className" name="classId" theme="simple"
							cssClass="required form-control input-medium" onchange="javascript:academicClassWiseAdmissionDetails(this.value);">
						</s:select>
					</div>
				</div>
			</s:form>
		</div>
	</s:if>
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<div id="sendMessageAdmittedDiv" style="display: none;">
			<jsp:include page="/WEB-INF/pages/admin/admission/ajaxSendAdmissionMessages.jsp"></jsp:include>
	</div>
	<div class="spaceDiv">&nbsp;</div>
	<div class="col-md-4">
		<p class="text-primary"><label><strong>
			Total Admitted Students :
			<s:property value="studentsList.size" />
			(Boys:
			<span class="totalBoys">0</span> | Girls:
			<span class="totalGirls">0</span>)

		</strong></label></p>
	</div>
	<div style="float: right;">
		<div class="col-md-7">
			<a
				href="${pageContext.request.contextPath}/reports/ajaxNewAdmittedStudentFeeDetails.do?academicYearId=<s:property value="academicYearId" />&tempId=<s:property value="classId"/>"
				 style="cursor: pointer;" class="btn btn-primary btn-sm">Download Students Fee Collection Report</a>
		</div>
		<div class="col-md-5">
			<a
				href="${pageContext.request.contextPath}/reports/ajaxDoDownloadAdmittedStudents.do?academicYearId=<s:property value="academicYearId" />&classId=<s:property value="classId"/>"
				 style="cursor: pointer;" class="btn btn-primary btn-sm">Download Admitted Students</a>
		</div>
	</div>
	<div class="spaceDiv">&nbsp;</div><div class="spaceDiv"></div>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Name
				</th>
				<th>
					# Admission
				</th>
				<th>
					Father Name
				</th>
				<th>
					Mobile Number
				</th>
				<th>
					Make Payment
				</th>
				<th>
					Receipt
				</th>
				<th>
					Send Message To All </label>
					<div class="checker">
								<span> 
					 <input type="checkbox" name="selectAll" value="" id="selectAllIds" onclick=checkAll(); class="checkbox allonlineStudents"> </a>
					 </span>
					 </div>
				</th>
				<th>
					Edit
				</th>
			</tr>
		</thead>
		<s:set var="classNameId" value=""></s:set>
		<s:iterator value="studentsList">
			<s:set var="studentDetailsId" value="studId"></s:set>
			<s:if test="%{classId != #classNameId}">
				<tr class="boysGirls">
					<td colspan="8">
						<center>
						<p class="alert-info"><label><strong>
								Class Name :
								<s:property value="className" />
								, Total Students :
								<span class="studentSize">0</span>
								(Boys:
								<span class="boys">0</span> | Girls:
								<span class="girls">0</span>)
							</strong></label></p>
						</center>
					</td>
				</tr>
			</s:if>
			<tr class="<s:property value="gender" />statusSex">
				<td>
					<%-- <s:url id="editStudentDetails" action="ajaxDoEditAdmittedStudents"
						includeParams="all" escapeAmp="false" namespace="/admin">
						<s:param name="anyId" value="%{accountId}" />
					</s:url>
					<sj:a href="%{editStudentDetails}"
						targets="academicSettingsContent" title="Edit"> --%>
						<s:property value="firstName" />
								&nbsp;
						<s:property value="lastName" />
				<%-- 	</sj:a> --%>
				</td>
				<td>
					<s:property value="admissionNumber" />
				</td>
				<td>
					<s:if test="%{fatherName != null && !fatherName.isEmpty()}">
						<s:property value="fatherName" />
					</s:if>
				</td>
				<td>
					<s:if test="%{mobileNumber != null && !mobileNumber.isEmpty()}">
						<s:property value="mobileNumber" />
					</s:if>
				</td>
					<s:if test='%{feeConfigured == "Y"}'>
						<td align="center">
							<s:if test='%{feePaidStatus != "F"}'>
								<s:url id="doManageAdmissionStudentFee" action="ajaxManageAdmissionStudentFee" includeParams="all" escapeAmp="false" namespace="/schoolfee">
									<s:param name="anyId" value="%{#studentDetailsId}"></s:param>
									<s:param name="tempId2" value="%{academicYearId}"></s:param>
									<s:param name="paymentStatus" value="%{paymentStatus}"></s:param>
								</s:url>
								<sj:a href="%{doManageAdmissionStudentFee}" indicator="indicator" targets="mainContentDiv" cssClass="">Pay</sj:a> / 
							</s:if>
							<a data-toggle="modal" href="#editPaymentDiv" onclick="javascript:PopupEditPaymentDetails(<s:property value="%{#studentDetailsId}" />,'P');">View</a>
						</td>
						<td align="center">
							<s:if test='%{feePaidStatus != "N"}'>
								<a data-toggle="modal" href="#viewAdmittedStudent" class="btn btn-xs blue"
									onclick="javascript:PopupViewAdmittedStudent(<s:property value="%{#studentDetailsId}" />,<s:property value="%{academicYearId}" />);">Download</a>
							</s:if><s:else>-</s:else>
						</td>
					</s:if>
					<s:else>
						<td align="center">-</td>
						<td align="center">-</td>
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
						id="toSendMsg<s:property value='applicationNumber' />" value='<s:property value='mobileNumber' />'> </span></div>
					</s:else>
				</td>
				<%-- <td>
					<a data-toggle="modal" href="#sendMessageAdmittedDiv"> <input
							type="checkbox" name="chkBoxSelectedIds"
							onclick="javascript:popupAdmittedStudsSendMessages('<s:property value='mobileNumber' />'); return false;"
							id="toSendMsg<s:property value='admissionNumber' />"
							value='<s:property value='mobileNumber' />'> </a>
				</td> --%>
				<td>
					<s:url id="editStudentDetails" action="ajaxDoEditAdmittedStudents"
						includeParams="all" escapeAmp="false" namespace="/admin">
						<s:param name="anyId" value="%{accountId}" />
					</s:url> 
					<sj:a href="%{editStudentDetails}" targets="academicSettingsContent"
						cssClass="btn btn-xs purple"> <i class="fa fa-edit"></i>Edit</sj:a>
				</td>
			</tr>
			<div id="searchFineFee<s:property value="#studentDetailsId"/>" style="display: none;" class="load"></div>
			<s:set var="classNameId" value="classId"></s:set>
		</s:iterator>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no admitted students for selected academic year.
	</div>
</s:else>
<div id="editPaymentDiv"></div>
<div id="viewAdmittedStudent"></div>
<script type="text/javascript">

$(document).ready(function() {
	// $("input:checkbox, input:radio:not('.toggle')").uniform();
	if($('input#lastClassWiseType').is(":not(:checked)")){
		$("div#selectAdmitStudentClass").hide();
	}
	$('.boysGirls').each(function() {
	 $(this).find('.boys').html(($(this).nextUntil('.boysGirls','tr.MstatusSex').size()));
	 $(this).find('.girls').html(($(this).nextUntil('.boysGirls','tr.FstatusSex').size()));
	  $(this).find('.studentSize').html(($(this).nextUntil('.boysGirls').size()));
	 $('.totalBoys').html($('tr.MstatusSex').size());
	 $('.totalGirls').html($('tr.FstatusSex').size());
	});
});
function PopupViewAdmittedStudent(studentDetailsId,academicYearId) {
	var url = jQuery.url.getChatURL("/schoolfee/ajaxStudentDownloadReceipt.do");
	$('#viewAdmittedStudent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "anyId=" + studentDetailsId +"&tempId2=" + academicYearId,
			success : function(html) {
				$("#viewAdmittedStudent").html(html);
			}
		});
	} 
function PopupEditPaymentDetails(id,paymentStatus) {
	var url = jQuery.url.getChatURL("/schoolfee/ajaxViewFeeSettingInvoice.do");
	$('#editPaymentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "id=" + id +"&paymentStatus=" + paymentStatus,
			success : function(html) {
				$("#editPaymentDiv").html(html);
			}
		});
}  
 
$('button.btn-default').click(function(){
	$('#sendMessageAdmittedDiv').hide();
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
		 $('#sendMessageAdmittedDiv').show();
		 
	}else{
		 $(this).parent('span').removeClass('checked');
		 $(this).attr("checked", false);
		 $('#sendMessageAdmittedDiv').hide();
		 
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
				 $('#sendMessageAdmittedDiv').hide();
				 $('textarea#offLineMsgsForStudents').val('');
			 } 
		 }
		 if($(this).parent('span').hasClass("checked")){
		 if(isNonEmpty($(this).val())){
			 selectedonlineStuIds.push($(this).val()); 
		 }
	     $('#sendMessageAdmittedDiv').show();
		 }
	});
	 if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		 $('#sendMessageAdmittedDiv').hide();
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
</script>
