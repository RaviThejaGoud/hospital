<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Event Name
				</th>
				<th>
					Start Date
				</th>
				<th>
					End Date
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<a data-toggle="modal" href="#viewEventDetials"
							onclick="javascript:PopupViewEventDetials(<s:property value="id" />);"><s:property
								value="eventName" /> </a>


					</td>
					<td>
						<s:property value="eventStartDateStr" />
					</td>
					<td>
						<s:property value="eventEndDateStr" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no events.
	</div>
</s:else>
<div id="viewEventDetials"></div>
<script type="text/javascript">
TableAdvanced.init();
function PopupViewEventDetials(id) {
	var url = jQuery.url.getChatURL("/student/ajaxViewSingleEvent.do");
	$('#viewEventDetials')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "eventId=" + id,
		success : function(html) {
			$("#viewEventDetials").html(html);
		}
	});
}
</script>


