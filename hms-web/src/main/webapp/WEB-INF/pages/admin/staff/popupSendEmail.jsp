<%@ include file="/common/taglibs.jsp"%>
<div id="stepStaffComplaints">
	<div class="form-body">
		<s:form action="ajaxSendEmailToStaff" theme="simple" id="sendEmailToStaff" cssClass="form-horizontal" namespace="/admin">
			<jsp:include page="/common/messages.jsp"></jsp:include>
			<s:hidden name="tempId" value="%{tempId}"/>
			<h4 class="pageTitle bold">
				Send Mail
			</h4>
			<p>
				<span class="label label-danger"> NOTE : </span>&nbsp;Simple! just
				type subject, description for mail and hit submit.
			</p>
			<div class="form-group">
				<label class="control-label col-md-2"><span class="required">*</span> Subject :</label>
				<div class="col-md-5">
					<s:textfield id="subject"  maxlength="50"
				cssClass="required form-control" name="subject" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2"><span class="required">*</span> Description :</label>
				<div class="col-md-5">
				<s:textarea rows="7" cols="40"  
				cssClass="form-control  required" name="description" />
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit   cssClass="btn blue" value="Submit" validate="true"
					  targets="stepStaffComplaints" formIds="sendEmailToStaff" />
				</div>
			</div>
		</s:form>
	</div>
</div>