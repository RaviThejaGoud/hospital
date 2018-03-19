<%@ include file="/common/taglibs.jsp"%>
<div class="row ">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<s:property value="tempString" />
				</div>
			</div>
			<div class="portlet-body">
				<div class="row profile">
					<div class="col-md-12">
					<s:form action="ajaxAddSchoolAnnualBudget" theme="simple" id="addAnnualBudget" method="post" cssClass="form-horizontal" namespace="/admin">
					<s:hidden name="anyTitle" cssClass="anyTitleId" value=""></s:hidden>
							<div class="form-body">
								<div class="form-group">
										<label class="col-md-2 control-label">Select Month :</label>
										<div class="col-md-3">
											<select class="form-control">
												<option>January</option>
												<option>February</option>
												<option>March</option>
												<option>April</option>
												<option>May</option>
												<option>June</option>
												<option>July</option>
												<option>August</option>
												<option>September</option>
												<option>October</option>
												<option>November</option>
												<option>December</option>
											</select>
										</div>
									</div>
									
								<h3 class="form-section">Establishment Cost</h3>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Salaries & Allowances :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="10000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Contingencies :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm"  value="20400" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<!--/row-->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Postage :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="1000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Water & Electricity :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="50002" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<!--/row-->        
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Telephone :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="54000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Travil & Conveyance :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="90000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Pringing & Stationery :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="1000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Bank Commissions :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="60000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Rates & Taxes :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="5400" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Audit Fees :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="50002" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<h3 class="form-section">Repairs & Maintenance </h3>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Building :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="6000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Equipments :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="50002" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<h3 class="form-section">Miscellaneous Expenses </h3>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Sports :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="85000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Reading Room :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="6000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Laboratory :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="7520" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Audio Visual :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="3000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Students welfare Fund / Teachers Benefit Fund :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="9200" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Exam Expenditures :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="95000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Public Functions / Cultural Activities :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="50002" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4"> Extra Additional Teachers :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="85222" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Computer Staff Salary :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="120000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<h5 class="form-section"><b>Closing Balances :</b></h5>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Cash on hand A/c :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="7520000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4">Cash in operation A/c :</label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm">
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="8005202" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<h3 class="form-section"><b></b></h3>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4"><b>Total :</b></label>
											<div class="col-md-8">
												<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="16305000" >
											</div>
											<div class="col-md-4">
												<input type="text" placeholder="Enter Amount" class="form-control input-sm" value="17520000" readonly="readonly">
											</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="row">
										<div class="col-md-6">
											<div class="col-md-offset-3 col-md-9">
												<sj:submit cssClass="submitBt btn blue" value="Approve" validate="true" indicator="indicator" onBeforeTopics="getApproveStatus"
													targets="mainContentDiv" formIds="addAnnualBudget" />
												<sj:submit cssClass="submitBt btn red" value="Reject" validate="true" indicator="indicator" onBeforeTopics="getRejectStatus"
													targets="mainContentDiv" formIds="addAnnualBudget" />
													<s:url id="doSecretaryDashboards" action="ajaxSecretaryDashboard" 
														includeParams="all" escapeAmp="false" namespace="/admin">
													</s:url>
													<sj:a href="%{doSecretaryDashboards}" cssClass="btn default"
														indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
											</div>
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
	</div>
	<script>
	$.subscribe('getApproveStatus', function(event, data) {
		$('.anyTitleId').val('approve');
	});
	$.subscribe('getRejectStatus', function(event, data) {
		$('.anyTitleId').val('reject');
	});
	</script>
