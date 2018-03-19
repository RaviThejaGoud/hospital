<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="form-body">
<s:form id="addProvisionItemsFormId"
	action="ajaxAddProvisionItems" theme="simple" name="myform"
	cssClass="form-horizontal" namespace="/hostel" method="post">
	<s:hidden name="tempString" cssClass="provistionItemsDataClass"></s:hidden>
		<span class="label label-danger"> NOTE : </span>&nbsp;
				You can add provision items.
		<h4 class="bold pageTitle">
			Add Provision Items
		</h4>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Item Name
					</th>
					<th>
						Measurement
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
								<s:select list="#{'units':'Units','kg':'Kg','ltr':'Ltr'}" cssClass="form-control input-medium required textfield measurement" label="Select Category" id="measurement%{id}"
									required="true" name="measurement" headerKey="" headerValue="- Select -" requiredposition="first" cssStyle="width:200px;height:35px">
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
			<sj:submit   targets="messSettingContent" value="Submit"
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
$.destroyTopic("addProvisionItemsValidation");
	$('.numeric').numeric( {
		allow : "."
	});
});
$.subscribe('addProvisionItemsValidation', function(event, data) {
	var provistionItemsClassId = 0;
	var itemName = '';
	var measurement = 0;
	var jsonObj = [];
	var boolVal=false;
	$('tr.provistionItemsData') .each(function() {										
		provistionItemsClassId = $(this).find("td.provistionItemsClassId").attr("id");
		itemName = $(this).find(".itemName").val();
		measurement = $(this).find(".measurement").val();
		if (isNonEmpty(provistionItemsClassId) && isNonEmpty(itemName) && isNonEmpty(measurement)) {
			jsonObj.push( {
	            "itemName" : itemName,
				"measurement" : measurement,
				"provistionItemsClassId" : provistionItemsClassId
			});
		}
		else{
		 boolVal=true;
		}
	});
		if(boolVal){
		  alert(" Item Name and Measurement can not be empty."); 
		  event.originalEvent.options.submit = false;
		}										
	$('.provistionItemsDataClass').val(JSON.stringify(jsonObj));
	if (isNonEmpty(JSON.stringify(jsonObj))
			&& (JSON.stringify(jsonObj)) == '[]') {
		alert("Please add at least one Item Name and Measurement  using 'Add Provision Items' link.");
		event.originalEvent.options.submit = false;
	}
});
var rowCount = 1;
function showProvisionItemsCreationForm() {
	$("tbody")
			.append(
					'<tr class="provistionItemsData"><td class="provistionItemsClassId" id="0">'
							+ '<div><input type="text" maxlength="45" class="form-control input-medium required itemName" id="itemName'
							+ rowCount
							+ '" name="itemName'
							+ rowCount
							+ '"/>'
							+ '</div></td>'
							
							+ '<td><div><select class="form-control input-medium required textfield measurement" required="true" requiredposition="first" style="width:200px;height:35px"  id="measurement'
							+ rowCount
							
							+ '" name="provisionItems.measurement'
							+ rowCount
							+ '">'
							
							+ '<option value="">- Select -</option><option value="units">Units</option><option value="kg">Kg</option><option value="ltr">Ltr</option></select></div></td>'
							
							+ '<td><div><a title="Delete"  id="removeActivityGrade"></a><div class="btn btn-xs red newDeleteRecord"><i class="fa fa-times">&nbsp;Delete</i></div></div></td>'
							+ '</tr>');
	rowCount++;
	$('.numeric').numeric( {
		allow : "."
	});
$('div.newDeleteRecord').on('click',function(){
	 $(this).parents('tr').remove();
});
}
</script>