<%@ include file="/common/taglibs.jsp"%>
 <table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Shop No
				</th>
				<th>
					Shop Key
				</th>
				<th>
					Dealer Name
				</th>
				<th>
					AadhaarNumber
				</th>
				<th>
					MobileNumber
				</th>
				<th>
					Address
				</th>
			</tr>
		</thead>
		<!--<tbody>
				<tr>
					<td id="shopKey">
					<s:url id="urlDealerDetails" action="ajaxDealerTransactions" namespace="/fps">
								<s:param name="classId" value="0" />
							</s:url>
							<sj:a id="dealerDetails" href="%{urlDealerDetails}" targets="mainContentDiv">#204</sj:a> 	
					</td>
					<td>
						222
					</td>
					<td id="name">
						Prasad
					</td>
					<td>
						8162112114211421
					</td>
					<td>
						9686680160
					</td>
					<td>
						#208B 3rd Floor Above Indus Bank
					</td>
				</tr>
		</tbody>
		-->
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
					<s:url id="urlDealerDetails" action="ajaxDealerTransactions" namespace="/fps">
								<s:param name="tempString" value="shopKey" />
							</s:url>
							<sj:a id="dealerDetails" href="%{urlDealerDetails}" targets="mainContentDiv">	<s:property value="shopNo" /></sj:a>
					</td>
					<td>
						<s:property value="shopKey" />
					</td>
					<td>
						<s:property value="dealerName" />
					</td>
					<td>
						<s:property value="aadhaarNumber" />
					</td>
					<td>
						<s:property value="mobileNumber" />
					</td>
					<td>
						<s:property value="addressLine1" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
 
<script type="text/javascript">
TableAdvanced.init();
</script>
