<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="col-md-12">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-globe"></i> <span class="hidden-title"></span>
			</div>
		</div>
		<div class="portlet-body">
			<div class="dropdown tabbable tabbable-custom tabbable-full-width">
				<div class="tab-content" id="FeeCollectionDetailsDiv">
					<s:form action="ajaxDownloadStudentMarksUpdatedDetails" theme="simple"
							id="studentMarksUpdatedIds" method="post" cssClass="form-horizontal"
							namespace="/reports">
							<input type="hidden" name="pdfId" value="pdf" />
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4"> <span
										class="required">*</span>Select Exam Type :
									</label>
									<div class="col-md-5">
										<s:select id="examTypes" listKey="id" listValue="examType" list="examTypeList" required="true"
											tabindex="1" name="tempId" cssClass="required form-control" />
									</div>
								</div>
							</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-2 col-md-9">
									<sj:submit cssClass="submitBt btn blue long" value="Generate Pdf" 
									formIds="studentMarksUpdatedIds"  />
								</div>
							</div>
				</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('li#allFeeReportsDiv').addClass('active');
	$('.page-sidebar-menu li.active').addClass('open');
	var title = '';
	$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
						+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
	title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
	if(!isNonEmpty(title)){//this is used to parent and student logns
		 $('span.hidden-title').html($('.navbar-nav li.active').children('a').children('span.title').text().trim() + "-->"
					+ $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim())
	  title = $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim();
	}
    changePageTitle(title);
  FormComponents.init();
  $("input:checkbox, input:radio").uniform();
  $("#createDailyAttendenceDiv").find('span.hidden-title').remove();
});
</script>