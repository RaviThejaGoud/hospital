<%@ include file="/common/taglibs.jsp"%>
<div role="progressbar" class="progress progress-striped" id="bar">
	<div class="progress-bar progress-bar-success" style="width: 100%;"></div>
</div>
<div id="stepClassFee" class="tab-content">
	<s:if test="%{schoolCategoriesList!= null || !schoolCategoriesList.isEmpty}">
		<span id="anyId" class="<s:property value='anyId'/>"></span>
		<s:form id="addSchoolWideBooks" action="#" method="post" cssClass="form-horizontal" theme="simple">
			<jsp:include page="/common/messages.jsp" />
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							Select Fee Setting Type :
						</label>
						<div class="col-md-6">
							<s:select list="objectList" listKey="id" listValue="settingName" id="settingFee" name="tempId" theme="simple"
								cssClass="required form-control input-medium" headerKey="null" label="Select Fee Setting Type"
								onchange="javascript:getAjaxFeeSettingCateogy(this.value);" headerValue="-Select Fee Setting Type-" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							Select Category Name :
						</label>
						<div class="col-md-6">
							<s:select list="schoolCategoriesList" listKey="id" cssClass="required form-control input-medium"
								listValue="categoryName" id="categoriesCont" onchange="javascript:getAjaxDoCategoryFee(this.value);"
								name="tempId2" theme="simple" headerKey="null" headerValue="-Select Category-">
							</s:select>
						</div>
					</div>
				</div>
			</div>
			<div>
			</div>
		</s:form>
		<div id="allClasses"></div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no category.
		</div>
	</s:else>
	<s:if test="%{#session.admissionAcademicYearId != null && #session.admissionAcademicYearId != empty}">
		<div align="right">
			<s:url id="doViewAdmissionsFeetermsId" namespace="/admin" action="ajaxGetOnlineAdmissions" includeParams="all" escapeAmp="false"> 
				<s:param name="anyTitle">finishAdmissionSettings</s:param>
			</s:url>
			<sj:a href="%{doViewAdmissionsFeetermsId}" cssClass="btn green" indicator="indicator" targets="mainContentDiv">Finish</sj:a>
			<s:url id="doViewAdmissionsFeeterms" action="ajaxViewAdmissionFeeTerms" includeParams="all" escapeAmp="false"> </s:url>
			<sj:a href="%{doViewAdmissionsFeeterms}" cssClass="btn default" indicator="indicator" targets="admissionSettingsDiv">
			<i class="fa fa-mail-reply"></i> Back</sj:a>
		</div>
		<script type="text/javascript">
			$(document).ready(function() {
				$('div#BreadNavigation ul > li').removeClass('completedTaskTab');
				$('div#BreadNavigation ul > li#AdmissionSettings6').nextAll().removeClass('currentTaskTab selected span completedTaskTab');
				$('div#BreadNavigation ul > li#AdmissionSettings6').prevAll().addClass('completedTaskTab');
				//$("li#AdmissionSettings5").removeClass('currentTaskTab selected span');
				//$("li#AdmissionSettings5").addClass('completedTaskTab');
				$("li#AdmissionSettings6").addClass('selected currentTaskTab');
			});
		</script>
	</s:if>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	if (getUrlVars()["target"] != "undefined") {
		if(!(getUrlVars()["target"] == "ADMS.ajaxify%20ADST" || getUrlVars()["target"] == "ADMS.ajaxify ADST")){
			$('div.progress-striped').remove();
			$('div#portletBlueDiv').remove();
		}
	}
	$("li#AdmissionSettings5").removeClass('active');
	$("li#AdmissionSettings5").addClass('done');
	$("li#AdmissionSettings6").addClass('active');
	changePageTitle("Class Fee");
	$('.blockHeader h2').html('Manage Academics');
	var categoryId = $('#categoriesCont').val();
	if (isNonEmpty(categoryId)) {
		getAjaxDoCategoryFee(categoryId);
	}
});
function getUrlVars() {
	var vars = [], hash;
	var hashes = window.location.href.slice(window.location.href.indexOf('#') + 1).split('&');
	for ( var i = 0; i < hashes.length; i++) {
		hash = hashes[i].split('=');
		vars.push(hash[0]);
		vars[hash[0]] = hash[1];
	}
	return vars;
}
$.subscribe('doDisplayFee', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
function getAjaxFeeSettingCateogy() {
	var catgId = $("select#categoriesCont option:eq(1)").attr("selected","selected");
	if (isNonEmpty(catgId.val())) {
		getAjaxDoCategoryFee(catgId.val());
	}
}
function getAjaxDoCategoryFee(categoryId) {
	var tempId = null;
	var feeSettingId = $("#settingFee").val();
	//alert(feeSettingId);
	if (isNonEmpty(categoryId)) {
		var pars = "tempId2=" + categoryId + "&tempId=" + feeSettingId;
		$("#allClasses").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/schoolfee/ajaxAdminGetCategoryFee.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#allClasses").html(html);
			}
		});
	}
}
</script>