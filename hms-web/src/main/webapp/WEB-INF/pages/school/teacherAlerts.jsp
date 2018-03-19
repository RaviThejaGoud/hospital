<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>Teacher Home</title>

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
				<jsp:include page="teacherLeftNav.jsp"></jsp:include>			
			<div class="grid_9 omega">
				<div class="block_head">
					<h2>
						Teacher Details
					</h2>
				</div>
				<div class="block_content" id="metricsContent">
					<div class="grid_8 alpha">
						Today's Timetable
					</div>
					<div class="grid_8 alpha">
						<table class="timeTable">
							<tr>
								<th>
									Period
								</th>
								<th>
									1
								</th>
								<th>
									2
								</th>
								<th>
									3
								</th>
								<th>
									4
								</th>
								<th>
									5
								</th>
								<th>
									6
								</th>
								<th>
									7
								</th>
								<th>
									8
								</th>
							</tr>
							<tr>
								<td>
									Teacher
								</td>
								<td>
									Telugu
								</td>
								<td>
									Hindi
								</td>
								<td>
									English
								</td>
								<td>
									Maths
								</td>
								<td>
									PS
								</td>
								<td>
									BS
								</td>
								<td>
									Social
								</td>
								<td>
									Maths
								</td>
							</tr>
							<tr>
								<td style="width: 50px; padding: 5px; vertical-align: middle;">
									Subject
								</td>
								<td>
									<img src="<c:url value='images/ramThumb.png'/>"
										style="border: 1px solid #FF7300" />
								</td>
								<td>
									<img src="<c:url value='images/ramThumb.png'/>"
										style="border: 1px solid #FF7300" />
								</td>
								<td>
									<img src="<c:url value='images/ramThumb.png'/>"
										style="border: 1px solid #FF7300" />
								</td>
								<td>
									<img src="<c:url value='images/ramThumb.png'/>"
										style="border: 1px solid #FF7300" />
								</td>
								<td>
									<img src="<c:url value='images/ramThumb.png'/>"
										style="border: 1px solid #FF7300" />
								</td>
								<td>
									<img src="<c:url value='images/ramThumb.png'/>"
										style="border: 1px solid #FF7300" />
								</td>
								<td>
									<img src="<c:url value='images/ramThumb.png'/>"
										style="border: 1px solid #FF7300" />
								</td>
								<td>
									<img src="<c:url value='images/ramThumb.png'/>"
										style="border: 1px solid #FF7300" />
								</td>
							</tr>

						</table>
					</div>
				</div>
			</div>
			<jsp:include page="studentRightNav.jsp"></jsp:include>		
		</div>
	</div>