<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<s:if test="%{absentStudOrStaffList != null && !absentStudOrStaffList.isEmpty()}">
	<div class="col-md-12">
		<div class="col-md-3" style="width: 14%;">&nbsp;</div>
		<div class="col-md-9" style="overflow-y:scroll;height: 350px;border: 1px solid ActiveCaption;margin-left: 18px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
					<s:if test="%{tempBoolean}">
							<th>
								Student Name 
							</th>
							<th>
								Class & Section
							</th>
					</s:if>
					<s:else>
							<th>
								Staff Name 
							</th>
							<th>
								Role
							</th>
					</s:else>
						 	<th>
								Mobile Number
							</th>
							<th>
								<div class="checkbox">
								<label>
									 <input type="checkbox" name="selectAll" value='' class="selectAllStudents"
										 id="selectAllIds" onclick="checkAllStudents();" />  
								</label>
								Select All
								</div>
							</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="absentStudOrStaffList" status="stat">
						<tr>
							<td>
								<s:property value="absentStudOrStaffList[#stat.count-1][0]" />
							</td>
							<s:if test="%{tempBoolean}">
								<td>
									<s:property value="absentStudOrStaffList[#stat.count-1][1]" />
								</td>
							</s:if>
							<s:else>
								<td>
									<s:property value="absentStudOrStaffList[#stat.count-1][1]" />
								</td>
							</s:else>
							<td>
								<s:if test="%{absentStudOrStaffList[#stat.count-1][2] != null && !absentStudOrStaffList[#stat.count-1][2].isEmpty()}">
									<s:property value="absentStudOrStaffList[#stat.count-1][2]" />
								</s:if>	
								<s:else>
									<strong> - </strong>
								</s:else>	
							</td>
							<td>
								<div class="checkbox">
									<s:if test="%{absentStudOrStaffList[#stat.count-1][3] != null}">
										<label>
											 <input type="checkbox" name="chkBoxSelectedAccountIds"
												value='<s:property value="absentStudOrStaffList[#stat.count-1][3]" />' id="checkbox">		
										</label>
									</s:if>
									<s:else>
										<label>
											 <input type="checkbox" name="chkBoxSelectedAccountIds"
												value="0" id="checkbox">		
										</label>
									</s:else>
								</div>
							</td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
		</div>
	</div>
	<div class="form-group"></div>
	<div id="smsContentDivId">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>SMS Text :
			</label>
			<div class="col-md-6">
				<sj:textarea name="messages.messageDescription"
					id="messageDescriptionDivId" rows="3"  maxCharsData="1000"
					cssClass="smsWord_count form-control required" cols="20" ></sj:textarea>
					<span class="smsCounter"></span>
			</div>
		</div>
	</div>
	<div class="form-actions fluid">
		<div class="col-md-offset-2 col-md-5">
				<s:if
					test='%{customer.checkMobileService == true || user.isSchoolAdmin == "Y"}'>
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						targets="schoolWideMessagesHomeDiv" indicator="indicator"
						formIds="formForAbsenteesMessages" validate="true"
						onBeforeTopics="formValidateForSchoolWideMessages" />
				</s:if>
				<s:if test='%{user.isSchoolTransport=="N" && user.isOnlySchoolTeacher =="Y"}'>
					<s:url id="urlInboxesDetails"
						action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
						includeParams="all" escapeAmp="false">
						<s:param value="#session.academicYear" name="academicYearId" />
					</s:url>
					<sj:a href="%{urlInboxesDetails}" targets="schoolWideMessagesHomeDiv"
						cssClass="btn default">Cancel</sj:a>
				</s:if>
				<s:else>
					<s:url id="urlInboxesDetails"
						action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
						includeParams="all" escapeAmp="false">
						<s:param value="#session.academicYear" name="academicYearId" />
					</s:url>
					<sj:a href="%{urlInboxesDetails}" targets="schoolWideMessagesHomeDiv"
						cssClass="btn default">Cancel</sj:a>
				</s:else>
		</div>
	</div>
</s:if>
<s:elseif test="%{tempBoolean}">
	<div class="alert alert-info">
		Oops! No Students are absent today.
	</div>
</s:elseif>
<s:else>
	<div class="alert alert-info">
		Oops! No staff is absent today.
	</div>
</s:else>
<script type="text/javascript">
changePageTitle("Send Absentees Message");
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	$(".selectAllStudents").parent('span').addClass("checked");
	$(".selectAllStudents").attr("checked", true);
	checkAllStudents();
	/* var type= '<s:property value="anyTitle"/>';
	if(type == 'P'){
		$(".selectAllStudents").parent('span').addClass("checked");
		$(".selectAllStudents").attr("checked", true);
		checkAllStudents();
	//	alert($('#selectAllIds').val());
	} */
});
function changeText(val){
	if(isNonEmpty(val)){
		$('.submitBt').removeAttr('disabled');
	}
} 
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