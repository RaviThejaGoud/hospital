<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>TimeTable
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear=="N"}'>
							<li>
								<s:url id="doViewSubstitutionTimetable" action="ajaxDoViewSubstitutionTimetable" namespace="/timeTable">
								</s:url>
								<sj:a href="%{doViewSubstitutionTimetable}" targets="viewTimetableContDiv" data-toggle="tab">View Substitution Timetable</sj:a> 	
							 </li>
							<li>
								<s:url id="doSubstitutionTimetable" action="ajaxDoSubstitutionTimetable" namespace="/timeTable">
								</s:url>
								<sj:a href="%{doSubstitutionTimetable}" targets="viewTimetableContDiv" data-toggle="tab">Substitution Timetable</sj:a> 	
							 </li>
							 
							<li>
								<s:url id="viewStaffwiseTimetable" action="ajaxDoViewStaffwiseTimetable" namespace="/timeTable">
								</s:url>
								<sj:a href="%{viewStaffwiseTimetable}" targets="viewTimetableContDiv" data-toggle="tab">View Staff wise Timetable</sj:a> 	
							 </li>
							  <li class="active">
								<s:url id="urlClasswiseTimetable" action="ajaxViewTimetable" namespace="/timeTable">
								</s:url>
								<sj:a id="classWisetimetable" href="%{urlClasswiseTimetable}" targets="mainContentDiv" data-toggle="tab">View Class wise Timetable</sj:a> 	
							 </li>
					    </s:if>
						 
					</ul>
					<div id="viewTimetableContDiv" class="tab-content">

						<div class="row">
							<div class="form-body">
								<s:if
									test="%{(studyClassList != null && !studyClassList.isEmpty())}">
									<s:if test='%{#session.previousYear=="N"}'>
										<div class="form-group">
											<label class="control-label col-md-2"> Select Class :
											</label>
											<div class="col-md-6">
												<s:select list="studyClassList" id="classSectionId"
													headerKey="" headerValue="- Select -"
													cssClass="form-control input-medium" name="classSectionId"
													listKey="id" listValue="classAndSection" theme="simple"
													onchange="javascript:getAjaxDoGetStudyClassTimetable(this.value);">
												</s:select>
											</div>
										</div>
									</s:if>

								</s:if>
								<s:else>
									<div class="alert alert-info">You have not created
										classes.</div>
								</s:else>
							</div>
						</div>

						<div id="viewTimetableDivId"></div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
 $("input:checkbox, input:radio:not('.toggle')").uniform(); 
});

function getAjaxDoGetStudyClassTimetable(studyClassId) {
	  	if (studyClassId == "") {
			alert("Please select the class.")
		} else {
			var pars = "studyClassId=" + studyClassId;
			$("#viewTimetableDivId")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/timeTable/ajaxViewStudyclassWiseTimetable.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#viewTimetableDivId").html(html);
				}
			});
		}
	}
	
</script>
