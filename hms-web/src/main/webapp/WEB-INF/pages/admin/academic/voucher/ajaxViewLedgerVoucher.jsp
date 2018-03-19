<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
	<div class="tempIsds">
	<s:if test="%{(tempList != null && !tempList.isEmpty()) && (tempList2 != null && !tempList2.isEmpty())}"> 
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_3">
			<thead>
				<tr>
					<th>Ledger Name</th>
					<th>group Name</th>
					<s:if test='%{#session.previousYear == "N"}'>
						<th>Edit</th>
						<!-- <th>Delete</th> -->
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList">
				<s:set var="groupId" value="%{id}"></s:set>
					<s:iterator value="ledgerDetails">
						<tr>
							<td><s:property value="ledgerName" /></td>
							<td><s:property value="groupName" /></td>
							<s:if test='%{#session.previousYear == "N"}'>
								<td>
								<a data-toggle="modal" href="#ledgerDiv"
									class="btn btn-xs purple" onclick="javascript:PopupEditLedger(<s:property value="%{id}" />,<s:property value="#groupId" />);"><i
										class="fa fa-edit"></i>Edit </a>
								</td>
								<%-- <td>
									<s:url id="removeDayBook" action="ajaxRemoveLedger"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="ledgerDetails.id" value="%{id}" />
									</s:url> <s:div cssClass="btn btn-xs red"
										onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
										id='%{removeDayBook}' title="Delete this subject">
										<i class="fa fa-times"></i>Delete
									</s:div>
								</td> --%>
							</s:if>
						</tr>
					</s:iterator>
				</s:iterator>
			</tbody>
		</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no ledger.</div>
		</s:else>
		</div>
	</div>
</div>
<div id="ledgerDiv"></div>
<script type="text/javascript">
TableAdvanced.init();
function PopupEditLedger(id,accountGroupId) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoLedgerVoucher.do");
	$('#ledgerDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "ledgerDetails.id=" + id+"&accountGroup.id=" + accountGroupId,
			success : function(html) {
				$("#ledgerDiv").html(html);
			}
		});
	}
</script>