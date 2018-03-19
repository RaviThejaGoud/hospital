<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Ticket
				</div>
			</div>
			<div class="portlet-body">
				<div id="ticketContentDiv" class="tab-content">
				<jsp:include page="/common/messages.jsp" />
				 <div class="row inbox">
						<div class="col-md-2">
							<ul class="inbox-nav margin-bottom-10">
								<li class="compose-btn">
									<a class="btn green" id="addTicket">Add New Ticket</a>
								</li>
								<li class="inbox active">
										<s:url id="urlTickeets" action="ajaxDoTicketForm" namespace="/fps"/>
										<sj:a href="%{urlTickeets}" targets="mainContentDiv"  cssClass="btn" data-title="Inbox"><i class="fa fa-envelope"></i>&nbsp;Tickets</sj:a>
									<b></b>
								</li>
							</ul>
						</div>
						<div class="col-md-10">
						<div id="inboxContentDiv"> 
							<div class="form-group form-horizontal">
									<label class="control-label col-md-2">
										<span class="required">*</span>Search Dealer :
									</label>
									<div class="col-md-10">
										<input type="hidden" id="select2_sample7"
											class="form-control select2 required">
									</div>
						      </div>
						      <div class="spaceDiv">&nbsp;</div>
				      <div class="spaceDiv">&nbsp;</div>
					<div id="searchDealersDiv">
						<jsp:include page="/WEB-INF/pages/fps/ajaxViewTicketFormDetails.jsp"></jsp:include>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		FormComponents.init();
		changePageTitle("Ticket");
	});
	$("#addTicket").click(function() {
		$('div#addTicketDiv').show();
	});
</script>