<%@ include file="/common/taglibs.jsp"%>

<s:form action="ajaxMailchimpAccountSettings" theme="simple"
	id="ajaxMailchimpAccountSettingsFormId" method="post" cssClass="form-horizontal"
	namespace="/admin">
	<s:hidden name="customer.id" value="%{customer.id}"></s:hidden>
	
	<s:hidden name="tempString" value="submit"></s:hidden>
	<div class="spaceDiv"></div>
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;
		You can create the account in <a href='<c:url value="http://www.mailchimp.com/"/>' target="_blank">mailchimp.com</a> and give that account details here.
	</p>
	<div class="form-body">
	
	 <div>&nbsp;</div>
							 
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>User Name :
			</label>
			<div class="col-md-3">
				<sj:textfield name="customer.mailChimpUserName" id="mailChimpUserName"
					labelposition="top" cssClass="form-control input-medium required"
					maxlength="40"></sj:textfield>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span> Password :
			</label>
			<div class="col-md-3">
				<sj:textfield name="customer.mailChimpPassword" id="mailChimpPassword"
					labelposition="top" cssClass="form-control input-medium required"
					maxlength="40"></sj:textfield>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Api Key:
			</label>
			<div class="col-md-3">
				<sj:textfield name="customer.mailChimpAPIKey" id="mailChimpAPIKey"
					labelposition="top" cssClass="form-control input-medium required"
					maxlength="40"></sj:textfield>
			</div>
		</div>
		
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					targets="mainContentDiv" validate="true"
					onBeforeTopics="formValidationHoliday"
					formIds="ajaxMailchimpAccountSettingsFormId" />
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{schoolHolidays.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
changePageTitle('Mailchimp Account Settings');
$(document).ready( function() {
			FormComponents.init();  
			
		});


$.subscribe('formValidationHoliday', function(event, data) {
		if ($('#ajaxMailchimpAccountSettingsFormId').valid()){
		$('button.close').click();
		} else
			 event.originalEvent.options.submit=false;	
}); 
</script>