<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-globe"></i><s:property value="financialYear.yearName"/> Budget</div>
	</div>
	<div class="portlet-body">
		<div id="homeBudgetDataChatsDivId" class="chart" style="height: 400px;">
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
<script type="text/javascript">
	$(document).ready(function() {
		getSchoolBudgetDetails();
		ChartsAmcharts.init();
		var financialYearId = '<s:property value="tempId2"/>';
		getSchoolBudgetDetails(financialYearId);
	});
function getSchoolBudgetDetails(financialYearId){
	var str="";
	var url ;
	 url = jQuery.url.getChatURL("/admin/ajaxGetSchoolBudgetDetailsJsonByCustId.do?financialYear.id="+financialYearId);
 	$('#homeBudgetDataChatsDivId').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
$.ajax( {
	url : url,
		cache : false,
		dataType : 'json',
		success : function(response) {
		if(isNonEmpty(response))
		{
			var data = response.budgetRequestData;
			if (data.length > 0) {
					
				var chart = AmCharts.makeChart("homeBudgetDataChatsDivId", {
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

			    $('#homeBudgetDataChatsDivId').closest('.portlet').find('.fullscreen').click(function() {
			        chart.invalidateSize();
			    });
			}
			 
			$('#homeBudgetDataChatsDivId').html(chart);
		  }else{
		  	$('#homeBudgetDataChatsDivId').html('<div class="alert alert-info">There is no budget request found for this financial year.</div>');
		  }
        }
		});
}
</script>