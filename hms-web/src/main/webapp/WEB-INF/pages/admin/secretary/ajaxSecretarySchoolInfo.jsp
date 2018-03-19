<%@ include file="/common/taglibs.jsp"%>
<div class="row ">
<div class="col-md-12">
	 <div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i><s:property value="tempString"/> </div>
		</div>
		<div class="portlet-body">
		   <div class="row profile">
				<div class="col-md-12">
				<jsp:include page="/common/messages.jsp" />
					<!--BEGIN TABS-->
					<div id="schoolContentDiv" class="tabbable tabbable-custom tabbable-full-width">
						<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="#tab_1_1">Overview</a></li>
						</ul>
						<div class="tab-content" style=" border-bottom: medium none;border-left: medium none;border-right: medium none;padding: 15px 0">
							<div id="tab_1_1" class="tab-pane active">
								<div class="row">
									<div class="col-md-3">
										<ul class="list-unstyled profile-nav">
											<li>
											<s:if test='%{tempString == "St.Antonys E.M High School"}'>
												<img alt="" class="img-responsive" src="../img/pages/3.jpg"> 
											</s:if>
											<s:elseif test='%{tempString == "Swaami Vivekananda Matric. Hr. Sec. School"}'>
												<img alt="" class="img-responsive" src="../img/profile/profile-img.png"> 
											</s:elseif>
											<s:elseif test='%{tempString == "GEETAM EDUCATIONAL INSTITUTIONS-KKD"}'>
												<img alt="" class="img-responsive" src="../img/works/img5.jpg"> 
											</s:elseif>
											<s:elseif test='%{tempString == "Akshara International School"}'>
												<img alt="" class="img-responsive" src="../img/profile/profile-img.png"> 
											</s:elseif>
											<s:else>
												<img alt="" class="img-responsive" src="../img/profile/profile-img.png"> 
											</s:else>
											
											</li>
										</ul>
									</div>
									<div class="col-md-9">
										<div class="row">
											<div class="col-md-8 profile-info form-horizontal">
												<div class="col-md-8">
														<div class="form-group">
															<label class="control-label col-md-3">Manager :</label>
															<div class="col-md-9">
																<p class="form-control-static"><b>
																<s:if test='%{tempString == "St.Antonys E.M High School"}'>
																Mrs.Lakshmi Rai
																</s:if>
																<s:else>
																	John Doe
																</s:else>
																</b></p>
															</div>
														</div>
													</div>
												<div class="col-md-8">
														<div class="form-group">
															<label class="control-label col-md-3">WebSite :</label>
															<div class="col-md-9">
																<p class="form-control-static">www.maharishiShool.com</p>
															</div>
														</div>
													</div>
													<div class="col-md-8">
														<div class="form-group">
															<label class="control-label col-md-3">Contact :</label>
															<div class="col-md-9">
																<p class="form-control-static"><td><a  data-toggle="modal" href="#mobile">9980054540</a></td></p>
															</div>
														</div>
													</div>
													<div class="col-md-8">
														<div class="form-group">
															<label class="control-label col-md-3">Email :</label>
															<div class="col-md-9">
																<p class="form-control-static"><td><a  data-toggle="modal" href="#email">jons2015@gmail.com</a></td></p>
															</div>
														</div>
													</div>
											</div>
											<!--end col-md-8-->
											<div class="col-md-4">
												<div class="portlet sale-summary">
													<div class="portlet-title">
														<div class="caption">Summary</div>
														<div class="tools">
															<a href="javascript:;" class="reload"></a>
														</div>
													</div>
													<div class="portlet-body">
														<ul class="list-unstyled">
															<li>
																<span class="sale-info">Total Students <i class="fa fa-img-up"></i></span> 
																<span class="sale-num">2300</span>
															</li>
															<li>
																<span class="sale-info">Total Staff <i class="fa fa-img-down"></i></span> 
																<span class="sale-num">267</span>
															</li>
														</ul>
													</div>
												</div>
											</div>
											<!--end col-md-4-->
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="tabbable tabbable-custom tabbable-custom-profile">
											<ul class="nav nav-tabs">
												<li class="active"><a data-toggle="tab" href="#tab_1_11">2014-15 Budget</a></li>
												<li><a data-toggle="tab" href="#tab_1_22">UpComing Mettings</a></li>
												<li><a data-toggle="tab" href="#tab_1_33">Meeting Minutes</a></li>
												
											</ul>
											<div class="tab-content">
												<div id="tab_1_11" class="tab-pane active">
													<div class="portlet-body">
														<table class="table table-striped table-bordered table-advance table-hover">
															<thead>
																<tr>
																	<th><i class="fa fa-calendar"></i> Request Date</th>
																	<th><i class="fa fa-rupee"></i> Total</th>
																	<th>Status</th>
																	<th>Action</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>09-Feb-2015</td>
																	<td><i class="fa fa-rupee"></i> 52560.10 </td>
																	<td><span class="label label-warning label-sm">Pending</span>  </td>
																	<td>
																		<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
																			namespace="/admin" escapeAmp="false" includeParams="all">
																			<s:param name="tempString"><s:property value="tempString"/></s:param>
																		</s:url>
																		<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
																			cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
																    </td>
																</tr>
																<tr>
																	<td>28-Jan-2015</td>
																	<td><i class="fa fa-rupee"></i> 5760.00</td>
																	<td><span class="label label-warning label-sm">Pending</span></td>
																	<td>
																		<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
																			namespace="/admin" escapeAmp="false" includeParams="all">
																			<s:param name="tempString"><s:property value="tempString"/></s:param>
																		</s:url>
																		<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
																			cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
																    </td>
																</tr>
																<tr>
																	<td>08-Sep-2015</td>
																	<td><i class="fa fa-rupee"></i> 12400.00 </td>
																	<td><span class="label label-success label-sm">Approved</span></td>
																	<td>
																		<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
																			namespace="/admin" escapeAmp="false" includeParams="all">
																			<s:param name="tempString"><s:property value="tempString"/></s:param>
																		</s:url>
																		<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
																			cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
																    </td>
																</tr>
																<tr>
																	<td>06-Aug-2015</td>
																	<td><i class="fa fa-rupee"></i> 610.50 </td>
																	<td><span class="label label-success label-sm">Approved</span>  </td>
																	<td>
																		<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
																			namespace="/admin" escapeAmp="false" includeParams="all">
																			<s:param name="tempString"><s:property value="tempString"/></s:param>
																		</s:url>
																		<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
																			cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
																    </td>
																</tr>
																<tr>
																	<td>22-Jul-2015</td>
																	<td><i class="fa fa-rupee"></i> 5760.00 </td>
																	<td><span class="label label-success label-sm">Approved</span>  </td>
																	<td>
																		<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
																			namespace="/admin" escapeAmp="false" includeParams="all">
																			<s:param name="tempString"><s:property value="tempString"/></s:param>
																		</s:url>
																		<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
																			cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
																    </td>
																</tr>
																<tr>
																	<td>12-Jun-2015</td>
																	<td><i class="fa fa-rupee"></i> 2760.00 </td>
																	<td><span class="label label-sm label-danger">Reject</span></td>
																	<td>
																		<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
																			namespace="/admin" escapeAmp="false" includeParams="all">
																			<s:param name="tempString"><s:property value="tempString"/></s:param>
																		</s:url>
																		<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
																			cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
																    </td>
																</tr>
																<tr>
																	<td>29-Apr-2015</td>
																	<td><i class="fa fa-rupee"></i> 12400.00 </td>
																	<td><span class="label label-success label-sm">Approved</span></td>
																	<td>
																		<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
																			namespace="/admin" escapeAmp="false" includeParams="all">
																			<s:param name="tempString"><s:property value="tempString"/></s:param>
																		</s:url>
																		<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
																			cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
																    </td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
												<div id="tab_1_33" class="tab-pane">
													 <table class="table table-striped table-bordered table-hover table-full-width" id="sample_5">
															<thead>
																<tr>
																	<th>School Name</th>
																	<th>Date- Time</th>
																	<th>Avenue</th>
																	<th>Agenda</th>
																	<th>Meeting Minutes</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td><s:property value="tempString"/> </td>
																	<td>15-Sep-2014 - 08:40 AM</td>
																	<td>Bangalore</td>
																	<td>Discus  on budget  </td>
																	<td><a  data-toggle="modal" href="#audio" title="Audio"><i class="fa fa-volume-up"></i> </a>&nbsp;&nbsp; |&nbsp;&nbsp; <a  data-toggle="modal" href="#video" title="Video"><i class="fa fa-video-camera"></i> </a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://c3094222.r22.cf0.rackcdn.com/les_4509_Calories_in_Broccoli_Salad.pdf" target="_new">&nbsp;&nbsp;<i class="fa fa-book"></i></a> </td>
																</tr>
																<tr>
																<td><s:property value="tempString"/> </td>
																	<td>05-Jan-2015 10:00 AM</td>
																	<td>Mysore</td>
																	<td>Regd: Annual Meeting </td>
																	<td><a  data-toggle="modal" href="#audio" title="Audio"><i class="fa fa-volume-up"></i> </a>&nbsp;&nbsp; |&nbsp;&nbsp; <a  data-toggle="modal" href="#video" title="Video"><i class="fa fa-video-camera"></i> </a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://c3094222.r22.cf0.rackcdn.com/les_4509_Calories_in_Broccoli_Salad.pdf" target="_new">&nbsp;&nbsp;<i class="fa fa-book"></i></a> </td>
																</tr>
																<tr >
																<td><s:property value="tempString"/> </td>
																	<td>04-Feb-2015 12:00 PM</td>
																	<td>Kadapa</td>
																	<td>Regd: Pay scale revision.</td>
																	<td><a  data-toggle="modal" href="#audio" title="Audio"><i class="fa fa-volume-up"></i> </a>&nbsp;&nbsp; |&nbsp;&nbsp; <a  data-toggle="modal" href="#video" title="Video"><i class="fa fa-video-camera"></i> </a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://c3094222.r22.cf0.rackcdn.com/les_4509_Calories_in_Broccoli_Salad.pdf" target="_new">&nbsp;&nbsp;<i class="fa fa-book"></i></a> </td>
																</tr>
															</tbody>
														</table>
												 </div>
												<div id="tab_1_22" class="tab-pane">
													<div class="portlet-body">
														<table class="table table-striped table-bordered table-advance table-hover">
															<thead>
																<tr>
																	<th>School Name</th>
																	<th>Agenda</th>
																	<th>Avenue</th>
																	<th>Date - Time</th>
																</tr>
															</thead>
															<tbody>
																<tr>
															<td><s:property value="tempString"/> </td>
															<td>Discus  on budget  </td>
															<td>Bangalore</td>
															<td>15-Apr-2015 - 10:00:00 AM</td>
														</tr>
														<tr>
															<td><s:property value="tempString"/> </td>
															<td>Regd: New recruitments </td>
															<td>Mysore</td>
															<td>27-Apr-2015 - 03:00:00 PM</td>
														</tr>
														<tr>
															<td><s:property value="tempString"/> </td>
															<td>Regd: Pay scale revision.</td>
															<td>Bangalore</td>
															<td>28-Mar-2015 - 08:00:00 AM </td>
														</tr>
														<tr>
															<td><s:property value="tempString"/> </td>
															<td>Regd: Annual Meeting </td>
															<td>Meeting hall in bangalore</td>
															<td>28-Mar-2015 - 08:55:31 PM</td>
														</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<!-- BEGIN CHART PORTLET-->
										<div class="portlet box blue">
											<div class="portlet-title">
												<div class="caption">
													<i class="icon-bar-chart font-green-haze"></i>
													<span class="caption-subject bold uppercase font-green-haze">2014-15 Annual Budget</span>
												</div>
												<div class="tools">
													<a href="javascript:;" class="reload">
													</a>
													<a href="javascript:;" class="fullscreen">
													</a>
												</div>
											</div>
											<div class="portlet-body">
												<div id="chart_5" class="chart" style="height: 400px;">
												</div>
												<div class="well margin-top-20">
													<div class="row">
														<div class="col-sm-3">
															<label class="text-left">Top Radius:</label>
															<input class="chart_5_chart_input" data-property="topRadius" type="range" min="0" max="1.5" value="1" step="0.01"/>
														</div>
														<div class="col-sm-3">
															<label class="text-left">Angle:</label>
															<input class="chart_5_chart_input" data-property="angle" type="range" min="0" max="89" value="30" step="1"/>
														</div>
														<div class="col-sm-3">
															<label class="text-left">Depth:</label>
															<input class="chart_5_chart_input" data-property="depth3D" type="range" min="1" max="120" value="40" step="1"/>
														</div>
													</div>
												</div>
											</div>
										</div>
								 </div>
							</div>
						</div>
					</div>
					<!--END TABS-->
				</div>
			</div>
			</div>
		</div>
	</div>
</div>

<div id="mobile" class="modal fade" tabindex="-1" data-width="760">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Message To Manager
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
		<s:form action="ajaxSendMessage" theme="simple"
			id="messages" cssClass="form-horizontal" >
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4"> <span class="required">*</span>Mobile Number :
							</label>
							<div class="col-md-5">
								<sj:textfield name="mobile" id="mobileNo"  value="9980054540" disabled="disabled"
									cssClass="form-control numeric" maxlength="10"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group ">
							<label class="control-label col-md-4">  <span class="required">*</span>Message : </label>
							<div class="col-md-5">
								<sj:textarea rows="3" cols="20" name="summary"
									maxCharsData="160" 
									cssClass="form-control word_count required"></sj:textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-4 col-md-7">
						<sj:submit cssClass="submitBt btn blue" value="Sand"
							resetForm="true" indicator="indicator" formIds="messages"
							targets="mainContentDiv" validate="true"  onBeforeTopics="checkMobile" />
						<button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
					</div>
					</div>
				</div>
		  </s:form>
	</div>
</div>
<div id="email" class="modal fade" tabindex="-1" data-width="760">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Message To Manager
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
		<s:form action="ajaxSendEmail" theme="simple"
			id="sendEmail" cssClass="form-horizontal" >
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4"> <span class="required">*</span>To :
							</label>
							<div class="col-md-5">
								<sj:textfield name="messageTo" id="to" value="jons2015@gmail.com" disabled="disabled"
									cssClass="form-control required" maxlength="100"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group ">
							<label class="control-label col-md-4">Subject : </label>
							<div class="col-md-5">
								<sj:textfield name="subject" id="Subject"  value=""
									cssClass="form-control" maxlength="10"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group ">
							<label class="control-label col-md-4">  <span class="required">*</span>Message : </label>
							<div class="col-md-7">
								<sj:textarea rows="3" cols="60" name="summaryMessage"
									maxCharsData="160" 
									cssClass="form-control word_count required"></sj:textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-4 col-md-7">
						<sj:submit cssClass="submitBt btn blue" value="Sand" formIds="sendEmail"  indicator="indicator"  
							targets="mainContentDiv" validate="true" onBeforeTopics="checkEmail" /> 
						<button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
					</div>
					</div>
				</div>
		  </s:form>
	</div>
</div>

<div id="audio" class="modal fade" tabindex="-1" data-width="760">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Meeting Minutes
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
				<div class="row">
					<div class="col-md-12">
					<audio controls>
					  <source src="horse.ogg" type="audio/ogg">
					  <source src="horse.mp3" type="audio/mpeg">
							Your browser does not support the audio element.
					</audio>
					</div>
				</div>
		</div>
	</div>
</div>
<div id="video" class="modal fade" tabindex="-1" data-width="760">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Meeting Minutes
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
				<div class="row">
					<div class="col-md-12">
						<video width="400" controls>
						  <source src="mov_bbb.mp4" type="video/mp4">
						  <source src="mov_bbb.ogg" type="video/ogg">
						  Your browser does not support HTML5 video.
						</video>
					</div>
				</div>
		</div>
	</div>
</div>
<script Language="Javascript" type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
	ChartsAmcharts.init();
});
$.subscribe('checkMobile',function(event, data) {
	if ($('#messages').valid()) {
		$('button.close').click();
	} else {
		event.originalEvent.options.submit = false;
	}
	});
$.subscribe('checkEmail',function(event, data) {
	if ($('#sendEmail').valid()) {
		$('button.close').click();
	} else {
		event.originalEvent.options.submit = false;
	}
	});
</script>