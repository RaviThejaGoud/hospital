<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet-body">
			<div class="tabbable tabbable-custom tabbable-full-width">
				<div id="logInPageDiv">
					<div class="row">
						<div class="col-md-12">
							<div>
								<p>
									<span class="label label-danger"> NOTE : </span>&nbsp; To
									 keep eazySchool login page in your website,please copy the below code and place it.
								</p>
								<div class="row">&nbsp;</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2"> Login page code :
								</label>
								<div class="col-md-10">
									<a href="#" onclick="javascript:copyToClipboard();">Click here to copy the below code</a>
								<textarea class="form-control" id="copyPasteDivCode" style="height: 160px;white-space: normal;">
								<form id="form-login" name="f" target="_blank" action="http://www.eazyschool.in/j_spring_security_check" method="post">
								<span id="loginTraining_message" class="alert"></span>
								<fieldset>
								<ul style="list-style-type:none;">
									<li>
										<label>
											User Name:
										</label>
										<input type='text' name='j_username' id="j_username"
											class="loginStyle text" maxlength="40" size="20" />
									     <br/>
									     <br/>
									     </li>
									     <li>
										<label style="margin-left:12px;">
											Password: 
										</label>
										<input type='password' id="j_password" name='j_password'
											maxlength="40" size="20" class="loginStyle text" />
											 <br/> <br/>
									</li>
									<li style="margin-left:82px;">
										<input type="submit" name="loginTraining$ctl13" value="Login"
											tabindex="3" class="submit">
									</li>
								</ul>
							</fieldset>
							 </form>
							</textarea>
						</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
 

<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Copy login code to your site");
	});

	function copyToClipboard() {
		// var $temp = $("<input>");
		// $("body").append($temp);

		$("#copyPasteDivCode").select();
		//$temp.val($(element).text()).select();
		document.execCommand("copy");

	}
	  
</script>
