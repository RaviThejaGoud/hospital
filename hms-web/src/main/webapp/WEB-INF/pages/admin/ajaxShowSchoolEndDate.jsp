<%@ include file="/common/taglibs.jsp"%>
<div class="modal fade modal-overflow in" data-width="760"
	id="responsiveEndDate"
	style="display: block; width: 400px; height: 250px; top: 202px;"
	aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close endDate" data-dismiss="modal"
			aria-hidden="true"></button>
			<div class="spaceDiv">&nbsp;</div>
			<div class="spaceDiv">&nbsp;</div>
	</div>	
	<div class="modal-body">
		<div class="alert alert-danger">	
			<h4 class="bold pagetitle" style="font-size: 20px;">
				Alert! You have exceeded your school end date.
			</h4>
		</div>
	</div>
</div>
<script language="JavaScript" type="text/javascript">
$('button.endDate').click(function(){
	   $('a#showEndDate').removeAttr('data-toggle');
	   $('a#showEndDate').removeAttr('href');
	});
	$(document).ready(function(){
	 $('div.modal-body').click();
	 if($("div#responsiveEndDate:hidden")){
	  $('a#showEndDate').removeAttr('href');
	 }
	});
</script>
