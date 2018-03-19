<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Govt Customers
				</div>
			</div>
			<div class="portlet-body">
					<div class="tab-content" id="govContentDiv">
						<%@ include file="/common/messages.jsp"%>
						<s:if test="%{customersList != null && !customersList.isEmpty()}">
							<h4 class="bold text-success">
								Total Customers Count :
								<s:property value="customersList.size" />
							</h4>
							<table
								class="table table-striped table-bordered table-hover table-full-width"
								id="sample_2">
								<thead>
									<tr>
										<th>
											Organization
										</th>
										<th>
											Status
										</th>
										<th>
											Email-Id
										</th>
										<th>
											Contact Number
										</th>
										<th>
											Edit
										</th>
										<th>
											Delete
										</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="customersList">
										<tr>
											<td>
												<s:property value="organization" />
											</td>
											<td>
												<s:if test='%{status== true}'>
													A
												</s:if><s:else>N</s:else>
												
											</td>
											<td>
												<s:property value="custEmail" />
											</td>
											<td>
												<s:property value="contactNumber" />
											</td>
											<td>
											<a data-toggle="modal" href="#doEditMasterAdminCustomer"
													class="btn btn-xs purple"
													onclick="javascript:PopupCustomerDetials(<s:property value="%{id}" />);"><i
													class="fa fa-edit"></i> Edit </a>
											</td>
											<td>
												<s:url id="removeCustomer" action="ajaxDeleteCustomer"
													escapeAmp="false" namespace="/masterAdmin">
													<s:param name="id" value="id"></s:param>
												</s:url>
												<s:div cssClass="btn btn-xs red emsRemove" id='%{removeCustomer}'
													theme="simple" title="Delete this customer">
													<i class="fa fa-times"></i> Delete</s:div>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Currently there are no Customer details.
							</div>
						</s:else>
					</div>
			</div>
		</div>
	</div>
</div>
<div id="doEditMasterAdminCustomer"></div>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
function PopupCustomerDetials(custId) {
	var url = jQuery.url.getChatURL("/masterAdmin/ajaxDoEditGovtCustomer.do");
	$('#doEditMasterAdminCustomer')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "custId=" + custId,
		success : function(html) {
			$("#doEditMasterAdminCustomer").html(html);
		}
	});
}

$(document).ready(function() {
	$("div#showHideGovtCustomers").hide();
	$("div#govtCustomerDetails").hide();
});

var name = 1;
$('div.orgDiv').click(
		function() {
			$('div#customersList>div.item').sortElements(
					function(a, b) {
						return ($(a).attr('organization').toLowerCase() > $(b)
								.attr('organization').toLowerCase() ? 1 : -1)
								* name;
					});
			updateDirectionArrows($(this), name);
			name = name * -1;
			"event.originalEvent.options.submit=false;"
		});
</script>