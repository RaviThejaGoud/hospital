<%@ include file="/common/taglibs.jsp"%>
<h4 class="pageTitle bold form-section">
	Schools Information is for &nbsp;&nbsp;
	<strong style="color: Green;"><s:property value="section" />
	</strong>
</h4>
<s:form action="ajaxGovGenerateExcelReport" theme="simple"
	cssClass="form-horizontal" namespace="/govt" id="schoolsInformation"
	method="post" onsubmit="return generateGovtReport();">
	<div class="form-body">
		<s:hidden name="anyTitle" cssClass="anyTitle" value=""></s:hidden>
		<div class="row">
			<s:if test='%{user.isSEO == "Y"}'>
				<span id="RoleName" class="SEO"></span>
				<div class="col-md-6" id="districtList">
					<div class="form-group">
						<label class="col-md-4 control-label">
							<span class="required">*</span>Select District :
						</label>
						<div class="col-md-8">
							<s:select id="districtId" list="objectList"
								cssClass="required form-control input-medium" listKey="id"
								listValue="districtName" headerKey="" theme="simple"
								headerValue="- Select -" name="selectedId"
								onchange="javascript:getMandals(this);" />
						</div>
					</div>
				</div>
			</s:if>
		</div>
		<div class="row">
			<s:if test='%{user.isDEO == "Y"}'>
				<span id="RoleName" class="DEO"></span>
				<div class="col-md-6" id="mandalsList">
					<div class="form-group">
						<label class="col-md-4 control-label">
							<span class="required">*</span>Select Mandal :
						</label>
						<div class="col-md-8">
							<s:select id="mandalId" list="objectList"
								cssClass="required form-control input-medium" listKey="id"
								listValue="mandalName" headerKey="" headerValue="- Select -"
								name="anyId" theme="simple"
								onchange="javascript:getVillages(this);" />
						</div>
					</div>
					<div id="villageDiv">
					</div>
				</div>
			</s:if>
		</div>
		<div class="row">
			<s:if test='%{user.isMEO == "Y"}'>
				<span id="RoleName" class="MEO"></span>
				<div class="col-md-6" id="mandalsList">
					<div class="form-group">
						<label class="col-md-4 control-label">
							<span class="required">*</span>Select Village :
						</label>
						<div class="col-md-8">
							<s:select id="villageId" list="objectList"
								cssClass="required form-control input-medium" listKey="id"
								listValue="villageName" headerKey="" headerValue="- Select -"
								name="section" theme="simple" />
						</div>
					</div>
				</div>
			</s:if>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span> From Date :
					</label>
					<div class="col-md-8">
						<div class="input-group input-medium date date-picker">
							<input type="text" readonly="readonly"
								class="required form-control" id="startDate"
								name="academicYear.startDate" onchange="verifyTime();">
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
						<span class="required">*</span> To Date :
					</label>
					<div class="col-md-8">
						<div class="input-group input-medium date date-picker">
							<input type="text" readonly="readonly"
								class="required form-control" id="endDate"
								name="academicYear.endDate" onchange="verifyTime();">
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
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<s:if
					test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
					<s:submit type="submit small" value="PDF"
						cssClass="submitBt btn blue" title="generate report"
						onClick="GetSchoolsPDFInformation()" cssStyle="cursor:pointer">
					</s:submit>
					<s:submit type="submit small" value="Excel"
						cssClass="submit btn green" title="generate report"
						onClick="GetSchoolsInformation()" cssStyle="cursor:pointer">
					</s:submit>
				</s:if>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
var exportId = "Y";
function GetSchoolsPDFInformation() {
	$('.anyTitle').val('PDF');
}
function GetSchoolsInformation() {
	$('.anyTitle').val('Excel');
}
function generateGovtReport() {
	if (($("input#startDate").val() == "") || ($("input#endDate").val() == "")) {
		alert("Please select dates to generate report.");
		return false;
	} else {
		return true;
	}
}
$(document).ready(function() {
	FormComponents.init();
});
function verifyTime(date0, date1) {
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (isNonEmpty(endDate) && isNonEmpty(startDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		if (endDate <= startDate) {
			$('#endDate').val('');
			alert("End date should be after start date.");
			return false
		} else {
			return true;
		}
	}
}
function getMandals(selectBox) {
	var organizationId = $("select#districtId").val();
	var url = jQuery.url.getChatURL("/govt/ajaxGovtMandalsByDistrict.do");
	if (organizationId.length == 0) {
		alert("!Oops select District.");
		$("#mandalDiv").empty();
		$("#villageDiv").empty();
	} else {
		$("#mandalDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "anyId=" + organizationId + "& section=" + exportId;
		$
				.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#mandalDiv").html(html);

						if ($("div#mandalDiv:contains('<span class='required'>*</span>')").length > 0) {
							if ($("#mandalDiv").find("span").length > 0) {
								$("#mandalDiv").find("span").remove();
							}
						}

						document.getElementById('mandalDiv').style.display = "block";
						$("#mandalDiv").append();
					}
				});
	}
}

function getVillages(selectBox) {
	var organizationId = $("select#mandalId").val();
	var url = jQuery.url.getChatURL("/govt/ajaxGovtVillagesByMandal.do");
	if (organizationId.length == 0) {
		alert("!Oops select Mandal.");
	} else {
		$("#villageDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "eventId=" + organizationId + "& section=" + exportId;
		;
		$
				.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#villageDiv").html(html);

						if ($("div#villageDiv:contains('<span class='required'>*</span>')").length > 0) {
							if ($("#villageDiv").find("span").length > 0) {
								$("#villageDiv").find("span").remove();
							}
						}
						document.getElementById('villageDiv').style.display = "block";
						$("div.grid_3").removeClass("labelRight");
					}
				});
	}

}
</script>