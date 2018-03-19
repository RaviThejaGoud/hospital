<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{reservations != null}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_editable_3">
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
					ID Proof
				</th>
				<th>
					Select
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="results">
				<tr>
					<td>
						<s:property value="reservations.bookReservationNo" />
					</td>
					<td>
						<s:property value="reservations.bookTitle.bookName" />
					</td>
					<td>
						<s:property value="reservations.bookTitle.author" />
					</td>
					<td>
						<s:if test='%{user!=null}'>
							<s:property value="user.username" />
						</s:if>
					</td>
					<td>
						<input type="checkbox" name="check"
							onclick="javascript:bookReservedConfirmDialog(this,
									'mainContentDiv',<s:property value='reservations.bookTitle.id'/>,
									<s:property value='user.id'/>,<s:property value='reservations.id'/>);" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no search results.
	</div>
</s:else>
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
TableEditable.init(); 
/* function bookReservedConfirmDialog(event, target, bookId, accountId,
		reservationId) {
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes')
			.bind(
					'click',
					function() {
						var prdDiv = $(this).parents('.question');
						prdDiv.html('Processing...');
						var url = jQuery.url
								.getChatURL("/library/ajaxAddIssuedBookThroughRequest.do");
						var pars = "bookId=" + bookId + "&tempId=" + accountId
								+ "&tempId1=" + reservationId;
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
} */
function bookReservedConfirmDialog(event,target,bookId,accountId,reservationId) {
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
		var url = jQuery.url.getChatURL("/library/ajaxAddIssuedBookThroughRequest.do");
		var pars = "bookId=" +bookId+"&tempId="+accountId+"&tempId1="+reservationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$('#'+target).html(html);
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