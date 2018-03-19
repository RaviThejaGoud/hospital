<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div id="deleteTRF">
<div class="form-body">
	<s:form action="ajaxAddTransportApplicationSettings" id="importTransportApplication"
		namespace="/admin" method="post" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal">
		<h4 class="pageTitle bold">
			Upload Transport Request Form
		</h4>
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						The upload excel sheet must be downloaded from the above "Download
						Transport Request Form" option.
					</li>
					<li>
						The system will not able to process the import successfully
						<font color="red">if any columns are changed or inserted</font> in
						the template.
					</li>
					<li>
						The system will generate the update status if any rows are not
						proceed due to data errors.
					</li>
				</ul>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Select updated transport request form :
			</label>
			<div class="col-md-4">
				<s:file name="fileUpload" id="photoURL" cssClass="btn default required">
				</s:file>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				<sj:submit targets="transportApplicationDiv" value="Upload Transport Request Form"
					cssClass="submitBt btn blue" formIds="importTransportApplication"
					validate="true" />
			</div>
		</div>
	</s:form>
</div>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<h4 class="pageTitle bold">
				Uploaded Transport Request Form
	</h4>
	<table
		class="table table-striped table-bordered table-hover table-full-width">
		<thead>
			<tr>
				<th>
					Template
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
						 <s:property value="fileName" />
					</td>
					<td width="10%">
						<s:form action="ajaxDownloadTransportReuestTemplate" method="post" theme="css_xhtml" cssClass="form-horizontal" namespace="/admin">
							<s:hidden name="tempId" value="%{id}"></s:hidden>	
							<s:submit type="submit" value="Download" cssClass="btn btn-xs blue " cssStyle="cursor:pointer;float:left;" />											
						</s:form>
					</td>
					<td>
						<s:url id="removetemplateTemplate"
							action="ajaxDeleteTransportRequestTemplate"
							includeParams="all" escapeAmp="false" namespace="/admin">
							<s:param name="tempId2" value="id"></s:param>
						</s:url>
						<s:div cssClass="btn btn-xs red" id='%{removetemplateTemplate}'
							theme="simple" title="Delete this Template"
							onclick="javascript:confirmDialogWithTarget(this,'deleteTRF');">
							<i class="fa fa-times"></i>Delete</s:div>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
<div class="spaceDiv">&nbsp;</div>
	<div class="alert alert-info">
		Currently there are no transport request form settings.
	</div>
</s:else>
</div>
