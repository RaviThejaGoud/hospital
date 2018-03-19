<%@ include file="/common/taglibs.jsp"%>
<div class="modal fade modal-overflow in" data-width="760"
	id="responsiveSchoolDates"
	style="display: block; width: 400px; height: 250px; top: 202px;"
	aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close schoolInfo" data-dismiss="modal"
			aria-hidden="true"></button>
			<!-- <div class="spaceDiv">&nbsp;</div>
			<div class="spaceDiv">&nbsp;</div> -->
	</div>	
	<div class="modal-body">
		<div class="alert alert-info">	
			<h4 class="bold pagetitle" style="font-size: 20px;">
				Alert! Please provide the academic settings for example School start date & end date , No.of working days.<br/><div class="spaceDiv"> </div>
				You can able to access remaining modules, only after providing academic settings.
				
			</h4>
		</div>
	</div>
</div>
<script language="JavaScript" type="text/javascript">
$('button.schoolInfo').click(function(){
	   $('a#showNewSchool').removeAttr('data-toggle');
	   $('a#showNewSchool').removeAttr('href');
	});
	$(document).ready(function(){
	 $('div.modal-body').click();
	 if($("div#responsiveSchoolDates:hidden")){
	  $('a#showNewSchool').removeAttr('href');
	 }
	});
</script>
