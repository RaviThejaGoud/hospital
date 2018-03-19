<%@ include file="/common/taglibs.jsp"%>
<span class='<s:property value="description"/>' id='paymentForAdmiss'></span>
<div id="searchStudentsForm112">
<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
	<div class="row">
		<div class="form-group form-horizontal">
			<label class="control-label col-md-2">
				Student Name :
			</label>
			<div class="col-md-5">
				<p class="form-control-static  bold pageTitle">
					<s:property value="student.studentName" /> &nbsp;( <s:property value="student.classSection.classAndSection" /> )
				</p>
			</div>
		</div>
	</div>
		<s:form action="ajaxStudentFutureAcademicPaymentFee" id="addStudentPaymentFee" method="post" theme="simple" cssClass="form-horizontal" name="myform" namespace="/schoolfee">
			<s:hidden name="studentNumber" value='%{student.id}' />
			<s:hidden id="feeData" name="anyId" />
			<s:hidden name="tempId1" value="%{academicYear.id}"/>
			<s:hidden name="description" value="%{description}" />
			<s:hidden id="paybtn" name="plSubTopic"></s:hidden>				
			<s:hidden id="paymentType" name="anyTitle"></s:hidden>
			<s:set name="schoolTermsId" value=""></s:set>
			<s:set name="feeTypeId" value=""></s:set>
			<div class="form-group">
				<label class="control-label col-md-2">
					Select Fee Setting Type :
				</label>
				<div class="col-md-9">
					<s:checkboxlist list="objectList" name="chkBoxSelectedIds"
						listKey="%{id}" listValue="settingName" id="classes" theme="ems"
						onclick="javascript:getStudentsFeeConfiguration(this);"></s:checkboxlist>
				</div>
			</div>
				<div class="form-group">
					<label class="control-label col-md-2">
						Select class for payment : 
					</label>
					<div class="col-md-9">
						<s:if test='%{student.futureAcademicClassSecId != null && student.futureAcademicClassSecId != 0 }'>
						<s:hidden name="classSectionId" value="%{classSectionId}"></s:hidden>
							<s:select list="studyClassList" id="futureYearClassId" disabled="true" cssClass="form-control input-medium"
									name="classSectionId" listKey="classSectionIdAndClassId" listValue="classAndSection"
									headerKey="" headerValue="- Select Class -" theme="simple"
									onchange="javascript:getStudentsFeeConfiguration();">
							</s:select>						
						</s:if>
						<s:else>
							<s:select list="studyClassList" id="futureYearClassId" cssClass="form-control input-medium"
									name="classSectionId" listKey="classSectionIdAndClassId" listValue="classAndSection"
									headerKey="" headerValue="- Select Class -" theme="simple"
									onchange="javascript:getStudentsFeeConfiguration();">
							</s:select>
						</s:else>
					</div>
				</div>
				<div id="feeSettingStudentInvoice"></div>
		</s:form>
		<span class="studentId" id="<s:property value='student.id'/>" ></span>
		<span class="academicYearId" id="<s:property value='academicYear.id'/>"></span>
		<span class="classSectionId" id="<s:property value='student.classSectionId'/>" ></span>
</s:if>
<s:else>
	<div class="alert alert-info">
		Future academic year classes data not found. Please create Admission settings for future academic year.
	</div>
</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
 $("input:checkbox, input:radio:not('.toggle')").uniform(); 
	changePageTitle("Make Student Payment");
	getStudentsFeeConfiguration();
});

function getStudentsFeeConfiguration(){
		var settingId = 0;
		var classSectionAndClassId = $('#futureYearClassId').val();
		var studentId = $('span.studentId').attr('id');
		var paymentForAdmi = $('span#paymentForAdmiss').attr('class');
        var academicYearId= $('span.academicYearId').attr('id');
        var classSectionId = $('span.classSectionId').attr('id');
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0  && isNonEmpty(classSectionAndClassId)) {
			var classIds = classSectionAndClassId.split(':');
			var feeSettingId = $("input[name=chkBoxSelectedIds]:checked");
			var selectedSettingIds = '(';
			for ( var i = 0; i < feeSettingId.length; i++) {
				settingId = feeSettingId[i].value[0];
					selectedSettingIds += settingId + ', ';
			}
			selectedSettingIds += '0)';
			var	pars = "anyId=" + selectedSettingIds +"&student.id="+studentId+"&academicYearId="+academicYearId+"&description="+paymentForAdmi+
			"&classSectionId="+classSectionId+"&classId="+classIds[1]+"&empId="+classIds[0]+"&wishTitle=futureYearPayment";
			var	url = jQuery.url.getChatURL("/schoolfee/ajaxLoadStudentForInvoice.do");		
			$('#feeSettingStudentInvoice').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#feeSettingStudentInvoice").html(html);
				}
			});
		}
		else{
			$("#feeSettingStudentInvoice").empty();
		}
	}
</script>