<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	id="responsive"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			View fee collection details
		</h4>
	</div>
	<div class="modal-body">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<div id="searchStudentsForm112">
			<!--<a
					onclick='javascript:hideFeeDatesCont(<s:property value="student.id"/>);'
					style="cursor: pointer;" class="labelRight">Close</a>
				-->
		<div class="row">
			<div class="col-md-6" align="left">
			    <div class="form-group">
			            <label class="col-md-12 control-label">
					        <b>Student Name : <s:property value="student.fullFormattedName"/>(<s:property value="student.classAndSection"/>)</b> 
				        </label>
			
			   </div>
	  </div>
	          <div class="col-md-6" align="right">
		             <div class="form-group">
			              <label class="col-md-9 control-label">
					       <b>Admission Number : <s:property value="student.studAdmissionNumber"/></b>
				          </label>
		            </div>  
		     </div>
		</div>
		<div class="row">
		<div class="form-group">
							<label class="col-md-3 control-label"> Select Fee Setting
								Type : </label>
							<div class="col-md-9">
								<s:checkboxlist list="objectList" name="chkBoxSelectedIds"
									listKey="%{id}" listValue="settingName" id="classes"
									theme="ems" onclick="javascript:getFeeSettingIdDetails(this);"></s:checkboxlist>
							</div>
						</div>
		
		
		</div>
		
			<div>Excess amount:- <b>Available:</b>&nbsp;<s:property value="balance"/>  &nbsp;&nbsp;&nbsp; <b>Used:</b>&nbsp;<s:property value="totalAmount"/></div>
			
			<div id="feeSettingStudentInvoice"></div>
			<span class="studentId" id="<s:property value='student.id'/>"></span>
			<span class="classSectionId"
				id="<s:property value='student.classSectionId'/>"></span>
			<s:if test="%{anyTitle != null && anyTitle == 'futureAcademic' }">
				<span class="academicYearId"
					id="<s:property value='student.academicYearId'/>"></span>
				<span class="futureAcademicClass" id="<s:property value='student.futureAcademicClassSecId'/>"></span>
			</s:if>
			<s:else>
				<span class="academicYearId" id="<s:property value='student.academicYearId'/>"></span>
			</s:else>
		</div>
		
		
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
$("input:checkbox, input:radio").uniform();

	getStudentFeeDetails();
});
/*function hideFeeDatesCont(studId){
 $('#searchFineFee'+studId).hide();
 }*/
function getStudentFeeDetails() {
	var settingId = 0;
	var studentId = $('span.studentId').attr('id');
	var futureAcademicClass = $('span.futureAcademicClass').attr('id');
	// var paymentStatus = $('span.stuPaymentStatus').attr('id');
	var classSectionId = $('span.classSectionId').attr('id');
	var academicYearId = $('span.academicYearId').attr('id');
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var feeSettingId = $("input[name=chkBoxSelectedIds]:checked");
		var selectedSettingIds = '(';
		for ( var i = 0; i < feeSettingId.length; i++) {
			settingId = feeSettingId[i].value[0];
			selectedSettingIds += settingId + ', ';
		}
		selectedSettingIds += '0)';
		var pars = '';
		if (isNonEmpty(futureAcademicClass))
			pars = "anyId=" + selectedSettingIds + "&student.id=" + studentId+ "&academicYearId=" + academicYearId + "&tempString="+ futureAcademicClass;
		else
			pars = "anyId=" + selectedSettingIds + "&student.id=" + studentId+ "&academicYearId=" + academicYearId;
		var url = jQuery.url
				.getChatURL("/schoolfee/ajaxViewStudentFeePaymentDetails.do");
		$('#feeSettingStudentInvoice').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#feeSettingStudentInvoice").html(html);
			}
		});
	} else {
		$("#feeSettingStudentInvoice").empty();
	}
}
</script>