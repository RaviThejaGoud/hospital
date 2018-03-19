<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>

<div class="grid_11">
			<s:if
				test="%{calendarEventsList != null && !calendarEventsList.isEmpty()}">
				<table class="striped">
					<thead>
						<tr>
							<th style="width: 150px">
								Title
							</th>
							<th style="width: 125px">
								Description
							</th>
							<th style="width: 125px">
								StartDate
							</th>
							<th style="width: 125px">
								EndDate
							</th>
							<th style="width: 100px">
								Place
							</th>
							<th style="width: 30px">
								Register
							</th>
						</tr>
					</thead>
				</table>
				<div id="resultsPage">
					<s:iterator value="calendarEventsList">
						<table class="striped" width="100%" style="margin-bottom: 0;"
							cellpadding="1" cellspacing="1">
							<tr class='loaded'>
								<td style="width: 150px">
									<s:property value="title" />
								</td>
								<td style="width: 125px">
									<s:property value="message" />
								</td>
								<td style="width: 125px">
									<s:property value="eventStartDateStr" />
								</td>
								<td style="width: 125px">
									<s:property value="eventEndDateAndTimeStr" />
								</td>
								<td style="width: 100px">
									<s:property value="place" />
								</td>
								<td style="width: 30px">
									<s:url id="doRegisterStudentEvent"
										action="ajaxDoRegisterStudentEvent" includeParams="all"
										escapeAmp="false" namespace="/student">
										<s:param name="id" value="{id}" />
									</s:url>
									<sj:a href="%{doRegisterStudentEvent}"
										onBeforeTopics="cleanOpenRows"
										onCompleteTopics="doRegisterEvent" indicator="indicator"
										targets="registerEvent%{id}">
										register
									</sj:a>
								</td>
							</tr>
							<tr id="registerEvent<s:property value='id' />"
								style="display: none;" class='load'>

							</tr>
						</table>
					</s:iterator>
				</div>

			</s:if>
			<s:else>
  Currently there are no events.
 </s:else>
		</div>
		
		