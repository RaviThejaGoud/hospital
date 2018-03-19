<%@ include file="/common/taglibs.jsp"%>
<div role="progressbar" class="progress progress-striped" id="bar">
	<div class="progress-bar progress-bar-success" style="width: 63%;">
	</div>
</div>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="admissionsParticularsDetails">
<s:form id="addAdmissionFeeParticularsFM" action="ajaxGetAdmissionFeeTerms" theme="simple" name="myParticularsForm" namespace="/schoolfee" cssClass="form-horizontal">
<s:hidden name="tempString" cssClass="admissionsParticularsData"></s:hidden>
		<h4 class="pageTitle bold">
			Fee particulars 
		</h4>
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						We can create new particulars, remove the existing particulars or we can use the same fee particulars for selected academic year.
					</li>
					<li>
						You can add or update the Committed Fee options to the particular those who has not paid the fee by any students.
					</li>
				</ul>
			</div>
		</div>
		<div data-target="feeTypesCont">
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>Select Fee Setting Type :
				</label>
				<div class="col-md-3">
					<s:select list="objectList" listKey="id" listValue="settingName"
						id="settingFee" name="tempId" theme="simple"
						cssClass="form-control " headerKey="null"
						label="Select Fee Setting Type"
						onchange="javascript:getAjaxGetFeeSettingParticular(this.value);"
						headerValue="-Select Fee Setting Type-">
					</s:select>
				</div>
			</div>
		</div>
		<div id="allFeeSettingParticular"></div>
	</s:form>	
	</div>	
<script type="text/javascript">
$(document).ready(function() {
	$('div#admissionSettingDiv ul > li').removeClass('active');
	$('div#admissionSettingDiv ul > li#AdmissionSettings3').nextAll().removeClass('active');
	$('div#admissionSettingDiv ul > li#AdmissionSettings4').prevAll().addClass('done');	
	$("li#AdmissionSettings4").addClass('active');
	var settingFeeId=$('#settingFee').val();
	if(isNonEmpty(settingFeeId)){
		getAjaxGetFeeSettingParticular(settingFeeId);
	}
	});
	function getAjaxGetFeeSettingParticular(feeSettingId) {
	var tempId=null;
	var tempString="feeParticulars";
		if(isNonEmpty(feeSettingId)){
			var pars = "tempId=" + feeSettingId +"&tempString="+tempString;
		$("#allFeeSettingParticular").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/schoolfee/ajaxSchoolFeeSettingParticular.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#allFeeSettingParticular").html(html);
			}
		});
		}
	}
	</script>
	
	
	