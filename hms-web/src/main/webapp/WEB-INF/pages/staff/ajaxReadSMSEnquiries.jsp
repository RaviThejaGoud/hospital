<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body form-horizontal">
	<h4 class="pageTitle bold">
		SMS Iquiries
	</h4>
 <s:if test="%{messageDetails != null && !messageDetails.isEmpty()}"> 
	<table class="table table-striped table-bordered table-hover table-full-width"
			id="sample_5">
			<thead>
				<tr>
				<th style="display: none;">
			    </th>
					<th style="white-space:normal">
						SMS Content
					</th>
					<th>
						Sent By
					</th>
					<s:if test='%{anyId != "E"}'>
						<th>
							Send Count
						</th>
					</s:if>
					<th>
						Sent Date
					</th>
					<th>
						Action
					</th>
				
				</tr>
			</thead>
			<tbody>
			
				<s:if test='%{ user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolAsstStaff=="Y"}'>
				<s:iterator value="messageDetails" >
						<tr>
						<td>
								<s:property value="messageDescription" />
						</td>
						<td>
								<s:property value="senderName" />
						</td>
							
							<td>
								<s:property value="smsCount" />
							</td>
							<td>
								<s:property value="messageDate" />
							</td>
							
						
						<td>
						<s:if test='%{ user.isSchoolAsstStaff!="Y"}'>
						<s:url id="showSMSStatus" action="ajaxSendEnquiredMessages"
								includeParams="all" escapeAmp="false" namespace="/common">
								<s:param name="smsStatus.id" value="%{id}" />
								<s:param name="messageenquirydetails.id" value="%{id}" />
							</s:url>
							<s:div cssClass="btn btn-xs blue"
								onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
								id='%{showSMSStatus}' title="show Status">
								<i class="fa fa-times"></i>Action
						</s:div>
						</s:if>
					</td>
						
						</tr>
			</s:iterator>
			</s:if>
			</table>
			</s:if>
						 
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script> 
<script type="text/javascript">
function confirmDialogWithTarget(event,target) {
	thishref = $(event).attr('id');
	if ($(event).next('.question').length <= 0) {
		$(event).after('<div class="question">Are You Sure?<br/> <span class="yes">Approve</span><span class="cancel">Reject</span></div>');
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
		}); 
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

						 
				
