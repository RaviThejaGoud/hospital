<%@ include file="/common/taglibs.jsp"%>
<div class="row"  >
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
								<s:url id="urlSchoolEditBooksLink" action="ajaxDoEditSchoolBooks" namespace="/library"/>
								<sj:a href="%{urlSchoolEditBooksLink}"
									targets="studentLibraryContentDiv" indicator="indicator" data-toggle="tab">Edit Books</sj:a>
							</li>
							
							<!--<li>
								<a 
								href="${pageContext.request.contextPath}/library/ajaxDownloadTemplateBooks.do?anyId='AG'&anyTitle=data"
								id="libraryDownloadLink">Download Books Excel</a>
							</li>
							--><li>
								<s:url id="urlSchoolImportBooksLink" action="ajaxDoImportSchoolBooks" namespace="/library"/>
								<sj:a href="%{urlSchoolImportBooksLink}"
									targets="studentLibraryContentDiv" indicator="indicator" data-toggle="tab">Import Books</sj:a>
							</li>
							<!--<li>
								<s:url id="urlSchoolWideBooksLink"
									action="ajaxDoAddSchoolWideBooks" />
								<sj:a href="%{urlSchoolWideBooksLink}"
									targets="studentLibraryContent" indicator="indicator" data-toggle="tab">Add Book</sj:a>
							</li>-->
							<li class="active">
								<s:url id="viewBookResults" action="ajaxLibraryHome"	
									namespace="/library">
								</s:url>
								<sj:a href="%{viewBookResults}" targets="mainContentDiv"
									data-toggle="tab">
										View</sj:a>
							</li>
						</s:if>
					</ul>
					<div id="studentLibraryContentDiv" class="tab-content">
						<jsp:include page="/WEB-INF/pages/library/ajaxSearchWords.jsp" />
						<div id="resultsDiv">
							<div id="schoolBooksList">
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
	$(document).ready(function(){
		changePageTitle("Stock Maintenance");
		$('#urlSchoolWideBooksLink').addClass('on');
		//$(".go-top").click();

	});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
