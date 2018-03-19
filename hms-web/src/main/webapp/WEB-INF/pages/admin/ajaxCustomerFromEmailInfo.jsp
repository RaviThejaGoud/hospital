<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Email Configuration Settings
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">

					<div id="changeSchoolInfoContent" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:if test='%{customer != null && !customer == ""}'>

							<div class="form-body">
								<s:form action="ajaxUpdateFromEmailInfo" theme="simple" cssClass="form-horizontal" id="changeSchoolInfo" method="post"
									enctype="multipart/form-data" namespace="/admin">
									 <div class="form-group">
										<div class="panel-body col-md-12">
											<div class="col-md-1">
												<span class="label label-danger"> NOTE : </span>&nbsp; 
											</div>
											<div class="col-md-10">
												<ul>
													<li>
														Give valid email and password.
													</li>
													<li>
														You can set a from email address to all the e-mails those are send by your school.
													</li>
													<li>
														Need to provide the login details for the email (Login id & Password) which you wish to set as from address.
													</li>
													<li>
														To configure gmail account, login to your email account and turn on "Less secure apps" option by <a href="https://www.google.com/settings/security/lesssecureapps" target="_new"  id="authentication">click on this</a> 
													</li>
												</ul>
											</div>
									 	</div>
								  	</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> 
													<span class="required">*</span> Email :
												</label>
												<div class="col-md-5">
													<sj:textfield name="customer.contactEmail" id="contactEmail"
														cssClass="required form-control text_right input-medium"></sj:textfield>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4"> 
													<span class="required">*</span>Password : 
												</label>
												<div class="col-md-5">
													<input name="customer.contactPassword" size="20" id="contactPassword" type="password"
														value='<s:property value="customer.contactPassword"/>' class="required form-control text_right input-medium numeric" />
												</div>
											</div>
										</div>
									</div>

									<s:if test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
										<div class="form-actions fluid">
											<div class="col-md-offset-2 col-md-9">
												<sj:submit cssClass="btn blue" value="Submit" targets="mainContentDiv" validate="true" />
											</div>
										</div>
									</s:if>
								</s:form>
							</div>
						</s:if>
						<s:else>
							<div class="alert alert-info">No information found for this customer.</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle('From Email Setting');
	});
</script>
