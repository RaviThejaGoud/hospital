<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14 commomnTabs">
	<s:form action="ajaxCreateMessTimeings" theme="css_xhtml" id="addMessTimings" namespace="/hostel">
		<s:hidden name="tempString" cssClass='tempString' />
		<s:hidden name="academicYearId" value="%{academicYearId}" cssClass='academicYearId' />
		<s:hidden name="type" value="%{'MessTimings'}" />
		<fieldset style="padding-top: 0px;">
			<div class="grid_13">
				<div class="grid_8">
						<s:if test="%{objectList!=null && !objectList.isEmpty()}">
						<div class="grid_6">
							<div class="grid_6">
								<label style="width: 250px;">
									<span class="required">*</span>Select Hostel & Building Name:
								</label>
							</div>
							<div class="grid_6">
								<s:select list="objectList" listKey="buildingId"
									listValue="hostelNameAndBuildingName"
									cssClass="required textfield" theme="css_xhtml" required="true"
									id="buldingId" name="tempId2" requiredposition="first"
									onchange="javascript:onChangeBuldingName(this.value)">
								</s:select>
							</div>
						</div>
					</s:if>
						<s:else>
								There are no Buildings available. Please add <a href=#>Buildings</a>
						</s:else>
					</div>
				</div>
				<div id="hostelFoodTimesContent"></div>
				<div class="grid_13 border">
				</div>
				<div class="grid_13">
					<s:url id="doAddTimeList" action="ajaxLoadMessInfoByStatus"
						includeParams="all" escapeAmp="false" namespace="/hostel">
					</s:url>
					<sj:a href="%{doAddTimeList}" cssClass="cancelButton"
						indicator="indicator" targets="viewMessSteps">Cancel</sj:a>

					<sj:submit   targets="viewMessSteps" value="Submit"
						cssClass="submit small" formIds="addMessTimings"
						onClickTopics="addMessTimeingsFormValidation"
						indicator="indicator" />
				</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
changePageTitle('Add Hostel Food Times');
$(document).ready(function() {
	 var buldingId = $('#buldingId').val();
		if (isNonEmpty(buldingId)) {
			onChangeBuldingName(buldingId);
		}
	$.subscribe('addMessTimeingsFormValidation', function(event, data) {
		var messFoodTypeId = '';
		var startTime = '';
		var endTime = '';
		var messFoodTypeName = '';
		var jsonObj = [];
		var unSelectedSchIds = '(';
		//var foodtypesCount=$("span.foodTypesCount").attr('id');
			$('span.messTimingsData').each(function() {
				/*for(var i=0;i<foodtypesCount;i++){
				alert("#foodType"+i);
					messFoodTypeName=$(this).find("#foodType"+i).val();
				}*/
				messFoodTypeId = $(this).find("span.messTimes").attr('id');
				messFoodTypeName = $(this).find('.messFoodType').val();
				startTime = $(this).find('.startTime').val();
				endTime = $(this).find('.endTime').val();
				if (isNonEmpty(messFoodTypeName)) {
					jsonObj.push( {
						"messFoodTypeId" : messFoodTypeId,
						"messFoodTypeName" : messFoodTypeName,
						"startTime" : startTime,
						"endTime" : endTime
					});
				}
			});
			unSelectedSchIds += '0)';
			$('.tempString').val(JSON.stringify(jsonObj));
			$('.hostelFoodIds').val(unSelectedSchIds);
			var response = $('.tempString').val();
			if (response == '[]') {
				alert("Please create atlest one food type.");
				return false;
			} else
				return true;
		});
});


function onChangeBuldingName(buldingId) {
	$('#hostelFoodTimesContent')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "tempId2=" + buldingId;
	$.ajax( {
		url : jQuery.url.getChatURL("/hostel/ajaxGetBuldingFoodTimeings.do"),
		cache : true,
		data : pars,
		success : function(response) {
			$('#hostelFoodTimesContent').html(response);
		}
	});
}
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/json2.js">
</script>
