<%@ include file="/common/taglibs.jsp"%>
<div id="studentTakenBooks">
<h4 class="pageTitle bold">
	Staff Taken Books
</h4>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_3">
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
					Subject
				</th>
				<th>
					Issued Date
				</th>
				<th>
					Return Date
				</th>
				<th>
					Book Number
				</th>
				<th>Renewal</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
				<tr>
					<td>
						<s:property value="bookTitle.bookName" />
					</td>
					<td>
						<s:property value="bookTitle.author" />
					</td>
					<td>
						<s:property value="bookTitle.publisher" />
					</td>
					<td>
						<s:if test="%{bookTitle.studySubject != null}">
							<s:property value="bookTitle.studySubject.name" />
						</s:if>
						<s:else>
							<s:property value="bookTitle.otherSubjects" />
						</s:else>
					</td>
					<td>
						<s:property value="issuedDateStr" />
					</td>
					<td>
						<s:property value="dueDateStr" />
					</td>
					<td>
						<s:property value="bookLable.lableCode" />
					</td>
					<td>
					<input type="checkbox" name="check"
									onclick="javascript:bookRenewalConfirmDialog(this,'studentTakenBooks',<s:property value='id'/>);" />
							 
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no taken books.
	</div>
</s:else>
</div>

<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
function bookRenewalConfirmDialog(event,target,issueBookId) {
	if ($(event).parent().parent().next('.question').length <= 0) {
		$(event).parent().parent().after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).parent('span').addClass("checked");
	$(event).attr("checked",true);
	$(event).parent().parent().next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() { 
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		var pars="bookId="+issueBookId;
		$.ajax( {
			url : jQuery.url.getChatURL("/library/ajaxRenewalStudentStaffBook.do"),
			cache : false,
			data : pars,
			success : function(html) {
				$('#' + target).html(html);
			}
		});
	});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).prev("div.checker").find('span.checked').removeClass("checked");
			$(this).prev("div.checker").find('span.checked').find("input").removeAttr("checked");
			$(this).remove();
		});
		return false;
	});
}
</script>	