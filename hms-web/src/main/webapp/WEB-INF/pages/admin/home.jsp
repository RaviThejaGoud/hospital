<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>Login | Hyniva Consulting Services Application Management</title>
</head>
<body />
	<div class="wrapper">&nbsp; 
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
					<div class="block_content">
						<div class="tableactions">
							<label>
								Select Class Name :
							</label>
							<s:select list="classList" listKey="id"
								listValue="classAndSection" label="Select Resource"
								cssClass="required" required="true" labelSeparator="yes"
								labelposition="no" name="serviceId" headerKey="" onchange="javascript: getClassSubject(this.value);"
								headerValue="- Select -" requiredposition="first" theme="simple">
							</s:select>
						</div>
						<div id="classSubjects" style="overflow: scroll; display: none;">
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function getClassSubject(classId){
	var masterAdminURL = jQuery.url.getChatURL("/admin/ajaxClassSubjects.do");
	$.ajax( {
			type : "POST",
			url : masterAdminURL,
			data: "classId=" + classId,
			cache : true,
			success : function(message) {
				$("#classSubjects").html(message);
				$("#classSubjects").show();
			}
		});
	}
	</script>