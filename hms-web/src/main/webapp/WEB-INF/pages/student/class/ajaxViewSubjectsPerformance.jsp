<%@ include file="/common/taglibs.jsp"%>
	<s:if test='%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}'>
		<span id="tempIdSpan" class="<s:property value='viewStudentPersonAccountDetails.studentId'/>"></span>
		<s:if test='%{viewStudentPersonAccountDetailsList.size > 1}'>
		<div class="form-group form-horizontal">
			<label class="control-label col-md-3">
				Student Name :
			</label>
			<div class="col-md-3">
				<s:select id="studentAccountId"
					list="viewStudentPersonAccountDetailsList" listKey="studentId"
					cssClass="form-control" listValue="idAndName" name="tempId"
					theme="simple"
					onchange="javascript:getStudentMarksDetails(this.value);" />
			</div>
		</div>
	</s:if>
	</s:if>
	<div class="spaceDiv"></div>
	<div class="spaceDiv"></div>
	<div class="spaceDiv"></div>
	<div class="spaceDiv"></div>
 <div id="studentTotalTermsComparison" style="min-width: 310px; height: 600px; margin: 0 auto"></div>
<script type="text/javascript">
      $(document).ready(function() {
      		var tempId=$('span#tempIdSpan').attr("class");
      		if(isNonEmpty(tempId))
				getStudentMarksDetails(tempId);
			else
				getStudentMarksDetails('');		
     });
     function getStudentMarksDetails(studentId){
			var url ;
    	     if(isNonEmpty(studentId))
				 url = jQuery.url.getChatURL("/exam/ajaxTermsWiseStudentMarksComparison.do?tempId="+studentId);
	          else
         	 	url = jQuery.url.getChatURL("/exam/ajaxTermsWiseStudentMarksComparison.do");
     		$("#studentTotalTermsComparison").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	     	$.ajax( {
			url : url,
			cache : false,
			dataType : 'json',
			success : function(response) {
				if(isNonEmpty(response)){
				$('#studentTotalTermsComparison').highcharts({
			      chart: {
			         renderTo: 'studentTotalTermsComparison',
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
			         	 return $.trim("<b>Exam Type : "+this.series.name+"</b><br/>" 
			         	  +response[this.series.name+this.x].slice(0,-1));
			         }
			      },
			      credits: {
			         enabled: false
			      },
			      series: response.series
			   });
				}else{
					$("#studentTotalTermsComparison").html('<div class="alert alert-info">Student marks not imported</div>');
				}
			}
		});
     } 
</script>
