<%@ include file="/common/taglibs.jsp"%>
<div class="row ">
<div class="col-md-12">
	 <div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i><s:property value="schoolName"/> </div>
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
										<ul class="profile-userpic">
											<s:if test="%{viewStaffPersonAccountDetails.OriginalAttachmentFilePath != null &&  viewStaffPersonAccountDetails.OriginalAttachmentFilePath != ''}">
													<img src='<c:url value="${viewStaffPersonAccountDetails.originalAttachmentFilePath}"/>'
														alt='<s:property  value="viewStaffPersonAccountDetails.fullName" />' border="0" style="width: 180px;" id="userProfileDiv"/>
												</s:if>
												<s:elseif test="%{student.profileImage.adjustedAttachmentFilePath != null &&  student.profileImage.adjustedAttachmentFilePath != ''}">
													<img src='<c:url value="${viewStaffPersonAccountDetails.adjustedAttachmentFilePath}"/>'
														alt='<s:property  value="user.fullPersonName" />' border="0" style="width: 180px;" id="studentProfileDiv"/>
												</s:elseif>
												<s:else>
													<img alt=""  src="../img/nophoto.jpg" style="width: 180px;100px;">
												</s:else>
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
																<%-- <s:if test='%{tempString == "St.Antonys E.M High School"}'>
																Mrs.Lakshmi Rai
																</s:if>
																<s:else>
																	John Doe
																</s:else> --%>
																<s:property value="viewStaffPersonAccountDetails.fullName"/>
																</b></p>
															</div>
														</div>
													</div>
												<!-- <div class="col-md-8">
														<div class="form-group">
															<label class="control-label col-md-3">WebSite :</label>
															<div class="col-md-9">
																<p class="form-control-static">www.maharishiShool.com</p>
															</div>
														</div>
													</div> -->
													<div class="col-md-8">
														<div class="form-group">
															<label class="control-label col-md-3">Contact :</label>
															<div class="col-md-9">
																<p class="form-control-static"><td><a  data-toggle="modal" href="#mobile"><s:property value="viewStaffPersonAccountDetails.mobileNumber"/></a></td></p>
															</div>
														</div>
													</div>
													<div class="col-md-8">
														<div class="form-group">
															<label class="control-label col-md-3">Email :</label>
															<div class="col-md-9">
																<p class="form-control-static"><td><a  data-toggle="modal" href="#email"><s:property value="viewStaffPersonAccountDetails.email"/></a></td></p>
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
																<span class="sale-num"><s:property value="tempId1"/></span>
															</li>
															<li>
																<span class="sale-info">Total Staff <i class="fa fa-img-down"></i></span> 
																<span class="sale-num"><s:property value="tempId2"/></span>
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
												<li class="active"><a data-toggle="tab" href="#tab_1_11"><s:property value="financialYear.yearName"/> Budget</a></li>
												<li><a data-toggle="tab" href="#tab_1_22">UpComing Mettings</a></li>
												<li><a data-toggle="tab" href="#tab_1_33">Meeting Minutes</a></li>
												
											</ul>
											<div class="tab-content">
												<div id="tab_1_11" class="tab-pane active">
													<div class="portlet-body">
													<s:if test="%{objectList != null && !objectList.isEmpty()}">	
														<table class="table table-striped table-bordered table-advance table-hover">
															<thead>
																<tr>
																	<th><i class="fa fa-calendar"></i> Request Date</th>
																	<th>Requested Month</th>
																	<th><i class="fa fa-rupee"></i> Total</th>
																	<th>Status</th>
																	<th>Action</th>
																</tr>
															</thead>
															<tbody>
																
																<s:iterator value="objectList">
																	<tr>
																		<td><s:property value="userFormattedCreatedDate"/></td>
																		<td><s:property value="monthName"/></td>
																		<td><i class="fa fa-rupee"></i><s:property value="totalBudgetAmount"/></td>
																		<td><s:property value="budgetStatusDesc"/></td>
																		
																		<td>
																			<s:url id="urlAcceptOrRejectLinks" action="ajaxViewBudgetDetailsBySecratary"
																				namespace="/admin" escapeAmp="false" includeParams="all">
																				<s:param name="budgetRequest.id" value="%{id}" />
																			</s:url>
																			<sj:a href="%{urlAcceptOrRejectLinks}" targets="schoolContentDiv"
																				cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
																	    </td>
																	</tr>
																</s:iterator>
															</tbody>
														</table>
														</s:if>
														<s:else>
															<div class="alert alert-info">
																No budget requests for this financial year.
															</div>
														</s:else>
													</div>
												</div>
												<div id="tab_1_33" class="tab-pane">
													<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">	
													 <table class="table table-striped table-bordered table-hover table-full-width" id="sample_5">
															<thead>
																<tr>
																	<th>School Name</th>
																	<th>Date- Time</th>
																	<th>Venue</th>
																	<th>Agenda</th>
																	<th>Meeting Minutes</th>
																</tr>
															</thead>
															<tbody>
																<s:iterator value="tempList1">
																	<tr>
																		<td><s:property value="organization" /></td>
																		<td><s:property value="meetingAgenda" /></td>
																		<td><s:property value="place" /></td>
																		<td><s:property value="meetingDateTime" /></td>
																		<td><a data-toggle="modal" title="Audio"
																			href="#audioDiv"
																			onclick="javascript:PopupViewAudioDetials(<s:property value="%{meetingDetailsId}" />,'audio');">
																				<i class="fa fa-volume-up"></i>
																		</a>&nbsp;&nbsp; |&nbsp;&nbsp; <a data-toggle="modal"
																			title="Video" href="#audioDiv"
																			onclick="javascript:PopupViewAudioDetials(<s:property value="%{meetingDetailsId}" />,'video');">
																				<i class="fa fa-video-camera"></i>
																		</a>&nbsp;&nbsp; |&nbsp;&nbsp; <a data-toggle="modal"
																			title="Documents" href="#audioDiv"
																			onclick="javascript:PopupViewAudioDetials(<s:property value="%{meetingDetailsId}" />,'application');"><i
																				class="fa fa-book"></i></a></td>
																	</tr>
																</s:iterator>
																<%-- <tr>
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
																</tr> --%>
															</tbody>
														</table>
														</s:if>
														<s:else><div class="alert alert-info">Corrently there are no meetings Details found.</div></s:else>
												 </div>
												<div id="tab_1_22" class="tab-pane">
													<div class="portlet-body">
													<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">	
														<table class="table table-striped table-bordered table-advance table-hover">
															<thead>
																<tr>
																	<th>School Name</th>
																	<th>Agenda</th>
																	<th>Venue</th>
																	<th>Date - Time</th>
																</tr>
															</thead>
															<tbody>
																<s:iterator value="tempList1">
																	<tr>
																		<td>
																			<s:property value="organization" />
																		</td>
																		<td>
																			<s:property value="meetingAgenda" />
																		</td>
																		<td>
																			<s:property value="place" />
																		</td>
																		<td>
																			<s:property value="meetingDateTime" />
																		</td>
																	</tr>
																</s:iterator>
															</tbody>
														</table>
														</s:if>
														<s:else><div class="alert alert-info">CUrrently there are no upcoming meetings.</div></s:else>
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
												<div id="budgetDataChatsDivId" class="chart" style="height: 400px;">
												</div>
												<div class="well margin-top-20">
													<div class="row">
														<div class="col-sm-3">
															<label class="text-left">Top Radius:</label>
															<input class="budgetData_chart_input" data-property="topRadius" type="range" min="0" max="1.5" value="1" step="0.01"/>
														</div>
														<div class="col-sm-3">
															<label class="text-left">Angle:</label>
															<input class="budgetData_chart_input" data-property="angle" type="range" min="0" max="89" value="30" step="1"/>
														</div>
														<div class="col-sm-3">
															<label class="text-left">Depth:</label>
															<input class="budgetData_chart_input" data-property="depth3D" type="range" min="1" max="120" value="40" step="1"/>
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
		<s:form action="ajaxSendMessageToManager" theme="simple" id="messages" cssClass="form-horizontal" >
			<s:hidden name="custId" value="%{customer.id}"></s:hidden>
			<s:hidden name="anyId" value="%{viewStaffPersonAccountDetails.mobileNumber}"></s:hidden>
			<s:hidden name="tempString" value="M"></s:hidden>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4"> <span class="required">*</span>Mobile Number :
							</label>
							<div class="col-md-5">
								<sj:textfield name="mobileNo" id="mobileNo"  value="%{viewStaffPersonAccountDetails.mobileNumber}" disabled="true"
									cssClass="form-control numeric required" maxlength="13"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group ">
							<label class="control-label col-md-4">  <span class="required">*</span>Message : </label>
							<div class="col-md-5">
								<sj:textarea rows="3" cols="20" name="messages.messageDescription"
									maxCharsData="160" 
									cssClass="form-control word_count required"></sj:textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-4 col-md-7">
						<sj:submit cssClass="submitBt btn blue" value="Send"
							resetForm="true" indicator="indicator" formIds="messages"
							targets="mainContentDiv" validate="true"  onBeforeTopics="checkMobile" />
						<button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
					</div>
				</div>
		  </s:form>
		  </div>
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
		<s:form action="ajaxSendEmail" theme="simple" id="sendEmail" cssClass="form-horizontal" >
		<s:hidden name="custId" value="%{customer.id}"></s:hidden>
			<s:hidden name="tempId" value="%{viewStaffPersonAccountDetails.accountId}"></s:hidden>
			<s:hidden name="messageTo" value="%{viewStaffPersonAccountDetails.email}"></s:hidden>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4"> <span class="required">*</span>To :
							</label>
							<div class="col-md-5">
								<sj:textfield name="to" id="to" value="%{viewStaffPersonAccountDetails.email}" disabled="true"
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
						<sj:submit cssClass="submitBt btn blue" value="Send" formIds="sendEmail"  indicator="indicator"  
							targets="mainContentDiv" validate="true" onBeforeTopics="checkEmail" /> 
						<button type="button" data-dismiss="modal" class="btn btn-default">Close</button>
					</div>
					</div>
		     </s:form>
		   </div>
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
						</video>
					</div>
				</div>
		</div>
	</div>
</div>
<div id="budgetDataChatsDivId1" class="chart" style="height: 400px;"></div>
<div id="audioDiv"></div>
<script  type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
	ChartsAmcharts.init();
	var custId = '<s:property value="customer.id"/>';
	var financialYearId = '<s:property value="financialYear.id"/>';
	
	getSchoolBudgetDetails(custId,financialYearId);
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
	
function getSchoolBudgetDetails(custId,financialYearId){
		var str="";
		var url ;
     if(isNonEmpty(custId))
		 url = jQuery.url.getChatURL("/admin/ajaxGetSchoolBudgetDetailsJsonByCustId.do?custId="+custId+"&financialYear.id="+financialYearId);
	 	$('#budgetDataChatsDivId').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		dataType : 'json',
		success : function(response) {
		if(isNonEmpty(response))
		{
			var data = response.budgetRequestData;
			if (data.length > 0) {
					
				var chart = AmCharts.makeChart("budgetDataChatsDivId", {
			        "theme": "light",
			        "type": "serial",
			        "startDuration": 2,

			        "fontFamily": 'Open Sans',
			        
			        "color":    '#888',

			        "dataProvider": data,
			        "valueAxes": [{
			            "position": "left",
			            "axisAlpha": 0,
			            "gridAlpha": 0
			        }],
			        "graphs": [{
			            "balloonText": "[[category]]: <b>[[value]]</b>",
			            "colorField": "color",
			            "fillAlphas": 0.85,
			            "lineAlpha": 0.1,
			            "type": "column",
			            "topRadius": 1,
			            "valueField": "visits"
			        }],
			        "depth3D": 40,
			        "angle": 30,
			        "chartCursor": {
			            "categoryBalloonEnabled": false,
			            "cursorAlpha": 0,
			            "zoomable": false
			        },
			        "categoryField": "country",
			        "categoryAxis": {
			            "gridPosition": "start",
			            "axisAlpha": 0,
			            "gridAlpha": 0

			        },
			        "exportConfig": {
			            "menuTop": "20px",
			            "menuRight": "20px",
			            "menuItems": [{
			                "icon": '/lib/3/images/export.png',
			                "format": 'png'
			            }]
			        }
			    }, 0);

			    jQuery('.budgetData_chart_input').off().on('input change', function() {
			        var property = jQuery(this).data('property');
			        var target = chart;
			        chart.startDuration = 0;

			        if (property == 'topRadius') {
			            target = chart.graphs[0];
			        }

			        target[property] = this.value;
			        chart.validateNow();
			    });

			    $('#budgetDataChatsDivId').closest('.portlet').find('.fullscreen').click(function() {
			        chart.invalidateSize();
			    });
			}
			 
			$('#budgetDataChatsDivId').html(chart);
		  }else{
		  	$('#budgetDataChatsDivId').html('<div class="alert alert-info">There is no budget request found for this financial year.</div>');
		  }
        }
		});
}
function PopupViewAudioDetials(id,type) {
var pars = "tempId1=" + id + "&anyId=" + type;
		var url = jQuery.url.getChatURL("/admin/ajaxPlayOrViewMeetingDetails.do");
		$('#responsive').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#audioDiv").html(html);
				}
			});
		}
</script>

</script>