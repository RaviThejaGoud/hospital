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
<div class="block" style="width: 680px;margin: 0px auto;" id="paymentSubscription">
	<div class="block_head">
		<h2>
			Subscription Options
		</h2>
	</div>
	<div class="block_content" style="background-color: #ffffff;">
	<div id="subscribedProducts">
	</div>
		<s:iterator value="urtProductList">
			<div class="productList grid_10" style="width:600px;"id="<s:property value="id" />">
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
					<div style="float: right;width: 106px;" class="grid_3">
					<s:url id="urlAddCostLink" action="ajaxGetBundleProducts"
							includeParams="all" escapeAmp="false">
							<s:param name="id" value="{id}" />
						</s:url>
						<sj:a href="%{urlAddCostLink}"
							onCompleteTopics="doInitAddGroupMember"
							onBeforeTopics="cleanOpenRows" indicator="indicator"
							targets="subscribedProducts" cssClass="submit">Add to Bundle</sj:a>
					
					</div>
				</div>
			</div>

		</s:iterator>

	</div>
