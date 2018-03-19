<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<s:if test="%{tempList!=null && !tempList.isEmpty() }">
	<span id='<s:property value="anyId"/>' class="routeId"></span>
	<div class="form-group">
		<label class="control-label col-md-2">
			Select Vehicle :
		</label>
		<div class="col-md-2">
			<s:select id="vehicleId" list="tempList" listKey="vehicleAcademicId"
				tabindex="21" cssClass="form-control input-medium" listValue="name" headerKey=""
				headerValue="- Select -" theme="simple"
				onchange="javascript:getAjaxGetVehicleNumbers(this.value);" />
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="form-group" id="transportId" style="display:none;">
				<label class="control-label col-md-2">
					Select Point :
				</label>
				<div class="col-md-2">
						<s:select name="transportStudentType" id="tansportStudentTypeId"
							cssClass="form-control input-medium required"
							list="#{'':' - Select -','B':'Both','P':'Pick Up','D':'Drop Point'}"
							onchange="javascript:getAjaxGetTransportStudentListByType(this.value);">
						</s:select>
				</div>
			</div>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Oops! No vehicle in the system. Did you
		already assigned and expected students? Please contact admin or call
		to eazyschool support at (91) 80-46620999  for
		quick help .
	</div>
</s:else>
<div id="vehicleNumbers"></div>
<script type="text/javascript">
   	changePageTitle('Manage Vehicles');
	function getAjaxGetVehicleNumbers(vehicleType) {
		if (vehicleType == "") {
			$("#transportId").hide();
			$("#vehicleNumbers").hide();
		}else{
			$("#transportId").show();
			$("#tansportStudentTypeId").val('');
			$("#vehicleNumbers").hide();
	  }
	}
	function getAjaxGetTransportStudentListByType(transportType){
	var pars="";
	var routeId=$('.routeId').attr('id');
	var vehicleType=$('#vehicleId').val();
	if(transportType == ""){
		$("#vehicleNumbers").hide();
	}else{
			if(isNonEmpty(routeId) && isNonEmpty(vehicleType) && isNonEmpty(transportType)){
				pars = "routeId="+routeId + "&vehicleId="+vehicleType+"&transportId="+transportType+"&tempString="+'<s:property value="tempString"/>';
			}else if(isNonEmpty(routeId) && !isNonEmpty(vehicleType) && isNonEmpty(transportType)){
				 pars = "routeId="+routeId +"&transportId="+transportType+"&tempString="+'<s:property value="tempString"/>';
			}else if(!isNonEmpty(routeId) && isNonEmpty(vehicleType) && isNonEmpty(transportType)){
				 pars = "vehicleId="+vehicleType +"&transportId="+transportType+"&tempString="+'<s:property value="tempString"/>';
			}
			$("div#vehicleNumbers")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/admin/ajaxGetAssignedStundentsToVehicle.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("div#vehicleNumbers").html(html);
					$("div#vehicleNumbers").show();
				}
			});
	  }
	}
</script>
