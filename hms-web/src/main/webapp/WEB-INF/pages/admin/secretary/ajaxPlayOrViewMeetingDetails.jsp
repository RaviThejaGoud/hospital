<%@ include file="/common/taglibs.jsp"%>

<div data-width="760" class="modal fade modal-overflow in"
	id="audioDiv"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
			<h4 class="modal-title">Meeting Minutes</h4>
	</div>
	<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
		<table
			class="table table-bordered table-striped table-condensed flip-content">
			<tr>
				<s:if test="%{anyId=='audio'}">
					 <th>Audio</th>
				</s:if>
				<s:elseif test="%{anyId=='video'}">
				 	<th>Video</th>
				</s:elseif>
				<s:else>
					 <th>Document</th>
				</s:else>
			    <th>File Name</th>
			</tr>
			<s:iterator value="tempList1">
				<tr>
						<s:if test="%{mapFile=='audio'}">
							<td>
								<div class="col-md-12">
									<audio controls>
									 	 <source src="<s:property value="filePath"/>/<s:property value="fileName"/>" /> 
									</audio>
								</div>
							</td>
						</s:if>
						<s:elseif test="%{mapFile=='video'}">
							<td>
								<div class="col-md-12">
									<video width="400" controls>
								 		<source src="<s:property value="filePath"/>/<s:property value="fileName"/>" />
									</video>
								</div>
							</td>
						</s:elseif>
						<s:else>
							<td>
								<div class="col-md-12">
									<a href="<s:property value="filePath"/>/<s:property value="fileName"/>" target="_new"><s:property value="fileName"/>&nbsp;&nbsp;<i class="fa fa-book"></i> </a>
								</div>
							</td>
						</s:else>
						<td>
							<s:property value="fileName"/>
						</td>
				</tr>
			</s:iterator>
		</table>
	</s:if>
	<s:else>
	<div class="alert alert-info">
		No audio files for this meeting.
	</div>
	</s:else>
</div> 
 