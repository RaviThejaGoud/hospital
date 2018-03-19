<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<%@ include file="/common/messages.jsp"%>
    <div class="grid_14 noteFont">
		<div class="grid_1">
		 		<span class="noteMassage"> Note :</span>
		 	</div>
		 	<div class="grid_13">
				You can modify/delete passwords must be existing in school settings.		 		
		 	</div>
	</div>
<div class="hideSearchStudentBody">
	<div class="grid_13 alpha">
		<div class="grid_4 alpha">
			<label class="labelRight">
				Select Staff/Student Type:
			</label>
		</div>
		<div class="grid_6">
			<div class="grid_2">
				<input type="radio" value="Student" id="Student"
					onclick="changeStudnetOrStaff(this.value);" name="addStaffToHostel"
					class="radio" checked="checked">
				Student
			</div>
			<div class="grid_2">
				<input type="radio" value="Staff" id="Staff"
					onclick="changeStudnetOrStaff(this.value);" name="addStaffToHostel"
					class="radio">
				Staff
			</div>

		</div>
	</div>
		<div id="Staffs">
			<div id="commonTabContent">
				<div id="commonTabWrapper">
					<div id="commonStep">
						<fieldset>
							<s:if
								test="%{staffPaymentList != null && !staffPaymentList.isEmpty()}">
								<div class="grid_15" align="right"  data-target="staffPaymentList" style="width: 940px">
									<jsp:include page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
								</div>
								<div class="grid_15 th" style="width: 940px">
									<div class="grid_3">
										Invioce Number
									</div>
									<div class="grid_3">
										Staff Name
									</div>
									<div class="grid_3">
										Payment Amount
									</div>
									<div class="grid_2">
										Modify
									</div>
									<div class="grid_2">
										Delete
									</div>
									<div class="grid_3">
										<input type="checkbox" name="" value="" onClick="checkAllStaffInvoices()" class="checkbox staffAllInvoices">&nbsp;&nbsp;All Invoices
									</div>
									<div id="staffPaymentList">
									<s:iterator value="staffPaymentList" status="stat">
										<div class="grid_15 row1" style="width: 940px">
											<div class="grid_3">
												<s:property value="staffPaymentList[#stat.count-1][0]"/>
											</div>
											<div class="grid_3">
												<s:property value="staffPaymentList[#stat.count-1][1]"/> <s:property value="staffPaymentList[#stat.count-1][2]"/>
											</div>
											<div class="grid_3">
												<s:property value="staffPaymentList[#stat.count-1][4]"/>
												&nbsp;
											</div>
											<div class="grid_2">
												<s:url id="modifyHStaffInvoice"
													action="ajaxDoModifyHostelStaffInvoice"
													includeParams="all" escapeAmp="false" namespace="/hostel">
													<s:param name="staffId" value="staffPaymentList[#stat.count-1][6]" />
													<s:param name="invoiceNumber" value="staffPaymentList[#stat.count-1][0]" />
													<s:param name="type" value="%{'Modify'}" />
													<s:param name="lastUpdatedDateStr" value="%{staffPaymentList[#stat.count-1][5]}" />	
												</s:url>
												<sj:a href="%{modifyHStaffInvoice}"
													targets="modifyHStaffInvoice%{staffPaymentList[#stat.count-1][0]}"
													onCompleteTopics="doModifyHStaffInvoice"
													onBeforeTopics="cleanOpenDivs" indicator="indicator">Modify
												</sj:a>
											</div>
											<div class="grid_2">
												<s:url id="deleteStaffInvoice"
													action="ajaxDoDeleteHostelStaffInvoice"
													includeParams="all" escapeAmp="false" namespace="/hostel">
													<s:param name="staffId" value="staffPaymentList[#stat.count-1][6]" />
													<s:param name="invoiceNumber" value="staffPaymentList[#stat.count-1][0]" />
												</s:url>
												<sj:a href="%{deleteStaffInvoice}"
													targets="deleteStaffInvoice%{staffPaymentList[#stat.count-1][0]}"
													onCompleteTopics="doDeleteStaffInvoice"
													onBeforeTopics="cleanOpenDivs" indicator="indicator">Delete
												</sj:a>
											</div>
											<div class="grid_3">
												<input type="checkbox" name="chkBoxSelectedIds1" value="<s:property value='staffPaymentList[#stat.count-1][6]'/>,<s:property value='todayDate'/>,<s:property value='staffPaymentList[#stat.count-1][0]'/>" />
												&nbsp;&nbsp;&nbsp;
												<a  style="cursor: pointer;"  id="noPrint" onClick="javascript:printSraffHostelPreview('<s:property value='staffPaymentList[#stat.count-1][6]'/>','<s:property value='todayDate'/>','<s:property value='staffPaymentList[#stat.count-1][0]'/>')";target="_new"><img
														style="" src="../images/index.jpg"> Invoice : <s:property
														value="staffPaymentList[#stat.count-1][0]" /> <b>Print</b></a>
											</div>
											<div id="modifyHStaffInvoice<s:property value='staffPaymentList[#stat.count-1][0]' />" style="display: none;" class='load'> </div>
											<div id="deleteStaffInvoice<s:property value='staffPaymentList[#stat.count-1][0]' />" style="display: none;" class='load'></div>
										</div>
									</s:iterator>
								</div>
							 </div>
							</s:if>
							<s:else>
								<div class="grid_15">
									Currently there are no today Invoice numbers.
								</div>
							</s:else>
							<div class="grid_14" style="padding-top:10px ">
									<a style="cursor: pointer; float: right;" id="noPrint"
										class="cancelButton"
										onClick="javascript:staffPrintAllInvoices()" target="_new">
										<b>Print</b>
									</a>
							</div>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
		<div id="Students">
			<div id="commonTabContent">
				<div id="commonTabWrapper">
					<div id="commonStep">
						<fieldset>
							<s:if
								test="%{studentPaymentList != null && !studentPaymentList.isEmpty()}">
								<div class="grid_15" align="right"  data-target="studentPaymentList" style="width: 940px">
									<jsp:include page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
								</div>
								<div class="grid_15 th" style="width: 940px">
									<div class="grid_2" style="width: 115px">
										Invioce Number
									</div>
									<div class="grid_3">
										Student Name
									</div>
									<div class="grid_2">
										RollNumber
									</div>
									<div class="grid_3">
										Payment Amount
									</div>
									<div class="grid_2">
										Modify
									</div>
									<div class="grid_2">
										Delete
									</div>
									<div class="grid_3">
										<input type="checkbox" name="" value="" onClick="checkAllStudentInvoices()" class="checkbox allInvoices">&nbsp;&nbsp;All Invoices
									</div>
									<div id="studentPaymentList">
									<s:iterator value="studentPaymentList" status="stat">
										<div class="grid_15 row1" style="width: 940px">
											<div class="grid_2" style="width: 115px">
												<s:property value="studentPaymentList[#stat.count-1][0]"/>
											</div>
											<div class="grid_3">
												<s:property value="studentPaymentList[#stat.count-1][1]"/> <s:property value="studentPaymentList[#stat.count-1][2]"/>
											</div>
											<div class="grid_2">
												<s:property value="studentPaymentList[#stat.count-1][3]"/>
												&nbsp;
											</div>
											<div class="grid_3">
												<s:property value="studentPaymentList[#stat.count-1][4]"/>
												&nbsp;
											</div>
											<div class="grid_2">
												<s:url id="modifyHStudentInvoice"
													action="ajaxDoModifyHostelStudentInvoice"
													includeParams="all" escapeAmp="false" namespace="/hostel">
													<s:param name="studentId" value="studentPaymentList[#stat.count-1][6]" />
													<s:param name="invoiceNumber" value="studentPaymentList[#stat.count-1][0]" />
													<s:param name="type" value="%{'Modify'}" />
													<s:param name="lastUpdatedDateStr" value="%{studentPaymentList[#stat.count-1][5]}" />	
									  			</s:url>
												<sj:a href="%{modifyHStudentInvoice}"
													targets="modifyHStudentInvoice%{studentPaymentList[#stat.count-1][0]}"
													onCompleteTopics="doModifyHStudentInvoice"
													onBeforeTopics="cleanOpenDivs" indicator="indicator">Modify
										        </sj:a>
											</div>
											<div class="grid_2">
												<s:url id="deleteStudentInvoice"
													action="ajaxDoDeleteHostelStudentInvoice"
													includeParams="all" escapeAmp="false" namespace="/hostel">
													<s:param name="studentId" value="studentPaymentList[#stat.count-1][6]" />
													<s:param name="invoiceNumber" value="studentPaymentList[#stat.count-1][0]" />
												</s:url>
												<sj:a href="%{deleteStudentInvoice}"
													targets="deleteStudentInvoice%{studentPaymentList[#stat.count-1][0]}"
													onCompleteTopics="doDeleteStudentInvoice"
													onBeforeTopics="cleanOpenDivs" indicator="indicator">Delete
												</sj:a>
											</div>
											<div class="grid_3">
												<input type="checkbox" name="chkBoxSelectedIds" value="<s:property value='studentPaymentList[#stat.count-1][6]'/>,<s:property value='todayDate'/>,<s:property value='studentPaymentList[#stat.count-1][0]'/>" />
												&nbsp;&nbsp;&nbsp;
												<a style="cursor: pointer;" id="noPrint" onClick="javascript:printHostelPreview('<s:property value='studentPaymentList[#stat.count-1][6]'/>','<s:property value='todayDate'/>','<s:property value='studentPaymentList[#stat.count-1][0]'/>')";target="_new"><img
														style="" src="../images/index.jpg"> Invoice : <s:property
														value="studentPaymentList[#stat.count-1][0]" /> <b>Print</b></a>
											</div>
											<div id="modifyHStudentInvoice<s:property value='studentPaymentList[#stat.count-1][0]' />" style="display: none;" class='load'></div>
											<div id="deleteStudentInvoice<s:property value='studentPaymentList[#stat.count-1][0]' />" style="display: none;" class='load'></div>
										</div>
									</s:iterator>
									</div>
								</div>
							</s:if>
							<s:else>
								<div class="grid_15">
									Currently there are no today Invoice numbers.
								</div>
							</s:else>
							<div class="grid_15" style="padding-top:10px ">
									<a style="cursor: pointer; float: right;" id="noPrint"
										class="cancelButton"
										onClick="javascript:studentPrintAllInvoices()" target="_new">
										<b>Print</b>
									</a>
							</div>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
</div>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
changePageTitle("Delete / Modify Today's Receipts");
	$("#staffPaymentList").pagination();
	$("#studentPaymentList").pagination();
	$('#Staffs').hide();
	$.subscribe('doModifyHStudentInvoice', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$.subscribe('doModifyHStaffInvoice', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
		$.subscribe('doDeleteStaffInvoice', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$.subscribe('doDeleteStudentInvoice', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
});

function checkAllStudentInvoices() {
	if ($(".allInvoices").is(':checked'))
		$("input[name='chkBoxSelectedIds']").attr("checked", "true");
	else
		$("input[name='chkBoxSelectedIds']").removeAttr("checked");

}
function checkAllStaffInvoices() {
	if ($(".staffAllInvoices").is(':checked'))
		$("input[name='chkBoxSelectedIds1']").attr("checked", "true");
	else
		$("input[name='chkBoxSelectedIds1']").removeAttr("checked");

}

$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		//$(".allInvoices").removeAttr("checked");
		$(".allInvoices").attr("checked", false);
	} else {
		$(".allInvoices").attr("checked", true);
	}
});

$("input[name=chkBoxSelectedIds1]").click(function() {
	if ($("input[name=chkBoxSelectedIds1]:unchecked").length > 0) {
		//$(".allInvoices").removeAttr("checked");
		$(".staffAllInvoices").attr("checked", false);
	} else {
		$(".staffAllInvoices").attr("checked", true);
	}
});
function changeStudnetOrStaff(staffType) {
	if (staffType == 'Staff') {
		$('#Students').hide();
		$('#Staffs').show();
	} else {
		$('#Students').show();
		$('#Staffs').hide();
	}
}
function studentPrintAllInvoices (){
	if($("input[name=chkBoxSelectedIds]:checked").length>0){
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassIds = '';
			for ( var i = 0; i < classIds.length; i++) {
				selectedClassIds += classIds[i].value + '~';
			}
		//$("#classNameIds").val(selectedClassIds);
		var pars = "selectedClassIds=" + selectedClassIds;
		var url = jQuery.url.getChatURL("/reports/ajaxStudentHostelPaymentPdfFeeReport.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			 success : function(data) {
           		prntPrvw(data);
        	}
		});
	return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		  alert("Please select at least one Invoice");
	return false;
   }
}
function staffPrintAllInvoices (){
	if($("input[name=chkBoxSelectedIds1]:checked").length>0){
		var classIds = $("input[name=chkBoxSelectedIds1]:checked");
		var selectedClassIds = '';
			for ( var i = 0; i < classIds.length; i++) {
				selectedClassIds += classIds[i].value + '~';
			}
		var pars = "selectedClassIds=" + selectedClassIds;
		var url = jQuery.url.getChatURL("/reports/ajaxStaffHostelPrintAllInvoicesReport.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			 success : function(data) {
           		prntPrvw(data);
        	}
		});
	return true;
	} else if ($("input[name=chkBoxSelectedIds1]:checked").length == 0) {
		  alert("Please select at least one Invoice");
	return false;
   }
}
</script>
<style type="text/css">
.grid_1,.grid_2,.grid_3,.grid_4,.grid_5,.grid_6,.grid_7,.grid_8,.grid_9,.grid_10,.grid_11,.grid_12,.grid_13,.grid_14,.grid_15,.grid_16,grid_17,grid_18
	{
	margin-right: 0px;
}
</style>
