<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{viewClassExamDetails != null && !viewClassExamDetails.isEmpty()}">
		<div class="form-group ">
			<label class="control-label col-md-2"> Select ExamType : </label>
			<div class="col-md-3">
				<s:select list="viewClassExamDetails" listKey="eid"
					listValue="examType" id="eid" name="tempId"
					cssClass="form-control"
					onchange="javascript:getAjaxDoStudentFeeDuesList(this.value);">
				</s:select><!--  -->
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-4"> Do you want to generate hall ticket for all sub types :
			</label>
			<div class="col-md-7">
				<div class="radio-list">
					<label class="radio-inline"> 
						<input type="radio" id="subTypeYes" value="Y" name="subTypeName" class="" checked="checked"
							onclick="javascript: checkSubTypeStatus(this.value)"> Yes
					</label> 
					<label class="radio-inline"> 
						<input type="radio" name="subTypeName" id="subTypeNo" value="N"  class=""
						onclick="javascript: checkSubTypeStatus(this.value)"> No
					</label>
				</div>
			</div>
		</div>
		<div id="subTypeDivId" style="display: none;">
			<div class="form-group " >
				<label class="control-label col-md-2"> Select Sub Type : </label>
				<div class="col-md-10">
					<s:checkboxlist name="chkBoxFeeSelectedIds" id="chkBoxFeeSelectedIds"
							list="subTypesList" listKey="id" listValue="subTypeName" cssClass="required" theme="ems"  onchange="javascript:getSubTypeDetails()"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-md-5"> Do you want to generate hall ticket for all sub type subjects :
				</label>
				<div class="col-md-6">
					<div class="radio-list">
						<label class="radio-inline"> 
						<input type="radio" id="subTypeSubjYes" value="Y" name="subTypeSubj" class="" checked="checked"
							onclick="javascript: checkSubTypeSubjStatus(this.value)"> Yes
						</label> <label class="radio-inline"> <input type="radio"
							name="subTypeSubj" id="subTypeSubjNo" value="N"  class=""
							onclick="javascript: checkSubTypeSubjStatus(this.value)"> No
						</label>
					</div>
				</div>
			</div>
		</div>
		
		<div  id="subTypeSubjDivId" style="display: none;">
			<div id="subTypeSubjList"> </div>
		</div>
	</s:if>
	<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>Please check the students from the list for whom you want to generate Hall Ticket.</li>
					<li>Hall Tickets will be generated only for the class sections who have exam schedules.</li>
				</ul>
			</div>
		</div>
		<div class="col-md-2" style="float: right;">
			<s:submit value="Generate HallTicket" cssClass="submit btn blue" />
		</div>
	<div class="spaceDiv">&nbsp;</div><div class="spaceDiv">&nbsp;</div>
	
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
			<thead>
				<tr>
					<th>Class Name</th>
					<th>Admission No</th>
					<th>Student Name</th>
					<th>Due Fee</th>
					<th>Force to Generate Hall Ticket <input type="checkbox"
						name="" value="" onClick="checkAllStudents()"
						class="checkbox allInvoices">
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="studentsList" status="stat">
					<!--<span class="feeNotPadiStudsDate"> <span
						id='<s:property value='studentsList[#stat.count-1][6]'/>'
						class='studentId'></span>
					-->
					<tr class="feeNotPadiStudsDate">
						<td class="studentId"
							id="<s:property value='studentsList[#stat.count-1][6]'/>"><s:if
								test='%{studentsList[#stat.count-1][0] != "" && studentsList[#stat.count-1][0] != null}'>
								<s:property value="studentsList[#stat.count-1][0]" />
							</s:if> <s:if
								test='%{studentsList[#stat.count-1][1] != "" && studentsList[#stat.count-1][1] != null}'>
								 - <s:property value="studentsList[#stat.count-1][1]" />
							</s:if></td>
						<td><s:property value="studentsList[#stat.count-1][2]" /></td>
						<td><s:property value="studentsList[#stat.count-1][3]" />
							&nbsp; <s:property value="studentsList[#stat.count-1][4]" /></td>
						<td><s:if test='%{studentsList[#stat.count-1][8] > 0}'>
								<s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
									Paid
								</s:if>
								<s:else>
									<a data-toggle="modal" href="#responsive"
										onclick="javascript:PopupDueFeeDetials(<s:property value="%{studentsList[#stat.count-1][6]}" />,<s:property value="%{studentsList[#stat.count-1][7]}" />);">Pending
										Fee Details </a>
									<!--<s:url id="viewFeeDetails" action="ajaxViewFeeDetails"
										includeParams="all" escapeAmp="false">
										<s:param name="anyId"
											value="%{studentsList[#stat.count-1][6]}" />
										<s:param name="classId"
											value="%{studentsList[#stat.count-1][7]}" />
									</s:url>
									<sj:a href="%{viewFeeDetails}" onCompleteTopics="toggleFeeDiv"
										onBeforeTopics="cleanOpenDivs" indicator="indicator"
										targets="toggleFee%{studentsList[#stat.count-1][6]}">Pending Fee Details</sj:a>
								-->
								</s:else>
							</s:if> <s:else>
								Fee is not configured
								</s:else></td>
						<td><s:if test='%{studentsList[#stat.count-1][5] == "P"}'>
								<input type="checkbox"
									id="payMent_<s:property value='studentsList[#stat.count-1][6]'/>"
									name="chkBoxSelectedIds"
									value="<s:property value='studentsList[#stat.count-1][6]'/>"
									checked="checked" />
							</s:if> <s:else>
								<input type="checkbox"
									id="payMent_<s:property value='studentsList[#stat.count-1][6]'/>"
									name="chkBoxSelectedIds"
									value="<s:property value='studentsList[#stat.count-1][6]'/>" />
							</s:else></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="spaceDiv">&nbsp;</div><div class="spaceDiv">&nbsp;</div>
		<div class="col-md-2" style="float: right;margin-top: -50px;">
			<s:submit value="Generate HallTicket" cssClass="submit btn blue" />
		</div>
	
	</s:if>
	<s:else>
		<div class="alert alert-info">You do not have 'Exam Schedules' for this class. Please create.</div>
	</s:else>
<script type="text/javascript">
	$(document).ready(function(){
		 $("input:checkbox, input:radio").uniform();
		 $("input[name='chkBoxFeeSelectedIds']").each(function(){
				$(this).attr("checked",true); 
				$(this).parent("span").addClass("checked");
			 });
		 getSubTypeDetails();
	}) 
	UIExtendedModals.init();
	function checkSubTypeStatus(status)
	{
		if(status == "Y"){
			$('#subTypeDivId').hide();
			$("input[name='chkBoxFeeSelectedIds']").each(function(){
				$(this).attr("checked",true); 
				$(this).parent("span").addClass("checked");
			 });
			$('#subTypeSubjDivId').hide();
			getSubTypeDetails();
		}else{
			$('#subTypeDivId').show();
			if($("input[name=subTypeSubj]:checked").val()=="N")
				$('#subTypeSubjDivId').show();
			
		}
	}
	function checkSubTypeSubjStatus(status){
		if(status =="Y"){
			$('#subTypeSubjDivId').hide();
			$("input[name='selectBoxIdList']").each(function(){
				$(this).attr("checked",true); 
				$(this).parent("span").addClass("checked");
			 });
		}
		else
			$('#subTypeSubjDivId').show();
	}
	function getSubTypeDetails(){
		var className = $('#classNameId :selected').text();
		var classId = $("#classId").val();
		var eid = $("#eid").val();
		var studyClassIds=$("input[name=chkBoxClassSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
		var subTypeIds=$("input[name=chkBoxFeeSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
		if(studyClassIds.length>0){
			$('#studyClassIds').val("("+studyClassIds+",0)");
			studyClassIds = "("+studyClassIds+",0)";
			subTypeIds = "("+subTypeIds+",0)";
			$('#subTypeSubjList').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/exam/ajaxGetSubtypeSubjectDetails.do");
			var pars = "classId=" + classId + "&tempString=" + className+ "&plTitle=HT&tempId="+eid+"&tempString2="+studyClassIds+"&tempString1="+subTypeIds;
			$.ajax( {
				type : "POST",
				url : url,
				data : pars,
				cache : false,
				success : function(html) {
					$("#subTypeSubjList").html(html);
					$("#subTypeSubjList").show();
				}
			});
		}
	  else{
		  $("#subTypeSubjList").html('');
		  $("#subTypeSubjList").html('<div class="alert alert-info">Please select atleast one sub type.</div>');
	     alert('Please select atleat one sub type.');
		 return false;
	  }
	}
	</script>