<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>Master Admin</title>
</head>

<body />
	<div id="demo" >
		<div id="dem_box">
			<div id="seriesAndSermonsList">
				<jsp:include page="/WEB-INF/pages/masterAdmin/customerDetails.jsp" />
			</div>
		</div>
	</div>
<script type="text/javascript">
function showInstanceCreation(){
document.getElementById('createInstance').style.display="block";	
}
</script>
