<%@ include file="/common/taglibs.jsp"%>
<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> <span class="hidden-481"> </span>
				</div>
			</div>
			<div class="portlet-body" id="studRegister">
				<div class="tab-content">
					<%@ include file="/common/messages.jsp"%>
					<s:form action="ajaxDownloadOtherFeeDetails" theme="simple"
						cssClass="form-horizontal" onsubmit="return generateClassIds();"
						id="classAndCommunity" method="post" namespace="/reports">
						<s:hidden name="reportType" id="reportType"></s:hidden>
						<s:hidden name="plTitle"></s:hidden>
						<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
						<s:hidden id="classNameIds" name="SelectedId" />
						
						<div class="form-body">
							<div class="form-group">
								<label class="control-label col-md-2">
									<span class="required">*</span>Fee Collections :
								</label>
								<div class="col-md-10">
									<s:radio list="#{'C':'Current Date','P':'Periodic','OL':'Over All'}" name="queryString" theme="ems"
										 id="queryString"
										onclick="javascript:CollectionOtherFeeReprots(this.value);" cssClass="radio"></s:radio>
								</div>
							</div>
							<div class="spavceDiv"></div>
							
							<div id="dateShow" style="display: none;">
							<div class="form-group">
								<label class="control-label col-md-2"> <span
									class="required">*</span>From Date :
								</label>
								<div class="col-md-5">
									<div data-date-format="mm/dd/yyyy"
										class="input-group input-medium date date-picker" data-date-end-date="+0d">
										<input type="text" id="startDate" readonly=""
											class="form-control required input-medium"
											onchange="dateValidation();" tabindex="3" name="startDate">
										<span class="input-group-btn">
											<button type="button" class="btn default">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block">(MM/DD/YYYY)</span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2"> <span
									class="required">*</span>To Date :
								</label>
								<div class="col-md-5">
									<div data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker" data-date-end-date="+0d">
										<input type="text" id="endDate" readonly=""
											class="form-control required input-medium"
											onchange="dateValidation();" tabindex="4" name="endDate">
										<span class="input-group-btn">
											<button type="button" class="btn default">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
									<span class="help-block">(MM/DD/YYYY)</span>
								</div>
							</div>
							</div>
							
							<s:if test='%{studyClassList.size >0}'>
								<div class="form-group">
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline"> <input
												type="checkbox" name="" value="" onClick="checkAllClasses()"
												class="checkbox allClasses"> All Classes
											</label>
										</div>
									</div>
								</div>
							</s:if>
							<s:if test='%{studyClassList.size >0}'>
								<div class="form-group">
									<label class="conLable col-md-3 control-label"> <span
										class="required">*</span> Classes With Students :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
												listKey="id" listValue="classAndSection" theme="ems"
												cssClass="small" />
										</div>
									</div>
								</div>
							</s:if>
							<s:if test='%{tempList2.size >0}'>
								<div class="form-group">
									<label class="conLable col-md-3 control-label"> <span
										class="required">*</span> Classes With Out Students :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<s:checkboxlist name="chkBoxNotStudents" list="tempList2"
												listKey="id" listValue="classAndSection" theme="ems"
												cssClass="small" disabled="true" />
										</div>
									</div>
								</div>
							</s:if>
							<s:if test='%{studyClassList.size == 0 && tempList2.size == 0}'>
								<div>
									<font style="color: red">You have not created any
										Classes. You can able to create Class & Section.</font>
								</div>
							</s:if>

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
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		FormAdvanced.init();
		FormComponents.init();
		$("input:checkbox, input:radio:not('.toggle')").uniform();
		$("#queryStringC").parent('span').addClass("checked");
		CollectionOtherFeeReprots("C");
		//$(".radio").parent('span').addClass("checked");
		var str = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
		if (isNonEmpty(str)) {
			$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim()+ "-->"+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
		} else {
			$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim())
		}
		
		var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
		changePageTitle(title);
		
		$("input[name=chkBoxSelectedIds]").click(function() {
			if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				$(".allClasses").parent('span').removeClass("checked");
				$(".allClasses").attr("checked", false);
			} else {
				$(".allClasses").parent('span').addClass("checked");
				$(".allClasses").attr("checked", true);
			}
		});
	});
	$('html, body').animate({
		scrollTop : $(document).height() - $(window).height()
	}, 10, function() {
		$(this).animate({
			scrollTop : 0
		}, 10);
	});
	
	function checkAllClasses() {
		if ($(".allClasses").is(':checked')) {
			$("[name='chkBoxSelectedIds']").each(function() {
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
		} else {
			$("[name='chkBoxSelectedIds']").each(function() {
				$(this).parent('span').removeClass('checked');
				$(this).removeAttr("checked");
			});
		}
	}
	var selectType =null;
	function CollectionOtherFeeReprots(queryString) {
		$("#reportType").val(queryString);
		selectType=queryString;
		if (queryString == "P") 
			$('#dateShow').show();
		 else
			$('#dateShow').hide();
	}
	function generateClassIds() {
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(selectType == "P"){
			if (!isNonEmpty(startDate) && !isNonEmpty(endDate)){
				alert("Please select start and end date");
				return false;
			}else if(!isNonEmpty(startDate)){
				alert("Please select start date");
				return false;
			}else if(!isNonEmpty(endDate)){
				alert("Please select end date");
				return false;
			} 
		}
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			if (classIds.length > 0) {
				selectedClassIds = '(';
				for (var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				selectedClassIds += '0)';
			}
			$("#classNameIds").val(selectedClassIds);
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
		}else {
			return false;
		}
	}
	
</script>