<%@ include file="/common/taglibs.jsp"%>
<div>
	<s:form id="studentGenerateTcInfoForm" method="post" theme="simple"
		action="ajaxGenerateHallTicket" cssClass="form-horizontal"
		onsubmit="javascript:return prepareSelectedStudentIds();" namespace="/admin">
		<s:hidden name="studentNumber" id="studNumbers"></s:hidden>
		<s:hidden name="tempString" id="classNames"></s:hidden>
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
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
							Fee Status
						</th>
						<th>
							Generate HallTicket
							<input type="checkbox" name="" value=""
								onClick="checkAllStudents()" class="checkbox allInvoices">
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="studentsList" status="stat">
						<s:set name="studentId"
							value="%{studentsList[#stat.count-1][6]+''}" />
						<span class="studsDate"> <span
							id='<s:property value='#studentId'/>' class='selectedStudentId'></span>
							<span id='<s:property value='studentsList[#stat.count-1][0]'/>'
							class='selectedStudentClassName'></span>
						<tr>
							<td>
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
										<s:url id="viewFeeDetails" action="ajaxViewFeeDetails"
											includeParams="all" escapeAmp="false" namespace="/schoolfee">
											<s:param name="student.id" value="%{#studentId}" />
											<s:param name="anyId" value="(1,2,3,4)" />
										</s:url>
										<sj:a href="%{viewFeeDetails}" onCompleteTopics="toggleFeeDiv"
											onBeforeTopics="cleanOpenDivs" indicator="indicator"
											targets="toggleFee%{#studentId}">Pending Fee Details</sj:a>
									</s:else>
								</s:if>
								<s:else>
								Fee is not configured
								</s:else>
							</td>
							<td>
								<input type="checkbox"
									id="payMent_<s:property value='#studentId'/>"
									name="chkBoxSelectedIds"
									value="<s:property value='studentId'/>" />
							</td>
						</tr>
						</span>
					</s:iterator>
				</tbody>
			</table>
			<s:submit value="Generate TC" cssClass="submit long"
				cssStyle="float:none;" />
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No students found.
			</div>
		</s:else>

	</s:form>
</div>
<script type="text/javascript">
TableAdvanced.init();

	function prepareSelectedStudentIds(){
		var studentId = 0;
		var className = '';
		var selectedStudIds = '(';
		var selectedClassNames = [];
		$('span.studsDate').each(
				function() {
					studentId = $(this).find("span.selectedStudentId").attr('id');
					className = $(this).find("span.selectedStudentClassName").attr('id');
					if(isNonEmpty(studentId) && studentId >0){
						if($('#payMent_'+studentId).attr("checked")){
							selectedStudIds += (studentId+",");
							if(isNonEmpty(className)){
								selectedClassNames.push("'"+className+"'");								
							}
						}
					}
		});
		selectedStudIds += "0)";
		$('#studNumbers').val(selectedStudIds);
		$('#classNames').val(selectedClassNames);
		if('(0)' == selectedStudIds){
			alert('Please select student.');
			return false;
		}else{
			return true;
		}
	}

$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		    $(".allInvoices").parent('span').removeClass("checked");
			$(".allInvoices").attr("checked", false);
		} else {
			$(".allInvoices").attr("checked", true);
			$(".allInvoices").parent('span').addClass("checked");
		}
});
function checkAllClassesForReports() {
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
 </script>