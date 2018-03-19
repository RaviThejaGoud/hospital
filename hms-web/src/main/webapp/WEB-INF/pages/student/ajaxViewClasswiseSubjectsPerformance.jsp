<%@ include file="/common/taglibs.jsp"%>
<div id="commonTabContent" class="grid_13">
	<div id="commonTabWrapper">
		<div id="commonStep">
		<form action="">
				<div class="grid_6">
					<s:if test="%{examTypeList != null && !examTypeList.isEmpty()}">
						<s:select list="examTypeList" listKey="id" listValue="examType"
							id="examtype" label="Select Exam Type" theme="css_xhtml"
							name="examTypeId">
						</s:select>
					</s:if>
					<s:else>
						No exam types available for this class.
					</s:else>
				</div>
				<div class="grid_6">
					<s:if test="%{subjectsList != null && !subjectsList.isEmpty()}">
						<s:select list="subjectsList" id="subjectName" listKey="name"
							listValue="name" label="Select Subject" name="subjectName"
							theme="css_xhtml"
							onchange="javascript:ajaxGetFSforSubjectwisePerformance();">
						</s:select>
					</s:if>
					<s:else>
		    			No Subjects available for this class.
		    		</s:else>
				</div>
				<div id="classWiseSubjectPerformance" class="grid_12">
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	ajaxGetFSforSubjectwisePerformance();
});
function ajaxGetFSforSubjectwisePerformance() {
	var examType = $('#examtype').val();
	var subjectName = $('#subjectName').val();
	if(isNonEmpty(examType) && isNonEmpty(subjectName)){
		chart = new Highcharts.Chart({
      chart: {
         renderTo: 'classWiseSubjectPerformance'
      },
      title: {
         text: 'Combination chart'
      },
      xAxis: {
         categories: ['Telugu']
      },
      tooltip: {
         formatter: function() {
            var s;
            if (this.point.name) { // the pie chart
               s = ''+
                  this.point.name +': '+ this.y +' fruits';
            } else {
               s = ''+
                  this.x  +': '+ this.y;
            }
            return s;
         }
      },
      series: [{
         type: 'column',
         name: 'Jane',
         data: [2]
      }, {
         type: 'line',
         name: 'Average',
         data: [['Telugu', 1.11], ['Telugu', 1.11]]
      }]
   });
	}
}
</script>
