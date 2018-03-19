<%@ include file="/common/taglibs.jsp"%>
<s:form id="userPromoteStudentsFrm" action="ajaxPromoteStudentstoClasses" method="post" theme="simple" cssClass="form-horizontal">
	<s:hidden name="selectedId" value="%{selectedId}"></s:hidden>
	<s:hidden name="tempString" cssClass="tempString"></s:hidden>
	<s:hidden name="anyTitle" value="%{anyTitle}"></s:hidden>
	<s:hidden name="empId" cssClass="empId"></s:hidden>
	
	<s:hidden name="queryString" cssClass="queryString"></s:hidden>
	
	<fieldset>
		<s:if test="%{tempBoolean}">
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<h4 class="pagetitle bold">
				Failure Promotable Students
			</h4>
			<p>Following students are not eligibile for promoting to another class. Please check the remarks column of each student for the reason.</p><hr/>
			<span class="label label-danger">NOTE :</span>
					 You can promote or demote student to particular class by changing 'Promotable class' option. Based on your selection student(s) is(are) promoted to that particular class.
					 <div class="spaceDiv"></div>
				<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_7">
				<thead>
					<tr>
					<th style="display: none;">
			    </th>
			    
						<th>
							Student Name
						</th>
						<th>
							Class Name (Medium)
						</th>
						<th>
							Promote Class<br>(Medium)
						</th>
						<th  style="white-space:normal">
							Remarks
						</th>
						<th>
							<input type="checkbox" id="selectall" />
							Forward pending fee
						</th>
					</tr>
					</thead>
					<tbody>
					<s:iterator value="studentsList">
							<tr class="failurePromoteStuds">
								<td style="display: none;">
									 <s:property value="classAndSection"/>
									<s:if test="%{medium !=null && medium != empty}">
										(<s:property value="medium"/>)
									</s:if>
								</td>
									<td id='<s:property value='id'/>' class='studId'>
										<ul class="tooltipDiv">
											<li>
												<a href="#"><s:property value="studentName" /> </a>
												<ul class="tooltipSubDiv">
													<div class="popover bottom " style="display: none;">
														<div class="arrow"></div>
														<h3 class="popover-title">
															View Student Information
														</h3>
														<div class="popover-content">
															<li>
																Admission Number :
																<s:property value="account.admissionNumber" />
															</li>
															<li>
																Roll Number :
																<s:property value="rollNumber" />
															</li>
															<li>
																Father name :
																<s:property value="account.person.fatherName" />
															</li>
															<li>
																Mobile Number :
																<s:property value="account.person.mobileNumber" />
															</li>
														</div>
													</div>
												</ul>
											</li>
										</ul>
									</td>
								<td>
									<s:property value="classAndSection"/>
									<s:if test="%{medium !=null && medium != empty}">
										(<s:property value="medium"/>)
									</s:if>
								</td>
								<td>
									<s:if test="%{tempList != null && !tempList.isEmpty()}">
										<select  class="form-control promotableClass" name="classAndSection" >
											<option>
												-Select-
											</option>
											<s:iterator value="tempList" status="stat">
												<option value="<s:property value="tempList[#stat.count-1][0]"/> 
													<s:if test="%{tempList[#stat.count-1][1] !=null && tempList[#stat.count-1][1] != ''}">
													-
													<s:property value="tempList[#stat.count-1][1]"/>
													</s:if>">
													
													<s:property value="tempList[#stat.count-1][0]"/>
													<s:if test="%{tempList[#stat.count-1][1] !=null && tempList[#stat.count-1][1] != ''}">
														-
														<s:property value="tempList[#stat.count-1][1]" />
													</s:if> (<s:property value="tempList[#stat.count-1][2]" />)
												</option>
											</s:iterator>
										</select>
									</s:if>
									<s:else>
									<select class="form-control">
										<option>
											-Select-
										</option>
									</select>
								</s:else>
										<!--<s:select list="tempList" listKey="classAndSection" listValue="promotableClassSectionAndMedium" id="promoteClassId" cssStyle="width : 171px;" cssClass="form-control promotableClass" name="classAndSection" headerKey="" headerValue="-Select-"></s:select>
								-->
								</td>
								<td  style="white-space:normal">
									<s:property value="tempString" escapeHtml="false"/>&nbsp;
									<s:hidden name="errorMsg" id="failure%{id}" cssClass="failure%{id}"></s:hidden>
								</td>
								<td>
									<input type="checkbox" name="anyId" id="anyId" value="<s:property value='id'/>" class="checkbox anyId" />
								</td>
							</tr>
					</s:iterator>
					</tbody>
					</table>
				</s:if>
				<s:else>
					<div class="alert alert-info">
						There are no promotable classes.
					</div>	
				</s:else>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					There are no failure promotable student. Click on 'Promote Students' button for promoting students.
				</div>	 
			</s:else>
			<div class="form-body">
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-4">
				<sj:submit   targets="promoteStudentsContent" value="Promote Students" onBeforeTopics="usrPromoteStudFormValidation"
						cssClass="submitBt btn blue long" formIds="userPromoteStudentsFrm" cssStyle="float:left;margin-left:10px;"
						indicator="failStudIndicator" />
				<s:url id="urlClosePromoteStuds" action="ajaxDoPromoteStudents"
							includeParams="all" escapeAmp="false">
						<s:param name="academicYearId" value="%{academicYearId}"></s:param>	
				</s:url>
				<sj:a href="%{urlClosePromoteStuds}" cssClass="btn default" cssStyle="float:left;margin-left:10px;"
					indicator="failStudIndicator" targets="promoteStudentsContent">Cancel</sj:a>
				
			</div>
			</div>
			</div>
		</s:if>
		<s:else>
			<s:url id="urlClosePromoteStuds" action="ajaxDoPromoteStudents"
				includeParams="all" escapeAmp="false">
				<s:param name="usrChgedAcademicId" value="%{usrChgedAcademicId}"></s:param>
			</s:url>
			<sj:a href="%{urlClosePromoteStuds}" indicator="failStudIndicator"
				targets="promoteStudentsContent" cssClass="btn default" cssStyle="float:right;margin-right:20px;">
				<i class="fa fa-mail-reply"></i>  Back to promote students</sj:a>
				<div id=spaceDiv>&nbsp;&nbsp;</div>
				<div id=spaceDiv>&nbsp;&nbsp;</div>
				<div id=spaceDiv>&nbsp;&nbsp;</div>
			<div class="alert alert-info">
				Students are not available for selected classes.
			</div>
		</s:else>
	</fieldset>
</s:form>
<div id="responsive"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js"></script>	
<script type="text/javascript">
$(document).ready(function() {
 $("input:checkbox, input:radio").uniform();
TableAdvanced.init();
	$.subscribe('usrPromoteStudFormValidation', function(event, data) {
					var studId = '';
					var promotableClass = '';
					var promotableClassMedium = '';
					var jsonObj = [];
					var failureJsonObj = [];
					var classSecAndMedium = '';
					$('tr.failurePromoteStuds').each(
							function() {
								studId = $(this).find("td.studId").attr('id');
								promotableClass = ($(this).find(".promotableClass option:selected").text()).trim();
								if(isNonEmpty(promotableClass)){
									//if('-Select-' != promotableClass){
										classSecAndMedium=promotableClass.split(' (');
										if(isNonEmpty(classSecAndMedium)){
											if(classSecAndMedium.length == 2){
												promotableClass = classSecAndMedium[0].trim();
												promotableClassMedium = classSecAndMedium[1].replace(')','').trim();
											}else if(classSecAndMedium.length < 2){
												promotableClass = classSecAndMedium[0].trim();
											}
										//}										
									}
									
								}
								if(isNonEmpty(studId) && isNonEmpty(promotableClass) &&  promotableClass != '-Select-'){
									jsonObj.push( {
												"studId" : studId,
												"promotableClass" : promotableClass,
												"promotableClassMedium" : promotableClassMedium
											});		
								}
								else
								{
									
									failureJsonObj.push( {
										"studId" : studId,
										"failureReason" : $('.failure'+studId).val()
									});
								}
							});
					$('.tempString').val(JSON.stringify(jsonObj));
					$('.queryString').val(JSON.stringify(failureJsonObj));
					
					return true;
			});
			
		var includeIds=[];
		$('#selectall').parent('span').addClass('checked')
				$("#selectall").attr("checked", true);
			$('.anyId').parent('span').addClass('checked');
				$('.anyId').attr('checked',true);
				includeIds=$("input[name=anyId]:checked").map(function () {return this.value;}).get().join(",");
				$('.empId').val(includeIds);
			
			$("#selectall").click(function(){
				if ($(this).is(":checked")){
					$("#selectall").parent('span').addClass('checked');
					$("#selectall").attr("checked", true);
						$('.anyId').parent('span').addClass('checked');
						$('.anyId').attr("checked", true);
					return false;
					
				}else{
					$('.anyId').parent('span').removeClass('checked');
				 	$('.anyId').removeAttr('checked');
				 	$('#selectall').parent('span').removeClass('checked');
				 	$('#selectall').removeAttr('checked');					
				}
				includeIds=$("input[name=anyId]:checked").map(function () {return this.value;}).get().join(",");
				$('.empId').val(includeIds);
				return true;
			});
			 
			$(".anyId").click(function(){
			//alert('hi');
				 if($(".anyId").length == $(".anyId:checked").length) {
				 	$("#selectall").parent('span').addClass('checked');
						$("#selectall").attr("checked", true);
				} else {
					$("#selectall").parent('span').removeClass('checked');
					$("#selectall").removeAttr("checked");
				}
				includeIds=$("input[name=anyId]:checked").map(function () {return this.value;}).get().join(",");
				$('.empId').val(includeIds);
				return true;
			});
		});
	
function PopupTcDetials(studentId, classId) {
	var pars = "student.id=" + studentId + "&anyId=(1,2,3,4)";
	var url = jQuery.url.getChatURL("/schoolfee/ajaxViewFeeDetails.do");
	$('#responsive').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#responsive").html(html);
		}
	});
}

</script>
