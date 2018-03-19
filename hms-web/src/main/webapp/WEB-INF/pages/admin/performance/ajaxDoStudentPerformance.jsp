<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Student Performance
				</div>
			</div>
			<div class="portlet-body">
<div class="form-body">
<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
	<s:form action="#" theme="simple" id="studentPerfromanceDetails" cssClass="form-horizontal"  onsubmit="return false;">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required"> * </span>Select Class :
			</label>
			<div class="col-md-3">
				<s:select list="studyClassList" listKey="id" listValue="classAndSection"
					cssClass="required form-control input-medium" 
					theme="simple" name="classId" headerKey="0"
					headerValue="- Select Class Name-" id="className"
					onchange="javascript:getClassStudents(this.value);">
				</s:select>
				
			</div>
		</div>
		<div id="studentNameDiv" />
		
		<div class="row" id="reportTypeDiv" hidden="true;">
			<label class="control-label col-md-2">
				<span class="required"> * </span>
				Performance By &nbsp;: 			
			</label>
			<div class="col-md-3">
			<div class="radio-list">
				<label class="radio-inline"> 
		   			<input type="radio"	checked="checked" value="E"	onclick="getClassExamTypes();" name="reportType" id="reportType">
						&nbsp;&nbsp;Exam Type &nbsp;&nbsp;
				</label> 
				<label class="radio-inline"> 
					<input type="radio"	value="S" onclick="getClassSubjects();"name="reportType" id="reportType"> 
			 		&nbsp;&nbsp;Subjects
				</label>
			</div>
			</div>
	</div>
	<div class="spaceDiv"></div>
	<div id="examTypesDiv"/>
	
	<div class="form-actions fluid" id ="submitDiv">
		<div class="col-md-offset-3 col-md-9">
			<s:submit type="submit small" value="Generate Chart"	cssClass="submitBt btn blue long Excel generateReport"	title="Generate Chart"	onclick="javaScript:generateStudentPerformance();" />
		</div>
	</div>
	
	</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no Class available.
		</div>
	</s:else>
</div>
</br>
<div class="spaceDiv"></div>
<div class="spaceDiv"></div>
<div id="container"></div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Student Performance");
	$("div#submitDiv").hide();
	$('#reportTypeDiv').hide();
	$("input[name=examTypesChkBoxes]").click(function() {
			if ($("input[name=examTypesChkBoxes]:unchecked").length > 0) {
				$(".allExamTypes").parent('span').removeClass("checked");
					$(".allExamTypes").attr("checked", false);
				} else {
					$(".allExamTypes").parent('span').addClass("checked");
					$(".allExamTypes").attr("checked", true);
				}
			});
});
 	 

function getClassExamTypes() {
	$('#submitDiv').hide();
	$('#examTypesDiv').hide();
	$('#subjectListDiv').hide();
	$('#container').hide();
	$('input:radio[name=reportType][value=E]').attr('checked', true);
	var classId = $('#className').val();
	if (isNonEmpty(classId)) {
		$('#examTypesDiv') .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "classId=" + classId;
		$.ajax( { 
				url : jQuery.url.getChatURL("/exam/ajaxGetClassExamTypesDetails.do"),
				cache : true,
				data : pars,
				success : function(response) {
					$('#examTypesDiv').html(response);
					$('#submitDiv').show();
					$('#examTypesDiv').show();
					$('#reportTypeDiv').show();
					$('#examTypeListDiv').show();
					
				}
			});
	
	}
}

function getClassSubjects() {
	$('#submitDiv').hide();
	$('#examTypesDiv').hide();
	$('#examTypeListDiv').hide();
	$('#container').hide();
	var classId = $('#className').val();
	if (isNonEmpty(classId)) {
		$('#examTypesDiv') .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "classId=" + classId;
		$.ajax( { 
				url : jQuery.url.getChatURL("/exam/ajaxGetClassSubjectDetails.do"),
				cache : true,
				data : pars,
				success : function(response) {
					$('#examTypesDiv').html(response);
					$('#submitDiv').show();
					$('#examTypesDiv').show();
					$('#reportTypeDiv').show();
					$('#subjectListDiv').show();
				}
			});
	
	}
}
function getClassStudents(classId) {
	$('#submitDiv').hide();
	$('#examTypesDiv').hide();
	$('#reportTypeDiv').hide();
	$('#container').hide();
	if (isNonEmpty(classId)) {
		$('#studentNameDiv') .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "classId=" + classId;
		$.ajax( { 
				url : jQuery.url.getChatURL("/exam/ajaxGetStudentsByClass.do"),
				cache : true,
				data : pars,
				success : function(response) {
					$('#studentNameDiv').html(response);
					$('#reportTypeDiv').hide();
				}
			});
	}
}


function checkAllExamTypes() {
	if ($(".allExamTypes").is(':checked')) {
		$("[name='examTypesChkBoxes']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='examTypesChkBoxes']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}

$("input[name=subjectChkBoxes]").click(function() {
	if ($("input[name=subjectChkBoxes]:unchecked").length > 0) {
	   $(".allSubjects").parent('span').removeClass("checked");
		$(".allSubjects").attr("checked", false);
	} else {
	    $(".allSubjects").parent('span').addClass("checked");
		$(".allSubjects").attr("checked", true);
	}
});
function selectAllSubjects() {
	if ($(".allSubjects").is(':checked')){
	    $("[name='subjectChkBoxes']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='subjectChkBoxes']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}

function generateStudentPerformance() { 
	var classId = 	$('#className').val();
	var studentId = $('#studentName').val();
	var reportType = 	$('input[name=reportType]:checked').val();
	if('E'== reportType)
	{
			if ($("input[name=examTypesChkBoxes]:checked").length > 0) {
					var examTypeIds = $("input[name=examTypesChkBoxes]:checked");
				var selectedExamTypeIds = '';
				if (examTypeIds.length > 0) {
					selectedExamTypeIds = '(';
					for ( var i = 0; i < examTypeIds.length; i++) {
						selectedExamTypeIds += examTypeIds[i].value + ', ';
					}
					selectedExamTypeIds += '0)';
				}
				getStudentPerformanceChart(selectedExamTypeIds,classId,studentId,reportType);
				return true;
			}else if ($("input[name=examTypesChkBoxes]:checked").length == 0) {
				alert("Please select at least one exam type");
				return false;
			} 
	}else if('S'== reportType)
	{
		if ($("input[name=subjectChkBoxes]:checked").length > 0) {
				var examTypeIds = $("input[name=subjectChkBoxes]:checked");
			var selectedExamTypeIds = '';
			if (examTypeIds.length > 0) {
				selectedExamTypeIds = '(';
				for ( var i = 0; i < examTypeIds.length; i++) {
					selectedExamTypeIds += examTypeIds[i].value + ', ';
				}
				selectedExamTypeIds += '0)';
			}
			getStudentMarksDetails(selectedExamTypeIds,classId,studentId,reportType);
			return true;
		}else if ($("input[name=subjectChkBoxes]:checked").length == 0) {
			alert("Please select at least one subject");
			return false;
		} 
}
}

function getStudentPerformanceChart(selectedExamTypeIds,classId,studentId,reportType) {
	var url = jQuery.url.getChatURL("/exam/ajaxStudentPerformanceChart.do?studentNumber="+studentId+"&selectedId="+selectedExamTypeIds +"&classId="+classId+"&reportType="+reportType);
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
		         text: 'Student Performance Comparision'
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
		    	   min:0,
		          max: 600,
		          tickInterval: 50,
		            title: {
		            text: 'Marks'
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
			$('#container').show();
		  }else{
		  	$('#container').html('<div class="alert alert-info">Student marks not submitted.</div>');
		  }
        }
		});
}
function getStudentMarksDetails(selectedExamTypeIds,classId,studentId,reportType){
	var url = jQuery.url.getChatURL("/exam/ajaxStudentPerformanceChart.do?studentNumber="+studentId+"&selectedId="+selectedExamTypeIds +"&classId="+classId+"&reportType="+reportType);
	$('#container').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
	url : url,
	cache : false,
	dataType : 'json',
	success : function(response) {
		if(isNonEmpty(response)){
		$('#container').highcharts({
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
		            text: 'SubjectMarks'
		        },
		        min:0,
		        max: 100,
		        tickInterval: 10
		        
		        
	      },stackLabels: {
	            enabled: false,
	            style: {
	               fontWeight: 'bold',
	               color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	            }
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
		$('#container').show();
		}else{
			$("#container").html('<div class="alert alert-info">Student marks not submitted.</div>');
		}
	}
});
} 
</script>
 