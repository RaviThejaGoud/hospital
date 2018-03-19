<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<s:if test="%{kBankTypeName != Null}">
						<s:property value="kBankTypeName" />
					</s:if>
					<s:else>
						About Knowledge Bank
					</s:else>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<s:if test='%{user.isSchoolLibrarian != "Y" && user.isParent != "Y" && user.isStudent != "Y"}'>
								<li>
									<s:url id="urlDoManageSkillType" action="ajaxDoManageSkillType"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param value="selectedId" name="CommonTypeId" />
									</s:url>
									<sj:a id="urlDoManageSkillType" href="%{urlDoManageSkillType}"
										data-toggle="tab" targets="caseStudy">Manage Skill Type</sj:a>
								</li>
							</s:if>
						</s:if>
						<s:if test="%{kBankTypeName != Null}">
							<li>
								<s:url id="urlViewAllKBankStudies"
									action="ajaxViewAllKBankStudies" includeParams="all"
									escapeAmp="false" namespace="/admin">
									<s:param value="selectedId" name="kBankTypeId" />
								</s:url>
								<sj:a   data-toggle="tab"
									href="%{urlViewAllKBankStudies}" targets="caseStudy">View All</sj:a>
							</li>
							<s:if test='%{user.isSchoolLibrarian != "Y" && user.isParent != "Y" && user.isStudent != "Y"}'>
								<s:if
									test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
									<li>
										<s:url id="urlDoAddCaseStudy" action="ajaxDoAddCaseStudy"
											includeParams="all" escapeAmp="false" namespace="/admin">
											<s:param name="kBankTypeId"><s:property value="selectedId"/></s:param>
											<s:param name="kBankTypeName"><s:property value="kBankTypeName" /></s:param>
											<s:param value="0" name="subjectId" />
										</s:url>
										<sj:a  href="%{urlDoAddCaseStudy}" data-toggle="tab"  targets="caseStudy">
										Add<s:if test="%{kBankTypeName != Null}">
												<s:property value="kBankTypeName" />
											</s:if>
										</sj:a>
									</li>
								</s:if>
							</s:if>
						</s:if>
						<li class="active">
							<s:url id="urlKBankDetailsLink" action="ajaxGetKBankDetails" namespace="/admin"
								includeParams="all" escapeAmp="false">
								<s:param name="kBankTypeId"><s:property value="selectedId"/></s:param>
								<s:param name="kBankTypeName"><s:property value="kBankTypeName" /></s:param>
							</s:url>
							<sj:a href="%{urlKBankDetailsLink}" targets="mainContentDiv" data-toggle="tab">
								View <s:property value="kBankTypeName" />
							</sj:a>
						</li>
					</ul>
					<div id="caseStudyView" class="tab-content">
						<div  id="caseStudy">
							<s:if test="%{kBankTypeName != Null}">
								<div>
									<jsp:include
										page="/WEB-INF/pages/admin/kBank/ajaxViewCaseStudy.jsp"></jsp:include>
								</div>
							</s:if>
							<s:else>
								<div>
									The knowledge bank is a repetatively when you can access varies
									assets,for day to day reference and for use on daily
									studies.The assets is K-Bank and useful to a wide range of
									audience which would also include students preparation for
									competitive exam,developing frequent knowledge,improving
									communication skills etc.
								</div>
							</s:else>
							<div>
								<s:if
									test="%{knowledgeBankTypeList != null && !knowledgeBankTypeList.isEmpty()}">
									<div style="padding-left: 0px;" class="grid_9">
										<s:iterator value="knowledgeBankTypeList">
											<div class="grid_3">
												<s:url id="urlKBankDetailsLink" action="ajaxGetKBankDetails"
													includeParams="all" escapeAmp="false" namespace="/admin">
													<s:param value="id" name="kBankTypeId" />
													<s:param value="typeName" name="kBankTypeName" />
												</s:url>
												<sj:a href="%{urlKBankDetailsLink}" targets="kBankContent"
													onclick="javascript:highlightNav(this.id)" id="%{id}">
													<s:property value="typeName" />(<s:property
														value="kBankCount" />)<br />
												</sj:a>
											</div>
										</s:iterator>
										<br />
									</div>
								</s:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var kBankTypeName = '<s:property value="kBankTypeName"/>';
if (isNonEmpty(kBankTypeName)) {
	changePageTitle(kBankTypeName);
} else {
	changePageTitle("About Knowledge Bank");
}
</script>