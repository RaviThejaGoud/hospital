<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet-body" id="studRegister">
			<div class="tab-content">
				<%@ include file="/common/messages.jsp"%>
				<s:if test="%{(hostelList != null && !hostelList.isEmpty())}">
					<s:form action="ajaxAddMess" theme="simple" id="addMessFormId"
						cssClass="form-horizontal" enctype="multipart/form-data"
						method="post" namespace="/hostel">
						<s:hidden id="hostelNameIds" name="anyTitle" />
						<div class="form-body">
							<h4 class="bold pageTitle">Add Mess</h4>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"> <span
											class="required">*</span>Mess Name :
										</label>
										<div class="col-md-7">
											<sj:textfield name="mess.messName" id="messName"
												cssClass="required form-control input-medium as-input"
												maxlength="50"></sj:textfield>
											<span class="help-block">(Enter at least three
												characters for Mess Name.)</span>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-3"> Description :</label>
										<div class="col-md-7">
											<sj:textarea rows="3" cols="20" name="mess.messDescription"
												maxCharsData="1000" tabindex="3"
												cssClass="form-control word_count"></sj:textarea>
											<span class="help-block">
												<div class="counter"></div>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label"> <span
									class="required">*</span> Available Hostel's :
								</label>
								<div class="col-md-9">
									<div class="checkbox-list">
										<label class="checkbox-inline"> <input type="checkbox"
											name="" value="" onClick="checkallHostels()"
											class="checkbox allHostels"> All Hostel's
										</label>
									</div>
									<s:checkboxlist name="chkBoxSelectedIds" list="hostelList"
										listKey="id" listValue="hostelName" theme="ems"
										cssClass="small" />
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-6">
									<div class="col-md-offset-3 col-md-12">
										<sj:submit cssClass="btn blue" value="Submit"
											indicator="indicator" formIds="addMessFormId"
											targets="mainContentDiv"
											onBeforeTopics="generateHostelIdsTopic" validate="true" />
										<s:url id="urlMessManagement" includeParams="all" escapeAmp="false"
											action="ajaxViewMessManagementHome" namespace="/hostel">
										</s:url>
										<sj:a href="%{urlMessManagement}" cssClass="btn default"
										indicator="indicator" targets="mainContentDiv"> Cancel</sj:a>
									</div>
								</div>
							</div>
						</div>
					</s:form>
				</s:if>
				<s:else>
					<div class="alert alert-info">Hostel's are not available.</div>
				</s:else>
			</div>
		</div>
	</div>
</div>
<script language="JavaScript" type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script language="JavaScript" type="text/javascript">
	changePageTitle('Add Mess Details');
	$(document)
			.ready(
					function() {
						FormComponents.init();
						FormAdvanced.init();
						UIExtendedModals.init();
						$.destroyTopic('generateHostelIdsTopic');
						$("input:checkbox, input:radio").uniform();
						FormAdvanced.init();
						$("input[name=chkBoxSelectedIds]")
								.click(
										function() {
											if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
												$(".allHostels").parent('span')
														.removeClass("checked");
												$(".allHostels").attr(
														"checked", false);
											} else {
												$(".allHostels").parent('span')
														.addClass("checked");
												$(".allHostels").attr(
														"checked", true);
											}
										});
						$("#messName").focus();
						$("#messName")
								.autoCheck(
										"${pageContext.request.contextPath}/hostel/ajaxCheckAddMessAvailableOrNot.do",
										{
											minChars : 3,
											min : "no",
										});
					});
	$.subscribe('generateHostelIdsTopic', function(event, data) {
		if ($('#addMessFormId').valid()) {
			event.originalEvent.options.submit = generateHostels();
		} else {
			event.originalEvent.options.submit = false;
		}
		 $('p.word-taken').each(function() {
			  if($(this).html()=='Already taken!!!'){
			     event.originalEvent.options.submit=false;
			   }
			 });
			 $('button.close').click();
	});
	function generateHostels() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var HostelIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedHostelIds = '';
			if (HostelIds.length > 0) {
				selectedHostelIds = '(';
				for (var i = 0; i < HostelIds.length; i++) {
					if (i == (HostelIds.length - 1))
						selectedHostelIds += HostelIds[i].value;
					else {
						selectedHostelIds += HostelIds[i].value + ', ';
					}
				}
				selectedHostelIds += ')';
			}
			$("#hostelNameIds").val(selectedHostelIds);
			//alert("Selected hostel shown under their individual columns in the report, All other (non selected) religious shown under OTHERS column.");
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one hostel");
			return false;
		}
	}

	function checkallHostels() {
		if ($(".allHostels").is(':checked')) {
			$("[name='chkBoxSelectedIds']").each(function() {
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
		} else {
			$("[name='chkBoxSelectedIds']").each(function() {
				$(this).parent('span').removeClass('checked');
				$(this).removeAttr("checked");
			});
		}
	}
</script>