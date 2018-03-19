<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{actualAmount != null && !actualAmount.isEmpty()}">
	<div class="grid_11">
		<div class="grid_4" style="margin: 0px;">
			Actual Amount :
		</div>
		<div class="grid_7" style="margin: 0px;">
			$<s:property value="actualAmount" />
		</div>
		<div>
			&nbsp;
		</div>
	</div>
</s:if>
<s:if test="%{discountAmount != null && !discountAmount.isEmpty()}">
	<div class="grid_11">
		<div class="grid_4" style="margin: 0px;">
			You get the Discount :
		</div>
		<div class="grid_7" style="margin: 0px;">
			$<s:property value="discountAmount" />
		</div>
		<div>
			&nbsp;
		</div>
	</div>
</s:if>
<div class="grid_11" >
	<div class="grid_4" style="margin: 0px;">
		Total Product(s) Cost :
	</div>
	<div class="grid_7" style="margin: 0px;">
		$<s:property value="amountWithoutTax" />
	</div>
	<div>
		&nbsp;
	</div>
</div>

<div class="grid_11">	
	<div class="grid_4" style="margin: 0px;">
		&nbsp;
	</div>
	<div class="grid_7" style="margin: 0px;">	
	<br/>
		<s:form action='ajaxExpresscheckout.do' method="post"
			namespace="/subscription" theme="css_xhtml">
			<s:hidden name="subscriptionType"/>
			<s:submit type="image" align="left"
				src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif"></s:submit>
		</s:form>
	</div>
</div>

