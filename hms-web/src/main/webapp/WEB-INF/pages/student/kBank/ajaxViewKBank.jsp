<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_14" id="caseStudyView">
	<div class="block_head">
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
					includeParams="all" escapeAmp="false">
					<s:param value="selectedId" name="kBankTypeId" />
				</s:url>
				<sj:a id="urlViewAllKBankStudies" href="%{urlViewAllKBankStudies}"
					targets="caseStudyView" indicator="indicator">View All</sj:a>	
			</li>
			<s:if test='%{user.isParent != "Y" && user.isStudent != "Y"}'>
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
		</s:if>
		</ul>
	</div>
	<div class="block_content" id="caseStudy">
		<s:if test="%{kBankTypeName != Null}">
			<div>
				<jsp:include page="/WEB-INF/pages/student/kBank/ajaxViewCaseStudy.jsp"></jsp:include>	
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
					<sj:a href="%{urlKBankDetailsLink}" targets="kBankContent" onclick="javascript:highlightNav(this.id)" id="%{id}"
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
function highlightNav(id){
		$('.kBankNavLinks > ul > li').removeClass("active");
		$('#kbankNav'+id).addClass("active");
	}
//changePageTitle("<s:property value="kBankTypeName"/>");
changePageTitle("<s:property value="kBankTypeName"/>");
 $('#classActivities').addClass('current');
</script>

