<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="form-body">
<s:if test="%{viewClassSubjectsSettings != null && !viewClassSubjectsSettings.isEmpty()}">
<s:if test="%{numberOfDays > 0}">
	 <div>
		<span class="label label-danger">NOTE :</span>
		<div class="panel-body">
			<ul>
				<li>
					Please select this check box for copying these subjects periods &amp; priority details to existing sections of this class.
				</li>
				<li>
					These details will copy to remaining sections of same subjects and total periods of remaining sections should equal to current section.
				</li>
				<li>
					 If subjects details are already defined to remaining sections those details should be overridden.
				</li>
			</ul>
	  </div>
	</div>
		<div>
			<span id='<s:property value="currentYear"/>' class='maxPeriodsPerDay'></span>
			<span id='<s:property value="numberOfDays"/>' class='totalWeekPeriods'></span>
			<label>Total periods per week :</label> <s:property value="numberOfDays"/>
		</div>
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
			<thead>
				<tr>
					<th>
						Subject Name
					</th>
					<th>
						No.of Periods per week
					</th>
					<th>
						Priority
					</th>
					<th>
					Continuous Periods Count
					<ul class="tooltipDiv">
						<li>
							<a href="#">(I)</a>
							<ul class="tooltipSubDiv">
								<div class="popover bottom " style="display: none;width: 250px;">
									<div class="arrow"></div>
									<div class="popover-content">
										<span class="help-block">(If any subject have continuous<br>periods you can add the no.of <br>periods count in the mentioned<br>column
										.For eg:if you enter 2 in<br>that column that subject is <br>continued for two periods on that<br> particular day.)</span> 
									</div>
								</div>
							</ul>
							</li>
						</ul>
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="viewClassSubjectsSettings">
					<tr class="subjectSettingsData" >
						<td class='subjectSettingId' id='<s:property value='classSubjectSettingId'/>'>
							<ul class="tooltipDiv">
								<li>
									<a href="#"><s:property value="subjectName" /> </a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;width: 233px;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												View Staff Details
											</h3>
											<div class="popover-content">
												<s:if
													test="%{staffHandlePeriodsDetails != null && staffHandlePeriodsDetails != empty}">
													<li>
														<s:property value="staffHandlePeriodsDetails" />
													</li>
												</s:if>
											</div>
										</div>
									</ul>
								</li>
							</ul>
						</td>
						<td id='<s:property value='subjectId'/>' class='subjectId'>
							<sj:textfield name="periodsPerWeek"
								id="numOfPeriods_%{subjectId}" onblur="caluclateTotalPeriods(this);" 
								cssClass="numeric form-control input-small periodsCount" maxlength="2" ></sj:textfield>
						</td>
						<td>
							<s:select list="weekDaysList" id="priorityId_%{subjectId}"
								name="subjectPriority" theme="simple"  
								cssClass="priority form-control input-medium"></s:select>
						</td>
						<td>
							<sj:textfield name="continuousPeriodsCount"
								id="comPeriods_%{subjectId}"
								onblur="checkCombinedPeriodsCount(this);"
								onkeypress="return onlyNumbers(event);"
								cssClass="numeric form-control input-small combinedPeriods" maxlength="2" ></sj:textfield>
						</td>
					</tr>
				</s:iterator>
			</tbody>
	</table>
	<div class="form-group">
		<label class="col-md-3 control-label">Copy these details to all sections : </label>
		<div class="col-md-9">
			<div class="checkbox-list">
				<label class="checkbox-inline">
					<input type="checkbox" name="status" class="copySubSettings"/></label>
					<span class="help-block">(These details will copy to remaining sections of same subjects and total periods of remaining sections should be equal to current section,then only you select this check box.)</span>
			</div>
		</div>
	</div>
	<div class="clearfix">&nbsp;</div>
		<div class="row form-horozintal">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">Total Allotted Periods :</label>
					<div class="col-md-5">
						<input type="text" disabled="disabled" id="totalPeriodsCount" class="form-control"/>
					</div>
				</div>
			</div>
		</div>
		<s:if test='%{#session.previousYear=="N"}'>
		<div class="spaceDiv"></div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit   targets="mainContentDiv" value="Submit" formIds="addClassSubSettings" cssClass="btn blue" onBeforeTopics="classSubjectSttingsFormValidation" />
				</div>
			</div>
		</s:if>
	   </s:if>
		<s:else>
			<div class="alert alert-info">
				Please create periods for this class.
			</div>
		</s:else>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No subjects defined for this class.
			</div>
		</s:else>
	</div>	
<script type="text/javascript">
changePageTitle('Periods');
$(document).ready(function() {
caluclateTotalPeriods(0);
	TableAdvanced.init();
	$("input:checkbox, input:radio").uniform();	
});
function caluclateTotalPeriods(event){
	var periodsCount = 0;
	$('tr.subjectSettingsData').each(function() {
		if(isNonEmpty($(this).find(".periodsCount").val())){
			periodsCount += Number($(this).find(".periodsCount").val());
			//alert("periodsCount"+periodsCount);
		}
	});
	if(isNonEmpty(event)){
		var periodsPerWeek = $('span.totalWeekPeriods').attr('id');
		if(Number(periodsPerWeek) < Number(periodsCount)){
			periodsCount -= $(event).val();
			$(event).val('0');
			alert('Total periods count should be equal to '+periodsPerWeek+'.');
		}
	}
	$('#totalPeriodsCount').val(periodsCount);
}
function checkCombinedPeriodsCount(event){
	 var maxCombinedPeriodscount = Number($('span.maxPeriodsPerDay').attr('id'));
	 if(maxCombinedPeriodscount < Number($(event).val())){
	 	alert('Combined periods count should be less than or equal to '+maxCombinedPeriodscount);
	 	$(event).val('0');
	 }
}
</script>
