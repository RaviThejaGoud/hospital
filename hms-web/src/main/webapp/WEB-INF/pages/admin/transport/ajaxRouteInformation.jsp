<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
	<div class="grid_10">
		<div id="commonStep13">
			<fieldset>
				<s:if test="%{routeList != null && !routeList.isEmpty()}">
					<div class="grid_10">
						<div class="grid_2">
							Route Name
						</div>
						<div class="grid_2">
							Departure Time
						</div>
						<div class="grid_3">
							Arrival Name
						</div>
						<div class="grid_2">
							Arrival Time
						</div>
						<div class="grid_1">
							Edit
						</div>
					</div>
					<div id="vehicleResultsPage">
						<s:iterator value="routeList">
							<div class="grid_10 row">
								<div class="grid_2">
									<s:property value="routeName" />
								</div>
								<div class="grid_2">
									<s:property value="routePointStartTime" />
								</div>
								<div class="grid_3">
									<s:property value="routeEndName" />
								</div>
								<div class="grid_2">
								<s:property value="routePointEndTime" />
									<!--<s:property value="routePointReturnEndTime" />-->
								</div>
								<div class="grid_1">
								 	<s:if test="%{id == vehicle.routeId}">
									<s:if test="%{balance != 0}">
										<input name="routeId" type="radio"
											value="<s:property value="id" />" checked="checked" disabled="disabled"/>
									</s:if>
										<s:else>
									<input name="routeId" type="radio"
											value="<s:property value="id" />" checked="checked"  />
											</s:else>
									</s:if>
									<s:else>
									<s:if test="%{balance != 0}">
									<input name="routeId" type="radio"
											value="<s:property value="id" />"  disabled="disabled"/>
											</s:if>
											<s:else>
									<input name="routeId" type="radio"
											value="<s:property value="id" />"  />
											</s:else>
									</s:else>
									<s:else>
										<input name="routeId" type="radio"
											value="<s:property value="id" />" />
									</s:else> 
								</div>
							</div>
					</s:iterator>
					</div>
				</s:if>
				<s:else>
					<div class="grid_11 thb">
						Currently there are no routes information.
					</div>
				</s:else>
			</fieldset>
		</div>
	</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/transport/buspaginator_dev.js">
</script>
<script type="text/javascript">
$(function() {
	$("#vehicleResultsPage").buspagination();
});
</script>



