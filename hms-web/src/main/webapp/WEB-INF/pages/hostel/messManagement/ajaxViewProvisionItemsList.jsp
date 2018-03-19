<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList!= null && !objectList.isEmpty()}">
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;You can
		edit/update existing mess settings by clicking on edit pen icon in
		each row at right side.
	</p>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Merchant Name
				</th>
				<th>
					Item Name
				</th>
				 <th>
					Purchase Quantity
				</th>
				<!-- <th>
					Available Quantity
				</th> -->
				<th>
					Price
				</th>
				<th>
					Purchase Date
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
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="merchant.merchantName" />
					</td> 
					<td>
						<s:property value="provisionItems.itemName" />
					</td>
					
					<td>
						<s:property value="quantityMeasurementStr" />
					</td>
					<%-- <td>
						<s:property value="quantity" />
					</td> --%>
					<td>
						<s:property value="price" />
					</td>
					<td>
						<s:property value="purchaseDateStr" />
					</td>
					
					<td>
						<%-- <s:if test='%{#session.previousYear == "N"}'>
							<a data-toggle="modal" href="#editBuildingSettingsDiv"
								class="btn btn-xs purple"
								onclick="javascript:PopupEditBuildingSettings(<s:property value="%{buildingId}" />);"><i
								class="fa fa-edit"></i>Edit </a>
						</s:if> --%>
						--
					</td>
					<td>
						<%-- <s:url id="removeBuilding" action="ajaxDeleteBuilding"
							includeParams="all" escapeAmp="false" namespace="/hostel">
							<s:param name="tempId2" value="%{buildingId}" />
						</s:url>
						<s:div cssClass="btn btn-xs red" id='%{removeBuilding}'
							theme="simple"
							onclick="javascript:confirmDialogWithTarget(this,'hostelSettingContent');"
							title="Delete this Building">
							<i class="fa fa-times"></i> Delete</s:div> --%>
							--
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no store items.
	</div>
</s:else>

<div id="editBuildingSettingsDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	UIExtendedModals.init();
});


</script>