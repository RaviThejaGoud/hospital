<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/json2.js"></script>
<div id="schoolContent">
	<s:if
		test='%{academicYear.transportFeeByBoardingPoint && schoolFeeSetting.settingName == "Transport Fee"}'>
		<s:hidden name="hiddenSelectedIds" value="%{className.id}" id="test"></s:hidden>
		<s:hidden name="schoolTermchkBoxSelectedIds" value="%{anyId}"
			id="schoolcategoryId"></s:hidden>
		<div class="alert alert-info">Transport Fee selected with
			boarding point wise fee.</div>
	</s:if>
	<s:else>
		<s:form action="ajaxAddSchoolFee" id="addClassFee" method="post"
			name="addClassFee1" cssClass="form-horizontal" theme="simple"
			namespace="/schoolfee">
			<s:hidden name="hiddenSelectedIds" value="%{className.id}" id="test"></s:hidden>
			<s:hidden name="schoolTermchkBoxSelectedIds" value="%{anyId}"
				id="schoolcategoryId"></s:hidden>
			<s:hidden name="tempString" cssClass='tempString' />
			<s:hidden name="tempId" value="%{tempId}" id="feeSettingId"></s:hidden>
			<p>
				<span class="label label-danger">NOTE :</span> The fees, that will
				be assigned through this form, are applicable for all sections of
				the selected classes.
			</p>
			<div class="form-body">
				<s:if test="%{schoolCategoriesList.size >0}">
					<div class="form-group">
						<label class="col-md-3 control-label" style="line-height: 33px;"> Select Category : </label>
						<div class="col-md-9">
							<div class="form-control-static">
								<s:checkboxlist list="schoolCategoriesList" theme="ems"
									name="schoolTermchkBoxSelectedIds" listKey="id"
									cssClass="check_all" listValue="categoryName"
									id="schoolTermchkBoxSelectedIds" />
								<s:checkboxlist list="tempList" theme="ems"
									name="schoolTermchkBoxSelectedIds" listKey="id"
									cssClass="check_all" listValue="categoryName"
									id="schoolTermchkBoxSelectedIds" disabled="true"/>
							</div>
						</div>
					</div>
				</s:if>
				<div id="feeLoad"></div>
				<s:if
					test="%{classFeeTypeList != null || !classFeeTypeList.isEmpty && schoolTermsList!= null || !schoolTermsList.isEmpty}">
					<div class="table-scrollable">
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2">
							<thead>
								<tr>
									<th>Fee Particulars</th>
									<s:if
										test="%{schoolTermsList!= null || !schoolTermsList.isEmpty}">
										<s:iterator status="term" value="schoolTermsList">
											<th><s:property value="termName" /></th>
										</s:iterator>
									</s:if>
									<s:else>
										<th><s:property value="schoolFeeSetting.settingName" />
										</th>
									</s:else>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="classFeeTypeList">
									<tr>
										<td><span id='<s:property value='feeTypeId'/>'
											class='feeTypeId'></span> <input type="hidden"
											name="feeTypeId" value="<s:property value='feeTypeId'/>">
											<div class="input-small ">
												<s:property value="feeType" />
											</div></td>
										<s:iterator value="schoolTermsList">
											<td><s:textfield name="feeAmount" value=""
													id="%{id}F%{feeTypeId}"
													cssClass="numeric form-control feeAmount input-small"
													maxlength="6"></s:textfield></td>
										</s:iterator>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</s:if>
				<s:if test="%{classList.size > 0}">
				<p>
					<span class="label label-danger">NOTE :</span> Select the classes
					whomever having the same fee.
				</p>
				<s:if test="%{classList!= null || !classList.isEmpty}">
						<h4 class="bold pageTitle">Select Applicable Classes :</h4>
						<div class="form-group">
							<div class="col-md-12">
								<div class="checkbox-list">
									<s:checkboxlist list="tempList2" theme="ems"
										name="chkBoxSelectedIds" listKey="id" listValue="className"
										cssClass="" id="chkBoxSelectedIds" disabled="true" />
									<s:checkboxlist list="classList" theme="ems"
										name="chkBoxSelectedIds" listKey="id" listValue="className"
										cssClass="" id="chkBoxSelectedIds" />
								</div>
							</div>
						</div>
					</s:if>
				</s:if>
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-3 col-md-12">
							<sj:submit value="Submit" id="submitButtonMainContent"
								targets="feeSettingsContent" indicator="indicator"
								cssClass="submitBt btn blue" formIds="addClassFee"
								onBeforeTopics="classFeeFormValidation" />
							<sj:submit value="Submit" id="submitButtonClassFee"
								targets="admissionSettingsDiv" indicator="indicator"
								cssClass="submitBt btn blue" cssStyle="display:none;"
								formIds="addClassFee" onBeforeTopics="classFeeFormValidation" />
							<s:url id="doCancelClassFee" action="ajaxDoFeeCategory"
								includeParams="all" escapeAmp="false">
								<s:param name="anyId" value="%{anyTitle}"></s:param>
							</s:url>
							<sj:a href="%{doCancelClassFee}" id="cancelMainContent"
								cssClass="btn default" targets="feeSettingsContent">Cancel</sj:a>
							<sj:a href="%{doCancelClassFee}" id="cancelAdmissionClassFee"
								cssStyle="display:none;" cssClass="btn default"
								targets="admissionSettingsDiv">Cancel</sj:a>
						</div>
					</div>
				</div>
			</div>
		</s:form>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('classFeeFormValidation');
	if (getUrlVars()["target"] != "undefined") {
		if (getUrlVars()["target"] == "ADMS.ajaxify%20ADST" || getUrlVars()["target"] == "ADMS.ajaxify ADST") {
			$('input#submitButtonMainContent').hide();
			$('input#submitButtonClassFee').show();
			$('a#cancelMainContent').hide();
			$('a#cancelAdmissionClassFee').show();
		} else {
			$('input#submitButtonMainContent').show();
			$('input#submitButtonClassFee').hide();
			$('a#cancelMainContent').show();
			$('a#cancelAdmissionClassFee').hide();
		}
	} else {
		$('input#submitButtonMainContent').show();
		$('input#submitButtonClassFee').hide();
		$('a#cancelMainContent').show();
		$('a#cancelAdmissionClassFee').hide();
	}
	$("input:checkbox, input:radio").uniform();
	$('.numeric').numeric();
	var classId = $('#test').val();
	var categoryId = $('#schoolcategoryId').val();
	var feeSettingId = $('#feeSettingId').val();
	var feeURL = jQuery.url.getChatURL("/schoolfee/ajaxEditClassFeeDetails.do?classId="+ classId + "&categoryId=" + categoryId);
	$.ajax( {
		url : feeURL,
		cache : false,
		dataType : 'json',
		success : function(response) {
			if (response.data) {
				var data = response.data;
				var feeTypeId;
				var schoolTermId;
				var feeAmount;
				var feeId;
				var studentPaymentId;
				var inputObj;
				if (data.length >= 1) {
					for ( var i = 0; i < data.length; i++) {
						feeTypeId = data[i].feeTypeId;
						schoolTermId = data[i].termId;
						feeAmount = data[i].amount;
						feeId = data[i].feeId;
						studentPaymentId = data[i].studentPaymentId;
						inputObj = $('input#' + schoolTermId + 'F' + feeTypeId);
						if (inputObj) {
							inputObj.val(feeAmount);
							inputObj.attr('id',schoolTermId + 'F'+ feeTypeId+ 'F'+ feeId);
							if (studentPaymentId != 0) {
								inputObj.attr('disabled',true);
							}
						}
					}
				}
			}
		}
	});
	$.subscribe('classFeeFormValidation',function(event, data) {
	var fieldErrorString = '';
	var feeTypeId = '';
	var schoolTermId = '';
	var feeAmount = '';
	var jsonObj = [];
	var objIds;
	var allids;
	var categoryId = $('#schoolcategoryId').val();
	$('input[name="feeAmount"]').each(function() {
		feeAmount = $(this).val();
		if (!isNonEmpty(feeAmount)) {
			feeAmount = "0";
		}
		objIds = $(this).attr('id');
		if (isNonEmpty(objIds)) {
			allids = objIds.split('F');
			if (allids.length > 2) {
				jsonObj.push( {
						"feeId" : allids[2],
						"feeTypeId" : allids[1],
						"schoolTermId" : allids[0],
						"feeAmount" : feeAmount
				});
			} else {
				jsonObj.push( {
					"feeId" : "0",
					"feeTypeId" : allids[1],
					"schoolTermId" : allids[0],
					"feeAmount" : feeAmount
				});
			}
		}
		$('#submitButtonMainContent').val('Saving...');
		$('#submitButtonClassFee').val('Saving...');
	});
	//$('.tempString').val(JSON.stringify(jsonObj));
		var classIds = [];
		classIds.push($('#test').val());
		$('input[name=chkBoxSelectedIds]').each(function() {
			if ($(this).is(':checked')) {
				classIds.push($(this).val());
			}
		});
		var categoryIds = [];
		$('input[name=schoolTermchkBoxSelectedIds]').each(function() {
			if ($(this).is(':checked')) {
				if (isNonEmpty($(this).val())) {		
					
					categoryIds.push($(this).val());
				}
			}
		});
		if (isNonEmpty($('#schoolcategoryId').val())) {
			categoryIds.push($('#schoolcategoryId').val());
		}
		//	var categoryIds=[];
		//$.each(categoryIds , function(i, val) { 
		//alert(categoryIds[i]);
		//});
		var jsono = {
			"chkBoxSelectedIds" : classIds,
			"schoolTermchkBoxSelectedIds" : categoryIds,
			"data" : jsonObj
		}
		if (jsonObj.length > 0 && isNonEmpty(categoryId)) {
			$('input[name=tempString]').val(JSON.stringify(jsono));
			$('input[name=tempId1]').val(categoryId);
			/*var url = jQuery.url.getChatURL("/schoolfee/ajaxAddSchoolFee.do");
			$.ajax( {
				url : url,
				cache : false,
				data : "tempString=" + JSON.stringify(jsono)+"&tempId1="+categoryId,
				success : function(response) {
					$('#mainContentDiv').html(response);
				}
			});*/
		} else {
			event.originalEvent.options.submit = false;
			alert('Please create fee term');
		}
	});
});

function getUrlVars() {
	var vars = [], hash;
	var hashes = window.location.href.slice(
			window.location.href.indexOf('#') + 1).split('&');
	for ( var i = 0; i < hashes.length; i++) {
		hash = hashes[i].split('=');
		vars.push(hash[0]);
		vars[hash[0]] = hash[1];
	}
	return vars;
}
</script>