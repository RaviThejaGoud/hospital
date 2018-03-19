<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<%@ include file="/common/messages.jsp"%>
<div class="form-body">
	<s:form action="ajaxUploadStudentImages" id="uploadImages"
		theme="simple" method="post" namespace="/student"
		cssClass="form-horizontal">
		<s:hidden name="anyTitle" id="jsonResponseData" value=""></s:hidden>
		<s:hidden name="classId" id="classNameId" value=""></s:hidden>
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<label>
				Total Students :
				<s:property value="studentsList.size" />
			</label>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_4">
				<thead>
					<tr>
						<th>
							Photo
						</th>
						<th>
							Admission No
						</th>
						<th>
							Student Name
						</th>
						<th>
							Upload
						</th>
						<th>
							Capture
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="studentsList" status="itStatus">
						<!--<s:if test='%{#itStatus.count == 1}'>
							<tr>
								<td colspan="7">
									<center>
										Class Name & Section :
										<s:property value="classAndSection" />
									</center>
								</td>
							</tr>
						</s:if> -->
						<tr id="item" data-Id="<s:property value='#itStatus.count' />">
							<td>
								<span id='<s:property value='accountId'/>'
									class='studentAccountId'></span>
								<div class="imageData">
									<img height="50px;"
											id="image<s:property value='#itStatus.count' />" border="0"
											alt="" src="../images/common/photo_notAvailable.jpg">
								</div>
							</td>
							<td>
								<div class="admissionNum" id="<s:property value='studId'/>">
									<s:property value="admissionNumber" />
								</div>
							</td>
							<td>
								<s:property value="firstName" />
								&nbsp;
								<s:property value="lastName" />
							</td>
							<td>
								<div class="browseInputVal">
									<input type="file" class="btn default browseButton" name="fileUpload" onchange="validateImage(this);"
										id="<s:property value='#itStatus.count' />" />
								</div>
							</td>
							<td>
								<a data-toggle="modal"  href="#studensCaptureDiv" class="capturePhoto btn default" id="<s:property value='#itStatus.count' />"
									onclick="javascript:viewPopupStudensCapture(<s:property value='#itStatus.count' />);"><i class="fa fa-camera"></i>Capture  
								</a>
								
							</td> 
							<!--<td>
								<div id="<s:property value='#itStatus.count' />">
									<a style="" id="<s:property value='#itStatus.count' />"
										href="<c:url value='/admin/ajaxCapturePhoto.do' />"
										class="capturePhoto btn default" title="Capture Image"> <i
										class="fa fa-camera"></i>Capture </a>
								</div>
							</td> -->
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit   value="Submit" cssClass="btn blue long" 
						targets="searchStudentsList" formIds="uploadImages" indicator="indicator"
						onBeforeTopics="uploadStudentImages" />
					<s:url id="urlMyStudentsLink" action="ajaxGetStudyClassList"
						namespace="/student">
						<s:param name="staff.id" value="0">
						</s:param>
					</s:url>
					<sj:a href="%{urlMyStudentsLink}"
						targets="mainContentDiv" cssClass="btn default">
									Cancel</sj:a>
				</div>
			</div>
		</s:if>
		<s:elseif test='%{alertSendType == "classWiseStudents"}'>
			<div class="alert alert-info">
				Currently there are no students for this class.
			</div>
		</s:elseif>
		<s:else>
			<div class="alert alert-info">
				Selected class all students are having image.
			</div>
		</s:else>
	</s:form>
</div>
<div id="studensCaptureDiv"></div>
<script type="text/javascript">
//TableAdvanced.init();
UIExtendedModals.init();
$(document).ready(function() {
	$('tr#item').each(function(){
		var imageDivIds =$(this).attr('data-id');
		var imagePath = $(this).find('div.imageData').children('img').attr('src');
		if(isNonEmpty(imagePath) && imagePath !=null){
			$('img#image'+imageDivIds).attr('src', imagePath + '?' + Math.random());
		  }   
	});
	$("input.browseButton").click(function(){
	 // $('#image'+$(this).attr('id')).attr('src',"/sms/images/common/photo_notAvailable.jpg");
	});
});
function validateImage(obj){
	 var val = $("input#"+$(obj).attr("id")).val();
	if(isNonEmpty(val)){
		var allowedExtensions = ['jpg','jpeg','png'];
		var ext = val.substring(val.lastIndexOf('.') + 1).toLowerCase(); 
		if (allowedExtensions.indexOf(ext) === -1) {
     	 	alert('Invalid file Format. Only ' + allowedExtensions.join(', ') + ' files are allowed.');
     	 $("input#"+$(obj).attr("id")).val('');
     	 	return false; 
   	 	}
	}
}
$("a.capturePhoto").click(function(){
   $("a.capturePhoto").each(function(){
     $(this).removeClass('open');
   });
   $(this).parents('td').prev('td').find('input').val(''); 
   $(this).addClass('open');
});
$.subscribe('uploadStudentImages', function(event, data) {
	if ($('#uploadImages').valid()) {
    var jsonObj = new Array();
	var html=new Array();
	var jsonRes='';
	$('tr#item').each(function(){
	var inp =document.getElementById($(this).attr('data-Id'));
	// $(this).find('div.firstDivChild').each(function(){
			       html.push({"IMAGEDATA" : $(this).find('div.imageData').children('img').attr('src'),
			 				  "ADMISSIONNUM" : $(this).find('div.admissionNum').html(),
			 				  "STUDENTID" :  $(this).find('div.admissionNum').attr('id'),
			  				  "BROWSEURL" : $(this).find('div.browseInputVal').children('input').val(),
			  				  "ACCOUNTID" : $(this).find('span.studentAccountId').attr('id'),
			  				  "CLASSNAME" : $('select#className option:selected').text()
			  				});
	 	 jsonObj.push(html);
	 	 if(jsonObj!='' && jsonObj!=','){
	 	   jsonRes={"JSONOBJ": jsonObj};
	 	 }
	 	 html=[];
	 	 //});
		});
		if(jsonRes==''){
		 jsonRes=({});
		}
		 //alert(JSON.stringify(jsonRes));
	    $('input#jsonResponseData').val(JSON.stringify(jsonRes));
	    $('input#classNameId').val($('select#className option:selected').val());
	}
});
 function viewPopupStudensCapture(id){
	if(isNonEmpty(id)){
	  var pars = "id=" + id;
        $.ajax( {
	        url : jQuery.url.getChatURL("/admin/ajaxCapturePhoto.do"),
			cache : true,
			data : pars,
			success : function(html) {
			$("#studensCaptureDiv").html(html);
			}
		});
	}
 }
</script>

