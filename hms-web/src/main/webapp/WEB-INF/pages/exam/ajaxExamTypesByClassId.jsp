<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-6">
	<s:if test='%{viewStudentsLatestExamMarksDetailsList != null && !viewStudentsLatestExamMarksDetailsList.isEmpty()}'>
		<div class="form-group">
			<label class="control-label col-md-6"> <span class="required">*</span>Select
				Exam Type :
			</label> 
			<div class="col-md-6">
				<div class="checkbox-list">
					<span id="examIdSpan" class="<s:property value='tempId1'/>"></span>
					<span id="classNameIdSpan" class="<s:property value='classId'/>"></span>
					<s:select name="examTypes" list="viewStudentsLatestExamMarksDetailsList" theme="simple"
						listKey="examTypeId" listValue="examTypeName" id="examTypeId"
						headerKey="tempId1" cssClass="form-control input-small required"
						onchange="javascript:getClassWiseExamGraphDetails(this.value);">
					</s:select>
				</div>
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently there are no examTypes.
		</div>
	</s:else>
</div>
<s:if test='%{viewStudentsLatestExamMarksDetailsList != null && !viewStudentsLatestExamMarksDetailsList.isEmpty()}'>
<div class="col-md-12">
	<div class="spaceDiv">&nbsp;</div>
	<div id="classWisePassAndFailGraphDiv"></div>
	<%-- <div id="moreClassWisePassAndFailPerformance">
				<s:url id="urlMoreLink" action="ajaxPassAndFailPersentGraphMoreDetails" namespace="/admin" />
				<sj:a href="%{urlMoreLink}" targets="mainContentDiv" cssClass="MSTI.ajaxify ACWMP">More Details</sj:a>
	</div> --%>
</div>
</s:if> 
<div id="classWisePassAndFailGraphDiv"></div>
<script type="text/javascript">
		/* $('div#moreClassWisePassAndFailPerformance').click(function(){
			window.location.hash="target=MSTI.ajaxify ACWMP";
			window.location.reload();
		}); */
		$('div#moreClassWisePassAndFailPerformance').click(function() {
			window.location.hash = "target=MSTI.ajaxify ACWMP";
			$('a#dashboard').parent('li').removeClass('active');
			$('li#manageStudent').addClass('open active');
			$('li#classWiseResult').addClass('active');
			$('li#classWiseResult a').click();
		});
         $(document).ready(function() {
         	var classId=$('span#classNameIdSpan').attr("class");
         	var examTypeId=$('span#examIdSpan').attr("class");
      		if(isNonEmpty(examTypeId)){
      			getClassWiseExamGraphDetails(examTypeId);
      		}
         });
         
         function getClassWiseExamGraphDetails(examTypeId){
        	 var classId=$('span#classNameIdSpan').attr("class");
        	// alert(classId+"========"+examTypeId);
         		var url = jQuery.url.getChatURL("/exam/ajaxGetPassAndFailPersentByClassIdGraph.do?tempId="+classId+"&tempId1="+examTypeId);
         	 	$('#classWisePassAndFailGraphDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				$.ajax( {
					url : url,
					cache : false,
					dataType : 'json',
					success : function(response) {
					if(isNonEmpty(response)){
				 
					    $('#classWisePassAndFailGraphDiv').highcharts({ 
					        chart: {
					            plotBackgroundColor: null,
					            plotBorderWidth: null,
					            plotShadow: false,
					            type: 'pie'
					        },
					        title: {
					            text: 'Class Wise Exam Results'
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
						$('#moreClassWisePassAndFailPerformance').show();
					  }else{
					  	$('#classWisePassAndFailGraphDiv').html('<div class="alert alert-info">'+'There are no latest marks.'+'</div>');
					  	$('#moreClassWisePassAndFailPerformance').hide();
					  }
			        }
         		});
         }
 </script>