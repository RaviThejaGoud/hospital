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
	<div class="block small center" style="width: 940px;">
		Your request could not be completed at this time.If you continue to
		experience difficulties, please
		<a href="#" target="new">contact
			us.</a>
	</div>
</div>
