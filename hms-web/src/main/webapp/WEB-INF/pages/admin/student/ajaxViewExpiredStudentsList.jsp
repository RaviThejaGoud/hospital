<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<p class="text-primary">
		Total Inactive Students :
		<b><s:property value="objectList.size" /></b>
	</p>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Photo
				</th>
				<th>
					Student Name
				</th>
				<th>
					Admission No
				</th>
				<th>
					Class & Section
				</th>
				<th>
					Join Date
				</th>
				<th>
					Left Date
				</th>
				<th>
					Reason
				</th>
				<th>
					Active
				</th>
				<s:if test='%{customer.preferences.feeRefund==true}'>
					<th>
						Fee Refund
					</th>
				</s:if>
				<th>
					Payment Receipt
				</th>
			</tr>
		</thead>
		<tbody>
			<s:set var="roleName" value="" />
			<s:iterator value="objectList">
				<tr>				
					<td>
						<s:if test="%{adjustedAttachmentFilePath != null && adjustedAttachmentFilePath != empty}">
							<img height="50px;" width="50px" src='<c:url value="${adjustedAttachmentFilePath}"/>' border="0" />
						</s:if>
					</td>
					<s:if
						test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
						<td>
							<a data-toggle="modal" href="#showInActiveStudenstProfileDiv"
								onclick="javascript:showInActiveStudentProfileDetails(<s:property value="studentId" />,<s:property value="classSectionId" />);"><s:property value="personFullName" />
							</a>
						</td>
					</s:if>
					<td>
						<s:property value="admissionNumber" />
					</td>
					<td>
						<s:property value="classAndSection" />
					</td>
					<td>
						<s:property value="studentDateOfJoing" />
					</td>
					<td>
						<s:property value="studentDateOfDiscontune" />
					</td>
					<td>
						<s:property value="description" />
					</td>
					<td>
						<s:if
							test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N") && feeRefundStatus != "Y"}'>
							<s:url id="enableStudent" action="ajaxEnableStudentDetails"
								includeParams="all" escapeAmp="false" namespace="/student">
								<s:param name="tempId" value="%{accountId}"></s:param>
								<s:param name="tempId1" value="%{classNameClassId}"></s:param>
								<s:param name="classSectionId" value="%{classSectionId}"></s:param>
							</s:url>
							<s:div id='%{enableStudent}' theme="simple"
								title="Active this student" cssClass="btn btn-xs green"
								onclick="javascript:confirmDialogWithTarget(this,'staffList');">
								Active
							</s:div>
						</s:if>
						<s:else> - </s:else>
					</td>
					<s:if test='%{customer.preferences.feeRefund==true}'>
						<td>
						<%-- <s:div id='%{enableStudent}' theme="simple"
									title="Active this student" cssClass="btn btn-xs green"
									onclick="javascript:viewFeeRefund(this,);">
									Refund
								</s:div> --%>
							<s:if test='%{feeRefundStatus != "Y" && feePaidStatus != "N"}'>
								<s:url id="searchStudentsForRefund" action="ajaxDoStudentFeeRefund" namespace="/schoolfee"> 
								<s:param name="id" value="%{studentId}"></s:param>
								</s:url>
									<sj:a href="%{searchStudentsForRefund}"  cssClass="btn btn-xs green down"
										targets="studentFeeRefund" data-toggle="tab"> Refund</sj:a>
							</s:if>
							<s:elseif test='%{feeRefundStatus == "Y"}'>
								Refunded
							</s:elseif>
							<s:else>
							 	- 
							</s:else>
						</td>
					</s:if>
					<td>
						<s:if test='%{(feePaidStatus != "N") && (feePaidStatus != "C") && feeRefundStatus != "Y" && present}'>
							<a data-toggle="modal"  href="#popupDownloadReceiptDiv" class="btn btn-xs blue" onclick="javascript:PopupStudentDownloadReceipt(<s:property value="studentId" />,<s:property value="academicYearId" />);"> Download </a>
						</s:if>
						<s:else>
						 	- 
						</s:else>
					</td>
					
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no discontinued students.
	</div>
</s:else>
<div id="popupDownloadReceiptDiv"></div>
<div id="showInActiveStudenstProfileDiv"></div>
<div id="studentFeeRefund" class="up"></div>
<form method="post" id="printRefundReport" action="javaScript:printFeeRefundPreview('<s:property value="studentNumber" />','<s:property value="todayDate"/>','<s:property value="plTitle"/>')" style="display: none;">		</form>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	UIExtendedModals.init();
		changePageTitle("Discontinue Students");
		 $("#searchStudentsList").hide();
		 
		 $(".down").click(function() {
		     $('html, body').animate({
		         scrollTop: $(".up").offset().top
		     }, 1000);
		 });
	});
function showInActiveStudentProfileDetails(studentId,classId){
	if(isNonEmpty(studentId) && isNonEmpty(classId)){
	  var pars = "tempId=" + studentId+"&tempId2="+classId;
        $.ajax( {
	        url : jQuery.url.getChatURL("/student/ajaxShowStudentProfileDetails.do"),
			cache : true,
			data : pars,
			success : function(html) {
			$("#showInActiveStudenstProfileDiv").html(html);
			// $('#'+dataDiv).html(data);
			}
		});
	}
 	}
function PopupStudentDownloadReceipt(studentDetailsId,academicYearId) {
	var url = jQuery.url.getChatURL("/schoolfee/ajaxStudentDownloadReceipt.do");
	var pars = "anyId=" + studentDetailsId+"&tempId2="+academicYearId;
	$('#popupDownloadReceiptDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#popupDownloadReceiptDiv").html(html);
			}
		});
	}
function printFeeRefundPreview(studentId,ctrDateStr,plTitle){
    var url = jQuery.url.getChatURL("/reports/ajaxStudentFeeRefundPdfReport.do");
    var paidAmountInwords = feeAmountInWords(parseFloat(plTitle));
    var pars = 'spId=' + studentId+'&createdDate='+ctrDateStr+'&plTitle='+paidAmountInwords;
    $.ajax({
        url : url,
        cache : false,
        data:pars,
        success : function(data) {
           prntPrvw(data,url,pars);
        }
     });
    }
	//below script used to value to words
	 function feeAmountInWords(value) {
	    var fraction = Math.round(frac(value) * 100);
	    var f_text = "";

	    if (fraction > 0) {
	        f_text = "And " + convert_number(fraction) + " Paise";
	    }

	    //return convert_number(value) + " Rupees " + f_text + " Only";
	    return convert_number(value);
	}
</script>