<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14 commomnTabs">
	<%@ include file="/common/messages.jsp"%>
	<div class="grid_14">
		<div class="grid_7">
			<h1>
				Current Mess Settings
			</h1>
		</div>
		<div class="grid_8">
			<div class="grid_8">
				<s:if test="%{buildingList!=null && !buildingList.isEmpty()}">
					<div class="grid_6">
						<div class="grid_6">
							<label>
								<span class="required">*</span>Select Building:
							</label>
						</div>
						<div class="grid_6">
							<s:select list="buildingList" listKey="id"
								listValue="buildingName" cssClass="required textfield"
								theme="css_xhtml" required="true" id="buildingId"
								name="selectedId" requiredposition="first"
								onchange="javascript:onChangeBuldingName(this.value)">
							</s:select>
						</div>
					</div>
				</s:if>
				<s:else>
					There are no Buildings available. Please add <a href=#>Buildings</a>
				</s:else>
				<div class="grid_6">&nbsp;</div>
				<div id="hostelFoodTimesContent"></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Mess Details");
	onChangeBuldingName(buldingId);
});
function onChangeBuldingName(buldingId) {
	$('#hostelFoodTimesContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "selectedId="+ buldingId;
	$.ajax( {
		url : jQuery.url.getChatURL("/hostel/ajaxGetBuldingFoodItems.do"),
		cache : true,
		data : pars,
		success : function(response) {
			$('#hostelFoodTimesContent').html(response);
		}
	});
}
</script>