
<%@ include file="/common/taglibs.jsp"%>
<s:form id="searchStudentByNumber"
	action="generateProgressReportByClassWise" theme="simple"
	cssClass="form-horizontal" onsubmit="return generateExamTypeIds();"
	namespace="/exam">
	<s:hidden id="examTypeIds" name="anyTitle" />
	<s:hidden id="classSectionVal" name="studyClassId"></s:hidden>
	<s:hidden id="classIdVal" name="classId"></s:hidden>
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		<div class="panel-body col-md-12">
			<span class="label label-danger"> NOTE : </span>
			<div class="spaceDiv"></div>
			<ul>
				<li>Please create Subject grades in Examination Section -> Grades & Exam Types -> Add subject grades.</li>
				<li>Please create Exam Grades in Examination Section -> Grades & Exam Types -> Add exam grades.</li>
			</ul>
		</div>
		<div class="form-body">
			<jsp:include page="/common/messages.jsp" />
			<h4>Generate Students Progress Report</h4>
			<p>
				<span class="label label-danger"> NOTE : </span>&nbsp;Select class
				then system shows marks uploaded exam types then click on 'Generate
				Report'.
			</p>
			<!--<div class="row">
				<div class="col-md-5">
					<div class="form-group">
						<label class="col-md-7 control-label">
							Select Type :
						</label>
						<div class="col-md-5">
							<div class="radio-list">
								<label class="radio-inline">
									<input type="radio" name="selectType" value="DT"
										onclick="checkTemplates(this.value);" checked="checked">
									Dynamic Template
								</label>
								<label class="radio-inline">
									<input type="radio" name="selectType" value="ST"
										id="checkSearchBox" onclick="checkTemplates(this.value);"
										class="radio">
									Static Template
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			-->
			<div class="row">
				<div class="col-md-5">
					<div class="form-group">
						<label class="control-label col-md-7"> <span
							class="required">*</span>Select Class :
						</label>
						<div class="col-md-5">
							<s:select list="studyClassList"
								listKey="classSectionIdAndClassId" listValue="classAndSection"
								id="classes" cssClass="required form-control input-medium small"
								name="classSectionId" headerKey="" headerValue="- Select -"
								onchange="javascript:onClassChange(this.value);">
							</s:select>
						</div>
					</div>
				</div>
			</div>
			<div id="examTypesContent" class="row"></div>
			<div id="ExamingDates">
				<div class="row">
					<div class="col-md-5">
						<div class="form-group">
							<label class="control-label col-md-7"> <span
								class="required">*</span> Exam Date From :
							</label>
							<div class="col-md-5">
								<div data-date-start-date="" data-date-end-date="+0d"
									data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="startDate" readonly="readonly"
										name="startDate" onchange="datesValidation()"
										class="required form-control fdate" /> <span
										class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-5">
						<div class="form-group">
							<label class="control-label col-md-7"> <span
								class="required">*</span> To Date :
							</label>
							<div class="col-md-5">
								<div data-date-start-date="" data-date-end-date="+0d"
									data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="endDate" readonly="readonly"
										name="endDate" onchange="datesValidation()"
										class="required form-control fdate" /> <span
										class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-6 col-md-9">
						<s:submit value="Generate Report" cssClass="submit long btn blue"
							cssStyle="float:none;" />
						<s:url id="urlViewScorCardGen"
							action="ajaxManageScorecardGeneration" includeParams="all"
							escapeAmp="false" namespace="/exam">
						</s:url>
						<sj:a href="%{urlViewScorCardGen}"
							cssClass="cancelButton btn default" indicator="indicator"
							targets="mainContentDiv">Cancel</sj:a>
					</div>
				</div>
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently there are no classes
			assigned for you.</div>
	</s:else>
</s:form>
<div id="myStudentList"></div>
<script type="text/javascript">
	FormComponents.init();
	$("input:checkbox, input:radio").uniform();
	changePageTitle("Generate Students Progress Report");
	$(document).ready(function() {
		changePageTitle("Marks Card Generation");
		var classSectionAndClassNameId = $('#classes').val();
		if (isNonEmpty(classSectionAndClassNameId)) {
			onClassChange(classSectionAndClassNameId);
		}
		$("div#ExamingDates").show();
	});

	function onClassChange(classSectionAndClassNameId) {
		var examTypeId = $('.examTypeId').val();
		$('#examTypesContent')
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = '';
		if (isNonEmpty(classSectionAndClassNameId)) {
			pars = "classId=" + classSectionAndClassNameId.split(':')[0];
		}
		$.ajax({
			url : jQuery.url.getChatURL("/exam/ajaxGetClassExamTypesList.do"),
			cache : true,
			data : pars,
			success : function(response) {
				$('#examTypesContent').html(response);
			}
		});
	}

	function generateExamTypeIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0
				&& isNonEmpty($('#classes').val())
				&& (isNonEmpty($('input#startDate').val()) && isNonEmpty($(
						'input#endDate').val()))) {
			var classSectionAndClassId = $('#classes').val();
			$('#classSectionVal').val(classSectionAndClassId.split(':')[0]);
			$('#classIdVal').val(classSectionAndClassId.split(':')[1]);

			var examTypeIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedCommunityIds = '';
			if (examTypeIds.length > 0) {
				selectedExamTypeIds = '(';
				for (var i = 0; i < examTypeIds.length; i++) {
					selectedExamTypeIds += examTypeIds[i].value + ', ';
				}
				selectedExamTypeIds += '0)';
			}
			$("#examTypeIds").val(selectedExamTypeIds);
			return true;
		} else if (!isNonEmpty($('#classes').val())) {
			alert("Please select class.");
			return false;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one exam type");
			return false;
		} else {
			alert("Please select dates.");
			return false;
		}
	}
	function datesValidation() {
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
			startDate = Date.parse(startDate);
			endDate = Date.parse(endDate);
			if (endDate < startDate) {
				alert("Your end date is more than your start date.");
				$('#endDate').val("");
			}
		}
	}
	/*function checkTemplates(value) {
	 if(value=="DT"){
	 $("#datesDiv").show();
	 }else{
	 $("#datesDiv").hide();
	 }
	 }*/
</script>