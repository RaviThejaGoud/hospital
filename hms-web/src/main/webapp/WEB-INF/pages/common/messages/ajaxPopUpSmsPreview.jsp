<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 660px; margin-left: -279px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-body">
		<div class="form-body">
			<s:form action="#" method="post" theme="simple" namespace=""
				cssClass="form-horizontal">
				<s:hidden name="tempString" value="%{tempString}"></s:hidden>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<b>SMS Preview</b>
					</h4>
				</div>
				<s:hidden name="tempId" value="%{tempId}" />
				<div class="form-group">
					<label class="control-label col-md-5">
					</label>
					<div class="space10"></div>
					<div class="col-md-10">
						<s:textarea cols="5" rows="5" id="messageDescription"
							disabled="true" maxCharsData="1024"
							cssClass="required form-control word_count" name="tempString"></s:textarea>
					</div>
				</div>
			</s:form>
		</div>
	</div>
</div>
