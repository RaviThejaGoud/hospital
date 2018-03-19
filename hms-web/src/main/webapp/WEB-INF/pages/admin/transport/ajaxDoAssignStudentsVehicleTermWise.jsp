<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{((studyClassList != null || !studyClassList.isEmpty()) || (objectList != null || !objectList.isEmpty()))}">
	<s:form action="ajaxAssignStudentBoardingDetails" id="assignStudentBoardingDetails" method="post" name="assignStudentBoardingDetails" 
		cssClass="form-horizontal" theme="simple" namespace="/admin">
		<div class="form-body" >
			<jsp:include page="/common/messages.jsp"></jsp:include>
			<s:hidden name="studentTransportData" cssClass='studnetTransportData' />
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>Please select class and assign the student to particular boarding type.</li>
						
					</ul>
				</div>
				<div class="form-group" style="background:;margin-left: -7px;margin-right: -15px;">
					<div class="col-md-6" >
						<div class="form-group" >
							<label class="col-md-4 control-label">
								Select Transport Mode :
							</label>
							<div class="radio-list">
								<label class="radio-inline">
									<input type="radio" name="transportType" id="transportType" checked="checked" value="T">
								School Transport
								</label>
								<label class="radio-inline">
									<input type="radio" name="transportType" value="O" id="transportType">Own Transport
								</label>
							</div>
						</div>
		
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">
						Select Class :
					</label>
					<div class="col-md-7">
						<s:select list="studyClassList" id="classSectionId"
							cssClass="form-control input-medium"
							name="classSectionId" listKey="id"
							listValue="classAndSection" theme="simple" headerKey="" headerValue="- Select -"
							onchange="javascript:getAjaxDoGetStudent(this.value);">
						</s:select>
					</div>
				</div>
				<div id="searchStudentsList" ></div>
		</div>
	</s:form>
</s:if>
<s:else>
	<div class="alert alert-info">No classes found for assigning
		students to vehicles.</div>
</s:else>

<script type="text/javascript">
	changePageTitle('Download Student Details');
	$(document).ready(function() {
		$("input:checkbox, input:radio").uniform();
	});
	$('input:radio').change(function() {
		$("#searchStudentsList").html("");
		$("#classSectionId").val("");
    });
	function getAjaxDoGetStudent(studyClassId) {
			var transportType = $('input[name=transportType]:radio:checked').val();
			var pars = "classSectionId=" + studyClassId+"&transportType="+transportType;
			//alert("pars :"+pars)
			$(".alert").hide();
			$("#searchStudentsList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/admin/ajaxSearchTransportModeWiseStudentDetails.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#searchStudentsList").html(html);
					$("#searchStudentsList").show();
				}
			});
		}
</script>