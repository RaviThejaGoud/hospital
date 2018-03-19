<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Search Library Books
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<li>
								<s:url id="urlSchoolImportBooksLink"
									action="ajaxDoImportSchoolBooks" namespace="/library"/>
								<sj:a href="%{urlSchoolImportBooksLink}"
									targets="studentLibraryContent" indicator="indicator" data-toggle="tab">Import Books</sj:a>
							</li>
							<li class="active">
								<sj:a id="viewBooks" href="%{viewBooks}"
									targets="mainContentDiv" data-toggle="tab">View</sj:a>
							</li>
						</s:if>
					</ul>
					<div id="studentLibraryContent" class="tab-content">
						<jsp:include page="/WEB-INF/pages/library/ajaxSearchWords.jsp" />
						<div id="resultsDiv">
							<div id="schoolBooksList">
								<jsp:include page="/common/messages.jsp" />
								<jsp:include
									page="/WEB-INF/pages/library/sendSchoolWideBooksList.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Stock Maintenance");
$('#urlSchoolWideBooksLink').addClass('on');
</script>
