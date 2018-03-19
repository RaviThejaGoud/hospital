<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{studyAndBonafiedSettings != null}">
	<s:form id="studentGenerateTcInfoForm" method="post" theme="simple"
		cssClass="form-horizontal" action="ajaxGenerateStudyAndBonafied"
		onsubmit="return prepareNonSelectedStudIds();" namespace="/admin">
		<s:hidden name="classId" value="%{studyClassId}"></s:hidden>
		<s:hidden name="tempString" value="%{tempString}" />
		<s:hidden id="studNumbers" name="studentNumber" />
		<s:hidden name="description" value="%{plTitle}"></s:hidden>
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<div align="right">
				<s:submit value="Generate Certificate" cssClass="btn green"
					cssStyle="float:none;" />
			</div>
			<p>
				<span class="label label-danger">NOTE :</span> Please check the
				students from the list for whom you want to generate certificate.
			</p>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">

				<thead>
					<tr>
						<th>Class Name</th>
						<th>Admission No</th>
						<th>Student Name</th>
						<th>Due Fee</th>
						<th>
						<div class="checker">
								<span><input type="checkbox" name="" value=""
									onClick="checkAllStudents()" class="checkbox allInvoices">
								</span>
							</div>
						Select All Students
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="studentsList" status="stat">
						<s:set name="studentId"
							value="%{studentsList[#stat.count-1][6]+''}" />
						<span class="feeNotPadiStudsDate"> <span
							id='<s:property value='#studentId'/>' class='studentId'></span> <span
							id='<s:property value='studentsList[#stat.count-1][0]'/>'
							class='selectedStudentClassName'></span>
							<tr>
								<td><s:if
										test='%{studentsList[#stat.count-1][0] != "" && studentsList[#stat.count-1][0] != null}'>
										<s:property value="studentsList[#stat.count-1][0]" />
									</s:if> <s:if
										test='%{studentsList[#stat.count-1][1] != "" && studentsList[#stat.count-1][1] != null}'>
								 - <s:property value="studentsList[#stat.count-1][1]" />
									</s:if></td>
								<td><s:property value="studentsList[#stat.count-1][2]" />
									&nbsp;</td>
								<td><s:property value="studentsList[#stat.count-1][3]" />
									&nbsp; <s:property value="studentsList[#stat.count-1][4]" /></td>
								<td><s:if test='%{studentsList[#stat.count-1][8] > 0}'>
										<s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
									Paid
								</s:if>
										<s:else>
											<!--<s:url id="viewFeeDetails" action="ajaxViewFeeDetails"
									includeParams="all" escapeAmp="false">
									<s:param name="anyId" value="%{#studentId}" />
									<s:param name="classId" value="%{studentsList[#stat.count-1][7]}" />
								</s:url>
								<sj:a href="%{viewFeeDetails}" onCompleteTopics="toggleFeeDiv"
									onBeforeTopics="cleanOpenDivs" indicator="indicator"
									targets="toggleFee%{#studentId}">Pending Fee Details</sj:a>									
								-->
											<a data-toggle="modal" href="#responsive"
												onclick="javascript:PopupPaymentDetials(<s:property value="%{studentId}" />,<s:property value="%{studentsList[#stat.count-1][7]}" />);">Pending
												Fee Details </a>

										</s:else>
									</s:if> <s:else>
								Fee is not configured
								</s:else></td>
								<td><s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
										<div class="checker">
											<span> <input type="checkbox"
												id="payMent_<s:property value='#studentId'/>"
												name="chkBoxSelectedIds"
												value="<s:property value='studentId'/>" checked="checked" />
											</span>
										</div>
									</s:if> <s:else>
										<div class="checker">
											<span> <input type="checkbox"
												id="payMent_<s:property value='#studentId'/>"
												name="chkBoxSelectedIds"
												value="<s:property value='studentId'/>" />
											</span>
										</div>
									</s:else></td>
							</tr>
							<div id="toggleFee<s:property value='#studentId' />"
								style="display: none;" class='load'></div>
						</span>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">No students found for this class.
			</div>
		</s:else>
	</s:form>
</s:if>
<div id="responsive"></div>
<script type="text/javascript">
$(document).ready(function() {
	$("input[name='chkBoxSelectedIds']").click(function() {
		if ($(this).is(":checked")) {
			$(this).parent('span').addClass("checked");
		    $(this).attr("checked", true);
		} else {
			   $(this).parent('span').removeClass("checked");
				$(this).attr("checked", false);
		}
		var i= $("input[name='chkBoxSelectedIds']").length;//0;
		var checked =$("input[name='chkBoxSelectedIds']:checked").length;//0;
		if(i==checked && i>0){
			$(".allInvoices").parent('span').addClass("checked");
			$(".allInvoices").attr("checked", true);
		}else{
			$(".allInvoices").parent('span').removeClass("checked");
			$(".allInvoices").attr("checked", false);
		}
	});
});
	function prepareNonSelectedStudIds(){
		var studentId = 0;
		var nonSelectedStudIds = '(';
		$('span.feeNotPadiStudsDate').each(
				function() {
					studentId = $(this).find("span.studentId")
							.attr('id');
					if(isNonEmpty(studentId) && studentId >0){
						if($('#payMent_'+studentId).attr("checked")){
							nonSelectedStudIds += (studentId+",");
						}
					}
		});
		nonSelectedStudIds += "0)";
		$('#studNumbers').val(nonSelectedStudIds);
		if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one student");
		return false;
		}
	}
	
	function checkAllStudents() {
		if ($(".allInvoices").is(':checked')){
			$(".allInvoices").parent('span').addClass("checked");
			$(".allInvoices").attr("checked", true);
		    $("input[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
			$(".allInvoices").parent('span').removeClass("checked");
			$(".allInvoices").attr("checked", false);
		 $("input[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
function PopupPaymentDetials(studentId, classId) {
		var pars = "student.id=" + studentId + "&anyId=(1,2,3,4)";
		var url = jQuery.url.getChatURL("/schoolfee/ajaxViewFeeDetails.do");
		$('#responsive').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#responsive").html(html);
			}
		});
	}
 </script>