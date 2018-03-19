<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> <span class="hidden-481"> </span>
				</div>
			</div>
			<div class="portlet-body" id="studRegister">
				<div class="tab-content">
					<%@ include file="/common/messages.jsp"%>

					<s:form action="ajaxMotherTongueTypeReports" theme="simple"
						cssClass="form-horizontal"
						onsubmit="return (generateClassIds() &&generateMotherTongueIds());"
						id="classAndMotherTongue" method="post" namespace="/reports">
						<s:hidden name="tempString"></s:hidden>
						<s:hidden name="plTitle"></s:hidden>
						<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
						<s:hidden id="classNameIds" name="SelectedId" />
						<s:hidden id="motherTongueIds" name="SelectedMotherTongueId" />
						<s:hidden id="roleName" name="username" />
						<div class="form-body">
							<h4 class="pageTitle bold">Mother Tongue Wise Student
								Summary Details</h4>

							<div class="form-group">
								<label class="conLable col-md-3 control-label"> <span
									class="required">*</span> Mother Tongue :
								</label>
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="checkbox-inline"> <input type="checkbox"
											name="" value="" onClick="checkAllMotherTongue()"
											class="checkbox allMotherTongue"> All Mother Tongue
										</label>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-12">
										<div class="checkbox-list">
											<s:checkboxlist name="chkBoxMotherTongueSelectedIds"
												list="motherTongueList" listKey="id" listValue="name"
												theme="ems" cssClass="small" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<s:if test='%{studyClassList.size >0}'>
							<div class="form-group">
								<label class="conLable col-md-3 control-label"> <span
									class="required">*</span> Class With Students :
								</label>
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="checkbox-inline"> <input type="checkbox"
											name="" value="" onClick="checkAllClasses()"
											class="checkbox allClasses"> All Classes & Sections
										</label>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-12">
										<div class="checkbox-list">
											<s:checkboxlist name="chkBoxSelectedIds"
												list="studyClassList" listKey="id"
												listValue="classAndSection" theme="ems" cssClass="small" />
										</div>
									</div>
								</div>
							</div>
						</s:if>
						<s:if test='%{tempList2.size == 0 && studyClassList.size == 0}'>
							<font style="color: red"> You have not created any
								Classes. You can able to create Class & Section.</font>
						</s:if>


						<div class="form-actions fluid">
							<div class="col-md-offset-2 col-md-9">

								<s:submit type="submit" value="Generate Excel"
									cssClass="submitBt btn blue" cssStyle="float:left;"
									title="generate report" onclick="reportFormate()">
								</s:submit>
							</div>
						</div>
				</div>
				</s:form>

			</div>
		</div>
	</div>
</div>
<span id="loginRoleName" style="display: none;"><s:property
		value="user.userRoleDescription" /> </span>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						FormAdvanced.init();
						$("input:checkbox, input:radio:not('.toggle')")
								.uniform();

						var str = $('.page-sidebar-menu li.active').find(
								'li.active').find('ul.sub-menu').find(
								'li.active').children('a').text().trim();
						if (isNonEmpty(str)) {
							$('span.hidden-481').html(
									$('.page-sidebar-menu li.active').find(
											'li.active').children('a')
											.children('span.title').text()
											.trim()
											+ "-->"
											+ $('.page-sidebar-menu li.active')
													.find('li.active').find(
															'ul.sub-menu')
													.find('li.active')
													.children('a').text()
													.trim())
						} else {
							$('span.hidden-481').html(
									$('.page-sidebar-menu li.active').find(
											'li.active').children('a')
											.children('span.title').text()
											.trim())
						}
						var title = $('.page-sidebar-menu li.active').find(
								'li.active').find('ul.sub-menu').find(
								'li.active').children('a').text().trim();

						changePageTitle(title);

						$("input[name=chkBoxSelectedIds]")
								.click(
										function() {
											if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
												$(".allClasses").parent('span')
														.removeClass("checked");
												$(".allClasses").attr(
														"checked", false);
											} else {
												$(".allClasses").parent('span')
														.addClass("checked");
												$(".allClasses").attr(
														"checked", true);
											}
										});
						$("input[name=chkBoxMotherTongueSelectedIds]")
								.click(
										function() {
											if ($("input[name=chkBoxMotherTongueSelectedIds]:unchecked").length > 0) {
												$(".allMotherTongue").parent(
														'span').removeClass(
														"checked");
												$(".allMotherTongue").attr(
														"checked", false);
											} else {
												$(".allMotherTongue").parent(
														'span').addClass(
														"checked");
												$(".allMotherTongue").attr(
														"checked", true);
											}
										});

					});
	$('html, body').animate({
		scrollTop : $(document).height() - $(window).height()
	}, 10, function() {
		$(this).animate({
			scrollTop : 0
		}, 10);
	});
	function reportFormate() {
		$('.anyId').val('Excel');
	}
	function reportType() {
		$('.anyId').val('PDF');
	}

	function checkAllClasses() {
		if ($(".allClasses").is(':checked')) {
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

	function generateClassIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			if (classIds.length > 0) {
				selectedClassIds = '(';
				for (var i = 0; i < classIds.length; i++) {
					selectedClassIds += classIds[i].value + ', ';
				}
				selectedClassIds += '0)';
			}
			$("#classNameIds").val(selectedClassIds);
			var value = $("input[name='selectedName']").val();
			if (isNonEmpty(value)) {
				$("#roleName").val(value);
			}
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
		} else {
			return false;
		}
	}
	function generateMotherTongueIds() {
		if ($("input[name=chkBoxMotherTongueSelectedIds]:checked").length > 0) {
			var motherTongueIds = $("input[name=chkBoxMotherTongueSelectedIds]:checked");
			var selectedMotherTongueIds = '';
			if (motherTongueIds.length > 0) {
				selectedMotherTongueIds = '(';
				for (var i = 0; i < motherTongueIds.length; i++) {
					selectedMotherTongueIds += motherTongueIds[i].value + ', ';
				}
				selectedMotherTongueIds += '0)';
			}
			$("#motherTongueIds").val(selectedMotherTongueIds);
			var value = $("input[name='selectedName']").val();
			if (isNonEmpty(value)) {
				$("#roleName").val(value);
			}
			return true;
		} else if ($("input[name=chkBoxMotherTongueSelectedIds]:checked").length == 0) {
			alert("Please select at least one Mother Tongue");
			return false;
		} else {
			return false;
		}

	}
	function checkAllMotherTongue() {
		if ($(".allMotherTongue").is(':checked')) {
			$("[name='chkBoxMotherTongueSelectedIds']").each(function() {
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
		} else {
			$("[name='chkBoxMotherTongueSelectedIds']").each(function() {
				$(this).parent('span').removeClass('checked');
				$(this).removeAttr("checked");
			});
		}
	}
</script>