<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:form id="studentGenerateLcInfoForm" method="post" theme="simple"
	action="ajaxGenerateLc"
	onsubmit="return prepareNonSelectedStudIds();" namespace="/admin">
	<s:hidden name="classId" value="%{classId}"></s:hidden>
	<s:hidden name="tempString" id="tempString" />
	<s:hidden id="studNumbers" name="studentNumber" />
	<s:hidden name="empId" cssClass="studentIds" id="studentIds"></s:hidden>
	<s:hidden id="templateSettingType" name="selectedId" />
	<s:hidden id="templateBookSettingType" name="anyTitle" />
	<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
		<div align="right">
				<s:submit value="Generate LC" cssClass="btn green" onclick="javascript:tcSettings('LC','lcbookSettings');"
					cssStyle="float:none;" />
		</div>
		<div class="panel-body">
			<div class="col-md-1">
				<span class="label label-danger">NOTE :</span>
			</div>
			<div class="col-md-9">
				<ul>
					<li>
						Please check the students from the list for whom you want to
						generate lc.
					</li>
					<li>
						Before generating lc make sure lc template is uploaded for all
						classes or not. In LC settings
					</li>
				</ul>
			</div>
		</div>
		<table
			class="table table-bordered table-striped table-condensed flip-content">
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
						Force to Generate LC
						<div class="checkbox-list">
							<label class="checkbox-inline">
								<input type="checkbox" name="" value=""
									onClick="checkAllStudents()" class="checkbox allInvoices">
							</label>
						</div>
					</th>
				</tr>
			</thead>
				<s:iterator value="studentsList" status="stat">
					<tr class="feeNotPadiStudsDate">
						<td id='<s:property value='studentsList[#stat.count-1][0]'/>' class='classId'>
							<s:if
								test='%{studentsList[#stat.count-1][0] != "" && studentsList[#stat.count-1][0] != null}'>
								<s:property value="studentsList[#stat.count-1][0]" />
							</s:if>
							<s:if
								test='%{studentsList[#stat.count-1][1] != "" && studentsList[#stat.count-1][1] != null}'>
							 - <s:property value="studentsList[#stat.count-1][1]" />
							</s:if>
						</td>
						<td id='<s:property value='studentsList[#stat.count-1][6]'/>' class='studentId'>
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
								<a data-toggle="modal" href="#responsive"
										onclick="javascript:PopupTcDetials(<s:property value="%{studentsList[#stat.count-1][6]}"/>,<s:property value="%{studentsList[#stat.count-1][7]}" />);">
										Pending Fee Details </a>
								</s:else>
							</s:if>
							<s:else>
							Fee is not configured
							</s:else>
						</td>
						<td>
							<s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
								<input type="checkbox" id="payMent_<s:property value='studentsList[#stat.count-1][6]'/>" name="chkBoxSelectedIds" value="<s:property value='studentsList[#stat.count-1][6]'/>" checked="checked"/>
							</s:if>
							<s:else>
								<input type="checkbox" id="payMent_<s:property value='studentsList[#stat.count-1][6]'/>" name="chkBoxSelectedIds" value="<s:property value='studentsList[#stat.count-1][6]'/>" />
							</s:else>
						</td>
					</tr>
				</s:iterator>
		</table>
	</s:if>
	<s:else>
	<div class="alert alert-info">
		 Oops! system couldn't find any match with keyword. Try by correcting the word.</div>
	</s:else>
</s:form>
<div id="responsive"></div>
<script type="text/javascript">
	$(document).ready(function() {
	TableAdvanced.init();
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
	});
	function tcSettings(value,string){
	$('#templateSettingType').val(value);
	$('#templateBookSettingType').val(string);
}
	function prepareNonSelectedStudIds(){
		var studentId = 0;
		var classId=0;
		var includeIds=[];
		var nonSelectedStudIds = '(';
		var classIds = '';
		$('tr.feeNotPadiStudsDate').each(function() {
			studentId = $(this).find("td.studentId").attr('id');
			classId = $(this).find("td.classId").attr('id');
			if(isNonEmpty(studentId) && studentId >0 ){
				if($('#payMent_'+studentId).attr("checked")){
					nonSelectedStudIds += (studentId+",");
					classIds += ("'"+classId+"'"+",");
					if($('#disaStud_'+studentId).is(':checked')){
						includeIds.push($('#disaStud_'+studentId).val());
					}
				}
			}
		});
		nonSelectedStudIds += "0)";
		classIds += "0";
		$('#studentIds').val(includeIds);
		$('#studNumbers').val(nonSelectedStudIds);
		$('#tempString').val(classIds);
		if($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one student.");
			return false;
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
	}

	var includeIds=[];
		$("#selectall").click(function(){
			if ($(this).is(":checked")){
				if(confirm('Are you Sure you want to change the selected student(s) status from Active to Inactive?')){
					$(".anySearchCheckedId").parent('span').addClass("checked");
					$('.anySearchCheckedId').attr('checked',true);		
				}else{
				 $(".anySearchCheckedId").parent('span').removeClass("checked");
					$('.anySearchCheckedId').removeAttr('checked');
					return false;
				}
			}else{
				 $(".anySearchCheckedId").parent('span').removeClass("checked");
				$('.anySearchCheckedId').removeAttr('checked');
			}
			return true;
		});
		 
		$(".anySearchCheckedId").click(function(){
			var result = true; 
			 if($(this).is(':checked')) {
			 	if(confirm('Are you Sure you want to change the selected student status from Active to Inactive?'))
			 		result =  true;
			 	else
			 		result =  false;
			}
			 if($(".anySearchCheckedId").length == $(".anySearchCheckedId:checked").length){
			 	$("#selectall").parent('span').addClass("checked");
			 	$("#selectall").attr("checked", "checked");
			 }else{
			  $("#selectall").parent('span').removeClass("checked");
			 	$("#selectall").removeAttr("checked");
			 }
			return result;
		});
	
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
 </script>