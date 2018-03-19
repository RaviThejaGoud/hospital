<%@ include file="/common/taglibs.jsp"%>
<div class="row"  >
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Online Admissions
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<div id="onlineAdmContentDiv" class="tab-content">
						 <div class="row">
							<div class="col-md-12">
							<div>
							<p>
								<span class="label label-danger"> NOTE : </span>&nbsp; To enable the online admissions copy the below code and place it in your school website.
							</p>
							<div class="row">&nbsp;</div>
							</div>
								<div class="form-group">
									<label class="control-label col-md-2">
										 Admissions Iframe Code :
									</label>
									<div class="col-md-10">
										<div class="form-control" style="height: auto;">
											<div style="margin-left:10px;">
												&lt;script type="text/javascript" charset="utf-8" &gt;
													<br />
														<div style="margin-left:20px">
															var host = (("https:" == document.location.protocol) ? "https://secure.": "http://");<br />
															document.write(unescape("%3Cscript src='<s:property value="hostUrl"/>/scripts/common/form.js' type='text/javascript'%3E%3C/script%3E"));<br />
														</div>
													&lt;/script&gt;
													<br />
												&lt;script type="text/javascript" charset="utf-8"&gt;
												<br /><div style="margin-left:20px">
													var eazyschool = new URTSubForm();<br />
													eazyschool.initialize({<br />
													'product':'signup',<br />
													'custId':'<s:property value="customerByCustId.id" />',<br />
													'scrolling':'yes',<br />
													'height':'1000'});<br />
													eazyschool.display();<br />
													</div>
												&lt;/script&gt;
												<br/>
												&lt;script type="text/javascript" charset="utf-8"&gt;
												<br /><div style="margin-left:20px">
													  window.location.reload(true);
													</div>
												&lt;/script&gt;
											</div>
										</div>
									</div>
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
	$(document).ready(function(){
		changePageTitle("Online Admissions Code");
	});
</script>
