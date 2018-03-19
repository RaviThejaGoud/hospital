<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Student Optional Subject
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li class="active"><s:url
								action="ajaxDoSelectStudentOptionalSubject"
								id="ajaxDoSelectStudentOptionalSubject" namespace="/student"
								includeParams="all" escapeAmp="false">
							</s:url> <sj:a href="%{ajaxDoSelectStudentOptionalSubject}"
								targets="mainContentDiv">View Student Optional Subject</sj:a></li>
					</ul>
					<div class="tab-content">
						<jsp:include page="/WEB-INF/pages/student/optionalSubject/ajaxDoViewSelectStudentOptionalSubject.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>