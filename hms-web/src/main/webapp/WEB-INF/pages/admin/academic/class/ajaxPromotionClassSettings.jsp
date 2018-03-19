<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
	<s:form id="createPromoteClass" action="ajaxCreatePromoteClass" method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">
		<h4 class="pageTitle bold">
				Add/Edit promotion class settings
		</h4>
		<hr/>
		<s:hidden name="tempString" cssClass='tempString' />
		<s:hidden name="usrChgedAcademicId" value="%{usrChgedAcademicId}"></s:hidden>
		<div class="panel-body">
		<span class="label label-danger">NOTE :</span>
		<div class="spaceDiv"></div>
			<ul>
				<li>For promotion of students, please select class and section in select box, if you don't find class in select box please type class and section. Format should be  <b>'I - A'</b>. If no section available for that class just type <b>'I'</b>. That class will be displayed in the next academicYear</li>
				<li>If you don't have any further classes to promote,Please select course completed option. </li>
				<li>Promote students process is based on the following settings . Please select Promoting classes of each class. </li>  
			</ul>
		</div>
		<div class="form-body">
		<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_3">
				<thead>
					<tr>
						<th>
							<b> Class Names (Medium) </b>
						</th>
						<th>
							<b> Promotable Class Names </b>
						</th>
						<th>
							<b> Promotable Class Medium </b>
						</th>
					</tr>
				</thead>
				<tbody>
						<s:iterator value="tempList">
							<tr class="promotionClassesDetails">
								<td id='<s:property value="promotionId"/>' class="promotionId">
									<span id='<s:property value='classSectionId'/>'
										class='classSectionId'></span>
									<s:property value="classAndSection" />
									<s:if test="%{medium != null && medium != ''}">
									(<s:property value="medium" />)
								</s:if>
									:
								</td>
								<s:if test="%{promoteProcessCompleted}">
									<td>
										<s:select list="studyClassList"
											listKey="classSectionAndMediumId" listValue="classAndSection" cssClass="form-control input-medium"
											id="promoteClassId%{classSectionId}" name="promotedClassId"
											onchange="$('input#text%{classSectionId}').val($(this).find('option:selected').text());javascript:generateMedium(this.value,%{classSectionId});"
											headerKey="completed" headerValue="Course completed"
											disabled="true"></s:select>
										<sj:textfield id="text%{classSectionId}"
											name="promotedClassAndSection" cssClass="form-control promoteClass"
											cssStyle="margin-left: -1px; margin-top: -51px; width: 207px;background-color : #FFF;display:inline-block;"
											disabled="true"></sj:textfield>
									</td>
									<td>
										<s:select list="objectList" listKey="id" listValue="name"
											id="promoteClassMedium%{classSectionId}" cssClass="form-control mediumId"
											name="promoteClassMediumId" disabled="true"></s:select>
									</td>
								</s:if>
								<s:else>
									<td>
										<s:select list="studyClassList"
											listKey="classSectionAndMediumId" listValue="classAndSection" cssClass="form-control input-medium promotedClassValue"
											id="promoteClassId%{classSectionId}" name="promotedClassId"
											onchange="$('input#text%{classSectionId}').val($(this).find('option:selected').text());javascript:generateMedium(this.value,%{classSectionId});"
											headerKey="completed" headerValue="Course completed"></s:select>
										<sj:textfield id="text%{classSectionId}"
											name="promotedClassAndSection" cssClass="form-control promoteClass"
											cssStyle="margin-left: -1px; margin-top: -51px; width: 207px;background-color : #FFF;display:inline-block;"></sj:textfield>
									</td>
									<td>
										<s:if test="%{promoteClassMediumId == 0}">
											<s:select list="objectList" listKey="id" listValue="name"
												id="promoteClassMedium%{classSectionId}" cssClass="form-control mediumId"
												headerKey="" headerValue="- select medium -" name="mediumId"></s:select>
										</s:if>
										<s:else>
											<s:select list="objectList" listKey="id" listValue="name"
												id="promoteClassMedium%{classSectionId}" cssClass="form-control mediumId"
												headerKey="" headerValue="- select medium -"
												name="promoteClassMediumId"></s:select>
										</s:else>
									</td>
								</s:else>
							</tr>
						</s:iterator>
				</tbody>
			</table>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-4">
			<sj:submit   targets="promoteStudentsContent" value="Submit"
					cssClass="submitBt btn blue small" formIds="createPromoteClass" onBeforeTopics="createEditPromotionDetails"
					indicator="indicator" validate="true" />
			</div></div>
			</div>
	</s:form>
	</s:if>
		<s:else>
			<div class="alert alert-info">
				Classes are not available.
			</div>
		</s:else>
<script type="text/javascript">
$(document).ready(function() {
  $("select.promotedClassValue").prepend("<option value='selected'>- select class -</option>");
  $("select.promotedClassValue").val($("select.promotedClassValue option:first").val());
	$.destroyTopic('createEditPromotionDetails');
	$.subscribe('createEditPromotionDetails', function(event, data) {
		generatePromotionClassData();
		//return true;
	});
});
function generatePromotionClassData() {
	var classSectionId = '';
	var promotedClass = '';
	var promotionId;
	var mediumId;
	var jsonObj = [];
	$('tr.promotionClassesDetails').each(function() {
		classSectionId = $(this).find("span.classSectionId").attr('id');
		promotedClass = $(this).find(".promoteClass").val();
		promotionId = $(this).find("td.promotionId").attr("id");
		mediumId = $(this).find(".mediumId").val();
		if (isNonEmpty(classSectionId) && classSectionId !=0 && isNonEmpty(mediumId))
			jsonObj.push( {
				"classSectionId" : classSectionId,
				"promotedClass" : promotedClass,
				"promotionId" : promotionId,
				"mediumId" : mediumId
			});
	});
	$('.tempString').val(JSON.stringify(jsonObj));
}
function generateMedium(clasSecMed,classSectionId){
	if(isNonEmpty(clasSecMed) && isNonEmpty(classSectionId)){
		var ids = clasSecMed.split(":");
		if(isNonEmpty(ids)){
			if(ids.length == 2)
				$('#promoteClassMedium'+classSectionId).val(ids[1]);
		}
	}
}
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
    $(this).animate({ scrollTop: 0 }, 10);
});
</script>
