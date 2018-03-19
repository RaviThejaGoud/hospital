<%@ include file="/common/taglibs.jsp"%>
<div class="row">
<div class="col-md-6">
	<s:if test="{examTypeList != NULL & !examTypeList.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-4">
				<span class="required">*</span>Select Exam Type :
			</label>
			<div class="col-md-6">
				<s:select list="examTypeList" listKey="id+'_'+maxMarks"
					id="examType" listValue="examType" cssClass="required form-control"
					name="examtype" onchange="javascript:getStaffPerformance();"
					theme="simple">
				</s:select>
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			No Subjects for this staff.
		</div>
	</s:else>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	getStaffPerformance();
});

function getStaffPerformance() {
	$("#subjectPerformanceAcrossSectionWise")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var fieldErrorString = '';
	var examType = $('#examType').val();
	var examTypeMaxMarks = '';
	if (isNonEmpty(examType)) {
		var ids = examType.split("_");
		if (isNonEmpty) {
			examType = ids[0];
			examTypeMaxMarks = ids[1];
		}
	}
	var classId = $("#className").val();
	var tempId = $('.staffId').attr("id");
	var staffAcId = $('.staffAccountId').attr("id");
	if (examType == null || examType == '') {
		fieldErrorString = fieldErrorString
				+ "<font style=\"color:red;font-weight:bold;\">Exam Type is required.<br /></font>";
	}
	if (classId == '' || classId == null) {
		fieldErrorString = fieldErrorString
				+ "<font style=\"color:red;font-weight:bold;\">Select Class is required.<br /></font>";
	}
	if (fieldErrorString != '') {
		document.getElementById('addSubjectFieldErrors').innerHTML = '<div class="alert alert-info">'+fieldErrorString+'</div>';
		document.getElementById('addSubjectFieldErrors').style.display = "block";
		$("#subjectPerformanceAcrossSectionWise").html("");
	} else {
		document.getElementById('addSubjectFieldErrors').innerHTML = "";
		document.getElementById('addSubjectFieldErrors').style.display = "none";
		var url = jQuery.url
				.getChatURL("/staff/ajaxSubjectPerformance.do?examType="
						+ examType + "&className.id=" + classId + "&staff.id="
						+ tempId + "&viewStudentMarksDetails.maxMarks="
						+ examTypeMaxMarks);
		$
				.ajax( {
					url : url,
					cache : false,
					dataType : 'json',
					success : function(response) {
						if (isNonEmpty(response)) {
							chart = new Highcharts.Chart(
									{
										chart : {
											renderTo : 'subjectPerformanceAcrossSectionWise',
											defaultSeriesType : 'column'
										},
										title : {
											text : 'Subjects Averages'
										},
										xAxis : {
											categories : response.categories
										},
										subtitle : {
											text : 'It compares averages of different section subjects.'
										},
										yAxis : {
											min : 0,
											max : response.maxMarks,
											title : {
												text : 'Average'
											}
										},
										/*legend: {
										   layout: 'vertical',
										   backgroundColor: Highcharts.theme.legendBackgroundColor || '#FFFFFF',
										   align: 'left',
										   verticalAlign: 'top',
										   x: 100,
										   y: 70,
										   floating: true,
										   shadow: true
										},*/
										tooltip : {
											formatter : function() {
												return '' + this.x + ': '
														+ this.y;
											}
										},
										plotOptions : {
											column : {
												pointPadding : 0.2,
												borderWidth : 0
											}
										},
										series : response.series
									});
						} else {
							$("#subjectPerformanceAcrossSectionWise").html('<div class="alert alert-info">'+"No data found."+'</div>');
						}
					}
				});
	}
}
</script>