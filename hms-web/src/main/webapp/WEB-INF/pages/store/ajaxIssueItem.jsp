<%@ include file="/common/taglibs.jsp"%>

<div data-width="760"  class="modal fade modal-overflow in" id="responsive2" style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
<div class="modal-header">		
<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Issue Item : <s:property value="itemName"/> - Available Quantity: <s:property value="availableQuantity"/>
		</h4>
</div>
<div class="modal-body">
	
<s:form action="ajaxIssueItem" theme="simple" id="issueItems"
	method="post" cssClass="form-horizontal" namespace="/store">	
	<s:hidden name="itemId" value="%{itemId}" id="itemId" />
	<s:hidden name="storeId" value="%{item.storeId}" id="storeId" />
	<s:hidden name="availableQuantity" value="%{item.availableQuantity}" id="availableQuantity" />
		<div class="form-body">		
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> 
							<span class="required">*</span>Quantity :
						</label>
						<div class="col-md-6">						 
							<sj:textfield name="issuedItems.quantity" id="issuedQuantity" class="myrule"
								cssClass="required numeric form-control input-medium" maxlength="10" required="required"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Receiver Name:
						</label>
						<div class="col-md-6">						 
							<sj:textfield name="issuedItems.recieverName" 
								cssClass="required form-control input-medium" maxlength="40" required="required" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit cssClass="submitBt btn blue" value="Submit"  id="clickSubmit"  validate="true"
							indicator="indicator" onBeforeTopics="submitFormValidation" targets="mainContentDiv" formIds="issueItems" />							
							
						<button type="button" data-dismiss="modal" class="btn default"> Cancel </button>	
										
					</div>
				</div>
			</div>
		</div>
	</s:form>
</div>
</div>
	
<script type="text/javascript">
$(document).ready(function () {
$('.numeric').numeric();		
    var message;

    $.validator.addMethod('method_C', function (value, element, param) {
        var result;
        var availableQnt = $('#availableQuantity').val();
		var issuesQnt = $('#issuedQuantity').val();
		if (parseInt(issuesQnt) > parseInt(availableQnt)) {
            // validate value as per A
            result = (parseInt(issuesQnt) > parseInt(availableQnt)) ? false : true; // (fail or pass)            
            message = "The available quantity is " + availableQnt+". Please change the quantity.";
        } else {
        	result = true;
        }
        return this.optional(element) || result;
    }, function () {
        return message;
    });

    $('#issueItems').validate({
        rules: {
            "issuedItems.quantity": {
                required: true,
                method_C: true
            }
        },
       
    });

});
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
	 
	 if ($('#issueItems').valid()){
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