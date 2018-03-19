<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-globe"></i>
			<s:if test="%{user.parent}">
				My Child Attendance
			</s:if>
			<s:else>
				My Attendance
			</s:else>
	    </div>
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
									onchange="javascript:getStudentAttendance(this.value);" />
							</div>
						</div>
						<div class="spaceDiv">&nbsp;</div><div class="spaceDiv">&nbsp;</div>
					</s:if>
				</s:if>
			  <div id="container" style="min-width: 310px; height: auto; margin: 0 auto"></div>
			  <div class="spaceDiv"></div>
			  <s:if test='%{anyTitle != null}'>
				  <div id="studentAtteDiv">
					  <a href="#" id="studentAtteDiv" class="btn green btn-xs">More Details</a>
				  </div>
			  </s:if>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/FusionCharts.js">
</script>

<script type="text/javascript">
		$('a#studentAtteDiv').click(function(){
			window.location.hash="target=ESR.ajaxify PCA";
			window.location.reload();
		});
         $(document).ready(function() {
         	var tempId=$('span#tempIdSpan').attr("class");
      		if(isNonEmpty(tempId)){
      			getStudentAttendance(tempId);
      		}
			else{
				getStudentAttendance('');
					
			}
         });
         function getStudentAttendance(studentId){
         		var str="";
         		var url ;
	    	     if(isNonEmpty(studentId))
					 url = jQuery.url.getChatURL("/student/ajaxGetStudentMonthwiseAttendance.do?tempId1="+studentId);
		          else
         	 		url = jQuery.url.getChatURL("/student/ajaxGetStudentMonthwiseAttendance.do");
         	 	$('#container').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				$.ajax( {
					url : url,
					cache : false,
					dataType : 'json',
					success : function(response) {
					if(isNonEmpty(response)){
						$('#container').highcharts({
					      chart: {
					         defaultSeriesType: 'column'
					      },
					      title: {
					         text: 'Monthwise Student Attendance'
					      },
		        	        xAxis: {
		        	            categories: response.categories,
		        	            crosshair: true
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
					         max: 31,
					         title: {
					            text: 'Days'
					         },
					         stackLabels: {
					            enabled: false,
					            style: {
					               fontWeight: 'bold',
					               color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					            }
					         }
					      },
					      tooltip: {
						         formatter: function() {
						            return '<b>'+ this.x +'</b><br/>'+
						                this.series.name +': '+ this.y;
						         }
						      },
					       series: response.series
					   });
						 $('#studentAtteDiv').show();
					  }else{
					  	$('#container').html('<div class="alert alert-info">There is no attendance found for this student.</div>');
					  	$('#studentAtteDiv').hide();
					  }
			        }
         		});
         }
 </script>

