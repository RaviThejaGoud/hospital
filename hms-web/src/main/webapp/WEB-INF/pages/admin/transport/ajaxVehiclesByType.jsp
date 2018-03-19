<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div>
<s:if test="%{vehicleList != null && !vehicleList.isEmpty()}">
		<table class="striped" width="100%" cellpadding="1" cellspacing="1" style="margin-bottom:0;" id="results">
			<thead>
				<tr>
					<th width="170px">
						Vehicle Number 
					</th>
					<s:if test="%{selectedVehicleType=='Car'}">
						<th width="390px" style="display: none;">
							Seating Capacity 
						</th>
					</s:if>
					<s:else>
						<th width="170px">
							Seating Capacity 
						</th>
					</s:else>
						<th width="170px">
							
						</th>
					<!--
					<th width="170px">
						Edit
					</th>
				--></tr>
			</thead>
			</table>
			<div id="vehicleResultsPage">
			<s:iterator value="vehicleList">
			<table class="striped" width="100%" cellpadding="1" cellspacing="1" style="margin-bottom:0;" id="results">
				<tr class="loaded">
					<td width="170px">
						<!--<s:url id="removeVehicle" action="ajaxRemoveVehicle"
							escapeAmp="false">
							<s:param name="id" value="id"></s:param>
						</s:url>
						<s:div cssClass="close emsRemove" id='%{removeVehicle}'
							theme="simple" title="Delete this group"></s:div>
						-->
					<s:property value="vehicleNumber" />
					</td>
					<s:if test="%{vehicleType=='Car'}">
						<td width="170px" style="display: none;">
							<s:property value="noOfSeats" />
						</td>
					</s:if>
					<s:else>
						<td width="170px">
							<s:property value="noOfSeats" />
						</td>
					</s:else>
					<td width="170px"><s:property value="vhicleId"/>
						<s:if test="%{vehicle.id == id}">
							<input name="vehicleId" type="radio" value="<s:property value="id" />" checked="checked"/>
						</s:if>
						<s:else>
							<input name="vehicleId" type="radio" value="<s:property value="id" />" />
						</s:else>
					</td>
					<!--<td width="170px">
						<s:url id="doEditvehicle" action="ajaxDoEditVehicle"
							includeParams="all" escapeAmp="false">
							<s:param name="id" value="{id}" />
						</s:url>
						<sj:a href="%{doEditvehicle}" onCompleteTopics="doInitEditVehicle"
							indicator="indicator" targets="editVehicle%{id}"
							onBeforeTopics="cleanOpenRows">
							<a href="#" title="Edit" class="normalEdit"></a>
						</sj:a>
					</td>
				--></tr>
				<!--<tr id="editVehicle<s:property value='id' />" style="display: none;" class="load">
				</tr>
				--></table>
			</s:iterator>
			</div>
		</s:if>
		<s:else>Currently there are no vehicles.</s:else>
	</div>
<style type="text/css">
.active
{
color:#0033CC;
text-decoration:none;
}
.inactive
{
font-weight: bold;
text-decoration: underline; 
cursor:default;
}
.buspaginator
{
text-align: right;
color: #FFF;
}
</style>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/transport/buspaginator_dev.js"></script>
	<script type="text/javascript">
	$(function(){ $("#vehicleResultsPage").buspagination(); });
</script>