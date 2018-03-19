<%@ include file="/common/taglibs.jsp"%>
<div id="steps" class="grid_13" style="padding-left:0px;">
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
	<div class="control-label">
		 <b>Requested Students : </b>
	    </div>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<div class="tab-content" id="contentDiv">
		<!-- <div class="control-label">
		 <b>Requested Students : </b>
	    </div> -->	
	    <div class="space10"></div>
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
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
							Expiry Date
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
					<s:iterator value="objectList">
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
							<td>
								<input type="checkbox" name="check"
									onclick="javascript:bookReservedConfirmDialog(this,'mainContentDiv',<s:property value='bookTitle.id'/>,<s:property value='user.id'/>,<s:property value='id'/>);" />
							</td>
							</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there no requested Books.
		</div>
	</s:else>
	<div class="space10"></div>
	<div class="space10"></div>
	<div class="control-label">
		 <b>Requested Staff : </b>
	    </div>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
		<div class="tab-content" id="contentDiv">
		 <!-- <div class="control-label">
		 <b>Requested Staff : </b>
	    </div> -->
	    <div class="space10"></div>
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
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
							Expiry Date
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
					<s:iterator value="tempList">
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
							<td>
								<input type="checkbox" name="check"
									onclick="javascript:bookReservedConfirmDialog(this,'mainContentDiv',<s:property value='bookTitle.id'/>,<s:property value='user.id'/>,<s:property value='id'/>);" />
							</td>
							</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there no requested Books.
		</div>
	</s:else>
</div>
<script type="text/javascript">
TableAdvanced.init();
 $("input:checkbox, input:radio").uniform();
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
