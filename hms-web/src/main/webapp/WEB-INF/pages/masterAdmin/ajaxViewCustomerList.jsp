<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="form-body">
	<s:form id="selectStudentForm" cssClass="form-horizontal"
		theme="simple">
		<s:if test="%{customersList != null && !customersList.isEmpty()}">
			<h4 class="bold text-success">
				Total Customers Count :
				<s:property value="customersList.size" /> ( National : <s:property value="tempId2" /> , International: <s:property value="tempId1" /> ) 
			</h4>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th style="display:none;"></th>
						<th>Registration Date</th>
						<th>Cust Id</th>
						<th>Organization</th>
						<th>Type</th>
						<th>Email-Id</th>
						<th>Mobile Number</th>
						<th>Country</th>
						<th>Edit</th>
						<th>Sender Id</th>
						<th>Download Templates</th>
						<!-- <th>Delete</th> -->
						<th>InActive</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="customersList">
						<tr>
							<td style="display:none;"></td>
							<td><s:date name="createdDate" format="dd/MMM/yyyy"/>
							<td><s:property value="id" /></td>
							<td><s:property value="organization" /></td>
							<td><s:property value="accountType" /></td>
							<td><s:property value="custEmail" /></td>
							<td><s:property value="mobileNumber" /></td>
							<td><s:property value="countryName" /></td>
							<td><a data-toggle="modal" href="#doEditMasterAdminCustomer"
								class="btn btn-xs purple"
								onclick="javascript:PopupCustomerDetials(<s:property value="%{id}" />);"><i
									class="fa fa-edit"></i> Edit </a></td>
							<td>
								<s:if test="%{sender != null}">
								<a id="senId" href="#doEditSenderId" class="btn btn-xs green" onclick="javascript:popupEditSenderId(<s:property value="%{id}" />);">
								<i class="fa fa-edit"></i><s:property value="%{sender}" /></a></s:if>
								<s:else>
								<a id="senId" href="#doEditSenderId" class="btn btn-xs blue" onclick="javascript:popupEditSenderId(<s:property value="%{id}" />);">
								<i class="fa fa-edit"></i>No Sender Id</a>
								</s:else>
							</td>
							<td>
								<ul class="tooltipDiv">
									<li><a href="#" class="btn btn-xs yellow"> </i>Download
									</a>
										<ul class="tooltipSubDiv">
											<div class="popover bottom " style="display: none;">
												<div class="arrow"></div>
												<h3 class="popover-title">Downolad Templates</h3>
												<div class="popover-content">
													<s:if
														test="%{templateList != null && !templateList.isEmpty()}">
														<a rel="nofollow"
															href='<c:url value='/masterAdmin/downloadCustomerTemplates.do'/>?custId=<s:property value="id" />&tempString=ST'>
															ScoreCard Templates </a>
														<br />
														<a rel="nofollow"
															href='<c:url value='/masterAdmin/downloadCustomerTemplates.do'/>?custId=<s:property value="id" />&tempString=TL'>
															TC & LC </a>
														<br />
														<a rel="nofollow"
															href='<c:url value='/masterAdmin/downloadCustomerTemplates.do'/>?custId=<s:property value="id" />&tempString=BC'>
															BonaFied Certificate </a>
														<br />
														<a rel="nofollow"
															href='<c:url value='/masterAdmin/downloadCustomerTemplates.do'/>?custId=<s:property value="id" />&tempString=SC'>
															Study Certificate </a>
														<br />
													</s:if>
													<s:else>
														<li>No Download Templates found.</li>
													</s:else>
												</div>
											</div>
										</ul></li>
								</ul>
							</td>
							<%-- <td><s:url id="removeCustomer" action="ajaxDeleteCustomer"
									includeParams="all" escapeAmp="false" namespace="/masterAdmin">
									<s:param name="id" value="%{id}" />
								</s:url> <s:div cssClass="btn btn-xs red emsRemove"
									id='%{removeCustomer}' title="Delete this Customer">
									<i class="fa fa-times"></i> Delete
												</s:div></td> --%>
							<td><a data-toggle="modal" href="#inactiveCustomerDiv"
								class="btn btn-xs red"
								onclick="javascript:popupDisableCustomer(<s:property value="%{id}" />);"><i
									class="fa fa-times"></i> Inactive </a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no Customer details.</div>
		</s:else>
	</s:form>
</div>
<div id="doEditSenderId"></div>
<div id="doEditMasterAdminCustomer"></div>
<div id="inactiveCustomerDiv"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	    TableAdvanced.init();
	    UIExtendedModals.init();
		$("div#Customers").show();
		$("div#CustomerDetails").show();
	});
	function popupEditSenderId(custId) {
		var answer = confirm("Are you sure you want to updating sender Id ?");
		if (!answer) {
			$('a#senId').attr('data-toggle','');
			return false;
		} else {
			$('a#senId').attr('data-toggle','modal');
			var url = jQuery.url.getChatURL("/masterAdmin/ajaxDoEditSenderId.do");
			$('#doEditSenderId').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
				url : url,
				cache : false,
				data : "id=" + custId,
				success : function(html) {
					$("#doEditSenderId").html(html);
					
				}
			});
		}
	}
	
	function PopupCustomerDetials(custId) {
		var url = jQuery.url.getChatURL("/masterAdmin/ajaxDoEditCustomer.do");
		$('#doEditMasterAdminCustomer') .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			data : "custId=" + custId,
			success : function(html) {
				$("#doEditMasterAdminCustomer").html(html);
				
			}
		});
	}
	function popupDisableCustomer(custId){
		if(isNonEmpty(custId)){
		  var pars = "tempId=" + custId;
	        $.ajax( {
		        url : jQuery.url.getChatURL("/masterAdmin/ajaxDoDisableCustomer.do"),
				cache : true,
				data : pars,
				success : function(html) {
				$("#inactiveCustomerDiv").html(html);
				}
			});
		}
	 }
	
</script>