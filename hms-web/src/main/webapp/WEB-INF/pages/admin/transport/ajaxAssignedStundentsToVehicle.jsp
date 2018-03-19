<%@ include file="/common/taglibs.jsp"%>
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
 <s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="col-md-12">
		<div class="col-md-3" style="width: 14%;">&nbsp;</div>
		<div class="col-md-9">
			<table class="table table-striped table-bordered" style="width: 60%;">
				<thead>
					<tr>
						<th style="width:147px;">
							Class Name 
						</th>					
						<th>
							Student Name
						</th>
						<th>
							<div class="checkbox">
								Select All
								<label>
									 <input type="checkbox" name="selectAll" value='' class="selectAllStudents"
												id="selectAllIds" onclick="checkAllStudents();" />  
								</label>
							</div>
						</th>
					</tr>
				</thead>
				</table>
				<div class="table-scrollable" style="height: 400px;overflow-y:scroll;margin: 0px;width: 60%;">
				<table class="table table-striped table-bordered table-hover table-full-width">
				<tbody>
					<s:iterator value="objectList">
						<tr>
							<td style="width:147px;">
								<s:property value="classAndSection" />
							</td>
							<td>
								<s:property value="fullName" />
							</td>
							<td>
								<div class="checkbox">
									<label>
										 <input type="checkbox"
													name="chkBoxSelectedAccountIds"
													value='<s:property value="accountId" />' id="checkbox">
									</label>
								</div>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	</div>
	<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Message Subject :
			</label>
			<div class="col-md-5">
				<sj:textarea name="messages.messageDescription" maxCharsData="1000" 
					id="messageDescription1" rows="3" cssClass="smsWord_count form-control required"
					cols="20"></sj:textarea>
					<span class="smsCounter"></span>
			</div>
		</div>
		<s:if test="%{(smsAlloted != 0) && (smsAlloted > smsCnt)}">
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit   cssClass="submitBt btn blue" value="Submit" targets="schoolWideMessagesHomeDiv" onBeforeTopics="transportWideStudentsMsgs"
						formIds="addTransportWideMessage" validate="true" />
					<s:if test='%{tempString == "stepSWMessage"}'>
						<s:url id="doCancelTransportWDMessage" action="ajaxCancelSchoolWideMessages" namespace="/common"
							includeParams="all" escapeAmp="flase">
						</s:url>
						<sj:a href="%{doCancelTransportWDMessage}" cssClass="btn default" targets="schoolWideMessagesHomeDiv">Cancel</sj:a>
					</s:if>
					<s:else>
						<s:url id="doCancelTransportWDMessage" action="ajaxCancelSchoolWideMessages" namespace="/common"
							includeParams="all" escapeAmp="flase">
							<s:param name="status">TR</s:param>
						</s:url>
						<sj:a href="%{doCancelTransportWDMessage}" cssClass="btn default"  targets="schoolWideMessagesHomeDiv">Cancel</sj:a>
					</s:else>
				</div>
			</div>
		</s:if>
	</s:if>
	<s:else>
		<div class="col-md-12">
			<div class="alert alert-info">
				Oops! No students found for the selected Point. Please contact admin if already assigned.
			</div>
		</div>
	</s:else>
<script type="text/javascript">
changePageTitle('Manage Vehicles');
$(document).ready(function() {
	$.destroyTopic('transportWideStudentsMsgs');
 $("input:checkbox, input:radio").uniform();
 $.subscribe('transportWideStudentsMsgs', function(event, data) {
	 $('.submitBt').attr('disabled', 'disabled');
	 if ($('#addTransportWideMessage').valid()) {
	 if($('input#transportWideId').is(':checked')){
		 if(($("input[name='chkBoxSelectedAccountIds']:checked").length == 0) && ($('input#transportWideId').is(':checked'))){
		 		alert("Please Select at least one student.");
		 		$('.submitBt').removeAttr('disabled');
		 		event.originalEvent.options.submit=false;
			 }else{
				 $('li#CreateSMSEmail').removeClass('active');
		 return true;
		 }
		} 
	 } else
		 $('.submitBt').removeAttr('disabled');
			return false;
	});
});

$("input[name=chkBoxSelectedAccountIds]").click(function() {
	if ($("input[name=chkBoxSelectedAccountIds]:unchecked").length > 0) {
	   $(".selectAllStudents").parent('span').removeClass("checked");
		$(".selectAllStudents").attr("checked", false);
	} else {
	    $(".selectAllStudents").parent('span').addClass("checked");
		$(".selectAllStudents").attr("checked", true);
	}
});
	
function checkAllStudents() {
	if ($(".selectAllStudents").is(':checked')){
	    $("[name='chkBoxSelectedAccountIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='chkBoxSelectedAccountIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}
</script>
