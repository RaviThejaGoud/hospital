<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<p class="text-primary">
		<strong>Total Active Students : <s:property
				value="studentsList.size" /> , Total Boys : <s:property
				value="tempString" /> , Total Girls : <s:property value="tempId1" />
			<s:if test="%{tempId2 >0}">
			, Gender UnAssigned : <s:property value="tempId2" />
			</s:if></strong>
	</p>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>Photo</th>
				<th>Roll No</th>
				<th>Admission No</th>
				<th>Student Name</th>
				<th>Parent Name</th>
				<s:if test='%{!(user.isSchoolTeacher=="Y"  && #session.parentMobileNoVisibleToTeacher == "N" )}'>
					<th>Mobile Number</th>
				</s:if>
				<th class="DisplayClass">Class Name</th>
				<s:if test='%{user.isOnlySchoolTeacher !="Y" && user.isSchoolAsstStaff !="Y"  && user.isAdminCoordinator !="Y" && user.isReceptionist !="Y"}'>
					<th>Edit</th>
				</s:if>
				<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
				   <s:if test='%{customer.parentPermissionStatus == "Y"}'>
				     <th>Permissions</th>
				    </s:if>
					<th>Status</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:set name="studyClassId" value=""></s:set>
			<s:iterator value="studentsList">
				<!--<s:if test="%{classSectionId != #studyClassId}">
					<div class="boysGirls" style="display: none;">
						Boys :
						<span class="boys">0</span> , Girls :
						<span class="girls">0</span>
						<span id='<s:property value='classSectionId'/>'
							class='classSectionId'></span>
					</div>
				</s:if>
				-->
				<!--<s:if test="%{classSectionId != #studyClassId}">
					<tr>
						<td colspan="7">
							<center>
								<LABEL>
									Class Name & Section :
									 <s:property value="classAndSection" /> 
								</LABEL>
							</center>
							<span id='<s:property value='classSectionId'/>'
								class='classSectionId'></span>
						</td>
					</tr>
				</s:if> -->
				<tr class="<s:property value="gender" />statusSex">
					<td>
						<s:if test="%{imagePath != null &&  imagePath != ''}">
							<img src='<s:property value='imagePath'/>'
								border="0"  style="height: 50px;width: 50px;" id="studentProfileDiv" />
						</s:if> 
						<s:else>
							<img height="50px;" width="50px"alt="" src="../images/common/photo_notAvailable.jpg">
						</s:else>
					</td>
					<td><s:property value="rollNumber" /></td>
					<td><s:property value="admissionNumber" /></td>
					<td>
					<a data-toggle="modal" href="#showStudenstProfileDiv"
									onclick="javascript:showStudentProfileDetails(<s:property value="studId" />,<s:property value="classSectionId" />);"><s:property value="firstName" /> &nbsp; <s:property
							value="lastName" /></a></td>
					<td><s:property value="fatherName" /></td>
					
					<s:if test='%{!(user.isSchoolTeacher=="Y"  && #session.parentMobileNoVisibleToTeacher == "N" )}'>
						<td><s:property value="mobileNumber" /></td>
					</s:if>
					<td class="DisplayClass"><s:property value="classAndSection" />
					</td>
					<s:if test='%{user.isOnlySchoolTeacher !="Y" && user.isSchoolAsstStaff !="Y" && user.isAdminCoordinator !="Y" && user.isReceptionist !="Y"}'>
						<td>
							<s:url id="studentAcademics" namespace="/student"
								action="ajaxViewStudentAcademics" includeParams="all"
								escapeAmp="false">
								<s:param name="anyId" value="%{studId}" />
								<s:param name="classId" value="%{classSectionId}" />
							</s:url> <sj:a href="%{studentAcademics}" targets="staffList"
								onBeforeTopics="doHideStudentListDiv"
								cssClass="btn btn-xs purple" title="Edit" indicator="indicator">
								<i class="fa fa-edit"></i>Edit
							</sj:a>
						</td>
					</s:if>
					
					<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
			  	   		<s:if test='%{customer.parentPermissionStatus == "Y"}'>
			  	   			<td>
			  	   				<s:if test='%{studDiscontinueDesc == null}'>
								<a data-toggle="modal" href="#permissionsDiv" onclick="javascript:permissionsDetails(<s:property value="studId" />);">Permissions</a>
								</s:if><s:else>-</s:else>
							</td>
					    </s:if>
						<td>
						<s:if test='%{#session.previousYear == "N"}'>
							 <s:if test='%{status == "Y"}'>
							 	<a data-toggle="modal" href="#inactiveStudenstDiv" class="btn btn-xs green"
									onclick="javascript:disableStudentAccount1(<s:property value="studId" />,<s:property value="classSectionId" />);"><i
									class="fa fa-times"></i>Active </a>
							 </s:if>
							 <s:elseif test='%{status == "B"}'>
							 	<span class="btn btn-xs blue">Blacklist</span>
							 </s:elseif>
							 <s:elseif test='%{status == "S"}'>
							 	<span class="btn btn-xs yellow">Suspend</span>
							 </s:elseif>
							 <s:elseif test='%{studDiscontinueDesc !=null}'>
							 	<label><b>	<s:property value="academicYear"/> </b><br/> <b>Inactive </b></label>
							 </s:elseif>
							<%--  <s:elseif test='%{status == "B"}'>
							 	<a data-toggle="modal" href="#inactiveStudenstDiv"
									class="btn btn-xs blue"
									onclick="javascript:disableStudentAccount1(<s:property value="studId" />,<s:property value="classSectionId" />);"><i
									class="fa fa-times"></i>Blacklist </a>
							 </s:elseif>
							 <s:elseif test='%{status == "S"}'>
							 	<a data-toggle="modal" href="#inactiveStudenstDiv"
									class="btn btn-xs yellow"
									onclick="javascript:disableStudentAccount1(<s:property value="studId" />,<s:property value="classSectionId" />);"><i
									class="fa fa-times"></i>Suspend </a>
							 </s:elseif>
							 <s:else>
							 	<a data-toggle="modal" href="#inactiveStudenstDiv"
									class="btn btn-xs red"
									onclick="javascript:disableStudentAccount1(<s:property value="studId" />,<s:property value="classSectionId" />);"><i
									class="fa fa-times"></i>Inactive </a>
							 </s:else> --%>
							</s:if>
					    </td>
					</s:if>

					<!--<td>
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="disableStudent" action="ajaxCkeckForDisableStudent"
								namespace="/student" includeParams="all" escapeAmp="false">
								<s:param name="tempId" value="%{studId}" />
								<s:param name="selectedId" value="%{classSectionId}" />
							</s:url>
							<sj:a href="%{disableStudent}" cssClass="btn btn-xs blue"
								targets="disableStudentDetails%{studId}"
								onBeforeTopics="doInitDsisableStudent"
								title="Inactive this student">
								<i class="fa fa-times"></i>Inactive
							</sj:a>
						</s:if>
					</td>
					<div id="disableStudentDetails<s:property value='studId' />" class='load'></div>
				-->
				</tr>
				<s:set name="studyClassId" value="classSectionId"></s:set>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:elseif test='%{alertSendType == "classWiseStudents"}'>
	<div class="alert alert-info">Currently there are no students for
		this class.</div>
</s:elseif>
<s:else>
	<div class="alert alert-info">Oops! No search results found for active students</div>
</s:else>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
		<thead>
			<tr>
				<th>Photo</th>
				<th>Roll No</th>
				<th>Admission No</th>
				<th>Student Name</th>
				<th>Parent Name</th>
				<th>Mobile Number</th>
				<th class="DisplayClass">Class Name</th>
				
				<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
				   
					<th>Status</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:set name="studyClassId" value=""></s:set>
			<s:iterator value="objectList">
				
				<tr class="<s:property value="gender" />statusSex">
					<td>
					
						<s:if test="%{imagePath != null &&  imagePath != ''}">
							<img src='<s:property value='imagePath'/>'
								border="0"  style="height: 50px;width: 50px;" id="studentProfileDiv" />
						</s:if> 
						<s:else>
							<img height="50px;" width="50px"alt="" src="../images/common/photo_notAvailable.jpg">
						</s:else>
					</td>
					<td><s:property value="rollNumber" /></td>
					<td><s:property value="admissionNumber" /></td>
					<td>
					<a data-toggle="modal" href="#showStudenstProfileDiv"
									onclick="javascript:showStudentProfileDetails(<s:property value="studId" />,<s:property value="classSectionId" />);"><s:property value="firstName" /> &nbsp; <s:property
							value="lastName" /></a></td>
					<td><s:property value="fatherName" /></td>
					<td><s:property value="mobileNumber" /></td>
					<td class="DisplayClass"><s:property value="classAndSection" />
					</td>
					
					<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
			  	   		
						<td>
							 <s:if test='%{studDiscontinueDesc !=null}'>
							 	<label><b>	<s:property value="academicYear"/> </b><br/> <b>Inactive </b></label>
							 </s:if>
							
					    </td>
					</s:if>

					
				</tr>
				<s:set name="studyClassId" value="classSectionId"></s:set>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<div id="inactiveStudenstDiv"></div>
<div id="showStudenstProfileDiv"></div>
<div id="permissionsDiv"></div>
<script type="text/javascript">

$(document).ready(function() {
var selected = $('input[name=SelectType]:radio:checked').val();
 if(selected=="Select Class"){		
   $("th.DisplayClass,td.DisplayClass").hide();
 }
TableAdvanced.init();
UIExtendedModals.init();
	$.subscribe('doHideStudentListDiv', function(event, data) {
			$('div#searchStudentsList').hide();
			//$('div.searchDiv').show();
			
	});
	/* var selectedIds =$('input[name=SelectType]:checked').val();
		if(selectedIds == 'Select Class'){
			$("div.alert-info").html('Currently there are no students for this class.');
		} */
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
				event.originalEvent.options.submit=false;
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
	});
	
	/*$('.boysGirls').each(function() {
	 $(this).find('.boys').html(($(this).nextUntil('.boysGirls','tr.MstatusSex').size()));
	 $(this).find('.girls').html(($(this).nextUntil('.boysGirls','tr.FstatusSex').size()));
	 $('.totalBoys').html($('tr.MstatusSex').size());
	 $('.totalGirls').html($('tr.FstatusSex').size());
	});*/
});
 function disableStudentAccount1(studentId,selectedId){
	if(isNonEmpty(studentId) && isNonEmpty(selectedId)){
	  var pars = "tempId=" + studentId+"&selectedId="+selectedId;
        $.ajax( {
	        url : jQuery.url.getChatURL("/student/ajaxDoDisableStudent.do"),
			cache : true,
			data : pars,
			success : function(html) {
			$("#inactiveStudenstDiv").html(html);
			// $('#'+dataDiv).html(data);
			}
		});
	}
 	}	
 
 function permissionsDetails(studentId){
		if(isNonEmpty(studentId)){
		  var pars = "tempId=" + studentId;
	        $.ajax( {
		        url : jQuery.url.getChatURL("/student/ajaxPermissionsDetails.do"),
				cache : true,
				data : pars,
				success : function(html) {
				$("#permissionsDiv").html(html);
				}
			});
		}
}
 
 function showStudentProfileDetails(studentId,classId){
		if(isNonEmpty(studentId) && isNonEmpty(classId)){
		  var pars = "tempId=" + studentId+"&tempId2="+classId;
	        $.ajax( {
		        url : jQuery.url.getChatURL("/student/ajaxShowStudentProfileDetails.do"),
				cache : true,
				data : pars,
				success : function(html) {
				$("#showStudenstProfileDiv").html(html);
				// $('#'+dataDiv).html(data);
				}
			});
		}
  }
</script>

