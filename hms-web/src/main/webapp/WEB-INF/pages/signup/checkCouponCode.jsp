<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="grid_4 type-text">
	Enter Coupon Code :
</div>
<div class="grid_7">
	<sj:textfield id="couponCodeVal" name="couponCodeVal"
		requiredposition="first" required="true" size="30" maxlength="20"
		cssClass="required text_right "
		onchange="javascript:checkCouponCode(this.value);" />
	<s:if test="%{validCheck != null && !validCheck.isEmpty()}">
		<font style="color: green; font-weight: bold">Accepted</font>
	</s:if>
</div>
