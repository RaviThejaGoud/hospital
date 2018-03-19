<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-481"> </span>
				</div>
			</div>
			<div class="portlet-body">
					<div class="tab-content">
					<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
						<s:form action="ajaxGenerateClassesMonthAttendance"
							cssClass="form-horizontal" theme="simple" namespace="/reports"
							id="classAndTodate" method="post">
							<jsp:include page="/common/messages.jsp" />
							<h4 class="pageTitle bold">
								Download Classes Day Wise Monthly Attendance Report
							</h4>
							<span class="label label-danger">NOTE :</span>
							<div class="panel-body">
								<ul>
									<li>
										System will generate attendance template for the months of
										academic planner.
									</li>
									<li>
										By default system will show holidays list in each month of
										sheet if holidays are defined in system.
									</li>
								</ul>
							</div>
							<div class="form-body">
								<div class="form-actions fluid">
									<div class="col-md-6">
										<div class="col-md-offset-6 col-md-9">
											<s:submit type="submit small" value="Generate Report"
												cssClass="submitBt btn blue long" title="generate report" />
										</div>
									</div>
								</div>
							</div>
						</s:form>
						<s:form action="ajaxUploadClassesAttendanceReport"
							cssClass="form-horizontal" id="uploadClassesAttSheet"
							namespace="/reports" method="post" theme="simple"
							enctype="multipart/form-data">
							<div class="spaceDiv"></div>
							<div class="spaceDiv"></div>
							<h4 class="pageTitle bold">
								Upload Classes Monthly Attendance Report
							</h4>
							<div class="alert alert-info">
								It stores uploaded attendance report in server location.
							</div>
							<p>
								<span class="label label-danger">NOTE :</span> If upload file
								name is already exist in server, file available in server will
								be replaced by new file. Please use different names if you
								upload multiple files.
							</p>
							<div class="spaceDiv"></div>
							<div class="form-body">
								<div class="form-group">
									<label class="control-label col-md-3">
										<span class="required">*</span>Upload an Excel Sheet :
									</label>
									<div class="col-md-4">
										<input type="file" name="uploadList[0]" value="" id="photoURL"
											class="btn default required">
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-6">
										<div class="col-md-offset-6 col-md-9">
											<sj:submit   targets="mainContentDiv" value="Submit"
												indicator="indicator" cssClass="submitBt btn blue small"
												validate="true" />
										</div>
									</div>
								</div>
							</div>
						</s:form>
						<div class="spaceDiv"></div>
						<div class="spaceDiv"></div>
						<div class="spaceDiv"></div>
						<s:if test="%{tempList != null && !tempList.isEmpty()}">
							<h4 class="pageTitle bold">
								Uploaded Files
							</h4>
							<p>
								<span class="label label-danger">NOTE :</span> Following files
								are uploaded in server. You can download following files by
								click on file name.
							</p>
							<div class="spaceDiv"></div>
							<s:iterator value="tempList">
								<a href='${pageContext.request.contextPath}/<s:property value="filePath" />' target="_new"> <s:property
										value="fileName" /> </a>
								<div class="spaceDiv"></div>
							</s:iterator>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								You have not uploaded any attendance reports.
							</div>
						</s:else>
						</s:if>
						<s:else>
							<div class="alert alert-info">Currently there are no student.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
</div>
<script type="text/javascript">
$(document).ready(
		function() {
			$("input:checkbox, input:radio").uniform();
			var title ='';
			$('span.hidden-481').html(
				$('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find( 'li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
				title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			if(!isNonEmpty(title)){//this is used to parent and student logns
				 $('span.hidden-481').html($('.navbar-nav li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim())
			 title = $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim();
			}
			changePageTitle(title);
		});
</script>