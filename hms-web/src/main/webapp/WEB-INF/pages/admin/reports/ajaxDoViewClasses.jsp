<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-title"></span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<s:form action="ajaxClassWiseGenderAndComunity" theme="simple"
						namespace="/reports" onsubmit="return generateClassIds();"
						id="classAndTodate" cssClass="form-horizontal" method="post">
						<s:hidden name="tempString"></s:hidden>
						<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
						<s:hidden name="plTitle"></s:hidden>
						<s:hidden id="classNameIds" name="SelectedId" />
						<s:hidden id="monthNameIds" name="monthNameIds" />
						<div class="form-body">
						<p>
							<span class="label label-danger">NOTE :</span>&nbsp;&nbsp; Report should be generated only for active students.
						</p>
							<%@ include
								file="/WEB-INF/pages/common/ajaxClassNamesChkBoxList.jsp"%>
						<div class="form-actions fluid">
							<div class="col-md-6">
								<div class="col-md-offset-3 col-md-9">
									<s:submit type="submit small" value="Generate Excel"
										cssClass="submitBt btn blue long" title="generate report" />
								</div>
							</div>
						</div>
						</div>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="schoolTermlist"></div>
<script type="text/javascript">
	$(document).ready(function() {
	changePageTitle("Category & Comunity Class Gender Wise Summary");
	$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
		+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
		var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
	});
</script>
