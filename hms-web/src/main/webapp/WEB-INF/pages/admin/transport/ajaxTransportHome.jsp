<%@ include file="/common/taglibs.jsp"%>
		<div id="transportContent">
			<jsp:include page="/WEB-INF/pages/admin/transport/ajaxTransportRoute.jsp"></jsp:include>
		</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
	changePageTitle("Manage Route");
	$('#transport').addClass('current');
	$('#adminStaffAndStudent').addClass('current');
	function getUrlVars()
	{			 
	    var vars = [], hash;
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	    for(var i = 0; i < hashes.length; i++)
	    {
	        hash = hashes[i].split('=');
	        vars.push(hash[0]);
	        vars[hash[0]] = hash[1];
	    }
	    return vars;
	}
	$(document).ready(function(){ 
	 	if(getUrlVars()["trans"] == "true"){ 
	    	  $("a#expiryDates").click();
	   	}
		if(getUrlVars()["value"] == "vehicle"){ 
	    	$("a#maintenance").click();
		}       
	});
</script>