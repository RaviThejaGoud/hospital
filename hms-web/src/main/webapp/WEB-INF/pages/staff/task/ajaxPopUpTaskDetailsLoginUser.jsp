<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 660px; margin-left: -279px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-body">
		<div class="form-body">
			<s:form action="#" method="post" theme="simple" namespace=""
				cssClass="form-horizontal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
					</h4>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<div class="col-md-9" style="margin-top: 6px;">
								<s:property value="tempString" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
				<div class="alert alert-info">
				Assign task to you, you want more information
				<s:url id="viewTaskIndo" action="ajaxTaskInformationHome" namespace="/staff"></s:url>
							<sj:a id="viewTaskInfo" href="%{viewTaskIndo}" targets="mainContentDiv" data-toggle="tab"><b>Click Here</b></sj:a>
							</div>
				</div>
			</s:form>
		</div>
	</div>
</div>
