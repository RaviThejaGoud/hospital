<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js">
</script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Assign Students to Hostel
				</div>
			</div>
			<div class="portlet-body">
					<div class="tab-content">
						<s:if test="%{objectList!= null && !objectList.isEmpty()}">
							<s:form id="template" action="#" theme="simple" cssClass="form-horizontal">
								<jsp:include page="/common/messages.jsp"></jsp:include>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-5">
												<span class="required">*</span>Select Hostel & Building :
											</label>
											<div class="col-md-5">
												<s:select list="objectList" listKey="buildingId"
													theme="simple" listValue="hostelNameAndBuildingName"
													cssClass="select2_category form-control required"
													onchange="javascript:getFloorDetailsByBuilding(this.value,'floors');"
													name="tempId2" headerKey="" headerValue="- Select -">
												</s:select>
											</div>
										</div>
									</div>
								<div id="buildingsDiv" class="col-md-6">
									<jsp:include
										page="/WEB-INF/pages/hostel/ajaxGetFloorsByBuilding.jsp" />
								</div>
								</div>
							</s:form>
							<div id="classDiv" style="display: none;">
								<jsp:include
									page="/WEB-INF/pages/hostel/ajaxGetClassesByFloors.jsp" />
							</div>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Hostels are not available. Please create hostels.
							</div>
						</s:else>
					</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Assign Students to Vehicles');   
function getHostelBuildings(selectBox) {
	var organizationId = $("select#hostelName").val();
	 var url = jQuery.url
			.getChatURL("/hostel/ajaxDoGetBuildingsByHostel.do");
	if (organizationId.length == 0) {
		alert("!Oops select Class");
	} else { 
		$("#subjectsDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "anyId=" + organizationId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#buildingsDiv").html(html);
				document.getElementById('buildingsDiv').style.display = "block"; 
		}
		});
	}
}
   function getMarksSubmitErrors(){
	var classSectionId=$("#classSection").val();
	if(isNonEmpty(classSectionId)){
		$('#selectedClassName').val($("select[id='classSection'] option:selected").text());
		return true;
	}
	else{
		alert("Please select class.");
		return false;
	}
  }
  function getFloorDetailsByBuilding(buldingId, anyTitle) {
	if(isNonEmpty(buldingId)){
		$('#buildingsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId2=" + buldingId + "&anyTitle=" + anyTitle;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxDoGetFloorsByBuildings.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#buildingsDiv').html(response);
				document.getElementById('buildingsDiv').style.display = "block"; 
			}
		});
	}else
		$('#buildingsDiv').html('<div class="thb" style="margin-top:10px;">Please select Hostel & Building.</div>');
}

function getRoomsDetailsByFloor(id, type) 
{
	if(isNonEmpty(id)){
		$('#classDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "tempId1=" + id + "&anyTitle=" + type;
		$.ajax( {
			url : jQuery.url.getChatURL("/hostel/ajaxGetClassesByFloorId.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#classDiv').html(response);
					document.getElementById('classDiv').style.display = "block"; 
			}
		});
	}else
		$('#classDiv').html('<div class="thb" style="margin-top:10px;">Please select Building & Floor.</div>');
}
	
function generateClassIds() 
{
 var floorId = $("select#floorId").val();
 	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) 
 	{
 	$('#classNames').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassIds = '';
		var selectedClassNames = '';
		if (classIds.length > 0) {
			selectedClassIds = '(';
			$(classIds).each(function(){
				selectedClassIds += $(this).val() + ', ';
				if(isNonEmpty($(this).parents('label').text()))
				    selectedClassNames +=  $(this).parents('label').text().replace(/^\s+|\s+$/g, ' '); 
			});
			selectedClassIds += '0)';
		}
		$("#classNameIds").val(selectedClassIds);
		$("#classNames").val(selectedClassNames);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one Class");
		return false;
	} else {
		return false;
	}
}
</script>
