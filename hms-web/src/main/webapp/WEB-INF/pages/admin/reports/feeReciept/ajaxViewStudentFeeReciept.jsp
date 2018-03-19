<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
@import url("${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css");
@import url("${pageContext.request.contextPath}/styles/newCss/pages/invoice.css");

.first {
	float: left;
	font-size: 14px;
	font-style: normal;
	font-variant: normal;
	font-weight: 500;
	font-family: "Lucida Calligraphy";
	padding-right: 15px;
}

.list:after {
	content: '';
	border-bottom: dotted 2px tomato;
	display: block;
	overflow: hidden;
	height: 0.8em;
}

page {
	background: white;
	display: block;
	margin: 0 auto;
	margin-bottom: 0.5cm;
	box-shadow: 0 0 0.5cm rgba(0, 0, 0, 0.5);
}

page[size="A4"] {
	width: 21cm;
	height: 22cm;
}

.ttl-amts {
	padding-right: 80px;
	text-align: right;
}

.lineBorder {
	border-bottom: 1px solid #222;
}

.input-group-lg>.form-control, .input-group-lg>.input-group-addon,
	.input-group-lg>.input-group-btn>.btn {
	border-radius: 0px;
	font-size: 13px;
	height: 40px;
	line-height: 0.33;
	padding: 0 10px;
}

p {
	margin-bottom: 30px;
}

*, *::before, *::after {
	text-align: left;
}

a#printButton {
	margin-left: 340px;
}

@media print {
	a#printButton {
		display: none !important;
	}
	body:blank {
		display: none;
	}
	.pageToPrint+.pageToPrint {
		page-break-before: always;
	}
}
</style>
<page size='A4' id="pageToPrint">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div>&nbsp;</div>
<div class="invoiceMain invoice">
	<div class="row">
		<div class="col-xs-7">
			<s:if test="%{customer.customerLogo != null &&  customer.customerLogo != ''}">
				<img src='<c:url value="${customer.customerLogo}"/>' alt='<s:property  value="customer.customerLogo" />' 
					height="83px" width="150px" border="0" style="float: left;" />&nbsp;
			 </s:if>
		</div>
		<div class="col-xs-5 invoice-payment">
			<b> <s:property value="customer.organization" />
			</b> <br>
			<s:if test="%{customer.address.streetName != null && customer.address.streetName != ''}">
				<b><s:property value="customer.address.streetName" />, </b>
			</s:if>
			<s:if test="%{customer.address.addressLine1 != null && customer.address.addressLine1 != ''}">
				<b><s:property value="customer.address.addressLine1" /> </b>
			</s:if>
			<br />
			<s:if test="%{customer.address.city != null && customer.address.city != ''}">
				<b><s:property value="customer.address.city" /> -</b>
			</s:if>
			<s:if test="%{customer.address.postalCode != null && customer.address.postalCode != ''}">
				<b><s:property value="customer.address.postalCode" /> ,</b>
			</s:if>
			<br />
			<s:if test="%{customer.contactNumber != null && customer.contactNumber != ''}">
				<b>Phone: <s:property value="customer.contactNumber" />,
				</b>
			</s:if>
			<s:if test="%{customer.mobileNumber != null && customer.mobileNumber != ''}">
				<b><s:property value="customer.mobileNumber" /> .</b>
			</s:if>
		</div>
	</div>
	<div class="row">&nbsp;</div>
	<div class="row">
		<div class="col-xs-7"></div>
		<div class="col-xs-5">
			<h4>
				Date &nbsp;&nbsp;:&nbsp;<b><s:property value="anyTitle" /></b>
			</h4>
			<h4>
				Receipt No# :
				<s:if test="%{customer.feeReceiptNoWithCustName != null &&  customer.feeReceiptNoWithCustName != ''}">
					<b>
						<s:property value="customer.feeReceiptNoWithCustName.toUpperCase()" /> &nbsp;/&nbsp;<s:property value="anyId" /> </b>
				</s:if>
				<s:elseif test='%{academicYear.receiptGenerationType=="M"}'>
					<b><s:property value="alertSendType" /> </b>
				</s:elseif>
				<s:else>
					<b><s:property value="anyId" /> </b>
				</s:else>
			</h4>
		</div>
	</div>
	<div class="row">
		<h3 class="receiptHeaer" style="text-transform: uppercase;">Receipt</h3>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<p>
				<i class='first'>Recived From </i>
					<span style="border-bottom: 1px solid; content: ''; display: block; height: 1.4em; overflow: hidden; width: 83%;text-align: center;">
						<b><s:property value="viewStudentClassDetails.fatherName" /></b>
					</span>
			</p>
			<p>
				<i class='first'>The sum of </i> 
					<span style="border-bottom: 1px solid; content: ''; display: block; height: 1.4em; overflow: hidden; width: 85%;text-align: center;">
						<b> <s:property value="roomId" /> </b>
					</span>
			</p>
			<p>
				<span style='overflow: hidden; display: block; content: ""; border-bottom: 1px solid; height: 1.2em; float: left; width: 44%;'></span>
				<span style='float: left; width: 8%;' class='first'>Naira</span>
				<span style='display: block; content: ""; border-bottom: 1px solid; overflow: hidden; height: 1.2em; width: 42%; float: left;text-align: center;'>
					<b><s:property value="eventId" /></b> 
				</span>
				<span style='float: left; width: 6%;' class='first'>Kobo</span>
			</p>
			<div style="padding-top: 50px; padding-bottom: 60px;">
				<span style="float: left; width: 210px;">
					<div class="input-group input-group-lg bootstrap-touchspin col-md-12">
						<span class="input-group-btn" style="background-color: #222;  border: 1px solid; font-size: 20px"><font color="#FFFFFF">&#8358;</font></span>
						<input type="text" class="form-control input-meduem disabled" style="width: 100px; float: left;" value='<s:property value="roomId"/>' disabled="disabled">
						<input type="text" class="form-control disabled" style="width: 45px; float: left;" value='<s:property value="eventId"/>' disabled="disabled"> 
						<span class="input-group-btn" style="background-color: #222;  border: 1px solid;font-size: 20px;float: left;width: 18px;height: 39px;"><font color="#FFFFFF">K</font></span>
					</div>
				</span> 
				<span style='float: left; width: 23%; margin-top: 23px;' class='first'>&nbsp;Teller/Cheque No</span> 
				<span style="border-bottom: 1px solid; content: ''; display: block; height: 1.4em; overflow: hidden; width: 47%; float: left; margin-top: 23px;">
					<b>
						<s:if test="%{studentPaymentList != null && !studentPaymentList.isEmpty()}">
							<s:set name="banksId" value=""></s:set>
							<s:iterator value="studentPaymentList">
								<s:if test="%{bankId != #banksId}">
									<s:property value="chequeNumber" />
								</s:if>
								<s:set name="banksId" value="%{bankId}"></s:set>
							</s:iterator>
						</s:if>
					</b>
				</span>
			</div>
			<br>
			<p>
				<i class='first'>Being part/complete payment for </i> 
				<span style="border-bottom: 1px solid; content: ''; display: block; height: auto; overflow: hidden; width: 63%; float: left;">
					<b><s:property value="queryString"/></b>
					<%-- <s:set name="stufeeTypeId" value=""></s:set> 
					<s:if test="%{studentPaymentList!= null || studentPaymentList!= null}">
						<s:iterator value="studentPaymentList">
							<s:if test="%{feeTypeId != #stufeeTypeId}">
								<b><s:property value="feeType" /> ,</b>
							</s:if>
							<s:set name="stufeeTypeId" value="%{feeTypeId}"></s:set>
						</s:iterator>
					</s:if> --%>
				</span>
			</p>
			<br />
			<p>
				<i class='first'>Class </i>
				<span style="border-bottom: 1px solid; content: ''; display: block; overflow: hidden; width: 92%; float: left;text-align: center;">
					<b><s:property value="viewStudentClassDetails.classAndSection" /></b></span>
			</p>
			<br />
			
			<div style="width: 100%;">
				<div style="width: 50%;float: left;">
						<span style="float: left;margin-left: -8px;width: 17%;" class="first">Balance&nbsp;</span>
							<s:if test="%{bedId > 0}">
									<div class="input-group input-group-lg bootstrap-touchspin">
										<span class="input-group-btn" style="background-color: #222; border: 1px solid; font-size: 20px"><font color="#FFFFFF">&#8358;</font></span>
										<input type="text" class="form-control input-meduem disabled" style="width: 100px; float: left;" value='<s:property value="bedId"/>' disabled="disabled"> 
										<input type="text" class="form-control disabled" style="width: 45px; float: left;" value='<s:property value="subjectId"/>' disabled="disabled"> 
										<span class="input-group-btn" style="background-color: #222;  border: 1px solid; font-size: 20px;float: left;width: 18px;height: 39px;"><font color="#FFFFFF">K</font></span><span class="first">&nbsp;For the term</span>
									</div>
							</s:if>
							<s:else>
									<div class="input-group input-group-lg bootstrap-touchspin">
										<span class="input-group-btn" style="background-color: #222;  border: 1px solid; font-size: 20px;font-style: "><font color="#FFFFFF">&#8358;</font></span>
										<input type="text" class="form-control input-meduem disabled" style="width: 100px; float: left;" value="0" disabled="disabled"> 
										<input type="text" class="form-control disabled" style="width: 45px; float: left;" value="0" disabled="disabled"> 
										<span class="input-group-btn" style="background-color: #222;  border: 1px solid; font-size: 20px;float: left;width: 18px;height: 39px;"><font color="#FFFFFF">K</font></span><span class="first">&nbsp;For the term</span>
									</div>
							</s:else>
				</div>
				<div style="width: 50%;float: right;">
							<s:if test="%{tempString2 > 0}">
							<span style="float: left;">
								<div class="input-group input-group-lg bootstrap-touchspin ">
									<span class="input-group-btn" style="background-color: #222; border: 1px solid; font-size: 20px"><font color="#FFFFFF">&#8358;</font></span>
									<input type="text" class="form-control input-meduem disabled" style="width: 100px; float: left;" value='<s:property value="tempString2"/>' disabled="disabled"> 
									<input type="text" class="form-control disabled" style="width: 45px; float: left;" value='<s:property value="subject"/>' disabled="disabled"> 
									<span class="input-group-btn" style="background-color: #222;  border: 1px solid; font-size: 20px;float: left;width: 18px;height: 39px;"><font color="#FFFFFF">K</font></span><span class="first">&nbsp;For the year</span>
								</div>
							</span>
						</s:if>
						<s:else>
							<span style="float: left;">
								<div class="input-group input-group-lg bootstrap-touchspin ">
									<span class="input-group-btn" style="background-color: #222;  border: 1px solid; font-size: 20px;font-style: "><font color="#FFFFFF">&#8358;</font></span>
									<input type="text" class="form-control input-meduem disabled" style="width: 100px; float: left;" value="0" disabled="disabled"> 
									<input type="text" class="form-control disabled" style="width: 45px; float: left;" value="0" disabled="disabled"> 
									<span class="input-group-btn" style="background-color: #222;  border: 1px solid; font-size: 20px;float: left;width: 18px;height: 39px;"><font color="#FFFFFF">K</font></span><span class="first">&nbsp;For the year</span>
								</div>
							</span>
						</s:else>
					</div>
			</div>
			<div class="col-md-12" style="padding-left: 0px;">&nbsp;</div>
			<div class="col-md-12" style="float: right; text-align: right;">
				<h4> <b style="float: right; border-top: solid black 2px;">For: EarlyStrides Preparatory School</b> </h4>
			</div>
			<br/><br/><br/>
			<div class="col-md-12" style="margin-top: 30px;font-size: 15px; text-align:center;font-style: italic;"> <b>PAYMENTS MADE ARE NEITHER REFUNDABLE NOR TRANSFERABLE</b></div>
		</div>
	</div>
</div>
</page>
<script type="text/javascript">
	var newElement = document.createElement("a");
	var html = "Print";
	newElement.setAttribute('id', "printButton");
	newElement.setAttribute('href', "javascript:window.print();");
	newElement.innerHTML = html;
	document.getElementById('pageToPrint').appendChild(newElement);
</script>