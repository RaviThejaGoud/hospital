<%@ include file="/common/taglibs.jsp"%>
<!--<div class="grid_14">
	<div class="block_head">
		<h2>
			<s:if test='%{tempString == "studyCertificate"}'>
				Study Certificate Settings
			</s:if> 
			<s:elseif test='%{tempString == "bonafiedCertificate"}'>
				Bonafied Certificate Settings
			</s:elseif>
			<s:elseif test='%{tempString == "noDuesCertificate"}'>
				No Dues Certificate Settings
			</s:elseif>
		</h2>
		<div id="topMenu">
			<ul> 
				 <li>
				 	<s:if test='%{tempString == "studyCertificate"}'>
						<a href="#" onclick="javascript:getSBGenerate('studyCert')">
					</s:if> 
					<s:elseif test='%{tempString == "bonafiedCertificate"}'>
						<a href="#" onclick="javascript:getSBGenerate('bonafiedCert')">
					</s:elseif>
					<s:elseif test='%{tempString == "noDuesCertificate"}'>
						<a href="#" onclick="javascript:getSBGenerate('NoduesCert')">
					</s:elseif>
					Back To Certificate Generation</a>
				</li>
			</ul>
		</div>
	</div>
	-->
<div class="tabbable tabbable-custom tabbable-full-width">
	<ul class="nav nav-tabs">
		<s:if
			test='%{tempString == "studyCertificate" || tempString == "studyBookSettings"}'>
			<li class="active">
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
			<li>
				<s:url id="urlDoStudy" action="ajaxDoGetStudyAndBonafiedGeneration"
					includeParams="all" escapeAmp="false" namespace="/admin">
					<s:param name="anyTitle"  value="tempString"></s:param>
				</s:url>
				<sj:a href="%{urlDoStudy}" targets="mainContentDiv"
					data-toggle="tab">Generate Certificate</sj:a>
			</li>
		</s:if>
		<s:elseif
			test='%{tempString == "bonafiedCertificate" || tempString == "bonafiedBookSettings"}'>
			<li class="active">
				<s:url id="urtAddTemplate" action="ajaxDoStudyAndBonafiedSettings"
					namespace="/admin">
					<s:param name="tempString">bonafiedCertificate</s:param>
				</s:url>
				<sj:a id="addtemplate" href="%{urtAddTemplate}"
					targets="certificatesCont" indicator="indicator" data-toggle="tab">Upload Templates</sj:a>
			</li>
			<li>
				<s:url id="urlDoStudy" action="ajaxDoGetStudyAndBonafiedGeneration"
					includeParams="all" escapeAmp="false" namespace="/admin">
					<s:param name="anyTitle"  value="tempString"></s:param>
				</s:url>
				<sj:a href="%{urlDoStudy}" targets="mainContentDiv"
					data-toggle="tab">Generate Certificate</sj:a>
			</li>
		</s:elseif>
		<s:if
			test='%{tempString == "noDuesCertificate" || tempString == "noDuesBookSettings"}'>
			<li class="active">
				<s:url id="urtAddTemplate" action="ajaxDoStudyAndBonafiedSettings"
					namespace="/admin">
					<s:param name="tempString">noDuesCertificate</s:param>
				</s:url>
				<sj:a id="addtemplate" href="%{urtAddTemplate}"
					targets="certificatesCont" data-toggle="tab">Upload Templates</sj:a>
			</li>
			<li>
				<s:url id="urlDoStudy" action="ajaxDoGetStudyAndBonafiedGeneration"
					includeParams="all" escapeAmp="false" namespace="/admin">
					<s:param name="anyTitle"  value="tempString"></s:param>
				</s:url>
				<sj:a href="%{urlDoStudy}" targets="mainContentDiv"
					data-toggle="tab">Generate Certificate</sj:a>
			</li>
		</s:if>
		<s:elseif
			test='%{tempString == "feeCertificate" || tempString == "feeBookSettings"}'>
			<li class="active">
				<s:url id="urtAddTemplate" action="ajaxDoStudyAndBonafiedSettings"
					namespace="/admin">
					<s:param name="tempString">feeCertificate</s:param>
				</s:url>
				<sj:a id="addtemplate" href="%{urtAddTemplate}"
					targets="certificatesCont" data-toggle="tab">Upload Templates</sj:a>
			</li>
			<li>
				<s:url id="urlDoStudy" action="ajaxDoGetStudyAndBonafiedGeneration"
					includeParams="all" escapeAmp="false" namespace="/admin">
					<s:param name="anyTitle"  value="tempString"></s:param>
				</s:url>
				<sj:a href="%{urlDoStudy}" targets="mainContentDiv"
					data-toggle="tab">Generate Certificate</sj:a>
			</li>
		</s:elseif>
	</ul>
	<div id="certificatesCont" class="tab-content">
		<jsp:include
			page="/WEB-INF/pages/admin/academic/studyAndBonafied/ajaxAddStudyAndBonafiedSettings.jsp" />
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Manage Study And Bonafied Settings');
	});
function getSBGenerate(moduleName){
	if(moduleName == 'studyCert')
		$('a#studyCert').click();
	else if(moduleName == 'bonafiedCert')
		$('a#bonafied').click();
	else if(moduleName == 'NoduesCert')
		$('a#noDue').click();
}
</script>