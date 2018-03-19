<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hiddenSpan"></span>
				</div>
			</div>
			<div class="portlet-body">
					<div class="tab-content">
						<s:form action="ajaxselectedTypeReports" theme="simple"
							cssClass="form-horizontal" onsubmit="return generateClassIds();"
							id="classAndCommunity" method="post" namespace="/reports">
							<s:hidden name="tempString"></s:hidden>
							<s:hidden name="plTitle"></s:hidden>
							<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
							<s:hidden id="classNameIds" name="SelectedId" />
							<s:hidden id="roleName" name="username" />
							<div class="form-body">
								<%@ include
									file="/WEB-INF/pages/common/ajaxClassNamesChkBoxList.jsp"%>
								<s:if test='%{plTitle != "ViewStudentClassSectionDetails"}'>
								<div class="form-group">
									<div class="col-md-7">
										<div class="make-switch has-switch" data-id="Y" data-value="N"
											style="width: 120px" data-off="warning" data-on="success"
											data-off-label="Inactive" data-on-label="Active">
											<input type="radio" class="toggle" checked="checked"
												id="active">
											<input type="hidden" name="anyTitle" value="Y">
										</div>
									</div>
								</div>
								<!--<div class="form-group">
										<div class="radio-list">
											<label class="radio-inline">
												<input type="radio" id="active" value="Y" class="radio"
													name="anyTitle" checked="checked">
												<b>Active </b>
											</label>
											<label class="radio-inline">
												<input type="radio" id="inactive" value="N" class="radio"
													name="anyTitle">
												<b>Inactive </b>
											</label>
										</div>
									</div>
								-->
								<div class="spaceDiv"></div><div class="spaceDiv"></div><div class="spaceDiv"></div><div class="spaceDiv"></div>
								
								
								</s:if>
								<div class="form-actions fluid">
									<div class="col-md-offset-2 col-md-9">
										<s:submit type="submit small" value="Generate Excel"
											cssClass="submitBt btn blue long" title="generate report"
											onclick="reportFormate()" />
									</div>
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script language="JavaScript" type="text/javascript">
changePageTitle('Add Student Details');
$(document).ready(function() {
	FormComponents.init();
	FormAdvanced.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();  
	
		$('span.hiddenSpan').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			changePageTitle(title);
		});
	function reportFormate() {
		$('.anyId').val('Excel');
	}
	function generateClassIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			if (classIds.length > 0) {
				selectedClassIds = '(';
				for ( var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				selectedClassIds += '0)';
			}
			$("#classNameIds").val(selectedClassIds);
			var value = $("input[name='selectedName']:checked").val();
			if (isNonEmpty(value)) {
				$("#roleName").val(value);
			} else {
				$("#roleName").val('ROLE_STUDENT');
			}
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
		} else {
			return false;
		}
	}
</script>