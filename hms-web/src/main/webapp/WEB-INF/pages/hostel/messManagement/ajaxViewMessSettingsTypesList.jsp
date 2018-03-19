<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{anyTitle == 'MessFoodTypes'}">
		<div class="grid_8">
			<s:if test="%{objectList!=null && !objectList.isEmpty()}">
				<div class="grid_6">
					<div class="grid_6">
						<label>
							<span class="required">*</span>Select Hostel & Building:
						</label>
					</div>
					<div class="grid_6">
						<s:select list="objectList" listKey="buildingId"
							listValue="hostelNameAndBuildingName"
							cssClass="required textfield" theme="css_xhtml" required="true"
							id="buildingId" name="tempId2" requiredposition="first"
							onchange="javascript:onChangeBuldingName(this.value,'MessFoodTypes')">
						</s:select>
					</div>
				</div>
			</s:if>
			<s:else>
			There are no Buildings available. Please add <a href=#>Buildings</a>
			</s:else>
			<div class="grid_6">
				&nbsp;
			</div>
			<div id="hostelFoodTimesContent"></div>
		</div>
		<div class="grid_14">
			<s:if test='%{foodTypeList.size()<3}'>
				<s:url id="doAddFoodTypes" action="ajaxDoAddFoodTypes"
					includeParams="all" escapeAmp="false" namespace="/hostel">
					<s:param name="academicYearId" value="%{#session.academicYear}" />
					<s:param name="tempId1" value="0" />
					<s:param name="buldingId" value="%{tempId2}" />
				</s:url>
				<sj:a href="%{doAddFoodTypes}" indicator="indicator"
					targets="viewMessSteps" button="false" cssClass="linkRight">Add Food Types</sj:a>
			</s:if>
			<h1>
				Current Food Types Details
			</h1>
			<s:if test="%{foodTypeList!= null && !foodTypeList.isEmpty()}">
			<div class="grid_14 th">
				<div class="grid_12">
					<div class="grid_3 foodNameDiv sortHeader divArrow">
						FoodType Name
					</div>
				</div>
				<div class="grid_1">
					Edit
				</div>
				<div class="grid_1">
					Delete
				</div>
			</div>
			<div id="foodItemDiv">
				<s:iterator value="foodTypeList">
				<div foodTypeName="<s:property value='foodTypeName' />" class="item">
					<div class="grid_14 row">
						<div class="grid_12">
							<s:property value="foodTypeName" />
						</div>
						<div class="grid_1">
							<s:url id="doViewFoodTypeList" action="ajaxDoEditFoodTypes"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="tempId1" value="%{id}" />
								<s:param name="tempId2" value="%{building.id}" />
							</s:url>
							<sj:a href="%{doViewFoodTypeList}" targets="doViewFoodTypeList%{id}"
								onCompleteTopics="doViewFoodTypeList"
								onBeforeTopics="cleanOpenDivs" indicator="indicator"
								button="false" buttonIcon="ui-icon-pencil"
								cssClass="normalEdit" title="Edit">
							</sj:a>
							&nbsp;
						</div>
						<div class="grid_1" align="center">
							<s:url id="removeFoodTypes" action="ajaxDeleteFoodTypes"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="tempId" value="id"></s:param>
								<s:param name="type" value="%{'MessFoodTypes'}" />
								<s:param name="building.id" value="%{buildingId}"></s:param>
							</s:url>
							<s:div cssClass="close emsRemove" id='%{removeFoodTypes}'
								theme="simple" title="Delete this Building"></s:div>
						</div>
						<div id="doViewFoodTypeList<s:property value='id' />"
									style="display: none;" class='load'></div>
					</div>
					</div>
				</s:iterator>
			</div>
			<div class="grid_14">
					<div class="grid_1">
						<span class="noteMassage"> Note:</span>
					</div>
					<div class="grid_13">
						<s:if test='%{foodTypeList.size()<3}'>
							You are created <s:property value="foodTypeList.size"/> food types.
						 </s:if>
						 <s:else>
							 You can't add more than 3 items.
						 </s:else>
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="grid_14 th thb">
					You have not created food types, Creating food types is simple
					process and system would guide you.
				</div>
			</s:else>
		</div>
	</s:if>
	<s:elseif test="%{anyTitle == 'MessTimings'}">
		<div class="grid_8">
			<s:if test="%{objectList!=null && !objectList.isEmpty()}">
				<div class="grid_6">
					<div class="grid_6">
						<label>
							<span class="required">*</span>Select Hostel & Building:
						</label>
					</div>
					<div class="grid_6">
						<s:select list="objectList" listKey="buildingId"
							listValue="hostelNameAndBuildingName"
							cssClass="required textfield" theme="css_xhtml" required="true"
							id="buildingId" name="tempId2" requiredposition="first"
							onchange="javascript:onChangeBuldingName(this.value,'MessTimings')">
						</s:select>
					</div>
				</div>
			</s:if>
			<s:else>
			There are no Buildings available. Please add <a href=#>Buildings</a>
			</s:else>
			<div class="grid_6">
				&nbsp;
			</div>
			<div id="hostelFoodTimesContent"></div>
		</div>

		<div class="grid_14">
			<s:url id="doAddMessTimings" action="ajaxDoAddMessTimings"
				includeParams="all" escapeAmp="false" namespace="/hostel">
				<s:param name="academicYearId" value="%{#session.academicYear}" />
				<s:param name="tempId" value="%{id}" />
			</s:url>
			<sj:a href="%{doAddMessTimings}" indicator="indicator"
				targets="viewMessSteps" button="false" cssClass="linkRight">Add/Edit Mess Timings</sj:a>

			<h1>
				Current Mess Timing Details
			</h1>
			<s:if
				test="%{messTimeingsList!= null && !messTimeingsList.isEmpty()}">
				<div class="grid_14" align="right" data-target="viewMessTimeContent">
					<jsp:include
						page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
				</div>
				<div class="grid_14 th">
					<div class="grid_4">
						<div class="grid_3 messNameDiv sortHeader divArrow">
								Mess Food Menu
						</div>
					</div>
					<div class="grid_4">
						Start Time
					</div>
					<div class="grid_4">
						End Time
					</div>
					<div class="grid_1">
						Delete
					</div>
				</div>
				<div id="viewMessTimeContent">
				<s:iterator value="messTimeingsList">
				<div messFoodTypeName="<s:property value='messFoodTypeName' />" class="item">
					<div class="grid_14 row">
						<div class="grid_4">
							<s:property value="messFoodTypeName" />
						</div>
						<div class="grid_4">
							<s:property value="startTime" />&nbsp;
						</div>
						<div class="grid_4">
							<s:property value="endTime" />&nbsp;
						</div>
						<div class="grid_1" align="center">
							<s:url id="removeMessTimeings" action="ajaxDeleteFoodTimeings"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="tempId" value="id"></s:param>
								<s:param name="type" value="%{'MessTimings'}" />
								<s:param name="building.id" value="%{buildingId}"></s:param>
								
							</s:url>
							<s:div cssClass="close emsRemove" id='%{removeMessTimeings}'
								theme="simple" title="Delete this Food Timeings"></s:div>
						</div>
					</div>
					</div>
				</s:iterator>
				</div>
			</s:if>
			<s:else>
				<div class="grid_14 th thb">
					You have not created food timings, Creating food timings is simple
					process and system would guide you.
				</div>
			</s:else>
		</div>
	</s:elseif>
	<s:elseif test="%{anyTitle == 'MessFoodItems'}">
		<div class="grid_8">
			<s:if test="%{objectList!=null && !objectList.isEmpty()}">
				<div class="grid_6">
					<div class="grid_6">
						<label>
							<span class="required">*</span>Select Hostel & Building:
						</label>
					</div>
					<div class="grid_6">
						<s:select list="objectList" listKey="buildingId"
							listValue="hostelNameAndBuildingName"
							cssClass="required textfield" theme="css_xhtml" required="true"
							id="buildingId" name="tempId2" requiredposition="first"
							onchange="javascript:onChangeBuldingName(this.value,'MessFoodItems')">
						</s:select>
					</div>
					<!--<span class="buildingId" id="<s:property value='buildingId'/>"></span> -->
				</div>
			</s:if>
			<s:else>
			There are no Buildings available. Please add <a href=#>Buildings</a>
			</s:else>
			<div class="grid_6">
				&nbsp;
			</div>
		</div>
		<div class="grid_14">
		<span class="buildingId" id="<s:property value='selectedId'/>"></span>
			<s:url id="doAddNewMenuItems" action="ajaxdoAddNewMenuItems"
				includeParams="all" escapeAmp="false" namespace="/hostel">
				<s:param name="academicYearId" value="%{#session.academicYear}" />
				<s:param name="tempId" value="%{id}" />
			</s:url>
			<sj:a href="%{doAddNewMenuItems}" indicator="indicator"
				targets="viewMessSteps" button="false" cssClass="linkRight">Add/Edit MenuItems </sj:a>
			<h1>
				Current Mess Menu Items
			</h1>
			<s:if test="%{weekDayList!= null && !weekDayList.isEmpty()}">
				<s:if
					test="%{messTimeingsList!= null && !messTimeingsList.isEmpty()}">
					<s:if test="%{foodTypeList!= null && !foodTypeList.isEmpty()}">
						<s:if
							test="%{foodMenuTypeList!= null && !foodMenuTypeList.isEmpty()}">
							<div class="grid_14 th">
								<div class="grid_2">
									<b>Food Menu</b>
								</div>
								<s:if test="%{foodTypeList != null && !foodTypeList.isEmpty()}">
									<s:iterator value="foodTypeList">
									<s:set name="foodTypeId" value="%{id}"></s:set>
										<div class="grid_4">
											<b><s:property value="foodTypeName" />
											</b>
										</div>
									</s:iterator>
								</s:if>
							</div>
							<s:iterator value="weekDayList">
									<s:set name="weekId" value="%{id}"></s:set>
									<div class="grid_14" align="center"
										style="background: Gray;width: 837px">
										<b><font color="greay";><s:property value="dayName" />
										</font> </b>
									</div>
										<s:iterator value="messTimeingsList">
										<div class="grid_14 row">
											<s:set name="messTimeId" value="%{id}"></s:set>
											<div class="grid_2">
												<font color="#D64646;"><s:property value="messFoodTypeName" /></font>
											</div>
											<s:iterator value="foodTypeList">
												<s:set name="foodTypeId" value="%{id}"></s:set>
												<div class="grid_4" id="day<s:property value='#weekId'/>_messTime<s:property value='#messTimeId'/>_foodtype<s:property value='#foodTypeId'/>">
													-
												</div>
											</s:iterator>
										</div>
										</s:iterator>
							</s:iterator>
						</s:if>
						<s:else>
							<div class="grid_14 th thb">
								You have not created food Menu Types, Creating food Menu Types
								is simple process and system would guide you.
							</div>
						</s:else>
					</s:if>
					<s:else>
						<div class="grid_14 th thb">
							You have not created food Types, Creating food Type is simple
							process and system would guide you.
						</div>
					</s:else>
				</s:if>
				<s:else>
					<div class="grid_14 th thb">
						You have not created menu items, Creating menu items is simple
						process and system would guide you.
					</div>
				</s:else>
			</s:if>
		</div>
	</s:elseif>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
    $('#viewMessTimeContent').pagination();
	$('#viewMenuItemsContent').pagination();
	$("#foodItemDiv").pagination();
		var buildingId=$('span.buildingId').attr('id');
		if (isNonEmpty(buildingId)) {
  		var messURL = jQuery.url.getChatURL("/hostel/ajaxGetViewMessMenuItems.do?tempId1="+buildingId);
			$.ajax({
			url : messURL,
			cache : false,
			dataType : 'json',
			success : function(response) {
				var foodMenuTypeList=response.foodMenuTypeList;
				var foodMenuItemsId = '';
				var foodTypeId = '';
				var messMenuTimeId = '';
				var dayId = '';
				if(foodMenuTypeList){
					for ( var i = 0; i < foodMenuTypeList.length; i++) {
						foodMenuItemsId = foodMenuTypeList[i].foodMenuItemsId;
						foodTypeId = foodMenuTypeList[i].foodTypeId;
						messMenuTimeId = foodMenuTypeList[i].messMenuTimeId;
						dayId=foodMenuTypeList[i].dayId;
							$('#day'+dayId+"_messTime"+messMenuTimeId+"_foodtype"+foodTypeId).html(foodMenuTypeList[i].menuItemNames);
					}
				}
			}
		 });
		}
  });
  
  function onChangeBuldingName(buldingId, type) {
	$('#viewMessSteps') .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "tempId2=" + buldingId + "&type=" + type;
	$.ajax( {
		url : jQuery.url.getChatURL("/hostel/ajaxGetBuldingFoodItems.do"),
		cache : true,
		data : pars,
		success : function(response) {
			$('#viewMessSteps').html(response);
		}
	});
}
  $.subscribe('doViewFoodTypeList', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
var name=1;
$('div.foodNameDiv').click(function () {
     $('div#foodItemDiv>div.item').sortElements(function (a, b) {return ($(a).attr('foodTypeName').toLowerCase() > $(b).attr('foodTypeName').toLowerCase() ? 1 : -1) * name;});
    updateDirectionArrows($(this), name);
    $("#foodItemDiv").pagination();
    name= name* -1;
    return false;
});

var mess=1;
$('div.messNameDiv').click(function () {
     $('div#viewMessTimeContent>div.item').sortElements(function (a, b) {return ($(a).attr('messFoodTypeName').toLowerCase() > $(b).attr('messFoodTypeName').toLowerCase() ? 1 : -1) * mess;});
    updateDirectionArrows($(this), mess);
    $("#viewMessTimeContent").pagination();
    mess= mess* -1;
    return false;
});
 
 </script>