<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>TimeTable
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear=="N"}'>
							  <li>
								<s:url id="urlAddSclPeriod" action="ajaxDoAddSclPeriod" namespace="/admin">
								</s:url>
								<sj:a id="addPeriod" href="%{urlAddSclPeriod}" targets="sclPeriodsContDiv" data-toggle="tab">Add Periods</sj:a> 	
							 </li>
					    </s:if>
						 <li class="active" id="viewPeroids">
							<s:url id="viewSchoolPeriods" action="ajaxGetSchoolPeriods" namespace="/admin">
							</s:url>
							<sj:a href="%{viewSchoolPeriods}" targets="mainContentDiv" data-toggle="tab">View Periods</sj:a> 	
						 </li>
					</ul>
					<div id="sclPeriodsContDiv" class="tab-content">
					<%-- <s:if test="%{(studyClassList != null && !studyClassList.isEmpty()) && (smsCnt) > 0}"> --%>
						<%@ include file="/common/messages.jsp"%>
						<h4 class="pageTitle bold">
							Class wise Periods
						</h4>
						<s:if test='%{#session.previousYear=="N"}'>
								<div class="form-horizontal form-group" id="serviceDiv">
									<label class="col-md-3 control-label">Does class
										teacher take first period :
									</label>
									<div class="col-md-8">
										<div class="checkbox-list">
											<span id="periodDiv"><label class="checkbox-inline" style="padding-left:0px;">
												<s:if test='%{status == "Y"}'>
													<input type="checkbox" name="status" checked="checked"
														value="true" id="classTeacherPeriod"
														onclick="javascript:classTeacherTakeFirstPeriodConfirmDialog(this,'serviceDiv');"
														class="changeServicesStatus" />
												</s:if>
												<s:else>
													<input type="checkbox" name="status" id="classTeacherPeriod"
														onclick="javascript:classTeacherTakeFirstPeriodConfirmDialog(this,'serviceDiv');"
														class="changeServicesStatus" />
												</s:else>
											</label>
											</span>

										</div>
									</div>
								</div>
							</s:if>
						<jsp:include page="/WEB-INF/pages/admin/academic/timeTable/ajaxViewClasswisePeriods.jsp"></jsp:include>
						<%-- </s:if>
						<s:else>
							<div class="alert alert-info">
								You have not created periods.
							</div>
						</s:else> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
	
 </div>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio:not('.toggle')").uniform(); 
	
});

function classTeacherTakeFirstPeriodConfirmDialog(event, target) {
	var url = '';
	var pars = '';
	if ($(event).parent().parent().next('.question').length <= 0) {
		$(event).parent().parent().after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).parent('span').addClass("checked");
	$(event).attr("checked", true);
	$(event).parent().parent().next('.question').animate({
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click',function() {
						var prdDiv = $(this).parents('.question');
						prdDiv.html('Processing...');
						if ($("#classTeacherPeriod").is(':checked')) {
							url = jQuery.url
									.getChatURL("/admin/ajaxChangeClassTeacherPeriodStatus.do");
						} else {
							url = jQuery.url
									.getChatURL("/admin/ajaxChangeClassTeacherPeriodStatus.do");
						}
						$.ajax({
							url : url,
							cache : false,
							data : pars,
							success : function(html) {			
								$(prdDiv).hide();
								location.reload();
							}
						});
					});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		if ($(event).val() == "true") {
			$(event).attr("checked", "checked");
		} else {
			$(event).removeAttr("checked");
			$(event).parent('span').removeClass('checked');
		}
		return false;
	});
}
</script>
