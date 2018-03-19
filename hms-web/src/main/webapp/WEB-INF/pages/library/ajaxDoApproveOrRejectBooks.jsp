<%@ include file="/common/taglibs.jsp"%>
<div class="row-fluid">
	<div class="span12">
			<div class="portlet-body">
				<div class="tabbable tabbable-custom">
					<div id="studentLibraryContent">
						<jsp:include
							page="/WEB-INF/pages/library/ajaxSearchRequestBook.jsp" />
						<div id="resultsDiv">
							<div id="schoolBooksList">
								<div class="commomnTabs">
									<jsp:include page="/common/messages.jsp" />
								</div>
								<jsp:include
									page="/WEB-INF/pages/library/ajaxAllRenewalRequestedbooks.jsp" />
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
</div>
<script type="text/javascript">
	changePageTitle("Online Request Books");
	TableEditable.init();
	$('#urlSchoolWideBooksLink').addClass('on');
</script>
