<%@ include file="/common/taglibs.jsp"%>
<div id="steps" class="grid_13" style="padding-left:0px;">
<jsp:include page="/common/messages.jsp"></jsp:include>
	<s:if test="%{bookTitleList != null && !bookTitleList.isEmpty()}">
		<div class="tab-content" id="contentDiv">
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
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
		</div>
	</s:if>
	<s:elseif test="%{objectList != null && !objectList.isEmpty()}">
		<div class="tab-content" id="contentDiv">
		<div class="control-label">
		 <b>Requested Students : </b>
	    </div>
	    <div class="space10"></div>
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
				<thead>
					<tr>
						<th>
							Book Name
						</th>
						<th>
							Author Name
						</th>
						<th>
							Due Date
						</th>
						<th>
							ID Proof
						</th>
						<th>
							Approve
						</th>
						<th>
							Reject
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="objectList">
						<tr>
							<td>
								<s:property value="bookTitle.bookName" />
							</td>
							<td>
								<s:property value="bookTitle.author" />
							</td>
							<td>
								<s:property value="dueDateStr" />
							</td>
							<td>
								<s:if test='%{user!=null}'>
									<s:property value="user.username" />
								</s:if>
							</td>
							<td>
								<input type="radio" name="radio<s:property value='bookTitle.id'/>"
									onclick="javascript:bookApproveOrRejectConfirmDialog(this,'schoolBooksList',<s:property value='bookTitle.id'/>,<s:property value='user.id'/>,<s:property value='id'/>,'Y');" />
							</td>
							<td>
								<input type="radio" name="radio<s:property value='bookTitle.id'/>"
									onclick="javascript:bookApproveOrRejectConfirmDialog(this,'schoolBooksList',<s:property value='bookTitle.id'/>,<s:property value='user.id'/>,<s:property value='id'/>,'N');" />
							</td>
							</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</s:elseif>
	<s:else>
		<div class="alert alert-info">
			Currently there no requested books for renewal.
		</div>
	</s:else>
	<div class="space10"></div>
	<div class="space10"></div>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
		<div class="tab-content" id="contentDiv">
		 <div class="control-label">
		 <b>Requested Staff : </b>
	    </div>
	    <div class="space10"></div>
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
				<thead>
					<tr>
						<th>
							Book Name
						</th>
						<th>
							Author Name
						</th>
						<th>
							Due Date
						</th>
						<th>
							ID Proof
						</th>
						<th>
							Approve
						</th>
						<th>
							Reject
						</th>
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
								<s:property value="dueDateStr" />
							</td>
							<td>
								<s:if test='%{user!=null}'>
									<s:property value="user.username" />
								</s:if>
							</td>
							<td>
								<input type="radio" name="radio<s:property value='bookTitle.id'/>"
									onclick="javascript:bookApproveOrRejectConfirmDialog(this,'schoolBooksList',<s:property value='bookTitle.id'/>,<s:property value='user.id'/>,<s:property value='id'/>,'Y');" />
							</td>
							<td>
								<input type="radio" name="radio<s:property value='bookTitle.id'/>"
									onclick="javascript:bookApproveOrRejectConfirmDialog(this,'schoolBooksList',<s:property value='bookTitle.id'/>,<s:property value='user.id'/>,<s:property value='id'/>,'N');" />
							</td>
							</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</s:if>
	
</div>
<script type="text/javascript">
TableAdvanced.init();
 $("input:checkbox, input:radio").uniform();
 function bookApproveOrRejectConfirmDialog(event,target,bookId,accountId,issueBookId,status) {
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
		var url = jQuery.url.getChatURL("/library/ajaxApproveOrRejectConfirmRequest.do");
		var pars = "bookId=" +bookId+"&tempId="+accountId+"&tempId1="+issueBookId+"&anyId="+status;
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
