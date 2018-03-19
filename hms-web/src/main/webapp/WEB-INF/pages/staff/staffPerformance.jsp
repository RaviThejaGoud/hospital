<%@ include file="/common/taglibs.jsp"%>
	<div id="classSectionsAttendancediv">
	</div>

<script type="text/javascript">
         $(document).ready(function() {
         changePageTitle("My Performance");
         	$("#classSectionsAttendancediv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
         	var staffId=$('.staffId').attr("id");
         	var url ;
         	if(isNonEmpty(staffId)){
         			 url = jQuery.url.getChatURL("/staff/ajaxGetLatestMySubjectsPerformance.do?staff.id="+staffId);
         		}else
         			 url = jQuery.url.getChatURL("/staff/ajaxGetLatestMySubjectsPerformance.do?staff.id=0");
				$.ajax( {
					url : url,
					cache : false,
					dataType : 'json',
					success : function(response) {
					if(isNonEmpty(response)){
					 if(isNonEmpty(response.categories)){
					 	chart = new Highcharts.Chart({
						chart: {
				        	 renderTo: 'classSectionsAttendancediv',
				            defaultSeriesType: 'column'
				      	},
				      title: {
				         text: 'My Subjects Performance'
				      },
				      xAxis: {
				         categories: response.categories
				      },
				      yAxis: {
				         min: 0,
				          max: 100,
				         title: {
				            text: 'Percentage'
				         },
				         stackLabels: {
				            enabled: false,
				            style: {
				               fontWeight: 'bold',
				               color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
				            }
				         }
				      },
				      /*legend: {
				         align: 'right',
				         x: -100,
				         verticalAlign: 'top',
				         y: 20,
				         floating: true,
				         backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
				         borderColor: '#CCC',
				         borderWidth: 1,
				         shadow: false
				      },*/
				      tooltip: {
				         formatter: function() {
				            return '<b>'+ this.x +'</b><br/>'+
				                this.series.name +': '+ this.y +'<br/>'+
				                'Total: '+ this.point.stackTotal;
				         }
				      },
			      plotOptions: {
			         column: {
			            stacking: 'normal',
			            dataLabels: {
			               enabled: false,
			               color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
			            }
			         }
			      },
			       series: response.series
				   });
				 }else{
				 	$("#classSectionsAttendancediv").html('<div class="alert alert-info">'+"Currently there is no data to display."+'</div>');
				 }
				}else
					$("#classSectionsAttendancediv").html('<div class="alert alert-info">'+"Currently there is no data to display."+'</div>');
				}
        		});
         });
      </script>
