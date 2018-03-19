<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{objectList != null && !objectList.isEmpty()}">
 <h4 id="availableTotalBooks">
  </h4>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Acquisition Number
				</th>
				<th>
					Book Name
				</th>
				<th>
					Book Number
				</th>
				<th>
					Edition
				</th>
				<th>
					Author Name
				</th>
				<!-- <th>
					Publisher
				</th> -->
				<th>
					Subject
				</th>
				<th>
					Available / Total Books
				</th>
				<th>
					Issue Book
				</th>
			</tr>
		</thead>
		<tbody>
		
		
		<s:iterator value="objectList" status="stat">
			<s:if test='%{noOfCopies > 0}'>
				<tr class="totalAvailbleBooks" id="<s:property value="issuedBooksCount" />" data-Id="<s:property value="noOfCopies" />">
					<td>
						<s:property value="acquisitionNumber" />
					</td>					
					<td>
						<a href="#popupEditBookNumbersDiv" data-toggle="modal"
							onclick="javascript:popupEditBookNumbers(<s:property value="id" />); return false;">
							<s:property value="bookName" /> </a>
					</td>
					<td>
						<s:property value="bookLabelCode" />
					</td>
					<td>
						<s:property value="bookEdition" />
					</td>
					<td>
						<s:property value="author" />
					</td>
					<%-- <td>
						<s:property value="objectList[#stat.count-1][5]" />
					</td> --%>

					<td>
						<s:if test='%{studySubject != null}'>
							<s:property value="studySubject.name" />
						</s:if>
						<s:else>
							<s:property value="otherSubjects" />
						</s:else>
					</td>
					<td>
						(
						<s:property value="issuedBooksCount" />
						&nbsp; /
						<s:property value="noOfCopies" />
						&nbsp; )
					</td>
					<td>
					<s:if test='%{issuedBooksCount!=0}'>
					<s:url id="urlIssueBooksLink" action="ajaxLibrarianIssueBookDetails" namespace="/library" includeParams="all" escapeAmp="false">
									<s:param name="tempId" value="%{id}"></s:param>
									</s:url>
					 <sj:a href="%{urlIssueBooksLink}" targets="studentLibraryContentDiv" indicator="indicator">
			         	Issue Book</sj:a>
			         </s:if>
					 <s:else>No Books</s:else>
					</td>
				</tr>
				</s:if>
			</s:iterator>
			
			
			<%-- <s:iterator value="objectList" status="stat">
				<tr class="totalAvailbleBooks" id="<s:property value="objectList[#stat.count-1][7]" />" data-Id="<s:property value="objectList[#stat.count-1][6]" />">
					<td>
						<s:property value="objectList[#stat.count-1][9]" />
					</td>					
					<td>
						<a href="#popupEditBookNumbersDiv" data-toggle="modal"
							onclick="javascript:popupEditBookNumbers(<s:property value="objectList[#stat.count-1][0]" />); return false;">
							<s:property value="objectList[#stat.count-1][1]" /> </a>
					</td>
					<td>
						<s:property value="objectList[#stat.count-1][11]" />
					</td>
					<td>
						<s:property value="objectList[#stat.count-1][10]" />
					</td>
					<td>
						<s:property value="objectList[#stat.count-1][4]" />
					</td>
					<td>
						<s:property value="objectList[#stat.count-1][5]" />
					</td>

					<td>
						<s:if test='%{objectList[#stat.count-1][2] != null}'>
							<s:property value="objectList[#stat.count-1][2]" />
						</s:if>
						<s:else>
							<s:property value="objectList[#stat.count-1][3]" />
						</s:else>
					</td>
					<td>
						(
						<s:property value="objectList[#stat.count-1][7]" />
						&nbsp; /
						<s:property value="objectList[#stat.count-1][6]" />
						&nbsp; )
					</td>
					<td>
					<s:if test='%{objectList[#stat.count-1][7]!=0}'>
					<s:url id="urlIssueBooksLink" action="ajaxLibrarianIssueBookDetails" namespace="/library" includeParams="all" escapeAmp="false">
									<s:param name="tempId" value="%{objectList[#stat.count-1][0]}"></s:param>
									</s:url>
					 <sj:a href="%{urlIssueBooksLink}" targets="studentLibraryContentDiv" indicator="indicator">
			         	Issue Book</sj:a>
			         </s:if>
					 <s:else>No Books</s:else>
					</td>
				</tr>
			</s:iterator> --%>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there no books. You can upload books by clicking on import books.
	</div>
</s:else>
<div id="popupEditBookNumbersDiv"></div>
<div id="popupEditIssueBooksDiv"></div>
<script language="JavaScript" type="text/javascript">
changePageTitle('School Wide Books ');
 $(document).ready(function(){
		var availCount=0;
	   var totalCount=0;
	   $('tr.totalAvailbleBooks').each(function(){
		availCount +=Number($(this).attr('id'));
		totalCount +=Number($(this).attr('data-Id'));
	});
	 $('h4#availableTotalBooks').html("Available / Total Books : "+availCount+" / "+totalCount);
	TableAdvanced.init();
});
 
function popupEditBookNumbers(selectedId) {
	var url = jQuery.url.getChatURL("/library/ajaxViewBookNumbers.do");
	$('#popupEditBookNumbersDiv')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "selectedId=" + selectedId,
		success : function(html) {
			$("#popupEditBookNumbersDiv").html(html);
		}
	});
}

function popupEditIssueBooks(tempId) {
	var url = jQuery.url
			.getChatURL("/library/ajaxLibrarianIssueBookDetails.do");
	$('#popupEditIssueBooksDiv')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "tempId=" + tempId,
		success : function(html) {
			$("#popupEditIssueBooksDiv").html(html);
		}
	});
}
</script>