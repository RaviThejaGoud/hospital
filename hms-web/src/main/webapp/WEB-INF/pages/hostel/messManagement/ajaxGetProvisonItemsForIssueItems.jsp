<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
<s:if test="%{tempList!= null && !tempList.isEmpty()}">
<s:hidden name="tempString" cssClass="provistionItemsDataClass"></s:hidden>
	<table
		class="table table-striped table-bordered table-hover table-full-width getProvisionItemsToIssueTable"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Item Name
				</th>
				<th>
					Available Qty
				</th>
				<th>
					Remaining Qty
				</th>
				<s:iterator value="tempList">
				<th>
					<s:property value="foodTypeName"/>
				</th>
				</s:iterator>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<div class="spaceDiv"></div>
	<div class="grid_12" id="addProvisionItemsDivId">
		<a href="javascript:void(0)"
			onclick="javascript:showProvisionItemsCreationForm();"
			class="btn btn-xs green" style="width: 165px"><b><i
				class="fa fa-plus"></i> Add Provision Items</b> </a>
	</div>
	<div class="spaceDiv"></div>
	<div class="spaceDiv"></div>
	<div class="form-actions fluid">
		<div class="col-md-offset-3 col-md-9">
			<sj:submit  targets="itemsToStoreDivId" value="Submit"
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
	</s:if>
	<s:else>
	<div class="alert alert-info">
		Currently there are no mess food types.
	</div>
</s:else>
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
	$('tr.provistionItemsData').each(function() 
	{		
		provistionItemsClassId = $(this).find("td.provistionItemsClassId").attr("id");
		itemName = $(this).find(".itemName").val();
		if (isNonEmpty(itemName)) {
			/* jsonObj.push( {
			            "itemName" : itemName,
						"quantity" : quantity,
						"price" : price,
						"purchaseDate" : purchaseDate,
						"provistionItemsClassId" : provistionItemsClassId
					}); */
					
			jsonObj.push( {
	            "itemName" : itemName
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
	
	var jsonArray = {"data":jsonObj}
	$('.provistionItemsDataClass').val(JSON.stringify(jsonArray));
	
	if (isNonEmpty(jsonArray)	&& (jsonArray) == '[]') {
		alert("Please add at least one Item Name and quantity  using 'Add Provision Items' link.");
		event.originalEvent.options.submit = false;
	}
});
var rowCount = 1;
function showProvisionItemsCreationForm() {
	
	var rowValue='';
	var tempListSize = "<s:property value='tempList1.size()'/>";
	
	if(tempListSize>=1)
	{
		rowValue =  rowValue + '<tr class="provistionItemsData" id="'+rowCount+'">';
		
		rowValue =  rowValue + '<td class="provistionItemsClassId" id="0"><div><select class="form-control input-medium required textfield itemName" required="true" requiredposition="first" style="width:200px;height:35px"  id="itemName';
		rowValue =  rowValue + rowCount + '" name="provisionItems.measurement' + rowCount + '" onchange="javascript:getAvailableProvisionItemsToMess(this.value,'+rowCount+');">';
		rowValue =  rowValue + '<option value="">- Select -</option>';
		
		<s:iterator value="tempList1">
			rowValue =  rowValue + '<option value="<s:property value="id"/>"><s:property value="itemMeasurement"/></option>';
		</s:iterator> 
		rowValue =  rowValue + '</select></div></td>'
		rowValue =  rowValue + '<td><div>';
		rowValue =  rowValue + '<input type="text" maxlength="45" class="form-control input-small required quantity numeric" disabled="true" id="fixedQuantityDivId' 
		rowValue =  rowValue + rowCount + '" name="manageItems.quantity' + rowCount + '"/>';
		
		rowValue =  rowValue + '</div></td>';
		
		rowValue =  rowValue + '<td><div>';
		rowValue =  rowValue + '<input type="text" maxlength="45" class="form-control input-small required quantity numeric" disabled="true" id="quantityDivId' 
		rowValue =  rowValue + rowCount + '" name="manageItems.quantity' + rowCount + '"/>';
		
		rowValue =  rowValue + '</div></td>';
		
		<s:iterator value="tempList">
			rowValue =  rowValue + '<td><div>';
			rowValue =  rowValue + '<input type="text" maxlength="45" class="form-control input-small required price numeric meessFoodType'+rowCount+'" onkeyup=javascript:checkAvailableQty(this.value,'+rowCount+',"'+ rowCount + "_" + '<s:property value="id" />"); id="messFoodType_' + rowCount + "_" + '<s:property value="id" />"'
			rowValue =  rowValue + '" name="messFoodType_' + rowCount + "_" + '<s:property value="id" />"/>';
			
			rowValue =  rowValue + '</div></td>';
		</s:iterator>
		
		rowValue =  rowValue + '<td><div><a title="Delete"  id="removeActivityGrade"></a><div class="btn btn-xs red newDeleteRecord"><i class="fa fa-times">&nbsp;Delete</i></div></div></td>';
		rowValue =  rowValue + '</tr>';
		
		/* var tds1 = $('table.getProvisionItemsToIssueTable').find("tr#1 td").length;
		
		 alert(tds1);
		 var tds = $('table.getProvisionItemsToIssueTable').children('tbody').children('tr').children('td').length;
		 alert(tds); */
		$("tbody").append(rowValue);

		if(tempListSize <= rowCount)
		{
			$('#addProvisionItemsDivId').hide();
		}
		

		rowCount++;
		FormComponents.init();
		$('.numeric').numeric( {
			allow : "."
		});
		$('div.newDeleteRecord').on('click',function()
		{
			 $(this).parents('tr').remove();
			 $('#addProvisionItemsDivId').show();
		});
	}
	else
	{
		alert("Item Names are empty, so please add item Names");
	}
}

function getAvailableProvisionItemsToMess(itemId,rowId) 
{
	
	var i=1;
	$('tr.provistionItemsData').each(function() 
	{		
		itemName = $(this).find(".itemName").val();
		var itemNameText = "itemName"+i;
		if (isNonEmpty(itemName)) 
		{
			var salectedItem = $("#"+itemNameText + " option[value='"+itemName+"']").val();
			if(Number(salectedItem) == itemId && i!=rowId)
			{
				alert("you have already selcted this item and please select different item.")
				$('#itemName'+rowId).val('');
				$('#quantityDivId'+rowId).val('0');
				itemId=0;
				
			}
		}
		 i = i+1;
	});
	
	var messId = $('#selectedMessId').val();
	if(!isNonEmpty(messId))
	{
		alert("Please select Mess."+rowId);
	}
	if(isNonEmpty(itemId) && isNonEmpty(messId)){
		//$('#issueProvisionItemsToMessDivId').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId=" + messId + "&tempId1="+itemId;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxCalculateAvailableProvisionItemsToMess.do"),
			cache : false,
			dataType : 'json',
			data : pars,
			success : function(response) 
			{
				if (isNonEmpty(response)) 
				{
					if(isNonEmpty(response.AvailableQty)){
						$('#quantityDivId'+rowId).val(response.AvailableQty);
						$('#fixedQuantityDivId'+rowId).val(response.AvailableQty);
						//var meessFoodTypeText = "meessFoodType"+rowId;
					     // $("."+meessFoodTypeText).val('0');
					}else{
						$('#quantityDivId'+rowId).val('0');
					}
					
				}else{
					$('#quantityDivId'+rowId).val('0');
					
				}
			}
		});
	}
}
 

function checkAvailableQty(givenQty,rowId,foodTypeRow) 
{
	var fixedQuantity = $('#fixedQuantityDivId'+rowId).val();
	var sum = 0;
	var meessFoodTypeText = "meessFoodType"+rowId;
	
	  $("."+meessFoodTypeText).each(function() {
		  
		  if(!isNaN(this.value) && this.value.length!=0) {
	            sum += parseFloat(this.value);
	            
	            if(sum < 0)
				{
					alert("Please select Item Name");
					$('#messFoodType_'+foodTypeRow).val('');
				}
				if(sum > fixedQuantity)
				{
					alert("Please enter less the available Qty");
					$('#messFoodType_'+foodTypeRow).val('');
					sum = sum - givenQty;
				}
	        }
      });
	if(sum <= fixedQuantity)
	{
		 $('#quantityDivId'+rowId).val(fixedQuantity-sum);
		  return true;
	}
}

</script>
