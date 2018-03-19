<%@ include file="/common/taglibs.jsp"%>
<title>Teacher | Class Exam Details</title>
<div class="block grid_12">
	<div class="block_head">
		<h2>
			Case Studies 
		</h2>
		 <ul>
				<!--<li>
				 <s:url id="urlDoAddCaseStudy" action="ajaxDoAddCaseStudy" />
					<sj:a id="urlDoAddCaseStudy" href="%{urlDoAddCaseStudy}"
						targets="caseStudy" indicator="indicator">Add Case Studies</sj:a>	
				</li>
				
			   --></ul>
	</div>
	<div class="block_content" id="caseStudy">
		<div>
			<jsp:include page="/WEB-INF/pages/staff/ajaxViewCaseStudy.jsp"></jsp:include>	
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Exam Schedules & Results");
</script>









