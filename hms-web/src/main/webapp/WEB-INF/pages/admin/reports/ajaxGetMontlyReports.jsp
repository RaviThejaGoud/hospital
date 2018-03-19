<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
<div class="col-md-12">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-globe"></i>Auto Monthly Report
			</div>
		</div>
		<div class="portlet-body">
				<div class="tab-content">
				<s:if test="%{attendanceDate !=null && !attendanceDate.isEmpty()}">
					<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
					<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
					<s:form action="ajaxGenarateMonthlyReportsDetails" id="createAssignment" theme="simple"	cssClass="form-horizontal" namespace="/reports">
					<div class="form-body">
						<div class="form-group">
							<div class="panel-body col-md-12">
								<div class="col-md-6">
									<span class="label label-danger"> NOTE : </span>&nbsp;&nbsp;Auto Monthly report will be calculated up to <b><s:property value="attendanceDate"/></b> 
									<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Report contains,
								</div>
								<div class="col-md-10">
									<ul>
										<li>
											Top 5 Best Teachers information with respect to examinations.
										</li>
										<li>
											Top 5 Best Students information with respect to examination results.
										</li>
										<li>
											 Top 5 Students information with respect to attendance.
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-2 col-md-9">
								<div id="classWiseStuDetailsPdf">
									<s:submit type="submit" value="Generate Excel" 
										onclick="reportType()" cssClass="submit btn blue"
										title="generate report" cssStyle="float:left;margin-left:10px;">
									</s:submit>
								</div>
							</div>
						</div>
						</div>
					</s:form>
				</s:if>
				<s:else>
					<div class="alert alert-info">Currently there is no data to generate the report.</div>
				</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
var startDate = $('span#startDateSpan').attr("class");
var endDate = $('span#endDateSpan').attr("class");
dateRestrictionWithinAcademicYear(startDate,endDate);
FormComponents.init();
</script>