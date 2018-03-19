<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>My Subjects</title>

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
				<jsp:include page="class/manageMyClass.jsp"></jsp:include>			
			<div class="grid_12 omega">
				<div class="block_head">
					<h2>
						My Subjects
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
						My Subjects
					</div>
					
					<div class="grid_11 alpha">
						<table class="subjects">
							<tr>
								<th style="width: 150px">
									Subject Name
								</th>
								<th  style="width: 150px">
									Teacher Name
								</th>								
							</tr>
							<tr>
								<td class="first">
									Telugu
								</td>
								<td>Prasad</td>
								</tr>
								<tr>
								<td class="first">
									Hindi
								</td>
								<td>Prasad</td>
								</tr>
								<tr>
								<td class="first">
									English
								</td>
								<td>Prasad</td>
								</tr>
								<tr>
								<td class="first">
									Maths
								</td>
								<td>Prasad</td>
								</tr>
								<tr>
								<td class="first">
									Physical Sciences
								</td>
								<td>Prasad</td>
								</tr>
								<tr>
								<td class="first">
									Biological Sciences
								</td>
								<td>Prasad</td>
								</tr>
								<tr>
								<td class="first">
									Social Studies
								</td>
								<td>Prasad</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		
		</div>			
	</div>