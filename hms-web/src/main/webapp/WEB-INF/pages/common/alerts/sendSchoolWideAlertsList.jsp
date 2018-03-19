<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{alertsList != null && !alertsList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Title
				</th>
				<th>
					Message Type
				</th>
				<th>
					Message Date
				</th>
				<s:if test='%{user.isSchoolAdmin=="Y"}'>
					<th>
						Delete 
					</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="alertsList">
				<tr>
					<td>
						<a data-toggle="modal"  href="#alertInfoDiv" 
							onclick="javascript:PopupAlertInfoDiv(<s:property value="id" />);"><s:property value="title" />
						</a>
					</td>
					<td>
						<s:if test='%{receiverType=="SF"}'>
							Staff
						</s:if>
						<s:else>
							parent/student
						</s:else>
					</td>
					<td>
						<s:property value="messageDateStr" />
					</td>
					<s:if test='%{user.isSchoolAdmin=="Y"}'>
						<td>
						<s:if test='%{todayDate.compareTo(messageDateStr) == 0}'>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:url id="removeAlert" action="ajaxDeleteAlert"
									includeParams="all" escapeAmp="false" namespace="/common">
									<s:param name="id" value="id"></s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red" id='%{removeAlert}'
									theme="simple" title="Delete this Alert"
									onclick="javascript:confirmDialogWithTarget(this,'schoolWideAlertsHome');">
									<i class="fa fa-times"></i>Delete</s:div>
							</s:if>
							</s:if>
							<s:else>
								&nbsp;&nbsp; -
							</s:else>
						</td>
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Alerts.
	</div>
</s:else>
<div id="alertInfoDiv"></div>
<script language="JavaScript" type="text/javascript">
changePageTitle('School Wide Alerts ');
$('ul.nav-tabs li').removeClass('active');
$('li#schoolWideMessage').addClass('active');
TableAdvanced.init();
function PopupAlertInfoDiv(id) {
	var url = jQuery.url.getChatURL("/common/ajaxViewATAlert.do");
	$('#alertInfoDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "id=" + id,
			success : function(html) {
				$("#alertInfoDiv").html(html);
			}
		});
	}
</script>
