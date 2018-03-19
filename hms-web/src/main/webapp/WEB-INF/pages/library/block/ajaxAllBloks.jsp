<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Block Name
				</th>
				<th>
					Manage Racks
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value='blockName' />
					</td>
					<td>
						<s:url id="addRackNumber" action="ajaxViewAllRackDetails"
							includeParams="all" escapeAmp="false" namespace="/library">
							<s:param name="blockId" value="{id}" />
						</s:url>
						<sj:a href="%{addRackNumber}"  cssClass="btn btn-xs purple"
					      indicator="indicator"	targets="resultsDiv1"><i class="fa fa-edit"></i>Manage Racks</sj:a>
					</td>
				</tr>
			</s:iterator>
					
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no blocks and racks.
	</div>
</s:else>	
<div class="spaceDiv"></div>	
 <hr/>
 <s:if test='%{#session.previousYear == "N"}'>		
<div class="addLinksBody">
					<s:form id="addBlockForm" action=" " method="post" theme="simple"
						cssClass="form-horizontal">
						<h4>
							Add Block 
						</h4>
						<div class="spaceDiv"></div>
						<span class="label label-danger">NOTE!</span> Simple..! just type block name and hit submit.
						<div class="spaceDiv"></div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								<span class="required">*</span>Block Name :
							</label>
							<div class="col-md-2">
								<div class="input-group">
									<sj:textfield name="block.blockName" id="blockName"
										cssClass="form-control required defaultValue" required="true" />
									
								</div>
							</div>
							<span class="input-group-btn">
								<s:submit type="submit" cssClass="btn blue" value="Submit" onclick="javascript:addBlockName(); return false;" />
							</span>
							<span class="input-group-btn">
								<s:submit type="submit" cssClass="btn blue" value="Reset" onclick="javascript:resetBlockName(); return false;" />
							</span>
						</div>
					</s:form>
				</div>
</s:if>
<script type="text/javascript">
changePageTitle("Manage Blocks & Racks");
TableAdvanced.init();
</script>
