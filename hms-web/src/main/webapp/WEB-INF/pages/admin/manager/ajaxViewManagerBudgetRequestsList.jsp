<%@ include file="/common/taglibs.jsp"%>
<div class="row ">
<div class="col-md-12">
<div class="spaceDiv"></div>
	<div class="spaceDiv"></div>
	 <div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i>Budget Request</div>
		</div>
		<div class="portlet-body">
		<s:if test="%{tempList!= null && !tempList.isEmpty()}">
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
				<thead>
					<tr>
						<th>Request Date</th>
						<th>Total Budget Amount</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="tempList">
					<tr>
						<td><s:property value="userFormattedCreatedDate"/></td>
						<td><s:property value="totalBudgetAmount"/></td>
						<td>
							<s:url id="urlMyFavouriteLinks" action="ajaxViewBudgetDetails"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="budgetRequest.id" value="%{id}" />
							</s:url>
							<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
								cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
					    </td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			</s:if>
			<s:else>
				<div class="alert alert-info">No budget requests for this financial year.</div>
			</s:else>
		</div>
	</div>	
</div>
<div class="col-md-12">
	 <div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i>Budget Accepts / Rejects</div>
		</div>
		<div class="portlet-body">
		<s:if test="%{tempList1!= null && !tempList1.isEmpty()}">
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_4">
				<thead>
					<tr>
						<th>Request Date</th>
						<th>Total Budget Amount</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="tempList1">
					<tr>
						<td><s:property value="userFormattedCreatedDate"/></td>
						<td><s:property value="totalBudgetAmount"/></td>
						<td><s:property value="budgetStatusDesc"/></td>
						<td>
							<s:url id="urlAcceptOrRejectLinks" action="ajaxViewBudgetDetails"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="budgetRequest.id" value="%{id}" />
							</s:url>
							<sj:a href="%{urlAcceptOrRejectLinks}" targets="mainContentDiv"
								cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
					    </td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			</s:if>
			<s:else>
				<div class="alert alert-info">No budget accepts / rejects  for this financial year. </div>
			</s:else>
		</div>
	</div>	
</div>
<div class="col-md-12">
	<!-- BEGIN CHART PORTLET-->
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-bar-chart font-green-haze"></i>
				<span class="caption-subject bold uppercase font-green-haze">2014-15 Budget</span>
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
	
	
</script>
