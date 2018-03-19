<%@ include file="/common/taglibs.jsp"%>
<title>Teacher | Class Exam Details</title>
<div class="block grid_12">
	<div class="block_head">
		<h2>
			My Class Material
		</h2>
		 <ul>
				<li>
				 <s:url id="urlDoAddMaterial" action="ajaxDoAddMaterial" />
							<sj:a id="urlDoAddMaterial" href="%{urlDoAddMaterial}"
								targets="addMaterial" indicator="indicator">Add Material</sj:a>	
				</li>
				
			   </ul>
	</div>
	<div class="block_content" id="addMaterial">
		<div>
			<jsp:include page="/WEB-INF/pages/staff/ajaxViewAddMaterial.jsp"></jsp:include>	
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Exam Schedules & Results");
</script>









