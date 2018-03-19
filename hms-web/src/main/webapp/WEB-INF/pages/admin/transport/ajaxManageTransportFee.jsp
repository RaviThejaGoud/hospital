<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>
<div>
	<div id="addTransportFee<s:property value='id' />" style="display: none;"
		style="margin: 0px 0px 0px 3px;display: none;">
	</div>
</div>

<div id="moreTransportFee">
<s:if test="%{transportFeeList != null && !transportFeeList.isEmpty()}">
<div id="commonStep13" >
	<fieldset>
	<div class="grid_14">
			<div id="results" class="grid_13 th">
				<div class="grid_2">
					ClassName
				</div>
				<div class="grid_3">
					Transport Fee
				</div>
				<div class="grid_3">
					Payment Due Date
				</div>
				<div class="grid_1">
					Edit
				</div>
			</div>
			<s:iterator value="transportFeeList">
			<div class="grid_13 row">
						<div class="grid_2">
							<s:url id="removeVehicle" action="ajaxRemoveVehicle"
								escapeAmp="false" namespace="/admin">
								<s:param name="id" value="id"></s:param>
							</s:url>
							<s:div cssClass="close emsRemove" id='%{removeVehicle}'
								theme="simple" title="Delete this group"></s:div>
							<s:property value="className.className" />
						</div>
						<div class="grid_3">
							<s:property value="transportFee" />
						</div>
						<div class="grid_3">
							<s:property value="transportFeeDueDateStr" />
						</div>
						<div class="grid_1">
							<s:url id="doEditTripFee" action="ajaxDoEditTripFee"
								includeParams="all" escapeAmp="false">
								<s:param name="id" value="%{id}" />
							</s:url>
							<sj:a href="%{doEditTripFee}" onCompleteTopics="doInitEditTripFee"
								indicator="indicator" targets="editTripFee%{id}"
								onBeforeTopics="cleanOpenRows"  cssClass="normalEdit" title="Edit">
							</sj:a>
						</div>
						<div id="editTripFee<s:property value='id' />"
							style="display: none;" class='load'></div>
						
			</div>
			</s:iterator>
		</div>
	</fieldset>
</div><%--







						<table class="striped">
							<thead>
								<tr>
									<th>
										ClassName
									</th>
									<th>
										Transport Fee
									</th>
									<th>
										Payment Due Date
									</th>
									<th>
										Edit
									</th>
								</tr>
							</thead>
							
							<s:iterator value="transportFeeList" status="status">
								<tr class='loaded' >
									<td width="16%">
										<!--<s:url id="removeTripFee" action="ajaxRemoveTripFee"
											escapeAmp="false">
											<s:param name="id" value="%{id}"></s:param>
										</s:url>
										<s:div cssClass="close emsRemove" id='%{removeTripFee}'
											theme="simple" title="Delete this group"></s:div>-->
										<s:property value="className.className" />
									</td>
									<td style="width: 100px">
										<s:property value="transportFee" />
									</td>
									<td style="width: 125px">
										<s:property value="transportFeeDueDateStr" />
									</td>
									<td width="10%">
									<s:url id="doEditTripFee" action="ajaxDoEditTripFee"
										includeParams="all" escapeAmp="false">
										<s:param name="id" value="%{id}" />
									</s:url>
									<sj:a href="%{doEditTripFee}" onCompleteTopics="doInitEditTripFee"
										indicator="indicator" targets="editTripFee%{id}"
										onBeforeTopics="cleanOpenRows"  cssClass="normalEdit" title="Edit">
									</sj:a>
								</td>
							</tr>
							<tr id="editTripFee<s:property value='id' />" style="display: none;" class="load">
							</tr>
						</s:iterator>
						</table>
					--%></s:if>
					<s:else>
  There are no fee details.
</s:else>
	</div>
<script type="text/javascript">
	$.subscribe('doInitEditTripFee', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

	$.subscribe('removeTripFee', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

	$.subscribe('addTransportFee', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

</script>


