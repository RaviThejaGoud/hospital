<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{ledgerDetails.id != 0}">
		<div data-width="760"  class="modal fade modal-overflow in" id="responsive" style="display: block; width: 860px; margin-left: -379px; margin-top: 50px;" aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Ledger
			</h4>
		</div>
		<div class="modal-body">
	</s:if>
<div class="form-body">
	<s:form id="addLedgerId" action="ajaxLedgerVoucher" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin">
		<s:hidden name="ledgerDetails.id" value="%{ledgerDetails.id}"/>
		<div class="form-group">
			<label class="control-label col-md-3"><span class="required">*</span>Select Group Name : </label>
			<div class="col-md-2">
				 <s:select id="groupId" list="tempList2" name="tempId" listKey="id" listValue="groupName" headerValue="-Select Group-" headerKey="" cssClass="required form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3"><span class="required">*</span>Ledger
				Name : </label>
			<div class="col-md-2">
				<sj:textfield name="ledgerDetails.ledgerName" id="ledgerName"
					cssClass="form-control required" maxlength="50"></sj:textfield>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-5">
				<sj:submit value="Submit" cssClass="submitBt btn blue"
					onBeforeTopics="addLedger" formIds="addLedgerId"
					targets="mainContentDiv" indicator="indicator" validate="true" />
					<s:if test="%{ledgerDetails.id != 0}">
						 <button type="button" data-dismiss="modal" class="btn default">Cancel</button>
					</s:if>
					<s:else>
						<s:url id="urlLedger" action="ajaxViewLedgerVoucher" namespace="/admin"/>
						<sj:a href="%{urlLedger}"
						targets="mainContentDiv" indicator="indicator" cssClass="btn default">Cancel</sj:a>
					</s:else>
			</div>
		</div>
	</s:form>
</div>
<s:if test="%{ledgerDetails.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
	$.subscribe('addLedger', function(event, data) {
		if ($('#addLedgerId').valid()) 
			$('button.close').click();
		  else
			event.originalEvent.options.submit = false;
	});
</script>