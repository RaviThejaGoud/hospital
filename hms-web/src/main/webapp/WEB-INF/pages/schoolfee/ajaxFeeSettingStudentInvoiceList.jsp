<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<span class='<s:property value="description"/>' id='paymentForAdmiss'></span>
<span class="eventId" id="<s:property value="eventId" />"></span>
<div>
	<div class="row" style="float: right; width: 150px;">
		<s:if test="%{description == 'studInvPaymt'}">
			<s:url id="doCancelStudent" action="ajaxSearchStudentsForMakePayment" namespace="/schoolfee" includeParams="all" escapeAmp="false">
				<s:param name="academicYearId" value="%{0}"></s:param>
				<s:param name="anyTitle" value="%{anyTitle}"></s:param>
				<s:param name="classSectionId" value="%{classSectionId}"></s:param>
			</s:url>
			<sj:a href="%{doCancelStudent}" cssClass="btn default" targets="searchStudentsForm112"> Back </sj:a>
		</s:if>
		<s:else>
			<s:url id="doCancelStudent" action="ajaxGetOnlineAdmissions" namespace="/admin" includeParams="all" escapeAmp="false">
				<s:param name="academicYearId" value="%{student.academicYearId}"></s:param>
			</s:url>
			<sj:a href="%{doCancelStudent}" cssClass="btn default" targets="mainContentDiv"> Back </sj:a>
		</s:else>
	</div>

	<div class="row">
<div class="alert alert-danger" style="display: none;margin-top: 42px;" id="currentTermDivId">
			<button data-dismiss="alert" class="close"></button>
			<strong>Currently no terms are available.
			</strong>
		</div>
		
</div>		
	<div class="row">
		<div class="form-group form-horizontal">
			<label class="control-label col-md-2"> Student Name : </label>
			<div class="col-md-3">
				<p class="form-control-static  bold pageTitle">
					<s:if test="%{onlineApplicationDetails != null && onlineApplicationDetails != empty}">
						<s:property value="onlineApplicationDetails.firstName" />
						<s:property value="onlineApplicationDetails.lastName" />
					</s:if>
					<s:else>
						<s:property value="student.studentName" /> &nbsp;(<s:property
							value="student.classSection.classAndSection"/> )
					</s:else>
				</p>
			</div>
		</div>
		<s:if test="%{description == 'studInvPaymt'}">
	     <div class="form-group form-horizontal" >
			<label class="control-label col-md-2"> Admission Number:</label>
			<div class="col-md-2">
				<p class="form-control-static  bold pageTitle">
					<s:property value="student.studAdmissionNumber" />
				</p>
			</div>
	   </div>
	   </s:if>
  </div>

	<s:if test='%{user.isSchoolHostel=="Y"}'>
		<s:if test='%{feeConfigured == "Y"}'>
			<s:property value="feeConfigured" />
			<s:if test="%{student.getBed() > 0}">
				<s:if test="%{onlineApplicationDetails != null}">
					<s:form action="ajaxAddAdmissionStudentsToClass"
						id="addStudentPaymentFee" cssClass="form-horizontal" method="post"
						theme="simple" name="myform" namespace="/admin">
						<s:hidden name="onlineApplicationDetails.id"></s:hidden>
						<s:hidden id="feeData" name="anyId" />
						<s:hidden id="paymentType" name="paymentType"></s:hidden>
						<s:hidden id="paybtn" name="plSubTopic"></s:hidden>
						<s:set name="c" value=""></s:set>
						<s:set name="feeTypeId" value=""></s:set>
						<div class="form-group">
							<label class="control-label col-md-2"> Select Fee Setting
								Type : </label>
							<div class="col-md-9">
								<s:checkboxlist list="objectList" name="chkBoxSelectedIds"
									listKey="%{id}" listValue="settingName" id="classes"
									theme="ems" onclick="javascript:getFeeSettingIdDetails(this);"></s:checkboxlist>
							</div>
						</div>
						<div id="feeSettingStudentInvoice"></div>
					</s:form>
					<span class="applicationId"
						id="<s:property value='onlineApplicationDetails.id'/>"></span>
					<span class="academicYearId"
						id="<s:property value='onlineApplicationDetails.academicYear.id'/>"></span>
				</s:if>
				<s:else>
					<s:form action="ajaxPayStudentPaymentFee" id="addStudentPaymentFee"
						cssClass="form-horizontal" method="post" theme="simple"
						name="myform" namespace="/schoolfee">
						<s:hidden name="studentNumber" value='%{student.id}' />
						<s:hidden id="feeData" name="anyId" />
						<s:hidden name="tempId1" value="%{student.academicYearId}" />
						<s:hidden name="description" value="%{description}" />
						<s:hidden id="paymentType" name="paymentType"></s:hidden>
						<s:hidden id="paybtn" name="plSubTopic"></s:hidden>
						<s:set name="schoolTermsId" value=""></s:set>
						<s:set name="feeTypeId" value=""></s:set>
						<div class="form-group">
							<label class="col-md-2 control-label"> Select Fee Setting
								Type : </label>
							<div class="col-md-9">
								<s:checkboxlist list="objectList" name="chkBoxSelectedIds"
									listKey="%{id}" listValue="settingName" id="classes"
									theme="ems" onclick="javascript:getFeeSettingIdDetails(this);"></s:checkboxlist>
							</div>
						</div>
						<div id="feeSettingStudentInvoice"></div>
					</s:form>
					<span class="studentId" id="<s:property value='student.id'/>"></span>
					<span class="classSectionId"
						id="<s:property value='student.classSectionId'/>"></span>
					<span class="academicYearId"
						id="<s:property value='student.academicYearId'/>"></span>
				</s:else>
			</s:if>
			<br />
			<br />
			<s:else>
				<div class="alert alert-info">Please assign bed for the
					student before configuring the fee </div>
			</s:else>
		</s:if>
		<br />
		<br />
		<s:else>
			<div class="alert alert-info">please configure fee in Admin</div>
		</s:else>
	</s:if>
	<s:else>
		<s:if test="%{onlineApplicationDetails != null}">
			<s:form action="ajaxAddAdmissionStudentsToClass"
				id="addStudentPaymentFee" cssClass="form-horizontal" method="post"
				theme="simple" name="myform" namespace="/admin">
				<s:hidden name="onlineApplicationDetails.id"></s:hidden>
				<s:hidden id="feeData" name="anyId" />
				<s:hidden id="paymentType" name="paymentType"></s:hidden>
				<s:hidden id="paybtn" name="plSubTopic"></s:hidden>
				<s:set name="c" value=""></s:set>
				<s:set name="feeTypeId" value=""></s:set>
				<div class="form-group">
					<label class="control-label col-md-2"> Select Fee Setting
						Type : </label>
					<div class="col-md-9">
						<s:checkboxlist list="objectList" name="chkBoxSelectedIds"
							listKey="%{id}" listValue="settingName" id="classes" theme="ems"
							onclick="javascript:getFeeSettingIdDetails(this);"></s:checkboxlist>
					</div>
				</div>
				<div id="feeSettingStudentInvoice"></div>
			</s:form>
			<span class="applicationId"
				id="<s:property value='onlineApplicationDetails.id'/>"></span>
			<span class="academicYearId"
				id="<s:property value='onlineApplicationDetails.academicYear.id'/>"></span>
		</s:if>
		<s:else>
			<s:form action="ajaxPayStudentPaymentFee" id="addStudentPaymentFee"
				cssClass="form-horizontal" method="post" theme="simple"
				name="myform" namespace="/schoolfee">
				<s:hidden name="studentNumber" value='%{student.id}' />
				<s:hidden id="feeData" name="anyId" />
				<s:hidden name="tempId1" value="%{student.academicYearId}" />
				<s:hidden name="description" value="%{description}" />
				<s:hidden id="paymentType" name="paymentType"></s:hidden>
				<s:hidden id="paybtn" name="plSubTopic"></s:hidden>
				<s:set name="schoolTermsId" value=""></s:set>
				<s:set name="feeTypeId" value=""></s:set>
				<div class="form-group">
					<label class="col-md-2 control-label"> Select Fee Setting
						Type : </label>
					<div class="col-md-9">
						<s:checkboxlist list="objectList" name="chkBoxSelectedIds"
							listKey="%{id}" listValue="settingName" id="classes" theme="ems"
							onclick="javascript:getFeeSettingIdDetails(this);"></s:checkboxlist>
					</div>
				</div>
				<div id="feeSettingStudentInvoice"></div>
			</s:form>
			<span class="studentId" id="<s:property value='student.id'/>"></span>
			<span class="classSectionId"
				id="<s:property value='student.classSectionId'/>"></span>
			<span class="academicYearId"
				id="<s:property value='student.academicYearId'/>"></span>
		</s:else>
	</s:else>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Make Student Payment");
		$("input:checkbox, input:radio:not('.toggle')").uniform();
		FormComponents.init();
		getFeeSettingIdDetails();
	});

	function getFeeSettingIdDetails() {
		var settingId = 0;
		var studentId = $('span.studentId').attr('id');
		var paymentForAdmi = $('span#paymentForAdmiss').attr('class');
		var eventId = $('span.eventId').attr('id');
		// var paymentStatus = $('span.stuPaymentStatus').attr('id');
		var classSectionId = $('span.classSectionId').attr('id');
		var applicationId = $('span.applicationId').attr('id');
		var academicYearId = $('span.academicYearId').attr('id');
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var feeSettingId = $("input[name=chkBoxSelectedIds]:checked");
			var selectedSettingIds = '(';
			for (var i = 0; i < feeSettingId.length; i++) {
				settingId = feeSettingId[i].value[0];
				selectedSettingIds += settingId + ', ';
			}
			selectedSettingIds += '0)';
			var pars = '';
			var url = '';
			if (isNonEmpty(applicationId)) {
				pars = "anyId=" + selectedSettingIds+ "&onlineApplicationDetails.id=" + applicationId+ "&academicYearId=" + academicYearId + "&eventId="+ eventId;
				url = jQuery.url.getChatURL("/schoolfee/ajaxLoadAdmissionStudentForInvoice.do");
			} else {
				pars = "anyId=" + selectedSettingIds + "&student.id="+ studentId + "&academicYearId=" + academicYearId+ "&description=" + paymentForAdmi + "&classSectionId="+ classSectionId;
				url = jQuery.url.getChatURL("/schoolfee/ajaxLoadStudentForInvoice.do");
			}
			$('#feeSettingStudentInvoice').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax({
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