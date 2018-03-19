<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14">
<s:if test="%{foodTypeList != null && !foodTypeList.isEmpty()}">
	<span class="foodTypesCount" id="<s:property value='foodTypeList.size'/>"></span>
	<s:if test="%{messTimeingsList != null && !messTimeingsList.isEmpty()}">
		<div class="noteFont grid_12">
			<div class="grid_1">
		 		<span class="noteMassage"> Note :</span>
		 	</div>
		 	<div class="grid_11">
		 		Simple..! Just type one or more food item name(s) [Separated by comma] and click on submit. 
		 	</div>
		</div>
		<div class="grid_13">
			<div class="grid_3">
			 &nbsp;
			</div>
			<s:if test="%{foodTypeList != null && !foodTypeList.isEmpty()}">
				<s:iterator value="foodTypeList">
					<div class="grid_3">
						<s:property value="foodTypeName" /> 
					</div>
				</s:iterator>
			</s:if>
		</div>
		<s:iterator value="messTimeingsList">
			<s:set name="messFoodTypeId" value="%{id}"></s:set>
			<span class="menuItemsData"> 
				<span id='<s:property value='id'/>' class='menutypeId'></span>
				 <div class="grid_13" id="results">
					<div class="grid_3">
						<label>
							<s:property value="messFoodTypeName" /> :<br/>
						</label>
						<span class="hintMessage">(<s:property value="startTime" />&nbsp;to&nbsp;<s:property value="endTime" />)</span>
					</div>
					<s:iterator value="foodTypeList">
						<div class="grid_3">
							<sj:textfield name="menuItemNames" id="%{id}~%{#messFoodTypeId}"  
								cssClass="textfield foodType" cssStyle="width:150px;"></sj:textfield>
						</div>
					</s:iterator>
				</div> 
			</span>
		</s:iterator>
		<div class="grid_13 thb">
		</div>
	</s:if>
	<s:else>
		<div class="grid_13">
			Currently there are no days.
		</div>
	</s:else>
</s:if>
<s:else>
	You have not created food types.Please add food types.
</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	getEditMenuItemsByDayIdAndBuildingId();
});
function getEditMenuItemsByDayIdAndBuildingId() {
var dayId = $('#dayId').val();
	$('.dayId').val(dayId);
	var buildingId = $("#buldingId").val();
	if (isNonEmpty(dayId) && isNonEmpty(buildingId)) {
		var editfoodMenuItemsURL = jQuery.url.getChatURL("/hostel/ajaxEditFoodMenuItems.do?selectedId="+ buildingId+"&anyId="+dayId);
		$.ajax( {
				url : editfoodMenuItemsURL,
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (response.data) {
						var data = response.data;
						var foodTypeId;
						var messMenuTypeId;
						var menuItemNames;
						var foodMenuItemsId;
						var inputObj;
						if (data.length >= 1) {
							for ( var i = 0; i < data.length; i++) {
								foodTypeId = data[i].foodTypeId;
								messMenuTypeId = data[i].messMenuTypeId;
								menuItemNames = data[i].menuItemNames;
								foodMenuItemsId = data[i].foodMenuItemsId;
								inputObj = $('input[id^=' + foodTypeId + '~'+ messMenuTypeId + ']');
								if (inputObj) {
									inputObj.val(menuItemNames);
									inputObj.attr('id', foodTypeId + '~'+ messMenuTypeId + '~' + foodMenuItemsId);
								}
							}
						}
					}
				}
			});
	}
}
</script>
