<%@ include file="/common/taglibs.jsp"%>
	<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
	<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
	<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
	<span id="admissionAcademicyear" class="<s:property value='#session.admissionAcademicYearId'/>"></span>
	<span id="usrAcadmicId" class="<s:property value='userAcademicYearId'/>"></span>
	<s:if test='%{anyId == "admissionTerms"}'>
		<s:form action="ajaxAddAdmissionSchoolTerms" id="addSchoolterms"
			method="post" cssClass="form-horizontal" theme="simple">
			<s:hidden name="schoolTermId" value="%{schoolTerms.id}"></s:hidden>
			<s:hidden name="tempString" value="%{tempString}"></s:hidden>
			<s:hidden id="classIdsIds" name="anyId" />
			<h4 class="pageTitle bold">
				Add term
			<div class="row"
				style="float: right; width: 150px; margin-top: -12px;">
				<s:url id="doVIewSchoolTermsBackLink" action="ajaxGetFeeTypes"
					includeParams="all" escapeAmp="false">
				</s:url>
				<sj:a href="%{doVIewSchoolTermsBackLink}" cssClass="btn default"
					targets="feeSettingsContent"> Back</sj:a>
			</div>
		</h4> 
		
		<span class="label label-danger">NOTE :</span>&nbsp;&nbsp;
				You can define the term to split the fee into multiple installments. You can define any number of fee terms. Each term has start date,end date and due date
				<div class="spaceDiv"></div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">
								<span class="required"> * </span>Select Fee Setting Type :
							</label>
							<div class="col-md-6">
								<s:select list="objectList" listKey="id" listValue="settingName"
									id="settingFeeTerm" name="tempId"
									theme="simple" headerKey="null"
									cssClass="required form-control input-medium"
									onchange="javascript:getAjaxGetFeeSettingTerms(this.value);"
									headerValue="-Select Fee Setting Type-" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<div id="allFeeSettingTerms"></div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4" >
								<span class="required">*</span>Term Type :
							</label>
							<div class="col-md-5">
								<sj:textfield name="schoolTerms.termName"
									id="termName" cssClass="required form-control input-medium termName"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4" >
								Reminder Before No. of
								Days :
							</label>
							<div class="col-md-5">
								<sj:textfield name="schoolTerms.noOfDays" maxlength="2" size="5"
									cssClass="numeric form-control input-medium" />
								<span class="help-block">(Reminder for SMS/E-mail before
									due date)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>From Date :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="fromDate" name="schoolTerms.fromDate" readonly="readonly"
										onchange="javascript:verifyDate(this.value);" 
										class="required form-control">
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4" >
								<span class="required">*</span>To Date :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="toDate" name="schoolTerms.toDate" readonly=""
										onchange="javascript:verifyDate(this.value);" type="text"
										class="required form-control">
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4" >
								<span class="required">*</span>Due Date :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="dueDate" name="schoolTerms.dueDate" readonly=""
										onchange="javascript:verifyDate(this.value);" type="text"
										class="required form-control">
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4" >
								Due Date1 :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="dueDate1" name="schoolTerms.dueDate1" readonly=""
										onchange="javascript:verifyDate(this.value);" type="text"
										class="form-control">
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4" >
								Due Date2 :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="dueDate2" name="schoolTerms.dueDate2" readonly=""
										onchange="javascript:verifyDate(this.value);" type="text"
										class="form-control">
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Content for Email reminder  :
							</label>
							<div class="col-md-6">
								<sj:textarea rows="3" cols="20" cssClass="form-control"
									readonly="true"
									value="This is just a friendly reminder that your children <Children Name>  <Term Name > Fee Due date is <Date>."></sj:textarea>
								<span class="help-block">(Do not remove <strong><
										></strong>type variables) </span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Content for SMS reminder  :
							</label>
							<div class="col-md-6">
								<sj:textarea rows="2" cols="30" cssClass="form-control"
									 readonly="true"
									 value="Dear Parent, This is friendly reminder your chaild <Children Name> <Class Name> <Term Name> fee of Rs.<Amount>/- due on <Date>, please ignore if you already paid. <School Name>"></sj:textarea>
								<span class="help-block">(Do not remove <strong><></strong>type
									variables)
							</div>
						</div>
					</div>
				</div>
				<s:if
					test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
					<s:if test="%{classList != null && !classList.isEmpty()}">
						<div class="row" id="showClassAndSetions" style="display: none;">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-5">
											Select Applicable Classes For Clone :
										</label>
										<div class="col-md-5">
											<input type="radio" id="checkAll" value="ToALL"
												onclick="frequencyChangeClass('ToALL')" name="classBelongs"
												checked>
											To All
											<input type="radio" value="Class"
												onclick="frequencyChangeClass('Class')" name="classBelongs"
												id="checkClass">
											Classes
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-11">
									<div id="clickClass">
										<div id="checkBoxFieldErrors"></div>
										<s:checkboxlist name="chkBoxSelectedIds" id="chkBoxClassIds"
											list="classList" listKey="id" listValue="className" 
											theme="ems" />
									</div>
								</div>
							</div>
						</div>
					</s:if>
				</s:if>
				<div class="form-actions fluid">
						<div class="col-md-6">
							<div class="col-md-offset-3 col-md-12">
								<sj:submit   targets="feeSettingsContent" value="Submit"
									indicator="indicator" formIds="addSchoolterms"
									cssClass="submitBt btn blue" validate="true"
									onBeforeTopics="schoolTermFormValidation" />
								<s:url id="doCancelSchoolTerms" action="ajaxGetFeeTypes"
									includeParams="all" escapeAmp="false">
									</s:url>
								<sj:a href="%{doCancelSchoolTerms}" cssClass="btn default"
									 targets="feeSettingsContent">Cancel</sj:a>
							</div>
						</div>
				</div>
		</s:form>
	</s:if>
	<s:else>
		<s:form action="ajaxAddSchoolTerms" id="addSchoolterms1" method="post"
			cssClass="form-horizontal" theme="simple">
			<s:hidden name="schoolTermId" value="%{schoolTerms.id}"></s:hidden>
			<s:hidden name="tempString" value="feeTerms"></s:hidden>
			<s:hidden id="classIdsIds" name="anyId" />
			<h4 class="pageTitle bold">
				Add term
			<div class="row" style="float: right; width: 150px;margin-top:-12px;">
				<s:url id="doCancelSchoolTerms" action="ajaxViewSelectedFeeSettings"
					includeParams="all" escapeAmp="false">
					<s:param name="tempString">feeTerms</s:param>
				</s:url>
				<sj:a href="%{doCancelSchoolTerms}" cssClass="btn default"
					targets="feeSettingsContent">  Back</sj:a>
			</div>
		</h4>
		
		<span  class="label label-danger">
			NOTE : </span>&nbsp;&nbsp;
				You can define the term to split the fee into multiple installments. You can define any number of fee terms. Each term has start date,end date and due date
				<div class="spaceDiv"></div>
			<div class="form-body">	
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-5">
								<span class="required"> * </span>Select Fee Setting Type :
							</label>
							<div class="col-md-6">
								<s:select list="objectList" listKey="id" listValue="settingName"
									 id="settingFeeTerm" name="tempId"
									theme="simple" headerKey="null"
									cssClass="required form-control input-medium"
									onchange="javascript:getAjaxGetFeeSettingTerms(this.value);"
									headerValue="-Select Fee Setting Type-" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<div id="allFeeSettingTerms"></div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4" >
								<span class="required">*</span>Term Type :
							</label>
							<div class="col-md-5">
								<sj:textfield name="schoolTerms.termName" 
									id="termName" cssClass="required form-control input-medium"
									maxlength="60"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4" >
							   Reminder Before No. of 
								Days :
							</label>
							<div class="col-md-5">
								<sj:textfield name="schoolTerms.noOfDays" id="subNum"
								maxlength="2"
									size="5" cssClass="numeric form-control input-medium" />
								<span class="help-block">(Reminder for SMS/E-mail before
									due date.)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4" >
								<span class="required">*</span>From Date :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="fromDate" name="schoolTerms.fromDate" readonly="readonly" value='<s:property value="schoolTerms.fromDate"/>'
										onchange="javascript:verifyDate(this.value);" type="text"
										class="required form-control input-medium fdate">
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-4 control-label">
								<span class="required">*</span>To Date :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="toDate" name="schoolTerms.toDate" readonly="readonly" value='<s:property value="schoolTerms.toDate"/>'
										onchange="javascript:verifyDate(this.value);" type="text"
									class="required form-control input-medium fdate">
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-4 control-label">
								<span class="required">*</span>Due Date :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="dueDate" name="schoolTerms.dueDate" readonly="readonly" value='<s:property value="schoolTerms.dueDate"/>'
										onchange="javascript:verifyDate(this.value);" type="text"
										class="required form-control input-medium fdate">
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-4 control-label">
								Due Date1:
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="dueDate1" name="schoolTerms.dueDate1" readonly="readonly" value='<s:property value="schoolTerms.dueDate1"/>'
										onchange="javascript:verifyDate(this.value);" type="text"
										class="form-control input-medium fdate">
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-md-4 control-label">
								Due Date2 :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="dueDate2" name="schoolTerms.dueDate2" readonly="readonly" value='<s:property value="schoolTerms.dueDate2"/>'
										onchange="javascript:verifyDate(this.value);" type="text"
										class="form-control input-medium fdate">
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Content for Email reminder :
							</label>
							<div class="col-md-6">
								<sj:textarea rows="3" cols="30" cssClass="form-control"
									readonly="true"
									value="This is just a friendly reminder that your children <Children Name>  <Term Name > Fee Due date is <Date>."></sj:textarea>
								<span class="help-block">(Do not remove <strong><
										></strong>type variables)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Content for SMS reminder :
							</label>
							<div class="col-md-6">
								<sj:textarea rows="2" cols="30" readonly="true"
									cssClass="form-control" 
									value="Dear Parent, This is friendly reminder your child <Children Name> <Class Name> <Term Name> fee of Rs.<Amount>/- due on <Date>, please ignore if you already paid. <School Name>"></sj:textarea>
								<span class="help-block">(Do not remove <strong><></strong>type
									variables)</span>
							</div>
						</div>
					</div>
				</div>
				<s:if
					test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
					<s:if test="%{classList != null && !classList.isEmpty()}">
						<div class="row" id="showClassAndSetions" style="display: none;">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="col-md-5 control-label">
											Select Applicable Classes For Clone :
										</label>
										<div class="col-md-6">
											<div class="radio-list">
												<label class="radio-inline">
													<input type="radio" id="checkAll" value="ToALL"
														onclick="frequencyChangeClass('ToALL')" name="classBelongs"
														checked>
													To All
												</label>
												<label class="radio-inline">
													<input type="radio" value="Class"
														onclick="frequencyChangeClass('Class')" name="classBelongs"
														id="checkClass">
													Classes
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div id="checkBoxFieldErrors"></div>
							<div class="form-group">
								<label class="col-md-3 control-label">
									Classes :
								</label>
								<div class="col-md-9">
									<div class="checkbox-list">
										<s:checkboxlist name="chkBoxSelectedIds" id="chkBoxClassIds"
											list="classList" listKey="id" listValue="className" theme="ems" />
									</div>
								</div>
							</div>
						</div>
					</s:if>
				</s:if>
				
				<div class="form-actions fluid">
					<div class="col-md-7">
						<div class="col-md-offset-4 col-md-12">
							<sj:submit   targets="feeSettingsContent" value="Submit"
								 formIds="addSchoolterms1"
								cssClass="submitBt btn blue" validate="true"
								onBeforeTopics="schoolTermFormValidation1" />
							<s:url id="doCancelSchoolTerms"
								action="ajaxViewSelectedFeeSettings" includeParams="all"
								escapeAmp="false">
								<s:param name="tempString">feeTerms</s:param>
								</s:url>
							<sj:a href="%{doCancelSchoolTerms}" cssClass="btn default"
								 targets="feeSettingsContent">Cancel</sj:a>
						</div>
					</div>
				</div>
			</div>	
		</s:form>
	</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		FormComponents.init();
		FormAdvanced.init();
		UIExtendedModals.init();
		
		$("input:checkbox, input:radio").uniform();
		changePageTitle("Create School Term");
		$('.numeric').numeric();
		$('.blockHeader h2').html('Manage Academics');
		var settingFeeTermId=$('#settingFeeTerm').val();
		$('select[name="tempId"]').change(function() {
			$("input[name='schoolTerms.termName']").val('');
			if ($('p.word-taken').html() == 'Already taken!!!') {
				$('p.word-taken').html('');
				$("#termName").attr.removeClass("required");
			}else if ($('p.word-available').html() == 'Available') {
				$('p.word-available').html('');
				$("#termName").attr.removeClass("required");
			}
		});
		if(isNonEmpty(settingFeeTermId)){
			getAjaxGetFeeSettingTerms(settingFeeTermId);
		}
		frequencyChangeClass('ToALL');
		$("#termName").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckFeeTurmType.do?selectedId="+settingFeeTermId,
		{
			minChars : 3,
			min : "no"
		});
		$("input:checkbox[name=chkBoxSelectedIds]").change(function() {
			var levelId = document.getElementsByName("chkBoxSelectedIds");
			var amount = 0;
			for (i = 0; i < levelId.length; i++) {
				if (levelId[i].checked) {
					$("input#checkClass").attr('checked', 'checked');
				} else {
					$("input#checkAll").removeAttr("checked");
				}
			}
		});
		var terms='<s:property value="anyId"/>';
		if ( terms == 'admissionTerms') {
			 
		}
		else{
			
			var startDate = $('span#startDateSpan').attr("class");
			var endDate = $('span#endDateSpan').attr("class");
			dateRestrictionWithinAcademicYear(startDate,endDate);
			
		}
		
	});
 
	$.subscribe('schoolTermFormValidation1', function(event, data) {
		var classIds=$("input[name=chkBoxSelectedIds]:checked");
		var selectedClassIds='';
	       if(classIds.length != 0){
			 selectedClassIds = '(';
			 for(var i=0; i< classIds.length;i++ )
	       	   {
					selectedClassIds += classIds[i].value + ', ';
		        }
		        selectedClassIds += '0)';
		}
		$("#classIdsIds").val(selectedClassIds);
		});
	function frequencyChangeClass(clickButton) {
	
		var frequency = clickButton;
		//alert (frequency);
		if (frequency == 'ToALL') {
			$("[name='chkBoxSelectedIds']").each(function(){
			   $(this).attr("checked",true);
			   $(this).parent('span').addClass('checked');
			});
			//$("#clickClass").hide();
		} else {
			if (frequency == 'Class') {
			$("[name='chkBoxSelectedIds']").each(function(){
			   $(this).removeAttr("checked");
			   $(this).parent('span').removeClass('checked');
			});
				//$("#clickClass").show();
			}
		}
	}
	
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $("input#checkAll").parent('span').removeClass("checked");
			$("input#checkAll").attr("checked", false);
			$("input#checkClass").parent('span').addClass("checked");
			$("input#checkClass").attr("checked", true);
		} else {
			  $("input#checkAll").parent('span').addClass("checked");
			  $("input#checkAll").attr("checked", true);
			  $("input#checkClass").parent('span').removeClass("checked");
			  $("input#checkClass").attr("checked", false);
		}
	});
	
	function verifyDate(date) {
		var startDate = $("#fromDate").val();
		var endDate = $("#toDate").val();
		var dueDate = $("#dueDate").val();
		var dueDate1 = $("#dueDate1").val();
		var dueDate2 = $("#dueDate2").val();
		if (startDate == "") {
			alert("Please select From Date first.");
			$('input#toDate').val('');
			$('input#dueDate').val('');
			$('input#dueDate1').val('');
			$('input#dueDate2').val('');
			return false;
		}
		if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
			if (Date.parse(endDate) < Date.parse(startDate)) {
				alert("To Date should be greater than or equal to From Date.");
				$("#toDate").val('');
				return false;
			}
		}
	}
	function getAjaxGetFeeSettingTerms(settingFeeTermId) {
	var tempId=null;
	var tempString="feeTerms";
		if(isNonEmpty(settingFeeTermId)){
			var pars = "tempId=" + settingFeeTermId +"&tempString="+tempString;
		$("#allFeeSettingTerms").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/schoolfee/ajaxSchoolFeeSettingCreateTerms.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#allFeeSettingTerms").html(html);
			}
		});
		}
	}
	$.subscribe('schoolTermFormValidation1',function(event, data) {
	 $('p.word-taken').each(function() {
	  if($(this).html()=='Already taken!!!'){
	     event.originalEvent.options.submit=false;
	   }
	 });
	 $('button.close').click();
	});
</script>