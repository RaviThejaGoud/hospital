<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studyClassList != null && !studyClassList.isEmpty}">
	<div class="row form-horizontal">
		<div class="form-group">
			<label class="control-label col-md-2">
				Select Class :
			</label>
			<div class="col-md-3">
				<s:select list="studyClassList" listKey="id"
					listValue="classAndSection" theme="simple" id="classSectionId"
					cssClass="form-control" name="classId"
					onchange="javascript:onClassChange(this.value);">
				</s:select>
			</div>
		</div>
	</div>
	<div id="classSectionsAttendancediv"></div>
</s:if>
<s:else>
	<div class="alert aleer-info">
		There are no classes.
	</div>
</s:else>
<script LANGUAGE="JavaScript" type="text/javascript">
    $(document).ready(function(){
    		var classId=$("#classSectionId").val();
    		if(isNonEmpty(classId))
    				onClassChange(classId);
      });
      function onClassChange(classId){
      $('#classSectionsAttendancediv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
      var className=$("select[id='classSectionId'] option:selected").text();
      	var url = jQuery.url.getChatURL("/common/ajaxAllClassesStudentsAttendance.do?tempId="+classId+"&tempString="+className);
		$.ajax( {
			url : url,
			cache : false,
			dataType : 'json',
			success : function(response) {
			if(isNonEmpty(response.categories)){
				chart = new Highcharts.Chart({
				      chart: {
				         renderTo: 'classSectionsAttendancediv',
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
				      legend: {
				         align: 'right',
				         x: -100,
				         verticalAlign: 'top',
				         y: 20,
				         floating: true,
				         backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
				         borderColor: '#CCC',
				         borderWidth: 1,
				         shadow: false
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
				 $('#classSectionsAttendancediv').html('<div class="alert alert-info">No data found for this class.</div>');
			}
			}
         });
      }
 </script>