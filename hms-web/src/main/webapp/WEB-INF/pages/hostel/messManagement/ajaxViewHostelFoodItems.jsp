<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{!foodMenuTypeList.isEmpty()}">
	<div class="grid_14">
		<s:url id="doAddNewMenuItems" action="ajaxdoAddNewMenuItems"
			includeParams="all" escapeAmp="false" namespace="/hostel">
			<s:param name="academicYearId" value="%{#session.academicYear}" />
			<s:param name="tempId" value="%{id}" />
		</s:url>
		<sj:a href="%{doAddNewMenuItems}" indicator="indicator"
			targets="stepMess" button="false" cssClass="linkRight">Add/Edit MenuItems</sj:a>
		<h1>
			Current Mess Food Items
		</h1>
		<s:if test="%{weekDayList!= null && !weekDayList.isEmpty()}">
			<s:if
				test="%{messTimeingsList!= null && !messTimeingsList.isEmpty()}">
				<s:if test="%{foodTypeList!= null && !foodTypeList.isEmpty()}">
					<s:if
						test="%{foodMenuTypeList!= null && !foodMenuTypeList.isEmpty()}">
						<div class="grid_14 th">
							<div class="grid_3">
								Food Menu Type
							</div>
							<s:if test="%{foodTypeList != null && !foodTypeList.isEmpty()}">
								<s:iterator value="foodTypeList">
									<s:set name="foodTypeId" value="%{id}"></s:set>
									<div class="grid_5">
										<s:property value="foodTypeName" />
									</div>
								</s:iterator>
							</s:if>
						</div>
						<s:iterator value="weekDayList">
							<s:set name="weekId" value="%{id}"></s:set>
							<div class="grid_2">
								<s:property value="dayName" />
							</div>
							<div class="grid_14 row">
								<s:iterator value="messTimeingsList">
									<s:set name="messTimeId" value="%{id}"></s:set>
									<div class="grid_3">
										<s:property value="messFoodTypeName" />
									</div>
									<s:iterator value="foodTypeList">
										<s:set name="foodTypeId" value="%{id}"></s:set>
										<div class="grid_5">
											<s:iterator value="foodMenuTypeList">
												<s:set name="foodMenuId" value="%{id}"></s:set>
												<div class="grid_2">
													<s:if
														test="%{ #weekId == dayId &&  messMenuTimeId ==  #messTimeId && foodTypeId == #foodTypeId}">
														<s:property value="menuItemNames" />
													</s:if>
												</div>
											</s:iterator>
										</div>
									</s:iterator>
									<br />
								</s:iterator>
							</div>
						</s:iterator>
					</s:if>
				</s:if>
			</s:if>
		</s:if>
		<s:else>
			<div class="grid_14 th thb">
				Currently there are no menu items.
			</div>
		</s:else>
	</div>
</s:if> 