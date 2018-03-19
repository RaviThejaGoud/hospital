<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet-body">
			<div class="tab-content">
				<%@ include file="/common/messages.jsp"%>
				<s:form action="ajaxCommonFeeCollectionAndDues" theme="simple"
					namespace="/reports" id="feeDefaulters" target="_new"
					onsubmit="javascript:return reportsType();"
					cssClass="form-horizontal">
					<input type="hidden" name="pdfId" value="pdf" />
					<input type="hidden" name="title"
						value="<s:property value='plTitle'/>" />
					<input type="hidden" name="tempId"
						value="<s:property value='tempId'/>" />
					<input type="hidden" name="selectedId" id="classNameIds" />
					<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
					<div class="form-body">
						<div id="checkStaffOrNot">
							<div class="form-group">
								<label class="control-label col-md-2">
									<span class="required">*</span>Fee Defaulters :
								</label>
								<div class="col-md-10">
									<s:radio list="collectionAndFeeDuesList" name="queryString"
										id="queryString" theme="ems" value="defaulters"
										cssClass="queryStringDiv"
										onclick="ajaxFeeDeafaulters(this.value);"></s:radio>
								</div>
								<div id="schoolClasslist"></div>
							</div>
						</div>
						<div id="overalAndToday">
							<div class="form-actions fluid">
								<div class="col-md-offset-2 col-md-9">
									<s:submit type="submit" value="Generate Pdf"
										cssClass="btn blue PDF generateReport" title="generate report" />
									<s:submit type="submit" value="Generate Excel"
										cssClass="btn blue Excel generateReport"
										title="generate excel" />
								</div>
							</div>
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready( function() {
	changePageTitle("Fee Defaulters");
	$("input:checkbox, input:radio").uniform();
	$('input.queryStringDiv').parents('label').css({width:'300px'});
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
		alert("Please select at least one Fee type");
		return false;
	}
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (feetype == "Class Wise Defaulters") {
	
		if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one class");
			return false;
		} 	
		else {
			generateReportsWithClassIds();
		}
	}else{
		$("[name='chkBoxClassSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
	}
	
}
 
function generateReportsWithClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
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
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one Class");
		return false;
	} else {
		return true;
	}
}

function ajaxFeeDeafaulters(queryString) {
	var pars = "queryString=" + queryString;
	if (queryString == "Class Wise Defaulters") {
		$('#schoolClasslist').show();
		var url = jQuery.url.getChatURL("/admin/ajaxDoClassWiseDefaulters.do?tempId=<s:property value='tempId'/>");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#schoolClasslist").html(html);
			}
		});
	} else if (queryString == "Total Defaulters") {
		$('#schoolClasslist').hide();
		$('#overalAndToday').show();
		$("#schoolTermlist").html("");
		$("#schoolClasslist").html("");
		$("#classNameIds").val("");
			return true;
		//document.myform2.submit();
		
		
	} else if (queryString == 'S') {
		alert("Please select at least one value.");
		return false;
	}
}
 
$("input[name=chkBoxClassSelectedIds]").click(function() {
	if ($("input[name=chkBoxClassSelectedIds]:unchecked").length > 0) {
	   $(".allTerms").parent('span').removeClass("checked");
		$(".allTerms").attr("checked", false);
	} else {
	    $(".allTerms").parent('span').addClass("checked");
		$(".allTerms").attr("checked", true);
	}
});
</script>