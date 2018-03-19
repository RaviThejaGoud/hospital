<%@ include file="/common/taglibs.jsp"%>
<div class="modal fade modal-overflow in" data-width="760"
	id="responsiveReminder"
	style="display: block; width: 662px; height: 425px; top: 300px;"
	aria-hidden="true">
	<div class="modal-header">
	<img src="../images\common\reminderclock.jpg" alt="reminder" style="margin-top: 0;" width="8%;" height="8%"/>
		<button type="button" class="close Reminder" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="bold pagetitle" style="margin-left:100px; margin-top:-39px" >
		<s:if test="%{(reminderDetails != null && !reminderDetails.isEmpty()) && (taskReminderDetails != null && !taskReminderDetails.isEmpty())}">
			Reminder and Task Reminder
		</s:if>
		<s:if test="%{(reminderDetails != null && !reminderDetails.isEmpty()) && (taskReminderDetails == null || taskReminderDetails.isEmpty())}">
			Reminder
		</s:if>
		<s:if test="%{(reminderDetails == null || reminderDetails.isEmpty()) && (taskReminderDetails != null && !taskReminderDetails.isEmpty())}">
			Task Reminder
		</s:if>
		</h4>
	</div>
	<div class="form-body">
		<s:if test="%{reminderDetails != null && !reminderDetails.isEmpty()}">
		<label style="margin-left:294px; margin-top:-39px; font-weight: bold;">Reminder</label>
			<table border="1" style="border-color:gray ;">
				<thead>
					<tr>
						<th  style="width:20%; text-align: center; border-color: gray;">Name</th>
						<th  style="width:14%; text-align: center; border-color: gray;">Expiration Date</th>
						<th  style="width:65%; text-align: center; border-color: gray;">Description</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="reminderDetails">
						<tr>
							<td style="border-color: gray;"><s:property value="name"/></td>
							<td style="border-color: gray;"><s:property value="expirationDateStr"/></td>
							<td style="border-color: gray;"><s:property value="description"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:if test="%{taskReminderDetails != null && !taskReminderDetails.isEmpty()}">
		<br><label style="margin-left:294px; margin-top:-39px; font-weight: bold;" >Task Reminder</label>
			<table border="1" style="border-color:gray ;">
				<thead>
					<tr>
						<th  style="width:20%; text-align: center; border-color: gray;">Name</th>
						<th  style="width:15%; text-align: center; border-color: gray;">Completion Date</th>
						<th  style="width:63%; text-align: center; border-color: gray;">Description</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="taskReminderDetails">
						<tr>
							<td style="border-color: gray;"><s:property value="taskName"/></td>
							<td style="border-color: gray;"><s:property value="taskDateStr"/></td>
							<td style="border-color: gray;"><s:property value="description"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
	</div>
</div>

<script language="JavaScript" type="text/javascript">
$(document).ready(function(){
	// TableAdvanced.init();
	 $('div.modal-header').click();
	 if($("div#responsiveReminder:hidden")){
	  $('a#showReminder').removeAttr('href');
	 }
});
$('button.reminder').click(function(){
	   $('a#showReminder').removeAttr('data-toggle');
	   $('a#showReminder').removeAttr('href');
});
</script>


