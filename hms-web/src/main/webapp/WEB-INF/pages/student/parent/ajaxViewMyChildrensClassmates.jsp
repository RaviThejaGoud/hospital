<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>My Class Mates
				</div>
			</div>
			<div class="portlet-body">
				<div id="myClassMatesHome" class="tab-content">
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<s:if test="%{objectList.size>1}">
							<div class="form-group form-horizontal">
								<label class="control-label col-md-4">
									Child Name :
								</label>
								<div class="col-md-3">
									<s:select id="sectionId" list="objectList"
										listKey="classSectionId" cssClass="form-control required"
										onchange="javascript:onClickToGetMyClassmates(this.value);"
										listValue="idAndName" theme="simple" name="anyId" />
								</div>
							</div>
							<div class="spaceDiv"></div>
							<div class="spaceDiv"></div>
							<div class="spaceDiv"></div>
							<div id="showStudentsDiv">
								<jsp:include
									page="/WEB-INF/pages/student/parent/ajaxViewMyChildrensClass.jsp"></jsp:include>
							</div>
						</s:if>
						<s:else>
							<jsp:include
								page="/WEB-INF/pages/student/parent/ajaxViewMyChildrensClass.jsp"></jsp:include>
						</s:else>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							There are no your children classmates.
						</div>
					</s:else>
					<div id="showStudentsDiv"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("My child(s) Classmates");
});

function onClickToGetMyClassmates(classId) 
{
	$("#showStudentsDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var classId = classId;
	var accountId = '<s:property value="leaveApprovalCount"/>';
	var pars = "classId=" + classId;
	var url = jQuery.url.getChatURL("/student/ajaxGetMyClassMates.do");
	$.ajax( {
		url : url,
		data : pars,
		cache : false,
		success : function(html) {
			$("#showStudentsDiv").html(html);
			$("#showStudentsDiv").show(html);
		}
	});
}
</script>