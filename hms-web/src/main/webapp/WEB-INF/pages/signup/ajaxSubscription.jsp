<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	function noBack() {
		window.history.forward();
	}
	window.onload = noBack;
	window.onpageshow = function(evt) {
		if (evt.persisted)
			noBack();
	};
	window.onunload = function() {
		void (0);
	};
</script>
<div class="block" style="width: 750px; margin: 0px auto;">
	<div class="block_head">
		<h2>
			Subscription Options
		</h2>
	</div>
	<div class="block_content" style="background-color: #ffffff;">
		<div class="grid_11" style="text-align: left; margin-bottom: 10px;">
			<div class="grid_3 type-text" style="margin: 0px;">
				Select Product Name :
			</div>
			<div class="grid_3" style="margin: 0px;">
				<s:iterator value="urtProductList">
					<div class="grid_3" style="margin: 0px;">
						<input
							onclick="javascript:addcost(<s:property value='id'/>);getSubLevelDetails(<s:property value='id'/>)"
							id="singleProduct<s:property value='id'/>"
							value="<s:property value='id'/>" type="checkbox"
							name="productName" title="all">
						<s:property value="name" />
					</div>
					<div class="grid_3" id="sublevelResponse<s:property value='id'/>"></div>
				</s:iterator>
				<input onclick="javascript: addcost(this.value);" id="allproducts"
					value="all" type="checkbox" name="allproducts" title="all">
				all
			</div>
		</div>

		<s:if test="%{emailBody != null && !emailBody.isEmpty()}">
			<div class="grid_11" id="enterCouponCodeId">
				<jsp:include page="/WEB-INF/pages/signup/checkCouponCode.jsp" />
			</div>
		</s:if>
		<div class="grid_11">
			&nbsp;
		</div>
		<div class="grid_11">
			<div class="grid_4" style="margin: 0px;">
				Select Subscription Type :
			</div>
			<div class="grid_7" style="margin: 0px;">
				<s:select list="#{'Y':'Yearly','M':'Monthly'}" id="subsType"
					label="Select Resource" labelSeparator="yes" labelposition="no"
					name="selectedResourceId" headerKey="" headerValue="- Select -"
					requiredposition="first" theme="simple"
					onchange="javascript:changeSubscription(this.value);"
					cssStyle="width:200px; margin: 0px 0px 16px 0px;">
				</s:select>
			</div>
		</div>
		<div id="subscribeProducts"></div>
	</div>
</div>
<script type="text/javascript">

function frequencyChange(selectBox){
     	    var frequency=selectBox.value;
     	    if(frequency=='Y'){
     	      $("#enterCouponCodeId").show();
     	    }else if(frequency=='N'){
     	        $("#enterCouponCodeId").hide();
     	      }
     	}

	$("input:checkbox[name=allproducts]")
			.change(
					function() {
						var params = "";
						if ($(this).is(":checked")) {
							$('input:checkbox[name=allproducts]').attr(
									"checked", true);
							$('input:checkbox[name=productName]').attr(
									"checked", true);
							$('input:checkbox[name=productName]').attr(
									"disabled", true);							
							/*$("input:radio[@name='subscribeType']").attr('disabled', true);
							$("input:radio[name='subscribeTypeALL']").attr('disabled', false);	*/													
						} else {
							$('input:checkbox[name=allproducts]').attr(
									"checked", false);							
							$('input:checkbox[name=productName]').attr(
									"disabled", false);
							$('input:checkbox[name=productName]').attr(
									"checked", false);
							/*$("input:radio[@name='subscribeType']").attr('disabled', false);
							$("input:radio[name='subscribeTypeALL']").attr('disabled', false);	*/												
                         }
					});
					
function checkCouponCode(cCode){
	var productIds = document.getElementsByName("productName");	
	var pars="cCode=" + cCode;
		var params = "";
		var isSelected = false;
		if($("input:checkbox[name=allproducts]").is(":checked")){
		   params='all';
		   isSelected = true;
		}else{		
			for (i = 0; i < productIds.length; i++) {
				if (productIds[i].checked == true) {
					params += productIds[i].value;
					params += ",";
					isSelected = true;
				}
			}
		}		
		if(!isSelected){
		   alert('at least one Product must be selected');
		}else{
		$.ajax( {
			url : jQuery.url.getChatURL("/signup/ajaxCheckCouponCode.do"),
			cache : false,
			data : "prodIds=" + params + "&cCode=" + cCode ,
			success : function(html) {					   
			$("#enterCouponCodeId").html(html);
		  }
		});
	}
 }
	function changeSubscription(type) {
		var productIds = document.getElementsByName("productName");	
		var levelId = document.getElementsByName("levelName");
		var params = "";
		var levelparams="";
		var isSelected = false;
		if(type!=''){
			if($("input:checkbox[name=allproducts]").is(":checked")){
			   params='all';
			   isSelected = true;
			  // alert("prods=all&type="+type);
			}else{		
				for (i = 0; i < productIds.length; i++) {
					if (productIds[i].checked == true) {
						params += productIds[i].value;
						params += ",";
						isSelected = true;
					}
				}
				$('input:checkbox[name=allproducts]').attr("disabled",true);
				   for (i = 0; i < levelId.length; i++) {
					  if (levelId[i].checked == true) {
					    if(levelparams!=''){
					       levelparams += ",";		
					    }
						levelparams += levelId[i].value;
					  }
				   } 
			}		
			if(!isSelected){
			   alert('at least one Product must be selected');
			}else{
			if(levelparams){
				$.ajax( {
					type : "POST",
					url  : jQuery.url.getChatURL("/signup/ajaxAddProductCost.do"),
					data : "prods=" + params + "&levelparams=" + levelparams + "&type=" + type ,
					cache : true,
					success : function(message) {
						$("#subscribeProducts").html(message);
					}
			   });
			}
			else{
				$.ajax( {
					type : "POST",
					url  : jQuery.url.getChatURL("/signup/ajaxAddProductCost.do"),
					data : "prods=" + params + "&type=" + type ,
					cache : true,
					success : function(message) {
						$("#subscribeProducts").html(message);
					}
			   });
			}
			    
			}
	     }
	     else{
	    	 $("#subscribeProducts").html('');
	     }
		/*if (type == "Y") {
			$("#yearlySubscription").show();
			$("#monthlySubscription").hide();
		} else {
			$("#monthlySubscription").show();
			$("#yearlySubscription").hide();
		}*/
		
	}
	
	function getSubLevelDetails(productId){
	if($('#singleProduct'+productId).attr('checked')){
	    $("#sublevelResponse"+productId).show();
	    $.ajax( {
						type : "POST",
						url : jQuery.url.getChatURL("/signup/ajaxGetProductLevels.do"),
						data : "pId="+productId,
						cache : true,
						success : function(message) {
							$("#sublevelResponse"+productId).html(message);
						}
					});
	    }
	    else{
	    $("#sublevelResponse"+productId).hide();
	    }
	}
	
	function addinglevelPrice(levelId,productId){
		if($("input:radio[name=levelName]").is(":checked")){
		    addcost(productId);
		}
	}
	
	
	function addcost(productId) {
	    var subscribeType='';
	    var subscribeType=document.getElementById('subsType').value;
	   if(productId=='all'){
	       if($("input:checkbox[name=allproducts]").is(":checked")){
		      $('input:checkbox[name=productName]').attr("disabled",true);
		      $('input:checkbox[name=productName]').attr("checked",true);
		       if(subscribeType!=''){	   
			       $.ajax( {
						type : "POST",
						url : jQuery.url.getChatURL("/signup/ajaxAddProductCost.do"),
						data : "prods=all&type="+subscribeType,
						cache : true,
						success : function(message) {
							$("#subscribeProducts").html(message);
						}
					});	 
				}
				else{
				   $("#subscribeProducts").html('');
				}
			} 
			else{
			    $("#subscribeProducts").html(''); 
			    $('input:checkbox[name=productName]').attr("disabled",false);
		      $('input:checkbox[name=productName]').attr("checked",false); 
			}  
	    }
	    else{	    
	       var catId = document.getElementsByName("productName");
	       var levelId = document.getElementsByName("levelName");
	       var checked = $("input[name=productName]:checked").length;
           if (checked == 0)
           {
              $('input:checkbox[name=productName]').attr("disabled",false);
              $('input:checkbox[name=allproducts]').attr("disabled",false);
              $('input:checkbox[name=productName]').attr("checked",false);
              $('input:checkbox[name=allproducts]').attr("checked",false);            
              $("#subscribeProducts").html('');             
           }else{               
               $('input:checkbox[name=allproducts]').attr("disabled",true);
              // $("input:radio[name='subscribeTypeALL']").attr('disabled', true);
               var params = "";	
               var levelparams = "";	   
			   for (i = 0; i < catId.length; i++) {
				  if (catId[i].checked == true) {
				    if(params!=''){
				       params += ",";		
				    }
					params += catId[i].value;
				  }
			   } 	
			   for (i = 0; i < levelId.length; i++) {
				  if (levelId[i].checked == true) {
				    if(levelparams!=''){
				       levelparams += ",";		
				    }
					levelparams += levelId[i].value;
				  }
			   } 
	       		if(subscribeType!=''){
	       		if(levelparams){
	       		 $.ajax( {
						type : "POST",
						url : jQuery.url.getChatURL("/signup/ajaxAddProductCost.do"),
						data : "prods="+params+"&levelparams="+levelparams+"&type="+subscribeType, 
						cache : true,
						success : function(message) {
							$("#subscribeProducts").html(message);
						}
					});	 
	       		}	
	       		else{
	       			$.ajax( {
						type : "POST",
						url : jQuery.url.getChatURL("/signup/ajaxAddProductCost.do"),
						data : "prods="+params+"&type="+subscribeType, 
						cache : true,
						success : function(message) {
							$("#subscribeProducts").html(message);
						}
					});	
	       		}
			         
				}
				else{
				   $("#subscribeProducts").html('');
				}
			  // alert("prods=" + params+"&type="+subscribeType);
           } 	
		   
	    }
	}
</script>
