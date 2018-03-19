<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{store.id != 0}">
<div data-width="760"  class="modal fade modal-overflow in" id="responsive2" style="display: block; width: 900px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Update Store
		</h4>
	</div>
	<div class="modal-body">
	</s:if>
	<s:form action="ajaxDoSaveStore" theme="simple" id="addNewStore"
		method="post" cssClass="form-horizontal" namespace="/store">
		<s:hidden name="store.id" value="%{store.id}" id="storeId" />
		<div class="form-body">		
		  <s:if test="%{store.id == 0}">
			<h4 class="bold pageTitle">
				Add New Store
			</h4>	
				
			</s:if>
			
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Store Name :
						</label>
						<div class="col-md-4">
							<sj:textfield name="store.storeName" id="storeName" 
								cssClass="required form-control input-medium" maxlength="40" />
						</div>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span>Store Keeper Name:
						</label>
						<div class="col-md-4">
						
								<s:select list="storeKeeperUsersList" listKey="accountId" 
								listValue="fullPersonName" id="storeKeeperName" cssClass=" required form-control input-medium"
								headerKey="" headerValue="- Select -"
								cssStyle="padding: 3px 10px;" name="store.storeKeeperAccountId" ></s:select>
		
						 
							
						</div>
					</div>
				</div>
		
				
				<!--/span-->
			</div>
			<!--/row-->
			<div class="row">
			 <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Street :
						</label>
						<div class="col-md-7">
							<sj:textfield name="store.storeAddress.streetName" id="street"								
								cssClass="required form-control input-medium" maxlength="200" />
						</div>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-5">
						<span class="required">*</span> City : </label>
						<div class="col-md-5">
							<sj:textfield name="store.storeAddress.city" id="city"
								cssClass="required form-control input-medium" maxlength="22"></sj:textfield>
						</div>
					</div>				
				</div>
			</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> 
								<span class="required">*</span>Country :
							</label>
							<div class="col-md-8">
								<s:select id="country" list="countryList" cssClass="required form-control input-medium"
									listKey="countryCode" listValue="countryName" headerKey="" headerValue="- Select -"
									name="store.storeAddress.country"
									onchange="javascript:enableStatesList(this.value);" />
							</div>
						</div>
					</div>
					
					<s:if test="%{store.id != 0}">
						<div class="col-md-6" id="countryState" >
					</s:if>
					<s:else>
						<div class="col-md-6" id="countryState" style="display: none">
					</s:else>
							<div class="form-group">
							<label class="control-label col-md-5"> <span
								class="required">*</span>State :
							</label>
							<div class="col-md-6">
								<s:select id="state" list="statesList" cssClass="required form-control stateSelect"
									listKey="stateCode" listValue="stateName" headerKey="" headerValue="- Select -"
									cssStyle="padding: 3px 10px;" name="store.storeAddress.state" />
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
						<span class="required">*</span> Pincode : </label>
						<div class="col-md-8">
							<sj:textfield name="store.storeAddress.postalCode" id="postalCode"
								cssClass="required numeric form-control input-medium"
								maxlength="6"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			
					<div class="col-md-5">
					&nbsp;
				</div>
			
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit cssClass="submitBt btn blue" value="Submit"  id="clickSubmit"
							indicator="indicator" validate="true" onBeforeTopics="submitFormValidation" targets="mainContentDiv" formIds="addNewStore" />							
							<s:if test='%{store.id != 0}'>
							 <button type="button" data-dismiss="modal" class="btn default"> Cancel </button>
							</s:if>
							<s:else>
							<s:url id="doViewStoreData" action="ajaxDoManageStores"
								includeParams="all" escapeAmp="false" namespace="/store">
							</s:url>							
							<sj:a href="%{doViewStoreData}" cssClass="btn default"
								indicator="indicator" targets="mainContentDiv">Cancel</sj:a>								
						 </s:else>						
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
	
	function enableStatesList(countryCode) {
		var countryId = countryCode;
		if (countryId == "IN") {
			$("#countryState").show();
		} else {
			$("#countryState").hide();
			$('#state').val('');
		}
	}
	
</script>