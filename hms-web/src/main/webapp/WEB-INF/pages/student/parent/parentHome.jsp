<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>Student Home</title>

</head>
<body />

	
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
<div class="wrapper container_16">
		<div class="block grid_4">
			<jsp:include page="parentLeftNav.jsp"></jsp:include>
		</div>
		<div id="schoolTable">	
		<div class="block_content" id="studentContent">
		<jsp:include page="home.jsp"></jsp:include>
		</div></div>
	</div>