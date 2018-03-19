<%@ include file="/common/taglibs.jsp"%>
<div id="ajaxResponseDiv">
	<div class="grid_12">
		<div class="grid_4 type-text">
			Selected Product Name :
		</div>
		<div class="grid_7">
		<sj:textfield id="urtproduct" name="urtProduct.name" readonly="true"></sj:textfield>
		</div>
	</div>
	<div class="grid_12">
	&nbsp;
	</div>
	<div class="grid_12">
		<div class="grid_4">
			Select Subscription Type :
		</div>
		<div class="grid_7">
			<s:select list="#{'Y':'Yearly','M':'Monthly'}"
				label="Select Resource" labelSeparator="yes" labelposition="no"
				name="selectedResourceId" headerKey="" headerValue="- Select -"
				requiredposition="first" theme="simple"
				onchange="javascript:changeSubscription(this.value,this.id);"
				cssStyle="width:200px; margin: 0px 0px 16px 0px;">
			</s:select>
		</div>
	</div>
	<div id="selectedProductCost" class="grid_12">

		</div>
		<div class="grid_12">
	&nbsp;
	</div>
	<div class="grid_12">
		<div class="grid_7" id="yearlySubscription">
			<s:form action='addSubscription' method="post"
				namespace="/signup" theme="css_xhtml">
				<input type="hidden" name="subscriptionType" value="Y" />
				<input id="params" type="hidden" name="productId" />
				<s:submit type="image" align="left"
					src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif"></s:submit>
			</s:form>
		</div>
		<div class="grid_7" id="monthlySubscription" style="display: none;">
			<s:form action='addSubscription' method="post"
				namespace="/signup" theme="css_xhtml">
				<input type="hidden" name="subscriptionType" value="M" />
				<input id="params" type="hidden" name="productId" />
				<s:submit type="image" align="left"
					src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif"></s:submit>
			</s:form>
		</div>
	</div>
</div>
<script type="text/javascript">
	function changeSubscription(type) {
	var signUpURL = jQuery.url
				.getChatURL("/signup/ajaxAddProductCost.do");
	var id='<s:property value="urtProduct.id" />';
	var pars = "params=" + id + "&type=" + type;
		if (type == "Y") {
			$("#yearlySubscription").show();
			$("#monthlySubscription").hide();
		} else {
			$("#monthlySubscription").show();
			$("#yearlySubscription").hide();
		}
		$.ajax( {
			type : "POST",
			url : signUpURL,
			data: pars,
			cache : true,
			success : function(message) {
				$("#selectedProductCost").html(message);
			}
		});
	}
</script>