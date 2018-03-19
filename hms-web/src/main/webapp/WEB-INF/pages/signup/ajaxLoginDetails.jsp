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
<div class="wrapper block">
	<div class="block_head">
		<h2>
			Payment Process Failed
		</h2>
	</div>
	<!-- .block_head ends -->
	<div id="signupStep1" class="block_content"
		style="background-color: #fff;">
		<div class="grid_16">
			<div class="grid_2">
				&nbsp;
			</div>
		</div>
		<div class="grid_16">
			<div class="grid_2">
				&nbsp;
			</div>
		</div>
		<div class="grid_16">
		<div class="grid_15">
			Your transaction could not be completed at this time. Your account
			has not been created and your credit card has not been charged. If
			you continue to experience difficulties, please
			<a href="#" target="new">contact us.</a>
			</div>
		</div>

	</div>
</div>