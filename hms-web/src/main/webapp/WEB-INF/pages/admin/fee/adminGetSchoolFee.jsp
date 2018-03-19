<%@ include file="/common/taglibs.jsp"%>
<head> 
<title> SMS | School Fee Details</title>
</head>
<div class="grid_11 alpha">
	<style>
div.odd {
	background-color: #F5F7F7;
	border-bottom: 1px solid #CCCCCC;
	border-top: 1px solid #CCCCCC;
	height: auto;
	width: 680px;
	padding: 10px 0px 0px 20px;
}

div.even {
	padding: 10px 0px 0px 20px;
}
</style>
	<div class="wrapper">
		<!-- wrapper begins -->
		<div class="grid_16 block grid_16MarginLeft">
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Finance
					</h2>

				</div>
				<div class="block_content" id="sideMenu" >
					<ul>
						<li class="active">
							<s:url id="urlManageFeeLink" action="ajaxAdminGetSchoolFee" />
							<sj:a id="manageFeeLink" href="%{urlManageFeeLink}"
								targets="studentFeeContent" indicator="indicator">Manage Fees</sj:a>
						
						</li>

						<!--<li >
							<s:url id="urlStudentFeeLink" action="ajaxManageStudentFee" />
							<sj:a id="studentFeeLink" href="%{urlStudentFeeLink}"
								targets="studentFeeContent" indicator="indicator">Student Fee Module</sj:a>
						</li>

						--><li >
							<s:url id="urlEditStudentFeeLink" action="ajaxEditStudentPaymentFee" />
							<sj:a id="editStudentFeeLink" href="%{urlEditStudentFeeLink}"
								targets="studentFeeContent" indicator="indicator">Fees Payment</sj:a>
						</li> 
						<!--<li>
							<s:url id="urlCreateStudentFeeReportLink" action="ajaxCreateStudentFeeReport" />
							<sj:a id="createStudentFeeReportLink" href="%{urlCreateStudentFeeReportLink}"
								targets="studentFeeContent" indicator="indicator">Create Student Fee Reports</sj:a>
						</li>
				--><!-- 
						<li >
							<a href="#">Create Student Fee Reports</a>
						</li> -->
					</ul>
				</div>
			</div>
				<div id="studentFeeContent">
							<jsp:include
								page="/WEB-INF/pages/admin/fee/ajaxGetSchoolFee.jsp" />
				</div>
				</div>
		</div>

	</div>
</div>