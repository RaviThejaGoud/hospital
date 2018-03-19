<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:form action="ajaxSendCampaignMails" theme="simple"
	cssClass="form-horizontal" id="sendCampainMailsFormId" method="post"
	enctype="multipart/form-data" namespace="/admin">
	<s:hidden name="from_email" value="%{customer.mailChimpUserName}"></s:hidden>
	<div class="form-body">
		<div class="row">
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						<span class="required">*</span>Lists :
					</label>
					<div class="col-md-6">
						<s:select id="state" list="selectboxMap" label="Lists"  
							cssClass="form-control required"
							headerKey="" headerValue="- Select -" name="listId" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						<span class="required">*</span>From Email :
					</label>
					<div class="col-md-6">
						<sj:textfield name="customer.mailChimpUserName" id="mailChimpUserName" readonly="true"
							cssClass="form-control required" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						<span class="required">*</span>From Name :
					</label>
					<div class="col-md-6">
						<sj:textfield name="from_name" id="City"
							cssClass="form-control required" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						<span class="required">*</span>Subject :
					</label>
					<div class="col-md-6">
						<sj:textfield name="subject" id="City"
							cssClass="form-control required" maxlength="60"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<div class="col-md-6">
					<label class="control-label col-md-5">
						<span class="required">*</span>Description :
					</label>
					<div class="col-md-6">
					
						<%-- <sj:textarea name="description" id="description" cssClass="form-control required"
							placeholder="This textarea has a limit of 2000 chars." rows="4" cols="20" ></sj:textarea> --%>
						<!-- <span class="help-block"> Maxlength is 2000 chars. </span> -->
						
						
						<sj:textarea name="description" id="description"
						rows="5" cssClass="wysihtml5 form-control messagesArea1 required"
						cols="30"></sj:textarea>
						
						
					</div>
				</div>
			</div>
		</div>
		
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					formIds="sendCampainMailsFormId" targets="mainContentDiv" validate="true"
					onBeforeTopics="sendCampainMailsValidId" />
			</div>
		</div>
	</div>
</s:form>


<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>


<script language="JavaScript" type="text/javascript">
changePageTitle("Send Campaign Mails");
	
$('.messagesArea1').wysihtml5({
	"font-styles" : true, //Font styling, e.g. h1, h2, etc. Default true
	"emphasis" : true, //Italics, bold, etc. Default true
	"lists" : true, //(Un)ordered lists, e.g. Bullets, Numbers. Default true
	"html" : true, //Button which allows you to edit the generated HTML. Default false
	"link" : true, //Button to insert a link. Default true
	"image" : false, //Button to insert an image. Default true,
	"color" : false
//Button to change color of font  
});

	 $.subscribe('sendCampainMailsValidId', function(event, data) {
		if ($('#sendCampainMailsFormId').valid()){
			
			return true;
		}
		else
			event.originalEvent.options.submit=false;
	}); 
		
	/*  function getTemplatesList(selectBox) {
			if (isNonEmpty(selectBox)) {
				var url = jQuery.url.getChatURL("/admin/ajaxViewSchooHolidaysByClassId.do");
				var classId = selectBox;
				$("#classHolidaysDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				var pars = "classId=" + classId;
				$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#classHolidaysDiv").html(html);
					}
				});
			}
		} */
</script>
