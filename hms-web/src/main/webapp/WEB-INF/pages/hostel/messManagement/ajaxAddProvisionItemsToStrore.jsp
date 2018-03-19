<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body">
<s:form id="addProvisionItemsFormId"
	action="ajaxAddProvisionItemsToStrore" theme="simple" name="myform"
	cssClass="form-horizontal" namespace="/hostel" method="post">
	<s:hidden name="tempString" cssClass="provistionItemsDataClass"></s:hidden>
		<span class="label label-danger"> NOTE : </span>&nbsp;
				You can add provision items to store.
		<h4 class="bold pageTitle">
			Add Items To Store
		</h4>
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required"> * </span>Select Mess :
			</label>
			<div class="col-md-3">
				<s:select list="tempList2" listKey="id" headerKey="" headerValue="- Select -" requiredposition="first"
					listValue="messName"
					cssClass="required form-control input-medium" id="selectedMessId"
					name="messId">
				</s:select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required"> * </span>Select Merchant :
			</label>
			<div class="col-md-3">
				<s:select list="tempList" listKey="id" headerKey="" headerValue="- Select -" requiredposition="first"
					listValue="merchantName"
					cssClass="required form-control input-medium" id="merchantId"
					name="merchantId">
				</s:select>
			</div>
		</div>
		<div class="table-scrollable">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Item Name
					</th>
					<th>
						quantity
					</th>
					<th>
						Price
					</th>
					<th>
						Purchase Date
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<tbody>
				<s:if test="%{objectList != null && !objectList.isEmpty()}">
					<s:iterator value="objectList">
						<tr class="provistionItemsData">
							<td id='<s:property value='id'/>' class='provistionItemsClassId'>
									<sj:textfield name="itemName" id="itemName%{id}"
										maxlength="45"
										cssClass="form-control input-medium required itemName"></sj:textfield>
							</td>
							<td>
								<s:select list="#{'units':'Units','kg':'Kg','ltr':'Ltr'}" cssClass="form-control input-medium required textfield quantity" label="Select Category" id="quantity%{id}"
									required="true" name="quantity" headerKey="" headerValue="- Select -" requiredposition="first" cssStyle="width:200px;height:35px">
								</s:select>
							</td>
							<%-- <td>
									<s:url id="removeProvisionItems"
										action="ajaxRemoveProvisionItems" includeParams="all"
										escapeAmp="false" namespace="/hostel">
										<s:param name="tempId" value="%{id}" />
									</s:url>
									<s:div cssClass="btn btn-xs red"
										onclick="javascript:confirmDialogWithTarget(this,'messSettingContent');"
										id='%{removeProvisionItems}' theme="simple"
										title="Delete this grade">
										<i class="fa fa-times"></i>Delete
									</s:div>
							</td> --%>
							<td>--
							</td>

						</tr>
					</s:iterator>
				</s:if>
			</tbody>
		</table>
		</div>
		<div class="spaceDiv">
		</div>
		<div class="grid_12">
			<a href="javascript:void(0)"
				onclick="javascript:showProvisionItemsCreationForm();"
				class="btn btn-xs green" style="width: 165px"><b><i
					class="fa fa-plus"></i> Add Provision Items</b> </a>
		</div>
		<div class="spaceDiv">
		</div>
		<div class="spaceDiv"></div>
	<div class="form-actions fluid">
		<div class="col-md-offset-3 col-md-9">
			<sj:submit  targets="mainContentDiv" value="Submit"
				cssClass="submit small btn blue"
				formIds="addProvisionItemsFormId" indicator="indicator"
				 onBeforeTopics="addProvisionItemsValidation" validate="true"/>
			<%-- <s:url id="urlImportActSheet" action="ajaxManageStudentActivities"
				includeParams="all" escapeAmp="false" namespace="/exam">
			</s:url>
			<sj:a href="%{urlImportActSheet}" cssClass="btn default"
				onCompleteTopics="highlight" indicator="downloadIndicator"
				targets="mainContentDiv">Cancel</sj:a> --%>
		</div>
	</div>
</s:form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	FormComponents.init();
$.destroyTopic("addProvisionItemsValidation");
	$('.numeric').numeric( {
		allow : "."
	});
	
});


$.subscribe('addProvisionItemsValidation', function(event, data) {
	var provistionItemsClassId = 0;
	var itemName = '';
	var quantity = 0;
	var price = 0;
	var purchaseDate = '';
	var jsonObj = [];
	var boolVal=false;
	$('tr.provistionItemsData') .each(
					function() {										
						provistionItemsClassId = $(this).find("td.provistionItemsClassId").attr("id");
						itemName = $(this).find(".itemName").val();
						quantity = $(this).find(".quantity").val();
						price =  $(this).find(".price").val();
						purchaseDate = $(this).find(".purchaseDate").val();
						if (isNonEmpty(provistionItemsClassId) && isNonEmpty(itemName) && isNonEmpty(quantity) && isNonEmpty(price)) {
							jsonObj.push( {
							            "itemName" : itemName,
										"quantity" : quantity,
										"price" : price,
										"purchaseDate" : purchaseDate,
										"provistionItemsClassId" : provistionItemsClassId
									});
						}
						else{
						 boolVal=true;
						}
					});
		if(boolVal){
		  alert(" Item Name and quantity can not be empty."); 
		  event.originalEvent.options.submit = false;
		}										
	$('.provistionItemsDataClass').val(JSON.stringify(jsonObj));
	if (isNonEmpty(JSON.stringify(jsonObj))
			&& (JSON.stringify(jsonObj)) == '[]') {
		alert("Please add at least one Item Name and quantity  using 'Add Provision Items' link.");
		event.originalEvent.options.submit = false;
	}
});
var rowCount = 1;
function showProvisionItemsCreationForm() {
	
	var rowValue='';
	
	rowValue =  rowValue + '<tr class="provistionItemsData">';
	
	rowValue =  rowValue + '<td class="provistionItemsClassId" id="0"><div><select class="form-control input-medium required textfield itemName" required="true" requiredposition="first" style="width:200px;height:35px"  id="itemName';
	rowValue =  rowValue + rowCount + '" name="provisionItems.measurement' + rowCount + '">';
	rowValue =  rowValue + '<option value="">- Select -</option>';
	
	<s:iterator value="tempList1">
		rowValue =  rowValue + '<option value="<s:property value="id"/>"><s:property value="itemMeasurement"/></option>';
	</s:iterator> 
	rowValue =  rowValue + '</select></div></td>'
	rowValue =  rowValue + '<td><div>';
	rowValue =  rowValue + '<input type="text" maxlength="45" class="form-control input-medium required quantity numeric" id="quantity'
	rowValue =  rowValue + rowCount + '" name="manageItems.quantity' + rowCount + '"/>';
	
	rowValue =  rowValue + '</div></td>';
	
	rowValue =  rowValue + '<td><div>';
	rowValue =  rowValue + '<input type="text" maxlength="45" class="form-control input-medium required price numeric" id="price'
	rowValue =  rowValue + rowCount + '" name="manageItems.price' + rowCount + '"/>';
	
	rowValue =  rowValue + '</div></td>';
	
	rowValue =  rowValue + '<td><div class="input-group input-medium date date-picker">';
	rowValue =  rowValue + '<input type="text" readonly="readonly" class="form-control purchaseDate required" id="date'
	rowValue =  rowValue + rowCount + '" name="manageItems.purchaseDate' + rowCount + '"/>';
	
	rowValue =  rowValue + '<span class="input-group-btn"><button type="button" class="btn default"><i class="fa fa-calendar"></i></button> </span>';
	rowValue =  rowValue + '</div><span class="help-block">(MM/DD/YYYY)</span></td>';
	
	rowValue =  rowValue + '<td><div><a title="Delete"  id="removeActivityGrade"></a><div class="btn btn-xs red newDeleteRecord"><i class="fa fa-times">&nbsp;Delete</i></div></div></td>';
	rowValue =  rowValue + '</tr>';
	
	$("tbody")
	.append(rowValue);

	rowCount++;
	FormComponents.init();
	$('.numeric').numeric( {
		allow : "."
	});
$('div.newDeleteRecord').on('click',function(){
	 $(this).parents('tr').remove();
});
}



</script>