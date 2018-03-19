<%@ include file="/common/taglibs.jsp"%>

<style type="text/css">
@import url("${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css");
@import url("${pageContext.request.contextPath}/styles/newCss/pages/invoice.css");
/* @page{size:auto; margin-bottom:5mm;}
@media print 
{
  a[href]:after { content: none !important; }
  img[src]:after { content: none !important; }
} */
</style>
<%-- <s:if test='%{customer.feeReceiptModel=="General"}'>
 <div style="width: 100%; padding: 10px;">
	<div style="width: 49%; float: left; border: 1px solid">
		<jsp:include page="/WEB-INF/pages/admin/reports/feeReciept/generalFeeReciept.jsp"></jsp:include>
		<div style="text-align: center;width:160px;">
			<b>ADMINISTRATOR COPY</b>
		</div>
		<div style="float:right;width:282px;">
				Generated By:<a id="myAnchor" href="http://www.Eazyschool.com" target="_blank">www.Eazyschool.com</a>
		</div>
	</div>
	<div style="width: 49%;float: right;border: 1px solid;">
	    <jsp:include page="/WEB-INF/pages/admin/reports/feeReciept/generalFeeReciept.jsp"></jsp:include>
		<div style="text-align: center;width:130px;">
			<b>PARENT'S COPY</b>
		</div>
		<div style="float:right;width:282px;">
			Generated By:<a id="myAnchor" href="http://www.Eazyschool.com" target="_blank">www.Eazyschool.com</a>
		</div>
	</div>
	<div style="width: 51%;float: right;" id="pageToPrint">
	</div>
</div>
 </s:if> --%>


<style type="text/css">
	.pageHeader1{
	 margin: 10px 10px 20px 10px;
	}
	td label{
		margin-bottom: 0px;
	}
</style>
<div align="center">
	<page size='<s:property value="customer.feeReceiptModel"/>' id="pageToPrint">
		<div class="pageHeader1">
			<jsp:include page="/WEB-INF/pages/admin/reports/admissions/viewA4Reciept.jsp"></jsp:include>
			<div class="row">
				<div><b class="parentCopy">PARENT'S COPY</b> </div>
			</div>
			<div class="generatedBy">Generated By:<a id="myAnchor" href="http://www.Eazyschool.com" target="_blank">www.Eazyschool.com</a> </div>
		</div>
		<div style="padding-top: 1px;" class="dotted"></div>
		<div class="pageHeader1">
			<jsp:include page="/WEB-INF/pages/admin/reports/admissions/viewA4Reciept.jsp"></jsp:include>
			<div class="row">
				<div> <b class="parentCopy">SCHOOL COPY</b> </div>
			</div>
			<div class="generatedBy">Generated By:<a id="myAnchor" href="http://www.Eazyschool.com" target="_blank">www.Eazyschool.com</a> </div>
		</div>
	</page>
	</div>


 
<script  type="text/javascript">
var newElement = document.createElement("a");
var html="Print";
newElement.setAttribute('id', "printButton");
newElement.setAttribute('href', "javascript:window.print();");
newElement.innerHTML = html;
document.getElementById('pageToPrint').appendChild(newElement);
 
</script>
<style type="text/css">
page {
  background: white;
  display: block;
  margin: 0 auto;
  margin-bottom: 0.5cm;
  box-shadow: 0 0 0.5cm rgba(0,0,0,0.5);
}
page[size="A3"] {
  width: 25cm;
  height: 27cm;
}
page[size="A4"] {  
  width: 21cm;
  height: 22cm; 
}

page[size="A5"] {
  width: 14.8cm;
  height: 19cm;
}

page[size="A6"] {
  width: 10.5cm;
  height: 13.8cm;
}
page[size="A3"][layout="portrait"] {
  width: 42cm;
  height: 24cm;  
}
page[size="A4"][layout="portrait"] {
  width: 25cm;
  height: 21cm;  
}
page[size="A5"][layout="portrait"] {
  width: 21cm;
  height: 13.8cm;  
}

page[size="A6"] [layout="portrait"] {
  width: 52cm;
  height: 13.8cm;
}
.pageHeader1{
 border: 1px solid #222;
}
.table{
	margin-bottom:0px;
}
.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td
{
padding:0px;
}
span.receptFontSubHeaderRow{
	font-size: 12px;
	text-align: center;
	color:0000FF;
	float: right;
	width: 150px;
}
.reasonFont{
	font-size: 10px;
	 text-align: left;
	  padding: 0px 5px 0px 5px;
}
.parentCopy{
  color:#c0c5ce;
  text-align: center;
}
.generatedBy{
 float:right;
 font-size: 10px;
}
.schoolName{
	color:#006400;
}
.orgHeader{
	color:#006400;
	font-size: 16px;
}
hr {
    border-color: #222 -moz-use-text-color -moz-use-text-color;
    border-width: 1px 0 0;
}
div.dotted {
	border-top-style: dotted;
}
.discount{
	color:#ff8a9b;
	font-size: 12px;
}
 
</style>
<style type="text/css" media="print">
    @media print{
    a#printButton {
       display:none !important;
    }
    body:blank{
      display: none;
    }
    .pageToPrint+.pageToPrint {
       page-break-before: always;
    }
}
</style>



 