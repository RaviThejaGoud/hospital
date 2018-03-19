<%@ include file="/common/taglibs.jsp"%>
<%
	String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z"};
%>
<%
	String[] color = {"Blue", "Blue"};
%>
<div style="width: 100%;">
	<div class="row" style="position: relative;margin-left: 41%;;">
		<s:if test="%{customer.customerLogo != null &&  customer.customerLogo != ''}">
			<img src='<c:url value="${customer.customerLogo}"/>'
				alt='<s:property  value="customer.customerLogo" />' height="50px"
				width="50px" border="0" style="float: left;" />&nbsp;
			   </s:if>
	</div>
	<div class="row" style="text-align: center;">
		<b class="orgHeader" style="font-size: 15px;"> <s:property value="customer.organization" /> </b>
		<br />
		<span class="receptFontSubHeader" style="font-size: 8px;"> 
			<s:if test="%{customer.address.streetName != null && customer.address.streetName != ''}">
				<b><s:property value="customer.address.streetName" />, </b>
			</s:if> 
			<s:if test="%{customer.address.addressLine1 != null && customer.address.addressLine1 != ''}">
				<b><s:property value="customer.address.addressLine1" /> </b>
			</s:if> 
			<s:if test="%{customer.address.city != null && customer.address.city != ''}">
				<b><s:property value="customer.address.city" /> -</b>
			</s:if> 
			<s:if test="%{customer.address.postalCode != null && customer.address.postalCode != ''}">
				<b><s:property value="customer.address.postalCode" /> ,</b>
			</s:if>
			<s:if test="%{customer.contactNumber != null && customer.contactNumber != ''}">
				<b>Phone: <s:property value="customer.contactNumber" />, </b>
			</s:if> 
			<s:if test="%{customer.mobileNumber != null && customer.mobileNumber != ''}">
				<b><s:property value="customer.mobileNumber" /> .</b>
			</s:if> </span>
		<br/>
		<b style="text-align: center; font-size: 9px;">FEE CHALLAN </b>
	</div>
</div>
<hr style="margin: 2px;" />

<table style="font-size: 12px; margin-left: 40px;" width="95%">	
	<tr>
		<td style="text-align: left ;width: 50%;"> Challan Number :
		 <b><s:property value="anyId" /> </b></td>
		<td style="text-align: left;width: 45%;">Date :<b>&nbsp; </b></td>
	</tr>
	<tr>
		<td colspan="2">Please accept fee and credit to our School A/C No <b><s:property
					value="bankAccountDetailsVO.accountNumber" /> </b></td>
	</tr>
</table>
	<div class="row">
		<div class="col-xs-12">
			<table class="table table-striped table-bordered table-hover table-full-width" style="font-size: 11px;width: 80%;margin-left: 40px;text-align: -moz-center">
				<thead>
					<tr>
						<th width="30%">Notes</th>
						<th width="35%">Rs.</th>
						<th width="15%">Ps.</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="30%" style="text-align: center;">2000 X</td>
						<td width="35%">&nbsp;</td>
						<td width="15%">&nbsp;</td>
					</tr>
					<tr>
						<td width="30%" style="text-align: center;">500 X</td>
						<td width="35%">&nbsp;</td>
						<td width="15%">&nbsp;</td>
					</tr>
					<tr>
						<td width="30%" style="text-align: center;">100 X</td>
						<td width="35%">&nbsp;</td>
						<td width="15%">&nbsp;</td>
					</tr>
					<tr>
						<td width="30%" style="text-align: center;">50 X</td>
						<td width="35%">&nbsp;</td>
						<td width="15%">&nbsp;</td>
					</tr>
					<tr>
						<td width="30%" style="text-align: center;">20 X</td>
						<td width="35%">&nbsp;</td>
						<td width="15%">&nbsp;</td>
					</tr>
					<tr>
						<td width="30%" style="text-align: center;">10 X</td>
						<td width="35%">&nbsp;</td>
						<td width="15%">&nbsp;</td>
					</tr>
					<tr>
						<td width="30%" style="text-align: center;">5 X</td>
						<td width="35%">&nbsp;</td>
						<td width="15%">&nbsp;</td>
					</tr>
					<tr>
						<td width="30%" style="text-align: center;">Coins</td>
						<td width="35%">&nbsp;</td>
						<td width="15%">&nbsp;</td>
					</tr>
					<tr>
						<td width="30%" style="text-align: center;">TOTAL</td>
						<td width="35%">&nbsp;</td>
						<td width="15%">&nbsp;</td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>
	<table style="width: 95%;font-size: 10px;margin-left: 10px;">
		<tr><td colspan="4">&nbsp;</td></tr>
					<tr>
						<td style="width: 3%;">By</td>
						<td style="text-align: center; border-bottom: 1px solid;width: 45%;text-transform: uppercase;"><s:property value="viewStudentClassDetails.fatherName"/></td>
						<td  style="width: 5%;"> F/O</td>
						<td style="text-align:center; border-bottom: 1px solid;width: 40%;text-transform: uppercase;"> <s:property value="viewStudentClassDetails.fullName"/></td>
					</tr>
				</table>
	<table style="width: 95%;font-size: 10px;margin-left: 10px;">
		<%-- <tr><td colspan="4">&nbsp;</td></tr>
		<tr style="width: 100%;">
			<td style="width: 100%;">
				<table style="width: 100%;font-size: 10px;">
					<tr>
						<td >By</td>
						<td style="text-align: center; border-bottom: 1px solid;text-transform: uppercase;"><s:property value="viewStudentClassDetails.fatherName"/></td>
						<td  > F/O</td>
						<td style="text-align:center; border-bottom: 1px solid;text-transform: uppercase;"> <s:property value="viewStudentClassDetails.fullName"/></td>
					</tr>
				</table>
			</td>
			<td style="width: 5%;">By</td>
			<td style="text-align: center; border-bottom: 1px solid;width: 45%;text-transform: uppercase;"><s:property value="viewStudentClassDetails.fatherName"/></td>
			<td  style="width: 5%;"> F/O</td>
			<td style="text-align:center; border-bottom: 1px solid;width: 40%;text-transform: uppercase;"> <s:property value="viewStudentClassDetails.fullName"/></td>
		</tr> --%>
		
		<tr>
			<td style="width: 100%;text-align: center;" colspan="4" >(Name in Block Letters)</td>
			
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td style="text-align: center; border-bottom: 1px solid;" colspan="4"></td>
			
		</tr>
		<tr>
			<td style="width: 100%;text-align: center;" colspan="4" >(Signature of Remitter)</td>
			
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr style="font-size: 12px;">
			<td style="text-align: center; border-bottom: 1px solid;" colspan="4"></td>
			
		</tr>
		<tr>
			<td style="width: 100%;text-align: center;" colspan="4" >To be retained at the Bank</td>
			
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr style="font-size: 12px;">
			<td style="text-align: center; border-bottom: 1px solid;" colspan="4"></td>
			
		</tr>
		<!-- <tr><td colspan="4">&nbsp;</td></tr> -->
		<tr>
			<td style="width: 3%;" colspan="4">To,</td>
		</tr>
		<tr>
			<td style="width: 15%;" colspan="4">The Manager</td>
		</tr>
		<tr>
			<td style="width: 75%;" colspan="4"><b>KARNATAKA BANK,</b> Varthur Branch, Bengaluru,</td>
		</tr>
		<tr>
			<td style="width: 15%;" colspan="4">Dear Sir</td>
		</tr>
		</table>
		<table style="width: 95%;font-size: 10px;margin-left: 10px;">
		<tr>
			<td style="width: 20%;" >Please accept Rs.</td>
			<td style="width: 80%;text-align: left; border-bottom: 1px solid;" colspan="3"><b><s:property value="country.countryCurrency.currencySymbol" escapeHtml="false"/> <s:property value="totalAmount"/>/-</b></td>
		</tr>
		<tr>
			<td style="width: 10%;" colspan="4"><s:property value="country.countryCurrency.currencyWord" escapeHtml="false"/> (in words) :</td>

		</tr>
		<tr>
			
			<td style="text-align: left; border-bottom: 1px solid;" colspan="4"><b><s:if test="%{totalAmount > 0}">
					<s:property value="plTitle" />
				</s:if>
				<s:else>
					Zero <s:property value="country.countryCurrency.currencyWord" escapeHtml="false"/> only
				</s:else></b></td>
		</tr>
		<tr><td colspan="4">&nbsp;</td></tr><tr><td colspan="4">&nbsp;</td></tr><tr><td colspan="4">&nbsp;</td></tr><!-- <tr><td colspan="4">&nbsp;</td></tr> -->
		<tr style="font-size: 12px;">
			<td style="text-align: center; border-bottom: 1px solid;" colspan="4"></td>
			
		</tr>
		<tr><td colspan="4">&nbsp;</td></tr><!-- <tr><td colspan="4">&nbsp;</td></tr> -->
		<tr>
			<td style="width: 75%;text-align: center;" colspan="4">Being the amount of fees payable to</td>
		</tr>
		<tr>
			<td style="width: 75%;text-align: center;" colspan="4"><b><s:property value="customer.organization" /></b></td>
		</tr>
		<tr>
			<td style="width: 75%;text-align: center;" colspan="4">Space For Bank Seal And Challan No :<b> <s:property value="anyId" /> </b></td>
		</tr>
		<tr>
			<td style="width: 75%;text-align: center;" colspan="4"><textarea rows="7" cols="55" disabled="disabled"></textarea></td>
		</tr>
	</table>
<style type="text/css">
.test1 {
	width: 25em;
	border: 0px solid #000000;
	word-break: keep-all;
}

.test2 {
	border: 0 solid #000000;
	width: 24em;
	word-break: normal;
}

.smallFonts {
	font-size: 10px;
	margin: 5px;
}

.header {
	width: 100%;
	font-size: 9px;
	padding-right: 3px;
}

.challanDiv {
	text-align: left;
	font-size: 10px;
	width: 20%;
}

@media print {
	a[href]:after {
		content: none !important;
	}
	img[src]:after {
		content: none !important;
	}
	.challanDiv {
		text-align: left;
		font-size: 10px;
		width: 20%;
	}
	.header {
		width: 100%;
		font-size: 9px;
		padding-right: 3px;
	}
}
</style>

<script type="text/javascript">
	for (var j = 0; j < 3; j++) {
		var x = document.getElementsByClassName("tabTotalTDClass")[j];

		var len = x.rows.length;
		var loopLength = 11 - len;
		for (var i = 0; i <= loopLength; i++) {

			var new_row = x.rows[1].cloneNode(true);
			var tdData = new_row.getElementsByTagName("td")[0];
			var tdData1 = new_row.getElementsByTagName("td")[1];
			tdData.innerHTML = "";
			tdData1.innerHTML = "";
			x.appendChild(new_row);
		}

		/* for(var k=0;k<=3;k++){
		 var y=document.getElementsByClassName("footerTable")[k];
		 var footer_row = y.rows[1].cloneNode(true);
		 var tdFooter1=footer_row.getElementsByTagName("td")[0];
		 var tdFooter2=footer_row.getElementsByTagName("td")[1];	
		 tdFooter1.innerHTML="Ganesh";
		 tdFooter2.innerHTML="Cherivi";	
		 y.appendChild( footer_row );	
		 } */
	}
</script>