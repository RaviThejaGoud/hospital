<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<s:if test="%{buildingList!= null && !buildingList.isEmpty()}">
<div class="grid_5">
<s:select list="objectList" listKey="buildingId" theme="simple"
				listValue="hostelNameAndBuildingName" required="true"
				onchange="javascript:getFloorDetailsByBuilding(this.value,'floors');"
				name="tempId2" headerKey="" headerValue="- Select -" requiredposition="first">
			</s:select>
</div> 
<div class="grid_13"> 
					<div class="grid_5">
					<div id="floorsDiv" style="display: none">
						<jsp:include page="/WEB-INF/pages/hostel/ajaxGetFloorsByBuilding.jsp"/>
					</div>
					</div>
			</div>
</s:if>
<s:else>
<div class="grid_12">
		<p>
				Floors are not created. Please create floors and try to assign students.
			</p>
</div>
</s:else>

<script type="text/javascript">
  function getFloorsByBuilding(selectBox) {
	var organizationId = $("select#buildingId").val();
	 var url = jQuery.url
			.getChatURL("/hostel/ajaxDoGetFloorsByBuildings.do");
	if (organizationId.length == 0) {
		alert("!Oops select Class");
	} else { 
		$("#floorsDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "title=" + organizationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#floorsDiv").html(html);
				document.getElementById('floorsDiv').style.display = "block"; 
		}
		});
	}
}
</script>
