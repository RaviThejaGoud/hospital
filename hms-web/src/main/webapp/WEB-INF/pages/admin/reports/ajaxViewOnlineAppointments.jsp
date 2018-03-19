<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-title">Online Appointments</span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">	
						<li class="active">
							<s:url id="onlineAppointments" action="ajaxOnlineAppointments" namespace="/reports">
							</s:url>
							<sj:a id="onlineAppointments" href="%{onlineAppointments}" targets="mainContentDiv" data-toggle="tab">Online Appointments</sj:a> 	
						</li>
					</ul>
					<div class="tab-content" id="FeeCollectionDetailsDiv">
					<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
					<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
					<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
					
							<s:form action="ajaxDownloadOnlineAppartments" theme="simple"
								id="teachingAndNonTeaching" method="post"
								cssClass="form-horizontal"
								onsubmit="javascript:return verifyOnlineAppointment();"
								namespace="/reports">
								<s:hidden id="classNameIds" name="tempString" />
								<div class="form-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-2">
													<span class="required">*</span> From Date :
												</label>
												<div class="col-md-5">
													<div 
														data-date-format="mm/dd/yyyy"
														class="input-group input-medium date date-picker">
														<input type="text" id="startDate" readonly="readonly"
															name="startDate"
															class="required form-control input-medium" />
														<span class="input-group-btn">
															<button type="button" class="btn default">
																<i class="fa fa-calendar"></i>
															</button> </span>
													</div>
													<span class="help-block">(MM/DD/YYYY)</span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-2">
													<span class="required">*</span> To Date :
												</label>
												<div class="col-md-5">
													<div 
														data-date-format="mm/dd/yyyy"
														class="input-group input-medium date date-picker">
														<input type="text" id="endDate" readonly="readonly"
															name="endDate"
															class="required form-control input-medium" />
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
									
									<div class="form-group">
										<div class="col-md-12">
											<div class="checkbox-list">
												<label class="checkbox-inline">
														<input type="checkbox" name="parentAppointments"> 
													Parents Appointments
												</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<div class="checkbox-list">
												<label class="checkbox-inline">
														<input type="checkbox" name="teacherAppointments"> 
													Teacher Appointments
												</label>
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<s:submit type="submit small" value="Generate Excel"
												onclick="reportType()" cssClass="submitBt btn blue long"
												title="generate report" />
										</div>
									</div>
								</div>
							</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	    changePageTitle("Online Appointments Report");
	  $("input:checkbox, input:radio").uniform();
	});
	
	
	function verifyOnlineAppointment() {
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		if (startDate == '' && endDate == '') {
			alert("Please select From and To date.");
			return false;
		} else if (startDate == '') {
			alert("Please select From date.");
			return false;
		} else if (endDate == '') {
			alert("Please select To date.");
			return false;
		} else {
			return true;
		}
	}
	
	var startDate = $('span#startDateSpan').attr("class");
	var endDate = $('span#endDateSpan').attr("class");
	dateRestrictionWithinAcademicYear(startDate,endDate);
	FormComponents.init();
</script>
