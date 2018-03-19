<%@ include file="/common/taglibs.jsp"%>
<div data-width="760"  class="modal fade modal-overflow in" style="display: block; width: 500px; margin-left: -190px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title" align="center">
			<b><s:property value="messages.title" /> </b>
		</h4>
	</div>
	<div class="modal-body">
	<div class="form-body">
		<div class="row form-horizontal">
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-3">
						To :
					</label>
					<div class="col-md-9">
						<p class="form-control-static">
							<s:if test='%{messages.receiverType=="SF"}'>
								All Staff
							</s:if>
							<s:else>
								All Students/Parent
							</s:else>
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-3">
						From :
					</label>
					<div class="col-md-9">
						<p class="form-control-static">
							<s:property value="anyTitle" />
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-3">
						Message :
					</label>
					<div class="col-md-9">
						<p class="form-control-static">
							<s:property value="messages.messageDescription" />
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</div>

