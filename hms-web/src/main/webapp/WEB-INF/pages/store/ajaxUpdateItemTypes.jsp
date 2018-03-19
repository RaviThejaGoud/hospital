<%@ include file="/common/taglibs.jsp"%>

<div data-width="760"  class="modal fade modal-overflow in" id="responsive2" style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
<div class="modal-header">		
<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Update Item Type
		</h4>
</div>
<div class="modal-body">
	
<s:form action="ajaxUpdateItems" theme="simple" id="updateItemType"
	method="post" cssClass="form-horizontal" namespace="/store">	
	<s:hidden name="itemType.id" value="%{itemType.id}" id="itemTypeId" />
		<div class="form-body">		
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>Type Name :
						</label>
						<div class="col-md-6">						 
							<sj:textfield name="itemType.typeName" 
								class="required form-control input-medium" maxlength="30" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>Measurement Type:
						</label>
						<div class="col-md-6">					
								<s:select  cssClass="form-control input-small"
								headerKey="" headerValue="- Select -"
								name="itemType.measurementType" value="%{itemType.measurementType}"
								list="#{'Kgs':'Kgs','Units':'Units','Liter':'Liter'}"></s:select>		
							
						</div>
					</div>
				</div>
			</div>
			<div id="TextBoxesGroup">			 
			</div>
			<br>
			
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit cssClass="submitBt btn blue" value="Submit"  id="clickSubmit" validate="true"
							indicator="indicator" onBeforeTopics="submitFormValidation" targets="mainContentDiv" formIds="updateItemType" />							
							
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
	 
	 if ($('#updateItemType').valid()){
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