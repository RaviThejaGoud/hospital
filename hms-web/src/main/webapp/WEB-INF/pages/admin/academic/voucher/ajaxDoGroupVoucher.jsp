<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{accountGroup.id != 0}">
		<div data-width="760"  class="modal fade modal-overflow in" id="responsive" style="display: block; width: 860px; margin-left: -379px; margin-top: 50px;" aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Group
			</h4>
		</div>
		<div class="modal-body">
	</s:if>
<div class="form-body">
	<s:form id="GroupVoucherId" action="ajaxGroupVoucher" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin">
		<s:hidden name="accountGroup.id" value="%{accountGroup.id}"></s:hidden>
		<div class="form-group">
			<label class="control-label col-md-3"><span class="required">*</span>Category Type : </label>
			<div class="col-md-2">
				<s:select id="categorysId" list="categoryQuestionList" name="tempId1" listKey="id" listValue="name" headerValue="-Select Category-" headerKey="" cssClass="required form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3"><span class="required">*</span>Group
				Name : </label>
			<div class="col-md-2">
				<sj:textfield name="accountGroup.groupName" id="name"
					cssClass="form-control required" maxlength="50"></sj:textfield>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-5">
				<sj:submit value="Submit" cssClass="submitBt btn blue"
					onBeforeTopics="addGroup" formIds="GroupVoucherId"
					targets="mainContentDiv" indicator="indicator" validate="true" />
					<s:if test="%{accountGroup.id != 0}">
						 <button type="button" data-dismiss="modal" class="btn default">Cancel</button>
					</s:if>
					<s:else>
						<s:url id="urlLedger" action="ajaxViewGroupVoucher" namespace="/admin"/>
						<sj:a href="%{urlLedger}"
						targets="mainContentDiv" indicator="indicator" cssClass="btn default">Cancel</sj:a>
					</s:else>
					
			</div>
		</div>
	</s:form>
</div>
<s:if test="%{accountGroup.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
	$.subscribe('addGroup', function(event, data) {
		if ($('#GroupVoucherId').valid()) 
			$('button.close').click();
		  else
			event.originalEvent.options.submit = false;
	});
</script>