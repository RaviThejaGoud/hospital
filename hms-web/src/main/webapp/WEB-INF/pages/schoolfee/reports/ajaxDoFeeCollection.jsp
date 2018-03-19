<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet-body">
			<div class="tab-content">
				<%@ include file="/common/messages.jsp"%>
				<s:form action="ajaxCommonFeeCollectionAndDues" id="feeCollections"
					cssClass="form-horizontal" theme="simple"
					namespace="/reports" onsubmit="javascript:return reportsType();">
					<input type="hidden" name="pdfId" value="pdf" />
					<input type="hidden" name="title"
						value="<s:property value='plTitle'/>" />
					<input type="hidden" name="tempId"
						value="<s:property value='tempId'/>" />
					<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
					<s:hidden name="anyTitle" id="schoolTermIds"></s:hidden>
					<s:hidden id="classNameIds" name="selectedId" />
					<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
					<div class="form-body">
						<div class="form-group">
							<label class="control-label col-md-2">
								<span class="required">*</span>Fee Collections :
							</label>
							<div class="col-md-10">
								<s:radio list="smsFeeReports" name="queryString" theme="ems"
									value="defaulters" id="queryString"
									onclick="javascript:CollectionFeeDuesReprots(this.value);"></s:radio>
							</div>
						</div>
						<div class="spavceDiv"></div>
						<div id="schoolTermlist"></div>
						<div id="overalAndToday">
							<div class="form-actions fluid">
								<div class="col-md-offset-2 col-md-9">
									<%-- <s:submit type="submit" value="Generate Pdf"
										cssClass="btn blue PDF generateReport" title="generate report" /> --%>
									<s:submit type="submit" value="Generate Excel"
										cssClass="btn blue Excel generateReport"
										title="generate excel" />
								</div>
							</div>
						</div>
					</div>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no classes.
						</div>
					</s:else>
				</s:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Fee Collection");
	$("input:checkbox, input:radio").uniform();
});
 

$('input.generateReport').click(function(){
	if($(this).hasClass('PDF')){
 		$('.anyId').val('PDF');
 	}else{
 		$('.anyId').val('Excel');
 	}
});
function reportsType(queryString) {
	var feetype = $('input[name=queryString]:checked').val();
	if ($("input[name=queryString]:checked").length == 0) {
		alert("Please select at least one fee type");
		return false;
	}
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (feetype == "Term Wise") {
		if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one term");
			return false;
		}
		if ($("input[name=chkBoxClassSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
		}
	} else if (feetype != "Today" && feetype != "Over All") {
		if (startDate == '' || endDate == '') {
			if (startDate == '' && endDate == '') {
				alert("Please select From and To date.");
				return false;
			} else if (startDate == '') {
				alert("Please select From date.");
				return false;
			} else {
				alert("Please select To date.");
				return false;
			}
		}
	} else {
		//$('.anyId').val('PDF');
		return true;
	}
}

function CollectionFeeDuesReprots(queryString) {
	$('b.removeHtml').removeAttr('style');
	var labelString = $(queryString).next('label');
	if (labelString) {
		var bolded = '<b class="removeHtml">' + queryString + '</b>'
		labelString.html(bolded);
	}
	var pars = "queryString=" + queryString;
	if ($("input[name=queryString]:checked").length == 0) {
		alert("Please select at least one fee type");
		return false;
	}
	if (queryString == "Term Wise") {
		$('#schoolTermlist').show();
		var url = jQuery.url.getChatURL("/admin/ajaxCollectionTermsByFeeDetails.do?tempId=<s:property value='tempId'/>");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#schoolTermlist").html(html);
			}
		});
	} else if (queryString == 'Days Between') {
		$('#schoolTermlist').show();
		var url = jQuery.url.getChatURL("/admin/ajaxCollectionDaysBetweenByFeeDetails.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#schoolTermlist").html(html);
			}
		});
	} else if (queryString == "Today" || queryString == "Over All") {
		$('#overalAndToday').show();
		$('#schoolTermlist').hide();
		return true;
		$("#schoolTermlist").html("");
	}
}
function generateReportsWithTermIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0 && $("input[name=chkBoxClassSelectedIds]:checked").length > 0) {
		var termIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedTermIds = '';
		if (termIds.length > 0) {
			selectedTermIds = '(';
			for ( var i = 0; i < termIds.length; i++) {
				selectedTermIds += termIds[i].value + ', ';
			}
			selectedTermIds += '0)';
		}
		$("#schoolTermIds").val(selectedTermIds);
		var classIds = $("input[name=chkBoxClassSelectedIds]:checked");
		var selectedClassIds = '';
		if (classIds.length > 0) {
			selectedClassIds = '(';
			for ( var i = 0; i < classIds.length; i++) {
				selectedClassIds += classIds[i].value + ', ';
			}
			selectedClassIds += '0)';
		}
		$("#classNameIds").val(selectedClassIds);
		return true;
	}
}
</script>