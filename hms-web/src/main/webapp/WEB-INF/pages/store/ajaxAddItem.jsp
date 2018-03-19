<%@ include file="/common/taglibs.jsp"%>
	<s:form action="ajaxSaveItemToStore" theme="simple" id="addNewItem"
		method="post" cssClass="form-horizontal" namespace="/store">		
		<div class="form-body">
			<h4 class="bold pageTitle">
				Add New Item to Store
			</h4>
			<br>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Stores :
						</label>
						<div class="col-md-7">
							<s:select id="store" list="storeDataList" label="Store"
								cssClass="required form-control input-medium" listKey="id"
								listValue="storeName" headerKey="" headerValue="- Select -"
								name="item.storeId"
								/>
						</div>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Item Types :
						</label>
						<div class="col-md-7">
							<s:select id="itemTypes" list="itemTypesList" label="ItemTypes"
								cssClass="required form-control input-medium" listKey="id"
								listValue="typeName" headerKey="" headerValue="- Select -"
								name="item.itemTypeId"
								/>
						</div>
					</div>
				</div>
					
			
			</div>
			
			<!--/row-->
			<div class="row">
			 <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Item Name :
						</label>
						<div class="col-md-7">
							<sj:textfield name="item.itemName" id="itemName"						
								cssClass="required form-control input-medium" maxlength="40" />
						</div>
					</div>
				</div>
			 <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Item Code :
						</label>
						<div class="col-md-7">
							<sj:textfield name="item.itemCode" id="itemCode"								
								cssClass="required form-control input-medium" maxlength="40" />
						</div>
					</div>
				</div>
			
			 </div>
			 <div class="row">
			 <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Quantity :
						</label>
						<div class="col-md-7">
							<sj:textfield name="item.quantity" id="quantity"								
								cssClass="required numeric form-control input-medium" maxlength="10" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Price :
						</label>
						<div class="col-md-7">
							<sj:textfield name="item.totalPrice" id="totalPrice"								
								cssClass="required numericDot form-control input-medium" maxlength="10" />
						</div>
					</div>
				</div>
			
			 </div>			 
			 
			  <div class="row">
			 <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Suppliers :
						</label>
						<div class="col-md-7">
							<s:select id="suppliers" list="suppliersList" label="supplier"
								cssClass="required form-control input-medium" listKey="id"
								listValue="supplierName" headerKey="" headerValue="- Select -"
								name="item.supplierId"
								/>
						</div>
					</div>
				</div>
			 </div>
			
			 
			 
			 
			 	</div>
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit cssClass="submitBt btn blue" value="Submit"  id="clickSubmit"
							indicator="indicator" validate="true" targets="mainContentDiv" formIds="addNewItem" />							
							<s:url id="doViewItems" action="ajaxManageStoreItems"
								includeParams="all" escapeAmp="false" namespace="/store">
							</s:url>							
							<sj:a href="%{doViewItems}" cssClass="btn default"
								indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	
<script type="text/javascript">
$(document).ready(function() {
$('.numeric').numeric();
		$('.numericDot').numeric({
			allow : "."
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
	 
	 if ($('#addNewStore').valid()){	 	
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