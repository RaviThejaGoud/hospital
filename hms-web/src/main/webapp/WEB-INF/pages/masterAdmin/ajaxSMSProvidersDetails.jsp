<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Provider Name
				</th>
				<th>
					Url
				</th>
				<th>
					Status
				</th>
				<th>
					Edit
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="serviceProvider" />
					</td>
					<td>
						<s:property value="url" />
					</td>
					<td>
						<s:property value="activeUrl" />
					</td>
					<td>
						<s:url id="doEditSMSProvider" action="ajaxCreateSMSProvider"
							includeParams="all" escapeAmp="false" namespace="/masterAdmin">
							<s:param name="smsServiceProvider.id" value="%{id}" />
						</s:url>
						<sj:a href="%{doEditSMSProvider}" indicator="indicator"
							targets="smsProvidersCont" title="Edit" button="false"
							cssClass="btn btn-xs purple">
							<i class="fa fa-edit"></i>Edit
							</sj:a>
					</td>
				</tr>
			</s:iterator>
			<s:else>
				<div class="alert alert-info">
					Currently there are no SMS provider details.
				</div>
			</s:else>
		</tbody>
	</table>
</s:if>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
$('ul.nav-tabs li').removeClass('active');
$('li#smsProviderCountList').addClass('active');
var name = 1;
$('div.proNameDiv').click(
		function() {
			$('div#objectList>div.item').sortElements(
					function(a, b) {
						return ($(a).attr('serviceProvider').toLowerCase() > $(
								b).attr('serviceProvider').toLowerCase() ? 1
								: -1)
								* name;
					});
			updateDirectionArrows($(this), name);
			$("#objectList").pagination();
			name = name * -1;
			event.originalEvent.options.submit=false; 
		});
</script>
