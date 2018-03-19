<%@ include file="/common/taglibs.jsp"%>

<div data-width="760"  class="modal fade modal-overflow in" id="responsive2" style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
<div class="modal-header">		
<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Update Item 
		</h4>
</div>
<div class="modal-body">
	
<s:form action="ajaxSaveItemToStore" theme="simple" id="updateItem"
	method="post" cssClass="form-horizontal" namespace="/store">	
	<s:hidden name="item.itemId" value="%{item.itemId}" id="itemId" />
	<s:hidden name="item.storeId" value="%{item.storeId}" id="storeId" />
		<div class="form-body">		
		
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Item Name :
						</label>
						<div class="col-md-6">						 
							<sj:textfield name="item.itemName" 
								cssClass="required form-control input-medium" maxlength="40" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Item Code:
						</label>
						<div class="col-md-6">						 
							<sj:textfield name="item.itemCode" 
								cssClass="required form-control input-medium" maxlength="20" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit cssClass="submitBt btn blue" value="Submit"  id="clickSubmit" validate="true"
							indicator="indicator" onBeforeTopics="submitFormValidation" targets="mainContentDiv" formIds="updateItem" />							
							
							 <button type="button" data-dismiss="modal" class="btn default"> Cancel </button>						
										
					</div>
				</div>
			</div>
		</div>
	</s:form>
	
<script type="text/javascript">

$.subscribe('submitFormValidation',function(event, data) {
	var notValid="No";
	 $('p.word-taken').each(function() {
	  if($(this).html()=='Already taken!!!'){
		  notValid = "Yes";
	     event.originalEvent.options.submit=false;
	   }
	 });	 
	 
	 $('p.word-available').each(function() 
	 {
		  if($(this).html()=='Available'){
			  $('button.close').click();
			  return true;
		   }
	 });
	 
	 if ($('#updateItem').valid()){
		 if("Yes" == notValid)
		 {
			 event.originalEvent.options.submit=false;
		 }
		 else
		 {
			 $('button.close').click();
				return true;
		 }
			
	}	 
});	
	
  	
</script>