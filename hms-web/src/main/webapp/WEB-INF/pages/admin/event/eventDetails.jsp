<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 560px; margin-left: -200px; margin-top: 200px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			View Event Details
		</h4>
	</div>
	<div class="modal-body form-horizontal">
	   <div class="form-body">
			<s:if test="{events != null}">
				<div class="form-group">
					<label class="control-label col-md-3">
						Event Name :
					</label>
					<div class="col-md-8">
						<p class="form-control-static">
							<s:property value="events.eventName" />
						</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						Description :
					</label>
					<div class="col-md-8">
						<p class="form-control-static">
							<s:property value="events.eventDescription" />
						</p>
					</div>
				</div>
				<%-- <div class="form-group">
					<label class="control-label col-md-3">
						Start Date :
					</label>
					<div class="col-md-8">
						<p class="form-control-static">
							<s:property value="events.eventStartDateStr" />
						</p>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-3">
						End Date :
					</label>
					<div class="col-md-8">
						<p class="form-control-static">
							<s:property value="events.eventEndDateStr" />
						</p>
					</div>
				</div> --%>
				
				<div class="form-group">
					<label class="control-label col-md-3">
						Event Date & Time
					</label>
					<div class="col-md-8">
						<p class="form-control-static">
							<s:property value="events.eventStartDateStr" /> - <s:property value="events.eventEndDateStr" /> <br/> <s:property value="events.startTime" /> - <s:property value="events.endTime" />
						</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						Event For :
					</label>
					<div class="col-md-8">
						<p class="form-control-static">
							<s:property value="events.viewClassesStaff" />
						</p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						Event Address :
					</label>
					<div class="col-md-8">
						<p class="form-control-static">
							<s:property value="events.eventAddress" />
						</p>
					</div>
				</div>

			</s:if>
			<s:else>
				<div class="alert alert-info">
					U don't have events
				</div>
			</s:else>
		</div>
	</div>
</div>

