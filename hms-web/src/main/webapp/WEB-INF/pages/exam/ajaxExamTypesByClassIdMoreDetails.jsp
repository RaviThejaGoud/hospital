<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-6">
<s:if test='%{viewStudentsLatestExamMarksDetailsList != null && !viewStudentsLatestExamMarksDetailsList.isEmpty()}'>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Select Exam Type :
			</label>
			<div class="col-md-6">
				<div class="checkbox-list">
				<span id="selectedEexamIdSpan" class="<s:property value='tempId1'/>"></span>
				<span id="selectedStudyClassIdSpan" class="<s:property value='tempId2'/>"></span>
					<s:select name="examTypes" list="viewStudentsLatestExamMarksDetailsList" theme="simple"
						listKey="examTypeId" listValue="examTypeName" id="examTypeIds" headerKey="tempId1"  
						cssClass="form-control input-medium required"
						onchange="javascript:getClassWiseExamGraphDetails(this.value);">
					</s:select>
				</div>
			</div>
		</div>
	</s:if>
	<s:else>
	<div class="alert alert-info">
		Currently there are no examTypes.
	</div>
	</s:else>
	<%-- <s:if test='%{viewStudentMarksDetailsList != null && !viewStudentMarksDetailsList.isEmpty()}'>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Select Exam Type :
			</label>
			<div class="col-md-6">
				<div class="checkbox-list">
				<span id="selectedEexamIdSpan" class="<s:property value='tempId1'/>"></span>
				<span id="selectedStudyClassIdSpan" class="<s:property value='tempId2'/>"></span>
					<s:select name="examTypes" list="viewStudentMarksDetailsList" theme="simple"
						listKey="id" listValue="examType" id="examTypeId" headerKey="tempId1"  
						cssClass="form-control input-medium required"
						onchange="javascript:getClassWiseExamGraphDetails(this.value);">
					</s:select>
				</div>
			</div>
		</div>
	</s:if>
	<s:else>
	<div class="alert alert-info">
		Currently there are no examTypes.
	</div>
	</s:else> --%>
</div>
<div class="col-md-12">
<div class="spaceDiv">&nbsp;</div>
	<div id="moreClassWisePassAndFailGraphDiv"></div>
</div>
<script type="text/javascript">
         $(document).ready(function() {
        	//var classSectionId=$('span#studyClassIdSpan').attr("class");
         	var examTypeIds=$('span#selectedEexamIdSpan').attr("class");
      		if(isNonEmpty(examTypeIds)){
      			getClassWiseExamGraphDetails(examTypeIds);
      		}
         });
         
         function getClassWiseExamGraphDetails(examTypeIds){
        	 var classSectionId=$('span#selectedStudyClassIdSpan').attr("class");
        	// alert(classSectionId+"========"+examTypeId);
         		var url = jQuery.url.getChatURL("/exam/ajaxGetPassAndFailPersentByClassIdGraph.do?classSectionId="+classSectionId+"&tempId1="+examTypeIds);
         	 	$('#moreClassWisePassAndFailGraphDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				$.ajax( {
					url : url,
					cache : false,
					dataType : 'json',
					success : function(response) {
					if(isNonEmpty(response)){
						$('#moreClassWisePassAndFailGraphDiv').highcharts({ 
					        chart: {
					            plotBackgroundColor: null,
					            plotBorderWidth: null,
					            plotShadow: false,
					            type: 'pie'
					        },
					        title: {
					            text: 'Section Wise Exam Results'
					        },
					        tooltip: {
					            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
					        },
					        plotOptions: {
					            pie: {
					                allowPointSelect: true,
					                cursor: 'pointer',
					                dataLabels: {
					                    enabled: true,
					                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
					                    style: {
					                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
					                    }
					                }
					            }
					        },
					        series:  response
					    });
					  }else{
					  	$('#moreClassWisePassAndFailGraphDiv').html('<div class="alert alert-info">'+'There are no latest marks.'+'</div>');
					  }
			        }
         		});
         }
 </script>