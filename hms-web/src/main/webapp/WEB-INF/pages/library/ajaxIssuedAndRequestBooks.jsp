<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Online Requested Books
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
				<s:if test='%{#session.previousYear == "N"}'>
							<ul class="nav nav-tabs">
								<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolLibrarian == "Y" || user.isSchoolClerk == "Y" || user.isSchoolTeacher=="N"}'>
								<li>
									<s:url id="urlRenewalBooksLink" action="ajaxDoApproveOrRejectBooks" namespace="/library" includeParams="all" escapeAmp="false">
									<s:param name="anyId">RN</s:param>
									</s:url>
									<sj:a href="%{urlRenewalBooksLink}" targets="studentLibraryContent" indicator="indicator" data-toggle="tab">
										View Renewal Requests</sj:a>
								</li>
								</s:if>
								<li class="active">
									<s:url id="urlRequestBooksLink" action="ajaxDoIssuedAndRequestBooks" namespace="/library" includeParams="all" escapeAmp="false">
									<s:param name="anyId">RR</s:param>
									</s:url>
									<sj:a href="%{urlRequestBooksLink}" targets="mainContentDiv" indicator="indicator" data-toggle="tab">
										View Requests </sj:a>
								</li>
							</ul>
					</s:if>
					<div class="tab-content" id="studentLibraryContent">
						<jsp:include
							page="/WEB-INF/pages/library/ajaxSearchRequestBook.jsp" />
					<div id="resultsDiv">
						<div id="schoolBooksList">
							<%@ include file="/common/messages.jsp"%>
							<jsp:include
								page="/WEB-INF/pages/library/ajaxAllRequestedbooks.jsp"></jsp:include>
						</div>
					   </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Online Request Books");
$('#urlSchoolWideBooksLink').addClass('on');
</script>

