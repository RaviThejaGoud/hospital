<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<s:if test='%{anyTitle == "studyCertificate"}'>
						Study Certificate
					</s:if>
					<s:elseif test='%{anyTitle == "bonafiedCertificate"}'>
						Bonafied Certificate
					</s:elseif>
					<s:elseif test='%{anyTitle == "noDuesCertificate"}'>
						No Dues Certificate
					</s:elseif>
					<s:elseif test='%{anyTitle == "feeCertificate"}'>
						Fee Certificate
					</s:elseif>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width" id="settingsDiv">
					<ul class="nav nav-tabs">
						<!--<%@ include
							file="/WEB-INF/pages/exam/admin/ajaxBackToAdminLink.jsp"%>
						-->
						<s:if
							test='%{anyTitle == "studyCertificate" || anyTitle == "studyBookSettings"}'>
							<li>
								<s:url id="urtAddTemplate" action="ajaxDoStudyAndBonafiedSettings"
									namespace="/admin">
									<s:param name="tempString">studyCertificate</s:param>
								</s:url>
								<sj:a id="addtemplate" href="%{urtAddTemplate}"
									targets="certificatesCont" data-toggle="tab">Upload Templates</sj:a>
							</li>
							<li>
								<s:url id="urlAddSection" action="ajaxDoStudyAndBonafiedSettings"
									namespace="/admin">
									<s:param name="tempString">studyBookSettings</s:param>
								</s:url>
								<sj:a id="addSection" href="%{urlAddSection}"
									targets="certificatesCont" data-toggle="tab">Book Settings</sj:a>
							</li>
						</s:if>
						<s:elseif
							test='%{anyTitle == "bonafiedCertificate" || anyTitle == "bonafiedBookSettings"}'>
							<li>
								<s:url id="urtAddTemplate" action="ajaxDoStudyAndBonafiedSettings"
									namespace="/admin">
									<s:param name="tempString">bonafiedCertificate</s:param>
								</s:url>
								<sj:a id="addtemplate" href="%{urtAddTemplate}"
									targets="certificatesCont" indicator="indicator" data-toggle="tab">Upload Templates</sj:a>
							</li>
						</s:elseif>
						<s:if
							test='%{anyTitle == "noDuesCertificate" || anyTitle == "noDuesBookSettings"}'>
							<li>
								<s:url id="urtAddTemplate" action="ajaxDoStudyAndBonafiedSettings"
									namespace="/admin">
									<s:param name="tempString">noDuesCertificate</s:param>
								</s:url>
								<sj:a id="addtemplate" href="%{urtAddTemplate}"
									targets="certificatesCont" data-toggle="tab">Upload Templates</sj:a>
							</li>
						</s:if>
						<s:if
							test='%{anyTitle == "feeCertificate" || anyTitle == "feeBookSettings"}'>
							<li>
								<s:url id="urtAddTemplate" action="ajaxDoStudyAndBonafiedSettings"
									namespace="/admin">
									<s:param name="tempString">feeCertificate</s:param>
								</s:url>
								<sj:a id="addtemplate" href="%{urtAddTemplate}"
									targets="certificatesCont" data-toggle="tab">Upload Templates</sj:a>
							</li>
						</s:if>
						<li class="active">
								<s:url id="urlDoStudy" action="ajaxDoGetStudyAndBonafiedGeneration"
									includeParams="all" escapeAmp="false" namespace="/admin">
									<s:param name="anyTitle"  value="anyTitle"></s:param>
								</s:url>
								<sj:a href="%{urlDoStudy}" targets="mainContentDiv"
									data-toggle="tab">Generate Certificate</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="certificatesCont">
						<jsp:include
							page="/WEB-INF/pages/admin/academic/studyAndBonafied/ajaxDoStudyAndBonafiedGeneration.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
changePageTitle('Manage Study Ceritificates');
});
</script>