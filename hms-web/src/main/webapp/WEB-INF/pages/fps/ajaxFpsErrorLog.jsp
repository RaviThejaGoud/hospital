<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Error Logs
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
						<div>
							<form action="https://app.raygun.io/signin" method="post" target="my-iframe" id="form">
								<input type=hidden id=EmailAddress name="EmailAddress" value="ramprasad15@gmail.com"/>
								<input type=hidden id=Password name="Password" value="urt$prasad123"/>
							</form>
							<div >
					<iframe  src="https://app.raygun.io/dashboard" name="my-iframe" width="1381px" height="1980" id="myframe" style="margin-left: -250px;margin-top: -125px;display:none;">
					</iframe> </div>
				</div>
						
				</div>
			</div>
		</div>
	</div>
</div>
<style type="text/css">
.raygun-container {
    background: none repeat scroll 0 0 #fff! important;
}

</style>
<script type="text/javascript">
window.onload = function() 
{ 
 $("#form").submit();
 $("#myframe").hide();
  setTimeout( "jQuery('#myframe').show();",3000 );
 };
</script>
 
 