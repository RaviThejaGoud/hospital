<%@ include file="/common/taglibs.jsp"%>
<div class="modal fade modal-overflow in" data-width="760"
	id="responsiveVision"
	style="display: block; width: 400px; height: 382px; top: 202px;"
	aria-hidden="true">
	<div class="modal-header">
		<div class="portlet solid green">
			<button type="button" class="close visionMision" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="bold pagetitle" style="color:#fff">
				Vision & Mission
			</h4>
		</div>
	</div>
	<div class="modal-body">
		<s:if
			test="%{#session.customerVision != null || !#session.customerVision.isEmpty()}">
			<div class="col-md-12">
				<label class="control-label col-md-2">
					<b>Vision :</b>
				</label>
				<div class="col-md-10">
					<s:property value="#session.customerVision" />
				</div>
			</div>
		</s:if>
		<div>
			&nbsp;
		</div>
		<div>
			&nbsp;
		</div>
		<s:if
			test="%{#session.customerMission != null || !#session.customerMission.isEmpty()}">
			<div class="col-md-12">
				<label class="control-label col-md-2">
					<b>Mission :</b>
				</label>
				<div class="col-md-10">
					<s:property value="#session.customerMission" />
				</div>
			</div>
		</s:if>
	</div>
</div>
<script language="JavaScript" type="text/javascript">
$('button.visionMision').click(function(){
   $('a#showVisionMission').removeAttr('data-toggle');
   $('a#showVisionMission').removeAttr('href');
});
$(document).ready(function(){
 $('div.modal-header').click();
 if($("div#responsiveVision:hidden")){
  $('a#showVisionMission').removeAttr('href');
 }
});
</script>
