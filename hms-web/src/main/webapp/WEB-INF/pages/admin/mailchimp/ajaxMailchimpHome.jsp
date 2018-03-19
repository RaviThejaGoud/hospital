<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Mailchimp
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{customer.mailChimpAPIKey !=null}'>
						
							<li>
								<s:url id="doUploadEmailsToMailchimp" action="ajaxDoUploadEmailsToMailchimp"
									includeParams="all" escapeAmp="false" namespace="/admin">
								</s:url>
								<sj:a href="%{doUploadEmailsToMailchimp}" data-toggle="tab"
									targets="mailChimpDivId" button="false">Upload Emails To Mailchimp</sj:a>
							</li>
							
							<li>
								<s:url id="doGetCampaignStats" action="ajaxDoGetCampaignStats"
									includeParams="all" escapeAmp="false" namespace="/admin">
								</s:url>
								<sj:a href="%{doGetCampaignStats}" data-toggle="tab"
									targets="mailChimpDivId" button="false">Get Campaign Stats</sj:a>
							</li>
							<li>
								<s:url id="doAddHolidays" action="ajaxDoSendCampaignMails"
									includeParams="all" escapeAmp="false" namespace="/admin">
								</s:url>
								<sj:a href="%{doAddHolidays}" data-toggle="tab"
									targets="mailChimpDivId" button="false">Send Campaign Mails</sj:a>
							</li>
						</s:if> 		
						<li class="active">
							<s:url id="viewHolidays" action="ajaxMailchimpAccountSettings"
								namespace="/admin">
							</s:url>
							<sj:a id="viewHolidays" href="%{viewHolidays}"
								targets="mainContentDiv" data-toggle="tab">Account Settings</sj:a>
						</li>
					</ul>
					<table style="float: right;">
			<tr>
				<td>
					<div>
						<a style="" href="<c:url value="http://www.mailchimp.com/"/>"
							target="_new"> What is MailChimp? </a>
					</div>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					<div>
						<a href="<c:url value="http://www.mailchimp.com/signup/h"/>"
							target="_new"> How can we sign up? </a>
					</div>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					<div>
						<a style=""
							href='<c:url value="http://us1.admin.mailchimp.com/lists"/>'
							target="_new">Create New List </a>
					</div>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					<div>
						<a style=""
							href='<c:url value="https://us1.admin.mailchimp.com/account/api"/>'
							target="_new"> Need an API Key? </a>
					</div>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					<div>

						<br />
					</div>
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					<!--<s:url id="urlnewgroup" action="ajaxDoConstantContactMailingList"/>
									<sj:a href="%{urlnewgroup}" targets="editGroupList">Create Contact
							List</sj:a> 
						-->
					<div>

					</div>
				</td>
			</tr>
		</table>
					<div id="mailChimpDivId" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
							 <jsp:include page="/WEB-INF/pages/admin/mailchimp/ajaxMailchimpAccountSettings.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Manage Mailchimp Settings');
});

</script>
