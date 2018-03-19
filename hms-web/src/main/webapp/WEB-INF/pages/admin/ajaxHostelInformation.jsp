<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{hostel.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Hostel
			</h4>
		</div>
		<div class="modal-body">
			<div class="form-body">
</s:if>
<s:form action="ajaxUpdateHostelDetails" theme="simple"
	cssClass="form-horizontal" id="changeHostelInfo" method="post"
	enctype="multipart/form-data" namespace="/admin">
	<s:hidden name="hostel.id"></s:hidden>
	<div class="form-body">
		<div class="row">
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						<span class="required">*</span>Hostel Name :
					</label>
					<div class="col-md-6">
						<sj:textfield name="hostel.hostelName" id="hostelName"
							cssClass="required form-control" maxlength="60"></sj:textfield>
					</div>
				</div>
				<div class="col-md-6">
					<label class="control-label col-md-5">
						Street :
					</label>
					<div class="col-md-6">
						<sj:textfield name="address.streetName" id="streetName"
							cssClass="form-control" maxlength="120"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						State :
					</label>
					<div class="col-md-6">
						<s:select id="state" list="statesList" label="State"
							cssClass="form-control" listKey="stateCode" listValue="stateName"
							headerKey="" headerValue="- Select -" name="address.state" />
					</div>
				</div>
				<div class="col-md-6">
					<label class="control-label col-md-5">
						City :
					</label>
					<div class="col-md-6">
						<sj:textfield name="address.city" id="City"
							cssClass="form-control" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						Pincode :
					</label>
					<div class="col-md-6">
						<sj:textfield name="address.postalCode" id="Pincode"
							cssClass="numeric form-control" maxlength="6"></sj:textfield>
					</div>
				</div>
				<div class="col-md-6">
					<label class="control-label col-md-5">
						<span class="required">*</span>Mobile Number :
					</label>
					<div class="col-md-6">
						<sj:textfield name="hostel.mobileNumber" id="mobileNumber"
							cssClass="required form-control" maxlength="10"
							onblur="return validateMobNumber(this.value)" ></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						Phone Number :
					</label>
					<div class="col-md-6">
						<sj:textfield name="hostel.contactNumber" id="contactNumber"
							cssClass="form-control numeric " maxlength="10"></sj:textfield>
					</div>
				</div>
				<div class="col-md-6">
					<label class="control-label col-md-5">
						<span class="required">*</span>Email :
					</label>
					<div class="col-md-6">
						<sj:textfield name="hostel.custEmail" id="emailAddress"
							cssClass="email form-control required" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					formIds="changeHostelInfo" targets="mainContentDiv" validate="true"
					onBeforeTopics="hostelFormValid" />
				<s:if test="%{hostel.id != 0}">
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</s:if>
				<s:else>
					<s:url id="viewHostelUrl" action="ajaxViewHostelDetails"
						includeParams="all" escapeAmp="false" namespace="/admin">
						<s:param name="hostel.id" value="0" />
					</s:url>
					<sj:a href="%{viewHostelUrl}" cssClass="btn default"
						targets="mainContentDiv">Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{hostel.id != 0}">
	</div>
	</div>
</s:if>
<script language="JavaScript" type="text/javascript">
changePageTitle("Add Hostel Details");
	$(document).ready(function() {
		$('.numeric').numeric();
		$('.alphabet').alpha();
		$("input[id=emailAddress]").autoCheck(
			"${pageContext.request.contextPath}/hostel/ajaxCheckHostelEmailId.do",
			{
				minChars : 3,
				min : "no"
			});
	});
	/* $.subscribe('hostelFormValid', function(event, data) {
		if ($('#changeHostelInfo').valid()){
			$('button.close').click();
			return true;
		}
		else
			event.originalEvent.options.submit=false;
	}); */
		$.subscribe('hostelFormValid',function(event, data) {
	 $('p.word-taken').each(function() {
	  if($(this).html()=='Already taken!!!'){
	     event.originalEvent.options.submit=false;
	   }
	 });
	 $('button.close').click();
	});
</script>
