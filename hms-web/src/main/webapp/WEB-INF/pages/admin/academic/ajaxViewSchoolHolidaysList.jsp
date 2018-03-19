<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Holidays
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test="%{academicYear != null && academicYear != empty}">
							<s:if test='%{#session.previousYear=="N"}'>
							<s:if test='%{user.IsSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
								<li>
									<s:url id="urlImportHolidaysDetails1"
										action="ajaxDoImportHolidaysExcelSheet" namespace="/admin" />
									<sj:a href="%{urlImportHolidaysDetails1}"
										targets="stepHolidays" data-toggle="tab">Import Holidays</sj:a>
								</li>
								<li>
									<s:url id="urlDownloadHolidaysDetails"
										action="ajaxDownloadHolidaysDetails" namespace="/admin" />
									<sj:a href="%{urlDownloadHolidaysDetails}"
										targets="stepHolidays" data-toggle="tab">Edit Holidays</sj:a>
								</li>
								<li>
									<s:url id="doAddHolidays" action="ajaxDoAddNewSchoolHolidays"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="holidayId" value="0" />
										<s:param name="academicYearId"
											value="%{#session.academicYear}" />
									</s:url>
									<sj:a href="%{doAddHolidays}" data-toggle="tab"
										targets="stepHolidays" button="false">Add Holidays</sj:a>
								</li>
							</s:if>
							</s:if>
						</s:if>
						<li class="active">
							<s:url id="viewHolidays" action="ajaxViewSchoolSettingsHolidays"
								namespace="/admin">
							</s:url>
							<sj:a id="viewHolidays" href="%{viewHolidays}"
								targets="mainContentDiv" data-toggle="tab">View Holidays</sj:a>
						</li>
					</ul>
					<div id="stepHolidays" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:if test="%{academicYear != null && academicYear != empty}">
							<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">
											<span class="required">*</span>Select ClassName :
										</label>
										<div class="col-md-3">
											<s:select list="studyClassList" listKey="id"
												listValue="classAndSection" id="dropDownClassId"
												cssClass="form-control input-medium required" onchange="javascript:getHolidaysForThisClass(this.value);"
												name="selectedId">
											</s:select>
										</div>
									</div>
								</div>
							 </s:if>
							 <div>&nbsp;</div>
							 <div id="classHolidaysDiv"> 
								 <jsp:include page="/WEB-INF/pages/admin/academic/ajaxViewSchoolHolidays.jsp" />
							  </div>
						</s:if> 
						<s:else>
							<div class="alert alert-info">
								Please add academic planner details. Then only you can add or
								view school holidays.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var selectBox = $('#dropDownClassId').val();
	if(isNonEmpty(selectBox)){
		getHolidaysForThisClass(selectBox);
	}
	changePageTitle('Manage School Holidays');
});
function getHolidaysForThisClass(selectBox) {
	if (isNonEmpty(selectBox)) {
		var url = jQuery.url.getChatURL("/admin/ajaxViewSchooHolidaysByClassId.do");
		var classId = selectBox;
		$("#classHolidaysDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "classId=" + classId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#classHolidaysDiv").html(html);
			}
		});
	}
}
</script>
