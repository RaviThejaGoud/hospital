<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>Login | Hyniva Consulting Services Application Management</title>

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
	<div class="wrapper">
		<!-- wrapper begins -->
		<div class="grid_16 block grid_16MarginLeft">
				<jsp:include page="adminLeftNav.jsp"></jsp:include>		
	<div id="schoolTable">	
			<div class="grid_12 omega">
			<div class="block_head">
				<div class="grid_7">
					<h2>
						Class Timetable
					</h2>
				</div>
				</div>
		<div class="block_content" id="metricsContent">
		<jsp:include page="ajaxTimeTable.jsp"></jsp:include>
		</div></div>
		</div>
		</div>
	</div>