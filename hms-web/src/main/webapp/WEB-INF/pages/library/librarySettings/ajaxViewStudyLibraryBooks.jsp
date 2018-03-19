<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width">
		<thead>
			<tr>
				<th>
					Max Books Allowed/Student
				</th>
				<th>
					Max Books Allowed/Staff 
				</th>
				<th>
					Fine Per Day
				</th>
				<th>
					Reservation Valid / Days
				</th>
				<th>
					No of Due Days For Return/Student
				</th>
				<th>
					No of Due Days For Return/Staff
				</th>
				<th>
					Edit
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="noOfStudentIssuBooks" />
					</td>
					<td>
						<s:property value="noOfStaffIssuBooks" />
					</td>
					<td>
						<s:property value="fineAmountPerDay" />
					</td>
					<td>
						<s:property value="limitDays" />
					</td>
					<td>
						<s:property value="noOfDaysForReuturnStudents" />
					</td>
					<td>
						<s:property value="noOfDaysForReuturnStaff" />
					</td>
					<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<a data-toggle="modal" href="#popupEditLibrarySettings"	class="btn btn-xs purple"
								onclick="javascript: popupLibrarySettingDetails(<s:property value="%{id}" />);"><i class="fa fa-edit"></i>Edit 
								</a>
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Please define the library settings like max allowed books per student
		or staff, fine etc.
	</div>
</s:else>
<div id=popupEditLibrarySettings></div>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
	function popupLibrarySettingDetails(tempId) {
		var url = jQuery.url.getChatURL("/library/ajaxDoEditLibSettings.do");
		$('#popupEditLibrarySettings').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "tempId=" + tempId,
				success : function(html) {
					$("#popupEditLibrarySettings").html(html);
				}
			});
		} 

changePageTitle("Library Settings");
$(document).ready(function() {
	$('a.normalEdit').click();
	$.subscribe('formValidationForSettings', function(event, data) {
		if ($('#addLibrarySettings').valid())
			event.originalEvent.options.submit = false;
	});
});
</script>