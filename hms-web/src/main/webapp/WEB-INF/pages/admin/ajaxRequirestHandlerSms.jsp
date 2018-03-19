<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Sub-merchant checkout page
				</div>
			</div>
			<div class="portlet-body">
			 	<form target="_new" id="nonseamless" method="get" name="redirect" action="#"/> 
					<input type="hidden" id="encRequest" name="encRequest" value="<s:property value="tempString"/>">
					<input type="hidden" name="access_code" id="access_code" value="AVLB06CI27AD69BLDA">
					<script language='javascript'>/* document.redirect.submit(); */</script>
				</form>
				<h4>Please wait a minute ....<img   src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="myDefaultIndicator" style="background-repeat: no-repeat;position: fixed;z-index: 1000;top: 50%;left: 50%;text-align:center;" /></h4>
			</div>
		</div>
	</div>
</div> 
<script>
var encRequest = $('#encRequest').val();
window.location = 'http://www.hyniva.com/ccavrequesthandler.php?'+encRequest;
</script>