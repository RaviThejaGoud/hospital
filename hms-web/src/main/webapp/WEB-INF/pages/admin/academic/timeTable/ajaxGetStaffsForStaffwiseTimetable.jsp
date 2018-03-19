<%@ include file="/common/taglibs.jsp"%>

<div class="form-body">
<s:if test="%{teacherList != null && !teacherList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required">*</span>Select Staff :
		</label>
		<div class="col-md-3">
			<s:select list="teacherList" id="staffId" headerKey="" headerValue="- Select -"
										cssClass="form-control input-medium"
										name="StaffId" listKey="staffId"
										listValue="fullNameRoleName" theme="simple"
										onchange="javascript:getAjaxDoGetStaffwiseTimetable(this.value);">
									</s:select>
									
		</div>
	</div>
	<div>&nbsp;</div><div>&nbsp;</div>
	
	<div id="viewTimetableDivId"></div>
	
	</s:if>
	<s:else>
	<div class="alert alert-info">Currently there are no staff available for displaying staff wise timetable </div>
	</s:else>
</div>
<script language="JavaScript" type="text/javascript">
changePageTitle('Staff Wise Timetable');
$(document).ready(function() {
	 $("input:checkbox, input:radio:not('.toggle')").uniform(); 
	});

	function getAjaxDoGetStaffwiseTimetable(staffId) {
		  
		  	if (staffId == "") {
				alert("Please select the staff.");
			} else {
				var pars = "tempId=" + staffId;
				$("#viewTimetableDivId")
						.html(
								'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				var url = jQuery.url.getChatURL("/timeTable/ajaxViewStaffWiseTimetable.do");
				$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#viewTimetableDivId").html(html);
					}
				});
			}
		}

</script>