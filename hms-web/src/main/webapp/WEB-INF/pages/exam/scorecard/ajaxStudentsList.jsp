<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<div class="col-md-12">
		<div class="col-md-3" style="width: 13%;"></div>
		<div class="col-md-6">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Student Name
						</th>
						<th>
							<div class="checkbox">
								Select All
								<label>
									<input type="checkbox" name="selectAll" value=''
										class="allClasses" id="selectAllIds" onclick="checkAll();" />
								</label>
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="studentsList">
						<tr>
							<td>
								<s:property value="FirstName" />
								&nbsp;
								<s:property value="lastName" />
							</td>
							<td>
								<div class="checkbox">
									<label>
										<input type="checkbox" name="chkBoxSelectedAccountIds"
											value='<s:property value="studId" />' id="checkbox">
									</label>
								</div>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	</s:if>
	<s:else>
	<div class="alart alart-info">
		Oops! No Students found for the selected class & section in the system.
	</div>
</s:else>

<script type="text/javascript">
changePageTitle("Send Class Wide Message");
$(document).ready(function() {
		FormAdvanced.init();
		FormComponents.init();
	$("input:checkbox, input:radio").uniform();
	/*var type = getCheckSelectedValues();
	if(type=="M" || type == ""){
		$('div.messageSubject2').hide();
	}else{
		$('div.messageSubject2').show();
	}*/
	$('.allClasses').parent('span').addClass('checked');
	$('.allClasses').attr("checked",true);
	$("[name='chkBoxSelectedAccountIds']").each(function() {
		$(this).parent('span').addClass('checked');
		$(this).attr("checked", "true");
	});
});
function checkAll() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedAccountIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='chkBoxSelectedAccountIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}

$("input[name=chkBoxSelectedAccountIds]").click(function() {
	if ($("input[name=chkBoxSelectedAccountIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
});
</script>