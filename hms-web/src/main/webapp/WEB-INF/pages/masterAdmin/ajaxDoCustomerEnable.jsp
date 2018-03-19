<%@ include file="/common/taglibs.jsp"%>
	<div data-width="760" class="modal fade modal-overflow in" 
		style="display: block; width: 650px; margin-left: -279px; margin-top: 100px;" aria-hidden="false">
		<div class="modal-body">
			<div class="form-body">
			<s:form id="editCustomerAnable" action="ajaxEnableCustomerfDetails" method="post"
				theme="simple" namespace="/masterAdmin" cssClass="form-horizontal">
				<s:hidden name="custId" value="%{tempId}"></s:hidden>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<b>Active Customer</b>
					</h4>
				</div>
					<s:hidden name="tempId" value="%{tempId}" />
						<div class="form-group">
							<label class="control-label col-md-5">
								<span class="required">*</span> Customer active for cause/reason :
							</label>
							<div class="col-md-10">
								<s:textarea cols="5" rows="5" id="messageDescription"
									maxCharsData="1024" cssClass="required form-control word_count"
									name="customer.customerInActiveDescription"></s:textarea>
								<span class="counter"></span>
							</div>
						</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
							<sj:submit   cssClass="submitBt btn blue" value="Submit"
								  formIds="editCustomerAnable" targets="contentDiv" validate="true" indicator="indicator"
								 onBeforeTopics="changeCustomerEnableValidation" />
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</div>
				</div>
			</s:form>
		 </div>
		</div>
	</div>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<script type="text/javascript">
$(document).ready(function(){
	 UIExtendedModals.init();
	 $.destroyTopic('changeCustomerEnableValidation');
});
$.subscribe('changeCustomerEnableValidation', function(event, data) {
	var desc= $('#messageDescription').val();
	if(desc.length == 0){
		alert("Please add Description");
		event.originalEvent.options.submit=false;
	}else{
		var answer = confirm("Are you sure you want to Active customer ?");
		 if (!answer) {
			 $('button.close').click();
			event.originalEvent.options.submit=false;
			return false;
		} else {
			$('button.close').click();
			return true;
		}
	}
}); 
	
</script>