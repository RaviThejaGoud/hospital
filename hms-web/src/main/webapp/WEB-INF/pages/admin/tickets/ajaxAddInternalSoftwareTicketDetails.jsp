<%@ include file="/common/taglibs.jsp"%>

<s:form action="ajaxAddInternalSoftwareTicketDetails" id="addInternalSoftwareTicketDetails"
	method="post" cssClass="form-horizontal" theme="simple">
	<div class="form-body">
		<h4 class="bold pageTitle">
			Add Tickets
			<hr />
		</h4>
		<!-- <div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>Please select fee setting type.</li>
					<li>Enter the fee particular name then hit submit.</li>
					<li>If you assign class fee to any particular. You can not change fee setting  type.</li>
				</ul>
			</div>
		</div> -->
		<div class="spaceDiv"></div>
		<%-- <div class="form-group">
			<label class="control-label col-md-3"> <span class="required">*</span>Select Issue
				Type :
			</label>
			<div class="col-md-5">
				<s:select id="staffType2" headerKey=""
						cssClass="required form-control required" tabindex="2"
						headerValue="- Select -" name="ticketDetailsVO.issueType"
						list="#{'P':'Problem','RC':'Request for Clarification','DS':'Data Setup','NF':'New Feature'}"
						/>
				
			</div>
		</div> --%>
		<div class="form-group">
			<label class="control-label col-md-3"> <span class="required">*</span>
				Description :
			</label>
			<div class="col-md-5">
				<sj:textarea rows="3" cols="20" name="ticketDetailsVO.issue" maxCharsData="1000" tabindex="1" cssClass="form-control word_count required"></sj:textarea>
			</div>
		</div>
		
		
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				<sj:submit cssClass="btn blue" value="Submit" validate="true"
					formIds="addInternalSoftwareTicketDetails" targets="ticketContent"
					onBeforeTopics="formValidation" />
					
				<s:url id="doCancelFee" action="ajaxViewSelectedFeeSettings"
						includeParams="all" escapeAmp="false">
						<s:set name="tempId" value="feeType.feeSettingId"></s:set>
						<s:param name="tempString">feeParticulars</s:param>
					</s:url>
					<sj:a href="%{doCancelFee}" cssClass="btn default"
						targets="feeSettingsContent">Cancel</sj:a>
			</div>
		</div>
	</div>
</s:form>
<span class="particularId" id="<s:property value='feeType.id'/>"></span>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Add Ticket");
});
/* $.subscribe('formValidation',function(event, data) {
	 $('p.word-taken').each(function() {
	  if($(this).html()=='Already taken!!!'){
	     event.originalEvent.options.submit=false;
	   }
	 });
	 if($('#addInternalSoftwareTicketDetails').valid()){
		 $('button.close').click();
	 }

}); */
	

</script>