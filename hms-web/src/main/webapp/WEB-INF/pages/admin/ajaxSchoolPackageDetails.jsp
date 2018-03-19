<%@ include file="/common/taglibs.jsp"%>
	<%@ include file="/common/messages.jsp"%>
		<s:if test='%{customer.packageDetails != null && !customer.packageDetails == ""}'>
			<table class="striped" width="100%" cellpadding="1" cellspacing="1">
				<thead>
					<tr>
						<th class="head" width="20%">
							Students Range
						</th>
						<th width="20%" class="head">
							Cost Per Student	
						</th>
						<th width="25%" class="head">
							Total Students Count
						</th>
						<th width="25%" class="head">
							Remaining Students Count
						</th>
						<th width="10%" class="head">
							Update Package
						</th>
					</tr>
				</thead>
					<tr class="loaded">
						<td  width="20%">
							Upto <s:property value="customer.packageDetails.studentsRange"/>	
						</td>
						<td width="20%">
							<s:property value="customer.packageDetails.costPerStudent"/>	
						</td>
						<td width="25%">
							<s:property value="tempId"/>
						</td>
						<td width="25%">
								<s:property value="%{(customer.packageDetails.studentsRange + customer.packageDetails.maxAllowableStudents)-(tempId)}"/>
						</td>
						<td>
						   <s:url id="doEditSchoolPackageDetails" action="ajaxDoUpdatePackageDetails" includeParams="all" escapeAmp="false" namespace="/admin">
						   </s:url>
						   <sj:a href="%{doEditSchoolPackageDetails}" onCompleteTopics="doEditPackageDetails" 
							  	targets="editPackageDetails%{customer.packageDetails.id}"  cssClass="normalEdit" title="Edit">
						   </sj:a>
						</td>  
					</tr>
					<tr id="editPackageDetails<s:property value='customer.packageDetails.id' />"
						style="display: none;" class="load"></tr>
				</table>
		</s:if>
		<s:else>
			No package details found. 
		</s:else>