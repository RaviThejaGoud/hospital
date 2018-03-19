<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Dealer Transaction Details - September
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
				<div class="row portfolio-block">
									<div class="col-md-5">
										<div class="portfolio-text">
											<img alt="" src="assets/img/profile/portfolio/logo_metronic.jpg">
											<div class="portfolio-text-info">
												<h4><s:property value="viewFpsSettings.shopNo"/>-<s:property value="viewFpsSettings.dealerName"/></h4>
												<ul class="list-inline">
													<li><i class="fa fa-map-marker"></i> <s:property value="viewFpsSettings.addressLine1"/> , <s:property value="viewFpsSettings.district"/>-<s:property value="viewFpsSettings.pinCode"/></li>
													
												</ul>
											</div>
										</div>
									</div>
									<div class="col-md-5">
										<div class="portfolio-info">
											Opening date
											<span><i class="fa fa-calendar"></i> <s:property value="viewFpsSettings.stockOpeningDate"/></span>
										</div>
										<div class="portfolio-info">
											Closing date
											<span><i class="fa fa-calendar"></i> <s:property value="viewFpsSettings.stockClosingDate"/></span>
										</div>
										
									</div>
								</div>
					<div>
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2">
							<thead>
								<tr>
									<th>
										Commodity Item
									</th>
									<th>
										Balance Quantity (K.gs)
									</th>
									<th>
										Issued Quantity (K.gs)
									</th>
									<th>
										Received Amount (Rs)
									</th>
								</tr>
							</thead>
							<tbody>
							 		<tr>
										<td>
										Rice
										</td>
										<td>
											250
										</td>
										<td>
											50
										</td>
										<td>
											300
										</td>
										 
									</tr>
									<tr>
										<td>
										Dal
										</td>
										<td>
											250
										</td>
										<td>
											50
										</td>
										<td>
											300
										</td>
										 
									</tr>
									<tr>
										<td>
										Kerosene
										</td>
										<td>
											250
										</td>
										<td>
											50
										</td>
										<td>
											300
										</td>
										 
									</tr> 
							</tbody>
							<!--<tbody>
								<s:iterator value="objectList">
									<tr>
										<td>
											<s:property value="name" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						--></table>
					</div>
					<div class="row portfolio-block">
									<div class="col-md-5">
										<div class="portfolio-text">
											<img alt="" src="assets/img/profile/portfolio/logo_metronic.jpg">
											<div class="portfolio-text-info">
													<h4>Previous Transactions</h4>
											</div>
										</div>
									</div>
								</div>
								<div class="form-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">
										From Date :
									</label>
									<div class="col-md-8">
										<div class="input-group input-medium date date-picker">
								<input type="text" readonly="readonly" class="form-control"
									id="date0" name="person.dateOfBirth" onchange="verifyDate();">
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
									<label class="control-label col-md-4">
										To Date :
									</label>
									<div class="col-md-8">
										<div class="input-group input-medium date date-picker">
								<input type="text" readonly="readonly" class="form-control"
									id="date0" name="person.dateOfBirth" onchange="verifyDate();">
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
						<div>
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2">
							<thead>
								<tr>
									<th>
										Commodity Item
									</th>
									<th>
										Balance Quantity (K.gs)
									</th>
									<th>
										Issued Quantity (K.gs)
									</th>
									<th>
										Received Amount (Rs)
									</th>
								</tr>
							</thead>
							<tbody>
							 		<tr>
										<td>
										Rice
										</td>
										<td>
											250
										</td>
										<td>
											50
										</td>
										<td>
											300
										</td>
										 
									</tr>
									<tr>
										<td>
										Dal
										</td>
										<td>
											250
										</td>
										<td>
											50
										</td>
										<td>
											300
										</td>
										 
									</tr>
									<tr>
										<td>
										Kerosene
										</td>
										<td>
											250
										</td>
										<td>
											50
										</td>
										<td>
											300
										</td>
										 
									</tr> 
							</tbody>
							<!--<tbody>
								<s:iterator value="objectList">
									<tr>
										<td>
											<s:property value="name" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
										<td>
											<s:property value="subjectNumber" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						--></table>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
FormComponents.init();
	FormAdvanced.init();
TableAdvanced.init();
function getAttendanceStuds() {
	var attendanceDate = $('#attendanceDate').val(); 
	var sectionId = $('#classId').val();
	if(isNonEmpty(attendanceDate) && isNonEmpty(sectionId)){
		var pars = "classId=" + sectionId + "&attendanceDate=" + attendanceDate;
		$("#createAttendenceDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url
					.getChatURL("/admin/ajaxViewAttendanceForm.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#createAttendenceDiv').html(response);
			}
		});
	}else{
		if (isNonEmpty(attendanceDate)) 
			$('#createAttendenceDiv').html("<p><span class='label label-danger'> NOTE : </span> &nbsp;&nbsp;Please select Class & Section.</p>");
		else
			$('#createAttendenceDiv').html("<p><span class='label label-danger'> NOTE : </span> &nbsp;&nbsp;Please select attendance date.</p>");
	}
}
</script>
