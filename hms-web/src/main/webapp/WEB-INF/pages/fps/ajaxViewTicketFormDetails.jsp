<%@ include file="/common/taglibs.jsp"%>
<s:property value="title"/>
<div id="addTicketDiv" style="display: none;">
 <s:form action="ajaxAddNewTicket" theme="simple" id="addTicketForm" method="post" cssClass="form-horizontal" namespace="/fps">
 <s:hidden name="fpsDealerSupport.dealerId" value="%{smsCnt}"></s:hidden>
 <s:hidden name="fpsDealerSupport.shopKey"  value="%{tempString}"></s:hidden> 
		<div class="tab-pane" id="tab_meta">
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-2 control-label">Subject :</label>
					<div class="col-md-10">
						<input type="text" class="form-control" name="fpsDealerSupport.subject" maxlength="200">
						<span class="help-block">
						max 200 chars </span>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-2 control-label">Let's get specific :</label>
					<div class="col-md-10">
						<select name="fpsDealerSupport.issueType" id="issue" onchange="" size="1"  class="form-control">
							 <option value="W" >I have a question about weighing machine</option>
							<option value="B" >I have a question about bio-metric device</option>
							<option value="S" >I have a question about softWare issue</option>
							<option value="L" >I have a issue with login</option>
							<option value="T" >I have a issue with transactions</option>
							<option value="BT" >I have a issue bluetooth machine</option>
						</select>
					</div>
				</div>
				<!--<div class="form-group">
					<label class="col-md-2 control-label">Requested Date :</label>
					<div class="col-md-10">
						<div data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker">
								<input type="text"  id="staffDate"  readonly="" class="form-control" name="fpsDealerSupport.requestedDate" >
								<span class="input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button>
								  </span>
							</div>
						   <span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
				-->
				<div class="form-group">
					<label class="col-md-2 control-label">Mobile Number :</label>
					<div class="col-md-10">
						<input type="text" class="form-control" name="fpsDealerSupport.mobileNumber" value='<s:property value="anyId"/>' maxlength="10" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label"><span class="required">*</span>Description :</label>
					<div class="col-md-10">
						<sj:textarea  name="fpsDealerSupport.requestDescription" id="maxlength_textarea" cssClass="form-control required" placeholder="This textarea has a limit of 2000 chars."  rows="4" cols="20" maxlength="2000"></sj:textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label"> <span class="required">*</span>Response :</label>
					<div class="col-md-10">
						<sj:textarea name="fpsDealerSupport.responseDescription" id="maxlength_textarea1" cssClass="form-control required" placeholder="This textarea has a limit of 2000 chars."   rows="4" cols="20" maxlength="2000"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-offset-2 col-md-12">
				<sj:submit cssClass="submitBt btn blue" value="Submit" onBeforeTopics="addTicketFromValidation"
					validate="true" targets="mainContentDiv" formIds="addTicketForm" />
		</div>
</s:form>
</div>
<div class="spaceDiv">&nbsp;</div>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Shop Key
				</th>	
				<th>
					Requested Date
				</th>
			
				<th>
					Request Description
				</th>
				<th>
					Issue Type
				</th>
				<th>
					Status
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:url id="doReplayTicket" action="ajaxDoReplayTicketSupport"
								includeParams="all" escapeAmp="false">
							<s:param name="tempId" value="%{id}"></s:param>
							<s:param name="selectedId" value="%{selectedId}"></s:param>
						</s:url>
						<sj:a href="%{doReplayTicket}" targets="inboxContentDiv">
							<s:property value="shopKey" />
						</sj:a>
					</td>
					<td>
						<s:property value="requestedDateStr" />
					</td>
					<td>
						<s:property value="requestDescription" />
					</td>
					<td>
						<s:if test='%{issueType == "W"}'>  I have a question about weighing machine </s:if>
						<s:elseif test='%{issueType == "B"}'>I have a question about bio-metric device</s:elseif>
						<s:elseif test='%{issueType == "S"}'>I have a question about softWare issue</s:elseif>
						<s:elseif test='%{issueType == "L"}'>I have a issue with login</s:elseif>
						<s:elseif test='%{issueType == "T"}'>I have a issue with transactions</s:elseif>
						<s:elseif test='%{issueType == "BT"}'>I have a issue bluetooth machine</s:elseif>
					</td>
					<td>
						<s:property value="status" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no tickets.
		</div>
	</s:else>
<script type="text/javascript">
TableAdvanced.init();
$.subscribe('addTicketFromValidation', function(event, data) {
		if ($('#addTicketForm').valid()){
			return true;
		}else{
			event.originalEvent.options.submit = false;
		}
	});
</script>
