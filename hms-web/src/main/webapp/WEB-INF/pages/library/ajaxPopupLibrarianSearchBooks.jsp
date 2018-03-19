<%@ include file="/common/taglibs.jsp"%>
<div id="studentLibraryContent">
	<jsp:include page="/common/messages.jsp" />
	<s:if test="%{librarySettings != null}">
		<s:if test="%{bookTitleList != null && !bookTitleList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Book Name
						</th>
						<th>
							Issued Date
						</th>
						<th>
							Due Date
						</th>
						<th>
							Due Days
						</th>
						<th>
							Author
						</th>
						<th>
							Fine
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="bookTitleList">
						<tr>
							<td>
								<s:property value="bookTitle.bookName" />
								&nbsp;
							</td>
							<td>
								<s:property value="issuedDateStr" />
								&nbsp;
							</td>
							<td>
								<s:property value="dueDateStr" />
								&nbsp;
							</td>
							<td>
								<s:if test="%{betweenDueDays<=0}">
										 0 Days
								</s:if>
								<s:else>
									<s:property value="betweenDueDays" />&nbsp;
								</s:else>
							</td>
							<td>
								<s:property value="bookTitle.author" />
								&nbsp;
							</td>
							<td>
								<s:if test="%{fine<=0}">
									 0
								</s:if>
								<s:else>
									<span style="font-size: 12px;">&#2352; </span> &nbsp;<s:property
										value="fine" />
								</s:else>
								&nbsp;
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
		<div id="schollBar">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Book Name
						</th>
						<th>
							Author Name
						</th>
						<th>
							Publisher
						</th>
						<th>
							Issue Book
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator>
						<tr>
							<td>
								<s:property value="bookTitle.bookName" />
								&nbsp;
							</td>
							<td>
								<s:property value="bookTitle.author" />
								&nbsp;
							</td>
							<td>
								<s:property value="bookTitle.publisher" />
								&nbsp;
							</td>
							<td>
								<s:if test="%{anyTitle == 'ROLE_STUDENT'}">
									<s:if test='%{tempList.size < librarySettings.noOfStudentIssuBooks}'>
										<s:if test='%{tempString =="Closed"}'>
												-
										</s:if>
										<s:else>
											<a href="#"
												onclick="javascript:issuedBooksConfirmDialog(this,'mainContentDiv',<s:property value='bookTitle.id'/>,<s:property value="tempId"/>);">Issue
												Book</a>
										</s:else>
									</s:if>
									<s:else>
										You have already taken <s:property
												value="librarySettings.noOfStudentIssuBooks" /> book(s).
									</s:else>
								</s:if>
								<s:else>
									<s:if test='%{tempList.size < librarySettings.noOfStaffIssuBooks}'>
										<s:if test='%{tempString =="Closed"}'>
												-
										</s:if>
										<s:else>
											<a href="#"
												onclick="javascript:issuedBooksConfirmDialog(this,'mainContentDiv',<s:property value='bookTitle.id'/>,<s:property value="tempId"/>);">Issue
												Book</a>
										</s:else>
									</s:if>
									<s:else>
										You have already taken <s:property
												value="librarySettings.noOfStaffIssuBooks" /> book(s).
									</s:else>
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			</div>
		</s:else>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently you have not define library settings.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $('#schollBar').on('click', function(){
        $('html,body').animate({scrollTop: $(this).offset().top}, 800);
    }); 
});  


function issuedBooksConfirmDialog(event, target, bookId, stuentOrStaffId) {
var bookNumber='<s:property value="bookNumber" />';
	if ($(event).next('.question').length <= 0) {
		$(event).after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click',function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		var url = jQuery.url.getChatURL("/library/ajaxSaveLibrarinIssuedBook.do");
		var pars = "tempId=" + stuentOrStaffId + "&tempId1=" + bookId+ "&bookNumber=" + bookNumber;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$('#' + target).html(html);
				//$('button.close').click();
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		if ($(event).val() == "true") {
			$(event).attr("checked", "checked");
		} else {
			$(event).removeAttr("checked");
		}
		return false;
	});
}
</script>