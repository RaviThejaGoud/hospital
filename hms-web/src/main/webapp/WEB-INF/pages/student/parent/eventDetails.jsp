<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>My Events</title>

</head>
<body />

	<div class="wrapper">
	<style type="text/css">
.subjects {
	border: 1px solid #ccc;
}

.subjects th {
	width: 150px;
	padding: 5px;
	background-color: #ccc;
	border-right: 1px solid #fff;
	text-align: left;
}

.subjects th.last {
	width: 150px;
	padding: 5px;
	background-color: #ccc;
	text-align: left;
}

.subjects td {
	width: 150px;
	padding: 5px;
	text-align: left;
	border: 1px solid #ccc;
}
</style>
		<!-- wrapper begins -->
		<div class="grid_16 block grid_16MarginLeft">
				<jsp:include page="parentLeftNav.jsp"></jsp:include>			
			<div class="grid_12 omega">
				<div class="block_head">
					<h2>
						Transport Details
					</h2>
				</div>
				<div class="block_content" id="metricsContent">
					<!-- <div class="grid_2 alpha">
						&nbsp;
					</div>
					<div class="grid_2 alpha">
						<img src="<c:url value='images/ram.png'/>" />
					</div> -->
					<div class="grid_11 alpha">
						<div class="grid_4 alpha">Route No:</div>
						<div class="grid_4 omega">1</div>
						<div class="grid_4 alpha">Pickup/Drop Point</div>
						<div class="grid_4 omega">AstaLakshmi Nagar</div>
						<div class="grid_4 alpha">Pickup Time</div>
						<div class="grid_4 omega">7:30 AM</div>
						<div class="grid_4 alpha">Drop Time</div>
						<div class="grid_4 omega">3:30 PM</div>
						<div class="grid_8 alpha"><b>&nbsp;</b></div>
						<div class="grid_8 alpha"><b>Route Details</b></div>
						<div class="grid_4 alpha">Starting Point</div>
						<div class="grid_4 omega">Vadapalani</div>
						<div class="grid_4 alpha">Starting Time</div>
						<div class="grid_4 omega">7:00 AM</div>						
						<div class="grid_4 alpha">Destination Point</div>
						<div class="grid_4 omega">School</div>
						<div class="grid_4 alpha">Reaching Time</div>
						<div class="grid_4 omega">8:50 AM</div>
					</div>
				</div>
			</div>
		</div>			
	</div>