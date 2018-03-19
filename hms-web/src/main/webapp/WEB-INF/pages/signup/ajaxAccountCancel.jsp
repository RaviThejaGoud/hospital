<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>Account Cancellation</title>
</head>
<script type="text/javascript">
	function noBack() {
		window.history.forward()
	}
	noBack();
	window.onload = noBack;
	window.onpageshow = function(evt) {
		if (evt.persisted)
			noBack()
	}
	window.onunload = function() {
		void (0)
	}
</script>

<div class="grid_16">
<div class="block_head">
		<h2>
			Payment Failed
		</h2>
	</div>
	<div class="block_content" style="background-color: #ffffff;">
	<div class="grid_4">
	
		<h2>
			Payment Process Failed
		</h2>
	</div>
	
	<div class="grid_13">
		Your transaction could not be completed at this time. Your account has
		not been created and your credit card has not been charged. If you
		continue to experience difficulties, please
		<a href="#" target="new">contact
			us.</a>
	</div>
</div>
</div>
