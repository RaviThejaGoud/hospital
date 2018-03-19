<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper container_18">
	<div class="wrapper">
		<div class="grid_18 block grid_18MarginLeft">
		<div class="grid_4 alpha">
			<div class="block_head">
				<h2>
					Generate Certificates 
				</h2>
			</div>
			<div class="block_content" id="sideMenu">
				<ul>
					<li>
					   <s:url id="urlDoStudy" action="ajaxDoGetStudyAndBonafiedGeneration" includeParams="all" escapeAmp="false" namespace="/admin">
					   	<s:param name="anyTitle" >studyCertificate</s:param>
					   </s:url>
						  <sj:a id="studyCert" href="%{urlDoStudy}"
							targets="certificatesSecContent" indicator="indicator">Study Certificate</sj:a>
					</li>
					<li>
					   <s:url id="urlDoBonafied" action="ajaxDoGetStudyAndBonafiedGeneration" includeParams="all" escapeAmp="false" namespace="/admin">
					   	<s:param name="anyTitle" >bonafiedCertificate</s:param>
					   </s:url>
						  <sj:a id="bonafied" href="%{urlDoBonafied}"
							targets="certificatesSecContent" indicator="indicator">Bonafied Certificate</sj:a>
					</li>
					<li>
					   <s:url id="urlNoDue" action="ajaxDoGetStudyAndBonafiedGeneration" includeParams="all" escapeAmp="false" namespace="/admin">
					   	<s:param name="anyTitle" >noDuesCertificate</s:param>
					   </s:url>
						  <sj:a id="noDue" href="%{urlNoDue}"
							targets="certificatesSecContent" indicator="indicator">No Dues Certificate</sj:a>
					</li>
					<li>
					   <s:url id="urlDoTcGeneration" action="ajaxDoGetTcGeneration" namespace="/admin"/>
						  <sj:a id="doTcGeneration" href="%{urlDoTcGeneration}"
							targets="certificatesSecContent" indicator="indicator">TC Generation</sj:a>
					</li>
				</ul>
			</div>
		</div>
		<div id="certificatesSecContent" class="grid_14 alpha">
			<jsp:include page="/WEB-INF/pages/admin/academic/studyAndBonafied/ajaxStudyAndBonafiedGeneration.jsp"></jsp:include>
		</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Examination Section");
$(document).ready(function(){
	 $('#adminStaffAndStudent').addClass('current');
	 $('div#sideMenu ul li:first-child').addClass('active');
	 if($('div#sideMenu ul li:first-child').hasClass("selected").toString()){
		 $('div#sideMenu ul ').find('li:first-child a').click();
	}
});
</script>