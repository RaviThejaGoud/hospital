<%@ include file="/common/taglibs.jsp"%>
<div id="studentTagetDivRes">
	<h4 class="bold pageTitle">
		Search Results
	</h4>
	<jsp:include page="/common/messages.jsp"></jsp:include>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_editable_3">
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
						Publisher
					</th>
					<th>
						Subject Name
					</th>
					<th>
						Label Code
					</th>
					<th>
						Block & Rack Details
					</th>
					<th>
						Request
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
							<s:property value="publisher" />
						</td>
						<td>
							<s:if test="%{subjectName != null}">
								<s:property value="subjectName" />
							</s:if>
							<s:else>
								<s:property value="otherSubjects" />
							</s:else>
						</td>
						<td>
							<s:property value="lableCode" />
						</td>
						<td>
							<s:if test='%{blockName!="" && !blockName.isEmpty()}'>
								<s:property value="blockName" />
							</s:if>
							<s:if test='%{rackName!="" && !rackName.isEmpty()}'>
			  					& <s:property value="rackName" />
							</s:if>
							<s:else>
								&  No Rack
						</s:else>
						</td>
						<td>
							<s:if test='%{tempList.size<3}'>
								<input type="checkbox" name="check"
									onclick="javascript:bookRequestConfirmDialog(this,'studentLibraryContent',<s:property value='bookTitleId'/>,'<s:property value='lableCode'/>');" />
							</s:if>
							<s:else>U don't have.</s:else>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:elseif test="%{tempList2 != null && !tempList2.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_4">
			<thead>
				<tr>
					<th>
						Book Request Code
					</th>
					<th>
						Book Name
					</th>
					<th>
						Author Name
					</th>
					<th>
						Expire Date
					</th>
					<th>
						ID Proof
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList2">
					<tr>
						<td>
							<s:property value="bookReservationNo" />
						</td>
						<td>
							<s:property value="bookTitle.bookName" />
						</td>
						<td>
							<s:property value="bookTitle.author" />
						</td>
						<td>
							<s:property value="expiryDateStr" />
						</td>
						<td>
							<s:if test='%{user!=null}'>
								<s:property value="user.username" />
							</s:if>
						</td>
				</s:iterator>
			</tbody>
		</table>
	</s:elseif>
	<s:else>
		<div class="alert alert-info">
			No Results Found.
		</div>
	</s:else>
</div>
<script type="text/javascript">
TableEditable.init();
$("input:checkbox, input:radio").uniform();
function bookRequestConfirmDialog(event, target, bookId) {
	if ($(event).parent().parent().next('.question').length <= 0) {
		$(event).parent().parent().after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).parent('span').addClass("checked");
	$(event).attr("checked",true);
	$(event).parent().parent().next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click',
		function() {
			var prdDiv = $(this).parents('.question');
			prdDiv.html('Processing...');
			var url = jQuery.url
					.getChatURL("/library/ajaxSendResquestStuddentBook.do");
			var pars = "bookId=" + bookId;
			$.ajax( {
				url : url,
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
			$(this).remove();
		});
		if ($(event).val() == "true") {
			$(event).attr("checked", "checked");
		} else {
			$(event).removeAttr("checked");
			$(event).parent('span').removeClass('checked');
		}
		return false;
	});
}
</script>