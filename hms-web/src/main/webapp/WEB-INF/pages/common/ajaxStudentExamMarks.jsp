<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:if test="%{tempBoolean}">
		<fieldset id="stepTcsss">
			<jsp:include page="/common/messages.jsp" />
				<div class="examTabBorder" align="right">
					<s:url id="stepAttendance" action="ajaxDownloadScorecardFromUserFiles" 
						namespace="/common" includeParams="all" escapeAmp="false">
						<s:param value="tempString" name="tempString"></s:param>
					</s:url>
					<sj:a id="stepAttendance" href="%{stepAttendance}" cssClass="linkRight"
						title="Score Card Download" targets="Blank">Download Score Card</sj:a>
					<!--<a href='${pageContext.request.contextPath}/common/ajaxDownloadScorecardFromUserFiles.do?tempString=<s:property value="tempString"/>'
							title="Score Card Download" target="Blank" class="labelRight">Download Score Card</a>
				--></div>
		</fieldset>
	</s:if>
	<div id="studentMarksContent">
		<!-- in ie used this style -->
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
var url = jQuery.url.getChatURL("/exam/ajaxGetStudentPerformance.do?student.id="+<s:property value='student.id'/>+"&student.classSection.id="+<s:property value='student.classSection.id'/>);
/*var url = jQuery.url.getChatURL("/exam/ajaxGetStudentPerformance.do?student.id="+<s:property value='student.id'/>);*/
	$("#studentMarksContent").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		dataType : 'json',
		success : function(response) {
		if(isNonEmpty(response)){
			 chart = new Highcharts.Chart({
		      chart: {
		         renderTo: 'studentMarksContent',
		         defaultSeriesType: 'column'
		      },
		      title: {
		         text: response.title
		      },
		       subtitle: {
			         text: 'Compares subjects of different terms.'
			      },
		      xAxis: {
		         categories: response.categories,
		         title: {
			            text: 'Subjects'
			        },
			        labels: {
			            rotation: -45,
			            align: 'right',
			            style: {
			                font: 'normal 13px Verdana, sans-serif'
			            }
			         }
		      },
		      yAxis: {
		         title: {
			            text: 'Percentage'
			        },
			         max: 100,
			        tickInterval: 10
		      },
		      tooltip: {
		         formatter: function() {
		         	  return $.trim("<b>Exam Type : "+this.series.name+"</b><br/>"+response[this.series.name+this.x]);
		         }
		      },
		      
		      credits: {
		         enabled: false
		      },
		      series: response.series
		      
		   });
		}else{
			$("#studentMarksContent").html('<div class="alert alert-info">Student marks not imported</div>');
		}
		}
	});
});
</script>