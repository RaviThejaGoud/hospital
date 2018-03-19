<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14">
	<jsp:include page="/common/messages.jsp" />
	<s:form action="ajaxAddWeekMenuItems" theme="css_xhtml" id="viewItems" method="post" namespace="/hostel">
		 <s:hidden name="tempString" cssClass='tempString' />
		 <s:hidden name="tempId1" value="%{anyId}" id="id"></s:hidden>
		 <s:hidden name="type" value="%{'MessFoodItems'}" />
		<h1>
			Create Menu Items
		</h1>
		<fieldset>
			<div class="grid_13">
				<div class="grid_6">
					<div class="grid_6">
						<label style="width: 250px;">
							<span class="required">*</span>Select Hostel & Building Name:
						</label>
					</div>
					<div class="grid_6">
						<s:select list="objectList" listKey="buildingId" listValue="hostelNameAndBuildingName"
							  cssClass="required textfield" theme="css_xhtml" required="true" id="buldingId" name="tempId2"  headerKey="" headerValue="-Select Building Name-"
							requiredposition="first" onchange="javascript:onChangeBulding(this.value)">
						</s:select>
					</div>
				</div>
				<div id="buildingMenuContent"></div>
				<div id="menuTypesContent"></div>
				<div class="grid_13">
					<div class="grid_13">
						&nbsp;
					</div>
					<s:url id="doAddMenuList" action="ajaxLoadMessInfoByStatus"
						includeParams="all" escapeAmp="false" namespace="/hostel">
					</s:url>
					<sj:a href="%{doAddMenuList}" cssClass="cancelButton"
						indicator="indicator" targets="viewMessSteps">Cancel</sj:a>
						
						
					<sj:submit   cssClass="submit small" value="Submit" validate="true"
						onClickTopics="addMenuValidation" indicator="indicator"
						targets="viewMessSteps" />
				</div>
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/json2.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	//It is for getting menu list on page load if menuId and buildingId are available. 
		var buldingId = $('#buldingId').val();
		if (isNonEmpty(buldingId)) {
			onChangeBulding(buldingId);
		}
		//It prepares munuItems   when user submits form.
		$.subscribe('addMenuValidation', function(event, data) {
		   if ($('#viewItems').valid()) {
					var fieldErrorString = '';
					var foodTypeId = '';
					var messMenuTypeId = '';
					var menuItemNames = '';
					var jsonObj = [];
					var objIds;
					var allids;
					$('input[name="menuItemNames"]').each(function() {
						menuItemNames = $(this).val();
						if (!isNonEmpty(menuItemNames)) {
							menuItemNames = " ";
						}
							objIds = $(this).attr('id');
							if (isNonEmpty(objIds)) {
								allids = objIds.split('~');
								if (allids.length >= 2) {
									jsonObj.push( {
										"foodTypeId" : allids[0],
										"messMenuTypeId" : allids[1],
										"menuItemNames" : menuItemNames
									});
								} else {
									jsonObj.push( {
										"foodTypeId" : "0",
										"messMenuTypeId" : allids[1],
										"menuItemNames" : menuItemNames
									});
								}
							}
						 
						//alert("foodTypeid :"+allids[0]+", messMenuTypeId : "+ allids[1]+" menuItemNames : "+menuItemNames);
					});
					$('.tempString').val(JSON.stringify(jsonObj));
					var response = $('.tempString').val();
					if (response == '[]') {
						alert("Please Fill the menu items.");
						return false;
					} else
						return true; 
			}
		});
	});
function onChangeBulding(buldingId) {
	$('#buildingMenuContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "buldingId=" + buldingId;
	$.ajax( {
		url : jQuery.url.getChatURL("/hostel/ajaxGetBuldingMenus.do"),
		cache : true,
		data : pars,
		success : function(response) {
			$('#buildingMenuContent').html(response);
		}
	});
}
</script>
