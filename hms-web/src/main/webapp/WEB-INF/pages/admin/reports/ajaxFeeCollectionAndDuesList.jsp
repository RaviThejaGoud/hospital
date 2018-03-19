<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div id="feeCollection">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>
						<span class="hidden-481">Fee Collections</span>
					</div>
				</div>
				<div class="portlet-body">
					<s:if test="%{(Title != null && !Title.isEmpty())}">
						<s:form action="ajaxCommonFeeCollectionAndDues" id="feeCollections" target="_new"
							cssClass="form-horizontal" theme="simple" namespace="/reports"
							onsubmit="javascript:return reportsType();">
						    <input type="hidden" name="pdfId" value="pdf" />
						    <input type="hidden" name="title" value="<s:property value='plTitle'/>" />
						    <input type="hidden" name="tempId" value="<s:property value='tempId'/>" />
						    <s:hidden name="anyId" cssClass="anyId" value="PDF"></s:hidden>
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
											<sj:submit   type="submit" value="Generate Pdf"
												cssClass="btn blue" title="generate report" />
										</div>
									</div>
								</div>
							</div>
						</s:form>
					</s:if>
				</div>
			</div>
		</div>
		<div id="outStanding">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>
						<span class="OutStandingReports">Out Standing Fee</span>
					</div>
				</div>
				<div class="portlet-body">
					<s:if test="%{(Title != null && !Title.isEmpty())}">
						<s:form action="ajaxCommonFeeCollectionAndDues" theme="simple" namespace="/reports" id="feeDefaulters" target="_new" onsubmit="javascript:return reportsType();" cssClass="form-horizontal">
							<input type="hidden" name="pdfId" value="pdf" />
							<input type="hidden" name="title" value="<s:property value='plTitle'/>" />
							<input type="hidden" name="tempId" value="<s:property value='tempId'/>" />
							<input type="hidden" name="selectedId" id="classNameIds"/>
							 <div class="form-body">
									<div id="checkStaffOrNot">
										<div class="form-group">
											<label class="control-label col-md-2">
												<span class="required">*</span>Fee Defaulters :
											</label>
											<div class="col-md-10">
												<s:radio list="collectionAndFeeDuesList" name="queryString"
													id="queryString" theme="ems" value="defaulters" cssClass="queryStringDiv"
													onclick="ajaxFeeDeafaulters(this.value);"></s:radio>
											</div>
											<div id="schoolClasslist"></div>
										</div>
									</div>
									<div id="overalAndToday">
										<div class="form-actions fluid">
											<div class="col-md-offset-2 col-md-9">
												<sj:submit   type="submit" value="Generate Pdf"
													cssClass="btn blue" title="generate report" />
											</div>
										</div>
									</div>
								</div>
						</s:form>
					</s:if>
				</div>
			</div>
		</div>
		<div id="paidAndUnpaid">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>
						<span class="PaidAndUnPaidReports">Paid & Un-Paid Fee Details</span>
					</div>
				</div>
				<div class="portlet-body">
				<div id="allFeeReports">
					<s:if test="%{(Title != null && !Title.isEmpty())}">
					<s:form action="ajaxCommonFeeCollectionAndDues" theme="simple" namespace="/reports" target="_new" onsubmit="javascript:return reportsTypes();" cssClass="form-horizontal">
							<input type="hidden" name="pdfId" value="pdf" />
							<input type="hidden" name="title" value="<s:property value='plTitle'/>" />
							<input type="hidden" name="tempId" value="<s:property value='tempId'/>" />
							<input type="hidden" name="selectedId" id="classNameIds"/>
							<input type="hidden" name="queryString" value="PaidAndUnPaidDetails" />
							<div class="form-body">
								<div  id="checkStaffOrNot">
										<s:if
											test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
											<div class="form-group">
												<label class="conLable col-md-3 control-label">
													<span class="required">*</span> Available Terms :
												</label>
												<div class="col-md-12">
													<div class="checkbox-list">
														<label class="checkbox-inline">
															<input type="checkbox" name="" value=""
																onClick="checkAllTermsForReports()"
																class="checkbox allTerms">
															All Terms
														</label>
													</div>
													<s:checkboxlist name="chkBoxClassSelectedIds"
														list="schoolTermsList" listKey="id" listValue="termName"
														theme="ems" cssClass="small" />
												</div>
											</div>
										</s:if>
										<s:else>
											<div class="alert alert-info">
												You have not created any terms.
											</div>
										</s:else>
										<div id="overalAndToday">
											<div class="form-actions fluid">
												<div class="col-md-offset-2 col-md-9">
													<sj:submit   type="submit small" value="Generate Pdf"
														cssClass="submitBt btn blue long" title="generate report" />
												</div>
											</div>
										</div>
									</div>
							</div>
						</s:form>
					</s:if>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready( function() {
$('input.queryStringDiv').parents('label').css({width:'300px'});
$("input:checkbox, input:radio:not('.toggle')").uniform(); 
		var title ='';
			$('span.hidden-481').html($('span.hidden-481').text().trim() + "-->" + $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			if(!isNonEmpty(title)){//this is used to parent and student logns
				 $('span.hidden-481').html($('.navbar-nav li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim())
			 title = $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim();
			}
			changePageTitle(title);
		});
function reportsTypes(queryString) {
	if ($("input[name=chkBoxClassSelectedIds]:checked").length == 0) {
		alert("Please select at least one Fee type");
		return false;
	}
}						
							
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
			alert("Please select at least one Class");
			return false;
		} 	
		else {
			generateReportsWithClassIds();
		}
	}
	if (feetype == "Term Wise") {
		if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one term");
			return false;
		}
	} else if (feetype != "To Day" && feetype != "Over All") {
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
		$('.anyId').val('PDF');
		return true;
	}
}
 
  function checkAllTermsForReports() {
		if ($(".allTerms").is(':checked')){
		    $("[name='chkBoxClassSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxClassSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	
 function checkAllClassesForReports() {
		if ($(".allClasses").is(':checked')){
		    $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	
	function allornone() {
		if ($(".allClasses").is(':checked')){
		    $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxSelectedIds']").each(function(){
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
function CollectionFeeDuesReprots(queryString) {
$('#outStanding *').attr('disabled', true);
$('#paidAndUnpaid *').attr('disabled', true);
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
		var url = jQuery.url
				.getChatURL("/admin/ajaxCollectionTermsByFeeDetails.do?tempId=<s:property value='tempId'/>");
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
	} else if (queryString == "To Day" || queryString == "Over All") {
		$('#overalAndToday').show();
		$('#schoolTermlist').hide();
		 return true;
		//document.myform2.submit();
		$("#schoolTermlist").html("");
	}
}

function ajaxGetStaffDeafaulters(staffValue, title) {
$('#feeCollection *').attr('disabled', true); 
$('#outStanding *').attr('disabled', true);
	$('b.removeStaff').removeAttr('style');
	var studentString1 = $("input[value='+staffValue+']").next('b');
	var studentString = $("input[value='+staffValue+']").next('b').html();
	if (isNonEmpty(studentString)) {
		var bolded = '<b class="removeStaff">' + studentString + '</b>'
		studentString1.html(bolded);
	}
	var pars = "anyTitle=" + staffValue + "&title=" + title;
	var url = jQuery.url.getChatURL("/admin/ajaxFeeCollectionAndDues.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#reportsList").html(html);
			if (staffValue == 'ROLE_STUDENT') {
				$("input#staff").attr("checked", false);
				$("input#sudent").attr("checked", true);
			} else {
				$("input#staff").attr("checked", true);
				$("input#sudent").attr("checked", false);
			}
		}
	});
}
function ajaxFeeDeafaulters(queryString) {
$('#feeCollection *').attr('disabled', true); 
$('#paidAndUnpaid *').attr('disabled', true);
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
			return true;
		//document.myform2.submit();
		$("#schoolTermlist").html("");
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
