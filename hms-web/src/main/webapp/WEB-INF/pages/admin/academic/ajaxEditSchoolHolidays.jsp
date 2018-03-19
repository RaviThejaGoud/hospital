<%@ include file="/common/taglibs.jsp"%>
<td colspan="4" id="schoolEditHolidays">
	<div>
					<s:form action="ajaxDoUpdateSchoolHolidays" theme="css_xhtml" id="updateSchoolHolidays" method="post" namespace="/admin">
					<s:hidden name="id" value="%{schoolHolidays.id}"></s:hidden>
					<s:hidden name="academicYearId" value="%{academicYearId}"></s:hidden>
					<div class="grid_11">
					<div class="grid_3">
					 <sj:textfield name="schoolHolidays.description" id="holidayName" required="true"
						label="Holiday Desc" labelposition="top"
						cssClass="textfield required" maxlength="20" cssStyle="width:140px;"></sj:textfield>
						</div>
						<div class="grid_4">
						<sj:datepicker id="startDate" label="From Date" readonly="true" 
						name="schoolHolidays.startDate" cssClass="text small required" cssStyle="width:120px;"
						required="true" changeMonth="true" changeYear="true" yearRange="2011:2015" minDate="0"/>
						</div>
						<div class="grid_4">
						<sj:datepicker id="endDate" label="To Date" readonly="true" 
						name="schoolHolidays.endDate" cssClass="text small required" cssStyle="width:120px;"
						required="true" changeMonth="true" changeYear="true" yearRange="2011:2015" minDate="0" />
						</div>
						<div class="grid_5">
						<div class="grid_2">
						<sj:submit   cssClass="submit small" value="Submit" indicator="indicator"
						targets="stepHolidays" validate="true" />
						</div>
						<div class="grid_2">
						<s:url id="doViewHolidaysList" includeParams="all" escapeAmp="false">
						   <s:param name="academicYearId" value="academicYearId"/>
						</s:url>
					    <sj:a href="%{doViewHolidaysList}" cssClass="cancelButton" onCompleteTopics="ajaxViewHolidays"
						indicator="indicator" targets="doEditContent%{schoolHolidays.id}" button="false">Cancel</sj:a>
						</div>
						</div>
					 </div>
				</s:form>
                   </div>
	      </td>
         <script type="text/javascript">
	     $(document).ready(function() {
						var validator;
					 	$("input#zipcode").mask("99999");
						$("input#phoneNumber").mask("(999)-999-9999");
					});
			document.title = 'view Holidays';
			$.subscribe('doEditNAdUpdateHolidays', function(event, data) {
				if ($('#' + data.id).is(":hidden")) {
					$('#' + data.id).show();
				} else {
					$('#' + data.id).hide();
				}
			});
</script>