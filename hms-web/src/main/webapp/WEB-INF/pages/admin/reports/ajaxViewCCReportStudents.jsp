<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
				<div id="generateCCStudentReport" align="right" style="margin-right: 381px;display: none;">
					<s:submit id="generateCCReport" value="Generate CCE Report" cssClass="btn green generateForSelected"
						cssStyle="float:none;" title="generate report for selected students"/>
				</div>
				<div id="sendEmailToParentDiv" align="right" style="margin-right: 178px;margin-top: -33px;display: none;">
				<s:form id="generateScoreCardsToEmail" method="post" theme="simple"
					action="ajaxGenerateScoreCardsEmailToParents" cssClass="form-horizontal"
					onsubmit="javascript:return validateScoreCardGenerationByClassForm();" namespace="/exam">
					<s:hidden name="sendEmail" value="true" id="sendEmailId"/> 
					<s:hidden name="classId" id="selectedClassId"></s:hidden>
					<s:hidden name="selectedId" id="selectedAdmissionNumbers"></s:hidden>
					<s:hidden name="tempId" id="selectedExamTypeId"></s:hidden>
					<s:if test='%{customer.checkEmailService == true}'>
						<sj:submit   targets="isCCReportExists"
							formIds="generateScoreCardsToEmail" value="Send Email To Parents" cssClass="btn green sendEmailToParentClass" onBeforeTopics="sendEmailToParentClassSub"
							validate="true" title="send Email to parents"/>
						</s:if>
						</s:form>
				</div>
			<p>
				<span class="label label-danger">NOTE :</span><b> Please check the
					students from the list for whom you want to generate CCE Report.</b>
			</p>
			<div class="spaceDiv"></div>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
					<th>
					Roll No
					</th>
					<th>
						Admission No
					</th>
					<th>
						Student Name
					</th>
					<th>
						Parent Name
					</th>
					<th>
						Mobile No
					</th>
						<th>
							Generate CCE Report
							<div class="checkbox-list">
								<label class="checkbox-inline">
									<input type="checkbox" name="" value=""
										onClick="checkAllStudents()" class="checkbox allInvoices">
								</label>
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="studentsList" status="stat">
						<tr>
						<td>
							<s:property value="rollNumber" />
						</td>
						<td>
							<s:property value="admissionNumber" />
						</td>
						<td>
							<s:property value="firstName" />
							&nbsp;
							<s:property value="lastName" />
						</td>
						<td>
							<s:property value="fatherName" />
						</td>
						<td>
							<s:property value="mobileNumber" />
						</td>
							<td>
								<s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
									<input type="checkbox" id="payMent_<s:property value='accountId'/>" name="chkBoxSelectedIds" value="<s:property value='accountId'/>" checked="checked"/>
								</s:if>
								<s:else>
									<input type="checkbox" id="payMent_<s:property value='accountId'/>" name="chkBoxSelectedIds" value="<s:property value='accountId'/>" />
								</s:else>
							</td>
						</tr>
						 
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No students found for this class.
			</div>
		</s:else>
<script type="text/javascript">
$(document).ready(function() {
$("input:checkbox, input:radio").uniform();
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			 $(".allInvoices").parent('span').removeClass("checked");
			$(".allInvoices").attr("checked", false);
		} else {
		 $(".allInvoices").parent('span').addClass("checked");
			$(".allInvoices").attr("checked", true);
		}
		submitDivShowHide();
		submitSendEmailDivShowHide();
	});
	
	$.subscribe('sendEmailToParentClassSub',function(event, data) {
		if ($('#generateScoreCardsToEmail').valid()) 
		{
			$("input#selectedExamTypeId").val($("select#examTypeId option:selected").val());
			 validateScoreCardGenerationByClassForm();
		} else
			event.originalEvent.options.submit=false;
		});
});

	function submitDivShowHide(){
	 if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			  $('div#generateCCStudentReport').show();
			}
			else{
			  $('div#generateCCStudentReport').hide();
	 }
  }
  function submitSendEmailDivShowHide(){
	 if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			  $('div#sendEmailToParentDiv').show();
			}
			else{
			  $('div#sendEmailToParentDiv').hide();
	 		}
  }
	function checkAllStudents() {
	if ($(".allInvoices").is(':checked')){
	    $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}
	submitDivShowHide();
	submitSendEmailDivShowHide();
  }
  
  function submitSendEmailCCReportToParent(){
	 $("#sendEmailId").val("true");
	 return true;
  }
  
  
 </script>