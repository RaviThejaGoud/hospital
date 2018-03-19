<%@ include file="/common/taglibs.jsp"%>
<div class="tooltip" >
	<div class="toolTipPerson" >
		<div class="toolTipPersonDetails">
			<table >
				<tr>
					<td valign="top">
						Name:&nbsp;
					</td>
					<td>
						<s:property value="personName" />
					</td>
				</tr>
				<tr>
					<td valign="top">
						Title:&nbsp;
					</td>
					<td>
						<s:property value="title" />
					</td>
				</tr>
				<tr>
					<td valign="top" style="width: 40px;">
						Phone:&nbsp;
					</td>
					<td>
						<s:property value="phoneNumber" />
					</td>
				</tr>
			</table>

		</div>
	</div>
</div>