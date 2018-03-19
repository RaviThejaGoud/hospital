<%@ include file="/common/taglibs.jsp"%>
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
<div id="article-top" class="grid_24">
</div>
<div  class="demo grid_24 omega">
	<s:if test='%{subscriptionType=="Y"}'>
		<jsp:include
			page="/WEB-INF/pages/signup/yearlyPaymentConfirmation.jsp" />
	</s:if>
	<s:else>
		<jsp:include
			page="/WEB-INF/pages/signup/monthlyPaymentConfirmation.jsp" />
	</s:else>
</div>