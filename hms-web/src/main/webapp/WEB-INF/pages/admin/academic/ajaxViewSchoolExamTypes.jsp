<%@ include file="/common/taglibs.jsp"%>
<div class="grid_12">
	<div class="block_head">
	<h2>
			View Examination Types
		</h2>
	  <ul style="float: right;">
	   <li>
	     <s:url id="doCancelSchoolSettings" action="ajaxSchoolSettings"
		includeParams="all"></s:url>
	    <sj:a href="%{doCancelSchoolSettings}" 
		indicator="indicator" targets="academicsContent" button="false">Back To Settings</sj:a>
		  </li>
		</ul>
	</div>
	<div class="block_content" id="schoolViewExamTypes">
		<!--<jsp:include
				page="/WEB-INF/pages/admin/academic/ajaxViewSchoolExamTypesList.jsp"></jsp:include>
			--></div>
	        </div>
         <script type="text/javascript">
			document.title = 'Eazy School | View Exam Types';
			$.subscribe('ajaxViewExamTypes', function(event, data) {
				if ($('#' + data.id).is(":hidden")) {
					$('#' + data.id).show();
				} else {
					$('#' + data.id).hide();
				}
			});
</script>