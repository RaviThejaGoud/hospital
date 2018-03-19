<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<h4 class="pageTitle bold form-section">
		Uploaded scorecard templates 		
	</h4>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Class name
				</th>
				<th>
					Exam Type
				</th>
				<th>
					Profile template
				</th>
				<th>
					Academics template
				</th>
				<th>
					Download
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="className" />
					</td>
					<td>
						<s:property value="examType" />
					</td>
					<td>
						<s:property value="docTemplateFileName" />
					</td>
					<td>
						<s:property value="templateFileName" />
					</td>
					<td width="10%">
						<s:form action="ajaxDownloadScoreCardTemplate" method="post" theme="css_xhtml" cssClass="form-horizontal" namespace="/exam">
							<s:hidden name="tempId" value="%{id}"></s:hidden>							
							<s:submit type="submit" value="Download" cssClass="btn btn-xs blue " cssStyle="cursor:pointer;float:left;" />											
						</s:form>
					</td>
					<td>
						<s:url id="removeScoreCard" action="ajaxDeleteScoreCardTemplate"
							includeParams="all" escapeAmp="false" namespace="/exam">
							<s:param name="tempId" value="%{id}"></s:param>
						</s:url>
						<s:div cssClass="btn btn-xs red" 
							id='%{removeScoreCard}' theme="simple"
							title="Delete this template"
							onclick="javascript:confirmDialogWithTarget(this,'scoreCardCont');">
							<i class="fa fa-times"></i>Delete</s:div>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		You have not uploaded scorecard template. Please upload scorecard
		template.
	</div>
</s:else>