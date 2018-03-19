<%@ include file="/common/taglibs.jsp"%>
<div id="commonTabContent" class="col-md-12">
	<div id="myStudentsAttendancediv">
	</div>
</div>
<script LANGUAGE="JavaScript">
    $(document).ready(function(){
    		var url = jQuery.url.getChatURL("/staff/ajaxMyClassViewAttendence.do");
    		$('#myStudentsAttendancediv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			dataType : 'json',
			success : function(response) { 
			if(isNonEmpty(response)){
				chart = new Highcharts.Chart({
				      chart: {
				         renderTo: 'myStudentsAttendancediv',
				         defaultSeriesType: 'column'
				      },
				      title: {
				         text: response.title
				      },
				      xAxis: {
				         categories: response.categories
				      },
				      yAxis: {
				         min: 0,
				         max:100,
				         title: {
				            text: 'Percentage'
				         },
				         stackLabels: {
				            enabled: true,
				            style: {
				               fontWeight: 'bold',
				               color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
				            }
				         }
				      },
				      tooltip: {
				         formatter: function() {
				         	return '<b>'+ this.series.name +'</b><br/>'+
				               this.x +': '+ this.y +' Percentage';
				         }
				      },
				      plotOptions: {
				         column: {
				            stacking: 'normal',
				            dataLabels: {
				               enabled: true,
				               color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
				            }
				         }
				      },
				       series: response.series
   		});
   }else{
   	$('#myStudentsAttendancediv').html('<div class="alert alert-info">'+"Student attendance data is not available." +'</div>');
   }
			
	}
      });
   });
 </script>