<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div role="progressbar" class="progress progress-striped" id="bar">
	<div class="progress-bar progress-bar-success" style="width: 47%;">
	</div>
</div>
<div id="admissionCategoriesContent">
	<s:form id="addAdmissionCategoriesFM"
		action="ajaxGetFeeParticularSettingsSteps" theme="simple"
		name="myform" namespace="/schoolfee" cssClass="from-horizontal">
		<s:hidden name="tempString" cssClass="admissionsCategoriesData"></s:hidden>
		<h4 class="pageTitle bold">
			Categories
		</h4>
		<div class="panel-body col-md-12">
			<span class="label label-danger"> NOTE: </span> &nbsp; We can create new categories, remove the existing categories or we can use the same fee categories for selected Academic year.
		</div>
		<s:if
			test="%{schoolCategoriesList != null && !schoolCategoriesList.isEmpty()}">
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
				<thead>
					<tr>
						<th>
							Category
						</th>
						<th>
							Delete
						</th>
					</tr>
				</thead>
				<tbody id="categoriesCont">
						<s:iterator value="schoolCategoriesList">
							<!-- <span class="admissionCategoriesData">  -->
							
							<tr class='admissionCategoriesData' >
								<%-- <span id='<s:property value='id'/>' class='categoryId'></span> --%>
								<s:hidden name="categoryId" cssClass="categoryId" value="%{id}"></s:hidden>
								<td>
									<s:if test='%{categoryName != "General"}'>
										<sj:textfield name="categoryName" id="categoryName%{id}"
											cssClass="form-control  required categoryName"></sj:textfield>
									</s:if>
									<s:else>
										<sj:textfield name="categoryName" id="categoryName%{id}" disabled="true"
											cssClass="form-control required categoryName"></sj:textfield>
									</s:else>
								</td>
							<td>
								<s:if test='%{categoryName != "General"}'>
										<s:url id="removeSchoolCategory"
											action="ajaxRemoveSchoolCategory" includeParams="all"
											escapeAmp="false" namespace="/schoolfee">
											<s:param name="schoolCategory.id" value="%{id}" />
											<s:param name="targetString">creatAdmissionConDetails</s:param>
										</s:url>
										<s:div cssClass="btn btn-xs red"
											onclick="javascript:confirmDialogWithTarget(this,'admissionCategoriesContent');"
											id='%{removeSchoolCategory}' 
											title="Delete this category">
											<i class="fa fa-times"></i> Delete
													</s:div>
								</s:if>
							</td>
						</tr>
						<!-- </span> -->
					</s:iterator>
				</tbody>
			</table>
			<div class="grid_14">
				<a href="javascript:void(0)"
					onclick="javascript:showAdmissionsCategoryCreationForm();"
					class="btn green"><i class="fa fa-plus"></i> <b>Add Categories</b>
				</a>
			</div>
			<div class="spaceDiv"></div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					
					<sj:submit   targets="admissionSettingsDiv" value="Next"
						cssClass="submitBt btn blue" formIds="addAdmissionCategoriesFM" validate="true"
						onBeforeTopics="addAdmissionsFeeValidation" indicator="indicator"/>
					<s:url id="doAddAdmissionsClassesLink"
						action="ajaxGetAdmissionClassDetails" includeParams="all"
						escapeAmp="false" namespace="/admin">
					</s:url>
					<sj:a href="%{doAddAdmissionsClassesLink}" cssClass="btn default"
						targets="admissionSettingsDiv"> <i class="fa fa-mail-reply"></i> Back</sj:a>
				</div>
			</div>
		</s:if>
		<s:else>
			<div id="categoriesCont">
			</div>
			<div class="col-nd-12">
				<a href="javascript:void(0)"
					onclick="javascript:showAdmissionsCategoryCreationForm();"
					class="btn green"><i class="fa fa-plus"></i> <b>Add Categories</b> </a>
			</div>
			<div class="spaceDiv"></div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit   targets="admissionSettingsDiv" value="Next"
						cssClass="submitBt btn blue" formIds="addAdmissionCategoriesFM" validate="true"
						onBeforeTopics="addAdmissionsFeeValidation" indicator="indicator"/>
					<s:url id="doAddAdmissionsClassesLink"
						action="ajaxGetAdmissionClassDetails" includeParams="all"
						escapeAmp="false" namespace="/admin">
					</s:url>
					<sj:a href="%{doAddAdmissionsClassesLink}" cssClass="btn default"
						targets="admissionSettingsDiv">Back</sj:a>
				</div>
			</div>
			<div class="spaceDiv">&nbsp;</div>
			<div class="alert alert-info">
				Categories are not created. You can able to create categories
				through + link.
			</div>
		</s:else>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('div#admissionSettingDiv ul > li').removeClass('active');
	$('div#admissionSettingDiv ul > li#AdmissionSettings3').nextAll().removeClass('active');
	$('div#admissionSettingDiv ul > li#AdmissionSettings3').prevAll().addClass('done');	
	$("li#AdmissionSettings3").addClass('active');
	
		$.subscribe('addAdmissionsFeeValidation', function(event, data) {
		var categoryId = 0;
		var categoryName = '';
		var jsonObj = [];
		if($('#addAdmissionCategoriesFM').valid()){
			$('tr.admissionCategoriesData').each(
			function() {
				//alert("ganesh each")
				if(!$(this).find('.categoryName').is(":disabled")){
					//alert("ganesh")
					categoryId = $(this).find(".categoryId").val();
					//alert($(this).html());
					categoryName = $(this).find('.categoryName').val();
					//alert("categoryId :"+categoryId+" categoryName :"+categoryName)
					if(isNonEmpty(categoryId)){
						jsonObj.push( {
									"categoryName" : categoryName,
									"categoryId" : categoryId
								});
					}
				}
			});
			//alert(JSON.stringify(jsonObj))
		$('.admissionsCategoriesData').val(JSON.stringify(jsonObj));
			return true;
		}else
			event.originalEvent.options.submit=false;
	});
});
	var rowCount =1;
	function showAdmissionsCategoryCreationForm(){
		$("tbody#categoriesCont").append('<tr class="admissionCategoriesData" id="admiss'+rowCount+'"><td><s:hidden name="categoryId" cssClass="categoryId" value="0"></s:hidden>'+
				'<label class="control-label"><span class="required">*</span>Fee Category</label>'+
				'<input type="text" class="form-control input-medium required categoryName" id="categoryName'+rowCount+'" name="categoryName"/></td>'+
				'</td><td><a title="Delete" class="btn btn-xs red" id="removeCategory" onclick="removeCategoryRow('+rowCount+')"><i class="fa fa-times"></i> Delete</a></td></tr>');
			rowCount++;
			
		/* $("tbody#categoriesCont").append('<span class="admissionCategoriesData"><span class="categoryId" id="0"></span><div id="results" class="col-md-12 note note-info">'+
										'<div class="col-md-12">'+
										'<div class="col-md-7"><input type="text" class="form-control input-medium required categoryName" id="categoryName'+rowCount+'" name="categoryName'+rowCount+'"/>'+
										'<span class="hintMessage">Multiple category names should be seperated by comma.</span>'+
										'</div>'+
										'<div class="col-md-1"><a title="Delete" class="btn btn-xs red" id="removeCategory" onclick="removeCategoryRow(this)"><i class="fa fa-trash-o"></i> Delete</a></div>'+
										'</div> </div>'+
										'</div></span>');
										rowCount++; */
	}
	
	function removeCategoryRow(rowNum){
		$('#admiss'+rowNum).remove();
	}
	</script>