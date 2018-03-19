<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>Login | Hyniva Consulting Services Application Management</title>

</head>
<body />

	<div class="wrapper">
	<style type="text/css">
.timeTable {
	border: 1px solid #ccc;
}

.timeTable th {
	width: 50px;
	padding: 5px;
	background-color: #ccc;
	border-right: 1px solid #fff;
	text-align: center;
}

.timeTable th.last {
	width: 50px;
	padding: 5px;
	background-color: #ccc;
	text-align: center;
}

.timeTable td {
	width: 50px;
	padding: 5px;
	text-align: center;
}
</style>
		<!-- wrapper begins -->
		<div class="grid_16 block grid_16MarginLeft">
				<jsp:include page="/WEB-INF/pages/transport/transportLeftNav.jsp"></jsp:include>			
			<div class="grid_12 omega" id="transportContent">
			<jsp:include page="/WEB-INF/pages/transport/ajaxTransportMainPage.jsp"></jsp:include>
			</div>
		</div>
	</div>