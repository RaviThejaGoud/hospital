<%@ include file="/common/taglibs.jsp"%>
<!--<title>Teacher | Class Exam Details</title>
<div class="block grid_14" id="caseStudyView">
	<div class="block_head">
		<h2>
				About Knowledge Bank
		</h2>
		<ul>
			<li >
			<s:url id="urlViewAllKBankStudies" action="ajaxGetKBankDetails"
				includeParams="all" escapeAmp="false">
				<s:param name="selectedId" value="%{kBankTypeId}" />
			</s:url>
			<sj:a href="%{urlViewAllKBankStudies}" 
				indicator="indicator" targets="caseStudyView%{kBankTypeId}" button="false"
				buttonIcon="ui-icon-plus">
				Back
			</sj:a>
				<s:url id="urlViewAllKBankStudies" 
					includeParams="all" escapeAmp="false">
					<s:param value="selectedId" name="kBankTypeId" />
				</s:url>
				<sj:a id="urlViewAllKBankStudies" href="%{urlViewAllKBankStudies}"
					targets="caseStudyvvvv" indicator="indicator">Back</sj:a>	
			</li>
			<li>
			 <s:url id="urlDoAddCaseStudy" action="ajaxDoAddCaseStudy"
					includeParams="all" escapeAmp="false">
					<s:param value="selectedId" name="kBankTypeId" />
				</s:url>
				<sj:a id="urlDoAddCaseStudy" href="%{urlDoAddCaseStudy}"
					targets="caseStudy" indicator="indicator">Add &nbsp;<s:if test="%{kBankTypeName != Null}"><s:property value="kBankTypeName"/></s:if>
					</sj:a>	
			</li>
		</ul>
	</div>
	<div class="block_content" id="caseStudy">
		<div>
			<jsp:include page="/WEB-INF/pages/admin/kBank/ajaxViewCaseStudy.jsp"></jsp:include>	
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("View all Knowledge Bank Details");
</script>
-->
<jsp:include page="/WEB-INF/pages/admin/kBank/ajaxViewCaseStudy.jsp"></jsp:include>	