<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>

<s:if test="%{alertsList != null && !alertsList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
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
			</tr>
		</thead>
		<tbody>
			<s:iterator value="alertsList">
				<tr>
					<td>
					<a data-toggle="modal"  href="#schoolWideAlertsDiv" 
							onclick="javascript:PopupSchoolWideAlerts(<s:property value="id" />);"><s:property value="title" />
						</a>
						<!--<a  
							href="<c:url value='/common/ajaxViewATAlert.do' />?id=<s:property value="id"/>"
							class="swMessages" title="View Alert"><s:property
								value="title" /> </a>
					--></td>
					<td>
						<s:if test='%{receiverType=="SF"}'>
								Staff
						</s:if>
						<%-- <s:if test='%{receiverType=="ST"}'>
								Student
							</s:if>
						<s:else>
								Parent
							</s:else> --%>
					</td>
					<td>
						<s:property value="messageDateStr" />
					</td>
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
<div id="schoolWideAlertsDiv"></div>
<script language="JavaScript" type="text/javascript">
changePageTitle('School Wide Alerts ');
TableAdvanced.init();
function PopupSchoolWideAlerts(id) {
	var url = jQuery.url.getChatURL("/common/ajaxViewATAlert.do");
	$('#schoolWideAlertsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "id=" + id,
			success : function(html) {
				$("#schoolWideAlertsDiv").html(html);
			}
		});
	}
</script>

