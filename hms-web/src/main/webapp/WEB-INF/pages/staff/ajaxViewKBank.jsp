<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<title>Teacher | Class Exam Details</title>
<div class="block grid_12" id="caseStudyView">
	<div class="block_head" id="topMenu">
		<h2>
			<s:if test="%{kBankTypeName != Null}">
				<s:property value="kBankTypeName"/>
			</s:if>
			<s:else>
				About Knowledge Bank
			</s:else>
		</h2>
		<ul>
		<s:if test="%{kBankTypeName != Null}">
			<li>
				<s:url id="urlViewAllKBankStudies" action="ajaxViewAllKBankStudies"
					includeParams="all" escapeAmp="false" namespace="/admin">
					<s:param value="selectedId" name="kBankTypeId" />
				</s:url>
				<sj:a id="urlViewAllKBankStudies" href="%{urlViewAllKBankStudies}"
					targets="caseStudyView" indicator="indicator">View All</sj:a>	
			</li>
			<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
			<li>
			 <s:url id="urlDoAddCaseStudy" action="ajaxDoAddCaseStudy"
					includeParams="all" escapeAmp="false">
					<s:param value="0" name="subjectId" />
					<s:param value="selectedId" name="kBankTypeId" />
				</s:url>
				<sj:a id="urlDoAddCaseStudy" href="%{urlDoAddCaseStudy}"
					targets="caseStudy" indicator="indicator">Add &nbsp;<s:if test="%{kBankTypeName != Null}"><s:property value="kBankTypeName"/></s:if></sj:a>	
			</li>
			</s:if>
		</s:if>
		</ul>
	</div>
	<div class="block_content" id="caseStudy">
		<s:if test='%{kBankTypeName != "About Knowledge Bank"}'>
			<div>
				<jsp:include page="/WEB-INF/pages/staff/ajaxViewCaseStudy.jsp"></jsp:include>	
			</div>
		</s:if>
		<s:else>
			<div>The knowledge bank is a repetatively when you can access varies assets,for day to day reference and for use on daily studies.The assets is K-Bank and useful to a wide range of audience which would also include students preparation for competitive exam,developing frequent knowledge,improving communication skills etc. </div>
		</s:else>
		<div>
		<s:if test="%{knowledgeBankTypeList != null && !knowledgeBankTypeList.isEmpty()}">
			<div style="padding-left: 0px;" class="grid_9">
				<s:iterator value="knowledgeBankTypeList">
				<div class="grid_3">
				<s:url id="urlKBankDetailsLink" action="ajaxGetKBankDetails"
					includeParams="all" escapeAmp="false">
					<s:param value="id" name="kBankTypeId" />
					<s:param value="typeName" name="kBankTypeName" />
				</s:url>
					<sj:a href="%{urlKBankDetailsLink}" targets="kBankContent"
						indicator="indicator"><s:property value="typeName"/>(<s:property value="kBankCount"/>)<br /></sj:a>
				</div>
				</s:iterator>
				<br/>
			</div>
		</s:if>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("<s:property value="kBankTypeName"/>");
</script>

