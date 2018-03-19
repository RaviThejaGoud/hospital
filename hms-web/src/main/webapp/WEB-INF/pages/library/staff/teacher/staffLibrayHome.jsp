<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Library
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlSchoolWideBooksLink"
								action="ajaxDoStaffBooksHistory" namespace="/library" />
							<sj:a href="%{urlSchoolWideBooksLink}"
								targets="staffLibraryContent" indicator="indicator"
								data-toggle="tab">Booked History</sj:a>
						</li>
						<li class="active">
							<s:url id="staffLibrary" action="ajaxGetStaffLibrayHome"
								namespace="/library">
							</s:url>
							<sj:a href="%{staffLibrary}" targets="mainContentDiv"
								data-toggle="tab">View Books</sj:a>
						</li>
					</ul>
					<div id="staffLibraryContent" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include
							page="/WEB-INF/pages/library/staff/teacher/ajaxStaffLibraryDetails.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--<script type="text/javascript">
   	$('#library').addClass('current');
</script>-->