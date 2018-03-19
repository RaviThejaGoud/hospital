<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>  
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 700px; margin-left: -340px; margin-top: 120px;">		
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Sms Delivery Details				
			</h4>
		  </div>
		  <s:if test="%{messagesList != null && !messagesList.isEmpty()}">  
		<div class="modal-body">
			<table
				class="table table-striped table-bordered table-hover table-full-width dataTable"
				id="sample_2">
				<thead>
					<tr>
						<td>
							<strong> Admission Number</strong>
						</td>
						<td>
							<strong> Class Name</strong>
						</td>
						<td>
							<strong> Person Name</strong>
						</td>
						<td>
							<strong> Mobile Number</strong>
						</td>
						<!-- <td align="center">
							<strong> Delivery Status</strong>
						</td> -->				
					</tr>
				</thead>
				<tbody>
					<s:iterator value = "messagesList"> 
						<tr>
							<td>
								<s:if test="%{account.admissionNumber != null || account.admissionNumber != ''}">
							 		<s:property value="account.admissionNumber" />
								</s:if>
								<s:else>
									<strong> - </strong> 
								</s:else>
							</td>
							<td>	
								<s:if test="%{account.admissionNumber != null || account.admissionNumber != ''}">
									<s:property value="className" />
								</s:if>
								<s:else>
									<strong> - </strong>
								</s:else>
							</td>
							
							<td>	
								<s:if test="%{account.fullPersonName != null || account.fullPersonName != ''}">
									<s:property value="account.fullPersonName" />
										<s:if test='%{account.isSchoolStudent == "Y"}'>
											(student)
										</s:if>
										<s:elseif test='%{account.isParent == "Y"}'>
											(Parent)
										</s:elseif>
										<s:else>
											(Staff)
										</s:else>
								</s:if>
								<s:else>
									<strong> - </strong>
								</s:else>	
							</td>
							
							
											
							<td>
								<s:property value="mobileNumber" />
							</td>
							<%-- <td align="center">
								<s:if test="%{deliveryStatus == 'S'}">
									<font color="green"><b>Success</b> </font>
								</s:if>
								<s:elseif test="%{deliveryStatus == 'F'}">
									<font color="red"><b>Failed</b> </font>
								</s:elseif>
								<s:elseif test="%{deliveryStatus == 'I'}">
									<font color="purple"><b>Invalid</b> </font>
								</s:elseif>	
								<s:elseif test="%{deliveryStatus == 'N'}">
									<font color="blue"><b>Inprogress</b> </font>
								</s:elseif>	
							</td>  --%>		
						</tr>
			    	  </s:iterator>	
			    	 </tbody>		
			    	</table> 
			      </div>
			    </s:if> 				 
				<s:else>
		 			<h5> You don't have any sms details tracking </h5>
		 		</s:else>
		 	</div>
		 	
		 		
