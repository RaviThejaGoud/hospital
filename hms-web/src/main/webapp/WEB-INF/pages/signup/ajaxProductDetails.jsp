<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{urtProduct != null}">
		<table width="100%">
			<tr>
				<td style="text-align: right" width="50%">
					createdBy :
				</td>
				<td style="text-align: left;">
					<s:property value="urtProduct.createdBy" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					createdDate :
				</td>
				<td style="text-align: left;">
					<s:property value="urtProduct.createdDate" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					lastAccessDate :
				</td>
				<td style="text-align: left;">
					<s:property value="urtProduct.lastAccessDate" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					lastUpdatedBy :
				</td>
				<td style="text-align: left;">
					<s:property value="urtProduct.lastUpdatedBy" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					link :
				</td>
				<td style="text-align: left;">
					<s:property value="urtProduct.link" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					name :
				</td>
				<td style="text-align: left;">
					<s:property value="urtProduct.name" />
				</td>
			</tr>

			<tr>
				<td style="text-align: right">
					Satus :
				</td>
				<td style="text-align: left;">
					<s:property value="urtProduct.status" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right">
					Amount :
				</td>
				<td style="text-align: left;">
					<s:property value="paymentTransaction.amount" />
				</td>
			</tr>
		</table>
	</s:if>
