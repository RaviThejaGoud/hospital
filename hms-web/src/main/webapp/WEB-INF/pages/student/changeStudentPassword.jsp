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
		<div class="block">
			<jsp:include page="class/manageMyClass.jsp"></jsp:include>
		</div>
			<div class="block" id="studentContent">
				<div class="block grid_9 alpha" id="studentHome">
					<jsp:include page="changeStudentPwd.jsp"></jsp:include>
				</div>
			</div>
			<div class="block grid_3" >
				<jsp:include page="upComingEvents.jsp"></jsp:include>
			</div>
	</div>