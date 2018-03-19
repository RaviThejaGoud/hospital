<%@ include file="/common/taglibs.jsp"%>
<div id="feeSettingsContent">
<div role="progressbar" class="progress progress-striped" id="bar">
	<div class="progress-bar progress-bar-success" style="width: 77%;">
	</div>
</div>
<jsp:include page="/common/messages.jsp"></jsp:include>
	<div class="col-md-12">
		<div class="col-md-10">
			<h4 class="pageTitle bold">
				Current fee terms
			</h4>
		</div>
		<div class="col-md-2">
			<s:url id="urlCreateSchoolTermsLink"
				action="ajaxDoAdmissionCreateSchoolTerms" escapeAmp="false"
				includeParams="all" namespace="/schoolfee">
				<s:param name="anyTitle">admissionTerms</s:param>
			</s:url>
			<sj:a id="createSchoolTermsLink" href="%{urlCreateSchoolTermsLink}"
				targets="admissionSettings5" cssClass="btn green">
				<i class="fa fa-plus"></i> Create Fee Term</sj:a>
		</div>
	</div>
	<div id="admissionSettings5">
			<div>
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE: </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								You can edit terms by click edit pen icon in each row at right
								side.
							</li>
							<li>
								We can create new Fee terms or update/ remove the existing Fee
								terms or we can use the existing fee terms for selected Academic
								year.
							</li>
						</ul>
					</div>
				</div>
				<div data-target="termsCont">
					<div class="form-group">
						<label class="control-label col-md-2">
							<span class="required">*</span>Select Fee Setting Type :
						</label>
						<div class="col-md-3">
							<s:select list="objectList" listKey="id" listValue="settingName"
								id="settingFeeTerm" name="tempId" theme="simple"
								cssClass="form-control" headerKey="null"
								label="Select Fee Setting Type"
								onchange="javascript:getAjaxGetFeeSettingTerms(this.value);"
								headerValue="-Select Fee Setting Type-">
							</s:select>
						</div>
					</div>
				</div>
				<div id="allFeeSettingTerms"></div>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
						<s:url id="doViwClassFeeSettings" action="ajaxDoGetClassFee"
							includeParams="all" escapeAmp="false" namespace="/schoolfee">
						</s:url>
						<sj:a href="%{doViwClassFeeSettings}" cssClass="submitBt btn blue"
							targets="admissionSettingsDiv" button="false"
							buttonIcon="ui-icon-plus">Next</sj:a>
						<s:url id="doViewFeeParticulars"
							action="ajaxGetAdmissionsFeeParticular" includeParams="all"
							escapeAmp="false" namespace="/schoolfee">
						</s:url>
						<sj:a href="%{doViewFeeParticulars}" cssClass="btn default"
							indicator="indicator" targets="admissionSettingsDiv">
							<i class="fa fa-mail-reply"></i> Back</sj:a>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function() {
	$('div#admissionSettingDiv ul > li').removeClass('active');
	$('div#admissionSettingDiv ul > li#AdmissionSettings4').nextAll().removeClass('active');
	$('div#admissionSettingDiv ul > li#AdmissionSettings5').prevAll().addClass('done');	
	$("li#AdmissionSettings5").addClass('active');
	
	var settingFeeTermId=$('#settingFeeTerm').val();
	if(isNonEmpty(settingFeeTermId)){
		getAjaxGetFeeSettingTerms(settingFeeTermId);
	}
	});
	function getAjaxGetFeeSettingTerms(settingFeeTermId) {
	var tempId=null;
		if(isNonEmpty(settingFeeTermId)){
			var pars = "tempId=" + settingFeeTermId;
		$("#allFeeSettingTerms").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/schoolfee/ajaxSchoolFeeSettingTerms.do");
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
	</script>