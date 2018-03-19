<%@ include file="/common/taglibs.jsp"%>
<div id="delteBookNumDiv">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 90px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				View Book Numbers
			</h4>
		</div>
		<div class="modal-body">
		<%@ include file="/common/messages.jsp"%>
			<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
				<div id="inner-content-div">
					<table
						class="table table-striped table-bordered table-hover table-full-width"
						id="sample_2">
						<thead>
							<tr>
								<th>
									Book Number
								</th>
								<th>
									Status
								</th>
								<th>
									Type
								</th>
								<th>
									Delete
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="tempList2">
								<tr>
									<td>
										<s:property value="lableCode" />
									</td>
									<td>
										<s:if test='%{bookLabelStatus=="O"}'>
								  	Open
										</s:if>
										<s:if test='%{bookLabelStatus=="C"}'>
											<b style="color: Tomato;">Closed</b>
										</s:if>
										<s:if test='%{bookLabelStatus=="I"}'>
											Issued
										</s:if>
									</td>
									<td>
										<s:if test='%{type=="IB"}'>
								  		Issued Book
									</s:if>
										<s:if test='%{type=="RB"}'>
											<b style="color: Tomato;">Reference Book</b>
										</s:if>
									</td>
									<td>
										<s:if test='%{#session.previousYear == "N"}'>
											<s:if test='%{bookLabelStatus=="O"}'>
												<s:url id="removeBooks" action="ajaxDeleteBooks"
													includeParams="all" escapeAmp="false" namespace="/library">
													<s:param name="searchBookNumber" value="%{bookLableId}" />
													<s:param name="selectedId" value="%{bookTitleId}" />
													<s:param name="anyTitle" value="%{type}" />
													<s:param name="selectedId3"
														value="%{<s:property value='type' />}" />
												</s:url>
												<s:div cssClass="btn btn-xs red"
													onclick="javascript:confirmDialogWithTarget(this,'delteBookNumDiv');"
													id='%{removeBooks}' title="Delete this book">
													<i class="fa fa-times"></i> Delete
									</s:div>
											</s:if>
										</s:if>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Currently there are no books .
				</div>
			</s:else>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#inner-content-div').slimScroll({
    height: '450px'
});
function confirmDialogWithTarget(event,target) {
	 thishref = $(event).attr('id');
		if ($(event).next('.question').length <= 0) {
			$(event).after('<div class="question" style="position:fixed;">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
		}
		$(event).next('.question').animate( {
			opacity : 1
		}, 300);
		$('.yes').unbind('click');
		$('.yes').bind('click', function() {
			var prdDiv = $(this).parents('.question');
			prdDiv.html('Processing...');
			$.ajax( {
				url : thishref,
				cache : false,
				success : function(html) {
					$('#'+target).html(html);
				}
			
			});prdDiv.remove();
			
		});
	        $('.cancel').unbind('click');
	        $('.cancel').bind('click', function() {
	        $(this).parents('.question').fadeOut(300, function() {
	            $(this).remove();
	        });
	        return false;
	    });
	  } 
</script> 