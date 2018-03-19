<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Subjects
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="chgClassOrder" action="ajaxChangeSubjectsOrder"
								namespace="/admin">
							</s:url>
							<sj:a id="chgClasOrder" href="%{chgClassOrder}"
								targets="subjectsContentDiv" data-toggle="tab">Change Subjects Order</sj:a>
						</li>
						<li>
							<s:url id="urlAddSubject" action="ajaxDoAddSubject"
								namespace="/admin">
								<s:param name="studySubject.id" value="0" />
							</s:url>
							<sj:a id="addSub" href="%{urlAddSubject}"
								targets="subjectsContentDiv" data-toggle="tab">Add Subject</sj:a>
						</li>
						<li class="active">
							<s:url id="viewSub" action="ajaxGetStudyClassSubjects"
								namespace="/admin">
							</s:url>
							<sj:a id="viewSub" href="%{viewSub}" targets="mainContentDiv"
								data-toggle="tab">View Subjects</sj:a>
						</li>
					</ul>
					<div id="subjectsContentDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include
							page="/WEB-INF/pages/admin/academic/class/ajaxViewStudyClassSubjects.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Subjects");
});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
    $(this).animate({ scrollTop: 0 }, 10);
});

</script>
 