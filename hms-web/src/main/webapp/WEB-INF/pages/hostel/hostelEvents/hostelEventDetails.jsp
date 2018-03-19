<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper container_12" style="width: 515px;">
	<div class="wrapper" style="width: 515px;">
		<div id="steps13" style="width: 515px;">
			<s:form action="#" theme="css_xhtml">
				<!--this is used for bacakground color -->
					<s:if test="{hostelEvents != null}">
						<div class="grid_3">
							<h3>Event Details</h3>
						</div>
						<div class="grid_6 alpha">
							&nbsp;
						</div>
						<div class="grid_6">
							<div class="grid_2">
								<b class="labelRight"> Event Name: </b>
							</div>
							<div class="grid_4">
								<s:property value="hostelEvents.eventName" />
							</div>
						</div>
						<div class="grid_6 alpha">
							&nbsp;
						</div>
						<div class="grid_6">
							<div class="grid_2">
								<b class="labelRight"> Description: </b>
							</div>
							<div class="grid_4">
								<s:property value="hostelEvents.eventDescription" />
							</div>
						</div>
						<div class="grid_6 alpha">
							&nbsp;
						</div>
						<div class="grid_6">
							<div class="grid_2">
								<b class="labelRight"> Start Date: </b>
							</div>
							<div class="grid_4">
								<s:property value="hostelEvents.eventStartDateStr" />
							</div>
						</div>
						<div class="grid_6 alpha">
							&nbsp;
						</div>
						<div class="grid_6">
							<div class="grid_2">
								<b class="labelRight"> End Date: </b>
							</div>
							<div class="grid_4">
								<s:property value="hostelEvents.eventEndDateStr" />
							</div>
						</div>
						<div class="grid_6 alpha">
							&nbsp;
						</div>
					</s:if>
					<s:else>
						<div class="grid_6 omega">
							U don't have hostelEvents
						</div>
					</s:else>
			</s:form>
		</div>
	</div>
</div>
