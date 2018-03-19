<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:form action="ajaxViewCampaignsStats" theme="simple"
	cssClass="form-horizontal" id="sendCampainMailsFormId" method="post"
	enctype="multipart/form-data" namespace="/admin">
	<s:hidden name="hostel.id"></s:hidden>
	<div class="form-body">
		<div class="row">
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						<span class="required">*</span>All Campaigns :
					</label>
					<div class="col-md-6">
						<s:select id="campaignsId" list="selectboxMap" label="All Campaigns"  
							cssClass="form-control required"
							headerKey="" headerValue="- Select -" name="campaignsId" />
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					formIds="sendCampainMailsFormId" targets="getCampaignsStatsDivId" validate="true"
					onBeforeTopics="sendCampainMailsValidId" />
			</div>
		</div>
	</div>
</s:form>


<div id="getCampaignsStatsDivId"></div>

<script language="JavaScript" type="text/javascript">
changePageTitle("Send Campaign Mails");
	$(document).ready(function() {
		
	});
	 $.subscribe('sendCampainMailsValidId', function(event, data) {
		if ($('#sendCampainMailsFormId').valid()){
			
			return true;
		}
		else
			event.originalEvent.options.submit=false;
	}); 
		
	  function getCampaignsList(listId) {
			if (isNonEmpty(listId)) {
				var url = jQuery.url.getChatURL("/admin/ajaxViewSchooHolidaysByClassId.do");
				var classId = selectBox;
				$("#getCampaignsListDivId").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				var pars = "listId=" + listId;
				$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#getCampaignsListDivId").html(html);
					}
				});
			}
			else
				{
					alert("Please select atleast one list.")
				}
		} 
</script>
