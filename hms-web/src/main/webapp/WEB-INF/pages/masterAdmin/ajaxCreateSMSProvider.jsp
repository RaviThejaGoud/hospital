<%@ include file="/common/taglibs.jsp"%>
<s:form id="addSmsProvider" action="ajaxSaveSmsProviderDetails"
	method="post" theme="simple" cssClass="form-horizontal" namespace="/masterAdmin">
	<s:hidden name="smsServiceProvider.id" value="%{smsServiceProvider.id}"></s:hidden>
	<div class="form-body">
		<h4 class="bold pageTitle">
			<s:if test="%{smsServiceProvider.id != 0}">
			Update SMS Service Provider Details
		</s:if>
			<s:else>
			Create SMS Service Provider Details		
		</s:else>
		</h4>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Service Provider :
			</label>
			<div class="col-md-9">
				<sj:textfield name="smsServiceProvider.serviceProvider"
					id="providerName"
					cssClass="required form-control input-medium as-input"
					maxlength="120"></sj:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Service Provider Url :
			</label>
			<div class="col-md-9">
				<sj:textarea name="smsServiceProvider.url"
					cssClass="required form-control col-md-9 word_count"
					maxCharsData="1045" id="providerUrl" rows="5" cols="40"></sj:textarea>
				<div class="counter"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Enable this service provider:
			</label>
			<div class="col-md-9">
				<p class="form-control-static">
					<s:checkbox name="smsServiceProvider.activeUrl" />
					<span class="hintMessage">(If
						you select this option mobile SMSs will send from this service
						provider.)</span>
				</p>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-6 col-md-9">
					<sj:submit   cssClass="submitBt btn blue" formIds="addSmsProvider"
						value="Submit"  targets="smsProvidersCont" validate="true"
						onBeforeTopics="formValidationForAddSmsProvider" />
					<s:url id="doCloseSmsProvider" action="ajaxManageSMSProviders" namespace="/masterAdmin"></s:url>
					<sj:a href="%{doCloseSmsProvider}" cssClass="btn default"  targets="mainContentDiv">Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
</script>
