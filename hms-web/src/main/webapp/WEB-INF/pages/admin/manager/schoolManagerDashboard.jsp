<%@ include file="/common/taglibs.jsp"%>
<div class="row ">
<div class="col-md-12">
	 <div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i>Upcoming Meetings</div>
		</div>
		<div class="portlet-body">
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
					 	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
							<thead>
								<tr>
									<th>School Name</th>
									<th>Agenda</th>
									<th>Venue</th>
									<th>Date- Time</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="objectList">
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
						<s:else><div class="alert alert-info"> Currently there are no upcoming meetings .</div></s:else>
		</div>
	</div>	
</div>
<div class="col-md-12">
<jsp:include page="/common/messages.jsp" />
<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i>Meeting Minutes Details</div>
		</div>
		<div class="portlet-body">
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
					 <table class="table table-striped table-bordered table-hover table-full-width" id="sample_5">
							<thead>
								<tr>
									<th>School Name</th>
									<th>Date- Time</th>
									<th>Venue</th>
									<th>Meeting Minutes</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="objectList">
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
										<a data-toggle="modal" title="Audio" href="#audioDiv" onclick="javascript:PopupViewAudioDetials(<s:property value="%{meetingDetailsId}" />,'audio');"> <i class="fa fa-volume-up"></i></a>&nbsp;&nbsp; |&nbsp;&nbsp;
							<a data-toggle="modal"  title="Video" href="#audioDiv" onclick="javascript:PopupViewAudioDetials(<s:property value="%{meetingDetailsId}" />,'video');"> <i class="fa fa-video-camera"></i></a>&nbsp;&nbsp; |&nbsp;&nbsp;
							<a data-toggle="modal"  title="Documents" href="#audioDiv" onclick="javascript:PopupViewAudioDetials(<s:property value="%{meetingDetailsId}" />,'application');"><i class="fa fa-book"></i></a>
									</td>
								</tr>
								</s:iterator>
								<!-- <tr>
									<td>Nabi Crescent Matriculation School, URT Secondary School...<a  data-toggle="modal" href="#SchoolNamesDiv" title="Audio">Read More</a> </td>
									<td>05-Jan-2015 - 10:00 AM</td>
									<td>Bangalore</td>
									<td><a  data-toggle="modal" href="#audio" title="Audio"><i class="fa fa-volume-up"></i> </a>&nbsp;&nbsp; |&nbsp;&nbsp; <a  data-toggle="modal" href="#video" title="Video"><i class="fa fa-video-camera"></i> </a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://c3094222.r22.cf0.rackcdn.com/les_4509_Calories_in_Broccoli_Salad.pdf" target="_new">&nbsp;&nbsp;<i class="fa fa-book"></i></a> </td>
								</tr>
								<tr>
									<td>URT Secondary School</td>
									<td>15-Sep-2014 - 08:40 AM</td>
									<td>Mysore</td>
									<td><a  data-toggle="modal" href="#audio" title="Audio"><i class="fa fa-volume-up"></i> </a>&nbsp;&nbsp; |&nbsp;&nbsp; <a  data-toggle="modal" href="#video" title="Video"><i class="fa fa-video-camera"></i> </a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://c3094222.r22.cf0.rackcdn.com/les_4509_Calories_in_Broccoli_Salad.pdf" target="_new">&nbsp;&nbsp;<i class="fa fa-book"></i></a> </td>
								</tr>
								<tr >
									<td>Nabi Crescent Matriculation School, Belarus T.M High School...<a data-toggle="modal" href="#SchoolNamesDiv" title="Audio">Read More</a></td>
									<td>04-Feb-2015 - 12:00 PM</td>
									<td>Kadapa</td>
									<td><a  data-toggle="modal" href="#audio" title="Audio"><i class="fa fa-volume-up"></i> </a>&nbsp;&nbsp; |&nbsp;&nbsp; <a  data-toggle="modal" href="#video" title="Video"><i class="fa fa-video-camera"></i> </a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://c3094222.r22.cf0.rackcdn.com/les_4509_Calories_in_Broccoli_Salad.pdf" target="_new">&nbsp;&nbsp;<i class="fa fa-book"></i></a> </td>
								</tr> -->
							</tbody>
						</table>
</s:if>
<s:else><div class="alert alert-info"> Currently there are no meeting minutes details .</div></s:else>
</div>
</div>
</div>
<div class="col-md-12">
	 <div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i>Budget Request</div>
		</div>
		<div class="portlet-body">
		<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
				<thead>
					<tr>
						<th>Request Date</th>
						<th>School Name</th>
						<th>Total</th>
						<th>Manager</th>
						<th>Mobile</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="tempList1">
					<tr>
						<td><s:property value="userFormattedCreatedDate"/></td>
						<td><s:property value="organization"/></td>
						<td><i class="fa fa-rupee"></i><s:property value="totalBudgetAmount"/></td>
						<td><s:property value="fullName"/></td>
						
						<td><s:property value="mobileNumber"/></td>
						<td>
							<s:url id="urlAcceptOrRejectLinks" action="ajaxViewBudgetDetails"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="budgetRequest.id" value="%{budgetRequestId}" />
							</s:url>
							<sj:a href="%{urlAcceptOrRejectLinks}" targets="mainContentDiv"
								cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
					    </td>
					</tr>
				</s:iterator>
				
					<%-- <tr>
						<td>28-Jan-2015</td>
						<td>Swaami Vivekananda Matric. Hr. Sec. School</td>
						<td> Mr.Narayana Swami </td>
						<td>9980054540 </td>
						<td>
							<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="tempString">Swaami Vivekananda Matric. Hr. Sec. School</s:param>
							</s:url>
							<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
								cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
					    </td>
					</tr>
					<tr >
						<td>07-Feb-2015</td>
						<td>URT Secondary School</td>
						<td> Mrs. Sunandha</td>
						<td>9980089564</td>
						<td>
							<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="tempString">URT Secondary School</s:param>
							</s:url>
							<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
								cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
					    </td>
					</tr>
					<tr >
						<td>09-Feb-2015</td>
						<td>Nabi Crescent Matriculation School</td>
						<td>Mr.Antony </td>
						<td>9980987456 </td>
						<td>
							<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="tempString">Nabi Crescent Matriculation School</s:param>
							</s:url>
							<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
								cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
					    </td>
						
					</tr>
					<tr >
						<td>10-Feb-2015</td>
						<td>Greenvally Matriculation Higher Secondary</td>
						<td> Mr.Jons </td>
						<td>9980089564 </td>
						<td>
							<s:url id="urlMyFavouriteLinks" action="ajaxBudgetView"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="tempString">Greenvally Matriculation Higher Secondary</s:param>
							</s:url>
							<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
								cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
					    </td>
					</tr> --%>
				</tbody>
			</table>
			</s:if>
			<s:else><div class="alert alert-info">No budget requests for this financial year.</div></s:else>
		</div>
	</div>	
</div>
<div class="col-md-12">
	 <div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i>Budget Accepts / Rejects</div>
		</div>
		<div class="portlet-body">
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_4">
				<thead>
					<tr>
						<th>Date</th>
						<th>School Name</th>
						<th>Total</th>
						<th>Manager</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				
				<s:iterator value="tempList">
					<tr>
						<td><s:property value="userFormattedCreatedDate"/></td>
						<td><s:property value="organization"/></td>
						<td><i class="fa fa-rupee"></i>&nbsp;<s:property value="totalBudgetAmount"/></td>
						<td><s:property value="fullName"/></td>
						<td><s:property value="budgetStatusDesc"/></td>
						
						<td>
							<s:url id="urlAcceptOrRejectLinks" action="ajaxViewBudgetDetails"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="budgetRequest.id" value="%{budgetRequestId}" />
							</s:url>
							<sj:a href="%{urlAcceptOrRejectLinks}" targets="mainContentDiv"
								cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
					    </td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			</s:if>
			<s:else><div class="alert alert-info">No budgets accepts / rejects for this financial year.</div></s:else>
		</div>
	</div>	
</div>
<div class="col-md-12">
	<!-- BEGIN CHART PORTLET-->
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-bar-chart font-green-haze"></i>
				<span class="caption-subject bold uppercase font-green-haze"><s:property value="financialYear.yearName"/> Budget</span>
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
<div id="SchoolNamesDiv" class="modal fade" tabindex="-1" data-width="760">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			View School Names
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped table-bordered table-hover table-full-width" >
							<thead>
								<tr>
									<th>School Name</th>
									<th>Contact Number</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Nabi Crescent Matriculation School</td>
									<td>9980054540</td>
								</tr>
								<tr>
								<td>URT Secondary School</td>
									<td>9956987440</td>
								</tr>
								<tr >
								<td>Akshara International School</td>
									<td>9980879440</td>
								</tr>
								<tr >
								<td>Federal Public School (East)</td>
									<td>9980561230</td>
								</tr>
							</tbody>
						</table>
						
						<br/>
						 <br/>
						<br>

					</div>
				</div>
		</div>
	</div>
</div>
<div id="audioDiv"></div>
<script Language="Javascript" type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Secretary Dashboard");
		ChartsAmcharts.init();
		TableAdvanced.init();
		
		var custId = '<s:property value="custId"/>';
		var financialYearId = '<s:property value="tempId"/>';
		
		getSchoolBudgetDetails(custId,financialYearId);
	});
	function getSchoolBudgetDetails(custId,financialYearId){
		var str="";
		var url ;
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
