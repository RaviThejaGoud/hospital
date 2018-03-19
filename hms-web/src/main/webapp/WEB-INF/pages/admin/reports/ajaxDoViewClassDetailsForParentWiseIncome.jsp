<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> <span class="hidden-481"></span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<s:form action="" theme="simple" cssClass="form-horizontal" 	name="buttonName" namespace="" onsubmit="return false;"		id="classAndTodate" method="post">
						<s:hidden name="tempString"></s:hidden>
						<s:hidden id="plTitle" name="plTitle"></s:hidden>
						<s:hidden id="classNameIds" name="selectedId" />
						<s:hidden id="incomeRangeIds" name="selectedIncomeId"></s:hidden>
						<s:hidden name="anyId" cssClass="anyId"></s:hidden>
						<span id="plTitle" class="<s:property value='plTitle'/>"></span>

						<s:if test='%{studyClassList !=null && !studyClassList.isEmpty()}'>
							<div class="form-body">
								<div class="grid_10">
									<s:if test='%{studyClassList.size >1}'>
										<div class="form-group">
											<div class="col-md-12">
												<div class="checkbox-list">
													<label class="checkbox-inline"> <input
														type="checkbox" name="" value=""
														onClick="checkAllClasses()" class="checkbox allClasses">
														All Class & Sections
													</label>
												</div>
											</div>
										</div>
									</s:if>
									<div class="form-group">
										<label class="conLable col-md-3 control-label"> <span
											class="required">*</span>Class With Students :
										</label>
										<div class="col-md-12">
											<div class="checkbox-list">
												<s:checkboxlist name="chkBoxSelectedIds"
													list="studyClassList" listKey="id"
													listValue="classAndSection" theme="ems" cssClass="small" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</s:if>
						<s:if test='%{parentIncomeRangesList !=null && !parentIncomeRangesList.isEmpty()}'>
							<div class="form-body">
								<div class="grid_10">
									<s:if test='%{parentIncomeRangesList.size >1}'>
										<div class="form-group">
											<div class="col-md-12">
												<div class="checkbox-list">
													<label class="checkbox-inline"> <input
														type="checkbox" name="" value=""
														onClick="checkAllIncomeRanges()" class="checkbox allRanges">
														All Income Ranges
													</label>
												</div>
											</div>
										</div>
									</s:if>
									<div class="form-group">
										<label class="conLable col-md-3 control-label"> <span
											class="required">*</span>Select Income Range :
										</label><br><br>
										<div class="col-md-3" style="margin-top: -15px;">
											<div class="checkbox-list" style="margin-left: 10px;">
												<s:checkboxlist name="chkBoxSelectedRangeIds"
													list="parentIncomeRangesList" listKey="id"
													listValue="rangeValues" theme="ems" cssClass="small" />
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<s:submit type="submit small" value="Submit"
											cssClass="submitBt btn blue long Excel generateReport"
											title="Generate Chart"
											onclick="javaScript:generateClassIds();" />
									</div>
								</div>
							</div>
						</s:if>
				</div>
				</s:form>
			</div>
		</div>
	</div>
	<div id="parentIncomeChart"></div>
</div>
<div id="schoolTermlist"></div>
<script type="text/javascript">
	$(document).ready(function() {
		FormComponents.init();
		$(".allClasses").attr("checked", true);
		checkAllClasses();
		$("div#parentIncomeChart").hide();
		var title = '';
		$('span.hidden-481').html(
			$('.page-sidebar-menu li.active').find(
			'li.active').children('a').children(
			'span.title').text().trim()
			+ "-->"
			+ $('.page-sidebar-menu li.active')
			.find('li.active').find(
			'ul.sub-menu').find(
			   'li.active').children(
				'a').text().trim())
			title = $('.page-sidebar-menu li.active').find(
			'li.active').find('ul.sub-menu').find(
			'li.active').children('a').text().trim();
			if (!isNonEmpty(title)) {
				$('span.hidden-481').html(
				$('.navbar-nav li.active').children('a')
				.children('span.title').text()
				.trim()
				+ "-->"
				+ $('.navbar-nav li.active').find(
				'li.active').find(
				'ul.dropdown-menu').find(
				'li.active').children('a')
				.text().trim())
				title = $('.navbar-nav li.active')
				   .find('li.active').find('ul.dropdown-menu')
					.find('li.active').children('a').text().trim();
			}
	 changePageTitle(title);
	$("input:checkbox").uniform();
	$("input[name=chkBoxSelectedIds]").click(function()
			{
				if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
						$(".allClasses").parent('span').removeClass("checked");
						$(".allClasses").attr("checked", false);
					} else {
						$(".allClasses").parent('span').addClass("checked");
						$(".allClasses").attr("checked", true);
						}
					});
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
	$("input[name=chkBoxSelectedRangeIds]").click(function(){
		if ($("input[name=chkBoxSelectedRangeIds]:unchecked").length > 0) {
				$(".allRanges").parent('span').removeClass("checked");
				$(".allRanges").attr("checked", false);
			} else {
				$(".allRanges").parent('span').addClass("checked");
				$(".allRanges").attr("checked", true);
				}
			});
	function checkAllIncomeRanges() {
		if ($(".allRanges").is(':checked')) {
			$("[name='chkBoxSelectedRangeIds']").each(function() {
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
		} else {
			$("[name='chkBoxSelectedRangeIds']").each(function() {
				$(this).parent('span').removeClass('checked');
				$(this).removeAttr("checked");
			});
		}
	}

	function generateClassIds() {
		if($("input[name=chkBoxSelectedIds]:checked").length == 0 && $("input[name=chkBoxSelectedRangeIds]:checked").length == 0){
			alert("Please select at least one Class and one income range");
			return false;
		}else if($("input[name=chkBoxSelectedIds]:checked").length == 0 && $("input[name=chkBoxSelectedRangeIds]:checked").length > 0){
			alert("Please select at least one Class");
			return false;
		}else if($("input[name=chkBoxSelectedIds]:checked").length > 0 && $("input[name=chkBoxSelectedRangeIds]:checked").length == 0){
			alert("Please select at least one income range");
			return false;
		}
		else if($("input[name=chkBoxSelectedIds]:checked").length > 0 && $("input[name=chkBoxSelectedRangeIds]:checked").length > 0){
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var incomeRangeIds = $("input[name=chkBoxSelectedRangeIds]:checked");
			var selectedClassIds = '';
			var selectedRangeIds  = '';
			if (classIds.length > 0) {
				selectedClassIds = '(';
				for (var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ',';
				}
				selectedClassIds += '0)';
			}
			if (incomeRangeIds.length > 0) {
				selectedRangeIds = '(';
				for (var i = 0; i < incomeRangeIds.length; i++) {
					selectedRangeIds += incomeRangeIds[i].value + ',';
				}
				selectedRangeIds += '0)';
			}
			$("#incomeRangeIds").val(selectedRangeIds);
			$("#classNameIds").val(selectedClassIds);
			getParentsIncomeWiseDetails(selectedClassIds,selectedRangeIds);
			return true;
		}
	}
	function getParentsIncomeWiseDetails(selectedClassIds,selectedRangeIds) {
		var str = "";
		var url = jQuery.url.getChatURL("/reports/ajaxParentIncomeWiseChart.do?selectedId="+ selectedClassIds+ "&selectedIncomeId="+ selectedRangeIds);
		$('#parentIncomeChart')	.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax({
				url : url,
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (isNonEmpty(response)) {
						$('#parentIncomeChart').highcharts(	{
												chart : {
													plotBackgroundColor : null,
													plotBorderWidth : null,
													plotShadow : false,
													type : 'pie'
												},
												title : {
													text : 'Parent Income wise Report'
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
												},
												plotOptions : {
													pie : {
														allowPointSelect : true,
														cursor : 'pointer',
														dataLabels : {
															enabled : true,
															format : '<b>{point.name}</b> : {point.percentage:.1f} %  (<b> {point.y} </b> )',
															style : {
																color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
																		|| 'black'
															}
														}
													}
												},
												series : response
											});
						}
						$('#parentIncomeChart').show();
					}
				});
	}
</script>