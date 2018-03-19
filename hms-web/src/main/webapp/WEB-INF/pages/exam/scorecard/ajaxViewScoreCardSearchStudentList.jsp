<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if
	test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
	<div class="spaceDiv"></div>
	<div class="spaceDiv"></div>
	<p>
		<span class="label label-danger">NOTE :</span> You can sort students
		by 'Admission Number' or 'Student Name' by click on appropriate
		header.
	</p>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Roll No
				</th>
				<th>

					Admission No
				</th>
				<th>
					Student Name
				</th>
				<th>
					Class & Section
				</th>
				<th>
					Download Scorecard
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="viewStudentPersonAccountDetailsList">
				<tr>
					<td>
						<s:property value="rollNumber" />
					</td>
					<td>
						<s:property value="admissionNumber" />
						&nbsp;
					</td>
					<td>
						<s:property value="firstName" />
						&nbsp;
						<s:property value="lastName" />
					</td>
					<td>
						<s:property value="classAndSection" />
					</td>
					<td>
						<ul class="tooltipDiv">
							<s:if test="%{present}">
								<s:if test="%{tempList != null && !tempList.isEmpty()}">
									<li>
										<a href="#" class="btn btn-xs purple"> <i
											class="fa fa-edit"></i>Download Scorecard</a>
										<ul class="tooltipSubDiv">
											<div class="popover bottom " style="display: none;">
												<div class="arrow"></div>
												<h3 class="popover-title">
													Download Scorecard
												</h3>
												<div class="popover-content">
													<s:set var="estudentId" value="studentId" />
													<s:iterator value="tempList">
														<s:if test="%{studentId == #estudentId}">
															<li>
																<a
																	href='${pageContext.request.contextPath}/exam/ajaxDownloadStudentScorecardFromUserFiles.do?tempString=<s:property value="anyString"/>'
																	title="Score Card Download"  
																	class="btn btn-xs green"><i class="fa fa-edit"></i>
																	<s:property value="typeOfExam" />
																</a>
															</li>
														</s:if>
														<s:else>
															<li>
																Scorecard not generated
															</li>
														</s:else>
													</s:iterator>
												</div>
											</div>
										</ul>
									</li>
								</s:if>
								<s:else>
									<a
										href='${pageContext.request.contextPath}/exam/ajaxDownloadStudentScorecardFromUserFiles.do?tempString=<s:property value="anyString"/>'
										title="Score Card Download" 
										class="btn btn-xs purple"><i class="fa fa-edit">Download Scorecard 
									</a>
								</s:else>
							</s:if>
							<s:else>
								<li>
									Scorecard not generated
								</li>
							</s:else>
						</ul>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<br />
	<div class="alert alert-info">
		Oops! system couldn't find any match with keyword. Try by correcting
		the word
	</div>
</s:else>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	$.subscribe('doInitEditStudent', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$.subscribe('doHideStudentListDiv', function(event, data) {
			$('div#searchStudentsList').hide();
			$('div.searchDiv').show();
	});

	$.subscribe('doInitDsisableStudent', function(event, data) {
	var json_obj = $.parseJSON($('#'+data.id).html());//parse JSON
	var studentId=data.id.replace(/[A-Za-z$-]/g, "");
	var selectedId = $('span.classSectionId').attr('id');
	if(isNonEmpty(json_obj)){
		 var output="";
            for (var i in json_obj) 
            {output+= json_obj[i];}
           var output1=output.split(",");    
            var finalOutPut='The selected student have Fee Balance / Issued Book(s). Are you sure you want to disable this Student?  \n\n';       
           for(var i=0;i<output1.length;i++){finalOutPut+=output1[i]+'\n';}
		if(isNonEmpty(output1) && output1.length >0){
			if(confirm(finalOutPut)){
			if ($('#' + data.id).is(":hidden")) {$('#' + data.id).hide();}
				disableStudentAccount(studentId,selectedId,data.id);
				$('#' + data.id).show();
				return true;
			}else{
				return false;
			}
		}else{
			if ($('#' + data.id).is(":hidden")) {$('#' + data.id).hide();}
			disableStudentAccount(studentId,selectedId,data.id);
			$('#' + data.id).show();
			return true;
		}
	}else{
		if ($('#' + data.id).is(":hidden")) {$('#' + data.id).hide();}
		disableStudentAccount(studentId,selectedId,data.id);
		$('#' + data.id).show();
		return true;
	}
	function disableStudentAccount(studentId,selectedId,dataDiv){
	if(isNonEmpty(studentId) && isNonEmpty(selectedId)){
	var pars = "tempId=" + studentId+"&selectedId="+selectedId;
        $.ajax( {
	        url : jQuery.url.getChatURL("/student/ajaxDoDisableStudent.do"),
			cache : true,
			data : pars,
			success : function(data) {
			 $('#'+dataDiv).html(data);
			}
		});
	}
 	}	
	});
	
	var timeInverse=1;	
	$('div.studentNameDiv').click(function () {
	     $('div#scoreCardSearchStuds>div.item').sortElements(function (a, b) {return ($(a).attr('studentName').toLowerCase() > $(b).attr('studentName').toLowerCase() ? 1 : -1) * timeInverse; });
	    updateDirectionArrows($(this), timeInverse);
	    $("#scoreCardSearchStuds").pagination();
	    timeInverse = timeInverse * -1;
	    return false;
	});
 
	var admNumber=1;
	$('div.admissionNumberDiv').click(function () {
	    $('div#scoreCardSearchStuds>div.item').sortElements(function (a, b) {return ($(a).attr('admissionNumber') > $(b).attr('admissionNumber') ? 1 : -1) * admNumber; });
	    updateDirectionArrows($(this), admNumber);
	    $("#scoreCardSearchStuds").pagination();
	    admNumber = admNumber * -1;
	    return false;
	});
	
	var rollNum = 1;
	$('div.rollNumberDiv').click(function () {
	    $('div#scoreCardSearchStuds>div.item').sortElements(function (a, b) {return (parseInt($(a).attr('rollNumber')) > parseInt($(b).attr('rollNumber')) ? 1 : -1) * rollNum; });
	    updateDirectionArrows($(this), rollNum);
	    $("#scoreCardSearchStuds").pagination();
	    rollNum = rollNum * -1;
	    return false;
	});
	
});
</script>

