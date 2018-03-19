<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
	<s:if test="%{tcSettings != null}">
		<s:form id="studentGenerateTcInfoForm" method="post" theme="simple"
			action="ajaxGenerateTc" cssClass="form-horizontal" namespace="/admin"
			onsubmit="return prepareNonSelectedStudIds();">
			<s:hidden name="classId" value="%{classId}"></s:hidden>
			<s:hidden name="tempString" value="%{tempString}" />
			<s:hidden id="studNumbers" name="studentNumber" />
			<s:hidden id="wishTitle" name="wishTitle"/>
			<s:hidden name="empId" cssClass="studentIds"></s:hidden>
			<s:hidden id="templateSettingType" name="selectedId" />
			<s:hidden id="templateBookSettingType" name="anyTitle" />
			<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<div align="right">
				<s:submit value="Generate TC" cssClass="btn green" onclick="javascript:tcSettings('templateSettings','bookSettings');"
					cssStyle="float:none;" />
			</div>
			<span class="label label-danger"> NOTE : </span>&nbsp;
			 Please check the students from the list for whom you want to generate tc.
				<div class="spaceDiv"></div>
				<table class="table table-bordered table-striped table-condensed flip-content">
					<thead class="flip-content">
					<tr>
						<th>
							Class Name
						</th>
						<th>
							Admission No
						</th>
						<th>
							Student Name
						</th>
						<th>
							Due Fee
						</th>
						<th>
							Force to Generate TC
							<div class="checkbox-list">
								<label class="checkbox-inline">
									<input type="checkbox" name="Force to Generate TC"
										onClick="checkAllStudents()" class="checkbox allInvoices">
								</label>
							</div>
							<!--<input type="checkbox" name="" value=""  theme="ems"
									onClick="checkAllStudents()" class="checkbox allInvoices">
							-->
						</th>
						<s:if test='%{#session.previousYear=="N"}'>
							<th>
								Inactive
								<input type="checkbox" id="selectall" class="checkbox inactiveStudent">
							</th>
						</s:if>
					</tr>
				</thead>
					<s:iterator value="studentsList" status="stat">
						<tr class="feeNotPadiStudsDate">
							<!--<span class="feeNotPadiStudsDate"> <span
								id='<s:property value='studentsList[#stat.count-1][6]'/>'
								class='studentId'></span>
								--><td class="studentId" id="<s:property value='studentsList[#stat.count-1][6]'/>">
									<s:if
										test='%{studentsList[#stat.count-1][0] != "" && studentsList[#stat.count-1][0] != null}'>
										<s:property value="studentsList[#stat.count-1][0]" />
									</s:if>
									<s:if
										test='%{studentsList[#stat.count-1][1] != "" && studentsList[#stat.count-1][1] != null}'>
								 - <s:property value="studentsList[#stat.count-1][1]" />
									</s:if>
								</td>
								<td>
									<s:property value="studentsList[#stat.count-1][2]" />
									&nbsp;
								</td>
								<td>
									<s:property value="studentsList[#stat.count-1][3]" />
									&nbsp;
									<s:property value="studentsList[#stat.count-1][4]" />
								</td>

								<td>
									<s:if test='%{studentsList[#stat.count-1][8] > 0}'>
										<s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
											Paid
										</s:if>
									<s:else>
										<!--<s:url id="viewFeeDetails" action="ajaxViewFeeDetails"
												includeParams="all" escapeAmp="false">
												<s:param name="anyId"
													value="%{studentsList[#stat.count-1][6]}" />
												<s:param name="classId"
													value="%{studentsList[#stat.count-1][7]}" />
											</s:url>
											<sj:a href="%{viewFeeDetails}"
												onCompleteTopics="toggleFeeDiv"
												onBeforeTopics="cleanOpenDivs" indicator="indicator"
												targets="toggleFee%{studentsList[#stat.count-1][6]}">Pending Fee Details</sj:a>
										-->
										<a data-toggle="modal" href="#responsive"
											onclick="javascript:PopupTcDetials(<s:property value="%{studentsList[#stat.count-1][6]}" />,<s:property value="%{studentsList[#stat.count-1][7]}" />);">Pending
											Fee Details </a>

									</s:else>
									</s:if>
									<s:else>
									Fee is not configured
									</s:else>
								</td>
								<td>
									<s:if test='%{studentsList[#stat.count-1][8] > 0}'>
										<s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
											<input type="checkbox"
												id="payMent_<s:property value='studentsList[#stat.count-1][6]'/>"
												name="chkBoxSelectedIds"
												value="<s:property value='studentsList[#stat.count-1][6]'/>"
												checked="checked" />
										</s:if>
										<s:else>
											<input type="checkbox"
												id="payMent_<s:property value='studentsList[#stat.count-1][6]'/>"
												name="chkBoxSelectedIds"
												value="<s:property value='studentsList[#stat.count-1][6]'/>" />
										</s:else>
									</s:if>
									<s:else>
										<input type="checkbox"
											id="payMent_<s:property value='studentsList[#stat.count-1][6]'/>"
											name="chkBoxSelectedIds"
											value="<s:property value='studentsList[#stat.count-1][6]'/>" />
									</s:else>
								</td>
								<td>
								<s:if test='%{#session.previousYear=="N"}'>
								<s:if test='%{studentsList[#stat.count-1][9] !=null}'>
									<s:if test='%{studentsList[#stat.count-1][8] > 0}'>
										<s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
											<input type="checkbox" id="disaStud_<s:property value='studentsList[#stat.count-1][6]'/>" name="chkBoxInActiveIds" value="<s:property value='studentsList[#stat.count-1][6]'/>" class="checkbox " disabled="disabled" />
										</s:if>
										<s:else>
											<input type="checkbox" id="disaStud_<s:property value='studentsList[#stat.count-1][6]'/>" name="chkBoxInActiveIds" value="<s:property value='studentsList[#stat.count-1][6]'/>"  class="checkbox " disabled="disabled"/>
										</s:else>
									</s:if>
									<s:else>
											<input type="checkbox" id="disaStud_<s:property value='studentsList[#stat.count-1][6]'/>" name="chkBoxInActiveIds" value="<s:property value='studentsList[#stat.count-1][6]'/>" class="checkbox " disabled="disabled"/>
									</s:else>
								</s:if>
								<s:else>
									<s:if test='%{studentsList[#stat.count-1][8] > 0}'>
										<s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
											<input type="checkbox" id="disaStud_<s:property value='studentsList[#stat.count-1][6]'/>" name="chkBoxInActiveIds" value="<s:property value='studentsList[#stat.count-1][6]'/>" class="checkbox anyCheckedId" />
										</s:if>
										<s:else>
											<input type="checkbox" id="disaStud_<s:property value='studentsList[#stat.count-1][6]'/>" name="chkBoxInActiveIds" value="<s:property value='studentsList[#stat.count-1][6]'/>"  class="checkbox anyCheckedId"/>
										</s:else>
									</s:if>
									<s:else>
											<input type="checkbox" id="disaStud_<s:property value='studentsList[#stat.count-1][6]'/>" name="chkBoxInActiveIds" value="<s:property value='studentsList[#stat.count-1][6]'/>" class="checkbox anyCheckedId" />
									</s:else>
								</s:else>
						 </s:if>
								</td>
								
								<!--<div
									id="toggleFee<s:property value='studentsList[#stat.count-1][6]' />"
									style="display: none;" class='load'>
								</div> --><!--</span>
						--></tr>

					</s:iterator>
				</table>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					 Oops! system couldn't find any match with keyword. Try by correcting the word.</div>
				</s:else>
		</s:form>
	</s:if>
<div id="responsive"></div>
<script type="text/javascript">
$(document).ready(function() {
/*if($("input[.anyCheckedId]:checked").length > 0){
}*/
$("input:checkbox, input:radio").uniform();
$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			 $(".allInvoices").parent('span').removeClass("checked");
			$(".allInvoices").attr("checked", false);
		} else {
		 $(".allInvoices").parent('span').addClass("checked");
			$(".allInvoices").attr("checked", true);
		}
	});
	$("#selectall").click(function(){
		if ($(this).is(":checked")){
			if(confirm('Are you Sure you want to change the selected student(s) status from Active to Inactive?')){
				$(".anyCheckedId").parent('span').addClass("checked");
				$('.anyCheckedId').attr('checked',true);
				prepareInActiveSelectedStudIds();
				var wishTitle = $("#wishTitle").val();
				inactiveStudentTcGenerate(wishTitle);
			}else{
				 $(".inactiveStudent").parent('span').removeClass("checked");
				 $('.inactiveStudent').removeAttr('checked');
				return false;
			}
		}else{
			 $(".anyCheckedId").parent('span').removeClass("checked");
			$('.anyCheckedId').removeAttr('checked');
		}
		return true;
	});
	$(".anyCheckedId").click(function(){
		var result = true; 
		 if($(this).is(':checked')) {
		 	if(confirm('Are you Sure you want to change the selected student status from Active to Inactive?')){
		 		prepareInActiveSelectedStudIds();
				var wishTitle = $("#wishTitle").val();
				inactiveStudentTcGenerate(wishTitle);
				result =  true;
		 	}else{
		 		$(".anyCheckedId").parent('span').removeClass("checked");
			     $('.anyCheckedId').removeAttr('checked');
			     return false;
		 	}
		}
		 if($(".anyCheckedId").length == $(".anyCheckedId:checked").length){
		 	$("#selectall").parent('span').addClass("checked");
		 	$("#selectall").attr("checked", "checked");
		 }else{
		 	 $("#selectall").parent('span').removeClass("checked");
		 	$("#selectall").removeAttr("checked");
		 }
		return result;
	});
});
function tcSettings(value,string){
	$('#templateSettingType').val(value);
	$('#templateBookSettingType').val(string);
}

	function prepareNonSelectedStudIds(){
		var studentId = 0;
		var includeIds=[];
		var nonSelectedStudIds = '(';
		$('tr.feeNotPadiStudsDate').each(
				function() {
					studentId = $(this).find("td.studentId").attr('id');
					if(isNonEmpty(studentId) && studentId >0){
						if($('#payMent_'+studentId).attr("checked")){
							nonSelectedStudIds += (studentId+",");
							if($('#disaStud_'+studentId).is(':checked')){
								includeIds.push($('#disaStud_'+studentId).val());
							}
						}
					}
		});
		nonSelectedStudIds += "0)";
		$('.studentIds').val(includeIds);
		$('#studNumbers').val(nonSelectedStudIds);
		if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one student");
		return false;
		}
	}
	
	function prepareInActiveSelectedStudIds(){
		var studentId = 0;
		var includeIds=[];
		var nonSelectedStudIds = '(';
		$('tr.feeNotPadiStudsDate').each(
				function() {
					studentId = $(this).find("td.studentId").attr('id');
					if(isNonEmpty(studentId) && studentId >0){
						if($('#disaStud_'+studentId).attr("checked")){
							nonSelectedStudIds += (studentId+",");
						}
					}
		});
		nonSelectedStudIds += "0)";
		$('#wishTitle').val(nonSelectedStudIds);
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
}
function PopupTcDetials(studentId, classId) {
		var pars = "student.id=" + studentId + "&anyId=(1,2,3,4)";
		var url = jQuery.url.getChatURL("/schoolfee/ajaxViewFeeDetails.do");
		$('#responsive')
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#responsive").html(html);
			}
		});
	}
 function inactiveStudentTcGenerate(wishTitle) {
	var pars = "wishTitle=" + wishTitle;
	var url = jQuery.url.getChatURL("/admin/ajaxTcGenerateInActiveStudent.do");
	$('#viewTcContent')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
		 	$("#viewTcContent").html(html);
		}
	});
}
 </script>