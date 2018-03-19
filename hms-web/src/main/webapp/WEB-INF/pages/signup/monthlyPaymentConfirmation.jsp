<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>URT Apps Signup</title>
</head>
<body onload="noBack()" onpageshow="if(event.persisted)noBack();"
	onunload="" />
	<div>
		<div class="wrapper block">
				<div class="block_head">
					<h2>
						Payment Confirmation
					</h2>
				</div>
				<!-- .block_head ends -->
				<div id="signupStep1" class="block_content" style="background-color: #fff;">
					<div class="grid_16">
						<div class="grid_2">
							&nbsp;
						</div>
					</div>
					<div class="grid_16">
						<div class="grid_9">
							<h2 style="float: left">
								Payment Confirmation
							</h2>
						</div>
					</div>
					<div class="grid_16">
						<div class="grid_2">
							&nbsp;
						</div>
					</div>
					<div class="grid_16">
						<div class="grid_2">
							&nbsp;
						</div>
					</div>
					<div class="grid_16">
					 <s:iterator value="urtProductList">
							<div class="grid_9" align="left">
								<h3 style="font-size: 17px">
									<s:property value="name"/>  Subscription - Monthly
								</h3>
							</div>
							<div class="grid_6">
								<h3 style="font-size: 17px;" >
									$
									<s:property value="priceWithTax" />
								</h3>
							</div>
						</s:iterator>
					</div>
					<div class="grid_16">
						<div class="grid_9" align="left">
							<h3 style="font-size: 16px; color: #CCCCCC;">
								<i>(Renews on the <s:property value="renewdate" /> of each
									month until cancelled)</i>
							</h3>
						</div>
					</div>
					<div class="grid_16">
						&nbsp;
					</div>
					<div class="grid_16">
						<div class="grid_9">
							&nbsp;
						</div>
						<div class="grid_6">
							--------------------------------------------------------------------
						</div>
					</div>
					<div class="grid_16">
						<div class="grid_9">
							&nbsp;
						</div>
						<div class="grid_4">
							<b style="font-size: 17px"> Total per Month </b>
						</div>
						<div class="grid_2">
							<h3 style="font-size: 17px" >
								$
								<s:property value="amountWithTax" />
							</h3>
						</div>
					</div>
					<div class="grid_16">
						&nbsp;
					</div>
					<div class="grid_16">
						<div class="grid_15">
							<p align="left">
								By clicking the Confirm button, you agree to pay UpperRoom
								Technologies the subscription price at the frequency stated
								above.
							<p>
						</div>
					</div>
					<div class="grid_16">
						&nbsp;
					</div>
					<div class="grid_16">
						<div class="grid_4" style="float: right;margin-right: 42px;">
							<s:form id="fieldError" action="ajaxOrderToConfirm" method="post"
								namespace="/signup" theme="css_xhtml">
									<sj:submit   targets="signupStep1" value="Confirm"
										cssClass="submit small" indicator="indicator"
										formIds="fieldError" onSuccessTopics="firstRegStep"  cssStyle="float:right"/>
								<img style="display: none;margin: 18px 0px 0px 17px;" alt="Loading..." src="${pageContext.request.contextPath}/images/indicator.gif" id="indicator">
								<a href="${pageContext.request.contextPath}/login.jsp"  class="cancelButton">Cancel</a>
							</s:form>
						</div>
					</div>
					<div class="grid_16">
						&nbsp;
					</div>
					<div class="grid_16">
						&nbsp;
					</div>
					<div class="grid_16">
						<div class="grid_2">
							&nbsp;
						</div>
					</div>
				</div>
			</div>
		</div>
	<script type="text/javascript">
	try {
		var pageTracker = _gat._getTracker("UA-3390449-50");
		pageTracker._trackPageview();
	} catch (err) {
	}
</script>