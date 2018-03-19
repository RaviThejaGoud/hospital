<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				var errMsg = $('.alert .alert-success');
				if (errMsg) {
					$('.alert .alert-success').hide().append(
							'<span class="close1" title="Click to Close"></span>')
							.fadeIn('slow');
					$('.alert .alert-success .close').hover( function() {
						$(this).addClass('hover');
					}, function() {
						$(this).removeClass('hover');
					});

					$('.alert .close').click( function() {
						$(this).parent().fadeOut('slow', function() {
							$(this).remove();
						});
					});
				}
	});
</script>

<s:if test="hasActionMessages()">
	<s:iterator value="actionMessages">
		<div class="alert alert-success">
			<button data-dismiss="alert" class="close"></button>
			<strong><s:property />
			</strong>
		</div>
	</s:iterator>
</s:if>


<s:if test="hasActionErrors()">
	<s:iterator value="actionErrors">
		<div class="alert alert-danger">
			<button data-dismiss="alert" class="close"></button>
			<strong><s:property escape="false"/>
			</strong>
		</div>
	</s:iterator>
</s:if> 

