<%@ include file="/common/taglibs.jsp"%>
<div id="steps">
<s:form action="ajaxEditTransportFee" id="editFee" method="post"
	theme="css_xhtml">
	<s:hidden name="transportFee.id"/>
	<div class='error' style='display: none;'>
		<span></span>
	</div>
		<!--<div class="grid_7 alpha">
		<sj:textfield name="className.className" id="admissionFee" label="Class Name"
					cssClass="text required small" maxlength="40" required="true"></sj:textfield>
		</div>
			--><div class="grid_7 alpha">
				<sj:textfield name="transportFee.transportFee" id="transportFee" label="Transport Fee "
					cssClass="numeric required text small" maxlength="5" required="true"></sj:textfield>
			</div>
			<div class="grid_10 alpha">
				&nbsp;
			</div>
			<div class="grid_10 alpha">
				<div class="grid_7 alpha">
						<sj:datepicker id="date0" name="transportFee.transportFeeDueDate" label="Payment Due Date"
						cssClass="text small required" required="true" readonly="true" 
						cssStyle="width:167px;height:20px;" changeMonth="true" changeYear="true" />
				</div>
			</div>
		
	<div class="grid_7 alpha">
			   <sj:submit   targets="transportContentFee" value="Submit" indicator="indicator"
				cssClass="submit small" formIds="editFee" onClickTopics="editTransFee"/>
			   <s:url id="doEditTripFee" action="ajaxDoEditTripFee"
					includeParams="all" escapeAmp="false">
					<s:param name="transportFee.id" value="%{transportFee.id}" />
				</s:url>
				<sj:a href="%{doEditTripFee}" onCompleteTopics="doInitEditTripFee" cssClass="cancelButton"
					indicator="indicator" targets="editTripFee%{transportFee.id}" button="false" buttonIcon="ui-icon-plus">
					Cancel
				</sj:a>
			</div>
		</s:form>
	</div>
	<script type="text/javascript">
changePageTitle('Edit Transport Fee');
$(document).ready(function() {
		$.subscribe('doInitEditTripFee', function(event, data) {
			if ($('#editFee').valid())
				return true;
			else
				return false;
		});
	});
</script>