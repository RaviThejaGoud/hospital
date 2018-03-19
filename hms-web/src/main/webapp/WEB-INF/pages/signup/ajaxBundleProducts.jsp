<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	function noBack() {
		window.history.forward()
	}
	noBack();
	window.onload = noBack;
	window.onpageshow = function(evt) {
		if (evt.persisted)
			noBack()
	}
	window.onunload = function() {
		void (0)
	}
</script>
	<div id="subscribedProducts">
		<s:if
						test="%{urtProductList != null && !urtProductList.isEmpty()}">	
						<ul style="padding-left: 0px;">					
						<s:iterator value="urtProductList">
			<div class="grid_10"
				style="padding: 10px; margin: 0px 0px 10px 6px; border: 2px solid #ccc;width: 600px;" id="<s:property value="id" />">
				<div class="grid_2" style="padding: 0px; margin: 0px;">
					<img alt=""
						src="${pageContext.request.contextPath}/images/productImage.jpg" />
				</div>
				<div class="grid_8" style="padding: 0px; margin: 0px;width: 500px;">
					<h2 style="margin-bottom: 2px;">
						<s:property value="name" />
					</h2>
					Lorem Ipsum is simply dummy text of the printing and typesetting
					industry. Lorem Ipsum has been the industry's standard dummy text
					ever since the 1500s, when an unknown printer took a galley of type
					and scrambled it to make a type specimen book. It has survived not
					only five centuries, but also the leap into electronic typesetting,
					remaining essentially unchanged. It was popularised in the 1960s
					with the release of Letraset sheets containing Lorem Ipsum
					passages, and more recently with desktop publishing software like
					Aldus PageMaker including versions of Lorem Ipsum.
					<br />
					<s:iterator value="productPrice">
							<div class="productPriceDetails">
							<div class="grid_3 subscriptionType"  style="width: 130px;">
								<h2>$<s:property value="productCost" />/<s:property value="subscriptionTypes" /></h2>								
								</div>	
							<div class="grid_3 buyNow" style="width: 80px;">						
							<s:url id="urlAddCostLink" action="ajaxSignUpStep1" ></s:url>
							<s:property value="urtProductList.id"/>
							<s:param name="productId" value="{productId}"></s:param>
							<sj:a id="goalsLink"
							href="%{urlAddCostLink}" targets="metricsContent"
							indicator="indicator"
					cssClass="submit">Buy Now</sj:a>
								</div>
								</div>
						<div class="clear"></div>
					</s:iterator> 
				</div>
			</div>

		</s:iterator>
						</ul>
						</s:if>
	</div>
