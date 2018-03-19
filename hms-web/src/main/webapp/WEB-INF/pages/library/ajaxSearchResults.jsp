<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-13">
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
			<thead>
				<tr>
					<th>
						Book Name
					</th>
					<th>
						Edition
					</th>
					<th>
						Author Name
					</th>
					<th>
						Subject Name
					</th>
					<th>
						Publisher
					</th>
					<th>
						Book Code
					</th>
					<th>
						Block & Rack Details
					</th>
					<th>
						Issue Book
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="objectList">
					<tr>
						<td>
							<s:property value="bookName" />
						</td>
						<td>
							<s:property value="bookEdition" />
						</td>
						<td>
							<s:property value="author" />
						</td>
						<td>
							<s:property value="subjectName" />
						</td>
						<td>
							<s:property value="publisher" />
						</td>
						<td>
							<s:property value="lableCode" />
						</td>
						<td>
							<s:if test='%{blockName!="" && blockName!=null}'>
								<s:property value="blockName" />
							</s:if> 
							<s:if test='%{rackName!="" && rackName!=null}'>
					 						& <s:property value="rackName" />
							</s:if> 
							<s:else>
										&  No Rack
							</s:else>
						</td>
						<td>
							<s:if test='%{issuedBooksCount!=0}'>
								<s:url id="urlIssueBooksLink"
									action="ajaxLibrarianIssueBookDetails" namespace="/library"
									includeParams="all" escapeAmp="false">
									<s:param name="tempId" value="%{bookTitleId}"></s:param>
								</s:url>
								<sj:a href="%{urlIssueBooksLink}" targets="studentLibraryContentDiv" indicator="indicator">
					         		Issue Book
					         	</sj:a>
							</s:if> <s:else>No Books</s:else>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently there are no search
			results.</div>
	</s:else>
</div>
<div id="SearchResultsDiv"></div>
<script type="text/javascript">
var BooksVar ="<s:property value="objectList.size()"/>";
if(BooksVar > 0){
	TableAdvanced.init();
}
	var subName = 1;
	$('div.bookNameDiv').click(
			function() {
				$('div#searchBookDiv>div.item').sortElements(
						function(a, b) {
							return ($(a).attr('bookName').toLowerCase() > $(b)
									.attr('bookName').toLowerCase() ? 1 : -1)
									* subName;
						});
				updateDirectionArrows($(this), subName);
				subName = subName * -1;
				event.originalEvent.options.submit = false;
			});
	function PopupSearchResults(id) {
		var url = jQuery.url
				.getChatURL("/library/ajaxLibrarianIssueBookDetails.do");
		$('#SearchResultsDiv')
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax({
			url : url,
			cache : false,
			data : "tempId=" + id,
			success : function(html) {
				$("#SearchResultsDiv").html(html);
			}
		});
	}
</script>
