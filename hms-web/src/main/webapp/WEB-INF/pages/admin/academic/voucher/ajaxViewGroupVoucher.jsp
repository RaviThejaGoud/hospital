<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
	<s:if test="%{objectList != null && !objectList.isEmpty()}"> 
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_3">
			<thead>
				<tr>
					<th>Group Name</th>
					<th>Category Type</th>
					<s:if test='%{#session.previousYear == "N"}'>
						<th>Edit</th>
						<!-- <th>Delete</th> -->
					 </s:if>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="objectList" status="stat">
				 <%-- <s:set var="groupId" value="%{id}"></s:set> --%>
				  <%--  <s:iterator value="accountGroup"> --%>
					<tr>
						<td><s:property value="objectList[#stat.count-1][2]"  /></td>
						<td><s:property value="objectList[#stat.count-1][1]"  /></td>
						<s:if test='%{#session.previousYear == "N"}'>
						<td>
								<a data-toggle="modal" href="#accountGroupDiv"
									class="btn btn-xs purple"
									onclick="javascript:PopupEditGroupDetials(<s:property value="objectList[#stat.count-1][0]"  />,<s:property value="objectList[#stat.count-1][3]"  />);">
									<i class="fa fa-edit"></i>Edit
								</a>
							</td>
							<%-- <td><s:url id="removeDayBook" action="ajaxRemoveGroupVoucher"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="accountGroup.id" value="%{objectList[#stat.count-1][0]}" />
									</s:url> <s:div cssClass="btn btn-xs red"
										onclick="javascript:confirmDialogWithTarget(this,'voucherContentDiv');"
										id='%{removeDayBook}' title="Delete this subject">
										<i class="fa fa-times"></i>Delete
								</s:div>
						</td> --%>
						</s:if>
					</tr>
				  <%--  
				</s:iterator> --%>
				</s:iterator>
			</tbody>
		</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no groups.</div>
		</s:else>
	</div>
</div>
<div id="accountGroupDiv"></div>
<script type="text/javascript">
TableAdvanced.init();
	$.subscribe('addGroup', function(event, data) {
		if ($('#GroupVoucherId').valid()) 
				return true;
			  else
				event.originalEvent.options.submit = false;
	});
	function PopupEditGroupDetials(id,accountCategorysId) {
		var url = jQuery.url.getChatURL("/admin/ajaxDoGroupVoucher.do");
		$('#accountGroupDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "accountGroup.id=" + id+"&accountCategorys.id="+accountCategorysId,
				success : function(html) {
					$("#accountGroupDiv").html(html);
				}
			});
		}
</script>