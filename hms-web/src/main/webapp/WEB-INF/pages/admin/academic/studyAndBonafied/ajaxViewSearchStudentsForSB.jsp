<%@ include file="/common/taglibs.jsp"%>
<s:form id="studentGenerateSBInfoForm" method="post" theme="simple"
	action="ajaxGenerateStudyAndBonafied"
	onsubmit="return prepareSelectedStudentIds();"
	cssClass="form-horizontal" namespace="/admin">
	<s:hidden name="studentNumber" id="studNumbers"></s:hidden>
	<s:hidden name="tempString" id="classNames"></s:hidden>
	<s:hidden name="description" value="%{description}"></s:hidden>
	<s:hidden name="classId" id="classId"></s:hidden>
	<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
		<div align="right">
			<s:submit value="Generate Certificate" cssClass="btn blue"
				cssStyle="float:none;" />
		</div>
		<div class="spaceDiv"></div>
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
						Generate Study
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
					<s:set name="studentId" value="%{studentsList[#stat.count-1][6]+''}" />
					 <tr class="studsDate">
						<td id='<s:property value='#studentId'/>' class='selectedStudentId'>
							<s:if
								test='%{studentsList[#stat.count-1][0] != "" && studentsList[#stat.count-1][0] != null}'>
								<s:property value="studentsList[#stat.count-1][0]" />
							</s:if>
							<s:if
								test='%{studentsList[#stat.count-1][1] != "" && studentsList[#stat.count-1][1] != null}'>
								 - <s:property value="studentsList[#stat.count-1][1]" />
							</s:if>
						</td>
						<td id='<s:property value='studentsList[#stat.count-1][0]'/>' class='selectedStudentClassName'>
							<s:property value="studentsList[#stat.count-1][2]" />
						</td>
						<td id='<s:property value='studentsList[#stat.count-1][10]'/>' class='studyClassId'>
							<s:property value="studentsList[#stat.count-1][3]" />
							<s:property value="studentsList[#stat.count-1][4]" />
						</td>
						<td>
							<s:if test='%{studentsList[#stat.count-1][8] > 0}'>
								<s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
									Paid
								</s:if>
								<s:else>
								<a data-toggle="modal" href="#responsive"
									onclick="javascript:PopupViewFeeDetials(<s:property value="%{#studentId}" />,<s:property value="%{studentsList[#stat.count-1][7]}" />);">
									Pending Fee Details </a>
								</s:else>
							</s:if>
							<s:else>
								Fee is not configured
							</s:else>
						</td>
						<td>
							<input type="checkbox"
								id="payMent_<s:property value='#studentId'/>"
								name="chkBoxSelectedIds" value="<s:property value='studentId'/>" />
						</td>
					</tr>
					<!--<div id="toggleFee<s:property value='#studentId' />"
						style="display: none;" class='load'>
					</div>
				--></s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			No students found.
		</div>
	</s:else>
</s:form>
<div id="responsive"></div>
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
		function prepareSelectedStudentIds(){
			var studentId = 0;
			var studyClassId = 0;
			var className = '';
			var selectedStudIds = '(';
			var selectedClassNames = [];
			var selectedStudyClassIds = '(';
			$('tr.studsDate').each(
					function() {
						studentId = $(this).find("td.selectedStudentId").attr('id');
						className = $(this).find("td.selectedStudentClassName").attr('id');
						studyClassIds = $(this).find("td.studyClassId").attr('id');
						if(isNonEmpty(studentId) && studentId >0){
							if($('#payMent_'+studentId).attr("checked")){
								selectedStudIds += (studentId+",");
								if(isNonEmpty(className)){
									selectedClassNames.push("'"+className+"'");								
								}
								if(isNonEmpty(studyClassIds)){
									selectedStudyClassIds += (studyClassIds+",");
								}
							}
						}
			});
			selectedStudIds += "0)";
			selectedStudyClassIds += "0)";
			//alert(selectedStudyClassIds);
			$('#studNumbers').val(selectedStudIds);
			//$('#classNames').val(selectedClassNames);
			$('#classId').val(selectedStudyClassIds);
			if('(0)' == selectedStudIds || '(0)' == selectedStudyClassIds){
				alert('Please select student.');
				return false;
			}else{
				return true;
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
	$("input[name=chkBoxSelectedIds]").click(function() {
			if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				 $(".allInvoices").parent('span').removeClass("checked");
						$(".allInvoices").attr("checked", false);
			} else {
				 $(".allInvoices").parent('span').addClass("checked");
				$(".allInvoices").attr("checked", true);
			}
	});
	
	function PopupViewFeeDetials(id,classId) {
		var pars = "student.id=" + id + "&anyId=(1,2,3,4)";
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