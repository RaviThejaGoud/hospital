<%@ include file="/common/taglibs.jsp"%>
<div id="addChildDiv">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i>
				Examination Performance
			</div>
			<s:if test="%{user.parent &&  studentsList.size > 1}">
				<div class="actions">
					<s:url id="urlAddChildren" action="ajaxDoAddChildren" namespace="/student"/>
					<sj:a href="%{urlAddChildren}" targets="addChildDiv" cssClass="btn default btn-xs">Add My children</sj:a>
				</div>
			</s:if>
		</div>
		<div class="portlet-body">
			<div id="site_statistics_content">
				<s:if test='%{studentsList != null && !studentsList.isEmpty()}'>
					<span id="tempIdSpan" class="<s:property value='student.id'/>"></span>
					<s:if test='%{studentsList.size > 1}'>
						<div class="form-group form-horizontal">
							<label class="control-label col-md-3">
								Student Name :
							</label>
							<div class="col-md-5">
								<s:select id="sectionId" list="studentsList" listKey="id"
									label="Student Name" listValue="studentNameAndUserName"
									cssClass="form-control" name="anyId" theme="simple"
									onchange="javascript:getStudentMarksDetails(this.value);" />
							</div>
						</div>
						<div class="spaceDiv">&nbsp;</div><div class="spaceDiv">&nbsp;</div>
					</s:if>
				</s:if>
				<div id="examMarksDiv"> </div>
				<div class="spaceDiv"></div>
				<div id="viewMyPerformance" >
					<a href="#" id="viewMyPerformance" class="btn green btn-xs">More Details</a>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/FusionCharts.js"> </script>

<script type="text/javascript">
		$('a#viewMyPerformance').click(function(){
			window.location.hash="target=ESR.ajaxify PE";
			window.location.reload();
		});
         $(document).ready(function() {
         	var tempId=$('span#tempIdSpan').attr("class");
      		if(isNonEmpty(tempId)){
      			getStudentMarksDetails(tempId);
      		}
			else{
				getStudentMarksDetails('');		
			}
         });
         function getStudentMarksDetails(studentId){
         		var str="";
         		var url ;
	    	     if(isNonEmpty(studentId))
					 url = jQuery.url.getChatURL("/exam/ajaxGetLatestStudentMarks.do?tempId="+studentId);
		          else
         	 		url = jQuery.url.getChatURL("/exam/ajaxGetLatestStudentMarks.do");
         	 	$('#examMarksDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				$.ajax( {
					url : url,
					cache : false,
					dataType : 'json',
					success : function(response) {
					if(isNonEmpty(response)){
						 chart = new Highcharts.Chart({
						      chart: {
						         renderTo: 'examMarksDiv',
						         defaultSeriesType: 'column'
						      },
						      title: {
						         text: response.title
						      },
						      subtitle: {
						        text: response.subtitle
						      },
						      xAxis: {
						         categories: response.categories,
						         labels: {
						            rotation: -45,
						            align: 'right',
						            style: {
						                font: 'normal 13px Verdana, sans-serif'
						            }
						         }
						      },
						      yAxis: {
						         min: 0,
						         max: response.max,
						         title: {
						            text: 'Marks'
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
							            return '<b>'+ this.x +'</b><br/>'+
							                this.series.name +': '+ this.y +'<br/>'+
							                'Total: '+ this.point.stackTotal;
							         }
							      }, 
						     /*  tooltip: {
						            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
						            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
						                '<td style="padding:0"><b>{point.y:.1f} marks</b></td></tr>',
						            footerFormat: '</table>',
						            shared: true,
						            useHTML: true
						        }, */
						       series: response.series
						   });
						   $('#viewMyPerformance').show();
					  }else{
					  	$('#examMarksDiv').html('<div class="alert alert-info">'+'There are no latest marks.'+'</div>');
					  	$('#viewMyPerformance').hide();
					  }
			        }
         		});
         }
 </script>

