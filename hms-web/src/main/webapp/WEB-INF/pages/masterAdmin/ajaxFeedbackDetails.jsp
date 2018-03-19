<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:if test="%{customersList != null && !customersList.isEmpty()}">
<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Name
				</th>
				<th>
					Email
				</th>
				<th>
					Contact Number
				</th>
				<th>	
					Message
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="customersList">
				<tr>
					<td>
						<s:property value="customerName" />
					</td>
					<td>
						<s:property value="custEmail" />
					</td>
					<td>
						<s:property value="contactNumber" />
					</td>
					<td>
						<a data-toggle="modal" href="#feedBackDetailsDiv"
							class="btn btn-xs yellow"
							onclick="javascript:PopupFeedBackDetails(<s:property value="%{id}" />);"><i
							class="fa fa-file-o"></i>View </a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no Support tickets.
	</div>
</s:else>
<div id="feedBackDetailsDiv"></div>
<script type="text/javascript">
TableAdvanced.init();
function PopupFeedBackDetails(id) {
		var url = jQuery.url.getChatURL("/masterAdmin/ajaxViewCustomerMessage.do");
		$('#feedBackDetailsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "id=" + id,
				success : function(html) {
					$("#feedBackDetailsDiv").html(html);
				}
			});
		} 
</script>

