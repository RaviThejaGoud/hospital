<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				var errMsg = $('.block .message');
				if (errMsg) {
					$('.block .message').hide().append(
							'<span class="close1" title="Click to Close"></span>')
							.fadeIn('slow');
					$('.block .message .close1').hover( function() {
						$(this).addClass('hover');
					}, function() {
						$(this).removeClass('hover');
					});

					$('.block .message .close1').click( function() {
						$(this).parent().fadeOut('slow', function() {
							$(this).remove();
						});
					});
				}
	});
</script>

<s:if test="hasActionMessages()">
	<s:iterator value="actionMessages">
		<div class="block"
			style="margin-bottom: 0px; margin-top: -5px; padding-bottom: 0px">
			<div style="display: block;width:500px;" class="message success">
				<s:property />
			</div>
		</div>
	</s:iterator>
</s:if>


<s:if test="hasActionErrors()">
	<s:iterator value="actionErrors">
		<div class="block"
			style="margin-bottom: 0px; margin-top: -15px; padding-bottom: 0px;">
			<div style="display: block" class="message errormsg">
			<img width="28" height="24"   src="../images/newImages/error_icon.png">
				<s:property />
			</div>
		</div>
	</s:iterator>
</s:if>
