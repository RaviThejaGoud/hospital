<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{student.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		id="inactiveStudenstDiv"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">Student Status </h4>
		</div>
		<div class="modal-body">
		</s:if>
			<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
				<span id="studyClassIdSpan" class="<s:property value='plTitle'/>"></span>
				<div class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Status :
					</label>
					<div class="col-md-9">
						<s:select list="#{'Y':'Active','N':'Inactive','B':'Blacklist','S':'Suspend'}"
							cssClass="required form-control  input-medium" id="studentStatusId"
							name="student.status" onchange="javascript:getAjaxDoGetStudentsStatusDetails(this.value);" />
					</div>
				</div>
				<div id="spaceDiv"></div>
				<div id="spaceDiv"></div>
				<div id="spaceDiv"></div>
				<div id="disableStudentsDiv"></div>
				</div>
				
			</s:if>
	<s:if test="%{student.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	var studentStatus=$("select#studentStatusId").val();
	//alert(studentStatus);
	getAjaxDoGetStudentsStatusDetails(studentStatus);
});

function getAjaxDoGetStudentsStatusDetails(studentStatus) {
	   var tempId ='<s:property value="tempId"/>';
	   var selectedId ='<s:property value="selectedId"/>';
	   var status = '<s:property value='plTitle'/>';
	   //alert(status);
	  // alert(tempId+"-"+selectedId+"-"+studentStatus+"-"+status);
	  	if (studentStatus == "" || studentStatus=='Y') {
			$("#disableStudentsDiv").hide();
		} else {
			 //alert("dfdfg:"+studentStatus);
			var pars = "tempId=" + tempId + "&selectedId=" +selectedId + "&plTitle=" +studentStatus +"&eventId="+status;
			$("#disableStudentsDiv")
					.html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/student/ajaxViewDisableStuents.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#disableStudentsDiv").html(html);
					$("#disableStudentsDiv").show();
				}
			});
		}
	}
	
</script>